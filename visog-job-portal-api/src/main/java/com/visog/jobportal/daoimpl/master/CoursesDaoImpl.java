package com.visog.jobportal.daoimpl.master;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.visog.jobportal.dao.AbstractDao;
import com.visog.jobportal.dao.master.CourseDao;
import com.visog.jobportal.model.master.Courses;

@Singleton
@Transactional
public class CoursesDaoImpl extends AbstractDao implements CourseDao{
	
	private static final Logger logger = Logger.getLogger(CoursesDaoImpl.class);

	/**
	 * This method returns the Courses data
	 */

	public List<Courses> getCourses() {
		
		CriteriaBuilder cb= em.getCriteriaBuilder();
		CriteriaQuery<Courses> q= cb.createQuery(Courses.class);
		Root<Courses> c=q.from(Courses.class);
		q.select(c);
		return em.createQuery(q).getResultList();
	}
	
	public void delete(String id){
		
		CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
		CriteriaDelete<Courses> query=criteriaBuilder.createCriteriaDelete(Courses.class);
		Root<Courses> root=query.from(Courses.class);
		query.where(root.get("id").in(id));
		em.createQuery(query).executeUpdate();
	}

}
