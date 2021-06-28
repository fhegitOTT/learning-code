/**
 *<p>DBMsg.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：数据库注解接口</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2012-3-27</p>
 *
 */
package com.netcom.nkestate.framework.dao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD}) 
public @interface DBMeta {	
	public String column();     //数据库字段名
	public String name() default "";       //字段中文名
	public String type()        default "String"; //类型String, Date, DateTime, Long, Int, Double, Float, Blob, Clob 
	public String length()      default "0";      //长度
	public String not_null()    default "false";  //是否允许为空， true/false
	public String primarykey()  default "false";  //是否是主键, true/false
	public String can_update()  default "true";   //是否能更新该字段, true/false
	public String default_Value() default ""; //默认值，如sysdate,Seq.nextval
	
	public String dict_table() default "";     //关联字典表，如common.ct_district，字典表要求有三个字段 code, name, status
	public String dict_table_code_column() default "code";
	public String dict_table_name_column() default "name";
}
