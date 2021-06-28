package com.netcom.nkestate.fhhouse.salecontractFHE.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "SELL_CONTRACT_TEMPLATE", sequence = "", id = DBModel.AssignedID)
public class SellContractTemplateVO extends AbstractBaseVO {

	private long contractID;//合同ID
	private String sellTemplateName;//模板名称
	private Date createTime;//创建时间 
	private Date updateTime;//修改时间

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "SELLTEMPLATENAME", name = "模板名称", type = "String")
	public String getSellTemplateName() {
		return sellTemplateName;
	}

	public void setSellTemplateName(String sellTemplateName) {
		this.sellTemplateName = sellTemplateName;
		this.settings.put("sellTemplateName", sellTemplateName);
	}

	@DBMeta(column = "CREATE_TIME", name = "创建时间", type = "Date")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		this.settings.put("createTime", createTime);
	}

	@DBMeta(column = "UPDATE_TIME", name = "修改时间", type = "Date")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		this.settings.put("updateTime", updateTime);
	}

}
