package com.stuff.lightningtalks;

//entity
//be careful while creating entities from schema
//business logic must not overwrite
public class Topic {
	
	private String subject;
	private String description;
	private String userId;
	
	// requirements of a valid object must be declared while constructing it
	public Topic(String subject, String description, String userId) {
		this.subject = subject;
		this.description = description;
		this.userId = userId;
	}

}
