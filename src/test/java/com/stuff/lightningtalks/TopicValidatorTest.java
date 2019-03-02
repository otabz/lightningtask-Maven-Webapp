package com.stuff.lightningtalks;

import org.junit.Test;

import static org.junit.Assert.*;

public class TopicValidatorTest {
	
	@Test
	public void nullValuesAreNotAccepted() {
		assertFalse("Must return false for null!",
				TopicValidator.notNull(null));
		assertTrue("Must return true for non null!",
				TopicValidator.notNull("Topic"));
	}
	
	@Test
	public void emptyValuesAreNotAccepted() {
		assertFalse("Must return false for whitespaces only string!",
				TopicValidator.notEmpty("     "));
		assertTrue("Must return true for non-whitespaces string!",
				TopicValidator.notEmpty("Topic"));
	}
	
	@Test
	public void mustNotAcceptCharactersMoreThanLimit() {
		assertFalse("Topic subject > 80 characters must return false!",
				TopicValidator.withinCharacterLimit("Lightning talks are run on a bi­monthly basis at Stuff NZ within the Product division. Every two months, team "
						+ "members will propose topics they will like to share by submitting a topic and synopsis to the organizing committee. "
						+ "From these submissions, the organizing committee will select THREE of the most interesting topics. These "
						+ "sessions are run on the first Tuesday of every even­month (e.g. February, April, …, December).", 80));
		
		assertTrue("Topic subject <= 80 characters must return true!",
				TopicValidator.withinCharacterLimit("Lightning talks are run on a bi­monthly basis at Stuff NZ within the Product div"
						+ ""
						+ "", 80));
		
		assertFalse("Topic description > 120 characters must return false!",
				TopicValidator.withinCharacterLimit("Lightning talks are run on a bi­monthly basis at Stuff NZ within the Product division. Every two months, team "
						+ "members will propose topics they will like to share by submitting a topic and synopsis to the organizing committee. "
						+ "From these submissions, the organizing committee will select THREE of the most interesting topics. These "
						+ "sessions are run on the first Tuesday of every even­month (e.g. February, April, …, December).", 120));
		
		assertTrue("Topic description <= 120 characters must return true!",
				TopicValidator.withinCharacterLimit("Lightning talks are run on a bi­monthly basis at Stuff NZ within the Product division. Every two months, team "
						+ "members wi"
						+ ""
						+ "", 120));
		
		assertFalse("Email address > 255 characters must return false!",
				TopicValidator.withinCharacterLimit("Lightning talks are run on a bi­monthly basis at Stuff NZ within the Product division. Every two months, team "
						+ "members will propose topics they will like to share by submitting a topic and synopsis to the organizing committee. "
						+ "From these submissions, the organizing committee will select THREE of the most interesting topics. These "
						+ "sessions are run on the first Tuesday of every even­month (e.g. February, April, …, December).", 255));
		
		assertTrue("Email address <= 255 characters must return true!",
				TopicValidator.withinCharacterLimit("Lightning talks are run on a bi­monthly basis at Stuff NZ within the Product division. Every two months, team "
						+ "members will propose topics they will like to share by submitting a topic and synopsis to the organizing committee. "
						+ "From these submissions, the o"
						+ "", 255));
	}
	
	@Test
	public void mustAcceptValidEmail() {
		throw new UnsupportedOperationException();
	}
	
	@Test
	public void mustNotAcceptJunkCharsInSubject() {
		throw new UnsupportedOperationException();
	}
	
}
