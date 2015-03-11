package com.tatsuyaoiw.restlet.persistence.memory;

import com.tatsuyaoiw.restlet.persistence.Persistence;
import com.tatsuyaoiw.restlet.persistence.entity.Movie;
import org.restlet.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemoryMoviePersistence extends MemoryPersistenceService implements Persistence<Movie> {

	private static final MemoryMoviePersistence INSTANCE = new MemoryMoviePersistence();

	private MemoryMoviePersistence() {}

	public static MemoryMoviePersistence getInstance() {
		return INSTANCE;
	}

	@Override
	public Movie add(Movie toAdd) {
		Context.getCurrentLogger().finer("Method add() of " + getClass().getSimpleName() + " called");

		String id = UUID.randomUUID().toString();
		toAdd.setId(id);
		getMovies().put(id, toAdd);

		Context.getCurrentLogger().finer("Method add() of " + getClass().getSimpleName() + " finished");

		return toAdd;
	}

	@Override
	public Boolean remove(String id) {
		Context.getCurrentLogger().finer("Method remove() of " + getClass().getSimpleName() + " called");

		Movie movie = getMovies().remove(id);

		Context.getCurrentLogger().finer("Method remove() of " + getClass().getSimpleName() + " finished");

		return movie != null;
	}

	@Override
	public List<Movie> findAll() {
		Context.getCurrentLogger().finer("Method findAll() of " + getClass().getSimpleName() + " called");

		List<Movie> movies = new ArrayList<Movie>(getMovies().values());

		Context.getCurrentLogger().finer("Method findAll() of " + getClass().getSimpleName() + " finished");

		return movies;
	}

	@Override
	public Movie findById(String id) {
		Context.getCurrentLogger().finer("Method findById() of " + getClass().getSimpleName() + " called");

		Movie movie = getMovies().get(id);

		Context.getCurrentLogger().finer("Method findById() of " + getClass().getSimpleName() + " finished");

		return movie;
	}

	@Override
	public Movie update(String id, Movie toUpdate) {
		Context.getCurrentLogger().finer("Method update() of " + getClass().getSimpleName() + " called");
		
		toUpdate.setId(id);
		getMovies().put(id, toUpdate);

		Context.getCurrentLogger().finer("Method update() of " + getClass().getSimpleName() + " finished");

		return toUpdate;
	}
}
