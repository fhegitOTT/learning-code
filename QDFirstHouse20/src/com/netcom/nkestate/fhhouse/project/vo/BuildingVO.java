package com.netcom.nkestate.fhhouse.project.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "BUILDING", sequence = "", id = DBModel.AssignedID)
public class BuildingVO extends FHVO {

	private long building_ID; //  楼栋ID
	private String building_Name; //  楼栋名

	/*
	 * private String crePerson; // 作成者 private long creDate; // 作成日期 private long creTime; // 作成时间 private String creProID; // 作成程序编码 private String updPerson; // 更新者 private long updDate; // 更新日期
	 * private long updTime; // 更新时间 private long updProID; // 更新程序编码 private long updCnt; // 更新计数器
	 */

	@DBMeta(column = "building_ID", name = "楼栋ID", type = "long", primarykey = "true", can_update = "false")
	public long getBuilding_ID() {
		return building_ID;
	}

	public void setBuilding_ID(long building_ID) {
		this.building_ID = building_ID;
		this.settings.put("building_ID", building_ID);
	}

	@DBMeta(column = "building_Name", name = "楼栋名", type = "String")
	public String getBuilding_Name() {
		return building_Name;
	}

	public void setBuilding_Name(String building_Name) {
		this.building_Name = building_Name;
		this.settings.put("building_Name", building_Name);
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
	 * @DBMeta(column = "updProID", name = "更新程序编码", type = "long") public long getUpdProID() { return updProID; } public void setUpdProID(long updProID) { this.updProID = updProID; }
	 * this.settings.put("updCnt", updCnt);
	 * @DBMeta(column = "updCnt", name = "更新计数器", type = "long") public long getUpdCnt() { return updCnt; } public void setUpdCnt(long updCnt) { this.updCnt = updCnt; }
	 */

}
