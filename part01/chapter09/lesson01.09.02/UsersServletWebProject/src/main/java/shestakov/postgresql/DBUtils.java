package shestakov.postgresql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.models.User;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

/**
 * The type Db utils.
 */
public class DBUtils {
    private static final Logger Log = LoggerFactory.getLogger(DBUtils.class);
    private DataSource datasource;

    /**
     * Init.
     *
     * @throws Exception the exception
     */
    public void init() throws Exception {
        try {
            InitialContext initialContext = new InitialContext();
            if ( initialContext == null ) {
                Log.error("There was no InitialContext in UsersServlet. Error occured.");
            }
            this.datasource = (DataSource) initialContext.lookup( "java:/comp/env/jdbc/postgres" );
            if ( this.datasource == null ) {
                Log.error("Could not find DataSource in UsersServlet. Error occured.");
            }
        }
        catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
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

    /**
     * Close connection.
     *
     * @param conn the conn
     */
    public void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Add user.
     *
     * @param user the user
     */
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

    /**
     * Gets user by login.
     *
     * @param login the login
     * @return the user by login
     */
    public User getUserByLogin(String login) {
        Connection conn = getConnection();
        User user = null;
        try (
                PreparedStatement st = conn.prepareStatement("SELECT u.name, u.login, u.email, u.createDate FROM users AS u WHERE u.login = ?");
        ) {
            st.setString(1, login);
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

    /**
     * Update user by login.
     *
     * @param user the user
     */
    public void updateUserByLogin(User user) {
        Connection conn = getConnection();
        try (
                PreparedStatement st = conn.prepareStatement("UPDATE users SET name=?, email=?, createDate=? WHERE login=?");
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setTimestamp(3, new Timestamp(user.getCreateDate()));
            st.setString(4, user.getLogin());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
    }

    /**
     * Delete user by login.
     *
     * @param user the user
     */
    public void deleteUserByLogin(User user) {
        Connection conn = getConnection();
        try (
                PreparedStatement st = conn.prepareStatement("DELETE FROM users WHERE login=?");
        ) {
            st.setString(1, user.getLogin());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
    }
}
