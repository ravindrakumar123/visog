package com.visog.jobportal.dao.master;

import java.util.List;
import com.visog.jobportal.dao.AbstractDaoI;
import com.visog.jobportal.model.master.Specilization;

public interface SpecilizationDao extends AbstractDaoI {
	
	public List<Specilization> getSpecilization();

}
