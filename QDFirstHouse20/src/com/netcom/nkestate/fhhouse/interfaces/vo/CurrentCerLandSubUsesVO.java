package com.netcom.nkestate.fhhouse.interfaces.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "CURRENTCER_LAND_SUBUSES_T", description = "证土地多用途表", sequence = "", id = DBModel.AssignedID)
public class CurrentCerLandSubUsesVO extends AbstractBaseVO {
	protected long certLandID; // 证地编号
	protected int districtID; // 区县编号
	protected long usersNo;//用途编号 对应common.CH_LOT_USES.USERSNO
	protected String ownerName; // 权利人姓名
	protected String landUse; // 用途
	protected Date landStartDate; // 土地使用起始日期
	protected Date landEndDate; // 土地使用终止日期
	protected int rightsNature; // 权利性质

	@DBMeta(column = "certLandID", name = "证地编号", type = "long", primarykey = "true")
	public long getCertLandID() {
		return certLandID;
	}

	public void setCertLandID(long certLandID) {
		this.certLandID = certLandID;
		this.settings.put("certLandID", certLandID);
	}

	@DBMeta(column = "districtID", name = "区县编号", type = "int")
	public int getDistrictID() {
		return districtID;
	}

	public void setDistrictID(int districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}

	@DBMeta(column = "usersNo", name = "用途编号", type = "long")
	public long getUsersNo() {
		return usersNo;
	}


	public void setUsersNo(long usersNo) {
		this.usersNo = usersNo;
		this.settings.put("usersNo", usersNo);
	}

	@DBMeta(column = "ownerName", name = "权利人姓名", type = "String")
	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
		this.settings.put("ownerName", ownerName);
	}

	@DBMeta(column = "landUse", name = "用途", type = "String")
	public String getLandUse() {
		return landUse;
	}


	public void setLandUse(String landUse) {
		this.landUse = landUse;
		this.settings.put("landUse", landUse);
	}

	@DBMeta(column = "landStartDate", name = "土地使用起始日期", type = "Date")
	public Date getLandStartDate() {
		return landStartDate;
	}


	public void setLandStartDate(Date landStartDate) {
		this.landStartDate = landStartDate;
		this.settings.put("landStartDate", landStartDate);
	}

	@DBMeta(column = "landEndDate", name = "土地使用终止日期", type = "Date")
	public Date getLandEndDate() {
		return landEndDate;
	}


	public void setLandEndDate(Date landEndDate) {
		this.landEndDate = landEndDate;
		this.settings.put("landEndDate", landEndDate);
	}

	@DBMeta(column = "rightsNature", name = "权利性质", type = "int")
	public int getRightsNature() {
		return rightsNature;
	}


	public void setRightsNature(int rightsNature) {
		this.rightsNature = rightsNature;
		this.settings.put("rightsNature", rightsNature);
	}

}
