package shestakov.dao.impl;

import shestakov.dao.IMusicType;
import shestakov.templates.MusicTypeTemplate;
import shestakov.templates.Template;
import shestakov.models.Entity;
import shestakov.models.MusicType;
import shestakov.db.DataSource;

import java.util.List;

/**
 * The type Music type.
 */
public class MusicTypeImpl implements IMusicType {
    private static final String SQL_CREATE = "INSERT INTO musicTypes(name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE musicTypes SET name=?, WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM musicTypes WHERE id=?";
    private static final String SQL_GET_BY_ID = "SELECT m.id, m.name FROM musicTypes AS m WHERE m.id = ?";
    private static final String SQL_GET_ALL = "SELECT m.id, m.name FROM musicTypes AS m";
    private static final DataSource instance = DataSource.getInstance();

    @Override
    public void create(MusicType musicType) {
        Template musicTypeTemplate = new MusicTypeTemplate();
        musicTypeTemplate.execute(instance, SQL_CREATE, musicType.getName());
    }

    @Override
    public List<Entity> getById(int id) {
        Template musicTypeTemplate = new MusicTypeTemplate();
        return musicTypeTemplate.executeAndReturn(instance, SQL_GET_BY_ID, id);
    }

    @Override
    public void update(MusicType musicType) {
        Template musicTypeTemplate = new MusicTypeTemplate();
        musicTypeTemplate.execute(instance, SQL_UPDATE, musicType.getName(), musicType.getId());
    }

    @Override
    public void delete(MusicType musicType) {
        Template musicTypeTemplate = new MusicTypeTemplate();
        musicTypeTemplate.execute(instance, SQL_DELETE, musicType.getId());
    }

    @Override
    public List<Entity> getAll() {
        Template musicTypeTemplate = new MusicTypeTemplate();
        return musicTypeTemplate.executeAndReturn(instance, SQL_GET_ALL);
    }
}
