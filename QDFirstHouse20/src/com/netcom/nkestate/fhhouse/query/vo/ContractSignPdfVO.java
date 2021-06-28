package com.netcom.nkestate.fhhouse.query.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "CONTRACT_SIGN_PDF", sequence = "", id = DBModel.AssignedID)
public class ContractSignPdfVO extends AbstractBaseVO {

	private long contractID; // 合同ID
	private long houseID; // 房屋ID
	private int contractType; // 合同类型
	private String pdfPath; // 合同路径
	private byte[] pdfData = "".getBytes(); // pdf数据
	private String createPerson; // 作成者
	private Date createDate; // 作成日期
	private String updatePerson; // 更新者
	private Date updateDate; // 更新日期
	private String sealPerson; // 盖章人
	private Date sealDate; // 盖章日期

	@DBMeta(column = "contractID", name = "合同ID", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "houseID", name = "房屋ID", type = "long")
	public long getHouseID() {
		return houseID;
	}

	public void setHouseID(long houseID) {
		this.houseID = houseID;
		this.settings.put("houseID", houseID);
	}

	@DBMeta(column = "contractType", name = "合同类型", type = "int")
	public int getContractType() {
		return contractType;
	}

	public void setContractType(int contractType) {
		this.contractType = contractType;
		this.settings.put("contractType", contractType);
	}

	@DBMeta(column = "pdfPath", name = "合同路径", type = "String")
	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
		this.settings.put("pdfPath", pdfPath);
	}


	@DBMeta(column = "createPerson", name = "作成者", type = "String")
	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
		this.settings.put("createPerson", createPerson);
	}

	@DBMeta(column = "createDate", name = "作成日期", type = "Date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
		this.settings.put("createDate", createDate);
	}

	@DBMeta(column = "updatePerson", name = "更新者", type = "String")
	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
		this.settings.put("updatePerson", updatePerson);
	}

	@DBMeta(column = "updateDate", name = "更新日期", type = "Date")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		this.settings.put("updateDate", updateDate);
	}

	public void setPdfData(byte[] pdfData) {
		this.pdfData = pdfData;
	}

	public byte[] getPdfData() {
		return pdfData;
	}
	@DBMeta(column = "sealDate", name = "盖章日期", type = "Date")
	public Date getSealDate() {
		return sealDate;
	}

	public void setSealDate(Date sealDate) {
		this.sealDate = sealDate;
		this.settings.put("sealDate", sealDate);
	}
	@DBMeta(column = "sealPerson", name = "盖章人", type = "String")
	public String getSealPerson() {
		return sealPerson;
	}

	public void setSealPerson(String sealPerson) {
		this.sealPerson = sealPerson;
		this.settings.put("sealPerson", sealPerson);
	}
	
	
}
