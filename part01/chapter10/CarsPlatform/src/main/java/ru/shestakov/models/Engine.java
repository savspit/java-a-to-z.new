package ru.shestakov.models;

/**
 * The type Engine.
 */
public class Engine {
    private int id;
    private String name;

    /**
     * Instantiates a new Engine.
     */
    public Engine() {
    }

    /**
     * Instantiates a new Engine.
     *
     * @param id the id
     */
    public Engine(int id) {
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
        return "Engine [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ']';
    }
}
