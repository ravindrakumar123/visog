package com.visog.jobportal.serviceimpl.master;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.visog.jobportal.dao.master.CourseDao;
import com.visog.jobportal.model.master.Courses;
import com.visog.jobportal.req.master.CoursesReq;
import com.visog.jobportal.res.master.CoursesRes;
import com.visog.jobportal.service.master.CoursesService;
import com.visog.jobportal.utils.DaoUtils;

public class CoursesServiceImpl implements CoursesService{
	
	private static final Logger logger = Logger.getLogger(CoursesServiceImpl.class);
	
	@Inject
	CourseDao dao;
	

	/**
	 * This method saves the Course
	 */
	public void saveCourse(CoursesReq req) {
		Courses courses=new Courses();
		courses.setName(req.getName());
		courses.setDescription(req.getDescription());
		
		DaoUtils.setEntityCreateAuditColumns(courses);
		dao.save(courses);
		logger.info("Course created successfully : " + courses.getId());
		
	}

	/**
	 * This method updates the Role
	 */
	public void updateCourse(CoursesReq req, String CourseId) {
		
		Courses courses=(Courses) dao.getByKey(Courses.class, CourseId);
		courses.setName(req.getName());
		courses.setDescription(req.getDescription());
		dao.update(courses);
		logger.info("Course updated successfully : " + courses.getId());
		
	}

	/**
	 * This method returns all the courses
	 */
	public List<CoursesRes> getCourses() {
		List<Courses> courses=dao.getCourses();
		List<CoursesRes> coursesList=new ArrayList<>();
		CoursesRes coursesRes=null;
		for(Courses course:courses){
			coursesRes=new CoursesRes();
			coursesRes.setId(course.getId());
			coursesRes.setName(course.getName());
			coursesRes.setDescription(course.getDescription());
			coursesList.add(coursesRes);
			
		}
		
		return coursesList;
	}

	/**
	 * This method returns Courses Details for the given course id  
	 */
	public CoursesRes getCourse(String id) {
		
		Courses course=(Courses) dao.getByKey(Courses.class, id);
		CoursesRes coursesRes=new CoursesRes();
		coursesRes.setId(course.getId());
		coursesRes.setName(course.getName());
		coursesRes.setDescription(course.getDescription());
		return coursesRes;
	}


	/**
	 * This method deletes the given course  
	 */
	public Boolean deleteCourse(String courseId) {
		return (dao.delete(Courses.class, courseId)!=0);
	}

}
