/**
 * <p>DBObj.java </p>
 *
 * <p>项目名称: 上海地产优家房屋租赁项目</p>
 * <p>系统名称: 房屋租赁管理业务系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2015-8-18<p>
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
@Target( { ElementType.METHOD })
public @interface MappingVO {

	public Class clazz(); //映射对象类

	public String fkColumn(); //外键字段，对于DBObj就是主键
	
	public String autoload() default "true"; //是否自动加载
}
