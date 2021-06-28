package com.netcom.nkestate.fhhouse.manage.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "DOCUMENT_HISTORY", sequence = "SEQ_DOCUMENT_HISTORY", id = DBModel.SequenceID)
public class DocumentHistoryVO extends FHVO {

	private long ID; // 	流水号
	private long document_ID; // 	件袋ID
	private long userID; // 	用户ID
	private long olduserID; //   老用户ID

	@DBMeta(column = "ID", name = "流水号", type = "long", primarykey = "true", can_update = "false")
	public long getID() {
		return ID;
	}
	public void setID(long ID) {
		this.ID = ID;
		this.settings.put("ID", ID);
	}

	@DBMeta(column = "document_ID", name = "件袋ID", type = "long")
	public long getDocument_ID() {
		return document_ID;
	}

	public void setDocument_ID(long document_ID) {
		this.document_ID = document_ID;
		this.settings.put("document_ID", document_ID);
	}

	@DBMeta(column = "userID", name = "用户ID", type = "long")
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
		this.settings.put("userID", userID);
	}

	@DBMeta(column = "olduserID", name = "老用户ID", type = "long")
	public long getOlduserID() {
		return olduserID;
	}
	public void setOlduserID(long olduserID) {
		this.olduserID = olduserID;
		this.settings.put("olduserID", olduserID);
	}

}
