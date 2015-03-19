package com.tatsuyaoiw.restlet.persistence.repository;

import com.tatsuyaoiw.restlet.persistence.entity.Movie;

public class MovieRepository extends Repository<Movie> {

	private static final MovieRepository INSTANCE = new MovieRepository();

	private MovieRepository() {}

	public static MovieRepository getInstance() {
		return INSTANCE;
	}

}
