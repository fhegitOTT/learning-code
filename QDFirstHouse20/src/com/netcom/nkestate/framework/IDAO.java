/**
 *<p>UserAction.java</p>
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

import java.sql.Connection;
import java.sql.SQLException;


public interface IDAO {
	/**
	 * 
	 * 功能描述：打开数据库连接
	 */
	public void connect() throws Exception;

	/**
	 * 
	 * 功能描述：关闭数据库连接
	 */
	public void close();

	/**
	 * 
	 * 功能描述：获得数据库连接对象
	 * 
	 * @return
	 */
	public Connection getConnection();

	/**
	 * 
	 * 功能描述：设置数据库连接对象
	 */
	public void setConnection(Connection connection);
	
	public void setConnection(IDAO dao);

	/**
	 * 
	 * 功能描述：设置数据库事务
	 */
	public void setTransaction() throws SQLException;

	/**
	 * 
	 * 功能描述：提交事务
	 */
	public void commit();

	/**
	 * 
	 * 功能描述：事务回滚
	 */
	public void rollback();

	/**
	 * 功能描述：新增一个对象
	 * 
	 * @param model
	 * @return 对象编号,如果是字符型主键，返回Long.MAX_VALUE
	 * @throws Exception
	 */
	public long add(IModel model) throws Exception;

	/**
	 * 功能描述：更新一个对象
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int update(IModel model) throws Exception;

	/**
	 * 功能描述：删除一个对象
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int delete(IModel model) throws Exception;

	/**
	 * 功能描述：查询一个对象
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public IModel find(IModel model) throws Exception;

	/**
	 * 功能描述：根据ID查找一个对象,必须是单一主键
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public IModel find(Class clazz, long id) throws Exception;
	
	
	public void setPage(Page page); // 设置分页

}
