/**
 * <p>EmptyVO.java </p>
 *
 * <p>系统名称: 青岛市不动产登记系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017年3月25日<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.permit.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;


public class EmptyVO extends AbstractBaseVO {
	private String id;
	private String text;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	

}
