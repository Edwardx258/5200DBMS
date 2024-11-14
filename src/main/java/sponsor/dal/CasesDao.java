package sponsor.dal;
import sponsor.model.Cases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CasesDao {
    private static CasesDao instance = null;
    protected ConnectionManager connectionManager;

    protected CasesDao() {
        connectionManager = new ConnectionManager();
    }

    public static CasesDao getInstance() {
        if (instance == null) {
            instance = new CasesDao();
        }
        return instance;
    }

    public Cases create(Cases caseObj) {
        String insertCase = "INSERT INTO Cases (CASE_NUMBER, CASE_STATUS, RECEIVED_DATE, DECISION_DATE, ORIG_FILE_DATE, PREVIOUS_SWA_CASE_NUMBER_STATE, SCHD_A_SHEEPHERDER) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(insertCase)) {

            stmt.setString(1, caseObj.getCaseNumber());
            stmt.setString(2, caseObj.getCaseStatus());
            stmt.setDate(3, caseObj.getReceivedDate());
            stmt.setDate(4, caseObj.getDecisionDate());
            stmt.setDate(5, caseObj.getOrigFileDate());
            stmt.setString(6, caseObj.getPreviousSwaCaseNumberState());
            stmt.setString(7, caseObj.getSchdASheepherder());
            stmt.executeUpdate();
            return caseObj;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating case", e);
        }
    }

    public Cases getCaseByNumber(String caseNumber) {
        String selectCase = "SELECT * FROM Cases WHERE CASE_NUMBER = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(selectCase)) {

            stmt.setString(1, caseNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cases(
                        rs.getString("CASE_NUMBER"),
                        rs.getString("CASE_STATUS"),
                        rs.getDate("RECEIVED_DATE"),
                        rs.getDate("DECISION_DATE"),
                        rs.getDate("ORIG_FILE_DATE"),
                        rs.getString("PREVIOUS_SWA_CASE_NUMBER_STATE"),
                        rs.getString("SCHD_A_SHEEPHERDER")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching case by number", e);
        }
        return null;
    }

    public List<Cases> getAllCases() {
        String selectAllCases = "SELECT * FROM Cases";
        List<Cases> casesList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(selectAllCases)) {

            while (rs.next()) {
                casesList.add(new Cases(
                    rs.getString("CASE_NUMBER"),
                    rs.getString("CASE_STATUS"),
                    rs.getDate("RECEIVED_DATE"),
                    rs.getDate("DECISION_DATE"),
                    rs.getDate("ORIG_FILE_DATE"),
                    rs.getString("PREVIOUS_SWA_CASE_NUMBER_STATE"),
                    rs.getString("SCHD_A_SHEEPHERDER")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all cases", e);
        }
        return casesList;
    }

    public Cases updateCase(Cases caseObj) {
        String updateCase = "UPDATE Cases SET CASE_STATUS = ?, RECEIVED_DATE = ?, DECISION_DATE = ?, ORIG_FILE_DATE = ?, PREVIOUS_SWA_CASE_NUMBER_STATE = ?, SCHD_A_SHEEPHERDER = ? WHERE CASE_NUMBER = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(updateCase)) {

            stmt.setString(1, caseObj.getCaseStatus());
            stmt.setDate(2, caseObj.getReceivedDate());
            stmt.setDate(3, caseObj.getDecisionDate());
            stmt.setDate(4, caseObj.getOrigFileDate());
            stmt.setString(5, caseObj.getPreviousSwaCaseNumberState());
            stmt.setString(6, caseObj.getSchdASheepherder());
            stmt.setString(7, caseObj.getCaseNumber());
            stmt.executeUpdate();
            return caseObj;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating case", e);
        }
    }

    public void deleteCase(String caseNumber) {
        String deleteCase = "DELETE FROM Cases WHERE CASE_NUMBER = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(deleteCase)) {

            stmt.setString(1, caseNumber);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting case", e);
        }
    }
}