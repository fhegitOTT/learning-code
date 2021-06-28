package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "DELIVERCONTRACT", sequence = "", id = DBModel.AssignedID)
public class DeliverContractVO extends FHVO {

	private long deliverID; //  	交接书ID YYYY+m_sequence表中的T_CONTRACT记录序号
	private long contractID; //  	合同ID
	private long houseID; //  	房屋ID	一期中为BARCODE        
	private String projectName; //  	楼盘名称
	private String seller; //  	甲方
	private String buyer; //  	乙方
	private int payType; //  	支付类型	CODE527 0:已付全部房价款、1:乙方已付人民币
	private int receiptType; //  	收据类型	CODE522 0:发票、1:收据
	private int bldNumType; //  	幢号区分	CODE508 0:幢、1:号
	private String deliverYear; //  	交付时间年
	private String deliverMonth; //  	交付时间月
	private String deliverDay; //  	交付时间日
	private double cellarArea; //  	地下附属面积
	private String laneName; //  	号（对应大机弄）
	private String subLane; //  	栋（对应大机支弄）
	private int status; //  	状态		CODE536 0:已撤销、1:已签
	private String n1_1; //  	路名
	private String n1_2; //  	弄号
	private String n1_3; //  	楼盘名称
	private String n1_4; //  	单元
	private String n1_5; //  	层号
	private String n1_6; //  	室号
	private double n1_7; //  	实测建筑面积
	private double n1_8; //  	公用分摊建筑面积
	private String n1_9; //  	测绘机构
	private String n1_10; //  	房地产权证号
	private double n2_1; //  	总房价款
	private String n2_2; //  	总房价款(大写)
	private double n2_3; //  	乙方付款
	private String n2_4; //  	乙方付款(大写)
	private byte[] n3_1 = "".getBytes(); //  	交接书第3条第1个填充项
	private String chargeDep; //	主管部门
	private String location; //	坐落

	@DBMeta(column = "DELIVER_ID", name = "交接书ID", type = "long", primarykey = "true", can_update = "false")
	public long getDeliverID() {
		return deliverID;
	}

