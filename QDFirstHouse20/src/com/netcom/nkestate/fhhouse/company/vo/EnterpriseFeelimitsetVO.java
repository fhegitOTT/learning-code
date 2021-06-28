package com.netcom.nkestate.fhhouse.company.vo;

import com.netcom.nkestate.framework.FHVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "ENTERPRISE_FEELIMITSET", sequence = "", id = DBModel.AssignedID)
public class EnterpriseFeelimitsetVO extends FHVO {

	private long comp_ID;//企业ID
	private double feelimitset;//费用下限


	@DBMeta(column = "Comp_ID", name = "企业ID", type = "long", primarykey = "true", can_update = "false")
	public long getComp_ID() {
		return comp_ID;
	}

	public void setComp_ID(long Comp_ID) {
		this.comp_ID = Comp_ID;
		this.settings.put("comp_ID", comp_ID);
	}

	@DBMeta(column = "Feelimitset", name = "费用下限", type = "double")
	public double getFeelimitset() {
		return feelimitset;
	}

	public void setFeelimitset(double Feelimitset) {
		this.feelimitset = Feelimitset;
		this.settings.put("feelimitset", feelimitset);
	}



}
