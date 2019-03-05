package com.stuff.lightningtalks;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.query.dsl.QueryBuilder;

@Stateless
public class DurableTopicsEntityManager implements DurableTopicsFacade {
	
	private final static float TOPIC_UNIQUENESS_MATCH = 0.5f;

	@PersistenceContext
	EntityManager em;
	
	public Object persist(Topic topic) {
		em.persist(topic);
		return topic;
	}

	public Collection<Topic> list(Date talkDate) {
		Query q = em.createNamedQuery("Topic.All", Topic.class)
				.setParameter("talk", talkDate);
		return q.getResultList();
	}

	public boolean notUnique(String topic) {
		List matches = (List) matches(topic);
		if(matches == null || matches.isEmpty()) {
			return true;
		} else {
			Object[] match = (Object[])matches.get(0);
			Float score =  (Float) match[0];
			if(score > TOPIC_UNIQUENESS_MATCH){
				return true;
			}
		}
		return false;
	}
	
	private FullTextSession createFullTextSession() {
		Session session = em.unwrap(Session.class);
		FullTextSession fullTextSession = org.hibernate.search.Search.getFullTextSession(session);
		return fullTextSession;
	}
	
	private QueryBuilder createBuilder(FullTextSession session,
			Class<?> forEntity) {
		QueryBuilder builder = session.getSearchFactory().buildQueryBuilder()
				.forEntity(forEntity).get();
		return builder;
	}

	public Collection<Topic> matches(String topic) {
		FullTextSession fullTextSession = createFullTextSession();
		QueryBuilder builder = createBuilder(fullTextSession, Topic.class);
		String fieldName = "subject_";

		org.apache.lucene.search.Query query = 
				builder.keyword().onField(fieldName)
				   .matching(topic).createQuery();
		
		FullTextQuery hibQuery = fullTextSession.createFullTextQuery(
			    query, Topic.class);
		
		hibQuery.setFirstResult(0).setMaxResults(1);
		
		hibQuery.setProjection(
			    FullTextQuery.SCORE,
			    FullTextQuery.THIS,
			    "mainAuthor.name" );
		
		return hibQuery.list();
	}

}
