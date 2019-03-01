package com.stuff.lightningtalks;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class TopicsTest {
	

	@Test
	public void submitTopicSuccessfully() {
		Topics topics = new Topics(new DurableTopicsInMemory());
		topics.submit("First Topic", "Topic Description", "email@domain.com");
	}
	
}
