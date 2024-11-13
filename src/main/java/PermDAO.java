import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PermDAO {
    public void createPerm(Perm perm) throws SQLException {
        String query = "INSERT INTO Perm (CASE_NUMBER, APPROVAL_RATE, AVG_PROCESSING_TIME, INDUSTRY) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, perm.getCaseNumber());
            stmt.setDouble(2, perm.getApprovalRate());
            stmt.setInt(3, perm.getAvgProcessingTime());
            stmt.setString(4, perm.getIndustry());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    perm.setPermId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // READ the data by perm id
    public Perm getPermById(int permId) throws SQLException {
        String query = "SELECT * FROM Perm WHERE PERM_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, permId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Perm(
                    rs.getInt("PERM_ID"),
                    rs.getString("CASE_NUMBER"),
                    rs.getDouble("APPROVAL_RATE"),
                    rs.getInt("AVG_PROCESSING_TIME"),
                    rs.getString("INDUSTRY")
                );
            }
            return null;
        }
    }

    // READ all the perms
    public List<Perm> getAllPerms() throws SQLException {
        String query = "SELECT * FROM Perm";
        List<Perm> permsList = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                permsList.add(new Perm(
                    rs.getInt("PERM_ID"),
                    rs.getString("CASE_NUMBER"),
                    rs.getDouble("APPROVAL_RATE"),
                    rs.getInt("AVG_PROCESSING_TIME"),
                    rs.getString("INDUSTRY")
                ));
            }
        }
        return permsList;
    }

    public void updatePerm(Perm perm) throws SQLException {
        String query = "UPDATE Perm SET CASE_NUMBER = ?, APPROVAL_RATE = ?, AVG_PROCESSING_TIME = ?, INDUSTRY = ? WHERE PERM_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, perm.getCaseNumber());
            stmt.setDouble(2, perm.getApprovalRate());
            stmt.setInt(3, perm.getAvgProcessingTime());
            stmt.setString(4, perm.getIndustry());
            stmt.setInt(5, perm.getPermId());
            stmt.executeUpdate();
        }
    }

    public void deletePerm(int permId) throws SQLException {
        String query = "DELETE FROM Perm WHERE PERM_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, permId);
            stmt.executeUpdate();
        }
    }
}