package com.tatsuyaoiw.restlet.resource.server;

import com.tatsuyaoiw.restlet.persistence.Persistence;
import com.tatsuyaoiw.restlet.persistence.PersistenceService;
import com.tatsuyaoiw.restlet.persistence.entity.Movie;
import com.tatsuyaoiw.restlet.representation.MovieRepresentation;
import com.tatsuyaoiw.restlet.resource.MovieListResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.List;

public class MovieListServerResource extends ServerResource implements MovieListResource {

	private Persistence<Movie> moviePersistence;

	@Override
	protected void doInit() throws ResourceException {
		getLogger().finer("Initialization of MovieListServerResource.");

		moviePersistence = PersistenceService.getMoviePersistence();

		getLogger().finer("Initialization of TrickListServerResource ended.");
	}

	@Override
	public List<MovieRepresentation> getMovies() {

		return null;
	}

	@Override
	public MovieRepresentation add(MovieRepresentation movie) {
		return null;
	}

}
