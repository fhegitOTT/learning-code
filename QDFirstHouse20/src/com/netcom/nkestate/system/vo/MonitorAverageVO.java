package com.netcom.nkestate.system.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;

public class MonitorAverageVO extends AbstractBaseVO {

	private long nmaid;//	主键ID
	private long nprojectID;//	项目ID
	private long nhouseID;//	房屋ID
	private double nhouseAverage;//	签约单价
	private long nhouseReference;//	房屋基准价格
	private double nbandAverage;//	浮动区间
	private String addDate;//	操作时间

	public long getNmaid() {
		return nmaid;
	}

	public void setNmaid(long nmaid) {
		this.nmaid = nmaid;
		this.settings.put("nmaid", nmaid);
	}

	public long getNprojectID() {
		return nprojectID;
	}

	public void setNprojectID(long nprojectID) {
		this.nprojectID = nprojectID;
		this.settings.put("nprojectID", nprojectID);
	}

	public long getNhouseID() {
		return nhouseID;
	}

	public void setNhouseID(long nhouseID) {
		this.nhouseID = nhouseID;
		this.settings.put("nhouseID", nhouseID);
	}

	public double getNhouseAverage() {
		return nhouseAverage;
	}

	public void setNhouseAverage(double nhouseAverage) {
		this.nhouseAverage = nhouseAverage;
		this.settings.put("nhouseAverage", nhouseAverage);
	}

	public long getNhouseReference() {
		return nhouseReference;
	}

	public void setNhouseReference(long nhouseReference) {
		this.nhouseReference = nhouseReference;
		this.settings.put("nhouseReference", nhouseReference);
	}

	public double getNbandAverage() {
		return nbandAverage;
	}

	public void setNbandAverage(double nbandAverage) {
		this.nbandAverage = nbandAverage;
		this.settings.put("nbandAverage", nbandAverage);
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
		this.settings.put("addDate", addDate);
	}

}
