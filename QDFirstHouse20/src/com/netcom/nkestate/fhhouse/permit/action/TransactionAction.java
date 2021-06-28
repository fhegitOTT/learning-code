/**
 * <p>TransactionAction.java </p>
 *
 * <p>系统名称: 青岛登记质量监管系统<p>  
 * <p>功能描述: 登记案件Action<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: zzg</p>
 * <p>创建时间: 2018-5-8<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.permit.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.common.NKRSAUtil;
import com.netcom.nkestate.fhhouse.interfaces.action.RealestateFinder;
import com.netcom.nkestate.fhhouse.permit.vo.EmptyVO;
import com.netcom.nkestate.framework.MiniConfiger;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.vo.SmUserVO;

@Controller
@RequestMapping(value = "/transaction")
public class TransactionAction  extends BaseAction{
	/**
	 * 功能描述：案件详细信息查看跳转服务
	 * @return 
	 */
	@RequestMapping(value = "/forwardFrame")
	public String forwardFrame(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		
		try{
			//验证案件是否为登记质量系统已抽查案件（考虑用户区县）
			EmptyVO transactionVO = null; 
			String transactionID=request.getParameter("transactionID");
			if(transactionID==null||"".equals(transactionID)){
				throw new Exception("参数异常，非法请求！");
			}
			SmUserVO user = (SmUserVO) session.getAttribute("LgUser"); //得到Session
			RealestateFinder finder = new RealestateFinder();
			//查询登记案件信息
			List<EmptyVO> list = finder.queryTransaction(Long.parseLong(transactionID), 1);
			//如果案件在现势表中不存在，则查询归档表
			if(list == null || list.size() == 0){
				List<EmptyVO> arcList = finder.queryTransaction(Long.parseLong(transactionID), -1);
				if(arcList != null && arcList.size() > 0){
					transactionVO = arcList.get(0);
				}
			}else {
				transactionVO = list.get(0);
			}
			
			//无查看权限
			if(transactionVO==null){
				throw new Exception("非本系统案件,无查看权限！");
			}
			
			//当前用户为市中心用户，要求案件也是市中心案件
//			int districtID=-1;
//			if(Constant.isDistrictCenter(user.getDistrictID())){
//				if(Constant.isDistrictCenter(transactionVO.getDistrictID())){
//					districtID=transactionVO.getDistrictID();
//				}else{
//					//无查看权限
//					throw new Exception("没有查看权限！");
//				}
//			}else{
//				if(user.getDistrictID()==transactionVO.getDistrictID()){
//					districtID=transactionVO.getDistrictID();
//				}else{
//					//无查看权限
//					throw new Exception("没有查看权限！");
//				}
//			}
			String datetime = DateUtil.format(DateUtil.getSysDate(), "yyyy-MM-dd HH:mm:ss");
//			String userInfo=user.getLoginName()+"@@"+user.getPassword()+"@@"+transactionVO.getAttribute("transactionID").toString()+"@@"+transactionVO.getAttribute("districtID").toString()+"@@"+datetime+"@@ViewFrameForDJZL";
			String userName = MiniConfiger.getProperty("djUserName");
			String password = MiniConfiger.getProperty("djPassword");
			String userInfo=userName+"@@"+password+"@@"+transactionVO.getAttribute("transactionID").toString()+"@@"+transactionVO.getAttribute("districtID").toString()+"@@"+datetime+"@@ViewFrameForDJZL";
			byte[] encodedData = NKRSAUtil.encryptByPublicKey(userInfo, NKRSAUtil.PUBLIC_KEY_STR_DJ);
			//使用预先生成的私钥信息解密
			String encodedUserInfo = NKRSAUtil.encryptBASE64ForPage(encodedData);
//			System.out.println("viewInfo="+encodedUserInfo);
			//写入业务日志
			String djUrl=MiniConfiger.getProperty("view.DJServiceURL");
			return "redirect:"+djUrl+"?viewInfo="+encodedUserInfo;
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
}
