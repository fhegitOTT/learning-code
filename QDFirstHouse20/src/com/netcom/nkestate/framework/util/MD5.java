/**
 * <p>MD5.java </p>
 *
 * <p>系统名称: 青岛房屋安全使用管理系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 上午10:00:33<p>
 * 
 */ 
package com.netcom.nkestate.framework.util;

import java.security.MessageDigest;


public class MD5 {
	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "#", "@", "a", "Z", "c", "X", "$", "Q" };

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for(int i = 0; i < b.length; ++i){
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if(n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String encode(String origin) {
		String resultString = null;
		try{
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		}catch (Exception localException){
		}
		return resultString;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(encode("111111"));
	}
}
