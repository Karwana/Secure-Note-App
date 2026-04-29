package securenotes.service;

import org.mindrot.jbcrypt.BCrypt;
import securenotes.repository.UserRepository;

public class RegistrationService {

    private final UserRepository repository = new UserRepository();

    public boolean registerUser(String username, String password) {
        if (username == null || username.isBlank()) {
            System.out.println("Username is empty");
            return false;
        }
        if (password == null || password.isBlank()) {
            System.out.println("Password is empty");
            return false;
        }
        if (password.length() < 6) {
            System.out.println("Password must be at least 6 characters long");
            return false;
        }
        if (repository.userExists(username)) {
            System.out.println("Username already taken, try again");
            return false;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        return repository.saveUser(username, hashedPassword, "USER");
    }
}
