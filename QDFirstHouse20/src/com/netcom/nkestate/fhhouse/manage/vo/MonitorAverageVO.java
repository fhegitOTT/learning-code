package com.netcom.nkestate.fhhouse.manage.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "MONITORAVERAGE", sequence = "", id = DBModel.SequenceID)
public class MonitorAverageVO extends AbstractBaseVO {

	private long nmaID;//	主键ID
	private long nProjectID;//	项目ID
	private long nHouseID;//	房屋ID
	private double nHouseAverage;//	签约单价
	private long nHouseReference;//	房屋基准价格
	private double nBandAverage;//	浮动区间
	private String addDate;//	操作时间

	@DBMeta(column = "NmaID", name = "主键ID", type = "long", primarykey = "true", can_update = "false")
	public long getNmaID() {
		return nmaID;
	}

	public void setNmaID(long nmaID) {
		this.nmaID = nmaID;
		this.settings.put("nmaID", nmaID);
	}

	@DBMeta(column = "NProjectID", name = "项目ID", type = "long")
	public long getNProjectID() {
		return nProjectID;
	}

	public void setNProjectID(long nProjectID) {
		this.nProjectID = nProjectID;
		this.settings.put("nProjectID", nProjectID);
	}

	@DBMeta(column = "NHouseID", name = "房屋ID", type = "long")
	public long getNHouseID() {
		return nHouseID;
	}

	public void setNHouseID(long nHouseID) {
		this.nHouseID = nHouseID;
		this.settings.put("nHouseID", nHouseID);
	}

	@DBMeta(column = "NHouseAverage", name = "签约单价", type = "double")
	public double getNHouseAverage() {
		return nHouseAverage;
	}

	public void setNHouseAverage(double nHouseAverage) {
		this.nHouseAverage = nHouseAverage;
		this.settings.put("nHouseAverage", nHouseAverage);
	}

	@DBMeta(column = "NHouseReference", name = "房屋基准价格", type = "long")
	public long getNHouseReference() {
		return nHouseReference;
	}

	public void setNHouseReference(long nHouseReference) {
		this.nHouseReference = nHouseReference;
		this.settings.put("nHouseReference", nHouseReference);
	}

	@DBMeta(column = "NBandAverage", name = "浮动区间", type = "double")
	public double getNBandAverage() {
		return nBandAverage;
	}

	public void setNBandAverage(double nBandAverage) {
		this.nBandAverage = nBandAverage;
		this.settings.put("nBandAverage", nBandAverage);
	}

	@DBMeta(column = "AddDate", name = "操作时间", type = "String")
	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
		this.settings.put("addDate", addDate);
	}


}
