package com.stuff.lightningtalks;


import java.util.Calendar;
import java.util.Collection;
import javax.inject.Inject;


//business layer
public class Topics {
	
	//inject durable storage
	@Inject
	private DurableTopicsFacade topicsDataStore;
	
	//no-args, default should be there for injections
	public Topics(){
	}
	
	//always best to declare dependencies while construction
	public Topics(DurableTopicsFacade topicsDataStore) {
		this.topicsDataStore = topicsDataStore;
	}

	public Object submit(String subject, String description, String userId,
			String ip, String host, String agent) {
		Topic topic = new Topic(subject, description, userId,
				Calendar.getInstance().getTime());
		topic.setIpAddress(ip);
		topic.setHostName(host);
		topic.setUserAgent(agent);
		return topicsDataStore.persist(topic);
	}
	
	public boolean notUnique(String topic) {
		return topicsDataStore.notUnique(topic);
	}
	
	public Collection<Topic> retrieve() {
		return topicsDataStore.list();
	}
	
	public Collection<Topic> matches(String topic) {
		return topicsDataStore.matches(topic);
	}
	
}
