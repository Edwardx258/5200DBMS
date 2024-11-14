package sponsor.dal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sponsor.model.User;

public class UsersDAO {

    // Single pattern: instantiation is limited to one object.
    private static UsersDAO instance = null;
    protected ConnectionManager connectionManager;

    protected UsersDAO() {
        connectionManager = new ConnectionManager();
    }

    public static UsersDAO getInstance() {
        if (instance == null) {
            instance = new UsersDAO();
        }
        return instance;
    }

    public void createUser(User user) throws SQLException {
        String query = "INSERT INTO Users (USERNAME, EMAIL, PASSWORD_HASH, CREATED_AT) VALUES (?, ?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setTimestamp(4, user.getCreatedAt());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // READ the data by the userId
    public User getUserById(int userId) throws SQLException {
        String query = "SELECT * FROM Users WHERE USER_ID = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("USER_ID"),
                    rs.getString("USERNAME"),
                    rs.getString("EMAIL"),
                    rs.getString("PASSWORD_HASH"),
                    rs.getTimestamp("CREATED_AT")
                );
            }
            return null;
        }
    }

    // READ all the users
    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM Users";
        List<User> usersList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                usersList.add(new User(
                    rs.getInt("USER_ID"),
                    rs.getString("USERNAME"),
                    rs.getString("EMAIL"),
                    rs.getString("PASSWORD_HASH"),
                    rs.getTimestamp("CREATED_AT")
                ));
            }
        }
        return usersList;
    }

    public void updateUser(User user) throws SQLException {
        String query = "UPDATE Users SET USERNAME = ?, EMAIL = ?, PASSWORD_HASH = ? WHERE USER_ID = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setInt(4, user.getUserId());
            stmt.executeUpdate();
        }
    }

    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM Users WHERE USER_ID = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }
}