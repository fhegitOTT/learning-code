/**
 * <p>NatureBuildingVO.java </p>
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

@DBModel(tablename = "COMMON.CH_NatureBuilding", sequence = "", id = DBModel.AssignedID)
public class CHNatureBuildingVO extends AbstractBaseVO {
	protected long natureID; // 自然幢编号（序列SEQ_CH_NBUILDING）
	protected long lotID; // 地块编号
	protected int nature_Number; // 自然幢号
	protected String building_Sign; // 自然幢标识（暂时保留，不用，序列SEQ_CH_NBUILDING_DUMMYSIGN）
	protected String building_Name; // 建筑物名称（项目幢（自然幢信息-建筑物名称）、基础测绘（幢编辑-标注幢名称））
	protected String location_Name; // 自然幢坐落（房产测绘-幢座落）
	protected String complete_Date; // 竣工日期
	protected double height; // 建筑物高度
	protected double building_Area; // 幢占地面积(房屋基底面积)
	protected double co_Flarea; // 幢用地分摊面积(=使用权面积=独用面积+分摊面积)
	protected double plan_Flarea; // 预测建筑面积
	protected double flarea; // 实测建筑面积
	protected double floors; // 地上层数
	protected double cellar_Floors; // 地下层数
	protected double depth; // 地下深度
	protected String survey_State; // 测绘状态(？？CT_SURVEY_STATE)
	protected Date edit_Date; // 修改日期
	protected Date created_Date; // 创建日期
	protected String state; // 处理状态(1-正常;2-锁定)
	protected Date state_Date; // 处理状态日期
	protected long version_ID; // 版本号
	protected String housingBasicUse; // 建筑物基本用途
	protected String note; // 备注
	protected String districtID; // 区县编号
	protected String x; // X坐标
	protected String y; // Y坐标
	protected String total_Floors; // 总层数
	protected int objectID; // 图形ID
	protected String pre_Location_Name; // 预测坐落
	protected Date end_Date; // 结束时间
	protected double balcony_Close_Area; // 封闭阳台面积（全算）
	protected double balcony_Open_Area; // 未封闭阳台面积（半算）
	protected int balcony_Close_Num; // 封闭阳台个数
	protected int balcony_Open_Num; // 未封闭阳台个数
	protected double plaster_Area; // 抹灰层面积
	protected String space_Location; // 空间位置
	protected String building_State; // 建筑状态
	protected String project_Location; // 项目坐落
	protected String lot_Number; // 宗地号
	protected String floorHeight_Note; // 层高描述
	protected String dwg_Paths; // CAD文件路径
	protected double be_Apportion; // 应分摊
	protected double no_Apportion; // 不分摊
	protected long project_ID; // 项目编号
	protected double onGround; // 地上面积
	protected double basement; // 地下面积
	protected String estate_Code; // 不动产登记代码
	protected String building_Mater; // 建筑结构
	protected double landco_Ratial; // 土地分摊系数
	protected int fz_Flag; // 项目内多幢房屋标志
	protected long plan_ID; // 自然幢宗地图

	@DBMeta(column = "natureID", name = "自然幢编号", type = "long")
	public long getNatureID() {
		return natureID;
	}

	public void setNatureID(long natureID) {
		this.natureID = natureID;
		this.settings.put("natureID", natureID);
	}

	@DBMeta(column = "lotID", name = "地块编号", type = "long")
	public long getLotID() {
		return lotID;
	}

	public void setLotID(long lotID) {
		this.lotID = lotID;
		this.settings.put("lotID", lotID);
	}

	@DBMeta(column = "nature_Number", name = "自然幢号", type = "int")
	public int getNature_Number() {
		return nature_Number;
	}

	public void setNature_Number(int nature_Number) {
		this.nature_Number = nature_Number;
		this.settings.put("nature_Number", nature_Number);
	}

	@DBMeta(column = "building_Sign", name = "自然幢标识", type = "String")
	public String getBuilding_Sign() {
		return building_Sign;
	}

	public void setBuilding_Sign(String building_Sign) {
		this.building_Sign = building_Sign;
		this.settings.put("building_Sign", building_Sign);
	}

	@DBMeta(column = "building_Name", name = "建筑物名称", type = "String")
	public String getBuilding_Name() {
		return building_Name;
	}

	public void setBuilding_Name(String building_Name) {
		this.building_Name = building_Name;
		this.settings.put("building_Name", building_Name);
	}

	@DBMeta(column = "location_Name", name = "自然幢坐落", type = "String")
	public String getLocation_Name() {
		return location_Name;
	}

	public void setLocation_Name(String location_Name) {
		this.location_Name = location_Name;
		this.settings.put("location_Name", location_Name);
	}

	@DBMeta(column = "complete_Date", name = "竣工日期", type = "String")
	public String getComplete_Date() {
		return complete_Date;
	}

	public void setComplete_Date(String complete_Date) {
		this.complete_Date = complete_Date;
		this.settings.put("complete_Date", complete_Date);
	}

	@DBMeta(column = "height", name = "建筑物高度", type = "double")
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
		this.settings.put("height", height);
	}

	@DBMeta(column = "building_Area", name = "幢占地面积", type = "double")
	public double getBuilding_Area() {
		return building_Area;
	}

	public void setBuilding_Area(double building_Area) {
		this.building_Area = building_Area;
		this.settings.put("building_Area", building_Area);
	}

	@DBMeta(column = "co_Flarea", name = "幢用地分摊面积", type = "double")
	public double getCo_Flarea() {
		return co_Flarea;
	}

	public void setCo_Flarea(double co_Flarea) {
		this.co_Flarea = co_Flarea;
		this.settings.put("co_Flarea", co_Flarea);
	}

	@DBMeta(column = "plan_Flarea", name = "预测建筑面积", type = "double")
	public double getPlan_Flarea() {
		return plan_Flarea;
	}

	public void setPlan_Flarea(double plan_Flarea) {
		this.plan_Flarea = plan_Flarea;
		this.settings.put("plan_Flarea", plan_Flarea);
	}

	@DBMeta(column = "flarea", name = "实测建筑面积", type = "double")
	public double getFlarea() {
		return flarea;
	}

	public void setFlarea(double flarea) {
		this.flarea = flarea;
		this.settings.put("flarea", flarea);
	}

	@DBMeta(column = "floors", name = "地上层数", type = "double")
	public double getFloors() {
		return floors;
	}

	public void setFloors(double floors) {
		this.floors = floors;
		this.settings.put("floors", floors);
	}

	@DBMeta(column = "cellar_Floors", name = "地下层数", type = "double")
	public double getCellar_Floors() {
		return cellar_Floors;
	}

	public void setCellar_Floors(double cellar_Floors) {
		this.cellar_Floors = cellar_Floors;
		this.settings.put("cellar_Floors", cellar_Floors);
	}

	@DBMeta(column = "depth", name = "地下深度", type = "double")
	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
		this.settings.put("depth", depth);
	}

	@DBMeta(column = "survey_State", name = "测绘状态", type = "String")
	public String getSurvey_State() {
		return survey_State;
	}

	public void setSurvey_State(String survey_State) {
		this.survey_State = survey_State;
		this.settings.put("survey_State", survey_State);
	}

	@DBMeta(column = "edit_Date", name = "修改日期", type = "Date")
	public Date getEdit_Date() {
		return edit_Date;
	}

	public void setEdit_Date(Date edit_Date) {
		this.edit_Date = edit_Date;
		this.settings.put("edit_Date", edit_Date);
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

	@DBMeta(column = "housingBasicUse", name = "建筑物基本用途", type = "String")
	public String getHousingBasicUse() {
		return housingBasicUse;
	}

	public void setHousingBasicUse(String housingBasicUse) {
		this.housingBasicUse = housingBasicUse;
		this.settings.put("housingBasicUse", housingBasicUse);
	}

	@DBMeta(column = "note", name = "备注", type = "String")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
		this.settings.put("note", note);
	}

	@DBMeta(column = "districtID", name = "区县编号", type = "String")
	public String getDistrictID() {
		return districtID;
	}

	public void setDistrictID(String districtID) {
		this.districtID = districtID;
		this.settings.put("districtID", districtID);
	}

	@DBMeta(column = "x", name = "X坐标", type = "String")
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
		this.settings.put("x", x);
	}

	@DBMeta(column = "y", name = "Y坐标", type = "String")
	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
		this.settings.put("y", y);
	}

	@DBMeta(column = "total_Floors", name = "总层数", type = "String")
	public String getTotal_Floors() {
		return total_Floors;
	}

	public void setTotal_Floors(String total_Floors) {
		this.total_Floors = total_Floors;
		this.settings.put("total_Floors", total_Floors);
	}

	@DBMeta(column = "objectID", name = "图形ID", type = "int")
	public int getObjectID() {
		return objectID;
	}

	public void setObjectID(int objectID) {
		this.objectID = objectID;
		this.settings.put("objectID", objectID);
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

	@DBMeta(column = "balcony_Close_Area", name = "封闭阳台面积", type = "double")
	public double getBalcony_Close_Area() {
		return balcony_Close_Area;
	}

	public void setBalcony_Close_Area(double balcony_Close_Area) {
		this.balcony_Close_Area = balcony_Close_Area;
		this.settings.put("balcony_Close_Area", balcony_Close_Area);
	}

	@DBMeta(column = "balcony_Open_Area", name = "未封闭阳台面积", type = "double")
	public double getBalcony_Open_Area() {
		return balcony_Open_Area;
	}

	public void setBalcony_Open_Area(double balcony_Open_Area) {
		this.balcony_Open_Area = balcony_Open_Area;
		this.settings.put("balcony_Open_Area", balcony_Open_Area);
	}

	@DBMeta(column = "balcony_Close_Num", name = "封闭阳台个数", type = "int")
	public int getBalcony_Close_Num() {
		return balcony_Close_Num;
	}

	public void setBalcony_Close_Num(int balcony_Close_Num) {
		this.balcony_Close_Num = balcony_Close_Num;
		this.settings.put("balcony_Close_Num", balcony_Close_Num);
	}

	@DBMeta(column = "balcony_Open_Num", name = "未封闭阳台个数", type = "int")
	public int getBalcony_Open_Num() {
		return balcony_Open_Num;
	}

	public void setBalcony_Open_Num(int balcony_Open_Num) {
		this.balcony_Open_Num = balcony_Open_Num;
		this.settings.put("balcony_Open_Num", balcony_Open_Num);
	}

	@DBMeta(column = "plaster_Area", name = "抹灰层面积", type = "double")
	public double getPlaster_Area() {
		return plaster_Area;
	}

	public void setPlaster_Area(double plaster_Area) {
		this.plaster_Area = plaster_Area;
		this.settings.put("plaster_Area", plaster_Area);
	}

	@DBMeta(column = "space_Location", name = "空间位置", type = "String")
	public String getSpace_Location() {
		return space_Location;
	}

	public void setSpace_Location(String space_Location) {
		this.space_Location = space_Location;
		this.settings.put("space_Location", space_Location);
	}

	@DBMeta(column = "building_State", name = "建筑状态", type = "String")
	public String getBuilding_State() {
		return building_State;
	}

	public void setBuilding_State(String building_State) {
		this.building_State = building_State;
		this.settings.put("building_State", building_State);
	}

	@DBMeta(column = "project_Location", name = "项目坐落", type = "String")
	public String getProject_Location() {
		return project_Location;
	}

	public void setProject_Location(String project_Location) {
		this.project_Location = project_Location;
		this.settings.put("project_Location", project_Location);
	}

	@DBMeta(column = "lot_Number", name = "宗地号", type = "String")
	public String getLot_Number() {
		return lot_Number;
	}

	public void setLot_Number(String lot_Number) {
		this.lot_Number = lot_Number;
		this.settings.put("lot_Number", lot_Number);
	}

	@DBMeta(column = "floorHeight_Note", name = "层高描述", type = "String")
	public String getFloorHeight_Note() {
		return floorHeight_Note;
	}

	public void setFloorHeight_Note(String floorHeight_Note) {
		this.floorHeight_Note = floorHeight_Note;
		this.settings.put("floorHeight_Note", floorHeight_Note);
	}

	@DBMeta(column = "dwg_Paths", name = "CAD文件路径", type = "String")
	public String getDwg_Paths() {
		return dwg_Paths;
	}

	public void setDwg_Paths(String dwg_Paths) {
		this.dwg_Paths = dwg_Paths;
		this.settings.put("dwg_Paths", dwg_Paths);
	}

	@DBMeta(column = "be_Apportion", name = "应分摊", type = "double")
	public double getBe_Apportion() {
		return be_Apportion;
	}

	public void setBe_Apportion(double be_Apportion) {
		this.be_Apportion = be_Apportion;
		this.settings.put("be_Apportion", be_Apportion);
	}

	@DBMeta(column = "no_Apportion", name = "不分摊", type = "double")
	public double getNo_Apportion() {
		return no_Apportion;
	}

	public void setNo_Apportion(double no_Apportion) {
		this.no_Apportion = no_Apportion;
		this.settings.put("no_Apportion", no_Apportion);
	}

	@DBMeta(column = "project_ID", name = "项目编号", type = "long")
	public long getProject_ID() {
		return project_ID;
	}

	public void setProject_ID(long project_ID) {
		this.project_ID = project_ID;
		this.settings.put("project_ID", project_ID);
	}

	@DBMeta(column = "onGround", name = "地上面积", type = "double")
	public double getOnGround() {
		return onGround;
	}

	public void setOnGround(double onGround) {
		this.onGround = onGround;
		this.settings.put("onGround", onGround);
	}

	@DBMeta(column = "basement", name = "地下面积", type = "double")
	public double getBasement() {
		return basement;
	}

	public void setBasement(double basement) {
		this.basement = basement;
		this.settings.put("basement", basement);
	}

	@DBMeta(column = "estate_Code", name = "不动产登记代码", type = "String")
	public String getEstate_Code() {
		return estate_Code;
	}

	public void setEstate_Code(String estate_Code) {
		this.estate_Code = estate_Code;
		this.settings.put("estate_Code", estate_Code);
	}

	@DBMeta(column = "building_Mater", name = "建筑结构", type = "String")
	public String getBuilding_Mater() {
		return building_Mater;
	}

	public void setBuilding_Mater(String building_Mater) {
		this.building_Mater = building_Mater;
		this.settings.put("building_Mater", building_Mater);
	}

	@DBMeta(column = "landco_Ratial", name = "土地分摊系数", type = "double")
	public double getLandco_Ratial() {
		return landco_Ratial;
	}

	public void setLandco_Ratial(double landco_Ratial) {
		this.landco_Ratial = landco_Ratial;
		this.settings.put("landco_Ratial", landco_Ratial);
	}

	@DBMeta(column = "fz_Flag", name = "项目内多幢房屋标志", type = "int")
	public int getFz_Flag() {
		return fz_Flag;
	}

	public void setFz_Flag(int fz_Flag) {
		this.fz_Flag = fz_Flag;
		this.settings.put("fz_Flag", fz_Flag);
	}

	@DBMeta(column = "plan_ID", name = "自然幢宗地图", type = "long")
	public long getPlan_ID() {
		return plan_ID;
	}

	public void setPlan_ID(long plan_ID) {
		this.plan_ID = plan_ID;
		this.settings.put("plan_ID", plan_ID);
	}

}
