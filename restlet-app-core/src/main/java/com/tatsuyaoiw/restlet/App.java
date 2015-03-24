package com.tatsuyaoiw.restlet;


import com.tatsuyaoiw.restlet.persistence.RepositoryManager;
import com.tatsuyaoiw.restlet.resource.server.MovieListServerResource;
import com.tatsuyaoiw.restlet.resource.server.MovieServerResource;
import com.tatsuyaoiw.restlet.resource.server.TrickListServerResource;
import com.tatsuyaoiw.restlet.resource.server.TrickServerResource;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App extends Application {

	public static final Logger LOGGER = Engine.getLogger(Application.class);

	private static final int DEFAULT_HTTP_PORT = 9000;

	public static Component createApp() {
		return createApp(DEFAULT_HTTP_PORT);
	}

	public static Component createApp(int defaultPort) {
		Engine.setLogLevel(Level.FINER);
		LOGGER.info("Starting application...");

		RepositoryManager.initRepositories(AppConfig.STORAGE);

		// Create a new Restlet component
		Component component = new Component();

		int port = defaultPort;
		if (System.getenv("PORT") != null) {
			port = Integer.valueOf(System.getenv("PORT"));
		}

		// Add a HTTP server connector to it
		component.getServers().add(Protocol.HTTP, port);

		// Add CLAP (ClassLoader Access Protocol) to access to representations via classloaders
		component.getClients().add(Protocol.CLAP);

		// Then attach it to the local host
		component.getDefaultHost().attach(new App());

		LOGGER.info("Application is now available on http://localhost:" + port + "/web/index.html");

		return component;
	}

	@Override
	public Restlet createInboundRoot() {
		return createApiRouter();
	}

	private Router createApiRouter() {
		Directory directory = new Directory(getContext(), "clap://class/static/");
		directory.setDeeplyAccessible(true);

		Router router = new Router(getContext());

		// Attach server resources to the given URL template.
		// For instance, TrickListServerResource is attached
		// to http://localhost:9000/trick and to http://localhost:9000/tricks/
		router.attach("/tricks", TrickListServerResource.class);
		router.attach("/tricks/", TrickListServerResource.class);
		router.attach("/tricks/{id}", TrickServerResource.class);

		router.attach("/movies", MovieListServerResource.class);
		router.attach("/movies/", MovieListServerResource.class);
		router.attach("/movies/{id}", MovieServerResource.class);

		router.attach("/web", directory);

		return router;
	}
}
