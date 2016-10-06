package shestakov.servlets;

import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Role get servlet.
 */
public class RoleGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User selectedUser = new User();
        if (session.getAttribute("login") != null) {
            selectedUser = DBUtils.getInstance().getUserByLogin((String) session.getAttribute("login"));
        }
        req.setAttribute("user", selectedUser);
        req.setAttribute("roles", DBUtils.getInstance().getAllRoles());
        req.getRequestDispatcher("/WEB-INF/views/RoleGet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("role") != null) {
            if (req.getParameter("select") != null) {
                HttpSession session = req.getSession();
                if (session.getAttribute("login") != null) {
                    req.setAttribute("login", session.getAttribute("login"));
                    req.setAttribute("role", req.getParameter("role"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/roleSelect");
                    dispatcher.include(req, resp);
                }
            } else if (req.getParameter("edit") != null) {
                HttpSession session = req.getSession();
                session.setAttribute("role", req.getParameter("role"));
                resp.sendRedirect(String.format("%s/roleUpdate", req.getContextPath()));
            } else if (req.getParameter("delete") != null) {
                req.setAttribute("role", req.getParameter("role"));
                RequestDispatcher dispatcher = req.getRequestDispatcher("/roleDelete");
                dispatcher.include(req, resp);
            }
        }
    }
}
