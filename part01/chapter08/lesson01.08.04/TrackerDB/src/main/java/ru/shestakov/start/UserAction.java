package ru.shestakov.start;

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
    void execute(Input input, Tracker tracker);

    /**
     * Method`s instruction for subclasses
     * @return
     */
    String info();

}