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
import java.util.List;

/**
 * The type User update servlet.
 */
public class UserUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("login".equals(req.getParameter("type"))) {
            HttpSession session = req.getSession();
            if (session.getAttribute("login") != null) {
                User user = DBUtils.getInstance().getUserByLogin(session.getAttribute("login").toString());
                PrintWriter writer = new PrintWriter(resp.getOutputStream());
                writer.append("[");
                writer.append("{\"login\":\""+user.getLogin()+
                              "\", \"name\":\""+user.getName()+
                              "\", \"email\":\""+user.getEmail()+
                              "\", \"date\":\""+user.getCreateDate()+
                              "\", \"role\":\""+user.getRole().getName()+
                              "\", \"country\":\""+user.getCountry()+
                              "\", \"city\":\""+user.getCity()+
                              "\", \"role\":\""+user.getRole()+
                              "\"}");
                writer.append("]");
                writer.flush();
            }
        } else {
            List<String> result;
            if ("countries".equals(req.getParameter("type"))) {
                result = DBUtils.getInstance().getAllCountries();
            } else {
                result = DBUtils.getInstance().getAllCities();
            }
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append("[");
            for (int i = 0; i < result.size(); i++) {
                writer.append("{\"name\":\"" + result.get(i) + "\"}");
                if (i + 1 != result.size()) {
                    writer.append(",");
                }
            }
            writer.append("]");
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        user.setCountry(req.getParameter("country"));
        user.setCity(req.getParameter("city"));
        DBUtils.getInstance().updateUserByLogin(user);
    }
}
