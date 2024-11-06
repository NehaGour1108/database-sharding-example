package com.example.ShardingApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.String;

public class UserRepository {
    private final DatabaseRouter router;

    public UserRepository(DatabaseRouter router) {
        this.router = router;
    }

    public void addUser(int id, String name) throws SQLException {
        DatabaseShard shard = router.getShardByUserId(id);
        System.out.println("shard " + shard.getConnection());
        try (Connection conn = shard.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (id, name) VALUES (?, ?)")) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.executeUpdate();
            System.out.println("Added user: " + name + " to shard " + (id % 2));
        }
    }

    public String getUserById(int id) throws SQLException {
        DatabaseShard shard = router.getShardByUserId(id);
        try (Connection conn = shard.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT name FROM users WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        }
        return null;
    }
}