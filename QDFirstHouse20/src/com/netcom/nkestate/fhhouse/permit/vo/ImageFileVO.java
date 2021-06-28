package com.netcom.nkestate.fhhouse.permit.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;
import com.netcom.nkestate.framework.util.DateUtil;

@DBModel(tablename = "IMAGE_FILE_T", sequence = "SEQ_IMAGEFILE_ID", id = DBModel.SequenceID)
public class ImageFileVO extends AbstractBaseVO {
	protected long imageID;
	protected long fileID;
	protected long transactionID;
	protected int districtID;
	protected String fileName;
	protected int fileNum;
	protected int totalPage; //总页数
	protected String fileCode;
	protected String fileType;
	protected long fileSize;
	protected String diskFileName;
	protected long userID;
	protected Date uploadTime;
	protected String uploadTimeStr;
	protected int sourceType;
	protected int conseqNum;

	@DBMeta(column = "imageID", name = "图片编号", type = "long", primarykey = "true")
	public long getImageID() {
		return imageID;
	}

	public void setImageID(long imageID) {
		this.imageID = imageID;
		this.settings.put("imageID", imageID);
	}

	@DBMeta(column = "fileID", name = "收件编号", type = "long")
	public long getFileID() {
		return fileID;
	}

	public void setFileID(long fileID) {
		this.fileID = fileID;
		this.settings.put("fileID", fileID);
	}

	@DBMeta(column = "transactionID", name = "登记编号", type = "long")
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

	@DBMeta(column = "fileNum", name = "文件序号", type = "int")
	public int getFileNum() {
		return fileNum;
	}

	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
		this.settings.put("fileNum", fileNum);
	}

	@DBMeta(column = "fileCode", name = "收件代码", type = "String")
	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
		this.settings.put("fileCode", fileCode);
	}

	@DBMeta(column = "fileType", name = "文件类型", type = "String")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
		this.settings.put("fileType", fileType);
	}

	@DBMeta(column = "totalPage", name = "总页数", type = "int")
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		this.settings.put("fileSize", fileSize);
	}

	@DBMeta(column = "fileSize", name = "文件大小", type = "long")
	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
		this.settings.put("fileSize", fileSize);
	}

	@DBMeta(column = "diskFileName", name = "存储的文件名称", type = "String")
	public String getDiskFileName() {
		return diskFileName;
	}

	public void setDiskFileName(String diskFileName) {
		this.diskFileName = diskFileName;
		this.settings.put("diskFileName", diskFileName);
	}

	@DBMeta(column = "userID", name = "上传用户", type = "long")
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
		this.settings.put("userID", userID);
	}

	@DBMeta(column = "uploadTime", name = "上传时间", type = "Date")
	public Date getUploadTime() {
		return uploadTime;
	}
	public String getUploadTimeStr() {
		return DateUtil.formatDateTime(getUploadTime());
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
		this.settings.put("uploadTime", uploadTime);
	}

	@DBMeta(column = "sourceType", name = "图片来源", type = "int")
	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
		this.settings.put("sourceType", sourceType);
	}

	@DBMeta(column = "conseqNum", name = "文件序号", type = "int")
	public int getConseqNum() {
		return conseqNum;
	}

	public void setConseqNum(int conseqNum) {
		this.conseqNum = conseqNum;
		this.settings.put("conseqNum", conseqNum);
	}
}
