package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import java.util.Date;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;
import com.netcom.nkestate.framework.util.DateUtil;

@DBModel(tablename = "CANCEL_CENSOR", sequence = "SEQ_CANCEL_CENSOR_ID", id = DBModel.SequenceID)
public class CancelCensorVO extends FHVO {

	private long ID; //  	流水号
	private long contractID; //  	合同ID
	private String location; //  	房屋地址
	private String confirmDate; //  	签约确认日期
	private double area; //  	面积
	private double cost; //  	总价
	private int status; //  	状态		CODE534 1:复核、2:信息已发布、3:审核不通过、4:审批、5:审批退回、6:审批通过、7:发布失败
	private String proposer1; //  	申请人1
	private String proposer2; //  	申请人2
	private String proxy1; //  	代理1
	private String proxy2; //  	代理2
	private String proposer1Call; //  	申请人1电话
	private String proposer2Call; //  	申请人2电话
	private String proxy1Call; //  	代理1电话
	private String proxy2Call; //  	代理2电话
	private String proposer1CardName; //  	申请人1证件名称(身份证/护照/营业执照)
	private String proposer2CardName; //  	申请人2证件名称(身份证/护照/营业执照)
	private String proxy1CardName; //  	代理1证件名称(身份证/护照/营业执照)
	private String proxy2CardName; //  	代理2证件名称(身份证/护照/营业执照)
	private String proposer1CardCode; //  	申请人1证件编号(身份证/护照/营业执照)
	private String proposer2CardCode; //  	申请人2证件编号(身份证/护照/营业执照)
	private String proxy1CardCode; //  	代理1证件编号(身份证/护照/营业执照)
	private String proxy2CardCode; //  	代理2证件编号(身份证/护照/营业执照)
	private String proposer1Address; //  	申请人1地址
	private String proposer2Address; //  	申请人2地址
	private String proxy1Address; //  	代理1地址
	private String proxy2Address; //  	代理2地址
	private String proposer1PostCode; //  	申请人1邮编
	private String proposer2PostCode; //  	申请人2邮编
	private String proxy1PostCode; //  	代理1邮编
	private String proxy2PostCode; //  	代理2邮编
	private String cancelComment; //  	备注
	private int contType; //  	合同类型	CODE546 1:预售合同、2:出售合同、3:定金合同
	private int earnestType; //  	定金合同撤销类型		CODE548 0:非连带撤销、1:被预/出售合同连带撤销
	private int reasonType; //  	撤销原因类型	CODE551 1:资金问题、2:输入错误、3:换房退房、4:增减权利人、5:楼盘表调整、6:其他
	private String firstCensor; //  	经办人
	private long firstDate; //  	经办日期
	private String firstNotion; //  	经办意见
	private String earlyCensor; //  	复核人
	private long earlyDate; //  	复核日期
	private String earlyNotion; //  	复核意见
	private String finalCensor; //  	审批人
	private long finalDate; //  	审批日期
	private String finalNotion; //  	审批意见
	private String publishCensor; // 发布人
	private Date publishDate; //  	发布日期
	private String publishNotion; //  	发布意见

