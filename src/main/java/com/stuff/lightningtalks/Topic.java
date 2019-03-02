package com.stuff.lightningtalks;

import java.util.Calendar;
import java.util.Date;

//entity
//be careful while creating entities from schema
//business logic must not overwrite
public class Topic {

	private String subject;
	private String description;
	private String userId;
	private Date time;
	private Date talkDate;
	private String ipAddress;
	private String hostName;
	private String userAgent;

	// requirements of a valid object must be declared while constructing it
	public Topic() {
	}

	public Topic(String subject) {
		this(subject, "", "", null);
	}

	public Topic(String subject, String description) {
		this(subject, description, "", null);
	}

	public Topic(String subject, String description, String userId) {
		this(subject, description, userId, null);
	}

	public Topic(String subject, String description, String userId, Date time) {
		this.subject = subject;
		this.description = description;
		this.userId = userId;
		Calendar cal = Calendar.getInstance();
		cal.set(1984, 9, 6);
		this.time = time == null ? cal.getTime() : time;
		this.talkDate = calculateTalkDate();
	}
	
	public Date calculateTalkDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.time);
		// year boundary case
		int submissionMonth = cal.get(Calendar.MONTH);
		int calculatedMonth = calculateMonth(cal);
		if(submissionMonth == 11 
				&& (cal.get(Calendar.MONTH) != calculatedMonth)) {
			cal.add(Calendar.YEAR, 1);
		}
		// month
		cal.set(Calendar.MONTH, calculatedMonth);
		// first tuesday
		Calendar calFromDay1 = Calendar.getInstance();
		calFromDay1.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
		cal.set(Calendar.DATE, calculateFirstTuesday(calFromDay1));
		return cal.getTime();
	}
	
	public int calculateFirstTuesday(Calendar cal) {
		int day = -1;
		if(cal.get(Calendar.DAY_OF_WEEK) > 3) {
			day = 1 + 3 + (7 - cal.get(Calendar.DAY_OF_WEEK));
		} else {
			day = 1 + (3 - cal.get(Calendar.DAY_OF_WEEK));
		}
		return day;
//		date + (3 + (7-7))
//		date + (3 + (7-6))
//		date + (3 + (7-5))
//		date + (3 + (7-4))
	}
	
	public int calculateMonth(Calendar cal) {
		int month = cal.get(Calendar.MONTH) + 1;
		if(month%2 != 0) {
			cal.add(Calendar.MONTH, 1);
		} else {
			int date = cal.get(Calendar.DATE);
			Calendar calFromDay1 = Calendar.getInstance();
			calFromDay1.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
			int firstTuesday = calculateFirstTuesday(calFromDay1);
			
			if(date >= firstTuesday)
			cal.add(Calendar.MONTH, 2);
		}
		return cal.get(Calendar.MONTH);
	}

	@Override
	public boolean equals(Object other) {
		if (null == other) {
			return false;
		} else if (!(other instanceof Topic)) {
			return false;
		} else if (!((Topic) other).subject.equals(this.subject)) {
			return false;
		} else if (!((Topic) other).description.equals(this.description)) {
			return false;
		} else if (!((Topic) other).userId.equals(this.userId)) {
			return false;
		} else if (((Topic) other).time.compareTo(this.time) != 0) {
			return false;
		} else if(((Topic) other).talkDate.compareTo(this.talkDate) != 0) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return this.subject.hashCode() / 11 + this.description.hashCode() / 33
				+ this.userId.hashCode() + (int) this.time.getTime()
				+ (int) this.time.getTime();
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

}
