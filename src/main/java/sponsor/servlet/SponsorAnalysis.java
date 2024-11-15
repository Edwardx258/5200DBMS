package sponsor.servlet;

import sponsor.dal.*;
import sponsor.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sponsoranalysis")
public class SponsorAnalysis extends HttpServlet {
    protected EmployerDao employerDao;
    protected SponsorshipDao sponsorshipDao;
    protected H1BDao h1bDao;
    protected PermDAO permDao;
    protected JobDao jobDao;
    protected CasesDao casesDao;
    
    @Override
    public void init() throws ServletException {
        employerDao = EmployerDao.getInstance();
        sponsorshipDao = SponsorshipDao.getInstance();
        h1bDao = H1BDao.getInstance();
        permDao = PermDAO.getInstance();
        jobDao = JobDao.getInstance();
        casesDao = CasesDao.getInstance();
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        try {
            // Get all employers first
            List<Employer> employers = employerDao.getAllEmployers();
            List<CompanySponsorshipSummary> summaries = new ArrayList<>();
            for (Employer employer : employers) {
                // Get all jobs for this employer
                List<Job> jobs = jobDao.getJobsByEmployerId(employer.getEmployerId());
                if (jobs.size() >= 1) { // Only consider employers with 5+ applications
                    CompanySponsorshipSummary summary = new CompanySponsorshipSummary(
                        employer.getEmployerName(),
                        employer.getEmployerCity(),
                        employer.getEmployerStateProvince()
                    );
                    
                    // Process each job
                    for (Job job : jobs) {
                        String caseNumber = job.getCaseNumber();
                        Sponsorship sponsorship = sponsorshipDao.getSponsorshipByCaseNumber(caseNumber);
                        Cases caseDetails = casesDao.getCaseByNumber(caseNumber);
                        
                        if (sponsorship != null && caseDetails != null) {
                            summary.addApplication(
                                sponsorship.getSponsorshipType(),
                                job.getJobTitle(),
                                caseDetails.getCaseStatus(),
                                1
                            );
                        }
                    }
                    summaries.add(summary);
                }
            }
            
            // Sort summaries by total applications
            summaries.sort((a, b) -> b.getTotalApplications() - a.getTotalApplications());
            req.setAttribute("sponsorshipSummaries", summaries);
            messages.put("success", "Successfully retrieved sponsorship analysis.");
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        
        req.getRequestDispatcher("/SponsorAnalysis.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}

// CompanySponsorshipSummary stays the same
class CompanySponsorshipSummary {
    private String companyName;
    private String city;
    private String state;
    private Map<String, Integer> sponsorshipTypes = new HashMap<>();
    private Map<String, Integer> jobRoles = new HashMap<>();
    private int totalApplications = 0;
    private int approvedApplications = 0;
    
    public CompanySponsorshipSummary(String companyName, String city, String state) {
        this.companyName = companyName;
        this.city = city;
        this.state = state;
    }
    
    public void addApplication(String sponsorshipType, String jobTitle, 
                             String status, int count) {
        sponsorshipTypes.merge(sponsorshipType, count, Integer::sum);
        jobRoles.merge(jobTitle, count, Integer::sum);
        totalApplications += count;
        if ("CERTIFIED".equals(status)) {
            approvedApplications += count;
        }
    }
    
    // Getters
    public String getCompanyName() { return companyName; }
    public String getLocation() { return city + ", " + state; }
    public Map<String, Integer> getSponsorshipTypes() { return sponsorshipTypes; }
    public Map<String, Integer> getJobRoles() { return jobRoles; }
    public int getTotalApplications() { return totalApplications; }
    public double getSuccessRate() { 
        return totalApplications > 0 ? 
            (double) approvedApplications / totalApplications * 100 : 0; 
    }
}