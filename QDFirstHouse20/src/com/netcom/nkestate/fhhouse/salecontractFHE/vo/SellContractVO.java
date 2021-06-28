package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "SELLCONTRACT", sequence = "", id = DBModel.AssignedID)
public class SellContractVO extends FHVO {

	private long contractID;//合同ID
	private long earnestID;//定金合同编号
	private int regType;//定金方式
	private int resolveType;//解决方式
	private int bldnumType;//幢号区分       CODE508 0:幢、1:号
	private int landpactType;//土地使用权合同
	private String projectName;//项目名称
	private int houseType;//房屋类型	CODE539 0:非住宅、1:住宅
	private String cellarArea;//地下附属面积
	private String laneName;//号（对应大机弄）
	private String subLane;//栋（对应大机支弄）
	private String N1_1;//区县名
	private String N1_2;//地块名
	private String N1_3;//出让合同编号
	private String N1_4;//条款1楼盘名称
	private String N1_5;//房地产权证号
	private String N2_1;//路名
	private String N2_2;//弄号
	private String N2_3;//条款2楼盘名称
	private String N2_4;//单元
	private String N2_5;//层号
	private String N2_6;//室号
	private String N2_7;//测绘机构名
	private String N2_8;//房屋建筑面积
	private String N2_9;//套内建筑面积
	private String N2_10;//公用分摊建筑面积
	private String N2_11;//建筑层高
	private int N2_ROOM;//室数
	private int N2_HALL;//厅数
	private int N2_TOILET;//卫生间数
	private int N2_COOKROOM;//厨房数
	private String N3_1;//建筑面积单价
	private String N3_2;//建筑面积单价（大写）
	private String N3_3;//总房价款
	private String N3_4;//总房价款（大写）
	private String N4_1;//监管机构名
	private String N4_2;//监管机构帐户名
	private String N4_3;//监管机构账号
	private String N5_1;//条款5违约金比例
	private String N5_2;//逾期天数
	private int N5_3;//赔偿方案种类
	private int N7_1;//住房方案种类
	private String N7_2;//签署之日起XX天
	private String N7_3;//条款7公证处名
	private String N7_4;//公证之日起XX天
	private String N7_5;//收到房价款之日起XX天内
	private String N9_1;//房屋交付的标志
	private String N9_2;//房屋用途
	private String N9_3;//质量保证书名
	private String N9_4;//使用说明书名
	private String N9_5;//物业管理服务合同名
	private String N10_1;//设备差价的倍数
	private String N12_1;//赔偿金限制天数
	private String N12_2;//赔偿金额百分比
	private String N13_1;//条款13违约金比例
	private String N13_2;//逾期超过天数
	private int N13_3;//方案种类
	private String N14_1;//保修年数
	private String N16_1;//修复费的倍数
	private String N19_1;// 登记年
	private String N19_2;//登记月
	private String N19_3;//登记日
	private int N19_4;//房地产登记机构名
	private String N19_5;//获得产证年期限
	private String N19_6;//获得产证月期限
	private String N19_7;//获得产证日期限
	private String N19_8;//条款19违约金比例
	private String N19_9;//无法取得房地产权证起始年
	private String N19_10;//无法取得房地产权证起始月
	private String N19_11;//无法取得房地产权证起始日
	private String N19_12;//起始年月日起的天数
	private String N20_1;//甲方收到乙方书面通知之日起的天数
	private String N20_2;//赔偿金额比例
	private String N22_1;//解除本合同的书面通知之日起的天数
	private String N23_1;//使用类型
	private String N24_1;//甲方与XX签订的土地使用权
	private String N27_1;//仲裁委员会名
	private String N28_1;//条款28公证处名
	private String N29_1;//合同份数
	private String N29_2;//甲、乙双方各执份数
	private String N29_3;//合同持有人1
	private String N29_4;//合同持有人2
	private String N29_5;//合同持有人3
	private String fitmentPriceCn;//房屋全装修总价大写
	private String fitmentPrice;//房屋全装修总价小写
	private String chargeDep;//主管部门

