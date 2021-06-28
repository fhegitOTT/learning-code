package com.netcom.nkestate.common;

import com.netcom.nkestate.framework.DataBaseFactory;

/**
 * 房屋维修基金数据库连接
 */
public class BaseHouseFundDAO extends MiniDAO {

	@Override
	public void connect() throws Exception {
		this.conn = DataBaseFactory.getConnection(Constant.HouseFundConnectionJNDI);
		this.commitFlag = conn.getAutoCommit();
	}
}
