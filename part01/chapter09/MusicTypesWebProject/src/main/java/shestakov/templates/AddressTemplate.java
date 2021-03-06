package shestakov.templates;

import shestakov.models.Entity;
import shestakov.models.MusicType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The type Address template.
 */
public class AddressTemplate extends Template{

    @Override
    public List<Entity> getListOfResults(ResultSet rs) throws SQLException {
        List<Entity> result = new CopyOnWriteArrayList<Entity>();
        while (rs.next()) {
            MusicType musicType = new MusicType();
            musicType.setId(rs.getInt("id"));
            musicType.setName(rs.getString("text"));
            result.add(musicType);
        }
        return result;
    }
}
