package com.tatsuyaoiw.restlet.persistence.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.tatsuyaoiw.restlet.persistence.Persistence;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import org.bson.types.ObjectId;
import org.restlet.Context;

import java.util.ArrayList;
import java.util.List;

public class MongoTrickPersistence extends MongoPersistenceService implements Persistence<Trick> {

	// Singleton pattern
	private static MongoTrickPersistence trickPersistence = new MongoTrickPersistence();

	public static synchronized MongoTrickPersistence getTrickPersistence() {
		return trickPersistence;
	}

	private MongoTrickPersistence() {}

	@Override
	public Trick add(Trick trick) {
		Context.getCurrentLogger().finer("Method add() of MongoTrickPersistence called.");

		BasicDBObject doc = new BasicDBObject("name", trick.getName())
				.append("description", trick.getDescription());
		getCollection().insert(doc);
		trick.setId(doc.get("_id").toString());

		Context.getCurrentLogger().finer("Method add() of MongoTrickPersistence finished.");
		return trick;
	}

	@Override
	public Boolean remove(String id) {
		Context.getCurrentLogger().finer("Method remove() of MongoTrickPersistence called");

		BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
		WriteResult result = getCollection().remove(query);

		Context.getCurrentLogger().finer("Method remove() of MongoTrickPersistence finished");

		int n = result.getN();
		return n != 0;
	}

	@Override
	public List<Trick> findAll() {
		Context.getCurrentLogger().finer("Method findAll() of MongoTrickPersistence called");

		List<Trick> tricks = new ArrayList<Trick>();

		DBCursor cursor = getCollection().find();
		try {
			while (cursor.hasNext()) {
				DBObject doc = cursor.next();
				Trick trick = new Trick();
				trick.setId(doc.get("_id").toString());
				trick.setName(doc.get("name").toString());
				trick.setDescription(doc.get("description").toString());
				tricks.add(trick);
			}
		} finally {
			cursor.close();
		}

		Context.getCurrentLogger().finer("Method findAll() of MongoTrickPersistence called");

		return tricks;
	}

	@Override
	public Trick findById(String id) {
		Context.getCurrentLogger().finer("Method findById() of MongoTrickPersistence called");

		BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));

		DBCursor cursor = getCollection().find(query);
		DBObject doc = cursor.one();
		cursor.close();

		if (doc == null) {
			return null;
		}
		Trick trick = new Trick();
		trick.setId(doc.get("_id").toString());
		trick.setName(doc.get("name").toString());
		trick.setDescription(doc.get("description").toString());

		Context.getCurrentLogger().finer("Method findById() of MongoTrickPersistence finished");

		return trick;
	}

	@Override
	public Trick update(String id, Trick trick) {
		Context.getCurrentLogger().finer("Method update() of MongoTrickPersistence called");

		BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
		BasicDBObject doc = new BasicDBObject("name", trick.getName())
				.append("description", trick.getDescription());
		getCollection().update(query, doc);
		trick.setId(query.get("_id").toString());

		Context.getCurrentLogger().finer("Method update() of MongoTrickPersistence finished");

		return trick;
	}

}
