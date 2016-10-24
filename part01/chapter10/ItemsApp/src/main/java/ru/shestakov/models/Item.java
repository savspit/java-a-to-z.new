package ru.shestakov.models;

import java.sql.Timestamp;

/**
 * The type Item.
 */
public class Item {
    private int id;
    private String description;
    private Timestamp created_date;
    private boolean done;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Timestamp getCreated_date() {
        return created_date;
    }

    /**
     * Sets created date.
     *
     * @param created_date the created date
     */
    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    /**
     * Gets done.
     *
     * @return the done
     */
    public boolean getDone() {
        return done;
    }

    /**
     * Sets done.
     *
     * @param done the done
     */
    public void setDone(boolean done) {
        this.done = done;
    }
}
