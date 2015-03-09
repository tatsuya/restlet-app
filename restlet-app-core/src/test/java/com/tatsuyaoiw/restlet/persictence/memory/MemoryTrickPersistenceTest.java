package com.tatsuyaoiw.restlet.persictence.memory;

import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import com.tatsuyaoiw.restlet.persistence.memory.MemoryTrickPersistence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MemoryTrickPersistenceTest {

	private final static String OLLIE = "Ollie";
	private final static String OLLIE_DESC = "A trick in which the snowboarder springs off the tail of the board and into the air.";
	private final static String NOLLIE = "Nollie";
	private final static String NOLLIE_DESC = "A trick in which the snowboarder springs off the nose of the board and into the air.";

	@Before
	public void before() throws Exception {
		MemoryTrickPersistence.initialize();
	}

	@Test
	public void testAdd() throws Exception {
		MemoryTrickPersistence persistence = MemoryTrickPersistence.getTrickPersistence();

		List<Trick> tricks = persistence.findAll();
		Assert.assertEquals(0, tricks.size());

		Trick toAdd = new Trick();
		toAdd.setName(OLLIE);
		toAdd.setDescription(OLLIE_DESC);

		Trick added = persistence.add(toAdd);

		Assert.assertNotNull(added.getId());
		Assert.assertEquals(OLLIE, added.getName());
		Assert.assertEquals(OLLIE_DESC, added.getDescription());

		tricks = persistence.findAll();
		Assert.assertEquals(1, tricks.size());

		Trick trick = tricks.get(0);
		Assert.assertEquals(added.getId(), trick.getId());
		Assert.assertEquals(OLLIE, trick.getName());
		Assert.assertEquals(OLLIE_DESC, trick.getDescription());
	}

	@Test
	public void testRemove() throws Exception {
		MemoryTrickPersistence persistence = MemoryTrickPersistence.getTrickPersistence();

		Trick toAdd = new Trick();
		toAdd.setName(OLLIE);
		toAdd.setDescription(OLLIE_DESC);

		Trick added = persistence.add(toAdd);

		List<Trick> tricks = persistence.findAll();
		Assert.assertEquals(1, tricks.size());

		Boolean isRemoved = persistence.remove(added.getId());
		Assert.assertTrue(isRemoved);

		isRemoved = persistence.remove(added.getId());
		Assert.assertFalse("should already be removed", isRemoved);

		tricks = persistence.findAll();
		Assert.assertEquals(0, tricks.size());
	}

	@Test
	public void testFindById() throws Exception {
		MemoryTrickPersistence persistence = MemoryTrickPersistence.getTrickPersistence();

		Trick toAdd = new Trick();
		toAdd.setName(OLLIE);
		toAdd.setDescription(OLLIE_DESC);

		Trick added = persistence.add(toAdd);

		List<Trick> tricks = persistence.findAll();
		Assert.assertEquals(1, tricks.size());

		Trick trick = persistence.findById(added.getId());
		Assert.assertEquals(added.getId(), trick.getId());
		Assert.assertEquals(OLLIE, trick.getName());
		Assert.assertEquals(OLLIE_DESC, trick.getDescription());
	}

	@Test
	public void testUpdate() throws Exception {
		MemoryTrickPersistence persistence = MemoryTrickPersistence.getTrickPersistence();

		Trick toAdd = new Trick();
		toAdd.setName(OLLIE);
		toAdd.setDescription(OLLIE_DESC);

		Trick added = persistence.add(toAdd);

		Assert.assertNotNull(added.getId());
		Assert.assertEquals(OLLIE, added.getName());
		Assert.assertEquals(OLLIE_DESC, added.getDescription());

		List<Trick> tricks = persistence.findAll();
		Assert.assertEquals(1, tricks.size());

		Trick toUpdate = new Trick();
		toUpdate.setName(NOLLIE);
		toUpdate.setDescription(NOLLIE_DESC);

		Trick updated = persistence.update(added.getId(), toUpdate);
		Assert.assertEquals(added.getId(), updated.getId());
		Assert.assertEquals(NOLLIE, updated.getName());
		Assert.assertEquals(NOLLIE_DESC, updated.getDescription());

		tricks = persistence.findAll();
		Assert.assertEquals(1, tricks.size());

		Trick trick = tricks.get(0);
		Assert.assertEquals(updated.getId(), trick.getId());
		Assert.assertEquals(NOLLIE, trick.getName());
		Assert.assertEquals(NOLLIE_DESC, trick.getDescription());
	}

}
