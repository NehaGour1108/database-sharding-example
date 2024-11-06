package com.example.ShardingApp;
import java.lang.String;
import java. lang. Exception;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DatabaseRouter router = new DatabaseRouter();

        try {
            // Initialize database tables in shards
            router.initializeShards();

            // Create a UserRepository instance
            UserRepository userRepository = new UserRepository(router);

            // Add users with different IDs (even IDs -> shard 0, odd IDs -> shard 1)
             // Goes to shard 1
            userRepository.addUser(2, "Bob");    // Goes to shard 0
            userRepository.addUser(1, "Alice");
            userRepository.addUser(3, "Charlie"); // Goes to shard 1
            userRepository.addUser(4, "David");  // Goes to shard 0

            // Retrieve users
            System.out.println("User with ID 1: " + userRepository.getUserById(1));
            System.out.println("User with ID 2: " + userRepository.getUserById(2));
            System.out.println("User with ID 3: " + userRepository.getUserById(3));
            System.out.println("User with ID 4: " + userRepository.getUserById(4));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}