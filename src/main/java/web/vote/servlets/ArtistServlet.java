package web.vote.servlets;

import web.vote.service.ArtistService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ArtistServlet", urlPatterns = "/vote/artist")
public class ArtistServlet extends HttpServlet {

    private ArtistService service;

    public ArtistServlet() {
        this.service = ArtistService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();

        printWriter.write("<p> Choose your favorite singer </p></br>");

        List<String> artists = service.getArtists();

        int index = 1;
        for (String artist : artists) {
            printWriter.write("<p>" + index++ + " artist" + " : " + artist + "</p></br>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        service.add(req.getParameter("artist"));


    }
}
