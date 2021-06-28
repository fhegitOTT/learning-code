package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "SELLTEMPLATE", sequence = "SEQ_TEMPLATE_ID", id = DBModel.SequenceID)
public class SellTemplateVO extends FHVO {

	private long templateID; // 模版ID
	private int projectID; // 项目ID
	private long startID; // 开盘ID
	private String tempname; // 模版名
	private int regtype; // 登记方式
	private int resolvetype; // 解决方式
	private int bldnumtype; // 幢号区分
	private int landpacttype; // 土地使用权合同类型
	private String project_Name; // 楼盘名称
	private int housetype; // 房屋类型
	private String cellar_Area; // 地下附属面积
	private String lane_Name; // 号（对应大机弄）
	private String sub_lane; // 栋（对应大机支弄）
	private String n1_1; // 区县名
	private String n1_2; // 地块名
	private String n1_3; // 出让合同编号
	private String n1_4; // 楼盘名称
	private String n1_5; // 房地产权证号
	private String n2_1; // 路名
	private String n2_2; // 弄号
	private String n2_3; // 楼盘名称
	private String n2_4; // 幢号
	private String n2_5; // 层号
	private String n2_6; // 室号
	private String n2_7; // 测绘机构名
	private String n2_8; // 房屋建筑面积
	private String n2_9; // 套内建筑面积
	private String n2_10; // 公用分摊建筑面积
	private String n2_11; // 建筑层高
	private int n2_Room; // 室数
	private int n2_Hall; // 厅数
	private int n2_Toilet; // 卫生间数
	private int n2_Cookroom; // 厨房数
	private String n3_1; // 建筑面积单价
	private String n3_2; // 建筑面积单价(大写)
	private String n3_3; // 总房价款
	private String n3_4; // 总房价款(大写)
	private String n4_1; // 监管机构名
	private String n4_2; // 监管机构帐户名称
	private String n4_3; // 监管机构帐号
	private String n5_1; // 违约金比例
	private String n5_2; // 逾期天数
	private int n5_3; // 赔偿方案种类
	private int n7_1; // 交付方案种类
	private String n7_2; // 签署之日起XX天
	private String n7_3; // 公证处名
	private String n7_4; // 公证之日起XX天
	private String n7_5; // 收到房价款之日起XX天内
	private String n9_1; // 房屋交付的标志
	private String n9_2; // 房屋用途
	private String n9_3; // 质量保证书名
	private String n9_4; // 使用说明书名
	private String n9_5; // 物业管理服务合同名
	private String n10_1; // 设备差价的倍数
	private String n12_1; // 赔偿金限制天数
	private String n12_2; // 赔偿金额百分比
	private String n13_1; // 违约金比例
	private String n13_2; // 逾期超过天数
	private int n13_3; // 方案种类
	private String n14_1; // 保修年数
	private String n16_1; // 修复费的倍数
	private String n19_1; // 登记年
	private String n19_2; // 登记月
	private String n19_3; // 登记日
	private int n19_4; // 房地产登记机构名
	private String n19_5; // 获得产证年期限
	private String n19_6; // 获得产证月期限
	private String n19_7; // 获得产证日期限
	private String n19_8; // 违约金比例
	private String n19_9; // 无法取得房地产权证起始年
	private String n19_10; //无法取得房地产权证起始月
	private String n19_11; // 无法取得房地产权证起始日
	private String n19_12; // 起始年月日起的天数
	private String n20_1; // 甲方收到乙方书面通知之日起的天数
	private String n20_2; // 赔偿金额比例
	private String n22_1; // 解除本合同的书面通知之日起的天数
	private String n23_1; // 使用类型
	private String n24_1; // 甲方与XX签订的土地使用权
	private String n27_1; // 仲裁委员会名
	private String n28_1; // 公证处名
	private String n29_1; // 合同份数
	private String n29_2; // 甲、乙双方各执份数
	private String n29_3; // 合同持有人1
	private String n29_4; // 合同持有人2
	private String n29_5; // 合同持有人3
	private String chargeDep; // 主管部门 

