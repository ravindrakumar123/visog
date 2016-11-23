package com.visog.jobportal.serviceimpl.master;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.visog.jobportal.dao.master.RoleDao;
import com.visog.jobportal.model.master.Roles;
import com.visog.jobportal.req.master.RolesReq;
import com.visog.jobportal.res.master.RolesRes;
import com.visog.jobportal.service.master.RolesService;
import com.visog.jobportal.utils.DaoUtils;

public class RolesSeviceImpl implements RolesService {

	private static final Logger logger = Logger.getLogger(RolesSeviceImpl.class);

	@Inject
	RoleDao dao;

	/**
	 * This method saves the Role
	 */
	public void saveRole(RolesReq req) {
		Roles roles = new Roles();
		roles.setName(req.getName());
		roles.setDescription(req.getDescription());

		DaoUtils.setEntityCreateAuditColumns(roles);
		
		dao.save(roles);

		logger.info("Role created successfully : " + roles.getId());
	}

	/**
	 * This method updates the Role
	 */
	public void updateRole(RolesReq req, String roleId) {
		Roles roles = (Roles) dao.getByKey(Roles.class, roleId);
		roles.setName(req.getName());
		roles.setDescription(req.getDescription());
		dao.update(roles);
		logger.info("Role updated successfully : " + roles.getId());
	}

	/**
	 * This method returns all the roles
	 */
	public List<RolesRes> getRoles() {

		List<Roles> roles = dao.getRoles();

		List<RolesRes> rolesList = new ArrayList<>();
		RolesRes rolesRes = null;

		for (Roles role : roles) {
			rolesRes = new RolesRes();
			rolesRes.setId(role.getId());
			rolesRes.setName(role.getName());
			rolesRes.setDescription(role.getDescription());
			rolesList.add(rolesRes);
		}

		return rolesList;
	}

	/**
	 * This method returns Role Details for the given role id  
	 */
	public RolesRes getRole(String id) {
		Roles role = (Roles) dao.getByKey(Roles.class, id);
		RolesRes rolesRes = new RolesRes();
		rolesRes.setId(role.getId());
		rolesRes.setName(role.getName());
		rolesRes.setDescription(role.getDescription());
		return rolesRes;
	}

	/**
	 * This method deletes the given role  
	 */
	public Boolean deleteRole(String roleId) {
		return (dao.delete(Roles.class, roleId) != 0);
		
	}

}
