package sponsor.model;
public class Worker {
    private int workerId;
    private String countryOfCitizenship;
    private String classOfAdmission;
    private String foreignWorkerEducation;
    private String foreignWorkerInstitution;
    private String foreignWorkerMajor;
    private String employerDeclarationTitle;

    public Worker(int workerId, String countryOfCitizenship, String classOfAdmission, 
                  String foreignWorkerEducation, String foreignWorkerInstitution, 
                  String foreignWorkerMajor, String employerDeclarationTitle) {
        this.workerId = workerId;
        this.countryOfCitizenship = countryOfCitizenship;
        this.classOfAdmission = classOfAdmission;
        this.foreignWorkerEducation = foreignWorkerEducation;
        this.foreignWorkerInstitution = foreignWorkerInstitution;
        this.foreignWorkerMajor = foreignWorkerMajor;
        this.employerDeclarationTitle = employerDeclarationTitle;
    }

    // Getters and Setters
    public int getWorkerId() { return workerId; }
    public String getCountryOfCitizenship() { return countryOfCitizenship; }
    public String getClassOfAdmission() { return classOfAdmission; }
    public String getForeignWorkerEducation() { return foreignWorkerEducation; }
    public String getForeignWorkerInstitution() { return foreignWorkerInstitution; }
    public String getForeignWorkerMajor() { return foreignWorkerMajor; }
    public String getEmployerDeclarationTitle() { return employerDeclarationTitle; }
}
