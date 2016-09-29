package shestakov.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Delete servlet.
 */
public class DeleteServlet extends HttpServlet {
    private static final Logger Log = LoggerFactory.getLogger(DeleteServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBUtils.getInstance().deleteUserByLogin(new User(req.getParameter("user")));
    }
}
