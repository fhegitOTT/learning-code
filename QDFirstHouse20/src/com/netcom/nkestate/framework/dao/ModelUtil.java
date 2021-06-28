/**
 *<p>ModelUtil.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：Model工具</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2013-2-17</p>
 *
 */
package com.netcom.nkestate.framework.dao;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.cache.ModelCache;
import com.netcom.nkestate.framework.util.DateUtil;

public class ModelUtil {
	
	static Logger logger = Logger.getLogger(ModelUtil.class.getName());
	
	// 解析VO对象,通过参数返回解析结果
	public static void parseVO(AbstractBaseVO vo, List<String> primarykeys , Map<DBMeta,Object> columns ) throws Exception {
		DBModel annotation = (DBModel)vo.getClass().getAnnotation(DBModel.class);		
		int ID = annotation.id(); // 主键生成方式
		Method[] methods = ModelCache.getMetaMethods(vo.getClass()); // 缓冲模式
		for(Method method : methods){
			boolean hasAnnotation = method.isAnnotationPresent(DBMeta.class);
			if(hasAnnotation && (method.getName().startsWith("get") || method.getName().startsWith("is"))){
				DBMeta meta = method.getAnnotation(DBMeta.class); // 根据注解类型返回方法的指定类型注解
				String column = meta.column(); // 字段名
				String name = meta.name(); // 中文名
				String primarykey = meta.primarykey(); // 是否主键
				String can_update = meta.can_update(); // 是否能更新
				String type = meta.type(); // 字段类型
				if (column != null && !column.trim().equals("")) { // 字段不允许为空
					Object value = method.invoke(vo); // 值
				    columns.put(meta, value);				    
				    if(primarykey.equals("true")) {
				    	primarykeys.add(column);
				    }
				}			    
			} 			
		}
	}
	
	// 解析Class对象,得到数据库字段定义
	public static List<DBMeta> findMetas(Class<AbstractBaseVO> clazz, List<String> primaryKeys) throws Exception {
		List<DBMeta> list = new ArrayList<DBMeta>();
		//Method[] methods = getMetaMethods(clazz); 	
		Method[] methods = ModelCache.getMetaMethods(clazz); // 缓冲模式
		for (Method method : methods) { 			
			boolean hasAnnotation = method.isAnnotationPresent(DBMeta.class);
			if(hasAnnotation && (method.getName().startsWith("get") || method.getName().startsWith("is"))){
				DBMeta meta = method.getAnnotation(DBMeta.class); // 根据注解类型返回方法的指定类型注解
			    list.add(meta);		
			    if("true".equals(meta.primarykey()))
			    	primaryKeys.add(meta.column());
			} 			
		}
		return list;
	}
	
	// 解析VO对象,得到主键字段数组
	public static List<String> findPrimaryKeys(Class clazz) throws Exception {
		List<String> list = new ArrayList<String>();
		//Method[] methods = getMetaMethods(clazz);
		Method[] methods = ModelCache.getMetaMethods(clazz); // 缓冲模式
		for (Method method : methods) { 			
			boolean hasAnnotation = method.isAnnotationPresent(DBMeta.class);
			if(hasAnnotation && (method.getName().startsWith("get") || method.getName().startsWith("is"))){
				DBMeta meta = method.getAnnotation(DBMeta.class); // 根据注解类型返回方法的指定类型注解
				String column = meta.column(); // 字段名
				String primarykey = meta.primarykey(); // 是否主键
				if("true".equals(primarykey))
					list.add(column);
			} 			
		}
		return list;
	}
	
	// 根据数据库字段名查找VO中对应的值
	public static Object getValueByColumn(AbstractBaseVO vo, String column) throws Exception {
		//Method[] methods = getMetaMethods(vo.getClass());
		Method[] methods = ModelCache.getMetaMethods(vo.getClass()); // 缓冲模式
		for (Method method : methods) { 			
			boolean hasAnnotation = method.isAnnotationPresent(DBMeta.class);
			if(hasAnnotation && (method.getName().startsWith("get") || method.getName().startsWith("is"))){
				DBMeta meta = method.getAnnotation(DBMeta.class); // 根据注解类型返回方法的指定类型注解
				if(column.equals(meta.column())) {
					return method.invoke(vo); // 值
				}				
			} 			
		}
		return null;
	}
	
