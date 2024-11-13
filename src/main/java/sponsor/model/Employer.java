package sponsor.model;
public class Employer {
    private int employerId;
    private String employerName;
    private String employerAddress;
    private String employerCity;
    private String employerStateProvince;
    private String employerPostalCode;
    private String employerCountry;

    public Employer(int employerId, String employerName, String employerAddress, String employerCity,
                    String employerStateProvince, String employerPostalCode, String employerCountry) {
        this.employerId = employerId;
        this.employerName = employerName;
        this.employerAddress = employerAddress;
        this.employerCity = employerCity;
        this.employerStateProvince = employerStateProvince;
        this.employerPostalCode = employerPostalCode;
        this.employerCountry = employerCountry;
    }

    // Getters and Setters
    public int getEmployerId() { return employerId; }
    public String getEmployerName() { return employerName; }
    public String getEmployerAddress() { return employerAddress; }
    public String getEmployerCity() { return employerCity; }
    public String getEmployerStateProvince() { return employerStateProvince; }
    public String getEmployerPostalCode() { return employerPostalCode; }
    public String getEmployerCountry() { return employerCountry; }
}
