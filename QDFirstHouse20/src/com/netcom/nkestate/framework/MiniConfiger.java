/**
 * <p>ConfigReader.java </p>
 *
 * <p>系统名称: 上海新建商品房备案系统<p>  
 * <p>功能描述:  读取"/config/nankang.properties"的数据<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017年12月27日<p>
 * 
 */ 
package com.netcom.nkestate.framework;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;


public class MiniConfiger {

	private static Properties p = new Properties();

	private static String DBCharSet = null; //数据库字符集
	private static String AttachementUrl = null; //附件存储地址

	static {
		load();
	}

	private static void load() {
		String file = "/config/nankang.properties";
		InputStream in = null;
		try {
			System.out.println("INFO: Start Loading " + file + " ... ...");

			in = DataBaseFactory.class.getResourceAsStream(file);
			//InputStream in = DataBaseFactory.class.getClassLoader().getSystemResourceAsStream(file);

			p = new Properties();
			p.load(in);

			DBCharSet = p.getProperty("DBCharSet");
			AttachementUrl = p.getProperty("AttachementUrl");

			Enumeration em = p.propertyNames();
			while (em.hasMoreElements()) {
				String key = (String) em.nextElement();
				String value = p.getProperty(key);
				//value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
				System.out.println("Key:" + key + ",value:" + value);
			}			

			System.out.println("INFO: Loading finished！");

		} catch (Exception e) {
			System.out.println("Error，ConfigReader:" + file + "出现异常");
			e.printStackTrace();
		} finally {
			try { in.close(); } catch(Exception e){}
		}
	}

	/**
	 * 功能描述：获取配置参数
	 * @param param
	 * @return
	 */
	public static String getProperty(String key) {
		if(p == null || p.isEmpty())
			return null;

		return p.getProperty(key);
	}

	/**
	 * 功能描述：获取配置的数据库字符集
	 * @return
	 */
	public static String getDBCharSet() {
		return DBCharSet;
	}

	/**
	 * 功能描述：获取配置的数据库字符集
	 * @return
	 */
	public static String getAttachementUrl() {
		return AttachementUrl;
	}
}
