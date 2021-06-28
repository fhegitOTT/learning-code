/**
 * <p>Base64Util.java </p>
 *
 * <p>项目名称: 上海地产优家房屋租赁项目</p>
 * <p>系统名称: 房屋租赁管理业务系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Wangjinjie</p>
 * <p>创建时间: 上午11:19:18<p>
 * 
 */ 
package com.netcom.nkestate.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Base64Util {
	
	static BASE64Encoder encoder = new BASE64Encoder();
	static BASE64Decoder decoder = new BASE64Decoder();
	/**  
	 * 编码  
	 * @param bstr  
	 * @return String  
	 */    
	public static String encode(String str){
		byte[] bstr  = str.getBytes();
		return new sun.misc.BASE64Encoder().encode(bstr);    
	}    
	   
	/**  
	 * 解码  
	 * @param str  
	 * @return string  
	 */    
	public static String decode(String str){
		String string = "";
		byte[] bt = null;    
		try {    
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();    
			bt = decoder.decodeBuffer( str );
			string = new String(bt);
	   } catch (IOException e) {    
	       e.printStackTrace();    
	   }    
	   return string;    
	}
	
	/**
	 * 解码
	 * @param str
	 * @return
	 */
	public static byte[] decodeBufer(String str){
		byte[] bt = null;    
		try {    
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();    
			bt = decoder.decodeBuffer( str );
		} catch (IOException e) {    
			e.printStackTrace();    
		}    
		return bt;    
	}

	/**
	 * 获取文件base64内容
	 * @param filePath
	 * @return
	 */
	public static String getPDFBinary(String filePath) {
		File file = new File(filePath);
		FileInputStream fileInputStream = null;
		BufferedInputStream bufferInputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		BufferedOutputStream bout =null;
		try{
			fileInputStream = new FileInputStream(file);
			bufferInputStream = new BufferedInputStream(fileInputStream);
			byteArrayOutputStream = new ByteArrayOutputStream();
			bout = new BufferedOutputStream(byteArrayOutputStream);
			byte[] buffer = new byte[1024];
			int len = bufferInputStream.read(buffer);
			while (len != -1){
				bout.write(buffer, 0, len);
				len = bufferInputStream.read(buffer);

			}

			bout.flush();
			byte[] bytes = byteArrayOutputStream.toByteArray();
			return encoder.encodeBuffer(bytes).trim();

		}catch (Exception e){

			e.printStackTrace();
		}finally{
			if(bout!=null){
				try {
					bout.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(byteArrayOutputStream!=null){
				try {
					byteArrayOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bufferInputStream!=null){
				try {
					bufferInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	/**
	 * base64字符串生成文件
	 * @param base64String
	 * @param newFile
	 */
	public static void base64StringToPDF(String base64String,String newFile) {
		try{
			if(base64String != null){
				base64String = base64String.replaceAll(" ", "+");
			}
			byte[] bytes = decoder.decodeBuffer(base64String);
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			BufferedInputStream bin = new BufferedInputStream(bais);
			File file = new File(newFile);
			FileOutputStream fout = new FileOutputStream(file);
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			byte[] buffers = new byte[1024];
			int len = bin.read(buffers);
			while (len != -1){
				bout.write(buffers, 0, len);
				len = bin.read(buffers);
			}
			bout.flush();
			bout.close();
		}catch (Exception e){

			e.printStackTrace();
		}

	}

	public static String replaceBase64(String base64String) {
		String reBase64String = "";
		try{
			if(base64String != null){
				reBase64String = base64String.replaceAll(" ", "+");
			}
		}catch (Exception e){

			e.printStackTrace();
		}
		return reBase64String;
	}
}
