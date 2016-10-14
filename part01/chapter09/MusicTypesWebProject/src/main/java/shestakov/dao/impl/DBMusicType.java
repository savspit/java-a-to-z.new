package shestakov.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.dao.IMusicType;
import shestakov.models.MusicType;
import shestakov.services.DBConnector;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The type Db music type.
 */
public class DBMusicType implements IMusicType {
    private static final Logger Log = LoggerFactory.getLogger(DBMusicType.class);
    private static final String SQL_CREATE = "INSERT INTO musicTypes(name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE musicTypes SET name=?, WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM musicTypes WHERE id=?";
    private static final String SQL_GET_BY_ID = "SELECT m.id, m.name FROM musicTypes AS m WHERE m.id = ?";
    private static final String SQL_GET_ALL = "SELECT m.id, m.name FROM musicTypes AS m";
    private static final DBConnector instance = DBConnector.getInstance();

    @Override
    public void create(MusicType musicType) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_CREATE);
        ) {
            st.setString(1, musicType.getName());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    @Override
    public MusicType getById(int id) {
        Connection conn = instance.getConnection();
        MusicType musicType = null;
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_BY_ID);
        ) {
            st.setInt(1, id);
            try (
                    ResultSet rs = st.executeQuery();
            ) {
                if (rs.next()) {
                    musicType = new MusicType();
                    musicType.setId(rs.getInt("id"));
                    musicType.setName(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
        return musicType;
    }

    @Override
    public void update(MusicType musicType) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_UPDATE);
        ) {
            st.setString(1, musicType.getName());
            st.setInt(2, musicType.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    @Override
    public void delete(MusicType musicType) {
        Connection conn = instance.getConnection();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_DELETE);
        ) {
            st.setInt(1, musicType.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        instance.closeConnection(conn);
    }

    @Override
    public List<MusicType> getAll() {
        Connection conn = instance.getConnection();
        List<MusicType> musicTypes = new CopyOnWriteArrayList<MusicType>();
        try (
                PreparedStatement st = conn.prepareStatement(SQL_GET_ALL);
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
}
