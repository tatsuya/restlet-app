package com.tatsuyaoiw.restlet.persictence.memory;

import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import com.tatsuyaoiw.restlet.persistence.memory.MemoryTrickPersistence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MemoryTrickPersistenceTest {

	@Before
	public void before() throws Exception {
		MemoryTrickPersistence.initialize();
	}

	@Test
	public void testAdd() throws Exception {
		MemoryTrickPersistence trickPersistence = MemoryTrickPersistence.getTrickPersistence();

		List<Trick> tricks = trickPersistence.findAll();
		Assert.assertEquals(0, tricks.size());

		Trick trick = new Trick();
		trick.setName("Ollie");
		trick.setDescription("A trick in which the snowboarder springs off the tail of the board and into the air.");
		trickPersistence.add(trick);

		tricks = trickPersistence.findAll();
		Assert.assertEquals(1, tricks.size());
	}

	@Test
	public void testRemove() throws Exception {
		MemoryTrickPersistence trickPersistence = MemoryTrickPersistence.getTrickPersistence();

		Trick trick = new Trick();
		trick.setName("Ollie");
		trick.setDescription("A trick in which the snowboarder springs off the tail of the board and into the air.");
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
}
