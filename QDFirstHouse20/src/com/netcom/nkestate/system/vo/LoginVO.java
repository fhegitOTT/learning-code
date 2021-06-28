package com.netcom.nkestate.system.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;


@DBModel(tablename = "T_LOGIN", sequence = "SEQ_LOGIN", id = DBModel.SequenceID)
public class LoginVO extends AbstractBaseVO {

	private long recordCode;//日志ID
	private long userID;//用户ID
	private String userIP;//用户IP
	private String sessionID;//sessionID
	private long startDate;//开始日期
	private long startTime;//开始时间
	private int userType;//用户类型 0:内网  1：外网
	private long endDate;//结束日期
	private long endTime;//结束时间
	private int loginType;//登录方式  0：用户名方式  1：key方式登录

	@DBMeta(column = "RecordCode", name = "日志ID", type = "long", primarykey = "true")
	public long getRecordCode() {
		return recordCode;
	}

	public void setRecordCode(long recordCode) {
		this.recordCode = recordCode;
		this.settings.put("recordCode", recordCode);
	}

	@DBMeta(column = "UserID", name = "用户ID", type = "long")
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
		this.settings.put("userID", userID);
	}

	@DBMeta(column = "UserIP", name = "用户IP", type = "String")
	public String getUserIP() {
		return userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
		this.settings.put("userIP", userIP);
	}

	@DBMeta(column = "SessionID", name = "sessionID", type = "String")
	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
		this.settings.put("sessionID", sessionID);
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

	@DBMeta(column = "UserType", name = "用户类型", type = "int")
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
		this.settings.put("userType", userType);
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


	@DBMeta(column = "loginType", name = "登录方式", type = "int")
	public int getLoginType() {
		return loginType;
	}


	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}


}
