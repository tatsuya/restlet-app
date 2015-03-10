package com.tatsuyaoiw.restlet.utils;

import com.tatsuyaoiw.restlet.persistence.entity.Movie;
import com.tatsuyaoiw.restlet.representation.MovieRepresentation;

public class MovieUtils {

	public static Movie toMovie(MovieRepresentation movieRepr) {
		if (movieRepr == null) {
			return null;
		}
		Movie movie = new Movie();
		movie.setTitle(movieRepr.getTitle());
		movie.setUrl(movieRepr.getUrl());
		return movie;
	}

	public static MovieRepresentation toMovieRepresentation(Movie movie) {
		if (movie == null) {
			return null;
		}
		MovieRepresentation movieRepr = new MovieRepresentation();
		movieRepr.setId(movie.getId());
		movieRepr.setTitle(movie.getTitle());
		movieRepr.setUrl(movie.getUrl());
		return movieRepr;
	}

}
