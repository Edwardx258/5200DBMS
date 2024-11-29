package sponsor.dal;import sponsor.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerDao {
	private static WorkerDao instance = null;
    protected ConnectionManager connectionManager;

    protected WorkerDao() {
        connectionManager = new ConnectionManager();
    }

    public static WorkerDao getInstance() {
        if (instance == null) {
            instance = new WorkerDao();
        }
        return instance;
    }

    public Worker create(Worker worker) throws SQLException {
        String insertWorker = "INSERT INTO Worker(COUNTRY_OF_CITIZENSHIP, CLASS_OF_ADMISSION, FOREIGN_WORKER_EDUCATION, FOREIGN_WORKER_INST_OF_ED, FOREIGN_WORKER_INFO_MAJOR, EMP_DECL_TITLE) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertWorker, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, worker.getCountryOfCitizenship());
            statement.setString(2, worker.getClassOfAdmission());
            statement.setString(3, worker.getForeignWorkerEducation());
            statement.setString(4, worker.getForeignWorkerInstitution());
            statement.setString(5, worker.getForeignWorkerMajor());
            statement.setString(6, worker.getEmployerDeclarationTitle());
            statement.executeUpdate();

            ResultSet resultKey = statement.getGeneratedKeys();
            int workerId = -1;
            if (resultKey.next()) {
                workerId = resultKey.getInt(1);
            }
            worker = new Worker(workerId, worker.getCountryOfCitizenship(), worker.getClassOfAdmission(), worker.getForeignWorkerEducation(), worker.getForeignWorkerInstitution(), worker.getForeignWorkerMajor(), worker.getEmployerDeclarationTitle());
            return worker;
        }
    }

    public Worker getWorkerById(int workerId) throws SQLException {
        String selectWorker = "SELECT * FROM Worker WHERE WORKER_ID=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectWorker)) {

            statement.setInt(1, workerId);
            ResultSet results = statement.executeQuery();

            if (results.next()) {
                String countryOfCitizenship = results.getString("COUNTRY_OF_CITIZENSHIP");
                String classOfAdmission = results.getString("CLASS_OF_ADMISSION");
                String foreignWorkerEducation = results.getString("FOREIGN_WORKER_EDUCATION");
                String foreignWorkerInstitution = results.getString("FOREIGN_WORKER_INST_OF_ED");
                String foreignWorkerMajor = results.getString("FOREIGN_WORKER_INFO_MAJOR");
                String employerDeclarationTitle = results.getString("EMP_DECL_TITLE");
                return new Worker(workerId, countryOfCitizenship, classOfAdmission, foreignWorkerEducation, foreignWorkerInstitution, foreignWorkerMajor, employerDeclarationTitle);
            }
        }
        return null;
    }

    public Worker delete(Worker worker) throws SQLException {
        String deleteWorker = "DELETE FROM Worker WHERE WORKER_ID=?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteWorker)) {

            statement.setInt(1, worker.getWorkerId());
            statement.executeUpdate();
            return null;
        }
    }
}
