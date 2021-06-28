package com.netcom.nkestate.fhhouse.interfaces.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "COMMON.CH_FLAT", sequence = "", id = DBModel.AssignedID)
public class CHFlatVO extends AbstractBaseVO {

	protected long houseID; // 户编号（序列SEQ_CH_FLAT）
	protected String house_Sign; // 户标识（暂时保留，可以不用，序列SEQ_CH_FLAT_HOUSE_SIGN）
	protected String house_Number; // 户号
	protected String realtyUnitCode; // 不动产单元代码
	protected String floor_From; // 起始实际层
	protected String floor_To; // 终止实际层
	protected long buildingID; // 楼栋编号
	protected String districtID; // 区县编号
	protected String room_Number; // 户室
	protected String room_Number_Shown; // 显示户室号
	protected String flat_Style; // 户型(字典代码)
	protected String house_Nature; // 房屋性质(字典代码)
	protected String house_Use; // 房屋规划用途(字典代码)
	protected String house_Use_Desc; // 房屋用途描述
	protected String plan_Flarea; // 期房预测面积
	protected String plan_Priv_Flarea; // 期房预测套内面积(包括阳台面积)
	protected String plan_Co_Flarea; // 期房分摊面积
	protected String plan_Ex_Flarea; // 期房其他面积(阳台面积)
	protected String plan_Ratial; // 期房分摊系数
	protected String plan_Cellar_Area; // 期房地下部分面积
	protected String plan_Special_Area; // 期房低于2.2米得面积
	protected String flarea; // 建筑面积
	protected String priv_Flarea; // 套内面积(包括阳台面积)
	protected String co_Flarea; // 分摊面积
	protected String ex_Flarea; // 其他面积(阳台面积)
	protected String ratial; // 分摊系数
	protected String cellar_Area; // 地下部分面积
	protected String special_Area; // 低于2.2米的面积
	protected String pro_Area; // 分摊土地面积
	protected String priv_Area; // 独用土地面积(暂时不用)
	protected String is_Fs; // 是否复式(字典表CT_IS_FS,0-否;1-是)
	protected String is_Qs; // 是否权属单元(字典表CT_IS_QS,0-否;1-是)
	protected String plan_ID; // 平面图编号
	protected String use_Note; // 多用途描述
	protected String note; // 备注
	protected String survey_State; // 测绘状态
	protected Date created_Date; // 创建日期
	protected String state; // 处理状态(1-正常;2-锁定)
	protected Date state_Date; // 状态日期
	protected long version_ID; // 版本号
	protected String flat_Type; // 房屋类型(字典代码)
	protected String right_Of_Owner; // 所有权性质(字典代码)
	protected String home_Descent; // 房屋来源(字典代码)
	protected String isReg; // 是否登记mdb( 1-登记0-未登记)
	protected String apportion_Quotiety; // 土地分摊系数
	protected Date edit_Date; // 修改日期
	protected String floor_Name; // 名义层
	protected String building_Type; // 建筑类型(字典代码)
	protected String house_Code; // 房屋编码
	protected double common_Area; // 共有土地面积
	protected String location_Name; // 坐落
	protected String pre_Location_Name; // 预测坐落
	protected Date end_Date; // 结束时间
	protected String building_Mater; // 建筑结构(字典代码)
	protected String own; // 房屋所有人
	protected String old_Location_Name; // 旧坐落	
	protected String flat_Structure; // 户型结构(字典代码)
	protected String survey_ID; // 楼盘对应ID
	protected Date tdsyqssj; // 土地使用起始时间
	protected Date tdsyjssj; // 土地使用结束时间
	protected String land_Use; // 土地用途（字典表）
	protected String rightsNature; // 权利性质（字典表）
	protected String rightsType; // 权利类型（字典表）
	protected String qtgs_D; // 墙体归属东
	protected String qtgs_N; // 墙体归属南
	protected String qtgs_X; // 墙体归属西
	protected String qtgs_B; // 墙体归属北
	protected String zjzl; // 证件种类
	protected String zjbh; // 证件编号
	protected String yb; // 邮编
	protected String qlr_Dh; // 权利人电话
	protected String qlr_Zz; // 权利人住址
	protected String qlr_Lx; // 权利人类型
	protected String xmmc; // 项目名称
	protected String gyqk; // 共有情况
	protected String dcyj; // 调查意见
	protected Date dcrq; // 调查日期
	protected String dcr; // 调查人
	protected String cb; // 产别DIC_RIGHT_KIND
	protected String sjyt; // 实际用途
	protected String areaCert;
	protected long lotID; // 地编号
	protected long usersNO; // 版本号
	protected String thisStr; //jsp页面传递this对象用

