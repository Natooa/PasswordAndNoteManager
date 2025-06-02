package databaseConf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {
    private static final Logger logger = Logger.getLogger(DB.class.getName());

    public static Connection connect() throws SQLException {
        try{
            var jdbcUrl = DatabaseConfig.getDbUrl();
            var jdbcUser = DatabaseConfig.getDbUsername();
            var jdbcPassword = DatabaseConfig.getDbPassword();

            return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database connection failed" + e.getMessage(), e);
            return null;
        }
    }
}
