package com.tatsuyaoiw.restlet.persistence.mongo;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.net.UnknownHostException;

public class MongoConfig {
	private final String host;
	private final Integer port;
	private final String database;
	private final String username;
	private final String password;

	public MongoConfig() {
		host = System.getenv("MONGO_HOST");

		if (System.getenv("MONGO_PORT") == null) {
			port = null;
		} else {
			port = Integer.parseInt(System.getenv("MONGO_PORT"));
		}

		database = System.getenv("MONGO_DATABASE");
		username = System.getenv("MONGO_USERNAME");
		password = System.getenv("MONGO_PASSWORD");
	}

	public ServerAddress createServerAddress() throws UnknownHostException {
		if (host == null) {
			return new ServerAddress();
		}
		if (port == null) {
			return new ServerAddress(host);
		}
		return new ServerAddress(host, port);
	}

	public MongoCredential createCredential() {
		if (username == null || database == null || password == null) {
			return null;
		}
		return MongoCredential.createCredential(username, database, password.toCharArray());
	}
}
