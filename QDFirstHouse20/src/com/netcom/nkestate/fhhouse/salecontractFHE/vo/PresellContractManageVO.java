package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "PRESELL_CONTRACT_MANAGE", sequence = "", id = DBModel.AssignedID)
public class PresellContractManageVO extends AbstractBaseVO {

	private long contract_ID;//合同编号
	private String presell_Code;//预售许可证
	private int state;//预售监管状态
	private String seller_Name;//房地产商名称
	private String manage_Acct_Name;//监管帐号对应的户名
	private String manage_Bank_Name;//监管帐号的开户行
	private String manage_Acct;//监管帐号
	private String manage_No;//监管协议号
	private String manage_Start_Time;//监管开始时间
	private String manage_Create_Time;//监管生成时间
	private String manage_End_Time;//监管结束时间
	private String create_Time;//插入时间
	private String update_Time;//撤销时间

	@DBMeta(column = "CONTRACT_ID", name = "合同编号", type = "long", primarykey = "true", can_update = "false")
	public long getContract_ID() {
		return contract_ID;
	}

	public void setContract_ID(long contract_ID) {
		this.contract_ID = contract_ID;
		this.settings.put("contract_ID", contract_ID);
	}

	@DBMeta(column = "PRESELL_CODE", name = "预售许可证", type = "String")
	public String getPresell_Code() {
		return presell_Code;
	}

	public void setPresell_Code(String presell_Code) {
		this.presell_Code = presell_Code;
		this.settings.put("presell_Code", presell_Code);
	}

	@DBMeta(column = "STATE", name = "预售监管状态", type = "int")
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		this.settings.put("state", state);
	}

	@DBMeta(column = "SELLER_NAME", name = "房地产商名称", type = "String")
	public String getSeller_Name() {
		return seller_Name;
	}

	public void setSeller_Name(String seller_Name) {
		this.seller_Name = seller_Name;
		this.settings.put("seller_Name", seller_Name);
	}

	@DBMeta(column = "MANAGE_ACCT_NAME", name = "监管帐号对应的户名", type = "String")
	public String getManage_Acct_Name() {
		return manage_Acct_Name;
	}

	public void setManage_Acct_Name(String manage_Acct_Name) {
		this.manage_Acct_Name = manage_Acct_Name;
		this.settings.put("manage_Acct_Name", manage_Acct_Name);
	}

	@DBMeta(column = "MANAGE_BANK_NAME", name = "监管帐号的开户行", type = "String")
	public String getManage_Bank_Name() {
		return manage_Bank_Name;
	}

	public void setManage_Bank_Name(String manage_Bank_Name) {
		this.manage_Bank_Name = manage_Bank_Name;
		this.settings.put("manage_Bank_Name", manage_Bank_Name);
	}

	@DBMeta(column = "MANAGE_ACCT", name = "监管帐号", type = "String")
	public String getManage_Acct() {
		return manage_Acct;
	}

	public void setManage_Acct(String manage_Acct) {
		this.manage_Acct = manage_Acct;
		this.settings.put("manage_Acct", manage_Acct);
	}

	@DBMeta(column = "MANAGE_NO", name = "监管协议号", type = "String")
	public String getManage_No() {
		return manage_No;
	}

	public void setManage_No(String manage_No) {
		this.manage_No = manage_No;
		this.settings.put("manage_No", manage_No);
	}

	@DBMeta(column = "MANAGE_START_TIME", name = "监管开始时间", type = "String")
	public String getManage_Start_Time() {
		return manage_Start_Time;
	}

	public void setManage_Start_Time(String manage_Start_Time) {
		this.manage_Start_Time = manage_Start_Time;
		this.settings.put("manage_Start_Time", manage_Start_Time);
	}

	@DBMeta(column = "MANAGE_CREATE_TIME", name = "监管生成时间", type = "String")
	public String getManage_Create_Time() {
		return manage_Create_Time;
	}

	public void setManage_Create_Time(String manage_Create_Time) {
		this.manage_Create_Time = manage_Create_Time;
		this.settings.put("manage_Create_Time", manage_Create_Time);
	}

	@DBMeta(column = "MANAGE_END_TIME", name = "监管结束时间", type = "String")
	public String getManage_End_Time() {
		return manage_End_Time;
	}

	public void setManage_End_Time(String manage_End_Time) {
		this.manage_End_Time = manage_End_Time;
		this.settings.put("manage_End_Time", manage_End_Time);
	}

	@DBMeta(column = "CREATE_TIME", name = "插入时间", type = "String")
	public String getCreate_Time() {
		return create_Time;
	}

	public void setCreate_Time(String create_Time) {
		this.create_Time = create_Time;
		this.settings.put("create_Time", create_Time);
	}

	@DBMeta(column = "UPDATE_TIME", name = "撤销时间", type = "String")
	public String getUpdate_Time() {
		return update_Time;
	}

	public void setUpdate_Time(String update_Time) {
		this.update_Time = update_Time;
		this.settings.put("update_Time", update_Time);
	}

}
