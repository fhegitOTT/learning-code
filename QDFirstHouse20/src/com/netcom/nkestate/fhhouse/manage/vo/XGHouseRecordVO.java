package com.netcom.nkestate.fhhouse.manage.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "XG_HOUSERECORD", sequence = "", id = DBModel.AssignedID)
public class XGHouseRecordVO extends AbstractBaseVO {

	private long houseID;//	房屋ID
	private long nState;//	限购状态
	private String saddUser;//	操作人
	private String saddDate;//	操作时间


	@DBMeta(column = "HOUSE_ID", name = "房屋ID", type = "long", primarykey = "true", can_update = "false")
	public long getHouseID() {
		return houseID;
	}


	public void setHouseID(long houseID) {
		this.houseID = houseID;
		this.settings.put("houseID", houseID);
	}

	@DBMeta(column = "NState", name = "限购状态", type = "long")
	public long getNState() {
		return nState;
	}


	public void setNState(long nState) {
		this.nState = nState;
		this.settings.put("nState", nState);
	}

	@DBMeta(column = "SaddUser", name = "操作人", type = "String")
	public String getSaddUser() {
		return saddUser;
	}

	public void setSaddUser(String saddUser) {
		this.saddUser = saddUser;
		this.settings.put("saddUser", saddUser);
	}

	@DBMeta(column = "SaddDate", name = "操作时间", type = "String")
	public String getSaddDate() {
		return saddDate;
	}

	public void setSaddDate(String saddDate) {
		this.saddDate = saddDate;
		this.settings.put("saddDate", saddDate);
	}


}
