package com.netcom.nkestate.fhhouse.manage.vo;

import java.util.Date;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "MANUAL_CONTRACT", sequence = "", id = DBModel.AssignedID)
public class ManualContractVO extends FHVO {

	private long house_ID; // 	房屋编号
	private long contract_ID; // 	合同号
	private long project_ID; // 	项目编号
	private long start_ID; // 	开盘编号
	private long presell_ID; // 	预售许可证编号
	private int status; // 	房屋状态	2:已签、5:撤销
	private double area; // 	预售面积
	private double cellar_Area; // 	地下面积
	private double total_Price; // 	总价
	private String buyer_Name; // 	乙方名称
	private Date confirmDate; // 	确认时间
	private Date last_Modi_Time; // 	最后更新时间
	private Date cancelDate; // 	撤销时间


	@DBMeta(column = "House_ID", name = "房屋编号", type = "long", primarykey = "true", can_update = "false")
	public long getHouse_ID() {
		return house_ID;
	}

	public void setHouse_ID(long House_ID) {
		this.house_ID = House_ID;
		this.settings.put("house_ID", house_ID);
	}

	@DBMeta(column = "Contract_ID", name = "合同号", type = "long")
	public long getContract_ID() {
		return contract_ID;
	}

	public void setContract_ID(long Contract_ID) {
		this.contract_ID = Contract_ID;
		this.settings.put("contract_ID", contract_ID);
	}

	@DBMeta(column = "Project_ID", name = "项目编号", type = "long")
	public long getProject_ID() {
		return project_ID;
	}

	public void setProject_ID(long Project_ID) {
		this.project_ID = Project_ID;
		this.settings.put("project_ID", project_ID);
	}

	@DBMeta(column = "Start_ID", name = "开盘编号", type = "long")
	public long getStart_ID() {
		return start_ID;
	}

	public void setStart_ID(long Start_ID) {
		this.start_ID = Start_ID;
		this.settings.put("start_ID", start_ID);
	}

	@DBMeta(column = "Presell_ID", name = "预售许可证编号", type = "long")
	public long getPresell_ID() {
		return presell_ID;
	}

	public void setPresell_ID(long Presell_ID) {
		this.presell_ID = Presell_ID;
		this.settings.put("presell_ID", presell_ID);
	}

	@DBMeta(column = "Status", name = "房屋状态", type = "int")
	public int getStatus() {
		return status;
	}

	public void setStatus(int Status) {
		this.status = Status;
		this.settings.put("status", status);
	}

	@DBMeta(column = "Area", name = "预售面积", type = "double")
	public double getArea() {
		return area;
	}

	public void setArea(double Area) {
		this.area = Area;
		this.settings.put("area", area);
	}

	@DBMeta(column = "Cellar_Area", name = "地下面积", type = "double")
	public double getCellar_Area() {
		return cellar_Area;
	}

	public void setCellar_Area(double Cellar_Area) {
		this.cellar_Area = Cellar_Area;
		this.settings.put("cellar_Area", cellar_Area);
	}

	@DBMeta(column = "Total_Price", name = "总价", type = "double")
	public double getTotal_Price() {
		return total_Price;
	}

	public void setTotal_Price(double Total_Price) {
		this.total_Price = Total_Price;
		this.settings.put("total_Price", total_Price);
	}

	@DBMeta(column = "Buyer_Name", name = "乙方名称", type = "String")
	public String getBuyer_Name() {
		return buyer_Name;
	}

	public void setBuyer_Name(String Buyer_Name) {
		this.buyer_Name = Buyer_Name;
		this.settings.put("buyer_Name", buyer_Name);
	}

	@DBMeta(column = "ConfirmDate", name = "确认时间", type = "Date")
	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date ConfirmDate) {
		this.confirmDate = ConfirmDate;
		this.settings.put("confirmDate", confirmDate);
	}

	@DBMeta(column = "Last_Modi_Time", name = "最后更新时间", type = "Date")
	public Date getLast_Modi_Time() {
		return last_Modi_Time;
	}

	public void setLast_Modi_Time(Date Last_Modi_Time) {
		this.last_Modi_Time = Last_Modi_Time;
		this.settings.put("last_Modi_Time", last_Modi_Time);
	}

	@DBMeta(column = "CancelDate", name = "撤销时间", type = "Date")
	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date CancelDate) {
		this.cancelDate = CancelDate;
		this.settings.put("cancelDate", cancelDate);
	}


}
