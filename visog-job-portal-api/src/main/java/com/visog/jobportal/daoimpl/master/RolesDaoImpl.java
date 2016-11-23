/*
@Author=ravi
*/

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
import com.visog.jobportal.dao.master.RoleDao;
import com.visog.jobportal.model.master.Roles;


@Singleton
@Transactional
public class RolesDaoImpl extends AbstractDao implements RoleDao {

	private static final Logger logger = Logger.getLogger(RolesDaoImpl.class);

	/**
	 * This method returns the Roles data
	 *@Author=ravi
	 */
	public List<Roles> getRoles() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Roles> q = cb.createQuery(Roles.class);
		Root<Roles> c = q.from(Roles.class);
		q.select(c);
		return em.createQuery(q).getResultList();
		
		
	}

	public void delete(String id) {
		CriteriaBuilder criteriaBuilder  = em.getCriteriaBuilder();
		CriteriaDelete<Roles> query = criteriaBuilder.createCriteriaDelete(Roles.class);
		Root<Roles> root = query.from(Roles.class);
		query.where(root.get("id").in(id));

		em.createQuery(query).executeUpdate();
		
	}
	
	

}
