package com.tatsuyaoiw.restlet.persistence;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.restlet.Context;

import java.net.UnknownHostException;
import java.util.List;

public abstract class PersistenceService<T> {

	private static MongoClient client;

	/**
	 * Returns a persistence layer for managing Tricks.
	 *
	 * @return A persistence layer for managing Tricks.
	 */
	public static TrickPersistence getTrickPersistence() {
		Context.getCurrentLogger().finer("Get the persistence layer for Tricks.");
		return TrickPersistence.getTrickPersistence();
	}

	public static void initialize() {
		try {
			client = new MongoClient();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}
	protected DB getDB() {
		if (client == null) {
			throw new RuntimeException("MongoClient must be initialized");
		}
		// At this point, the db object will be a connection to a MongoDB server
		// for the specified database. With it, you can do further operations.
		return client.getDB("mydb");
	}

	protected DBCollection getCollection() {
		Context.getCurrentLogger().finer("Get a collection to use");
		DBCollection collection = getDB().getCollection("testCollection");
		Context.getCurrentLogger().finer("Got a collection to use");
		return collection;
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
