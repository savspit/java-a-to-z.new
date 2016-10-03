package shestakov.servlets;

import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type User delete servlet.
 */
public class UserDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBUtils.getInstance().deleteUserByLogin(new User(req.getParameter("user")));
    }
}
