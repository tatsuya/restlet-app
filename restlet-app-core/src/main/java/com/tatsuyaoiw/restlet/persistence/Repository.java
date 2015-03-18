package com.tatsuyaoiw.restlet.persistence;

import com.tatsuyaoiw.restlet.persistence.entity.Entity;

import java.util.List;

public interface Repository<T extends Entity> {
	/**
	 * Adds a new entity to the database.
	 *
	 * @param entity The entity to add.
	 * @return The newly added entity, especially with its technical identifier, in case it is computed.
	 */
	T add(T entity);

	/**
	 * Remove an entity from the database
	 *
	 * @param id The identifier of the entity to remove.
	 * @return True if the entity has been removed
	 */
	Boolean remove(String id);

	/**
	 * Returns a list of entities stored in the database.
	 *
	 * @return The list of entities stored in the database.
	 */
	List<T> findAll();

	/**
	 * Finds an existing entity
	 *
	 * @param id The identifier of the entity to find.
	 * @return The found entity.
	 */
	T findById(String id);

	/**
	 * Update an existing entity.
	 *
	 * @param id The identifier of the entity to update.
	 * @param entity The new state of the entity
	 * @return The updated entity.
	 */
	T update(String id, T entity);
}
