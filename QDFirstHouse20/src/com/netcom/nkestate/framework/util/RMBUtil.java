/**
 *<p>RMBUtil.java</p>
 *
 *<p>项目名称：银川房产项目</p>
 *<p>系统名称：存量房交易资金监管系统</p>
 *<p>功能描述：人民币转换工具</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: Administrator</p>
 *<p>创建日期: 2014-7-30</p>
 *
 */
package com.netcom.nkestate.framework.util;

import java.math.BigDecimal;

public class RMBUtil {

	private static String bigLetter[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

	private static String unit[] = { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万" };

	private static String unitSmall[] = { "分", "角" };

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String[] testStrs = { "1234567888888.100"/* 大十进制数 */, "1001"/* 没有小数，且含相邻的两个零 */, "101005.1"/* 零在单位所处的位置 */, "1010"/* 零在元位 */};
		System.out.println(new BigDecimal(testStrs[2]).toString());
		System.out.println(trans(testStrs[2]));
	}  



	public static String trans(String str) {
		if(null == str || ("").equals(str)){
			return "";
		}
		BigDecimal dec = check2BigDecimal(str);
		if(dec == null){
			return null;
		}else if("0".equals(dec.toString())){
			return null;
		}
		return toBigRMB(Double.parseDouble(str));		
	}

	public static String transInt(String str) {
		if("0".equals(str))
			return "";

		StringBuffer newS = new StringBuffer();
		char[] cArr = str.toCharArray();

		for(int i = 0; i < cArr.length; i++){
			String u = unit[cArr.length - i - 1];
			int position = "元万亿".indexOf(u);

			// 遇到零，特殊处理  
			if(cArr[i] == '0'){
				// 如果零在特殊位置上：比如个位，万位，亿位  
				if(position >= 0){
					// 如果零在个位：需要加上单位“元”  
					if(position == 0){
						newS.append(unit[cArr.length - i - 1]);
						continue;
					}else{// 如果零不在个位  
						// 零不在个位，且这个位后，紧跟着一个零：只需要加上单位  
						if(i < cArr.length - 1 && cArr[i + 1] == '0'){
							newS.append(unit[cArr.length - i - 1]);
							continue;
						}else{// 零不在个位，且这个位后，没有紧跟着一个零：需要加上单位和大写数字(注意次序：单位在前，大写数字在后)  
							newS.append(unit[cArr.length - i - 1]);
							newS.append(bigLetter[cArr[i] - 48]);
							continue;
						}
					}
				}else{// 零不在特殊位置上  
					// 零不在特殊位置上，且这个位后，紧跟着一个零：忽略这个零  
					if(i < cArr.length - 1 && cArr[i + 1] == '0'){
						continue;
					}
					// 零不在特殊位置上，且这个位后，没有紧跟着一个零：需要加上大写数字  
					newS.append(bigLetter[cArr[i] - 48]);
					continue;
				}

			}

			// 不是零，常规处理  
			newS.append(bigLetter[cArr[i] - 48]);
			newS.append(unit[cArr.length - i - 1]);

		}

		return newS.toString();
	}

	public static String transSmall(String str) {
		//因为最多精确到分，所有str的长度最多位2  

		if(str == null || "".equals(str))
			return "";

		char[] cArr = str.toCharArray();
		if(str.indexOf("0") == -1){//不含有零，常规处理  
			StringBuffer newS = new StringBuffer();
			for(int i = 0; i < cArr.length; i++){
				newS.append(bigLetter[cArr[i] - 48]);
				newS.append(unitSmall[cArr.length - i - 1]);
			}
			return newS.toString();
		}else{//含有零，分情况考虑  
			if(cArr[0] == '0' && cArr[cArr.length == 2 ? 1 : 0] == '0')//两个都为零  
				return "";
			if(cArr[0] == '0' && cArr.length == 2 && cArr[1] != '0')//角位为零，分位不为零  
				return bigLetter[0] + bigLetter[cArr[1] - 48] + unitSmall[0];
			if(cArr[0] != '0' && cArr.length == 2 && cArr[1] == '0')//分位为零，角位不为零  
				return bigLetter[cArr[0] - 48] + unitSmall[1];
		}

		return "";
	}

	public static BigDecimal check2BigDecimal(String str) {
		try{
			double f = Double.parseDouble(str); // 0.0 0.1 -5 ""  
			if(f < 0)
				System.out.println("输入数据小于0，请检查！");
			return new BigDecimal(f);
		}catch (NumberFormatException e){
			e.printStackTrace();
		}

		return null;
	}

	///DAOBIN ADD
	/**
	 * 人民币转成大写
	 * @param value
	 * @return String
	 */
	public static String toBigRMB(double value) {
		if(value == 0){
			return "零元整";
		}
		char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
		char[] vunit = { '万', '亿' }; // 段名表示
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
		long midVal = (long) (value * 100); // 转化成整形
		String valStr = String.valueOf(midVal); // 转化成字符串

		String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
		String rail = valStr.substring(valStr.length() - 2); // 取小数部分

		String prefix = ""; // 整数部分转化的结果
		String suffix = ""; // 小数部分转化的结果
		// 处理小数点后面的数
		if(rail.equals("00")){ // 如果小数部分为0
			suffix = "整";
		}else{
			suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
		char zero = '0'; // 标志'0'表示出现过0
		byte zeroSerNum = 0; // 连续出现0的次数
		for(int i = 0; i < chDig.length; i++){ // 循环处理每个数字
			int idx = (chDig.length - i - 1) % 4; // 取段内位置
			int vidx = (chDig.length - i - 1) / 4; // 取段位置
			if(chDig[i] == '0'){ // 如果当前字符是0
				zeroSerNum++; // 连续0次数递增
				if(idx == 0 && vidx > 0 && zeroSerNum < 4){
					prefix += vunit[vidx - 1];
					zero = '0';
				}else if(zero == '0'){ // 标志
					zero = digit[0];
				}
				continue;
			}
			zeroSerNum = 0; // 连续0次数清零
			if(zero != '0'){ // 如果标志不为0,则加上,例如万,亿什么的
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0']; // 转化该数字表示
			if(idx > 0)
				prefix += hunit[idx - 1];
			if(idx == 0 && vidx > 0){
				prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
			}
		}

		if(prefix.length() > 0)
			prefix += '元'; // 如果整数部分存在,则有元的字样
		return prefix + suffix; // 返回正确表示
	} 
}
