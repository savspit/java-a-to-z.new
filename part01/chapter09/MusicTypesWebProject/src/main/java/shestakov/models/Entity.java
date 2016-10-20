package shestakov.models;

/**
 * The type Entity.
 */
public abstract class Entity {
    /**
     * The Id.
     */
    public int id;

    /**
     * Instantiates a new Entity.
     */
    public Entity() {
    }

    /**
     * Instantiates a new Entity.
     *
     * @param id the id
     */
    public Entity(int id) {
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

}
