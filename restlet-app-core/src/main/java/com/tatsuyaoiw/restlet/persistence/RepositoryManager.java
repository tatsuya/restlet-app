package com.tatsuyaoiw.restlet.persistence;

import com.tatsuyaoiw.restlet.persistence.entity.Movie;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import com.tatsuyaoiw.restlet.persistence.repository.MovieRepository;
import com.tatsuyaoiw.restlet.persistence.repository.Repository;
import com.tatsuyaoiw.restlet.persistence.repository.TrickRepository;
import com.tatsuyaoiw.restlet.persistence.strategy.InMemoryStrategy;
import com.tatsuyaoiw.restlet.persistence.strategy.MongoStrategy;
import org.restlet.Context;

public abstract class RepositoryManager {

	private static Storage STORAGE;

	/**
	 * Returns a persistence layer for managing Tricks.
	 *
	 * @return A persistence layer for managing Tricks.
	 */
	public static void initialize(Storage storage) {
		STORAGE = storage;
		if (STORAGE.equals(Storage.MONGO)) {
			TrickRepository.init(new MongoStrategy<Trick>(Trick.class, "mydb", "tricks"));
			MovieRepository.init(new MongoStrategy<Movie>(Movie.class, "mydb", "movies"));
		} else if (STORAGE.equals(Storage.MEMORY)) {
			TrickRepository.init(new InMemoryStrategy<Trick>());
			MovieRepository.init(new InMemoryStrategy<Movie>());
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
		return TrickRepository.getInstance();
	}

	public static Repository<Movie> getMovieRepository() {
		Context.getCurrentLogger().finer("Get the persistence layer for Movies.");

		checkStorage();
		return MovieRepository.getInstance();
	}

}
