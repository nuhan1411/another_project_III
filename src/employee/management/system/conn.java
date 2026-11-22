package employee.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("ALL")
public class conn {

    public Connection connection;
    public Statement statement;

    public conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Configurable database connection via environment variables with defaults
            String host = System.getenv("DB_HOST") != null ? System.getenv("DB_HOST") : "localhost";
            String port = System.getenv("DB_PORT") != null ? System.getenv("DB_PORT") : "3306";
            String dbName = System.getenv("DB_NAME") != null ? System.getenv("DB_NAME") : "employeemanagement";
            String user = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";
            String password = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "rifat@194rain";

            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName
                    + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

            System.out.println("Database connected successfully to " + dbName + " at " + host + ":" + port);

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Please ensure mysql-connector-j jar is on classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database Connection Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public void close() {
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
