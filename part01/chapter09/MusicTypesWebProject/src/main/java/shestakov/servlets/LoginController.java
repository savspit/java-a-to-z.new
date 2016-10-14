package shestakov.servlets;

import shestakov.dao.impl.DBUser;
import shestakov.services.DBUtils;

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
        DBUtils dbUtils = new DBUtils();
        dbUtils.init();
        HttpSession session = req.getSession();
        session.setAttribute("login", req.getParameter("login"));
        DBUser dbUser = new DBUser();
        boolean result = dbUser.getByLogin(req.getParameter("login")) != null;
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(String.valueOf(result));
        writer.flush();
    }
}