	// 根据字典表字段，设置VO中相应的值
	public static void setDictionaryValue(AbstractBaseVO vo, String column,String value) throws Exception {
		String setMethod = "set" + column + "_Title";  //setDistrictID_Title();
		String setMethod2 = "set" + column + "_Name";  //setDistrictID_Name();
		String setMethod3 = setMethod2;
		//if(column.toUpperCase().endsWith("ID") && column.length() >= 4){
		// setMethod3 = "set" + column.substring(0, column.length() - 2) +
		// "Name"; //如 districtID=>districtName
		//}
		//Method[] methods = getSettingMethods(vo.getClass());
		Method[] methods = ModelCache.getSettingMethods(vo.getClass()); // 缓冲模式
		for(Method method : methods){			
			if (method.getName().toLowerCase().equals(setMethod.toLowerCase())
					|| method.getName().toLowerCase().equals(setMethod2.toLowerCase())
					|| method.getName().toLowerCase().equals(setMethod3.toLowerCase())){
				method.invoke(vo, value);
				return ;
			} 			
		}	
		vo.setAttribute(column.toLowerCase() +"_dict_name", value);
	}
	
	// 设置属性值
	public static int setValue(AbstractBaseVO vo, String column, Object value) throws Exception {
		String setMethod = "set" + column; //setDistrictID_Title();
		//Method[] methods = getSettingMethods(vo.getClass());
		Method[] methods = ModelCache.getSettingMethods(vo.getClass()); // 缓冲模式
		for(Method method : methods){
			if(method.getName().toLowerCase().equals(setMethod.toLowerCase())){
				method.invoke(vo, value);
				return 1;
			}
		}
		return 0;
	}
	
	// 根据数据库字段返回对应的VO属性名称
	public static String getFieldNameByColumn(Class clazz, String column) {
		//Method[] methods = getMetaMethods(clazz);
		Method[] methods = ModelCache.getMetaMethods(clazz); // 缓冲模式
		for (Method method : methods) { 			
			boolean hasAnnotation = method.isAnnotationPresent(DBMeta.class);
			if(hasAnnotation && (method.getName().startsWith("get") || method.getName().startsWith("is"))){
				DBMeta meta = method.getAnnotation(DBMeta.class); // 根据注解类型返回方法的指定类型注解
				if(column.equals(meta.column())) {
					String filedName = method.getName().substring(3);
					filedName = filedName.substring(0,1).toLowerCase() + filedName.substring(1);
					return filedName;
				}				
			} 			
		}
		return null;
	}
	
	// 根据VO属性名称得到对应的数据库字段
	public static DBMeta getColumnByField(Class clazz, String field) {
		//Method[] methods = getMetaMethods(clazz);
		Method[] methods = ModelCache.getMetaMethods(clazz); // 缓冲模式
		for (Method method : methods) { 	
			boolean hasAnnotation = method.isAnnotationPresent(DBMeta.class);
			if(hasAnnotation && (method.getName().startsWith("get") || method.getName().startsWith("is")) 
					&& method.isAnnotationPresent(DBMeta.class)
					// &&
					// method.getName().toLowerCase().endsWith(field.toLowerCase()))
					// { //get方法名称＝filed
					&& (method.getName().toLowerCase().equals("get" + field.toLowerCase()) || 
							method.getName().toLowerCase().equals("is" + field.toLowerCase()))) { // get方法名称＝filed
				DBMeta meta = method.getAnnotation(DBMeta.class); // 根据注解类型返回方法的指定类型注解
				return meta;
			}			
		}
		return null;
	}
	
