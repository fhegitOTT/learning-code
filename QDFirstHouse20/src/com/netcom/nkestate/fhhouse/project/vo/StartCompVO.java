package com.netcom.nkestate.fhhouse.project.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "START_COMP", sequence = "", id = DBModel.AssignedID)
public class StartCompVO extends FHVO {

	private long start_ID; //  开盘ID
	private long comp_ID; //  企业ID
	private int serial; //  合同中甲方顺序号


	@DBMeta(column = "start_ID", name = "开盘ID", type = "long", primarykey = "true", can_update = "false")
	public long getStart_ID() {
		return start_ID;
	}

	public void setStart_ID(long start_ID) {
		this.start_ID = start_ID;
		this.settings.put("start_ID", start_ID);
	}

	@DBMeta(column = "comp_ID", name = "企业ID", type = "long")
	public long getComp_ID() {
		return comp_ID;
	}

	public void setComp_ID(long comp_ID) {
		this.comp_ID = comp_ID;
		this.settings.put("comp_ID", comp_ID);
	}

	@DBMeta(column = "serial", name = "合同中甲方顺序号", type = "int")
	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
		this.settings.put("serial", serial);
	}


}
