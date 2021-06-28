package com.netcom.nkestate.fhhouse.project.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "PRO_PRE_BLD_SIGNER", sequence = "", id = DBModel.AssignedID)
public class ProPreBldSignerVO extends FHVO {

	private long signer_ID; //  签约人ID
	private long project_ID; //  项目ID
	private long start_ID; //  开盘ID
	private long building_ID; //  楼栋ID

	@DBMeta(column = "signer_ID", name = "签约人ID", type = "long", primarykey = "true", can_update = "false")
	public long getSigner_ID() {
		return signer_ID;
	}

	public void setSigner_ID(long signer_ID) {
		this.signer_ID = signer_ID;
		this.settings.put("signer_ID", signer_ID);
	}

	@DBMeta(column = "project_ID", name = "项目ID", type = "long")
	public long getProject_ID() {
		return project_ID;
	}

	public void setProject_ID(long project_ID) {
		this.project_ID = project_ID;
		this.settings.put("project_ID", project_ID);
	}

	@DBMeta(column = "start_ID", name = "开盘ID", type = "long")
	public long getStart_ID() {
		return start_ID;
	}

	public void setStart_ID(long start_ID) {
		this.start_ID = start_ID;
		this.settings.put("start_ID", start_ID);
	}

	@DBMeta(column = "building_ID", name = "楼栋ID", type = "long")
	public long getBuilding_ID() {
		return building_ID;
	}

	public void setBuilding_ID(long building_ID) {
		this.building_ID = building_ID;
		this.settings.put("building_ID", building_ID);
	}


}
