package web.vote.servlets;

import web.vote.core.VoteDTO;
import web.vote.service.VoteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {

    private VoteService service;

    public VoteServlet() {
        this.service = VoteService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int artist = Integer.parseInt(req.getParameter("artist")) - 1;
        int[] genres = Arrays.stream(req.getParameterMap().get("genre"))
                .mapToInt(s -> Integer.parseInt(s) - 1)
                .toArray();
        String about = req.getParameter("about");

        VoteDTO voteDTO = new VoteDTO(artist, genres, about);

        service.save(voteDTO);

    }
}
