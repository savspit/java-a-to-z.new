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
        /*writer.append(this.dbutils.getUserByLogin(req.getParameter("login")).toString());
        writer.flush();*/

        StringBuilder sb = new StringBuilder("<table style='border : lpx solid black'>");
        for (User currentUser : this.dbUtils.getAllUsers()) {
            sb.append("<tr>");
            sb.append("<td style='border : lpx solid black'>" + currentUser.getLogin() + "</td>");
            sb.append("<td style='border : lpx solid black'>" + currentUser.getName() + "</td>");
            sb.append("<td style='border : lpx solid black'>" + currentUser.getEmail() + "</td>");
            sb.append("<td style='border : lpx solid black'>" + currentUser.getCreateDate() + "</td>");
            sb.append("<form action='"+req.getContextPath()+"/echo/update' method='put'>");
            sb.append("<td style='border : lpx solid black'>" + "<input type='submit' value='edit'></form>" + "</td>");
            sb.append("<form action='"+req.getContextPath()+"/echo/delete' method='delete'>");
            sb.append("<td style='border : lpx solid black'>" + "<input type='submit' value='delete'></form>" + "</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        sb.append("<td><p><a href='" + req.getContextPath() + "/echo/create '>Add new user</a></p></td>");

        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>GET</title>" +
                "</head>" +
                "<body>" +
                /*"<form action='"+req.getContextPath()+"/echo' method='post'>" +
                "Name : <input type='text' name='login'/>" +
                "<input type='submit'>" +
                "</form>" +*/
                //"<br/>" +
                sb.toString() +
                "</body>" +
                "</html>");
        writer.flush();
    }

}
