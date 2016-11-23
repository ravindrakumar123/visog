package com.visog.jobportal.serviceimpl;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;

import com.visog.jobportal.dao.AdminDao;
import com.visog.jobportal.service.AdminService;

@Singleton
public class AdminServiceImpl implements AdminService {

	private static final Logger logger = Logger.getLogger(AdminServiceImpl.class);

	@Inject
	AdminDao dao;

}
