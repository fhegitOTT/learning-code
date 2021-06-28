package com.netcom.nkestate.fhhouse.manage.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "PROJECTAVERAGE", sequence = "", id = DBModel.AssignedID)
public class ProjectAverageVO extends AbstractBaseVO {

	private long nprojectID; // 	项目ID
	private long nbuildingID; // 	楼栋ID
	private long nbuildingAverage; // 	楼栋均价
	private double nbuildingBand; // 	楼栋幅度
	private String scity; // 	区市
	private String sadder; // 	添加人
	private String saddDate; // 	添加时间	日期格式：YYYY-MM-DD

	@DBMeta(column = "NprojectID", name = "项目ID", type = "long", primarykey = "true", can_update = "false")
	public long getNprojectID() {
		return nprojectID;
	}

	public void setNprojectID(long NprojectID) {
		this.nprojectID = NprojectID;
		this.settings.put("nprojectID", nprojectID);
	}

	@DBMeta(column = "NbuildingID", name = "楼栋ID", type = "long")
	public long getNbuildingID() {
		return nbuildingID;
	}

	public void setNbuildingID(long NbuildingID) {
		this.nbuildingID = NbuildingID;
		this.settings.put("nbuildingID", nbuildingID);
	}

	@DBMeta(column = "NbuildingAverage", name = "楼栋均价", type = "long")
	public long getNbuildingAverage() {
		return nbuildingAverage;
	}

	public void setNbuildingAverage(long NbuildingAverage) {
		this.nbuildingAverage = NbuildingAverage;
		this.settings.put("nbuildingAverage", nbuildingAverage);
	}

	@DBMeta(column = "NbuildingBand", name = "楼栋幅度", type = "double")
	public double getNbuildingBand() {
		return nbuildingBand;
	}

	public void setNbuildingBand(double NbuildingBand) {
		this.nbuildingBand = NbuildingBand;
		this.settings.put("nbuildingBand", nbuildingBand);
	}

	@DBMeta(column = "Scity", name = "区市", type = "String")
	public String getScity() {
		return scity;
	}

	public void setScity(String Scity) {
		this.scity = Scity;
		this.settings.put("scity", scity);
	}

	@DBMeta(column = "Sadder", name = "添加人", type = "String")
	public String getSadder() {
		return sadder;
	}

	public void setSadder(String Sadder) {
		this.sadder = Sadder;
		this.settings.put("sadder", sadder);
	}

	@DBMeta(column = "SaddDate", name = "添加时间", type = "String")
	public String getSaddDate() {
		return saddDate;
	}

	public void setSaddDate(String SaddDate) {
		this.saddDate = SaddDate;
		this.settings.put("saddDate", saddDate);
	}


}
