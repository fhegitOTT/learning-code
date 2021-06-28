package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "PRESELL_DEPOSIT", sequence = "", id = DBModel.AssignedID)
public class PresellDepositVO extends AbstractBaseVO {

	private long contractID; //  	合同编号
	private double deposit; //  	预售定金
	private String depositCn; //  	预售定金大写
	private String payDate; //  	支付定金日期

	@DBMeta(column = "CONTRACT_ID", name = "合同编号", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "DEPOSIT", name = "预售定金", type = "double")
	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
		this.settings.put("deposit", deposit);
	}

	@DBMeta(column = "DEPOSIT_CN", name = "预售定金大写", type = "String")
	public String getDepositCn() {
		return depositCn;
	}

	public void setDepositCn(String depositCn) {
		this.depositCn = depositCn;
		this.settings.put("depositCn", depositCn);
	}

	@DBMeta(column = "PAY_DATE", name = "支付定金日期", type = "String")
	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
		this.settings.put("payDate", payDate);
	}

}
