/**
 *<p>CharSet.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：TODO</p>
 *<p>功能描述：TODO</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2012-3-22</p>
 *
 */
package com.netcom.nkestate.common;


public class CharSet {
	
	public static String toDBCharSet(String str) {
		return toGBK(str);
	}
	
	public static String toWebCharSet(String str) {
		return str==null ? "" : str;
	}
	public static String toWebCharSet(Object obj) {
		return obj==null ? "" : obj.toString();
	}
	
	
	//字符转化
	public static String toISO(String str) {
		try	{
			return new String(str.getBytes(),"ISO8859-1");
		} catch(Exception e) {	
			return "";
		}
	}
	
	//字符转化
	public static String toGBK(String str) {
		try	{
			return new String(str.getBytes(),"GBK");
		} catch(Exception e) {	
			return "";
		}
	}
	
	public static String ISOtoGBK(String str){
		try{
			return new String(str.getBytes("ISO8859-1"),"GBK");
		}catch(Exception e){
			return "";
		}
	}
	public static String GBKtoISO(String str){
		try{
			return new String(str.getBytes("GBK"),"ISO8859-1");
		}catch(Exception e){
			return "";
		}
	}
	
	public static String toCharSet(String str,String src,String tag)
	{
		try
		{
			return new String(str.getBytes(src),tag);
		}
		catch(Exception e)
		{	
			return "";
		}
	}
	

	
	public static String handleXmlChar(String str){
		if(str==null)
			return "";
		
		StringBuffer sb=new StringBuffer("");
		String tmp=str.trim();
		for(int i=0;i<tmp.length();i++){
				switch(tmp.charAt(i)){
					case '\"':
						sb=sb.append("&quot;");
						break;
					case '<':
						sb=sb.append("&lt;");
						break;
					case '>':
						sb=sb.append("&gt;");
						break;
					case '&':
						sb=sb.append("&amp;");
						break;
					default:
						sb=sb.append(tmp.charAt(i));
						break;
				}
		}
		return sb.toString();
		
	}
	
	
	public static String JsTrim(String str) throws Exception
	{
		try{
			if(str==null)
				return "";
			else{
				StringBuffer sb=new StringBuffer("");
				String tmp=str.trim();
				for(int i=0;i<tmp.length();i++){
					switch(tmp.charAt(i)){
						case '\"':
							sb=sb.append("\\\"");
							break;
						case '\\':
							sb=sb.append("\\\\");
							break;
						case '\'':
							sb=sb.append("\\\'");
							break;
						case '\n':
							sb=sb.append("\\n");
							break;
						case '\r':
							sb=sb.append("\\r");
							break;
						default:
							sb=sb.append(tmp.charAt(i));
							break;
					}
				}
				return sb.toString();
			}
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
	}
	
	

	//替换函数，将s中的s2字符串用s3替换
	public static String replace(String s,String s2,String s3)
	{
   		try
   		{
   			int pos;
   			String tmpS="";   	
   			if(s2!=null && s3!=null && !s2.equals(""))
   			{
      			pos=s.indexOf(s2);
      			while(pos>=0)
      			{
        			tmpS += s.substring(0,pos) + s3 ; 
        			s = s.substring(pos+s2.length());
        			pos = s.indexOf(s2);
      			}	
      			tmpS += s ;
   			}
   			else
   			{
	   			tmpS = s ;
   			}	
   			return tmpS;
   		}
   		catch(Exception e)	
   		{
   			return s;
   		}	
	}
	
	//将"AAAA;BBBB;CCCC;"拆开成一个数组
    public static String[] split(String s,String s2)
	{
		String dataSet[] ;
		String tmpS;
		int pos,i;
		if(s==null || s.equals("")) return null;
		
		//判断数组的维数
		pos=s.indexOf(s2);
		if(pos>=0)
		{
      	tmpS = s;
      	i=0;
      	pos=tmpS.indexOf(s2);
      	while(pos>=0)
      	{
      		i++;
      		tmpS = tmpS.substring(pos + s2.length());
      		if(tmpS==null || tmpS.equals("")){ break; }
      		pos=tmpS.indexOf(s2);
      		if(pos<0 && tmpS!=null && !tmpS.equals("")) i++;
      	}	
      	
      	dataSet = new String[i];
      	
      	//给数组赋值
      	i = 0;
      	pos = s.indexOf(s2);
      	while(pos>=0)
      	{
        		tmpS = s.substring(0,pos);         		        		
        		dataSet[i] = tmpS;
        		s = s.substring(pos + s2.length());
        		if(s==null || s.equals("")){ break; }
        		pos = s.indexOf(s2);        		
        		i++;
        		if(pos<0 && s!=null && !s.equals(""))
        		{        			
        			dataSet[i] = s;
        		}	
      	}
      }
      else
      {
      	dataSet = new String[1];
      	dataSet[0] = s ;      		
      }		
		
		return dataSet;
	}	
}
