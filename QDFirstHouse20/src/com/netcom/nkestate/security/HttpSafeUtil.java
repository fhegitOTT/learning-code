/**
 * <p>HttpSafeUtil.java </p>
 *
 * <p>系统名称: 上海新建商品房备案系统<p>  
 * <p>功能描述: 安全组件，封装ESAPI接口<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2018年1月22日<p>
 * 
 */ 
package com.netcom.nkestate.security;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.reference.RandomAccessReferenceMap;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.util.VOUtil;


public class HttpSafeUtil {

	static Logger logger = Logger.getLogger(HttpSafeUtil.class.getName());

	/**
	 * 将用户数据输出到html body某处时，必须经过html转义
	 * @param str
	 * @return
	 */
	static public String encodeForHTML(String str) {
		if(str == null)
			return "";
		return ESAPI.encoder().encodeForHTML(str);
	}

	/**
	 * 将用户数据输出到html 标签的属性时，必须经过标签属性的转义。 注意：不包含href, src, style和事件处理函数属性
	 * @param str
	 * @return
	 */
	static public String encodeForHTMLAttribute(Object str) {
		if(str == null)
			return "";
		return ESAPI.encoder().encodeForHTMLAttribute(String.valueOf(str));
	}

	/**
	 * 将用户数据输出到JavaScript数据域时，必须经过JavaScript转义 注意：用户数据必须在引号内，否则转义后数据仍然是不安全的。
	 * @param str
	 * @return
	 */
	static public String encodeForJavaScript(String str) {
		if(str == null)
			return "";
		return ESAPI.encoder().encodeForJavaScript(str);
	}

	/**
	 * 将用户数据输出到URL的参数时，必须经过URL转义。 <a href="http://www.somesite.com/x/y/z?test=...【用户数据】...">link</a >
	 * @param str
	 * @return
	 */
	static public String encodeForURL(String str) throws Exception {
		if(str == null)
			return "";
		return ESAPI.encoder().encodeForURL(str);
	}

	/**
	 * 功能描述：重建Session
	 * @param request
	 * @return
	 */
	public static HttpSession changeSessionID(HttpServletRequest request) {
		// get the current session
		HttpSession oldSession = request.getSession();
		// make a copy of the session content
		Map<String , Object> temp = new ConcurrentHashMap<String , Object>();
		Enumeration e = oldSession.getAttributeNames();
		while (e != null && e.hasMoreElements()) {
			String name = (String) e.nextElement();
			Object value = oldSession.getAttribute(name);
			temp.put(name, value);
		}

		// kill the old session and create a new one
		oldSession.invalidate();
		HttpSession newSession = request.getSession();

		// copy back the session content
		for(Map.Entry<String , Object> stringObjectEntry : temp.entrySet()) {
			newSession.setAttribute(stringObjectEntry.getKey(), stringObjectEntry.getValue());
		}
		return newSession;
	}

	//创建防爬对象引用
	static public RandomAccessReferenceMap createRandomReferenceMap(List<AbstractBaseVO> VOs,String column) throws Exception {
		Set fileSet = new HashSet();
		ArrayList list = new ArrayList();
		//将需要映射的资源的直接引用名（此处为message id）添加到list
		for(AbstractBaseVO vo : VOs) {
			Object value = VOUtil.getValueByColumn(vo, column);
			if(value == null) {
				value = vo.getAttribute(column);
			}
			if(value == null)
				throw new Exception("未知的列名");
			list.add(String.valueOf(value));
		}
		fileSet.addAll(list);
		RandomAccessReferenceMap IDMap = new RandomAccessReferenceMap(fileSet);

		return IDMap;
	}

	//验证页面访问权限，防止爬虫
	static public boolean validRandomReferenceMap(RandomAccessReferenceMap IDMap,long ID) {
		return validRandomReferenceMap(IDMap, String.valueOf(ID));
	}

	//验证页面访问权限，防止爬虫
	static public boolean validRandomReferenceMap(RandomAccessReferenceMap IDMap,String ID) {
		try {
			if(IDMap == null)
				return false;

			String s = IDMap.getIndirectReference(ID);
			if(s == null)
				return false;

			String s2 = String.valueOf(IDMap.getDirectReference(s));
			if(s2==null)
				return false;

			if(!ID.endsWith(s2))
				return false;
			
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}


//	public static void main(String[] args) throws Exception {
//		List list = new ArrayList();
//		for(int i = 0; i < 10; i++) {
//			LogLoginVO vo = new LogLoginVO();
//			vo.setLogID(i);
//			vo.setLoginName("Name--" + i);
//			list.add(vo);
//		}
//
//		RandomAccessReferenceMap IDMap = HttpSafeUtil.createRandomReferenceMap(list, "logID");
//
//		boolean ret = HttpSafeUtil.validRandomReferenceMap(IDMap, 5);
//		System.out.println("valid result:" + ret);
//
//		ret = HttpSafeUtil.validRandomReferenceMap(IDMap, 15);
//		System.out.println("valid result2:" + ret);
//	}


}
