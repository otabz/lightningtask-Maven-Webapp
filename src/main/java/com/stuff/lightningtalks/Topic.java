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
	
	@Override
	public boolean equals(Object other) {
		if(null == other) {
			return false;
		}
		else if(!(other instanceof Topic)) {
			return false;
		}
		else if(!((Topic)other).subject.equals(this.subject)) {
			return false;
		}
		else if(!((Topic)other).description.equals(this.description)) {
			return false;
		}
		else if(!((Topic)other).userId.equals(this.userId)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return this.subject.hashCode()/11
				+ this.description.hashCode()/33 + this.userId.hashCode();
	}

}
