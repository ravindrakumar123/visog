package com.visog.jobportal.dao.master;

import java.util.List;

import com.visog.jobportal.dao.AbstractDaoI;
import com.visog.jobportal.model.master.Gender;
import com.visog.jobportal.res.master.GenderRes;

public interface GenderDao extends AbstractDaoI {

	public List<Gender> getGenders();

	public GenderRes getGender(String id);

}
