package com.tatsuyaoiw.restlet.resource.server;

import com.tatsuyaoiw.restlet.representation.TrickRepresentation;
import com.tatsuyaoiw.restlet.resource.TrickResource;
import com.tatsuyaoiw.restlet.utils.TrickUtils;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class TrickServerResource extends ServerResource implements TrickResource {

	private TrickRepresentation trick;

	@Override
	protected void doInit() throws ResourceException {
		String id = getAttribute("id");

		trick = TrickUtils.toTrickRepresentation();

		if (!trick.getId().equals(id)) {
			throw new IllegalArgumentException("Trick id does not exist: " + id);
		}

		getLogger().finer("Initialization of TrickServerResource with trick id: " + id);
	}

	@Override
	public Representation getTrick() {
		getLogger().finer("Retrieve a trick");

		Representation json = new JacksonRepresentation<TrickRepresentation>(trick);

		getLogger().finer("Trick successfully retrieved");

		return json;
	}

}