	@DBMeta(column = "houseID", name = "户编号", type = "long", primarykey = "true", can_update = "false")
	public long getHouseID() {
		return houseID;
	}

	public void setHouseID(long houseID) {
		this.houseID = houseID;
		this.settings.put("houseID", houseID);
	}

	@DBMeta(column = "house_Sign", name = "户标识", type = "String")
	public String getHouse_Sign() {
		return house_Sign;
	}

	public void setHouse_Sign(String house_Sign) {
		this.house_Sign = house_Sign;
		this.settings.put("house_Sign", house_Sign);
	}

	@DBMeta(column = "house_Number", name = "户号", type = "String")
	public String getHouse_Number() {
		return house_Number;
	}

	public void setHouse_Number(String house_Number) {
		this.house_Number = house_Number;
		this.settings.put("house_Number", house_Number);
	}

	@DBMeta(column = "floor_From", name = "起始实际层", type = "String")
	public String getFloor_From() {
		return floor_From;
	}

	public void setFloor_From(String floor_From) {
		this.floor_From = floor_From;
		this.settings.put("floor_From", floor_From);
	}

	@DBMeta(column = "floor_To", name = "终止实际层", type = "String")
	public String getFloor_To() {
		return floor_To;
	}

	public void setFloor_To(String floor_To) {
		this.floor_To = floor_To;
		this.settings.put("floor_To", floor_To);
	}

	@DBMeta(column = "buildingID", name = "楼栋编号", type = "long")
	public long getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(long buildingID) {
		this.buildingID = buildingID;
		this.settings.put("buildingID", buildingID);
	}

	@DBMeta(column = "districtID", name = "区县编号", type = "String")
	public String getDistrictID() {
		return districtID;
	}

	public void setDistrictID(String districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}

	@DBMeta(column = "room_Number", name = "户室", type = "String")
	public String getRoom_Number() {
		return room_Number;
	}

	public void setRoom_Number(String room_Number) {
		this.room_Number = room_Number;
		this.settings.put("room_Number", room_Number);
	}

	@DBMeta(column = "room_Number_Shown", name = "显示户室号", type = "String")
	public String getRoom_Number_Shown() {
		return room_Number_Shown;
	}

	public void setRoom_Number_Shown(String room_Number_Shown) {
		this.room_Number_Shown = room_Number_Shown;
		this.settings.put("room_Number_Shown", room_Number_Shown);
	}

	@DBMeta(column = "flat_Style", name = "户型", type = "String")
	public String getFlat_Style() {
		return flat_Style;
	}

	public void setFlat_Style(String flat_Style) {
		this.flat_Style = flat_Style;
		this.settings.put("flat_Style", flat_Style);
	}

	@DBMeta(column = "house_Nature", name = "房屋性质", type = "String")
	public String getHouse_Nature() {
		return house_Nature;
	}

	public void setHouse_Nature(String house_Nature) {
		this.house_Nature = house_Nature;
		this.settings.put("house_Nature", house_Nature);
	}

	@DBMeta(column = "house_Use", name = "房屋规划用途", type = "String")
	public String getHouse_Use() {
		return house_Use;
	}

	public void setHouse_Use(String house_Use) {
		this.house_Use = house_Use;
		this.settings.put("house_Use", house_Use);
	}

	@DBMeta(column = "house_Use_Desc", name = "房屋用途描述", type = "String")
	public String getHouse_Use_Desc() {
		return house_Use_Desc;
	}

