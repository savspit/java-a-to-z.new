package shestakov.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class GetServlet extends HttpServlet {
    private static final Logger Log = LoggerFactory.getLogger(GetServlet.class);
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

    // get
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        StringBuilder sb = new StringBuilder();

        sb.append("<form action='"+req.getContextPath()+"/echo/get' method='post' >");

        for (User currentUser : this.dbUtils.getAllUsers()) {
            sb.append("<input type='radio' name='user' value=' "+currentUser.getLogin()+"' > "+currentUser.toString()+"<br>");
        }

        sb.append("<input type='submit' value='edit' name='edit' />");
        sb.append("<input type='submit' value='delete' name='delete' />");
        sb.append("</form>");

        sb.append("<td><p><a href='" + req.getContextPath() + "/echo/create'>Add new user</a></p></td>");

        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>GET</title>" +
                "</head>" +
                "<body>" +
                sb.toString() +
                "</table>" +
                "</body>" +
                "</html>");
        writer.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        if (req.getParameter("edit") != null) {
            req.setAttribute("user",req.getParameter("user"));
            RequestDispatcher rd = req.getRequestDispatcher("/echo/update");
            rd.forward(req,resp);
        } else if (req.getParameter("delete") != null) {
            User newUser = new User(req.getParameter("user"));
            this.dbUtils.deleteUserByLogin(newUser);
        }
        doGet(req, resp);
    }

}
