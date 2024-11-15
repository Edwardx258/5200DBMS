package sponsor.dal;import sponsor.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDao {
	private static JobDao instance = null;
    protected ConnectionManager connectionManager;
    private Connection connection = null;

    protected JobDao() {
        connectionManager = new ConnectionManager();
        try {
			connection = connectionManager.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static JobDao getInstance() {
        if (instance == null) {
            instance = new JobDao();
        }
        return instance;
    }

    public Job create(Job job) throws SQLException {
        String insertJob = "INSERT INTO Job(CASE_NUMBER, EMPLOYER_ID, JOB_TITLE, PW_SOC_CODE, PW_SOC_TITLE, PW_SKILL_LEVEL, PW_WAGE, WAGE_OFFER_FROM, WAGE_OFFER_TO, WAGE_OFFER_UNIT_OF_PAY) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertJob);//) {

            statement.setString(1, job.getCaseNumber());
            statement.setInt(2, job.getEmployerId());
            statement.setString(3, job.getJobTitle());
            statement.setString(4, job.getPwSocCode());
            statement.setString(5, job.getPwSocTitle());
            statement.setString(6, job.getPwSkillLevel());
            statement.setDouble(7, job.getPwWage());
            statement.setDouble(8, job.getWageOfferFrom());
            statement.setDouble(9, job.getWageOfferTo());
            statement.setString(10, job.getWageOfferUnitOfPay());
            statement.executeUpdate();

            return job;
//        }
    }

    public Job getJobByCaseNumber(String caseNumber) throws SQLException {
        String selectJob = "SELECT * FROM Job WHERE CASE_NUMBER=?";
//        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectJob);//) {

            statement.setString(1, caseNumber);
            ResultSet results = statement.executeQuery();


            if (results.next()) {
                int employerId = results.getInt("EMPLOYER_ID");
                String jobTitle = results.getString("JOB_TITLE");
                String pwSocCode = results.getString("PW_SOC_CODE");
                String pwSocTitle = results.getString("PW_SOC_TITLE");
                String pwSkillLevel = results.getString("PW_SKILL_LEVEL");
                double pwWage = results.getDouble("PW_WAGE");
                double wageOfferFrom = results.getDouble("WAGE_OFFER_FROM");
                double wageOfferTo = results.getDouble("WAGE_OFFER_TO");
                String wageOfferUnitOfPay = results.getString("WAGE_OFFER_UNIT_OF_PAY");

                return new Job(caseNumber, employerId, jobTitle, pwSocCode, pwSocTitle, pwSkillLevel, pwWage, wageOfferFrom, wageOfferTo, wageOfferUnitOfPay);
            }

//        }
        return null;
    }

    public Job delete(Job job) throws SQLException {
        String deleteJob = "DELETE FROM Job WHERE CASE_NUMBER=?";
//        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteJob);//) {

            statement.setString(1, job.getCaseNumber());
            statement.executeUpdate();
            return null;
//        }
    }
    
    public List<Job> getJobsByEmployerId(int employerId) throws SQLException {
        List<Job> jobs = new ArrayList<>();
        String selectByEmployer = "SELECT * FROM Job WHERE EMPLOYER_ID=?";
        
//        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectByEmployer);//) {
            
            statement.setInt(1, employerId);
            ResultSet results = statement.executeQuery();

            
            while (results.next()) {
                Job job = new Job(
                    results.getString("CASE_NUMBER"),
                    results.getInt("EMPLOYER_ID"),
                    results.getString("JOB_TITLE"),
                    results.getString("PW_SOC_CODE"),
                    results.getString("PW_SOC_TITLE"),
                    results.getString("PW_SKILL_LEVEL"),
                    results.getDouble("PW_WAGE"),
                    results.getDouble("WAGE_OFFER_FROM"),
                    results.getDouble("WAGE_OFFER_TO"),
                    results.getString("WAGE_OFFER_UNIT_OF_PAY")
                );
                jobs.add(job);
            }

//        }
        return jobs;
    }
}
