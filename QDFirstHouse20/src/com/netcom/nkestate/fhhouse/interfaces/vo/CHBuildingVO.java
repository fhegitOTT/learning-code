/**
 * <p>BuildingVO.java </p>
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

@DBModel(tablename = "COMMON.CH_BUILDING", sequence = "", id = DBModel.AssignedID)
public class CHBuildingVO extends AbstractBaseVO {

	protected long buildingID; // 楼栋编号（序列SEQ_CH_BUILDING）
	protected long natureID; // 自然幢编号
	protected long logical_Number; // 逻辑幢号
	protected String building_Name; // 逻辑幢名称
	protected String doorPlate; // 门牌号
	protected double plan_Flarea; // 预测面积
	protected double area; // 实测面积
	protected double cellar_Area; // 地下面积
	protected double ex_Area; // 其他面积
	protected String building_Type; // 建筑类型(字典代码)
	protected String building_Mater; // 建筑结构(字典代码)
	protected String complete_Date; // 竣工日期
	protected String note; // 备注
	protected String survey_State; // 测绘状态
	protected Date created_Date; // 创建日期
	protected String state; // 处理状态(1-正常;2-锁定)
	protected Date state_Date; // 处理状态日期
	protected long version_ID; // 版本号
	protected Date edit_Date; // 修改日期
	protected String districtID; // 区县编号
	protected double plan_Ex_Flarea; // 预测其他面积
	protected double plan_Cellar_Area; // 预测地下面积
	protected String house_Use; // 房屋用途(字典代码)
	protected Date end_Date; // 结束日期
	protected String location_Name; // 逻辑幢坐落
	protected long survey_ID; // 楼盘对应ID
	protected String building_Number;
	protected String floors;
	protected String cellarFloors;

	@DBMeta(column = "buildingID", name = "楼栋编号", type = "long", primarykey = "true", can_update = "false")
	public long getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(long buildingID) {
		this.buildingID = buildingID;
		this.settings.put("buildingID", buildingID);
	}

	@DBMeta(column = "natureID", name = "自然幢编号", type = "long")
	public long getNatureID() {
		return natureID;
	}

	public void setNatureID(long natureID) {
		this.natureID = natureID;
		this.settings.put("natureID", natureID);
	}

	@DBMeta(column = "logical_Number", name = "逻辑幢号", type = "long")
	public long getLogical_Number() {
		return logical_Number;
	}

	public void setLogical_Number(long logical_Number) {
		this.logical_Number = logical_Number;
		this.settings.put("logical_Number", logical_Number);
	}

	@DBMeta(column = "building_Name", name = "逻辑幢名称", type = "String")
	public String getBuilding_Name() {
		return building_Name;
	}

	public void setBuilding_Name(String building_Name) {
		this.building_Name = building_Name;
		this.settings.put("building_Name", building_Name);
	}

	@DBMeta(column = "doorPlate", name = "门牌号", type = "String")
	public String getDoorPlate() {
		return doorPlate;
	}

	public void setDoorPlate(String doorPlate) {
		this.doorPlate = doorPlate;
		this.settings.put("doorPlate", doorPlate);
	}

	@DBMeta(column = "plan_Flarea", name = "预测面积", type = "double")
	public double getPlan_Flarea() {
		return plan_Flarea;
	}

	public void setPlan_Flarea(double plan_Flarea) {
		this.plan_Flarea = plan_Flarea;
		this.settings.put("plan_Flarea", plan_Flarea);
	}

	@DBMeta(column = "area", name = "实测面积", type = "double")
	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
		this.settings.put("area", area);
	}

	@DBMeta(column = "cellar_Area", name = "地下面积", type = "double")
	public double getCellar_Area() {
		return cellar_Area;
	}

	public void setCellar_Area(double cellar_Area) {
		this.cellar_Area = cellar_Area;
		this.settings.put("cellar_Area", cellar_Area);
	}

	@DBMeta(column = "ex_Area", name = "其他面积", type = "double")
	public double getEx_Area() {
		return ex_Area;
	}

	public void setEx_Area(double ex_Area) {
		this.ex_Area = ex_Area;
		this.settings.put("ex_Area", ex_Area);
	}

	@DBMeta(column = "building_Type", name = "建筑类型", type = "String")
	public String getBuilding_Type() {
		return building_Type;
	}

	public void setBuilding_Type(String building_Type) {
		this.building_Type = building_Type;
		this.settings.put("building_Type", building_Type);
	}

	@DBMeta(column = "building_Mater", name = "建筑结构", type = "String")
	public String getBuilding_Mater() {
		return building_Mater;
	}

	public void setBuilding_Mater(String building_Mater) {
		this.building_Mater = building_Mater;
		this.settings.put("building_Mater", building_Mater);
	}

	@DBMeta(column = "complete_Date", name = "竣工日期", type = "String")
	public String getComplete_Date() {
		return complete_Date;
	}

	public void setComplete_Date(String complete_Date) {
		this.complete_Date = complete_Date;
		this.settings.put("complete_Date", complete_Date);
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

	@DBMeta(column = "edit_Date", name = "修改日期", type = "Date")
	public Date getEdit_Date() {
		return edit_Date;
	}

	public void setEdit_Date(Date edit_Date) {
		this.edit_Date = edit_Date;
		this.settings.put("edit_Date", edit_Date);
	}

	@DBMeta(column = "districtID", name = "区县编号", type = "String")
	public String getDistrictID() {
		return districtID;
	}

	public void setDistrictID(String districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}

	@DBMeta(column = "plan_Ex_Flarea", name = "预测其他面积", type = "double")
	public double getPlan_Ex_Flarea() {
		return plan_Ex_Flarea;
	}

	public void setPlan_Ex_Flarea(double plan_Ex_Flarea) {
		this.plan_Ex_Flarea = plan_Ex_Flarea;
		this.settings.put("plan_Ex_Flarea", plan_Ex_Flarea);
	}

	@DBMeta(column = "plan_Cellar_Area", name = "预测地下面积", type = "double")
	public double getPlan_Cellar_Area() {
		return plan_Cellar_Area;
	}

	public void setPlan_Cellar_Area(double plan_Cellar_Area) {
		this.plan_Cellar_Area = plan_Cellar_Area;
		this.settings.put("plan_Cellar_Area", plan_Cellar_Area);
	}

	@DBMeta(column = "house_Use", name = "房屋用途", type = "String")
	public String getHouse_Use() {
		return house_Use;
	}

	public void setHouse_Use(String house_Use) {
		this.house_Use = house_Use;
		this.settings.put("house_Use", house_Use);
	}

	@DBMeta(column = "end_Date", name = "结束日期", type = "Date")
	public Date getEnd_Date() {
		return end_Date;
	}

	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
		this.settings.put("end_Date", end_Date);
	}

	@DBMeta(column = "location_Name", name = "逻辑幢坐落", type = "String")
	public String getLocation_Name() {
		return location_Name;
	}

	public void setLocation_Name(String location_Name) {
		this.location_Name = location_Name;
		this.settings.put("location_Name", location_Name);
	}

	@DBMeta(column = "survey_ID", name = "楼盘对应ID", type = "long")
	public long getSurvey_ID() {
		return survey_ID;
	}

	public void setSurvey_ID(long survey_ID) {
		this.survey_ID = survey_ID;
		this.settings.put("survey_ID", survey_ID);
	}

	@DBMeta(column = "building_Number", name = "楼盘号", type = "String")
	public String getBuilding_Number() {
		return building_Number;
	}


	public void setBuilding_Number(String building_Number) {
		this.building_Number = building_Number;
		this.settings.put("building_Number", building_Number);
	}

	@DBMeta(column = "floors", name = "地上层数", type = "String")
	public String getFloors() {
		return floors;
	}


	public void setFloors(String floors) {
		this.floors = floors;
		this.settings.put("floors", floors);
	}

	@DBMeta(column = "cellarFloors", name = "地下层数", type = "String")
	public String getCellarFloors() {
		return cellarFloors;
	}


	public void setCellarFloors(String cellarFloors) {
		this.cellarFloors = cellarFloors;
		this.settings.put("cellarFloors", cellarFloors);
	}


}
