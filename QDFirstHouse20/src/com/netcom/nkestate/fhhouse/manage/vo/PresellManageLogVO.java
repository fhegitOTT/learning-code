package com.netcom.nkestate.fhhouse.manage.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;


public class PresellManageLogVO extends AbstractBaseVO {

	private long presell_Manage_Log_ID; // 	预售监管日志ID
	private String building_ID; // 	楼栋ID
	private String presell_Code; // 	预售许可证
	private int state; // 	预售监管状态
	private String seller_Name; // 	房地产商名称
	private String manage_Acct_Name; // 	监管帐号对应的户名
	private String manage_Bank_Name; // 	监管帐号的开户行
	private String manage_Acct; // 	监管帐号
	private String manage_No; // 	监管协议号
	private String manage_Start_Time; // 	监管开始时间
	private String manage_Create_Time; // 	监管生成时间
	private String manage_End_Time; // 	监管结束时间
	private String create_Time; // 	插入时间
	private String update_Time; // 	更新时间

	@DBMeta(column = "Presell_Manage_Log_ID", name = "预售监管日志ID", type = "long", primarykey = "true", can_update = "false")
	public long getPresell_Manage_Log_ID() {
		return presell_Manage_Log_ID;
	}

	public void setPresell_Manage_Log_ID(long Presell_Manage_Log_ID) {
		this.presell_Manage_Log_ID = Presell_Manage_Log_ID;
		this.settings.put("presell_Manage_Log_ID", presell_Manage_Log_ID);
	}

	@DBMeta(column = "Building_ID", name = "楼栋ID", type = "String")
	public String getBuilding_ID() {
		return building_ID;
	}

	public void setBuilding_ID(String Building_ID) {
		this.building_ID = Building_ID;
		this.settings.put("building_ID", building_ID);
	}

	@DBMeta(column = "Presell_Code", name = "预售许可证", type = "String")
	public String getPresell_Code() {
		return presell_Code;
	}

	public void setPresell_Code(String Presell_Code) {
		this.presell_Code = Presell_Code;
		this.settings.put("presell_Code", presell_Code);
	}

	@DBMeta(column = "State", name = "预售监管状态", type = "int")
	public int getState() {
		return state;
	}

	public void setState(int State) {
		this.state = State;
		this.settings.put("state", state);
	}

	@DBMeta(column = "Seller_Name", name = "房地产商名称", type = "String")
	public String getSeller_Name() {
		return seller_Name;
	}

	public void setSeller_Name(String Seller_Name) {
		this.seller_Name = Seller_Name;
		this.settings.put("seller_Name", seller_Name);
	}

	@DBMeta(column = "Manage_Acct_Name", name = "监管帐号对应的户名", type = "String")
	public String getManage_Acct_Name() {
		return manage_Acct_Name;
	}

	public void setManage_Acct_Name(String Manage_Acct_Name) {
		this.manage_Acct_Name = Manage_Acct_Name;
		this.settings.put("manage_Acct_Name", manage_Acct_Name);
	}

	@DBMeta(column = "Manage_Bank_Name", name = "监管帐号的开户行", type = "String")
	public String getManage_Bank_Name() {
		return manage_Bank_Name;
	}

	public void setManage_Bank_Name(String Manage_Bank_Name) {
		this.manage_Bank_Name = Manage_Bank_Name;
		this.settings.put("manage_Bank_Name", manage_Bank_Name);
	}

	@DBMeta(column = "Manage_Acct", name = "监管帐号", type = "String")
	public String getManage_Acct() {
		return manage_Acct;
	}

	public void setManage_Acct(String Manage_Acct) {
		this.manage_Acct = Manage_Acct;
		this.settings.put("manage_Acct", manage_Acct);
	}

	@DBMeta(column = "Manage_No", name = "监管协议号", type = "String")
	public String getManage_No() {
		return manage_No;
	}

	public void setManage_No(String Manage_No) {
		this.manage_No = Manage_No;
		this.settings.put("manage_No", manage_No);
	}

	@DBMeta(column = "Manage_Start_Time", name = "监管开始时间", type = "String")
	public String getManage_Start_Time() {
		return manage_Start_Time;
	}

	public void setManage_Start_Time(String Manage_Start_Time) {
		this.manage_Start_Time = Manage_Start_Time;
		this.settings.put("manage_Start_Time", manage_Start_Time);
	}

	@DBMeta(column = "Manage_Create_Time", name = "监管生成时间", type = "String")
	public String getManage_Create_Time() {
		return manage_Create_Time;
	}

	public void setManage_Create_Time(String Manage_Create_Time) {
		this.manage_Create_Time = Manage_Create_Time;
		this.settings.put("manage_Create_Time", manage_Create_Time);
	}

	@DBMeta(column = "Manage_End_Time", name = "监管结束时间", type = "String")
	public String getManage_End_Time() {
		return manage_End_Time;
	}

	public void setManage_End_Time(String Manage_End_Time) {
		this.manage_End_Time = Manage_End_Time;
		this.settings.put("manage_End_Time", manage_End_Time);
	}

	@DBMeta(column = "Create_Time", name = "插入时间", type = "String")
	public String getCreate_Time() {
		return create_Time;
	}

	public void setCreate_Time(String Create_Time) {
		this.create_Time = Create_Time;
		this.settings.put("create_Time", create_Time);
	}

	@DBMeta(column = "Update_Time", name = "更新时间", type = "String")
	public String getUpdate_Time() {
		return update_Time;
	}

	public void setUpdate_Time(String Update_Time) {
		this.update_Time = Update_Time;
		this.settings.put("update_Time", update_Time);
	}


}
