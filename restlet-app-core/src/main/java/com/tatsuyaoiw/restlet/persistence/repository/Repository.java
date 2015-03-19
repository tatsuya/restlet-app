package com.tatsuyaoiw.restlet.persistence.repository;

import com.tatsuyaoiw.restlet.persistence.entity.Entity;
import com.tatsuyaoiw.restlet.persistence.strategy.RepositoryStrategy;
import org.restlet.Context;

import java.util.List;

public abstract class Repository<T extends Entity> {

	private RepositoryStrategy<T> strategy;

	public void init(RepositoryStrategy<T> strategy) {
		this.strategy = strategy;
	}

	/**
	 * Adds a new entity to the database.
	 *
	 * @param entity The entity to create.
	 * @return The newly added entity, especially with its technical identifier, in case it is computed.
	 */
	public T create(T entity) {
		Context.getCurrentLogger().finer("Method create() of " + getClass().getSimpleName() + " called");
		return strategy.create(entity);
	}

	/**
	 * Finds an existing entity
	 *
	 * @param id The identifier of the entity to find.
	 * @return The found entity.
	 */
	public T retrieve(String id) {
		Context.getCurrentLogger().finer("Method retrieve() of " + getClass().getSimpleName() + " called");
		return strategy.retrieve(id);
	}

	/**
	 * Update an existing entity.
	 *
	 * @param entity The new state of the entity
	 * @return The updated entity.
	 */
	public T update(T entity) {
		Context.getCurrentLogger().finer("Method update() of " + getClass().getSimpleName() + " called");
		return strategy.update(entity);
	}

	/**
	 * Remove an entity from the database
	 *
	 * @param id The identifier of the entity to delete.
	 * @return True if the entity has been removed
	 */
	public Boolean delete(String id) {
		Context.getCurrentLogger().finer("Method delete() of " + getClass().getSimpleName() + " called");
		return strategy.delete(id);
	}

	/**
	 * Returns a list of entities stored in the database.
	 *
	 * @return The list of entities stored in the database.
	 */
	public List<T> list() {
		Context.getCurrentLogger().finer("Method list() of " + getClass().getSimpleName() + " called");
		return strategy.list();
	}

}
