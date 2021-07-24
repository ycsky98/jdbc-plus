package org.jdbc.plus.actuator;

/**
 * @author yangcong 执行器
 */
public interface Execute<T> {

    /**
     * 执行Sql
     * 
     * @param sql
     * @return
     */
    public T execute(String sql);

    /**
     * 执行单个查询sql
     * 
     * @param sql
     * @return
     */
    public T executeSelectOne(String sql);
}
