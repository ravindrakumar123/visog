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
import com.visog.jobportal.dao.master.SpecilizationDao;
import com.visog.jobportal.model.master.Specilization;

@Singleton
@Transactional
public class SpecilizationDaoImpl  extends AbstractDao implements SpecilizationDao{

	private static final Logger logger = Logger.getLogger(SpecilizationDaoImpl.class);

	/**
	 * This method returns the Specialization data
	 *@Author=ravi
	 */
	public List<Specilization> getSpecilization() {
		
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Specilization> q=cb.createQuery(Specilization.class);
		Root<Specilization> c=q.from(Specilization.class);
		q.select(c);
		
		return em.createQuery(q).getResultList();
	}
	
	public void delete(String id){
		
		CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
		CriteriaDelete<Specilization> query=criteriaBuilder.createCriteriaDelete(Specilization.class);
		Root<Specilization> root=query.from(Specilization.class);
		query.where(root.get("id").in(id));
		em.createQuery(query).executeUpdate();
	}
	
	

}
