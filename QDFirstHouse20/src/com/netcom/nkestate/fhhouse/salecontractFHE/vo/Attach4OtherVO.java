package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "ATTACH4OTHER", sequence = "SEQ_ATTACH4OTHER_ID", id = DBModel.SequenceID)
public class Attach4OtherVO extends FHVO {

	private long ID; //  	流水号
	private long contractID; //  	合同ID
	private String otherRightType; //  	权利种类
	private String debtAccount; //  	债权金额
	private String otherRightPart; //  	房产坐落
	private String otherRightNo; //  	权证或证明号
	private String ownerName; //  	他项权利人
	private String planStartDate; //  	设定日期
	private String planEndDate; //  	结束日期
	private String startDate; //  	受理日期
	private String passDate; //  	核准日期
	private String remark; //  	备注

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

	@DBMeta(column = "OTHERRIGHTTYPE", name = "权利种类", type = "String")
	public String getOtherRightType() {
		return otherRightType;
	}

	public void setOtherRightType(String otherRightType) {
		this.otherRightType = otherRightType;
		this.settings.put("otherRightType", otherRightType);
	}

	@DBMeta(column = "DEBTACCOUNT", name = "债权金额", type = "String")
	public String getDebtAccount() {
		return debtAccount;
	}

	public void setDebtAccount(String debtAccount) {
		this.debtAccount = debtAccount;
		this.settings.put("debtAccount", debtAccount);
	}

	@DBMeta(column = "OTHERRIGHTPART", name = "房产坐落", type = "String")
	public String getOtherRightPart() {
		return otherRightPart;
	}

	public void setOtherRightPart(String otherRightPart) {
		this.otherRightPart = otherRightPart;
		this.settings.put("otherRightPart", otherRightPart);
	}

	@DBMeta(column = "OTHERRIGHTNO", name = "权证或证明号", type = "String")
	public String getOtherRightNo() {
		return otherRightNo;
	}

	public void setOtherRightNo(String otherRightNo) {
		this.otherRightNo = otherRightNo;
		this.settings.put("otherRightNo", otherRightNo);
	}

	@DBMeta(column = "OWNERNAME", name = "他项权利人", type = "String")
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
		this.settings.put("ownerName", ownerName);
	}

	@DBMeta(column = "PLANSTARTDATE", name = "设定日期", type = "String")
	public String getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(String planStartDate) {
		this.planStartDate = planStartDate;
		this.settings.put("planStartDate", planStartDate);
	}

	@DBMeta(column = "PLANENDDATE", name = "结束日期", type = "String")
	public String getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(String planEndDate) {
		this.planEndDate = planEndDate;
		this.settings.put("planEndDate", planEndDate);
	}

	@DBMeta(column = "STARTDATE", name = "受理日期", type = "String")
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
		this.settings.put("startDate", startDate);
	}

	@DBMeta(column = "PASSDATE", name = "核准日期", type = "String")
	public String getPassDate() {
		return passDate;
	}

	public void setPassDate(String passDate) {
		this.passDate = passDate;
		this.settings.put("passDate", passDate);
	}

	@DBMeta(column = "REMARK", name = "备注", type = "String")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		this.settings.put("remark", remark);
	}

}
