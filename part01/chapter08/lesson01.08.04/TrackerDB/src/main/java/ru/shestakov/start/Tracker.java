package ru.shestakov.start;

import ru.shestakov.models.*;
import java.util.*;

/**
 * Init Tracker class
 */
public class Tracker {

    /** Items array */
    private Item[] items = new Item[10];
    /** Position in Items array */
    private int position = 0;
    /** Random for Items ID */
    private static final Random RN = new Random();

    /**\
     * Add new Item in array
     * @param item
     * @return
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
    }

    /**
     * Add new comment for Item
     * @param newComment
     */
    public void addComment(Comment newComment) {
        Item item = this.findById(newComment.getItemId());
        if(item != null) {
            item.setComment(newComment);
        }
    }

    /**
     * Change Item in array by ID
     * @param item
     */
    public void update(Item item) {
        for (int index=0; index<this.position; index++) {
            if (this.items[index] != null && this.items[index].getId().equals(item.getId())) {
                this.items[index] = item;
                break;
            }
        }
    }

    /**
     * Delete Item in array
     * @param itemForDelete
     */
    public void delete(Item itemForDelete) {
        for (int index=0; index<this.position; index++) {
            if (this.items[index].getId().equals(itemForDelete.getId())) {
                this.items[index] = null;
                break;
            }
        }
	}

    /**
     * Find Item in array by ID
     * @param id
     * @return
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Find Item in array by filter
     * @param filter
     * @return
     */
    public Item[] findBy(Filter filter) {
        List<Item> result = new ArrayList<Item>();
        for (Item item : items) {
            if (filter.check(item)) { result.add(item); }
        }
        return result.toArray(new Item[result.size()]);
    }

    /**
     * Generates random ID for Item
     * @return
     */
    String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Gets array of all Items
     * @return
     */
    public Item[] getAll() {
        Item[] result = new Item[position];
        for (int index=0; index!=this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }
}