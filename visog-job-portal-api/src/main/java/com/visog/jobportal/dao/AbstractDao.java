package com.visog.jobportal.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractDao implements AbstractDaoI {
	
	@PersistenceContext(unitName = "VisogJobPortal")//
	protected EntityManager em;
	
	public Object getByKey(Class persistentClass, String key) {
		return em.find(persistentClass, key);
	}

	public void save(Object entity) {
		em.persist(entity);
	}
	
	public void update(Object entity) {
		em.merge(entity);
	}

	public Integer delete(Class persistentClass, String key) {
		Query query = em.createQuery("DELETE FROM "+persistentClass.getSimpleName()+" WHERE id = :id");
		return query.setParameter("id", key).executeUpdate();
	}

}
