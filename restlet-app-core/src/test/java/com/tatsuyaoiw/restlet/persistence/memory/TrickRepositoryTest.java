package com.tatsuyaoiw.restlet.persistence.memory;

import com.tatsuyaoiw.restlet.persistence.strategy.InMemoryStrategy;
import com.tatsuyaoiw.restlet.persistence.repository.TrickRepository;
import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TrickRepositoryTest {

	private final static String NAME_1 = "Ollie";
	private final static String DESC_1 = "A trick in which the snowboarder springs off the tail of the board and into the air.";
	private final static String NAME_2 = "Nollie";
	private final static String DESC_2 = "A trick in which the snowboarder springs off the nose of the board and into the air.";

	@Before
	public void before() throws Exception {
		TrickRepository.getInstance().init(new InMemoryStrategy<Trick>());
	}

	@Test
	public void testAdd() throws Exception {
		TrickRepository repository = TrickRepository.getInstance();

		List<Trick> tricks = repository.findAll();
		Assert.assertEquals(0, tricks.size());

		Trick toAdd = new Trick();
		toAdd.setName(NAME_1);
		toAdd.setDescription(DESC_1);

		Trick added = repository.add(toAdd);

		Assert.assertNotNull(added.getId());
		Assert.assertEquals(NAME_1, added.getName());
		Assert.assertEquals(DESC_1, added.getDescription());

		tricks = repository.findAll();
		Assert.assertEquals(1, tricks.size());

		Trick trick = tricks.get(0);
		Assert.assertEquals(added.getId(), trick.getId());
		Assert.assertEquals(NAME_1, trick.getName());
		Assert.assertEquals(DESC_1, trick.getDescription());
	}

	@Test
	public void testRemove() throws Exception {
		TrickRepository repository = TrickRepository.getInstance();

		Trick toAdd = new Trick();
		toAdd.setName(NAME_1);
		toAdd.setDescription(DESC_1);

		Trick added = repository.add(toAdd);

		List<Trick> tricks = repository.findAll();
		Assert.assertEquals(1, tricks.size());

		Boolean isRemoved = repository.remove(added.getId());
		Assert.assertTrue(isRemoved);

		isRemoved = repository.remove(added.getId());
		Assert.assertFalse("should already be removed", isRemoved);

		tricks = repository.findAll();
		Assert.assertEquals(0, tricks.size());
	}

	@Test
	public void testFindById() throws Exception {
		TrickRepository repository = TrickRepository.getInstance();

		Trick toAdd = new Trick();
		toAdd.setName(NAME_1);
		toAdd.setDescription(DESC_1);

		Trick added = repository.add(toAdd);

		List<Trick> tricks = repository.findAll();
		Assert.assertEquals(1, tricks.size());

		Trick trick = repository.findById(added.getId());
		Assert.assertEquals(added.getId(), trick.getId());
		Assert.assertEquals(NAME_1, trick.getName());
		Assert.assertEquals(DESC_1, trick.getDescription());
	}

	@Test
	public void testUpdate() throws Exception {
		TrickRepository repository = TrickRepository.getInstance();

		Trick toAdd = new Trick();
		toAdd.setName(NAME_1);
		toAdd.setDescription(DESC_1);

		Trick added = repository.add(toAdd);

		Assert.assertNotNull(added.getId());
		Assert.assertEquals(NAME_1, added.getName());
		Assert.assertEquals(DESC_1, added.getDescription());

		List<Trick> tricks = repository.findAll();
		Assert.assertEquals(1, tricks.size());

		Trick toUpdate = new Trick();
		toUpdate.setName(NAME_2);
		toUpdate.setDescription(DESC_2);

		Trick updated = repository.update(added.getId(), toUpdate);
		Assert.assertEquals(added.getId(), updated.getId());
		Assert.assertEquals(NAME_2, updated.getName());
		Assert.assertEquals(DESC_2, updated.getDescription());

		tricks = repository.findAll();
		Assert.assertEquals(1, tricks.size());

		Trick trick = tricks.get(0);
		Assert.assertEquals(updated.getId(), trick.getId());
		Assert.assertEquals(NAME_2, trick.getName());
		Assert.assertEquals(DESC_2, trick.getDescription());
	}

}