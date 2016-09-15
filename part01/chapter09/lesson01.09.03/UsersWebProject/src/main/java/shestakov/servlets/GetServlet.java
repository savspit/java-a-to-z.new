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
import java.sql.Timestamp;

public class GetServlet extends HttpServlet {
    private static final Logger Log = LoggerFactory.getLogger(GetServlet.class);
    private DBUtils dbutils;

    @Override
    public void init() throws ServletException {
        this.dbutils = new DBUtils();
        this.dbutils.setProperties();
        this.dbutils.openConnection();
    }

    @Override
    public void destroy() {
        this.dbutils.closeConnection();
    }

    // get
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        /*writer.append(this.dbutils.getUserByLogin(req.getParameter("login")).toString());
        writer.flush();*/

        StringBuilder sb = new StringBuilder("<table>");
        for (User currentUser : this.dbutils.getAllUsers()) {
            sb.append("<tr>");
            sb.append("<td>" + currentUser.getLogin() + "</td>");
            sb.append("<td>" + currentUser.getName() + "</td>");
            sb.append("<td>" + currentUser.getEmail() + "</td>");
            sb.append("<td>" + currentUser.getCreateDate() + "</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");

        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>" +
                "<form action='"+req.getContextPath()+"/echo' method='post'>" +
                "Name : <input type='text' name='login'/>" +
                "<input type='submit'>" +
                "</form>" +
                "<br/>" +
                sb.toString() +
                "</body>" +
                "</html>");
        writer.flush();

    }
}
