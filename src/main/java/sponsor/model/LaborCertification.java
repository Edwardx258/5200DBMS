package sponsor.model;
import java.math.BigDecimal;

public class LaborCertification {
    private int certificationId;
    private String caseNumber;
    private BigDecimal approvalRate;
    private Integer avgProcessingTime;
    private String industry;

    // Constructor for creating a new LaborCertification (without certificationId)
    public LaborCertification(String caseNumber, BigDecimal approvalRate, Integer avgProcessingTime, String industry) {
        this.caseNumber = caseNumber;
        this.approvalRate = approvalRate;
        this.avgProcessingTime = avgProcessingTime;
        this.industry = industry;
    }

    // Constructor for reading an existing LaborCertification (with certificationId)
    public LaborCertification(int certificationId, String caseNumber, BigDecimal approvalRate, Integer avgProcessingTime, String industry) {
        this.certificationId = certificationId;
        this.caseNumber = caseNumber;
        this.approvalRate = approvalRate;
        this.avgProcessingTime = avgProcessingTime;
        this.industry = industry;
    }

    // Getters and Setters
    public int getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(int certificationId) {
        this.certificationId = certificationId;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public BigDecimal getApprovalRate() {
        return approvalRate;
    }

    public void setApprovalRate(BigDecimal approvalRate) {
        this.approvalRate = approvalRate;
    }

    public Integer getAvgProcessingTime() {
        return avgProcessingTime;
    }

    public void setAvgProcessingTime(Integer avgProcessingTime) {
        this.avgProcessingTime = avgProcessingTime;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}