package ru.shestakov.models;

/**
 * The type Transmission.
 */
public class Transmission {
    /**
     * id field.
     */
    private int id;
    /**
     * name field.
     */
    private String name;

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
        return "Transmission ["
                + "id=" + id
                + ", name='" + name + '\''
                + ']';
    }
}
