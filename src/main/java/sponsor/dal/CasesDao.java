package sponsor.dal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import sponsor.model.Cases;

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

    public void createCase(Cases caseObj) throws SQLException {
        String query = "INSERT INTO Cases (CASE_NUMBER, CASE_STATUS, RECEIVED_DATE, DECISION_DATE, ORIG_FILE_DATE, PREVIOUS_SWA_CASE_NUMBER_STATE, SCHD_A_SHEEPHERDER) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, caseObj.getCaseNumber());
            stmt.setString(2, caseObj.getCaseStatus());
            stmt.setDate(3, caseObj.getReceivedDate());
            stmt.setDate(4, caseObj.getDecisionDate());
            stmt.setDate(5, caseObj.getOrigFileDate());
            stmt.setString(6, caseObj.getPreviousSwaCaseNumberState());
            stmt.setString(7, caseObj.getSchdASheepherder());
            stmt.executeUpdate();
        }
    }

    // READ the data by the cases number
    public Cases getCaseByNumber(String caseNumber) throws SQLException {
        String query = "SELECT * FROM Cases WHERE CASE_NUMBER = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, caseNumber);
            ResultSet rs = stmt.executeQuery();
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
            return null;
        }
    }

    // READ all the cases
    public List<Cases> getAllCases() throws SQLException {
        String query = "SELECT * FROM Cases";
        List<Cases> casesList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
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
        }
        return casesList;
    }

    public void updateCase(Cases caseObj) throws SQLException {
        String query = "UPDATE Cases SET CASE_STATUS = ?, RECEIVED_DATE = ?, DECISION_DATE = ?, ORIG_FILE_DATE = ?, PREVIOUS_SWA_CASE_NUMBER_STATE = ?, SCHD_A_SHEEPHERDER = ? WHERE CASE_NUMBER = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, caseObj.getCaseStatus());
            stmt.setDate(2, caseObj.getReceivedDate());
            stmt.setDate(3, caseObj.getDecisionDate());
            stmt.setDate(4, caseObj.getOrigFileDate());
            stmt.setString(5, caseObj.getPreviousSwaCaseNumberState());
            stmt.setString(6, caseObj.getSchdASheepherder());
            stmt.setString(7, caseObj.getCaseNumber());
            stmt.executeUpdate();
        }
    }

    public void deleteCase(String caseNumber) throws SQLException {
        String query = "DELETE FROM Cases WHERE CASE_NUMBER = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, caseNumber);
            stmt.executeUpdate();
        }
    }
}