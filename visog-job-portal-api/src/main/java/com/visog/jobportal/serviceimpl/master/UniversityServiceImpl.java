package com.visog.jobportal.serviceimpl.master;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.visog.jobportal.dao.master.UniversityDao;
import com.visog.jobportal.model.master.University;
import com.visog.jobportal.req.master.UniversityReq;
import com.visog.jobportal.res.master.UniversityRes;
import com.visog.jobportal.service.master.UniversityService;
import com.visog.jobportal.utils.DaoUtils;

public class UniversityServiceImpl  implements UniversityService{
	
	private static final Logger logger = Logger.getLogger(UniversityServiceImpl.class);

	@Inject
	UniversityDao dao;
	
	/**
	 * This method saves the University
	 */
	public void saveUniversity(UniversityReq req) {
		
		University universities=new University();
		universities.setName(req.getName());
		universities.setDescription(req.getDescription());
		
		DaoUtils.setEntityCreateAuditColumns(universities);
		dao.save(universities);
		logger.info("University created successfully : " + universities.getId());
	}
	/**
	 * This method updates the University
	 */

	public void updateUniversity(UniversityReq req, String universityId) {
		
		University universities=(University) dao.getByKey(University.class, universityId);
		
		universities.setName(req.getName());
		universities.setDescription(req.getDescription());
	//	logger.info("name::"+universities.getName());
		//logger.info("desc::"+universities.getDescription());
		dao.update(universities);
		
		logger.info("University updated successfully : " + universities.getId());
	}
	/**
	 * This method returns all the universities
	 */
	public List<UniversityRes> getUniversity() {
		
		List<University> universities=dao.getUniversity();
		List<UniversityRes> universitiesList= new ArrayList<>();
		UniversityRes universityRes=null;
		for(University university :universities){
			
			universityRes=new UniversityRes();
			universityRes.setId(university.getId());
			universityRes.setName(university.getName());
			universityRes.setDescription(university.getDescription());
			universitiesList.add(universityRes);
			
		}
		return universitiesList;
	}
	/**
	 * This method returns University Details for the given university id  
	 */
	public UniversityRes getUniversity(String id) {

		University university=(University) dao.getByKey(University.class, id);
		UniversityRes universityRes=new UniversityRes();
		
		universityRes.setId(university.getId());
		universityRes.setName(university.getName());
		universityRes.setDescription(university.getDescription());
		
		return universityRes;
	}

	/**
	 * This method deletes the given university
	 */
	public Boolean deleteUniversity(String universityId) {
		return (dao.delete(University.class, universityId)!=0);
	}
	

}
