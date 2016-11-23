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
import com.visog.jobportal.dao.master.LanguageDao;
import com.visog.jobportal.model.master.Languages;

@Singleton
@Transactional
public class LanguagesDaoImpl extends AbstractDao implements LanguageDao{
	
	private static final Logger logger = Logger.getLogger(RolesDaoImpl.class);

	/**
	 * This method returns the Roles data
	 *@Author=ravi
	 */
	public List<Languages> getLanguages() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Languages> q=cb.createQuery(Languages.class);
		Root<Languages> c=q.from(Languages.class);
		q.select(c);
		
		return em.createQuery(q).getResultList();
	}
	
	public void delete(String id) {
		CriteriaBuilder criteriaBuilder  = em.getCriteriaBuilder();
		CriteriaDelete<Languages> query= criteriaBuilder.createCriteriaDelete(Languages.class);
		Root<Languages> root=query.from(Languages.class);
		query.where(root.get("id").in(id));
		
		em.createQuery(query).executeUpdate();
	}

}
