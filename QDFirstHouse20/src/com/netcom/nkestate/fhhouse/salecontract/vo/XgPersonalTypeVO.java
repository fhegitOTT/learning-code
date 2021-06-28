package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "XG_PERSONAL_TYPE", sequence = "", id = DBModel.AssignedID)
public class XgPersonalTypeVO extends AbstractBaseVO {

	private long houseID; //  房屋ID
	private String name; //  申请类型

	@DBMeta(column = "HOUSEID", name = "房屋ID", type = "long", primarykey = "true", can_update = "false")
	public long getHouseID() {
		return houseID;
	}

	public void setHouseID(long houseID) {
		this.houseID = houseID;
		this.settings.put("houseID", houseID);
	}

	@DBMeta(column = "NAME", name = "申请类型", type = "String")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.settings.put("name", name);
	}

}
