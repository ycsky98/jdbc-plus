package org.jdbc.plus.actuator;

import java.sql.PreparedStatement;

/**
 * @author yangcong 执行器规范
 */
public interface Executor<T> {

    /**
     * 执行Sql
     * 
     * @param sql
     * @return
     */
    public boolean execute(PreparedStatement preparedStatement, boolean commit);

    /**
     * 执行单个查询sql
     * 
     * @param sql
     * @return
     */
    public T executeSelectOne(PreparedStatement preparedStatement, Class<T> class1);

    /**
     * 修改语句执行
     * 
     * @param preparedStatement
     * @param commit
     * @return
     */
    public int executeUpdate(PreparedStatement preparedStatement, boolean commit);
}
