package com.netcom.nkestate.fhhouse.company.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "COMPANY_PROJECT", sequence = "", id = DBModel.AssignedID)
public class CompanyProjectVO extends FHVO {

	private long comp_ID; // 企业ID
	private long project_ID; // 项目ID
	private int serial; // 合同中甲方顺序号


	@DBMeta(column = "Comp_ID", name = "企业ID", type = "long", primarykey = "true", can_update = "false")
	public long getComp_ID() {
		return comp_ID;
	}

	public void setComp_ID(long Comp_ID) {
		this.comp_ID = Comp_ID;
		this.settings.put("comp_ID", comp_ID);
	}

	@DBMeta(column = "Project_ID", name = "项目ID", type = "long")
	public long getProject_ID() {
		return project_ID;
	}

	public void setProject_ID(long Project_ID) {
		this.project_ID = Project_ID;
		this.settings.put("project_ID", project_ID);
	}

	@DBMeta(column = "Serial", name = "合同中甲方顺序号", type = "int")
	public int getSerial() {
		return serial;
	}

	public void setSerial(int Serial) {
		this.serial = Serial;
		this.settings.put("serial", serial);
	}



}
