package com.stuff.lightningtalks;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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
	
	public Collection<Topic> retrieve() throws ParseException {
		Topic topic = new Topic("", "", "", new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTime(topic.calculateTalkDate());
		cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);
		return topicsDataStore.list(cal.getTime());
	}
	
	public Collection<Topic> matches(String topic) {
		return topicsDataStore.matches(topic);
	}
	
}
