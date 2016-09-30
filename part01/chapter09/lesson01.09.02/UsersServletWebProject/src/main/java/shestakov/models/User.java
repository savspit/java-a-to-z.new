package shestakov.models;

import java.sql.Timestamp;

/**
 * The type User.
 */
public class User {
    private String name;
    private String login;
    private String email;
    private long createDate;
    private Role role;

    /**
     * Instantiates a new User.
     */
    public User() {

    }

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
     * @param name  the name
     * @param login the login
     * @param email the email
     */
    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
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
        this.role = new Role();
    }

    /**
     * Instantiates a new User.
     *
     * @param name       the name
     * @param login      the login
     * @param email      the email
     * @param createDate the create date
     * @param role       the role
     */
    public User(String name, String login, String email, long createDate, Role role) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.role = role;
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
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("Login: %s, Name: %s, Email: %s, Create date: %s, Role: %s", this.login, this.name, this.email, new Timestamp(this.createDate).toString(), this.role.getName());
    }
}
