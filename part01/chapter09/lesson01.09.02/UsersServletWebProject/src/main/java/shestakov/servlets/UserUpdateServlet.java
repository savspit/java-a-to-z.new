package shestakov.servlets;

import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type User update servlet.
 */
public class UserUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User selectedUser = new User();
        if (session.getAttribute("login") != null) {
            selectedUser = DBUtils.getInstance().getUserByLogin((String) session.getAttribute("login"));
            session.removeAttribute("login");
        }
        req.setAttribute("user", selectedUser);
        req.getRequestDispatcher("/WEB-INF/views/UserUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("login") != null) {
            if (req.getParameter("update") != null) {
                DBUtils.getInstance().updateUserByLogin(new User(req.getParameter("name"), req.getParameter("login"),req.getParameter("email")));
            }
        }
    }
}
