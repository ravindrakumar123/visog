package com.visog.jobportal.serviceimpl.master;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.visog.jobportal.dao.master.GenderDao;
import com.visog.jobportal.model.master.Gender;
import com.visog.jobportal.model.master.Roles;
import com.visog.jobportal.req.master.GenderReq;
import com.visog.jobportal.res.master.GenderRes;
import com.visog.jobportal.res.master.RolesRes;
import com.visog.jobportal.service.master.GenderService;

public class GenderServiceImpl implements GenderService {

	private static final Logger logger = Logger.getLogger(GenderServiceImpl.class);

	@Inject
	GenderDao dao;

	/**
	 * This method saves the gender
	 *
	 */

	public void saveGender(GenderReq req) {

		Gender gender = new Gender();
		gender.setName(req.getName());
		gender.setDescription(req.getDescription());
		dao.save(gender);
		logger.info("Gender created successfully : " + gender.getId());
	}

	/**
	 * This method updates the Gender
	 */

	public void updateGender(GenderReq req, String genderId) {

		Gender gender = (Gender) dao.getByKey(Gender.class, genderId);
		gender.setName(req.getName());
		gender.setDescription(req.getDescription());
		dao.update(gender);
		logger.info("Gender updated successfully : " + gender.getId());

	}

	/**
	 * This method returns all the genders
	 */

	public List<GenderRes> getGenders() {

		List<Gender> genders = dao.getGenders();

		List<GenderRes> genderList = new ArrayList<>();
		GenderRes genderRes = null;

		for (Gender gender : genders) {
			genderRes = new GenderRes();
			genderRes.setId(gender.getId());
			genderList.add(genderRes);
		}

		return genderList;

	}

	/**
	 * This method returns a gender
	 */
	public GenderRes getGender(String id) {
	
		return dao.getGender(id);
	}

}
