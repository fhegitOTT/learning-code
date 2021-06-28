package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "ATTACH4LIMIT", sequence = "SEQ_ATTACH4LIMIT_ID", id = DBModel.SequenceID)
public class Attach4LimitVO extends FHVO {

	private long ID; //  	流水号
	private long contractID; //  	合同ID
	private String nameb; //  	被限制人
	private String limitPart; //  	房屋坐落
	private String names; //  	限制人
	private String limitNo; //  	权证或证明号
	private String planStartDate; //  	起始日期
	private String planEndDate; //  	结束日期
	private String startDate; //  	受理日期
	private String passDate; //  	核准日期
	private String remark; // 	备注

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

	@DBMeta(column = "NAMEB", name = "被限制人", type = "String")
	public String getNameb() {
		return nameb;
	}

	public void setNameb(String nameb) {
		this.nameb = nameb;
		this.settings.put("nameb", nameb);
	}

	@DBMeta(column = "LIMITPART", name = "房屋坐落", type = "String")
	public String getLimitPart() {
		return limitPart;
	}

	public void setLimitPart(String limitPart) {
		this.limitPart = limitPart;
		this.settings.put("limitPart", limitPart);
	}

	@DBMeta(column = "NAMES", name = "限制人", type = "String")
	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
		this.settings.put("names", names);
	}

	@DBMeta(column = "LIMITNO", name = "权证或证明号", type = "String")
	public String getLimitNo() {
		return limitNo;
	}

	public void setLimitNo(String limitNo) {
		this.limitNo = limitNo;
		this.settings.put("limitNo", limitNo);
	}

	@DBMeta(column = "PLANSTARTDATE", name = "起始日期", type = "String")
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
