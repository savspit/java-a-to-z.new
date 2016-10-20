package shestakov.models;

/**
 * The type Address.
 */
public class Address extends Entity{

    private String text;

    /**
     * Instantiates a new Address.
     */
    public Address() {
        super();
    }

    /**
     * Instantiates a new Address.
     *
     * @param id the id
     */
    public Address(int id) {
        super(id);
    }

    /**
     * Instantiates a new Address.
     *
     * @param text the text
     */
    public Address(String text) {
        this.text = text;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return id == address.id;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        return result;
    }
}
