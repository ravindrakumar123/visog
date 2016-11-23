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
import com.visog.jobportal.req.master.SpecilizationReq;
import com.visog.jobportal.res.master.JobPortalResponse;
import com.visog.jobportal.service.master.SpecilizationService;

@Path("/master")
@Produces(MediaType.APPLICATION_JSON)
public class SpecilizationController {
	
	private static final Logger logger = Logger.getLogger(SpecilizationController.class);

	private @CookieParam("User-Identifier") String userIdentifier;

	@Inject
	private SpecilizationService service;
	
	/**
	 * This method creates the specialization
	 * 
	 * @param req
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/specilization")
	public JobPortalResponse createSpecilization(SpecilizationReq req) {
		
		service.saveSpecilization(req);
		
		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		
		jobPortalResponse.setMessage("Specilization saved succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);

		return jobPortalResponse;
		
	}
	/**
	 * This method updates the specialization
	 * 
	 * @param req
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/specilization/{specilizationId}")
	public JobPortalResponse updateSpecilization(@PathParam("specilizationId") String specilizationId, SpecilizationReq req) {
		
		service.updateSpecilization(req, specilizationId);
		
		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		jobPortalResponse.setMessage("Specilization updated succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);
		
		return jobPortalResponse;
		
	}
	
	/**
	 * This method retrieves all specializations
	 * 
	 * @return
	 */
	@GET
	@Path("/specilization")
	public JobPortalResponse getSpecilization() {
		
		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		jobPortalResponse.setData(service.getSpecilization());
		jobPortalResponse.setMessage("Specilization fetched succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);
		return jobPortalResponse;
	}
	
	/**
	 * This method retrieves a single specialization
	 * 
	 * @return
	 */
	@GET
	@Path("/specilization/{specilizationId}")
	public JobPortalResponse getSpecilization(@PathParam("specilizationId") String specilizationId) {
		
		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		
		jobPortalResponse.setData(service.getSpecilization(specilizationId));
		jobPortalResponse.setMessage("Role fetched succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);
		return jobPortalResponse;
		
	}
	/**
	 * This method delete the specialization
	 * 
	 * @return
	 */
	
	@DELETE
	@Path("/specilization/{specilizationId}")
	public JobPortalResponse deleteSpecilization(@PathParam("specilizationId") String specilizationId) {
		

		JobPortalResponse jobPortalResponse = new JobPortalResponse();

		if(service.deleteSpecilization(specilizationId)) {
			jobPortalResponse.setMessage("specilization deleted succcessfully");
			jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
			jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);
		} else {
			jobPortalResponse.setMessage("Failed to delete he specilization");
			jobPortalResponse.setStatus(Status.STATUS_FAIL);
			jobPortalResponse.setStatusCode(Status.STATUSCODE_FAIL);
		}


		return jobPortalResponse;
		
	}
	

}
