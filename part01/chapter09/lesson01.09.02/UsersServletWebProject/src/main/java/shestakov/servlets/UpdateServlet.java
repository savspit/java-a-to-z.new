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
        User selectedUser = new User();
        if (req.getParameter("user") != null) {
            selectedUser = this.dbUtils.getUserByLogin(req.getParameter("user"));
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>UPDATE</title>" +
                "</head>" +
                "<body>" +
                "<form action='"+req.getContextPath()+"/echo/update' method='GET'>" +
                "Login : <input type='text' name='login' value='"+selectedUser.getLogin()+"'/><br/>" +
                "Name : <input type='text' name='name' value='"+selectedUser.getName()+"'/><br/>" +
                "Email : <input type='email' name='email' value='"+selectedUser.getEmail()+"'/><br/>" +
                "<br/>" +

                "<input type='submit' value='update' name='update' >" +

                "</form>" +

                "</body>" +
                "</html>");
        if (req.getParameter("login") != null) {
            if (req.getParameter("update") != null) {
                this.dbUtils.updateUserByLogin(new User(req.getParameter("name"), req.getParameter("login"),req.getParameter("email")));
            }
        }
        writer.flush();
    }

}
