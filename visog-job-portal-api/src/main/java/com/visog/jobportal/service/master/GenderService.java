package com.visog.jobportal.service.master;

import java.util.List;

import com.visog.jobportal.req.master.GenderReq;
import com.visog.jobportal.res.master.GenderRes;

public interface GenderService {

	public void saveGender(GenderReq req);

	public void updateGender(GenderReq req, String genderId);

	public List<GenderRes> getGenders();

	public GenderRes getGender(String id);
}
