package com.stuff.lightningtalks;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DurableTopicsEntityManager implements DurableTopicsFacade {

	@PersistenceContext
	EntityManager em;
	
	public Object persist(Topic topic) {
		em.persist(topic);
		return topic;
	}

	public Collection<Topic> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
