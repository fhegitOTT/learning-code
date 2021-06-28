package com.netcom.nkestate.fhhouse.permit.vo;


import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "APPLY_FILE_T", sequence = "SEQ_APPLY_FILE_ID", id = DBModel.SequenceID)
public class ApplyFileVO  extends AbstractBaseVO {
	protected long fileID;
	protected long transactionID;
	protected int districtID;
	protected String fileName;
	protected int fileCount;
//	protected int fileCode;
	protected int fileKind;//1、标准收件，3、补充收件
	protected int fileType;
//	protected int fileFlag;
	protected int typeBID;
	protected int receivedFlag;
	protected String remark;
	
	@DBMeta(column = "fileID", name = "收件编号", type = "long", primarykey = "true")
	public long getFileID() {
		return fileID;
	}
	public void setFileID(long fileID) {
		this.fileID = fileID;
		this.settings.put("fileID", fileID);
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
	@DBMeta(column = "fileName", name = "文件名称", type = "String")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
		this.settings.put("fileName", fileName);
	}
	@DBMeta(column = "fileCount", name = "文件数量", type = "int")
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
		this.settings.put("fileCount", fileCount);
	}
	/*@DBMeta(column = "fileCode", name = "文件代码", type = "int")
	public int getFileCode() {
		return fileCode;
	}
	public void setFileCode(int fileCode) {
		this.fileCode = fileCode;
		this.settings.put("fileCode", fileCode);
	}*/
	@DBMeta(column = "fileKind", name = "文件性质", type = "int")
	public int getFileKind() {
		return fileKind;
	}
	public void setFileKind(int fileKind) {
		this.fileKind = fileKind;
		this.settings.put("fileKind", fileKind);
	}
	@DBMeta(column = "fileType", name = "文件类型", type = "int",dict_table="ct_file_type")
	public int getFileType() {
		return fileType;
	}
	public void setFileType(int fileType) {
		this.fileType = fileType;
		this.settings.put("fileType", fileType);
	}
	/*@DBMeta(column = "fileFlag", name = "文件标记", type = "int")
	public int getFileFlag() {
		return fileFlag;
	}
	
	public void setFileFlag(int fileFlag) {
		this.fileFlag = fileFlag;
		this.settings.put("fileFlag", fileFlag);
	}*/
	@DBMeta(column = "typeBID", name = "小类编号", type = "int")
	public int getTypeBID() {
		return typeBID;
	}
	
	public void setTypeBID(int typeBID) {
		this.typeBID = typeBID;
		this.settings.put("typeBID", typeBID);
	}
	@DBMeta(column = "receivedFlag", name = "收到标记", type = "int")
	public int getReceivedFlag() {
		return receivedFlag;
	}
	public void setReceivedFlag(int receivedFlag) {
		this.receivedFlag = receivedFlag;
		this.settings.put("receivedFlag", receivedFlag);
	}
	@DBMeta(column = "remark", name = "备注", type = "String")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
		this.settings.put("remark", remark);
	}
	@Override
	public String toString() {
		return "ApplyFileVO [districtID=" + districtID + ", fileCount="
				+ fileCount + ", fileID=" + fileID + ", fileName=" + fileName
				+ ", fileType=" + fileType + ", receivedFlag=" + receivedFlag
				+ ", remark=" + remark + ", transactionID=" + transactionID
				+ ", typeBID=" + typeBID + "]";
	}
	
}
