package com.tatsuyaoiw.restlet.persistence;

import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import org.restlet.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TrickPersistence extends PersistenceService<Trick> {

	// Fake persistent storage
	private static Map<String, Trick> persistence = new HashMap<String, Trick>();

	// Singleton pattern
	private static TrickPersistence trickPersistence = new TrickPersistence();

	public static synchronized TrickPersistence getTrickPersistence() {
		return trickPersistence;
	}

	private TrickPersistence() {}

	@Override
	public Trick add(Trick trick) {
		Context.getCurrentLogger().finer("Method add() of TrickPersistence called.");

		String id = UUID.randomUUID().toString();
		trick.setId(id);
		persistence.put(id, trick);

		Context.getCurrentLogger().finer("Method add() of TrickPersistence finished.");
		return trick;
	}

	@Override
	public List<Trick> findAll() {
		Context.getCurrentLogger().finer("Method findAll() of TrickPersistence called");

		List<Trick> tricks = new ArrayList<Trick>(persistence.values());

		Context.getCurrentLogger().finer("Method findAll() of TrickPersistence called");

		return tricks;
	}

	@Override
	public Trick findById(String id) {
		Context.getCurrentLogger().finer("Method findById() of TrickPersistence called");

		Trick trick = persistence.get(id);

		Context.getCurrentLogger().finer("Method findById() of TrickPersistence finished.");

		return trick;
	}

}
