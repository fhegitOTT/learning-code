package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "PRESELLTEMPLATE", sequence = "SEQ_TEMPLATE_ID", id = DBModel.SequenceID)
public class PresellTemplateVO extends FHVO {

	private long templateID; // 模版ID
	private int projectID; // 项目ID
	private long startID; // 开盘ID
	private String tempname; // 模版名
	private String project_Name; // 楼盘名称
	private int regtype; // 登记方式
	private int landattorntype; // 土地划拨方式
	private int landpacttype; // 土地使用权合同类型
	private int bldnumtype; // 幢号区分
	private int housetype; // 房屋类型
	private String cellar_Area; // 地下附属面积
	private String lane_Name; // 号（对应大机弄）
	private String sub_lane; // 栋（对应大机支弄）
	private String n1_1; // 区县名
	private String n1_2; // 地块名
	private String n1_3; // 房地产权证号
	private String n1_4; // 土地面积
	private String n1_5; // 土地用途
	private String n1_6; // 楼盘名称
	private String n1_7; // 建筑结构
	private int n1_8; // 地上层数
	private int n1_9; // 地下层数
	private String n1_10; // 房屋土地管理局名
	private String n1_11; // 预售许可证编号
	private String n2_1; // 路名
	private String n2_2; // 弄号
	private String n2_3; // 楼盘名称
	private String n2_4; // 幢号
	private String n2_5; // 层号
	private String n2_6; // 房间号
	private String n2_7; // 规划用途
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
	private String n5_1; // 误差超过值1
	private String n5_2; // 误差包括值1
	private String n5_3; // 误差超过值2
	private String n5_4; // 误差包括值2
	private String n6_1; // 工程阶段
	private String n6_2; // 预收款监管机构
	private String n6_3; // 预收款监管机构帐户名称
	private String n6_4; // 预收款监管机构帐号
	private String n7_1; // 违约金比例
	private String n7_2; // 逾期天数
	private int n7_3; // 赔偿方案种类
	private String n7_4; // 赔偿金额比例
	private String n8_1; // 批准之日起有效天数
	private String n9_1; // 违约金百分比
	private int n10_1; // 方案种类
	private String n10_2; // 承诺登记年
	private String n10_3; // 承诺登记月
	private String n10_4; // 承诺登记日
	private String n11_1; // 合同第11条第1个填充项 年
	private String n11_2; // 合同第11条第2个填充项 月
	private String n11_3; // 合同第11条第3个填充项 日
	private String n12_1; // 违约金比例
	private String n12_2; // 逾期天数
	private int n12_3; // 方案种类
	private String n13_1; // 交付之日起有效天数
	private String n13_2; // 通知之日起有效天数
	private String n13_3; // 房屋交付的标志
	private String n13_4; // 房屋用途
	private String n13_5; // 质量保证书名
	private String n13_6; // 使用说明书名
	private String n14_1; // 取得房地产权证后有效天数
	private String n14_2; // 签署《房屋交接书》之日起有效天数
	private int n14_3; // 交易中心名
	private String n17_1; // 设备差价的倍数
	private String n19_1; // 甲方收到乙方的书面通知起的有效天数
	private String n19_2; // 赔偿金额百分比
	private String n21_1; // 解除本合同的书面通知之日起的有效天数
	private String n22_1; // 修复费的倍数
	private String n24_1; // 物业公司名
	private String n24_2; // 房屋规划用途
	private String n24_3; // XXXX使用公约
	private String n25_1; // 与XXXX签订的土地使用权 
	private String n27_1; // 投邮后(以寄出的邮戳为准)第XX日
	private String n31_1; // XXXX公证处
	private String n31_2; // 本合同生效之日起XX日内
	private int n32_1; // 房地产登记机构名
	private int n33_1; // 第XX种方式解决
	private String n33_2; // 向XXXX仲裁委员会申请仲裁
	private String n34_1; // 本合同壹式XX份
	private String n34_2; // 甲、乙双方各执XX份
	private String n34_3; // 合同持有人1
	private String n34_4; // 合同持有人2
	private String n34_5; // 合同持有人3
	private String chargeDep; // 主管部门 
	private String n5_5; //  
	private int n5_6; //  
	private int n32_2; //  

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

