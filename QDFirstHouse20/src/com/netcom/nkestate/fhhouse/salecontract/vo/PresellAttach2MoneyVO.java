package com.netcom.nkestate.fhhouse.salecontract.vo;

import java.text.DecimalFormat;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "PRESELL_ATTACH2MONEY", sequence = "SEQ_ATTACH2MONEYID", id = DBModel.SequenceID)
public class PresellAttach2MoneyVO extends AbstractBaseVO {

	private long attach2MoneyID; //  	ID
	private long contractID; //  	合同号
	private String paymentMode; //  	支付方式
	private String borrowMode; //  	贷款方式
	private int state; //  	支付方式标志	1为一次性付款2为分期付款3为贷款中的首付分期4为贷款分期
	private int stateSign; //  	贷款方式标志	分期付款分为多次付款:0,1,2···首付款和贷款同上
	private double totalMoney; //  	房屋总价款
	private double deposit; //  	定金
	private int fkDate; //  	付款时间
	private double money; //  	金额
	private String bankName; //  	贷款缴纳机构
	private String dkDate; //  	贷款缴纳时间
	private String contentText; //  	备注（补充条款）
	private long creDate; //  	作成日期
	private long creTime; //  	作成时间
	private long updDate; //  	更新日期
	private long updTime; //  	更新时间

	@DBMeta(column = "ATTACH2MONEYID", name = "ID", type = "long", primarykey = "true", can_update = "false")
	public long getAttach2MoneyID() {
		return attach2MoneyID;
	}

	public void setAttach2MoneyID(long attach2MoneyID) {
		this.attach2MoneyID = attach2MoneyID;
		this.settings.put("attach2MoneyID", attach2MoneyID);
	}

	@DBMeta(column = "CONTRACT_ID", name = "合同号", type = "long")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "PAYMENTMODE", name = "支付方式", type = "String")
	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
		this.settings.put("paymentMode", paymentMode);
	}

	@DBMeta(column = "BORROWMODE", name = "贷款方式", type = "String")
	public String getBorrowMode() {
		return borrowMode;
	}

	public void setBorrowMode(String borrowMode) {
		this.borrowMode = borrowMode;
		this.settings.put("borrowMode", borrowMode);
	}

	@DBMeta(column = "STATE", name = "支付方式标志", type = "int")
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		this.settings.put("state", state);
	}

	@DBMeta(column = "STATESIGN", name = "贷款方式标志", type = "int")
	public int getStateSign() {
		return stateSign;
	}

	public void setStateSign(int stateSign) {
		this.stateSign = stateSign;
		this.settings.put("stateSign", stateSign);
	}

	@DBMeta(column = "TOTALMONEY", name = "房屋总价款", type = "double")
	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
		this.settings.put("totalMoney", totalMoney);
	}

	@DBMeta(column = "DEPOSIT", name = "定金", type = "double")
	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
		this.settings.put("deposit", deposit);
	}

	@DBMeta(column = "FKDATE", name = "付款时间", type = "int")
	public int getFkDate() {
		return fkDate;
	}

	public void setFkDate(int fkDate) {
		this.fkDate = fkDate;
		this.settings.put("fkDate", fkDate);
	}

	@DBMeta(column = "MONEY", name = "金额", type = "double")
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
		this.settings.put("money", money);
	}

	@DBMeta(column = "BANKNAME", name = "贷款缴纳机构", type = "String")
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
		this.settings.put("bankName", bankName);
	}

	@DBMeta(column = "DKDATE", name = "贷款缴纳时间", type = "String")
	public String getDkDate() {
		return dkDate;
	}

	public void setDkDate(String dkDate) {
		this.dkDate = dkDate;
		this.settings.put("dkDate", dkDate);
	}

	@DBMeta(column = "CONTENTTEXT", name = "备注", type = "String")
	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
		this.settings.put("contentText", contentText);
	}

	@DBMeta(column = "CREDATE", name = "作成日期", type = "long")
	public long getCreDate() {
		return creDate;
	}

	public void setCreDate(long creDate) {
		this.creDate = creDate;
		this.settings.put("creDate", creDate);
	}

	@DBMeta(column = "CRETIME", name = "作成时间", type = "long")
	public long getCreTime() {
		return creTime;
	}

	public void setCreTime(long creTime) {
		this.creTime = creTime;
		this.settings.put("creTime", creTime);
	}

	@DBMeta(column = "UPDDATE", name = "更新日期", type = "long")
	public long getUpdDate() {
		return updDate;
	}

	public void setUpdDate(long updDate) {
		this.updDate = updDate;
		this.settings.put("updDate", updDate);
	}

	@DBMeta(column = "UPDTIME", name = "更新时间", type = "long")
	public long getUpdTime() {
		return updTime;
	}

	public void setUpdTime(long updTime) {
		this.updTime = updTime;
		this.settings.put("updTime", updTime);
	}

	public String getStateStr() {
		if(state == 9){
			return "定金";
		}else if(state == 4){
			return "贷款";
		}else{
			return "房款";
		}
	}

	public String getTotalMoneyStr() {
		DecimalFormat df = new DecimalFormat("#.00");
		String tm = df.format(totalMoney);
		return tm;

	}

	public String getMoneyStr() {
		DecimalFormat df = new DecimalFormat("#.00");
		String tm = df.format(money);
		return tm;

	}

}
