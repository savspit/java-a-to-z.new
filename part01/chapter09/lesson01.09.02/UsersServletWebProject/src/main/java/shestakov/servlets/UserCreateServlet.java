package shestakov.servlets;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/userCreate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User newUser = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), System.currentTimeMillis());
        DBUtils.getInstance().addUser(newUser);
    }
}
