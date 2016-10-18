package shestakov.servlets;

import shestakov.dao.impl.UserImpl;
import shestakov.db.LiquibaseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Login controller.
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LiquibaseUtils lq = new LiquibaseUtils();
        try {
            lq.migrate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("login", req.getParameter("login"));
        UserImpl dbUser = new UserImpl();
        boolean result = dbUser.getByLogin(req.getParameter("login")) != null;
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(String.valueOf(result));
        writer.flush();
    }
}
