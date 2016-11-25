package ru.shestakov.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import ru.shestakov.models.Filter;
import ru.shestakov.models.User;
import ru.shestakov.services.AdvertsStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * The type Filter remove servlet.
 */
public class FilterRemoveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        Filter filter = mapper.readValue(req.getParameter("filter"), Filter[].class)[0];
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        AdvertsStorage storage = new AdvertsStorage();
        User user = storage.getUserByLogin((String) session.getAttribute("login"));
        filter.setUser(user);
        Filter currFilter = storage.getFilters(filter).get(0);
        storage.removeFilter(currFilter);
    }
}
