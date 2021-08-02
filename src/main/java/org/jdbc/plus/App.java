package org.jdbc.plus;

import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;

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
        build.sqlBuild("SELECT * FROM admin", new Logic().eq("column", 1).eq("column2", 2), null);
    }
}
