/**
 *<p>MetaStatistics</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：统计属性值</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2016-12-26</p>
 *
 */
package com.netcom.nkestate.framework.dao;


public class MetaStatistic {

	static public String Count = "count"; //计数
	static public String Sum = "sum"; //合计
	static public String Max = "max"; //最大值
	static public String Min = "min"; //最小值

	private String field = null; //统计字段名
	private String method = null; //统计方法，count, sum，max,min,avg
	private String alias = null; //别名
	private String having = null; //统计过滤

	public MetaStatistic(String field, String method, String alias) {
		super();
		this.field = field;
		this.method = method;
		this.alias = alias;
	}
	
	public MetaStatistic(String field, String method, String having, String alias) {
		super();
		this.field = field;
		this.method = method;
		this.alias = alias;
		this.having = having;
	}


	public String getField() {
		return field;
	}


	public String getMethod() {
		return method;
	}


	public String getHaving() {
		return having;
	}

	
	public String getAlias() {
		return alias;
	}


}
