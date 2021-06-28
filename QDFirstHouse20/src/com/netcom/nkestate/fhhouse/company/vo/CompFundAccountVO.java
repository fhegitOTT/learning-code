package com.netcom.nkestate.fhhouse.company.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "COMPFUNDACCOUNT", sequence = "", id = DBModel.AssignedID)
public class CompFundAccountVO extends FHVO {

	private long comp_ID; // 	企业ID
	private double freezeMoney; // 	冻结金额
	private double balance; // 	余额

	@DBMeta(column = "Comp_ID", name = "企业ID", type = "long", primarykey = "true", can_update = "false")
	public long getComp_ID() {
		return comp_ID;
	}

	public void setComp_ID(long Comp_ID) {
		this.comp_ID = Comp_ID;
		this.settings.put("comp_ID", comp_ID);
	}

	@DBMeta(column = "FreezeMoney", name = "冻结金额", type = "double")
	public double getFreezeMoney() {
		return freezeMoney;
	}

	public void setFreezeMoney(double FreezeMoney) {
		this.freezeMoney = FreezeMoney;
		this.settings.put("freezeMoney", freezeMoney);
	}

	@DBMeta(column = "Balance", name = "余额", type = "double")
	public double getBalance() {
		return balance;
	}

	public void setBalance(double Balance) {
		this.balance = Balance;
		this.settings.put("balance", balance);
	}

}
