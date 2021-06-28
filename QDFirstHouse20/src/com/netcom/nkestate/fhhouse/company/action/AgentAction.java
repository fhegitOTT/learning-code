package com.netcom.nkestate.fhhouse.company.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.bo.AgentBO;
import com.netcom.nkestate.fhhouse.company.bo.CompanyBO;
import com.netcom.nkestate.fhhouse.company.vo.AgentVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.vo.SmUserVO;

/*
 * 代理商开发模块Action
 */
@Controller
@RequestMapping("/inner/agentmanage")
public class AgentAction extends BaseAction {

	static Logger logger = Logger.getLogger(AgentAction.class.getName());

	/**
	 * 功能描述：代理商录入
	 * @return
	 */
	@RequestMapping("/gotoAgentAddQuery")
	public String agentList(HttpServletRequest request,HttpSession session) {
		try{
			return "fhhouse/company/agent/AgentQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：未提交审核的销售代理商列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoAgentAddQueryList")
	public String gotoAgentAddList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String logo = "part";
			String compCode = request.getParameter("compCode");
			String compName = request.getParameter("compName");
			request.setAttribute("compCode", compCode);
			request.setAttribute("compName", compName);

			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("district", "in", userDistrictList));
			params.add(new MetaFilter("status", "=", FHConstant.AGENT_STATUS_EDIT));
			if(compCode != null && !"".equals(compCode)){
				params.add(new MetaFilter("agentCode", "=", compCode));
			}
			if(compName != null && !"".equals(compName)){
				params.add(new MetaFilter("name", "like", "%" + compName + "%"));
			}
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
			orders.add(new MetaOrder("updTime", MetaOrder.DESC));

