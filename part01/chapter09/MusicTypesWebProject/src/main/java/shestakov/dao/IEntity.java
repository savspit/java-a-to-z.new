package shestakov.dao;

import shestakov.models.Entity;

import java.util.List;

/**
 * The interface Entity.
 *
 * @param <E> the type parameter
 */
public interface IEntity<E extends Entity> {

    /**
     * Create.
     *
     * @param e the e
     */
    void create(E e);

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    List<Entity> getById(int id);

    /**
     * Update.
     *
     * @param e the e
     */
    void update(E e);

    /**
     * Delete.
     *
     * @param e the e
     */
    void delete(E e);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<Entity> getAll();
}
