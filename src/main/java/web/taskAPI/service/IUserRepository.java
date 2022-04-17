package web.taskAPI.service;

import web.taskAPI.core.dto.User;

import javax.servlet.http.HttpSession;

public interface IUserRepository {
    void check(User user);
    void signIn(String login, String password, HttpSession session);
    User get(String login);


}
