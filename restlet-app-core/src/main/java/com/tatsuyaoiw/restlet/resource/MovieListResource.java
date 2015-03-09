package com.tatsuyaoiw.restlet.resource;

import com.tatsuyaoiw.restlet.representation.MovieRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public interface MovieListResource {

	@Get("json")
	public List<MovieRepresentation> getMovies();

	@Post("json")
	public MovieRepresentation add(MovieRepresentation movie);

}
