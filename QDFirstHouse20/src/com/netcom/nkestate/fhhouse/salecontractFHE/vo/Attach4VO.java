package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "ATTACH4", sequence = "", id = DBModel.AssignedID)
public class Attach4VO extends FHVO {

	private long contractID; //  	合同ID
	private String doorNum; //  	幢号
	private String part; //  	部位
	private String buildingArea; //  	建筑面积
	private String totalFloors; //  	层数
	private String houseType; //  	房屋类型
	private String houseArch; //  	房屋结构
	private String sourceID; //  	产权来源
	private String finishedDate; //  	竣工日期
	private String lotCode; //  	地号
	private String allDate; //  	使用期限
	private String landSource; //  	使用权来源
	private String permitUsage; //  	批准用途
	private String blockArea; //  	共用面积
	private String landArea; //  	总面积

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "DOORNUM", name = "幢号", type = "String")
	public String getDoorNum() {
		return doorNum;
	}

	public void setDoorNum(String doorNum) {
		this.doorNum = doorNum;
		this.settings.put("doorNum", doorNum);
	}

	@DBMeta(column = "PART", name = "部位", type = "String")
	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
		this.settings.put("part", part);
	}

	@DBMeta(column = "BUILDINGAREA", name = "建筑面积", type = "String")
	public String getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(String buildingArea) {
		this.buildingArea = buildingArea;
		this.settings.put("buildingArea", buildingArea);
	}

	@DBMeta(column = "TOTALFLOORS", name = "层数", type = "String")
	public String getTotalFloors() {
		return totalFloors;
	}

	public void setTotalFloors(String totalFloors) {
		this.totalFloors = totalFloors;
		this.settings.put("totalFloors", totalFloors);
	}

	@DBMeta(column = "HOUSETYPE", name = "房屋类型", type = "String")
	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
		this.settings.put("houseType", houseType);
	}

	@DBMeta(column = "HOUSEARCH", name = "房屋结构", type = "String")
	public String getHouseArch() {
		return houseArch;
	}

	public void setHouseArch(String houseArch) {
		this.houseArch = houseArch;
		this.settings.put("houseArch", houseArch);
	}

	@DBMeta(column = "SOURCEID", name = "产权来源", type = "String")
	public String getSourceID() {
		return sourceID;
	}

	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
		this.settings.put("sourceID", sourceID);
	}

	@DBMeta(column = "FINISHEDDATE", name = "竣工日期", type = "String")
	public String getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(String finishedDate) {
		this.finishedDate = finishedDate;
		this.settings.put("finishedDate", finishedDate);
	}

	@DBMeta(column = "LOTCODE", name = "地号", type = "String")
	public String getLotCode() {
		return lotCode;
	}

	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
		this.settings.put("lotCode", lotCode);
	}

	@DBMeta(column = "ALLDATE", name = "使用期限", type = "String")
	public String getAllDate() {
		return allDate;
	}

	public void setAllDate(String allDate) {
		this.allDate = allDate;
		this.settings.put("allDate", allDate);
	}

	@DBMeta(column = "LANDSOURCE", name = "使用权来源", type = "String")
	public String getLandSource() {
		return landSource;
	}

	public void setLandSource(String landSource) {
		this.landSource = landSource;
		this.settings.put("landSource", landSource);
	}

	@DBMeta(column = "PERMITUSAGE", name = "批准用途", type = "String")
	public String getPermitUsage() {
		return permitUsage;
	}

	public void setPermitUsage(String permitUsage) {
		this.permitUsage = permitUsage;
		this.settings.put("permitUsage", permitUsage);
	}

	@DBMeta(column = "BLOCKAREA", name = "共用面积", type = "String")
	public String getBlockArea() {
		return blockArea;
	}

	public void setBlockArea(String blockArea) {
		this.blockArea = blockArea;
		this.settings.put("blockArea", blockArea);
	}

	@DBMeta(column = "LANDAREA", name = "总面积", type = "String")
	public String getLandArea() {
		return landArea;
	}

	public void setLandArea(String landArea) {
		this.landArea = landArea;
		this.settings.put("landArea", landArea);
	}

}
