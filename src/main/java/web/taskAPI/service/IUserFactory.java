package web.taskAPI.service;

import web.taskAPI.core.dto.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IUserFactory {
    void create(HttpServletRequest req, HttpServletResponse resp, User user);


    User get(HttpServletRequest req);
}
