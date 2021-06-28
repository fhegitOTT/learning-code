/**
 * <p>LandVO.java </p>
 *
 * <p>系统名称: 青岛市不动产登记系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017年3月6日<p>
 * 
 */
package com.netcom.nkestate.fhhouse.interfaces.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "COMMON.CH_LOT", sequence = "", id = DBModel.AssignedID)
public class CHLotVO extends AbstractBaseVO {
	protected long lotID; // 地编号（序列SEQ_CH_LOT）
	protected String lot_Number2; // 暂无用
	protected String districtID; // 区划代码
	protected String realtyUnitCode; // 不动产单元编号
	protected String block_Code; // 街坊代码
	protected String lot_Name; // 地块名称
	protected String map_Num; // 所在图幅号
	protected String land_Rights; // 权属性质(DIC_LAND_RIGHTS)
	protected String land_Descent; // 使用权取得方式(CT_LAND_DESCENT)
	protected String land_Use; // 土地用途(DIC_LAND_USE)
	protected String land_Use_Desc; // 土地用途描述
	protected double area_Survey; // 测量面积(默认=AREA)
	protected String note; // 备注
	protected Date created_Date; // 创建日期
	protected String state; // 处理状态(1-正常;2-锁定)
	protected Date state_Date; // 处理状态日期
	protected long version_ID; // 版本号（序列号SEQ_VERSION，该序列号楼盘表共用）
	protected String isValid; // 有效标识(0-历史;1-现势)
	protected double area; // 图形面积(根据ArcGIS图形计算)理论上等于PRIV_AREA和AREA_SHARED之和
	protected String lot_Location; // 土地座落(等同于LOT_NAME)，测绘软件中可维护的字段为LOT_NAME。LOT_LOCATION用处不大。
	protected String land_Grade; // 土地等级(DIC_LAND_GRADE)
	protected String east_Border; // 东至
	protected String north_Border; // 北至
	protected String west_Border; // 西至
	protected String sorth_Border; // 南至
	protected String land_Source; // 暂无用(土地来源)
	protected double construct_Volume_Rate; // 建筑容积率（地籍）
	protected double construct_Density; // 建筑密度（地籍）
	protected double construct_Height_Limit; // 建筑限高（地籍）
	protected Date edit_Date; // 修改日期
	protected String approval_Number; // 用地批文号（地籍）
	protected String survey_ID; // 供地编号（地籍）
	protected String street_Code; // 街道代码
	protected String lot_Number; // 地籍号
	protected double priv_Area; // 土地独用面积（地籍）
	protected double area_Shared; // 土地共用面积（地籍）
	protected Date use_StartDay; // 土地使用起始日期（地籍）
	protected Date use_EndDay; // 土地使用终止日期（地籍）
	protected String element_Code; // 要素代码（地籍）
	protected double cellar_Start_Depth; // 地下起始深度（地籍）
	protected double cellar_End_Depth; // 地下终止深度（地籍）
	protected String data_Source; // 数据来源（地籍）
	protected String location_Num; // 座落单位代码（地籍）
	protected String use_Period; // 使用期限
	protected double construct_Area; // 建筑物占地面积（地籍）
	protected Date end_Date; // 结束日期
	protected int objectID; // 图形ID
	protected String border; // 四至
	protected String rightsOwner_Syq; // 权利人所有权
	protected String rightsOwner_Usuage; // 权利人使用权
	protected String rightsOwner_Kind; // 权利人类型（字典表）
	protected String rightsOwner_Card; // 权利人证件种类（字典表）
	protected String rightsOwner_Cardcode; // 权利人证件号
	protected String rightsOwner_Address; // 权利人通讯地址
	protected String rightsNature; // 权利性质（字典表）
	protected String landSourceDemo; // 土地权属来源证明材料
	protected String director; // 法定代表人或负责人姓名
	protected String director_Card; // 法定代表人或负责人证件类型（字典表）
	protected String director_CardCode; // 法定代表人或负责人证件编号
	protected String director_Phone; // 法定代表人或负责人电话
	protected String proxy; // 代理人姓名
	protected String proxy_Card; // 代理人证件类型（字典表）
	protected String proxy_CardCode; // 代理人证件编号
	protected String proxy_Phone; // 代理人电话
	protected String rights_Set; // 权利设定方式（字典表）
	protected String economic_Classify; // 国民经济行业分类代码（字典表）
	protected String pre_Lot_Number; // 预编宗地代码
	protected String scale; // 比例尺（字典表）
	protected String approved_Use; // 批准用途（字典表）
	protected String approved_Use_Code; // 批准用途地类编码（字典表）
	protected String actual_Use; // 实际用途（字典表）
	protected String actual_Use_Code; // 实际用途地类编码（字典表）
	protected double approved_Area; // 批准面积
	protected double building_Area; // 建筑面积
	protected String common_RightsOwners; // 共有/共用权利人情况
	protected long plan_ID; // 宗地草图ID
	protected String bound_Point_Pos_Desc; // 界址点位说明
	protected String bound_Line_Direction_Desc; // 主要权属界线走向说明
	protected String rightsType; // 权利类型	
	protected double area_Remain; // 剩余面积（=土地面积-幢占地面积）
	protected String yzdtybm; // 原宗地统一编码
	protected String mjdw; // 面积单位
	protected double nydmj; // 农用地面积
	protected double gdmj; // 耕地面积
	protected double cdmj; // 草地面积
	protected double qtmj; // 其他面积
	protected double jsydmj; // 建设用地面积
	protected double wlydmj; // 未利用用地面积
	protected double ldmj; // 林地面积

