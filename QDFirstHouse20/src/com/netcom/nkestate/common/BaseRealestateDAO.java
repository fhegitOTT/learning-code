/**
 * <p>BaseRealestateDAO.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 登记内网 类<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.common;

import com.netcom.nkestate.framework.DataBaseFactory;


public class BaseRealestateDAO extends MiniDAO {

	@Override
	public void connect() throws Exception {
		this.conn = DataBaseFactory.getConnection(Constant.RealestateConnectionJNDI);
		this.commitFlag = conn.getAutoCommit();
	}
}
