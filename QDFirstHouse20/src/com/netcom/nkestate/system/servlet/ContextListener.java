/**
 * <p>ContextListener.java </p>
 *
 * <p>系统名称: 青岛房屋安全使用管理系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 上午10:58:50<p>
 * 
 */
package com.netcom.nkestate.system.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.netcom.nkestate.common.Constant;
import com.netcom.nkestate.framework.MiniConfiger;
import com.netcom.nkestate.framework.cache.DictionaryCache;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.action.ActionCache;
import com.netcom.nkestate.system.bo.ActionListBO;
import com.netcom.nkestate.system.bo.LoginBO;
import com.netcom.nkestate.system.vo.SmUserVO;


public class ContextListener implements ServletContextListener, HttpSessionListener {

	private static int allActiveSessions = 0;//总登录人数
	private static int innerActiveSessions = 0;//内网登录人数
	private static int outerActiveSessions = 0;//外网登录人数

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("...  System Initialization Start ...");

		String MiniConnectionJNDI = sce.getServletContext().getInitParameter("MiniConnectionJNDI");
		String RealestateConnectionJNDI = sce.getServletContext().getInitParameter("RealestateConnectionJNDI");
		String HouseFundConnectionJNDI = sce.getServletContext().getInitParameter("HouseFundConnectionJNDI");
		String UnirealestateJNDI = sce.getServletContext().getInitParameter("UnirealestateJNDI");
		//String AttachmentURL = sce.getServletContext().getInitParameter("AttachmentURL");
		String ContractURL = sce.getServletContext().getInitParameter("ContractURL");
		String ContractPdfPath = sce.getServletContext().getInitParameter("ContractPdfPath");
		String housePlanURL = sce.getServletContext().getInitParameter("housePlanURL");
		String REG_SMALL_CODE = sce.getServletContext().getInitParameter("REG_SMALL_CODE");
		String TempFilePath = sce.getServletContext().getInitParameter("TempFilePath");


		String ZBURL = MiniConfiger.getProperty("ZBURL");
		String username = MiniConfiger.getProperty("username");
		String password = MiniConfiger.getProperty("password");
		String imageServerURL = MiniConfiger.getProperty("imageServerURL");
		String SmsURL = MiniConfiger.getProperty("SmsURL");

		//Constant.AttachmentURL = AttachmentURL;
		Constant.MiniConnectionJNDI = MiniConnectionJNDI;
		Constant.RealestateConnectionJNDI = RealestateConnectionJNDI;
		Constant.HouseFundConnectionJNDI = HouseFundConnectionJNDI;
		Constant.UnirealestateJNDI = UnirealestateJNDI;
		Constant.ContractURL = ContractURL;
		Constant.ContractPdfPath = ContractPdfPath;
		Constant.housePlanURL = housePlanURL;
		Constant.REG_SMALL_CODE = REG_SMALL_CODE;
		Constant.TempFilePath = TempFilePath;
		Constant.ZBURL = ZBURL;
		Constant.username = username;
		Constant.password = password;
		Constant.imageServerURL = imageServerURL;
		Constant.SmsURL = SmsURL;

		System.out.println(".........MiniConnectionJNDI=" + MiniConnectionJNDI);
		System.out.println(".........RealestateConnectionJNDI=" + RealestateConnectionJNDI);
		System.out.println(".........HouseFundConnectionJNDI=" + HouseFundConnectionJNDI);
		System.out.println(".........UnirealestateJNDI=" + UnirealestateJNDI);
		System.out.println(".........ContractURL=" + ContractURL);
		System.out.println(".........ContractPdfPath=" + ContractPdfPath);
		System.out.println(".........housePlanURL=" + housePlanURL);
		//System.out.println(".........AttachmentURL=" + AttachmentURL);
		System.out.println(".........REG_SMALL_CODE=" + REG_SMALL_CODE);
		System.out.println(".........TempFilePath=" + TempFilePath);
		System.out.println(".........ZBURL=" + ZBURL);
		System.out.println(".........username=" + username);
		System.out.println(".........password=" + password);

		try{
			ActionCache.load(); //加载对象

			System.out.println("... ActionCache.load() Success ....");
		}catch (Exception e){
			System.out.println("... Exception：ActionCache.load()," + e.getMessage());
			e.printStackTrace();
		}

		try{
			DictionaryCache.loadSysDict(); //加载对象

			System.out.println("... DictionaryCache.loadSysDict() Success ....");
		}catch (Exception e){
			System.out.println("... Exception：DictionaryCache.loadSysDict()," + e.getMessage());
			e.printStackTrace();
		}

		try{
			ActionListBO.loadactionsublist(); //加载需权限校验的列表
			System.out.println("... ActionListBO.loadactionsublist() Success ....");
		}catch (Exception e){
			System.out.println("... Exception：ActionListBO.loadactionsublist()," + e.getMessage());
			e.printStackTrace();
		}


		System.out.println("...  System Initialization Finished ...");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();
	}

	public void sessionCreated(HttpSessionEvent hse) {
		HttpSession session = hse.getSession();
		SmUserVO user = (SmUserVO) session.getAttribute("LgUser");
		if(user != null){
			System.out.println("####User login," + user.getLoginName() + " from " + user.getClientIP() + ", Time=" + DateUtil.getSysDate());
			innerActiveSessions++;
		}

		allActiveSessions++;
	}

	public void sessionDestroyed(HttpSessionEvent hse) {
		HttpSession session = hse.getSession();
		try{
			SmUserVO user = (SmUserVO) session.getAttribute("LgUser");
			if(user != null){
				System.out.println("@@@@User logout," + user.getLoginName() + " from " + user.getClientIP() + ", Time=" + DateUtil.getSysDate());

				LoginBO loginBO = new LoginBO();
				loginBO.udpateLogoutTime(user);
				loginBO.udpateLogoutLogTime(user);

				session.setAttribute("LgUser", null);
				innerActiveSessions--;
			}
		}catch (Exception e){
			//e.printStackTrace();
		}

		allActiveSessions--;
	}



}
