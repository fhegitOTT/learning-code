/**
 * 工具类汉字助手
 */
package com.netcom.nkestate.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ChineseHelper {

	static Map<String, String> map = new HashMap<String, String>();
	
	static String txtPath = "./config/UncommonChineseUnicode.txt";
	
	static {
		System.out.println(".......加载生僻字库" + txtPath);
		try {
			try {
				load(txtPath);
			}catch(Exception e) {
				System.out.println("...........从文件中加载生僻字库出现异常，" + e.getMessage());
				System.out.println("...........加载缺省的生僻字..........");
				loadDefault();	
			}			
			System.out.println(".......加载生僻字库成功");
		} catch(Exception e) {
			System.out.println("加载" + txtPath + "失败，" + e.getMessage());
			e.printStackTrace();
		}
	}

	static void load(String txtPath) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(txtPath);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		int count = 0;
		String key ;
		String value;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			int pos = line.indexOf("|");
			if(pos < 0)
				continue;
			key = line.substring(0, pos);
			value = line.substring(pos+1);
			System.out.println("key:"+key+",value:" + value);
			map.put(key, value);
			
			count++;
		}
		br.close();
		
		System.out.println(".......总共加载[" + count + "]个生僻字");
	}
	
	//缺省生僻字
		private static void loadDefault(){
		map.put("䶮", "\\u4dae");
		map.put("穇", "\\u7a47");
		map.put("㥌", "\\u394c");
		map.put("㭎", "\\u3b4e");
		map.put("韡", "\\u97e1");
					
			java.util.Iterator it = map.entrySet().iterator();
			while(it.hasNext()){
			      java.util.Map.Entry entry = (java.util.Map.Entry)it.next();		      
			      System.out.println("...Key:"+entry.getKey() + ",value:" + entry.getValue());		      
			}
		}
	
	public static String setUncommonChineseToUnicode(String txt) {		
		if(!containUncommonChinese(txt)) //不存在生僻字，返回
			return txt;
		
		int len = txt.length();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<len;i++){
			String ss = txt.substring(i, i+1);			
			if(map.containsKey(ss)) {
				sb.append(map.get(ss));
			} else {
				sb.append(ss);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 是否包含生僻字
	 * @param txt
	 * @return
	 */
	public static boolean containUncommonChinese(String txt) {
		if(txt == null || txt.trim().equals(""))
			return false;
		
		int len = txt.length();
		for(int i=0;i<len;i++){
			String ss = txt.substring(i, i+1);
			if(map.containsKey(ss)) {
				return true;
			}
		}
		return false;
	}

	public static String utf8ToUnicode(final String gbString) {
		char[] utfBytes = gbString.toCharArray(); //utfBytes = [测, 试] 
		String unicodeBytes = "";
		for(int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++){
			String hexB = Integer.toHexString(utfBytes[byteIndex]); //转换为16进制整型字符串
			if(hexB.length() <= 2){
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + "\\u" + hexB;
		}
		return unicodeBytes;
	}

	public static String unicodeToUtf8(String txt) {
		if(txt.indexOf("\\u") < 0)
			return txt;

		char aChar;
		int len = txt.length();
		StringBuffer outBuffer = new StringBuffer(len);

		for(int x = 0; x < len;){
			aChar = txt.charAt(x++);
			if(aChar == '\\'){
				aChar = txt.charAt(x++);
				if(aChar == 'u'){
					int value = 0;
					for(int i = 0; i < 4; i++){
						aChar = txt.charAt(x++);
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				}else{
					if(aChar == 't')
						aChar = '\t';
					else if(aChar == 'r')
						aChar = '\r';
					else if(aChar == 'n')
						aChar = '\n';
					else if(aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			}else{
				outBuffer.append(aChar);
			}
		}
		return outBuffer.toString();
	}

	public static void main(String[] args) throws IOException {
		String[] txts = { "韡", "到斌㭎", "中国sss", "饕餮" };
		for(int i = 0; i < txts.length; i++){
			String txt2 = setUncommonChineseToUnicode(txts[i]);
			System.out.println("unicode:" + txt2);
			System.out.println("utf8:" + unicodeToUtf8(txt2));
		}
	}

}
