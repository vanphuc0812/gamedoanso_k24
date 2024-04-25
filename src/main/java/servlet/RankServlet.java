package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Game;
import service.RankService;
import utils.JspUtils;
import utils.UrlUtils;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = UrlUtils.RANK)
public class RankServlet extends HttpServlet {
    RankService rankService = new RankService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Game> listRankByPlayTime = rankService.getTopByPlayTime(10);
        // get top 10 by guess times
        List<Game> listRankByGuessTimes = rankService.getTopByGuessTimes(10);

        req.setAttribute("listRankByPlayTime", listRankByPlayTime);
        req.setAttribute("listRankByGuessTimes", listRankByGuessTimes);
        req.getRequestDispatcher(JspUtils.RANK).forward(req, resp);
    }
}
