/**
 * <p>VOUtil.java </p>
 *
 * <p>项目名称: 上海地产优家房屋租赁项目</p>
 * <p>系统名称: 房屋租赁管理业务系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2015-8-6<p>
 * 
 */ 
package com.netcom.nkestate.framework.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.cache.ModelCache;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.ModelUtil;
import com.netcom.nkestate.framework.vo.DBFieldVO;


public class VOUtil {

	/**
	 * 功能描述：得到VO对象中的主键和键值
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	static public List<DBFieldVO> getPrimaryKeyValue(AbstractBaseVO vo) throws Exception {
		List<DBFieldVO> list = new ArrayList<DBFieldVO>();
		List<String> keys = ModelUtil.findPrimaryKeys(vo.getClass());
		for(String key : keys) {
			Object value = ModelUtil.getValueByColumn(vo, key);
			list.add(new DBFieldVO(key,value));
		}
		return list;
	}

	/**
	 * 功能描述：比较两个同类型的vo值发生了哪些编号，主要用于数据库保存时比较差异
	 * @param nvo
	 * 新值
	 * @param ovo
	 * 原值
	 * @return String 数组，格式为：TableName:****$_$Column:***$_$OldValue:****$_$NewValue:***$_$
	 * @throws Exception
	 */
	static public List<String> compareDiffence(AbstractBaseVO nvo,AbstractBaseVO ovo) throws Exception {
		return ModelUtil.compareDiffence(nvo, ovo);
	}

	/**
	 * 功能描述：根据数据库字段名查找VO中对应的值
	 * @param vo
	 * VO对象
	 * @param column
	 * 对应的列名
	 * @return Object
	 * @throws Exception
	 */
	public static Object getValueByColumn(AbstractBaseVO vo,String column) throws Exception {
		return ModelUtil.getValueByColumn(vo, column);
	}

	/**
	 * 功能描述：VO字符字段字符集转换
	 * @param sourceVO
	 * @param sourceCharSet
	 * 来源字符集，空默认为当前字符集
	 * @param targetCharSet
	 * 目标字符集
	 * @return
	 * @throws Exception
	 */
	static public AbstractBaseVO cloneVO(AbstractBaseVO sourceVO,String sourceCharSet,String targetCharSet) throws Exception {
		AbstractBaseVO cloneVo = (AbstractBaseVO) sourceVO.getClass().newInstance();
		Method[] methods = ModelCache.getMetaMethods(sourceVO.getClass()); //
		for(Method method : methods){
			boolean hasAnnotation = method.isAnnotationPresent(DBMeta.class);
			if(hasAnnotation && (method.getName().startsWith("get") || method.getName().startsWith("is"))){
				DBMeta meta = method.getAnnotation(DBMeta.class); // 根据注解类型返回方法的指定类型注解
				String column = meta.column(); // 字段名
				String type = meta.type(); // 字段类型
				if(column != null && !column.trim().equals("")){ // 字段不允许为空
					Object value = method.invoke(sourceVO); // 值
					if(value != null){
						if("string".equalsIgnoreCase(type)){
							if(sourceCharSet != null && !"".equals(sourceCharSet)){
								value = new String(value.toString().getBytes(sourceCharSet), targetCharSet);
							}else{
								value = new String(value.toString().getBytes(), targetCharSet);
							}
						}
					}
					ModelUtil.setValue(cloneVo, column, value);
				}
			}
		}
		return cloneVo;
	}

	/**
	 * 功能描述：VO字符字段字符集转换
	 * @param sourceVO
	 * @param sourceCharSet
	 * 来源字符集，空默认为当前字符集
	 * @param targetCharSet
	 * 目标字符集
	 * @return
	 * @throws Exception
	 */
	static public AbstractBaseVO copyVO(AbstractBaseVO sourceVO,AbstractBaseVO targetVO) throws Exception {
		Method[] methods = ModelCache.getMetaMethods(sourceVO.getClass()); //
		Method[] targetMethods = ModelCache.getMetaMethods(targetVO.getClass()); //
		for(Method method : methods){
			boolean hasAnnotation = method.isAnnotationPresent(DBMeta.class);
			if(hasAnnotation && (method.getName().startsWith("get") || method.getName().startsWith("is"))){
				DBMeta meta = method.getAnnotation(DBMeta.class); // 根据注解类型返回方法的指定类型注解
				String column = meta.column(); // 字段名
				String type = meta.type(); // 字段类型
				if(column != null && !column.trim().equals("")){ // 字段不允许为空
					Object value = method.invoke(sourceVO); // 值

					if(value != null){
						for(Method targetMethod : targetMethods){
							boolean targetHasAnnotation = targetMethod.isAnnotationPresent(DBMeta.class);
							if(targetHasAnnotation && (targetMethod.getName().startsWith("get") || targetMethod.getName().startsWith("is"))){
								DBMeta targetMeta = targetMethod.getAnnotation(DBMeta.class); // 根据注解类型返回方法的指定类型注解
								String targetColumn = targetMeta.column(); // 字段名
								String targetType = targetMeta.type(); // 字段类型

								if(targetColumn.equalsIgnoreCase(column) && targetType.equalsIgnoreCase(type)){
									ModelUtil.setValue(targetVO, column, value);
									break;
								}
							}
						}
					}

				}
			}
		}
		return targetVO;
	}
}
