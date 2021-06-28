package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import java.util.Date;

import com.netcom.nkestate.common.ChineseHelper;
import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "BUYERINFO", sequence = "", id = DBModel.AssignedID)
public class BuyerInfoVO extends FHVO {

	private long contractID;//合同ID
	private int serial;//乙方顺序	从1开始采番 eg:第一个乙方=1，第二个乙方=2
	private String buyerName;//乙方名称
	private String buyerCardname;//证件名称	CODE506
	private String buyerNationality;//国籍	CODE504
	private String buyerSex;//性别	CODE505
	private String buyerBirth;//出生年月
	private String buyerAddress;//地址
	private String buyerPostcode;//邮编
	private String buyerCardcode;//身份证/护照/营业执照号码
	private String buyerCall;//电话
	private String buyerProxyType;//代理人类型（委托，法定）
	private String buyerProxy;//代理人
	private String buyerProxyAdr;//代理人地址
	private String buyerProxyCall;//代理人电话
	private String buyerProvince;//所在省市区分	code524
	private String buyerType;//个人/公司区分	code525
	private String verifyCode;//6位短信验证码
	private int signFlag;//0,未签字，1已签字
	private Date signDate;//签字日期

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "SERIAL", name = "乙方顺序", type = "int", primarykey = "true")
	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
		this.settings.put("serial", serial);
	}

	@DBMeta(column = "BUYER_NAME", name = "乙方名称", type = "String")
	public String getBuyerName() {
		return ChineseHelper.unicodeToUtf8(buyerName);
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
		this.settings.put("buyerName", buyerName);
	}

	@DBMeta(column = "BUYER_CARDNAME", name = "证件名称", type = "String", dict_table = "CT_506")
	public String getBuyerCardname() {
		return buyerCardname;
	}

	public void setBuyerCardname(String buyerCardname) {
		this.buyerCardname = buyerCardname;
		this.settings.put("buyerCardname", buyerCardname);
	}

	@DBMeta(column = "BUYER_NATIONALITY", name = "国籍", type = "String", dict_table = "CT_504")
	public String getBuyerNationality() {
		return buyerNationality;
	}

	public void setBuyerNationality(String buyerNationality) {
		this.buyerNationality = buyerNationality;
		this.settings.put("buyerNationality", buyerNationality);
	}

	@DBMeta(column = "BUYER_SEX", name = "性别", type = "String", dict_table = "CT_505")
	public String getBuyerSex() {
		return buyerSex;
	}

	public void setBuyerSex(String buyerSex) {
		this.buyerSex = buyerSex;
		this.settings.put("buyerSex", buyerSex);
	}

	@DBMeta(column = "BUYER_BIRTH", name = "出生年月", type = "String")
	public String getBuyerBirth() {
		return buyerBirth;
	}

	public void setBuyerBirth(String buyerBirth) {
		this.buyerBirth = buyerBirth;
		this.settings.put("buyerBirth", buyerBirth);
	}

	@DBMeta(column = "BUYER_ADDRESS", name = "地址", type = "String")
	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
		this.settings.put("buyerAddress", buyerAddress);
	}

	@DBMeta(column = "BUYER_POSTCODE", name = "邮编", type = "String")
	public String getBuyerPostcode() {
		return buyerPostcode;
	}

	public void setBuyerPostcode(String buyerPostcode) {
		this.buyerPostcode = buyerPostcode;
		this.settings.put("buyerPostcode", buyerPostcode);
	}

	@DBMeta(column = "BUYER_CARDCODE", name = "身份证/护照/营业执照号码", type = "String")
	public String getBuyerCardcode() {
		return buyerCardcode;
	}

	public void setBuyerCardcode(String buyerCardcode) {
		this.buyerCardcode = buyerCardcode;
		this.settings.put("buyerCardcode", buyerCardcode);
	}

	@DBMeta(column = "BUYER_CALL", name = "电话", type = "String")
	public String getBuyerCall() {
		return buyerCall;
	}

	public void setBuyerCall(String buyerCall) {
		this.buyerCall = buyerCall;
		this.settings.put("buyerCall", buyerCall);
	}

	@DBMeta(column = "BUYER_PROXY_TYPE", name = "代理人类型（委托，法定）", type = "String")
	public String getBuyerProxyType() {
		return buyerProxyType;
	}

	public void setBuyerProxyType(String buyerProxyType) {
		this.buyerProxyType = buyerProxyType;
		this.settings.put("buyerProxyType", buyerProxyType);
	}

	@DBMeta(column = "BUYER_PROXY", name = "代理人", type = "String")
	public String getBuyerProxy() {
		return buyerProxy;
	}

	public void setBuyerProxy(String buyerProxy) {
		this.buyerProxy = buyerProxy;
		this.settings.put("buyerProxy", buyerProxy);
	}

	@DBMeta(column = "BUYER_PROXY_ADR", name = "代理人地址", type = "String")
	public String getBuyerProxyAdr() {
		return buyerProxyAdr;
	}

	public void setBuyerProxyAdr(String buyerProxyAdr) {
		this.buyerProxyAdr = buyerProxyAdr;
		this.settings.put("buyerProxyAdr", buyerProxyAdr);
	}

	@DBMeta(column = "BUYER_PROXY_CALL", name = "代理人电话", type = "String")
	public String getBuyerProxyCall() {
		return buyerProxyCall;
	}

	public void setBuyerProxyCall(String buyerProxyCall) {
		this.buyerProxyCall = buyerProxyCall;
		this.settings.put("buyerProxyCall", buyerProxyCall);
	}

	@DBMeta(column = "BUYER_PROVINCE", name = "所在省市区分", type = "String", dict_table = "CT_524")
	public String getBuyerProvince() {
		return buyerProvince;
	}

	public void setBuyerProvince(String buyerProvince) {
		this.buyerProvince = buyerProvince;
		this.settings.put("buyerProvince", buyerProvince);
	}

	@DBMeta(column = "BUYER_TYPE", name = "个人/公司区分", type = "String", dict_table = "CT_525")
	public String getBuyerType() {
		return buyerType;
	}

	public void setBuyerType(String buyerType) {
		this.buyerType = buyerType;
		this.settings.put("buyerType", buyerType);
	}

	@DBMeta(column = "verifyCode", name = "短信验证码", type = "String")
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
		this.settings.put("verifyCode", verifyCode);
	}

	@DBMeta(column = "signFlag", name = "是否签字", type = "int")
	public int getSignFlag() {
		return signFlag;
	}

	public void setSignFlag(int signFlag) {
		this.signFlag = signFlag;
		this.settings.put("signFlag", signFlag);
	}

	public String getSignFlagStr() {
		if(signFlag == 0){
			return "未签字";
		}else if(signFlag == 1){
			return "已签字";
		}
		return "";
	}
	
	@DBMeta(column = "signDate", name = "签字日期", type = "Date")
	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
		this.settings.put("signDate", signDate);
	}
	
	
}
