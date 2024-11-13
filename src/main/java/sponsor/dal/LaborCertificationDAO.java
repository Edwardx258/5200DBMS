package sponsor.dal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaborCertificationDAO {
    public void createLaborCertification(LaborCertification laborCert) throws SQLException {
        String query = "INSERT INTO LaborCertification (CASE_NUMBER, APPROVAL_RATE, AVG_PROCESSING_TIME, INDUSTRY) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, laborCert.getCaseNumber());
            stmt.setDouble(2, laborCert.getApprovalRate());
            stmt.setInt(3, laborCert.getAvgProcessingTime());
            stmt.setString(4, laborCert.getIndustry());
            stmt.executeUpdate();

            // Get the generated CERTIFICATION_ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    laborCert.setCertificationId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // READ the data by certification id
    public LaborCertification getLaborCertificationById(int certificationId) throws SQLException {
        String query = "SELECT * FROM LaborCertification WHERE CERTIFICATION_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, certificationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new LaborCertification(
                    rs.getInt("CERTIFICATION_ID"),
                    rs.getString("CASE_NUMBER"),
                    rs.getDouble("APPROVAL_RATE"),
                    rs.getInt("AVG_PROCESSING_TIME"),
                    rs.getString("INDUSTRY")
                );
            }
            return null;
        }
    }

    // READ all the labor certifications
    public List<LaborCertification> getAllLaborCertifications() throws SQLException {
        String query = "SELECT * FROM LaborCertification";
        List<LaborCertification> laborCertList = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                laborCertList.add(new LaborCertification(
                    rs.getInt("CERTIFICATION_ID"),
                    rs.getString("CASE_NUMBER"),
                    rs.getDouble("APPROVAL_RATE"),
                    rs.getInt("AVG_PROCESSING_TIME"),
                    rs.getString("INDUSTRY")
                ));
            }
        }
        return laborCertList;
    }

    public void updateLaborCertification(LaborCertification laborCert) throws SQLException {
        String query = "UPDATE LaborCertification SET CASE_NUMBER = ?, APPROVAL_RATE = ?, AVG_PROCESSING_TIME = ?, INDUSTRY = ? WHERE CERTIFICATION_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, laborCert.getCaseNumber());
            stmt.setDouble(2, laborCert.getApprovalRate());
            stmt.setInt(3, laborCert.getAvgProcessingTime());
            stmt.setString(4, laborCert.getIndustry());
            stmt.setInt(5, laborCert.getCertificationId());
            stmt.executeUpdate();
        }
    }

    public void deleteLaborCertification(int certificationId) throws SQLException {
        String query = "DELETE FROM LaborCertification WHERE CERTIFICATION_ID = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, certificationId);
            stmt.executeUpdate();
        }
    }
}

