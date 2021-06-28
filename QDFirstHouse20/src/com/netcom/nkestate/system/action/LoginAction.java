package com.netcom.nkestate.system.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.common.NKRSAUtil;
import com.netcom.nkestate.common.RandomValidateCode;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.framework.MiniConfiger;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.util.HttpRequestUtil;
import com.netcom.nkestate.framework.util.StringUtil;
import com.netcom.nkestate.system.bo.LoginBO;
import com.netcom.nkestate.system.vo.BulletinVO;
import com.netcom.nkestate.system.vo.LogLoginTodayVO;
import com.netcom.nkestate.system.vo.NonLoginTimeVO;
import com.netcom.nkestate.system.vo.SmObjectVO;
import com.netcom.nkestate.system.vo.SmUserVO;

/**
 * 用户登陆Action
 */
@Controller
@RequestMapping(value = "/login")
public class LoginAction extends BaseAction {

	private LoginBO loginBO = new LoginBO();


	/**
	 * 功能描述：建设页面跳转
	 * @return
	 */
	@RequestMapping(value = "/Construction")
	public String Construction() {
		return "/Construction";
	}


	/**
	 * 功能描述：内网用户登录检查
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "checkUserLogin")
	@ResponseBody
	public String checkUserLogin(HttpServletRequest request) {
		LoginBO loginBO = new LoginBO();
		try{
			String loginName = request.getParameter("loginName");
//			String password = request.getParameter("password");
			SmUserVO uservo = loginBO.findLoginUser(loginName);
			if(uservo == null){
				return "FALSE";
			}else{
				return "TRUE";
			}
//			byte[] b = NKRSAUtil.decryptByPrivateKey(password, NKRSAUtil.PRIVATE_KEY_STR);
//			password = new String(b);
//			if(password.equals(uservo.getPassword())){
//
//				return "TRUE";
//			}else{
//				return "FALSE";
//			}

		}catch (Exception e){
			e.printStackTrace();
			//request.setAttribute("message", "1");
			return "ERROR";
		}
	}

	/**
	 * 功能描述：内网用户登录
	 * @param user
	 * @param httpSession
	 * @param objectCodes
	 * @return
	 */
	@RequestMapping(value = "/InnerLogin")
	public ModelAndView innerLogin(SmUserVO user,HttpSession httpSession,HttpServletRequest request) {
		return loginType(user, httpSession, request);
	}

	/**
	 * 功能描述：内网单点登入sso
	 * @param user
	 * @param httpSession
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/InnerLoginSSO")
	public ModelAndView innerLoginSSO(HttpSession httpSession,HttpServletRequest request) {
		LoginBO userBO = new LoginBO();
		SmUserVO user = null;
		try{
			user = userBO.findLoginUserByID(request.getAttribute("userID").toString());
		}catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginType2(user, httpSession, request);
	}

	/**
	 * 功能描述：单点登入方式
	 * @param user
	 * @param httpSession
	 * @param request
	 * @return
	 */
	public ModelAndView loginType2(SmUserVO user,HttpSession httpSession,HttpServletRequest request) {
		LoginBO loginBO = new LoginBO();
		String loginName = user.getLoginName();
		String password = user.getPassword();
		SmUserVO userVO = null;
		//Map map = new HashMap();
		try{
			//验证码再次检查
			//String checkStr = request.getParameter("check_input");
			//String random = (String) httpSession.getAttribute("RANDOMVALIDATECODEKEY");
			//if(checkStr == null || "".equals(checkStr) || !checkStr.equalsIgnoreCase(random)){
			//	return new ModelAndView("redirect:/InnerLogin.jsp?message=3");
			//}

			//检查是否用连续多次失败记录
			long minutes = this.volidateFailTime(loginName, 0);
			if(minutes > 0){
				return new ModelAndView("redirect:/InnerLogin.jsp?message=8&minutes=" + minutes);
			}

			String clientIP = HttpRequestUtil.getIpAddress(request);
			userVO = loginBO.findLoginUser(loginName);
			if(userVO == null){ //用户不存在
				loginBO.addLoginLog(userVO, 1, clientIP, request.getSession().getId(), request.getServerName(), -1, "用户名不存在");
				return new ModelAndView("redirect:/InnerLogin.jsp?message=0");
			}

			/*
			 * byte[] b = NKRSAUtil.decryptByPrivateKey(password, NKRSAUtil.PRIVATE_KEY_STR); password = new String(b); if(!userVO.getPassword().equals(password)){ //密码不正确 loginBO.addLoginLog(userVO,
			 * 1, clientIP, request.getSession().getId(), request.getServerName(), -1, "密码不正确"); return new ModelAndView("redirect:/InnerLogin.jsp?message=0"); }
			 */
			userVO.setUserType(FHConstant.WEBACTION_TYPE_INNER);
			//用户权限菜单
			List<SmObjectVO> objectList = loginBO.findUserObjects(userVO.getUserId());
			userVO.setObjectList(objectList);

			//记录登录日志
			loginBO.insertLoginLog(userVO, clientIP, httpSession.getId(), userVO.getUserType());

			//记录登录日志			
			loginBO.addLoginLog(userVO, 1, clientIP, request.getSession().getId(), request.getServerName(), 1, null);

			//设置Session
			httpSession.setAttribute("LgUser", userVO);
			request.setAttribute("loginTime", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(DateUtil.getSysDate()));

		}catch (Exception e){
			e.printStackTrace();
			//request.setAttribute("message", "1");
			return new ModelAndView("redirect:/InnerLogin.jsp?message=1");
		}
		return new ModelAndView("/InnerIndex");
	}

