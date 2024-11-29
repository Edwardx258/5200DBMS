package sponsor.dal;
import sponsor.model.LaborCertification;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LaborCertificationDAO {
    private static LaborCertificationDAO instance = null;
    protected ConnectionManager connectionManager;

    protected LaborCertificationDAO() {
        connectionManager = new ConnectionManager();
    }

    public static LaborCertificationDAO getInstance() {
        if (instance == null) {
            instance = new LaborCertificationDAO();
        }
        return instance;
    }

    // Create a new LaborCertification
    public LaborCertification create(LaborCertification certification) throws SQLException {
        String insertCertification = "INSERT INTO LaborCertification(CASE_NUMBER, APPROVAL_RATE, AVG_PROCESSING_TIME, INDUSTRY) VALUES(?, ?, ?, ?);";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertCertification, Statement.RETURN_GENERATED_KEYS)) {

            insertStmt.setString(1, certification.getCaseNumber());
            insertStmt.setBigDecimal(2, certification.getApprovalRate());
            insertStmt.setObject(3, certification.getAvgProcessingTime(), Types.INTEGER);
            insertStmt.setString(4, certification.getIndustry());
            insertStmt.executeUpdate();

            try (ResultSet resultKey = insertStmt.getGeneratedKeys()) {
                if (resultKey.next()) {
                    certification.setCertificationId(resultKey.getInt(1));
                }
            }
            return certification;
        }
    }

    // Get LaborCertification by certificationId
    public LaborCertification getLaborCertificationById(int certificationId) throws SQLException {
        String selectCertification = "SELECT * FROM LaborCertification WHERE CERTIFICATION_ID=?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectCertification)) {

            selectStmt.setInt(1, certificationId);
            try (ResultSet results = selectStmt.executeQuery()) {
                if (results.next()) {
                    String caseNumber = results.getString("CASE_NUMBER");
                    BigDecimal approvalRate = results.getBigDecimal("APPROVAL_RATE");
                    Integer avgProcessingTime = (Integer) results.getObject("AVG_PROCESSING_TIME");
                    String industry = results.getString("INDUSTRY");

                    return new LaborCertification(certificationId, caseNumber, approvalRate, avgProcessingTime, industry);
                }
            }
        }
        return null;
    }

    // Update approval rate of a LaborCertification
    public LaborCertification updateApprovalRate(LaborCertification certification, BigDecimal newApprovalRate) throws SQLException {
        String updateApprovalRate = "UPDATE LaborCertification SET APPROVAL_RATE=? WHERE CERTIFICATION_ID=?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement updateStmt = connection.prepareStatement(updateApprovalRate)) {

            updateStmt.setBigDecimal(1, newApprovalRate);
            updateStmt.setInt(2, certification.getCertificationId());
            updateStmt.executeUpdate();
            certification.setApprovalRate(newApprovalRate);
            return certification;
        }
    }

    // Delete a LaborCertification by certificationId
    public LaborCertification delete(LaborCertification certification) throws SQLException {
        String deleteCertification = "DELETE FROM LaborCertification WHERE CERTIFICATION_ID=?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement deleteStmt = connection.prepareStatement(deleteCertification)) {

            deleteStmt.setInt(1, certification.getCertificationId());
            int affectedRows = deleteStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No records available to delete for CERTIFICATION_ID=" + certification.getCertificationId());
            }
            return null;
        }
    }

    // Get all LaborCertifications for a specific case number
    public List<LaborCertification> getLaborCertificationsByCaseNumber(String caseNumber) throws SQLException {
        List<LaborCertification> certifications = new ArrayList<>();
        String selectCertifications = "SELECT * FROM LaborCertification WHERE CASE_NUMBER=?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectCertifications)) {

            selectStmt.setString(1, caseNumber);
            try (ResultSet results = selectStmt.executeQuery()) {
                while (results.next()) {
                    int certificationId = results.getInt("CERTIFICATION_ID");
                    BigDecimal approvalRate = results.getBigDecimal("APPROVAL_RATE");
                    Integer avgProcessingTime = (Integer) results.getObject("AVG_PROCESSING_TIME");
                    String industry = results.getString("INDUSTRY");

                    LaborCertification certification = new LaborCertification(certificationId, caseNumber, approvalRate, avgProcessingTime, industry);
                    certifications.add(certification);
                }
            }
        }
        return certifications;
    }
}
