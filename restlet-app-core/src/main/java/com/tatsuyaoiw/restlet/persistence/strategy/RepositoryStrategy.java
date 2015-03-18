package com.tatsuyaoiw.restlet.persistence.strategy;

import com.tatsuyaoiw.restlet.persistence.entity.Entity;

import java.util.List;

public abstract class RepositoryStrategy<T extends Entity> {

	public abstract T add(T entity);

	public abstract List<T> list();

	public abstract T get(String id);

	public abstract T update(T entity);

	public abstract boolean remove(String id);

}
