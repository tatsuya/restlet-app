package com.tatsuyaoiw.restlet.resource;

import com.tatsuyaoiw.restlet.representation.TrickRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public interface TrickListResource {

	@Get("json")
	public List<TrickRepresentation> getTricks();

	@Post("json")
	public TrickRepresentation add(TrickRepresentation trick);

}
