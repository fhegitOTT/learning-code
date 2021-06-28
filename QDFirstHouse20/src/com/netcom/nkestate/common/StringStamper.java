package com.netcom.nkestate.common;

import java.util.Random;

public class StringStamper {

	/** 加密后的字符串长度是原串的最大倍数 */
	public static int MAX_LENGTH_TIMES=4;
	
	/** 加密后的字符串长度是原串的最小倍数 */
	public static int MIN_LENGTH_TIMES=2;
	public static void main(String args[])
	{
			
		String result = StringStamper.encode("1");
		System.out.println("result:"+result);
		result = StringStamper.decode(result);
		System.out.println("result:"+result);
		
		String transactionid="201425000330";
		System.out.println("encode:" + encode(transactionid));
		
	}
	private static String HEX_STRING="0123456789ABCDEF";

	/**
	 * 解密的过程： 1、校验码的验证 2、获得原串长度，将整体字符串长度-8-1； 3、求出step=整体字符串长度-8-1/原串长度; 4、按步长取出原串， 5、转成byte[], 6、转换成String
	 * @param encryptKey
	 * @return
	 */
	public static String decode(String encryptKey)
	{
		if(encryptKey==null||encryptKey.trim().length()<10)
			return null;
		int length = encryptKey.length();
		String checkNumber = encryptKey.substring(length-1);
		String originKeyLengthStr = encryptKey.substring(0,4)+encryptKey.substring(length-5,length-1);
		
		encryptKey = encryptKey.substring(4,length-5);
		
		int sum = 0;
		
		for(int i=0;i<encryptKey.length();i++)
		{
			char c = encryptKey.charAt(i);
			sum+=c;
		}
		int yu =sum%10;
		if(Integer.parseInt(checkNumber) != yu)//校验不成功
			return null;
		//获得原串的长度
		int originKeyLengthInt = HexConvertionTool.bytesToInt(HexConvertionTool.decodeStringToByte(originKeyLengthStr));
		char [] key = new char[originKeyLengthInt];
		int step = encryptKey.length()/originKeyLengthInt-1;
		for(int i=step,j=0;i<encryptKey.length();i+=step+1,j++)
		{
			key[j] = encryptKey.charAt(i);
		}
		byte [] bytesKey = HexConvertionTool.decodeStringToByte(new String(key));
		String str = new String(bytesKey);
		return str;
	}
	
	public static String encode(String key)
	{
		if(key==null)
			return null;
		try
		{
			String hexString = HexConvertionTool.encode(key);//16进制字符串,key的16进制字符串
			// 	计算字符串的倍数
			Random r = new Random();
			int random_i = r.nextInt(MAX_LENGTH_TIMES);
			int EncryptLength = random_i*hexString.length();
			//生成EncryptLength位 的随机数，用于混淆,直接用0-9a-f的char来混淆，不用从byte转char
			char  randombytes [] = new char[EncryptLength];
			for(int i=0;i<randombytes.length;i++)
			{
				int random_index =  r.nextInt(16);
				char hex_char = HEX_STRING.charAt(random_index);
				randombytes[i] = hex_char;
			}
			int step = random_i;

			//将随机数和原始数据混淆
			char[] des = new char[EncryptLength+hexString.length()];
			int nextpos = step;//下一个插入原始数据的位置
			for(int i=0,key_index=0,random_index=0;i<des.length;i++)
			{
				if(i==nextpos)
				{
					des[i] =  hexString.charAt(key_index);
					key_index++;
					nextpos +=step+1;
				}
				else
				{
					des[i] = randombytes[random_index];
					random_index++;
				}
			}
			//结果字符串
			StringBuffer sp = new StringBuffer();
			sp.append(new String(des));
			
			//原始串长度的处理
			int keyLength = hexString.length();
			//将int转成4个 byte，再将4个byte转成8个char，分别拼接到头尾。
			String hexKeyLengthString = HexConvertionTool.encode(keyLength);
			int half = hexKeyLengthString.length()/2;
			
			int sum=0;
			//增加校验码,将混淆的所有的字符的ascii码相加并%10所得的余数，不包括原串长度。
			for(int i=0;i<sp.length();i++)
			{
				char c = sp.charAt(i);
				sum+=c;
			}
			int yu = sum%10;
			
			sp = sp.insert(0, hexKeyLengthString.substring(0,half));
			sp = sp.append(hexKeyLengthString.substring(half));
			sp.append(yu);

			return sp.toString();
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
		return null;
	}
	
	
	

}
