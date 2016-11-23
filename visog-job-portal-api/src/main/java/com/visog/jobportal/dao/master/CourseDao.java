package com.visog.jobportal.dao.master;

import java.util.List;

import com.visog.jobportal.dao.AbstractDaoI;
import com.visog.jobportal.model.master.Courses;

public interface CourseDao extends AbstractDaoI{
	
	public List<Courses> getCourses();

}
