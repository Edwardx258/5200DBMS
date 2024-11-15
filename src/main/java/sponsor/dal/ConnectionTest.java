package sponsor.dal;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * main() runner, used for the app demo.
 *
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class ConnectionTest {

  public static void main(String[] args) throws SQLException {
    ConnectionManager connectionManager = new ConnectionManager();
    try (Connection connection = connectionManager.getConnection()) {
      if (connection != null && !connection.isClosed()) {
        System.out.println("Database connection successful!");
      } else {
        System.out.println("Failed to connect to the database.");
      }
    } catch (SQLException e) {
      System.out.println("Error occurred while connecting to the database:");
      e.printStackTrace();
    }
  }

}