	@DBMeta(column = "lotID", name = "地编号", type = "long")
	public long getLotID() {
		return lotID;
	}

	public void setLotID(long lotID) {
		this.lotID = lotID;
		this.settings.put("lotID", lotID);
	}

	@DBMeta(column = "lot_Number2", name = "暂无用", type = "String")
	public String getLot_Number2() {
		return lot_Number2;
	}

	public void setLot_Number2(String lot_Number2) {
		this.lot_Number2 = lot_Number2;
		this.settings.put("lot_Number2", lot_Number2);
	}

	@DBMeta(column = "districtID", name = "区划代码", type = "String")
	public String getDistrictID() {
		return districtID;
	}

	public void setDistrictID(String districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}

	@DBMeta(column = "block_Code", name = "街坊代码", type = "String")
	public String getBlock_Code() {
		return block_Code;
	}

	public void setBlock_Code(String block_Code) {
		this.block_Code = block_Code;
		this.settings.put("block_Code", block_Code);
	}

	@DBMeta(column = "lot_Name", name = "地块名称", type = "String")
	public String getLot_Name() {
		return lot_Name;
	}

	public void setLot_Name(String lot_Name) {
		this.lot_Name = lot_Name;
		this.settings.put("lot_Name", lot_Name);
	}

	@DBMeta(column = "map_Num", name = "所在图幅号", type = "String")
	public String getMap_Num() {
		return map_Num;
	}

	public void setMap_Num(String map_Num) {
		this.map_Num = map_Num;
		this.settings.put("map_Num", map_Num);
	}

	@DBMeta(column = "land_Rights", name = "权属性质", type = "String")
	public String getLand_Rights() {
		return land_Rights;
	}

	public void setLand_Rights(String land_Rights) {
		this.land_Rights = land_Rights;
		this.settings.put("land_Rights", land_Rights);
	}

	@DBMeta(column = "land_Descent", name = "使用权取得方式", type = "String")
	public String getLand_Descent() {
		return land_Descent;
	}

	public void setLand_Descent(String land_Descent) {
		this.land_Descent = land_Descent;
		this.settings.put("land_Descent", land_Descent);
	}

	@DBMeta(column = "land_Use", name = "土地用途", type = "String")
	public String getLand_Use() {
		return land_Use;
	}

	public void setLand_Use(String land_Use) {
		this.land_Use = land_Use;
		this.settings.put("land_Use", land_Use);
	}

