package com.tatsuyaoiw.restlet.resource.server;

import com.tatsuyaoiw.restlet.persistence.Persistence;
import com.tatsuyaoiw.restlet.persistence.PersistenceService;
import com.tatsuyaoiw.restlet.persistence.Storage;
import com.tatsuyaoiw.restlet.persistence.entity.Movie;
import com.tatsuyaoiw.restlet.representation.MovieRepresentation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class MovieServerResourceTest {

	private final static String TITLE_1 = "How to Ollie on a Snowboard";
	private final static String URL_1 = "www.youtube.com/watch?v=9f9_ILcZjCk";
	private final static String TITLE_2 = "How to Ollie Higher on a Snowboard";
	private final static String URL_2 = "www.youtube.com/watch?v=7B4-Lwlo3xM";

	private Persistence<Movie> persistence;
	private String id;

	@Before
	public void before() throws Exception {
		PersistenceService.initialize(Storage.MEMORY);

		persistence = PersistenceService.getMoviePersistence();

		Movie toAdd = new Movie();
		toAdd.setTitle(TITLE_1);
		toAdd.setUrl(URL_1);

		Movie added = persistence.add(toAdd);

		List<Movie> movies = persistence.findAll();
		Assert.assertEquals(1, movies.size());

		Movie trick = movies.get(0);
		Assert.assertEquals(added.getId(), trick.getId());
		Assert.assertEquals(TITLE_1, trick.getTitle());
		Assert.assertEquals(URL_1, trick.getUrl());

		id = added.getId();
	}

	private MovieServerResource spyResource(MovieServerResource resource) {
		MovieServerResource spy = spy(resource);
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("id", id);
		when(spy.getRequestAttributes()).thenReturn(attributes);
		return spy;
	}

	@Test
	public void testGetMovie() throws Exception {
		MovieServerResource spy = spyResource(new MovieServerResource());

		spy.doInit();

		MovieRepresentation trick = spy.getMovie();
		Assert.assertEquals(id, trick.getId());
		Assert.assertEquals(TITLE_1, trick.getTitle());
		Assert.assertEquals(URL_1, trick.getUrl());
	}

	@Test
	public void testRemove() throws Exception {
		MovieServerResource spy = spyResource(new MovieServerResource());

		spy.doInit();

		spy.remove();

		List<Movie> movies = persistence.findAll();
		Assert.assertEquals(0, movies.size());
	}

	@Test
	public void testUpdate() throws Exception {
		MovieServerResource spy = spyResource(new MovieServerResource());

		spy.doInit();

		MovieRepresentation toUpdate = new MovieRepresentation();
		toUpdate.setTitle(TITLE_2);
		toUpdate.setUrl(URL_2);

		spy.update(toUpdate);

		List<Movie> movies = persistence.findAll();
		Assert.assertEquals(1, movies.size());

		Movie movie = movies.get(0);
		Assert.assertEquals(id, movie.getId());
		Assert.assertEquals(TITLE_2, movie.getTitle());
		Assert.assertEquals(URL_2, movie.getUrl());
	}
}
