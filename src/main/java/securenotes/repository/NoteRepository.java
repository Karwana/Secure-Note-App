package securenotes.repository;

import securenotes.config.DatabaseConnection;
import securenotes.model.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteRepository {

    public boolean saveNote(int userId, String title, String content) {
        String sql = "INSERT INTO notes (user_id, title, content) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setString(2, title);
            statement.setString(3, content);

            int rows = statement.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Note> getNoteByUserId(int userId) {
        String sql = "SELECT * FROM notes WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            List<Note> notes = new ArrayList<>();
            while (rs.next()) {
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setContent(rs.getString("content"));
                note.setTitle(rs.getString("title"));
                note.setUserId(rs.getInt("user_id"));
                notes.add(note);
            }
            return notes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean editNote(int id, int userId, String content) {
        String sql = "UPDATE notes SET content = ? WHERE id = ? AND user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, content);
            statement.setInt(2, id);
            statement.setInt(3, userId);

            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNote(int id, int userId) {
        String sql = "DELETE FROM notes WHERE id = ? AND user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setInt(2, userId);

            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Note> adminGetAllNotes() {
        String sql = "SELECT * FROM notes";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            List<Note> notes = new ArrayList<>();
            while (rs.next()) {
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setContent(rs.getString("content"));
                note.setTitle(rs.getString("title"));
                note.setUserId(rs.getInt("user_id"));
                notes.add(note);
            }
            return notes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean adminDeleteNote(int id) {
        String sql = "DELETE FROM notes WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
