package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "PRESELL_MANAGE_CONTRACT_CANCEL", sequence = "", id = DBModel.AssignedID)
public class PresellManageContractCancelVO extends AbstractBaseVO {

	private long contractID; // 合同编号 
	private String presellID; // 预售许可证 
	private String sellerName; // 房地产商名称
	private String manageAcctName; // 监管帐号对应的户名
	private String manageBankName; // 监管帐号的开户行 
	private String manageAcct; // 监管帐号
	private String createTime; // 插入时间
	private String cancelTime; // 撤销时间

	@DBMeta(column = "CONTRACT_ID", name = "合同编号", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "PRESELL_ID", name = "预售许可证", type = "String")
	public String getPresellID() {
		return presellID;
	}

	public void setPresellID(String presellID) {
		this.presellID = presellID;
		this.settings.put("presellID", presellID);
	}

	@DBMeta(column = "SELLER_NAME", name = "房地产商名称", type = "String")
	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
		this.settings.put("sellerName", sellerName);
	}

	@DBMeta(column = "MANAGE_ACCT_NAME", name = "监管帐号对应的户名", type = "String")
	public String getManageAcctName() {
		return manageAcctName;
	}

	public void setManageAcctName(String manageAcctName) {
		this.manageAcctName = manageAcctName;
		this.settings.put("manageAcctName", manageAcctName);
	}

	@DBMeta(column = "MANAGE_BANK_NAME", name = "监管帐号的开户行", type = "String")
	public String getManageBankName() {
		return manageBankName;
	}

	public void setManageBankName(String manageBankName) {
		this.manageBankName = manageBankName;
		this.settings.put("manageBankName", manageBankName);
	}

	@DBMeta(column = "MANAGE_ACCT", name = "监管帐号", type = "String")
	public String getManageAcct() {
		return manageAcct;
	}

	public void setManageAcct(String manageAcct) {
		this.manageAcct = manageAcct;
		this.settings.put("manageAcct", manageAcct);
	}

	@DBMeta(column = "CREATE_TIME", name = "插入时间", type = "String")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
		this.settings.put("createTime", createTime);
	}

	@DBMeta(column = "CANCEL_TIME", name = "撤销时间", type = "String")
	public String getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
		this.settings.put("cancelTime", cancelTime);
	}

}
