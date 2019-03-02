package com.stuff.lightningtalks;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

//business layer
public class Topics {
	
	//inject durable storage
	@Inject
	DurableTopicsFacade topicsDataStore;
	
	//no-args, default should be there for injections
	public Topics(){
	}
	
	//always best to declare dependencies while construction
	public Topics(DurableTopicsFacade topicsDataStore) {
		this.topicsDataStore = topicsDataStore;
	}

	public Object submit(String subject, String description, String userId) {
		Topic topic = new Topic(subject, description, userId,
				Calendar.getInstance().getTime());
		return topicsDataStore.persist(topic);
	}
	
	public Collection<Topic> retrieve() {
		return topicsDataStore.list();
	}
	
}
