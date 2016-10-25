package ru.shestakov.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import ru.shestakov.models.Item;
import ru.shestakov.services.ItemsStorage;
import ru.shestakov.utils.LiquibaseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * The type Items controller.
 */
public class ItemsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LiquibaseUtils lq = new LiquibaseUtils();
        lq.migrate();
        ItemsStorage storage = new ItemsStorage();
        List<Item> result = storage.getItemsByFilter(req.getParameter("showAll"));
        final ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(mapper.writeValueAsString(result));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemsStorage storage = new ItemsStorage();
        storage.setNewItem(req.getParameter("itemDesc"));
    }
}
