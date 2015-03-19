package com.tatsuyaoiw.restlet.persistence.strategy;

import com.tatsuyaoiw.restlet.persistence.entity.Entity;

import java.util.List;

public interface RepositoryStrategy<T extends Entity> {

	T create(T entity);

	List<T> list();

	T retrieve(String id);

	T update(T entity);

	boolean delete(String id);

}
