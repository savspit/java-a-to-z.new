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

        StringBuilder sb = new StringBuilder("<table style='width:100%'>");
        /*sb.append("<tr>");
        sb.append("<th>user</th>");
        sb.append("<th>actions</th>");
        sb.append("</tr>");*/
        for (User currentUser : this.dbUtils.getAllUsers()) {
            sb.append("<tr>");

            /*sb.append("<td>");
            sb.append("<form action='"+req.getContextPath()+"/echo/update' method='post'>");
            //sb.append("<action='"+req.getContextPath()+"/echo/update' method='post'>");
            sb.append("<input type='submit' value='edit' />");
            sb.append("</form>");
            sb.append("</td>");*/

            sb.append("<td>");
            sb.append("Login : <input type='text' name='login' value='" + currentUser.getLogin() + "' />");
            sb.append("</td>");
            sb.append("<td>");
            sb.append("Name : <input type='text' name='name' value='" + currentUser.getName() + "' />");
            sb.append("</td>");
            sb.append("<td>");
            sb.append("Email : <input type='text' name='email' value='" + currentUser.getEmail() + "' />");
            sb.append("</td>");
            sb.append("<td>");
            sb.append("Create date : <input type='text' name='create date' value='" + currentUser.getCreateDate() + "' />");
            sb.append("</td>");

            sb.append("<td>");
            sb.append("<form action='"+req.getContextPath()+"/echo/get' method='post' >");
            sb.append("<input type='submit' value='edit' name='edit' />");
            sb.append("</form>");
            sb.append("</td>");
            sb.append("<td>");
            sb.append("<form action='"+req.getContextPath()+"/echo/get' method='post' >");
            sb.append("<input type='submit' value='delete' name='delete' />");
            sb.append("</form>");
            sb.append("</td>");

            sb.append("</tr>");

            /*sb.append("<tr>");
            sb.append("<td style='border : lpx solid black'>" + currentUser.getLogin() + "</td>");
            sb.append("<td style='border : lpx solid black'>" + currentUser.getName() + "</td>");
            sb.append("<td style='border : lpx solid black'>" + currentUser.getEmail() + "</td>");
            sb.append("<td style='border : lpx solid black'>" + currentUser.getCreateDate() + "</td>");
            sb.append("<form action='"+req.getContextPath()+"/echo/update' method='put'>");
            sb.append("<td style='border : lpx solid black'>" + "<input type='submit' value='edit'></form>" + "</td>");
            sb.append("<form action='"+req.getContextPath()+"/echo/delete' method='delete'>");
            sb.append("<td style='border : lpx solid black'>" + "<input type='submit' value='delete'></form>" + "</td>");
            sb.append("</tr>");*/


        }
        sb.append("</table>");
        sb.append("<td><p><a href='" + req.getContextPath() + "/echo/create'>Add new user</a></p></td>");

        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>GET</title>" +
                /*"<style>" +
                "table, th, td { border: 1px solid black; border-collapse: collapse;}" +
                "</style>" +*/
                "</head>" +
                "<body>" +
                /*"<form action='"+req.getContextPath()+"/echo' method='post'>" +
                "Name : <input type='text' name='login'/>" +
                "<input type='submit'>" +
                "</form>" +*/
                //"<br/>" +
                sb.toString() +
                "</table>" +
                "</body>" +
                "</html>");
        writer.flush();

        /*req.setAttribute("product", req);
        req.getRequestDispatcher("/WEB-INF/product.jsp").forward(req, resp);*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        if (req.getParameter("edit") != null) {

        } else if (req.getParameter("delete") != null) {

        }
    }

}
