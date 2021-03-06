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
 * The type Json controller.
 */
public class JsonController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("login", req.getParameter("login"));
        boolean result = DBUtils.getInstance().isCredentional(req.getParameter("login"));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(String.valueOf(result));
        writer.flush();
    }
}
