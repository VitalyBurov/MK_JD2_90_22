package web.taskAPI.servlets;

import web.taskAPI.core.dto.User;
import web.taskAPI.service.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet(name = "UserServlets", urlPatterns = "/api/user")
public class RegistrationServlets extends HttpServlet {

    public static final String REQUEST_LOGIN = "login";
    public static final String REQUEST_PASSWORD = "password";
    public static final String REQUEST_DATE_OF_BIRTHDAY = "dateOfBirthday";
    public static final String REQUEST_FIRST_NAME = "firstName";
    public static final String REQUEST_LAST_NAME = "lastName";
    public static final String REQUEST_THIRD_NAME = "thirdName";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp
    ) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter(REQUEST_LOGIN);
        String password = req.getParameter(REQUEST_PASSWORD);
        String firstName = req.getParameter(REQUEST_FIRST_NAME);
        String thirdName = req.getParameter(REQUEST_THIRD_NAME);
        String lastName = req.getParameter(REQUEST_LAST_NAME);
        LocalDate dateOfBirthday = LocalDate.parse(req.getParameter(REQUEST_DATE_OF_BIRTHDAY));

        User user = new User(login, password, lastName, firstName, thirdName, dateOfBirthday);

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        UserRepository userRepository = UserRepository.getInstance();
        try {
            userRepository.check(user);
        } catch (IllegalArgumentException e) {
            writer.write(e.getMessage());
        }


    }

}