	public void setHouse_Use_Desc(String house_Use_Desc) {
		this.house_Use_Desc = house_Use_Desc;
		this.settings.put("house_Use_Desc", house_Use_Desc);
	}

	@DBMeta(column = "plan_Flarea", name = "期房预测面积", type = "String")
	public String getPlan_Flarea() {
		return plan_Flarea;
	}

	public void setPlan_Flarea(String plan_Flarea) {
		this.plan_Flarea = plan_Flarea;
		this.settings.put("plan_Flarea", plan_Flarea);
	}

	@DBMeta(column = "plan_Priv_Flarea", name = "期房预测套内面积", type = "String")
	public String getPlan_Priv_Flarea() {
		return plan_Priv_Flarea;
	}

	public void setPlan_Priv_Flarea(String plan_Priv_Flarea) {
		this.plan_Priv_Flarea = plan_Priv_Flarea;
		this.settings.put("plan_Priv_Flarea", plan_Priv_Flarea);
	}

	@DBMeta(column = "plan_Co_Flarea", name = "期房分摊面积", type = "String")
	public String getPlan_Co_Flarea() {
		return plan_Co_Flarea;
	}

	public void setPlan_Co_Flarea(String plan_Co_Flarea) {
		this.plan_Co_Flarea = plan_Co_Flarea;
		this.settings.put("plan_Co_Flarea", plan_Co_Flarea);
	}

	@DBMeta(column = "plan_Ex_Flarea", name = "期房其他面积", type = "String")
	public String getPlan_Ex_Flarea() {
		return plan_Ex_Flarea;
	}

	public void setPlan_Ex_Flarea(String plan_Ex_Flarea) {
		this.plan_Ex_Flarea = plan_Ex_Flarea;
		this.settings.put("plan_Ex_Flarea", plan_Ex_Flarea);
	}

	@DBMeta(column = "plan_Ratial", name = "期房分摊系数", type = "String")
	public String getPlan_Ratial() {
		return plan_Ratial;
	}

	public void setPlan_Ratial(String plan_Ratial) {
		this.plan_Ratial = plan_Ratial;
		this.settings.put("plan_Ratial", plan_Ratial);
	}

	@DBMeta(column = "plan_Cellar_Area", name = "期房地下部分面积", type = "String")
	public String getPlan_Cellar_Area() {
		return plan_Cellar_Area;
	}

	public void setPlan_Cellar_Area(String plan_Cellar_Area) {
		this.plan_Cellar_Area = plan_Cellar_Area;
		this.settings.put("plan_Cellar_Area", plan_Cellar_Area);
	}

	@DBMeta(column = "plan_Special_Area", name = "期房低于2.2米得面积", type = "String")
	public String getPlan_Special_Area() {
		return plan_Special_Area;
	}

	public void setPlan_Special_Area(String plan_Special_Area) {
		this.plan_Special_Area = plan_Special_Area;
		this.settings.put("plan_Special_Area", plan_Special_Area);
	}

	@DBMeta(column = "flarea", name = "建筑面积", type = "String")
	public String getFlarea() {
		return flarea;
	}

	public void setFlarea(String flarea) {
		this.flarea = flarea;
		this.settings.put("flarea", flarea);
	}

	@DBMeta(column = "priv_Flarea", name = "套内面积", type = "String")
	public String getPriv_Flarea() {
		return priv_Flarea;
	}

	public void setPriv_Flarea(String priv_Flarea) {
		this.priv_Flarea = priv_Flarea;
		this.settings.put("priv_Flarea", priv_Flarea);
	}

	@DBMeta(column = "co_Flarea", name = "分摊面积", type = "String")
	public String getCo_Flarea() {
		return co_Flarea;
	}

	public void setCo_Flarea(String co_Flarea) {
		this.co_Flarea = co_Flarea;
		this.settings.put("co_Flarea", co_Flarea);
	}

	@DBMeta(column = "ex_Flarea", name = "其他面积", type = "String")
	public String getEx_Flarea() {
		return ex_Flarea;
	}

