package securenote.DBnote;

import databaseConf.DB;
import securenote.note.Note;

import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoteDB {
    public static int addNote(Note note) {
        var sql = "INSERT INTO notestable (title, note) VALUES (?, ?)";

        try (var conn = DB.connect()) {
            if(conn == null) {
                throw new SQLException("Connection Error");
            }
            try(var pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, note.getTitle());
                pstmt.setString(2, note.getNote());

                int insertRow = pstmt.executeUpdate();
                if(insertRow > 0) {
                    var rs = pstmt.getGeneratedKeys();
                    if(rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        }catch (SQLException e) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "insert failed: " + e.getMessage(), e);
        }
        return -1;
    }

    public static List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();

        var sql = "SELECT * FROM notestable";

        try(var conn = DB.connect()) {
            if(conn == null) {
                throw new SQLException("Connection failed");
            }

            try(var pstmt = conn.prepareStatement(sql);
                var rs = pstmt.executeQuery()) {
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String note = rs.getString("note");

                    Note noteObj = new Note(id, title, note);
                    notes.add(noteObj);
                }
            }
        }catch (SQLException e) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "getAllNotes failed: " + e.getMessage(), e);
        }
        return notes;
    }

    public static boolean updateNote(Note note, int id) {
        String sql = "UPDATE notestable SET title = ?, note = ? WHERE id = ?";

        try(var conn = DB.connect();
            var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, note.getTitle());
            pstmt.setString(2, note.getNote());
            pstmt.setInt(3, id);

            int rowsUpdate = pstmt.executeUpdate();
            return rowsUpdate > 0;
        } catch (SQLException e) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "update failed: " + e.getMessage(), e);
            return false;
        }
    }

    public static boolean deleteNote(int id) {
        String sql = "DELETE FROM notestable WHERE id = ?";

        try(var conn = DB.connect()) {
            if(conn == null) {
                throw new SQLException("Connection failed");
            }
            try (var pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);

                int rowsDelete = pstmt.executeUpdate();
                return rowsDelete > 0;
            }
        } catch (SQLException e) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "update failed: " + e.getMessage(), e);
            return false;
        }
    }
}
