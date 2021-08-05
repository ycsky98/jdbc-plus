package org.jdbc.plus.actuator.actuatorImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.jdbc.plus.actuator.Executor;
import org.jdbc.plus.annotation.Param;

/**
 * @author yangcong
 * 
 *         根执行器(封装最基础的规范)
 */
public class BaseExecutor<T> implements Executor<T> {

    /**
     * 用于执行sql
     */
    private PreparedStatement preparedStatement;

    /**
     * 一个连接事务(使用时只传入一个连接,保证执行在一个事务内)
     */
    private Connection connection;

    public BaseExecutor(Connection connection) {
        this.connection = connection;
    }

    public BaseExecutor() {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return this.connection;
    }

    /**
     * 执行各种sql
     */
    @Override
    public boolean execute(PreparedStatement preparedStatement, boolean commit) {
        boolean flag = false;
        try {
            flag = preparedStatement.execute();
            // 如果是true直接提交
            if (commit) {
                this.connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 出现异常,数据回滚
            try {
                this.connection.rollback();
                flag = false;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 执行单条sql
     */
    @Override
    public T executeSelectOne(PreparedStatement preparedStatement, Class<T> bean) {
        ResultSet resultSet = null;

        T t = null;
        try {
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isLast()) {
                // 不是最后一个抛出异常
                throw new RuntimeException("It's going to be multiple rows");
            }

            Field[] fields = null;
            try {
                t = bean.getDeclaredConstructor().newInstance();
                // 拿到类属性
                fields = Arrays.stream(t.getClass().getDeclaredFields())
                        .filter(field -> field.isAnnotationPresent(Param.class)).toArray(Field[]::new);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
            Param param = null;
            if (resultSet.next()) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        if (!"".equals((param = field.getAnnotation(Param.class)).name())) {
                            field.set(t, resultSet.getObject(param.name()));
                        } else {
                            field.set(t, resultSet.getObject(field.getName()));
                        }
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            return t;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * update语句执行器
     */
    @Override
    public int executeUpdate(PreparedStatement preparedStatement, boolean commit) {
        int count = 0;
        try {
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // 出现异常,数据回滚
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return count;
    }

}