	@DBMeta(column = "project_Name", name = "project_Name", type = "String")
	public String getProject_Name() {
		return project_Name;
	}

	public void setProject_Name(String project_Name) {
		this.project_Name = project_Name;
		this.settings.put("project_Name", project_Name);
	}
	@DBMeta(column = "regtype", name = "regtype", type = "int")
	public int getRegtype() {
		return regtype;
	}
	public void setRegtype(int regtype) {
		this.regtype = regtype;
		this.settings.put("regtype", regtype);
	}
	@DBMeta(column = "landattorntype", name = "landattorntype", type = "int")
	public int getLandattorntype() {
		return landattorntype;
	}
	public void setLandattorntype(int landattorntype) {
		this.landattorntype = landattorntype;
		this.settings.put("landattorntype", landattorntype);
	}
	@DBMeta(column = "landpacttype", name = "landpacttype", type = "int")
	public int getLandpacttype() {
		return landpacttype;
	}
	public void setLandpacttype(int landpacttype) {
		this.landpacttype = landpacttype;
		this.settings.put("landpacttype", landpacttype);
	}

	@DBMeta(column = "bldnumtype", name = "bldnumtype", type = "int", dict_table = "CT_508")
	public int getBldnumtype() {
		return bldnumtype;
	}
	public void setBldnumtype(int bldnumtype) {
		this.bldnumtype = bldnumtype;
		this.settings.put("bldnumtype", bldnumtype);
	}

	@DBMeta(column = "housetype", name = "housetype", type = "int", dict_table = "CT_539")
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

	@DBMeta(column = "n1_6", name = "n1_6", type = "String")
	public String getn1_6() {
		return n1_6;
	}

	public void setn1_6(String n1_6) {
		this.n1_6 = n1_6;
		this.settings.put("n1_6", n1_6);
	}

	@DBMeta(column = "n1_7", name = "n1_7", type = "String")
	public String getn1_7() {
		return n1_7;
	}

	public void setn1_7(String n1_7) {
		this.n1_7 = n1_7;
		this.settings.put("n1_7", n1_7);
	}

	@DBMeta(column = "n1_8", name = "n1_8", type = "int")
	public int getn1_8() {
		return n1_8;
	}

	public void setn1_8(int n1_8) {
		this.n1_8 = n1_8;
		this.settings.put("n1_8", n1_8);
	}

	@DBMeta(column = "n1_9", name = "n1_9", type = "int")
	public int getn1_9() {
		return n1_9;
	}

	public void setn1_9(int n1_9) {
		this.n1_9 = n1_9;
		this.settings.put("n1_9", n1_9);
	}

	@DBMeta(column = "n1_10", name = "n1_10", type = "String")
	public String getn1_10() {
		return n1_10;
	}

	public void setn1_10(String n1_10) {
		this.n1_10 = n1_10;
		this.settings.put("n1_10", n1_10);
	}

	@DBMeta(column = "n1_11", name = "n1_11", type = "String")
	public String getn1_11() {
		return n1_11;
	}

