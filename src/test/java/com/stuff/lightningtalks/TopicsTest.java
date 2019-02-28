package com.stuff.lightningtalks;

import org.junit.Test;
import static org.junit.Assert.*;

public class TopicsTest {

	@Test
	public void submitTopicSuccessfully() {
		Topics topics = new Topics();
		assertTrue(topics.submit("First topic", "Topic Description", "email@doamin.com"));
	}
}
