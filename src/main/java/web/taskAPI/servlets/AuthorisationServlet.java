package web.taskAPI.servlets;

import web.taskAPI.service.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AuthorisationServlet", urlPatterns = "/api/login")
public class AuthorisationServlet extends HttpServlet {

    public static final String REQUEST_LOGIN = "login";
    public static final String REQUEST_PASSWORD = "password";



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter(REQUEST_LOGIN);
        String password = req.getParameter(REQUEST_PASSWORD);

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        UserRepository userRepository = UserRepository.getInstance();
        HttpSession session = req.getSession();
        try {
            userRepository.signIn(login,password,session);
        } catch (IllegalArgumentException e) {
            writer.write(e.getMessage());
        }






    }
}
