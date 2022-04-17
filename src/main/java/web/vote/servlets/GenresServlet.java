package web.vote.servlets;

import web.vote.service.ArtistService;
import web.vote.service.GenresService;
import web.vote.service.VoteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GenresServlet", urlPatterns = "/vote/genre")
    public class GenresServlet extends HttpServlet {

        private GenresService service;

        public GenresServlet() {
            this.service = GenresService.getInstance();
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter printWriter = resp.getWriter();

            printWriter.write("<p> Choose your favorite genres </p></br>");

            List<String> genres = service.getGenres();

            int index = 1;
            for (String genre : genres) {
                printWriter.write("<p>" + index++ + " genre" + " : " + genre + "</p></br>");
            }
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        service.add(req.getParameter("genre"));
    }
}

