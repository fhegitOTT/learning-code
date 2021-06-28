package com.netcom.nkestate.fhhouse.project.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "HOUSE_STATE_CONSUME", sequence = "", id = DBModel.AssignedID)
public class HouseStateConsumeVO extends AbstractBaseVO {

	private long house_ID; //  房屋ID
	private int districtID; //  区县ID
	private int status; //  房屋状态
	private int deletedFlag; //  逻辑删除标志
	private Date last_Modi_Time; //  最后次更新时间

	@DBMeta(column = "house_ID", name = "房屋ID", type = "long", primarykey = "true", can_update = "false")
	public long getHouse_ID() {
		return house_ID;
	}

	public void setHouse_ID(long house_ID) {
		this.house_ID = house_ID;
		this.settings.put("house_ID", house_ID);
	}

	@DBMeta(column = "districtID", name = "区县ID", type = "int")
	public int getDistrictID() {
		return districtID;
	}

	public void setDistrictID(int districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}

	@DBMeta(column = "status", name = "房屋状态", type = "int")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}

	@DBMeta(column = "deletedFlag", name = "逻辑删除标志", type = "int")
	public int getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(int deletedFlag) {
		this.deletedFlag = deletedFlag;
		this.settings.put("deletedFlag", deletedFlag);
	}

	@DBMeta(column = "last_Modi_Time", name = "最后次更新时间", type = "Date")
	public Date getLast_Modi_Time() {
		return last_Modi_Time;
	}

	public void setLast_Modi_Time(Date last_Modi_Time) {
		this.last_Modi_Time = last_Modi_Time;
		this.settings.put("last_Modi_Time", last_Modi_Time);
	}

}
