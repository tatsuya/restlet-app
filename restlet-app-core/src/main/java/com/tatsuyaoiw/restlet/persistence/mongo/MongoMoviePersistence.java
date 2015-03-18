package com.tatsuyaoiw.restlet.persistence.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;
import com.tatsuyaoiw.restlet.persistence.Repository;
import com.tatsuyaoiw.restlet.persistence.entity.Movie;
import org.bson.types.ObjectId;
import org.restlet.Context;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoMoviePersistence implements Repository<Movie> {

	private static MongoClient client;

	private static final String DB_NAME = "mydb";
	private static final String COLLECTION_NAME = "movies";

	private static final MongoMoviePersistence INSTANCE = new MongoMoviePersistence();

	private MongoMoviePersistence() {}

	public static MongoMoviePersistence getInstance() {
		return INSTANCE;
	}

	public static void initialize() {
		MongoConfig config = new MongoConfig();
		try {
			ServerAddress server = config.createServerAddress();
			MongoCredential credential = config.createCredential();
			if (credential == null) {
				client = new MongoClient(server);
			} else {
				client = new MongoClient(server, Arrays.asList(credential));
			}
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	private DBCollection getCollection() {
		if (client == null) {
			throw new RuntimeException("MongoClient must be initialized");
		}
		return client.getDB(DB_NAME).getCollection(COLLECTION_NAME);
	}

	@Override
	public Movie add(Movie movie) {
		Context.getCurrentLogger().finer("Method add() of " + getClass().getSimpleName() + " called");

		BasicDBObject doc = new BasicDBObject("title", movie.getTitle())
				.append("url", movie.getUrl());
		getCollection().insert(doc);
		movie.setId(doc.getString("_id"));

		Context.getCurrentLogger().finer("Method add() of " + getClass().getSimpleName() + " finished");
		return movie;
	}

	@Override
	public Boolean remove(String id) {
		Context.getCurrentLogger().finer("Method remove() of " + getClass().getSimpleName() + " called");

		BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
		WriteResult result = getCollection().remove(query);

		Context.getCurrentLogger().finer("Method remove() of " + getClass().getSimpleName() + " finished");

		return result.getN() != 0;
	}

	@Override
	public List<Movie> findAll() {
		Context.getCurrentLogger().finer("Method findAll() of " + getClass().getSimpleName() + " called");

		List<Movie> movies = new ArrayList<Movie>();

		DBCursor cursor = getCollection().find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject doc = (BasicDBObject) cursor.next();
				Movie movie = new Movie();
				movie.setId(doc.getString("_id"));
				movie.setTitle(doc.getString("title"));
				movie.setUrl(doc.getString("url"));
				movies.add(movie);
			}
		} finally {
			cursor.close();
			Context.getCurrentLogger().finer("Method findAll() of " + getClass().getSimpleName() + " finished");
		}

		return movies;
	}

	@Override
	public Movie findById(String id) {
		Context.getCurrentLogger().finer("Method findById() of " + getClass().getSimpleName() + " called");

		BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));

		DBCursor cursor = getCollection().find(query);

		try {
			BasicDBObject doc = (BasicDBObject) cursor.one();
			if (doc == null) {
				return null;
			}
			Movie movie = new Movie();
			movie.setId(doc.getString("_id"));
			movie.setTitle(doc.getString("title"));
			movie.setUrl(doc.getString("url"));
			return movie;

		} finally {
			cursor.close();
			Context.getCurrentLogger().finer("Method findById() of " + getClass().getSimpleName() + " finished");
		}
	}

	@Override
	public Movie update(String id, Movie movie) {
		Context.getCurrentLogger().finer("Method update() of " + getClass().getSimpleName() + " called");

		BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
		BasicDBObject doc = new BasicDBObject("title", movie.getTitle())
				.append("url", movie.getUrl());
		getCollection().update(query, doc);
		movie.setId(query.getString("_id"));

		Context.getCurrentLogger().finer("Method update() of " + getClass().getSimpleName() + " finished");

		return movie;
	}
}
