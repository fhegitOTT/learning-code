package com.netcom.nkestate.fhhouse.manage.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "T_USER_PROJECT_SET", sequence = "", id = DBModel.AssignedID)
public class UserProjectSetVO extends AbstractBaseVO {

	private String userName; // 	人员ID
	private String acceptno; // 	许可证ID
	private String key; // 	key
	private String saveDate; // 	操作时间
	private String savePerson;//操作人员

	@DBMeta(column = "UserName", name = "人员ID", type = "String", primarykey = "true", can_update = "false")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String UserName) {
		this.userName = UserName;
		this.settings.put("userName", userName);
	}

	@DBMeta(column = "Acceptno", name = "许可证ID", type = "String", primarykey = "true", can_update = "false")
	public String getAcceptno() {
		return acceptno;
	}

	public void setAcceptno(String Acceptno) {
		this.acceptno = Acceptno;
		this.settings.put("acceptno", acceptno);
	}

	@DBMeta(column = "Key", name = "key", type = "String")
	public String getKey() {
		return key;
	}

	public void setKey(String Key) {
		this.key = Key;
		this.settings.put("key", key);
	}

	@DBMeta(column = "SaveDate", name = "操作时间", type = "String")
	public String getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(String SaveDate) {
		this.saveDate = SaveDate;
		this.settings.put("saveDate", saveDate);
	}

	@DBMeta(column = "savePerson", name = "操作人员", type = "String")
	public String getSavePerson() {
		return savePerson;
	}


	public void setSavePerson(String savePerson) {
		this.savePerson = savePerson;
		this.settings.put("savePerson", savePerson);
	}


}
