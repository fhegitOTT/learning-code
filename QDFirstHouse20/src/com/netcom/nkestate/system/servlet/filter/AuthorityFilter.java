/**
 *<p>AuthorityFilter.java</p>
 *
 * <p>项目名称: 青岛房屋安全管理项目</p>
 * <p>系统名称: 房屋安全管理系统<p>  
 * <p>功能描述：权限验证</p>
 *
 *<p>公司名称：上海南康科技有限公司</p> 
 *<p>作   者: Administrator</p>
 *<p>创建日期: 2017-01-10</p>
 *
 */
package com.netcom.nkestate.system.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.netcom.nkestate.framework.util.StringUtil;
import com.netcom.nkestate.system.bo.ActionListBO;
import com.netcom.nkestate.system.vo.SmUserVO;


public class AuthorityFilter implements Filter {

	/** ContextPath常量 */
	public static final String CONTEXT_PATH = "%CONTEXT_PATH%";

	/** 忽略权限校验的URL列表。 */
	protected List m_IgnoredUrlList;

	/** 需要验证的网页扩展名列表 */
	protected List m_UrlExtNameList;
	
	/*** 跨站攻击脚本特征 ***/
	protected List<String> m_XssCode;

	/** 错误页 */
	/** 是否为调试模式:调试模式下不验证权限。 */
	protected boolean m_DebugMode;

