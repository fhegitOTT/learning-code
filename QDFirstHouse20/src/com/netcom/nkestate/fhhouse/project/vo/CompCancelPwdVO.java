package com.netcom.nkestate.fhhouse.project.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "COMPCANCELPWD", sequence = "", id = DBModel.AssignedID)
public class CompCancelPwdVO extends FHVO {

	private long compID; //  公司ID
	private long projectID; //  项目ID
	private String pwd; //  合同撤销密码
	private String refPwd; //  参考价格密码

	@DBMeta(column = "COMP_ID", name = "公司ID", type = "long", primarykey = "true", can_update = "false")
	public long getCompID() {
		return compID;
	}

	public void setCompID(long compID) {
		this.compID = compID;
		this.settings.put("compID", compID);
	}

	@DBMeta(column = "PROJECT_ID", name = "项目ID", type = "long", primarykey = "true", can_update = "false")
	public long getProjectID() {
		return projectID;
	}

	public void setProjectID(long projectID) {
		this.projectID = projectID;
		this.settings.put("projectID", projectID);
	}

	@DBMeta(column = "pwd", name = "合同撤销密码", type = "String")
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
		this.settings.put("pwd", pwd);
	}

	@DBMeta(column = "REF_PWD", name = "参考价格密码", type = "String")
	public String getRefPwd() {
		return refPwd;
	}

	public void setRefPwd(String refPwd) {
		this.refPwd = refPwd;
		this.settings.put("refPwd", refPwd);
	}
}
