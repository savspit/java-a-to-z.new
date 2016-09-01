package ru.shestakov.templates;

import ru.shestakov.start.*;

/**
 * Init BaseAction abstract class
 */
public abstract class BaseAction implements UserAction {

    public String name;

    /**
     * Initializes a newly created BaseAction
     * @param name
     */
    public BaseAction(String name) {
        this.name = name;
    }

    /**
     * Method for key of user`s action
     * @return
     */
    public abstract int key();

    /**
     * Method executes user`s action
     * @param input
     * @param tracker
     */
    public abstract void execute(Input input, Tracker tracker);

    /**
     * Method returns formatted string of name of action
     * @return
     */
    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }

}