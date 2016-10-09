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

/**
 * The type Role update servlet.
 */
public class RoleUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("role") != null) {
            HttpSession session = req.getSession();
            Role role = DBUtils.getInstance().getRoleByName(session.getAttribute("role").toString());
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append("[");
            writer.append("{\"name\":\""+role.getName()+"\"}");
            writer.append("]");
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("role") != null) {
            HttpSession session = req.getSession();
            Role selectedRole = DBUtils.getInstance().getRoleByName(session.getAttribute("role").toString());
            if (selectedRole != null) {
                selectedRole.setName(req.getParameter("role"));
                DBUtils.getInstance().updateRoleById(selectedRole);
            }
        }
    }
}
