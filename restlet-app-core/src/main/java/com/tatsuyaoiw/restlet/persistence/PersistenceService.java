package com.tatsuyaoiw.restlet.persistence;

import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import com.tatsuyaoiw.restlet.persistence.memory.MemoryPersistenceService;
import com.tatsuyaoiw.restlet.persistence.memory.MemoryTrickPersistence;
import com.tatsuyaoiw.restlet.persistence.mongo.MongoPersistenceService;
import com.tatsuyaoiw.restlet.persistence.mongo.MongoTrickPersistence;
import org.restlet.Context;

public abstract class PersistenceService {

	/**
	 * Returns a persistence layer for managing Tricks.
	 *
	 * @return A persistence layer for managing Tricks.
	 */
	public static void initialize(Storage storage) {
		if (storage.equals(Storage.MONGO)) {
			MongoPersistenceService.initialize();
		} else if (storage.equals(Storage.MEMORY)) {
			MemoryPersistenceService.initialize();
		} else {
			throw new IllegalArgumentException("Unsupported storage");
		}
	}

	public static Persistence<Trick> getTrickPersistence(Storage storage) {
		Context.getCurrentLogger().finer("Get the persistence layer for Tricks.");
		if (storage.equals(Storage.MONGO)) {
			return MongoTrickPersistence.getTrickPersistence();
		} else if (storage.equals(Storage.MEMORY)) {
			return MemoryTrickPersistence.getTrickPersistence();
		} else {
			throw new IllegalArgumentException("Unsupported storage");
		}
	}

}
