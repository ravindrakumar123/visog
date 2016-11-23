package com.visog.jobportal.service.master;

import java.util.List;

import com.visog.jobportal.req.master.CoursesReq;
import com.visog.jobportal.res.master.CoursesRes;

public interface CoursesService {
	
	public void saveCourse(CoursesReq req);
	
	public void updateCourse(CoursesReq req,String CourseId);
	
	public List<CoursesRes> getCourses();
	
	public CoursesRes getCourse(String id);
	
	public Boolean deleteCourse(String CourseId);
	
	

}
