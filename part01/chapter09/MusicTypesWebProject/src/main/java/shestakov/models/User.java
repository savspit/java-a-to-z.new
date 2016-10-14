package shestakov.models;

/**
 * The type User.
 */
public class User extends Entity {
    private String login;
    private String name;
    private Role role;
    private Address address;
    private MusicType musicType;

    /**
     * Instantiates a new User.
     */
    public User() {
        super();
    }

    /**
     * Instantiates a new User.
     *
     * @param id the id
     */
    public User(int id) {
        super(id);
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
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
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Gets music type.
     *
     * @return the music type
     */
    public MusicType getMusicType() {
        return musicType;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
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
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Sets music type.
     *
     * @param musicType the music type
     */
    public void setMusicType(MusicType musicType) {
        this.musicType = musicType;
    }
}
