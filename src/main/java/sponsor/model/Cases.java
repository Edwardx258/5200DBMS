package sponsor.model;
import java.sql.Date;

/**
 * Cases is a simple, plain old Java object (POJO) that represents a case.
 */
public class Cases {
    private String caseNumber;
    private String caseStatus;
    private Date receivedDate;
    private Date decisionDate;
    private Date origFileDate;
    private String previousSwaCaseNumberState;
    private String schdASheepherder;

    // Constructor with all fields
    public Cases(String caseNumber, String caseStatus, Date receivedDate, Date decisionDate, Date origFileDate, String previousSwaCaseNumberState, String schdASheepherder) {
        this.caseNumber = caseNumber;
        this.caseStatus = caseStatus;
        this.receivedDate = receivedDate;
        this.decisionDate = decisionDate;
        this.origFileDate = origFileDate;
        this.previousSwaCaseNumberState = previousSwaCaseNumberState;
        this.schdASheepherder = schdASheepherder;
    }

    // Getters and setters
    public String getCaseNumber() { return caseNumber; }
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }

    public String getCaseStatus() { return caseStatus; }
    public void setCaseStatus(String caseStatus) { this.caseStatus = caseStatus; }

    public Date getReceivedDate() { return receivedDate; }
    public void setReceivedDate(Date receivedDate) { this.receivedDate = receivedDate; }

    public Date getDecisionDate() { return decisionDate; }
    public void setDecisionDate(Date decisionDate) { this.decisionDate = decisionDate; }

    public Date getOrigFileDate() { return origFileDate; }
    public void setOrigFileDate(Date origFileDate) { this.origFileDate = origFileDate; }

    public String getPreviousSwaCaseNumberState() { return previousSwaCaseNumberState; }
    public void setPreviousSwaCaseNumberState(String previousSwaCaseNumberState) { this.previousSwaCaseNumberState = previousSwaCaseNumberState; }

    public String getSchdASheepherder() { return schdASheepherder; }
    public void setSchdASheepherder(String schdASheepherder) { this.schdASheepherder = schdASheepherder; }
}