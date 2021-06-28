/**
 *<p>Column.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：数据库字段</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2013-1-24</p>
 *
 */
package com.netcom.nkestate.framework.dao;

public class Column {
	private String column; //字段名
	private String type;   //类型 
	private Object value;  //值
	
	public Column(String column, String type, Object value) {
		this.column = column;
		this.type = type;
		this.value = value;
	}
	
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	
	
}
