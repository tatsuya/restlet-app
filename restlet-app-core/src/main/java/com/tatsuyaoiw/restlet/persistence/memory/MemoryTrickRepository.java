package com.tatsuyaoiw.restlet.persistence.memory;

import com.tatsuyaoiw.restlet.persistence.Repository;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import org.restlet.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MemoryTrickRepository implements Repository<Trick> {

	private static Map<String, Trick> tricks;

	private static final MemoryTrickRepository INSTANCE = new MemoryTrickRepository();

	private MemoryTrickRepository() {}

	public static synchronized MemoryTrickRepository getINSTANCE() {
		return INSTANCE;
	}

	public static void initialize() {
		tricks = new HashMap<String, Trick>();
	}

	public Map<String, Trick> getTricks() {
		return tricks;
	}

	@Override
	public Trick add(Trick toAdd) {
		Context.getCurrentLogger().finer("Method add() of MemoryTrickPersistence called.");

		String id = UUID.randomUUID().toString();
		toAdd.setId(id);
		getTricks().put(id, toAdd);

		Context.getCurrentLogger().finer("Method add() of MemoryTrickPersistence finished.");

		return toAdd;
	}

	@Override
	public Boolean remove(String id) {
		Context.getCurrentLogger().finer("Method remove() of MemoryTrickPersistence called");

		Trick trick = getTricks().remove(id);

		Context.getCurrentLogger().finer("Method remove() of MemoryTrickPersistence finished");

		return trick != null;
	}

	@Override
	public List<Trick> findAll() {
		Context.getCurrentLogger().finer("Method findAll() of MemoryTrickPersistence called");

		List<Trick> tricks = new ArrayList<Trick>(getTricks().values());

		Context.getCurrentLogger().finer("Method findAll() of MemoryTrickPersistence called");

		return tricks;
	}

	@Override
	public Trick findById(String id) {
		Context.getCurrentLogger().finer("Method findById() of MemoryTrickPersistence called");

		Trick trick = getTricks().get(id);

		Context.getCurrentLogger().finer("Method findById() of MemoryTrickPersistence finished");

		return trick;
	}

	@Override
	public Trick update(String id, Trick toUpdate) {
		Context.getCurrentLogger().finer("Method update() of MemoryTrickPersistence called");

		toUpdate.setId(id);
		getTricks().put(id, toUpdate);

		Context.getCurrentLogger().finer("Method update() of MemoryTrickPersistence finished");

		return toUpdate;
	}

}
