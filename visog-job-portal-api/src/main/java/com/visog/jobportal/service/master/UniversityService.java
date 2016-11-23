package com.visog.jobportal.service.master;

import java.util.List;

import com.visog.jobportal.req.master.UniversityReq;
import com.visog.jobportal.res.master.UniversityRes;

public interface UniversityService {
	
	public void saveUniversity(UniversityReq req);
	
	public void updateUniversity(UniversityReq req , String  universityId);
	
	public List<UniversityRes> getUniversity();
	
	public UniversityRes getUniversity(String id);
	
	public Boolean deleteUniversity(String universityId);

}
