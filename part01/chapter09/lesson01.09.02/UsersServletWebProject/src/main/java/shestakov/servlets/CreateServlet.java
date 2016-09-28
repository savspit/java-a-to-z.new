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
                "<br/>" +
                "<action='"+req.getContextPath()+"/echo/create' method='post'>" +
                "<td style='border : lpx solid black'>" + "<input type='submit' value='add'>" + "</td>" +
                "</form>" +
                "</body>" +
                "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User newUser = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), System.currentTimeMillis());
        DBUtils.getInstance().addUser(newUser);
        doGet(req, resp);
    }
}
