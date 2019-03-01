package com.stuff.lightningtalks;

import java.util.Collection;
import java.util.HashSet;
import org.junit.Test;
import static org.junit.Assert.*;

public class TopicsTest {

	@Test
	public void submitATopicSuccessfully() {
		Topics topics = new Topics(new DurableTopicsInMemory(new HashSet<Topic>()));
		topics.submit("First Topic", "Topic Description", "email@domain.com");
		Collection<Topic> list = topics.retrieve();
		assertEquals(1, list.size());
	}
	
	
}
