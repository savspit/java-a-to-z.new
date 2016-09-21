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

        User selectedUser = this.dbUtils.getUserByLogin(req.getParameter("user"));

        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>UPDATE</title>" +
                "</head>" +
                "<body>" +
                "<form action='"+req.getContextPath()+"/echo/update' method='post'>" +
                "Login : <input type='text' name='login' value='"+selectedUser.getLogin()+"'/><br/>" +
                "Name : <input type='text' name='name' value='"+selectedUser.getName()+"'/><br/>" +
                "Email : <input type='email' name='email' value='"+selectedUser.getEmail()+"'/><br/>" +
                "<br/>" +

                "<action='"+req.getContextPath()+"/echo/update' method='post'>" +
                "<td style='border : lpx solid black'>" + "<input type='submit' value='update'>" + "</td>" +

                "</form>" +

                "</body>" +
                "</html>");
        writer.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name") != null) {
            User newUser = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
            this.dbUtils.updateUserByLogin(newUser);
        }
        doGet(req,resp);
    }
}
