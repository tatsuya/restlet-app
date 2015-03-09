package com.tatsuyaoiw.restlet.resource;

import com.tatsuyaoiw.restlet.representation.TrickRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface TrickResource {

	@Get("json")
	TrickRepresentation getTrick();

	@Delete("json")
	void remove();

	@Put("json")
	TrickRepresentation update(TrickRepresentation trick);

}
