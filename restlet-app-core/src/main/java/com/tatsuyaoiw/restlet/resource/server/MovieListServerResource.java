package com.tatsuyaoiw.restlet.resource.server;

import com.tatsuyaoiw.restlet.core.util.ResourceUtils;
import com.tatsuyaoiw.restlet.persistence.Persistence;
import com.tatsuyaoiw.restlet.persistence.PersistenceService;
import com.tatsuyaoiw.restlet.persistence.entity.Movie;
import com.tatsuyaoiw.restlet.representation.MovieRepresentation;
import com.tatsuyaoiw.restlet.resource.MovieListResource;
import com.tatsuyaoiw.restlet.utils.MovieUtils;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
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
		getLogger().finer("Retrieve the list of movies");

		List<Movie> movies = moviePersistence.findAll();

		List<MovieRepresentation> movieReprs = new ArrayList<MovieRepresentation>();
		for (Movie movie : movies) {
			movieReprs.add(MovieUtils.toMovieRepresentation(movie));
		}

		getLogger().finer("List of movies successfully retrieved.");

		return movieReprs;
	}

	@Override
	public MovieRepresentation add(MovieRepresentation movieReprIn) {
		getLogger().finer("Add a new movie");

		Movie toAdd = MovieUtils.toMovie(movieReprIn);

		Movie added = moviePersistence.add(toAdd);

		MovieRepresentation movieReprOut = MovieUtils.toMovieRepresentation(added);

		getResponse().setLocationRef(ResourceUtils.getMovieUrl(added.getId()));
		getResponse().setStatus(Status.SUCCESS_CREATED);

		getLogger().finer("Movie successfully added");

		return movieReprOut;
	}

}
