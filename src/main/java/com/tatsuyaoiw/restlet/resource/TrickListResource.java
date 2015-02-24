package com.tatsuyaoiw.restlet.resource;

import com.tatsuyaoiw.restlet.representation.TrickRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public interface TrickListResource {

	@Get("json")
	public Representation getTricks();

	@Post("json")
	public TrickRepresentation add(TrickRepresentation trick);

}
