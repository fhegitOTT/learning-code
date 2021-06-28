package com.netcom.nkestate.system.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "T_EXECUTIVE_H", sequence = "SEQ_LOG_EXEC_HEAD", id = DBModel.AssignedID)
public class ExecutiveHVO extends AbstractBaseVO {

	private long recordID;//	记录ID
	private String userCode;//	用户编号
	private String execFunc;//	执行机能
	private int functionType;//	机能类型
	private long executeStatus;//	执行状态
	private long startDate;//	开始日期
	private long startTime;//	开始时间
	private long endDate;//	结束日期
	private long endTime;//	结束时间
	private int userType;//	内外网用户区分

	@DBMeta(column = "RecordID", name = "记录ID", type = "long", primarykey = "true")
	public long getRecordID() {
		return recordID;
	}

	public void setRecordID(long recordID) {
		this.recordID = recordID;
		this.settings.put("recordID", recordID);
	}

	@DBMeta(column = "UserCode", name = "用户编号", type = "String")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
		this.settings.put("userCode", userCode);
	}

	@DBMeta(column = "ExecFunc", name = "执行机能", type = "String")
	public String getExecFunc() {
		return execFunc;
	}

	public void setExecFunc(String execFunc) {
		this.execFunc = execFunc;
		this.settings.put("execFunc", execFunc);
	}

	@DBMeta(column = "FunctionType", name = "机能类型", type = "int")
	public int getFunctionType() {
		return functionType;
	}

	public void setFunctionType(int functionType) {
		this.functionType = functionType;
		this.settings.put("functionType", functionType);
	}

	@DBMeta(column = "ExecuteStatus", name = "执行状态", type = "long")
	public long getExecuteStatus() {
		return executeStatus;
	}

	public void setExecuteStatus(long executeStatus) {
		this.executeStatus = executeStatus;
		this.settings.put("executeStatus", executeStatus);
	}

	@DBMeta(column = "StartDate", name = "开始日期", type = "long")
	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
		this.settings.put("startDate", startDate);
	}

	@DBMeta(column = "StartTime", name = "开始时间", type = "long")
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
		this.settings.put("startTime", startTime);
	}

	@DBMeta(column = "EndDate", name = "结束日期", type = "long")
	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
		this.settings.put("endDate", endDate);
	}

	@DBMeta(column = "EndTime", name = "结束时间", type = "long")
	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
		this.settings.put("endTime", endTime);
	}

	@DBMeta(column = "UserType", name = "内外网用户区分", type = "int")
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
		this.settings.put("userType", userType);
	}


}
