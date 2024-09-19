package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
	static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/practice-db";
        String user = "postgres";
        String password = "root";
        return DriverManager.getConnection(url,user,password);
    }
}
