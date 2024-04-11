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

@WebServlet(urlPatterns = {UrlUtils.LOGIN, UrlUtils.REGISTER, UrlUtils.LOGOUT})
public class AuthSevlet extends HttpServlet {
    AuthService authService = new AuthService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.LOGIN:
                req.getRequestDispatcher(JspUtils.LOGIN).forward(req,resp);
                break;
            case UrlUtils.REGISTER:
                req.getRequestDispatcher(JspUtils.REGISTER).forward(req, resp);
                break;
            case UrlUtils.LOGOUT:
                req.getSession().invalidate();
                resp.sendRedirect(req.getContextPath() + UrlUtils.LOGIN);
                break;
        }

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
            case UrlUtils.REGISTER:
                username = req.getParameter("username");
                password = req.getParameter("password");
                String confirmPassword = req.getParameter("confirmPassword");

                if(!password.equals(confirmPassword)) {
                    req.setAttribute("errors", "Password and confirm are not matched");
                    req.getRequestDispatcher(JspUtils.REGISTER).forward(req, resp);
                }
                String result = authService.register(username, password);
                if("Success".equals(result)) {
                    resp.sendRedirect(req.getContextPath() + UrlUtils.LOGIN);
                } else {
                    req.setAttribute("errors", result);
                    req.getRequestDispatcher(JspUtils.REGISTER).forward(req, resp);
                }
            default:
                break;
        }
    }
}
