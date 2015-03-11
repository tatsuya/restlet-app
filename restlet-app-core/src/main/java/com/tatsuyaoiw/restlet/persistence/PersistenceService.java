package com.tatsuyaoiw.restlet.persistence;

import com.tatsuyaoiw.restlet.persistence.entity.Movie;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import com.tatsuyaoiw.restlet.persistence.memory.MemoryMoviePersistence;
import com.tatsuyaoiw.restlet.persistence.memory.MemoryPersistenceService;
import com.tatsuyaoiw.restlet.persistence.memory.MemoryTrickPersistence;
import com.tatsuyaoiw.restlet.persistence.mongo.MongoMoviePersistence;
import com.tatsuyaoiw.restlet.persistence.mongo.MongoPersistenceService;
import com.tatsuyaoiw.restlet.persistence.mongo.MongoTrickPersistence;
import org.restlet.Context;

public abstract class PersistenceService {

	private static Storage STORAGE;

	/**
	 * Returns a persistence layer for managing Tricks.
	 *
	 * @return A persistence layer for managing Tricks.
	 */
	public static void initialize(Storage storage) {
		STORAGE = storage;
		if (STORAGE.equals(Storage.MONGO)) {
			MongoPersistenceService.initialize();
		} else if (STORAGE.equals(Storage.MEMORY)) {
			MemoryPersistenceService.initialize();
		} else {
			throw new IllegalArgumentException("Unsupported storage");
		}
	}

	private static void checkStorage() {
		if (STORAGE == null) {
			throw new IllegalStateException("Storage must be initialized");
		}
	}

	public static Persistence<Trick> getTrickPersistence() {
		Context.getCurrentLogger().finer("Get the persistence layer for Tricks.");

		checkStorage();

		if (STORAGE.equals(Storage.MONGO)) {
			return MongoTrickPersistence.getInstance();
		} else if (STORAGE.equals(Storage.MEMORY)) {
			return MemoryTrickPersistence.getINSTANCE();
		} else {
			throw new IllegalStateException("Unsupported storage");
		}
	}

	public static Persistence<Movie> getMoviePersistence() {
		Context.getCurrentLogger().finer("Get the persistence layer for Tricks.");

		checkStorage();

		if (STORAGE.equals(Storage.MONGO)) {
			return MongoMoviePersistence.getInstance();
		} else if (STORAGE.equals(Storage.MEMORY)) {
			return MemoryMoviePersistence.getInstance();
		} else {
			throw new IllegalStateException("Unsupported storage");
		}
	}

}
