/**
 *<p>ModelCache.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：TODO</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2013-5-28</p>
 *
 */
package com.netcom.nkestate.framework.cache;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.netcom.nkestate.framework.dao.ModelUtil;

public class ModelCache {
	static Logger logger = Logger.getLogger(ModelCache.class.getName());
	
	protected static SyncObject m_lock = new SyncObject();
	
	private static Map<Class , Method[]> m_get = new HashMap<Class , Method[]>();
	private static Map<Class , Method[]> m_set = new HashMap<Class , Method[]>();

	/**
	 * 
	 * 功能描述：得到映射数据库字段的get方法
	 * 
	 * @param clazz
	 * @return
	 */
	public static Method[] getMetaMethods(Class clazz) {
		if(!m_get.containsKey(clazz))
			loadMetaMethod(clazz);
		
		if(m_get.containsKey(clazz)){
			return m_get.get(clazz);
		}
		return null;
	}
	
	private static void loadMetaMethod(Class clazz) {
		//m_lock.lock();
		synchronized (clazz){			
			Method[] methods = ModelUtil.getMetaMethods(clazz);
			m_get.put(clazz, methods);
			logger.info("加载类" + clazz.getName() + "的get方法，" + methods.length
					+ "个get方法");
			//			int i = 0;
			//			for(Method method : methods){
			//				System.out.println("Method-" + i + "," + method.getName());
			//				i++;
			//			}
		}
	}

	/**
	 * 功能描述：得到映射数据库字段的set方法
	 * 
	 * @param clazz
	 * @return
	 */
	public static Method[] getSettingMethods(Class clazz) {
		if(!m_set.containsKey(clazz))
			loadSettingMethod(clazz);

		if(m_set.containsKey(clazz)){
			return m_set.get(clazz);
		}
		return null;
	}
	
	private static void loadSettingMethod(Class clazz) {
		//m_lock.lock();
		synchronized (clazz){
			Method[] methods = ModelUtil.getSettingMethods(clazz);
			m_set.put(clazz, methods);
			logger.info("加载类" + clazz.getName() + "的set方法，" + methods.length
					+ "个set方法");
		}
	}
	
	
}
