package com.tatsuyaoiw.restlet.resource.server;

import com.tatsuyaoiw.restlet.representation.TrickRepresentation;
import com.tatsuyaoiw.restlet.resource.TrickListResource;
import com.tatsuyaoiw.restlet.utils.TrickUtils;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.List;

public class TrickListServerResource extends ServerResource implements TrickListResource {

	@Override
	public Representation getTricks() {
		getLogger().finer("Retrieve the list of tricks");

		List<TrickRepresentation> tricks = new ArrayList<TrickRepresentation>();
		tricks.add(TrickUtils.toTrickRepresentation());

		getLogger().finer("List of tricks successfully retrieved.");

		return new JacksonRepresentation<List<TrickRepresentation>>(tricks);
	}

}
