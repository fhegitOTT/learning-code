/**
 *<p>DBModel.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：TODO</p>
 *<p>功能描述：TODO</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2012-3-29</p>
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
@Target(ElementType.TYPE) 
public @interface DBModel {
	public int SequenceID = 1 ;
	public int AssignedID = 2 ;
	public int AutoIncreaseID = 3 ;
	public int AutoIncreaseID2 = 4;  //适合sqlserver,mysql主键字段自增长，即字段是auto_increment属性
	
	public String tablename() default "";  //表名	
	//sequence序列 
	//assigned分配
	//auto-increase自增长
	public int id() default SequenceID;  //ID生成方式        
	public String sequence() default "";  //序列名
	public String description() default "";  //描述
}
