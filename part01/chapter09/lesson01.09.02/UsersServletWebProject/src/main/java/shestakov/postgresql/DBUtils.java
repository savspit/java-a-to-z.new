package shestakov.postgresql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.models.Role;
import shestakov.models.User;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The type Db utils.
 */
public class DBUtils {
    private static final Logger Log = LoggerFactory.getLogger(DBUtils.class);
    private static final DBUtils instance = new DBUtils();
    private DataSource datasource;

    private DBUtils() {
        try {
            init();
            deleteAllUsersAndRoles();
            createRoot();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DBUtils getInstance() {
        return instance;
    }

    /**
     * Init.
     *
     * @throws Exception the exception
     */
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

    /**
     * Sets datasource.
     *
     * @param datasource the datasource
     */
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
     * Create root.
     */
    public void createRoot() {
        Role role = new Role("root");
        addRole(role);
        addUser(new User("root", "root", "root@root", System.currentTimeMillis(), role));
    }

    /**
     * Add user.
     *
     * @param user the user
     */
    public void addUser(User user) {
        Connection conn = getConnection();
        try (
                PreparedStatement st = conn.prepareStatement("INSERT INTO users(name, login, email, createDate, roleId) VALUES (?, ?, ?, ?, (SELECT r.id FROM roles AS r WHERE r.name = ?))");
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setTimestamp(4, new Timestamp(user.getCreateDate()));
            st.setString(5, user.getRole().getName());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
    }

    /**
     * Add role.
     *
     * @param role the role
     */
    public void addRole(Role role) {
        Connection conn = getConnection();
        try (
                PreparedStatement st = conn.prepareStatement("INSERT INTO roles(name) VALUES (?)");
        ) {
            st.setString(1, role.getName());
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

    /**
     * Update user by login.
     *
     * @param user the user
     */
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
            st.setString(1, user.getLogin().trim());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
    }

    /**
     * Change users role.
     *
     * @param user the user
     * @param role the role
     */
    public void changeUsersRole(User user, Role role) {
        Connection conn = getConnection();
        try (
                //PreparedStatement st = conn.prepareStatement("UPDATE users SET roleId = roles.id FROM users AS u JOIN roles ON roles.name=? WHERE u.login=?");
                PreparedStatement st = conn.prepareStatement("UPDATE users SET roleId = (SELECT r.id FROM roles AS r WHERE r.name=?) WHERE login=?");
        ) {
            st.setString(1, role.getName());
            st.setString(2, user.getLogin().trim());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
    }

    /**
     * Gets role by name.
     *
     * @param name the name
     * @return the role by name
     */
    public Role getRoleByName(String name) {
        Connection conn = getConnection();
        Role role = null;
        try (
                PreparedStatement st = conn.prepareStatement("SELECT r.id FROM roles AS r WHERE r.name = ?");
        ) {
            st.setString(1, name.trim());
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                if (rs.next()) {
                    role = new Role(Integer.parseInt(rs.getString("id")), name);
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
        return role;
    }

    /**
     * Gets role by user login.
     *
     * @param login the login
     * @return the role by user login
     */
    public Role getRoleByUserLogin(String login) {
        Connection conn = getConnection();
        Role role = null;
        try (
                PreparedStatement st = conn.prepareStatement("SELECT r.name FROM roles AS r JOIN users AS u ON u.roleId = r.id AND u.login = ?");
        ) {
            st.setString(1, login.trim());
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                if (rs.next()) {
                    role = new Role(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
        return role;
    }

    /**
     * Update role by id.
     *
     * @param role the role
     */
    public void updateRoleById(Role role) {
        Connection conn = getConnection();
        try (
                PreparedStatement st = conn.prepareStatement("UPDATE roles SET name=? WHERE id=?");
        ) {
            st.setString(1, role.getName());
            st.setInt(2, role.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
    }

    /**
     * Delete role by name.
     *
     * @param role the role
     */
    public void deleteRoleByName(Role role) {
        Connection conn = getConnection();
        try (
                PreparedStatement st = conn.prepareStatement("DELETE FROM roles WHERE name=?");
        ) {
            st.setString(1, role.getName().trim());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    public List<User> getAllUsers() {
        Connection conn = getConnection();
        List<User> users = new CopyOnWriteArrayList<User>();
        try (
                PreparedStatement st = conn.prepareStatement("SELECT u.name, u.login, u.email, u.createDate, r.name as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id");
        ) {
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    users.add(new User(rs.getString("name"), rs.getString("login"), rs.getString("email"), rs.getTimestamp("createDate").getTime(), new Role(rs.getString("role"))));
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
        return users;
    }

    /**
     * Gets all roles.
     *
     * @return the all roles
     */
    public List<Role> getAllRoles() {
        Connection conn = getConnection();
        List<Role> roles = new CopyOnWriteArrayList<Role>();
        try (
                PreparedStatement st = conn.prepareStatement("SELECT r.name FROM roles AS r");
        ) {
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    roles.add(new Role(rs.getString("name")));
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
        return roles;
    }

    /**
     * Delete all users and roles.
     */
    public void deleteAllUsersAndRoles() {
        Connection conn = getConnection();
        try (
                PreparedStatement st1 = conn.prepareStatement("DELETE FROM users");
                PreparedStatement st2 = conn.prepareStatement("DELETE FROM roles");
        ) {
            st1.executeUpdate();
            st2.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
    }

    /**
     * Is credentional boolean.
     *
     * @param login the login
     * @return the boolean
     */
    public boolean isCredentional(String login) {
        return getUserByLogin(login) != null;
    }
}
