package shestakov.postgresql;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.models.Role;
import shestakov.models.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;
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
            createTables();
            deleteAllUsersAndRoles();
            createRoot();
            deleteAllCountriesAndCities();
            fillCountriesAndCities();
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
     * Gets db properties.
     *
     * @return the db properties
     * @throws IOException the io exception
     */
    public Properties getDBProperties() throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream("db.properties");
        if(in == null)
        {
            Log.error("there is no db.properties found using defaults");
            return null;
        }
        Properties props = new Properties();
        props.load(in);
        in.close();
        return props;
    }

    /**
     * Gets db queries.
     *
     * @return the db queries
     * @throws IOException the io exception
     */
    public Properties getDBQueries() throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream("dbqueries.properties");
        if(in == null)
        {
            Log.error("there is no dbqueries.properties found using defaults");
            return null;
        }
        Properties props = new Properties();
        props.load(in);
        in.close();
        return props;
    }

    /**
     * Init.
     *
     * @throws Exception the exception
     */
    public void init() throws Exception {
        try {
            Properties prop = getDBProperties();

            Class.forName(prop.getProperty("driver"));

            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES,
                    "org.apache.naming");
            InitialContext ic = new InitialContext();

            ic.createSubcontext("java:");
            ic.createSubcontext("java:/comp");
            ic.createSubcontext("java:/comp/env");
            ic.createSubcontext("java:/comp/env/jdbc");

            DataSource ds = new DataSource();
            ds.setUrl(prop.getProperty("url"));
            ds.setUsername(prop.getProperty("username"));
            ds.setPassword(prop.getProperty("password"));

            ic.bind("java:/comp/env/jdbc/postgres", ds);

            Context initContext = new InitialContext();
            Context webContext = (Context)initContext.lookup("java:/comp/env");

            this.datasource = (DataSource) webContext.lookup("jdbc/postgres");

        } catch (NamingException e) {
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
        User user = new User();
        user.setName("root");
        user.setLogin("root");
        user.setEmail("root@root");
        user.setRole(role);
        addUser(user);
    }

    /**
     * Add user.
     *
     * @param user the user
     */
    public void addUser(User user) {
        Connection conn = getConnection();
        try (
                PreparedStatement st = conn.prepareStatement("INSERT INTO users(name, login, email, createDate, countryId, cityId, roleId) VALUES (?, ?, ?, ?, (SELECT cn.id FROM countries AS cn WHERE cn.name = ?), (SELECT ct.id FROM cities AS ct WHERE ct.name = ?), (SELECT r.id FROM roles AS r WHERE r.name = ?))");
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setTimestamp(4, new Timestamp(user.getCreateDate()));
            st.setString(5, user.getCountry());
            st.setString(6, user.getCity());
            st.setString(7, user.getRole().getName().trim());
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
                PreparedStatement st = conn.prepareStatement("SELECT u.name, u.login, u.email, u.createDate, coalesce(cn.name,'') as country, coalesce(ct.name,'') as city, coalesce(r.name,'') as role FROM users AS u LEFT JOIN countries AS cn ON u.countryId = cn.id LEFT JOIN cities AS ct ON u.cityId = ct.id LEFT JOIN roles AS r ON u.roleId = r.id WHERE u.login = ?");
        ) {
            st.setString(1, login.trim());
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                if (rs.next()) {
                    user = new User();
                    user.setLogin(rs.getString("login"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setCountry(rs.getString("country"));
                    user.setCity(rs.getString("city"));
                    user.setRole(new Role(rs.getString("role")));
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
                PreparedStatement st = conn.prepareStatement("UPDATE users SET name=?, email=?, countryId=(SELECT cn.id FROM countries AS cn WHERE cn.name=?), cityId=(SELECT ct.id FROM cities AS ct WHERE ct.name=?) WHERE login=?");
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getCountry());
            st.setString(4, user.getCity());
            st.setString(5, user.getLogin().trim());
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
                    User user = new User();
                    user.setName(rs.getString("name"));
                    user.setLogin(rs.getString("login"));
                    user.setEmail(rs.getString("email"));
                    user.setCreateDate(rs.getTimestamp("createDate").getTime());
                    user.setRole(new Role(rs.getString("role")));
                    users.add(user);
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
     * Gets all countries.
     *
     * @return the all countries
     */
    public List<String> getAllCountries() {
        Connection conn = getConnection();
        List<String> countries = new CopyOnWriteArrayList<String>();
        try (
                PreparedStatement st = conn.prepareStatement("SELECT c.name FROM countries AS c");
        ) {
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    countries.add(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
        return countries;
    }

    /**
     * Gets all cities.
     *
     * @return the all cities
     */
    public List<String> getAllCities() {
        Connection conn = getConnection();
        List<String> cities = new CopyOnWriteArrayList<String>();
        try (
                PreparedStatement st = conn.prepareStatement("SELECT c.name FROM cities AS c");
        ) {
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    cities.add(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
        return cities;
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

    /**
     * Is root boolean.
     *
     * @param login the login
     * @return the boolean
     */
    public boolean isRoot(String login) {
        return "root".equals(getRoleByUserLogin(login).getName());
    }

    /**
     * Create tables.
     *
     * @throws IOException the io exception
     */
    public void createTables() throws IOException {
        Connection conn = getConnection();
        Properties props = getDBQueries();
        try (
                PreparedStatement st1 = conn.prepareStatement(props.getProperty("tableRoles"));
                PreparedStatement st2 = conn.prepareStatement(props.getProperty("tableUsers"));
                PreparedStatement st3 = conn.prepareStatement(props.getProperty("tableCities"));
                PreparedStatement st4 = conn.prepareStatement(props.getProperty("tableCountries"));
        ) {
            st1.executeUpdate();
            st1.close();
            st2.executeUpdate();
            st2.close();
            st3.executeUpdate();
            st3.close();
            st4.executeUpdate();
            st4.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Fill countries.
     */
    public void fillCountries() {
        Connection conn = getConnection();
        try (
                PreparedStatement st1 = conn.prepareStatement("INSERT INTO countries(name) VALUES ('Russia'),('France'),('United States'),('Germany'),('Australia')");
        ) {
            st1.executeUpdate();
            st1.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Fill cities.
     */
    public void fillCities() {
        Connection conn = getConnection();
        try (
                PreparedStatement st1 = conn.prepareStatement("INSERT INTO cities(name) VALUES ('Moscow'),('Piter'),('Paris'),('Washington'),('Canberra'),('Berlin')");
        ) {
            st1.executeUpdate();
            st1.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * Fill countries and cities.
     */
    public void fillCountriesAndCities() {
        fillCountries();
        fillCities();
    }

    /**
     * Delete all countries and cities.
     */
    public void deleteAllCountriesAndCities() {
        Connection conn = getConnection();
        try (
                PreparedStatement st1 = conn.prepareStatement("DELETE FROM countries");
                PreparedStatement st2 = conn.prepareStatement("DELETE FROM cities");
        ) {
            st1.executeUpdate();
            st2.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        closeConnection(conn);
    }
}
