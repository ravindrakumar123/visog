package com.visog.jobportal.dao.master;

import java.util.List;

import com.visog.jobportal.dao.AbstractDaoI;
import com.visog.jobportal.model.master.Languages;

public interface LanguageDao  extends AbstractDaoI{
	
	public List<Languages> getLanguages();

}
