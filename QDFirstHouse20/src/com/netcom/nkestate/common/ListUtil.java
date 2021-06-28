package com.netcom.nkestate.common;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.cache.ModelCache;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.util.DateUtil;

public class ListUtil {

	public static String toText(Class clazz,AbstractBaseVO vo) throws Exception{
		try{
			StringBuffer sb = new StringBuffer();
			String field = null;
			Method[] methods = clazz.getDeclaredMethods();
			for(Method method : methods){ 
				if(method.getName().startsWith("get") || method.getName().startsWith("is")){
					if(method.getName().startsWith("get"))
						field = method.getName().substring(3);
					else
						field = method.getName().substring(2);
						Object value = method.invoke(vo); //值	
	
					if(value == null){
						sb.append(field + " = NULL \r\n");
					}else if(value instanceof java.util.Date){
						sb.append(field + " = " + DateUtil.formatDateTime((java.util.Date) value) + "\r\n");
					}else{
						sb.append(field + " = " + String.valueOf(value) + "\r\n");
					}
				}
			}

			Map<String, Object> map = vo.getAttribute();
			//拿到map集合中的key的Set集合
			Set<String> keySet = map.keySet();
			for(Iterator<String> it = keySet.iterator();it.hasNext();) {
				String key = it.next();
				//通过便利本Set集合的过程中就可以获取map集合中key的value
				sb.append(key + " = " + String.valueOf(map.get(key)) + "\r\n");
			}

			return sb.toString();
		}catch (Exception e){
			System.out.println("Exception at " + vo.getClass().getName() + ".toString()," + e.getMessage());
			return vo.toString();
		}
	}
	
	/**
	 * 功能描述：获取VO中对应字段名称
	 * 
	 * @param clazz
	 * @return List<DBMeta>
	 * @throws Exception
	 */
	// 解析Class对象,得到数据库字段定义
	public static List<DBMeta> findMetas(Class clazz) throws Exception {
		List<DBMeta> list = new ArrayList<DBMeta>();
		Method[] methods = ModelCache.getMetaMethods(clazz); // 缓冲模式
		for (Method method : methods) { 	
			if(method.getName().startsWith("get") || method.getName().startsWith("is")){
				DBMeta meta = method.getAnnotation(DBMeta.class); // 根据注解类型返回方法的指定类型注解
			    list.add(meta);		
			} 			
		}
		return list;
	}

	// 解析Class对象,得到数据库主键定义
	public static List<String> findPrimaryKeys(Class clazz) throws Exception {
		List<String> primaryKeys = new ArrayList<String>();
		Method[] methods = ModelCache.getMetaMethods(clazz); // 缓冲模式
		for (Method method : methods) {
			if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
				DBMeta meta = method.getAnnotation(DBMeta.class); // 根据注解类型返回方法的指定类型注解
				if ("true".equals(meta.primarykey()))
					primaryKeys.add(meta.column());
			}
		}
		return primaryKeys;
	}
	/**
	 * 功能描述：获取VO中对应字段的值
	 * 
	 * @param vo
	 * @param column
	 * @return Object
	 * @throws Exception
	 */
	// 根据数据库字段名查找VO中对应的值
	public static Object getValueByColumn(AbstractBaseVO vo,String column) throws Exception {
		//Method[] methods = getMetaMethods(vo.getClass());
		Method[] methods = ModelCache.getMetaMethods(vo.getClass()); // 缓冲模式
		for (Method method : methods) { 			
			if(method.getName().startsWith("get") || method.getName().startsWith("is")){
				DBMeta meta = method.getAnnotation(DBMeta.class); // 根据注解类型返回方法的指定类型注解
				if(column.equals(meta.column())) {
					return method.invoke(vo); // 值
				}				
			} 			
		}
		return null;
	}

	/**
	 * 功能描述：获取VO中对应字段的名称
	 * @param vo
	 * @param column
	 * @return Object
	 * @throws Exception
	 */
	// 根据数据库字段名查找VO中对应的值
	public static Object getNameByColumn(AbstractBaseVO vo,String column) throws Exception {
		//Method[] methods = getMetaMethods(vo.getClass());
		Method[] methods = ModelCache.getMetaMethods(vo.getClass()); // 缓冲模式
		for(Method method : methods) {
			if(method.getName().startsWith("get") || method.getName().startsWith("is")) {
				DBMeta meta = method.getAnnotation(DBMeta.class); // 根据注解类型返回方法的指定类型注解

				if(column.equals(meta.column())) {
					return meta.name(); // 名称
				}
			}
		}
		return null;
	}

	/**
	 * 功能描述：将rs转换成hashmap
	 * 
	 * @param rs
	 * @return hashmap
	 * @throws Exception
	 */
	public static HashMap[] listToHashMap(List list){
		try {
			if(list == null) {
				HashMap[] outMaps = new HashMap[0];
				return outMaps;
			}
			HashMap[] outMaps = new HashMap[list.size()];
			for(int i=0;i<list.size();i++){
				AbstractBaseVO vo = (AbstractBaseVO) list.get(i);
				
				List<DBMeta> listColumn = findMetas(vo.getClass());
				HashMap tempMap = new HashMap();
				for(int k=0;k<listColumn.size();k++){	
					String column = listColumn.get(k).column();
					Object obj = getValueByColumn(vo, column);
					tempMap.put(column, obj);
				}
				
				Map<String, Object> map = vo.getAttribute();
				//拿到map集合中的key的Set集合
				Set<String> keySet = map.keySet();
				for(Iterator<String> it = keySet.iterator();it.hasNext();) {
					String key = it.next();
					//通过便利本Set集合的过程中就可以获取map集合中key的value
					tempMap.put(key, map.get(key));
				}
				
				outMaps[i] = tempMap;
				//outDataObject.clear();
			}

			return outMaps;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	/**
	 * 功能描述：将rs转换成hashmap
	 * @param rs
	 * @return hashmap
	 * @throws Exception
	 */
	public static HashMap toHashMap(AbstractBaseVO vo) {
		try {
			if (vo == null) {
				HashMap outMap = new HashMap();
				return outMap;
			}
			List<DBMeta> listColumn = findMetas(vo.getClass());
			HashMap tempMap = new HashMap();
			for (int k = 0; k < listColumn.size(); k++) {
				String column = listColumn.get(k).column();
				Object obj = getValueByColumn(vo, column);
				tempMap.put(column, obj);
			}

			Map<String , Object> map = vo.getAttribute();
			//拿到map集合中的key的Set集合
			Set<String> keySet = map.keySet();
			for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
				String key = it.next();
				//通过便利本Set集合的过程中就可以获取map集合中key的value
				tempMap.put(key, map.get(key));
			}

			return tempMap;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	
}
