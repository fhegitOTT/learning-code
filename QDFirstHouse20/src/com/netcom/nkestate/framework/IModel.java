/**
 *<p>IModel.java</p>
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

import java.util.Map;

public interface IModel {
	public String toString();
	
	public void setAttribute(String name,Object obj);
	
	public Object getAttribute(String name);

	public Map<String , Object> getSettings();

	public void unset(String field);

	public void set(String field,Object value);

	public boolean isSet(String field);
}
