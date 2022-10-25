package cz.cvut.fit.tjv.nebesluk.dao.repository;

import java.util.Collection;
import java.util.Optional;

/**
 * Interface for unifying basic CRUD operations on entity repositories
 * @param <T> Type of the entity e.g. Client,Tag,...
 * @param <ID> ID of the entity (primary key) e.g. Long, String
 */
public interface CrudRepository<T,ID> {

    /**
     * Saves the entity. Returned entity should replace the old one, as the new entity could have
     * replaced the old one completely.
     * @param entity Entity to be saved, must not be  null
     * @return the saved entity, will never be null
     * @throws IllegalArgumentException if the entity is null
     */
    T save(T entity);

    /**
     * Finds entity by its ID.
     * @param id ID of the requested entity; must not be null
     * @return The entity, or empty Optional if the requested ID doesn't exists.
     * @throws IllegalArgumentException if ID is null
     */
    Optional<T> findByID(ID id);

    /**
     * Checks, if the requested ID exists.
     * @param id Requested ID; must not be null
     * @return True if ID exists; false otherwise
     * @throws IllegalArgumentException if ID is null
     */
    boolean existsByID(ID id);

    /**
     * Retrieves all entities.
     * @return Collection of all entities.
     */
    Collection<T> findAll();

    /**
     * Deletes entity with requested ID.
     * @param id Requested ID; must not be null.
     * @throws IllegalArgumentException if ID is null
     */
    void deleteByID(ID id);
}
