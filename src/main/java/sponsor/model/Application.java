package sponsor.model;
import java.sql.Date;

/**
 * Application is a simple, plain old Java object (POJO) representing a record in the Application table.
 */
public class Application {
    protected int applicationId;
    protected String caseNumber;
    protected Date receivedDate;
    protected Date decisionDate;
    protected Date origFileDate;
    protected String caseStatus;

    // Constructor with all fields
    public Application(int applicationId, String caseNumber, Date receivedDate, Date decisionDate, Date origFileDate, String caseStatus) {
        this.applicationId = applicationId;
        this.caseNumber = caseNumber;
        this.receivedDate = receivedDate;
        this.decisionDate = decisionDate;
        this.origFileDate = origFileDate;
        this.caseStatus = caseStatus;
    }

    // Constructor without applicationId (useful for creating new Application records)
    public Application(String caseNumber, Date receivedDate, Date decisionDate, Date origFileDate, String caseStatus) {
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