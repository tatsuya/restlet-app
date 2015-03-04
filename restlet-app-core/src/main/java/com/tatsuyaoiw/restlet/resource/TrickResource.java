package com.tatsuyaoiw.restlet.resource;

import com.tatsuyaoiw.restlet.representation.TrickRepresentation;
import org.restlet.resource.Get;

public interface TrickResource {

	@Get("json")
	TrickRepresentation getTrick();

}
