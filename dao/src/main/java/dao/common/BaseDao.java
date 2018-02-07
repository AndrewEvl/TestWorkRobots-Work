package dao.common;

import entity.BaseEntity;

import java.util.List;

/**
 * This interface is for implement of the basic function dao layer.
 * @param <T> - will be replaced into entity that will call one of the methods.
 */
public interface BaseDao<T extends BaseEntity>  {

    /**
     * The method will find entity by id in database.
     * @param id - id entity.
     * @return - return founded entity, if don't find it will return null.
     */
    T findById(Long id);

    /**
     * Save the received entity in database.
     * @param entity - entity.
     */
    void save(T entity);

    /**
     * Delete the received entity in database.
     * @param entity - entity.
     */
    void delete(T entity);

    /**
     * Update new information of received entity and save it.
     * @param entity - new entity.
     */
    void update(T entity);

    /**
     * Receive all entities which are located in the database
     * @return - list of all entity.
     */
    List<T> findAll();
}
