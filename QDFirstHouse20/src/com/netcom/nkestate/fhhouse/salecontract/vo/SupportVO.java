package com.netcom.nkestate.fhhouse.salecontract.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;

public class SupportVO extends AbstractBaseVO {
	
	private long code; //代码
	private String name; //名称
	private int status; //状态
	
	@DBMeta(column = "code", name = "代码", type = "long")
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	
	@DBMeta(column = "name", name = "名称", type = "String")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@DBMeta(column = "status", name = "状态", type = "int")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getStatusString() {
		if(status==1)
			return "有效";
		
		return "无效";
	}
	
	
}
