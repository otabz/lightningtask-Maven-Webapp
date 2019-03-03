package com.stuff.lightningtalks;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DurableTopicsEntityManager implements DurableTopicsFacade {

	@PersistenceContext
	EntityManager em;
	
	public Object persist(Topic topic) {
		em.persist(topic);
		return topic;
	}

	public Collection<Topic> list() {
		Query q = em.createNamedQuery("Topic.All", Topic.class);
		return q.getResultList();
	}

	public boolean notUnique(String topic) {
		if(null != em.find(Topic.class, topic)) {
			return true;
		}
		return false;
	}

}
