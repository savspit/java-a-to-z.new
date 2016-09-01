package ru.shestakov.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shestakov.models.Comment;
import ru.shestakov.models.Filter;
import ru.shestakov.models.Item;
import ru.shestakov.models.Task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The type Psq lmanager.
 */
public class PSQLmanager {
    private static final Logger Log = LoggerFactory.getLogger(PSQLmanager.class);
    private Connection conn;

    /**
     * Create connection.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    public void createConnection() throws IOException, SQLException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(System.getProperty("user.dir") + "/part01/chapter08/lesson01.08.04/TrackerDB/trackerdb.cfg"));
        String url = prop.getProperty("url").toString();
        String username = prop.getProperty("username").toString();
        String password = prop.getProperty("password").toString();
        //String driver = prop.getProperty("driver").toString();
        //Class.forName(driver);
        this.conn = DriverManager.getConnection(url, username, password);
    }

    /**
     * Create structure.
     *
     * @throws SQLException the sql exception
     */
    public void createStructure() throws SQLException {
        Statement st = this.conn.createStatement();
        st.execute("CREATE TABLE tasks (id serial PRIMARY KEY, name VARCHAR(255), description VARCHAR(2000), createDate TIMESTAMP)");
        st.execute("CREATE TABLE tasksComments (id serial PRIMARY KEY, name VARCHAR(2000), taskId INTEGER REFERENCES tasks(id))");
        st.close();

    }

    /**
     * Add task.
     *
     * @param item the item
     * @throws SQLException the sql exception
     */
    public void addTask(Item item) throws SQLException {
        PreparedStatement st = this.conn.prepareStatement("INSERT INTO tasks(name, description, createDate) VALUES (?, ?, ?)");
        st.setString(1, item.getName());
        st.setString(2, item.getDescription());
        st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        st.executeUpdate();
        st.close();
    }

    /**
     * Update task.
     *
     * @param item the item
     * @throws SQLException the sql exception
     */
    public void updateTask(Item item) throws SQLException {
        PreparedStatement st = this.conn.prepareStatement("UPDATE tasks SET name=?, description=?, createDate=? WHERE id=?");
        st.setString(1, item.getName());
        st.setString(2, item.getDescription());
        st.setTimestamp(3, new Timestamp(item.getCreate()));
        st.setInt(4, Integer.parseInt(item.getId()));
        st.executeUpdate();
        st.close();
    }

    /**
     * Delete task.
     *
     * @param item the item
     * @throws SQLException the sql exception
     */
    public void deleteTask(Item item) throws SQLException {
        PreparedStatement st = this.conn.prepareStatement("DELETE FROM tasks WHERE id=?");
        st.setInt(1, Integer.parseInt(item.getId()));
        st.executeUpdate();
        st.close();
    }

    /**
     * Add comment.
     *
     * @param comment the comment
     * @throws SQLException the sql exception
     */
    public void addComment(Comment comment) throws SQLException {
        PreparedStatement st = this.conn.prepareStatement("INSERT INTO tasksComments(name, taskId) VALUES (?, ?)");
        st.setString(1, comment.getText());
        st.setInt(2, Integer.parseInt(comment.getItemId()));
        st.executeUpdate();
        st.close();
    }

    /**
     * Find task by id item.
     *
     * @param id the id
     * @return the item
     * @throws SQLException the sql exception
     */
    public Item findTaskById(String id) throws SQLException {
        PreparedStatement st = this.conn.prepareStatement("SELECT t.name, t.description, t.createDate FROM tasks AS t WHERE t.id=?");
        st.setInt(1, Integer.parseInt(id));
        ResultSet rs = st.executeQuery();
        rs.next();
        Item item = new Item(rs.getString("name"), rs.getString("description"), rs.getTimestamp("createDate").getTime());
        rs.close();
        st.close();
        return item;
    }

    /**
     * Find task by name item [ ].
     *
     * @param name the name
     * @return the item [ ]
     * @throws SQLException the sql exception
     */
    public Item[] findTaskByName(String name) throws SQLException {
        PreparedStatement st = this.conn.prepareStatement("SELECT t.id, t.name, t.description, t.createDate FROM tasks AS t WHERE t.name LIKE ?");
        st.setString(1, "%"+name+"%");
        ResultSet rs = st.executeQuery();
        List<Item> items = new ArrayList<>();
        while (rs.next()) {
            items.add(new Item(String.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("description"), rs.getTimestamp("createDate").getTime()));
        }
        rs.close();
        st.close();
        Item[] itemsArr = new Item[items.size()];
        return items.toArray(itemsArr);
    }

    /**
     * Find task by description item [ ].
     *
     * @param description the description
     * @return the item [ ]
     * @throws SQLException the sql exception
     */
    public Item[] findTaskByDescription(String description) throws SQLException {
        PreparedStatement st = this.conn.prepareStatement("SELECT t.id, t.name, t.description, t.createDate FROM tasks AS t WHERE t.description LIKE ?");
        st.setString(1, "%"+description+"%");
        ResultSet rs = st.executeQuery();
        List<Item> items = new ArrayList<>();
        while (rs.next()) {
            items.add(new Item(String.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("description"), rs.getTimestamp("createDate").getTime()));
        }
        rs.close();
        st.close();
        Item[] itemsArr = new Item[items.size()];
        return items.toArray(itemsArr);
    }

    /**
     * Find task by date item [ ].
     *
     * @param date the date
     * @return the item [ ]
     * @throws SQLException the sql exception
     */
    public Item[] findTaskByDate(long date) throws SQLException {
        PreparedStatement st = this.conn.prepareStatement("SELECT t.id, t.name, t.description, t.createDate FROM tasks AS t WHERE t.createDate=?");
        st.setTimestamp(1, new Timestamp(date));
        ResultSet rs = st.executeQuery();
        List<Item> items = new ArrayList<>();
        while (rs.next()) {
            items.add(new Item(String.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("description"), rs.getTimestamp("createDate").getTime()));
        }
        rs.close();
        st.close();
        Item[] itemsArr = new Item[items.size()];
        return items.toArray(itemsArr);
    }

    /**
     * Get all tasks item [ ].
     *
     * @return the item [ ]
     * @throws SQLException the sql exception
     */
    public Item[] getAllTasks() throws SQLException {
        PreparedStatement st = this.conn.prepareStatement("SELECT t.id, t.name, t.description, t.createDate FROM tasks AS t");
        ResultSet rs = st.executeQuery();
        List<Item> items = new ArrayList<>();
        while (rs.next()) {
            items.add(new Item(String.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("description"), rs.getTimestamp("createDate").getTime()));
        }
        rs.close();
        st.close();
        Item[] itemsArr = new Item[items.size()];
        return items.toArray(itemsArr);
    }
}
