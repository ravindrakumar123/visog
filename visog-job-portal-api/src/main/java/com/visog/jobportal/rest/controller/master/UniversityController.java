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
import com.visog.jobportal.req.master.UniversityReq;
import com.visog.jobportal.res.master.JobPortalResponse;
import com.visog.jobportal.service.master.UniversityService;

@Path("/master")
@Produces(MediaType.APPLICATION_JSON)
public class UniversityController {
	
	private static final Logger logger = Logger.getLogger(UniversityController.class);

	private @CookieParam("User-Identifier") String userIdentifier;

	@Inject
	private UniversityService service;

	/**
	 * This method creates the University
	 * 
	 * @param req
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/universities")
	public  JobPortalResponse createUniversity(UniversityReq req){
		
		service.saveUniversity(req);
		JobPortalResponse  jobPortalResponse = new JobPortalResponse();
		jobPortalResponse.setMessage("University saved succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);
		
		return jobPortalResponse;
		
	}
	/**
	 * This method updates the University
	 * 
	 * @param req
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/universities/{universityId}")
	public JobPortalResponse updateUniversity(@PathParam("universityId") String universityId, UniversityReq req) {
		
		service.updateUniversity(req, universityId);

		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		jobPortalResponse.setMessage("University updated succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);
		
		return jobPortalResponse;
	}
	/**
	 * This method retrieves all University
	 * 
	 * @return
	 */
	@GET
	@Path("/universities")
	public JobPortalResponse getUniversity() {
		
		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		
		jobPortalResponse.setData(service.getUniversity());
		jobPortalResponse.setMessage("University fetched succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);
		
		return jobPortalResponse;
		
	}
	
	
	/**
	 * This method retrieves a single University
	 * 
	 * @return
	 */

	@GET
	@Path("/universities/{universityId}")
	public JobPortalResponse getUniversity(@PathParam("universityId") String universityId) {
		
		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		jobPortalResponse.setData(service.getUniversity(universityId));
		jobPortalResponse.setMessage("University fetched succcessfully");
		
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);

		return jobPortalResponse;
		
	}
	/**
	 * This method delete the University
	 * 
	 * @return
	 */
	
	@DELETE
	@Path("/universities/{universityId}")
	public JobPortalResponse deleteUniversity(@PathParam("universityId") String universityId) {
		
		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		if(service.deleteUniversity(universityId)){
			jobPortalResponse.setMessage("University deleted succcessfully");
			jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
			jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);
		}else {
			jobPortalResponse.setMessage(" Failed to delete he role");
			jobPortalResponse.setStatus(Status.STATUS_FAIL);
			jobPortalResponse.setStatusCode(Status.STATUSCODE_FAIL);
		}
		
		return jobPortalResponse;
		
	}
		
}
