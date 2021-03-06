package shestakov.servlets;

import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * The type User get servlet.
 */
public class UserGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<User> result = new ArrayList<>();
        if (DBUtils.getInstance().isRoot(session.getAttribute("login").toString())) {
            result = DBUtils.getInstance().getAllUsers();
        } else {
            User user = DBUtils.getInstance().getUserByLogin(session.getAttribute("login").toString());
            result.add(user);
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("[");
        for (int i=0; i<result.size(); i++) {
            writer.append("{\"login\":\""+result.get(i).getLogin()+"\", \"name\":\""+result.get(i).getName()+"\", \"email\":\""+result.get(i).getEmail()+"\", \"date\":\""+result.get(i).getCreateDate()+"\", \"role\":\""+result.get(i).getRole().getName()+"\"}");
            if (i+1 != result.size()) { writer.append(","); }
        }
        writer.append("]");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("login", req.getParameter("login"));
    }
}
