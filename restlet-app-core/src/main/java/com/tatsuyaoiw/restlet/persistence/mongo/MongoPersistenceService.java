package com.tatsuyaoiw.restlet.persistence.mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.restlet.Context;

import java.net.UnknownHostException;
import java.util.Arrays;

public abstract class MongoPersistenceService {

	private static MongoClient client;

	private static final String DB_NAME = "mydb";

	public static void initialize() {
		MongoConfig config = new MongoConfig();
		try {
			ServerAddress server = config.createServerAddress();
			MongoCredential credential = config.createCredential();
			MongoClientOptions options = new MongoClientOptions.Builder().connectTimeout(20000).build();
			if (credential == null) {
				client = new MongoClient(server, options);
			} else {
				client = new MongoClient(server, Arrays.asList(credential), options);
			}
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
		Context.getCurrentLogger().finer("MongoDB persistent storage is initialized");
	}

	private DB getDB() {
		if (client == null) {
			throw new RuntimeException("MongoClient must be initialized");
		}
		// At this point, the db object will be a connection to a MongoDB server
		// for the specified database. With it, you can do further operations.
		return client.getDB(DB_NAME);
	}

	protected DBCollection getCollection(String name) {
		Context.getCurrentLogger().finer("Get a collection: " + name);
		DBCollection collection = getDB().getCollection(name);
		Context.getCurrentLogger().finer("Get a collection: " + name);
		return collection;
	}

}
