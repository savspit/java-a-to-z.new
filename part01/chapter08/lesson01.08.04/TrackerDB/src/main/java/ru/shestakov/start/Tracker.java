package ru.shestakov.start;

import ru.shestakov.models.*;
import ru.shestakov.sql.DBConnect;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * The type Tracker.
 */
public class Tracker {

    private Item[] items = new Item[10];
    private int position = 0;
    private DBConnect dbConnect = new DBConnect();

    /**
     * Add item.
     *
     * @param item the item
     * @return the item
     * @throws SQLException the sql exception
     */
    public Item add(Item item) throws SQLException {
        dbConnect.addTask(item);
        return item;
    }

    /**
     * Add comment.
     *
     * @param newComment the new comment
     * @throws SQLException the sql exception
     */
    public void addComment(Comment newComment) throws SQLException {
        dbConnect.addComment(newComment);
    }

    /**
     * Update.
     *
     * @param item the item
     * @throws SQLException the sql exception
     */
    public void update(Item item) throws SQLException {
        dbConnect.updateTask(item);
    }

    /**
     * Delete.
     *
     * @param itemForDelete the item for delete
     * @throws SQLException the sql exception
     */
    public void delete(Item itemForDelete) throws SQLException {
        dbConnect.deleteTask(itemForDelete);
	}

    /**
     * Find by id item.
     *
     * @param id the id
     * @return the item
     * @throws SQLException the sql exception
     */
    public Item findById(String id) throws SQLException {
        return dbConnect.findTaskById(id);
    }

    /**
     * Find by name item [ ].
     *
     * @param name the name
     * @return the item [ ]
     * @throws SQLException the sql exception
     */
    public Item[] findByName(String name) throws SQLException {
        return dbConnect.findTaskByName(name);
    }

    /**
     * Find by description item [ ].
     *
     * @param description the description
     * @return the item [ ]
     * @throws SQLException the sql exception
     */
    public Item[] findByDescription(String description) throws SQLException {
        return dbConnect.findTaskByDescription(description);
    }

    /**
     * Find by date item [ ].
     *
     * @param date the date
     * @return the item [ ]
     * @throws SQLException the sql exception
     */
    public Item[] findByDate(long date) throws SQLException {
        return dbConnect.findTaskByDate(date);
    }

    /**
     * Get all item [ ].
     *
     * @return the item [ ]
     * @throws SQLException the sql exception
     */
    public Item[] getAll() throws SQLException {
        return dbConnect.getAllTasks();
    }

    /**
     * Create connection.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    public void createConnection() throws IOException, SQLException {
        this.dbConnect.createConnection();
    }

    public void closeConnection() {
        this.dbConnect.closeConnection();
    }

    /**
     * Create structure.
     *
     * @throws SQLException the sql exception
     */
    public void createStructure() throws SQLException {
        this.dbConnect.createStructure();
    }
}