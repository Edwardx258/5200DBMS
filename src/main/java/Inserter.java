import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Inserter {

    public static void main(String[] args) {
        try {
            // Testing CasesDAO
            CasesDao casesDAO = new CasesDao();
            Cases newCase = new Cases("C12345", "Open", Date.valueOf("2023-01-01"), Date.valueOf("2023-01-15"), Date.valueOf("2023-01-10"), "SWA001", "Yes");
            casesDAO.createCase(newCase);

            Cases retrievedCase = casesDAO.getCaseByNumber("C12345");
            System.out.println("Retrieved Case: " + retrievedCase.getCaseStatus());

            newCase.setCaseStatus("Closed");
            casesDAO.updateCase(newCase);
            System.out.println("Updated Case Status: " + casesDAO.getCaseByNumber("C12345").getCaseStatus());

            casesDAO.deleteCase("C12345");
            System.out.println("Deleted Case: " + (casesDAO.getCaseByNumber("C12345") == null));

            // Testing UsersDAO
            UsersDAO usersDAO = new UsersDAO();
            User newUser = new User(0, "testUser", "testuser@example.com", "hashedPassword", new Timestamp(System.currentTimeMillis()));
            usersDAO.createUser(newUser);

            User retrievedUser = usersDAO.getUserById(newUser.getUserId());
            System.out.println("Retrieved User: " + retrievedUser.getUsername());

            newUser.setUsername("updatedUser");
            usersDAO.updateUser(newUser);
            System.out.println("Updated User Name: " + usersDAO.getUserById(newUser.getUserId()).getUsername());

            usersDAO.deleteUser(newUser.getUserId());
            System.out.println("Deleted User: " + (usersDAO.getUserById(newUser.getUserId()) == null));

            // Testing ApplicationsDAO
            ApplicationsDAO applicationsDAO = new ApplicationsDAO();
            Application newApplication = new Application(0, "C12345", Date.valueOf("2023-02-01"), Date.valueOf("2023-02-15"), Date.valueOf("2023-02-10"), "Pending");
            applicationsDAO.createApplication(newApplication);

            Application retrievedApplication = applicationsDAO.getApplicationById(newApplication.getApplicationId());
            System.out.println("Retrieved Application: " + retrievedApplication.getCaseStatus());

            newApplication.setCaseStatus("Approved");
            applicationsDAO.updateApplication(newApplication);
            System.out.println("Updated Application Status: " + applicationsDAO.getApplicationById(newApplication.getApplicationId()).getCaseStatus());

            applicationsDAO.deleteApplication(newApplication.getApplicationId());
            System.out.println("Deleted Application: " + (applicationsDAO.getApplicationById(newApplication.getApplicationId()) == null));

            // Testing PermDAO
            PermDAO permDAO = new PermDAO();
            Perm newPerm = new Perm(0, "C12345", 85.5, 30, "IT");
            permDAO.createPerm(newPerm);

            Perm retrievedPerm = permDAO.getPermById(newPerm.getPermId());
            System.out.println("Retrieved Perm: " + retrievedPerm.getIndustry());

            newPerm.setIndustry("Healthcare");
            permDAO.updatePerm(newPerm);
            System.out.println("Updated Perm Industry: " + permDAO.getPermById(newPerm.getPermId()).getIndustry());

            permDAO.deletePerm(newPerm.getPermId());
            System.out.println("Deleted Perm: " + (permDAO.getPermById(newPerm.getPermId()) == null));

            // Testing LaborCertificationDAO
            LaborCertificationDAO laborCertificationDAO = new LaborCertificationDAO();
            LaborCertification newLaborCertification = new LaborCertification(0, "C12345", 90.0, 45, "Engineering");
            laborCertificationDAO.createLaborCertification(newLaborCertification);

            LaborCertification retrievedLaborCertification = laborCertificationDAO.getLaborCertificationById(newLaborCertification.getCertificationId());
            System.out.println("Retrieved Labor Certification: " + retrievedLaborCertification.getIndustry());

            newLaborCertification.setIndustry("Construction");
            laborCertificationDAO.updateLaborCertification(newLaborCertification);
            System.out.println("Updated Labor Certification Industry: " + laborCertificationDAO.getLaborCertificationById(newLaborCertification.getCertificationId()).getIndustry());

            laborCertificationDAO.deleteLaborCertification(newLaborCertification.getCertificationId());
            System.out.println("Deleted Labor Certification: " + (laborCertificationDAO.getLaborCertificationById(newLaborCertification.getCertificationId()) == null));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}