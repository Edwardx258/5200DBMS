import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H1BDao {
    private static H1BDao instance = null;

    protected H1BDao() {}

    public static H1BDao getInstance() {
        if (instance == null) {
            instance = new H1BDao();
        }
        return instance;
    }

    public H1B create(H1B h1b) throws SQLException {
        String insertH1B = "INSERT INTO H1B(CASE_NUMBER, CAP_STATUS, WAGE_LEVEL, PW_SOC_CODE) VALUES(?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertH1B, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, h1b.getCaseNumber());
            statement.setString(2, h1b.getCapStatus());
            statement.setString(3, h1b.getWageLevel());
            statement.setString(4, h1b.getPwSocCode());
            statement.executeUpdate();

            ResultSet resultKey = statement.getGeneratedKeys();
            int h1bId = -1;
            if (resultKey.next()) {
                h1bId = resultKey.getInt(1);
            }
            h1b.setH1bId(h1bId);
            return h1b;
        }
    }

    public H1B getH1BById(int h1bId) throws SQLException {
        String selectH1B = "SELECT * FROM H1B WHERE H1B_ID=?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectH1B)) {

            statement.setInt(1, h1bId);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                String caseNumber = results.getString("CASE_NUMBER");
                String capStatus = results.getString("CAP_STATUS");
                String wageLevel = results.getString("WAGE_LEVEL");
                String pwSocCode = results.getString("PW_SOC_CODE");
                return new H1B(h1bId, caseNumber, capStatus, wageLevel, pwSocCode);
            }
        }
        return null;
    }

    public H1B delete(H1B h1b) throws SQLException {
        String deleteH1B = "DELETE FROM H1B WHERE H1B_ID=?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteH1B)) {

            statement.setInt(1, h1b.getH1bId());
            statement.executeUpdate();
            return null;
        }
    }
}
