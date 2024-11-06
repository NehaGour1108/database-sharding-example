# Database Sharding in Java

## Overview
This project demonstrates database sharding and routing using Java and H2. It routes data based on user IDs to different database shards.

## Features
- **Database Sharding**: Distributes users across two database shards.
- **CRUD Operations**: Adds users to sharded databases.
- **H2 Database**: Uses an in-memory H2 database.

## Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/database-shards.git
   cd database-shards
   ```

2. **Build with Maven**:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   ```bash
   mvn exec:java
   ```

## Key Classes
- **`DatabaseRouter.java`**: Routes operations to the correct shard based on `userId`.
- **`DatabaseShard.java`**: Manages database connections for each shard.
- **`ShardingApp.java`**: Main entry point to initialize shards and perform operations.

## Access H2 Console (Optional)
For web access to the H2 database:
```
http://localhost:8080/h2-console
```
- **JDBC URL**: `jdbc:h2:mem:shard0`
- **Username**: `sa`
- **Password**: (blank)

## Troubleshooting
- **Table Not Found**: Create the `users` table in H2 with:
  ```sql
  CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(255));
  ```