	@DBMeta(column = "land_Use_Desc", name = "土地用途描述", type = "String")
	public String getLand_Use_Desc() {
		return land_Use_Desc;
	}

	public void setLand_Use_Desc(String land_Use_Desc) {
		this.land_Use_Desc = land_Use_Desc;
		this.settings.put("land_Use_Desc", land_Use_Desc);
	}

	@DBMeta(column = "area_Survey", name = "测量面积", type = "double")
	public double getArea_Survey() {
		return area_Survey;
	}

	public void setArea_Survey(double area_Survey) {
		this.area_Survey = area_Survey;
		this.settings.put("area_Survey", area_Survey);
	}

	@DBMeta(column = "note", name = "备注", type = "String")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
		this.settings.put("note", note);
	}

	@DBMeta(column = "created_Date", name = "创建日期", type = "Date")
	public Date getCreated_Date() {
		return created_Date;
	}

	public void setCreated_Date(Date created_Date) {
		this.created_Date = created_Date;
		this.settings.put("created_Date", created_Date);
	}

	@DBMeta(column = "state", name = "处理状态", type = "String")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		this.settings.put("state", state);
	}

	@DBMeta(column = "state_Date", name = "处理状态日期", type = "Date")
	public Date getState_Date() {
		return state_Date;
	}

	public void setState_Date(Date state_Date) {
		this.state_Date = state_Date;
		this.settings.put("state_Date", state_Date);
	}

	@DBMeta(column = "version_ID", name = "版本号", type = "long")
	public long getVersion_ID() {
		return version_ID;
	}

	public void setVersion_ID(long version_ID) {
		this.version_ID = version_ID;
		this.settings.put("version_ID", version_ID);
	}

	@DBMeta(column = "isValid", name = "有效标识", type = "String")
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
		this.settings.put("isValid", isValid);
	}

	@DBMeta(column = "area", name = "图形面积", type = "double")
	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
		this.settings.put("area", area);
	}

	@DBMeta(column = "lot_Location", name = "土地座落", type = "String")
	public String getLot_Location() {
		return lot_Location;
	}

	public void setLot_Location(String lot_Location) {
		this.lot_Location = lot_Location;
		this.settings.put("lot_Location", lot_Location);
	}

	@DBMeta(column = "land_Grade", name = "土地等级", type = "String")
	public String getLand_Grade() {
		return land_Grade;
	}

	public void setLand_Grade(String land_Grade) {
		this.land_Grade = land_Grade;
		this.settings.put("land_Grade", land_Grade);
	}

	@DBMeta(column = "east_Border", name = "东至", type = "String")
	public String getEast_Border() {
		return east_Border;
	}

	public void setEast_Border(String east_Border) {
		this.east_Border = east_Border;
		this.settings.put("east_Border", east_Border);
	}

	@DBMeta(column = "north_Border", name = "北至", type = "String")
	public String getNorth_Border() {
		return north_Border;
	}

	public void setNorth_Border(String north_Border) {
		this.north_Border = north_Border;
		this.settings.put("north_Border", north_Border);
	}

	@DBMeta(column = "west_Border", name = "西至", type = "String")
	public String getWest_Border() {
		return west_Border;
	}

	public void setWest_Border(String west_Border) {
		this.west_Border = west_Border;
		this.settings.put("west_Border", west_Border);
	}

	@DBMeta(column = "sorth_Border", name = "南至", type = "String")
	public String getSorth_Border() {
		return sorth_Border;
	}

	public void setSorth_Border(String sorth_Border) {
		this.sorth_Border = sorth_Border;
		this.settings.put("sorth_Border", sorth_Border);
	}

	@DBMeta(column = "land_Source", name = "暂无用", type = "String")
	public String getLand_Source() {
		return land_Source;
	}

	public void setLand_Source(String land_Source) {
		this.land_Source = land_Source;
		this.settings.put("land_Source", land_Source);
	}

	@DBMeta(column = "construct_Volume_Rate", name = "建筑容积率", type = "double")
	public double getConstruct_Volume_Rate() {
		return construct_Volume_Rate;
	}

	public void setConstruct_Volume_Rate(double construct_Volume_Rate) {
		this.construct_Volume_Rate = construct_Volume_Rate;
		this.settings.put("construct_Volume_Rate", construct_Volume_Rate);
	}

	@DBMeta(column = "construct_Density", name = "建筑密度", type = "double")
	public double getConstruct_Density() {
		return construct_Density;
	}

	public void setConstruct_Density(double construct_Density) {
		this.construct_Density = construct_Density;
		this.settings.put("construct_Density", construct_Density);
	}

	@DBMeta(column = "construct_Height_Limit", name = "建筑限高", type = "double")
	public double getConstruct_Height_Limit() {
		return construct_Height_Limit;
	}

	public void setConstruct_Height_Limit(double construct_Height_Limit) {
		this.construct_Height_Limit = construct_Height_Limit;
		this.settings.put("construct_Height_Limit", construct_Height_Limit);
	}

	@DBMeta(column = "edit_Date", name = "修改日期", type = "Date")
	public Date getEdit_Date() {
		return edit_Date;
	}

	public void setEdit_Date(Date edit_Date) {
		this.edit_Date = edit_Date;
		this.settings.put("edit_Date", edit_Date);
	}

	@DBMeta(column = "approval_Number", name = "用地批文号", type = "String")
	public String getApproval_Number() {
		return approval_Number;
	}

	public void setApproval_Number(String approval_Number) {
		this.approval_Number = approval_Number;
		this.settings.put("approval_Number", approval_Number);
	}

	@DBMeta(column = "survey_ID", name = "供地编号", type = "String")
	public String getSurvey_ID() {
		return survey_ID;
	}

	public void setSurvey_ID(String survey_ID) {
		this.survey_ID = survey_ID;
		this.settings.put("survey_ID", survey_ID);
	}

	@DBMeta(column = "street_Code", name = "街道代码", type = "String")
	public String getStreet_Code() {
		return street_Code;
	}

	public void setStreet_Code(String street_Code) {
		this.street_Code = street_Code;
		this.settings.put("street_Code", street_Code);
	}

	@DBMeta(column = "lot_Number", name = "地籍号", type = "String")
	public String getLot_Number() {
		return lot_Number;
	}

	public void setLot_Number(String lot_Number) {
		this.lot_Number = lot_Number;
		this.settings.put("lot_Number", lot_Number);
	}

	@DBMeta(column = "priv_Area", name = "土地独用面积", type = "double")
	public double getPriv_Area() {
		return priv_Area;
	}

	public void setPriv_Area(double priv_Area) {
		this.priv_Area = priv_Area;
		this.settings.put("priv_Area", priv_Area);
	}

	@DBMeta(column = "area_Shared", name = "土地共用面积", type = "double")
	public double getArea_Shared() {
		return area_Shared;
	}

	public void setArea_Shared(double area_Shared) {
		this.area_Shared = area_Shared;
		this.settings.put("area_Shared", area_Shared);
	}

	@DBMeta(column = "use_StartDay", name = "土地使用起始日期", type = "Date")
	public Date getUse_StartDay() {
		return use_StartDay;
	}

	public void setUse_StartDay(Date use_StartDay) {
		this.use_StartDay = use_StartDay;
		this.settings.put("use_StartDay", use_StartDay);
	}

	@DBMeta(column = "use_EndDay", name = "土地使用终止日期", type = "Date")
	public Date getUse_EndDay() {
		return use_EndDay;
	}

	public void setUse_EndDay(Date use_EndDay) {
		this.use_EndDay = use_EndDay;
		this.settings.put("use_EndDay", use_EndDay);
	}

	@DBMeta(column = "element_Code", name = "要素代码", type = "String")
	public String getElement_Code() {
		return element_Code;
	}

	public void setElement_Code(String element_Code) {
		this.element_Code = element_Code;
		this.settings.put("element_Code", element_Code);
	}

	@DBMeta(column = "cellar_Start_Depth", name = "地下起始深度", type = "double")
	public double getCellar_Start_Depth() {
		return cellar_Start_Depth;
	}

	public void setCellar_Start_Depth(double cellar_Start_Depth) {
		this.cellar_Start_Depth = cellar_Start_Depth;
		this.settings.put("cellar_Start_Depth", cellar_Start_Depth);
	}

	@DBMeta(column = "cellar_End_Depth", name = "地下终止深度", type = "double")
	public double getCellar_End_Depth() {
		return cellar_End_Depth;
	}

	public void setCellar_End_Depth(double cellar_End_Depth) {
		this.cellar_End_Depth = cellar_End_Depth;
		this.settings.put("cellar_End_Depth", cellar_End_Depth);
	}

	@DBMeta(column = "data_Source", name = "数据来源", type = "String")
	public String getData_Source() {
		return data_Source;
	}

	public void setData_Source(String data_Source) {
		this.data_Source = data_Source;
		this.settings.put("data_Source", data_Source);
	}

	@DBMeta(column = "location_Num", name = "座落单位代码", type = "String")
	public String getLocation_Num() {
		return location_Num;
	}

	public void setLocation_Num(String location_Num) {
		this.location_Num = location_Num;
		this.settings.put("location_Num", location_Num);
	}

	@DBMeta(column = "use_Period", name = "使用期限", type = "String")
	public String getUse_Period() {
		return use_Period;
	}

	public void setUse_Period(String use_Period) {
		this.use_Period = use_Period;
		this.settings.put("use_Period", use_Period);
	}

	@DBMeta(column = "construct_Area", name = "建筑物占地面积", type = "double")
	public double getConstruct_Area() {
		return construct_Area;
	}

	public void setConstruct_Area(double construct_Area) {
		this.construct_Area = construct_Area;
		this.settings.put("construct_Area", construct_Area);
	}

	@DBMeta(column = "end_Date", name = "结束日期", type = "Date")
	public Date getEnd_Date() {
		return end_Date;
	}

	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
		this.settings.put("end_Date", end_Date);
	}

	@DBMeta(column = "objectID", name = "图形ID", type = "int")
	public int getObjectID() {
		return objectID;
	}

	public void setObjectID(int objectID) {
		this.objectID = objectID;
		this.settings.put("objectID", objectID);
	}

	@DBMeta(column = "border", name = "四至", type = "String")
	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
		this.settings.put("border", border);
	}

	@DBMeta(column = "rightsOwner_Syq", name = "权利人所有权", type = "String")
	public String getRightsOwner_Syq() {
		return rightsOwner_Syq;
	}

	public void setRightsOwner_Syq(String rightsOwner_Syq) {
		this.rightsOwner_Syq = rightsOwner_Syq;
		this.settings.put("rightsOwner_Syq", rightsOwner_Syq);
	}

	@DBMeta(column = "rightsOwner_Usuage", name = "权利人使用权", type = "String")
	public String getRightsOwner_Usuage() {
		return rightsOwner_Usuage;
	}

	public void setRightsOwner_Usuage(String rightsOwner_Usuage) {
		this.rightsOwner_Usuage = rightsOwner_Usuage;
		this.settings.put("rightsOwner_Usuage", rightsOwner_Usuage);
	}

	@DBMeta(column = "rightsOwner_Kind", name = "权利人类型", type = "String")
	public String getRightsOwner_Kind() {
		return rightsOwner_Kind;
	}

	public void setRightsOwner_Kind(String rightsOwner_Kind) {
		this.rightsOwner_Kind = rightsOwner_Kind;
		this.settings.put("rightsOwner_Kind", rightsOwner_Kind);
	}

	@DBMeta(column = "rightsOwner_Card", name = "权利人证件种类", type = "String")
	public String getRightsOwner_Card() {
		return rightsOwner_Card;
	}

	public void setRightsOwner_Card(String rightsOwner_Card) {
		this.rightsOwner_Card = rightsOwner_Card;
		this.settings.put("rightsOwner_Card", rightsOwner_Card);
	}

	@DBMeta(column = "rightsOwner_Cardcode", name = "权利人证件号", type = "String")
	public String getRightsOwner_Cardcode() {
		return rightsOwner_Cardcode;
	}

	public void setRightsOwner_Cardcode(String rightsOwner_Cardcode) {
		this.rightsOwner_Cardcode = rightsOwner_Cardcode;
		this.settings.put("rightsOwner_Cardcode", rightsOwner_Cardcode);
	}

	@DBMeta(column = "rightsOwner_Address", name = "权利人通讯地址", type = "String")
	public String getRightsOwner_Address() {
		return rightsOwner_Address;
	}

	public void setRightsOwner_Address(String rightsOwner_Address) {
		this.rightsOwner_Address = rightsOwner_Address;
		this.settings.put("rightsOwner_Address", rightsOwner_Address);
	}

	@DBMeta(column = "rightsNature", name = "权利性质", type = "String")
	public String getRightsNature() {
		return rightsNature;
	}

	public void setRightsNature(String rightsNature) {
		this.rightsNature = rightsNature;
		this.settings.put("rightsNature", rightsNature);
	}

	@DBMeta(column = "landSourceDemo", name = "土地权属来源证明材料", type = "String")
	public String getLandSourceDemo() {
		return landSourceDemo;
	}

	public void setLandSourceDemo(String landSourceDemo) {
		this.landSourceDemo = landSourceDemo;
		this.settings.put("landSourceDemo", landSourceDemo);
	}

	@DBMeta(column = "director", name = "法定代表人或负责人姓名", type = "String")
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
		this.settings.put("director", director);
	}

	@DBMeta(column = "director_Card", name = "法定代表人或负责人证件类型", type = "String")
	public String getDirector_Card() {
		return director_Card;
	}

	public void setDirector_Card(String director_Card) {
		this.director_Card = director_Card;
		this.settings.put("director_Card", director_Card);
	}

	@DBMeta(column = "director_CardCode", name = "法定代表人或负责人证件编号", type = "String")
	public String getDirector_CardCode() {
		return director_CardCode;
	}

	public void setDirector_CardCode(String director_CardCode) {
		this.director_CardCode = director_CardCode;
		this.settings.put("director_CardCode", director_CardCode);
	}

	@DBMeta(column = "director_Phone", name = "法定代表人或负责人电话", type = "String")
	public String getDirector_Phone() {
		return director_Phone;
	}

	public void setDirector_Phone(String director_Phone) {
		this.director_Phone = director_Phone;
		this.settings.put("director_Phone", director_Phone);
	}

	@DBMeta(column = "proxy", name = "代理人姓名", type = "String")
	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
		this.settings.put("proxy", proxy);
	}

	@DBMeta(column = "proxy_Card", name = "代理人证件类型", type = "String")
	public String getProxy_Card() {
		return proxy_Card;
	}

	public void setProxy_Card(String proxy_Card) {
		this.proxy_Card = proxy_Card;
		this.settings.put("proxy_Card", proxy_Card);
	}

	@DBMeta(column = "proxy_CardCode", name = "代理人证件编号", type = "String")
	public String getProxy_CardCode() {
		return proxy_CardCode;
	}

	public void setProxy_CardCode(String proxy_CardCode) {
		this.proxy_CardCode = proxy_CardCode;
		this.settings.put("proxy_CardCode", proxy_CardCode);
	}

	@DBMeta(column = "proxy_Phone", name = "代理人电话", type = "String")
	public String getProxy_Phone() {
		return proxy_Phone;
	}

	public void setProxy_Phone(String proxy_Phone) {
		this.proxy_Phone = proxy_Phone;
		this.settings.put("proxy_Phone", proxy_Phone);
	}

	@DBMeta(column = "rights_Set", name = "权利设定方式", type = "String")
	public String getRights_Set() {
		return rights_Set;
	}

	public void setRights_Set(String rights_Set) {
		this.rights_Set = rights_Set;
		this.settings.put("rights_Set", rights_Set);
	}

	@DBMeta(column = "economic_Classify", name = "国民经济行业分类代码", type = "String")
	public String getEconomic_Classify() {
		return economic_Classify;
	}

	public void setEconomic_Classify(String economic_Classify) {
		this.economic_Classify = economic_Classify;
		this.settings.put("economic_Classify", economic_Classify);
	}

	@DBMeta(column = "pre_Lot_Number", name = "预编宗地代码", type = "String")
	public String getPre_Lot_Number() {
		return pre_Lot_Number;
	}

	public void setPre_Lot_Number(String pre_Lot_Number) {
		this.pre_Lot_Number = pre_Lot_Number;
		this.settings.put("pre_Lot_Number", pre_Lot_Number);
	}

	@DBMeta(column = "scale", name = "比例尺", type = "String")
	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
		this.settings.put("scale", scale);
	}

	@DBMeta(column = "approved_Use", name = "批准用途", type = "String")
	public String getApproved_Use() {
		return approved_Use;
	}

	public void setApproved_Use(String approved_Use) {
		this.approved_Use = approved_Use;
		this.settings.put("approved_Use", approved_Use);
	}

	@DBMeta(column = "approved_Use_Code", name = "批准用途地类编码", type = "String")
	public String getApproved_Use_Code() {
		return approved_Use_Code;
	}

	public void setApproved_Use_Code(String approved_Use_Code) {
		this.approved_Use_Code = approved_Use_Code;
		this.settings.put("approved_Use_Code", approved_Use_Code);
	}

	@DBMeta(column = "actual_Use", name = "实际用途", type = "String")
	public String getActual_Use() {
		return actual_Use;
	}

	public void setActual_Use(String actual_Use) {
		this.actual_Use = actual_Use;
		this.settings.put("actual_Use", actual_Use);
	}

	@DBMeta(column = "actual_Use_Code", name = "实际用途地类编码", type = "String")
	public String getActual_Use_Code() {
		return actual_Use_Code;
	}

	public void setActual_Use_Code(String actual_Use_Code) {
		this.actual_Use_Code = actual_Use_Code;
		this.settings.put("actual_Use_Code", actual_Use_Code);
	}

	@DBMeta(column = "approved_Area", name = "批准面积", type = "double")
	public double getApproved_Area() {
		return approved_Area;
	}

	public void setApproved_Area(double approved_Area) {
		this.approved_Area = approved_Area;
		this.settings.put("approved_Area", approved_Area);
	}

	@DBMeta(column = "building_Area", name = "建筑面积", type = "double")
	public double getBuilding_Area() {
		return building_Area;
	}

	public void setBuilding_Area(double building_Area) {
		this.building_Area = building_Area;
		this.settings.put("building_Area", building_Area);
	}

	@DBMeta(column = "common_RightsOwners", name = "共有/共用权利人情况", type = "String")
	public String getCommon_RightsOwners() {
		return common_RightsOwners;
	}

	public void setCommon_RightsOwners(String common_RightsOwners) {
		this.common_RightsOwners = common_RightsOwners;
		this.settings.put("common_RightsOwners", common_RightsOwners);
	}

	@DBMeta(column = "plan_ID", name = "宗地草图ID", type = "long")
	public long getPlan_ID() {
		return plan_ID;
	}

	public void setPlan_ID(long plan_ID) {
		this.plan_ID = plan_ID;
		this.settings.put("plan_ID", plan_ID);
	}

	@DBMeta(column = "bound_Point_Pos_Desc", name = "界址点位说明", type = "String")
	public String getBound_Point_Pos_Desc() {
		return bound_Point_Pos_Desc;
	}

	public void setBound_Point_Pos_Desc(String bound_Point_Pos_Desc) {
		this.bound_Point_Pos_Desc = bound_Point_Pos_Desc;
		this.settings.put("bound_Point_Pos_Desc", bound_Point_Pos_Desc);
	}

	@DBMeta(column = "bound_Line_Direction_Desc", name = "主要权属界线走向说明", type = "String")
	public String getBound_Line_Direction_Desc() {
		return bound_Line_Direction_Desc;
	}

	public void setBound_Line_Direction_Desc(String bound_Line_Direction_Desc) {
		this.bound_Line_Direction_Desc = bound_Line_Direction_Desc;
		this.settings.put("bound_Line_Direction_Desc",
				bound_Line_Direction_Desc);
	}

	@DBMeta(column = "rightsType", name = "权利类型", type = "String")
	public String getRightsType() {
		return rightsType;
	}

	public void setRightsType(String rightsType) {
		this.rightsType = rightsType;
		this.settings.put("rightsType", rightsType);
	}

	@DBMeta(column = "realtyUnitCode", name = "不动产单元编号", type = "String")
	public String getRealtyUnitCode() {
		return realtyUnitCode;
	}

	public void setRealtyUnitCode(String realtyUnitCode) {
		this.realtyUnitCode = realtyUnitCode;
		this.settings.put("realtyUnitCode", realtyUnitCode);
	}

	@DBMeta(column = "area_Remain", name = "剩余面积", type = "double")
	public double getArea_Remain() {
		return area_Remain;
	}

	public void setArea_Remain(double area_Remain) {
		this.area_Remain = area_Remain;
		this.settings.put("area_Remain", area_Remain);
	}

	@DBMeta(column = "yzdtybm", name = "原宗地统一编码", type = "String")
	public String getYzdtybm() {
		return yzdtybm;
	}

	public void setYzdtybm(String yzdtybm) {
		this.yzdtybm = yzdtybm;
		this.settings.put("yzdtybm", yzdtybm);
	}

	@DBMeta(column = "mjdw", name = "面积单位", type = "String")
	public String getMjdw() {
		return mjdw;
	}

	public void setMjdw(String mjdw) {
		this.mjdw = mjdw;
		this.settings.put("mjdw", mjdw);
	}

	@DBMeta(column = "nydmj", name = "农用地面积", type = "double")
	public double getNydmj() {
		return nydmj;
	}

	public void setNydmj(double nydmj) {
		this.nydmj = nydmj;
		this.settings.put("nydmj", nydmj);
	}

	@DBMeta(column = "gdmj", name = "耕地面积", type = "double")
	public double getGdmj() {
		return gdmj;
	}

	public void setGdmj(double gdmj) {
		this.gdmj = gdmj;
		this.settings.put("gdmj", gdmj);
	}

	@DBMeta(column = "cdmj", name = "草地面积", type = "double")
	public double getCdmj() {
		return cdmj;
	}

	public void setCdmj(double cdmj) {
		this.cdmj = cdmj;
		this.settings.put("cdmj", cdmj);
	}

	@DBMeta(column = "qtmj", name = "其他面积", type = "double")
	public double getQtmj() {
		return qtmj;
	}

	public void setQtmj(double qtmj) {
		this.qtmj = qtmj;
		this.settings.put("qtmj", qtmj);
	}

	@DBMeta(column = "jsydmj", name = "建设用地面积", type = "double")
	public double getJsydmj() {
		return jsydmj;
	}

	public void setJsydmj(double jsydmj) {
		this.jsydmj = jsydmj;
		this.settings.put("jsydmj", jsydmj);
	}

	@DBMeta(column = "wlydmj", name = "未利用用地面积", type = "double")
	public double getWlydmj() {
		return wlydmj;
	}

	public void setWlydmj(double wlydmj) {
		this.wlydmj = wlydmj;
		this.settings.put("wlydmj", wlydmj);
	}

	@DBMeta(column = "ldmj", name = "林地面积", type = "double")
	public double getLdmj() {
		return ldmj;
	}

	public void setLdmj(double ldmj) {
		this.ldmj = ldmj;
		this.settings.put("ldmj", ldmj);
	}

}
