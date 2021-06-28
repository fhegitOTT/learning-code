package com.netcom.nkestate.system.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "M_SEQUENCE", sequence = "", id = DBModel.AssignedID)
public class MSequenceVO extends AbstractBaseVO {

	private String numTpcd;//
	private long numValue;//
	private int numLen;//
	private String updType;//
	private long updDate;//

	@DBMeta(column = "NumTpcd", name = "NumTpcd", type = "long", primarykey = "true")
	public String getNumTpcd() {
		return numTpcd;
	}

	public void setNumTpcd(String numTpcd) {
		this.numTpcd = numTpcd;
		this.settings.put("numTpcd", numTpcd);
	}

	@DBMeta(column = "NumValue", name = "NumValue", type = "long")
	public long getNumValue() {
		return numValue;
	}

	public void setNumValue(long numValue) {
		this.numValue = numValue;
		this.settings.put("numValue", numValue);
	}

	@DBMeta(column = "NumLen", name = "NumLen", type = "int")
	public int getNumLen() {
		return numLen;
	}

	public void setNumLen(int numLen) {
		this.numLen = numLen;
		this.settings.put("numLen", numLen);
	}

	@DBMeta(column = "UpdType", name = "UpdType", type = "String")
	public String getUpdType() {
		return updType;
	}

	public void setUpdType(String updType) {
		this.updType = updType;
		this.settings.put("updType", updType);
	}

	@DBMeta(column = "UpdDate", name = "UpdDate", type = "long")
	public long getUpdDate() {
		return updDate;
	}

	public void setUpdDate(long updDate) {
		this.updDate = updDate;
		this.settings.put("updDate", updDate);
	}


}
