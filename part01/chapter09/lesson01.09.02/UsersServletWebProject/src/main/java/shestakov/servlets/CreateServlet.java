package shestakov.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Create servlet.
 */
public class CreateServlet extends HttpServlet {
    private static final Logger Log = LoggerFactory.getLogger(CreateServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setContentType("text/html");
        User newUser = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), System.currentTimeMillis());
        DBUtils.getInstance().addUser(newUser);
        //doGet(req, resp);
    }
}
