package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "ATTACH4REAL", sequence = "SEQ_ATTACH4REAL_ID", id = DBModel.SequenceID)
public class Attach4RealVO extends FHVO {

	private long ID; //  	流水号
	private long contractID; //  	合同ID
	private String realName; //  	权利人
	private String allRealName; //  	共有人与共有情况
	private String location; //  	房产坐落
	private String startDate; //  	受理日期
	private String passDate; //  	核准日期
	private String putinDate; //  	注销日期
	private String realNo; //  	权证或证明号
	private String remark; //  	备注
	private String tradeType; //  	登记小类代码

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

	@DBMeta(column = "REALNAME", name = "权利人", type = "String")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
		this.settings.put("realName", realName);
	}

	@DBMeta(column = "ALLREALNAME", name = "共有人与共有情况", type = "String")
	public String getAllRealName() {
		return allRealName;
	}

	public void setAllRealName(String allRealName) {
		this.allRealName = allRealName;
		this.settings.put("allRealName", allRealName);
	}

	@DBMeta(column = "LOCATION", name = "房产坐落", type = "String")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
		this.settings.put("location", location);
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

	@DBMeta(column = "PUTINDATE", name = "注销日期", type = "String")
	public String getPutinDate() {
		return putinDate;
	}

	public void setPutinDate(String putinDate) {
		this.putinDate = putinDate;
		this.settings.put("putinDate", putinDate);
	}

	@DBMeta(column = "REALNO", name = "权证或证明号", type = "String")
	public String getRealNo() {
		return realNo;
	}

	public void setRealNo(String realNo) {
		this.realNo = realNo;
		this.settings.put("realNo", realNo);
	}

	@DBMeta(column = "REMARK", name = "备注", type = "String")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		this.settings.put("remark", remark);
	}

	@DBMeta(column = "TRADETYPE", name = "登记小类代码", type = "String")
	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
		this.settings.put("tradeType", tradeType);
	}

}