	/**
	 * 带 功能描述：TODO
	 * @param user
	 * @param httpSession
	 * @param request
	 * @return
	 */
	
	public ModelAndView loginType(SmUserVO user,HttpSession httpSession,HttpServletRequest request) {
		LoginBO loginBO = new LoginBO();
		String loginName = user.getLoginName();
		String password = user.getPassword();
		SmUserVO userVO = null;
		//Map map = new HashMap();
		try{
			//验证码再次检查
			//String checkStr = request.getParameter("check_input");
			//String random = (String) httpSession.getAttribute("RANDOMVALIDATECODEKEY");
			//if(checkStr == null || "".equals(checkStr) || !checkStr.equalsIgnoreCase(random)){
			//	return new ModelAndView("redirect:/InnerLogin.jsp?message=3");
			//}

			//检查是否用连续多次失败记录
			long minutes = this.volidateFailTime(loginName, 0);
			if(minutes > 0){
				return new ModelAndView("redirect:/InnerLogin.jsp?message=8&minutes=" + minutes);
			}

			String clientIP = HttpRequestUtil.getIpAddress(request);

			userVO = loginBO.findLoginUser(loginName);
			if(userVO == null){ //用户不存在
				loginBO.addLoginLog(userVO, 1, clientIP, request.getSession().getId(), request.getServerName(), -1, "用户名不存在");
				return new ModelAndView("redirect:/InnerLogin.jsp?message=0");
			}

			byte[] b = NKRSAUtil.decryptByPrivateKey(password, NKRSAUtil.PRIVATE_KEY_STR);
			password = new String(b);
			if(!userVO.getPassword().equals(password)){ //密码不正确
				loginBO.addLoginLog(userVO, 1, clientIP, request.getSession().getId(), request.getServerName(), -1, "密码不正确");
				return new ModelAndView("redirect:/InnerLogin.jsp?message=0");
			}
			userVO.setUserType(FHConstant.WEBACTION_TYPE_INNER);
			//用户权限菜单
			List<SmObjectVO> objectList = loginBO.findUserObjects(userVO.getUserId());
			userVO.setObjectList(objectList);

			//记录登录日志
			loginBO.insertLoginLog(userVO, clientIP, httpSession.getId(), userVO.getUserType());

			//记录登录日志			
			loginBO.addLoginLog(userVO, 1, clientIP, request.getSession().getId(), request.getServerName(), 1, null);

			//设置Session
			httpSession.setAttribute("LgUser", userVO);
			request.setAttribute("loginTime", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(DateUtil.getSysDate()));

		}catch (Exception e){
			e.printStackTrace();
			//request.setAttribute("message", "1");
			return new ModelAndView("redirect:/InnerLogin.jsp?message=1");
		}
		return new ModelAndView("/InnerIndex");
	}

