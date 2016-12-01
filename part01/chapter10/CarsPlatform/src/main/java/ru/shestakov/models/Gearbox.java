package ru.shestakov.models;

/**
 * The type Gearbox.
 */
public class Gearbox {
    /**
     * id field.
     */
    private int id;
    /**
     * name field.
     */
    private String name;

    /**
     * Instantiates a new Gearbox.
     */
    public Gearbox() {
    }

    /**
     * Instantiates a new Gearbox.
     *
     * @param id the id
     */
    public Gearbox(int id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Gearbox ["
                + "id=" + id
                + ", name='" + name + '\''
                + ']';
    }
}
