package shestakov.models;

import java.sql.Timestamp;

public class User {
    private String name;
    private String login;
    private String email;
    private long createDate;

    public User(String login) {
        this.login = login;
    }

    public User(String name, String login, String email, long createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", this.login, this.name, this.email, new Timestamp(this.createDate).toString());
    }
}
