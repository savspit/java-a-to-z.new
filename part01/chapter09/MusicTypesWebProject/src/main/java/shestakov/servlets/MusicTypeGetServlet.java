package shestakov.servlets;

import shestakov.dao.impl.MusicTypeImpl;
import shestakov.dao.impl.UserImpl;
import shestakov.models.Entity;
import shestakov.models.MusicType;
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

public class MusicTypeGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MusicTypeImpl dbMusicType = new MusicTypeImpl();
        List<Entity> result = dbMusicType.getByUserLogin(session.getAttribute("login").toString());
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("[");
        for (int i=0; i<result.size(); i++) {
            MusicType musicType = (MusicType) result.get(i);
            writer.append("{\"id\":\""+musicType.getId()+"\", \"name\":\""+musicType.getName()+"\"}");
            if (i+1 != result.size()) { writer.append(","); }
        }
        writer.append("]");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*HttpSession session = req.getSession();
        session.setAttribute("login", req.getParameter("login"));*/
    }
}
