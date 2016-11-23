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
import com.visog.jobportal.dao.master.UniversityDao;
import com.visog.jobportal.model.master.University;

@Singleton
@Transactional
public class UniversityDaoImpl extends AbstractDao implements UniversityDao {
	
	private static final Logger logger = Logger.getLogger(UniversityDaoImpl.class);

	/**
	 * This method returns the University data
	 * @Author=ravi
	*/
	public List<University> getUniversity() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<University> q=cb.createQuery(University.class);
		Root<University> c=q.from(University.class);
		q.select(c);

		return em.createQuery(q).getResultList();
	}
	
	
	public void delete(String id){
		CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
		CriteriaDelete<University> query=criteriaBuilder.createCriteriaDelete(University.class);
		Root<University> root=query.from(University.class);
		query.where(root.get("id").in(id));
		
		em.createQuery(query).executeUpdate();
	}
	
	

}
