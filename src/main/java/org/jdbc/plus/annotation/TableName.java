package org.jdbc.plus.annotation;

/**
 * @author yangcong 表映射注解
 */
public @interface TableName {

    String tableName() default "";
}
