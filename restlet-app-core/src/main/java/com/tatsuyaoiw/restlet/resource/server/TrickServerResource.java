package com.tatsuyaoiw.restlet.resource.server;

import com.tatsuyaoiw.restlet.AppConfig;
import com.tatsuyaoiw.restlet.persistence.Persistence;
import com.tatsuyaoiw.restlet.persistence.PersistenceService;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import com.tatsuyaoiw.restlet.representation.TrickRepresentation;
import com.tatsuyaoiw.restlet.resource.TrickResource;
import com.tatsuyaoiw.restlet.utils.TrickUtils;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class TrickServerResource extends ServerResource implements TrickResource {

	private Persistence<Trick> trickPersistence;

	private Trick trick;

	@Override
	protected void doInit() throws ResourceException {
		// Get trick related to given id
		String id = getAttribute("id");

		getLogger().finer("Initialization of TrickServerResource with trick id: " + id);

		// Initialize the persistence layer
		trickPersistence = PersistenceService.getTrickPersistence(AppConfig.STORAGE);

		trick = trickPersistence.findById(id);

		// Check if retrieved trick is not null. If it is null it means that the given id is wrong.
		setExisting(trick != null);
		if (!isExisting()) {
			getLogger().config("Trick id does not exist: " + id);
		}

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
		Boolean isRemoved = trickPersistence.remove(trick.getId());

		if (!isRemoved) {
			getLogger().finer("Trick id does not exist");
			throw new IllegalArgumentException("Trick with the following identifier does not exist: " + trick.getId());
		}

		getLogger().finer("Trick successfully removed.");
	}

}
