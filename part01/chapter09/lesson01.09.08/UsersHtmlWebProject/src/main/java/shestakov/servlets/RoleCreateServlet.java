package shestakov.servlets;

import shestakov.models.Role;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Role create servlet.
 */
public class RoleCreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Role newRole = new Role(req.getParameter("role"));
        DBUtils.getInstance().addRole(newRole);
    }
}
