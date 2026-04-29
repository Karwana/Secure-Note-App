package securenotes.service;

import securenotes.model.Note;
import securenotes.repository.NoteRepository;

import java.util.List;

public class NoteService {

    private final NoteRepository repository = new NoteRepository();

    public boolean createNote(int userId, String title, String content) {
        if (title == null || title.isBlank()) {
            System.out.println("Please enter a title for the note.");
            return false;
        }
        if (content == null || content.isBlank()) {
            System.out.println("Note cannot be empty.");
            return false;
        }
        return repository.saveNote(userId, title, content);
    }

    public List<Note> viewNote(int userId) {
        if (userId > 0) {
            return repository.getNoteByUserId(userId);
        }
        return null;
    }

    public boolean editNote(int id, int userId, String content) {
        if (content == null || content.isBlank()) {
            System.out.println("Note cannot be empty.");
            return false;
        }
        return repository.editNote(id, userId, content);
    }

    public boolean deleteNote(int id, int userId) {
        if (id <= 0) {
            System.out.println("No note found.");
            return false;
        }
        return repository.deleteNote(id, userId);
    }

    public List<Note> adminGetAllNotes() {
        return repository.adminGetAllNotes();
    }

    public boolean adminDeleteNote(int id) {
        return repository.adminDeleteNote(id);
    }
}
