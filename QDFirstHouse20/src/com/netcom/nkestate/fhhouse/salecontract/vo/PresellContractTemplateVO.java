/**
 * <p>PresellContractTemplateVO.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Dennis</p>
 * <p>创建时间: 2017-4-7<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.salecontract.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "PRESELL_CONTRACT_TEMPLATE", sequence = "", id = DBModel.AssignedID)
public class PresellContractTemplateVO extends AbstractBaseVO {

	private long contractID;//合同编号   
	private String presellTemplateName;//模板名称
	private Date createTime;//插入时间
	private Date updateTime;//撤销时间 

	@DBMeta(column = "CONTRACT_ID", name = "合同ID", type = "long", primarykey = "true", can_update = "false")
	public long getContractID() {
		return contractID;
	}

	public void setContractID(long contractID) {
		this.contractID = contractID;
		this.settings.put("contractID", contractID);
	}

	@DBMeta(column = "PRESELLTEMPLATENAME", name = "模板名称", type = "String")
	public String getPresellTemplateName() {
		return presellTemplateName;
	}

	public void setPresellTemplateName(String presellTemplateName) {
		this.presellTemplateName = presellTemplateName;
		this.settings.put("presellTemplateName", presellTemplateName);
	}

	@DBMeta(column = "CREATE_TIME", name = "插入时间", type = "Date")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		this.settings.put("createTime", createTime);
	}

	@DBMeta(column = "UPDATE_TIME", name = "撤销时间", type = "Date")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		this.settings.put("updateTime", updateTime);
	}

}
