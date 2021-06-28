package com.netcom.nkestate.system.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "SIGNERACTIONS", sequence = "", id = DBModel.AssignedID)
public class SignerActionsVO extends AbstractBaseVO {

	private long actionID;//
	private long signerID;//
	
	@DBMeta(column = "ActionID", name = "ActionID", type = "long", primarykey = "true")
	public long getActionID() {
		return actionID;
	}

	public void setActionID(long actionID) {
		this.actionID = actionID;
		this.settings.put("actionID", actionID);
	}


	@DBMeta(column = "SIGNER_ID", name = "SignerID", type = "long")
	public long getSignerID() {
		return signerID;
	}


	public void setSignerID(long signerID) {
		this.signerID = signerID;
		this.settings.put("signerID", signerID);
	}


}
