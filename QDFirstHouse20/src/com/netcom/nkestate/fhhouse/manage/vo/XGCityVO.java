package com.netcom.nkestate.fhhouse.manage.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "XG_CITY", sequence = "", id = DBModel.AssignedID)
public class XGCityVO extends AbstractBaseVO {

	private long cityID; //  区市 ID
	private long nstate; //  限购


	@DBMeta(column = "CityID", name = "区市 ID", type = "long", primarykey = "true", can_update = "false")
	public long getCityID() {
		return cityID;
	}

	public void setCityID(long CityID) {
		this.cityID = CityID;
		this.settings.put("cityID", cityID);
	}

	@DBMeta(column = "Nstate", name = "限购", type = "long")
	public long getNstate() {
		return nstate;
	}

	public void setNstate(long Nstate) {
		this.nstate = Nstate;
		this.settings.put("nstate", nstate);
	}

}
