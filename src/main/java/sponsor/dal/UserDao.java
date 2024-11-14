package sponsor.dal;

import sponsor.model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underlying Users table in your
 * MySQL instance. This is used to store {@link Users} into your MySQL instance and 
 * retrieve {@link Users} from MySQL instance.
 */
public class UserDao {
    // Single pattern: instantiation is limited to one object.
    private static UserDao instance = null;
    protected ConnectionManager connectionManager;

    protected UserDao() {
        connectionManager = new ConnectionManager();
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public Users create(Users user) throws SQLException {
        String insertUser = "INSERT INTO Users(UserName, Email, PasswordHash, CreatedAt) VALUES(?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, user.getUsername());
            insertStmt.setString(2, user.getEmail());
            insertStmt.setString(3, user.getPasswordHash());
            insertStmt.setTimestamp(4, new Timestamp(user.getCreatedAt().getTime()));
            insertStmt.executeUpdate();

            ResultSet resultKey = insertStmt.getGeneratedKeys();
            int userId = -1;
            if (resultKey.next()) {
                userId = resultKey.getInt(1);
            }
            user.setUserId(userId);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public Users getUserByUserId(int userId) throws SQLException {
        String selectUser = "SELECT UserId, UserName, Email, PasswordHash, CreatedAt FROM Users WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectUser);
            selectStmt.setInt(1, userId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String username = results.getString("UserName");
                String email = results.getString("Email");
                String passwordHash = results.getString("PasswordHash");
                Date createdAt = new Date(results.getTimestamp("CreatedAt").getTime());
                Users user = new Users(userId, username, email, passwordHash, createdAt);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    public Users updateEmail(Users user, String newEmail) throws SQLException {
        String updateUser = "UPDATE Users SET Email=? WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateUser);
            updateStmt.setString(1, newEmail);
            updateStmt.setInt(2, user.getUserId());
            updateStmt.executeUpdate();
            user.setEmail(newEmail);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public Users delete(Users user) throws SQLException {
        String deleteUser = "DELETE FROM Users WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteUser);
            deleteStmt.setInt(1, user.getUserId());
            int affectedRows = deleteStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No records available to delete for UserId=" + user.getUserId());
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }

    public List<Users> getUsersByUsername(String username) throws SQLException {
        List<Users> usersList = new ArrayList<>();
        String selectUsers = "SELECT UserId, UserName, Email, PasswordHash, CreatedAt FROM Users WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectUsers);
            selectStmt.setString(1, username);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int userId = results.getInt("UserId");
                String resultUsername = results.getString("UserName");
                String email = results.getString("Email");
                String passwordHash = results.getString("PasswordHash");
                Date createdAt = new Date(results.getTimestamp("CreatedAt").getTime());
                Users user = new Users(userId, resultUsername, email, passwordHash, createdAt);
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return usersList;
    }
}
