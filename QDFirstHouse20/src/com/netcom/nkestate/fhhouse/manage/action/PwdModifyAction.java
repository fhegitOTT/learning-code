package com.netcom.nkestate.fhhouse.manage.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.fhhouse.company.vo.EnterpriseQualifyVO;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.fhhouse.manage.bo.PwdModifyBO;
import com.netcom.nkestate.fhhouse.project.vo.CompCancelPwdVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractCancelPwdVO;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.util.StringUtil;
import com.netcom.nkestate.system.vo.SmUserVO;

/**
 * 系统管理 -重置密码Action
 */
@Controller
@RequestMapping("/inner/pwdmodify")
public class PwdModifyAction extends BaseAction {

	/**
	 * 功能描述：转到签约用户查询
	 * @return
	 */
	@RequestMapping(value = "/gotoSigerQuery")
	public String gotoQuerySiger(HttpServletRequest request,HttpSession session) {
		try{
			return "fhhouse/manage/pwdmodify/SignerQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：签约用户查询
	 * @return JSONArray
	 */
	@RequestMapping(value = "/findSigner")
	@ResponseBody
	public JSONArray findSigner(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			PwdModifyBO pmBo = new PwdModifyBO();
			String loginname = request.getParameter("loginName");
			int len = 10;
			String loginName = StringUtil.getFullwidthStr(loginname, len);
			SignerVO sVo = new SignerVO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("loginName", "=", loginName));
			List list = pmBo.search(SignerVO.class, params);
			if(list != null && list.size() > 0){
				SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
				String districtStr = this.getUserSqlDistricts(vo.getRegionId());
				List<SignerVO> lists = new PwdModifyBO().querySigner(loginName, districtStr);
				if(!lists.isEmpty() && lists.size() > 0){
					SignerVO signerVO = lists.get(0);
					map.put("result", "success");
					map.put("message", loginname);
				}else{
					map.put("result", "fail");
					map.put("message", "不能对该签约人重置密码！");
				}
			}else{
				map.put("result", "fail");
				map.put("message", "未找到对应签约人记录！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "签约用户查询失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：签约用户信息查询，转到签约用户密码重置
	 * @return
	 */
	@RequestMapping(value = "/gotoSignerPwdMoify")
	public String querySinger(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String loginname = request.getParameter("loginName");
			int len = 10;
			String loginName = StringUtil.getFullwidthStr(loginname, len);
			PwdModifyBO pmBo = new PwdModifyBO();
			String districtStr = this.getUserSqlDistricts(vo.getRegionId());
			List<SignerVO> list = pmBo.querySigner(loginName, districtStr);
			if(!list.isEmpty() && list.size() > 0){
				SignerVO sVo = list.get(0);
				request.setAttribute("signerId", sVo.getSigner_ID());
				request.setAttribute("loginName", sVo.getLoginName());
				request.setAttribute("name", sVo.getName());
			}else{
				request.setAttribute("loginName", loginName + "不存在！请返回重新输入！");
			}
			return "fhhouse/manage/pwdmodify/SignerPwdModify";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：签约用户密码重置
	 * @return JSONArray
	 */
	@RequestMapping(value = "/signerPwdModify")
	@ResponseBody
	public JSONArray modifySignerPwd(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			String signerId = request.getParameter("signerId");
			long signer_ID = 0;
			if(signerId != null && !"".equals(signerId)){
				signer_ID = Long.parseLong(signerId);
			}
			String newPwd = request.getParameter("newPwd");
			String newPwdConfirm = request.getParameter("newPwdConfirm");
			if(!newPwd.trim().equals(newPwdConfirm.trim())){
				map.put("result", "fail");
				map.put("message", "新密码两次输入不一致！");
			}else{
				SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
				SignerVO sVo = new SignerVO();
				sVo.setSigner_ID(signer_ID);
				sVo.setPwd(newPwd);
				sVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				sVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				sVo.setUpdPerson(vo.getLoginName());
				long count = new PwdModifyBO().update(sVo);
				if(count > 0){
					map.put("result", "success");
					map.put("message", "重置密码成功！");
				}else{
					map.put("result", "fail");
					map.put("message", "重置密码失败！");
				}
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "重置密码失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：转到合同甲方查询
	 * @return
	 */
	@RequestMapping(value = "/gotoQueryEnterPrise")
	public String gotoQueryEnterPrise(HttpServletRequest request,HttpSession session) {
		try{
			return "fhhouse/manage/pwdmodify/EnterPriseQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同甲方查询
	 * @return JSONArray
	 */
	@RequestMapping(value = "/findEnterPrise")
	@ResponseBody
	public JSONArray findEnterPrise(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			String compCode = request.getParameter("compCode");
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> districtList = this.getUserDistricts(vo.getRegionId());
			PwdModifyBO pmBO = new PwdModifyBO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("bizdistrict", "in", districtList));
			params.add(new MetaFilter("comp_Code", "=", compCode));
			List<EnterpriseQualifyVO> list = pmBO.search(EnterpriseQualifyVO.class, params);
			if(!list.isEmpty() && list.size() > 0){
				EnterpriseQualifyVO eVo = list.get(0);
				String districtStr = this.getUserSqlDistricts(vo.getRegionId());
				List<Map<String , Object>> lists = pmBO.queryProject(compCode, districtStr);
				if(!lists.isEmpty() && lists.size() > 0){
					map.put("result", "success");
					map.put("message", eVo.getName());
					map.put("message1", eVo.getComp_ID());
				}else{
					map.put("result", "fail");
					map.put("message", "不能对该企业项目重置密码！");
				}
			}else{
				map.put("result", "fail");
				map.put("message", "未找到对应企业记录！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "企业查询失败！");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：转到合同甲方密码重置
	 * @return
	 */
	@RequestMapping(value = "/gotoCompCancelPwdMoify")
	public String gotoCompCancelPwdMoify(HttpServletRequest request,HttpSession session) {
		try{
			long compID = Long.parseLong(request.getParameter("compID"));
			String name = request.getParameter("name");
			String compCode = request.getParameter("compCode");
			request.setAttribute("compID", compID);
			request.setAttribute("name", URLDecoder.decode(name, "UTF-8"));
			request.setAttribute("compCode", compCode);
			return "fhhouse/manage/pwdmodify/CompCancelPwdMoify";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：合同甲方项目查询
	 * @return JSONArray
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public JSONArray queryProject(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String compCode = request.getParameter("compCode");
			PwdModifyBO pmBo = new PwdModifyBO();
			String districtStr = this.getUserSqlDistricts(vo.getRegionId());
			List<Map<String , Object>> list = pmBo.queryProject(compCode, districtStr);
			JSONArray json = JSONArray.fromObject(list);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 功能描述：合同甲方密码重置
	 * @return JSONArray
	 */
	@RequestMapping(value = "/compCancelPwdMoify")
	@ResponseBody
	public JSONArray modifyCompCancelPwd(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			long compID = Long.parseLong(request.getParameter("compID"));
			long projectID = Long.parseLong(request.getParameter("projectID"));
			String newPwd = request.getParameter("newPwd");
			String newPwdConfirm = request.getParameter("newPwdConfirm");
			if(!newPwd.trim().equals(newPwdConfirm.trim())){
				map.put("result", "fail");
				map.put("message", "新密码两次输入不一致！");
			}else{
				SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
				CompCancelPwdVO ccpVo = new CompCancelPwdVO();
				ccpVo.setCompID(compID);
				ccpVo.setProjectID(projectID);
				ccpVo.setPwd(newPwd);
				ccpVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				ccpVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				ccpVo.setUpdPerson(vo.getLoginName());
				long count = new PwdModifyBO().update(ccpVo);
				if(count > 0){
					map.put("result", "success");
					map.put("message", "重置密码成功！");
				}else{
					map.put("result", "fail");
					map.put("message", "重置密码失败！");
				}
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "重置密码失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：转到合同乙方查询
	 * @return
	 */
	@RequestMapping(value = "/gotoQueryBuyer")
	public String gotoQueryBuyer(HttpServletRequest request,HttpSession session) {
		try{
			return "fhhouse/manage/pwdmodify/BuyerQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同乙方查询
	 * @return JSONArray
	 */
	@RequestMapping(value = "/findBuyer")
	@ResponseBody
	public JSONArray findBuyer(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			long contractID = Long.parseLong(request.getParameter("contractID"));
			PwdModifyBO pmBO = new PwdModifyBO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("contractID", "=", contractID));
			List<BuyerInfoVO> list = pmBO.search(BuyerInfoVO.class, params);
			if(!list.isEmpty() && list.size() > 0){
				SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
				String districtStr = this.getUserSqlDistricts(vo.getRegionId());
				List<Map<String , Object>> lists = pmBO.queryBuyer(contractID, districtStr);
				if(!lists.isEmpty() && lists.size() > 0){
					map.put("result", "success");
					map.put("message", "");
				}else{
					map.put("result", "fail");
					map.put("message", "不能对该合同下用户重置密码！");
				}
			}else{
				map.put("result", "fail");
				map.put("message", "未找到对应合同记录！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "合同查询失败！");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：转到合同乙方密码重置
	 * @return
	 */
	@RequestMapping(value = "/gotoContractCancelPwdModify")
	public String gotoContractCancelPwdModify(HttpServletRequest request,HttpSession session) {
		try{
			long contractID = Long.parseLong(request.getParameter("contractID"));
			request.setAttribute("contractID", contractID);
			return "fhhouse/manage/pwdmodify/ContractCancelPwdModify";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同乙方用户查询
	 * @return JSONArray
	 */
	@RequestMapping(value = "/queryBuyer")
	@ResponseBody
	public JSONArray queryBuyer(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			long contractID = Long.parseLong(request.getParameter("contractID"));
			PwdModifyBO pmBo = new PwdModifyBO();
			String districtStr = this.getUserSqlDistricts(vo.getRegionId());
			List<Map<String , Object>> list = pmBo.queryBuyer(contractID, districtStr);
			JSONArray json = JSONArray.fromObject(list);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 功能描述：合同乙方密码重置
	 * @return JSONArray
	 */
	@RequestMapping(value = "/contractCancelPwdModify")
	@ResponseBody
	public JSONArray contractCancelPwdModify(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			long contractID = Long.parseLong(request.getParameter("contractID"));
			int serial = Integer.parseInt(request.getParameter("serial"));
			String newPwd = request.getParameter("newPwd");
			String newPwdConfirm = request.getParameter("newPwdConfirm");
			if(!newPwd.trim().equals(newPwdConfirm.trim())){
				map.put("result", "fail");
				map.put("message", "新密码两次输入不一致！");
			}else{
				SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
				ContractCancelPwdVO ccpVo = new ContractCancelPwdVO();
				ccpVo.setContractID(contractID);
				ccpVo.setSerial(serial);
				ccpVo.setPwd(newPwd);
				ccpVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				ccpVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				ccpVo.setUpdPerson(vo.getLoginName());
				long count = new PwdModifyBO().update(ccpVo);
				if(count > 0){
					map.put("result", "success");
					map.put("message", "重置密码成功！");
				}else{
					map.put("result", "fail");
					map.put("message", "重置密码失败！");
				}
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "重置密码失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：重置密码打印
	 * @return
	 */
	@RequestMapping(value = "/printNewPwd")
	public String printNewPwd(HttpServletRequest request,HttpSession session) {
		try{
			request.setAttribute("string1", URLDecoder.decode(request.getParameter("string1"), "UTF-8"));
			request.setAttribute("string2", URLDecoder.decode(request.getParameter("string2"), "UTF-8"));
			request.setAttribute("string3", URLDecoder.decode(request.getParameter("string3"), "UTF-8"));
			return "fhhouse/manage/pwdmodify/PrintNewPwd";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
}
