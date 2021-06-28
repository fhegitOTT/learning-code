package com.netcom.nkestate.framework.util;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class StringUtil {

	public static String escapeHtml(String str) {
		str = replace(str, "<", "&lt;");
		str = replace(str, ">", "&gt;");
		str = replace(str, " ", "&nbsp;");
		str = replace(str, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		str = replace(str, "\r\n", "<br>");
		return str;
	}

	public static String replace(String text,String repl,String with) {
		return replace(text, repl, with, -1);
	}

	public static String replace(String text,String repl,String with,int max) {
		if(text == null)
			return null;
		StringBuffer buffer = new StringBuffer(text.length());
		int start = 0;
		for(int end = 0; (end = text.indexOf(repl, start)) != -1;){
			buffer.append(text.substring(start, end)).append(with);
			start = end + repl.length();
			if(--max == 0)
				break;
		}

		buffer.append(text.substring(start));
		return buffer.toString();
	}

	public static String getHumpName(String strHungaryName) {
		if(strHungaryName.length() == 0)
			return null;
		int pFirst = 0;
		StringBuffer hump = new StringBuffer();
		for(int pIndex = pFirst; pIndex < strHungaryName.length();){
			while (pIndex < strHungaryName.length()){
				if(!Character.isUpperCase(strHungaryName.charAt(pIndex)))
					break;
				pIndex++;
			}
			if(pIndex - pFirst > 1){
				hump.append(strHungaryName.substring(pFirst, pIndex - 1).toLowerCase());
				hump.append('_');
				pFirst = pIndex - 1;
			}
			for(; pIndex < strHungaryName.length(); pIndex++)
				if(!Character.isLowerCase(strHungaryName.charAt(pIndex)))
					break;

			hump.append(strHungaryName.substring(pFirst, pIndex).toLowerCase());
			hump.append('_');
			pFirst = pIndex;
		}

		if(hump.charAt(hump.length() - 1) == '_')
			return hump.substring(0, hump.length() - 1);
		else
			return hump.toString();
	}

	public static String repeat(char ch,int n) {
		if(n < 0)
			return null;
		if(n == 0)
			return "";
		char buf[] = new char[n];
		for(int i = 0; i < n; i++)
			buf[i] = ch;

		return new String(buf);
	}

	public static String[] split(String string,String delim) {
		if(string == null)
			return null;
		if(delim == null || delim.length() == 0)
			return (new String[] { string });
		ArrayList list = new ArrayList();
		int p1 = 0;
		for(int p2 = 0; (p2 = string.indexOf(delim, p1)) >= 0;){
			list.add(string.substring(p1, p2));
			p1 = p2 + delim.length();
		}

		list.add(string.substring(p1));
		String a[] = new String[list.size()];
		for(int i = 0; i < list.size(); i++)
			a[i] = (String) list.get(i);

		return a;
	}

	public static String toGBK(String str) {
		if(str == null)
			return null;
		try{
			return new String(str.getBytes("ISO8859-1"), "GBK");
		}catch (Exception e){
			return "";
		}
	}

	public static String toISO8859(String str) {
		if(str == null)
			return null;
		try{
			return new String(str.getBytes("GBK"), "ISO8859-1");
		}catch (Exception e){
			return "";
		}
	}

	public static String GBKtoUTF8(String str) {
		if(str == null)
			return null;
		try{
			return new String(str.getBytes("GBK"), "UTF-8");
		}catch (Exception e){
			return "";
		}
	}
	
	public static String UTF8toGBK(String str) {
		if(str == null)
			return null;
		try{
			return new String(str.getBytes("UTF-8"), "GBK");
		}catch (Exception e){
			return "";
		}
	}


	/**
	 * 功能描述：自动补零
	 * @param l
	 * @return
	 */
	public static String repeat(long l){
		// 得到一个NumberFormat的实例  
        NumberFormat nf = NumberFormat.getInstance();  
		// 设置是否使用分组  
        nf.setGroupingUsed(false);  
		// 设置最大整数位数  
        nf.setMaximumIntegerDigits(10);  
		// 设置最小整数位数  
        nf.setMinimumIntegerDigits(10);
        
        return nf.format(l);
	}

	/**
	 * 功能描述：自动补零
	 * @param l
	 * @return
	 */
	public static String repeat(long l,int bigInt,int smallInt){
		// 得到一个NumberFormat的实例  
        NumberFormat nf = NumberFormat.getInstance();  
		// 设置是否使用分组  
        nf.setGroupingUsed(false);  
		// 设置最大整数位数  
        nf.setMaximumIntegerDigits(bigInt);  
		// 设置最小整数位数  
        nf.setMinimumIntegerDigits(smallInt);
        
        return nf.format(l);
	}

	/**
	 * 功能描述：将double类型转换为String
	 * @param d
	 * @return
	 */
	public static String  convertDouble(double d){
		//DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");//格式化设置  
		DecimalFormat decimalFormat = new DecimalFormat("0.00");//格式化设置  
		return decimalFormat.format(d); 
	}
	
	static public boolean isBlank(String str) {
		if(str==null || str.trim().equals(""))
			return true;
		return false;
	}

	/**
	 * 对传入的字符串进行补0操作，返回宽度为width的字符串。 如果传入的字符串为null，则返回null; 如果要求返回的字符串长度小于等于0，则返回null; 如果要求返回的字符串长度小于等于本身传入的字符串长度，则返回原字符串。
	 * @param str
	 * @param width
	 * @return String
	 */
	public static String getFullwidthStr(String str,int width) {
		String result = null;
		String zero = "0";
		if(width <= 0)
			return null;
		if(str != null){
			int strLen = str.length();
			if(strLen >= width){
				return str;
			}else{
				result = str;
				for(int i = 1; i <= width - strLen; i++){
					result = zero.concat(result);
				}
			}
		}else{
			return null;
		}
		return result;
	}

	/**
	 * 功能描述：数字转换成字符串
	 * @param num
	 * @return
	 */
	public static String getDoubleStr(double num) {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		String dstr = decimalFormat.format(num);
		return dstr;
	}
	
	public static String toEmpty(Object val) {
		String outVal = "";
		if(!"".equals(val) && val != null && !"null".equals(val)) {
			outVal = String.valueOf(val);
		}
		return outVal;
	}
}
