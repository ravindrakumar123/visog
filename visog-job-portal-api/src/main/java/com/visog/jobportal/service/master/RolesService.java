package com.visog.jobportal.service.master;

import java.util.List;

import com.visog.jobportal.req.master.RolesReq;
import com.visog.jobportal.res.master.RolesRes;

public interface RolesService {
	
	public void saveRole(RolesReq req);
	
	public void updateRole(RolesReq req, String roleId);
	
	public List<RolesRes> getRoles();
	
	public RolesRes getRole(String id);
	
	public Boolean deleteRole(String roleId);
	
}
