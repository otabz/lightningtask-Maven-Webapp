package com.stuff.lightningtalks;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TopicsTest {
	
	//Take out datastore building part
	private static DurableTopicsFacade db;
	private static Set<Topic> ds;
	
	@BeforeClass
	public static void setUpInMemoryDB() {
		ds = new HashSet<Topic>();
		db = new DurableTopicsInMemory(ds);
	}
	
	@After
	public void tearDownInMemoryDB() {
		ds.clear();
	}

	@Test
	public void submitATopicSuccessfully() {
		Topics topics = new Topics(db);
		topics.submit("First Topic", "Topic Description", "email@domain.com");
		Collection<Topic> list = topics.retrieve();
		assertEquals("Topic couldn't be persisted!",1, list.size());
		assertTrue("Topic submitted and persisted are not equal!", list.contains(
				new Topic("First Topic", "Topic Description", "email@domain.com",
						Calendar.getInstance().getTime())));
	}
	
	@Test
	public void submitMultiTopicsSuccessfully() {
		Topics topics = new Topics(db);
		topics.submit("First Topic", "Topic Description", "email@domain.com");
		topics.submit("Second Topic", "Topic Description", "email@domain.com");
		Collection<Topic> list = topics.retrieve();
		assertEquals("Topic couldn't be persisted!",2, list.size());
		assertTrue("Topics submitted and persisted are not equal!", list.contains(
				new Topic("First Topic", "Topic Description", "email@domain.com",
						Calendar.getInstance().getTime())) &&
				list.contains(new Topic("Second Topic", "Topic Description", "email@domain.com",
						Calendar.getInstance().getTime())));
	}
	
}
