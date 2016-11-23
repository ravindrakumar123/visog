package com.visog.jobportal.rest.controller;
 
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.visog.jobportal.constants.Status;
import com.visog.jobportal.res.master.JobPortalResponse;
import com.visog.jobportal.service.AdminService;
import com.visog.jobportal.utils.ParamUtils;
 
@Path("/admin")
public class AdminController {
	
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	private @CookieParam("User-Identifier") String userIdentifier;
	
	@Inject
	private AdminService service;
 
	/**
	 * This method creates the user for the given institution
	 * 
	 * @param institutionId
	 * @param userReq
	 * @return
	 */
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("test/{id}")
	public JobPortalResponse createUser(@PathParam("id") String instStr) {
		

		Long institutionId = ParamUtils.getLongParam(instStr, userIdentifier);
		
		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		jobPortalResponse.setMessage("test message");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);
		jobPortalResponse.setData("Hai");
		
		return jobPortalResponse;
	}	

}