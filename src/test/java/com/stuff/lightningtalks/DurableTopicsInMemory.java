package com.stuff.lightningtalks;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.inject.Alternative;
import javax.inject.Named;


@Alternative
public class DurableTopicsInMemory implements DurableTopicsFacade {

	Set<Topic> setOfTopics;
	
	public DurableTopicsInMemory() {
		this(new HashSet<Topic>());
	}
	
	public DurableTopicsInMemory(Set<Topic> setOfTopics) {
		this.setOfTopics = setOfTopics;
	}

	public Object persist(Topic topic) {
		return this.setOfTopics.add(topic);
	}
	
	public Collection<Topic> list() {
		return Collections.unmodifiableCollection(this.setOfTopics);
	}

}
