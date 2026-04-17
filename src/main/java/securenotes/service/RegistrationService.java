package securenotes.service;

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
        return repository.saveUser(username,password, "USER");
    }
}
