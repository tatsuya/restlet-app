package com.tatsuyaoiw.restlet.resource.server;

import com.tatsuyaoiw.restlet.persistence.RepositoryManager;
import com.tatsuyaoiw.restlet.persistence.repository.Repository;
import com.tatsuyaoiw.restlet.persistence.entity.Movie;
import com.tatsuyaoiw.restlet.representation.MovieRepresentation;
import com.tatsuyaoiw.restlet.resource.MovieResource;
import com.tatsuyaoiw.restlet.utils.MovieUtils;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class MovieServerResource extends ServerResource implements MovieResource {

	private Repository<Movie> repository;

	private Movie movie;

	private String id;

	@Override
	protected void doInit() throws ResourceException {
		id = getAttribute("id");

		getLogger().finer("Initialization of " + getClass().getSimpleName() + " with movie id: " + id);

		repository = RepositoryManager.getMovieRepository();

		movie = repository.findById(id);

		setExisting(movie != null);

		getLogger().finer("Initialization of " + getClass().getSimpleName() + " ended with movie id: " + id);
	}

	@Override
	public MovieRepresentation getMovie() {
		getLogger().finer("Retrieve a movie");

		MovieRepresentation movieRepr = MovieUtils.toMovieRepresentation(movie);

		getLogger().finer("Movie successfully retrieved");

		return movieRepr;
	}

	@Override
	public void remove() {
		getLogger().finer("Removal of movie");

		Boolean isRemoved = repository.remove(id);

		if (!isRemoved) {
			getLogger().finer("Movie id does not exist");
			throw new IllegalArgumentException("Movie with the following identifier does not exist: " + id);
		}

		getLogger().finer("Movie successfully removed");
	}

	@Override
	public MovieRepresentation update(MovieRepresentation movieReprIn) {
		getLogger().finer("Update a movie");

		Movie movieIn = MovieUtils.toMovie(movieReprIn);

		if (!isExisting()) {
			throw new IllegalArgumentException("Movie with the following id does not exist: " + id);
		}

		Movie movieOut = repository.update(id, movieIn);

		getLogger().finer("Movie successfully updated");

		return MovieUtils.toMovieRepresentation(movieOut);
	}

}
