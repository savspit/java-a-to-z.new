package shestakov.models;

/**
 * The type Role.
 */
public class Role extends Entity {

    private String name;

    /**
     * Instantiates a new Role.
     */
    public Role() {
        super();
    }

    /**
     * Instantiates a new Role.
     *
     * @param id the id
     */
    public Role(int id) {
        super(id);
    }

    /**
     * Instantiates a new Role.
     *
     * @param name the name
     */
    public Role(String name) {
        this.name = name;
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
}
