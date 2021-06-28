package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "XG_BUYEROWNERNAME", sequence = "", id = DBModel.AssignedID)
public class XgBuyerOwnerNameVO extends AbstractBaseVO {

	private long buyerRightID; //  	申请人ID
	private long limitSaleID; //  	编号ID
	private String name; //  	姓名
	private String localCode; //  	户籍
	private long cercardTypeID; //  	证件类型
	private String cardNo; //  	证件号码
	private String rightConseqNum; //  	权利顺序
	private String memo; //  	备注
	private int member; //  	与户主关系    '1', '户主' '2', '配偶' '3', '未成年子女'
	private int buyerDistrictID; // 	该住房申请区市

	@DBMeta(column = "BUYERRIGHTID", name = "申请人ID", type = "long", primarykey = "true", can_update = "false")
	public long getBuyerRightID() {
		return buyerRightID;
	}

	public void setBuyerRightID(long buyerRightID) {
		this.buyerRightID = buyerRightID;
		this.settings.put("buyerRightID", buyerRightID);
	}

	@DBMeta(column = "LIMITSALEID", name = "编号ID", type = "long")
	public long getLimitSaleID() {
		return limitSaleID;
	}

	public void setLimitSaleID(long limitSaleID) {
		this.limitSaleID = limitSaleID;
		this.settings.put("limitSaleID", limitSaleID);
	}

	@DBMeta(column = "NAME", name = "姓名", type = "String")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.settings.put("name", name);
	}

	@DBMeta(column = "LOCALCODE", name = "户籍", type = "String")
	public String getLocalCode() {
		return localCode;
	}

	public void setLocalCode(String localCode) {
		this.localCode = localCode;
		this.settings.put("localCode", localCode);
	}

	@DBMeta(column = "CERCARDTYPEID", name = "证件类型", type = "long")
	public long getCercardTypeID() {
		return cercardTypeID;
	}

	public void setCercardTypeID(long cercardTypeID) {
		this.cercardTypeID = cercardTypeID;
		this.settings.put("cercardTypeID", cercardTypeID);
	}

	@DBMeta(column = "CARDNO", name = "证件号码", type = "String")
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
		this.settings.put("cardNo", cardNo);
	}

	@DBMeta(column = "RIGHTCONSEQNUM", name = "权利顺序", type = "String")
	public String getRightConseqNum() {
		return rightConseqNum;
	}

	public void setRightConseqNum(String rightConseqNum) {
		this.rightConseqNum = rightConseqNum;
		this.settings.put("rightConseqNum", rightConseqNum);
	}

	@DBMeta(column = "MEMO", name = "备注", type = "String")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
		this.settings.put("memo", memo);
	}

	@DBMeta(column = "MEMBER", name = "与户主关系", type = "int")
	public int getMember() {
		return member;
	}

	public void setMember(int member) {
		this.member = member;
		this.settings.put("member", member);
	}

	@DBMeta(column = "BUYERDISTRICTID", name = "该住房申请区市", type = "int")
	public int getBuyerDistrictID() {
		return buyerDistrictID;
	}

	public void setBuyerDistrictID(int buyerDistrictID) {
		this.buyerDistrictID = buyerDistrictID;
		this.settings.put("buyerDistrictID", buyerDistrictID);
	}

}
