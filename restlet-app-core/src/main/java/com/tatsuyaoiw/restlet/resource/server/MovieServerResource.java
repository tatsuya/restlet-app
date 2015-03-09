package com.tatsuyaoiw.restlet.resource.server;

import com.tatsuyaoiw.restlet.representation.MovieRepresentation;
import com.tatsuyaoiw.restlet.resource.MovieResource;
import org.restlet.resource.ServerResource;

public class MovieServerResource extends ServerResource implements MovieResource {

	@Override
	public MovieRepresentation getMovie() {
		return null;
	}

	@Override
	public void remove() {

	}

	@Override
	public MovieRepresentation update(MovieRepresentation movie) {
		return null;
	}

}
