package com.tatsuyaoiw.restlet.persistence;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import org.restlet.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrickPersistence extends PersistenceService<Trick> {

	// Fake persistent storage
	private static Map<String, Trick> persistence = new HashMap<String, Trick>();

	// Singleton pattern
	private static TrickPersistence trickPersistence = new TrickPersistence();

	public static synchronized TrickPersistence getTrickPersistence() {
		return trickPersistence;
	}

	private TrickPersistence() {}

	@Override
	public Trick add(Trick trick) {
		Context.getCurrentLogger().finer("Method add() of TrickPersistence called.");

		BasicDBObject doc = new BasicDBObject("name", trick.getName())
				.append("description", trick.getDescription());
		getCollection().insert(doc);
		trick.setId(doc.get("_id").toString());

//		String id = UUID.randomUUID().toString();
//		trick.setId(id);
//		persistence.put(id, trick);

		Context.getCurrentLogger().finer("Method add() of TrickPersistence finished.");
		return trick;
	}

	@Override
	public List<Trick> findAll() {
		Context.getCurrentLogger().finer("Method findAll() of TrickPersistence called");

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

//		List<Trick> tricks = new ArrayList<Trick>(persistence.values());

		Context.getCurrentLogger().finer("Method findAll() of TrickPersistence called");

		return tricks;
	}

	@Override
	public Trick findById(String id) {
		Context.getCurrentLogger().finer("Method findById() of TrickPersistence called");

//		Trick trick = persistence.get(id);

		BasicDBObject query = new BasicDBObject("_id", id);

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

		Context.getCurrentLogger().finer("Method findById() of TrickPersistence finished.");

		return trick;
	}

}
