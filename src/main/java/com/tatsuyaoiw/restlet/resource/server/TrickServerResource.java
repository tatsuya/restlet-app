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
		String id = getAttribute("id");

		getLogger().finer("Initialization of TrickServerResource with trick id: " + id);

		trickPersistence = PersistenceService.getTrickPersistence(AppConfig.STORAGE);

		trick = trickPersistence.findById(id);

		if (trick == null) {
			throw new IllegalArgumentException("Trick id does not exist: " + id);
		}
	}

	@Override
	public TrickRepresentation getTrick() {
		getLogger().finer("Retrieve a trick");

		TrickRepresentation trickRepr = TrickUtils.toTrickRepresentation(trick);

		getLogger().finer("Trick successfully retrieved");

		return trickRepr;
	}

}
