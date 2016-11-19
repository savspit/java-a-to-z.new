package ru.shestakov.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
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

public class FilterSaveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        //mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        Filter filter = mapper.readValue(req.getParameter("filter"), Filter[].class)[0];
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        AdvertsStorage storage = new AdvertsStorage();
        List<User> user = storage.getUserByLogin((String) session.getAttribute("login"));
        filter.setUser(user.get(0));
        boolean isFirstFilter = storage.getFilters(filter).size() == 0;
        if (!isFirstFilter) { filter.setCondition("or"); };
        storage.saveFilter(login, filter);
    }
}