	/**
	 * 功能描述：内网用户退出登录
	 * @param user
	 * @param httpSession
	 * @param objectCodes
	 * @return
	 */
	@RequestMapping(value = "/InnerLogout")
	public ModelAndView innerLogout(HttpSession httpSession) {
		try{			
			SmUserVO user = (SmUserVO) httpSession.getAttribute("LgUser"); //得到Session
			if(user!=null) {
				httpSession.invalidate();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/InnerLogin.jsp");
	}


	/**
	 * 功能描述：页面跳转
	 * @param url
	 * @param flag
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/MainNav")
	public String MainNav(String url,String flag,HttpServletRequest request) {
		request.setAttribute("flag", flag);
		return url;
	}

	/**
	 * 功能描述：获取用户二级以下菜单
	 * @param objectList
	 * @return
	 */
	@RequestMapping(value = "/menuChange")
	@ResponseBody
	public static Map<String , Object> getMemuTree(String objectCodes,HttpSession session) {
		List<SmObjectVO> secondObjectList = getSonLevelObjectList(objectCodes, session, 2);
		StringBuffer treeStr = new StringBuffer();
		treeStr.append("{'menus':[");
		if(secondObjectList != null && secondObjectList.size() > 0){
			for(SmObjectVO objectVO2 : secondObjectList){
				String secondTreeId = objectVO2.getTreeId();
				String secondName = objectVO2.getName();
				String secondLink = objectVO2.getLink();
				List<SmObjectVO> threeObjectList = getSonLevelObjectList(secondTreeId, session, 3);
				//二级子菜单
				treeStr.append("{ menuid : '" + secondTreeId + "' ,");
				treeStr.append("menuname : '" + secondName + "' ,");
				treeStr.append("icon: 'icon-sys'");
				if(threeObjectList == null || threeObjectList.size() <= 0){

					treeStr.append(",'menus': [");
					treeStr.append("{ menuid : '" + secondTreeId + "0001" + "',");
					treeStr.append("menuname : '" + secondName + "',");
					treeStr.append("icon: 'icon-nav'");
					treeStr.append(",url: '" + secondLink + "' ");

					treeStr.append("}]");
				}else{
					treeStr.append(",'menus': [");
					for(SmObjectVO objectVO3 : threeObjectList){
						//三级子菜单
						String threeTreeId = objectVO3.getTreeId();
						String threeName = objectVO3.getName();
						String threeLink = objectVO3.getLink();

						treeStr.append("{ menuid : '" + threeTreeId + "',");
						treeStr.append("menuname : '" + threeName + "',");
						treeStr.append("icon: 'icon-nav'");

						treeStr.append(",url: '" + threeLink + "' ");

						treeStr.append("},");
					}
					treeStr.delete(treeStr.length() - 1, treeStr.length());
					treeStr.append("]");
				}
				treeStr.append("},");
			}
			treeStr.delete(treeStr.length() - 1, treeStr.length());
		}

		treeStr.append("]}");
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("treeStr", treeStr.toString());
		return map;
	}

	/**
	 * 获取子菜单集合
	 * @param objectCode
	 * @param session
	 * @param level
	 * @return
	 */
	private static List<SmObjectVO> getSonLevelObjectList(String objectCodes,HttpSession session,int level) {
		List<SmObjectVO> list = new ArrayList<SmObjectVO>();
		SmUserVO user = (SmUserVO) session.getAttribute("LgUser");
		List<SmObjectVO> objectList = user.getObjectList();
		if(objectList != null && objectList.size() > 0){
			for(SmObjectVO vo : objectList){
				String treeId = vo.getTreeId();
				if(treeId.length() == 4*level && treeId.startsWith(objectCodes)){
					list.add(vo);
				}

			}
		}
		return list;

	}



	/**
	 * 功能描述：获取登陆验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片  
		response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容  
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expire", 0); 
        RandomValidateCode randomValidateCode = new RandomValidateCode(); 
        try { 
			randomValidateCode.getRandcode(request, response);//输出验证码图片方法  
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }


	/**
	 * 功能描述：登陆验证码输入检查
	 * @param inputStr
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "checkVerify")
    @ResponseBody
    public String checkVerify(String inputStr, HttpSession session){
		if(inputStr == null || "".equals(inputStr.trim())){
			return "FALSE";
		}
		//从session中获取随机数
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
		if(inputStr.equals(random)){
			return "TRUE";//验证码正确
		}else{
			return "FALSE";//验证码错误
		}
    }


	/**
	 * 功能描述：内网用户修改密码
	 * @return
	 */
	@RequestMapping(value = "modifyUserPassword")
	@ResponseBody
	public JSONArray modifyUserPassword(String password,String newPassword1,String newPassword2,HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		LoginBO loginBO = new LoginBO();
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			if(vo.getUserType() != 0){
				throw new Exception("非法用户！");
			}
			//20190326-added by gcf improve:新增检查数据库的密码与界面上传递过来的参数是否一致。
			SmUserVO user = loginBO.findLoginUserByID(vo.getUserId()+"");
			//判断原密码是否输入正确
			if(user.getPassword().equals(password)){
				boolean flag = loginBO.updateUserPassWord(vo.getUserId(), newPassword1);
				if(flag){
					map.put("result", "success");
					map.put("message", "密码修改成功！");
				}else{
					map.put("result", "fail");
					map.put("message", "密码修改失败！");
				}
				map.put("id", flag + "");
			}else{
				map.put("result", "fail");
				map.put("message", "原密码输入错误！");
			}

			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "密码修改失败");
			json = JSONArray.fromObject(map);
			return json;

		}

	}


