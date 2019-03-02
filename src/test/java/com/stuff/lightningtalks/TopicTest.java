package com.stuff.lightningtalks;

import java.util.Calendar;

import org.junit.Test;

import static org.junit.Assert.*;

public class TopicTest {
	
	@Test
	public void whenComparedWithNullMustReturnFalse() {
		Topic topic = new Topic();
		assertFalse("Topic comparison with null didn't return false!",
				topic.equals(null));
	}
	
	@Test
	public void whenComparedWithNotInstanceOfTopicMustReturnFalse() {
		Topic topic = new Topic();
		assertFalse("Topic comparison with other object type didn't return false!",
				topic.equals(new Object()));
	}
	
	@Test
	public void whenComparedToDiffSubjectMustReturnFalse() {
		Topic topic = new Topic("Topic Subject");
		Topic other = new Topic("Different Subject");
		assertFalse("Topic comparison with diff. subject didn't return false!",
				topic.equals(other));
		
	}
	
	@Test
	public void whenComparedToDiffDescriptionMustReturnFalse() {
		Topic topic = new Topic("Topic Subject", "Topic Description");
		Topic other = new Topic("Topic Subject", "Different Description");
		assertFalse("Topic comparison with diff. description didn't return false!",
				topic.equals(other));
	}
	
	@Test
	public void whenComparedToDiffEmailMustReturnFalse() {
		Topic topic = new Topic("Topic Subject", "Topic Description", "Email");
		Topic other = new Topic("Topic Subject", "Topic Description", "Different Email");
		assertFalse("Topic comparison with diff. email didn't return false!",
				topic.equals(other));
	}
	
	@Test
	public void whenComparedToDiffSubmissionDateMustReturnFalse() {
		Topic topic = new Topic("Topic Subject", "Topic Description", "Email");
		Topic other = new Topic("Topic Subject", "Topic Description", "Email",
				Calendar.getInstance().getTime());
		assertFalse("Topic comparison with diff. submission date didn't return false!",
				topic.equals(other));
	}
	
	@Test
	public void whenComparedtoSameTopicMustReturnTrue() {
		Topic topic = new Topic("Topic Subject", "Topic Description", "user@domain.com",
				Calendar.getInstance().getTime());
		assertTrue("Topic comparison with same object didn't return true!",
				topic.equals(new Topic("Topic Subject", "Topic Description", "user@domain.com",
						Calendar.getInstance().getTime())));
	}
	
	@Test
	public void differentTopicsIdealtoHaveDiffHashCode() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Topic topic1 = new Topic("Topic Subject", "Topic Description", "user@domain.com",
				cal.getTime());
		Topic topic2 = new Topic("Topic Subject", "Topic Description", "user@domain.com",
				Calendar.getInstance().getTime());
		assertFalse("For different topics it is ideal to have different hashcodes!",
				topic1.hashCode() == topic2.hashCode());
	}
	
	@Test
	public void euqalTopicsMustHaveSameHashCode() {
		Topic topic1 = new Topic("Topic Subject", "Topic Description", "user@domain.com",
				Calendar.getInstance().getTime());
		Topic topic2 = new Topic("Topic Subject", "Topic Description", "user@domain.com",
				Calendar.getInstance().getTime());
		assertTrue("Same topics hashcode didn't match!", topic1.hashCode() == topic2.hashCode());
	}
	
}
