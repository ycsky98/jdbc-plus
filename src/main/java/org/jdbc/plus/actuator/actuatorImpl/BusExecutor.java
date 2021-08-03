package org.jdbc.plus.actuator.actuatorImpl;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;

import org.jdbc.plus.rules.build.SqlWhereBuild;
import org.jdbc.plus.rules.rule.Logic;

/**
 * @author yangcong
 * 
 *         执行器总线
 */
public class BusExecutor<T> extends CacheExecutor<T> {

    /**
     * hikari配置
     */
    private HikariConfig hikariConfig;

    /**
     * 默认不自动提交
     */
    private Boolean commit = false;

    /**
     * sql条件编译
     */
    private SqlWhereBuild sqlWhereBuild;

    public BusExecutor(HikariConfig hikariConfig, SqlWhereBuild sqlWhereBuild) throws SQLException {
        this.hikariConfig = hikariConfig;
        super.setConnection(hikariConfig.getDataSource().getConnection());
        sqlWhereBuild.setConnection(super.getConnection());
        this.sqlWhereBuild = sqlWhereBuild;
    }

    /**
     * 设置自动提交
     * 
     * @param commit
     */
    public void autoCommit(Boolean commit) {
        this.commit = commit;
    }

    /**
     * 执行器
     * 
     * @param befSql
     * @param logic
     * @return
     */
    public boolean execute(String befSql, Logic logic) {
        boolean flag = false;
        try {
            // 调用父级
            flag = super.execute(this.sqlWhereBuild.sqlBuild(befSql, logic), this.commit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 单体sql查询器
     * 
     * @param befSql
     * @param logic
     * @param bean
     * @return
     */
    public T executeSelectOne(String befSql, Logic logic, Class<T> bean) {
        T t = null;
        try {
            t = super.executeSelectOne(this.sqlWhereBuild.sqlBuild(befSql, logic), bean);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t;
    }

    public int executeUpdate() {
        return 0;
    }
}
