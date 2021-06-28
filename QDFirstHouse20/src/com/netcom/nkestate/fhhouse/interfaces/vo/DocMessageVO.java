package com.netcom.nkestate.fhhouse.interfaces.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "UNIREALESTATE.DOC_MESSAGE_T", sequence = "UNIREALESTATE.SEQ_DOC_MESSAGE_ID", id = DBModel.SequenceID)
public class DocMessageVO extends AbstractBaseVO{

	protected long messageID;
	protected long transactionID;
	protected int districtID;
	protected int rightType;
	protected int typeBID;
	protected String name;
	protected String mobile;
	protected int flag;//操作标志：1:终审 2：退件 3：问题案件
	protected Date writeTime;
	protected String content;
	protected int status;

	@DBMeta(column = "messageID", name = "短信编号", type = "long", primarykey = "true")
	public long getMessageID() {
		return messageID;
	}

	public void setMessageID(long messageID) {
		this.messageID = messageID;
		this.settings.put("messageID", messageID);
	}

	@DBMeta(column = "transactionID", name = "案件编号", type = "long")
	public long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
		this.settings.put("transactionID", transactionID);
	}

	@DBMeta(column = "districtID", name = "区县编号", type = "int")
	public int getDistrictID() {
		return districtID;
	}

	public void setDistrictID(int districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}

	@DBMeta(column = "rightType", name = "权利类别", type = "int")
	public int getRightType() {
		return rightType;
	}

	public void setRightType(int rightType) {
		this.rightType = rightType;
		this.settings.put("rightType", rightType);
	}

	@DBMeta(column = "typeBID", name = "小类编号", type = "int")
	public int getTypeBID() {
		return typeBID;
	}

	public void setTypeBID(int typeBID) {
		this.typeBID = typeBID;
		this.settings.put("typeBID", typeBID);
	}

	@DBMeta(column = "name", name = "短信接收人姓名", type = "String")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.settings.put("name", name);
	}

	@DBMeta(column = "mobile", name = "短信接收人电话", type = "String")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
		this.settings.put("mobile", mobile);
	}

	@DBMeta(column = "flag", name = "操作标志", type = "int")
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
		this.settings.put("flag", flag);
	}

	@DBMeta(column = "writeTime", name = "短信写入时间", type = "Date")
	public Date getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
		this.settings.put("writeTime", writeTime);
	}

	@DBMeta(column = "content", name = "短信内容", type = "String")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		this.settings.put("content", content);
	}

	@DBMeta(column = "status", name = "状态", type = "int")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}


}
