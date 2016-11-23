package com.visog.jobportal.rest.controller.master;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.visog.jobportal.constants.Status;
import com.visog.jobportal.req.master.GenderReq;
import com.visog.jobportal.req.master.RolesReq;
import com.visog.jobportal.res.master.JobPortalResponse;
import com.visog.jobportal.service.master.GenderService;

@Path("/master")
@Produces(MediaType.APPLICATION_JSON)
public class GenderController {

	private static final Logger logger = Logger.getLogger(RolesController.class);

	private @CookieParam("User-Identifier") String userIdentifier;

	@Inject
	private GenderService service;

	/**
	 * This method creates the Gender
	 * 
	 * @param req
	 * @return
	 */

	@POST
	@Consumes("application/json")
	@Path("/gender")
	public JobPortalResponse createGender(GenderReq req) {

		service.saveGender(req);

		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		jobPortalResponse.setMessage("Gender saved succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);

		return jobPortalResponse;
	}

	/**
	 * This method updates the Role
	 * 
	 * @param req
	 * @return
	 */
	@PUT
	@Consumes("application/json")
	@Path("/gender/{genderId}")
	public JobPortalResponse updateGender(@PathParam("id") String genderId, GenderReq req) {

		service.updateGender(req, genderId);

		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		jobPortalResponse.setMessage("Gender updated succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);

		return jobPortalResponse;

	}

	/**
	 * This method retrieves all gender
	 * 
	 * @return
	 */
	@GET
	@Consumes("application/json")
	@Path("/gender")
	public JobPortalResponse getGenders() {

		service.getGenders();

		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		jobPortalResponse.setMessage("Gender fetched succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);

		return jobPortalResponse;

	}

	/**
	 * This method retrieves a single Gender
	 * 
	 * @return
	 */

	@GET
	@Produces("application/json")
	@Path("/gender/{genderId}")
	public JobPortalResponse getGender(String id) {

		service.getGender(id);

		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		jobPortalResponse.setMessage("Gender fetched succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);

		return jobPortalResponse;

	}

}
