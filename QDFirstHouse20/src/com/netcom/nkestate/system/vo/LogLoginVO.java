package com.netcom.nkestate.system.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

/**
 * 登陆日志表
 */
@DBModel(tablename = "LOG_LOGIN_T", sequence = "", id = DBModel.AssignedID)
public class LogLoginVO extends AbstractBaseVO {

	private long logID; //日志编号
	private long userID; //用户ID
	private int userType;// 用户类型，0管理用户，1开发企业用户	
	private String loginName; //登录名称
	private int loginType; //登录类型
	private Date loginTime; //登录时间
	private Date logoutTime; //退出时间
	private String clientIP; //客服端ip
	private String serverIP; //服务器ip
	private String sessionID; //
	private int success; //成功标志
	private String strSuccess;
	private String strType ;
	private String memo; //备注

	@DBMeta(column = "logID", name = "日志编号", type = "Long", primarykey = "true")
	public long getLogID() {
		return logID;
	}

	public void setLogID(long logID) {
		this.logID = logID;
		this.settings.put("logID", logID);
	}

	@DBMeta(column = "userID", name = "用户ID", type = "long")
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
		this.settings.put("userID", userID);
	}

	@DBMeta(column = "userType", name = "用户类型", type = "int")
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
		this.settings.put("userType", userType);
	}

	@DBMeta(column = "loginName", name = "登录名称", type = "String")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
		this.settings.put("loginName", loginName);
	}

	@DBMeta(column = "loginType", name = "登录类型", type = "int")
	public int getloginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
		this.settings.put("loginType", loginType);
	}

	@DBMeta(column = "loginTime", name = "登录时间", type = "Date")
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
		this.settings.put("loginTime", loginTime);
	}

	@DBMeta(column = "logoutTime", name = "退出时间", type = "Date")
	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
		this.settings.put("logoutTime", logoutTime);
	}

	@DBMeta(column = "clientIP", name = "客服端ip", type = "String")
	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
		this.settings.put("clientIP", clientIP);
	}

	@DBMeta(column = "serverIP", name = "服务器ip", type = "String")
	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
		this.settings.put("serverIP", serverIP);
	}

	@DBMeta(column = "sessionID", name = "sessionID", type = "String")
	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
		this.settings.put("sessionID", sessionID);
	}

	@DBMeta(column = "success", name = "成功标志", type = "int")
	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
		this.settings.put("success", success);
	}
	public String getStrSuccess() {
		if(success == 1) {
			strSuccess = "成功";
		}
		if(success == -1) {
			strSuccess = "失败";
		}
		return strSuccess;
	}

	public void setStrSuccess(String strSuccess) {
		this.strSuccess = strSuccess;
	}
	
	public String getStrType() {
		if(userType == 0) {
			strType = "管理用户";
		}
		if(userType == 1) {
			strType = "开发企业用户";
		}
		return strType;
	}

	
	public void setStrType(String strType) {
		this.strType = strType;
	}

	@DBMeta(column = "memo", name = "备注", type = "String")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
		this.settings.put("memo", memo);
	}


}
