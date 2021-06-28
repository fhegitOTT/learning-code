/**
 *<p>MetaOrder.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：排序字段</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2013-4-27</p>
 *
 */
package com.netcom.nkestate.framework.dao;

public class MetaOrder {
	static public String ASC = "asc";
	static public String DESC = "desc";
	
	private String field; //VO属性名称
	private String order; //排序方式  asc, desc	
	
	public MetaOrder(String field, String order) {
		super();
		this.field = field;
		this.order = order;
	}
	
	public MetaOrder() {
		super();
	}

	
	public String getField() {
		return field;
	}

	public String getOrder() {
		return order;
	}
	
	public void setField(String field) {
		this.field = field;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	
}