	@DBMeta(column = "chargeDep", name = "主管部门", type = "String")
	public String getChargeDep() {
		return chargeDep;
	}

	public void setChargeDep(String chargeDep) {
		this.chargeDep = chargeDep;
		this.settings.put("chargeDep", chargeDep);

	}

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "EARNEST_ID", name = "定金合同编号", type = "long")
	public long getEarnestID() {
		return earnestID;
	}

	public void setEarnestID(long earnestID) {
		this.earnestID = earnestID;
		this.settings.put("earnestID", earnestID);
	}

	@DBMeta(column = "REGTYPE", name = "登记方式", type = "int")
	public int getRegType() {
		return regType;
	}

	public void setRegType(int regType) {
		this.regType = regType;
		this.settings.put("regType", regType);
	}

	@DBMeta(column = "RESOLVETYPE", name = "解决方式", type = "int")
	public int getResolveType() {
		return resolveType;
	}

	public void setResolveType(int resolveType) {
		this.resolveType = resolveType;
		this.settings.put("resolveType", resolveType);
	}

	@DBMeta(column = "BLDNUMTYPE", name = "幢号区分", type = "int", dict_table = "CT_508")
	public int getBldnumType() {
		return bldnumType;
	}

	public void setBldnumType(int bldnumType) {
		this.bldnumType = bldnumType;
		this.settings.put("bldnumType", bldnumType);
	}

	@DBMeta(column = "LANDPACTTYPE", name = "土地使用权合同", type = "int")
	public int getLandpactType() {
		return landpactType;
	}

	public void setLandpactType(int landpactType) {
		this.landpactType = landpactType;
		this.settings.put("landpactType", landpactType);
	}

	@DBMeta(column = "PROJECT_NAME", name = "项目名称", type = "String")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
		this.settings.put("projectName", projectName);
	}

	@DBMeta(column = "HOUSETYPE", name = "房屋类型", type = "int", dict_table = "CT_539")
	public int getHouseType() {
		return houseType;
	}

	public void setHouseType(int houseType) {
		this.houseType = houseType;
		this.settings.put("houseType", houseType);
	}

	@DBMeta(column = "CELLAR_AREA", name = "地下附属面积", type = "String")
	public String getCellarArea() {
		return cellarArea;
	}

	public void setCellarArea(String cellarArea) {
		this.cellarArea = cellarArea;
		this.settings.put("cellarArea", cellarArea);
	}

	@DBMeta(column = "LANE_NAME", name = "号（对应大机弄）", type = "String")
	public String getLaneName() {
		return laneName;
	}

	public void setLaneName(String laneName) {
		this.laneName = laneName;
		this.settings.put("laneName", laneName);
	}

	@DBMeta(column = "SUB_LANE", name = "栋（对应大机支弄）", type = "String")
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

	public void setN1_1(String n1_1) {
		N1_1 = n1_1;
		this.settings.put("N1_1", n1_1);
	}

	@DBMeta(column = "N1_2", name = "地块名", type = "String")
	public String getN1_2() {
		return N1_2;
	}

	public void setN1_2(String n1_2) {
		N1_2 = n1_2;
		this.settings.put("N1_2", n1_2);
	}

	@DBMeta(column = "N1_3", name = "出让合同编号", type = "String")
	public String getN1_3() {
		return N1_3;
	}

	public void setN1_3(String n1_3) {
		N1_3 = n1_3;
		this.settings.put("N1_3", n1_3);
	}

	@DBMeta(column = "N1_4", name = "条款1楼盘名称", type = "String")
	public String getN1_4() {
		return N1_4;
	}

	public void setN1_4(String n1_4) {
		N1_4 = n1_4;
		this.settings.put("N1_4", n1_4);
	}

	@DBMeta(column = "N1_5", name = "房地产权证号", type = "String")
	public String getN1_5() {
		return N1_5;
	}

	public void setN1_5(String n1_5) {
		N1_5 = n1_5;
		this.settings.put("N1_5", n1_5);
	}

	@DBMeta(column = "N2_1", name = "路名", type = "String")
	public String getN2_1() {
		return N2_1;
	}

	public void setN2_1(String n2_1) {
		N2_1 = n2_1;
		this.settings.put("N2_1", n2_1);
	}

	@DBMeta(column = "N2_2", name = "弄号", type = "String")
	public String getN2_2() {
		return N2_2;
	}

	public void setN2_2(String n2_2) {
		N2_2 = n2_2;
		this.settings.put("N2_2", n2_2);
	}

	@DBMeta(column = "N2_3", name = "条款2楼盘名称", type = "String")
	public String getN2_3() {
		return N2_3;
	}

	public void setN2_3(String n2_3) {
		N2_3 = n2_3;
		this.settings.put("N2_3", n2_3);
	}

	@DBMeta(column = "N2_4", name = "单元", type = "String")
	public String getN2_4() {
		return N2_4;
	}

	public void setN2_4(String n2_4) {
		N2_4 = n2_4;
		this.settings.put("N2_4", n2_4);
	}

	@DBMeta(column = "N2_5", name = "层号", type = "String")
	public String getN2_5() {
		return N2_5;
	}

	public void setN2_5(String n2_5) {
		N2_5 = n2_5;
		this.settings.put("N2_5", n2_5);
	}

	@DBMeta(column = "N2_6", name = "室号", type = "String")
	public String getN2_6() {
		return N2_6;
	}

	public void setN2_6(String n2_6) {
		N2_6 = n2_6;
		this.settings.put("N2_6", n2_6);
	}

	@DBMeta(column = "N2_7", name = "测绘机构名", type = "String")
	public String getN2_7() {
		return N2_7;
	}

	public void setN2_7(String n2_7) {
		N2_7 = n2_7;
		this.settings.put("N2_7", n2_7);
	}

	@DBMeta(column = "N2_8", name = "房屋建筑面积", type = "String")
	public String getN2_8() {
		return N2_8;
	}

	public void setN2_8(String n2_8) {
		N2_8 = n2_8;
		this.settings.put("N2_8", n2_8);
	}

	@DBMeta(column = "N2_9", name = "套内建筑面积", type = "String")
	public String getN2_9() {
		return N2_9;
	}

	public void setN2_9(String n2_9) {
		N2_9 = n2_9;
		this.settings.put("N2_9", n2_9);
	}

	@DBMeta(column = "N2_10", name = "公用分摊建筑面积", type = "String")
	public String getN2_10() {
		return N2_10;
	}

	public void setN2_10(String n2_10) {
		N2_10 = n2_10;
		this.settings.put("N2_10", n2_10);
	}

	@DBMeta(column = "N2_11", name = "建筑层高", type = "String")
	public String getN2_11() {
		return N2_11;
	}

	public void setN2_11(String n2_11) {
		N2_11 = n2_11;
		this.settings.put("N2_11", n2_11);
	}

	@DBMeta(column = "N2_ROOM", name = "室数", type = "int")
	public int getN2_ROOM() {
		return N2_ROOM;
	}

	public void setN2_ROOM(int n2ROOM) {
		N2_ROOM = n2ROOM;
		this.settings.put("N2_ROOM", n2ROOM);
	}

	@DBMeta(column = "N2_HALL", name = "厅数", type = "int")
	public int getN2_HALL() {
		return N2_HALL;
	}

	public void setN2_HALL(int n2HALL) {
		N2_HALL = n2HALL;
		this.settings.put("N2_HALL", n2HALL);
	}

	@DBMeta(column = "N2_TOILET", name = "卫生间数", type = "int")
	public int getN2_TOILET() {
		return N2_TOILET;
	}

	public void setN2_TOILET(int n2TOILET) {
		N2_TOILET = n2TOILET;
		this.settings.put("N2_TOILET", n2TOILET);
	}

	@DBMeta(column = "N2_COOKROOM", name = "厨房数", type = "int")
	public int getN2_COOKROOM() {
		return N2_COOKROOM;
	}

	public void setN2_COOKROOM(int n2COOKROOM) {
		N2_COOKROOM = n2COOKROOM;
		this.settings.put("N2_COOKROOM", n2COOKROOM);
	}

	@DBMeta(column = "N3_1", name = "建筑面积单价", type = "String")
	public String getN3_1() {
		return N3_1;
	}

	public void setN3_1(String n3_1) {
		N3_1 = n3_1;
		this.settings.put("N3_1", n3_1);
	}

	@DBMeta(column = "N3_2", name = "建筑面积单价(大写)", type = "String")
	public String getN3_2() {
		return N3_2;
	}

	public void setN3_2(String n3_2) {
		N3_2 = n3_2;
		this.settings.put("N3_2", n3_2);
	}

	@DBMeta(column = "N3_3", name = "总房价款", type = "String")
	public String getN3_3() {
		return N3_3;
	}

	public void setN3_3(String n3_3) {
		N3_3 = n3_3;
		this.settings.put("N3_3", n3_3);
	}

	@DBMeta(column = "N3_4", name = "总房价款(大写)", type = "String")
	public String getN3_4() {
		return N3_4;
	}

	public void setN3_4(String n3_4) {
		N3_4 = n3_4;
		this.settings.put("N3_4", n3_4);
	}

	@DBMeta(column = "N4_1", name = "监管机构名", type = "String")
	public String getN4_1() {
		return N4_1;
	}

	public void setN4_1(String n4_1) {
		N4_1 = n4_1;
		this.settings.put("N4_1", n4_1);
	}

	@DBMeta(column = "N4_2", name = "监管机构账户名称", type = "String")
	public String getN4_2() {
		return N4_2;
	}

	public void setN4_2(String n4_2) {
		N4_2 = n4_2;
		this.settings.put("N4_2", n4_2);
	}

	@DBMeta(column = "N4_3", name = "监管机构账号", type = "String")
	public String getN4_3() {
		return N4_3;
	}

	public void setN4_3(String n4_3) {
		N4_3 = n4_3;
		this.settings.put("N4_3", n4_3);
	}

	@DBMeta(column = "N5_1", name = "条款5违约金比例", type = "String")
	public String getN5_1() {
		return N5_1;
	}

	public void setN5_1(String n5_1) {
		N5_1 = n5_1;
		this.settings.put("N5_1", n5_1);
	}

	@DBMeta(column = "N5_2", name = "逾期天数", type = "String")
	public String getN5_2() {
		return N5_2;
	}

	public void setN5_2(String n5_2) {
		N5_2 = n5_2;
		this.settings.put("N5_2", n5_2);
	}

	@DBMeta(column = "N5_3", name = "赔偿方案种类", type = "int")
	public int getN5_3() {
		return N5_3;
	}

	public void setN5_3(int n5_3) {
		N5_3 = n5_3;
		this.settings.put("N5_3", n5_3);
	}

	@DBMeta(column = "N7_1", name = "支付方案种类", type = "int")
	public int getN7_1() {
		return N7_1;
	}

	public void setN7_1(int n7_1) {
		N7_1 = n7_1;
		this.settings.put("N7_1", n7_1);
	}

	@DBMeta(column = "N7_2", name = "签署之日起XX天", type = "String")
	public String getN7_2() {
		return N7_2;
	}

	public void setN7_2(String n7_2) {
		N7_2 = n7_2;
		this.settings.put("N7_2", n7_2);
	}

	@DBMeta(column = "N7_3", name = "条款7公证处名", type = "String")
	public String getN7_3() {
		return N7_3;
	}

	public void setN7_3(String n7_3) {
		N7_3 = n7_3;
		this.settings.put("N7_3", n7_3);
	}

	@DBMeta(column = "N7_4", name = "公证之日起XX天", type = "String")
	public String getN7_4() {
		return N7_4;
	}

	public void setN7_4(String n7_4) {
		N7_4 = n7_4;
		this.settings.put("N7_4", n7_4);
	}

	@DBMeta(column = "N7_5", name = "收到房价款之日", type = "String")
	public String getN7_5() {
		return N7_5;
	}

	public void setN7_5(String n7_5) {
		N7_5 = n7_5;
		this.settings.put("N7_5", n7_5);
	}

	@DBMeta(column = "N9_1", name = "房屋交付的标志", type = "String")
	public String getN9_1() {
		return N9_1;
	}

	public void setN9_1(String n9_1) {
		N9_1 = n9_1;
		this.settings.put("N9_1", n9_1);
	}

	@DBMeta(column = "N9_2", name = "房屋用途", type = "String")
	public String getN9_2() {
		return N9_2;
	}

	public void setN9_2(String n9_2) {
		N9_2 = n9_2;
		this.settings.put("N9_2", n9_2);
	}

	@DBMeta(column = "N9_3", name = "质量保证书名", type = "String")
	public String getN9_3() {
		return N9_3;
	}

	public void setN9_3(String n9_3) {
		N9_3 = n9_3;
		this.settings.put("N9_3", n9_3);
	}

	@DBMeta(column = "N9_4", name = "使用说明书名", type = "String")
	public String getN9_4() {
		return N9_4;
	}

	public void setN9_4(String n9_4) {
		N9_4 = n9_4;
		this.settings.put("N9_4", n9_4);
	}

	@DBMeta(column = "N9_5", name = "物业管理服务合同名", type = "String")
	public String getN9_5() {
		return N9_5;
	}

	public void setN9_5(String n9_5) {
		N9_5 = n9_5;
		this.settings.put("N9_5", n9_5);
	}

	@DBMeta(column = "N10_1", name = "设备差价的倍数", type = "String")
	public String getN10_1() {
		return N10_1;
	}

	public void setN10_1(String n10_1) {
		N10_1 = n10_1;
		this.settings.put("N10_1", n10_1);
	}

	@DBMeta(column = "N12_1", name = "赔偿金限制天数", type = "String")
	public String getN12_1() {
		return N12_1;
	}

	public void setN12_1(String n12_1) {
		N12_1 = n12_1;
		this.settings.put("N12_1", n12_1);
	}

	@DBMeta(column = "N12_2", name = "赔偿金额百分比", type = "String")
	public String getN12_2() {
		return N12_2;
	}

	public void setN12_2(String n12_2) {
		N12_2 = n12_2;
		this.settings.put("N12_2", n12_2);
	}

	@DBMeta(column = "N13_1", name = "条款13违约金比例", type = "String")
	public String getN13_1() {
		return N13_1;
	}

	public void setN13_1(String n13_1) {
		N13_1 = n13_1;
		this.settings.put("N13_1", n13_1);
	}

	@DBMeta(column = "N13_2", name = "逾期超过天数", type = "String")
	public String getN13_2() {
		return N13_2;
	}

	public void setN13_2(String n13_2) {
		N13_2 = n13_2;
		this.settings.put("N13_2", n13_2);
	}

	@DBMeta(column = "N13_3", name = "方案种类", type = "int")
	public int getN13_3() {
		return N13_3;
	}

	public void setN13_3(int n13_3) {
		N13_3 = n13_3;
		this.settings.put("N13_3", n13_3);
	}

	@DBMeta(column = "N14_1", name = "保修年数", type = "String")
	public String getN14_1() {
		return N14_1;
	}

	public void setN14_1(String n14_1) {
		N14_1 = n14_1;
		this.settings.put("N14_1", n14_1);
	}

	@DBMeta(column = "N16_1", name = "修复费的倍数", type = "String")
	public String getN16_1() {
		return N16_1;
	}

	public void setN16_1(String n16_1) {
		N16_1 = n16_1;
		this.settings.put("N16_1", n16_1);
	}

	@DBMeta(column = "N19_1", name = "登记年", type = "String")
	public String getN19_1() {
		return N19_1;
	}

	public void setN19_1(String n19_1) {
		N19_1 = n19_1;
		this.settings.put("N19_1", n19_1);
	}

	@DBMeta(column = "N19_2", name = "登记月", type = "String")
	public String getN19_2() {
		return N19_2;
	}

	public void setN19_2(String n19_2) {
		N19_2 = n19_2;
		this.settings.put("N19_2", n19_2);
	}

	@DBMeta(column = "N19_3", name = "登记日", type = "String")
	public String getN19_3() {
		return N19_3;
	}

	public void setN19_3(String n19_3) {
		N19_3 = n19_3;
		this.settings.put("N19_3", n19_3);
	}

	@DBMeta(column = "N19_4", name = "房地产登记机构名", type = "int")
	public int getN19_4() {
		return N19_4;
	}

	public void setN19_4(int n19_4) {
		N19_4 = n19_4;
		this.settings.put("N19_4", n19_4);
	}

	@DBMeta(column = "N19_5", name = "获得产证年期限", type = "String")
	public String getN19_5() {
		return N19_5;
	}

	public void setN19_5(String n19_5) {
		N19_5 = n19_5;
		this.settings.put("N19_5", n19_5);
	}

	@DBMeta(column = "N19_6", name = "获得产证月期限", type = "String")
	public String getN19_6() {
		return N19_6;
	}

	public void setN19_6(String n19_6) {
		N19_6 = n19_6;
		this.settings.put("N19_6", n19_6);
	}

	@DBMeta(column = "N19_7", name = "获得产证日期限", type = "String")
	public String getN19_7() {
		return N19_7;
	}

	public void setN19_7(String n19_7) {
		N19_7 = n19_7;
		this.settings.put("N19_7", n19_7);
	}

	@DBMeta(column = "N19_8", name = "条款19违约金比例", type = "String")
	public String getN19_8() {
		return N19_8;
	}

	public void setN19_8(String n19_8) {
		N19_8 = n19_8;
		this.settings.put("N19_8", n19_8);
	}

	@DBMeta(column = "N19_9", name = "无法取得房地产权证起始年", type = "String")
	public String getN19_9() {
		return N19_9;
	}

	public void setN19_9(String n19_9) {
		N19_9 = n19_9;
		this.settings.put("N19_9", n19_9);
	}

	@DBMeta(column = "N19_10", name = "无法取得房地产权证起始月", type = "String")
	public String getN19_10() {
		return N19_10;
	}

	public void setN19_10(String n19_10) {
		N19_10 = n19_10;
		this.settings.put("N19_10", n19_10);
	}

	@DBMeta(column = "N19_11", name = "无法取得房地产权证起始日", type = "String")
	public String getN19_11() {
		return N19_11;
	}

	public void setN19_11(String n19_11) {
		N19_11 = n19_11;
		this.settings.put("N19_11", n19_11);
	}

	@DBMeta(column = "N19_12", name = "起始年月日起的天数", type = "String")
	public String getN19_12() {
		return N19_12;
	}

	public void setN19_12(String n19_12) {
		N19_12 = n19_12;
		this.settings.put("N19_12", n19_12);
	}

	@DBMeta(column = "N20_1", name = "甲方收到乙方书面通知之日起的天数", type = "String")
	public String getN20_1() {
		return N20_1;
	}

	public void setN20_1(String n20_1) {
		N20_1 = n20_1;
		this.settings.put("N20_1", N20_1);
	}

	@DBMeta(column = "N20_2", name = "赔偿金额比例", type = "String")
	public String getN20_2() {
		return N20_2;
	}

	public void setN20_2(String n20_2) {
		N20_2 = n20_2;
		this.settings.put("N20_2", n20_2);
	}

	@DBMeta(column = "N22_1", name = "解除本合同的书面通知之日起的天数", type = "String")
	public String getN22_1() {
		return N22_1;
	}

	public void setN22_1(String n22_1) {
		N22_1 = n22_1;
		this.settings.put("N22_1", n22_1);
	}

	@DBMeta(column = "N23_1", name = "使用类型", type = "String")
	public String getN23_1() {
		return N23_1;
	}

	public void setN23_1(String n23_1) {
		N23_1 = n23_1;
		this.settings.put("N23_1", n23_1);
	}

	@DBMeta(column = "N24_1", name = "甲方与XX签订的土地使用权", type = "String")
	public String getN24_1() {
		return N24_1;
	}

	public void setN24_1(String n24_1) {
		N24_1 = n24_1;
		this.settings.put("N24_1", n24_1);
	}

	@DBMeta(column = "N27_1", name = "仲裁委员会名", type = "String")
	public String getN27_1() {
		return N27_1;
	}

	public void setN27_1(String n27_1) {
		N27_1 = n27_1;
		this.settings.put("N27_1", n27_1);
	}

	@DBMeta(column = "N28_1", name = "条款28公证处名", type = "String")
	public String getN28_1() {
		return N28_1;
	}

	public void setN28_1(String n28_1) {
		N28_1 = n28_1;
		this.settings.put("N28_1", n28_1);
	}

	@DBMeta(column = "N29_1", name = "合同份数", type = "String")
	public String getN29_1() {
		return N29_1;
	}

	public void setN29_1(String n29_1) {
		N29_1 = n29_1;
		this.settings.put("N29_1", n29_1);
	}

	@DBMeta(column = "N29_2", name = "甲、乙双方各执份数", type = "String")
	public String getN29_2() {
		return N29_2;
	}

	public void setN29_2(String n29_2) {
		N29_2 = n29_2;
		this.settings.put("N29_2", n29_2);
	}

	@DBMeta(column = "N29_3", name = "合同持有人1", type = "String")
	public String getN29_3() {
		return N29_3;
	}

	public void setN29_3(String n29_3) {
		N29_3 = n29_3;
		this.settings.put("N29_3", n29_3);
	}

	@DBMeta(column = "N29_4", name = "合同持有人2", type = "String")
	public String getN29_4() {
		return N29_4;
	}

	public void setN29_4(String n29_4) {
		N29_4 = n29_4;
		this.settings.put("N29_4", n29_4);
	}

	@DBMeta(column = "N29_5", name = "合同持有人3", type = "String")
	public String getN29_5() {
		return N29_5;
	}

	public void setN29_5(String n29_5) {
		N29_5 = n29_5;
		this.settings.put("N29_5", n29_5);
	}

	@DBMeta(column = "FITMENT_PRICE_CN", name = "房屋全装修总价大写", type = "String")
	public String getFitmentPriceCn() {
		return fitmentPriceCn;
	}

	public void setFitmentPriceCn(String fitmentPriceCn) {
		this.fitmentPriceCn = fitmentPriceCn;
		this.settings.put("fitmentPriceCn", fitmentPriceCn);
	}

	@DBMeta(column = "FITMENT_PRICE", name = "房屋全装修总价小写", type = "String")
	public String getFitmentPrice() {
		return fitmentPrice;
	}

	public void setFitmentPrice(String fitmentPrice) {
		this.fitmentPrice = fitmentPrice;
		this.settings.put("fitmentPrice", fitmentPrice);
	}

}
