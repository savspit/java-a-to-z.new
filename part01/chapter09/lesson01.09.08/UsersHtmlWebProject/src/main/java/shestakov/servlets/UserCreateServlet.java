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
 * The type User create servlet.
 */
public class UserCreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User newUser = new User();
        newUser.setLogin(req.getParameter("login"));
        newUser.setName(req.getParameter("name"));
        newUser.setEmail(req.getParameter("email"));
        newUser.setCountry(req.getParameter("country"));
        newUser.setCity(req.getParameter("city"));
        newUser.setRole(new Role(""));
        DBUtils.getInstance().addUser(newUser);
    }
}
