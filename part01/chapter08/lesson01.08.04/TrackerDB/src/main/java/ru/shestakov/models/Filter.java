package ru.shestakov.models;

/**
 * Init interface for "find" operations
 */
public interface Filter {

    /**
     * Method`s instruction for subclasses
     * @param item
     * @return
     */
    boolean check(Item item);

 }