	/**
	 * 判断指定的URI是否忽略权限校验。
	 * @param uri
	 * URL字符串。
	 * @param szContextPath
	 * ContextPath值。
	 * @return 如果在Filter参数中定义了该链接，则返回true，否则返回false。
	 */
	protected boolean isUrlIgnored(String uri,String szContextPath) {
		if(uri == null){
			return true;
		}
		if(uri.startsWith("/services/"))
			return true;

		for(int i = 0; i < m_IgnoredUrlList.size(); i++){
			String u = (String) m_IgnoredUrlList.get(i);
						
			if(match(uri, u)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 比较带通配符(*)的URL。
	 * @param str
	 * URL字符串。
	 * @param pattern
	 * 匹配模式。
	 * @return 匹配，则返回true，否则返回false。
	 */
	private boolean match(String str,String pattern) {
		if(str == null && pattern == null)
			return true;
		if(str == null)
			return false;
		if(pattern == null)
			return false;
		int p = pattern.indexOf(42);
		if(p >= 0){
			String left = pattern.substring(0, p);
			boolean bleft = true;
			if(left.length() > 0)
				bleft = str.startsWith(left);
			String right = pattern.substring(p + 1);
			boolean bright = true;
			if(right.length() > 0)
				bright = str.endsWith(right);
			return bleft && bright;
		}else{
			return str.equals(pattern);
		}
	}

	/**
	 * 判断指定的URI是否需要进行权限验证。
	 * @param uri
	 * URI。
	 * @return 如果在web.xml中配置了该扩展名，则需要验证，返回true，否则返回false。
	 */
	protected boolean needValidate(String uri) {
		if(uri == null){
			return false;
		}
		for(int i = 0; i < m_UrlExtNameList.size(); i++){
			if(uri.endsWith((String) m_UrlExtNameList.get(i))){
				return true;
			}
		}
		
		//spring mvc的链接 "/aaa/bb/dosomething",必须验证
		int pos = uri.lastIndexOf("/");
		if(pos > 0){
			String ss = uri.substring(pos);
			if(ss.indexOf(".") < 0)
				return true;
		}


		return false;
	}

	/**
	 * 构造AuthorityFilter对象。
	 */
	public AuthorityFilter() {
		super();
		m_IgnoredUrlList = new ArrayList();
		m_UrlExtNameList = new ArrayList();
		m_XssCode = new ArrayList<String>();
	}

	/**
	 * 初始化Filter对象。
	 * @param config
	 * Filter配置对象。
	 * @exception ServletException
	 * 如果发生Servlet异常。
	 */
	public void init(FilterConfig config) throws ServletException {
		String urls = config.getInitParameter("ignored_url_list");
		if(urls != null){
			String url_array[] = StringUtil.split(urls, ";");
			for(int i = 0; i < url_array.length; i++){
				if(url_array[i].length() > 0){
					m_IgnoredUrlList.add(url_array[i]);
				}
			}
		}

		String exts = config.getInitParameter("url_extname_list");
		if(exts != null){
			String ext_array[] = StringUtil.split(exts, ";");
			for(int i = 0; i < ext_array.length; i++){
				m_UrlExtNameList.add(ext_array[i]);
			}
		}

		String debug = config.getInitParameter("debug_mode");
		if(debug == null){
			m_DebugMode = false;
		}else{
			m_DebugMode = debug.equalsIgnoreCase("true");
		}
		m_DebugMode = false;
		
		String xss = config.getInitParameter("xss_code");
		if(xss != null){
			String array[] = StringUtil.split(xss, "||");
			String str = null;
			for(int i = 0; i < array.length; i++){
				str = array[i].trim();
				str = StringUtil.replace(str, "&lt;", "<");
				str = StringUtil.replace(str, "&gt;", ">");
				m_XssCode.add(str);
			}
		}
	}

	/**
	 * 进行Filter处理。
	 * @param request
	 * 请求对象
	 * @param response
	 * 响应对象。
	 * @param chain
	 * Filter链对象。
	 * @exception IOException
	 * 如果发生IO异常。
	 * @exception ServletException
	 * 如果发生Servlet异常。
	 */
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		//System.out.println("================doFilter==============================" + req.getRequestURI());
		if(m_DebugMode){
			chain.doFilter(request, response);
			return;
		}
		
		if(request instanceof HttpServletRequest){
			String szContextPath = req.getContextPath();
			String uri = req.getRequestURI();
			uri = uri.replaceAll(szContextPath, "").replaceAll("//", "/");

			if(!isValidRequestContent(req, res)){
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<p style='font-size:12pt;color:red'>非法操作.<hr noshade></p>");
				out.println("<p style='font-size:10.5pt'>");
				out.println("可能的原因有:<br>");
				out.println("(1)你输入了不允许的特殊字符.<br>");
				out.println("</p>");
				return;
			}

			//仅考虑动态网页，并且没有被忽略。
			if( (!isUrlIgnored(uri, szContextPath)) && needValidate(uri)){
				boolean existRight = true;

				HttpSession session = req.getSession();
				SmUserVO user = (SmUserVO) session.getAttribute("LgUser");
				if(user == null){
					res.setContentType("text/html; charset=UTF-8");
					PrintWriter out = res.getWriter();
					out.println("<p style='font-size:12pt;color:red'>===已超时，请重新登录.===</p>");
					return;
				}

				//用户权限检查
				if(uri.endsWith(".jsp") || uri.endsWith(".action") || uri.endsWith(".do") || uri.indexOf(".")<0) {

					if(user.getUserType() == 0){
						//内网用户
						if(!uri.startsWith("/inner/") && !uri.startsWith("/system/")){
							System.out.println("$$$$$$$$$$$$$$$$$$$$" + uri);
							res.setContentType("text/html; charset=UTF-8");
							PrintWriter out = res.getWriter();
							out.println("<p style='font-size:12pt;color:red'>===中心用户非法URL操作.===</p>");
							return;
						}

					}else if(user.getUserType() == 1){
						//外网用户
						if(!uri.startsWith("/outer/") && !uri.startsWith("/system/") && !uri.startsWith("/inner/ContractPdf/")
								&& !uri.startsWith("/inner/contractquery/")){
							System.out.println("$$$$$$$$$$$$$$$$$$$$" + uri);
							res.setContentType("text/html; charset=UTF-8");
							PrintWriter out = res.getWriter();
							out.println("<p style='font-size:12pt;color:red'>===签约用户非法URL操作.===</p>");
							return;
						}
					}
				}

				//访问权限校验
				if(!ActionListBO.isActionSub(user, uri)){
					res.setContentType("text/html; charset=UTF-8");
					PrintWriter out = res.getWriter();
					out.println("<p style='font-size:12pt;color:red'>===用户非法操作URL。===</p>");
					return;
				}

				existRight = true;
				if(!existRight){
					res.setContentType("text/html; charset=UTF-8");
					PrintWriter out = res.getWriter();
					out.println("<p style='font-size:12pt;color:red'>当前用户没有访问该资源的权限.<hr noshade></p>");
					out.println("<p style='font-size:10.5pt'>");
					out.println("可能的原因有:<br>");
					out.println("(1)尚未登录.<br>");
					out.println("(2)用户未被授予访问该资源的权限.<br>");
					out.println("(3)用户虽被授权,但是该权限已被置为无效.<br>");
					out.println("(4)用户虽被授权,但是该权限已经过期.<br>");
					out.println("</p>");
					return;
				}
			}
		}

		chain.doFilter(request, response);
	}

	
	private boolean isValidReg(String realURL, String regURL) {		
		//String regexp = "/aaa/.*/.*.action";
		//regURL = "/aaa/bbb/d.*";
		//realURL = "/aaa/bbb/dfdbSave.action";

		Pattern p = Pattern.compile(regURL);
		Matcher m = p.matcher(realURL); // 获取 matcher 对象

		boolean b = m.matches();
		
		return b;
	}
	
	private String trimURL(String url) {
		if(url==null || url.indexOf("?")<0)
			return url;
		url = url.substring(0,url.indexOf("?"));
		if(!url.startsWith("/"))
			url = "/" + url;
		return url;
	}


	/**
	 * 销毁本对象。
	 */
	public void destroy() {
		m_IgnoredUrlList.clear();
		m_UrlExtNameList.clear();
		m_XssCode.clear();
	}
	
	//检查是否存在特殊Xss字符
	private boolean isValidRequestContent(HttpServletRequest request,HttpServletResponse response) throws IOException {
		if(m_XssCode == null || m_XssCode.size() == 0)
			return true;
		
		String strquery = request.getQueryString();
		String struri = request.getRequestURI();
		//System.out.println("QueryString2=" + URLDecoder.decode(strquery, "UTF-8"));
		
		if(existXssCode(strquery) || existXssCode(struri)){			
			return false;
		}
		
		//检查提交参数
		Enumeration em = request.getParameterNames();
		while (em.hasMoreElements()){
			String name = (String) em.nextElement();
			String[] values = request.getParameterValues(name);
			for(String value : values){
				if(existXssCode(value)){					
					return false;
				}
			}
		}		

		return true;
	}
	
	private boolean existXssCode(String code) {
		if(code == null || "".equals(code.trim()))
			return false;
		
		for(String xsscode : m_XssCode){
			if(code.indexOf(xsscode) >= 0)
				return true;
		}
		return false;
	}

}
