package shestakov.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.dao.impl.DBRole;
import shestakov.dao.impl.DBUser;
import shestakov.models.Role;
import shestakov.models.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The type Db utils.
 */
public class DBUtils {
    private static final Logger Log = LoggerFactory.getLogger(DBUtils.class);
    private static final String SQL_CREATE_TABLE_ROLES = "CREATE TABLE IF NOT EXISTS roles (id IDENTITY, name VARCHAR(255) NOT NULL UNIQUE)";
    private static final String SQL_CREATE_TABLE_ADDRESSES = "CREATE TABLE IF NOT EXISTS addresses (id IDENTITY, text VARCHAR(2000))";
    private static final String SQL_CREATE_TABLE_MUSICTYPES = "CREATE TABLE IF NOT EXISTS musicTypes (id IDENTITY, name VARCHAR(255))";
    private static final String SQL_CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS users (id IDENTITY, login VARCHAR(255) NOT NULL UNIQUE, name VARCHAR(255), addressId INTEGER REFERENCES addresses(id) UNIQUE, roleId INTEGER REFERENCES roles(id))";
    private static final String SQL_CREATE_TABLE_USERS_AND_MUSICTYPES = "CREATE TABLE IF NOT EXISTS usersAndMusicTypes (id IDENTITY, usersId INTEGER REFERENCES users(id), musicTypeId INTEGER REFERENCES musicTypes(id))";
    private static final String SQL_DELETE_ROLES = "DELETE FROM roles";
    private static final String SQL_DELETE_ADDRESSES = "DELETE FROM roles";
    private static final String SQL_DELETE_MUSICTYPES = "DELETE FROM roles";
    private static final String SQL_DELETE_USERS = "DELETE FROM roles";
    private static final String SQL_DELETE_USERS_AND_MUSICTYPES = "DELETE FROM roles";
    private static final String SQL_FILL_DEFAULT_DATA_ROLES = "INSERT INTO roles(name) VALUES ('admin'),('user'),('mandator')";
    private static final String SQL_FILL_DEFAULT_DATA_MUSICTYPES = "INSERT INTO musicTypes(name) VALUES ('rap'),('rock'),('r&b'),('punk'),('blues'),('classic'),('industrial'),('jazz'),('nu metal')";
    private static final DBConnector instance = DBConnector.getInstance();

    /**
     * Init.
     *
     * @throws IOException the io exception
     */
    public void init() throws IOException {
        if (instance.isFirstStart()) {
            createTables();
            clearAllData();
            createRoot();
            fillDefaultData();
        }
    }

    /**
     * Gets db queries.
     *
     * @return the db queries
     * @throws IOException the io exception
     */
    public Properties getDBQueries() throws IOException {
        Properties props = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream("dbqueries.properties");
        if (in == null)
        {
            props.setProperty("tableRoles", SQL_CREATE_TABLE_ROLES);
            props.setProperty("tableAddresses", SQL_CREATE_TABLE_ADDRESSES);
            props.setProperty("tableMusicTypes", SQL_CREATE_TABLE_MUSICTYPES);
            props.setProperty("tableUsers", SQL_CREATE_TABLE_USERS);
            props.setProperty("tableUsersAndMusicTypes", SQL_CREATE_TABLE_USERS_AND_MUSICTYPES);
        } else {
            props.load(in);
        }
        in.close();
        return props;
    }

    /**
     * Create tables.
     *
     * @throws IOException the io exception
     */
    public void createTables() throws IOException {
        Connection conn = instance.getConnection();
        Properties props = getDBQueries();
        try (
                PreparedStatement st1 = conn.prepareStatement(props.getProperty("tableRoles"));
                PreparedStatement st2 = conn.prepareStatement(props.getProperty("tableAddresses"));
                PreparedStatement st3 = conn.prepareStatement(props.getProperty("tableMusicTypes"));
                PreparedStatement st4 = conn.prepareStatement(props.getProperty("tableUsers"));
                PreparedStatement st5 = conn.prepareStatement(props.getProperty("tableUsersAndMusicTypes"));
        ) {
            st1.executeUpdate();
            st1.close();
            st2.executeUpdate();
            st2.close();
            st3.executeUpdate();
            st3.close();
            st4.executeUpdate();
            st4.close();
            st5.executeUpdate();
            st5.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    /**
     * Clear all data.
     */
    public void clearAllData() {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st1 = conn.prepareStatement(SQL_DELETE_ROLES);
                PreparedStatement st2 = conn.prepareStatement(SQL_DELETE_ADDRESSES);
                PreparedStatement st3 = conn.prepareStatement(SQL_DELETE_MUSICTYPES);
                PreparedStatement st4 = conn.prepareStatement(SQL_DELETE_USERS);
                PreparedStatement st5 = conn.prepareStatement(SQL_DELETE_USERS_AND_MUSICTYPES);
        ) {
            st1.executeUpdate();
            st2.executeUpdate();
            st3.executeUpdate();
            st4.executeUpdate();
            st5.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    /**
     * Create root.
     */
    public void createRoot() {
        Role role = new Role("root");
        User user = new User();
        user.setLogin("root");
        user.setName("root");
        user.setRole(role);
        DBRole dbRole = new DBRole();
        dbRole.create(role);
        DBUser dbUser = new DBUser();
        dbUser.create(user);
    }

    /**
     * Fill default data.
     */
    public void fillDefaultData() {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st1 = conn.prepareStatement(SQL_FILL_DEFAULT_DATA_ROLES);
                PreparedStatement st2 = conn.prepareStatement(SQL_FILL_DEFAULT_DATA_MUSICTYPES);
        ) {
            st1.executeUpdate();
            st1.close();
            st2.executeUpdate();
            st2.close();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

}
