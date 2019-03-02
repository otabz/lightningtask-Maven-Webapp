package com.stuff.lightningtalks;

public class TopicValidator {
	
	public static boolean notNull(String value) {
		if(null != value) {
			return true;
		}
		return false;
	}
	
	public static boolean notEmpty(String value) {
		if(!value.trim().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static boolean withinCharacterLimit(String value, int length) {
		System.out.println(value.length());
		if(value.length() <= length) {
			return true;
		}
		return false;
	}
	
}
