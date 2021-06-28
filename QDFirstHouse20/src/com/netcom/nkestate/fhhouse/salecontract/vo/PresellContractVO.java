package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "PRESELLCONTRACT", sequence = "", id = DBModel.AssignedID)
public class PresellContractVO extends FHVO {

	private long contractID; //  	合同ID
	private long earnestID; //  	定金合同ID
	private String projectName; //  	楼盘名称
	private int regType; //  	登记方式	CODE511 0:甲方、1:乙方、2:双方
	private int landattornType; //  	土地划拨方式	CODE507 0:出让、1:转让、2:划拨
	private int landpactType; //  	土地使用权合同类型
	private int bldnumType; //  	幢号区分	CODE508 0:幢、1:号
	private int houseType; //  	房屋类型	CODE539 0:非住宅、1:住宅
	private double cellarArea; //  	地下附属面积
	private String laneName; //  	号（对应大机弄）
	private String subLane; //  	栋（对应大机支弄）
	private String N1_1; //  	区县名
	private String N1_2; //  	地块名
	private String N1_3; //  	房地产权证号
	private double N1_4; //  	土地面积
	private String N1_5; //  	土地用途
	private String N1_6; //  	楼盘名称
	private String N1_7; //  	建筑结构
	private int N1_8; //  	地上层数
	private int N1_9; //  	地下层数
	private String N1_10; //  	房屋土地管理局名
	private String N1_11; //  	预售许可证编号
	private String N2_1; //  	路名
	private String N2_2; //  	弄号
	private String N2_3; //  	楼盘名称
	private String N2_4; //  	单元
	private String N2_5; //  	层号
	private String N2_6; //  	房间号
	private String N2_7; //  	规划用途
	private double N2_8; //  	房屋建筑面积
	private double N2_9; //  	套内建筑面积
	private double N2_10; //  	公用分摊建筑面积
	private String N2_11; //  	建筑层高
	private int n2Room; //  	室数
	private int n2Hall; //  	厅数
	private int n2Toilet; //  	卫生间数
	private int n2Cookroom; //  	厨房数
	private double N3_1; //  	建筑面积单价
	private String N3_2; //  	建筑面积单价(大写)
	private double N3_3; //  	总房价款
	private String N3_4; //  	总房价款(大写)
	private double N5_1; //  	误差超过值1
	private double N5_2; //  	误差包括值1
	private double N5_3; //  	误差超过值2
	private double N5_4; //  	误差包括值2
	private String N6_1; //  	工程阶段
	private String N6_2; //  	预收款监管机构
	private String N6_3; //  	预收款监管机构帐户名称
	private String N6_4; //  	预收款监管机构帐号
	private String N7_1; //  	违约金比例
	private String N7_2; //  	逾期天数
	private int N7_3; //  	赔偿方案种类
	private double N7_4; //  	赔偿金额比例
	private String N8_1; //  	批准之日起有效天数
	private double N9_1; //  	违约金百分比
	private int N10_1; //  	方案种类
	private String N10_2; //  	承诺登记年
	private String N10_3; //  	承诺登记月
	private String N10_4; //  	承诺登记日
	private String N11_1; //  	合同第11条第1个填充项 年
	private String N11_2; //  	合同第11条第2个填充项 月
	private String N11_3; //  	合同第11条第3个填充项 日
	private double N12_1; //  	违约金比例
	private String N12_2; //  	逾期天数
	private int N12_3; //  	方案种类
	private String N13_1; //  	交付之日起有效天数
	private String N13_2; //  	通知之日起有效天数
	private String N13_3; //  	房屋交付的标志
	private String N13_4; //  	房屋用途
	private String N13_5; //  	质量保证书名
	private String N13_6; //  	使用说明书名
	private String N14_1; //  	取得房地产权证后有效天数
	private String N14_2; //  	签署《房屋交接书》之日起有效天数
	private int N14_3; //  	交易中心名
	private double N17_1; //  	设备差价的倍数
	private String N19_1; //  	甲方收到乙方的书面通知起的有效天数
	private double N19_2; //  	赔偿金额百分比
	private String N21_1; //  	解除本合同的书面通知之日起的有效天数
	private double N22_1; //  	修复费的倍数
	private String N24_1; //  	物业公司名
	private String N24_2; //  	房屋规划用途
	private String N24_3; //  	XXXX使用公约
	private String N25_1; //  	与XXXX签订的土地使用权
	private String N27_1; //  	投邮后(以寄出的邮戳为准)第XX日
	private String N31_1; //  	XXXX公证处
	private String N31_2; //  	本合同生效之日起XX日内
	private int N32_1; //  	房地产登记机构名
	private int N33_1; //  	第XX种方式解决
	private String N33_2; //  	向XXXX仲裁委员会申请仲裁
	private String N34_1; //  	本合同壹式XX份
	private String N34_2; //  	甲、乙双方各执XX份
	private String N34_3; //  	合同持有人1
	private String N34_4; //  	合同持有人2
	private String N34_5; //  	合同持有人3
	private String fitmentPriceCn; //  	房屋全装修总价大写
	private double fitmentPrice; //  	房屋全装修总价小写
	private String chargeDep; //  	主管部门
	
