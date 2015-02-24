package com.tatsuyaoiw.restlet.utils;

import com.tatsuyaoiw.restlet.persistence.entity.Trick;
import com.tatsuyaoiw.restlet.representation.TrickRepresentation;

public class TrickUtils {

	public static Trick toTrick(TrickRepresentation trickRepr) {
		if (trickRepr == null) {
			return null;
		}
		Trick trick = new Trick();
		trick.setName(trickRepr.getName());
		trick.setDescription(trickRepr.getDescription());
		return trick;
	}

	public static TrickRepresentation toTrickRepresentation(Trick trick) {
		if (trick == null) {
			return null;
		}
		TrickRepresentation trickRepr = new TrickRepresentation();
		trickRepr.setName(trick.getName());
		trickRepr.setDescription(trick.getDescription());
		return trickRepr;
	}

}
