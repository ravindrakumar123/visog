package com.visog.jobportal.model.master;

import javax.persistence.Column;
import javax.persistence.Table;

import com.visog.jobportal.model.AbstractModel;

@Table(name ="GENDER")
public class Gender extends AbstractModel {

	@Column(name ="NAME")
	 private String name;
	
	
	@Column(name ="DESCRIPTION")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