	private double N5_5; // 第五条
	private int N5_6; //  	第五条
	private int N32_2; //  

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "EARNEST_ID", name = "定金合同ID", type = "long")
	public long getEarnestID() {
		return earnestID;
	}

	public void setEarnestID(long earnestID) {
		this.earnestID = earnestID;
		this.settings.put("earnestID", earnestID);
	}

	@DBMeta(column = "PROJECT_NAME", name = "楼盘名称", type = "String")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
		this.settings.put("projectName", projectName);
	}

	@DBMeta(column = "REGTYPE", name = "登记方式", type = "int", dict_table = "CT_511")
	public int getRegType() {
		return regType;
	}

	public void setRegType(int regType) {
		this.regType = regType;
		this.settings.put("regType", regType);
	}

	@DBMeta(column = "LANDATTORNTYPE", name = "土地划拨方式", type = "int", dict_table = "CT_507")
	public int getLandattornType() {
		return landattornType;
	}

	public void setLandattornType(int landattornType) {
		this.landattornType = landattornType;
		this.settings.put("landattornType", landattornType);
	}

	@DBMeta(column = "LANDPACTTYPE", name = "土地使用权合同类型", type = "int")
	public int getLandpactType() {
		return landpactType;
	}

	public void setLandpactType(int landpactType) {
		this.landpactType = landpactType;
		this.settings.put("landpactType", landpactType);
	}

	@DBMeta(column = "BLDNUMTYPE", name = "幢号区分", type = "int", dict_table = "CT_508")
	public int getBldnumType() {
		return bldnumType;
	}

	public void setBldnumType(int bldnumType) {
		this.bldnumType = bldnumType;
		this.settings.put("bldnumType", bldnumType);
	}

	@DBMeta(column = "HOUSETYPE", name = "房屋类型", type = "int", dict_table = "CT_539")
	public int getHouseType() {
		return houseType;
	}

	public void setHouseType(int houseType) {
		this.houseType = houseType;
		this.settings.put("houseType", houseType);
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

	@DBMeta(column = "N1_1", name = "区县名", type = "String")
	public String getN1_1() {
		return N1_1;
	}

	public void setN1_1(String N1_1) {
		this.N1_1 = N1_1;
		this.settings.put("N1_1", N1_1);
	}

	@DBMeta(column = "N1_2", name = "地块名", type = "String")
	public String getN1_2() {
		return N1_2;
	}

	public void setN1_2(String N1_2) {
		this.N1_2 = N1_2;
		this.settings.put("N1_2", N1_2);
	}

	@DBMeta(column = "N1_3", name = "房地产权证号", type = "String")
	public String getN1_3() {
		return N1_3;
	}

	public void setN1_3(String N1_3) {
		this.N1_3 = N1_3;
		this.settings.put("N1_3", N1_3);
	}

	@DBMeta(column = "N1_4", name = "土地面积", type = "double")
	public double getN1_4() {
		return N1_4;
	}

	public void setN1_4(double N1_4) {
		this.N1_4 = N1_4;
		this.settings.put("N1_4", N1_4);
	}

	@DBMeta(column = "N1_5", name = "土地用途", type = "String")
	public String getN1_5() {
		return N1_5;
	}

	public void setN1_5(String N1_5) {
		this.N1_5 = N1_5;
		this.settings.put("N1_5", N1_5);
	}

	@DBMeta(column = "N1_6", name = "楼盘名称", type = "String")
	public String getN1_6() {
		return N1_6;
	}

	public void setN1_6(String N1_6) {
		this.N1_6 = N1_6;
		this.settings.put("N1_6", N1_6);
	}

	@DBMeta(column = "N1_7", name = "建筑结构", type = "String")
	public String getN1_7() {
		return N1_7;
	}

	public void setN1_7(String N1_7) {
		this.N1_7 = N1_7;
		this.settings.put("N1_7", N1_7);
	}

	@DBMeta(column = "N1_8", name = "地上层数", type = "int")
	public int getN1_8() {
		return N1_8;
	}

	public void setN1_8(int N1_8) {
		this.N1_8 = N1_8;
		this.settings.put("N1_8", N1_8);
	}

	@DBMeta(column = "N1_9", name = "地下层数", type = "int")
	public int getN1_9() {
		return N1_9;
	}

	public void setN1_9(int N1_9) {
		this.N1_9 = N1_9;
		this.settings.put("N1_9", N1_9);
	}

	@DBMeta(column = "N1_10", name = "房屋土地管理局名", type = "String")
	public String getN1_10() {
		return N1_10;
	}

	public void setN1_10(String N1_10) {
		this.N1_10 = N1_10;
		this.settings.put("N1_10", N1_10);
	}

	@DBMeta(column = "N1_11", name = "预售许可证编号", type = "String")
	public String getN1_11() {
		return N1_11;
	}

	public void setN1_11(String N1_11) {
		this.N1_11 = N1_11;
		this.settings.put("N1_11", N1_11);
	}

	@DBMeta(column = "N2_1", name = "路名", type = "String")
	public String getN2_1() {
		return N2_1;
	}

	public void setN2_1(String N2_1) {
		this.N2_1 = N2_1;
		this.settings.put("N2_1", N2_1);
	}

	@DBMeta(column = "N2_2", name = "弄号", type = "String")
	public String getN2_2() {
		return N2_2;
	}

	public void setN2_2(String N2_2) {
		this.N2_2 = N2_2;
		this.settings.put("N2_2", N2_2);
	}

	@DBMeta(column = "N2_3", name = "楼盘名称", type = "String")
	public String getN2_3() {
		return N2_3;
	}

	public void setN2_3(String N2_3) {
		this.N2_3 = N2_3;
		this.settings.put("N2_3", N2_3);
	}

	@DBMeta(column = "N2_4", name = "单元", type = "String")
	public String getN2_4() {
		return N2_4;
	}

	public void setN2_4(String N2_4) {
		this.N2_4 = N2_4;
		this.settings.put("N2_4", N2_4);
	}

	@DBMeta(column = "N2_5", name = "层号", type = "String")
	public String getN2_5() {
		return N2_5;
	}

	public void setN2_5(String N2_5) {
		this.N2_5 = N2_5;
		this.settings.put("N2_5", N2_5);
	}

	@DBMeta(column = "N2_6", name = "房间号", type = "String")
	public String getN2_6() {
		return N2_6;
	}

	public void setN2_6(String N2_6) {
		this.N2_6 = N2_6;
		this.settings.put("N2_6", N2_6);
	}

	@DBMeta(column = "N2_7", name = "规划用途", type = "String")
	public String getN2_7() {
		return N2_7;
	}

	public void setN2_7(String N2_7) {
		this.N2_7 = N2_7;
		this.settings.put("N2_7", N2_7);
	}

	@DBMeta(column = "N2_8", name = "房屋建筑面积", type = "double")
	public double getN2_8() {
		return N2_8;
	}

	public void setN2_8(double N2_8) {
		this.N2_8 = N2_8;
		this.settings.put("N2_8", N2_8);
	}

	@DBMeta(column = "N2_9", name = "套内建筑面积", type = "double")
	public double getN2_9() {
		return N2_9;
	}

	public void setN2_9(double N2_9) {
		this.N2_9 = N2_9;
		this.settings.put("N2_9", N2_9);
	}

	@DBMeta(column = "N2_10", name = "公用分摊建筑面积", type = "double")
	public double getN2_10() {
		return N2_10;
	}

	public void setN2_10(double N2_10) {
		this.N2_10 = N2_10;
		this.settings.put("N2_10", N2_10);
	}

	@DBMeta(column = "N2_11", name = "建筑层高", type = "String")
	public String getN2_11() {
		return N2_11;
	}

	public void setN2_11(String N2_11) {
		this.N2_11 = N2_11;
		this.settings.put("N2_11", N2_11);
	}

	@DBMeta(column = "N2_ROOM", name = "室数", type = "int")
	public int getn2Room() {
		return n2Room;
	}

	public void setn2Room(int n2Room) {
		this.n2Room = n2Room;
		this.settings.put("n2Room", n2Room);
	}

	@DBMeta(column = "N2_HALL", name = "厅数", type = "int")
	public int getn2Hall() {
		return n2Hall;
	}

	public void setn2Hall(int n2Hall) {
		this.n2Hall = n2Hall;
		this.settings.put("n2Hall", n2Hall);
	}

	@DBMeta(column = "N2_TOILET", name = "卫生间数", type = "int")
	public int getn2Toilet() {
		return n2Toilet;
	}

	public void setn2Toilet(int n2Toilet) {
		this.n2Toilet = n2Toilet;
		this.settings.put("n2Toilet", n2Toilet);
	}

	@DBMeta(column = "N2_COOKROOM", name = "厨房数", type = "int")
	public int getn2Cookroom() {
		return n2Cookroom;
	}

	public void setn2Cookroom(int n2Cookroom) {
		this.n2Cookroom = n2Cookroom;
		this.settings.put("n2Cookroom", n2Cookroom);
	}

	@DBMeta(column = "N3_1", name = "建筑面积单价", type = "double")
	public double getN3_1() {
		return N3_1;
	}

	public void setN3_1(double N3_1) {
		this.N3_1 = N3_1;
		this.settings.put("N3_1", N3_1);
	}

	@DBMeta(column = "N3_2", name = "建筑面积单价", type = "String")
	public String getN3_2() {
		return N3_2;
	}

	public void setN3_2(String N3_2) {
		this.N3_2 = N3_2;
		this.settings.put("N3_2", N3_2);
	}

	@DBMeta(column = "N3_3", name = "总房价款", type = "double")
	public double getN3_3() {
		return N3_3;
	}

	public void setN3_3(double N3_3) {
		this.N3_3 = N3_3;
		this.settings.put("N3_3", N3_3);
	}

	@DBMeta(column = "N3_4", name = "总房价款", type = "String")
	public String getN3_4() {
		return N3_4;
	}

	public void setN3_4(String N3_4) {
		this.N3_4 = N3_4;
		this.settings.put("N3_4", N3_4);
	}

	@DBMeta(column = "N5_1", name = "误差超过值1", type = "double")
	public double getN5_1() {
		return N5_1;
	}

	public void setN5_1(double N5_1) {
		this.N5_1 = N5_1;
		this.settings.put("N5_1", N5_1);
	}

	@DBMeta(column = "N5_2", name = "误差包括值1", type = "double")
	public double getN5_2() {
		return N5_2;
	}

	public void setN5_2(double N5_2) {
		this.N5_2 = N5_2;
		this.settings.put("N5_2", N5_2);
	}

	@DBMeta(column = "N5_3", name = "误差超过值2", type = "double")
	public double getN5_3() {
		return N5_3;
	}

	public void setN5_3(double N5_3) {
		this.N5_3 = N5_3;
		this.settings.put("N5_3", N5_3);
	}

	@DBMeta(column = "N5_4", name = "误差包括值2", type = "double")
	public double getN5_4() {
		return N5_4;
	}

	public void setN5_4(double N5_4) {
		this.N5_4 = N5_4;
		this.settings.put("N5_4", N5_4);
	}

	@DBMeta(column = "N6_1", name = "工程阶段", type = "String")
	public String getN6_1() {
		return N6_1;
	}

	public void setN6_1(String N6_1) {
		this.N6_1 = N6_1;
		this.settings.put("N6_1", N6_1);
	}

	@DBMeta(column = "N6_2", name = "预收款监管机构", type = "String")
	public String getN6_2() {
		return N6_2;
	}

	public void setN6_2(String N6_2) {
		this.N6_2 = N6_2;
		this.settings.put("N6_2", N6_2);
	}

	@DBMeta(column = "N6_3", name = "预收款监管机构帐户名称", type = "String")
	public String getN6_3() {
		return N6_3;
	}

	public void setN6_3(String N6_3) {
		this.N6_3 = N6_3;
		this.settings.put("N6_3", N6_3);
	}

	@DBMeta(column = "N6_4", name = "预收款监管机构帐号", type = "String")
	public String getN6_4() {
		return N6_4;
	}

	public void setN6_4(String N6_4) {
		this.N6_4 = N6_4;
		this.settings.put("N6_4", N6_4);
	}

	@DBMeta(column = "N7_1", name = "违约金比例", type = "String")
	public String getN7_1() {
		return N7_1;
	}

	public void setN7_1(String N7_1) {
		this.N7_1 = N7_1;
		this.settings.put("N7_1", N7_1);
	}

	@DBMeta(column = "N7_2", name = "逾期天数", type = "String")
	public String getN7_2() {
		return N7_2;
	}

	public void setN7_2(String N7_2) {
		this.N7_2 = N7_2;
		this.settings.put("N7_2", N7_2);
	}

	@DBMeta(column = "N7_3", name = "赔偿方案种类", type = "int")
	public int getN7_3() {
		return N7_3;
	}

	public void setN7_3(int N7_3) {
		this.N7_3 = N7_3;
		this.settings.put("N7_3", N7_3);
	}

	@DBMeta(column = "N7_4", name = "赔偿金额比例", type = "double")
	public double getN7_4() {
		return N7_4;
	}

	public void setN7_4(double N7_4) {
		this.N7_4 = N7_4;
		this.settings.put("N7_4", N7_4);
	}

	@DBMeta(column = "N8_1", name = "批准之日起有效天数", type = "String")
	public String getN8_1() {
		return N8_1;
	}

	public void setN8_1(String N8_1) {
		this.N8_1 = N8_1;
		this.settings.put("N8_1", N8_1);
	}

	@DBMeta(column = "N9_1", name = "违约金百分比", type = "double")
	public double getN9_1() {
		return N9_1;
	}

	public void setN9_1(double N9_1) {
		this.N9_1 = N9_1;
		this.settings.put("N9_1", N9_1);
	}

	@DBMeta(column = "N10_1", name = "方案种类", type = "int")
	public int getN10_1() {
		return N10_1;
	}

	public void setN10_1(int N10_1) {
		this.N10_1 = N10_1;
		this.settings.put("N10_1", N10_1);
	}

	@DBMeta(column = "N10_2", name = "承诺登记年", type = "String")
	public String getN10_2() {
		return N10_2;
	}

	public void setN10_2(String N10_2) {
		this.N10_2 = N10_2;
		this.settings.put("N10_2", N10_2);
	}

	@DBMeta(column = "N10_3", name = "承诺登记月", type = "String")
	public String getN10_3() {
		return N10_3;
	}

	public void setN10_3(String N10_3) {
		this.N10_3 = N10_3;
		this.settings.put("N10_3", N10_3);
	}

	@DBMeta(column = "N10_4", name = "承诺登记日", type = "String")
	public String getN10_4() {
		return N10_4;
	}

	public void setN10_4(String N10_4) {
		this.N10_4 = N10_4;
		this.settings.put("N10_4", N10_4);
	}

	@DBMeta(column = "N11_1", name = "合同第11条第1个填充项 年", type = "String")
	public String getN11_1() {
		return N11_1;
	}

	public void setN11_1(String N11_1) {
		this.N11_1 = N11_1;
		this.settings.put("N11_1", N11_1);
	}

	@DBMeta(column = "N11_2", name = "合同第11条第2个填充项 月", type = "String")
	public String getN11_2() {
		return N11_2;
	}

	public void setN11_2(String N11_2) {
		this.N11_2 = N11_2;
		this.settings.put("N11_2", N11_2);
	}

	@DBMeta(column = "N11_3", name = "合同第11条第3个填充项 日", type = "String")
	public String getN11_3() {
		return N11_3;
	}

	public void setN11_3(String N11_3) {
		this.N11_3 = N11_3;
		this.settings.put("N11_3", N11_3);
	}

	@DBMeta(column = "N12_1", name = "违约金比例", type = "double")
	public double getN12_1() {
		return N12_1;
	}

	public void setN12_1(double N12_1) {
		this.N12_1 = N12_1;
		this.settings.put("N12_1", N12_1);
	}

	@DBMeta(column = "N12_2", name = "逾期天数", type = "String")
	public String getN12_2() {
		return N12_2;
	}

	public void setN12_2(String N12_2) {
		this.N12_2 = N12_2;
		this.settings.put("N12_2", N12_2);
	}

	@DBMeta(column = "N12_3", name = "方案种类", type = "int")
	public int getN12_3() {
		return N12_3;
	}

	public void setN12_3(int N12_3) {
		this.N12_3 = N12_3;
		this.settings.put("N12_3", N12_3);
	}

	@DBMeta(column = "N13_1", name = "交付之日起有效天数", type = "String")
	public String getN13_1() {
		return N13_1;
	}

	public void setN13_1(String N13_1) {
		this.N13_1 = N13_1;
		this.settings.put("N13_1", N13_1);
	}

	@DBMeta(column = "N13_2", name = "通知之日起有效天数", type = "String")
	public String getN13_2() {
		return N13_2;
	}

	public void setN13_2(String N13_2) {
		this.N13_2 = N13_2;
		this.settings.put("N13_2", N13_2);
	}

	@DBMeta(column = "N13_3", name = "房屋交付的标志", type = "String")
	public String getN13_3() {
		return N13_3;
	}

	public void setN13_3(String N13_3) {
		this.N13_3 = N13_3;
		this.settings.put("N13_3", N13_3);
	}

	@DBMeta(column = "N13_4", name = "房屋用途", type = "String")
	public String getN13_4() {
		return N13_4;
	}

	public void setN13_4(String N13_4) {
		this.N13_4 = N13_4;
		this.settings.put("N13_4", N13_4);
	}

	@DBMeta(column = "N13_5", name = "质量保证书名", type = "String")
	public String getN13_5() {
		return N13_5;
	}

	public void setN13_5(String N13_5) {
		this.N13_5 = N13_5;
		this.settings.put("N13_5", N13_5);
	}

	@DBMeta(column = "N13_6", name = "使用说明书名", type = "String")
	public String getN13_6() {
		return N13_6;
	}

	public void setN13_6(String N13_6) {
		this.N13_6 = N13_6;
		this.settings.put("N13_6", N13_6);
	}

	@DBMeta(column = "N14_1", name = "取得房地产权证后有效天数", type = "String")
	public String getN14_1() {
		return N14_1;
	}

	public void setN14_1(String N14_1) {
		this.N14_1 = N14_1;
		this.settings.put("N14_1", N14_1);
	}

	@DBMeta(column = "N14_2", name = "签署《房屋交接书》之日起有效天数", type = "String")
	public String getN14_2() {
		return N14_2;
	}

	public void setN14_2(String N14_2) {
		this.N14_2 = N14_2;
		this.settings.put("N14_2", N14_2);
	}

	@DBMeta(column = "N14_3", name = "交易中心名", type = "int")
	public int getN14_3() {
		return N14_3;
	}

	public void setN14_3(int N14_3) {
		this.N14_3 = N14_3;
		this.settings.put("N14_3", N14_3);
	}

	@DBMeta(column = "N17_1", name = "设备差价的倍数", type = "double")
	public double getN17_1() {
		return N17_1;
	}

	public void setN17_1(double N17_1) {
		this.N17_1 = N17_1;
		this.settings.put("N17_1", N17_1);
	}

	@DBMeta(column = "N19_1", name = "甲方收到乙方的书面通知起的有效天数", type = "String")
	public String getN19_1() {
		return N19_1;
	}

	public void setN19_1(String N19_1) {
		this.N19_1 = N19_1;
		this.settings.put("N19_1", N19_1);
	}

	@DBMeta(column = "N19_2", name = "赔偿金额百分比", type = "double")
	public double getN19_2() {
		return N19_2;
	}

	public void setN19_2(double N19_2) {
		this.N19_2 = N19_2;
		this.settings.put("N19_2", N19_2);
	}

	@DBMeta(column = "N21_1", name = "解除本合同的书面通知之日起的有效天数", type = "String")
	public String getN21_1() {
		return N21_1;
	}

	public void setN21_1(String N21_1) {
		this.N21_1 = N21_1;
		this.settings.put("N21_1", N21_1);
	}

	@DBMeta(column = "N22_1", name = "修复费的倍数", type = "double")
	public double getN22_1() {
		return N22_1;
	}

	public void setN22_1(double N22_1) {
		this.N22_1 = N22_1;
		this.settings.put("N22_1", N22_1);
	}

	@DBMeta(column = "N24_1", name = "物业公司名", type = "String")
	public String getN24_1() {
		return N24_1;
	}

	public void setN24_1(String N24_1) {
		this.N24_1 = N24_1;
		this.settings.put("N24_1", N24_1);
	}

	@DBMeta(column = "N24_2", name = "房屋规划用途", type = "String")
	public String getN24_2() {
		return N24_2;
	}

	public void setN24_2(String N24_2) {
		this.N24_2 = N24_2;
		this.settings.put("N24_2", N24_2);
	}

	@DBMeta(column = "N24_3", name = "XXXX使用公约", type = "String")
	public String getN24_3() {
		return N24_3;
	}

	public void setN24_3(String N24_3) {
		this.N24_3 = N24_3;
		this.settings.put("N24_3", N24_3);
	}

	@DBMeta(column = "N25_1", name = "与XXXX签订的土地使用权", type = "String")
	public String getN25_1() {
		return N25_1;
	}

	public void setN25_1(String N25_1) {
		this.N25_1 = N25_1;
		this.settings.put("N25_1", N25_1);
	}

	@DBMeta(column = "N27_1", name = "投邮后", type = "String")
	public String getN27_1() {
		return N27_1;
	}

	public void setN27_1(String N27_1) {
		this.N27_1 = N27_1;
		this.settings.put("N27_1", N27_1);
	}

	@DBMeta(column = "N31_1", name = "XXXX公证处", type = "String")
	public String getN31_1() {
		return N31_1;
	}

	public void setN31_1(String N31_1) {
		this.N31_1 = N31_1;
		this.settings.put("N31_1", N31_1);
	}

	@DBMeta(column = "N31_2", name = "本合同生效之日起XX日内", type = "String")
	public String getN31_2() {
		return N31_2;
	}

	public void setN31_2(String N31_2) {
		this.N31_2 = N31_2;
		this.settings.put("N31_2", N31_2);
	}

	@DBMeta(column = "N32_1", name = "房地产登记机构名", type = "int")
	public int getN32_1() {
		return N32_1;
	}

	public void setN32_1(int N32_1) {
		this.N32_1 = N32_1;
		this.settings.put("N32_1", N32_1);
	}

	@DBMeta(column = "N33_1", name = "第XX种方式解决", type = "int")
	public int getN33_1() {
		return N33_1;
	}

	public void setN33_1(int N33_1) {
		this.N33_1 = N33_1;
		this.settings.put("N33_1", N33_1);
	}

	@DBMeta(column = "N33_2", name = "向XXXX仲裁委员会申请仲裁", type = "String")
	public String getN33_2() {
		return N33_2;
	}

	public void setN33_2(String N33_2) {
		this.N33_2 = N33_2;
		this.settings.put("N33_2", N33_2);
	}

	@DBMeta(column = "N34_1", name = "本合同壹式XX份", type = "String")
	public String getN34_1() {
		return N34_1;
	}

	public void setN34_1(String N34_1) {
		this.N34_1 = N34_1;
		this.settings.put("N34_1", N34_1);
	}

	@DBMeta(column = "N34_2", name = "甲、乙双方各执XX份", type = "String")
	public String getN34_2() {
		return N34_2;
	}

	public void setN34_2(String N34_2) {
		this.N34_2 = N34_2;
		this.settings.put("N34_2", N34_2);
	}

	@DBMeta(column = "N34_3", name = "合同持有人1", type = "String")
	public String getN34_3() {
		return N34_3;
	}

	public void setN34_3(String N34_3) {
		this.N34_3 = N34_3;
		this.settings.put("N34_3", N34_3);
	}

	@DBMeta(column = "N34_4", name = "合同持有人2", type = "String")
	public String getN34_4() {
		return N34_4;
	}

	public void setN34_4(String N34_4) {
		this.N34_4 = N34_4;
		this.settings.put("N34_4", N34_4);
	}

	@DBMeta(column = "N34_5", name = "合同持有人3", type = "String")
	public String getN34_5() {
		return N34_5;
	}

	public void setN34_5(String N34_5) {
		this.N34_5 = N34_5;
		this.settings.put("N34_5", N34_5);
	}

	@DBMeta(column = "FITMENT_PRICE_CN", name = "房屋全装修总价大写", type = "String")
	public String getFitmentPriceCn() {
		return fitmentPriceCn;
	}

	public void setFitmentPriceCn(String fitmentPriceCn) {
		this.fitmentPriceCn = fitmentPriceCn;
		this.settings.put("fitmentPriceCn", fitmentPriceCn);
	}

	@DBMeta(column = "FITMENT_PRICE", name = "房屋全装修总价小写", type = "double")
	public double getFitmentPrice() {
		return fitmentPrice;
	}

	public void setFitmentPrice(double fitmentPrice) {
		this.fitmentPrice = fitmentPrice;
		this.settings.put("fitmentPrice", fitmentPrice);
	}

	@DBMeta(column = "CHARGE_DEP", name = "主管部门", type = "String")
	public String getChargeDep() {
		return chargeDep;
	}

	public void setChargeDep(String chargeDep) {
		this.chargeDep = chargeDep;
		this.settings.put("chargeDep", chargeDep);
	}
	@DBMeta(column = "N5_5", name = "", type = "double")
	public double getN5_5() {
		return N5_5;
	}

	public void setN5_5(double N5_5) {
		this.N5_5 = N5_5;
		this.settings.put("N5_5", N5_5);
	}
	@DBMeta(column = "N5_6", name = "", type = "int")
	public int getN5_6() {
		return N5_6;
	}

	public void setN5_6(int N5_6) {
		this.N5_6 = N5_6;
		this.settings.put("N5_6", N5_6);
	}
	@DBMeta(column = "N32_2", name = "", type = "int")
	public int getN32_2() {
		return N32_2;
	}

	public void setN32_2(int N32_2) {
		this.N32_2 = N32_2;
		this.settings.put("N32_2", N32_2);
	}

}
