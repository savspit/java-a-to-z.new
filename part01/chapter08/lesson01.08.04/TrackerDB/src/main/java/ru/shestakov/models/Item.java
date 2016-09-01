package ru.shestakov.models;

/**
 * The type Item.
 */
public class Item {

    private String id;
    /**
     * The Name.
     */
    public String name;
    /**
     * The Description.
     */
    public String description;
    /**
     * The Create.
     */
    public long create;

    /**
     * Instantiates a new Item.
     */
    public Item() {

    }

    /**
     * Instantiates a new Item.
     *
     * @param name        the name
     * @param description the description
     * @param create      the create
     */
    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
    }

    /**
     * Instantiates a new Item.
     *
     * @param id          the id
     * @param name        the name
     * @param description the description
     * @param create      the create
     */
    public Item(String id, String name, String description, long create) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.create = create;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets create.
     *
     * @return the create
     */
    public long getCreate() {
        return this.create;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets create.
     *
     * @param create the create
     */
    public void setCreate(long create) {
        this.create = create;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}