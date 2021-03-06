package ru.shestakov.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import ru.shestakov.models.Engine;
import ru.shestakov.services.AdvertsStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * The type Engines controller.
 */
public class EnginesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdvertsStorage storage = new AdvertsStorage();
        List<Engine> result = storage.getEngines();
        final ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(mapper.writeValueAsString(result));
        writer.flush();
    }

}
