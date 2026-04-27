package securenotes.ui;

import securenotes.model.Note;
import securenotes.model.User;
import securenotes.service.AuthenticationService;
import securenotes.service.NoteService;
import securenotes.service.RegistrationService;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final RegistrationService regiService = new RegistrationService();
    private final AuthenticationService authService = new AuthenticationService();
    private final NoteService noteService = new NoteService();
    private User loggedInUser;

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> register();
                case "2" -> login();
                case "3" -> running = false;
                default -> System.out.println("Invalid choice");
            }

        }
    }

    public void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n[Welcome to the Secure Notes App]");
            System.out.println("1. Create Note");
            System.out.println("2. View Notes");
            System.out.println("3. Edit Note");
            System.out.println("4. Delete Note");
            System.out.println("5. Change Password");
            System.out.println("6. Logout");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> createNote();
                case "2" -> viewNote();
                case "3" -> editNote();
                case "4" -> deleteNote();
                case "5" -> changePassword();
                case "6" -> running = false;
                default -> System.out.println("Invalid choice");
            }

        }

    }

    private void register() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        boolean success = regiService.registerUser(username, password);

        if (success) {
            System.out.println("Registration successful");
        } else {
            System.out.println("Registration failed");
        }
    }

    private void login() {
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        loggedInUser = authService.login(username, password);

        if (loggedInUser != null) {
            System.out.println("Login successful");
            mainMenu();
        } else {
            System.out.println("Login failed");
        }
    }

    private void createNote() {
        System.out.println("Enter note title: ");
        String title = scanner.nextLine();
        System.out.println("Enter note content: ");
        String content = scanner.nextLine();

        boolean success = noteService.createNote(loggedInUser.getId(), title, content);

        if (success) {
            System.out.println("Note " + "(" + title + ")" + " created successfully");
        } else {
            System.out.println("Note creation failed");
        }
    }

    private void viewNote() {
        List<Note> notes = noteService.viewNote(loggedInUser.getId());
        if (notes == null || notes.isEmpty()) {
            System.out.println("No notes found");
            return;
        }
        for (Note note : notes) {
            System.out.println(note.getId() + " - " + note.getTitle());
        }
        System.out.println("Enter ID of note: ");
        String pickedNoteId = scanner.nextLine();
        for (Note note : notes) {
            if (Integer.parseInt(pickedNoteId) == note.getId()) {
                System.out.println(note.getContent());
            }
        }
    }

    private void changePassword() {
        System.out.println("Enter new password: ");
        String newPassword = scanner.nextLine();

        if (authService.changeUserPassword(loggedInUser.getUsername(), newPassword)) {
            System.out.println("Password changed successfully");
        } else {
            System.out.println("Password change failed");
        }
    }

    private void editNote() {
        List<Note> notes = noteService.viewNote(loggedInUser.getId());
        if (notes == null || notes.isEmpty()) {
            System.out.println("No notes found");
            return;
        }
        System.out.println("Select note to edit: ");
        for (Note note : notes) {
            System.out.println(note.getId() + " - " + note.getTitle());
        }
        System.out.println("Enter note ID: ");
        String noteId = scanner.nextLine();
        System.out.println("Enter new content: ");
        String newContent = scanner.nextLine();

        if (noteService.editNote(Integer.parseInt(noteId), newContent)) {
            System.out.println("Note edited successfully");
        } else {
            System.out.println("Note not found or could not be edited");
        }
    }

    private void deleteNote() {
        List<Note> notes = noteService.viewNote(loggedInUser.getId());
        if (notes == null || notes.isEmpty()) {
            System.out.println("No notes found");
            return;
        }
        for (Note note : notes) {
            System.out.println(note.getId() + " - " + note.getTitle());
        }
        System.out.println("Enter ID of note to delete: ");
        String noteId = scanner.nextLine();
        if (noteService.deleteNote(Integer.parseInt(noteId))) {
            System.out.println("Note deleted successfully");
        } else {
            System.out.println("Note deletion failed");
        }
    }
}


