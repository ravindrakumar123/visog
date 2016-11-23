package com.visog.jobportal.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.visog.jobportal.utils.PropertyUtil;

public class ContextListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(ContextListener.class);
	
	/**
	 * This method will be executed at the time of this application deployment
	 */
	public void contextInitialized(ServletContextEvent sce) {

		logger.info(" Start: Calling Job Portal ContextListener.contextInitialized() method");
		
		logger.error("LOG TEST Job Portal ===========================START");
		
		logger.trace("TEST TRACE");
		logger.debug("TEST DEBUG");
		logger.info("TEST INFO");
		logger.warn("TEST WARN");
		logger.error("TEST ERROR");
		logger.fatal("TEST FATAL");
	
		logger.error("LOG TEST Job Portal ===========================END");
		
		PropertyUtil.setProperties();

		logger.info("jobportal.properties was loaded successfully");


		logger.info(" End: Calling Job Portal ContextListener.contextInitialized() method");
	}

	/**
	 * This method will be executed at the time of un-deployment of the server
	 * 
	 * @param sce
	 */
	public void contextDestroyed(ServletContextEvent sce) {

		logger.info(" Start: Calling Job Portal ContextListener.contextDestroyed() method");
		
		logger.info(" End: Calling Job Portal ContextListener.contextDestroyed() method");
	}

}
