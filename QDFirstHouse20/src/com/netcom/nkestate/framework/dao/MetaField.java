/**
 *<p>MetaField.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：属性值</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2013-4-27</p>
 *
 */
package com.netcom.nkestate.framework.dao;


public class MetaField {
	private String field; //VO属性名称
	private Object value; //值
	
	public MetaField(String field, Object value) {
		super();
		this.field = field;
		this.value = value;
	}
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
