package com.netcom.nkestate.fhhouse.manage.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "HMF_CITY", sequence = "", id = DBModel.AssignedID)
public class HMFCityVO extends AbstractBaseVO {

	private long cityID;//
	private long nstate;//

	@DBMeta(column = "City_ID", name = "CityID", type = "long", primarykey = "true", can_update = "false")
	public long getCityID() {
		return cityID;
	}

	public void setCityID(long cityID) {
		this.cityID = cityID;
		this.settings.put("cityID", cityID);
	}

	@DBMeta(column = "Nstate", name = "Nstate", type = "long")
	public long getNstate() {
		return nstate;
	}

	public void setNstate(long nstate) {
		this.nstate = nstate;
		this.settings.put("nstate", nstate);
	}


}
