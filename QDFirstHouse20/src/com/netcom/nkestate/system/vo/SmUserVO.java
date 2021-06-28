package com.netcom.nkestate.system.vo;

import java.util.Date;
import java.util.List;

import com.netcom.nkestate.framework.AbstractBaseVO;

;

/**
 * 系统用户表
 */
public class SmUserVO extends AbstractBaseVO {

	private int regionId;//区县ID
	private long userId;//用户ID
	private int loginType;//登录方式
	private String loginName;//登录名
	private String password;//密码
	private String displayName;//显示名称
	private int defaultAppId;//默认系统ID
	private String email;//邮件
	private int defaultAccount;//是否默认账号
	private String description;//描述
	private int user_imark;//
	private int valid;//有效标志


	private String clientIP;//登录IP
	private long loginLogID;//登录日志ID
	private Date loginDate;//登录时间
	private int userType;//0:内网用户 1:外网用户
	private long orgID;//机构ID
	private String orgName;//机构名称
	private List<SmObjectVO> objectList;//菜单列表

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
		this.settings.put("regionId", regionId);
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
		this.settings.put("userId", userId);
	}

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
		this.settings.put("loginType", loginType);
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
		this.settings.put("loginName", loginName);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		this.settings.put("password", password);
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
		this.settings.put("displayName", displayName);
	}

	public int getDefaultAppId() {
		return defaultAppId;
	}

	public void setDefaultAppId(int defaultAppId) {
		this.defaultAppId = defaultAppId;
		this.settings.put("defaultAppId", defaultAppId);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		this.settings.put("email", email);
	}

	public int getDefaultAccount() {
		return defaultAccount;
	}

	public void setDefaultAccount(int defaultAccount) {
		this.defaultAccount = defaultAccount;
		this.settings.put("defaultAccount", defaultAccount);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		this.settings.put("description", description);
	}

	public int getUser_imark() {
		return user_imark;
	}

	public void setUser_imark(int userImark) {
		user_imark = userImark;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
		this.settings.put("valid", valid);
	}


	public String getClientIP() {
		return clientIP;
	}


	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
		this.settings.put("clientIP", clientIP);
	}


	public long getLoginLogID() {
		return loginLogID;
	}


	public void setLoginLogID(long loginLogID) {
		this.loginLogID = loginLogID;
		this.settings.put("loginLogID", loginLogID);
	}


	public Date getLoginDate() {
		return loginDate;
	}


	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
		this.settings.put("loginDate", loginDate);
	}


	public int getUserType() {
		return userType;
	}


	public void setUserType(int userType) {
		this.userType = userType;
		this.settings.put("userType", userType);
	}

	public List<SmObjectVO> getObjectList() {
		return objectList;
	}


	public void setObjectList(List<SmObjectVO> objectList) {
		this.objectList = objectList;
		this.settings.put("objectList", objectList);
	}

	public long getOrgID() {
		return orgID;
	}

	public void setOrgID(long orgID) {
		this.orgID = orgID;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
