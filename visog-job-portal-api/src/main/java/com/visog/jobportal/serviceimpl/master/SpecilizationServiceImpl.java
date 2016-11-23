package com.visog.jobportal.serviceimpl.master;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import com.visog.jobportal.dao.master.SpecilizationDao;
import com.visog.jobportal.model.master.Courses;
import com.visog.jobportal.model.master.Specilization;
import com.visog.jobportal.req.master.SpecilizationReq;
import com.visog.jobportal.res.master.SpecilizationRes;
import com.visog.jobportal.service.master.SpecilizationService;
import com.visog.jobportal.utils.DaoUtils;

public class SpecilizationServiceImpl implements SpecilizationService {
	
	private static final Logger logger = Logger.getLogger(SpecilizationServiceImpl.class);

	@Inject
	SpecilizationDao dao;
	/**
	 * This method saves the Specialization
	 */
	public void saveSpecilization(SpecilizationReq req) {
		
		Specilization specilizations=new Specilization();
		
		specilizations.setName(req.getName());
		specilizations.setDescription(req.getCourse());
		
		Courses courses=new Courses();
		courses.setId(req.getCourse());
		
		specilizations.setCourse(courses);
		
		DaoUtils.setEntityCreateAuditColumns(specilizations);
		dao.save(specilizations);
		logger.info("Specilization created successfully : " + specilizations.getId());
	}

	/**
	 * This method updates the Specialization
	 */
	public void updateSpecilization(SpecilizationReq req, String specilizationId) {
		
		Specilization specilizations=(Specilization) dao.getByKey(Specilization.class, specilizationId);
		
		specilizations.setName(req.getName());
		specilizations.setDescription(req.getDescription());
		Courses courses=new Courses();
		courses.setId(req.getCourse());
		
		specilizations.setCourse(courses);
		
		dao.update(specilizations);
		logger.info("Specialization updated successfully : " + specilizations.getId());
	}

	/**
	 * This method returns all the Specializations
	 */
	public List<SpecilizationRes> getSpecilization() {
		
		List<Specilization> specilizations=dao.getSpecilization();
		List<SpecilizationRes> specilizationsList=new ArrayList<>();
		SpecilizationRes specilizationRes=null;
		
		for(Specilization specilization:specilizations){
			
			specilizationRes =new SpecilizationRes();
			specilizationRes.setId(specilization.getId());
			specilizationRes.setName(specilization.getName());
			specilizationRes.setDescription(specilization.getDescription());
			specilizationRes.setCourse(specilization.getCourse().getId());
			specilizationsList.add(specilizationRes);
			
		}
		return specilizationsList;
	}

	/**
	 * This method returns Specialization Details for the given Specialization id  
	 */
	public SpecilizationRes getSpecilization(String Id) {

		Specilization specilizations=(Specilization) dao.getByKey(Specilization.class, Id);
		SpecilizationRes specilizationRes=new SpecilizationRes();
		specilizationRes.setId(specilizations.getId());
		specilizationRes.setName(specilizations.getName());
		specilizations.setDescription(specilizations.getDescription());
		specilizationRes.setCourse(specilizations.getCourse().getId());
		
		return specilizationRes;
	}

	/**
	 * This method deletes the given role  
	 */
	public Boolean deleteSpecilization(String specilizationId) {
		return (dao.delete(Specilization.class, specilizationId) !=0);
	}


}
