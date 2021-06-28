/**
 * <p>BaseRealestateBO.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 内网登记基础BO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.common;

import java.util.ArrayList;
import java.util.List;

import com.netcom.nkestate.framework.AbstractBaseBO;
import com.netcom.nkestate.framework.IModel;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;


public class BaseRealestateBO extends AbstractBaseBO {

	private BaseRealestateDAO realDAO = new BaseRealestateDAO();

	public IModel find(IModel vo) throws Exception {
		try{
			openDAO(realDAO);
			vo = realDAO.find(vo);
			return vo;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(realDAO);
		}
	}

	/**
	 * 功能描述：根据ID查找一个对象,必须是单一主键
	 * @param clazz
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public IModel find(Class clazz,long id) throws Exception {
		try{
			openDAO(realDAO);
			return realDAO.find(clazz, id);
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(realDAO);
		}
	}

	/**
	 * 功能描述：查询列表
	 * @param clazz
	 * @param params
	 * @param orders
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List search(Class clazz,List<MetaFilter> params,List<MetaOrder> orders,Page page) throws Exception {
		List proList = new ArrayList();
		try{
			openDAO(realDAO);
			proList = realDAO.searchByAttibutes(clazz, params, orders, page);
			return proList;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(realDAO);
		}
	}
	
	public long add(IModel vo) throws Exception {
		try{
			openDAO(realDAO);
			long id = realDAO.add(vo);
			return id;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(realDAO);
		}
	}
}
