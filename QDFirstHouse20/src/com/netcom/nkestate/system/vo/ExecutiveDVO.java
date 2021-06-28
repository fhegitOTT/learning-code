package com.netcom.nkestate.system.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "T_EXECUTIVE_D", sequence = "SEQ_LOG_EXEC_DETAIL", id = DBModel.SequenceID)
public class ExecutiveDVO extends AbstractBaseVO {

	private long informationID; // 详细ID
	private long recordID; // 记录ID 
	private long lvl; //  等级
	private String information; // 信息  
	private long recordTime; //  记录时间

	@DBMeta(column = "InformationID", name = "详细ID", type = "long", primarykey = "true", can_update = "false")
	public long getInformationID() {
		return informationID;
	}

	public void setInformationID(long InformationID) {
		this.informationID = InformationID;
		this.settings.put("informationID", informationID);
	}

	@DBMeta(column = "RecordID", name = "记录ID", type = "long")
	public long getRecordID() {
		return recordID;
	}

	public void setRecordID(long RecordID) {
		this.recordID = RecordID;
		this.settings.put("recordID", recordID);
	}

	@DBMeta(column = "Lvl", name = "等级", type = "long")
	public long getLvl() {
		return lvl;
	}

	public void setLvl(long Lvl) {
		this.lvl = Lvl;
		this.settings.put("lvl", lvl);
	}

	@DBMeta(column = "Information", name = "信息", type = "String")
	public String getInformation() {
		return information;
	}

	public void setInformation(String Information) {
		this.information = Information;
		this.settings.put("information", information);
	}

	@DBMeta(column = "RecordTime", name = "记录时间", type = "long")
	public long getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(long RecordTime) {
		this.recordTime = RecordTime;
		this.settings.put("recordTime", recordTime);
	}

}
