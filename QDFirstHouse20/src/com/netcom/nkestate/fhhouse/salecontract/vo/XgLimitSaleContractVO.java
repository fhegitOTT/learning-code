package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;


@DBModel(tablename = "XG_LIMITSALE_CONTRACT", sequence = "", id = DBModel.AssignedID)
public class XgLimitSaleContractVO extends AbstractBaseVO {

	private long contractID;//合同ID
	private long limitSaleID;//编号ID

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "LIMITSALEID", name = "编号ID", type = "long", primarykey = "true", can_update = "false")
	public long getLimitSaleID() {
		return limitSaleID;
	}

	public void setLimitSaleID(long limitSaleID) {
		this.limitSaleID = limitSaleID;
		this.settings.put("limitSaleID", limitSaleID);
	}

}