	/*************** 签约人登陆 **************************/
	/**
	 * 功能描述：签约人用户登录检查
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "checkSignerLogin")
	@ResponseBody
	public String checkSignerLogin(HttpServletRequest request) {
		LoginBO loginBO = new LoginBO();
		try{
			String loginName = request.getParameter("loginName");
			String password = request.getParameter("password");
			SignerVO signerVO = loginBO.findLoginSigner(StringUtil.getFullwidthStr(loginName, 10));
			if(signerVO == null){
				//用户名或错误
				return "E1";
			}
			//			byte[] b = NKRSAUtil.decryptByPrivateKey(password, NKRSAUtil.PRIVATE_KEY_STR);
			//			password = new String(b);
			//			if(password.equals(signerVO.getPwd())){
				if(signerVO.getIsEnable() == FHConstant.SIGNER_VALID_ON && signerVO.getStatus() == FHConstant.SIGNER_STATUS_PERMITTED){
				//登陆时间检查
				List<NonLoginTimeVO> dailyList = loginBO.findNonLoginTimeList(0);//每天非登陆时间
				List<NonLoginTimeVO> onceList = loginBO.findNonLoginTimeList(1);//一次性非登陆时间
					if( (dailyList == null || dailyList.isEmpty()) && (onceList == null || onceList.isEmpty())){
					//非登录时间未设置，请先从内网设置非登录时间。
						return "E4";
					}
					if(dailyList == null || dailyList.isEmpty()){
					//系统维护中，不可登录。
					return "E5";
					}
					int len = 6;
					Date sysdate = DateUtil.getSysDate();
					String date1 = DateUtil.getSysDateYYYYMMDD();
					String starttime = StringUtil.getFullwidthStr(String.valueOf(dailyList.get(0).getStartTime()), len);
					String endtime = StringUtil.getFullwidthStr(String.valueOf(dailyList.get(0).getEndTime()), len);

					Date startDate = DateUtil.parseDateTime2(date1 + starttime);
					Date endDate = DateUtil.parseDateTime2(date1 + endtime);
				boolean flag = false;//时间大小判断
					if(startDate.compareTo(endDate) > 0){
						flag = true;
					}
					long startCache = startDate.getTime();
					long endCache = endDate.getTime();
					if(! (flag)){
						if( (sysdate.getTime() >= startCache) && (sysdate.getTime() <= endCache)){
						//系统维护中，不可登录。
						return "E5";
						}

					}else{
						if( (sysdate.getTime() >= startCache) || (sysdate.getTime() <= endCache)){
						//系统维护中，不可登录。
						return "E5";
						}
					}
				//一次性日期检查
				if(onceList != null && onceList.size() > 0){
						for(NonLoginTimeVO timevo : onceList){
							String start = timevo.getStartDate() + StringUtil.getFullwidthStr(String.valueOf(timevo.getStartTime()), len);
							String end = timevo.getEndDate() + StringUtil.getFullwidthStr(String.valueOf(timevo.getEndTime()), len);
							startDate = DateUtil.parseDateTime2(start);
							endDate = DateUtil.parseDateTime2(end);
							startCache = startDate.getTime();
							endCache = endDate.getTime();
							if( (sysdate.getTime() >= startCache) && (sysdate.getTime() <= endCache)){
							//系统维护中，不可登录。
							return "E5";
							}
						}
					}


					return "TRUE";
				}else{
				//签约人无效
				return "E3";
				}
			//			}else{
			//				//密码错误
			//				return "E1";
			//			}

		}catch (Exception e){
			e.printStackTrace();
			//request.setAttribute("message", "1");
			return "ERROR";
		}
	}

	/**
	 * 功能描述：签约人用户登录
	 * @param user
	 * @param httpSession
	 * @param objectCodes
	 * @return
	 */
	@RequestMapping(value = "/OuterLogin")
	public ModelAndView outerLogin(SmUserVO user,HttpSession httpSession,HttpServletRequest request) {
		LoginBO loginBO = new LoginBO();
		String loginName = user.getLoginName();
		String password = user.getPassword();
		SmUserVO userVO = new SmUserVO();
		//Map map = new HashMap();
		try{
			//验证码再次检查
			String checkStr = request.getParameter("check_input");
			String random = (String) httpSession.getAttribute("RANDOMVALIDATECODEKEY");
			if(checkStr == null || "".equals(checkStr) || !checkStr.equalsIgnoreCase(random)){
				return new ModelAndView("redirect:/OuterLogin.jsp?message=3");
			}

			//检查是否用连续多次失败记录
			long minutes = this.volidateFailTime(StringUtil.getFullwidthStr(loginName, 10), 1);
			if(minutes > 0){
				return new ModelAndView("redirect:/OuterLogin.jsp?message=8&minutes=" + minutes);
			}

			String clientIP = HttpRequestUtil.getIpAddress(request);

			SignerVO signerVO = loginBO.findLoginSigner(StringUtil.getFullwidthStr(loginName, 10));
			if(signerVO == null){ //用户不存在
				loginBO.addLoginLog(userVO, 1, clientIP, request.getSession().getId(), request.getServerName(), -1, "用户名不存在");
				return new ModelAndView("redirect:/OuterLogin.jsp?message=0");
			}

			userVO.setUserId(signerVO.getSigner_ID());
			userVO.setUserType(FHConstant.WEBACTION_TYPE_OUTER);
			userVO.setLoginName(signerVO.getLoginName());

			byte[] b = NKRSAUtil.decryptByPrivateKey(password, NKRSAUtil.PRIVATE_KEY_STR);
			password = new String(b);
			if(!signerVO.getPwd().equals(password)){ //密码不正确
				loginBO.addLoginLog(userVO, 1, clientIP, request.getSession().getId(), request.getServerName(), -1, "密码不正确");
				return new ModelAndView("redirect:/OuterLogin.jsp?message=0");
			}
			if(signerVO.getIsEnable() != FHConstant.SIGNER_VALID_ON || signerVO.getStatus() != FHConstant.SIGNER_STATUS_PERMITTED){
				return new ModelAndView("redirect:/OuterLogin.jsp?message=2");
			}
			String lType = request.getParameter("loginType");
			int loginType = 0;
			if(lType != null && !"".equals(lType)){
				loginType = Integer.parseInt(lType);
			}

			//复制信息
			userVO.setRegionId(Integer.parseInt(signerVO.getAttribute("districtid").toString()));
			userVO.setOrgID(Long.parseLong(signerVO.getAttribute("orgid").toString()));
			userVO.setOrgName(signerVO.getAttribute("orgname").toString());
			userVO.setDisplayName(signerVO.getName());
			userVO.setPassword(signerVO.getPwd());
			userVO.setLoginType(loginType);
			userVO.setLoginName(signerVO.getLoginName());
			
			userVO.setUserType(FHConstant.WEBACTION_TYPE_OUTER);
			//用户权限菜单
			List<SmObjectVO> objectList = loginBO.findSignerObjects(userVO.getUserId());
			userVO.setObjectList(objectList);

			//记录登录日志
			loginBO.insertLoginLog(userVO, clientIP, httpSession.getId(), userVO.getUserType());

			//记录登录日志			
			loginBO.addLoginLog(userVO, 1, clientIP, request.getSession().getId(), request.getServerName(), 1, null);

			//设置Session
			httpSession.setAttribute("LgUser", userVO);
			request.setAttribute("loginTime", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(DateUtil.getSysDate()));

		}catch (Exception e){
			e.printStackTrace();
			//request.setAttribute("message", "1");
			return new ModelAndView("redirect:/OuterLogin.jsp?message=1");
		}
		return new ModelAndView("/OuterIndex");
	}

