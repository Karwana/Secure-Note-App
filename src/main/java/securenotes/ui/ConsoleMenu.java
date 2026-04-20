package securenotes.ui;

import securenotes.service.RegistrationService;

import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final RegistrationService service = new RegistrationService();

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

    private void register() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        boolean success = service.registerUser(username, password);

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
    }

}
