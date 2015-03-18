package com.tatsuyaoiw.restlet.resource.server;

import com.tatsuyaoiw.restlet.persistence.RepositoryManager;
import com.tatsuyaoiw.restlet.persistence.Storage;
import com.tatsuyaoiw.restlet.representation.MovieRepresentation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.restlet.Response;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class MovieListServerResourceTest {

	private final static String TITLE_1 = "How to Ollie on a Snowboard";
	private final static String URL_1 = "www.youtube.com/watch?v=9f9_ILcZjCk";

	@Before
	public void before() throws Exception {
		RepositoryManager.initialize(Storage.MEMORY);
	}

	@Test
	public void testAdd() throws Exception {
		MovieListServerResource resource = new MovieListServerResource();
		MovieListServerResource spy = spy(resource);
		Response mockResponse = mock(Response.class);
		when(spy.getResponse()).thenReturn(mockResponse);

		spy.doInit();

		List<MovieRepresentation> movies = spy.getMovies();
		Assert.assertEquals(0, movies.size());

		MovieRepresentation toAdd = new MovieRepresentation();
		toAdd.setTitle(TITLE_1);
		toAdd.setUrl(URL_1);

		MovieRepresentation added = spy.add(toAdd);

		Assert.assertNotNull(added.getId());
		Assert.assertEquals(TITLE_1, added.getTitle());
		Assert.assertEquals(URL_1, added.getUrl());

		movies = spy.getMovies();
		Assert.assertEquals(1, movies.size());

		MovieRepresentation movie = movies.get(0);
		Assert.assertEquals(added.getId(), movie.getId());
		Assert.assertEquals(TITLE_1, movie.getTitle());
		Assert.assertEquals(URL_1, movie.getUrl());
	}

}
