package shestakov.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.dao.IUser;
import shestakov.models.Role;
import shestakov.models.User;
import shestakov.services.DBConnector;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The type Db user.
 */
public class DBUser implements IUser {
    private static final Logger Log = LoggerFactory.getLogger(DBUser.class);
    private static final String SQL_CREATE = "INSERT INTO users(login, name, roleId) VALUES (?, ?, (SELECT r.id FROM roles AS r WHERE r.name = ?))";
    private static final String SQL_UPDATE = "UPDATE users SET name=?, WHERE login=?";
    private static final String SQL_DELETE = "DELETE FROM users WHERE login=?";
    private static final String SQL_GET_BY_ID = "SELECT u.id, u.name, u.login, coalesce(r.name,'') as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id WHERE u.id = ?";
    private static final String SQL_GET_BY_LOGIN = "SELECT u.id, u.name, u.login, coalesce(r.name,'') as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id WHERE u.login = ?";
    private static final String SQL_GET_ALL = "SELECT u.id, u.name, u.login, r.name as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id";
    private static final DBConnector instance = DBConnector.getInstance();

    @Override
    public void create(User user) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_CREATE);
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin().trim());
            st.setString(3, user.getRole().getName().trim());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    @Override
    public User getById(int id) {
        Connection conn = instance.getConnection();
        User user = null;
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_BY_ID);
        ) {
            st.setInt(1, id);
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setName(rs.getString("name"));
                    user.setRole(new Role(rs.getString("role")));
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
        return user;
    }

    @Override
    public void update(User user) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_UPDATE);
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin().trim());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    @Override
    public void delete(User user) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_DELETE);
        ) {
            st.setString(1, user.getLogin().trim());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    @Override
    public List<User> getAll() {
        Connection conn = instance.getConnection();
        List<User> users = new CopyOnWriteArrayList<User>();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_ALL);
        ) {
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setLogin(rs.getString("login"));
                    user.setRole(new Role(rs.getString("role")));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
        return users;
    }

    @Override
    public User getByLogin(String login) {
        Connection conn = instance.getConnection();
        User user = null;
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_BY_LOGIN);
        ) {
            st.setString(1, login.trim());
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setName(rs.getString("name"));
                    user.setRole(new Role(rs.getString("role")));
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
        return user;
    }

    @Override
    public boolean isRoot(String login) {
        DBRole dbRole = new DBRole();
        return "root".equals(dbRole.getByUserLogin(login).getName());
    }
}
