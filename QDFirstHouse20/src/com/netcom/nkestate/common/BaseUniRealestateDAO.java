package com.netcom.nkestate.common;

import com.netcom.nkestate.framework.DataBaseFactory;

public class BaseUniRealestateDAO extends MiniDAO {

	@Override
	public void connect() throws Exception {
		this.conn = DataBaseFactory.getConnection(Constant.UnirealestateJNDI);
		this.commitFlag = conn.getAutoCommit();
	}
}

