package ru.shestakov.models;

/**
 * Init class for "find by name" operation
 */
public class FilterByName implements Filter {

    final private String name;

    /**
     * Initializes a newly created filter by name
     * @param name
     */
    public FilterByName(String name) {
        this.name = name;
    }

    /**
     * Checks value on equals
     * @param item
     * @return
     */
    public boolean check(Item item) {
        return (item != null) && (item.getName().contains(name));
    }

}