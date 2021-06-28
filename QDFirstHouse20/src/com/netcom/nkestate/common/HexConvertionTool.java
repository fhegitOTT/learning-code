package com.netcom.nkestate.common;

import java.io.ByteArrayOutputStream;

public class HexConvertionTool{
	public static void main(String[] args) {
		try {
			byte aa[] = "123中文".getBytes("GBK");

			System.out.println(encode("中文"));
			System.out.println(decode("0E000000"));
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	// 转化十六进制编码为字符串
	public static String toStringHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2 , i * 2 + 2) , 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			s = new String(baKeyword , "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	/*
	 * 16进制数字字符集
	 */
	private static String hexString = "0123456789ABCDEF";

	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String encode(String str) {
		if (str == null)
			return null;
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuffer sb = new StringBuffer(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	public static String encode(int num) {
		// 根据默认编码获取字节数组
		byte[] bytes = valueIntegerToByte(num);
		StringBuffer sb = new StringBuffer(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/*
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 */
	public static String decode(String bytes) {
		if (bytes == null)
			return null;

		ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes
					.charAt(i + 1))));
		return new String(baos.toByteArray());
	}

	/**
	 * 以2位字符串为一个byte，拆分
	 * @return
	 */
	public static byte[] decodeStringToByte(String bytes) {
		int len = bytes.length();
		if (len % 2 != 0)
			return null;
		byte[] result = new byte[len / 2];
		for (int i = 0; i < bytes.length(); i += 2) {
			int c1 = getByteLowerFourValue(bytes.charAt(i));// 低4位
			int c2 = getByteLowerFourValue(bytes.charAt(i + 1));// 高四位
			result[i / 2] = (byte) ((byte) c1 << 4 | (byte) (c2));

		}
		return result;
	}

	/**
	 * 将一个char的字面数字转换成十进制int型
	 * @param c
	 * @return
	 */
	public static int getByteLowerFourValue(char c) {
		for (int i = 0; i < hexString.length(); i++) {
			if (hexString.charAt(i) == c) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * int转byte【】
	 * @param
	 * @return
	 */
	public static byte[] valueIntegerToByte(int i) {
		byte[] bt = new byte[4];
		bt[0] = (byte) (0xff & i);
		bt[1] = (byte) ((0xff00 & i) >> 8);
		bt[2] = (byte) ((0xff0000 & i) >> 16);
		bt[3] = (byte) ((0xff000000 & i) >> 24);
		return bt;
	}

	public static int bytesToInt(byte[] by) {
		int x = 0;
		for (int i = by.length - 1; i >= 0; i--) {
			x <<= 8; // x右移8位
			x |= by[i] & 0xff; // 把by[i]加到x的低8位，运算时8位的by[i]要扩展到32位，扩展的高位用符号填冲，所以用0xff把高24位清0，
		}
		return x;
	}
}
