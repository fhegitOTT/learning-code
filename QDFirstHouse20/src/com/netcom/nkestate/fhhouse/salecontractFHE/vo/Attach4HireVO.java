package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "ATTACH4HIRE", sequence = "", id = DBModel.AssignedID)
public class Attach4HireVO extends FHVO {

	private long ID; //  	流水号
	private long contractID; //  	合同ID
	private String lessor; //  	出租人
	private String lendType; //  	租赁类型
	private String lessee; //  	承租人
	private String lendusage; //  	租赁用途
	private String limitPart; //  	房屋坐落
	private String timeBetween; //  	期限
	private String rightNo; //  	权证或证明号
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

	@DBMeta(column = "LESSOR", name = "出租人", type = "String")
	public String getLessor() {
		return lessor;
	}

	public void setLessor(String lessor) {
		this.lessor = lessor;
		this.settings.put("lessor", lessor);
	}

	@DBMeta(column = "lendType", name = "租赁类型", type = "String")
	public String getLendType() {
		return lendType;
	}

	public void setLendType(String lendType) {
		this.lendType = lendType;
		this.settings.put("lendType", lendType);
	}

	@DBMeta(column = "LESSEE", name = "承租人", type = "String")
	public String getLessee() {
		return lessee;
	}

	public void setLessee(String lessee) {
		this.lessee = lessee;
		this.settings.put("lessee", lessee);
	}

	@DBMeta(column = "LENDUSAGE", name = "租赁用途", type = "String")
	public String getLendusage() {
		return lendusage;
	}

	public void setLendusage(String lendusage) {
		this.lendusage = lendusage;
		this.settings.put("lendusage", lendusage);
	}

	@DBMeta(column = "LIMITPART", name = "房屋坐落", type = "String")
	public String getLimitPart() {
		return limitPart;
	}

	public void setLimitPart(String limitPart) {
		this.limitPart = limitPart;
		this.settings.put("limitPart", limitPart);
	}

	@DBMeta(column = "TIMEBETWEEN", name = "期限", type = "String")
	public String getTimeBetween() {
		return timeBetween;
	}

	public void setTimeBetween(String timeBetween) {
		this.timeBetween = timeBetween;
		this.settings.put("timeBetween", timeBetween);
	}

	@DBMeta(column = "RIGHTNO", name = "权证或证明号", type = "String")
	public String getRightNo() {
		return rightNo;
	}

	public void setRightNo(String rightNo) {
		this.rightNo = rightNo;
		this.settings.put("rightNo", rightNo);
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
