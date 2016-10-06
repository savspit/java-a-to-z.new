package shestakov.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonController extends HttpServlet {
    private static final Logger Log = LoggerFactory.getLogger(JsonController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("[{\"login\":\"test2\", \"email\":\"email2\"}, {\"login\":\"test2\", \"email\":\"email2\"}]");
        writer.flush();*/
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //UserStorage.getInstance().add(new User("", req.getParameter("login"), "", ""));

        /*boolean result = DBUtils.getInstance().isCredentional(req.getParameter("login"));
        resp.getWriter().write(String.valueOf(result));*/

        boolean result = DBUtils.getInstance().isCredentional(req.getParameter("login"));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(String.valueOf(result));
        writer.flush();
    }
}
