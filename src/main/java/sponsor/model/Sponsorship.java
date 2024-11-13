package sponsor.model;
public class Sponsorship {
    private int sponsorshipId;
    private String caseNumber;
    private String sponsorshipType;
    private String jobTitle;

    public Sponsorship(int sponsorshipId, String caseNumber, String sponsorshipType, String jobTitle) {
        this.sponsorshipId = sponsorshipId;
        this.caseNumber = caseNumber;
        this.sponsorshipType = sponsorshipType;
        this.jobTitle = jobTitle;
    }

    // Getters and Setters
    public int getSponsorshipId() { return sponsorshipId; }
    public void setSponsorshipId(int sponsorshipId) { this.sponsorshipId = sponsorshipId; }

    public String getCaseNumber() { return caseNumber; }
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }

    public String getSponsorshipType() { return sponsorshipType; }
    public void setSponsorshipType(String sponsorshipType) { this.sponsorshipType = sponsorshipType; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
}
