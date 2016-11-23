package com.visog.jobportal.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.visog.jobportal.rest.controller.AdminController;
import com.visog.jobportal.rest.controller.SuperAdminController;
import com.visog.jobportal.rest.controller.master.CoursesController;
import com.visog.jobportal.rest.controller.master.RolesController;
import com.visog.jobportal.rest.controller.master.SpecilizationController;
import com.visog.jobportal.rest.controller.master.UniversityController;


public class JobPortalRestApplication extends Application {
	
	private Set<Object> singletons = new HashSet<>();
	private Set<Class<?>> classes = new HashSet<>();

	public JobPortalRestApplication() {
		classes.add(AdminController.class);
		classes.add(SuperAdminController.class);
		classes.add(RolesController.class);
		classes.add(CoursesController.class);
		classes.add(UniversityController.class);
		classes.add(SpecilizationController.class);
	}

	public Set<Object> getSingletons() {
		return singletons;
	}

	public Set<Class<?>> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class<?>> classes) {
		this.classes = classes;
	}
}