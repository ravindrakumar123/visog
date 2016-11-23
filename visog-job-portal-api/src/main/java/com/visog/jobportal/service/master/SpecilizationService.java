package com.visog.jobportal.service.master;

import java.util.List;

import com.visog.jobportal.req.master.SpecilizationReq;
import com.visog.jobportal.res.master.SpecilizationRes;

public interface SpecilizationService {
	
	public void saveSpecilization(SpecilizationReq req);
	
	public void updateSpecilization(SpecilizationReq req, String specilizationId);
	
	public List<SpecilizationRes> getSpecilization();
	
	public SpecilizationRes getSpecilization(String Id);
	
	public Boolean deleteSpecilization(String specilizationId);

}
