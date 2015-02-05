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

	public static void main(String[] args) throws Exception {
		LOGGER.info("Starting application...");

		// Create a new Restlet component and add a HTTP server connector to it
		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 9000);

		// Then attach it to the local host
		component.getDefaultHost().attach(new App());

		component.start();

		LOGGER.info("Sample Restlet App started");
		LOGGER.info("URL: http://localhost:9000");
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
