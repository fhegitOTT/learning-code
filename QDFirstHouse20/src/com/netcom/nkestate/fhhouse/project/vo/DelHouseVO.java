package com.netcom.nkestate.fhhouse.project.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "DEL_HOUSE", sequence = "", id = DBModel.AssignedID)
public class DelHouseVO extends FHVO {

	private long house_ID; //  房屋ID
	private int status; //  状态
	private int manual_Status; //  人工干预
	private long pre_Plan_ID; //  预测平面图ID
	private long sell_Plan_ID; //  实测平面图ID

	/*
	 * private String crePerson; // 作成者 private long creDate; // 作成日期 private long creTime; // 作成时间 private String creProID; // 作成程序编码 private String updPerson; // 更新者 private long updDate; // 更新日期
	 * private long updTime; // 更新时间 private String updProID; // 更新程序编码 private long updCnt; // 更新计数器
	 */

	@DBMeta(column = "house_ID", name = "房屋ID", type = "long", primarykey = "true", can_update = "false")
	public long getHouse_ID() {
		return house_ID;
	}

	public void setHouse_ID(long house_ID) {
		this.house_ID = house_ID;
		this.settings.put("house_ID", house_ID);
	}

	@DBMeta(column = "status", name = "状态", type = "int")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}

	@DBMeta(column = "manual_Status", name = "人工干预", type = "int")
	public int getManual_Status() {
		return manual_Status;
	}

	public void setManual_Status(int manual_Status) {
		this.manual_Status = manual_Status;
		this.settings.put("manual_Status", manual_Status);
	}

	@DBMeta(column = "pre_Plan_ID", name = "预测平面图ID", type = "long")
	public long getPre_Plan_ID() {
		return pre_Plan_ID;
	}

	public void setPre_Plan_ID(long pre_Plan_ID) {
		this.pre_Plan_ID = pre_Plan_ID;
		this.settings.put("pre_Plan_ID", pre_Plan_ID);
	}

	@DBMeta(column = "sell_Plan_ID", name = "实测平面图ID", type = "long")
	public long getSell_Plan_ID() {
		return sell_Plan_ID;
	}

	public void setSell_Plan_ID(long sell_Plan_ID) {
		this.sell_Plan_ID = sell_Plan_ID;
		this.settings.put("sell_Plan_ID", sell_Plan_ID);
	}

	/*
	 * this.settings.put("crePerson", crePerson);
	 * @DBMeta(column = "crePerson", name = "作成者", type = "String") public String getCrePerson() { return crePerson; } public void setCrePerson(String crePerson) { this.crePerson = crePerson; }
	 * this.settings.put("creDate", creDate);
	 * @DBMeta(column = "creDate", name = "作成日期", type = "long") public long getCreDate() { return creDate; } public void setCreDate(long creDate) { this.creDate = creDate; }
	 * this.settings.put("creTime", creTime);
	 * @DBMeta(column = "creTime", name = "作成时间", type = "long") public long getCreTime() { return creTime; } public void setCreTime(long creTime) { this.creTime = creTime; }
	 * this.settings.put("creProID", creProID);
	 * @DBMeta(column = "creProID", name = "作成程序编码", type = "String") public String getCreProID() { return creProID; } public void setCreProID(String creProID) { this.creProID = creProID; }
	 * this.settings.put("updPerson", updPerson);
	 * @DBMeta(column = "updPerson", name = "更新者", type = "String") public String getUpdPerson() { return updPerson; } public void setUpdPerson(String updPerson) { this.updPerson = updPerson; }
	 * this.settings.put("updDate", updDate);
	 * @DBMeta(column = "updDate", name = "更新日期", type = "long") public long getUpdDate() { return updDate; } public void setUpdDate(long updDate) { this.updDate = updDate; }
	 * this.settings.put("updTime", updTime);
	 * @DBMeta(column = "updTime", name = "更新时间", type = "long") public long getUpdTime() { return updTime; } public void setUpdTime(long updTime) { this.updTime = updTime; }
	 * this.settings.put("updProID", updProID);
	 * @DBMeta(column = "updProID", name = "更新程序编码", type = "String") public String getUpdProID() { return updProID; } public void setUpdProID(String updProID) { this.updProID = updProID; }
	 * this.settings.put("updCnt", updCnt);
	 * @DBMeta(column = "updCnt", name = "更新计数器", type = "long") public long getUpdCnt() { return updCnt; } public void setUpdCnt(long updCnt) { this.updCnt = updCnt; }
	 */

}
