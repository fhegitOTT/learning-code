package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "TODAY_CONTRACTDEAL", sequence = "", id = DBModel.AssignedID)
public class TodayContractDealVO extends FHVO {

	private long contractID;//合同ID
	private long houseID;//房屋ID		一期中为BARCODE        
	private int status;//状态
	private String signer;//签约人帐号
	private long signdate;//签约时间
	private String confirmer;//合同确认人
	private long confirmDate;//确认时间
	private long operateDate;//状态变迁日期
	private int type;//合同类型

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "HOUSE_ID", name = "房屋ID", type = "long")
	public long getHouseID() {
		return houseID;
	}

	public void setHouseID(long houseID) {
		this.houseID = houseID;
		this.settings.put("houseID", houseID);
	}

	@DBMeta(column = "STATUS", name = "状态", type = "int")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}

	@DBMeta(column = "SIGNER", name = "签约人帐号", type = "String")
	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
		this.settings.put("signer", signer);
	}

	@DBMeta(column = "SIGNDATE", name = "签约时间", type = "long")
	public long getSigndate() {
		return signdate;
	}

	public void setSigndate(long signdate) {
		this.signdate = signdate;
		this.settings.put("signdate", signdate);
	}

	@DBMeta(column = "CONFIRMER", name = "合同确认人", type = "String")
	public String getConfirmer() {
		return confirmer;
	}

	public void setConfirmer(String confirmer) {
		this.confirmer = confirmer;
		this.settings.put("confirmer", confirmer);
	}

	@DBMeta(column = "CONFIRMDATE", name = "确认时间", type = "long")
	public long getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(long confirmDate) {
		this.confirmDate = confirmDate;
		this.settings.put("confirmDate", confirmDate);
	}

	@DBMeta(column = "OPERATEDATE", name = "状态变迁日期", type = "long")
	public long getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(long operateDate) {
		this.operateDate = operateDate;
		this.settings.put("operateDate", operateDate);
	}

	@DBMeta(column = "TYPE", name = "合同类型", type = "int")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		this.settings.put("type", type);
	}

}
