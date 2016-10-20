package shestakov.servlets;

import shestakov.dao.impl.UserImpl;
import shestakov.models.Entity;
import shestakov.models.User;

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
        List<Entity> result = new ArrayList<>();
        UserImpl dbUser = new UserImpl();
        if (dbUser.isRoot(session.getAttribute("login").toString())) {
            result = dbUser.getAll();
        } else {
            result = dbUser.getByLogin(session.getAttribute("login").toString());
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("[");
        for (int i=0; i<result.size(); i++) {
            User user = (User) result.get(i);
            writer.append("{\"login\":\""+user.getLogin()+"\", \"name\":\""+user.getName()+"\", \"role\":\""+user.getRole().getName()+"\"}");
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
