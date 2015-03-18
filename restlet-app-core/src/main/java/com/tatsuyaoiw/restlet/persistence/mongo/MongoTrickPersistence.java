package com.tatsuyaoiw.restlet.persistence.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;
import com.tatsuyaoiw.restlet.persistence.Repository;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import org.bson.types.ObjectId;
import org.restlet.Context;

import java.util.ArrayList;
import java.util.List;

public class MongoTrickPersistence extends MongoPersistenceService implements Repository<Trick> {

	private static final String COLLECTION_NAME = "tricks";

	private static final MongoTrickPersistence INSTANCE = new MongoTrickPersistence();

	private MongoTrickPersistence() {}

	public static MongoTrickPersistence getInstance() {
		return INSTANCE;
	}

	@Override
	public Trick add(Trick trick) {
		Context.getCurrentLogger().finer("Method add() of " + getClass().getSimpleName() + " called");

		BasicDBObject doc = new BasicDBObject("name", trick.getName())
				.append("description", trick.getDescription());
		getCollection(COLLECTION_NAME).insert(doc);
		trick.setId(doc.getString("_id"));

		Context.getCurrentLogger().finer("Method add() of " + getClass().getSimpleName() + " finished");
		return trick;
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
	public List<Trick> findAll() {
		Context.getCurrentLogger().finer("Method findAll() of " + getClass().getSimpleName() + " called");

		List<Trick> tricks = new ArrayList<Trick>();

		DBCursor cursor = getCollection(COLLECTION_NAME).find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject doc = (BasicDBObject) cursor.next();
				Trick trick = new Trick();
				trick.setId(doc.getString("_id"));
				trick.setName(doc.getString("name"));
				trick.setDescription(doc.getString("description"));
				tricks.add(trick);
			}
		} finally {
			cursor.close();
			Context.getCurrentLogger().finer("Method findAll() of " + getClass().getSimpleName() + " finished");
		}

		return tricks;
	}

	@Override
	public Trick findById(String id) {
		Context.getCurrentLogger().finer("Method findById() of " + getClass().getSimpleName() + " called");

		BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));

		DBCursor cursor = getCollection(COLLECTION_NAME).find(query);

		try {
			BasicDBObject doc = (BasicDBObject) cursor.one();
			if (doc == null) {
				return null;
			}
			Trick trick = new Trick();
			trick.setId(doc.getString("_id"));
			trick.setName(doc.getString("name"));
			trick.setDescription(doc.getString("description"));
			return trick;

		} finally {
			cursor.close();
			Context.getCurrentLogger().finer("Method findById() of " + getClass().getSimpleName() + " finished");
		}
	}

	@Override
	public Trick update(String id, Trick trick) {
		Context.getCurrentLogger().finer("Method update() of " + getClass().getSimpleName() + " called");

		BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
		BasicDBObject doc = new BasicDBObject("name", trick.getName())
				.append("description", trick.getDescription());
		getCollection(COLLECTION_NAME).update(query, doc);
		trick.setId(query.getString("_id"));

		Context.getCurrentLogger().finer("Method update() of " + getClass().getSimpleName() + " finished");

		return trick;
	}

}
