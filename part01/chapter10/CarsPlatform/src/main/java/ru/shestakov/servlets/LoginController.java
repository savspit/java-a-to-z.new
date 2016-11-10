package ru.shestakov.servlets;

import ru.shestakov.services.AdvertsStorage;
import ru.shestakov.utils.LiquibaseUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Login controller.
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LiquibaseUtils lq = new LiquibaseUtils();
        lq.migrate();
        HttpSession session = req.getSession();
        session.setAttribute("login", req.getParameter("login"));
        AdvertsStorage storage = new AdvertsStorage();
        boolean result = storage.getUserByLogin(req.getParameter("login")).size() != 0;
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(String.valueOf(result));
        writer.flush();
    }
}
