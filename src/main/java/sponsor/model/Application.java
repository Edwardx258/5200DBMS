package sponsor.model;
import java.sql.Date;

public class Application {
    private int applicationId;
    private String caseNumber;
    private Date receivedDate;
    private Date decisionDate;
    private Date origFileDate;
    private String caseStatus;

    public Application(int applicationId, String caseNumber, Date receivedDate, Date decisionDate, Date origFileDate, String caseStatus) {
        this.applicationId = applicationId;
        this.caseNumber = caseNumber;
        this.receivedDate = receivedDate;
        this.decisionDate = decisionDate;
        this.origFileDate = origFileDate;
        this.caseStatus = caseStatus;
    }

    // Getters and setters
    public int getApplicationId() { return applicationId; }
    public void setApplicationId(int applicationId) { this.applicationId = applicationId; }

    public String getCaseNumber() { return caseNumber; }
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }

    public Date getReceivedDate() { return receivedDate; }
    public void setReceivedDate(Date receivedDate) { this.receivedDate = receivedDate; }

    public Date getDecisionDate() { return decisionDate; }
    public void setDecisionDate(Date decisionDate) { this.decisionDate = decisionDate; }

    public Date getOrigFileDate() { return origFileDate; }
    public void setOrigFileDate(Date origFileDate) { this.origFileDate = origFileDate; }

    public String getCaseStatus() { return caseStatus; }
    public void setCaseStatus(String caseStatus) { this.caseStatus = caseStatus; }
}