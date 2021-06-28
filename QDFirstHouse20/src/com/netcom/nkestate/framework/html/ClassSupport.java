/**
 *<p>ClassSupport.java</p>
 *
 *<p>项目名称：银川房产项目</p>
 *<p>系统名称：存量房交易资金监管系统</p>
 *<p>功能描述：VO支持类</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: Administrator</p>
 *<p>创建日期: 2014-8-14</p>
 *
 */
package com.netcom.nkestate.framework.html;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.util.Formatter;


public class ClassSupport {
	private static Map<Class , Method[]> m_get = new HashMap<Class , Method[]>();
	

	/**
	 * 功能描述：取出VO对象的方法值
	 * @param obj
	 * @param name
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static String getVOValue(Object vo,String name) throws Exception {
		Object value = null;

		Method method = getGettingMethod(vo.getClass(), name);
		if(method != null){
			value = method.invoke(vo, null);
		}else if(vo instanceof AbstractBaseVO){
			value = ((AbstractBaseVO) vo).getAttribute(name);
		}

		if(value == null){
			value = "";
		}
		return String.valueOf(value);
	}
	
	/**
	 * 功能描述：TODO
	 * @param vo
	 * @param name
	 * @param defaultValue
	 * @return
	 * @throws Exception
	 */
	public static String getVOValue(Object vo,String name,String defaultValue) throws Exception {
		Object value = null;

		Method method = getGettingMethod(vo.getClass(), name);
		if(method != null){
			value = method.invoke(vo, null);
		}else if(vo instanceof AbstractBaseVO){
			value = ((AbstractBaseVO) vo).getAttribute(name);
		}

		return value == null ? defaultValue : String.valueOf(value);
	}

	/**
	 * 功能描述：取出VO对象的方法值
	 * @param vo
	 * @param name
	 * @param format
	 * @param defaultValue
	 * @return
	 * @throws Exception
	 */
	public static String getVOValue(Object vo,String name,String format,String defaultValue) throws Exception {
		Object value = null;
		
		Method method = getGettingMethod(vo.getClass(), name);		
		if(method!=null) {
			value = method.invoke(vo, null);
		}else if(vo instanceof AbstractBaseVO){
			value = ((AbstractBaseVO) vo).getAttribute(name);
		}
		
		if(value!=null) {
			if(value instanceof java.util.Date && format == null)
				return DateUtil.formatDate((java.util.Date) value);
			if(value instanceof java.util.Date && format != null)
				return DateUtil.format((java.util.Date) value, format);
			if(value instanceof java.lang.Double)
				return Formatter.formatDecimal(value, format);
			if(value instanceof java.lang.Float)
				return Formatter.formatDecimal(value, format);
		}
		
		return value == null ? defaultValue : String.valueOf(value);		
	}

	/**
	 * 功能描述：得到get方法
	 * @param clazz
	 * @param name
	 * @return
	 */	
	private static Method getGettingMethod(Class clazz,String name) {
		if(m_get.get(clazz) == null){
			synchronized (clazz){
				m_get.put(clazz, getGettingMethods(clazz));
			}			
		}
		Method[] methods = m_get.get(clazz);
		if(methods != null){
			for(Method method : methods){
				if(method.getName().toLowerCase().equals("get" + name.toLowerCase()))
					return method;
			}
		}		
		return null;
	}
	
	//查找VO对象的get方法
	private static Method[] getGettingMethods(Class clazz) {
		Set<Method> set = new HashSet<Method>();
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method : methods){
			if(method.getName().startsWith("get")){
				set.add(method);
			}
		}

		//查询父类的方法
		findParentGettingMethods(clazz.getSuperclass(), set);
		methods = new Method[set.size()];
		Object[] objs = set.toArray();
		for(int i = 0; i < objs.length; i++){
			methods[i] = (Method) objs[i];
		}

		return methods;
	}
	
	//得到父节点的方法
	private static void findParentGettingMethods(Class clazz,Set<Method> set) {
		if(clazz != null && !"object".equals(clazz.getName().toLowerCase())){
			Method[] methods = clazz.getDeclaredMethods();
			for(Method method : methods){
				if(method.getName().startsWith("get") || method.getName().startsWith("is")){
					set.add(method);
				}
			}
			findParentGettingMethods(clazz.getSuperclass(), set); //递归调用
		}		
	}
}
