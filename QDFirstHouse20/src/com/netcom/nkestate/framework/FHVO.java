package com.netcom.nkestate.framework;

import java.util.Date;

import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.util.StringUtil;


public class FHVO extends AbstractBaseVO {

	protected String crePerson;//	作成者
	protected long creDate;//	作成日期
	protected long creTime;//	作成时间
	protected String creProid;//	作成程序编码
	protected String updPerson;//	更新者
	protected long updDate;//	更新日期
	protected long updTime;//	更新时间
	protected String updProid;//	更新程序编码
	protected long updCnt;//	更新计数器

	@DBMeta(column = "CrePerson", name = "作成者", type = "String")
	public String getCrePerson() {
		return crePerson;
	}

	public void setCrePerson(String crePerson) {
		this.crePerson = crePerson;
		this.settings.put("crePerson", crePerson);
	}

	@DBMeta(column = "CreDate", name = "作成日期", type = "long")
	public long getCreDate() {
		return creDate;
	}

	public void setCreDate(long creDate) {
		this.creDate = creDate;
		this.settings.put("creDate", creDate);
	}

	@DBMeta(column = "CreTime", name = "作成时间", type = "long")
	public long getCreTime() {
		return creTime;
	}

	public void setCreTime(long creTime) {
		this.creTime = creTime;
		this.settings.put("creTime", creTime);
	}

	@DBMeta(column = "CreProid", name = "作成程序编码", type = "String")
	public String getCreProid() {
		return creProid;
	}

	public void setCreProid(String creProid) {
		this.creProid = creProid;
		this.settings.put("creProid", creProid);
	}

	@DBMeta(column = "UpdPerson", name = "更新者", type = "String")
	public String getUpdPerson() {
		return updPerson;
	}

	public void setUpdPerson(String updPerson) {
		this.updPerson = updPerson;
		this.settings.put("updPerson", updPerson);
	}

	@DBMeta(column = "UpdDate", name = "更新日期", type = "long")
	public long getUpdDate() {
		return updDate;
	}

	public void setUpdDate(long updDate) {
		this.updDate = updDate;
		this.settings.put("updDate", updDate);
	}

	@DBMeta(column = "UpdTime", name = "更新时间", type = "long")
	public long getUpdTime() {
		return updTime;
	}

	public void setUpdTime(long updTime) {
		this.updTime = updTime;
		this.settings.put("updTime", updTime);
	}

	@DBMeta(column = "UpdProid", name = "更新程序编码", type = "String")
	public String getUpdProid() {
		return updProid;
	}

	public void setUpdProid(String updProid) {
		this.updProid = updProid;
		this.settings.put("updProid", updProid);
	}

	@DBMeta(column = "UpdCnt", name = "更新计数器", type = "long")
	public long getUpdCnt() {
		return updCnt;
	}

	public void setUpdCnt(long updCnt) {
		this.updCnt = updCnt;
		this.settings.put("updCnt", updCnt);
	}

	public Date getUpdateTime() {
		Date updateTime = null;
		if(this.updDate > 0){
			String date = updDate + StringUtil.getFullwidthStr(String.valueOf(updTime), 6);
			try{
				updateTime = DateUtil.parseDateTime2(date);
			}catch (Exception e){
			}
		}
		return updateTime;
	}

	public String getUpdateTimeStr() {

		return DateUtil.formatDateTime(this.getUpdateTime());
	}

}
