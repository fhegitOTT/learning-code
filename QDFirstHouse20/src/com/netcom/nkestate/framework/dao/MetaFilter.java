/**
 *<p>MetaFilter.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理系统</p>
 *<p>功能描述：SQL过滤对象</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2013-2-28</p>
 *
 */
package com.netcom.nkestate.framework.dao;

public class MetaFilter {
	
	private String field;   //VO属性名称
	private String comparer; //比较器 >,>=,=,<,<=,like,is
	private Object value; //值，日期
	
	public MetaFilter(String field, String comparer, Object value) {
		super();
		this.field = field;
		this.comparer = comparer;
		this.value = value;
	}
	
	public String getField() {
		return field;
	}	
	public void setField(String field) {
		this.field = field;
	}
	
	public String getComparer() {
		return comparer;
	}	
	public void setComparer(String comparer) {
		this.comparer = comparer;
	}
	
	public Object getValue() {
		return value;
	}	
	public void setValue(Object value) {
		this.value = value;
	}	
	
}
