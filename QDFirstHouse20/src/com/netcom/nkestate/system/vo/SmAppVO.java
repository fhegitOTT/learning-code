package com.netcom.nkestate.system.vo;

import java.util.Date;

import com.netcom.nkestate.framework.AbstractBaseVO;

/**
 * 系统角色表
 */
public class SmAppVO extends AbstractBaseVO {

	private long appID;//系统编号
	private String name;//系统名称
	private String shortName;//系统简称
	private String code;//系统编号
	private String structure;//机构
	private String entry;//入口
	private Date startTime;//开始时间
	private Date stopTime;//结束时间
	private int status;//状态
	private String description;//描述
	
	
	public long getAppID() {
		return appID;
	}
	public void setAppID(long appID) {
		this.appID = appID;
		this.settings.put("appID", appID);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.settings.put("name", name);
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
		this.settings.put("shortName", shortName);
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		this.settings.put("code", code);
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
		this.settings.put("structure", structure);
	}
	
	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
		this.settings.put("entry", entry);
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		this.settings.put("startTime", startTime);
	}
	
	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
		this.settings.put("stopTime", stopTime);
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
		this.settings.put("status", status);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		this.settings.put("description", description);
	}

	public String getStatusName() {
		if(status == 1){
			return "有效";
		}else{
			return "无效";
		}
	}
	
}
