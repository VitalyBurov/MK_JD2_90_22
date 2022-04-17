package web.tests;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SessionTestServlet", urlPatterns = "/session")
public class SessionTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String firstNameValue = getValue(req,"firstName");
        saveSession(req,"firstName",firstNameValue);
        String lastNameValue = getValue(req,"lastName");
        saveSession(req,"lastName",lastNameValue);

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("<p>Hello, " + firstNameValue + " " + lastNameValue + "!</p>");
    }

    public static String getValue(HttpServletRequest req, String key) {
        String value = req.getParameter(key);

        if (value == null) {
            HttpSession session = req.getSession();

            if (!session.isNew()) {
                value = (String) session.getAttribute(key);
            }
        }

        if (value == null) {
            throw new IllegalArgumentException("No parameters received");
        }
        return value;
    }

    public static void saveSession(HttpServletRequest req, String key, String value){
        HttpSession session = req.getSession();
        session.setAttribute(key,value);
    }

}
