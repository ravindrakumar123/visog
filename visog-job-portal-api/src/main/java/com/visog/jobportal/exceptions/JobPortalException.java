package com.visog.jobportal.exceptions;


public class JobPortalException extends RuntimeException  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String erroMsg;
	
	private Throwable e;
	
	public JobPortalException(String erroMsg) {
		this.erroMsg = erroMsg;
	}

	public JobPortalException(Throwable e) {
		this.e = e;
	}

	public String getErroMsg() {
		return erroMsg;
	}

	public void setErroMsg(String erroMsg) {
		this.erroMsg = erroMsg;
	}

	public Throwable getE() {
		return e;
	}

	public void setE(Throwable e) {
		this.e = e;
	}
	
	

}
