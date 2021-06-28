package com.netcom.nkestate.system.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "USERINFO", sequence = "", id = DBModel.AssignedID)
public class UserinfoVO extends FHVO {

	private long userId;//用户id
	private int districtId;//区县id
	private String loginName;//登陆名
	private String userName;//显示名称
	private String pwd;//密码
	private String dept;//部门
	private String address;//地址
	private String call;//电话
	private int deletedFlag;//删除标准

	@DBMeta(column = "userId", name = "用户id", type = "long", primarykey = "true", can_update = "false")
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@DBMeta(column = "districtId", name = "区县id", type = "int")
	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	@DBMeta(column = "loginName", name = "登陆名", type = "String")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@DBMeta(column = "userName", name = "登陆名", type = "String")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@DBMeta(column = "pwd", name = "密码", type = "String")
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@DBMeta(column = "dept", name = "部门", type = "String")
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@DBMeta(column = "address", name = "地址", type = "String")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@DBMeta(column = "call", name = "电话", type = "String")
	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	@DBMeta(column = "deletedFlag", name = "删除标志", type = "int")
	public int getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(int deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	
}
