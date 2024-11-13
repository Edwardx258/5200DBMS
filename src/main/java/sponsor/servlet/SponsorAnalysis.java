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
    
    @Override
    public void init() throws ServletException {
        employerDao = EmployerDao.getInstance();
        sponsorshipDao = SponsorshipDao.getInstance();
        h1bDao = H1BDao.getInstance();
        permDao = PermDAO.getInstance();
        jobDao = JobDao.getInstance();
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        try {
            // Get the analysis timeframe from request, default to "all"
            String timeframe = req.getParameter("timeframe");
            if (timeframe == null || timeframe.trim().isEmpty()) {
                timeframe = "all";
            }
            messages.put("timeframe", timeframe);
            
            // Get analysis results
            List<CompanySponsorshipSummary> summaries = getCompanySponsorships(timeframe);
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
        // Handle POST the same way as GET
        doGet(req, resp);
    }
    
    private List<CompanySponsorshipSummary> getCompanySponsorships(String timeframe) throws SQLException {
        // Execute SQL to get comprehensive sponsorship data
        String query = """
            SELECT 
                e.EMPLOYER_ID,
                e.EMPLOYER_NAME,
                e.EMPLOYER_CITY,
                e.EMPLOYER_STATE_PROVINCE,
                s.SPONSORSHIP_TYPE,
                j.JOB_TITLE,
                c.CASE_STATUS,
                COUNT(*) as TOTAL_APPLICATIONS
            FROM Employer e
            JOIN Job j ON e.EMPLOYER_ID = j.EMPLOYER_ID
            JOIN Sponsorship s ON j.CASE_NUMBER = s.CASE_NUMBER
            JOIN Cases c ON s.CASE_NUMBER = c.CASE_NUMBER
            GROUP BY 
                e.EMPLOYER_ID, 
                e.EMPLOYER_NAME,
                s.SPONSORSHIP_TYPE,
                j.JOB_TITLE,
                c.CASE_STATUS
            HAVING COUNT(*) >= 5
            ORDER BY COUNT(*) DESC
        """;
        
        // Execute query using ConnectionManager and process results
        Map<Integer, CompanySponsorshipSummary> summaryMap = new HashMap<>();
        
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                int employerId = rs.getInt("EMPLOYER_ID");
                
                // Create or get existing summary
                CompanySponsorshipSummary summary = summaryMap.computeIfAbsent(
                    employerId,
                    id -> new CompanySponsorshipSummary(
                        rs.getString("EMPLOYER_NAME"),
                        rs.getString("EMPLOYER_CITY"),
                        rs.getString("EMPLOYER_STATE_PROVINCE")
                    )
                );
                
                // Update summary with this record
                summary.addApplication(
                    rs.getString("SPONSORSHIP_TYPE"),
                    rs.getString("JOB_TITLE"),
                    rs.getString("CASE_STATUS"),
                    rs.getInt("TOTAL_APPLICATIONS")
                );
            }
        }
        
        // Sort by total applications and return
        return summaryMap.values().stream()
            .sorted((a, b) -> b.getTotalApplications() - a.getTotalApplications())
            .toList();
    }
}

// Helper class to store summary data
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