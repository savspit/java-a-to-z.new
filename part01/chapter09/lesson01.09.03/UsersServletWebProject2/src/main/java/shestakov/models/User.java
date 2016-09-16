package shestakov.models;

/**
 * The type User.
 */
public class User {
    private String name;
    private String login;
    private String email;
    private long createDate;

    /**
     * Instantiates a new User.
     *
     * @param login the login
     */
    public User(String login) {
        this.login = login;
    }

    /**
     * Instantiates a new User.
     *
     * @param name       the name
     * @param login      the login
     * @param email      the email
     * @param createDate the create date
     */
    public User(String name, String login, String email, long createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
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
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets create date.
     *
     * @return the create date
     */
    public long getCreateDate() {
        return createDate;
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
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", this.name, this.login, this.email, this.createDate);
    }
}
