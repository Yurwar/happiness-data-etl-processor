package com.kpi.fict.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static HikariConfig config = new HikariConfig("./src/main/resources/db.properties");
    private static HikariDataSource dataSource = new HikariDataSource(config);

    private ConnectionPool() {}

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException("Can not get a connection");
        }
    }
}
