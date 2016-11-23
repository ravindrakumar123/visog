package com.visog.jobportal.service.master;

import java.util.List;

import com.visog.jobportal.req.master.LanguagesReq;
import com.visog.jobportal.res.master.LanguagesRes;

public interface LanguagesService {
	
    public void saveLanguages(LanguagesReq req);
	
	public void updateLanguages(LanguagesReq req, String languageId);
	
	public List<LanguagesReq> getRoles();
	
	public LanguagesRes getLanguages(String id);
	
	public Boolean deleteLanguages(String languageId);

}
