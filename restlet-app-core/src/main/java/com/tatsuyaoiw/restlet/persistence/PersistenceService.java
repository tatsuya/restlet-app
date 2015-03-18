package com.tatsuyaoiw.restlet.persistence;

import com.tatsuyaoiw.restlet.persistence.entity.Movie;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import com.tatsuyaoiw.restlet.persistence.memory.MemoryMovieRepository;
import com.tatsuyaoiw.restlet.persistence.memory.MemoryTrickRepository;
import com.tatsuyaoiw.restlet.persistence.mongo.MongoMovieRepository;
import com.tatsuyaoiw.restlet.persistence.mongo.MongoTrickRepository;
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
			MongoTrickRepository.initialize();
			MongoMovieRepository.initialize();
		} else if (STORAGE.equals(Storage.MEMORY)) {
			MemoryTrickRepository.initialize();
			MemoryMovieRepository.initialize();
		} else {
			throw new IllegalArgumentException("Unsupported storage");
		}
	}

	private static void checkStorage() {
		if (STORAGE == null) {
			throw new IllegalStateException("Storage must be initialized");
		}
	}

	public static Repository<Trick> getTrickRepository() {
		Context.getCurrentLogger().finer("Get the persistence layer for Tricks.");

		checkStorage();

		if (STORAGE.equals(Storage.MONGO)) {
			return MongoTrickRepository.getInstance();
		} else if (STORAGE.equals(Storage.MEMORY)) {
			return MemoryTrickRepository.getINSTANCE();
		} else {
			throw new IllegalStateException("Unsupported storage");
		}
	}

	public static Repository<Movie> getMovieRepository() {
		Context.getCurrentLogger().finer("Get the persistence layer for Tricks.");

		checkStorage();

		if (STORAGE.equals(Storage.MONGO)) {
			return MongoMovieRepository.getInstance();
		} else if (STORAGE.equals(Storage.MEMORY)) {
			return MemoryMovieRepository.getInstance();
		} else {
			throw new IllegalStateException("Unsupported storage");
		}
	}

}
