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
		MemoryTrickPersistence trickPersistence = MemoryTrickPersistence.getTrickPersistence();

		List<Trick> tricks = trickPersistence.findAll();
		Assert.assertEquals(0, tricks.size());

		Trick trickIn = new Trick();
		trickIn.setName(OLLIE);
		trickIn.setDescription(OLLIE_DESC);

		Trick trickOut = trickPersistence.add(trickIn);
		Assert.assertNotNull(trickOut.getId());
		Assert.assertEquals(OLLIE, trickOut.getName());
		Assert.assertEquals(OLLIE_DESC, trickOut.getDescription());

		tricks = trickPersistence.findAll();
		Assert.assertEquals(1, tricks.size());
	}

	@Test
	public void testRemove() throws Exception {
		MemoryTrickPersistence trickPersistence = MemoryTrickPersistence.getTrickPersistence();

		Trick trick = new Trick();
		trick.setName(OLLIE);
		trick.setDescription(OLLIE_DESC);
		trickPersistence.add(trick);

		List<Trick> tricks = trickPersistence.findAll();
		Assert.assertEquals(1, tricks.size());

		Boolean isRemoved = trickPersistence.remove(tricks.get(0).getId());
		Assert.assertTrue(isRemoved);

		isRemoved = trickPersistence.remove(tricks.get(0).getId());
		Assert.assertFalse(isRemoved);

		tricks = trickPersistence.findAll();
		Assert.assertEquals(0, tricks.size());
	}

	@Test
	public void testUpdate() throws Exception {
		MemoryTrickPersistence trickPersistence = MemoryTrickPersistence.getTrickPersistence();

		List<Trick> tricks = trickPersistence.findAll();
		Assert.assertEquals(0, tricks.size());

		Trick trickToAdd = new Trick();
		trickToAdd.setName(OLLIE);
		trickToAdd.setDescription(OLLIE_DESC);

		Trick trickAdded = trickPersistence.add(trickToAdd);
		Assert.assertNotNull(trickAdded.getId());
		Assert.assertEquals(OLLIE, trickAdded.getName());
		Assert.assertEquals(OLLIE_DESC, trickAdded.getDescription());

		tricks = trickPersistence.findAll();
		Assert.assertEquals(1, tricks.size());

		Trick trickToUpdate = new Trick();
		trickToUpdate.setName(NOLLIE);
		trickToUpdate.setDescription(NOLLIE_DESC);

		Trick trickUpdated = trickPersistence.update(trickAdded.getId(), trickToUpdate);
		Assert.assertEquals(trickAdded.getId(), trickUpdated.getId());
		Assert.assertEquals(NOLLIE, trickUpdated.getName());
		Assert.assertEquals(NOLLIE_DESC, trickUpdated.getDescription());

		tricks = trickPersistence.findAll();
		Assert.assertEquals(1, tricks.size());
	}

}
