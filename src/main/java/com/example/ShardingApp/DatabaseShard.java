package com.example.ShardingApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.String;

public class DatabaseShard {
    private final String url;
    private final String username;
    private final String password;

    public DatabaseShard(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
