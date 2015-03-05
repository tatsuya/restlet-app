package com.tatsuyaoiw.restlet.persistence.memory;

import com.tatsuyaoiw.restlet.persistence.Persistence;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import org.restlet.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemoryTrickPersistence extends MemoryPersistenceService implements Persistence<Trick> {

	// Singleton pattern
	private static MemoryTrickPersistence trickPersistence = new MemoryTrickPersistence();

	public static MemoryTrickPersistence getTrickPersistence() {
		return trickPersistence;
	}

	private MemoryTrickPersistence() {}

	@Override
	public Trick add(Trick trick) {
		Context.getCurrentLogger().finer("Method add() of TrickPersistence called.");

		String id = UUID.randomUUID().toString();
		trick.setId(id);
		getPersistence().put(id, trick);

		Context.getCurrentLogger().finer("Method add() of TrickPersistence finished.");

		return trick;
	}

	@Override
	public Boolean remove(String id) {
		Context.getCurrentLogger().finer("Method remove() of TrickPersistence called");

		Trick trick = getPersistence().remove(id);

		Context.getCurrentLogger().finer("Method remove() of TrickPersistence finished");

		return trick != null;
	}

	@Override
	public List<Trick> findAll() {
		Context.getCurrentLogger().finer("Method findAll() of TrickPersistence called");

		List<Trick> tricks = new ArrayList<Trick>(getPersistence().values());

		Context.getCurrentLogger().finer("Method findAll() of TrickPersistence called");

		return tricks;
	}

	@Override
	public Trick findById(String id) {
		Context.getCurrentLogger().finer("Method findById() of TrickPersistence called");

		Trick trick = getPersistence().get(id);

		Context.getCurrentLogger().finer("Method findById() of TrickPersistence finished.");

		return trick;
	}

}
