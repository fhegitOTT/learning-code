package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.common.ChineseHelper;
import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "SS_CONTRACT_FHE_BUYER", sequence = "", id = DBModel.AssignedID)
//public class BuyerInfoVOFHE  extends FHVO {
public class BuyerInfoVOFHE extends AbstractBaseVO {

	private long contractID;//合同ID  //1
	private int serial;//乙方顺序	从1开始采番 eg:第一个乙方=1，第二个乙方=2  //1
	private String buyerName;//乙方名称   //1
	private String buyerSex;//性别	CODE505  //1
	private String buyerAddress;//地址         //1
	private String buyerPostcode;//邮编      //1
	private String buyerCall;//电话     //1
	private int signFlag;//0,未签字，1已签字  //1

	//private Date signDate;//签字日期

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

	@DBMeta(column = "BUYERNAME", name = "乙方名称", type = "String")
		public String getBuyerName() {
			return ChineseHelper.unicodeToUtf8(buyerName);
		}

		public void setBuyerName(String buyerName) {
			this.buyerName = buyerName;
			this.settings.put("buyerName", buyerName);
		}


	@DBMeta(column = "SEX", name = "性别", type = "String", dict_table = "CT_505")
		public String getBuyerSex() {
			return buyerSex;
		}

		public void setBuyerSex(String buyerSex) {
			this.buyerSex = buyerSex;
			this.settings.put("buyerSex", buyerSex);
		}



	@DBMeta(column = "ADDRESS", name = "地址", type = "String")
		public String getBuyerAddress() {
			return buyerAddress;
		}

		public void setBuyerAddress(String buyerAddress) {
			this.buyerAddress = buyerAddress;
			this.settings.put("buyerAddress", buyerAddress);
		}

	@DBMeta(column = "EMAIL", name = "邮编", type = "String")
		public String getBuyerPostcode() {
			return buyerPostcode;
		}

		public void setBuyerPostcode(String buyerPostcode) {
			this.buyerPostcode = buyerPostcode;
			this.settings.put("buyerPostcode", buyerPostcode);
		}



	@DBMeta(column = "TELEPHONE", name = "电话", type = "String")
		public String getBuyerCall() {
			return buyerCall;
		}

		public void setBuyerCall(String buyerCall) {
			this.buyerCall = buyerCall;
			this.settings.put("buyerCall", buyerCall);
		}


	@DBMeta(column = "SIGNFLAG", name = "是否签字", type = "int")
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
		

		
	}
