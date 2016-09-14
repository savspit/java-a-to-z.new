package shestakov.postgresql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.models.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtils {
    private static final Logger Log = LoggerFactory.getLogger(DBUtils.class);
    private Connection conn;
    private String url;
    private String username;
    private String password;

    public void setProperties() {
        Properties prop = new Properties();
        try (
                FileInputStream fis = new FileInputStream(this.getClass().getClassLoader().getResource("usersservletwebproject.properties").getPath());
        ) {
            prop.load(fis);
            this.url = prop.getProperty("url").toString();
            this.username = prop.getProperty("username").toString();
            this.password = prop.getProperty("password").toString();
        } catch (IOException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public void openConnection() {
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            this.conn = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public void addUser(User user) {
        try (
                PreparedStatement st = this.conn.prepareStatement("INSERT INTO users(name, login, email, createDate) VALUES (?, ?, ?, ?)");
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setTimestamp(4, new Timestamp(user.getCreateDate()));
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public User getUserByLogin(String login) {
        User user = null;
        try (
                PreparedStatement st = this.conn.prepareStatement("SELECT u.name, u.login, u.email, u.createDate FROM users AS u WHERE u.login = ? ORDER BY u.id LIMIT 1");
        ) {
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("name"), rs.getString("login"), rs.getString("email"), rs.getTimestamp("createDate").getTime());
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return user;
    }

    public void updateUserByLogin(User user) {
        try (
                PreparedStatement st = this.conn.prepareStatement("UPDATE users SET name=?, email=?, createDate=? WHERE login=?");
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setTimestamp(3, new Timestamp(user.getCreateDate()));
            st.setString(4, user.getLogin());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public void deleteUserByLogin(User user) {
        try (
                PreparedStatement st = this.conn.prepareStatement("DELETE FROM users WHERE login=?");
        ) {
            st.setString(1, user.getLogin());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
