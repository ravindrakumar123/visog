package com.visog.jobportal.daoimpl.master;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.visog.jobportal.dao.AbstractDao;
import com.visog.jobportal.dao.master.GenderDao;
import com.visog.jobportal.model.master.Gender;
import com.visog.jobportal.res.master.GenderRes;

public class GenderDaoImpl extends AbstractDao implements GenderDao {

	private static final Logger logger = Logger.getLogger(GenderDaoImpl.class);

	public List<Gender> getGenders() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Gender> q = cb.createQuery(Gender.class);
		Root<Gender> c = q.from(Gender.class);
		q.select(c);
		return em.createQuery(q).getResultList();
	}

	public GenderRes getGender(String id) {
		// TODO Auto-generated method stub
		return em.find(GenderRes.class, id);
	}

}
