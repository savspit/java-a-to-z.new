package shestakov.models;

/**
 * The type Music type.
 */
public class MusicType extends Entity {

    private String name;

    /**
     * Instantiates a new Music type.
     */
    public MusicType() {
        super();
    }

    /**
     * Instantiates a new Music type.
     *
     * @param id the id
     */
    public MusicType(int id) {
        super(id);
    }

    /**
     * Instantiates a new Music type.
     *
     * @param name the name
     */
    public MusicType(String name) {
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
        if (!(o instanceof MusicType)) return false;
        MusicType musicType = (MusicType) o;
        return id == musicType.id;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        return result;
    }
}
