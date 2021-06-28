/**
 * <p>IContextInitImpl.java </p>
 *
 * <p>系统名称: 青岛登记质量监管系统<p>  
 * <p>功能描述: TODO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2018-12-27<p>
 * 
 */
package com.netcom.nkestate.system.action;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.netcom.nkestate.system.bo.LoginBO;
import com.netcom.nkestate.system.vo.SmUserVO;

import edu.yale.its.tp.cas.client.IContextInit;


public class IContextInitImpl implements IContextInit {

	public String getTranslatorUser(String userID) {
		System.out.println("getTranslatorUser--->userID:"+userID);
		//TODO 业务系统获取用户 
		LoginBO userBO = new LoginBO();
		SmUserVO userVO = null;
		try{
			userVO = userBO.findLoginUser(userID);
			if(userVO != null){
				return String.valueOf(userVO.getUserId());
			}else{
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "1";
	}

	public void initContext(ServletRequest request,ServletResponse response,FilterChain chain,String userId) {
		System.out.println("initContext---->userId:"+userId);
		if(userId == null){
			throw new RuntimeException("Illegal user");
		}
		try{
			request.setAttribute("userID", userId);
			chain.doFilter(request, response);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
