package com.visog.jobportal.dao.master;

import java.util.List;

import com.visog.jobportal.dao.AbstractDaoI;
import com.visog.jobportal.model.master.Roles;

public interface RoleDao extends AbstractDaoI {
	
	public List<Roles> getRoles();
	
}
