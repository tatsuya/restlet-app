package com.tatsuyaoiw.restlet.persistence.repository;

import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import com.tatsuyaoiw.restlet.persistence.strategy.RepositoryStrategy;
import org.restlet.Context;

import java.util.List;

public class TrickRepository implements Repository<Trick> {

	private static RepositoryStrategy<Trick> strategy;

	private static final TrickRepository INSTANCE = new TrickRepository();

	private TrickRepository() {}

	public static synchronized TrickRepository getInstance() {
		return INSTANCE;
	}

	public static void init(RepositoryStrategy<Trick> strategy) {
		TrickRepository.strategy = strategy;
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
