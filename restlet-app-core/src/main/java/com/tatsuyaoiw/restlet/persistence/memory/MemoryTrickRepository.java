package com.tatsuyaoiw.restlet.persistence.memory;

import com.tatsuyaoiw.restlet.persistence.Repository;
import com.tatsuyaoiw.restlet.persistence.RepositoryStrategy;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import org.restlet.Context;

import java.util.List;

public class MemoryTrickRepository implements Repository<Trick> {

	private static RepositoryStrategy<Trick> strategy;

	private static final MemoryTrickRepository INSTANCE = new MemoryTrickRepository();

	private MemoryTrickRepository() {}

	public static synchronized MemoryTrickRepository getINSTANCE() {
		return INSTANCE;
	}

	public static void init(RepositoryStrategy<Trick> strategy) {
		MemoryTrickRepository.strategy = strategy;
	}

	@Override
	public Trick add(Trick toAdd) {
		Context.getCurrentLogger().finer("Method add() of " + getClass().getSimpleName() + " called");
		return strategy.add(toAdd);
	}

	@Override
	public Boolean remove(String id) {
		Context.getCurrentLogger().finer("Method remove() of " + getClass().getSimpleName() + " called");
		return strategy.remove(id);
	}

	@Override
	public List<Trick> findAll() {
		Context.getCurrentLogger().finer("Method findAll() of " + getClass().getSimpleName() + " called");
		return strategy.list();
	}

	@Override
	public Trick findById(String id) {
		Context.getCurrentLogger().finer("Method findById() of " + getClass().getSimpleName() + " called");
		return strategy.get(id);
	}

	@Override
	public Trick update(String id, Trick toUpdate) {
		Context.getCurrentLogger().finer("Method update() of " + getClass().getSimpleName() + " called");
		toUpdate.setId(id);
		return strategy.update(toUpdate);
	}

}