	// 查找映射数据库字段的get方法
	public static Method[] getMetaMethods(Class clazz) {
		Set<Method> set = new HashSet<Method>();		
		Method[] methods = clazz.getDeclaredMethods(); 			
		for(Method method : methods){
			//boolean hasAnnotation = method.isAnnotationPresent(DBMeta.class);
			boolean hasAnnotation = method.isAnnotationPresent(DBMeta.class) || method.isAnnotationPresent(MappingVO.class);
			if(hasAnnotation && (method.getName().startsWith("get") || method.getName().startsWith("is"))){
				set.add(method);				
			}
		}
		// 查询父类的方法
		findParentMetaMethods(clazz.getSuperclass(), set);
		methods = new Method[set.size()];			
		Object[] objs = set.toArray();
		for(int i = 0; i < objs.length; i++){
			methods[i] = (Method) objs[i];
		}
		return methods;		
	}

	// 得到父节点的方法
	private static void findParentMetaMethods(Class clazz, Set<Method> set) {		
		if(clazz != null && !"object".equals(clazz.getName().toLowerCase())){
			Method[] methods = clazz.getDeclaredMethods();
			for(Method method : methods){
				//boolean hasAnnotation = method.isAnnotationPresent(DBMeta.class);
				boolean hasAnnotation = method.isAnnotationPresent(DBMeta.class) || method.isAnnotationPresent(MappingVO.class);
				if(hasAnnotation && (method.getName().startsWith("get") || method.getName().startsWith("is"))){
					set.add(method);					
				}
			}
			findParentMetaMethods(clazz.getSuperclass(), set); // 递归调用
		}	
	}
	
	// 查找映射数据库字段的set方法
	public static Method[] getSettingMethods(Class clazz) {
		Set<Method> set = new HashSet<Method>();
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method : methods){
			if(method.getName().startsWith("set")){
				set.add(method);
			}
		}
		
		// 查询父类的方法
		findParentSettingMethods(clazz.getSuperclass(), set);
		methods = new Method[set.size()];
		Object[] objs = set.toArray();
		for(int i = 0; i < objs.length; i++){
			methods[i] = (Method) objs[i];
		}
		
