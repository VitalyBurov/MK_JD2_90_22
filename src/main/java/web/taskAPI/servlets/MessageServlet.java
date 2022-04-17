package web.taskAPI.servlets;

import web.taskAPI.core.dto.Message;
import web.taskAPI.core.dto.User;
import web.taskAPI.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "MessageServlet", urlPatterns = "/api/message")
public class MessageServlet extends HttpServlet {

    public static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        User user = (User) session.getAttribute(USER);
        if(user == null){
            writer.write("401: Unauthorized");
        }

        MessageService messageService = MessageService.getInstance();

        List<Message> messagesList = messageService.getMessage(user);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(messagesList != null) {
            for (Message message : messagesList) {
                writer.write("<p>" + message.getFromWhom() + ", ");
                writer.write(message.getDateAndTime().format(dateTimeFormatter) + "</p>");
                writer.write("<p>" + message.getContent() + "</p></br>");
            }

        } else writer.write("Messages not found!!!");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String toWhom = req.getParameter("toWhom");
        String messageContent = req.getParameter("message");


        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        User user = (User) session.getAttribute(USER);
        if(user == null){
            writer.write("401: Unauthorized");
        }
        Message message = new Message(user.getLogin(), toWhom, messageContent);

        MessageService messageService = MessageService.getInstance();
        try {
            messageService.sendMessage(message);
        } catch (IllegalArgumentException e) {
            writer.write(e.getMessage() + e);
        }
    }
}
