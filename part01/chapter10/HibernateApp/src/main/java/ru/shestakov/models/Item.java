package ru.shestakov.models;

import java.util.List;

public class Item {
    private User author;
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
