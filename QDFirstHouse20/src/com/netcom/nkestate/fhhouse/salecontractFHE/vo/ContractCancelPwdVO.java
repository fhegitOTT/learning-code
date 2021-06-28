package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "CONTRACTCANCELPWD", sequence = "", id = DBModel.AssignedID)
public class ContractCancelPwdVO extends FHVO {

	private long contractID;//合同ID
	private int serial;//乙方顺序	一期中为USERNO
	private String pwd;//合同撤销密码

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "SERIAL", name = "乙方顺序", type = "int", primarykey = "true", can_update = "false")
	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
		this.settings.put("serial", serial);
	}

	@DBMeta(column = "PWD", name = "合同撤销密码", type = "String")
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
		this.settings.put("pwd", pwd);
	}

}
