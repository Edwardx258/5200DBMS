package sponsor.model;
import java.sql.Date;

public class Perm {
    private int permId;
    private String caseNumber;
    private double approvalRate;
    private int avgProcessingTime;
    private String industry;

    public Perm() {}

    public Perm(int permId, String caseNumber, double approvalRate, int avgProcessingTime, String industry) {
        this.permId = permId;
        this.caseNumber = caseNumber;
        this.approvalRate = approvalRate;
        this.avgProcessingTime = avgProcessingTime;
        this.industry = industry;
    }

    // Getters and setters
    public int getPermId() { return permId; }
    public void setPermId(int permId) { this.permId = permId; }

    public String getCaseNumber() { return caseNumber; }
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }

    public double getApprovalRate() { return approvalRate; }
    public void setApprovalRate(double approvalRate) { this.approvalRate = approvalRate; }

    public int getAvgProcessingTime() { return avgProcessingTime; }
    public void setAvgProcessingTime(int avgProcessingTime) { this.avgProcessingTime = avgProcessingTime; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }
}