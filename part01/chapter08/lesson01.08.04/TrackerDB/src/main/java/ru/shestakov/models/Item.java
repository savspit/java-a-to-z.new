package ru.shestakov.models;

/**
 * Init Item class
 */
public class Item {

    /** Item`s ID */
    private String id;
    /** Item`s name */
    public String name;
    /** Item`s description */
    public String description;
    /** Item`s date */
    public long create;
    /** Item`s array of comments */
    public Comment[] comments = new Comment[10];

    /**
     * Initializes a newly created Item
     */
    public Item() {

    }

    /**
     * Initializes a newly created Item with params
     * @param name
     * @param description
     * @param create
     */
    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
    }

    /**
     * Gets name of Item
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets description of Item
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets date of Item
     * @return
     */
    public long getCreate() {
        return this.create;
    }

    /**
     * Gets array of comments of Item
     * @return
     */
    public Comment[] getComments() {
        return this.comments;
    }

    /**
     * Gets ID of Item
     * @return
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set Item`s ID
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set Item`s name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set Item`s date
     * @param create
     */
    public void setCreate(long create) {
        this.create = create;
    }

    /**
     * Set Item`s description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set Item`s comment
     * @param newComment
     */
    public void setComment(Comment newComment) {
        for (int x=0; x<this.comments.length; x++) {
            if(this.comments[x] == null) {
                this.comments[x] = newComment;
                break;
            }
        }
    }
}