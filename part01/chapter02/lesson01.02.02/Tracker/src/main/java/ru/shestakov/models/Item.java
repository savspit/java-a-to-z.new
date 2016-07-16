package ru.shestakov.models;

public class Item {

    private String id;
    public String name;
    public String description;
    public long create;
    public Comment[] comments = new Comment[10];

    public Item() {

    }

    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public long getCreate() {
        return this.create;
    }

    public Comment[] getComments() {
        return this.comments;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreate(long create) {
        this.create = create;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComment(Comment newComment) {
        for (int x=0; x<this.comments.length; x++) {
            if(this.comments[x] == null) {
                this.comments[x] = newComment;
                break;
            }
        }
    }
}