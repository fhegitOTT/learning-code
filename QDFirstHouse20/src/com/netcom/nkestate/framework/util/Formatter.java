/**
 *<p>Formater.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：TODO</p>
 *<p>功能描述：TODO</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2012-8-27</p>
 *
 */
package com.netcom.nkestate.framework.util;

import java.text.DecimalFormat;

public class Formatter {
	//取所有整数部分
	static public String formatNormal(double d) {
		return (new DecimalFormat("#.00")).format(d).toString();
	}
	
	public static void main(String[]args){
	/*	
	　　　　doublepi=3.1415927;　//圆周率
	　　　　//取一位整数
	　　　　System.out.println(newDecimalFormat("0").format(pi));　　　//3
	　　　　//取一位整数和两位小数
	　　　　System.out.println(newDecimalFormat("0.00").format(pi));　//3.14
	　　　　//取两位整数和三位小数，整数不足部分以0填补。
	　　　　System.out.println(newDecimalFormat("00.000").format(pi));// 03.142
	　　　　//取所有整数部分
	　　　　System.out.println(newDecimalFormat("#").format(pi));　　　//3
	　　　　//以百分比方式计数，并取两位小数
	　　　　System.out.println(newDecimalFormat("#.##%").format(pi));　//314.16%
	　　
	　　　　longc=299792458;　　//光速
	　　　　//显示为科学计数法，并取五位小数
	　　　　System.out.println(newDecimalFormat("#.#####E0").format(c));　//2.99792E8
	　　　　//显示为两位整数的科学计数法，并取四位小数
	　　　　System.out.println(newDecimalFormat("00.####E0").format(c));　//29.9792E7
	　　　　//每三位以逗号进行分隔。
	　　　　System.out.println(newDecimalFormat(",###").format(c));　　　//299,792,458
	　　　　//将格式嵌入文本
	　　　　System.out.println(newDecimalFormat("光速大小为每秒,###米。").format(c));
	　　}
	*/
	}

	/**
	 * 功能描述：TODO
	 * @param obj
	 * @param formatting
	 * @return
	 */
	public static String formatDecimal(Object obj,String formatting) {
		if(formatting == null || "".equals(formatting)){
			formatting = "#,##0.00";
		}
		if(obj != null && !"".equals(obj)){
			DecimalFormat df = new DecimalFormat(formatting);
			obj = df.format(obj);
			return String.valueOf(obj);
		}
		return "";
	}
}
