package shestakov.servlets;

import shestakov.models.Role;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Role update servlet.
 */
public class RoleUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Role selectedRole = new Role();
        if (session.getAttribute("role") != null) {
            selectedRole = DBUtils.getInstance().getRoleByName((String) session.getAttribute("role"));
        }
        req.setAttribute("role", selectedRole);
        req.getRequestDispatcher("/WEB-INF/views/RoleUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name") != null) {
            if (req.getParameter("update") != null) {
                HttpSession session = req.getSession();
                if (session.getAttribute("role") != null) {
                    Role role = (Role) session.getAttribute("role");
                    Role selectedRole = DBUtils.getInstance().getRoleByName(role.getName());
                    if (selectedRole != null) {
                        selectedRole.setName(req.getParameter("name"));
                        DBUtils.getInstance().updateRoleById(selectedRole);
                    }
                }
            }
        }
    }
}
