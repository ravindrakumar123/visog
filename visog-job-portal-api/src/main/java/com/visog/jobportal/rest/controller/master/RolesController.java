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
import com.visog.jobportal.req.master.RolesReq;
import com.visog.jobportal.res.master.JobPortalResponse;
import com.visog.jobportal.service.master.RolesService;

@Path("/master")
@Produces(MediaType.APPLICATION_JSON)
public class RolesController {

	private static final Logger logger = Logger.getLogger(RolesController.class);

	private @CookieParam("User-Identifier") String userIdentifier;

	@Inject
	private RolesService service;

	/**
	 * This method creates the Role
	 * 
	 * @param req
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/roles")
	public JobPortalResponse createRole(RolesReq req) {

		service.saveRole(req);

		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		jobPortalResponse.setMessage("Role saved succcessfully");
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/roles/{roleId}")
	public JobPortalResponse updateRole(@PathParam("roleId") String roleId, RolesReq req) {

		service.updateRole(req, roleId);

		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		jobPortalResponse.setMessage("Role updated succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);

		return jobPortalResponse;

	}

	/**
	 * This method retrieves all roles
	 * 
	 * @return
	 */
	@GET
	@Path("/roles")
	public JobPortalResponse getRoles() {

		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		jobPortalResponse.setData(service.getRoles());
		jobPortalResponse.setMessage("Roles fetched succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);

		return jobPortalResponse;

	}

	/**
	 * This method retrieves a single Role
	 * 
	 * @return
	 */

	@GET
	@Path("/roles/{roleId}")
	public JobPortalResponse getRole(@PathParam("roleId") String roleId) {

		JobPortalResponse jobPortalResponse = new JobPortalResponse();
		jobPortalResponse.setData(service.getRole(roleId));
		jobPortalResponse.setMessage("Role fetched succcessfully");
		jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
		jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);

		return jobPortalResponse;

	}
	
	/**
	 * This method delete the role
	 * 
	 * @return
	 */
	
	@DELETE
	@Path("/roles/{roleId}")
	public JobPortalResponse deleteRole(@PathParam("roleId") String roleId) {
		
		JobPortalResponse jobPortalResponse = new JobPortalResponse();

		if(service.deleteRole(roleId)) {
			jobPortalResponse.setMessage("Role deleted succcessfully");
			jobPortalResponse.setStatus(Status.STATUS_SUCCESS);
			jobPortalResponse.setStatusCode(Status.STATUSCODE_SUCCESS);
		} else {
			jobPortalResponse.setMessage("Failed to delete he role");
			jobPortalResponse.setStatus(Status.STATUS_FAIL);
			jobPortalResponse.setStatusCode(Status.STATUSCODE_FAIL);
		}


		return jobPortalResponse;
	
	
	}
}