	public void setEx_Flarea(String ex_Flarea) {
		this.ex_Flarea = ex_Flarea;
		this.settings.put("ex_Flarea", ex_Flarea);
	}

	@DBMeta(column = "ratial", name = "分摊系数", type = "String")
	public String getRatial() {
		return ratial;
	}

	public void setRatial(String ratial) {
		this.ratial = ratial;
		this.settings.put("ratial", ratial);
	}

	@DBMeta(column = "cellar_Area", name = "地下部分面积", type = "String")
	public String getCellar_Area() {
		return cellar_Area;
	}

	public void setCellar_Area(String cellar_Area) {
		this.cellar_Area = cellar_Area;
		this.settings.put("cellar_Area", cellar_Area);
	}

	@DBMeta(column = "special_Area", name = "低于2.2米的面积", type = "String")
	public String getSpecial_Area() {
		return special_Area;
	}

	public void setSpecial_Area(String special_Area) {
		this.special_Area = special_Area;
		this.settings.put("special_Area", special_Area);
	}

	@DBMeta(column = "pro_Area", name = "分摊土地面积", type = "String")
	public String getPro_Area() {
		return pro_Area;
	}

	public void setPro_Area(String pro_Area) {
		this.pro_Area = pro_Area;
		this.settings.put("pro_Area", pro_Area);
	}

	@DBMeta(column = "priv_Area", name = "独用土地面积", type = "String")
	public String getPriv_Area() {
		return priv_Area;
	}

	public void setPriv_Area(String priv_Area) {
		this.priv_Area = priv_Area;
		this.settings.put("priv_Area", priv_Area);
	}

	@DBMeta(column = "is_Fs", name = "是否复式", type = "String")
	public String getIs_Fs() {
		return is_Fs;
	}

	public void setIs_Fs(String is_Fs) {
		this.is_Fs = is_Fs;
		this.settings.put("is_Fs", is_Fs);
	}

	@DBMeta(column = "is_Qs", name = "是否权属单元", type = "String")
	public String getIs_Qs() {
		return is_Qs;
	}

	public void setIs_Qs(String is_Qs) {
		this.is_Qs = is_Qs;
		this.settings.put("is_Qs", is_Qs);
	}

	@DBMeta(column = "plan_ID", name = "平面图编号", type = "String")
	public String getPlan_ID() {
		return plan_ID;
	}

	public void setPlan_ID(String plan_ID) {
		this.plan_ID = plan_ID;
		this.settings.put("plan_ID", plan_ID);
	}

	@DBMeta(column = "use_Note", name = "多用途描述", type = "String")
	public String getUse_Note() {
		return use_Note;
	}

	public void setUse_Note(String use_Note) {
		this.use_Note = use_Note;
		this.settings.put("use_Note", use_Note);
	}

