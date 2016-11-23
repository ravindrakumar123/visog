package com.visog.jobportal.utils;

import java.util.Date;
import java.util.UUID;

import com.visog.jobportal.model.AbstractModel;

public class DaoUtils {
	
	public static void setEntityCreateAuditColumns(AbstractModel model) {
		
		model.setId(getUUID());
		model.setCreatedBy("18ae947b-4114-4110-a275-5135a50c39c4");
		model.setCreatedOn(new Date());
		model.setCreatedSid("273fa17d-16e1-43b8-8915-d35daf05fcc1");
	}

	public static void setEntityUpdateAuditColumns(AbstractModel model) {
		
		model.setUpdatedBy("3ba00b3c-8a9c-417d-8ac6-b02153a68dd8");
		model.setUpdatedOn(new Date());
		model.setUpdatedSid("7233ec14-7dec-44d5-b4bb-4a9b5ca05505");
	}
	
	private static String getUUID() {
		return UUID.randomUUID().toString();
	}

}
 