package com.tatsuyaoiw.restlet.resource.server;

import com.tatsuyaoiw.restlet.persistence.RepositoryManager;
import com.tatsuyaoiw.restlet.persistence.repository.Repository;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import com.tatsuyaoiw.restlet.representation.TrickRepresentation;
import com.tatsuyaoiw.restlet.resource.TrickResource;
import com.tatsuyaoiw.restlet.utils.TrickUtils;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * The basic idea is that the application will be composed of resources, extending
 * the org.restlet.resource.Resource class. Those sub-classed resources will be
 * in charge of handling the incoming requests. One instance of resource subclass
 * will be created for each request to handle, making sure that you don't have to
 * care about concurrent access at this point of your application.
 */
public class TrickServerResource extends ServerResource implements TrickResource {

	private Repository<Trick> repository;

	private Trick trick;

	private String id;

	@Override
	protected void doInit() throws ResourceException {
		// Get trick related to given id
		id = getAttribute("id");

		getLogger().finer("Initialization of TrickServerResource with trick id: " + id);

		// Initialize the persistence layer
		repository = RepositoryManager.getTrickRepository();

		trick = repository.findById(id);

		// Check if retrieved trick is not null. If it is null it means that the given id is wrong.
		setExisting(trick != null);

		getLogger().finer("Initialization of TrickServerResource ended with trick id: " + id);
	}

	@Override
	public TrickRepresentation getTrick() {
		getLogger().finer("Retrieve a trick");

		TrickRepresentation trickRepr = TrickUtils.toTrickRepresentation(trick);

		getLogger().finer("Trick successfully retrieved");

		return trickRepr;
	}

	@Override
	public void remove() {
		getLogger().finer("Removal of trick");

		// Remove trick in DB
		Boolean isRemoved = repository.remove(id);

		if (!isRemoved) {
			getLogger().finer("Trick id does not exist");
			throw new IllegalArgumentException("Trick with the following identifier does not exist: " + id);
		}

		getLogger().finer("Trick successfully removed");
	}

	@Override
	public TrickRepresentation update(TrickRepresentation trickReprIn) {
		getLogger().finer("Update a trick");

		Trick trickIn = TrickUtils.toTrick(trickReprIn);

		if (!isExisting()) {
			throw new IllegalArgumentException("Trick with the following id does not exist: " + id);
		}

		Trick trickOut = repository.update(id, trickIn);

		getLogger().finer("Trick successfully updated");

		return TrickUtils.toTrickRepresentation(trickOut);
	}

}
