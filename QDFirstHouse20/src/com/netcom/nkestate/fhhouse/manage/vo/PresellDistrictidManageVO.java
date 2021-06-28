package com.netcom.nkestate.fhhouse.manage.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;


@DBModel(tablename = "PRESELL_DISTRICTID_MANAGE", sequence = "", id = DBModel.AssignedID)
public class PresellDistrictidManageVO extends AbstractBaseVO {

	private int districtid; // 	区市		区市编号
	private int state; // 	预售监管状态	状态：0未开启监管1开启监管
	private String regulator; // 	预售监管机构

	@DBMeta(column = "Districtid", name = "区市", type = "int", primarykey = "true", can_update = "false")
	public int getDistrictid() {
		return districtid;
	}

	public void setDistrictid(int Districtid) {
		this.districtid = Districtid;
		this.settings.put("districtid", districtid);
	}

	@DBMeta(column = "State", name = "预售监管状态", type = "int")
	public int getState() {
		return state;
	}

	public void setState(int State) {
		this.state = State;
		this.settings.put("state", state);
	}

	@DBMeta(column = "Regulator", name = "预售监管机构", type = "String")
	public String getRegulator() {
		return regulator;
	}

	public void setRegulator(String Regulator) {
		this.regulator = Regulator;
		this.settings.put("regulator", regulator);
	}


}
