package com.stuff.lightningtalks;

import java.util.Collection;
import java.util.Date;

public interface DurableTopicsFacade {

	public Object persist(Topic topic);
	public boolean notUnique(String topic);
	public Collection<Topic> list(Date talkDate);
	public Collection<Topic> matches(String topic);
	
}
