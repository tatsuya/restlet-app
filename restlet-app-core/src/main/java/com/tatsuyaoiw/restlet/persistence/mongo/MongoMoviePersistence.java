package com.tatsuyaoiw.restlet.persistence.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;
import com.tatsuyaoiw.restlet.persistence.Persistence;
import com.tatsuyaoiw.restlet.persistence.entity.Movie;
import org.bson.types.ObjectId;
import org.restlet.Context;

import java.util.ArrayList;
import java.util.List;

public class MongoMoviePersistence extends MongoPersistenceService implements Persistence<Movie> {

	private static final String COLLECTION_NAME = "movies";

	// Singleton pattern
	private static MongoMoviePersistence moviePersistence = new MongoMoviePersistence();

	public static synchronized MongoMoviePersistence getMoviePersistence() {
		return moviePersistence;
	}

	private MongoMoviePersistence() {}

	@Override
	public Movie add(Movie movie) {
		Context.getCurrentLogger().finer("Method add() of " + getClass().getSimpleName() + " called");

		BasicDBObject doc = new BasicDBObject("title", movie.getTitle())
				.append("url", movie.getUrl());
		getCollection(COLLECTION_NAME).insert(doc);
		movie.setId(doc.getString("_id"));

		Context.getCurrentLogger().finer("Method add() of " + getClass().getSimpleName() + " finished");
		return movie;
	}

	@Override
	public Boolean remove(String id) {
		Context.getCurrentLogger().finer("Method remove() of " + getClass().getSimpleName() + " called");

		BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
		WriteResult result = getCollection(COLLECTION_NAME).remove(query);

		Context.getCurrentLogger().finer("Method remove() of " + getClass().getSimpleName() + " finished");

		return result.getN() != 0;
	}

	@Override
	public List<Movie> findAll() {
		Context.getCurrentLogger().finer("Method findAll() of " + getClass().getSimpleName() + " called");

		List<Movie> movies = new ArrayList<Movie>();

		DBCursor cursor = getCollection(COLLECTION_NAME).find();
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

		DBCursor cursor = getCollection(COLLECTION_NAME).find(query);

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
		getCollection(COLLECTION_NAME).update(query, doc);
		movie.setId(query.getString("_id"));

		Context.getCurrentLogger().finer("Method update() of " + getClass().getSimpleName() + " finished");

		return movie;
	}
}
