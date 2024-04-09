package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Player;
import service.AuthService;
import utils.JspUtils;
import utils.UrlUtils;

import java.io.IOException;

@WebServlet(urlPatterns = UrlUtils.LOGIN)
public class AuthSevlet extends HttpServlet {
    AuthService authService = new AuthService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspUtils.LOGIN).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.LOGIN:
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                // logic login
                Player player = authService.login(username, password);
                if(player == null) {
                    req.setAttribute("errors", "Username or password is not correct");
                    req.getRequestDispatcher(JspUtils.LOGIN).forward(req,resp);
                } else {
                    req.getSession().setAttribute("currentUser", player);
                    resp.sendRedirect(req.getContextPath() + UrlUtils.GAME);
                }
                break;
            default:
                break;
        }
    }
}