	public void setn1_11(String n1_11) {
		this.n1_11 = n1_11;
		this.settings.put("n1_11", n1_11);
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

	@DBMeta(column = "n5_3", name = "n5_3", type = "String")
	public String getn5_3() {
		return n5_3;
	}

	public void setn5_3(String n5_3) {
		this.n5_3 = n5_3;
		this.settings.put("n5_3", n5_3);
	}

	@DBMeta(column = "n5_4", name = "n5_4", type = "String")
	public String getn5_4() {
		return n5_4;
	}

	public void setn5_4(String n5_4) {
		this.n5_4 = n5_4;
		this.settings.put("n5_4", n5_4);
	}

	@DBMeta(column = "n6_1", name = "n6_1", type = "String")
	public String getn6_1() {
		return n6_1;
	}

	public void setn6_1(String n6_1) {
		this.n6_1 = n6_1;
		this.settings.put("n6_1", n6_1);
	}

	@DBMeta(column = "n6_2", name = "n6_2", type = "String")
	public String getn6_2() {
		return n6_2;
	}

	public void setn6_2(String n6_2) {
		this.n6_2 = n6_2;
		this.settings.put("n6_2", n6_2);
	}

	@DBMeta(column = "n6_3", name = "n6_3", type = "String")
	public String getn6_3() {
		return n6_3;
	}

	public void setn6_3(String n6_3) {
		this.n6_3 = n6_3;
		this.settings.put("n6_3", n6_3);
	}

	@DBMeta(column = "n6_4", name = "n6_4", type = "String")
	public String getn6_4() {
		return n6_4;
	}

	public void setn6_4(String n6_4) {
		this.n6_4 = n6_4;
		this.settings.put("n6_4", n6_4);
	}

	@DBMeta(column = "n7_1", name = "n7_1", type = "String")
	public String getn7_1() {
		return n7_1;
	}

	public void setn7_1(String n7_1) {
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

	@DBMeta(column = "n7_3", name = "n7_3", type = "int")
	public int getn7_3() {
		return n7_3;
	}

	public void setn7_3(int n7_3) {
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

	@DBMeta(column = "n8_1", name = "n8_1", type = "String")
	public String getn8_1() {
		return n8_1;
	}

	public void setn8_1(String n8_1) {
		this.n8_1 = n8_1;
		this.settings.put("n8_1", n8_1);
	}

	@DBMeta(column = "n9_1", name = "n9_1", type = "String")
	public String getn9_1() {
		return n9_1;
	}

	public void setn9_1(String n9_1) {
		this.n9_1 = n9_1;
		this.settings.put("n9_1", n9_1);
	}

	@DBMeta(column = "n10_1", name = "n10_1", type = "int")
	public int getn10_1() {
		return n10_1;
	}

	public void setn10_1(int n10_1) {
		this.n10_1 = n10_1;
		this.settings.put("n10_1", n10_1);
	}

	@DBMeta(column = "n10_2", name = "n10_2", type = "String")
	public String getn10_2() {
		return n10_2;
	}

	public void setn10_2(String n10_2) {
		this.n10_2 = n10_2;
		this.settings.put("n10_2", n10_2);
	}

	@DBMeta(column = "n10_3", name = "n10_3", type = "String")
	public String getn10_3() {
		return n10_3;
	}

	public void setn10_3(String n10_3) {
		this.n10_3 = n10_3;
		this.settings.put("n10_3", n10_3);
	}

	@DBMeta(column = "n10_4", name = "n10_4", type = "String")
	public String getn10_4() {
		return n10_4;
	}

	public void setn10_4(String n10_4) {
		this.n10_4 = n10_4;
		this.settings.put("n10_4", n10_4);
	}

	@DBMeta(column = "n11_1", name = "n11_1", type = "String")
	public String getn11_1() {
		return n11_1;
	}

	public void setn11_1(String n11_1) {
		this.n11_1 = n11_1;
		this.settings.put("n11_1", n11_1);
	}

	@DBMeta(column = "n11_2", name = "n11_2", type = "String")
	public String getn11_2() {
		return n11_2;
	}

	public void setn11_2(String n11_2) {
		this.n11_2 = n11_2;
		this.settings.put("n11_2", n11_2);
	}

	@DBMeta(column = "n11_3", name = "n11_3", type = "String")
	public String getn11_3() {
		return n11_3;
	}

	public void setn11_3(String n11_3) {
		this.n11_3 = n11_3;
		this.settings.put("n11_3", n11_3);
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

	@DBMeta(column = "n12_3", name = "n12_3", type = "int")
	public int getn12_3() {
		return n12_3;
	}

	public void setn12_3(int n12_3) {
		this.n12_3 = n12_3;
		this.settings.put("n12_3", n12_3);
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

	@DBMeta(column = "n13_3", name = "n13_3", type = "String")
	public String getn13_3() {
		return n13_3;
	}

	public void setn13_3(String n13_3) {
		this.n13_3 = n13_3;
		this.settings.put("n13_3", n13_3);
	}

	@DBMeta(column = "n13_4", name = "n13_4", type = "String")
	public String getn13_4() {
		return n13_4;
	}

	public void setn13_4(String n13_4) {
		this.n13_4 = n13_4;
		this.settings.put("n13_4", n13_4);
	}

	@DBMeta(column = "n13_5", name = "n13_5", type = "String")
	public String getn13_5() {
		return n13_5;
	}

	public void setn13_5(String n13_5) {
		this.n13_5 = n13_5;
		this.settings.put("n13_5", n13_5);
	}

	@DBMeta(column = "n13_6", name = "n13_6", type = "String")
	public String getn13_6() {
		return n13_6;
	}

	public void setn13_6(String n13_6) {
		this.n13_6 = n13_6;
		this.settings.put("n13_6", n13_6);
	}

	@DBMeta(column = "n14_1", name = "n14_1", type = "String")
	public String getn14_1() {
		return n14_1;
	}

	public void setn14_1(String n14_1) {
		this.n14_1 = n14_1;
		this.settings.put("n14_1", n14_1);
	}

	@DBMeta(column = "n14_2", name = "n14_2", type = "String")
	public String getn14_2() {
		return n14_2;
	}

	public void setn14_2(String n14_2) {
		this.n14_2 = n14_2;
		this.settings.put("n14_2", n14_2);
	}

	@DBMeta(column = "n14_3", name = "n14_3", type = "int")
	public int getn14_3() {
		return n14_3;
	}

	public void setn14_3(int n14_3) {
		this.n14_3 = n14_3;
		this.settings.put("n14_3", n14_3);
	}

	@DBMeta(column = "n17_1", name = "n17_1", type = "String")
	public String getn17_1() {
		return n17_1;
	}

	public void setn17_1(String n17_1) {
		this.n17_1 = n17_1;
		this.settings.put("n17_1", n17_1);
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

	@DBMeta(column = "n21_1", name = "n21_1", type = "String")
	public String getn21_1() {
		return n21_1;
	}

	public void setn21_1(String n21_1) {
		this.n21_1 = n21_1;
		this.settings.put("n21_1", n21_1);
	}

	@DBMeta(column = "n22_1", name = "n22_1", type = "String")
	public String getn22_1() {
		return n22_1;
	}

	public void setn22_1(String n22_1) {
		this.n22_1 = n22_1;
		this.settings.put("n22_1", n22_1);
	}

	@DBMeta(column = "n24_1", name = "n24_1", type = "String")
	public String getn24_1() {
		return n24_1;
	}

	public void setn24_1(String n24_1) {
		this.n24_1 = n24_1;
		this.settings.put("n24_1", n24_1);
	}

	@DBMeta(column = "n24_2", name = "n24_2", type = "String")
	public String getn24_2() {
		return n24_2;
	}

	public void setn24_2(String n24_2) {
		this.n24_2 = n24_2;
		this.settings.put("n24_2", n24_2);
	}

	@DBMeta(column = "n24_3", name = "n24_3", type = "String")
	public String getn24_3() {
		return n24_3;
	}

	public void setn24_3(String n24_3) {
		this.n24_3 = n24_3;
		this.settings.put("n24_3", n24_3);
	}

	@DBMeta(column = "n25_1", name = "n25_1", type = "String")
	public String getn25_1() {
		return n25_1;
	}

	public void setn25_1(String n25_1) {
		this.n25_1 = n25_1;
		this.settings.put("n25_1", n25_1);
	}

	@DBMeta(column = "n27_1", name = "n27_1", type = "String")
	public String getn27_1() {
		return n27_1;
	}

	public void setn27_1(String n27_1) {
		this.n27_1 = n27_1;
		this.settings.put("n27_1", n27_1);
	}

	@DBMeta(column = "n31_1", name = "n31_1", type = "String")
	public String getn31_1() {
		return n31_1;
	}

	public void setn31_1(String n31_1) {
		this.n31_1 = n31_1;
		this.settings.put("n31_1", n31_1);
	}

	@DBMeta(column = "n31_2", name = "n31_2", type = "String")
	public String getn31_2() {
		return n31_2;
	}

	public void setn31_2(String n31_2) {
		this.n31_2 = n31_2;
		this.settings.put("n31_2", n31_2);
	}

	@DBMeta(column = "n32_1", name = "n32_1", type = "int")
	public int getn32_1() {
		return n32_1;
	}

	public void setn32_1(int n32_1) {
		this.n32_1 = n32_1;
		this.settings.put("n32_1", n32_1);
	}

	@DBMeta(column = "n33_1", name = "n33_1", type = "int")
	public int getn33_1() {
		return n33_1;
	}

	public void setn33_1(int n33_1) {
		this.n33_1 = n33_1;
		this.settings.put("n33_1", n33_1);
	}

	@DBMeta(column = "n33_2", name = "n33_2", type = "String")
	public String getn33_2() {
		return n33_2;
	}

	public void setn33_2(String n33_2) {
		this.n33_2 = n33_2;
		this.settings.put("n33_2", n33_2);
	}

	@DBMeta(column = "n34_1", name = "n34_1", type = "String")
	public String getn34_1() {
		return n34_1;
	}

	public void setn34_1(String n34_1) {
		this.n34_1 = n34_1;
		this.settings.put("n34_1", n34_1);
	}

	@DBMeta(column = "n34_2", name = "n34_2", type = "String")
	public String getn34_2() {
		return n34_2;
	}

	public void setn34_2(String n34_2) {
		this.n34_2 = n34_2;
		this.settings.put("n34_2", n34_2);
	}

	@DBMeta(column = "n34_3", name = "n34_3", type = "String")
	public String getn34_3() {
		return n34_3;
	}

	public void setn34_3(String n34_3) {
		this.n34_3 = n34_3;
		this.settings.put("n34_3", n34_3);
	}

	@DBMeta(column = "n34_4", name = "n34_4", type = "String")
	public String getn34_4() {
		return n34_4;
	}

	public void setn34_4(String n34_4) {
		this.n34_4 = n34_4;
		this.settings.put("n34_4", n34_4);
	}

	@DBMeta(column = "n34_5", name = "n34_5", type = "String")
	public String getn34_5() {
		return n34_5;
	}

	public void setn34_5(String n34_5) {
		this.n34_5 = n34_5;
		this.settings.put("n34_5", n34_5);
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

	@DBMeta(column = "n5_5", name = "n5_5", type = "String")
	public String getn5_5() {
		return n5_5;
	}

	public void setn5_5(String n5_5) {
		this.n5_5 = n5_5;
		this.settings.put("n5_5", n5_5);
	}

	@DBMeta(column = "n5_6", name = "n5_6", type = "int")
	public int getn5_6() {
		return n5_6;
	}

	public void setn5_6(int n5_6) {
		this.n5_6 = n5_6;
		this.settings.put("n5_6", n5_6);
	}

	@DBMeta(column = "n32_2", name = "n32_2", type = "int")
	public int getn32_2() {
		return n32_2;
	}

	public void setn32_2(int n32_2) {
		this.n32_2 = n32_2;
		this.settings.put("n32_2", n32_2);
	}

}
