package shestakov.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Get servlet.
 */
public class GetServlet extends HttpServlet {
    private static final Logger Log = LoggerFactory.getLogger(GetServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("user") != null) {
            if (req.getParameter("edit") != null) {
                HttpSession session = req.getSession(false);
                session.setAttribute("login", req.getParameter("user"));
                resp.sendRedirect(String.format("%s/update.jsp", req.getContextPath()));
            } else if (req.getParameter("delete") != null) {
                req.setAttribute("login", req.getParameter("user"));
                RequestDispatcher dispatcher = req.getRequestDispatcher("/echo/delete");
                dispatcher.include(req, resp);
            }
        }
    }


}
