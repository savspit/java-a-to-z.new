package shestakov.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.dao.IAddress;
import shestakov.models.Address;
import shestakov.services.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The type Db address.
 */
public class DBAddress implements IAddress {
    private static final Logger Log = LoggerFactory.getLogger(DBAddress.class);
    private static final String SQL_CREATE = "INSERT INTO addresses(name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE addresses SET text=?, WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM addresses WHERE id=?";
    private static final String SQL_GET_BY_ID = "SELECT a.id, a.text FROM addresses AS a WHERE a.id = ?";
    private static final String SQL_GET_ALL = "SELECT a.id, a.text FROM addresses AS a";
    private static final DBConnector instance = DBConnector.getInstance();

    @Override
    public void create(Address address) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_CREATE);
        ) {
            st.setString(1, address.getText());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    @Override
    public Address getById(int id) {
        Connection conn = instance.getConnection();
        Address address = null;
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_BY_ID);
        ) {
            st.setInt(1, id);
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

    @Override
    public void update(Address address) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_UPDATE);
        ) {
            st.setString(1, address.getText());
            st.setInt(2, address.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    @Override
    public void delete(Address address) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_DELETE);
        ) {
            st.setInt(1, address.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    @Override
    public List<Address> getAll() {
        Connection conn = instance.getConnection();
        List<Address> addresses = new CopyOnWriteArrayList<Address>();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_ALL);
        ) {
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                while (rs.next()) {
                    Address address = new Address();
                    address.setId(rs.getInt("id"));
                    address.setText(rs.getString("text"));
                    addresses.add(address);
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
        return addresses;
    }
}
