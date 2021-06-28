package com.netcom.nkestate.fhhouse.manage.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "JGGLLOG", sequence = "SEQ_NLOGID", id = DBModel.SequenceID)
public class JGGLLogVO extends AbstractBaseVO {

	private long nlogID; //主键ID
	private String sprojectName; //项目名称
	private String shouseLocation; //房屋坐落
	private long nhouseAverage; //房屋基准价格
	private double nbandAverage; //上幅度
	private double nbandAveragexia; //下幅度
	private String saddDate; //添加时间	精确到分秒
	private String sendDate; //结束时间
	private String saddUser; //操作人
	private long projectID; //项目ID
	private long houseID; //房屋ID

	@DBMeta(column = "NlogID", name = "主键ID", type = "long", primarykey = "true", can_update = "false")
	public long getNlogID() {
		return nlogID;
	}

	public void setNlogID(long NlogID) {
		this.nlogID = NlogID;
		this.settings.put("nlogID", nlogID);
	}

	@DBMeta(column = "SprojectName", name = "项目名称", type = "String")
	public String getSprojectName() {
		return sprojectName;
	}

	public void setSprojectName(String SprojectName) {
		this.sprojectName = SprojectName;
		this.settings.put("sprojectName", sprojectName);
	}

	@DBMeta(column = "ShouseLocation", name = "房屋坐落", type = "String")
	public String getShouseLocation() {
		return shouseLocation;
	}

	public void setShouseLocation(String ShouseLocation) {
		this.shouseLocation = ShouseLocation;
		this.settings.put("shouseLocation", shouseLocation);
	}

	@DBMeta(column = "NhouseAverage", name = "房屋基准价格", type = "long")
	public long getNhouseAverage() {
		return nhouseAverage;
	}

	public void setNhouseAverage(long NhouseAverage) {
		this.nhouseAverage = NhouseAverage;
		this.settings.put("nhouseAverage", nhouseAverage);
	}

	@DBMeta(column = "nbandAverage", name = "上幅度", type = "double")
	public double getNbandAverage() {
		return nbandAverage;
	}

	public void setNbandAverage(double nbandAverage) {
		this.nbandAverage = nbandAverage;
		this.settings.put("nbandAverage", nbandAverage);
	}

	@DBMeta(column = "nbandAveragexia", name = "下幅度", type = "double")
	public double getAbandAveragexia() {
		return nbandAveragexia;
	}

	public void setNbandAveragexia(double nbandAveragexia) {
		this.nbandAveragexia = nbandAveragexia;
		this.settings.put("nbandAveragexia", nbandAveragexia);
	}

	@DBMeta(column = "SaddDate", name = "添加时间", type = "String")
	public String getSaddDate() {
		return saddDate;
	}

	public void setSaddDate(String SaddDate) {
		this.saddDate = SaddDate;
		this.settings.put("saddDate", saddDate);
	}

	@DBMeta(column = "SendDate", name = "结束时间", type = "String")
	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String SendDate) {
		this.sendDate = SendDate;
		this.settings.put("sendDate", sendDate);
	}

	@DBMeta(column = "SaddUser", name = "操作人", type = "String")
	public String getSaddUser() {
		return saddUser;
	}

	public void setSaddUser(String SaddUser) {
		this.saddUser = SaddUser;
		this.settings.put("saddUser", saddUser);
	}

	@DBMeta(column = "PROJECT_ID", name = "项目ID", type = "long")
	public long getProjectID() {
		return projectID;
	}

	public void setProjectID(long projectID) {
		this.projectID = projectID;
		this.settings.put("projectID", projectID);

	}

	@DBMeta(column = "HOUSE_ID", name = "房屋ID", type = "long")
	public long getHouseID() {
		return houseID;
	}


	public void setHouseID(long houseID) {
		this.houseID = houseID;
		this.settings.put("houseID", houseID);
	}


}
