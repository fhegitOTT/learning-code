/**
 *<p>DictVO.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：字典表VO</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2011-11-22</p>
 *
 */
package com.netcom.nkestate.framework.vo;


import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;

public class DictVO extends AbstractBaseVO  {

	private int code; //代码
	private String name; //名称
	private int status; //状态
	
	@DBMeta(column = "code", name = "代码", type = "int")
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
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
	
	static public void main(String[] args) throws Exception {
		DictVO vo = new DictVO();
		vo.setCode(21);
		vo.setName("common.ct_district");
		vo.setStatus(1);
		System.out.println(vo.toString());
	}
	
	
}
