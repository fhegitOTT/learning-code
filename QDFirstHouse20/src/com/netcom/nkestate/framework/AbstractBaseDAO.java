/**
 *<p>AbstractDAO.java</p>
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.netcom.nkestate.framework.dao.DataBaseUtil;

public abstract class AbstractBaseDAO implements IDAO {
	protected Connection conn = null; // 数据库连接对象
	protected PreparedStatement pstmt = null;   //
	protected Statement stmt = null;			//
	protected ResultSet rs = null;				//
	
	protected boolean commitFlag = false;

	/**
	 * 
	 * 功能描述：打开数据库连接
	 */
	
	abstract public void connect() throws Exception;

	/**
	 * 
	 * 功能描述：关闭数据库连接
	 */
	public void close() {
		
		try {if(rs!=null) rs.close(); } catch (Exception e) {}
		try {if(pstmt!=null) pstmt.close(); } catch (Exception e) {}
		try {if(stmt!=null) stmt.close(); } catch (Exception e) {}
		try {if(conn!=null) conn.setAutoCommit(commitFlag); } catch (Exception e) {}
		try {if(conn!=null) conn.close(); } catch (Exception e) {}
		
		rs = null;
		stmt = null;
		pstmt = null;
		conn = null;
	}
	
	public void close(PreparedStatement pstmt) {
		try{
			pstmt.close();
		}catch (Exception e){
		}
	}
	public void close(Statement stmt) {
		try{
			stmt.close();
		}catch (Exception e){
		}
	}

	public void close(ResultSet rs) {
		try{
			rs.close();
		}catch (Exception e){
		}
	}

	public void close(PreparedStatement pstmt, ResultSet rs) {
		try{
			pstmt.close();
		}catch (Exception e){
		}
		try{
			rs.close();
		}catch (Exception e){
		}
	}

	/**
	 * 
	 * 功能描述：获得数据库连接对象
	 * 
	 * @return
	 */
	public Connection getConnection() {
		return this.conn;
	}

	/**
	 * 
	 * 功能描述：设置数据库连接对象
	 */
	public void setConnection(Connection connection) {
		this.conn = connection;
	}
	
	public void setConnection(IDAO dao) {
		this.conn = dao.getConnection();
	}

	/**
	 * 
	 * 功能描述：设置数据库事务
	 */
	public void setTransaction() throws SQLException {
		conn.setAutoCommit(false);
	}

	/**
	 * 
	 * 功能描述：提交事务
	 */
	public void commit() {
		try {
			this.conn.commit();
		} catch(SQLException e) {
			System.out.println("Commit()出现异常," + this.getClass().getName()
					+ "," + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 功能描述：事务回滚
	 */
	public void rollback() {
		try {
			this.conn.rollback();
		} catch(SQLException e) {
			//
		}
	}
	
	// 分页信息
	protected Page page = null;

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * 功能描述：新增一个对象
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public long add(IModel model) throws Exception {
		return DataBaseUtil.add((AbstractBaseVO) model, conn);
	}

	/**
	 * 功能描述：更新一个对象
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int update(IModel model) throws Exception {
		return DataBaseUtil.update((AbstractBaseVO) model, conn);
	}

	/**
	 * 功能描述：删除一个对象
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int delete(IModel model) throws Exception {
		return DataBaseUtil.delete((AbstractBaseVO) model, conn);
	}

	/**
	 * 功能描述：查询一个对象
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public IModel find(IModel model) throws Exception {
		return DataBaseUtil.find((AbstractBaseVO) model, conn);
	}

	/**
	 * 功能描述：根据ID查找一个对象,必须是单一主键
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public IModel find(Class clazz, long id) throws Exception {
		return DataBaseUtil.find(clazz, id, conn);
	}

}
