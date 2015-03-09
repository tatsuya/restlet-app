package com.tatsuyaoiw.restlet.persistence.memory;

import com.tatsuyaoiw.restlet.persistence.entity.Movie;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import org.restlet.Context;

import java.util.HashMap;
import java.util.Map;

public abstract class MemoryPersistenceService {

	// Fake persistent storage
	private static Map<String, Trick> tricks;
	private static Map<String, Movie> movies;

	public static void initialize() {
		tricks = new HashMap<String, Trick>();
		movies = new HashMap<String, Movie>();
		Context.getCurrentLogger().finer("OnMemory persistent storage is initialized.");
	}

	protected Map<String, Trick> getTricks() {
		return tricks;
	}

	protected Map<String, Movie> getMovies() {
		return movies;
	}

}
