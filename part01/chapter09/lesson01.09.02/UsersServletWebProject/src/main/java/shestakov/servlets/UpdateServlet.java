package shestakov.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class UpdateServlet extends HttpServlet {
    private static final Logger Log = LoggerFactory.getLogger(UpdateServlet.class);
    private DBUtils dbUtils;

    @Override
    public void init() {
        try {
            this.dbUtils = new DBUtils();
            this.dbUtils.init();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>CREATE</title>" +
                "</head>" +
                "<body>" +
                "<form action='"+req.getContextPath()+"/echo/create' method='post'>" +
                "Login : <input type='text' name='login'/><br/>" +
                "Name : <input type='text' name='name'/><br/>" +
                "Email : <input type='email' name='email'/><br/>" +
                //"<input type='submit'>" +
                "<br/>" +

                "<action='"+req.getContextPath()+"/echo/create' method='post'>" +
                "<td style='border : lpx solid black'>" + "<input type='submit' value='update'>" + "</td>" +

                "</form>" +

                "</body>" +
                "</html>");
        writer.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        req.getRequestDispatcher(req.getContextPath()+"/echo/get").forward(req, resp);
    }
}
