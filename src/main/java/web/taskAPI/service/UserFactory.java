package web.taskAPI.service;

import web.taskAPI.core.dto.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserFactory implements IUserFactory {
    @Override
    public void create(HttpServletRequest req, HttpServletResponse resp, User user) {

    }

    @Override
    public User get(HttpServletRequest req) {
        return null;
    }
}
