package com.visog.jobportal.rest.controller.master;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import com.visog.jobportal.constants.Status;
import com.visog.jobportal.req.master.CoursesReq;
import com.visog.jobportal.res.master.JobPortalResponse;
import com.visog.jobportal.service.master.CoursesService;

@Path("/master")
@Produces(MediaType.APPLICATION_JSON)
public class CoursesController {
	

	private static final Logger logger = Logger.getLogger(CoursesController.class);

	private @CookieParam("User-Identifier") String userIdentifier;

	@Inject
	private CoursesService service;

	/**
	 * This method creates the Course
	 * 
	 * @param req
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/courses")
	public JobPortalResponse createCourse(CoursesReq req){
		
		service.saveCourse(req);
		
		JobPortalResponse jobPortalResponse=new JobPortalResponse();
		jobPortalResponse.setMessage("Course saved succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);
		
		return jobPortalResponse;
	}
	
	/**
	 * This method updates the Course
	 * 
	 * @param req
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/courses/{courseId}")
	public JobPortalResponse updateCourse(@PathParam("courseId") String courseId, CoursesReq req) {
		service.updateCourse(req, courseId);
		
		JobPortalResponse jobPortalResponse=new JobPortalResponse();
		jobPortalResponse.setMessage("Course updated succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);
		
		return jobPortalResponse;
		
	}
	
	/**
	 * This method retrieves all Courses
	 * 
	 * @return
	 */
	@GET
	@Path("/courses")
	public JobPortalResponse getCourses() {
		
		JobPortalResponse jobPortalResponse=new JobPortalResponse();
		
		jobPortalResponse.setData(service.getCourses());
		jobPortalResponse.setMessage("Course fetched succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);
		
		return jobPortalResponse;
	}

	/**
	 * This method retrieves a single Course
	 * 
	 * @return
	 */
	@GET
	@Path("/courses/{courseId}")
	public JobPortalResponse getCourse(@PathParam("courseId") String courseId) {
		
		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		
		jobPortalResponse.setData(service.getCourse(courseId));
		jobPortalResponse.setMessage("Course fetched succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);
		
		return jobPortalResponse;
		
	}
	
	/**
	 * This method delete the course
	 * 
	 * @return
	 */	
	@DELETE
	@Path("/courses/{courseId}")
	public JobPortalResponse deleteCourse(@PathParam("courseId") String courseId) {
		
		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		if(service.deleteCourse(courseId)){
			
			jobPortalResponse.setMessage("course deleted succcessfully");
			jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
			jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);
		}else {
			
			jobPortalResponse.setMessage("Failed to delete he course");
			jobPortalResponse.setStatus(Status.STATUS_FAIL);
			jobPortalResponse.setStatusCode(Status.STATUSCODE_FAIL);
		}
		
		return jobPortalResponse;
	}


}
