package shestakov.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.dao.IRole;
import shestakov.models.Role;
import shestakov.db.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The type Db role.
 */
public class RoleImpl implements IRole {
    private static final Logger Log = LoggerFactory.getLogger(RoleImpl.class);
    private static final String SQL_CREATE = "INSERT INTO roles(name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE roles SET name=?, WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM roles WHERE id=?";
    private static final String SQL_GET_BY_ID = "SELECT r.id, r.name FROM roles AS r WHERE r.id = ?";
    private static final String SQL_GET_BY_USER_LOGIN = "SELECT r.name FROM roles AS r JOIN users AS u ON u.roleId = r.id AND u.login = ?";
    private static final String SQL_GET_ALL = "SELECT r.id, r.name FROM roles AS r";
    private static final DataSource instance = DataSource.getInstance();

    @Override
    public void create(Role role) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_CREATE);
        ) {
            st.setString(1, role.getName().trim());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    @Override
    public Role getById(int id) {
        Connection conn = instance.getConnection();
        Role role = null;
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_BY_ID);
        ) {
            st.setInt(1, id);
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                if (rs.next()) {
                    role = new Role();
                    role.setId(rs.getInt("id"));
                    role.setName(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
        return role;
    }

    @Override
    public void update(Role role) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_UPDATE);
        ) {
            st.setString(1, role.getName());
            st.setInt(2, role.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    @Override
    public void delete(Role role) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_DELETE);
        ) {
            st.setInt(1, role.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    @Override
    public List<Role> getAll() {
        Connection conn = instance.getConnection();
        List<Role> roles = new CopyOnWriteArrayList<Role>();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_ALL);
        ) {
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    Role role = new Role();
                    role.setId(rs.getInt("id"));
                    role.setName(rs.getString("name"));
                    roles.add(role);
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
        return roles;
    }

    @Override
    public Role getByName(String name) {
        Connection conn = instance.getConnection();
        Role role = null;
        try (
                PreparedStatement st = conn.prepareStatement("SELECT r.id, r.name FROM roles AS r WHERE r.name = ?");
        ) {
            st.setString(1, name.trim());
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                if (rs.next()) {
                    role = new Role();
                    role.setId(rs.getInt("id"));
                    role.setName(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
        return role;
    }

    @Override
    public Role getByUserLogin(String login) {
        Connection conn = instance.getConnection();
        Role role = null;
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_BY_USER_LOGIN);
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
        instance.closeConnection(conn);
        return role;
    }
}
