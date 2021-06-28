package com.netcom.nkestate.system.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "BULLETIN", sequence = "SEQ_BULLETIN", id = DBModel.SequenceID)
public class BulletinVO extends FHVO {

	private long ID;//	流水号
	private String title;//	标题
	private String content;//	内容
	private int status;//	状态	CODE200 0:不弹出显示、1:弹出显示
	private int districtID;//	所属区县编号
	private String cancel_By;//	取消者
	private long cancel_Tm;//	取消时间


	@DBMeta(column = "ID", name = "流水号", type = "long", primarykey = "true")
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		this.ID = iD;
		this.settings.put("ID", ID);
	}

	@DBMeta(column = "Title", name = "标题", type = "String")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.settings.put("title", title);
	}

	@DBMeta(column = "Content", name = "内容", type = "String")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		this.settings.put("content", content);
	}

	@DBMeta(column = "Status", name = "状态", type = "int", dict_table = "CT_200")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}

	@DBMeta(column = "DistrictID", name = "所属区县编号", type = "int", dict_table = "CT_DISTRICT")
	public int getDistrictID() {
		return districtID;
	}

	public void setDistrictID(int districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}

	@DBMeta(column = "Cancel_By", name = "取消者", type = "String")
	public String getCancel_By() {
		return cancel_By;
	}

	public void setCancel_By(String cancel_By) {
		this.cancel_By = cancel_By;
		this.settings.put("cancel_By", cancel_By);
	}

	@DBMeta(column = "Cancel_Tm", name = "取消时间", type = "long")
	public long getCancel_Tm() {
		return cancel_Tm;
	}



}
