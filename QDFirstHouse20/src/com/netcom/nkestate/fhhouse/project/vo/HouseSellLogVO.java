/*
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 开发项目VO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 

package com.netcom.nkestate.fhhouse.project.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "Log_House_Sell_t", sequence = "SEQ_House_Sell_log_ID", id = DBModel.SequenceID)
public class HouseSellLogVO extends AbstractBaseVO {

	private long logID; //日志ID
	private long houseID; //房屋编号
	private long operatorUserID; //操作人
	private Date operateDate;//操作时间
	private int operateType;//操作类型,1人工干预可售，0人工干预不可售
	@DBMeta(column = "logID", name = "日志ID", type = "long", primarykey = "true", can_update = "false")
	public long getLogID() {
		return logID;
	}

	public void setLogID(long logID) {
		this.logID = logID;
		this.settings.put("logID", logID);
	}
	@DBMeta(column = "houseID", name = "房屋编号", type = "long")
	public long getHouseID() {
		return houseID;
	}

	public void setHouseID(long houseID) {
		this.houseID = houseID;
		this.settings.put("houseID", houseID);
	}
	@DBMeta(column = "operatorUserID", name = "操作人员", type = "long")
	public long getOperatorUserID() {
		return operatorUserID;
	}

	public void setOperatorUserID(long operatorUserID) {
		this.operatorUserID = operatorUserID;
		this.settings.put("operatorUserID", operatorUserID);
	}
	@DBMeta(column = "operateDate", name = "操作时间", type = "Date")
	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
		this.settings.put("operateDate", operateDate);
	}
	@DBMeta(column = "operateType", name = "操作类型", type = "int")
	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
		this.settings.put("operateType", operateType);
	}


}