		return methods;
	}

	// 得到父节点的set方法
	private static void findParentSettingMethods(Class clazz, Set<Method> set) {
		if(clazz != null && !"object".equals(clazz.getName().toLowerCase())){
			Method[] methods = clazz.getDeclaredMethods();
			for(Method method : methods){
				if(method.getName().startsWith("set")){
					set.add(method);
				}
			}
			findParentSettingMethods(clazz.getSuperclass(), set); // 递归调用
		}
	}
	
	// 查找全部的get方法
	public static Method[] getGettingMethods(Class clazz) {
		Set<Method> set = new HashSet<Method>();
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method : methods){
			if(method.getName().startsWith("get") || method.getName().startsWith("is")){
				set.add(method);
			}
		}
		// 查询父类的方法
		findParentGettingMethods(clazz.getSuperclass(), set);
		if(methods.length < set.size()){
			methods = new Method[set.size()];
			Object[] objs = set.toArray();
			for(int i = 0; i < objs.length; i++){
				methods[i] = (Method) objs[i];
			}
		}
		return methods;
	}
	
	// 得到父节点的方法
	private static void findParentGettingMethods(Class clazz, Set<Method> set) {
		if(clazz != null && !"object".equals(clazz.getName().toLowerCase())){
			Method[] methods = clazz.getDeclaredMethods();
			for(Method method : methods){
				if(method.getName().startsWith("get") || method.getName().startsWith("is")){
					set.add(method);
				}
			}
			findParentGettingMethods(clazz.getSuperclass(), set); // 递归调用
		}
	}

	/**
	 * 功能描述：比较两个同类型的vo值发生了哪些编号，主要用于数据库保存时比较差异
	 * 
	 * @param nvo
	 *            新值
	 * @param ovo
	 *            原值
	 * @return String
	 *         数组，格式为：TableName:****$_$Column:***$_$OldValue:****$_$NewValue
	 *         :***$_$
	 * @throws Exception
	 */	
	static public List<String> compareDiffence(AbstractBaseVO nvo, AbstractBaseVO ovo) throws Exception {
		if(!nvo.getClass().getName().equals(ovo.getClass().getName()))
			throw new Exception("对象类型不一致,分别是" + nvo.getClass() + "和"
					+ ovo.getClass().getName());
		if(!nvo.getClass().isAnnotationPresent(DBModel.class))
			throw new Exception(nvo.getClass().getName() + "缺失DBModel配置信息");
		
		List<String> list = new ArrayList<String>();
		DBModel annotation = (DBModel)nvo.getClass().getAnnotation(DBModel.class);
		String tableName = annotation.tablename();
		
		try{
			Method[] methods = getMetaMethods(nvo.getClass());
			for(Method method : methods){
				boolean hasAnnotation = method.isAnnotationPresent(DBMeta.class);
				if(hasAnnotation && (method.getName().startsWith("get") || method.getName().startsWith("is"))){
					DBMeta meta = method.getAnnotation(DBMeta.class); // 根据注解类型返回方法的指定类型注解
					if ("false".equals(meta.can_update())) // 不能更新的字段
						continue;
					String column = meta.column();
					Object nValue = method.invoke(nvo, null); // 新值
					Object oValue = method.invoke(ovo, null); // 原值
					Object o = nValue == null ? oValue : nValue;
					
					if( (nValue == null && oValue == null) || (nValue == null && "".equals(oValue)) || ("".equals(nValue) && oValue == null)
							|| ("".equals(nValue) && "".equals(oValue)) || nValue instanceof byte[]){
						continue;
					} else if (nValue instanceof java.lang.Long) { // 基本数据类别
						if( ( ((Long) nValue)).longValue() != ( ((Long) oValue)).longValue())
							list.add("TableName:" + tableName + "$_$Column:" + column + "$_$OldValue:" + String.valueOf(oValue) + "$_$NewValue:" + String.valueOf(nValue) + "$_$");
					} else if (nValue instanceof java.lang.Double) { // 基本数据类别
						if( ( ((Double) nValue)).doubleValue() != ( ((Double) oValue)).doubleValue())
							list.add("TableName:" + tableName + "$_$Column:" + column + "$_$OldValue:" + String.valueOf(oValue) + "$_$NewValue:" + String.valueOf(nValue) + "$_$");
					} else if (nValue instanceof java.lang.Integer) { // 基本数据类别
						if( ( ((Integer) nValue)).intValue() != ( ((Integer) oValue)).intValue())
							list.add("TableName:" + tableName + "$_$Column:" + column + "$_$OldValue:" + String.valueOf(oValue) + "$_$NewValue:" + String.valueOf(nValue) + "$_$");
					} else if (nValue instanceof java.lang.Float) { // 基本数据类别
						if( ( ((Float) nValue)).floatValue() != ( ((Float) oValue)).floatValue())
							list.add("TableName:" + tableName + "$_$Column:" + column + "$_$OldValue:" + String.valueOf(oValue) + "$_$NewValue:" + String.valueOf(nValue) + "$_$");	
					} else if(nValue instanceof java.util.Date) {
						String vn = nValue == null ? "" : DateUtil.formatDate((Date) nValue);
						String vo = oValue == null ? "" : DateUtil.formatDate((Date) oValue);
						if(!vn.equals(vo))
							list.add("TableName:" + tableName + "$_$Column:" + column + "$_$OldValue:" + vo + "$_$NewValue:" + vn + "$_$");
					}else if(nValue instanceof java.lang.String){
						nValue = nValue == null ? "" : nValue;
						oValue = oValue == null ? "" : oValue;
						if(!oValue.equals(nvo))
							list.add("TableName:" + tableName + "$_$Column:" + column + "$_$OldValue:" + String.valueOf(oValue) + "$_$NewValue:" + String.valueOf(nValue) + "$_$");
					}else{
						//
					}
				}
			}
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	/**
	 * 功能描述：将VO对象转换为String，根据get方法得到的值来转换
	 * 
	 * @param vo
	 * @return fieldname=***\Tfieldname=***\T....
	 */
	static public String voToString(AbstractBaseVO vo) {
		StringBuffer sb = new StringBuffer();
		try{
			Method[] methods = vo.getClass().getDeclaredMethods(); 	
			for(Method method : methods) {
				System.out.println("method:" + method.getName() + ",Accessed:" + method.isAccessible());
				if(method.getName().startsWith("get")){
					Object value = method.invoke(vo, null);
					String fieldname = method.getName();
					if(fieldname.startsWith("get")){
						fieldname = fieldname.substring("get".length());
					}else if(fieldname.startsWith("is")){
						//fieldname = fieldname.substring("is".length());
					}
					
					if(value == null){
						sb.append(fieldname + "=" + value);
					}else if(value instanceof java.util.Date){
						sb.append(fieldname + "=" + DateUtil.formatDateTime((java.util.Date) value));					
					}else if(value instanceof java.util.List){
						sb.append(fieldname + "(List).size=" + ((java.util.List) value).size());
					}else if(value instanceof byte[]){
						sb.append(fieldname + "(byte[])=" + (new String((byte[]) value)));
					} else {
						sb.append(fieldname + "=" + value);
					}
					sb.append("\t");
				}
			}
			
		}catch (Exception e){
			logger.error(e);
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	// VO属性拷贝，方法名称一致时拷贝，如将fromVO.getDistrictID()的值拷贝到toVO.setDistrictID()中
	static public int copy(AbstractBaseVO fromVO, AbstractBaseVO toVO) {
		int count = 0;
		try{
			Method[] getMethods = getGettingMethods(fromVO.getClass());
			Method[] setMethods = ModelUtil.getSettingMethods(toVO.getClass());
			
			for(Method getMethod : getMethods){
				String method_from = getMethod.getName();
				String method_to = "set" + method_from.substring("get".length());
				for(Method setMethod : setMethods){
					if(setMethod.getName().equals(method_to)){
						try{
							setMethod.invoke(toVO, getMethod.invoke(fromVO, null));
							count++;
						}catch (Exception e){
							//
						}
						break;
					}
				}
			}
			
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			return -1;
		}
		return count;
	}
	
	// 解析VO对象,通过参数返回非空的对象值
	// 返回值是主键编号
	public static String getNotNullFields(AbstractBaseVO vo,List<MetaField> fields) throws Exception {
		String id = null;
		DBModel annotation = (DBModel)vo.getClass().getAnnotation(DBModel.class);		
		Method[] methods = ModelCache.getMetaMethods(vo.getClass()); // 缓冲模式
		for(Method method : methods){
			boolean hasAnnotation = method.isAnnotationPresent(DBMeta.class);
			if(hasAnnotation && (method.getName().startsWith("get") || method.getName().startsWith("is"))){
				Object value = method.invoke(vo); // 值
				if(value == null)
					continue;
				if(value instanceof java.lang.Integer && ( ((Integer) value).intValue() == 0)){
					continue;
				}
				if(value instanceof java.lang.Long && ( ((Long) value).longValue() == 0)){
					   continue;
				}
				if(value instanceof java.lang.Double && ( ((Double) value).doubleValue() == 0)){
					continue;
				}
				
				DBMeta meta = method.getAnnotation(DBMeta.class); // 根据注解类型返回方法的指定类型注解
				String column = meta.column(); // 字段名
				String name = meta.name(); // 中文名
				String primarykey = meta.primarykey(); // 是否主键
				String can_update = meta.can_update(); // 是否可更新
				
				if (column != null && !column.trim().equals("")) { // 字段不允许为空
				    if(primarykey.equals("true")) {
						id = String.valueOf(value);
				    }
				}	
				
				if("false".equals(can_update) )
					continue;

				String methodname = method.getName();
				if(methodname.startsWith("get"))
					methodname = methodname.substring("get".length());
				else
					methodname = methodname.substring("is".length());

				fields.add(new MetaField(methodname, value));
						    
			} 			
		}
		return id;
	}
	
		
}
