/**
 * <p>CFGFileStandardVO.java </p>
 *
 * <p>系统名称: 青岛市不动产登记系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: nk0088</p>
 * <p>创建时间: 2017年3月11日<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.permit.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "CFG_FILE_STANDARD_T", sequence = "SEQ_CFG_RULE_ID", id = DBModel.SequenceID)
public class CfgFileStandardVO extends AbstractBaseVO {
	protected int ruleID;
	protected int typeAID;
	protected int typeBID;
	protected int fileCode;
	protected String fileName;
	protected int fileNum;
	protected int flag;
	protected int fileType;
	protected int isDefault;
	protected Date validDate;
	protected Date invalidDate;
	
	@DBMeta(column = "ruleID", name = "规则编号", type = "int", primarykey = "true")
	public int getRuleID() {
		return ruleID;
	}
	
	public void setRuleID(int ruleID) {
		this.ruleID = ruleID;
		this.settings.put("ruleID", ruleID);
	}
	
	@DBMeta(column = "typeAID", name = "大类编号", type = "int")
	public int getTypeAID() {
		return typeAID;
	}
	
	public void setTypeAID(int typeAID) {
		this.typeAID = typeAID;
		this.settings.put("typeAID", typeAID);
	}
	
	@DBMeta(column = "typeBID", name = "小类编号", type = "int")
	public int getTypeBID() {
		return typeBID;
	}
	
	public void setTypeBID(int typeBID) {
		this.typeBID = typeBID;
		this.settings.put("typeBID", typeBID);
	}
	
	@DBMeta(column = "fileCode", name = "收件编号", type = "int")
	public int getFileCode() {
		return fileCode;
	}
	
	public void setFileCode(int fileCode) {
		this.fileCode = fileCode;
		this.settings.put("fileCode", fileCode);
	}
	
	@DBMeta(column = "fileName", name = "收件名称", type = "String")
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
		this.settings.put("fileName", fileName);
	}
	
	@DBMeta(column = "fileNum", name = "收件数量", type = "int")
	public int getFileNum() {
		return fileNum;
	}
	
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
		this.settings.put("fileNum", fileNum);
	}
	
	@DBMeta(column = "flag", name = "标志", type = "int")
	public int getFlag() {
		return flag;
	}
	
	public void setFlag(int flag) {
		this.flag = flag;
		this.settings.put("flag", flag);
	}
	
	@DBMeta(column = "fileType", name = "收件类型", type = "int")
	public int getFileType() {
		return fileType;
	}
	
	public void setFileType(int fileType) {
		this.fileType = fileType;
		this.settings.put("fileType", fileType);
	}
	
	@DBMeta(column = "isDefault", name = "是否默认", type = "int")
	public int getIsDefault() {
		return isDefault;
	}
	
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
		this.settings.put("isDefault", isDefault);
	}
	
	@DBMeta(column = "validDate", name = "生效日期", type = "Date")
	public Date getValidDate() {
		return validDate;
	}
	
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
		this.settings.put("validDate", validDate);
	}
	
	@DBMeta(column = "invalidDate", name = "失效日期", type = "Date")
	public Date getInvalidDate() {
		return invalidDate;
	}
	
	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
		this.settings.put("invalidDate", invalidDate);
	}
	
	
	
}
