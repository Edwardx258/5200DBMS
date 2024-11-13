package sponsor.model;
public class H1B {
    private int h1bId;
    private String caseNumber;
    private String capStatus;
    private String wageLevel;
    private String pwSocCode;

    public H1B(int h1bId, String caseNumber, String capStatus, String wageLevel, String pwSocCode) {
        this.h1bId = h1bId;
        this.caseNumber = caseNumber;
        this.capStatus = capStatus;
        this.wageLevel = wageLevel;
        this.pwSocCode = pwSocCode;
    }

    // Getters and Setters
    public int getH1bId() { return h1bId; }
    public void setH1bId(int h1bId) { this.h1bId = h1bId; }

    public String getCaseNumber() { return caseNumber; }
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }

    public String getCapStatus() { return capStatus; }
    public void setCapStatus(String capStatus) { this.capStatus = capStatus; }

    public String getWageLevel() { return wageLevel; }
    public void setWageLevel(String wageLevel) { this.wageLevel = wageLevel; }

    public String getPwSocCode() { return pwSocCode; }
    public void setPwSocCode(String pwSocCode) { this.pwSocCode = pwSocCode; }
}
