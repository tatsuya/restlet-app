package com.tatsuyaoiw.restlet.persistence.mongo;

import com.tatsuyaoiw.restlet.persistence.Persistence;
import com.tatsuyaoiw.restlet.persistence.entity.Movie;

import java.util.List;

public class MongoMoviePersistence extends MongoPersistenceService implements Persistence<Movie> {
	// Singleton pattern
	private static MongoMoviePersistence moviePersistence = new MongoMoviePersistence();

	public static synchronized MongoMoviePersistence getMoviePersistence() {
		return moviePersistence;
	}

	private MongoMoviePersistence() {}

	@Override
	public Movie add(Movie toAdd) {
		return null;
	}

	@Override
	public Boolean remove(String id) {
		return null;
	}

	@Override
	public List<Movie> findAll() {
		return null;
	}

	@Override
	public Movie findById(String id) {
		return null;
	}

	@Override
	public Movie update(String id, Movie toUpdate) {
		return null;
	}
}
