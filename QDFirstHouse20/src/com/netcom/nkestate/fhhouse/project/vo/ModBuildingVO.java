package com.netcom.nkestate.fhhouse.project.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "MOD_BUILDING", sequence = "", id = DBModel.AssignedID)
public class ModBuildingVO extends AbstractBaseVO {

	private String case_Number; //  成果案件号
	private long building_ID; //  楼栋编号
	private int state; //  变更标志
	private Date mod_Date; //  变更日期
	private long history_ID; //  历史编号

	@DBMeta(column = "case_Number", name = "成果案件号", type = "String", primarykey = "true", can_update = "false")
	public String getCase_Number() {
		return case_Number;
	}

	public void setCase_Number(String case_Number) {
		this.case_Number = case_Number;
		this.settings.put("case_Number", case_Number);
	}

	@DBMeta(column = "building_ID", name = "楼栋编号", type = "long")
	public long getBuilding_ID() {
		return building_ID;
	}

	public void setBuilding_ID(long building_ID) {
		this.building_ID = building_ID;
		this.settings.put("building_ID", building_ID);
	}

	@DBMeta(column = "state", name = "变更标志", type = "int")
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		this.settings.put("state", state);
	}

	@DBMeta(column = "mod_Date", name = "变更日期", type = "Date")
	public Date getMod_Date() {
		return mod_Date;
	}

	public void setMod_Date(Date mod_Date) {
		this.mod_Date = mod_Date;
		this.settings.put("mod_Date", mod_Date);
	}

	@DBMeta(column = "history_ID", name = "历史编号", type = "long")
	public long getHistory_ID() {
		return history_ID;
	}

	public void setHistory_ID(long history_ID) {
		this.history_ID = history_ID;
		this.settings.put("history_ID", history_ID);
	}


}
