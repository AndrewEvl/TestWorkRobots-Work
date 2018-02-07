package dao.common;

import entity.BaseEntity;

import java.util.List;

/**
 * This interface to implement the basic function dao layer.
 * @param <T> - the will com an entity that calls one of the methods.
 */
public interface BaseDao<T extends BaseEntity>  {

    /**
     * The method find by id in database.
     * @param id - id entity.
     * @return - returns the found entity, if there is none, then returns null.
     */
    T findById(Long id);

    /**
     * Save the receives entity on database.
     * @param entity - entity.
     */
    void save(T entity);

    /**
     * Delete the receives entity on database.
     * @param entity - entity.
     */
    void delete(T entity);

    /**
     * Update new information receives entity and save him.
     * @param entity - new entity.
     */
    void update(T entity);

    /**
     * Receive all the entity of thi obtained,
     * which are located in the database
     * @return - list all entity.
     */
    List<T> findAll();
}
