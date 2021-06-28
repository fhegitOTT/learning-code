package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "TQ_PRECERTHOUSE_T", sequence = "", id = DBModel.AssignedID)
public class TqPrecerthouseTVO extends FHVO {

	private long contractID; //  	合同ID
	private int projectID; //  	项目ID
	private long presellID; //  	预售许可证/大产证ID
	private long buildingID; //  	楼栋ID
	private long houseID; //  	房屋ID
	private int totalFloors; //  	地上层数(大机)
	private int floorNumber; //  	层次(大机)
	private double bargainval; //  	金额
	private double buildingArea; //  	建筑面积
	private String houseArch; //  	结构
	private long passDate; //  	交易时间(插入时间)
	private long cancelDate; //  	撤销时间
	private String streetCode; //  	街道(大机)
	private String permitusage; //  	用途
	private String projectName; //  	楼盘名称
	private String projectLocation; //  	地址
	private int status; //  	状态		状态（1合同成交，0合同撤销）

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "PROJECT_ID", name = "项目ID", type = "int")
	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
		this.settings.put("projectID", projectID);
	}

	@DBMeta(column = "PRESELL_ID", name = "预售许可证/大产证ID", type = "long")
	public long getPresellID() {
		return presellID;
	}

	public void setPresellID(long presellID) {
		this.presellID = presellID;
		this.settings.put("presellID", presellID);
	}

	@DBMeta(column = "BUILDING_ID", name = "楼栋ID", type = "long")
	public long getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(long buildingID) {
		this.buildingID = buildingID;
		this.settings.put("buildingID", buildingID);
	}

	@DBMeta(column = "HOUSE_ID", name = "房屋ID", type = "long")
	public long getHouseID() {
		return houseID;
	}

	public void setHouseID(long houseID) {
		this.houseID = houseID;
		this.settings.put("houseID", houseID);
	}

	@DBMeta(column = "TOTALFLOORS", name = "地上层数", type = "int")
	public int getTotalFloors() {
		return totalFloors;
	}

	public void setTotalFloors(int totalFloors) {
		this.totalFloors = totalFloors;
		this.settings.put("totalFloors", totalFloors);
	}

	@DBMeta(column = "FLOORNUMBER", name = "层次", type = "int")
	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
		this.settings.put("floorNumber", floorNumber);
	}

	@DBMeta(column = "BARGAINVAL", name = "金额", type = "double")
	public double getBargainval() {
		return bargainval;
	}

	public void setBargainval(double bargainval) {
		this.bargainval = bargainval;
		this.settings.put("bargainval", bargainval);
	}

	@DBMeta(column = "BUILDINGAREA", name = "建筑面积", type = "double")
	public double getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(double buildingArea) {
		this.buildingArea = buildingArea;
		this.settings.put("buildingArea", buildingArea);
	}

	@DBMeta(column = "HOUSEARCH", name = "结构", type = "String")
	public String getHouseArch() {
		return houseArch;
	}

	public void setHouseArch(String houseArch) {
		this.houseArch = houseArch;
		this.settings.put("houseArch", houseArch);
	}

	@DBMeta(column = "PASSDATE", name = "交易时间(插入时间)", type = "long")
	public long getPassDate() {
		return passDate;
	}

	public void setPassDate(long passDate) {
		this.passDate = passDate;
		this.settings.put("passDate", passDate);
	}

	@DBMeta(column = "CANCELDATE", name = "撤销时间", type = "long")
	public long getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(long cancelDate) {
		this.cancelDate = cancelDate;
		this.settings.put("cancelDate", cancelDate);
	}

	@DBMeta(column = "STREETCODE", name = "街道", type = "String")
	public String getStreetCode() {
		return streetCode;
	}

	public void setStreetCode(String streetCode) {
		this.streetCode = streetCode;
		this.settings.put("streetCode", streetCode);
	}

	@DBMeta(column = "PERMITUSAGE", name = "用途", type = "String")
	public String getPermitusage() {
		return permitusage;
	}

	public void setPermitusage(String permitusage) {
		this.permitusage = permitusage;
		this.settings.put("permitusage", permitusage);
	}

	@DBMeta(column = "PROJECTNAME", name = "楼盘名称", type = "String")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
		this.settings.put("projectName", projectName);
	}

	@DBMeta(column = "PROJECTLOCATION", name = "地址", type = "String")
	public String getProjectLocation() {
		return projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
		this.settings.put("projectLocation", projectLocation);
	}

	@DBMeta(column = "STATUS", name = "状态", type = "int")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}

}
