package ru.shestakov.models;

/**
 * Init class for "find by create" operation
 */
public class FilterByCreate implements Filter {

    final private long create;

    /**
     * Initializes a newly created filter by create
     * @param create
     */
    public FilterByCreate(long create) {
        this.create = create;
    }

    /**
     * Checks value on equals
     * @param item
     * @return
     */
    public boolean check(Item item) {
        return (item != null) && (new Long(item.getCreate()).equals(create));
    }
}