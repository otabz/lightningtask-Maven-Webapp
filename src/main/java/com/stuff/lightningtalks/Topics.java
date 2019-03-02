package com.stuff.lightningtalks;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

//business layer
public class Topics {
	
	//no-args, default should be there for injections
	public Topics(){
		
	}
	
	//always best to declare dependencies while construction
	public Topics(DurableTopicsFacade topicsDataStore) {
		this.topicsDataStore = topicsDataStore;
	}
	
	//inject durable storage
	DurableTopicsFacade topicsDataStore;

	public boolean submit(String subject, String description, String userId) {
		Topic topic = new Topic(subject, description, userId,
				Calendar.getInstance().getTime());
		return topicsDataStore.persist(topic);
	}
	
	public Collection<Topic> retrieve() {
		return topicsDataStore.list();
	}
	
	
}
