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
		assertFalse(
				"Topic comparison with other object type didn't return false!",
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
		assertFalse(
				"Topic comparison with diff. description didn't return false!",
				topic.equals(other));
	}

	@Test
	public void whenComparedToDiffEmailMustReturnFalse() {
		Topic topic = new Topic("Topic Subject", "Topic Description", "Email");
		Topic other = new Topic("Topic Subject", "Topic Description",
				"Different Email");
		assertFalse("Topic comparison with diff. email didn't return false!",
				topic.equals(other));
	}

	@Test
	public void whenComparedToDiffSubmissionDateMustReturnFalse() {
		Topic topic = new Topic("Topic Subject", "Topic Description", "Email");
		Topic other = new Topic("Topic Subject", "Topic Description", "Email",
				Calendar.getInstance().getTime());
		assertFalse(
				"Topic comparison with diff. submission date didn't return false!",
				topic.equals(other));
	}

	@Test
	public void whenComparedtoSameTopicMustReturnTrue() {
		Topic topic = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", Calendar.getInstance().getTime());
		assertTrue("Topic comparison with same object didn't return true!",
				topic.equals(new Topic("Topic Subject", "Topic Description",
						"user@domain.com", Calendar.getInstance().getTime())));
	}

	@Test
	public void differentTopicsIdealtoHaveDiffHashCode() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Topic topic1 = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", cal.getTime());
		Topic topic2 = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", Calendar.getInstance().getTime());
		assertFalse(
				"For different topics it is ideal to have different hashcodes!",
				topic1.hashCode() == topic2.hashCode());
	}

	@Test
	public void euqalTopicsMustHaveSameHashCode() {
		Topic topic1 = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", Calendar.getInstance().getTime());
		Topic topic2 = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", Calendar.getInstance().getTime());
		assertTrue("Same topics hashcode didn't match!",
				topic1.hashCode() == topic2.hashCode());
	}

	@Test
	public void verifyTalkMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(2019, 0, 28);
		Topic topic = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", cal.getTime());
		assertEquals(1, topic.calculateMonth(cal));
		
		cal.set(2019, 10, 28);
		topic = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", cal.getTime());
		assertEquals(11, topic.calculateMonth(cal));
		
		cal.set(2019, 01, 4);
		topic = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", cal.getTime());
		assertEquals(1, topic.calculateMonth(cal));
		
		cal.set(2019, 01, 5);
		topic = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", cal.getTime());
		assertEquals(3, topic.calculateMonth(cal));
		
		cal.set(2019, 11, 3);
		topic = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", cal.getTime());
		assertEquals(1, topic.calculateMonth(cal));
	}
	
	@Test
	public void verifyFirstTuesdayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(2018, Calendar.DECEMBER, 1);
		Topic topic = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", cal.getTime());
		assertEquals(4, topic.calculateFirstTuesday(cal));
		
		cal.set(2019, Calendar.MARCH, 1);
		topic = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", cal.getTime());
		assertEquals(5, topic.calculateFirstTuesday(cal));
		
		cal.set(2018, Calendar.NOVEMBER, 1);
		topic = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", cal.getTime());
		assertEquals(6, topic.calculateFirstTuesday(cal));
		
		cal.set(2018, Calendar.OCTOBER, 1);
		topic = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", cal.getTime());
		assertEquals(2, topic.calculateFirstTuesday(cal));
		
		cal.set(2019, Calendar.SEPTEMBER, 1);
		topic = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", cal.getTime());
		assertEquals(3, topic.calculateFirstTuesday(cal));
	}
	
	@Test
	public void verifyTalkDateAfter1Month() {
		Calendar cal = Calendar.getInstance();
		cal.set(2019, Calendar.JANUARY, 30);
		Topic topic = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", cal.getTime());
		Calendar expected = Calendar.getInstance();
		expected.set(2019, Calendar.FEBRUARY, 5);
		assertEquals(0, expected.getTime().compareTo(topic.calculateTalkDate()));
	}
	
	@Test
	public void verifyTalkDateAfter2Months() {
		Calendar cal = Calendar.getInstance();
		cal.set(2019, Calendar.FEBRUARY, 5);
		Topic topic = new Topic("Topic Subject", "Topic Description",
				"user@domain.com", cal.getTime());
		Calendar expected = Calendar.getInstance();
		expected.set(2019, Calendar.APRIL, 2);
		assertEquals(0, expected.getTime().compareTo(topic.calculateTalkDate()));
	}
}
