package shestakov.servlets;

import shestakov.models.Role;
import shestakov.postgresql.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type User get servlet.
 */
public class UserGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute("login", (String) session.getAttribute("login"));
        req.setAttribute("role", (Role) session.getAttribute("role"));
        req.setAttribute("users", DBUtils.getInstance().getAllUsers());
        req.getRequestDispatcher("/WEB-INF/views/UserGet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("user") != null) {
            if (req.getParameter("edit") != null) {
                HttpSession session = req.getSession();
                session.setAttribute("login", req.getParameter("user"));
                resp.sendRedirect(String.format("%s/update", req.getContextPath()));
            } else if (req.getParameter("delete") != null) {
                req.setAttribute("login", req.getParameter("user"));
                RequestDispatcher dispatcher = req.getRequestDispatcher("/delete");
                dispatcher.include(req, resp);
            } else if (req.getParameter("editRole") != null) {
                HttpSession session = req.getSession();
                session.setAttribute("login", req.getParameter("user"));
                resp.sendRedirect(String.format("%s/roleGet", req.getContextPath()));
            }
        }
    }
}
