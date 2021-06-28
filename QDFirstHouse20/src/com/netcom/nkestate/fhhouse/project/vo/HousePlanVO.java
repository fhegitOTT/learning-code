package com.netcom.nkestate.fhhouse.project.vo;


import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "HOUSE_PLAN", sequence = "", id = DBModel.AssignedID)
public class HousePlanVO extends FHVO {

	private long plan_ID; //  房屋平面图ID
	private int districtID; //  区县ID
	private byte[] plan_Pdf; //  房屋平面图的PDF文件

	@DBMeta(column = "plan_ID", name = "房屋平面图ID", type = "long", primarykey = "true", can_update = "false")
	public long getPlan_ID() {
		return plan_ID;
	}

	public void setPlan_ID(long plan_ID) {
		this.plan_ID = plan_ID;
		this.settings.put("plan_ID", plan_ID);
	}

	@DBMeta(column = "districtID", name = "区县ID", type = "int")
	public int getDistrictID() {
		return districtID;
	}

	public void setDistrictID(int districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}

	@DBMeta(column = "plan_Pdf", name = "房屋平面图的PDF文件", type = "Blob")
	public byte[] getPlan_Pdf() {
		return plan_Pdf;
	}

	public void setPlan_Pdf(byte[] plan_Pdf) {
		this.plan_Pdf = plan_Pdf;
		this.settings.put("plan_Pdf", plan_Pdf);
	}

}
