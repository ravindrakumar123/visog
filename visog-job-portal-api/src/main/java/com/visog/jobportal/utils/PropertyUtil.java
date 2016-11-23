package com.visog.jobportal.utils;


import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyUtil {

	private static Logger logger = Logger.getLogger("PropertyUtil");
	
	private static Properties properties;

	public static void setProperties() {
		try {
			Properties props = new Properties();
			ClassLoader classLoader = PropertyUtil.class.getClassLoader();
			props.load(classLoader.getResourceAsStream("jobportal.properties"));
			properties = props;

		} catch (IOException e) {
			logger.error("Error in loading properties", e);
		}
		
	}
	
	public static String getProperty(String propertyName) {
		
		return properties.getProperty(propertyName);
		
	}

}
