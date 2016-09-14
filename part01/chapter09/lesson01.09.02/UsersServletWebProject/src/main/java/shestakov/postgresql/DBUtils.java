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

    public void addUser(String name, String login, String email, Timestamp createDate) {
        try (
                PreparedStatement st = this.conn.prepareStatement("INSERT INTO users(name, login, email, createDate) VALUES (?, ?, ?, ?)");
        ) {
            st.setString(1, name);
            st.setString(2, login);
            st.setString(3, email);
            st.setTimestamp(4, createDate);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public String getUserByLogin(String login) {
        String userInfo = null;
        try (
                PreparedStatement st = this.conn.prepareStatement("SELECT u.name, u.login, u.email, u.createDate FROM users AS u WHERE u.login = ? ORDER BY u.id LIMIT 1");
        ) {
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                userInfo = String.format("%s, %s, %s, %s", rs.getString("name"), rs.getString("login"), rs.getString("email"), rs.getTimestamp("createDate").getTime());
            }
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return userInfo;
    }

    public void updateUserByLogin(String name, String login, String email, Timestamp createDate) {
        try (
                PreparedStatement st = this.conn.prepareStatement("UPDATE users SET name=?, email=?, createDate=? WHERE login=?");
        ) {
            st.setString(1, name);
            st.setString(2, email);
            st.setTimestamp(3, createDate);
            st.setString(4, login);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public void deleteUserByLogin(String login) {
        try (
                PreparedStatement st = this.conn.prepareStatement("DELETE FROM users WHERE login=?");
        ) {
            st.setString(1, login);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
