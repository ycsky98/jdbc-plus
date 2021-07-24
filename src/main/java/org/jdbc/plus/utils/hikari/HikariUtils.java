package org.jdbc.plus.utils.hikari;

import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;

/**
 * @author yangcong Hikari连接池工具类
 */
public class HikariUtils {

    /**
     * 构建HikariCP连接池配置
     * 
     * @param properties
     * @return
     */
    public static HikariConfig createHikariConfig(Properties properties) {
        return new HikariConfig(properties);
    }

}
