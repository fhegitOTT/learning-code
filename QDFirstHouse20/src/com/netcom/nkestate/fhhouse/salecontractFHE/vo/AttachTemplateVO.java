package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "ATTACHTEMPLATE", sequence = "SEQ_ATTACHTEMPLATE_ID", id = DBModel.SequenceID)
public class AttachTemplateVO extends FHVO {

	private long ID;//	流水号
	private long templateID;//	合同模板ID
	private long serial;//	附件序号	CODE528
	private byte[] content = "".getBytes(); //	附件内容

	@DBMeta(column = "ID", name = "流水号", type = "long", primarykey = "true", can_update = "false")
	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
		this.settings.put("ID", ID);
	}

	@DBMeta(column = "TEMPLATE_ID", name = "合同模板ID", type = "long")
	public long getTemplateID() {
		return templateID;
	}

	public void setTemplateID(long templateID) {
		this.templateID = templateID;
		this.settings.put("templateID", templateID);
	}

	@DBMeta(column = "SERIAL", name = "附件序号", type = "long", dict_table = "CT_528")
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
