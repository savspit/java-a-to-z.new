package ru.shestakov.servlets;

import ru.shestakov.models.Item;
import ru.shestakov.services.ItemsStorage;
import ru.shestakov.utils.LiquibaseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ItemsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LiquibaseUtils lq = new LiquibaseUtils();
        lq.migrate();

        ItemsStorage storage = new ItemsStorage();
        List<Item> result = storage.getItemsByFilter(req.getParameter("showAll"));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("[");
        for (int i=0; i<result.size(); i++) {
            Item item = (Item) result.get(i);
            writer.append("{\"id\":\""+item.getId()+"\", \"description\":\""+item.getDescription()+"\", \"created_date\":\""+item.getCreated_date()+"\", \"done\":\""+item.getDone()+"\"}");
            if (i+1 != result.size()) { writer.append(","); }
        }
        writer.append("]");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemsStorage storage = new ItemsStorage();
        storage.setNewItem(req.getParameter("itemDesc"));
    }
}
