package web.tests;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

@WebServlet(name = "Session", urlPatterns = "/test_session")
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        session.setAttribute("lalalal", "Идет дождь ");

        PrintWriter writer = resp.getWriter();

        writer.write(String.valueOf(session.isNew()));
        writer.write((String) session.getAttribute("lalalal"));

    }
}
