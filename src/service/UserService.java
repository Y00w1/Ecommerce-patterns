package service;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private List<User> users;

    public UserService() {
        users = new ArrayList<User>();
    }

    public User createUser(String name, String email, String password) {
        User user = new User(name, email, password);
        users.add(user);
        return user;
    }
    public Optional<User> getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Optional<User> login(String email, String password) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password))
                .findFirst();
    }
    public Optional<User> register(String name, String email, String password) {
        Optional<User> user = getUserByEmail(email);
        if (user.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(createUser(name, email, password));
    }
}
