package shestakov.servlets;

import shestakov.models.Role;
import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Role select servlet.
 */
public class RoleSelectServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBUtils.getInstance().changeUsersRole(new User(req.getAttribute("login").toString()), new Role(req.getAttribute("role").toString()));
    }
}
