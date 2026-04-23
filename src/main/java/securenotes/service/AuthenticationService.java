package securenotes.service;

import org.mindrot.jbcrypt.BCrypt;
import securenotes.model.User;
import securenotes.repository.UserRepository;

public class AuthenticationService {

    private final UserRepository repository = new UserRepository();

    public boolean login(String username, String password) {
        User user = repository.getUserByUsername(username);
        if (user == null) {
            System.out.println("User not found");
            return false;
        }
        else return BCrypt.checkpw(password, user.getPassword());
    }
}