	@DBMeta(column = "template_ID", name = "templateID", type = "long", primarykey = "true", can_update = "false")
	public long getTemplateID() {
		return templateID;
	}

	public void setTemplateID(long templateID) {
		this.templateID = templateID;
		this.settings.put("templateID", templateID);
	}

	@DBMeta(column = "project_ID", name = "projectID", type = "int")
	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
		this.settings.put("projectID", projectID);
	}

	@DBMeta(column = "start_ID", name = "startID", type = "long")
	public long getStartID() {
		return startID;
	}

	public void setStartID(long startID) {
		this.startID = startID;
		this.settings.put("startID", startID);
	}

	@DBMeta(column = "tempname", name = "tempname", type = "String")
	public String getTempname() {
		return tempname;
	}

	public void setTempname(String tempname) {
		this.tempname = tempname;
		this.settings.put("tempname", tempname);
	}

	@DBMeta(column = "regtype", name = "regtype", type = "int")
	public int getRegtype() {
		return regtype;
	}

	public void setRegtype(int regtype) {
		this.regtype = regtype;
		this.settings.put("regtype", regtype);
	}

	@DBMeta(column = "resolvetype", name = "resolvetype", type = "int")
	public int getResolvetype() {
		return resolvetype;
	}

	public void setResolvetype(int resolvetype) {
		this.resolvetype = resolvetype;
		this.settings.put("resolvetype", resolvetype);
	}

	@DBMeta(column = "bldnumtype", name = "bldnumtype", type = "int")
	public int getBldnumtype() {
		return bldnumtype;
	}

	public void setBldnumtype(int bldnumtype) {
		this.bldnumtype = bldnumtype;
		this.settings.put("bldnumtype", bldnumtype);
	}

	@DBMeta(column = "landpacttype", name = "landpacttype", type = "int")
	public int getLandpacttype() {
		return landpacttype;
	}

	public void setLandpacttype(int landpacttype) {
		this.landpacttype = landpacttype;
		this.settings.put("landpacttype", landpacttype);
	}

	@DBMeta(column = "project_Name", name = "project_Name", type = "String")
	public String getProject_Name() {
		return project_Name;
	}

	public void setProject_Name(String project_Name) {
		this.project_Name = project_Name;
		this.settings.put("project_Name", project_Name);
	}

	@DBMeta(column = "housetype", name = "housetype", type = "int")
	public int getHousetype() {
		return housetype;
	}

	public void setHousetype(int housetype) {
		this.housetype = housetype;
		this.settings.put("housetype", housetype);
	}

	@DBMeta(column = "cellar_Area", name = "cellar_Area", type = "String")
	public String getCellar_Area() {
		return cellar_Area;
	}

	public void setCellar_Area(String cellar_Area) {
		this.cellar_Area = cellar_Area;
		this.settings.put("cellar_Area", cellar_Area);
	}

	@DBMeta(column = "n1_1", name = "n1_1", type = "String")
	public String getn1_1() {
		return n1_1;
	}

	public void setn1_1(String n1_1) {
		this.n1_1 = n1_1;
		this.settings.put("n1_1", n1_1);
	}

	@DBMeta(column = "n1_2", name = "n1_2", type = "String")
	public String getn1_2() {
		return n1_2;
	}

	public void setn1_2(String n1_2) {
		this.n1_2 = n1_2;
		this.settings.put("n1_2", n1_2);
	}

	@DBMeta(column = "n1_3", name = "n1_3", type = "String")
	public String getn1_3() {
		return n1_3;
	}

	public void setn1_3(String n1_3) {
		this.n1_3 = n1_3;
		this.settings.put("n1_3", n1_3);
	}

	@DBMeta(column = "n1_4", name = "n1_4", type = "String")
	public String getn1_4() {
		return n1_4;
	}

	public void setn1_4(String n1_4) {
		this.n1_4 = n1_4;
		this.settings.put("n1_4", n1_4);
	}

	@DBMeta(column = "n1_5", name = "n1_5", type = "String")
	public String getn1_5() {
		return n1_5;
	}

	public void setn1_5(String n1_5) {
		this.n1_5 = n1_5;
		this.settings.put("n1_5", n1_5);
	}

	@DBMeta(column = "n2_1", name = "n2_1", type = "String")
	public String getn2_1() {
		return n2_1;
	}

	public void setn2_1(String n2_1) {
		this.n2_1 = n2_1;
		this.settings.put("n2_1", n2_1);
	}

	@DBMeta(column = "n2_2", name = "n2_2", type = "String")
	public String getn2_2() {
		return n2_2;
	}

	public void setn2_2(String n2_2) {
		this.n2_2 = n2_2;
		this.settings.put("n2_2", n2_2);
	}

	@DBMeta(column = "n2_3", name = "n2_3", type = "String")
	public String getn2_3() {
		return n2_3;
	}

	public void setn2_3(String n2_3) {
		this.n2_3 = n2_3;
		this.settings.put("n2_3", n2_3);
	}

	@DBMeta(column = "n2_4", name = "n2_4", type = "String")
	public String getn2_4() {
		return n2_4;
	}

	public void setn2_4(String n2_4) {
		this.n2_4 = n2_4;
		this.settings.put("n2_4", n2_4);
	}

	@DBMeta(column = "n2_5", name = "n2_5", type = "String")
	public String getn2_5() {
		return n2_5;
	}

	public void setn2_5(String n2_5) {
		this.n2_5 = n2_5;
		this.settings.put("n2_5", n2_5);
	}

	@DBMeta(column = "n2_6", name = "n2_6", type = "String")
	public String getn2_6() {
		return n2_6;
	}

	public void setn2_6(String n2_6) {
		this.n2_6 = n2_6;
		this.settings.put("n2_6", n2_6);
	}

	@DBMeta(column = "n2_7", name = "n2_7", type = "String")
	public String getn2_7() {
		return n2_7;
	}

	public void setn2_7(String n2_7) {
		this.n2_7 = n2_7;
		this.settings.put("n2_7", n2_7);
	}

	@DBMeta(column = "n2_8", name = "n2_8", type = "String")
	public String getn2_8() {
		return n2_8;
	}

	public void setn2_8(String n2_8) {
		this.n2_8 = n2_8;
		this.settings.put("n2_8", n2_8);
	}

	@DBMeta(column = "n2_9", name = "n2_9", type = "String")
	public String getn2_9() {
		return n2_9;
	}

	public void setn2_9(String n2_9) {
		this.n2_9 = n2_9;
		this.settings.put("n2_9", n2_9);
	}

	@DBMeta(column = "n2_10", name = "n2_10", type = "String")
	public String getn2_10() {
		return n2_10;
	}

	public void setn2_10(String n2_10) {
		this.n2_10 = n2_10;
		this.settings.put("n2_10", n2_10);
	}

	@DBMeta(column = "n2_11", name = "n2_11", type = "String")
	public String getn2_11() {
		return n2_11;
	}

	public void setn2_11(String n2_11) {
		this.n2_11 = n2_11;
		this.settings.put("n2_11", n2_11);
	}

	@DBMeta(column = "n2_Room", name = "n2_Room", type = "int")
	public int getn2_Room() {
		return n2_Room;
	}

	public void setn2_Room(int n2_Room) {
		this.n2_Room = n2_Room;
		this.settings.put("n2_Room", n2_Room);
	}

	@DBMeta(column = "n2_Hall", name = "n2_Hall", type = "int")
	public int getn2_Hall() {
		return n2_Hall;
	}

	public void setn2_Hall(int n2_Hall) {
		this.n2_Hall = n2_Hall;
		this.settings.put("n2_Hall", n2_Hall);
	}

	@DBMeta(column = "n2_Toilet", name = "n2_Toilet", type = "int")
	public int getn2_Toilet() {
		return n2_Toilet;
	}

	public void setn2_Toilet(int n2_Toilet) {
		this.n2_Toilet = n2_Toilet;
		this.settings.put("n2_Toilet", n2_Toilet);
	}

	@DBMeta(column = "n2_Cookroom", name = "n2_Cookroom", type = "int")
	public int getn2_Cookroom() {
		return n2_Cookroom;
	}

	public void setn2_Cookroom(int n2_Cookroom) {
		this.n2_Cookroom = n2_Cookroom;
		this.settings.put("n2_Cookroom", n2_Cookroom);
	}

	@DBMeta(column = "n3_1", name = "n3_1", type = "String")
	public String getn3_1() {
		return n3_1;
	}

	public void setn3_1(String n3_1) {
		this.n3_1 = n3_1;
		this.settings.put("n3_1", n3_1);
	}

	@DBMeta(column = "n3_2", name = "n3_2", type = "String")
	public String getn3_2() {
		return n3_2;
	}

	public void setn3_2(String n3_2) {
		this.n3_2 = n3_2;
		this.settings.put("n3_2", n3_2);
	}

	@DBMeta(column = "n3_3", name = "n3_3", type = "String")
	public String getn3_3() {
		return n3_3;
	}

	public void setn3_3(String n3_3) {
		this.n3_3 = n3_3;
		this.settings.put("n3_3", n3_3);
	}

	@DBMeta(column = "n3_4", name = "n3_4", type = "String")
	public String getn3_4() {
		return n3_4;
	}

	public void setn3_4(String n3_4) {
		this.n3_4 = n3_4;
		this.settings.put("n3_4", n3_4);
	}

	@DBMeta(column = "n4_1", name = "n4_1", type = "String")
	public String getn4_1() {
		return n4_1;
	}

	public void setn4_1(String n4_1) {
		this.n4_1 = n4_1;
		this.settings.put("n4_1", n4_1);
	}

	@DBMeta(column = "n4_2", name = "n4_2", type = "String")
	public String getn4_2() {
		return n4_2;
	}

	public void setn4_2(String n4_2) {
		this.n4_2 = n4_2;
		this.settings.put("n4_2", n4_2);
	}

	@DBMeta(column = "n4_3", name = "n4_3", type = "String")
	public String getn4_3() {
		return n4_3;
	}

	public void setn4_3(String n4_3) {
		this.n4_3 = n4_3;
		this.settings.put("n4_3", n4_3);
	}

	@DBMeta(column = "n5_1", name = "n5_1", type = "String")
	public String getn5_1() {
		return n5_1;
	}

	public void setn5_1(String n5_1) {
		this.n5_1 = n5_1;
		this.settings.put("n5_1", n5_1);
	}

	@DBMeta(column = "n5_2", name = "n5_2", type = "String")
	public String getn5_2() {
		return n5_2;
	}

	public void setn5_2(String n5_2) {
		this.n5_2 = n5_2;
		this.settings.put("n5_2", n5_2);
	}

	@DBMeta(column = "n5_3", name = "n5_3", type = "int")
	public int getn5_3() {
		return n5_3;
	}

	public void setn5_3(int n5_3) {
		this.n5_3 = n5_3;
		this.settings.put("n5_3", n5_3);
	}

	@DBMeta(column = "n7_1", name = "n7_1", type = "int")
	public int getn7_1() {
		return n7_1;
	}

	public void setn7_1(int n7_1) {
		this.n7_1 = n7_1;
		this.settings.put("n7_1", n7_1);
	}

	@DBMeta(column = "n7_2", name = "n7_2", type = "String")
	public String getn7_2() {
		return n7_2;
	}

	public void setn7_2(String n7_2) {
		this.n7_2 = n7_2;
		this.settings.put("n7_2", n7_2);
	}

	@DBMeta(column = "n7_3", name = "n7_3", type = "String")
	public String getn7_3() {
		return n7_3;
	}

	public void setn7_3(String n7_3) {
		this.n7_3 = n7_3;
		this.settings.put("n7_3", n7_3);
	}

	@DBMeta(column = "n7_4", name = "n7_4", type = "String")
	public String getn7_4() {
		return n7_4;
	}

	public void setn7_4(String n7_4) {
		this.n7_4 = n7_4;
		this.settings.put("n7_4", n7_4);
	}

	@DBMeta(column = "n7_5", name = "n7_5", type = "String")
	public String getn7_5() {
		return n7_5;
	}

	public void setn7_5(String n7_5) {
		this.n7_5 = n7_5;
		this.settings.put("n7_5", n7_5);
	}

	@DBMeta(column = "n9_1", name = "n9_1", type = "String")
	public String getn9_1() {
		return n9_1;
	}

	public void setn9_1(String n9_1) {
		this.n9_1 = n9_1;
		this.settings.put("n9_1", n9_1);
	}

	@DBMeta(column = "n9_2", name = "n9_2", type = "String")
	public String getn9_2() {
		return n9_2;
	}

	public void setn9_2(String n9_2) {
		this.n9_2 = n9_2;
		this.settings.put("n9_2", n9_2);
	}

	@DBMeta(column = "n9_3", name = "n9_3", type = "String")
	public String getn9_3() {
		return n9_3;
	}

	public void setn9_3(String n9_3) {
		this.n9_3 = n9_3;
		this.settings.put("n9_3", n9_3);
	}

	@DBMeta(column = "n9_4", name = "n9_4", type = "String")
	public String getn9_4() {
		return n9_4;
	}

	public void setn9_4(String n9_4) {
		this.n9_4 = n9_4;
		this.settings.put("n9_4", n9_4);
	}

	@DBMeta(column = "n9_5", name = "n9_5", type = "String")
	public String getn9_5() {
		return n9_5;
	}

	public void setn9_5(String n9_5) {
		this.n9_5 = n9_5;
		this.settings.put("n9_5", n9_5);
	}

	@DBMeta(column = "n10_1", name = "n10_1", type = "String")
	public String getn10_1() {
		return n10_1;
	}

	public void setn10_1(String n10_1) {
		this.n10_1 = n10_1;
		this.settings.put("n10_1", n10_1);
	}

	@DBMeta(column = "n12_1", name = "n12_1", type = "String")
	public String getn12_1() {
		return n12_1;
	}

	public void setn12_1(String n12_1) {
		this.n12_1 = n12_1;
		this.settings.put("n12_1", n12_1);
	}

	@DBMeta(column = "n12_2", name = "n12_2", type = "String")
	public String getn12_2() {
		return n12_2;
	}

	public void setn12_2(String n12_2) {
		this.n12_2 = n12_2;
		this.settings.put("n12_2", n12_2);
	}

	@DBMeta(column = "n13_1", name = "n13_1", type = "String")
	public String getn13_1() {
		return n13_1;
	}

	public void setn13_1(String n13_1) {
		this.n13_1 = n13_1;
		this.settings.put("n13_1", n13_1);
	}

	@DBMeta(column = "n13_2", name = "n13_2", type = "String")
	public String getn13_2() {
		return n13_2;
	}

	public void setn13_2(String n13_2) {
		this.n13_2 = n13_2;
		this.settings.put("n13_2", n13_2);
	}

	@DBMeta(column = "n13_3", name = "n13_3", type = "int")
	public int getn13_3() {
		return n13_3;
	}

	public void setn13_3(int n13_3) {
		this.n13_3 = n13_3;
		this.settings.put("n13_3", n13_3);
	}

	@DBMeta(column = "n14_1", name = "n14_1", type = "String")
	public String getn14_1() {
		return n14_1;
	}

	public void setn14_1(String n14_1) {
		this.n14_1 = n14_1;
		this.settings.put("n14_1", n14_1);
	}

	@DBMeta(column = "n16_1", name = "n16_1", type = "String")
	public String getn16_1() {
		return n16_1;
	}

	public void setn16_1(String n16_1) {
		this.n16_1 = n16_1;
		this.settings.put("n16_1", n16_1);
	}

	@DBMeta(column = "n19_1", name = "n19_1", type = "String")
	public String getn19_1() {
		return n19_1;
	}

	public void setn19_1(String n19_1) {
		this.n19_1 = n19_1;
		this.settings.put("n19_1", n19_1);
	}

	@DBMeta(column = "n19_2", name = "n19_2", type = "String")
	public String getn19_2() {
		return n19_2;
	}

	public void setn19_2(String n19_2) {
		this.n19_2 = n19_2;
		this.settings.put("n19_2", n19_2);
	}

	@DBMeta(column = "n19_3", name = "n19_3", type = "String")
	public String getn19_3() {
		return n19_3;
	}

	public void setn19_3(String n19_3) {
		this.n19_3 = n19_3;
		this.settings.put("n19_3", n19_3);
	}

	@DBMeta(column = "n19_4", name = "n19_4", type = "int")
	public int getn19_4() {
		return n19_4;
	}

	public void setn19_4(int n19_4) {
		this.n19_4 = n19_4;
		this.settings.put("n19_4", n19_4);
	}

	@DBMeta(column = "n19_5", name = "n19_5", type = "String")
	public String getn19_5() {
		return n19_5;
	}

	public void setn19_5(String n19_5) {
		this.n19_5 = n19_5;
		this.settings.put("n19_5", n19_5);
	}

	@DBMeta(column = "n19_6", name = "n19_6", type = "String")
	public String getn19_6() {
		return n19_6;
	}

	public void setn19_6(String n19_6) {
		this.n19_6 = n19_6;
		this.settings.put("n19_6", n19_6);
	}

	@DBMeta(column = "n19_7", name = "n19_7", type = "String")
	public String getn19_7() {
		return n19_7;
	}

	public void setn19_7(String n19_7) {
		this.n19_7 = n19_7;
		this.settings.put("n19_7", n19_7);
	}

	@DBMeta(column = "n19_8", name = "n19_8", type = "String")
	public String getn19_8() {
		return n19_8;
	}

	public void setn19_8(String n19_8) {
		this.n19_8 = n19_8;
		this.settings.put("n19_8", n19_8);
	}

	@DBMeta(column = "n19_9", name = "n19_9", type = "String")
	public String getn19_9() {
		return n19_9;
	}

	public void setn19_9(String n19_9) {
		this.n19_9 = n19_9;
		this.settings.put("n19_9", n19_9);
	}

	@DBMeta(column = "n19_10", name = "n19_10", type = "String")
	public String getn19_10() {
		return n19_10;
	}

	public void setn19_10(String n19_10) {
		this.n19_10 = n19_10;
		this.settings.put("n19_10", n19_10);
	}

	@DBMeta(column = "n19_11", name = "n19_11", type = "String")
	public String getn19_11() {
		return n19_11;
	}

	public void setn19_11(String n19_11) {
		this.n19_11 = n19_11;
		this.settings.put("n19_11", n19_11);
	}

	@DBMeta(column = "n19_12", name = "n19_12", type = "String")
	public String getn19_12() {
		return n19_12;
	}

	public void setn19_12(String n19_12) {
		this.n19_12 = n19_12;
		this.settings.put("n19_12", n19_12);
	}

	@DBMeta(column = "n20_1", name = "n20_1", type = "String")
	public String getn20_1() {
		return n20_1;
	}

	public void setn20_1(String n20_1) {
		this.n20_1 = n20_1;
		this.settings.put("n20_1", n20_1);
	}

	@DBMeta(column = "n20_2", name = "n20_2", type = "String")
	public String getn20_2() {
		return n20_2;
	}

	public void setn20_2(String n20_2) {
		this.n20_2 = n20_2;
		this.settings.put("n20_2", n20_2);
	}

	@DBMeta(column = "n22_1", name = "n22_1", type = "String")
	public String getn22_1() {
		return n22_1;
	}

	public void setn22_1(String n22_1) {
		this.n22_1 = n22_1;
		this.settings.put("n22_1", n22_1);
	}

	@DBMeta(column = "n23_1", name = "n23_1", type = "String")
	public String getn23_1() {
		return n23_1;
	}

	public void setn23_1(String n23_1) {
		this.n23_1 = n23_1;
		this.settings.put("n23_1", n23_1);
	}

	@DBMeta(column = "n24_1", name = "n24_1", type = "String")
	public String getn24_1() {
		return n24_1;
	}

	public void setn24_1(String n24_1) {
		this.n24_1 = n24_1;
		this.settings.put("n24_1", n24_1);
	}

	@DBMeta(column = "n27_1", name = "n27_1", type = "String")
	public String getn27_1() {
		return n27_1;
	}

	public void setn27_1(String n27_1) {
		this.n27_1 = n27_1;
		this.settings.put("n27_1", n27_1);
	}

	@DBMeta(column = "n28_1", name = "n28_1", type = "String")
	public String getn28_1() {
		return n28_1;
	}

	public void setn28_1(String n28_1) {
		this.n28_1 = n28_1;
		this.settings.put("n28_1", n28_1);
	}

	@DBMeta(column = "n29_1", name = "n29_1", type = "String")
	public String getn29_1() {
		return n29_1;
	}

	public void setn29_1(String n29_1) {
		this.n29_1 = n29_1;
		this.settings.put("n29_1", n29_1);
	}

	@DBMeta(column = "n29_2", name = "n29_2", type = "String")
	public String getn29_2() {
		return n29_2;
	}

	public void setn29_2(String n29_2) {
		this.n29_2 = n29_2;
		this.settings.put("n29_2", n29_2);
	}

	@DBMeta(column = "n29_3", name = "n29_3", type = "String")
	public String getn29_3() {
		return n29_3;
	}

	public void setn29_3(String n29_3) {
		this.n29_3 = n29_3;
		this.settings.put("n29_3", n29_3);
	}

	@DBMeta(column = "n29_4", name = "n29_4", type = "String")
	public String getn29_4() {
		return n29_4;
	}

	public void setn29_4(String n29_4) {
		this.n29_4 = n29_4;
		this.settings.put("n29_4", n29_4);
	}

	@DBMeta(column = "n29_5", name = "n29_5", type = "String")
	public String getn29_5() {
		return n29_5;
	}

	public void setn29_5(String n29_5) {
		this.n29_5 = n29_5;
		this.settings.put("n29_5", n29_5);
	}

	public String getTypeName() {
		String type = (String) this.map.get("typename");
		if(type != null && "1".equals(type)){
			return "预售合同";
		}else{
			return "出售合同";
		}
	}

	@DBMeta(column = "lane_Name", name = "号（对应大机弄）", type = "String")
	public String getLane_Name() {
		return lane_Name;
	}

	public void setLane_Name(String lane_Name) {
		this.lane_Name = lane_Name;
		this.settings.put("lane_Name", lane_Name);
	}

	@DBMeta(column = "sub_lane", name = "栋（对应大机支弄）", type = "String")
	public String getSub_lane() {
		return sub_lane;
	}

	public void setSub_lane(String sub_lane) {
		this.sub_lane = sub_lane;
		this.settings.put("sub_lane", sub_lane);
	}

	@DBMeta(column = "charge_Dep", name = "主管部门", type = "String")
	public String getChargeDep() {
		return chargeDep;
	}

	public void setChargeDep(String chargeDep) {
		this.chargeDep = chargeDep;
		this.settings.put("chargeDep", chargeDep);
	}

}