	@DBMeta(column = "ID", name = "流水号", type = "long", primarykey = "true", can_update = "false")
	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
		this.settings.put("ID", ID);
	}

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "LOCATION", name = "房屋地址", type = "String")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
		this.settings.put("location", location);
	}

	@DBMeta(column = "CONFIRMDATE", name = "签约确认日期", type = "String")
	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
		this.settings.put("confirmDate", confirmDate);
	}

	@DBMeta(column = "AREA", name = "面积", type = "double")
	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
		this.settings.put("area", area);
	}

	@DBMeta(column = "COST", name = "总价", type = "double")
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
		this.settings.put("cost", cost);
	}

	@DBMeta(column = "STATUS", name = "状态", type = "int", dict_table = "CT_534")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}

	@DBMeta(column = "PROPOSER1", name = "申请人1", type = "String")
	public String getProposer1() {
		return proposer1;
	}

	public void setProposer1(String proposer1) {
		this.proposer1 = proposer1;
		this.settings.put("proposer1", proposer1);
	}

	@DBMeta(column = "PROPOSER2", name = "申请人2", type = "String")
	public String getProposer2() {
		return proposer2;
	}

	public void setProposer2(String proposer2) {
		this.proposer2 = proposer2;
		this.settings.put("proposer2", proposer2);
	}

	@DBMeta(column = "PROXY1", name = "代理1", type = "String")
	public String getProxy1() {
		return proxy1;
	}

	public void setProxy1(String proxy1) {
		this.proxy1 = proxy1;
		this.settings.put("proxy1", proxy1);
	}

	@DBMeta(column = "PROXY2", name = "代理2", type = "String")
	public String getProxy2() {
		return proxy2;
	}

	public void setProxy2(String proxy2) {
		this.proxy2 = proxy2;
		this.settings.put("proxy2", proxy2);
	}

	@DBMeta(column = "PROPOSER1_CALL", name = "申请人1电话", type = "String")
	public String getProposer1Call() {
		return proposer1Call;
	}

	public void setProposer1Call(String proposer1Call) {
		this.proposer1Call = proposer1Call;
		this.settings.put("proposer1Call", proposer1Call);
	}

	@DBMeta(column = "PROPOSER2_CALL", name = "申请人2电话", type = "String")
	public String getProposer2Call() {
		return proposer2Call;
	}

	public void setProposer2Call(String proposer2Call) {
		this.proposer2Call = proposer2Call;
		this.settings.put("proposer2Call", proposer2Call);
	}

	@DBMeta(column = "PROXY1_CALL", name = "代理1电话", type = "String")
	public String getProxy1Call() {
		return proxy1Call;
	}

	public void setProxy1Call(String proxy1Call) {
		this.proxy1Call = proxy1Call;
		this.settings.put("proxy1Call", proxy1Call);
	}

	@DBMeta(column = "PROXY2_CALL", name = "代理2电话", type = "String")
	public String getProxy2Call() {
		return proxy2Call;
	}

	public void setProxy2Call(String proxy2Call) {
		this.proxy2Call = proxy2Call;
		this.settings.put("proxy2Call", proxy2Call);
	}

	@DBMeta(column = "PROPOSER1_CARDNAME", name = "申请人1证件名称", type = "String")
	public String getProposer1CardName() {
		return proposer1CardName;
	}

	public void setProposer1CardName(String proposer1CardName) {
		this.proposer1CardName = proposer1CardName;
		this.settings.put("proposer1CardName", proposer1CardName);
	}

	@DBMeta(column = "PROPOSER2_CARDNAME", name = "申请人2证件名称", type = "String")
	public String getProposer2CardName() {
		return proposer2CardName;
	}

	public void setProposer2CardName(String proposer2CardName) {
		this.proposer2CardName = proposer2CardName;
		this.settings.put("proposer2CardName", proposer2CardName);
	}

	@DBMeta(column = "PROXY1_CARDNAME", name = "代理1证件名称", type = "String")
	public String getProxy1CardName() {
		return proxy1CardName;
	}

	public void setProxy1CardName(String proxy1CardName) {
		this.proxy1CardName = proxy1CardName;
		this.settings.put("proxy1CardName", proxy1CardName);
	}

	@DBMeta(column = "PROXY2_CARDNAME", name = "代理2证件名称", type = "String")
	public String getProxy2CardName() {
		return proxy2CardName;
	}

	public void setProxy2CardName(String proxy2CardName) {
		this.proxy2CardName = proxy2CardName;
		this.settings.put("proxy2CardName", proxy2CardName);
	}

	@DBMeta(column = "PROPOSER1_CARDCODE", name = "申请人1证件编号", type = "String")
	public String getProposer1CardCode() {
		return proposer1CardCode;
	}

	public void setProposer1CardCode(String proposer1CardCode) {
		this.proposer1CardCode = proposer1CardCode;
		this.settings.put("proposer1CardCode", proposer1CardCode);
	}

	@DBMeta(column = "PROPOSER2_CARDCODE", name = "申请人2证件编号", type = "String")
	public String getProposer2CardCode() {
		return proposer2CardCode;
	}

	public void setProposer2CardCode(String proposer2CardCode) {
		this.proposer2CardCode = proposer2CardCode;
		this.settings.put("proposer2CardCode", proposer2CardCode);
	}

	@DBMeta(column = "PROXY1_CARDCODE", name = "代理1证件编号", type = "String")
	public String getProxy1CardCode() {
		return proxy1CardCode;
	}

	public void setProxy1CardCode(String proxy1CardCode) {
		this.proxy1CardCode = proxy1CardCode;
		this.settings.put("proxy1CardCode", proxy1CardCode);
	}

	@DBMeta(column = "PROXY2_CARDCODE", name = "代理2证件编号", type = "String")
	public String getProxy2CardCode() {
		return proxy2CardCode;
	}

	public void setProxy2CardCode(String proxy2CardCode) {
		this.proxy2CardCode = proxy2CardCode;
		this.settings.put("proxy2CardCode", proxy2CardCode);
	}

	@DBMeta(column = "PROPOSER1_ADDRESS", name = "申请人1地址", type = "String")
	public String getProposer1Address() {
		return proposer1Address;
	}

	public void setProposer1Address(String proposer1Address) {
		this.proposer1Address = proposer1Address;
		this.settings.put("proposer1Address", proposer1Address);
	}

	@DBMeta(column = "PROPOSER2_ADDRESS", name = "申请人2地址", type = "String")
	public String getProposer2Address() {
		return proposer2Address;
	}

	public void setProposer2Address(String proposer2Address) {
		this.proposer2Address = proposer2Address;
		this.settings.put("proposer2Address", proposer2Address);
	}

	@DBMeta(column = "PROXY1_ADDRESS", name = "代理1地址", type = "String")
	public String getProxy1Address() {
		return proxy1Address;
	}

	public void setProxy1Address(String proxy1Address) {
		this.proxy1Address = proxy1Address;
		this.settings.put("proxy1Address", proxy1Address);
	}

	@DBMeta(column = "PROXY2_ADDRESS", name = "代理2地址", type = "String")
	public String getProxy2Address() {
		return proxy2Address;
	}

	public void setProxy2Address(String proxy2Address) {
		this.proxy2Address = proxy2Address;
		this.settings.put("proxy2Address", proxy2Address);
	}

	@DBMeta(column = "PROPOSER1_POSTCODE", name = "申请人1邮编", type = "String")
	public String getProposer1PostCode() {
		return proposer1PostCode;
	}

	public void setProposer1PostCode(String proposer1PostCode) {
		this.proposer1PostCode = proposer1PostCode;
		this.settings.put("proposer1PostCode", proposer1PostCode);
	}

	@DBMeta(column = "PROPOSER2_POSTCODE", name = "申请人2邮编", type = "String")
	public String getProposer2PostCode() {
		return proposer2PostCode;
	}

	public void setProposer2PostCode(String proposer2PostCode) {
		this.proposer2PostCode = proposer2PostCode;
		this.settings.put("proposer2PostCode", proposer2PostCode);
	}

	@DBMeta(column = "PROXY1_POSTCODE", name = "代理1邮编", type = "String")
	public String getProxy1PostCode() {
		return proxy1PostCode;
	}

	public void setProxy1PostCode(String proxy1PostCode) {
		this.proxy1PostCode = proxy1PostCode;
		this.settings.put("proxy1PostCode", proxy1PostCode);
	}

	@DBMeta(column = "PROXY2_POSTCODE", name = "代理2邮编", type = "String")
	public String getProxy2PostCode() {
		return proxy2PostCode;
	}

	public void setProxy2PostCode(String proxy2PostCode) {
		this.proxy2PostCode = proxy2PostCode;
		this.settings.put("proxy2PostCode", proxy2PostCode);
	}

	@DBMeta(column = "CANCEL_COMMENT", name = "备注", type = "String")
	public String getCancelComment() {
		return cancelComment;
	}

	public void setCancelComment(String cancelComment) {
		this.cancelComment = cancelComment;
		this.settings.put("cancelComment", cancelComment);
	}

	@DBMeta(column = "CONT_TYPE", name = "合同类型", type = "int", dict_table = "CT_546")
	public int getContType() {
		return contType;
	}

	public void setContType(int contType) {
		this.contType = contType;
		this.settings.put("contType", contType);
	}

	@DBMeta(column = "EARNEST_TYPE", name = "定金合同撤销类型", type = "int", dict_table = "CT_548")
	public int getEarnestType() {
		return earnestType;
	}

	public void setEarnestType(int earnestType) {
		this.earnestType = earnestType;
		this.settings.put("earnestType", earnestType);
	}

	@DBMeta(column = "REASON_TYPE", name = "撤销原因类型", type = "int", dict_table = "CT_551")
	public int getReasonType() {
		return reasonType;
	}

	public void setReasonType(int reasonType) {
		this.reasonType = reasonType;
		this.settings.put("reasonType", reasonType);
	}

	@DBMeta(column = "FIRST_CENSOR", name = "经办人", type = "String")
	public String getFirstCensor() {
		return firstCensor;
	}

	public void setFirstCensor(String firstCensor) {
		this.firstCensor = firstCensor;
		this.settings.put("firstCensor", firstCensor);
	}

	@DBMeta(column = "FIRST_DATE", name = "经办日期", type = "long")
	public long getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(long firstDate) {
		this.firstDate = firstDate;
		this.settings.put("firstDate", firstDate);
	}

	@DBMeta(column = "FIRST_NOTION", name = "经办意见", type = "String")
	public String getFirstNotion() {
		return firstNotion;
	}

	public void setFirstNotion(String firstNotion) {
		this.firstNotion = firstNotion;
		this.settings.put("firstNotion", firstNotion);
	}

	@DBMeta(column = "EARLY_CENSOR", name = "复核人", type = "String")
	public String getEarlyCensor() {
		return earlyCensor;
	}

	public void setEarlyCensor(String earlyCensor) {
		this.earlyCensor = earlyCensor;
		this.settings.put("earlyCensor", earlyCensor);
	}

	@DBMeta(column = "EARLY_DATE", name = "复核日期", type = "long")
	public long getEarlyDate() {
		return earlyDate;
	}

	public void setEarlyDate(long earlyDate) {
		this.earlyDate = earlyDate;
		this.settings.put("earlyDate", earlyDate);
	}

	@DBMeta(column = "EARLY_NOTION", name = "复核意见", type = "String")
	public String getEarlyNotion() {
		return earlyNotion;
	}

	public void setEarlyNotion(String earlyNotion) {
		this.earlyNotion = earlyNotion;
		this.settings.put("earlyNotion", earlyNotion);
	}

	@DBMeta(column = "FINAL_CENSOR", name = "审批人", type = "String")
	public String getFinalCensor() {
		return finalCensor;
	}

	public void setFinalCensor(String finalCensor) {
		this.finalCensor = finalCensor;
		this.settings.put("finalCensor", finalCensor);
	}

	@DBMeta(column = "FINAL_DATE", name = "审批日期", type = "long")
	public long getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(long finalDate) {
		this.finalDate = finalDate;
		this.settings.put("finalDate", finalDate);
	}

	@DBMeta(column = "FINAL_NOTION", name = "审批意见", type = "String")
	public String getFinalNotion() {
		return finalNotion;
	}

	public void setFinalNotion(String finalNotion) {
		this.finalNotion = finalNotion;
		this.settings.put("finalNotion", finalNotion);
	}

	@DBMeta(column = "publishCensor", name = "发布人", type = "String")
	public String getPublishCensor() {
		return publishCensor;
	}

	public void setPublishCensor(String publishCensor) {
		this.publishCensor = publishCensor;
		this.settings.put("publishCensor", publishCensor);
	}

	@DBMeta(column = "publishDate", name = "发布日期", type = "Date")
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
		this.settings.put("publishDate", publishDate);
	}

	@DBMeta(column = "publishNotion", name = "发布意见", type = "String")
	public String getPublishNotion() {
		return publishNotion;
	}

	public void setPublishNotion(String publishNotion) {
		this.publishNotion = publishNotion;
		this.settings.put("publishNotion", publishNotion);
	}

	/**
	 * 功能描述：将yyyymmdd格式日期转为yyyy-mm-dd格式
	 * @return
	 */
	public String getFirstDate1() {
		return DateUtil.parseDateTime3(String.valueOf(this.firstDate));
	}

	public String getEarlyDate1() {
		return DateUtil.parseDateTime3(String.valueOf(this.earlyDate));
	}

	public String getConfirmDate1() {
		return DateUtil.parseDateTime3(String.valueOf(this.confirmDate));
	}

	public String getFinalDate1() {
		return DateUtil.parseDateTime3(String.valueOf(this.finalDate));
	}


}
