package ru.shestakov.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import ru.shestakov.models.Transmission;
import ru.shestakov.services.AdvertsStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Advert transmission servlet.
 */
public class AdvertTransmissionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int advertId = Integer.parseInt((String) session.getAttribute("advertId"));
        AdvertsStorage storage = new AdvertsStorage();
        Transmission result = storage.getTransmissionByAdvertId(advertId);
        final ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(mapper.writeValueAsString(result));
        writer.flush();
    }

}
