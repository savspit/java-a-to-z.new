package ru.shestakov.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import ru.shestakov.models.Advert;
import ru.shestakov.services.AdvertsStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * The type Adverts controller.
 */
public class AdvertsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        AdvertsStorage storage = new AdvertsStorage();
        List<Advert> result = storage.getAdverts(login);
        final ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(mapper.writeValueAsString(result));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdvertsStorage storage = new AdvertsStorage();
        storage.setNewAdvert(req.getParameter("advertDesc"));
    }
}
