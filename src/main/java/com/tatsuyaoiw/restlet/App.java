package com.tatsuyaoiw.restlet;

import com.tatsuyaoiw.restlet.resource.server.TrickListServerResource;
import com.tatsuyaoiw.restlet.resource.server.TrickServerResource;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.routing.Router;

import java.util.logging.Logger;

public class App extends Application {

	public static final Logger LOGGER = Engine.getLogger(Application.class);

	private static final int DEFAULT_PORT = 9000;

	public static void main(String[] args) throws Exception {
		LOGGER.info("Starting application...");

		// Create a new Restlet component
		Component component = new Component();

		// Add a HTTP server connector to it
		int port = DEFAULT_PORT;
		if (System.getenv("PORT") != null) {
			port = Integer.valueOf(System.getenv("PORT"));
		}
		component.getServers().add(Protocol.HTTP, port);

		// Then attach it to the local host
		component.getDefaultHost().attach(new App());

		component.start();

		LOGGER.info("Sample Restlet App started");
		LOGGER.info("URL: http://localhost:" + port);
	}

	@Override
	public Restlet createInboundRoot() {
		return createApiRouter();
	}

	private Router createApiRouter() {
		Router router = new Router(getContext());

		router.attach("/tricks", TrickListServerResource.class);
		router.attach("/tricks/", TrickListServerResource.class);
		router.attach("/tricks/{id}", TrickServerResource.class);

		return router;
	}
}
