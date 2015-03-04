package com.tatsuyaoiw.restlet.representation;

/**
 * Acts as container for the representation of a trick.<br>
 * It is mainly used in order to gain fine control on the way we generate XML
 * and JSON document via Jackson.<br>
 */
public class TrickRepresentation {

	private String id;
	private String name;
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
