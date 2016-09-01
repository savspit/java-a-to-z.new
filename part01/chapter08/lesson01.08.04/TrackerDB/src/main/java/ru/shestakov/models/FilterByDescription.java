package ru.shestakov.models;

/**
 * Init class for "find by description" operation
 */
public class FilterByDescription implements Filter {

    final private String desc;

    /**
     * Initializes a newly created filter by description
     * @param desc
     */
    public FilterByDescription(String desc) {
        this.desc = desc;
    }

    /**
     * Checks value on equals
     * @param item
     * @return
     */
    public boolean check(Item item) {
        return (item != null) && (item.getDescription().contains(desc));
    }
}