package com.tatsuyaoiw.restlet.resource;

import com.tatsuyaoiw.restlet.representation.MovieRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface MovieResource {

	@Get("json")
	MovieRepresentation getMovie();

	@Delete("json")
	void remove();

	@Put("json")
	MovieRepresentation update(MovieRepresentation movie);

}
