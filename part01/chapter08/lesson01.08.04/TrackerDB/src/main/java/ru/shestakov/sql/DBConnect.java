package ru.shestakov.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shestakov.models.Comment;
import ru.shestakov.models.Item;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The type Db connect.
 */
public class DBConnect {
    private static final Logger Log = LoggerFactory.getLogger(DBConnect.class);
    private Connection conn;

    /**
     * Create connection.
     *
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    public void createConnection() {
        Properties prop = new Properties();
        try (
                FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/part01/chapter08/lesson01.08.04/TrackerDB/trackerdb.cfg");
        ) {
            prop.load(fis);
        } catch (IOException e) {
            Log.error(e.getMessage(), e);
        }
        String url = prop.getProperty("url").toString();
        String username = prop.getProperty("username").toString();
        String password = prop.getProperty("password").toString();
        //String driver = prop.getProperty("driver").toString();
        //Class.forName(driver);
        try {
            this.conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Close connection.
     */
    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Create structure.
     *
     * @throws SQLException the sql exception
     */
    public void createStructure() {
        Statement st = null;
        try {
            st = this.conn.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS tasks (id serial PRIMARY KEY, name VARCHAR(255), description VARCHAR(2000), createDate TIMESTAMP)");
            st.execute("CREATE TABLE IF NOT EXISTS tasksComments (id serial PRIMARY KEY, name VARCHAR(2000), taskId INTEGER REFERENCES tasks(id))");
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        } finally {
            try {
                st.close();
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Add task.
     *
     * @param item the item
     * @throws SQLException the sql exception
     */
    public void addTask(Item item) {
        try (
        PreparedStatement st = this.conn.prepareStatement("INSERT INTO tasks(name, description, createDate) VALUES (?, ?, ?)");
        ) {
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Update task.
     *
     * @param item the item
     * @throws SQLException the sql exception
     */
    public void updateTask(Item item) throws SQLException {
        try (
        PreparedStatement st = this.conn.prepareStatement("UPDATE tasks SET name=?, description=?, createDate=? WHERE id=?");
        ) {
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setTimestamp(3, new Timestamp(item.getCreate()));
            st.setInt(4, Integer.parseInt(item.getId()));
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Delete task.
     *
     * @param item the item
     * @throws SQLException the sql exception
     */
    public void deleteTask(Item item) throws SQLException {
        try (
        PreparedStatement st = this.conn.prepareStatement("DELETE FROM tasks WHERE id=?");
        ) {
            st.setInt(1, Integer.parseInt(item.getId()));
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Add comment.
     *
     * @param comment the comment
     * @throws SQLException the sql exception
     */
    public void addComment(Comment comment) throws SQLException {
        try (
        PreparedStatement st = this.conn.prepareStatement("INSERT INTO tasksComments(name, taskId) VALUES (?, ?)");
        ) {
            st.setString(1, comment.getText());
            st.setInt(2, Integer.parseInt(comment.getItemId()));
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Find task by id item.
     *
     * @param id the id
     * @return the item
     * @throws SQLException the sql exception
     */
    public Item findTaskById(String id) throws SQLException {
        Item item = null;
        try (
        PreparedStatement st = this.conn.prepareStatement("SELECT t.name, t.description, t.createDate FROM tasks AS t WHERE t.id=?");
        ) {
            st.setInt(1, Integer.parseInt(id));
            ResultSet rs = st.executeQuery();
            rs.next();
            item = new Item(rs.getString("name"), rs.getString("description"), rs.getTimestamp("createDate").getTime());
            rs.close();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        } finally {
            return item;
        }
    }

    /**
     * Find task by name item [ ].
     *
     * @param name the name
     * @return the item [ ]
     * @throws SQLException the sql exception
     */
    public Item[] findTaskByName(String name) throws SQLException {
        List<Item> items = new ArrayList<>();
        Item[] itemsArr = new Item[1];
        try (
        PreparedStatement st = this.conn.prepareStatement("SELECT t.id, t.name, t.description, t.createDate FROM tasks AS t WHERE t.name LIKE ?");
        ) {
            st.setString(1, "%"+name+"%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                items.add(new Item(String.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("description"), rs.getTimestamp("createDate").getTime()));
            }
            rs.close();
            st.close();
            itemsArr = new Item[items.size()];
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        } finally {
            return items.toArray(itemsArr);
        }
    }

    /**
     * Find task by description item [ ].
     *
     * @param description the description
     * @return the item [ ]
     * @throws SQLException the sql exception
     */
    public Item[] findTaskByDescription(String description) throws SQLException {
        List<Item> items = new ArrayList<>();
        Item[] itemsArr = new Item[1];
        try (
        PreparedStatement st = this.conn.prepareStatement("SELECT t.id, t.name, t.description, t.createDate FROM tasks AS t WHERE t.description LIKE ?");
        ) {
            st.setString(1, "%"+description+"%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                items.add(new Item(String.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("description"), rs.getTimestamp("createDate").getTime()));
            }
            rs.close();
            st.close();
            itemsArr = new Item[items.size()];
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        } finally {
            return items.toArray(itemsArr);
        }
    }

    /**
     * Find task by date item [ ].
     *
     * @param date the date
     * @return the item [ ]
     * @throws SQLException the sql exception
     */
    public Item[] findTaskByDate(long date) throws SQLException {
        List<Item> items = new ArrayList<>();
        Item[] itemsArr = new Item[1];
        try (
        PreparedStatement st = this.conn.prepareStatement("SELECT t.id, t.name, t.description, t.createDate FROM tasks AS t WHERE t.createDate=?");
        ) {
            st.setTimestamp(1, new Timestamp(date));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                items.add(new Item(String.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("description"), rs.getTimestamp("createDate").getTime()));
            }
            rs.close();
            st.close();
            itemsArr = new Item[items.size()];
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        } finally {
            return items.toArray(itemsArr);
        }
    }

    /**
     * Get all tasks item [ ].
     *
     * @return the item [ ]
     * @throws SQLException the sql exception
     */
    public Item[] getAllTasks() throws SQLException {
        List<Item> items = new ArrayList<>();
        Item[] itemsArr = new Item[1];
        try (
        PreparedStatement st = this.conn.prepareStatement("SELECT t.id, t.name, t.description, t.createDate FROM tasks AS t");
        ) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                items.add(new Item(String.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("description"), rs.getTimestamp("createDate").getTime()));
            }
            rs.close();
            st.close();
            itemsArr = new Item[items.size()];
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        } finally {
            return items.toArray(itemsArr);
        }
    }
}