	@DBMeta(column = "note", name = "备注", type = "String")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
		this.settings.put("note", note);
	}

	@DBMeta(column = "survey_State", name = "测绘状态", type = "String")
	public String getSurvey_State() {
		return survey_State;
	}

	public void setSurvey_State(String survey_State) {
		this.survey_State = survey_State;
		this.settings.put("survey_State", survey_State);
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

	@DBMeta(column = "state_Date", name = "状态日期", type = "Date")
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

	@DBMeta(column = "flat_Type", name = "房屋类型", type = "String")
	public String getFlat_Type() {
		return flat_Type;
	}

	public void setFlat_Type(String flat_Type) {
		this.flat_Type = flat_Type;
		this.settings.put("flat_Type", flat_Type);
	}

	@DBMeta(column = "right_Of_Owner", name = "所有权性质", type = "String")
	public String getRight_Of_Owner() {
		return right_Of_Owner;
	}

	public void setRight_Of_Owner(String right_Of_Owner) {
		this.right_Of_Owner = right_Of_Owner;
		this.settings.put("right_Of_Owner", right_Of_Owner);
	}

	@DBMeta(column = "home_Descent", name = "房屋来源", type = "String")
	public String getHome_Descent() {
		return home_Descent;
	}

	public void setHome_Descent(String home_Descent) {
		this.home_Descent = home_Descent;
		this.settings.put("home_Descent", home_Descent);
	}

	@DBMeta(column = "isReg", name = "是否登记mdb", type = "String")
	public String getIsReg() {
		return isReg;
	}

	public void setIsReg(String isReg) {
		this.isReg = isReg;
		this.settings.put("isReg", isReg);
	}

	@DBMeta(column = "apportion_Quotiety", name = "土地分摊系数", type = "String")
	public String getApportion_Quotiety() {
		return apportion_Quotiety;
	}

	public void setApportion_Quotiety(String apportion_Quotiety) {
		this.apportion_Quotiety = apportion_Quotiety;
		this.settings.put("apportion_Quotiety", apportion_Quotiety);
	}

	@DBMeta(column = "edit_Date", name = "修改日期", type = "Date")
	public Date getEdit_Date() {
		return edit_Date;
	}

	public void setEdit_Date(Date edit_Date) {
		this.edit_Date = edit_Date;
		this.settings.put("edit_Date", edit_Date);
	}

	@DBMeta(column = "floor_Name", name = "名义层", type = "String")
	public String getFloor_Name() {
		return floor_Name;
	}

	public void setFloor_Name(String floor_Name) {
		this.floor_Name = floor_Name;
		this.settings.put("floor_Name", floor_Name);
	}

	@DBMeta(column = "building_Type", name = "建筑类型", type = "String")
	public String getBuilding_Type() {
		return building_Type;
	}

	public void setBuilding_Type(String building_Type) {
		this.building_Type = building_Type;
		this.settings.put("building_Type", building_Type);
	}

	@DBMeta(column = "house_Code", name = "房屋编码", type = "String")
	public String getHouse_Code() {
		return house_Code;
	}

	public void setHouse_Code(String house_Code) {
		this.house_Code = house_Code;
		this.settings.put("house_Code", house_Code);
	}

	@DBMeta(column = "common_Area", name = "共有土地面积", type = "double")
	public double getCommon_Area() {
		return common_Area;
	}

	public void setCommon_Area(double common_Area) {
		this.common_Area = common_Area;
		this.settings.put("common_Area", common_Area);
	}

	@DBMeta(column = "location_Name", name = "坐落", type = "String")
	public String getLocation_Name() {
		return location_Name;
	}

	public void setLocation_Name(String location_Name) {
		this.location_Name = location_Name;
		this.settings.put("location_Name", location_Name);
	}

	@DBMeta(column = "pre_Location_Name", name = "预测坐落", type = "String")
	public String getPre_Location_Name() {
		return pre_Location_Name;
	}

	public void setPre_Location_Name(String pre_Location_Name) {
		this.pre_Location_Name = pre_Location_Name;
		this.settings.put("pre_Location_Name", pre_Location_Name);
	}

	@DBMeta(column = "end_Date", name = "结束时间", type = "Date")
	public Date getEnd_Date() {
		return end_Date;
	}

	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
		this.settings.put("end_Date", end_Date);
	}

	@DBMeta(column = "building_Mater", name = "建筑结构", type = "String")
	public String getBuilding_Mater() {
		return building_Mater;
	}

	public void setBuilding_Mater(String building_Mater) {
		this.building_Mater = building_Mater;
		this.settings.put("building_Mater", building_Mater);
	}

	@DBMeta(column = "own", name = "房屋所有人", type = "String")
	public String getOwn() {
		return own;
	}

	public void setOwn(String own) {
		this.own = own;
		this.settings.put("own", own);
	}

	@DBMeta(column = "old_Location_Name", name = "旧坐落", type = "String")
	public String getOld_Location_Name() {
		return old_Location_Name;
	}

	public void setOld_Location_Name(String old_Location_Name) {
		this.old_Location_Name = old_Location_Name;
		this.settings.put("old_Location_Name", old_Location_Name);
	}

	@DBMeta(column = "realtyUnitCode", name = "不动产登记代码", type = "String")
	public String getRealtyUnitCode() {
		return realtyUnitCode;
	}

	public void setRealtyUnitCode(String realtyUnitCode) {
		this.realtyUnitCode = realtyUnitCode;
		this.settings.put("realtyUnitCode", realtyUnitCode);
	}

	@DBMeta(column = "flat_Structure", name = "户型结构", type = "String")
	public String getFlat_Structure() {
		return flat_Structure;
	}

	public void setFlat_Structure(String flat_Structure) {
		this.flat_Structure = flat_Structure;
		this.settings.put("flat_Structure", flat_Structure);
	}

	@DBMeta(column = "survey_ID", name = "楼盘对应ID", type = "String")
	public String getSurvey_ID() {
		return survey_ID;
	}

	public void setSurvey_ID(String survey_ID) {
		this.survey_ID = survey_ID;
		this.settings.put("survey_ID", survey_ID);
	}

	@DBMeta(column = "tdsyqssj", name = "土地使用起始时间", type = "Date")
	public Date getTdsyqssj() {
		return tdsyqssj;
	}

	public void setTdsyqssj(Date tdsyqssj) {
		this.tdsyqssj = tdsyqssj;
		this.settings.put("tdsyqssj", tdsyqssj);
	}

	@DBMeta(column = "tdsyjssj", name = "土地使用结束时间", type = "Date")
	public Date getTdsyjssj() {
		return tdsyjssj;
	}

	public void setTdsyjssj(Date tdsyjssj) {
		this.tdsyjssj = tdsyjssj;
		this.settings.put("tdsyjssj", tdsyjssj);
	}

	@DBMeta(column = "land_Use", name = "土地用途", type = "String")
	public String getLand_Use() {
		return land_Use;
	}

	public void setLand_Use(String land_Use) {
		this.land_Use = land_Use;
		this.settings.put("land_Use", land_Use);
	}

	@DBMeta(column = "rightsNature", name = "权利性质", type = "String")
	public String getRightsNature() {
		return rightsNature;
	}

	public void setRightsNature(String rightsNature) {
		this.rightsNature = rightsNature;
		this.settings.put("rightsNature", rightsNature);
	}

	@DBMeta(column = "rightsType", name = "权利类型", type = "String")
	public String getRightsType() {
		return rightsType;
	}

	public void setRightsType(String rightsType) {
		this.rightsType = rightsType;
		this.settings.put("rightsType", rightsType);
	}

	@DBMeta(column = "qtgs_D", name = "墙体归属东", type = "String")
	public String getQtgs_D() {
		return qtgs_D;
	}

	public void setQtgs_D(String qtgs_D) {
		this.qtgs_D = qtgs_D;
		this.settings.put("qtgs_D", qtgs_D);
	}

	@DBMeta(column = "qtgs_N", name = "墙体归属南", type = "String")
	public String getQtgs_N() {
		return qtgs_N;
	}

	public void setQtgs_N(String qtgs_N) {
		this.qtgs_N = qtgs_N;
		this.settings.put("qtgs_N", qtgs_N);
	}

	@DBMeta(column = "qtgs_X", name = "墙体归属西", type = "String")
	public String getQtgs_X() {
		return qtgs_X;
	}

	public void setQtgs_X(String qtgs_X) {
		this.qtgs_X = qtgs_X;
		this.settings.put("qtgs_X", qtgs_X);
	}

	@DBMeta(column = "qtgs_B", name = "墙体归属北", type = "String")
	public String getQtgs_B() {
		return qtgs_B;
	}

	public void setQtgs_B(String qtgs_B) {
		this.qtgs_B = qtgs_B;
		this.settings.put("qtgs_B", qtgs_B);
	}

	@DBMeta(column = "zjzl", name = "证件种类", type = "String")
	public String getZjzl() {
		return zjzl;
	}

	public void setZjzl(String zjzl) {
		this.zjzl = zjzl;
		this.settings.put("zjzl", zjzl);
	}

	@DBMeta(column = "zjbh", name = "证件编号", type = "String")
	public String getZjbh() {
		return zjbh;
	}

	public void setZjbh(String zjbh) {
		this.zjbh = zjbh;
		this.settings.put("zjbh", zjbh);
	}

	@DBMeta(column = "yb", name = "邮编", type = "String")
	public String getYb() {
		return yb;
	}

	public void setYb(String yb) {
		this.yb = yb;
		this.settings.put("yb", yb);
	}

	@DBMeta(column = "qlr_Dh", name = "权利人电话", type = "String")
	public String getQlr_Dh() {
		return qlr_Dh;
	}

	public void setQlr_Dh(String qlr_Dh) {
		this.qlr_Dh = qlr_Dh;
		this.settings.put("qlr_Dh", qlr_Dh);
	}

	@DBMeta(column = "qlr_Zz", name = "权利人住址", type = "String")
	public String getQlr_Zz() {
		return qlr_Zz;
	}

	public void setQlr_Zz(String qlr_Zz) {
		this.qlr_Zz = qlr_Zz;
		this.settings.put("qlr_Zz", qlr_Zz);
	}

	@DBMeta(column = "qlr_Lx", name = "权利人类型", type = "String")
	public String getQlr_Lx() {
		return qlr_Lx;
	}

	public void setQlr_Lx(String qlr_Lx) {
		this.qlr_Lx = qlr_Lx;
		this.settings.put("qlr_Lx", qlr_Lx);
	}

	@DBMeta(column = "xmmc", name = "项目名称", type = "String")
	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
		this.settings.put("xmmc", xmmc);
	}

	@DBMeta(column = "gyqk", name = "共有情况", type = "String")
	public String getGyqk() {
		return gyqk;
	}

	public void setGyqk(String gyqk) {
		this.gyqk = gyqk;
		this.settings.put("gyqk", gyqk);
	}

	@DBMeta(column = "dcyj", name = "调查意见", type = "String")
	public String getDcyj() {
		return dcyj;
	}

	public void setDcyj(String dcyj) {
		this.dcyj = dcyj;
		this.settings.put("dcyj", dcyj);
	}

	@DBMeta(column = "dcrq", name = "调查日期", type = "Date")
	public Date getDcrq() {
		return dcrq;
	}

	public void setDcrq(Date dcrq) {
		this.dcrq = dcrq;
		this.settings.put("dcrq", dcrq);
	}

	@DBMeta(column = "dcr", name = "调查人", type = "String")
	public String getDcr() {
		return dcr;
	}

	public void setDcr(String dcr) {
		this.dcr = dcr;
		this.settings.put("dcr", dcr);
	}

	@DBMeta(column = "cb", name = "产别", type = "String")
	public String getCb() {
		return cb;
	}

	public void setCb(String cb) {
		this.cb = cb;
		this.settings.put("cb", cb);
	}

	@DBMeta(column = "sjyt", name = "实际用途", type = "String")
	public String getSjyt() {
		return sjyt;
	}

	public void setSjyt(String sjyt) {
		this.sjyt = sjyt;
		this.settings.put("sjyt", sjyt);
	}
	
	@DBMeta(column = "lotID", name = "宗地编号", type = "long")
	public long getLotID() {
		return lotID;
	}

	public void setLotID(long lotID) {
		this.lotID = lotID;
		this.settings.put("lotID", lotID);
	}
	
	@DBMeta(column = "usersNO", name = "用户编号", type = "long")
	public long getUsersNO() {
		return usersNO;
	}


	public void setUsersNO(long usersNO) {
		this.usersNO = usersNO;
		this.settings.put("usersNO", usersNO);
	}

	public String getAreaCert() {
		String area = "";
		if(getPlan_Flarea() != null && !"".equals(getPlan_Flarea())){
			area += getPlan_Flarea()+"/";
		}else{
			area += "0/";
		}
		if(getFlarea() != null && !"".equals(getFlarea())){
			area += getFlarea();
		}else{
			area += "0";
		}
		
		return area;
	}

	public String getThisStr() {
		return "this";
	}
	
	
	
}
