package ru.shestakov.models;

/**
 * Init class of Item`s comment
 */
public class Comment extends Item {

    /** Item`s ID */
    private String itemId;
    /** Comment`s text */
    public String text;

    /**
     * Initializes a newly created Comment
     * @param itemId
     * @param text
     */
    public Comment(String itemId, String text) {
        this.itemId = itemId;
        this.text = text;
    }

    /**
     * Gets Item`s ID
     * @return
     */
    public String getItemId() {
        return this.itemId;
    }

    /**
     * Get`s Comment`s text
     * @return
     */
    public String getText() {
        return this.text;
    }
}