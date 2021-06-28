package com.netcom.nkestate.fhhouse.project.vo;

import java.util.Date;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "HOUSE", sequence = "", id = DBModel.AssignedID)
public class HouseVO extends FHVO {

	private long house_ID; //  房屋ID
	private long building_ID; //  楼栋ID
	private int districtID; //  区县ID	CODE512
	private String room_Number; //  室号
	private int status; //  状态	  CODE519 2:已签（黄）、3:已登记（红）、4:可售（绿）、8:已付定金（粉红）、9:未纳入网上销售（白）
	private int manual_Status; //  人工干预	CODE531 0:强制不可售、1:强制可售
	private String flarea; //  现房建筑面积
	private String priv_FlArea; //  现房套内面积
	private String co_FlArea; //  现房共用面积
	private String ex_FlArea; //  现房其它建筑面积
	private String ratial; //  现房分摊系数
	private String plan_FlArea; //  期房建筑面积
	private String plan_Priv_FlArea; //  期房套内面积
	private String plan_Co_FlArea; //  期房共用面积
	private String plan_Ex_FlArea; //  期房其它建筑面积
	private String plan_Ratial; //  期房分摊系数
	private String pre_Plan_ID; //  预测平面图ID
	private String sell_Plan_ID; //  实测平面图ID
	private String house_Number; //  户号,户籍管理中使用(ID7)
	private String flat_Style; //  户型字典表CT_FLAT_STYLE
	private String right_of_Owner; //  所有权性质
	private String area; //  土地面积
	private String co_Area; //  共有土地面积
	private String pro_Area; //  分摊土地面积
	private String priv_Area; //  独用土地面积
	private String ex_Area; //  超占土地面积
	private String adv_Area; //  调查土地面积
	private String building_Type; //  房屋类型
	private String flat_Type; //  房屋分类
	private String land_Use; //  房屋用途
	private String state; //  处理状态
	private Date state_Date; //  处理状态日期
	private String floor; //  楼层
	private String floor_Name; //  名义层
	private String note; //  备注
	private Date start_Date; //  记录入库时间
	private String land_Descent; //  土地来源
	private String home_Descent; //  房屋来源
	private String eOwner; //  权利人
	private String vocation; //  行业
	private String data_State; //  数据来源
	private String land_Use2; //  土地实际用途
	private String cellar_Area; //  地下附属面积
	private String plan_Cellar_Area; // 期房地下附属面积 
	private String house_Type; //  房屋标志 字典表:CT_HOUSE_TYPE
	private int noXgState;//房屋不限购标志  1：是  0/空：否

	@DBMeta(column = "house_ID", name = "房屋ID", type = "long", primarykey = "true", can_update = "false")
	public long getHouse_ID() {
		return house_ID;
	}

	public void setHouse_ID(long house_ID) {
		this.house_ID = house_ID;
		this.settings.put("house_ID", house_ID);
	}

	@DBMeta(column = "building_ID", name = "楼栋ID", type = "long")
	public long getBuilding_ID() {
		return building_ID;
	}

	public void setBuilding_ID(long building_ID) {
		this.building_ID = building_ID;
		this.settings.put("building_ID", building_ID);
	}

	@DBMeta(column = "districtID", name = "区县ID", type = "int", dict_table = "CT_DISTRICT")
	public int getDistrictID() {
		return districtID;
	}

	public void setDistrictID(int districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}

	@DBMeta(column = "room_Number", name = "室号", type = "String")
	public String getRoom_Number() {
		return room_Number;
	}

	public void setRoom_Number(String room_Number) {
		this.room_Number = room_Number;
		this.settings.put("room_Number", room_Number);
	}

