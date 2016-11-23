package com.visog.jobportal.rest.controller;
 
import javax.inject.Inject;
import javax.ws.rs.CookieParam;
import javax.ws.rs.Path;

import org.apache.log4j.Logger;

import com.visog.jobportal.service.SuperAdminService;
 
@Path("/v1/fi")
public class SuperAdminController {
	
	private static final Logger logger = Logger.getLogger(SuperAdminController.class);
	
	private @CookieParam("User-Identifier") String userIdentifier;
	
	@Inject
	private SuperAdminService service;
 

}