	/**
	 * 功能描述：内网用户退出登录
	 * @param user
	 * @param httpSession
	 * @param objectCodes
	 * @return
	 */
	@RequestMapping(value = "/OuterLogout")
	public ModelAndView outerLogout(HttpSession httpSession) {
		try{
			SmUserVO user = (SmUserVO) httpSession.getAttribute("LgUser"); //得到Session
			if(user != null){
				httpSession.invalidate();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/OuterLogin.jsp");
	}

	/**
	 * 功能描述：签约人用户修改密码
	 * @return
	 */
	@RequestMapping(value = "modifySignerPassword")
	@ResponseBody
	public JSONArray modifySignerPassword(String password,String newPassword1,String newPassword2,HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		LoginBO loginBO = new LoginBO();
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			if(vo.getUserType() != 1){
				throw new Exception("非法签约用户！");
			}
			//20190326-added by gcf improve:新增检查数据库的密码与界面上传递过来的参数是否一致。
			SignerVO signer = loginBO.findLoginSigner(vo.getLoginName());
			//判断原密码是否输入正确
			if(signer.getPwd().equals(password)){
				SignerVO signerVO = new SignerVO();
				signerVO.setPwd(newPassword1);
				signerVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				signerVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				signerVO.setUpdPerson(vo.getLoginName());
				signerVO.setSigner_ID(vo.getUserId());
				signerVO.setChanged(FHConstant.OUTWEB_PWD_IS_CHANGED);

				long flag = loginBO.update(signerVO);
				if(flag > 0){
					map.put("result", "success");
					map.put("message", "密码修改成功！");
				}else{
					map.put("result", "fail");
					map.put("message", "密码修改失败！");
				}
				map.put("id", flag + "");
			}else{
				map.put("result", "fail");
				map.put("message", "原密码输入错误！");
			}

			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "密码修改失败");
			json = JSONArray.fromObject(map);
			return json;

		}

	}

