package com.tatsuyaoiw.restlet.persistence.mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.restlet.Context;

import java.net.UnknownHostException;

public abstract class MongoPersistenceService {

	private static MongoClient client;

	public static void initialize() {
		try {
			client = new MongoClient();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
		Context.getCurrentLogger().finer("MongoDB persistent storage is initialized");
	}

	protected DB getDB() {
		if (client == null) {
			throw new RuntimeException("MongoClient must be initialized");
		}
		// At this point, the db object will be a connection to a MongoDB server
		// for the specified database. With it, you can do further operations.
		return client.getDB("mydb");
	}

	protected DBCollection getCollection() {
		Context.getCurrentLogger().finer("Get a collection to use");
		DBCollection collection = getDB().getCollection("testCollection");
		Context.getCurrentLogger().finer("Got a collection to use");
		return collection;
	}

}
