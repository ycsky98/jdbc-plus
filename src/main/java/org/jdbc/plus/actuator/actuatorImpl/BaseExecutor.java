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
            } else {
                // 数据回滚
                this.connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            if (resultSet.next()) {

                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        field.set(t, resultSet.getObject(field.getName()));
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return t;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
