package com.visog.jobportal.dao.master;

import java.util.List;

import com.visog.jobportal.dao.AbstractDaoI;
import com.visog.jobportal.model.master.University;

public interface UniversityDao extends AbstractDaoI {
	
	public List<University> getUniversity();

}
