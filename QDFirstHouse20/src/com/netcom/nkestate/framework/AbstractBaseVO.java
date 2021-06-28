/**
 *<p>AbstractBaseVO.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：基础类VO</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2011-11-10</p>
 *
 */
package com.netcom.nkestate.framework;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.netcom.nkestate.framework.util.DateUtil;

public class AbstractBaseVO implements IModel {
	
	protected Map<String , Object> map = new HashMap<String , Object>();
	
	public String toText() {
		try{
			StringBuffer sb = new StringBuffer();
			String field = null;
			Method[] methods = this.getClass().getDeclaredMethods(); 
			for(Method method : methods){ 
				if(method.getName().startsWith("get") || method.getName().startsWith("is")){
					if(method.getName().startsWith("get"))
						field = method.getName().substring(3);
					else
						field = method.getName().substring(2);
					Object value = method.invoke(this); //值		
					if(value == null){
						sb.append(field + " = NULL \r\n");
					}else if(value instanceof java.util.Date){
						sb.append(field + " = " + DateUtil.formatDateTime((java.util.Date) value) + "\r\n");
					}else{
						sb.append(field + " = " + String.valueOf(value) + "\r\n");
					}
				}
			}
			return sb.toString();
		}catch (Exception e){
			System.out.println("Exception at " + this.getClass().getName() + ".toString()," + e.getMessage());
			return this.toString();
		}
	}
	
	public void setAttribute(String name,Object obj) {
		map.put(name.toLowerCase(), obj);
	}
	
	public Object getAttribute(String name) {
		return map.get(name.toLowerCase());
	}
	
	public Map<String , Object> getAttribute() {
		return map;
	}
	

	protected Map<String , Object> settings = new HashMap<String , Object>();

	public Map<String , Object> getSettings() {
		return settings;
	}

	public void unset(String field) {
		settings.remove(field);
	}

	public void set(String field,Object value) {
		settings.put(field, value);
	}

	public boolean isSet(String field) {
		return settings.containsKey(field);
	}

	public void clearSet() {
		settings.clear();
	}
}
