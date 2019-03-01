package com.stuff.lightningtalks;

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

	public boolean submit(String topic, String description, String userId) {
		return topicsDataStore.persist(topic, description, userId);
	}
	
	
}