	public void setDeliverID(long deliverID) {
		this.deliverID = deliverID;
		this.settings.put("deliverID", deliverID);
	}

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "HOUSE_ID", name = "房屋ID", type = "long")
	public long getHouseID() {
		return houseID;
	}

	public void setHouseID(long houseID) {
		this.houseID = houseID;
		this.settings.put("houseID", houseID);
	}

	@DBMeta(column = "PROJECT_NAME", name = "楼盘名称", type = "String")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
		this.settings.put("projectName", projectName);
	}

	@DBMeta(column = "SELLER", name = "甲方", type = "String")
	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
		this.settings.put("seller", seller);
	}

	@DBMeta(column = "BUYER", name = "乙方", type = "String")
	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
		this.settings.put("buyer", buyer);
	}

	@DBMeta(column = "PAYTYPE", name = "支付类型", type = "int", dict_table = "CT_527")
	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
		this.settings.put("payType", payType);
	}

	@DBMeta(column = "RECEIPTTYPE", name = "收据类型", type = "int", dict_table = "CT_522")
	public int getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(int receiptType) {
		this.receiptType = receiptType;
		this.settings.put("receiptType", receiptType);
	}

	@DBMeta(column = "BLDNUMTYPE", name = "幢号区分", type = "int", dict_table = "CT_508")
	public int getBldNumType() {
		return bldNumType;
	}

	public void setBldNumType(int bldNumType) {
		this.bldNumType = bldNumType;
		this.settings.put("bldNumType", bldNumType);
	}

	@DBMeta(column = "DELIVER_YEAR", name = "交付时间年", type = "String")
	public String getDeliverYear() {
		return deliverYear;
	}

	public void setDeliverYear(String deliverYear) {
		this.deliverYear = deliverYear;
		this.settings.put("deliverYear", deliverYear);
	}

	@DBMeta(column = "DELIVER_MONTH", name = "交付时间月", type = "String")
	public String getDeliverMonth() {
		return deliverMonth;
	}

	public void setDeliverMonth(String deliverMonth) {
		this.deliverMonth = deliverMonth;
		this.settings.put("deliverMonth", deliverMonth);
	}

	@DBMeta(column = "DELIVER_DAY", name = "交付时间日", type = "String")
	public String getDeliverDay() {
		return deliverDay;
	}

	public void setDeliverDay(String deliverDay) {
		this.deliverDay = deliverDay;
		this.settings.put("deliverDay", deliverDay);
	}

	@DBMeta(column = "CELLAR_AREA", name = "地下附属面积", type = "double")
	public double getCellarArea() {
		return cellarArea;
	}

	public void setCellarArea(double cellarArea) {
		this.cellarArea = cellarArea;
		this.settings.put("cellarArea", cellarArea);
	}

	@DBMeta(column = "LANE_NAME", name = "号", type = "String")
	public String getLaneName() {
		return laneName;
	}

	public void setLaneName(String laneName) {
		this.laneName = laneName;
		this.settings.put("laneName", laneName);
	}

	@DBMeta(column = "SUB_LANE", name = "栋", type = "String")
	public String getSubLane() {
		return subLane;
	}

	public void setSubLane(String subLane) {
		this.subLane = subLane;
		this.settings.put("subLane", subLane);
	}

	@DBMeta(column = "STATUS", name = "状态", type = "int", dict_table = "CT_536")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}

	@DBMeta(column = "n1_1", name = "路名", type = "String")
	public String getN1_1() {
		return n1_1;
	}

	public void setN1_1(String n1_1) {
		this.n1_1 = n1_1;
		this.settings.put("n1_1", n1_1);
	}

	@DBMeta(column = "n1_2", name = "弄号", type = "String")
	public String getN1_2() {
		return n1_2;
	}

	public void setN1_2(String n1_2) {
		this.n1_2 = n1_2;
		this.settings.put("n1_2", n1_2);
	}

	@DBMeta(column = "n1_3", name = "楼盘名称", type = "String")
	public String getN1_3() {
		return n1_3;
	}

	public void setN1_3(String n1_3) {
		this.n1_3 = n1_3;
		this.settings.put("n1_3", n1_3);
	}

	@DBMeta(column = "n1_4", name = "单元", type = "String")
	public String getN1_4() {
		return n1_4;
	}

	public void setN1_4(String n1_4) {
		this.n1_4 = n1_4;
		this.settings.put("n1_4", n1_4);
	}

	@DBMeta(column = "n1_5", name = "层号", type = "String")
	public String getN1_5() {
		return n1_5;
	}

	public void setN1_5(String n1_5) {
		this.n1_5 = n1_5;
		this.settings.put("n1_5", n1_5);
	}

	@DBMeta(column = "n1_6", name = "室号", type = "String")
	public String getN1_6() {
		return n1_6;
	}

	public void setN1_6(String n1_6) {
		this.n1_6 = n1_6;
		this.settings.put("n1_6", n1_6);
	}

	@DBMeta(column = "n1_7", name = "实测建筑面积", type = "double")
	public double getN1_7() {
		return n1_7;
	}

	public void setN1_7(double n1_7) {
		this.n1_7 = n1_7;
		this.settings.put("n1_7", n1_7);
	}

	@DBMeta(column = "n1_8", name = "公用分摊建筑面积", type = "double")
	public double getN1_8() {
		return n1_8;
	}

	public void setN1_8(double n1_8) {
		this.n1_8 = n1_8;
		this.settings.put("n1_8", n1_8);
	}

	@DBMeta(column = "n1_9", name = "测绘机构", type = "String")
	public String getN1_9() {
		return n1_9;
	}

	public void setN1_9(String n1_9) {
		this.n1_9 = n1_9;
		this.settings.put("n1_9", n1_9);
	}

	@DBMeta(column = "n1_10", name = "房地产权证号", type = "String")
	public String getN1_10() {
		return n1_10;
	}

	public void setN1_10(String n1_10) {
		this.n1_10 = n1_10;
		this.settings.put("n1_10", n1_10);
	}

	@DBMeta(column = "n2_1", name = "总房价款", type = "double")
	public double getN2_1() {
		return n2_1;
	}

	public void setN2_1(double n2_1) {
		this.n2_1 = n2_1;
		this.settings.put("n2_1", n2_1);
	}

	@DBMeta(column = "n2_2", name = "总房价款", type = "String")
	public String getN2_2() {
		return n2_2;
	}

	public void setN2_2(String n2_2) {
		this.n2_2 = n2_2;
		this.settings.put("n2_2", n2_2);
	}

	@DBMeta(column = "n2_3", name = "乙方付款", type = "double")
	public double getN2_3() {
		return n2_3;
	}

	public void setN2_3(double n2_3) {
		this.n2_3 = n2_3;
		this.settings.put("n2_3", n2_3);
	}

	@DBMeta(column = "n2_4", name = "乙方付款", type = "String")
	public String getN2_4() {
		return n2_4;
	}

	public void setN2_4(String n2_4) {
		this.n2_4 = n2_4;
		this.settings.put("n2_4", n2_4);
	}

	@DBMeta(column = "n3_1", name = "交接书第3条第1个填充项", type = "Clob")
	public byte[] getN3_1() {
		return n3_1;
	}

	public void setN3_1(byte[] n3_1) {
		this.n3_1 = n3_1;
		this.settings.put("n3_1", n3_1);
	}

	@DBMeta(column = "CHARGE_DEP", name = "主管部门", type = "String")
	public String getChargeDep() {
		return chargeDep;
	}

	public void setChargeDep(String chargeDep) {
		this.chargeDep = chargeDep;
		this.settings.put("chargeDep", chargeDep);
	}

	@DBMeta(column = "location", name = "坐落", type = "String")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
		this.settings.put("location", location);
	}
}
