package org.jdbc.plus;

import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;

import org.jdbc.plus.actuator.actuatorImpl.BusExecutor;
import org.jdbc.plus.rules.build.SqlWhereBuild;
import org.jdbc.plus.rules.rule.Logic;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        SqlWhereBuild build = new SqlWhereBuild();
        BusExecutor<Integer> busExecutor = new BusExecutor<>(hikariConfig, build);

        busExecutor.execute("SELECT COUNT(1) FROM admin", new Logic().eq("username", "admin").end());

        busExecutor.executeUpdate("UPDATE admin", new Logic().set("username", "zhangsan").set("password", "abcde"));

        busExecutor.getConnection().commit();
    }
}
