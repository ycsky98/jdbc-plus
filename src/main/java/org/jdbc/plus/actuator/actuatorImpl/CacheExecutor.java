package org.jdbc.plus.actuator.actuatorImpl;

import java.sql.Connection;

/**
 * @author yangcong
 * 
 *         缓存执行器
 */
public class CacheExecutor<T> extends BaseExecutor<T> {

    /**
     * 构造
     * 
     * @param connection 一个连接(一个事务)
     */
    public CacheExecutor(Connection connection) {
        super(connection);
    }

    public CacheExecutor() {
    }

    // 待完成

}
