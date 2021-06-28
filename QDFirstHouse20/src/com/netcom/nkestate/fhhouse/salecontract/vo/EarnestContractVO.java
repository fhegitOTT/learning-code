package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "EARNESTCONTRACT", sequence = "", id = DBModel.AssignedID)
public class EarnestContractVO extends FHVO {

	private long contractID; //  	合同ID
	private String projectName; //  	楼盘名称
	private double cellarArea; //  	地下附属面积
	private String laneName; //  	号(对应大机弄)
	private String subLane; //  	栋（对应大机支弄）
	private String N1_1; //  	路名
	private String N1_2; //  	弄号
	private String N1_4; //  	单元
	private int N1_5; //  	幢号区分
	private String N1_6; //  	层
	private String N1_7; //  	室
	private int N1_8; //  	证书区分	CODE529 0:商品房预售许可证、1:房地产权证
	private String N1_9; //  	证书号
	private String N1_10; //  	测绘机构
	private double N1_11; //  	房屋建筑面积
	private String N1_12; //  	交付时间年
	private String N1_13; //  	交付时间月
	private String N1_14; //  	交付时间日
	private double N2_1; //  	单价的小写
	private String N2_2; //  	单价的大写
	private double N2_3; //  	总房价的小写
	private String N2_4; //  	总房价的大写
	private int N2_5; //  	付款方式	CODE553 0:一次性付清、1:分期付款、2:抵押贷款付款
	private double N3_1; //  	合同第3条第1个填充项(定金的小写)
	private String N3_2; //  	合同第3条第2个填充项(定金的大写)
	private int N4_1; //  	合同第5条第1个填充项(预定天数)
	private String N4_2; //  	合同第5条第2个填充项(签约时间年月日)
	private String N4_3; //  	合同第5条第3个填充项(签约地址)
	private String N9_1; //  	合同第9条第1个填充项(协议分数)
	private String N9_2; //  	合同第9条第2个填充项(甲乙双方各持分数)
	private String N9_3; //  	合同第9条第3个填充项(其他持有者1)
	private String N9_4; //  	合同第9条第4个填充项(其他持有者2)
	private String remark; //  	备注

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "PROJECT_NAME", name = "楼盘名称", type = "String")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
		this.settings.put("projectName", projectName);
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

	@DBMeta(column = "N1_1", name = "路名", type = "String")
	public String getN1_1() {
		return N1_1;
	}

	public void setN1_1(String N1_1) {
		this.N1_1 = N1_1;
		this.settings.put("N1_1", N1_1);
	}

	@DBMeta(column = "N1_2", name = "弄号", type = "String")
	public String getN1_2() {
		return N1_2;
	}

	public void setN1_2(String N1_2) {
		this.N1_2 = N1_2;
		this.settings.put("N1_2", N1_2);
	}

	@DBMeta(column = "N1_4", name = "单元", type = "String")
	public String getN1_4() {
		return N1_4;
	}

	public void setN1_4(String N1_4) {
		this.N1_4 = N1_4;
		this.settings.put("N1_4", N1_4);
	}

	@DBMeta(column = "N1_5", name = "幢号区分", type = "int")
	public int getN1_5() {
		return N1_5;
	}

	public void setN1_5(int N1_5) {
		this.N1_5 = N1_5;
		this.settings.put("N1_5", N1_5);
	}

	@DBMeta(column = "N1_6", name = "层", type = "String")
	public String getN1_6() {
		return N1_6;
	}

	public void setN1_6(String N1_6) {
		this.N1_6 = N1_6;
		this.settings.put("N1_6", N1_6);
	}

	@DBMeta(column = "N1_7", name = "室", type = "String")
	public String getN1_7() {
		return N1_7;
	}

	public void setN1_7(String N1_7) {
		this.N1_7 = N1_7;
		this.settings.put("N1_7", N1_7);
	}

	@DBMeta(column = "N1_8", name = "证书区分", type = "int", dict_table = "CT_529")
	public int getN1_8() {
		return N1_8;
	}

	public void setN1_8(int N1_8) {
		this.N1_8 = N1_8;
		this.settings.put("N1_8", N1_8);
	}

	@DBMeta(column = "N1_9", name = "证书号", type = "String")
	public String getN1_9() {
		return N1_9;
	}

	public void setN1_9(String N1_9) {
		this.N1_9 = N1_9;
		this.settings.put("N1_9", N1_9);
	}

	@DBMeta(column = "N1_10", name = "测绘机构", type = "String")
	public String getN1_10() {
		return N1_10;
	}

	public void setN1_10(String N1_10) {
		this.N1_10 = N1_10;
		this.settings.put("N1_10", N1_10);
	}

	@DBMeta(column = "N1_11", name = "房屋建筑面积", type = "double")
	public double getN1_11() {
		return N1_11;
	}

	public void setN1_11(double N1_11) {
		this.N1_11 = N1_11;
		this.settings.put("N1_11", N1_11);
	}

	@DBMeta(column = "N1_12", name = "交付时间年", type = "String")
	public String getN1_12() {
		return N1_12;
	}

	public void setN1_12(String N1_12) {
		this.N1_12 = N1_12;
		this.settings.put("N1_12", N1_12);
	}

	@DBMeta(column = "N1_13", name = "交付时间月", type = "String")
	public String getN1_13() {
		return N1_13;
	}

	public void setN1_13(String N1_13) {
		this.N1_13 = N1_13;
		this.settings.put("N1_13", N1_13);
	}

	@DBMeta(column = "N1_14", name = "交付时间日", type = "String")
	public String getN1_14() {
		return N1_14;
	}

	public void setN1_14(String N1_14) {
		this.N1_14 = N1_14;
		this.settings.put("N1_14", N1_14);
	}

	@DBMeta(column = "N2_1", name = "单价的小写", type = "double")
	public double getN2_1() {
		return N2_1;
	}

	public void setN2_1(double N2_1) {
		this.N2_1 = N2_1;
		this.settings.put("N2_1", N2_1);
	}

	@DBMeta(column = "N2_2", name = "单价的大写", type = "String")
	public String getN2_2() {
		return N2_2;
	}

	public void setN2_2(String N2_2) {
		this.N2_2 = N2_2;
		this.settings.put("N2_2", N2_2);
	}

	@DBMeta(column = "N2_3", name = "总房价的小写", type = "double")
	public double getN2_3() {
		return N2_3;
	}

	public void setN2_3(double N2_3) {
		this.N2_3 = N2_3;
		this.settings.put("N2_3", N2_3);
	}

	@DBMeta(column = "N2_4", name = "总房价的大写", type = "String")
	public String getN2_4() {
		return N2_4;
	}

	public void setN2_4(String N2_4) {
		this.N2_4 = N2_4;
		this.settings.put("N2_4", N2_4);
	}

	@DBMeta(column = "N2_5", name = "付款方式", type = "int", dict_table = "CT_553")
	public int getN2_5() {
		return N2_5;
	}

	public void setN2_5(int N2_5) {
		this.N2_5 = N2_5;
		this.settings.put("N2_5", N2_5);
	}

	@DBMeta(column = "N3_1", name = "合同第3条第1个填充项", type = "double")
	public double getN3_1() {
		return N3_1;
	}

	public void setN3_1(double N3_1) {
		this.N3_1 = N3_1;
		this.settings.put("N3_1", N3_1);
	}

	@DBMeta(column = "N3_2", name = "合同第3条第2个填充项", type = "String")
	public String getN3_2() {
		return N3_2;
	}

	public void setN3_2(String N3_2) {
		this.N3_2 = N3_2;
		this.settings.put("N3_2", N3_2);
	}

	@DBMeta(column = "N4_1", name = "合同第5条第1个填充项", type = "int")
	public int getN4_1() {
		return N4_1;
	}

	public void setN4_1(int N4_1) {
		this.N4_1 = N4_1;
		this.settings.put("N4_1", N4_1);
	}

	@DBMeta(column = "N4_2", name = "合同第5条第2个填充项", type = "String")
	public String getN4_2() {
		return N4_2;
	}

	public void setN4_2(String N4_2) {
		this.N4_2 = N4_2;
		this.settings.put("N4_2", N4_2);
	}

	@DBMeta(column = "N4_3", name = "合同第5条第3个填充项", type = "String")
	public String getN4_3() {
		return N4_3;
	}

	public void setN4_3(String N4_3) {
		this.N4_3 = N4_3;
		this.settings.put("N4_3", N4_3);
	}

	@DBMeta(column = "N9_1", name = "合同第9条第1个填充项", type = "String")
	public String getN9_1() {
		return N9_1;
	}

	public void setN9_1(String N9_1) {
		this.N9_1 = N9_1;
		this.settings.put("N9_1", N9_1);
	}

	@DBMeta(column = "N9_2", name = "合同第9条第2个填充项", type = "String")
	public String getN9_2() {
		return N9_2;
	}

	public void setN9_2(String N9_2) {
		this.N9_2 = N9_2;
		this.settings.put("N9_2", N9_2);
	}

	@DBMeta(column = "N9_3", name = "合同第9条第3个填充项", type = "String")
	public String getN9_3() {
		return N9_3;
	}

	public void setN9_3(String N9_3) {
		this.N9_3 = N9_3;
		this.settings.put("N9_3", N9_3);
	}

	@DBMeta(column = "N9_4", name = "合同第9条第4个填充项", type = "String")
	public String getN9_4() {
		return N9_4;
	}

	public void setN9_4(String N9_4) {
		this.N9_4 = N9_4;
		this.settings.put("N9_4", N9_4);
	}

	@DBMeta(column = "REMARK", name = "备注", type = "String")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		this.settings.put("remark", remark);
	}

}
