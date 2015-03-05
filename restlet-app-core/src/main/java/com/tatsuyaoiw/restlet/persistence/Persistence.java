package com.tatsuyaoiw.restlet.persistence;

import java.util.List;

public interface Persistence<T> {
	/**
	 * Adds a new entity to the database.
	 *
	 * @param toAdd The entity to add.
	 * @return The newly added entity, especially with its technical identifier, in case it is computed.
	 */
	T add(T toAdd);

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
}
