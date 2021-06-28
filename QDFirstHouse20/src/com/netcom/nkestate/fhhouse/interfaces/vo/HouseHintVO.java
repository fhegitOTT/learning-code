/**
 * <p>HouseHintVO.java </p>
 *
 * <p>系统名称: 青岛市不动产登记系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Michael</p>
 * <p>创建时间: 2017年4月10日<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.interfaces.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "HOUSE_HINT_T", sequence = "SEQ_HOUSE_HINTID", id = DBModel.SequenceID)
public class HouseHintVO extends AbstractBaseVO {
	private long hintID;
	private long houseID;
	private String note;
	private long userID;
	private Date writeTime;
	private Date updateTime;
	private int status;
	
	@DBMeta(column = "hintID", name = "提示编号", type = "long", primarykey = "true")
	public long getHintID() {
		return hintID;
	}
	
	public void setHintID(long hintID) {
		this.hintID = hintID;
		settings.put("hintID", this.hintID);
	}
	
	@DBMeta(column = "houseID", name = "房屋编号", type = "long")
	public long getHouseID() {
		return houseID;
	}
	
	public void setHouseID(long houseID) {
		this.houseID = houseID;
		settings.put("houseID", this.houseID);
	}
	
	@DBMeta(column = "note", name = "提示内容", type = "String")
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
		settings.put("note", this.note);
	}
	
	@DBMeta(column = "userID", name = "操作员", type = "long")
	public long getUserID() {
		return userID;
	}
	
	public void setUserID(long userID) {
		this.userID = userID;
		settings.put("userID", this.userID);
	}
	
	@DBMeta(column = "writeTime", name = "写入时间", type = "Date")
	public Date getWriteTime() {
		return writeTime;
	}
	
	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
		settings.put("writeTime", this.writeTime);
	}
	
	@DBMeta(column = "updateTime", name = "更新时间", type = "Date")
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		settings.put("updateTime", this.updateTime);
	}
	
	@DBMeta(column = "status", name = "状态", type = "int")
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
		settings.put("status", this.status);
	}
	
}
