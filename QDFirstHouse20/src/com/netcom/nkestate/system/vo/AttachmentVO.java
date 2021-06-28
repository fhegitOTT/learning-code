package com.netcom.nkestate.system.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "Attachment_T", sequence = "Seq_Attchment_ID", id = DBModel.SequenceID)
public class AttachmentVO   extends AbstractBaseVO {

	private long AttachmentID;//附件编号
	private long ObjectID;//业务对象编号
	private int ObjectType;//业务对象类型
	private int FileCode;//文件种类代码
	private String FileName;//文件名称
	private int FileCount;//附件数量
	private String RelativeURL;//存储相对路径
	private String Memo;//备注
	
	@DBMeta(column = "AttachmentID", name = "附件编号", type = "long", primarykey = "true", can_update = "false")
	public long getAttachmentID() {
		return AttachmentID;
	}

	public void setAttachmentID(long attachmentID) {
		AttachmentID = attachmentID;
	}

	@DBMeta(column = "ObjectID", name = "业务对象编号", type = "long")
	public long getObjectID() {
		return ObjectID;
	}
	public void setObjectID(long objectID) {
		ObjectID = objectID;
	}

	@DBMeta(column = "ObjectType", name = "业务对象类型", type = "int")
	public int getObjectType() {
		return ObjectType;
	}
	public void setObjectType(int objectType) {
		ObjectType = objectType;
	}

	@DBMeta(column = "FileCode", name = "文件种类代码", type = "int")
	public int getFileCode() {
		return FileCode;
	}
	public void setFileCode(int fileCode) {
		FileCode = fileCode;
	}

	@DBMeta(column = "FileName", name = "文件名称", type = "String")
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}

	@DBMeta(column = "FileCount", name = "附件数量", type = "Int")
	public int getFileCount() {
		return FileCount;
	}
	public void setFileCount(int fileCount) {
		FileCount = fileCount;
	}


	@DBMeta(column = "RelativeURL", name = "存储相对路径", type = "String")
	public String getRelativeURL() {
		return RelativeURL;
	}
	public void setRelativeURL(String relativeURL) {
		RelativeURL = relativeURL;
	}

	@DBMeta(column = "Memo", name = "备注", type = "String")
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	
	
}
