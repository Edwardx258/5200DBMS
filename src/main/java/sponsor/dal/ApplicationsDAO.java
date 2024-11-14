package sponsor.dal;
import sponsor.model.Application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationsDAO {
    private static ApplicationsDAO instance = null;
    protected ConnectionManager connectionManager;

    protected ApplicationsDAO() {
        connectionManager = new ConnectionManager();
    }

    public static ApplicationsDAO getInstance() {
        if (instance == null) {
            instance = new ApplicationsDAO();
        }
        return instance;
    }

    public Application create(Application application) {
        String insertApplication = "INSERT INTO Application (CASE_NUMBER, RECEIVED_DATE, DECISION_DATE, ORIG_FILE_DATE, CASE_STATUS) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(insertApplication, Statement.RETURN_GENERATED_KEYS)) {

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
            return application;
        } catch (SQLException e) {
            // Log or rethrow a custom exception
            throw new RuntimeException("Error creating application", e);
        }
    }

    public Application getApplicationById(int applicationId) {
        String selectApplication = "SELECT * FROM Application WHERE APPLICATION_ID = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(selectApplication)) {

            stmt.setInt(1, applicationId);
            try (ResultSet rs = stmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching application by ID", e);
        }
        return null;
    }

    public List<Application> getAllApplications() {
        String selectAllApplications = "SELECT * FROM Application";
        List<Application> applicationsList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(selectAllApplications)) {

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
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all applications", e);
        }
        return applicationsList;
    }

    public Application updateApplication(Application application) {
        String updateApplication = "UPDATE Application SET CASE_NUMBER = ?, RECEIVED_DATE = ?, DECISION_DATE = ?, ORIG_FILE_DATE = ?, CASE_STATUS = ? WHERE APPLICATION_ID = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(updateApplication)) {

            stmt.setString(1, application.getCaseNumber());
            stmt.setDate(2, application.getReceivedDate());
            stmt.setDate(3, application.getDecisionDate());
            stmt.setDate(4, application.getOrigFileDate());
            stmt.setString(5, application.getCaseStatus());
            stmt.setInt(6, application.getApplicationId());
            stmt.executeUpdate();
            return application;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating application", e);
        }
    }

    public void deleteApplication(int applicationId) {
        String deleteApplication = "DELETE FROM Application WHERE APPLICATION_ID = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(deleteApplication)) {

            stmt.setInt(1, applicationId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting application", e);
        }
    }
}