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
	 * @param entity The entity to add.
	 * @return The newly added entity, especially with its technical identifier, in case it is computed.
	 */
	public T add(T entity) {
		Context.getCurrentLogger().finer("Method add() of " + getClass().getSimpleName() + " called");
		return strategy.add(entity);
	}

	/**
	 * Remove an entity from the database
	 *
	 * @param id The identifier of the entity to remove.
	 * @return True if the entity has been removed
	 */
	public Boolean remove(String id) {
		Context.getCurrentLogger().finer("Method remove() of " + getClass().getSimpleName() + " called");
		return strategy.remove(id);
	}

	/**
	 * Returns a list of entities stored in the database.
	 *
	 * @return The list of entities stored in the database.
	 */
	public List<T> findAll() {
		Context.getCurrentLogger().finer("Method findAll() of " + getClass().getSimpleName() + " called");
		return strategy.list();
	}

	/**
	 * Finds an existing entity
	 *
	 * @param id The identifier of the entity to find.
	 * @return The found entity.
	 */
	public T findById(String id) {
		Context.getCurrentLogger().finer("Method findById() of " + getClass().getSimpleName() + " called");
		return strategy.get(id);
	}

	/**
	 * Update an existing entity.
	 *
	 * @param id The identifier of the entity to update.
	 * @param entity The new state of the entity
	 * @return The updated entity.
	 */
	public T update(String id, T entity) {
		Context.getCurrentLogger().finer("Method update() of " + getClass().getSimpleName() + " called");
		entity.setId(id);
		return strategy.update(entity);
	}

}
