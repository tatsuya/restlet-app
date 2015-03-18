package com.tatsuyaoiw.restlet.resource.server;

import com.tatsuyaoiw.restlet.core.util.ResourceUtils;
import com.tatsuyaoiw.restlet.persistence.RepositoryManager;
import com.tatsuyaoiw.restlet.persistence.repository.Repository;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import com.tatsuyaoiw.restlet.representation.TrickRepresentation;
import com.tatsuyaoiw.restlet.resource.TrickListResource;
import com.tatsuyaoiw.restlet.utils.TrickUtils;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.List;

public class TrickListServerResource extends ServerResource implements TrickListResource {

	private Repository<Trick> repository;

	/**
	 * Method called at the creation of the Resources (ie : each time the resource is called).
	 */
	@Override
	protected void doInit() throws ResourceException {
		getLogger().finer("Initialization of TrickListServerResource.");

		repository = RepositoryManager.getTrickRepository();

		getLogger().finer("Initialization of TrickListServerResource ended.");
	}

	@Override
	public List<TrickRepresentation> getTricks() {
		getLogger().finer("Retrieve the list of tricks");

		List<Trick> tricks = repository.findAll();

		List<TrickRepresentation> trickReprs = new ArrayList<TrickRepresentation>();
		for (Trick trick : tricks) {
			trickReprs.add(TrickUtils.toTrickRepresentation(trick));
		}

		getLogger().finer("List of tricks successfully retrieved.");

		return trickReprs;
	}

	@Override
	public TrickRepresentation add(TrickRepresentation trickRepr) {
		getLogger().finer("Add a new trick");

		// Convert TrickRepresentation to Trick
		Trick trickIn = TrickUtils.toTrick(trickRepr);

		// Add new trick in DB and retrieve created trick
		Trick trickOut = repository.add(trickIn);

		TrickRepresentation result = TrickUtils.toTrickRepresentation(trickOut);

		getResponse().setLocationRef(ResourceUtils.getTrickUrl(trickOut.getId()));
		getResponse().setStatus(Status.SUCCESS_CREATED);

		getLogger().finer("Trick successfully added");

		return result;
	}

}
