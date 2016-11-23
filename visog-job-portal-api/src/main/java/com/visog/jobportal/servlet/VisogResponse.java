package com.visog.jobportal.servlet;

import org.apache.log4j.Logger;

/**
 * This is the Response Class for all Visog Responses
 * @author sguggilla
 *
 */
public class VisogResponse {

	private static final Logger logger = Logger.getLogger(VisogResponse.class);
	
	private final Object data;
	
	public VisogResponse(Object data, String key) {
		
		//this.data = SecurityUtils.encodeJSON(key, new JSONObject(new GsonBuilder().create().toJson(data)));
		this.data = data;
		
	}

	public Object getData() {
		return data;
	}

}