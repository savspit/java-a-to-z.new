package shestakov.servlets;

import shestakov.models.Role;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * The type Role get servlet.
 */
public class RoleGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> result = DBUtils.getInstance().getAllRoles();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("[");
        for (int i=0; i<result.size(); i++) {
            writer.append("{\"name\":\""+result.get(i).getName()+"\"}");
            if (i+1 != result.size()) { writer.append(","); }
        }
        writer.append("]");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("login") != null) {
            HttpSession session = req.getSession();
            session.setAttribute("login", req.getParameter("login"));
        }
        if (req.getParameter("role") != null) {
            HttpSession session = req.getSession();
            session.setAttribute("role", req.getParameter("role"));
        }
    }
}
