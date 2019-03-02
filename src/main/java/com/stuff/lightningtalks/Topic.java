package com.stuff.lightningtalks;

import java.util.Calendar;
import java.util.Date;

//entity
//be careful while creating entities from schema
//business logic must not overwrite
public class Topic {
	
	private String subject;
	private String description;
	private String userId;
	private Date time;
	
	// requirements of a valid object must be declared while constructing it
	public Topic() {
	}
	
	public Topic(String subject) {
		this(subject, "", "", null);
	}
	
	public Topic(String subject, String description) {
		this(subject, description, "", null);
	}
	
	public Topic(String subject, String description, String userId) {
		this(subject, description, userId, null);
	}
	
	public Topic(String subject, String description, String userId, Date time) {
		this.subject = subject;
		this.description = description;
		this.userId = userId;
		Calendar cal = Calendar.getInstance();
		cal.set(1984, 9, 6);
		this.time = time == null ? cal.getTime() : time;
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
		else if(!((Topic)other).time.equals(this.time)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		System.out.println(this.time.getTime());
		return this.subject.hashCode()/11
				+ this.description.hashCode()/33
				+ this.userId.hashCode()
				+ (int)this.time.getTime();
	}

}
