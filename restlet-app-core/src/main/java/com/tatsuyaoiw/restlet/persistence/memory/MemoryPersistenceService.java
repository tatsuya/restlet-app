package com.tatsuyaoiw.restlet.persistence.memory;

import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import org.restlet.Context;

import java.util.HashMap;
import java.util.Map;

public abstract class MemoryPersistenceService {

	// Fake persistent storage
	private static Map<String, Trick> persistence;

	public static void initialize() {
		persistence = new HashMap<String, Trick>();
		Context.getCurrentLogger().info("OnMemory persistent storage is initialized.");
	}

	protected Map<String, Trick> getPersistence() {
		return persistence;
	}
}
