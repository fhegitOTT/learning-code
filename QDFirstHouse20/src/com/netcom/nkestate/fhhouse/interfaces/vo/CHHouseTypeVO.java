package com.netcom.nkestate.fhhouse.interfaces.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "COMMON.CH_HOUSE_TYPE", sequence = "", id = DBModel.AssignedID)
public class CHHouseTypeVO extends AbstractBaseVO {

	protected long houseID; // 户编号
	protected int districtID; // 区县编号
	protected int house_Type;
	protected int house_Flag;
	protected Date syncTime;
	protected String case_Number;

	@DBMeta(column = "houseID", name = "户编号", type = "long", primarykey = "true")
	public long getHouseID() {
		return houseID;
	}

	public void setHouseID(long houseID) {
		this.houseID = houseID;
		this.settings.put("houseID", houseID);
	}

	@DBMeta(column = "districtID", name = "区县编号", type = "int")
	public int getDistrictID() {
		return districtID;
	}

	public void setDistrictID(int districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}

	@DBMeta(column = "house_Type", name = "", type = "int")
	public int getHouse_Type() {
		return house_Type;
	}

	public void setHouse_Type(int house_Type) {
		this.house_Type = house_Type;
		this.settings.put("house_Type", house_Type);
	}

	@DBMeta(column = "house_Flag", name = "", type = "int")
	public int getHouse_Flag() {
		return house_Flag;
	}

	public void setHouse_Flag(int house_Flag) {
		this.house_Flag = house_Flag;
		this.settings.put("house_Flag", house_Flag);
	}

	@DBMeta(column = "syncTime", name = "", type = "Date")
	public Date getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
		this.settings.put("syncTime", syncTime);
	}

	@DBMeta(column = "case_Number", name = "", type = "String")
	public String getCase_Number() {
		return case_Number;
	}

	public void setCase_Number(String case_Number) {
		this.case_Number = case_Number;
		this.settings.put("case_Number", case_Number);
	}

}
