package com.netcom.nkestate.fhhouse.project.vo;

import java.util.Date;

import org.apache.commons.io.filefilter.IOFileFilter;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;
import com.netcom.nkestate.framework.util.DateUtil;

@DBModel(tablename = "LOG_START_UNIT_REASON", sequence = "SEQ_LOG_START_UNIT_REASON", id = DBModel.SequenceID)
public class StartUnitReasonVO extends AbstractBaseVO {

	private long logID; //日志ID
	private long startID; //开盘ID
	private String reason; //操作原因
	private Date operateDate; //操作日期
	private long operateUser; //操作人
	private int status; //状态0,立即开盘，1，不开盘，2暂停销售

	
	@DBMeta(column = "logID", name = "日志ID", type = "long", primarykey = "true", can_update = "false")
	public long getLogID() {
		return logID;
	}

	public void setLogID(long logID) {
		this.logID = logID;
		this.settings.put("logID", logID);
	}
	
	@DBMeta(column = "startID", name = "开盘ID", type = "long",can_update = "false")
	public long getStartID() {
		return startID;
	}

	public void setStartID(long startID) {
		this.startID = startID;
		this.settings.put("startID", startID);
	}
	@DBMeta(column = "reason", name = "操作原因", type = "String",can_update = "false")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
		this.settings.put("reason", reason);
	}
	@DBMeta(column = "operateDate", name = "操作日期", type = "Date",can_update = "false")
	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
		this.settings.put("operateDate", operateDate);
	}
	@DBMeta(column = "operateUser", name = "操作人", type = "long",can_update = "false")
	public long getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(long operateUser) {
		this.operateUser = operateUser;
		this.settings.put("operateUser", operateUser);
	}
	@DBMeta(column = "status", name = "状态", type = "int",can_update = "false")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}
	
	public String getStatusStr() {
		String ret = "";
		switch (status) {
		case 0:
			ret = "立即开盘";
			break;
		case 1:
			ret = "不开盘";
			break;
		case 2:
			ret = "暂停销售";
			break;

		default:
			break;
		}
		return ret;
	}
	
	public String getOperateDateStr(){
		String dateString = "";
		if(operateDate!=null){
			dateString = DateUtil.formatDateTime(operateDate);
		}
		return dateString;
	}
}
