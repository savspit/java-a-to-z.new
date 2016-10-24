package shestakov.dao;

import shestakov.models.Entity;
import shestakov.models.MusicType;

import java.util.List;

/**
 * The interface Music type.
 */
public interface IMusicType extends IEntity<MusicType> {
    List<Entity> getByUserLogin(String login);
}
