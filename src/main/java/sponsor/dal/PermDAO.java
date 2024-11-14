package sponsor.dal;
import sponsor.model.Perm;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PermDAO {
    private static PermDAO instance = null;
    protected ConnectionManager connectionManager;

    protected PermDAO() {
        connectionManager = new ConnectionManager();
    }

    public static PermDAO getInstance() {
        if (instance == null) {
            instance = new PermDAO();
        }
        return instance;
    }

    // CREATE method
    public Perm create(Perm perm) throws SQLException {
        String insertPerm = "INSERT INTO Perm (CASE_NUMBER, APPROVAL_RATE, AVG_PROCESSING_TIME, INDUSTRY) VALUES (?, ?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertPerm, Statement.RETURN_GENERATED_KEYS)) {

            insertStmt.setString(1, perm.getCaseNumber());
            insertStmt.setDouble(2, perm.getApprovalRate());
            insertStmt.setInt(3, perm.getAvgProcessingTime());
            insertStmt.setString(4, perm.getIndustry());
            insertStmt.executeUpdate();

            try (ResultSet resultKey = insertStmt.getGeneratedKeys()) {
                if (resultKey.next()) {
                    perm.setPermId(resultKey.getInt(1));
                }
            }
            return perm;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // READ by ID
    public Perm getPermById(int permId) throws SQLException {
        String selectPerm = "SELECT * FROM Perm WHERE PERM_ID = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectPerm)) {

            selectStmt.setInt(1, permId);
            try (ResultSet results = selectStmt.executeQuery()) {
                if (results.next()) {
                    return mapRowToPerm(results);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    // READ all perms
    public List<Perm> getAllPerms() throws SQLException {
        List<Perm> permsList = new ArrayList<>();
        String selectAll = "SELECT * FROM Perm";
        try (Connection connection = connectionManager.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet results = stmt.executeQuery(selectAll)) {

            while (results.next()) {
                permsList.add(mapRowToPerm(results));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return permsList;
    }

    // UPDATE method
    public Perm updatePerm(Perm perm) throws SQLException {
        String updatePerm = "UPDATE Perm SET CASE_NUMBER = ?, APPROVAL_RATE = ?, AVG_PROCESSING_TIME = ?, INDUSTRY = ? WHERE PERM_ID = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement updateStmt = connection.prepareStatement(updatePerm)) {

            updateStmt.setString(1, perm.getCaseNumber());
            updateStmt.setDouble(2, perm.getApprovalRate());
            updateStmt.setInt(3, perm.getAvgProcessingTime());
            updateStmt.setString(4, perm.getIndustry());
            updateStmt.setInt(5, perm.getPermId());
            updateStmt.executeUpdate();
            return perm;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // DELETE method
    public Perm deletePerm(int permId) throws SQLException {
        String deletePerm = "DELETE FROM Perm WHERE PERM_ID = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement deleteStmt = connection.prepareStatement(deletePerm)) {

            deleteStmt.setInt(1, permId);
            int affectedRows = deleteStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No records available to delete for Perm ID=" + permId);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Helper method to map a ResultSet row to a Perm object
    private Perm mapRowToPerm(ResultSet results) throws SQLException {
        int permId = results.getInt("PERM_ID");
        String caseNumber = results.getString("CASE_NUMBER");
        double approvalRate = results.getDouble("APPROVAL_RATE");
        int avgProcessingTime = results.getInt("AVG_PROCESSING_TIME");
        String industry = results.getString("INDUSTRY");

        return new Perm(permId, caseNumber, approvalRate, avgProcessingTime, industry);
    }
}