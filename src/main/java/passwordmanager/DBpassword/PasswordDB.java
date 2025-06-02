package passwordmanager.DBpassword;

import databaseConf.DB;
import passwordmanager.password.Password;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordDB {
    public static int add (Password password) {
        var sql = "INSERT INTO passwords (login, password, website) VALUES (?, ?, ?)";

        try(var conn = DB.connect()) {

            if(conn == null) {
                throw new SQLException("Connection error");
            }
            try(var pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, password.getLogin());
                pstmt.setString(2, password.getPassword());
                pstmt.setString(3, password.getWebsite());

                int insertRow = pstmt.executeUpdate();
                if (insertRow > 0) {
                    var rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PasswordDB.class.getName()).log(Level.SEVERE, "insert failed: " + e.getMessage(), e);
        }
        return -1;
    }

    public static List<Password> getAll() {
        List<Password> passwords = new ArrayList<>();

        String sql = "SELECT * FROM passwords";

        try(var conn = DB.connect()) {
            if(conn == null) {
                throw new SQLException("Connection is null");
            }
            try (var stmt = conn.prepareStatement(sql);
                var rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String login = rs.getString("login");
                    String pwd = rs.getString("password");
                    String website = rs.getString("website");

                    Password p = new Password(id, login, pwd, website);
                    passwords.add(p);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(PasswordDB.class.getName()).log(Level.SEVERE, "Query failed: " + e.getMessage(), e);
        }
        return passwords;
    }

    public static boolean update (Password password, int id) {
        String sql = "UPDATE passwords SET login = ?, password = ?, website = ? WHERE id = ?";

        try (var conn = DB.connect();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, password.getLogin());
            pstmt.setString(2, password.getPassword());
            pstmt.setString(3, password.getWebsite());
            pstmt.setInt(4, id);

            int rowsUpdate = pstmt.executeUpdate();
            return rowsUpdate > 0;
        } catch (SQLException e) {
            Logger.getLogger(PasswordDB.class.getName()).log(Level.SEVERE, "update failed: " + e.getMessage(), e);
            return false;
        }
    }
}
