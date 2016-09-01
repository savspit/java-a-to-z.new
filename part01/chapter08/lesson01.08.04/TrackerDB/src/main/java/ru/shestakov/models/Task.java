package ru.shestakov.models;

/**
 * Init Task class
 */
public class Task extends Item {

    /**
     * Initializes a newly created Task
     */
    public Task() {

    }

    /**
     * Initializes a newly created Task with params
     * @param name
     * @param desc
     * @param create
     */
    public Task(String name, String desc, long create) {
        this.name = name;
        this.description = desc;
        this.create = create;
    }

    /**
     * Some kind of method
     * @return
     */
    public String calculatePrice() {
        return "100%";
    }

}