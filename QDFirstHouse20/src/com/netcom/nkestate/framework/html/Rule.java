/**
 * <p>Rule.java </p>
 *
 * <p>项目名称: 上海地产优家房屋租赁项目</p>
 * <p>系统名称: 房屋租赁管理业务系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Wangjinjie</p>
 * <p>创建时间: 下午05:11:46<p>
 * 
 */ 
package com.netcom.nkestate.framework.html;


public class Rule {
	private String field;
	private String operator;
	
	public String getField() {
		return field;
	}

	
	public void setField(String field) {
		this.field = field;
	}

	
	public String getOperator() {
		return operator;
	}

	
	public void setOperator(String operator) {
		this.operator = operator;
	}

	
	public String getValue() {
		return value;
	}

	
	public void setValue(String value) {
		this.value = value;
	}

	private String value;
	
	public Rule(String field,String operator,String value){
		this.field = field;
		this.operator = operator;
		this.value = value;
	}
	
	public boolean checkValue(String newValue){
		if(operator.equals("==") || operator.equals("=")){
			if(value.equals(newValue)){
				return true;
			}else{
				return false;
			}
		}else if(operator.equals("<>") || operator.equals("!=")){
			if(value.equals(newValue)){
				return false;
			}else{
				return true;
			}
		}
		
		return true;
	}
}
