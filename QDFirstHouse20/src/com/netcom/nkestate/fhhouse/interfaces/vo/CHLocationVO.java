package com.netcom.nkestate.fhhouse.interfaces.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "common.ch_location", sequence = "", id = DBModel.AssignedID)
public class CHLocationVO extends AbstractBaseVO {

	protected long locationId;//坐落编号
	protected String roadName;//路名
	protected String laneName;//巷名，弄
	protected String subLane;//支弄
	protected String streetNumber;//街道号
	protected String locationName;//坐落名称
	protected int districtId;//区县编号

	@DBMeta(column = "locationId", name = "坐落编号", type = "long")
	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	@DBMeta(column = "roadName", name = "", type = "String")
	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	@DBMeta(column = "laneName", name = "", type = "String")
	public String getLaneName() {
		return laneName;
	}

	public void setLaneName(String laneName) {
		this.laneName = laneName;
	}

	@DBMeta(column = "subLane", name = "", type = "String")
	public String getSubLane() {
		return subLane;
	}

	public void setSubLane(String subLane) {
		this.subLane = subLane;
	}

	@DBMeta(column = "streetNumber", name = "", type = "String")
	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	@DBMeta(column = "locationName", name = "", type = "String")
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@DBMeta(column = "districtId", name = "", type = "Int")
	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	
}
