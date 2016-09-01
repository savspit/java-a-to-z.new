package ru.shestakov.start;

import java.sql.SQLException;

/**
 * Init UserAction interface
 */
public interface UserAction {

    /**
     * Method`s instruction for subclasses
     * @return
     */
    int key();

    /**
     * Method`s instruction for subclasses
     * @param input
     * @param tracker
     */
    void execute(Input input, Tracker tracker) throws SQLException;

    /**
     * Method`s instruction for subclasses
     * @return
     */
    String info();

}