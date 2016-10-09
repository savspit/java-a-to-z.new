package shestakov.servlets;

import shestakov.models.Role;
import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Role select servlet.
 */
public class RoleSelectServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("login") != null) {
            User user = new User();
            user.setLogin(session.getAttribute("login").toString());
            DBUtils.getInstance().changeUsersRole(user, new Role(req.getParameter("role")));
        }
    }
}
