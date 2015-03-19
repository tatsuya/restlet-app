package com.tatsuyaoiw.restlet.persistence.repository;

import com.tatsuyaoiw.restlet.persistence.entity.Trick;

public class TrickRepository extends Repository<Trick> {

	private static final TrickRepository INSTANCE = new TrickRepository();

	private TrickRepository() {}

	public static synchronized TrickRepository getInstance() {
		return INSTANCE;
	}

}
