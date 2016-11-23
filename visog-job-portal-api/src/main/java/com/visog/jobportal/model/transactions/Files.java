package com.visog.jobportal.model.transactions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "FILES")
@Entity
public class Files {
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="FILE_PATH")
	private String filepath;
	
	
	@Column(name="FILE_TYPE_ID")
	private String filetypeid;
	
	@Column(name="ASSOCIATED_TYPE")
	private String associatedtype;
	
	@Column(name="ASSOCIATED_ID")
	private String associatedid;


}
