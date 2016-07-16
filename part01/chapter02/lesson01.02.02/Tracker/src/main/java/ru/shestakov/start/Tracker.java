package ru.shestakov.start;

import ru.shestakov.models.*;
import java.util.*;

public class Tracker {

    private Item[] items = new Item[10];
    private int position = 0;
    private static final Random RN = new Random();

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
    }

    public void addComment(Comment newComment) {
        Item item = this.findById(newComment.getItemId());
        if(item != null) {
            item.setComment(newComment);
        }
    }

    public void update(Item item) {
        for (int index=0; index<this.position; index++) {
            if (this.items[index].getId().equals(item.getId())) {
                this.items[index] = item;
                break;
            }
        }
    }

    public void delete(Item itemForDelete) {
        for (int index=0; index<this.position; index++) {
            if (this.items[index].getId().equals(itemForDelete.getId())) {
                this.items[index] = null;
                break;
            }
        }
	}

    protected Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    public Item findByIdTest(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    public Task findBy(Filter filter) {
        return (Task) filter.checkIn(this.items);
    }

    String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    public Item[] getAll() {
        Item[] result = new Item[position];
        for (int index=0; index!=this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }
}