package com.stuff.lightningtalks;

public class BodyParams {
	public static final int TOPIC_LENGTH = 80;
	public static final int DESCRIPTION_LENGTH = 120;
	public static final int EMAIL_LENGTH = 255;
	public static final String PARAM_ERROR = "{\"error\": \" %s shouldn't be empty and <= %d\"}";
	
	private String subject;
	private String description;
	private String email;
	private String error;
	
	public boolean validate() {
		if(!(notNull(this.subject)
		&& notEmpty(this.subject)
		&& withinCharacterLimit(this.subject,
				TOPIC_LENGTH))) {
			this.error = String.format(PARAM_ERROR, "topic", TOPIC_LENGTH);
			return false;
		}
		if(!(notNull(this.description)
				&& notEmpty(this.description)
				&& withinCharacterLimit(this.description,
						DESCRIPTION_LENGTH))) {
			this.error = String.format(PARAM_ERROR, "description", DESCRIPTION_LENGTH);
			return false;
		}
		if(!(notNull(this.email)
				&& notEmpty(this.email)
				&& withinCharacterLimit(this.email,
						EMAIL_LENGTH))) {
			this.error = String.format(PARAM_ERROR, "email", EMAIL_LENGTH);
			return false;
		}
		return true;
	}
	
	public boolean notNull(String value) {
		if(null != value) {
			return true;
		}
		return false;
	}
	
	public boolean notEmpty(String value) {
		if(!value.trim().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean withinCharacterLimit(String value, int length) {
		if(value.length() <= length) {
			return true;
		}
		return false;
	}
	

	public String getSubject() {
		return subject;
	}

	public String getDescription() {
		return description;
	}

	public String getEmail() {
		return email;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getError() {
		return this.error;
	}
}