	/**
	 * 功能描述：获取签约人用户菜单
	 * @param objectList
	 * @return
	 */
	@RequestMapping(value = "/signerMenuChange")
	@ResponseBody
	public static Map<String , Object> getSignerMenuTree(String objectCodes,HttpSession session) {
		List<SmObjectVO> secondObjectList = getSonLevelObjectList(objectCodes, session, 1);
		StringBuffer treeStr = new StringBuffer();
		treeStr.append("{'menus':[");
		if(secondObjectList != null && secondObjectList.size() > 0){
			for(SmObjectVO objectVO2 : secondObjectList){
				String secondTreeId = objectVO2.getTreeId();
				String secondName = objectVO2.getName();
				String secondLink = objectVO2.getLink();
				List<SmObjectVO> threeObjectList = getSonLevelObjectList(secondTreeId, session, 2);
				//二级子菜单
				treeStr.append("{ menuid : '" + secondTreeId + "' ,");
				treeStr.append("menuname : '" + secondName + "' ,");
				treeStr.append("icon: 'icon-sys'");
				if(threeObjectList == null || threeObjectList.size() <= 0){

					treeStr.append(",'menus': [");
					treeStr.append("{ menuid : '" + secondTreeId + "0001" + "',");
					treeStr.append("menuname : '" + secondName + "',");
					treeStr.append("icon: 'icon-nav'");
					treeStr.append(",url: '" + secondLink + "' ");

					treeStr.append("}]");
				}else{
					treeStr.append(",'menus': [");
					for(SmObjectVO objectVO3 : threeObjectList){
						//三级子菜单
						String threeTreeId = objectVO3.getTreeId();
						String threeName = objectVO3.getName();
						String threeLink = objectVO3.getLink();

						treeStr.append("{ menuid : '" + threeTreeId + "',");
						treeStr.append("menuname : '" + threeName + "',");
						treeStr.append("icon: 'icon-nav'");

						treeStr.append(",url: '" + threeLink + "' ");

						treeStr.append("},");
					}
					treeStr.delete(treeStr.length() - 1, treeStr.length());
					treeStr.append("]");
				}
				treeStr.append("},");
			}
			treeStr.delete(treeStr.length() - 1, treeStr.length());
		}

		treeStr.append("]}");
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("treeStr", treeStr.toString());
		return map;
	}

