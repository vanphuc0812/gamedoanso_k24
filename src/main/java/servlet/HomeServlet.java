package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Game;
import model.Player;
import service.GameService;
import utils.JspUtils;
import utils.UrlUtils;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = UrlUtils.HOME)
public class HomeServlet extends HttpServlet {
    GameService gameService = new GameService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = ((Player) req.getSession().getAttribute("currentUser")).getUsername();
        List<Game> gameList = gameService.getGameByUsername(username);
        req.setAttribute("listGame", gameList);
        req.getRequestDispatcher(JspUtils.HOME).forward(req, resp);
    }
}
