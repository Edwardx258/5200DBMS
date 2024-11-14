package sponsor.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sponsor.model.Sponsorship;

public class SponsorshipDao {
    private static SponsorshipDao instance = null;
    protected ConnectionManager connectionManager;

    protected SponsorshipDao() {
        connectionManager = new ConnectionManager();
    }

    public static SponsorshipDao getInstance() {
        if (instance == null) {
            instance = new SponsorshipDao();
        }
        return instance;
    }

    public Sponsorship create(Sponsorship sponsorship) throws SQLException {
        String insertSponsorship = "INSERT INTO Sponsorship(CASE_NUMBER, SPONSORSHIP_TYPE, JOB_TITLE) VALUES(?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertSponsorship, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, sponsorship.getCaseNumber());
            statement.setString(2, sponsorship.getSponsorshipType());
            statement.setString(3, sponsorship.getJobTitle());
            statement.executeUpdate();

            ResultSet resultKey = statement.getGeneratedKeys();
            int sponsorshipId = -1;
            if (resultKey.next()) {
                sponsorshipId = resultKey.getInt(1);
            }
            sponsorship.setSponsorshipId(sponsorshipId);
            return sponsorship;
        }
    }

    public Sponsorship getSponsorshipById(int sponsorshipId) throws SQLException {
        String selectSponsorship = "SELECT * FROM Sponsorship WHERE SPONSORSHIP_ID=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectSponsorship)) {

            statement.setInt(1, sponsorshipId);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                String caseNumber = results.getString("CASE_NUMBER");
                String sponsorshipType = results.getString("SPONSORSHIP_TYPE");
                String jobTitle = results.getString("JOB_TITLE");
                return new Sponsorship(sponsorshipId, caseNumber, sponsorshipType, jobTitle);
            }
        }
        return null;
    }

    public Sponsorship delete(Sponsorship sponsorship) throws SQLException {
        String deleteSponsorship = "DELETE FROM Sponsorship WHERE SPONSORSHIP_ID=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteSponsorship)) {

            statement.setInt(1, sponsorship.getSponsorshipId());
            statement.executeUpdate();
            return null;
        }
    }
}