			AgentBO agentBO = new AgentBO();
			List<AgentVO> list = agentBO.search(AgentVO.class, params, orders, page);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("agentID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			//tableProperty.addSelectControl("radio", "selCompID", linkparam, "");
			tableProperty.addColumn("企业名称", "name", "doCompanyEdit2", linkparam);
			tableProperty.addColumn("地址", "contactadr");
			tableProperty.addColumn("法人代表", "delegate");
			tableProperty.addColumn("法人代码", "legalManCode");
			tableProperty.addColumn("企业ID", "agentID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("logo", logo);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/company/agent/AgentQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：代理商录入或编辑
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoAgentEdit")
	public String gotoCompanyEdit(HttpServletRequest request,HttpSession session) {
		try{
			AgentBO agentBO = new AgentBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String logo = request.getParameter("logo");
			String agentID = request.getParameter("agentID");
			String cmd = request.getParameter("cmd"); //add:代理商新增   edit：代理商修改
			AgentVO agvo = new AgentVO();
			agvo.setRegTypeNum(FHConstant.REGISTER_TYPE_GY); //默认值设置


			if("edit".equals(cmd)){
				agvo.setAgentID(Long.parseLong(agentID));
				agvo = (AgentVO) agentBO.find(agvo);
			}
			request.setAttribute("cmd", cmd);
			request.setAttribute("agvo", agvo);
			request.setAttribute("logo", logo);
			return "fhhouse/company/agent/AgentEdit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 代理商录入或编辑保存检查
	 * @return
	 */
	@RequestMapping(value = "/doAgentSaveCheck")
	@ResponseBody
	public JSONArray saveCheckAgent(HttpServletRequest request,HttpSession session,AgentVO agvo) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String cmd = request.getParameter("cmd"); //add:代理商新增   edit：代理商修改
			String agentId = request.getParameter("agentID");
			List<Object> userDistrictList = this.getUserDistricts(uservo.getRegionId());//获取用户操作区县列表

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("district", "in", userDistrictList));
			params.add(new MetaFilter("agentCode", "=", agvo.getAgentCode()));

			AgentBO agentBO = new AgentBO();
			List<AgentVO> list = agentBO.search(AgentVO.class, params);

			map.put("result", "success");
			map.put("message", "");
			if(list == null || list.size() <= 0){
				map.put("result", "success");
				map.put("message", "");
			}else if(list.size() == 1){

				if("add".equals(cmd)){
					map.put("result", "fail");
					map.put("message", "企业全国唯一编码已经存在！");
				}else{
					AgentVO vo = list.get(0);
					if(Long.parseLong(agentId) != vo.getAgentID()){
						map.put("result", "fail");
						map.put("message", "企业全国唯一编码已经存在！");
					}

				}
			}else{
				map.put("result", "fail");
				map.put("message", "企业全国唯一编码已经存在！");
			}


			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "代理商保存检查失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：代理商录入或编辑保存
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doAgentSave")
	public String saveAgent(HttpServletRequest request,HttpSession session,AgentVO agvo) {
		String url = "";
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			CompanyBO cmBO = new CompanyBO();
			String logo = request.getParameter("logo");
			String cmd = request.getParameter("cmd"); //add:企业新增   edit：企业修改
			//日期字段处理
			agvo.setBizEndDate(agvo.getBizEndDate().replaceAll("-", ""));
			agvo.setBizRegDate(agvo.getBizRegDate().replaceAll("-", ""));

			agvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			agvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			agvo.setUpdPerson(String.valueOf(uservo.getUserId()));
			if("add".equals(cmd)){
				agvo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				agvo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				agvo.setCrePerson(String.valueOf(uservo.getUserId()));
				long agentId = cmBO.add(agvo);
				if(agentId <= 0){
					throw new Exception("新增代理商失败！");
				}
				agvo.setAgentID(agentId);
			}else{
				long count = cmBO.update(agvo);
				if(count <= 0){
					throw new Exception("更新代理商失败！");
				}
			}

			request.setAttribute("cmd", cmd);
			request.setAttribute("agvo", agvo);
			if("part".equals(logo)){
				url = "redirect:/inner/agentmanage/gotoAgentAddQuery";
			}
			if("all".equals(logo)){
				url = "redirect:/inner/agentmanage/gotoAgentUpdateQuery";
			}
			return url;
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 代理商提交审核
	 * @return
	 */
	@RequestMapping(value = "doAgentFirstSubmit")
	@ResponseBody
	public JSONArray firstSubmitAgent(HttpServletRequest request,HttpSession session,AgentVO agentvo) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			AgentVO agvo = new AgentVO();
			agvo.setAgentID(agentvo.getAgentID());
			agvo.setStatus(FHConstant.AGENT_STATUS_SUBMIT);
			agvo.setFirstAuditDate(DateUtil.getSysDateYYYYMMDD());
			agvo.setFirstCensor(String.valueOf(uservo.getUserId()));
			//			agvo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			//			agvo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			//			agvo.setCrePerson(uservo.getLoginName());
			agvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			agvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			agvo.setUpdPerson(String.valueOf(uservo.getUserId()));

			AgentBO agentBO = new AgentBO();
			long count = agentBO.update(agvo);
			if(count == 1){
				map.put("result", "success");
				map.put("message", "提交成功！");
			}else{
				map.put("result", "fail");
				map.put("message", "提交失败！");
			}


			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "代理商提交出错");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：代理商审核
	 * @return
	 */
	@RequestMapping("/gotoAgentCheckQuery")
	public String gotoCheckAgent() {
		try{
			return "fhhouse/company/agent/AgentCheckQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：待审核的销售代理商列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoAgentCheckQueryList")
	public String gotoCheckAgentList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String compCode = request.getParameter("compCode");
			String compName = request.getParameter("compName");
			request.setAttribute("compCode", compCode);
			request.setAttribute("compName", compName);

			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("district", "in", userDistrictList));
			params.add(new MetaFilter("status", "=", FHConstant.AGENT_STATUS_SUBMIT));
			if(compCode != null && !"".equals(compCode)){
				params.add(new MetaFilter("agentCode", "=", compCode));
			}
			if(compName != null && !"".equals(compName)){
				params.add(new MetaFilter("name", "like", "%" + compName + "%"));
			}
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
			orders.add(new MetaOrder("updTime", MetaOrder.DESC));

			AgentBO agentBO = new AgentBO();
			List<AgentVO> list = agentBO.search(AgentVO.class, params, orders, page);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("agentID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("企业代码", "agentCode", "doCompanyEdit2", linkparam);
			tableProperty.addColumn("企业名称", "name");
			tableProperty.addColumn("法人代表", "delegate");
			tableProperty.addColumn("提交审核日期", "firstDate");
			tableProperty.addColumn("企业ID", "agentID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "fhhouse/company/agent/AgentCheckQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：代理商修改
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoCheckAgentEdit")
	public String gotoCheckAgentEdit(HttpServletRequest request,HttpSession session) {
		try{
			AgentBO agentBO = new AgentBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String agentID = request.getParameter("agentID");
			String cmd = request.getParameter("cmd"); //add:代理商新增   edit：代理商修改
			AgentVO agvo = new AgentVO();
			agvo.setRegTypeNum(FHConstant.REGISTER_TYPE_GY); //默认值设置


			if("edit".equals(cmd)){
				agvo.setAgentID(Long.parseLong(agentID));
				agvo = (AgentVO) agentBO.find(agvo);
			}
			request.setAttribute("cmd", cmd);
			request.setAttribute("agvo", agvo);
			return "fhhouse/company/agent/AgentCheckEdit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：代理商审核后状态
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/submitCheck")
	public String submitCheckAgent(HttpServletRequest request,HttpSession session) {
		try{
			AgentBO agentBO = new AgentBO();
			SmUserVO suvo = (SmUserVO) session.getAttribute("LgUser");
			String agentId = request.getParameter("agentID");
			String status = request.getParameter("status");
			String finalMark = request.getParameter("finalMark");
			finalMark = URLDecoder.decode(finalMark, "utf-8"); //处理中文传参乱码问题
			AgentVO vo = new AgentVO();
			vo.setAgentID(Long.parseLong(agentId));
			vo = (AgentVO) agentBO.find(vo);
			if("1".equals(status)){
				vo.setStatus(FHConstant.AGENT_STATUS_PASSED);
			}
			if("2".equals(status)){
				vo.setStatus(FHConstant.AGENT_STATUS_NOPASSED);
			}
			vo.setFinalAuditDate(DateUtil.getSysDateYYYYMMDD());
			vo.setFinalCensor(String.valueOf(suvo.getUserId()));
			vo.setFinalMark(finalMark);
			agentBO.update(vo);
			return "redirect:/inner/agentmanage/gotoAgentCheckQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：代理商修改
	 * @return
	 */
	@RequestMapping("/gotoAgentUpdateQuery")
	public String gotoSearchAgent() {
		try{
			return "fhhouse/company/agent/AgentUpdateQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：待审核的销售代理商列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoAgentUpdateQueryList")
	public String gotoAgentList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String compCode = request.getParameter("compCode");
			String compName = request.getParameter("compName");
			request.setAttribute("compCode", compCode);
			request.setAttribute("compName", compName);

			String logo = "all";
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			List<Object> statusList = new ArrayList<Object>();
			statusList.add(String.valueOf(FHConstant.AGENT_STATUS_PASSED));
			statusList.add(String.valueOf(FHConstant.AGENT_STATUS_NOPASSED));
			statusList.add(String.valueOf(FHConstant.AGENT_STATUS_RECOVER));
			statusList.add(String.valueOf(FHConstant.AGENT_STATUS_CANCEL));
			statusList.add(String.valueOf(FHConstant.AGENT_STATUS_NOPERMITTED));
			statusList.add(String.valueOf(FHConstant.AGENT_STATUS_PERMITTED));

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("district", "in", userDistrictList));
			params.add(new MetaFilter("status", "in", statusList));
			if(compCode != null && !"".equals(compCode)){
				params.add(new MetaFilter("agentCode", "=", compCode));
			}
			if(compName != null && !"".equals(compName)){
				params.add(new MetaFilter("name", "like", "%" + compName + "%"));
			}
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
			orders.add(new MetaOrder("updTime", MetaOrder.DESC));

			AgentBO agentBO = new AgentBO();
			List<AgentVO> list = agentBO.search(AgentVO.class, params, orders, page);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("agentID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("企业名称", "name", "doCompanyEdit2", linkparam);
			tableProperty.addColumn("地址", "contactadr");
			tableProperty.addColumn("法人代表", "delegate");
			tableProperty.addColumn("状态", "status_dict_name");
			tableProperty.addColumn("企业ID", "agentID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("logo", logo);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/company/agent/AgentQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：代理商撤销
	 * @return
	 */
	@RequestMapping("/gotoAgentRevokedQuery")
	public String gotoSearchRevokedAgent() {
		try{
			return "fhhouse/company/agent/AgentRevokedQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：代理商撤销列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoAgentRevokedQueryList")
	public String gotoRevokedAgentList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			AgentBO agentBO = new AgentBO();
			String compCode = request.getParameter("compCode");
			String compName = request.getParameter("compName");
			String status = request.getParameter("status");
			request.setAttribute("compCode", compCode);
			request.setAttribute("compName", compName);
			request.setAttribute("status", status);

			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("district", "in", userDistrictList));
			if(compCode != null && !"".equals(compCode)){
				params.add(new MetaFilter("agentCode", "=", compCode));
			}
			if(compName != null && !"".equals(compName)){
				params.add(new MetaFilter("name", "like", "%" + compName + "%"));
			}
			if(status != null && !"".equals(status)){
				params.add(new MetaFilter("status", "=", Integer.parseInt(status)));
			}else{
				params.add(new MetaFilter("status", "=", FHConstant.AGENT_STATUS_CANCEL));
			}

			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
			orders.add(new MetaOrder("updTime", MetaOrder.DESC));

			List<AgentVO> list = agentBO.search(AgentVO.class, params, orders, page);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("agentID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("企业代码", "agentCode", "doCompanyEdit2", linkparam);
			tableProperty.addColumn("企业名称", "name");
			tableProperty.addColumn("通过时间", "finalDate");
			tableProperty.addColumn("法人代表", "delegate");
			tableProperty.addColumn("状态", "status_dict_name");
			tableProperty.addColumn("查看详细", "查看", "showAgent", linkparam, "查看", null);
			tableProperty.addColumn("企业ID", "agentID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "fhhouse/company/agent/AgentRevokedQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：代理商撤销编辑
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoAgentRevokedEdit")
	public String gotoAgentRevokedEdit(HttpServletRequest request,HttpSession session) {
		try{
			AgentBO agentBO = new AgentBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String agentID = request.getParameter("agentID");
			String cmd = request.getParameter("cmd"); //add:代理商新增   edit：代理商修改
			AgentVO agvo = new AgentVO();
			agvo.setRegTypeNum(FHConstant.REGISTER_TYPE_GY);

			if("edit".equals(cmd)){
				agvo.setAgentID(Long.parseLong(agentID));
				agvo = (AgentVO) agentBO.find(agvo);
			}
			request.setAttribute("cmd", cmd);
			request.setAttribute("agvo", agvo);
			return "fhhouse/company/agent/AgentRevokedEdit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：代理商撤销或恢复
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/revokedOrRestore")
	public String revokedOrRestoreAgent(HttpServletRequest request,HttpSession session) {
		try{
			AgentBO agentBO = new AgentBO();
			String agentId = request.getParameter("agentID");
			String status = request.getParameter("status");
			AgentVO vo = new AgentVO();
			vo.setAgentID(Long.parseLong(agentId));
			vo = (AgentVO) agentBO.find(vo);
			if("5".equals(status)){
				vo.setStatus(FHConstant.AGENT_STATUS_RECOVER);
			}
			if("2".equals(status)){
				vo.setStatus(FHConstant.AGENT_STATUS_CANCEL);
			}
			agentBO.update(vo);
			return "redirect:/inner/agentmanage/gotoAgentRevokedQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：代理商发布
	 * @return
	 */
	@RequestMapping("/gotoAgentPublishQuery")
	public String gotoAgentPublishQuery() {
		try{
			return "fhhouse/company/agent/AgentPublishQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：待发布的销售代理商列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoAgentPublishQueryList")
	public String gotoAgentPublishQueryList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String compCode = request.getParameter("compCode");
			String compName = request.getParameter("compName");
			request.setAttribute("compCode", compCode);
			request.setAttribute("compName", compName);

			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("district", "in", userDistrictList));
			params.add(new MetaFilter("status", "=", FHConstant.AGENT_STATUS_PASSED));
			if(compCode != null && !"".equals(compCode)){
				params.add(new MetaFilter("agentCode", "=", compCode));
			}
			if(compName != null && !"".equals(compName)){
				params.add(new MetaFilter("name", "like", "%" + compName + "%"));
			}
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
			orders.add(new MetaOrder("updTime", MetaOrder.DESC));

			AgentBO agentBO = new AgentBO();
			List<AgentVO> list = agentBO.search(AgentVO.class, params, orders, page);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("agentID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("企业代码", "agentCode", "doAgentEdit", linkparam);
			tableProperty.addColumn("企业名称", "name");
			tableProperty.addColumn("法人代表", "delegate");
			tableProperty.addColumn("提交审核日期", "firstDate");
			tableProperty.addColumn("企业ID", "agentID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "fhhouse/company/agent/AgentPublishQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：代理商发布编辑
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoAgentPublishEdit")
	public String gotoAgentPublishEdit(HttpServletRequest request,HttpSession session) {
		try{
			AgentBO agentBO = new AgentBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String agentID = request.getParameter("agentID");
			String cmd = request.getParameter("cmd"); //add:代理商新增   edit：代理商修改
			AgentVO agvo = new AgentVO();

			if("edit".equals(cmd)){
				agvo.setAgentID(Long.parseLong(agentID));
				agvo = (AgentVO) agentBO.find(agvo);
			}
			request.setAttribute("cmd", cmd);
			request.setAttribute("agvo", agvo);
			return "fhhouse/company/agent/AgentPublishEdit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：代理商撤销或恢复
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/publishSubmit")
	public String publishSubmit(HttpServletRequest request,HttpSession session) {
		try{
			AgentBO agentBO = new AgentBO();
			String agentId = request.getParameter("agentID");
			String status = request.getParameter("status");
			AgentVO vo = new AgentVO();
			vo.setAgentID(Long.parseLong(agentId));
			vo = (AgentVO) agentBO.find(vo);
			if("1".equals(status)){
				vo.setStatus(FHConstant.AGENT_STATUS_PERMITTED);
			}
			if("2".equals(status)){
				vo.setStatus(FHConstant.AGENT_STATUS_NOPERMITTED);
			}
			agentBO.update(vo);
			return "redirect:/inner/agentmanage/gotoAgentPublishQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

}
