package com.tatsuyaoiw.restlet.persistence.strategy;

import com.tatsuyaoiw.restlet.persistence.entity.Entity;

import java.util.List;

public interface RepositoryStrategy<T extends Entity> {

	T add(T entity);

	List<T> list();

	T get(String id);

	T update(T entity);

	boolean remove(String id);

}
