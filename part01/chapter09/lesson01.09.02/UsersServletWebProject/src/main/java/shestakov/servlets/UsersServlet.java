package shestakov.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.models.User;
import shestakov.postgresql.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class UsersServlet extends HttpServlet {
    private static final Logger Log = LoggerFactory.getLogger(UsersServlet.class);
    private DBUtils dbutils;

    @Override
    public void init() throws ServletException {
        this.dbutils = new DBUtils();
        this.dbutils.setProperties();
        this.dbutils.openConnection();
    }

    @Override
    public void destroy() {
        this.dbutils.closeConnection();
    }

    // get
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(this.dbutils.getUserByLogin(req.getParameter("login")).toString());
        writer.flush();
    }

    // create
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.dbutils.addUser(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), Timestamp.valueOf(req.getParameter("createDate")).getTime()));
        doGet(req, resp);
    }

    // update
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.dbutils.updateUserByLogin(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), Timestamp.valueOf(req.getParameter("createDate")).getTime()));
    }

    // delete
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.dbutils.deleteUserByLogin(new User(req.getParameter("login")));
    }
}
