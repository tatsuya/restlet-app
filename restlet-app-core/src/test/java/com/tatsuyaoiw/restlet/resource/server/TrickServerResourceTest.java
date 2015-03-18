package com.tatsuyaoiw.restlet.resource.server;

import com.tatsuyaoiw.restlet.persistence.Repository;
import com.tatsuyaoiw.restlet.persistence.PersistenceService;
import com.tatsuyaoiw.restlet.persistence.Storage;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import com.tatsuyaoiw.restlet.representation.TrickRepresentation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class TrickServerResourceTest {

	private final static String NAME_1 = "Ollie";
	private final static String DESC_1 = "A trick in which the snowboarder springs off the tail of the board and into the air.";
	private final static String NAME_2 = "Nollie";
	private final static String DESC_2 = "A trick in which the snowboarder springs off the nose of the board and into the air.";

	private Repository<Trick> persistence;
	private String id;

	@Before
	public void before() {
		PersistenceService.initialize(Storage.MEMORY);

		persistence = PersistenceService.getTrickPersistence();

		Trick toAdd = new Trick();
		toAdd.setName(NAME_1);
		toAdd.setDescription(DESC_1);

		Trick added = persistence.add(toAdd);

		List<Trick> tricks = persistence.findAll();
		Assert.assertEquals(1, tricks.size());

		Trick trick = tricks.get(0);
		Assert.assertEquals(added.getId(), trick.getId());
		Assert.assertEquals(NAME_1, trick.getName());
		Assert.assertEquals(DESC_1, trick.getDescription());

		id = added.getId();
	}

	private TrickServerResource spyResource(TrickServerResource resource) {
		TrickServerResource spy = spy(resource);
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("id", id);
		when(spy.getRequestAttributes()).thenReturn(attributes);
		return spy;
	}

	@Test
	public void testGetTrick() throws Exception {
		TrickServerResource spy = spyResource(new TrickServerResource());

		spy.doInit();

		TrickRepresentation trick = spy.getTrick();
		Assert.assertEquals(id, trick.getId());
		Assert.assertEquals(NAME_1, trick.getName());
		Assert.assertEquals(DESC_1, trick.getDescription());
	}

	@Test
	public void testRemove() throws Exception {
		TrickServerResource spy = spyResource(new TrickServerResource());

		spy.doInit();

		spy.remove();

		List<Trick> tricks = persistence.findAll();
		Assert.assertEquals(0, tricks.size());
	}

	@Test
	public void testUpdate() throws Exception {
		TrickServerResource spy = spyResource(new TrickServerResource());

		spy.doInit();

		TrickRepresentation toUpdate = new TrickRepresentation();
		toUpdate.setName(NAME_2);
		toUpdate.setDescription(DESC_2);

		spy.update(toUpdate);

		List<Trick> tricks = persistence.findAll();
		Assert.assertEquals(1, tricks.size());

		Trick trick = tricks.get(0);
		Assert.assertEquals(id, trick.getId());
		Assert.assertEquals(NAME_2, trick.getName());
		Assert.assertEquals(DESC_2, trick.getDescription());
	}

}
