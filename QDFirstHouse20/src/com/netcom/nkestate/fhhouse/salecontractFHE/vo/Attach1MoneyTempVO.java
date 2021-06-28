package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "ATTACH1MONEYTEMP", sequence = "SEQ_ATTACH1MONEYTEMP_ID", id = DBModel.SequenceID)
public class Attach1MoneyTempVO extends FHVO {

	private long ID;//流水号
	private long templateID;//	模版ID
	private int serial;//	款项序号	从1开始采番，eg:首付=1，第二笔=2，第三笔=3
	private long paymentDate;//	付款时间
	private double money;//	付款金额
	private String moneyCn;//	付房款金额(大写)

	@DBMeta(column = "ID", name = "流水号", type = "long", primarykey = "true", can_update = "false")
	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
		this.settings.put("ID", ID);
	}

	@DBMeta(column = "TEMPLATE_ID", name = "模板ID", type = "long", primarykey = "true")
	public long getTemplateID() {
		return templateID;
	}

	public void setTemplateID(long templateID) {
		this.templateID = templateID;
		this.settings.put("templateID", templateID);
	}

	@DBMeta(column = "SERIAL", name = "款项序号", type = "int")
	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
		this.settings.put("serial", serial);
	}

	@DBMeta(column = "PAYMENTDATE", name = "付款时间", type = "long")
	public long getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(long paymentDate) {
		this.paymentDate = paymentDate;
		this.settings.put("paymentDate", paymentDate);
	}

	@DBMeta(column = "MONEY", name = "付款金额", type = "double")
	public double getMoney() {
		return money;
	}


	public void setMoney(double money) {
		this.money = money;
		this.settings.put("money", money);
	}

	@DBMeta(column = "MONEYCN", name = "付房款金额(大写)", type = "String")
	public String getMoneyCn() {
		return moneyCn;
	}


	public void setMoneyCn(String moneyCn) {
		this.moneyCn = moneyCn;
		this.settings.put("moneyCn", moneyCn);
	}

}

