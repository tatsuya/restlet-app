package com.tatsuyaoiw.restlet.persistence;

import org.restlet.Context;

import java.util.List;

public abstract class PersistenceService<T> {

	/**
	 * Returns a persistence layer for managing Tricks.
	 *
	 * @return A persistence layer for managing Tricks.
	 */
	public static TrickPersistence getTrickPersistence() {
		Context.getCurrentLogger().finer("Get the persistence layer for Tricks.");
		return TrickPersistence.getTrickPersistence();
	}

	/**
	 * Adds a new entity to the database.
	 *
	 * @param toAdd The entity to add.
	 * @return The newly added entity, especially with its technical identifier, in case it is computed.
	 */
	public abstract T add(T toAdd);

	/**
	 * Returns a list of entities stored in the database.
	 *
	 * @return The list of entities stored in the database.
	 */
	public abstract List<T> findAll();

	/**
	 * Finds an existing entity
	 *
	 * @param id The identifier of the entity to find.
	 * @return The found entity.
	 */
	public abstract T findById(String id);
}
