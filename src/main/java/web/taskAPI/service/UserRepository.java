package web.taskAPI.service;

import web.taskAPI.core.dto.Role;
import web.taskAPI.core.dto.User;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UserRepository implements IUserRepository {
    public static final String ADMIN = "admin";
    public static final String USER = "user";
    private Map<String, User> users;

    private static UserRepository userRepository;

    private UserRepository() {
        users = new HashMap<>();
        User admin = new User(ADMIN, ADMIN, ADMIN, ADMIN, ADMIN, LocalDate.now());
        admin.setRole(String.valueOf(Role.ADMIN));
        users.put(ADMIN, admin);
    }

    public static UserRepository getInstance() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }


    @Override
    public void check(User user) throws IllegalArgumentException {
        for (Map.Entry<String, User> savedUser : users.entrySet()) {
            if (savedUser.getKey().equals(user.getLogin())) {
                throw new IllegalArgumentException("The User has already exist!!!");
            }
        }
        users.put(user.getLogin(), user);
    }

    @Override
    public void signIn(String login, String password, HttpSession session) throws IllegalArgumentException {
        boolean isExist = false;
        for (Map.Entry<String, User> savedUser : users.entrySet()) {
            if (savedUser.getKey().equals(login)) {
                if (savedUser.getValue().getPassword().equals(password)) {
                    session.setAttribute(USER, savedUser.getValue());
                    isExist = true;
                } else {
                    throw new IllegalArgumentException("Wrong login or password");
                }
            }
        }
        if (!isExist) {
            throw new IllegalArgumentException("The User doesn't exist");
        }
    }

    @Override
    public User get(String login) throws IllegalArgumentException {
        for (Map.Entry<String, User> savedUser : users.entrySet()) {
            if (savedUser.getKey().equals(login))
                return savedUser.getValue();
        }
        throw new IllegalArgumentException("The User doesn't exist");
    }
}



