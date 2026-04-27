package securenotes.service;

import org.mindrot.jbcrypt.BCrypt;
import securenotes.model.User;
import securenotes.repository.UserRepository;

public class AuthenticationService {

    private final UserRepository repository = new UserRepository();

    public User login(String username, String password) {
        User user = repository.getUserByUsername(username);
        if (user == null) {
            System.out.println("User not found");
            return null;
        }
        if (BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public boolean changeUserPassword(String username, String newPassword) {
        if (newPassword == null || newPassword.isBlank()) {
            System.out.println("Password cannot be empty");
            return false;
        }
        User user = repository.getUserByUsername(username);
        if (BCrypt.checkpw(newPassword, user.getPassword())) {
            System.out.println("You cannot use the same password");
            return false;
        }
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        return repository.changeUserPassword(username, hashedPassword);
    }
}
