package com.stuff.lightningtalks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BodyParamsTest {

	private final static String LARGE_TXT = "Lightning talks are run on a bi­monthly basis at Stuff NZ within the Product division. Every two months, team "
			+ "members will propose topics they will like to share by submitting a topic and synopsis to the organizing committee. "
			+ "From these submissions, the organizing committee will select THREE of the most interesting topics. These "
			+ "sessions are run on the first Tuesday of every even­month (e.g. February, April, …, December).";
	
	@Test
	public void nullValuesAreNotAccepted() {
		BodyParams params = new BodyParams();
		assertFalse("Must return false for null!",
				params.notNull(null));
		assertTrue("Must return true for non null!",
				params.notNull("Topic"));
	}
	
	@Test
	public void emptyValuesAreNotAccepted() {
		BodyParams params = new BodyParams();
		assertFalse("Must return false for whitespaces only string!",
				params.notEmpty("     "));
		assertTrue("Must return true for non-whitespaces string!",
				params.notEmpty("Topic"));
	}
	
	@Test
	public void mustNotAcceptCharactersMoreThanLimit() {
		BodyParams params = new BodyParams();
		assertFalse("Topic subject > 80 characters must return false!",
				params.withinCharacterLimit(LARGE_TXT, 80));
		
		assertTrue("Topic subject <= 80 characters must return true!",
				params.withinCharacterLimit("Lightning talks are run on a bi­monthly basis at Stuff NZ within the Product div"
						+ ""
						+ "", 80));
		
		assertFalse("Topic description > 120 characters must return false!",
				params.withinCharacterLimit(LARGE_TXT, 120));
		
		assertTrue("Topic description <= 120 characters must return true!",
				params.withinCharacterLimit("Lightning talks are run on a bi­monthly basis at Stuff NZ within the Product division. Every two months, team "
						+ "members wi"
						+ ""
						+ "", 120));
		
		assertFalse("Email address > 255 characters must return false!",
				params.withinCharacterLimit(LARGE_TXT, 255));
		
		assertTrue("Email address <= 255 characters must return true!",
				params.withinCharacterLimit("Lightning talks are run on a bi­monthly basis at Stuff NZ within the Product division. Every two months, team "
						+ "members will propose topics they will like to share by submitting a topic and synopsis to the organizing committee. "
						+ "From these submissions, the o"
						+ "", 255));
	}
	
	@Test
	public void mustAcceptValidEmail() {
		//throw new UnsupportedOperationException();
	}
	
	@Test
	public void mustNotAcceptJunkCharsInSubject() {
		//throw new UnsupportedOperationException();
	}
	
}