	@DBMeta(column = "status", name = "状态", type = "int", dict_table = "CT_519")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}

	@DBMeta(column = "manual_Status", name = "人工干预", type = "int", dict_table = "CT_531")
	public int getManual_Status() {
		return manual_Status;
	}

	public void setManual_Status(int manual_Status) {
		this.manual_Status = manual_Status;
		this.settings.put("manual_Status", manual_Status);
	}

	@DBMeta(column = "flarea", name = "现房建筑面积", type = "String")
	public String getFlarea() {
		return flarea;
	}

	public void setFlarea(String flarea) {
		this.flarea = flarea;
		this.settings.put("flarea", flarea);
	}

	@DBMeta(column = "priv_FlArea", name = "现房套内面积", type = "String")
	public String getPriv_FlArea() {
		return priv_FlArea;
	}

	public void setPriv_FlArea(String priv_FlArea) {
		this.priv_FlArea = priv_FlArea;
		this.settings.put("priv_FlArea", priv_FlArea);
	}

	@DBMeta(column = "co_FlArea", name = "现房共用面积", type = "String")
	public String getCo_FlArea() {
		return co_FlArea;
	}

	public void setCo_FlArea(String co_FlArea) {
		this.co_FlArea = co_FlArea;
		this.settings.put("co_FlArea", co_FlArea);
	}

	@DBMeta(column = "ex_FlArea", name = "现房其它建筑面积", type = "String")
	public String getEx_FlArea() {
		return ex_FlArea;
	}

	public void setEx_FlArea(String ex_FlArea) {
		this.ex_FlArea = ex_FlArea;
		this.settings.put("ex_FlArea", ex_FlArea);
	}

	@DBMeta(column = "ratial", name = "现房分摊系数", type = "String")
	public String getRatial() {
		return ratial;
	}

	public void setRatial(String ratial) {
		this.ratial = ratial;
		this.settings.put("ratial", ratial);
	}

	@DBMeta(column = "plan_FlArea", name = "期房建筑面积", type = "String")
	public String getPlan_FlArea() {
		return plan_FlArea;
	}

	public void setPlan_FlArea(String plan_FlArea) {
		this.plan_FlArea = plan_FlArea;
		this.settings.put("plan_FlArea", plan_FlArea);
	}

	@DBMeta(column = "plan_Priv_FlArea", name = "期房套内面积", type = "String")
	public String getPlan_Priv_FlArea() {
		return plan_Priv_FlArea;
	}

	public void setPlan_Priv_FlArea(String plan_Priv_FlArea) {
		this.plan_Priv_FlArea = plan_Priv_FlArea;
		this.settings.put("plan_Priv_FlArea", plan_Priv_FlArea);
	}

	@DBMeta(column = "plan_Co_FlArea", name = "期房共用面积", type = "String")
	public String getPlan_Co_FlArea() {
		return plan_Co_FlArea;
	}

	public void setPlan_Co_FlArea(String plan_Co_FlArea) {
		this.plan_Co_FlArea = plan_Co_FlArea;
		this.settings.put("plan_Co_FlArea", plan_Co_FlArea);
	}

	@DBMeta(column = "plan_Ex_FlArea", name = "期房其它建筑面积", type = "String")
	public String getPlan_Ex_FlArea() {
		return plan_Ex_FlArea;
	}

	public void setPlan_Ex_FlArea(String plan_Ex_FlArea) {
		this.plan_Ex_FlArea = plan_Ex_FlArea;
		this.settings.put("plan_Ex_FlArea", plan_Ex_FlArea);
	}

	@DBMeta(column = "plan_Ratial", name = "期房分摊系数", type = "String")
	public String getPlan_Ratial() {
		return plan_Ratial;
	}

	public void setPlan_Ratial(String plan_Ratial) {
		this.plan_Ratial = plan_Ratial;
		this.settings.put("plan_Ratial", plan_Ratial);
	}

	@DBMeta(column = "pre_Plan_ID", name = "预测平面图ID", type = "String")
	public String getPre_Plan_ID() {
		return pre_Plan_ID;
	}

	public void setPre_Plan_ID(String pre_Plan_ID) {
		this.pre_Plan_ID = pre_Plan_ID;
		this.settings.put("pre_Plan_ID", pre_Plan_ID);
	}

	@DBMeta(column = "sell_Plan_ID", name = "实测平面图ID", type = "String")
	public String getSell_Plan_ID() {
		return sell_Plan_ID;
	}

	public void setSell_Plan_ID(String sell_Plan_ID) {
		this.sell_Plan_ID = sell_Plan_ID;
		this.settings.put("sell_Plan_ID", sell_Plan_ID);
	}

	@DBMeta(column = "house_Number", name = "户号,户籍管理中使用", type = "String")
	public String getHouse_Number() {
		return house_Number;
	}

	public void setHouse_Number(String house_Number) {
		this.house_Number = house_Number;
		this.settings.put("house_Number", house_Number);
	}

	@DBMeta(column = "flat_Style", name = "户型字典表CT_FLAT_STYLE", type = "String")
	public String getFlat_Style() {
		return flat_Style;
	}

	public void setFlat_Style(String flat_Style) {
		this.flat_Style = flat_Style;
		this.settings.put("flat_Style", flat_Style);
	}

	@DBMeta(column = "right_of_Owner", name = "所有权性质", type = "String")
	public String getRight_of_Owner() {
		return right_of_Owner;
	}

	public void setRight_of_Owner(String right_of_Owner) {
		this.right_of_Owner = right_of_Owner;
		this.settings.put("right_of_Owner", right_of_Owner);
	}

	@DBMeta(column = "area", name = "土地面积", type = "String")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
		this.settings.put("area", area);
	}

	@DBMeta(column = "co_Area", name = "共有土地面积", type = "String")
	public String getCo_Area() {
		return co_Area;
	}

	public void setCo_Area(String co_Area) {
		this.co_Area = co_Area;
		this.settings.put("co_Area", co_Area);
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

	@DBMeta(column = "ex_Area", name = "超占土地面积", type = "String")
	public String getEx_Area() {
		return ex_Area;
	}

	public void setEx_Area(String ex_Area) {
		this.ex_Area = ex_Area;
		this.settings.put("ex_Area", ex_Area);
	}

	@DBMeta(column = "adv_Area", name = "调查土地面积", type = "String")
	public String getAdv_Area() {
		return adv_Area;
	}

	public void setAdv_Area(String adv_Area) {
		this.adv_Area = adv_Area;
		this.settings.put("adv_Area", adv_Area);
	}

	@DBMeta(column = "building_Type", name = "房屋类型", type = "String")
	public String getBuilding_Type() {
		return building_Type;
	}

	public void setBuilding_Type(String building_Type) {
		this.building_Type = building_Type;
		this.settings.put("building_Type", building_Type);
	}

	@DBMeta(column = "flat_Type", name = "房屋分类", type = "String")
	public String getFlat_Type() {
		return flat_Type;
	}

	public void setFlat_Type(String flat_Type) {
		this.flat_Type = flat_Type;
		this.settings.put("flat_Type", flat_Type);
	}

	@DBMeta(column = "land_Use", name = "房屋用途", type = "String")
	public String getLand_Use() {
		return land_Use;
	}

	public void setLand_Use(String land_Use) {
		this.land_Use = land_Use;
		this.settings.put("land_Use", land_Use);
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

	@DBMeta(column = "floor", name = "楼层", type = "String")
	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
		this.settings.put("floor", floor);
	}

	@DBMeta(column = "floor_Name", name = "名义层", type = "String")
	public String getFloor_Name() {
		return floor_Name;
	}

	public void setFloor_Name(String floor_Name) {
		this.floor_Name = floor_Name;
		this.settings.put("floor_Name", floor_Name);
	}

	@DBMeta(column = "note", name = "备注", type = "String")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
		this.settings.put("note", note);
	}

	@DBMeta(column = "start_Date", name = "记录入库时间", type = "Date")
	public Date getStart_Date() {
		return start_Date;
	}

	public void setStart_Date(Date start_Date) {
		this.start_Date = start_Date;
		this.settings.put("start_Date", start_Date);
	}

	@DBMeta(column = "land_Descent", name = "土地来源", type = "String")
	public String getLand_Descent() {
		return land_Descent;
	}

	public void setLand_Descent(String land_Descent) {
		this.land_Descent = land_Descent;
		this.settings.put("land_Descent", land_Descent);
	}

	@DBMeta(column = "home_Descent", name = "房屋来源", type = "String")
	public String getHome_Descent() {
		return home_Descent;
	}

	public void setHome_Descent(String home_Descent) {
		this.home_Descent = home_Descent;
		this.settings.put("home_Descent", home_Descent);
	}

	@DBMeta(column = "eOwner", name = "权利人", type = "String")
	public String geteOwner() {
		return eOwner;
	}

	public void seteOwner(String eOwner) {
		this.eOwner = eOwner;
		this.settings.put("eOwner", eOwner);
	}

	@DBMeta(column = "vocation", name = "行业", type = "String")
	public String getVocation() {
		return vocation;
	}

	public void setVocation(String vocation) {
		this.vocation = vocation;
		this.settings.put("vocation", vocation);
	}

	@DBMeta(column = "data_State", name = "数据来源", type = "String")
	public String getData_State() {
		return data_State;
	}

	public void setData_State(String data_State) {
		this.data_State = data_State;
		this.settings.put("data_State", data_State);
	}

	@DBMeta(column = "land_Use2", name = "土地实际用途", type = "String")
	public String getLand_Use2() {
		return land_Use2;
	}

	public void setLand_Use2(String land_Use2) {
		this.land_Use2 = land_Use2;
		this.settings.put("land_Use2", land_Use2);
	}

	@DBMeta(column = "cellar_Area", name = "地下附属面积", type = "String")
	public String getCellar_Area() {
		return cellar_Area;
	}

	public void setCellar_Area(String cellar_Area) {
		this.cellar_Area = cellar_Area;
		this.settings.put("cellar_Area", cellar_Area);
	}

	@DBMeta(column = "plan_Cellar_Area", name = "期房地下附属面积", type = "String")
	public String getPlan_Cellar_Area() {
		return plan_Cellar_Area;
	}

	public void setPlan_Cellar_Area(String plan_Cellar_Area) {
		this.plan_Cellar_Area = plan_Cellar_Area;
		this.settings.put("plan_Cellar_Area", plan_Cellar_Area);
	}

	@DBMeta(column = "house_Type", name = "房屋标志 字典表:CT_HOUSE_TYPE", type = "String")
	public String getHouse_Type() {
		return house_Type;
	}

	public void setHouse_Type(String house_Type) {
		this.house_Type = house_Type;
		this.settings.put("house_Type", house_Type);
	}

	@DBMeta(column = "noXgState", name = "房屋不限购状态", type = "int")
	public int getNoXgState() {
		return noXgState;
	}


	public void setNoXgState(int noXgState) {
		this.noXgState = noXgState;
		this.settings.put("noXgState", noXgState);
	}

	public String getNoXgState2() {
		if(noXgState == 1){
			return "已  取消  限购令";
		}else{
			return "已  执行  限购令";
		}
	}

}
