package com.netcom.nkestate.system.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "Attachment_File_T", sequence = "Seq_Attachment_File_ID", id = DBModel.SequenceID)
public class AttachmentFileVO   extends AbstractBaseVO {

	private long AttachmentFileID;//附件文件编号
	private long AttachmentID;//附件编号
	private String OrginalFileName;//原始文件名称
	private String FileType;//文件类型
	private long FileSize;//文件大小
	private long UserID;//上传人
	private Date UploadTime;//上传时间

	@DBMeta(column = "AttachmentFileID", name = "附件文件编号", type = "long", primarykey = "true", can_update = "false")
	public long getAttachmentFileID() {
		return AttachmentFileID;
	}

	public void setAttachmentFileID(long attachmentFileID) {
		AttachmentFileID = attachmentFileID;
	}

	@DBMeta(column = "AttachmentID", name = "附件编号", type = "long")
	public long getAttachmentID() {
		return AttachmentID;
	}

	public void setAttachmentID(long attachmentID) {
		AttachmentID = attachmentID;
	}

	@DBMeta(column = "OrginalFileName", name = "原始文件名称", type = "String")
	public String getOrginalFileName() {
		return OrginalFileName;
	}
	public void setOrginalFileName(String orginalFileName) {
		OrginalFileName = orginalFileName;
	}

	@DBMeta(column = "FileType", name = "文件类型", type = "String")
	public String getFileType() {
		return FileType;
	}
	public void setFileType(String fileType) {
		FileType = fileType;
	}

	@DBMeta(column = "FileSize", name = "文件大小", type = "long")
	public long getFileSize() {
		return FileSize;
	}
	public void setFileSize(long fileSize) {
		FileSize = fileSize;
	}

	@DBMeta(column = "UserID", name = "上传人", type = "long")
	public long getUserID() {
		return UserID;
	}
	public void setUserID(long userID) {
		UserID = userID;
	}

	@DBMeta(column = "UploadTime", name = "上传时间", type = "Date")
	public Date getUploadTime() {
		return UploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		UploadTime = uploadTime;
	}
	
	
}
