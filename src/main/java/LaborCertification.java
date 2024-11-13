public class LaborCertification {
    private int certificationId;
    private String caseNumber;
    private double approvalRate;
    private int avgProcessingTime;
    private String industry;

    public LaborCertification(int certificationId, String caseNumber, double approvalRate, int avgProcessingTime, String industry) {
        this.certificationId = certificationId;
        this.caseNumber = caseNumber;
        this.approvalRate = approvalRate;
        this.avgProcessingTime = avgProcessingTime;
        this.industry = industry;
    }

    // Getters and setters
    public int getCertificationId() { return certificationId; }
    public void setCertificationId(int certificationId) { this.certificationId = certificationId; }

    public String getCaseNumber() { return caseNumber; }
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }

    public double getApprovalRate() { return approvalRate; }
    public void setApprovalRate(double approvalRate) { this.approvalRate = approvalRate; }

    public int getAvgProcessingTime() { return avgProcessingTime; }
    public void setAvgProcessingTime(int avgProcessingTime) { this.avgProcessingTime = avgProcessingTime; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }
}

