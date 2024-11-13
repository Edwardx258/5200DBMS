package sponsor.model;
public class Job {
    private String caseNumber;
    private int employerId;
    private String jobTitle;
    private String pwSocCode;
    private String pwSocTitle;
    private String pwSkillLevel;
    private double pwWage;
    private double wageOfferFrom;
    private double wageOfferTo;
    private String wageOfferUnitOfPay;

    public Job(String caseNumber, int employerId, String jobTitle, String pwSocCode, String pwSocTitle,
               String pwSkillLevel, double pwWage, double wageOfferFrom, double wageOfferTo, String wageOfferUnitOfPay) {
        this.caseNumber = caseNumber;
        this.employerId = employerId;
        this.jobTitle = jobTitle;
        this.pwSocCode = pwSocCode;
        this.pwSocTitle = pwSocTitle;
        this.pwSkillLevel = pwSkillLevel;
        this.pwWage = pwWage;
        this.wageOfferFrom = wageOfferFrom;
        this.wageOfferTo = wageOfferTo;
        this.wageOfferUnitOfPay = wageOfferUnitOfPay;
    }

    // Getters and Setters
    public String getCaseNumber() { return caseNumber; }
    public int getEmployerId() { return employerId; }
    public String getJobTitle() { return jobTitle; }
    public String getPwSocCode() { return pwSocCode; }
    public String getPwSocTitle() { return pwSocTitle; }
    public String getPwSkillLevel() { return pwSkillLevel; }
    public double getPwWage() { return pwWage; }
    public double getWageOfferFrom() { return wageOfferFrom; }
    public double getWageOfferTo() { return wageOfferTo; }
    public String getWageOfferUnitOfPay() { return wageOfferUnitOfPay; }
}
