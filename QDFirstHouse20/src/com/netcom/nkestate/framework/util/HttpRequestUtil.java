/**
 *<p>HttpRequestUtil.java</p>
 *
 *<p>项目名称：南康房地产市场信息系统</p>
 *<p>系统名称：登记管理子系统</p>
 *<p>功能描述：HttpRequest工具</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: daobin</p>
 *<p>创建日期: 2013-5-8</p>
 *
 */
package com.netcom.nkestate.framework.util;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.ModelUtil;

public class HttpRequestUtil {
	
	static Logger logger = Logger.getLogger(HttpRequestUtil.class.getName());
	
	/**
	 * 功能描述：将request请求参数复制到vo对象中
	 * @param request
	 * @param vo
	 * @return 拷贝的记录数
	 */
    static public int copy(ServletRequest request, AbstractBaseVO vo) {    	
    	int count = 0;
    	try{
    		Method[] methods = ModelUtil.getSettingMethods(vo.getClass());
    		Enumeration e = request.getParameterNames(); //请求参数
			while (e.hasMoreElements()){
				String name = (String) e.nextElement();
				String[] values = request.getParameterValues(name);
				Method method = findMatchMethod(methods, name);				
				if(method != null){
					if(values[0] == null || values.length != 1)
						continue;
					Class[] cp = method.getParameterTypes();
					if("long".equals(cp[0].getName())){						
						method.invoke(vo, Long.parseLong(values[0]));
					}else if("int".equals(cp[0].getName())){
						method.invoke(vo, Integer.parseInt(values[0]));
					}else if("double".equals(cp[0].getName())){
						method.invoke(vo, Double.parseDouble(values[0]));
					}else if("float".equals(cp[0].getName())){
						method.invoke(vo, Float.parseFloat(values[0]));
					}else if("java.util.Date".equals(cp[0].getName())){
						if(values[0].length() > 10)  //带有时间
							method.invoke(vo, DateUtil.parseDateTime(values[0]));
						else  
							method.invoke(vo, DateUtil.parseDate(values[0]));
					}else if("java.lang.String".equals(cp[0].getName())){
						method.invoke(vo, values[0]);
					}
					count++;
				}else{
					if(values.length > 1)
						vo.setAttribute(name, values[0]);
					else
						vo.setAttribute(name, values);
				}				
			}

		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			return -1;
		}
		return count;
	}

	/**
	 * 功能描述：将request请求参数复制到vo数组对象中
	 * @param request
	 * @param vo
	 * @return 拷贝的记录数
	 */
    static public AbstractBaseVO[] copy(ServletRequest request, Class clazz) {
    	int size = getRequestObjectSize(request);
    	AbstractBaseVO[] vos = new AbstractBaseVO[size];
    	try{
    		AbstractBaseVO vo = (AbstractBaseVO) clazz.newInstance();    		
    		for(int i = 0; i < vos.length; i++)
				vos[i] = (AbstractBaseVO) clazz.newInstance();    		
			Method[] methods = ModelUtil.getSettingMethods(vo.getClass());
			int index = 0;
			Enumeration e = request.getParameterNames(); //请求参数			
			while (e.hasMoreElements()){
				String name = (String) e.nextElement();
				String[] values = request.getParameterValues(name);
				Method method = findMatchMethod(methods, name);
				if(method == null)
					continue;
				for(int i = 0; i < values.length; i++){
					String value = values[i];
					vo = vos[i];
					if(value == null || "".equals(value.trim()))
						continue;
					Class[] cp = method.getParameterTypes();
					String classname = cp[0].getName();
					if("long".equals(classname)){
						method.invoke(vos, Long.parseLong(value));
					}else if("int".equals(classname)){
						method.invoke(vo, Integer.parseInt(value));
					}else if("double".equals(classname)){
						method.invoke(vo, Double.parseDouble(value));
					}else if("float".equals(classname)){
						method.invoke(vo, Float.parseFloat(value));
					}else if("java.util.Date".equals(classname)){
						if(values[0].length() > 10) //带有时间
							method.invoke(vo, DateUtil.parseDateTime(value));
						else
							method.invoke(vo, DateUtil.parseDate(value));
					}else if("java.lang.String".equals(classname)){
						method.invoke(vo, value);
					}			
				}
			}
			
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();			
		}
		return vos;
	}
    
    //得到数组长度
    static private int getRequestObjectSize(ServletRequest request) {
		int count = 0;
		Enumeration e = request.getParameterNames(); //请求参数			
		while (e.hasMoreElements()){
			String name = (String) e.nextElement();
			String[] values = request.getParameterValues(name);
			count = values.length;
			if(count > 1)
				break;
		}
		return count;
	}
    
    //查找匹配请求参数的VO方法
    static private Method findMatchMethod(Method[] methods, String name) {
		for(Method method : methods){
			if(method.getName().toLowerCase().equals("set" + name.toLowerCase())){
				return method;
			}
		}
		return null;
	}
    
    /**
	 * 获取访问者IP
	 * 
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	static public String getIpAddress(HttpServletRequest request) throws Exception {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtil.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtil.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
}
