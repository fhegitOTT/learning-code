package com.netcom.nkestate.fhhouse.company.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "EXCHANGERECORD", sequence = "SEQ_EXCHANGERECORD", id = DBModel.SequenceID)
public class ExchangeRecordVO extends FHVO {

	private long exchange_ID; // 	流水号
	private long comp_ID; // 	企业ID
	private int exType; // 	交易类型
	private double exCount; // 	交易金额
	private double balance; // 	交易后余额
	private String operator; // 	操作人

	@DBMeta(column = "Exchange_ID", name = "流水号", type = "long", primarykey = "true", can_update = "false")
	public long getExchange_ID() {
		return exchange_ID;
	}

	public void setExchange_ID(long Exchange_ID) {
		this.exchange_ID = Exchange_ID;
		this.settings.put("exchange_ID", exchange_ID);
	}

	@DBMeta(column = "Comp_ID", name = "企业ID", type = "long")
	public long getComp_ID() {
		return comp_ID;
	}

	public void setComp_ID(long Comp_ID) {
		this.comp_ID = Comp_ID;
		this.settings.put("comp_ID", comp_ID);
	}

	@DBMeta(column = "ExType", name = "交易类型", type = "int", dict_table = "CT_003")
	public int getExType() {
		return exType;
	}

	public void setExType(int ExType) {
		this.exType = ExType;
		this.settings.put("exType", exType);
	}

	@DBMeta(column = "ExCount", name = "交易金额", type = "double")
	public double getExCount() {
		return exCount;
	}

	public void setExCount(double ExCount) {
		this.exCount = ExCount;
		this.settings.put("exCount", exCount);
	}

	@DBMeta(column = "Balance", name = "交易后余额", type = "double")
	public double getBalance() {
		return balance;
	}

	public void setBalance(double Balance) {
		this.balance = Balance;
		this.settings.put("balance", balance);
	}

	@DBMeta(column = "Operator", name = "操作人", type = "String")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String Operator) {
		this.operator = Operator;
		this.settings.put("operator", operator);
	}



}
