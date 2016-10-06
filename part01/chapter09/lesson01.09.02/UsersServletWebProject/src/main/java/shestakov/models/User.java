package shestakov.models;

import java.sql.Timestamp;

public class User {
    private String name;
    private String login;
    private String email;
    private long createDate;
    private String country;
    private String city;
    private Role role;

    public User() {

    }

    public User(String login) {
        this.name = "";
        this.login = login;
        this.email = "";
        this.createDate = System.currentTimeMillis();
        this.role = new Role("");
    }

    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = System.currentTimeMillis();
        this.role = new Role("");
    }

    public User(String name, String login, String email, long createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.role = new Role("");
    }

    public User(String name, String login, String email, long createDate, Role role) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public long getCreateDate() {
        return createDate;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("Login: %s, Name: %s, Email: %s, Create date: %s, Role: %s", this.login, this.name, this.email, new Timestamp(this.createDate).toString(), this.role.getName());
    }
}
