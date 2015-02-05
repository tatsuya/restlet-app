package com.tatsuyaoiw.restlet.resource;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public interface TrickResource {

	@Get
	Representation getTrick();

}
