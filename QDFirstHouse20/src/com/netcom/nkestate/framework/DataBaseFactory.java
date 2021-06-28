/**
 *<p>DataBaseUtil.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：数据库连接对象获得者</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2011-11-16</p>
 *
 */
package com.netcom.nkestate.framework;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataBaseFactory {	

	private static boolean TestMode = false;
	
	public static void setTestMode(boolean mode) {
		TestMode = mode;
	}

	/**
	 * 
	 * 功能描述：获得登记库连接
	 * 
	 * @return
	 * @throws Exception
	 */	
	public static Connection getConnection(String JNDI_Name) throws Exception {
		if (TestMode) // 开发单元测试模式
			return getTestConnection();
		
		Connection conn = null;
		try {
			InitialContext initCtx = new InitialContext();
			DataSource ds = (javax.sql.DataSource) initCtx.lookup(JNDI_Name);
	        conn = ds.getConnection();
	        
	        if(conn==null)
	        	throw new Exception("No Connection");
		} catch (NamingException e) {
			throw new Exception("Can't Find " + JNDI_Name + " Connection");
		} catch(SQLException ignore) {
			throw new Exception("No Connection");
		}
		return conn;
	}
		
	
	// 开发测试连接,只取第一个连接
	private static Connection getTestConnection() throws Exception {
		Connection conn = null;
		
		if(NKEstate_Driver==null)
			load();	
		/*
		if(DBUserName.equalsIgnoreCase(NKEstate_User)) {
			Class.forName(NKEstate_Driver).newInstance();
			String url=NKEstate_URL; 
			String user=NKEstate_User;
			String password=NKEstate_Password;
			conn= DriverManager.getConnection(url,user,password); 			
		} else if(DBUserName.equalsIgnoreCase(NKMarket_User)) {
			Class.forName(NKMarket_Driver).newInstance();
			String url=NKMarket_URL; 
			String user=NKMarket_User;
			String password=NKMarket_Password;
			conn= DriverManager.getConnection(url,user,password); 
		}	
		*/
		Class.forName(NKEstate_Driver).newInstance();
		String url=NKEstate_URL; 
		String user=NKEstate_User;
		String password=NKEstate_Password;
		conn= DriverManager.getConnection(url,user,password); 
		if(conn==null)
			throw new Exception("No Connection");
		return conn;
	}
	
	private static String NKEstate_Driver = null;
	private static String NKEstate_URL = null;
	private static String NKEstate_User = null;
	private static String NKEstate_Password = null;

	
	
	private static void load() throws Exception {
		String file = "/config/db.properties";		
		try {
			InputStream in = DataBaseFactory.class.getResourceAsStream(file);
			//InputStream in = DataBaseFactory.class.getClassLoader().getSystemResourceAsStream(file);
			Properties p = new Properties();
			p.load(in);

			NKEstate_Driver = p.getProperty("nkestate.driver");
			NKEstate_URL = p.getProperty("nkestate.url");
			NKEstate_User = p.getProperty("nkestate.username");
			NKEstate_Password = p.getProperty("nkestate.password");
			
			
			System.out.println("NKEstate_Driver:" + NKEstate_Driver);
			System.out.println("NKEstate_URL:" + NKEstate_URL);
			System.out.println("NKEstate_User:" + NKEstate_User);
			System.out.println("NKEstate_Password:" + NKEstate_Password);
			
			//System.out.println("NKMarket_Driver:" + NKMarket_Driver);
			//System.out.println("NKMarket_URL:" + NKMarket_URL);
			//System.out.println("NKMarket_User:" + NKMarket_User);
			//System.out.println("NKMarket_Password:" + NKMarket_Password);			
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	static public void main(String[] args) throws Exception {
		load();
		Connection connection = getConnection("jdbc/OuterQueryConnection");
		String sql = "select * from company_t";



	}
	
}
