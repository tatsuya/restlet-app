package com.tatsuyaoiw.restlet.utils;

import com.tatsuyaoiw.restlet.representation.TrickRepresentation;

public class TrickUtils {

	public static TrickRepresentation toTrickRepresentation() {
		TrickRepresentation trick = new TrickRepresentation();
		trick.setId("01234567-89ab-cdef-0123-456789abcdef");
		trick.setName("Ollie");
		trick.setDescription("A trick in which the snowboarder springs off the tail of the board and into the air.");
		return trick;
	}

}