	/**
	 * 功能描述：检查签约人密码是否修改过一次
	 * @param inputStr
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "checkSignerPWChanged")
	@ResponseBody
	public String checkSignerPWChanged(HttpSession session) {

		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			SignerVO signerVO = new SignerVO();
			LoginBO loginBO = new LoginBO();
			signerVO.setSigner_ID(vo.getUserId());
			signerVO = (SignerVO)loginBO.find(signerVO);
			if(signerVO.getChanged() == FHConstant.OUTWEB_PWD_NO_CHANGE){
				return "FALSE";
			}

		}catch (Exception e){
			e.printStackTrace();
			return "ERROR";
		}
		return "TRUE";
	}

	/**
	 * 功能描述：检查是否有公告信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "checkShowBulletin")
	@ResponseBody
	public String checkShowBulletin(HttpSession session) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			LoginBO loginBO = new LoginBO();
			List<BulletinVO> list = loginBO.findShowBulletinList(vo.getRegionId());
			if(list != null && list.size() > 0){
				return "TRUE";
			}
		}catch (Exception e){
			e.printStackTrace();
			return "ERROR";
		}
		return "FALSE";
	}

	/**
	 * 功能描述：显示公告信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/showBulletin")
	public String showBulletin(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			LoginBO loginBO = new LoginBO();
			List<BulletinVO> list = loginBO.findShowBulletinList(vo.getRegionId());
			request.setAttribute("bulletinList", list);
			return "/ShowBulletin";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：验证是否多次登录失败
	 * @param loginName
	 * 登录名
	 * @param userType
	 * 登录用户类型
	 * @return long 登录还需等待时间
	 */
	private long volidateFailTime(String loginName,int userType) {
		long minutes = 0;
		try{
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("loginName", "=", loginName));
			params.add(new MetaFilter("userType", "=", userType));
			Date nowDate = DateUtil.parseDateTime2(DateUtil.format(DateUtil.getSysDate(), "yyyyMMdd") + "000000");
			params.add(new MetaFilter("loginTime", ">", nowDate));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("logID", MetaOrder.DESC));
			Page page = new Page();
			//获取连续失败次数配置
			int failureTimes = Integer.parseInt(MiniConfiger.getProperty("login.FailureTimes"));
			page.setPageSize(failureTimes);
			LoginBO loginBO = new LoginBO();
			//以登录时间降序查询登录用户当天最近 配置次数 的登录记录，如果几次中有成功的则跳出，否则查询最后一次登录时间与当前时间间隔
			List<LogLoginTodayVO> loginTodayVOs = loginBO.search(LogLoginTodayVO.class, params, orders, page);
			if(loginTodayVOs.size() == failureTimes){
				boolean successStutas = true;
				for(LogLoginTodayVO logLoginTodayVO : loginTodayVOs){
					if(logLoginTodayVO.getSuccess() == 1){
						successStutas = true;
						break;
					}else{
						successStutas = false;
					}
				}
				if(!successStutas){
					Date lastDate = loginTodayVOs.get(0).getLoginTime();
					Date currentDate = DateUtil.getSysDate();
					System.out.println("lastDate:" + DateUtil.formatDateTime(lastDate) + ",currentDate:" + DateUtil.formatDateTime(currentDate));
					//比较最后登录时间与当前数据库时间的差值
					minutes = Math.abs(DateUtil.differentMinute(lastDate, currentDate));
					//获取配置的锁定间隔时间，与登录时间差值比较，如果差值大于间隔设置，可以登录，返回0；否则返回锁定时间
					long failureInterval = Long.parseLong(MiniConfiger.getProperty("login.FailureInterval")) / 60;
					if(minutes > failureInterval){
						minutes = 0;
					}else{
						minutes = failureInterval - minutes;
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return minutes;
	}
}
