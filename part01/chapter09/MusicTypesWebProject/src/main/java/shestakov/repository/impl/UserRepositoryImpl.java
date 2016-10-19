package shestakov.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.dao.impl.UserImpl;
import shestakov.db.DataSource;
import shestakov.models.Address;
import shestakov.models.MusicType;
import shestakov.models.Role;
import shestakov.models.User;
import shestakov.repository.IUserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserRepositoryImpl implements IUserRepository{
    private static final Logger Log = LoggerFactory.getLogger(UserRepositoryImpl.class);
    private static final String SQL_GET_ROLE = "SELECT r.id, r.name FROM roles AS r JOIN users AS u ON r.id = u.roleId AND u.login = ?";
    private static final String SQL_GET_ADDRESS = "SELECT a.id, a.text FROM addresses AS a JOIN users AS u ON a.id = u.addressId AND u.login = ?";
    private static final String SQL_GET_MUSICTYPES = "SELECT mt.id, mt.name FROM musicTypes AS mt JOIN usersAndMusicTypes AS umt ON mt.id = umt.musicTypeId AND umt.userId = (SELECT id FROM users WHERE login = ?)";
    private static final String SQL_ADD_ADDRESS_TO_USER = "UPDATE users SET addressId = (SELECT id FROM addresses WHERE text = ?) WHERE login = ?";
    private static final String SQL_ADD_MUSICTYPE_TO_USER = "UPDATE usersAndMusicTypes SET userId = (SELECT id FROM users WHERE login = ?) WHERE musicTypeId = (SELECT id FROM musicTypes WHERE name = ?)";
    private static final String SQL_ADD_ROLE_TO_USER = "UPDATE users SET roleId = (SELECT id FROM roles WHERE name = ?) WHERE login = ?";
    private static final String SQL_GET_BY_NAME = "SELECT u.id, u.name, u.login, coalesce(r.name,'') as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id WHERE u.name = ?";
    private static final String SQL_GET_BY_ROLE = "SELECT u.id, u.name, u.login, coalesce(r.name,'') as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id AND r.name = ?";
    private static final String SQL_GET_BY_MUSICTYPE = "SELECT u.id, u.name, u.login, coalesce(r.name,'') as role FROM users AS u LEFT JOIN roles AS r ON u.roleId = r.id JOIN usersAndMusicTypes AS umt ON u.id = umt.usersId JOIN musicTypes AS mt ON mt.id = umt.musicTypeId AND mt.name = ?";
    private static final DataSource instance = DataSource.getInstance();

    public Role getRole(User user) {
        Connection conn = instance.getConnection();
        Role role = null;
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_ROLE);
        ) {
            st.setString(1, user.getLogin().trim());
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

    public Address getAddress(User user) {
        Connection conn = instance.getConnection();
        Address address = null;
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_ADDRESS);
        ) {
            st.setString(1, user.getLogin().trim());
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                if (rs.next()) {
                    address = new Address();
                    address.setId(rs.getInt("id"));
                    address.setText(rs.getString("text"));
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
        return address;
    }

    public List<MusicType> getMusicTypes(User user) {
        Connection conn = instance.getConnection();
        List<MusicType> musicTypes = new CopyOnWriteArrayList<MusicType>();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_MUSICTYPES);
        ) {
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    MusicType musicType = new MusicType();
                    musicType.setId(rs.getInt("id"));
                    musicType.setName(rs.getString("name"));
                    musicTypes.add(musicType);
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
        return musicTypes;
    }

    public void addUser(User user) {
        UserImpl userImpl = new UserImpl();
        userImpl.create(user);
    }

    public void addUsersAddress(User user, Address address) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_ADD_ADDRESS_TO_USER);
        ) {
            st.setString(1, address.getText());
            st.setString(2, user.getLogin().trim());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    public void addUsersMusicType(User user, MusicType musicType) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_ADD_MUSICTYPE_TO_USER);
        ) {
            st.setString(1, user.getLogin().trim());
            st.setString(2, musicType.getName());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    public void addUsersRole(User user, Role role) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_ADD_ROLE_TO_USER);
        ) {
            st.setString(1, role.getName());
            st.setString(2, user.getLogin().trim());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    public User getByLogin(String login) {
        UserImpl userImpl = new UserImpl();
        return userImpl.getByLogin(login);
    }

    public User getById(int id) {
        UserImpl userImpl = new UserImpl();
        return userImpl.getById(id);
    }

    public List<User> getByName(String name) {
        Connection conn = instance.getConnection();
        List<User> users = new CopyOnWriteArrayList<User>();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_BY_NAME);
        ) {
            st.setString(1, name.trim());
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

    public List<User> getByRole(Role role) {
        Connection conn = instance.getConnection();
        List<User> users = new CopyOnWriteArrayList<User>();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_BY_ROLE);
        ) {
            st.setString(1, role.getName().trim());
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

    public List<User> getByMusicType(MusicType musicType) {
        Connection conn = instance.getConnection();
        List<User> users = new CopyOnWriteArrayList<User>();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_BY_MUSICTYPE);
        ) {
            st.setString(1, musicType.getName().trim());
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
}
