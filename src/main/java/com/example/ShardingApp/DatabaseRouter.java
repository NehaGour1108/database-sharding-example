package com.example.ShardingApp;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class DatabaseRouter {
    private final Map<Integer, DatabaseShard> shardMap = new HashMap<>();

    public DatabaseRouter() {
        // Define two sample shards (H2 in-memory databases)
        shardMap.put(0, new DatabaseShard("jdbc:h2:mem:shard0", "sa", ""));
        shardMap.put(1, new DatabaseShard("jdbc:h2:mem:shard1", "sa", ""));
    }

    // Route by user ID (e.g., even ID to shard 0, odd ID to shard 1)
    public DatabaseShard getShardByUserId(int userId) {
        return shardMap.get(userId % shardMap.size());
    }

    public void initializeShards() throws SQLException {
        // Initialize tables in each shard
        for (DatabaseShard shard : shardMap.values()) {
            try (Connection conn = shard.getConnection();
                 Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY, name VARCHAR(255))");
                ResultSet rs = stmt.executeQuery("SHOW TABLES");
                // Print the table names
                while (rs.next()) {
                    System.out.println("Table: " + rs.getString("TABLE_NAME")+ "shard " + shard.getConnection());
                }
            }
        }
    }
}