/**
 * <p>LocationVO.java </p>
 *
 * <p>系统名称: 青岛市不动产登记系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: hwg</p>
 * <p>创建时间: 2017年5月9日<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.permit.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "COMMON.CH_LOCATION", sequence = "", id = DBModel.AssignedID)
public class LocationVO extends AbstractBaseVO {

	protected long locationID; // 代码
	protected String roadName; // 
	protected String laneName; // 
	protected String subLane; // 
	protected String streetNumber;
	protected String locationName;
	protected int districtID; // 区县代码

	@DBMeta(column = "locationID", name = "坐落编号", type = "long", primarykey = "true")
	public long getLocationID() {
		return locationID;
	}

	public void setLocationID(long locationID) {
		this.locationID = locationID;
		this.settings.put("locationID", locationID);
	}

	@DBMeta(column = "roadName", name = "", type = "String")
	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
		this.settings.put("roadName", roadName);
	}

	@DBMeta(column = "laneName", name = "", type = "String")
	public String getLaneName() {
		return laneName;
	}

	public void setLaneName(String laneName) {
		this.laneName = laneName;
		this.settings.put("laneName", laneName);
	}

	@DBMeta(column = "subLane", name = "", type = "String")
	public String getSubLane() {
		return subLane;
	}

	public void setSubLane(String subLane) {
		this.subLane = subLane;
		this.settings.put("subLane", subLane);
	}

	@DBMeta(column = "streetNumber", name = "", type = "String")
	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
		this.settings.put("streetNumber", streetNumber);
	}

	@DBMeta(column = "locationName", name = "", type = "String")
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
		this.settings.put("locationName", locationName);
	}

	@DBMeta(column = "districtID", name = "", type = "int")
	public int getDistrictID() {
		return districtID;
	}

	public void setDistrictID(int districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}


}
