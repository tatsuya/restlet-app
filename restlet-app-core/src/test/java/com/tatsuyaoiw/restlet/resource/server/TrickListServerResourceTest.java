package com.tatsuyaoiw.restlet.resource.server;

import com.tatsuyaoiw.restlet.persistence.RepositoryManager;
import com.tatsuyaoiw.restlet.persistence.StrategyType;
import com.tatsuyaoiw.restlet.representation.TrickRepresentation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.restlet.Response;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class TrickListServerResourceTest {

	private final static String NAME_1 = "Ollie";
	private final static String DESC_1 = "A trick in which the snowboarder springs off the tail of the board and into the air.";

	@Before
	public void before() throws Exception {
		RepositoryManager.initRepositories(StrategyType.MEMORY);
	}

	@Test
	public void testAdd() throws Exception {
		TrickListServerResource resource = new TrickListServerResource();
		TrickListServerResource spy = spy(resource);
		Response mockResponse = mock(Response.class);
		when(spy.getResponse()).thenReturn(mockResponse);

		spy.doInit();

		List<TrickRepresentation> tricks = spy.getTricks();
		Assert.assertEquals(0, tricks.size());

		TrickRepresentation toAdd = new TrickRepresentation();
		toAdd.setName(NAME_1);
		toAdd.setDescription(DESC_1);

		TrickRepresentation added = spy.add(toAdd);

		Assert.assertNotNull(added.getId());
		Assert.assertEquals(NAME_1, added.getName());
		Assert.assertEquals(DESC_1, added.getDescription());

		tricks = spy.getTricks();
		Assert.assertEquals(1, tricks.size());

		TrickRepresentation trick = tricks.get(0);
		Assert.assertEquals(added.getId(), trick.getId());
		Assert.assertEquals(NAME_1, trick.getName());
		Assert.assertEquals(DESC_1, trick.getDescription());
	}
}
