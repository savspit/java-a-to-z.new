package ru.shestakov.models;

public class Comment extends Item {

    private String itemId;
    public String text;

    public Comment(String itemId, String text) {
        this.itemId = itemId;
        this.text = text;
    }

    public String getItemId() {
        return this.itemId;
    }
}

