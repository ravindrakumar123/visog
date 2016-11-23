package com.visog.jobportal.serviceimpl;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;

import com.visog.jobportal.dao.SuperAdminDao;
import com.visog.jobportal.service.SuperAdminService;

@Singleton
public class SuperAdminServiceImpl implements SuperAdminService {

	private static final Logger logger = Logger.getLogger(SuperAdminServiceImpl.class);

	@Inject
	private SuperAdminDao dao;

}
