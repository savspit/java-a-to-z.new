package shestakov.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The type Echo servlet.
 */
public class EchoServlet extends HttpServlet {
    private static final Logger Log = LoggerFactory.getLogger(EchoServlet.class);
    private List<String> users = new CopyOnWriteArrayList<String>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        StringBuilder sb = new StringBuilder("<table>");
        for (String login : this.users) {
            sb.append("<tr><td>" + login + "</tr></td>");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        UserStorage.getInstance().add(new User(req.getParameter("login"),req.getParameter("email")));
        this.users.add(req.getParameter("login"));
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }


}
