package com.visog.jobportal.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {
	
	public static String getPrintStackTraceString(Exception ex) {
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
	

}
