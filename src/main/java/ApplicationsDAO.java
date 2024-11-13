import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationsDAO {
    public void createApplication(Application application) throws SQLException {
        String query = "INSERT INTO Application (CASE_NUMBER, RECEIVED_DATE, DECISION_DATE, ORIG_FILE_DATE, CASE_STATUS) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, application.getCaseNumber());
            stmt.setDate(2, application.getReceivedDate());
            stmt.setDate(3, application.getDecisionDate());
            stmt.setDate(4, application.getOrigFileDate());
            stmt.setString(5, application.getCaseStatus());
            stmt.executeUpdate();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    application.setApplicationId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // READ the data by application Id
    public Application getApplicationById(int applicationId) throws SQLException {
        String query = "SELECT * FROM Application WHERE APPLICATION_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, applicationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Application(
                    rs.getInt("APPLICATION_ID"),
                    rs.getString("CASE_NUMBER"),
                    rs.getDate("RECEIVED_DATE"),
                    rs.getDate("DECISION_DATE"),
                    rs.getDate("ORIG_FILE_DATE"),
                    rs.getString("CASE_STATUS")
                );
            }
            return null;
        }
    }

    // READ all the applications
    public List<Application> getAllApplications() throws SQLException {
        String query = "SELECT * FROM Application";
        List<Application> applicationsList = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                applicationsList.add(new Application(
                    rs.getInt("APPLICATION_ID"),
                    rs.getString("CASE_NUMBER"),
                    rs.getDate("RECEIVED_DATE"),
                    rs.getDate("DECISION_DATE"),
                    rs.getDate("ORIG_FILE_DATE"),
                    rs.getString("CASE_STATUS")
                ));
            }
        }
        return applicationsList;
    }

    public void updateApplication(Application application) throws SQLException {
        String query = "UPDATE Application SET CASE_NUMBER = ?, RECEIVED_DATE = ?, DECISION_DATE = ?, ORIG_FILE_DATE = ?, CASE_STATUS = ? WHERE APPLICATION_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, application.getCaseNumber());
            stmt.setDate(2, application.getReceivedDate());
            stmt.setDate(3, application.getDecisionDate());
            stmt.setDate(4, application.getOrigFileDate());
            stmt.setString(5, application.getCaseStatus());
            stmt.setInt(6, application.getApplicationId());
            stmt.executeUpdate();
        }
    }

    public void deleteApplication(int applicationId) throws SQLException {
        String query = "DELETE FROM Application WHERE APPLICATION_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, applicationId);
            stmt.executeUpdate();
        }
    }
}
