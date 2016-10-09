package shestakov.servlets;

import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Service controller.
 */
public class ServiceController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        boolean result = DBUtils.getInstance().isRoot(session.getAttribute("login").toString());
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(String.valueOf(result));
        writer.flush();
    }
}
