package sponsor.tools;

import sponsor.dal.*;
import sponsor.model.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class Inserter {

    public static void main(String[] args) throws SQLException {
        // DAO instance
        UsersDAO usersDAO = new UsersDAO();

        // INSERT examples
        System.out.println("Inserting users...");
        User user1 = new User(0, "user1", "user1@example.com", "password1", new Timestamp(System.currentTimeMillis()));
        user1 = usersDAO.createUser(user1);

        User user2 = new User(0, "user2", "user2@example.com", "password2", new Timestamp(System.currentTimeMillis()));
        user2 = usersDAO.createUser(user2);

        User user3 = new User(0, "user3", "user3@example.com", "password3", new Timestamp(System.currentTimeMillis()));
        user3 = usersDAO.createUser(user3);

        System.out.println("Inserted users with IDs: " + user1.getUserId() + ", " + user2.getUserId() + ", " + user3.getUserId());

        // READ by ID example
        System.out.println("Fetching user by ID...");
        User fetchedUser = usersDAO.getUserById(user1.getUserId());
        if (fetchedUser != null) {
            System.out.println("Fetched user: " + fetchedUser.getUsername() + ", " + fetchedUser.getEmail());
        } else {
            System.out.println("User not found.");
        }

        // READ all users example
        System.out.println("Fetching all users...");
        List<User> allUsers = usersDAO.getAllUsers();
        for (User user : allUsers) {
            System.out.println("User: " + user.getUserId() + ", " + user.getUsername() + ", " + user.getEmail());
        }

        // UPDATE example
        System.out.println("Updating user...");
        user1.setUsername("updatedUser1");
        user1.setEmail("updatedUser1@example.com");
        usersDAO.updateUser(user1);
        System.out.println("Updated user: " + user1.getUsername() + ", " + user1.getEmail());

        // DELETE example
        System.out.println("Deleting user...");
        usersDAO.deleteUser(user2.getUserId());
        System.out.println("Deleted user with ID: " + user2.getUserId());

        // Verify deletion
        User deletedUser = usersDAO.getUserById(user2.getUserId());
        if (deletedUser == null) {
            System.out.println("User successfully deleted.");
        } else {
            System.out.println("Failed to delete user.");
        }
    }
}
