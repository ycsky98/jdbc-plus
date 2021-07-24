package org.jdbc.plus.annotation;

/**
 * @author yancgong
 * 
 *         事务注解
 */
public @interface Transaction {

    /**
     * 事务回滚异常,默认Exception
     * 
     * @return
     */
    Class<? extends Throwable> exception() default Exception.class;
}
