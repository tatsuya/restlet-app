package com.tatsuyaoiw.restlet.persistence.repository;

import com.tatsuyaoiw.restlet.persistence.entity.Movie;
import com.tatsuyaoiw.restlet.persistence.strategy.RepositoryStrategy;
import org.restlet.Context;

import java.util.List;

public class MovieRepository implements Repository<Movie> {

	private static RepositoryStrategy<Movie> strategy;

	private static final MovieRepository INSTANCE = new MovieRepository();

	private MovieRepository() {}

	public static MovieRepository getInstance() {
		return INSTANCE;
	}

	public static void init(RepositoryStrategy<Movie> strategy) {
		MovieRepository.strategy = strategy;
	}

	@Override
	public Movie add(Movie toAdd) {
		Context.getCurrentLogger().finer("Method add() of " + getClass().getSimpleName() + " called");
		return strategy.add(toAdd);
	}

	@Override
	public Boolean remove(String id) {
		Context.getCurrentLogger().finer("Method remove() of " + getClass().getSimpleName() + " called");
		return strategy.remove(id);
	}

	@Override
	public List<Movie> findAll() {
		Context.getCurrentLogger().finer("Method findAll() of " + getClass().getSimpleName() + " called");
		return strategy.list();
	}

	@Override
	public Movie findById(String id) {
		Context.getCurrentLogger().finer("Method findById() of " + getClass().getSimpleName() + " called");
		return strategy.get(id);
	}

	@Override
	public Movie update(String id, Movie toUpdate) {
		Context.getCurrentLogger().finer("Method update() of " + getClass().getSimpleName() + " called");
		toUpdate.setId(id);
		return strategy.update(toUpdate);
	}

}
