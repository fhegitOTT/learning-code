package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "PRESELL_ATTACH2MONEYRECORD", sequence = "", id = DBModel.AssignedID)
public class PresellAttach2MoneyRecordVO extends AbstractBaseVO {

	private long contractID; //  	合同号

	@DBMeta(column = "CONTRACT_ID", name = "合同号", type = "long")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

}
