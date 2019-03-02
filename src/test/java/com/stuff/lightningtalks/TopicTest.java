package com.stuff.lightningtalks;

import org.junit.Test;

import static org.junit.Assert.*;

public class TopicTest {

	@Test
	public void whenComparedWithNullMustReturnFalse() {
		Topic topic = new Topic("Topic Subject", "Topic Description", "user@domain.com");
		assertFalse("Topic comparison with null didn't return false!", topic.equals(null));
	}
	
	@Test
	public void whenComparedWithNotInstanceOfTopicMustReturnFalse() {
		Topic topic = new Topic("Topic Subject", "Topic Description", "user@domain.com");
		assertFalse("Topic comparison with other object type didn't return false!", topic.equals(new Object()));
	}
	
	@Test
	public void whenComparedtoTopicWithDiffSubjectMustReturnFalse() {
		Topic topic = new Topic("Topic Subject", "Topic Description", "user@domain.com");
		assertFalse("Topic comparison with diff. subject didn't return false!",
				topic.equals(new Topic("Different Subject", "Topic Description", "user@domain.com")));
		assertFalse("Topic comparison with diff. description didn't return false!",
				topic.equals(new Topic("Topic Subject", "Different Description", "user@domain.com")));
		assertFalse("Topic comparison with diff. user didn't return false!",
				topic.equals(new Topic("Topic Subject", "Topic Description", "diff-user@domain.com")));
	}
	
	@Test
	public void whenComparedtoSameObjectMustReturnTrue() {
		Topic topic = new Topic("Topic Subject", "Topic Description", "user@domain.com");
		assertTrue("Topic comparison with same object didn't return true!",
				topic.equals(new Topic("Topic Subject", "Topic Description", "user@domain.com")));
	}
	
	@Test
	public void differentTopicsIdealtoHaveDiffHashCode() {
		Topic topic1 = new Topic("Topic Subject", "Topic Description", "user@domain.com");
		Topic topic2 = new Topic("Topic Subject", "Topic Description", "user1@domain.com");
		assertFalse("For different topics it is ideal to have different hashcodes!",
				topic1.hashCode() == topic2.hashCode());
	}
	
	@Test
	public void euqalTopicsMustHaveSameHashCode() {
		Topic topic1 = new Topic("Topic Subject", "Topic Description", "user@domain.com");
		Topic topic2 = new Topic("Topic Subject", "Topic Description", "user@domain.com");
		assertTrue("Same topics hashcode didn't match!", topic1.hashCode() == topic2.hashCode());
	}
}
