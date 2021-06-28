/**
 *<p>AbstractBO.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：基础类</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2011-11-10</p>
 *
 */
package com.netcom.nkestate.framework;


public abstract class AbstractBaseBO implements IBO {
	private IDAO _dao_ = null;
	
	private boolean isOpenWithoutDAO = true; // 无DAO参数创建
	
	public AbstractBaseBO() {
	}
	
	public AbstractBaseBO(IDAO dao) {
		if(dao != null){
			isOpenWithoutDAO = false;
			_dao_ = dao;
		}
	}
	
	public void openDAO(IDAO dao) throws Exception {
		if(isOpenWithoutDAO)
			dao.connect();
	}

	public void closeDAO(IDAO dao) throws Exception {
		if(isOpenWithoutDAO)
			dao.close();
	}
	
	
}
