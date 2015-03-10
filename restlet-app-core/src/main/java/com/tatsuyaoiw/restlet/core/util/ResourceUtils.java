package com.tatsuyaoiw.restlet.core.util;

public class ResourceUtils {

	/**
	 * Returns the URL of the resource that represents a trick.
	 *
	 * @param id The identifier of the trick.
	 * @return The URL of the resource that represents a trick.
	 */
	public static String getTrickUrl(String id) {
		return "/tricks/" + id;
	}

	/**
	 * Returns the URL of the resource that represents a movie.
	 *
	 * @param id The identifier of the movie.
	 * @return The URL of the resource that represents a movie.
	 */
	public static String getMovieUrl(String id) {
		return "/contacts/" + id;
	}

}
