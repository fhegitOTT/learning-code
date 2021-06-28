/**
 * <p>DBFieldVO.java </p>
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
package com.netcom.nkestate.framework.vo;


public class DBFieldVO {

	private String column; //字段名
	private Object value; //值

	public DBFieldVO(String column, Object value) {
		super();
		this.column = column;
		this.value = value;
	}


	public String getColumn() {
		return column;
	}


	public void setColumn(String column) {
		this.column = column;
	}


	public Object getValue() {
		return value;
	}


	public void setValue(Object value) {
		this.value = value;
	}


}
