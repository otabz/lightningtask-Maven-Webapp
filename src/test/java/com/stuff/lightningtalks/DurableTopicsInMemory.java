package com.stuff.lightningtalks;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;


public class DurableTopicsInMemory implements DurableTopicsFacade {

	Set<Topic> setOfTopics;
	
	public DurableTopicsInMemory(Set<Topic> setOfTopics) {
		this.setOfTopics = setOfTopics;
	}

	public boolean persist(Topic topic) {
		return this.setOfTopics.add(topic);
	}
	
	public Collection<Topic> list() {
		return Collections.unmodifiableCollection(this.setOfTopics);
	}

}
