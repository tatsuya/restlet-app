package com.tatsuyaoiw.restlet.persistence.memory;

import com.tatsuyaoiw.restlet.persistence.strategy.InMemoryStrategy;
import com.tatsuyaoiw.restlet.persistence.repository.MovieRepository;
import com.tatsuyaoiw.restlet.persistence.entity.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MovieRepositoryTest {

	private final static String TITLE_1 = "How to Ollie on a Snowboard";
	private final static String URL_1 = "www.youtube.com/watch?v=9f9_ILcZjCk";
	private final static String TITLE_2 = "How to Ollie Higher on a Snowboard";
	private final static String URL_2 = "www.youtube.com/watch?v=7B4-Lwlo3xM";

	@Before
	public void before() throws Exception {
		MovieRepository.init(new InMemoryStrategy<Movie>());
	}

	@Test
	public void testAdd() throws Exception {
		MovieRepository repository = MovieRepository.getInstance();

		List<Movie> movies = repository.findAll();
		Assert.assertEquals(0, movies.size());

		Movie toAdd = new Movie();
		toAdd.setTitle(TITLE_1);
		toAdd.setUrl(URL_1);

		Movie added = repository.add(toAdd);

		Assert.assertNotNull(added.getId());
		Assert.assertEquals(TITLE_1, added.getTitle());
		Assert.assertEquals(URL_1, added.getUrl());

		movies = repository.findAll();
		Assert.assertEquals(1, movies.size());

		Movie movie = movies.get(0);
		Assert.assertEquals(added.getId(), movie.getId());
		Assert.assertEquals(TITLE_1, movie.getTitle());
		Assert.assertEquals(URL_1, movie.getUrl());
	}

	@Test
	public void testRemove() throws Exception {
		MovieRepository repository = MovieRepository.getInstance();

		Movie toAdd = new Movie();
		toAdd.setTitle(TITLE_1);
		toAdd.setUrl(URL_1);

		Movie added = repository.add(toAdd);

		List<Movie> movies = repository.findAll();
		Assert.assertEquals(1, movies.size());

		Boolean isRemoved = repository.remove(added.getId());
		Assert.assertTrue(isRemoved);

		isRemoved = repository.remove(added.getId());
		Assert.assertFalse("should already be removed", isRemoved);

		movies = repository.findAll();
		Assert.assertEquals(0, movies.size());
	}

	@Test
	public void testFindById() throws Exception {
		MovieRepository repository = MovieRepository.getInstance();

		Movie toAdd = new Movie();
		toAdd.setTitle(TITLE_1);
		toAdd.setUrl(URL_1);

		Movie added = repository.add(toAdd);

		List<Movie> movies = repository.findAll();
		Assert.assertEquals(1, movies.size());

		Movie movie = repository.findById(added.getId());
		Assert.assertEquals(added.getId(), movie.getId());
		Assert.assertEquals(TITLE_1, movie.getTitle());
		Assert.assertEquals(URL_1, movie.getUrl());
	}

	@Test
	public void testUpdate() throws Exception {
		MovieRepository repository = MovieRepository.getInstance();

		Movie toAdd = new Movie();
		toAdd.setTitle(TITLE_1);
		toAdd.setUrl(URL_1);

		Movie added = repository.add(toAdd);

		List<Movie> movies = repository.findAll();
		Assert.assertEquals(1, movies.size());

		Assert.assertNotNull(added.getId());
		Assert.assertEquals(TITLE_1, added.getTitle());
		Assert.assertEquals(URL_1, added.getUrl());

		Movie toUpdate = new Movie();
		toUpdate.setTitle(TITLE_2);
		toUpdate.setUrl(URL_2);

		Movie updated = repository.update(added.getId(), toUpdate);

		Assert.assertEquals(added.getId(), updated.getId());
		Assert.assertEquals(TITLE_2, updated.getTitle());
		Assert.assertEquals(URL_2, updated.getUrl());

		movies = repository.findAll();
		Assert.assertEquals(1, movies.size());

		Movie movie = movies.get(0);
		Assert.assertEquals(updated.getId(), movie.getId());
		Assert.assertEquals(TITLE_2, movie.getTitle());
		Assert.assertEquals(URL_2, movie.getUrl());
	}

}
