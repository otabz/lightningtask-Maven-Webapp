package com.stuff.lightningtalks;

import java.util.Collection;

public interface DurableTopicsFacade {

	public Object persist(Topic topic);
	public Collection<Topic> list();
	
}
