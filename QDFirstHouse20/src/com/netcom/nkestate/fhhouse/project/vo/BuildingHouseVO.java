package com.netcom.nkestate.fhhouse.project.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "BUILDING_HOUSE", sequence = "", id = DBModel.AssignedID)
public class BuildingHouseVO extends FHVO {

	private long house_ID; //  房屋ID
	private long start_ID; //  开盘ID
	private long building_ID; //  楼栋ID
	private double reference_Price; //  参考价格
	private double ratio; //  幅度

	@DBMeta(column = "house_ID", name = "房屋ID", type = "long", primarykey = "true", can_update = "false")
	public long getHouse_ID() {
		return house_ID;
	}

	public void setHouse_ID(long house_ID) {
		this.house_ID = house_ID;
		this.settings.put("house_ID", house_ID);
	}

	@DBMeta(column = "start_ID", name = "开盘ID", type = "long")
	public long getStart_ID() {
		return start_ID;
	}

	public void setStart_ID(long start_ID) {
		this.start_ID = start_ID;
		this.settings.put("start_ID", start_ID);
	}

	@DBMeta(column = "building_ID", name = "楼栋ID", type = "long")
	public long getBuilding_ID() {
		return building_ID;
	}

	public void setBuilding_ID(long building_ID) {
		this.building_ID = building_ID;
		this.settings.put("building_ID", building_ID);
	}

	@DBMeta(column = "reference_Price", name = "参考价格", type = "double")
	public double getReference_Price() {
		return reference_Price;
	}

	public void setReference_Price(double reference_Price) {
		this.reference_Price = reference_Price;
		this.settings.put("reference_Price", reference_Price);
	}

	@DBMeta(column = "ratio", name = "幅度", type = "double")
	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
		this.settings.put("ratio", ratio);
	}

}
