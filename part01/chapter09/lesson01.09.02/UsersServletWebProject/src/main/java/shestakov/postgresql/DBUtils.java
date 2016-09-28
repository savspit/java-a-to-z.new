package shestakov.postgresql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.models.User;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DBUtils {
    private static final Logger Log = LoggerFactory.getLogger(DBUtils.class);
    private static final DBUtils instance = new DBUtils();
    private DataSource datasource;

    private DBUtils() {
        try {
            init();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    public static DBUtils getInstance() {
        return instance;
    }

    public void init() throws Exception {
        try {
            InitialContext initialContext = new InitialContext();
            if ( initialContext == null ) {
                Log.error("There was no InitialContext in GetServlet. Error occured.");
            }
            this.datasource = (DataSource) initialContext.lookup( "java:/comp/env/jdbc/postgres" );
            if ( this.datasource == null ) {
                Log.error("Could not find DataSource in GetServlet. Error occured.");
            }
        }
        catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = this.datasource.getConnection();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return conn;
    }

    public void setDatasource(DataSource datasource) {
        this.datasource = datasource;
    }

    public void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    public void addUser(User user) {
        Connection conn = getConnection();
        try (
                PreparedStatement st = conn.prepareStatement("INSERT INTO users(name, login, email, createDate) VALUES (?, ?, ?, ?)");
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setTimestamp(4, new Timestamp(user.getCreateDate()));
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
    }

    public User getUserByLogin(String login) {
        Connection conn = getConnection();
        User user = null;
        try (
                PreparedStatement st = conn.prepareStatement("SELECT u.name, u.login, u.email, u.createDate FROM users AS u WHERE u.login = ?");
        ) {
            st.setString(1, login.trim());
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                if (rs.next()) {
                    user = new User(rs.getString("name"), rs.getString("login"), rs.getString("email"), rs.getTimestamp("createDate").getTime());
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
        return user;
    }

    public void updateUserByLogin(User user) {
        Connection conn = getConnection();
        try (
                PreparedStatement st = conn.prepareStatement("UPDATE users SET name=?, email=? WHERE login=?");
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getLogin().trim());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
    }

    public void deleteUserByLogin(User user) {
        Connection conn = getConnection();
        try (
                PreparedStatement st = conn.prepareStatement("DELETE FROM users WHERE login=?");
        ) {
            st.setString(1, user.getLogin().trim());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
    }

    public List<User> getAllUsers() {
        Connection conn = getConnection();
        List<User> users = new CopyOnWriteArrayList<User>();
        try (
                PreparedStatement st = conn.prepareStatement("SELECT u.name, u.login, u.email, u.createDate FROM users AS u");
        ) {
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    users.add(new User(rs.getString("name"), rs.getString("login"), rs.getString("email"), rs.getTimestamp("createDate").getTime()));
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
        return users;
    }
}
