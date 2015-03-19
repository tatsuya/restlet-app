package com.tatsuyaoiw.restlet.persistence;

import com.tatsuyaoiw.restlet.persistence.entity.Movie;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import com.tatsuyaoiw.restlet.persistence.repository.MovieRepository;
import com.tatsuyaoiw.restlet.persistence.repository.Repository;
import com.tatsuyaoiw.restlet.persistence.repository.TrickRepository;
import com.tatsuyaoiw.restlet.persistence.strategy.InMemoryStrategy;
import com.tatsuyaoiw.restlet.persistence.strategy.MongoStrategy;
import com.tatsuyaoiw.restlet.persistence.strategy.RepositoryStrategy;
import org.restlet.Context;

public abstract class RepositoryManager {

	private static StrategyType strategy;

	/**
	 * Initializes persistence layers.
	 */
	public static void initRepositories(StrategyType strategy) {
		RepositoryManager.strategy = strategy;

		RepositoryStrategy<Trick> trickStrategy;
		RepositoryStrategy<Movie> movieStrategy;

		switch (RepositoryManager.strategy) {
			case MONGO:
				trickStrategy = new MongoStrategy<Trick>(Trick.class, "mydb", "tricks");
				movieStrategy = new MongoStrategy<Movie>(Movie.class, "mydb", "movies");
				break;
			case MEMORY:
				trickStrategy = new InMemoryStrategy<Trick>();
				movieStrategy = new InMemoryStrategy<Movie>();
				break;
			default:
				throw new IllegalArgumentException("Unsupported repository strategy: " + RepositoryManager.strategy);
		}

		TrickRepository.getInstance().init(trickStrategy);
		MovieRepository.getInstance().init(movieStrategy);
	}

	/**
	 * Returns a persistence layer for managing Tricks.
	 *
	 * @return A persistence layer for managing Tricks.
	 */
	public static Repository<Trick> getTrickRepository() {
		checkRepositoriesInitialized();
		Context.getCurrentLogger().finer("Get the persistence layer for Tricks.");
		return TrickRepository.getInstance();
	}

	/**
	 * Returns a persistence layer for managing Movies.
	 *
	 * @return A persistence layer for managing Movies.
	 */
	public static Repository<Movie> getMovieRepository() {
		checkRepositoriesInitialized();
		Context.getCurrentLogger().finer("Get the persistence layer for Movies.");
		return MovieRepository.getInstance();
	}

	private static void checkRepositoriesInitialized() {
		if (strategy == null) {
			throw new IllegalStateException("Repository must be initialized");
		}
	}

}
