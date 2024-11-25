# **Database Sharding and Routing Example**

This repository demonstrates a simple **Database Sharding** and **Routing** solution using Java and H2 in-memory databases. The project routes user data to specific database shards based on a routing strategy, showcasing how sharding can enhance database scalability and manageability.

---

## **Features**
- Shards user data across multiple databases (H2 in-memory).
- Implements a **user ID-based routing** strategy:
  - **Even user IDs** → Shard 0.
  - **Odd user IDs** → Shard 1.
- Provides basic CRUD operations for sharded databases.

---

## **Repository Structure**
```plaintext
src/main/java/com/example/ShardingApp/
├── DatabaseShard.java        # Represents an individual database shard.
├── DatabaseRouter.java       # Routes database requests to the correct shard.
├── UserRepository.java       # Handles user-related database operations.
└── Main.java                 # Entry point for initializing and testing the sharding system.
```

---

## **Setup and Usage**

### **Prerequisites**
- JDK 11 or higher.
- Maven or an IDE such as IntelliJ IDEA or Eclipse.

### **Clone the Repository**
```bash
git clone https://github.com/<your-repo-name>/DatabaseShardingApp.git
cd DatabaseShardingApp
```

### **Run the Application**
1. Open the project in your IDE.
2. Run the `Main.java` file.
3. Observe console output for:
   - User data being added to the correct shards.
   - User retrieval based on ID.

---

## **How It Works**

### **Sharding Mechanism**
- Two H2 in-memory database shards (`shard0` and `shard1`) are initialized.
- A user ID-based routing strategy directs:
  - **Even user IDs** → `shard0`.
  - **Odd user IDs** → `shard1`.

### **Main Flow**
1. Initialize shards and create `users` tables.
2. Add users:
   - For example, user with ID `1` goes to `shard1`, and ID `2` goes to `shard0`.
3. Retrieve users:
   - Queries are routed to the correct shard to fetch user data.

---

## **Example Output**
```plaintext
Added user: Alice to shard 1
Added user: Bob to shard 0
Added user: Charlie to shard 1
Added user: David to shard 0
User with ID 1: Alice
User with ID 2: Bob
User with ID 3: Charlie
User with ID 4: David
```

---

## **Key Classes**
### **1. DatabaseShard**
Represents a single shard and provides database connection management.

### **2. DatabaseRouter**
Routes database operations to the correct shard based on user ID.

### **3. UserRepository**
Handles adding and retrieving user data, leveraging the `DatabaseRouter` to interact with the correct shard.

### **4. Main**
Entry point for initializing the system, adding users, and retrieving user data.

---

## **Project Highlights**
- **Scalable:** Easily extend the solution by adding more shards.
- **Lightweight:** Uses H2 in-memory databases for simplicity.
- **Customizable:** Routing logic can be adapted to different scenarios (e.g., hash-based or range-based).

---

## **Future Enhancements**
- Add support for range-based or hash-based sharding.
- Implement shard health monitoring.
- Add fault tolerance for database failures.
