package com.tatsuyaoiw.restlet.resource;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public interface TrickListResource {

	@Get("json")
	public Representation getTricks();

}
