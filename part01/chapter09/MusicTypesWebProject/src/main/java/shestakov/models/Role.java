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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        return result;
    }
}
