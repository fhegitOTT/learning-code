package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "ATTACH", sequence = "SEQ_ATTACH_ID", id = DBModel.SequenceID)
public class AttachVO extends FHVO {

	private long ID;//	流水号
	private long contractID;//	合同ID
	private long serial;//	附件序号
	private byte[] content;//	附件内容
	
	@DBMeta(column = "ID", name = "流水号", type = "long", primarykey = "true", can_update = "false")
	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
		this.settings.put("ID", ID);
	}

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "SERIAL", name = "附件序号", type = "long")
	public long getSerial() {
		return serial;
	}

	public void setSerial(long serial) {
		this.serial = serial;
		this.settings.put("serial", serial);
	}

	@DBMeta(column = "CONTENT", name = "附件内容", type = "Clob")
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
		this.settings.put("content", content);
	}

}
