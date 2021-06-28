/**
 * <p>CompanyAction.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 开发企业Action <p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.company.action;

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
import com.netcom.nkestate.common.PassGenerator;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.bo.CompanyBO;
import com.netcom.nkestate.fhhouse.company.vo.AgentVO;
import com.netcom.nkestate.fhhouse.company.vo.EnterpriseQualifyVO;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.fhhouse.project.bo.ProjectBO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingHouseVO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.project.vo.PresellVO;
import com.netcom.nkestate.fhhouse.project.vo.ProPreBldSignerVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.bo.SystemBO;
import com.netcom.nkestate.system.vo.SignerActionsVO;
import com.netcom.nkestate.system.vo.SmObjectVO;
import com.netcom.nkestate.system.vo.SmUserVO;

/**
 * 开发企业资质模块Action
 */
@Controller
@RequestMapping(value = "/inner/companymanage")
public class CompanyAction extends BaseAction {

	/**
	 * 功能描述：开发企业资质录入
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoCompanyAddQuery")
	public String gotoCompanyAdd(HttpServletRequest request,HttpSession session) {
		try{
			//返回标志  add:资质录入 edit:资质修改
			session.setAttribute("backFlag", "add");
			return "/fhhouse/company/CompanyAddQueryFrame";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：开发企业资质录入列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoCompanyAddQueryList")
	public String gotoCompanyAddList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String compCode = request.getParameter("compCode");
			String compName = request.getParameter("compName");
			request.setAttribute("compCode", compCode);
			request.setAttribute("compName", compName);
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			
			CompanyBO cmBO = new CompanyBO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("bizdistrict", "in", userDistrictList));
			params.add(new MetaFilter("status", "=", FHConstant.COMP_STATUS_EDIT));
			if(compCode != null && !"".equals(compCode)){
				params.add(new MetaFilter("comp_Code", "=", compCode));
			}
			if(compName != null && !"".equals(compName)){
				params.add(new MetaFilter("name", "like", "%" + compName + "%"));
			}
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
			orders.add(new MetaOrder("updTime", MetaOrder.DESC));
			

			List<EnterpriseQualifyVO> list = cmBO.search(EnterpriseQualifyVO.class, params, orders, page);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("comp_ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addSelectControl("radio", "selCompID", linkparam, "");
			tableProperty.addColumn("企业名称", "name", "doCompanyEdit2", linkparam);
			tableProperty.addColumn("企业编码", "comp_Code");
			tableProperty.addColumn("地址", "regadr");
			tableProperty.addColumn("法人代表", "delegate");
			tableProperty.addColumn("法人代码", "legalManCode");
			tableProperty.addColumn("企业ID", "comp_ID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/company/CompanyAddQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：开发企业资质录入
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoCompanyEdit")
	public String gotoCompanyEdit(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String logo = request.getParameter("logo");
			CompanyBO cmBO = new CompanyBO();
			String compId = request.getParameter("compId");
			String cmd = request.getParameter("cmd"); //add:企业新增   edit：企业修改
			EnterpriseQualifyVO eqvo = new EnterpriseQualifyVO();
			//默认值设置
			eqvo.setType(FHConstant.COMP_TYPE_ENTERPRISE);
			eqvo.setRegtype_Num(FHConstant.REGISTER_TYPE_GY);
			eqvo.setStatus(FHConstant.COMP_STATUS_EDIT);
			eqvo.setAptitudeLevel(FHConstant.QUALIFIED_GRADE_FIRST);
			eqvo.setBizdistrict(vo.getRegionId());

			if("edit".equals(cmd)){
				eqvo.setComp_ID(Long.parseLong(compId));
				eqvo = (EnterpriseQualifyVO) cmBO.find(eqvo);
			}
			
			request.setAttribute("logo", logo);
			request.setAttribute("cmd", cmd);
			request.setAttribute("eqvo", eqvo);
			return "/fhhouse/company/CompanyEdit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：开发企业编辑页面返回
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doCompanyEditBack")
	public String doCompanyEditBack(HttpServletRequest request,HttpSession session) {
		try{
			String url = "";
			//返回标志  add:资质录入
			String backFlag = (String) session.getAttribute("backFlag");
			if("add".equals(backFlag)){
				url = "/fhhouse/company/CompanyAddQueryFrame";
			}
			if("edit".equals(backFlag)){
				url = "/fhhouse/company/CompanyUpdateQuery";
			}
			return url;
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 开发企业保存检查
	 * @return
	 */
	@RequestMapping(value = "doCompanySaveCheck")
	@ResponseBody
	public JSONArray doCompanySaveCheck(HttpServletRequest request,HttpSession session,EnterpriseQualifyVO eqvo) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String cmd = request.getParameter("cmd"); //add:企业新增   edit：企业修改
			String compId = request.getParameter("comp_ID");
			List<Object> userDistrictList = this.getUserDistricts(uservo.getRegionId());//获取用户操作区县列表

			CompanyBO cmBO = new CompanyBO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("bizdistrict", "in", userDistrictList));
			params.add(new MetaFilter("comp_Code", "=", eqvo.getComp_Code()));


			List<EnterpriseQualifyVO> list = cmBO.search(EnterpriseQualifyVO.class, params);

			map.put("result", "success");
			map.put("message", "");
			if(list == null || list.size() <= 0){
				map.put("result", "success");
				map.put("message", "");
			}else if(list.size()==1){

				if("add".equals(cmd)){
					map.put("result", "fail");
					map.put("message", "企业全国唯一编码已经存在！");
				}else{
					EnterpriseQualifyVO vo = list.get(0);
					if(Long.parseLong(compId) != vo.getComp_ID()){
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
			map.put("message", "开发企业保存检查失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：开发企业资质保存
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doCompanySave")
	public String doCompanySave(HttpServletRequest request,HttpSession session,EnterpriseQualifyVO eqvo) {
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			CompanyBO cmBO = new CompanyBO();
			String cmd = request.getParameter("cmd"); //add:企业新增   edit：企业修改
			//日期字段处理
			eqvo.setPassDate(eqvo.getPassDate().replaceAll("-", ""));
			eqvo.setBizend_Date(eqvo.getBizend_Date().replaceAll("-", ""));
			eqvo.setBizreg_Date(eqvo.getBizreg_Date().replaceAll("-", ""));
			eqvo.setEffectstartDate(eqvo.getEffectstartDate().replaceAll("-", ""));
			eqvo.setEffectendDate(eqvo.getEffectendDate().replaceAll("-", ""));
			eqvo.setPassbizDate(eqvo.getPassbizDate().replaceAll("-", ""));

			eqvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			eqvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			eqvo.setUpdPerson(String.valueOf(uservo.getUserId()));
			if("add".equals(cmd)){
				eqvo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				eqvo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				eqvo.setCrePerson(String.valueOf(uservo.getUserId()));
				long compId = cmBO.add(eqvo);
				if(compId <= 0){
					throw new Exception("新增开发企业失败！");
				}
				eqvo.setComp_ID(compId);
			}else{
				long count = cmBO.update(eqvo);
				if(count <= 0){
					throw new Exception("更新开发企业失败！");
				}
			}

			request.setAttribute("cmd", "edit");
			request.setAttribute("eqvo", eqvo);
			return "/fhhouse/company/CompanyEdit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 开发企业提交审核
	 * @return
	 */
	@RequestMapping(value = "doCompanyFirstSubmit")
	@ResponseBody
	public JSONArray doCompanyFirstSubmit(HttpServletRequest request,HttpSession session,EnterpriseQualifyVO tempeqvo,Page page) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			CompanyBO cmBO = new CompanyBO();
			ProjectBO pBo = new ProjectBO();
			EnterpriseQualifyVO neweqvo = new EnterpriseQualifyVO();
			neweqvo.setComp_ID(tempeqvo.getComp_ID());

			long compId = tempeqvo.getComp_ID(); //企业id
			List<ProjectVO> plist = pBo.findCompanyProjectList(compId, null, page); //项目列表
			List<StartUnitVO> sulist = cmBO.findStartUnit(compId); //开盘单元列表

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("status", "<>", FHConstant.SIGNER_STATUS_SUBMIT));
			params.add(new MetaFilter("comp_ID", "=", compId));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
			orders.add(new MetaOrder("updTime", MetaOrder.DESC));
			List<SignerVO> slist = cmBO.search(SignerVO.class, params, orders, page);//签约人列表
			boolean a = cmBO.firstSubmitCompany(uservo, neweqvo, slist, plist, sulist);

			//neweqvo.setStatus(FHConstant.COMP_STATUS_SUBMIT);
			//long count = cmBO.update(neweqvo);
			if(a == true){
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
			map.put("message", "开发企业提交出错");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：开发企业签约人录入列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoSignerEditList")
	public String gotoSignerEditList(HttpServletRequest request,HttpSession session,Page page,long compId) {
		try{
			String backFlag = session.getAttribute("backFlag").toString();//返回标志  add:资质录入 edit:资质修改
			//SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			//List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表

			CompanyBO cmBO = new CompanyBO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("status", "<>", FHConstant.SIGNER_STATUS_SUBMIT));
			params.add(new MetaFilter("comp_ID", "=", compId));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
			orders.add(new MetaOrder("updTime", MetaOrder.DESC));


			List<SignerVO> list = cmBO.search(SignerVO.class, params, orders, page);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("signer_ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("个人帐户", "loginName");
			tableProperty.addColumn("姓名", "name", "doSignerEdit", linkparam);
			tableProperty.addColumn("证件名称", "cardName");
			tableProperty.addColumn("证件号码", "cardCode");
			tableProperty.addColumn("审核状态", "status_dict_name");
			tableProperty.addColumn("使用状态", "isEnable_dict_name");
			tableProperty.addColumn("签约人ID", "signer_ID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			request.setAttribute("compId", compId);
			request.setAttribute("backFlag", backFlag);
			return "/fhhouse/company/SignerEditList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：开发企业签约人录入列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoSignerEdit")
	public String gotoSignerEdit(HttpServletRequest request,HttpSession session) {
		try{
			String cmd = request.getParameter("cmd");//add:新增   edit：修改
			String compId = request.getParameter("compId");
			String signerId = request.getParameter("signerId");
			String backFlag = session.getAttribute("backFlag").toString();//返回标志  add:资质录入 edit:资质修改
			String agentName = "";
			CompanyBO cmBO = new CompanyBO();
			SignerVO signerVO = new SignerVO();
			if("edit".equals(cmd)){
				signerVO.setSigner_ID(Long.parseLong(signerId));
				signerVO = (SignerVO) cmBO.find(signerVO);
				//代理商查询
				if(signerVO.getAgent_ID() != null && !"".equals(signerVO.getAgent_ID())){
					AgentVO agentVO = new AgentVO();
					agentVO.setAgentID(Long.parseLong(signerVO.getAgent_ID()));
					agentVO = (AgentVO) cmBO.find(agentVO);
					agentName = agentVO.getName();
				}
			}else{
				signerVO.setComp_ID(compId);
				//初始化数据
				signerVO.setStatus(FHConstant.SIGNER_STATUS_EDIT);
				//signerVO.setIsEnable(FHConstant.SIGNER_VALID_ON);
				//signerVO.setChanged(FHConstant.OUTWEB_PWD_NO_CHANGE);
			}
			//菜单列表
			List<SmObjectVO> objectList = cmBO.findSignerObjectList(signerVO.getSigner_ID());
			//签约人楼栋列表
			List<ProPreBldSignerVO> signerBuildingList = cmBO.findSignerRelationBuildingList(signerVO.getSigner_ID());

			request.setAttribute("objectList", objectList);
			request.setAttribute("signerBuildingList", signerBuildingList);
			request.setAttribute("signerVO", signerVO);
			request.setAttribute("agentName", agentName);
			request.setAttribute("cmd", cmd);
			request.setAttribute("backFlag", backFlag);
			return "/fhhouse/company/SignerEdit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/**
	 * 功能描述 ：签约人项目选择列表
	 * @return
	 */
	@RequestMapping(value = "/getSignerProjectListJson")
	@ResponseBody
	public JSONArray getSignerProjectListJson(HttpSession httpSession,HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			String compId = request.getParameter("compId");
			//项目列表
			CompanyBO cmBO = new CompanyBO();
			List<ProjectVO> projectList = cmBO.findSignerProjectList(Long.parseLong(compId));
			StringBuffer sb = new StringBuffer();
			sb.append("<option value=''>----所有项目----</option>");

			if(projectList != null && projectList.size() > 0){
				for(ProjectVO pvo : projectList){
					sb.append("<option value='" + pvo.getProject_ID() + "'>" + pvo.getProjectName() + "</option>");
				}
			}
			map.put("result", "success");
			map.put("message", "获取成功！");
			map.put("projectList", sb.toString());
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "获取失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述 ：签约人开盘单元选择列表
	 * @return
	 */
	@RequestMapping(value = "/getStartUnitListJson")
	@ResponseBody
	public JSONArray getStartUnitListJson(HttpSession httpSession,HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			String compId = request.getParameter("compId");
			String projectId = request.getParameter("projectId");
			CompanyBO cmBO = new CompanyBO();
			//开盘单元列表
			List<StartUnitVO> startUnitList = cmBO.findSignerStartUnitList(Long.parseLong(compId), projectId);
			StringBuffer sb = new StringBuffer();
			sb.append("<option value=''>----所有许可证----</option>");

			if(startUnitList != null && startUnitList.size() > 0){
				for(StartUnitVO unitvo : startUnitList){
					sb.append("<option value='" + unitvo.getStart_ID() + "'>");
					sb.append(unitvo.getAttribute("presell_desc") + "(" + unitvo.getStart_Code() + ")");
					sb.append("</option>");
				}
			}
			map.put("result", "success");
			map.put("message", "获取成功！");
			map.put("startunitList", sb.toString());
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "获取失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述 ：签约人楼栋选择列表
	 * @return
	 */
	@RequestMapping(value = "/getStartBuidlingListJson")
	@ResponseBody
	public JSONArray getStartBuidlingListJson(HttpSession httpSession,HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			String compId = request.getParameter("compId");
			String projectId = request.getParameter("projectId");
			String startId = request.getParameter("startId");
			CompanyBO cmBO = new CompanyBO();
			//开盘单元楼栋列表
			List<BuildingHouseVO> startBuidlingList = cmBO.findSignerStartBuildingList(Long.parseLong(compId), projectId, startId);
			StringBuffer sb = new StringBuffer();

			if(startBuidlingList != null && startBuidlingList.size() > 0){
				for(BuildingHouseVO buildingHouseVO : startBuidlingList){
					sb.append("<option value='" + buildingHouseVO.getStart_ID() + "_" + buildingHouseVO.getBuilding_ID() + "'>");
					sb.append(buildingHouseVO.getAttribute("building_name"));
					sb.append("</option>");
				}
			}
			map.put("result", "success");
			map.put("message", "获取成功！");
			map.put("buidlingList", sb.toString());
			json = JSONArray.fromObject(map);

			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "获取失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述 ：签约人保存操作
	 * @return
	 */
	@RequestMapping(value = "/doSignerSave")
	@ResponseBody
	public JSONArray doSignerSave(HttpSession session,HttpServletRequest request,SignerVO signerVO) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
		try{
			long singerId = -1;
			String errormsg = "";
			if(signerVO.getStatus() == FHConstant.SIGNER_STATUS_SUBMIT){
				errormsg = "签约人已经提交审核，无法进行保存操作 ";
			}else if(signerVO.getStatus() == FHConstant.SIGNER_STATUS_CANCEL){
				errormsg = "签约人已经撤销，无法进行保存操作 ";
			}
			String cmd = request.getParameter("cmd");//add:新增   edit：修改
			CompanyBO cmBO = new CompanyBO();
			signerVO.setStatus(FHConstant.SIGNER_STATUS_EDIT);
			signerVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			signerVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			signerVO.setUpdPerson(String.valueOf(uservo.getUserId()));

			//菜单选择处理
			String[] actions = request.getParameterValues("actionID");
			List<SignerActionsVO> actionList = new ArrayList<SignerActionsVO>();
			if(actions != null){
				for(int i = 0; i < actions.length; i++){
					SignerActionsVO actionvo = new SignerActionsVO();
					actionvo.setActionID(Long.parseLong(actions[i]));
					actionvo.setSignerID(signerVO.getSigner_ID());
					actionList.add(actionvo);
				}
			}

			//楼栋选择处理
			String[] bldStr = request.getParameterValues("SELECTEDBUILDING");

			List<ProPreBldSignerVO> buildingList = new ArrayList<ProPreBldSignerVO>();
			if(bldStr != null){
				List<BuildingHouseVO> allBuidlingList = cmBO.findSignerStartBuildingList(Long.parseLong(signerVO.getComp_ID()), null, null);
				for(int i = 0; i < bldStr.length; i++){
					String bldID = bldStr[i];
					for(BuildingHouseVO bhvo : allBuidlingList){
						if(bldID.equals(bhvo.getStart_ID() + "_" + bhvo.getBuilding_ID())){
							ProPreBldSignerVO ppbvo = new ProPreBldSignerVO();
							ppbvo.setSigner_ID(signerVO.getSigner_ID());
							ppbvo.setStart_ID(bhvo.getStart_ID());
							ppbvo.setBuilding_ID(bhvo.getBuilding_ID());
							ppbvo.setProject_ID(Long.parseLong(bhvo.getAttribute("project_id").toString()));
							ppbvo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
							ppbvo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
							ppbvo.setCrePerson(String.valueOf(uservo.getUserId()));
							ppbvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
							ppbvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
							ppbvo.setUpdPerson(String.valueOf(uservo.getUserId()));

							buildingList.add(ppbvo);
						}
					}

				}
			}

			if("add".equals(cmd)){
				signerVO.setIsEnable(FHConstant.SIGNER_VALID_ON);
				signerVO.setChanged(FHConstant.OUTWEB_PWD_NO_CHANGE);
				signerVO.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				signerVO.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				signerVO.setCrePerson(String.valueOf(uservo.getUserId()));
				singerId = cmBO.registSigner(signerVO, actionList, buildingList);
			}else{
				//关联代理商状态判断
				if(signerVO.getAgent_ID() != null && !"".equals(signerVO.getAgent_ID())){
					AgentVO agentVO = new AgentVO();
					agentVO.setAgentID(Long.parseLong(signerVO.getAgent_ID()));
					agentVO = (AgentVO) cmBO.find(agentVO);
					if(agentVO == null){
						errormsg = "关联代理商记录不存在！";
					}else{
						if(agentVO.getStatus() != FHConstant.AGENT_STATUS_PERMITTED && agentVO.getStatus() != FHConstant.AGENT_STATUS_NOPERMITTED
								&& agentVO.getStatus() != FHConstant.AGENT_STATUS_PASSED)
							errormsg = "代理商id=" + signerVO.getAgent_ID() + "状态已非审核通过状态";
					}
				}

				if("".equals(errormsg)){
					singerId = cmBO.updateSigner(signerVO, actionList, buildingList);
				}
			}

			if("".equals(errormsg)){
				map.put("result", "success");
				map.put("message", singerId);
			}else{
				map.put("result", "fail");
				map.put("message", errormsg);
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "保存失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：签约人修改选择代理机构
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoAgentSelectQuery")
	public String gotoAgentSelectQuery(HttpServletRequest request,HttpSession session) {
		try{
			return "/fhhouse/company/SignerAgentSelectFrame";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：签约人修改选择代理机构列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoAgentSelectList")
	public String gotoAgentSelectList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String agentCode = request.getParameter("agentCode");
			String agentName = request.getParameter("agentName");
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表

			//EnterpriseQualifyVO eqvo = new EnterpriseQualifyVO();
			CompanyBO cmBO = new CompanyBO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("district", "in", userDistrictList));
			params.add(new MetaFilter("status", "=", FHConstant.AGENT_STATUS_PERMITTED));
			if(agentCode != null && !"".equals(agentCode)){
				params.add(new MetaFilter("agentCode", "=", agentCode));
			}
			if(agentName != null && !"".equals(agentName)){
				params.add(new MetaFilter("name", "like", "%" + agentName + "%"));
			}
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
			orders.add(new MetaOrder("updTime", MetaOrder.DESC));


			List<AgentVO> list = cmBO.search(AgentVO.class, params, orders, page);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("agentID");
			linkparam.add("name");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("企业名称", "name");
			tableProperty.addColumn("企业编码", "agentCode");
			tableProperty.addColumn("地址", "regadr");
			tableProperty.addColumn("选择", "选择", "doSelAgent", linkparam, "选择", null);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("agentCode", agentCode == null ? "" : agentCode);
			request.setAttribute("agentName", agentName == null ? "" : agentName);
			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/company/SignerAgentSelectList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述 ：签约人撤消操作
	 * @return
	 */
	@RequestMapping(value = "/doSignerCancel")
	@ResponseBody
	public JSONArray doSignerCancel(HttpSession session,HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		String msg = "";
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String signerId = request.getParameter("signer_ID");
			String repeal_Comment = request.getParameter("repeal_Comment");
			CompanyBO cmBO = new CompanyBO();
			SignerVO signervo = new SignerVO();
			signervo.setSigner_ID(Long.parseLong(signerId));
			signervo = (SignerVO) cmBO.find(signervo);
			if(signervo == null){
				msg = "无效签约人记录！";
			}
			//判断签约人状态是否有发布
			if(signervo.getStatus() != FHConstant.SIGNER_STATUS_PERMITTED){
				msg = "签约人还未完全审核通过，不能撤销！";
			}
			if(signervo.getStatus() == FHConstant.SIGNER_STATUS_CANCEL){
				msg = "签约人已经是撤销状态！";
			}
			if("".equals(msg)){

				signervo = new SignerVO();
				signervo.setSigner_ID(Long.parseLong(signerId));
				signervo.setRepeal_Comment(repeal_Comment);
				signervo.setStatus(FHConstant.SIGNER_STATUS_CANCEL);
				signervo.setFirstAuditDate(DateUtil.getSysDateYYYYMMDD());
//				signervo.setFirstCensor(uservo.getLoginName());
				signervo.setFirstCensor(String.valueOf(uservo.getUserId()));
				signervo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				signervo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				signervo.setUpdPerson(String.valueOf(uservo.getUserId()));
				long count = cmBO.update(signervo);
				if(count == 0){
					msg = "撤销失败，未更新到记录！";
				}
			}

			if("".equals(msg)){
				map.put("result", "success");
				map.put("message", "撤销成功！");
			}else{
				map.put("result", "fail");
				map.put("message", msg);
			}
			json = JSONArray.fromObject(map);

			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "签约人撤消失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述 ：签约人撤销恢复操作
	 * @return
	 */
	@RequestMapping(value = "/doSignerRepeal")
	@ResponseBody
	public JSONArray doSignerRepeal(HttpSession session,HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		String msg = "";
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String signerId = request.getParameter("signer_ID");
			CompanyBO cmBO = new CompanyBO();
			SignerVO signervo = new SignerVO();
			signervo.setSigner_ID(Long.parseLong(signerId));
			signervo = (SignerVO) cmBO.find(signervo);
			if(signervo == null){
				msg = "无效签约人记录！";
			}
			if(signervo.getStatus() != FHConstant.SIGNER_STATUS_CANCEL){
				msg = "签约人不是撤销状态，无法进行恢复操作！";
			}
			if("".equals(msg)){

				signervo = new SignerVO();
				signervo.setSigner_ID(Long.parseLong(signerId));
				signervo.setStatus(FHConstant.SIGNER_STATUS_RECOVER);
				signervo.setFirstAuditDate(DateUtil.getSysDateYYYYMMDD());
//				signervo.setFirstCensor(uservo.getLoginName());
				signervo.setFirstCensor(String.valueOf(uservo.getUserId()));
				signervo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				signervo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				signervo.setUpdPerson(String.valueOf(uservo.getUserId()));
				long count = cmBO.update(signervo);
				if(count == 0){
					msg = "撤销恢复失败，未更新到记录！";
				}
			}

			if("".equals(msg)){
				map.put("result", "success");
				map.put("message", "撤销恢复成功！");
			}else{
				map.put("result", "fail");
				map.put("message", msg);
			}
			json = JSONArray.fromObject(map);

			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "签约人撤消恢复失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述 ：签约人提交审核操作
	 * @return
	 */
	@RequestMapping(value = "/doSignerFirstSubmit")
	@ResponseBody
	public JSONArray doSignerFirstSubmit(HttpSession session,HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		String msg = "";
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String signerId = request.getParameter("signer_ID");
			CompanyBO cmBO = new CompanyBO();
			SignerVO signervo = new SignerVO();
			signervo.setSigner_ID(Long.parseLong(signerId));
			signervo = (SignerVO) cmBO.find(signervo);
			if(signervo == null){
				msg = "无效签约人记录！";
			}
			if(signervo.getStatus() == FHConstant.SIGNER_STATUS_CANCEL){
				msg = "签约人已经撤销，无法进行提交审核操作！";
			}
			if(signervo.getStatus() == FHConstant.SIGNER_STATUS_SUBMIT){
				msg = "签约人已经提交审核，无法再次进行提交审核操作！";
			}
			if(signervo.getStatus() != FHConstant.SIGNER_STATUS_EDIT){
				msg = "签约人不是编辑状态无法进行提交审核，请先进行保存操作！";
			}
			if("".equals(msg)){

				signervo = new SignerVO();
				signervo.setSigner_ID(Long.parseLong(signerId));
				signervo.setStatus(FHConstant.SIGNER_STATUS_SUBMIT);
				signervo.setFirstAuditDate(DateUtil.getSysDateYYYYMMDD());
//				signervo.setFirstCensor(uservo.getLoginName());
				signervo.setFirstCensor(String.valueOf(uservo.getUserId()));
				signervo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				signervo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				signervo.setUpdPerson(String.valueOf(uservo.getUserId()));
				long count = cmBO.update(signervo);
				if(count == 0){
					msg = "提交审核失败，未更新到记录！";
				}
			}

			if("".equals(msg)){
				map.put("result", "success");
				map.put("message", "提交审核成功！");
			}else{
				map.put("result", "fail");
				map.put("message", msg);
			}
			json = JSONArray.fromObject(map);

			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "签约人提交审核失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}


	/**
	 * 功能描述：资质撤销
	 * @return
	 */
	@RequestMapping("/gotoCompanyRevokedQuery")
	public String gotoSearchRevokedCompany() {
		try{
			return "fhhouse/company/CompanyRevokedQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：资质撤销列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/gotoCompanyRevokedQueryList")
	public String gotoRevokedAgentList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String compCode = request.getParameter("compCode");
			String compName = request.getParameter("compName");
			String status = request.getParameter("status");
			request.setAttribute("compCode", compCode);
			request.setAttribute("compName", compName);
			request.setAttribute("status", status);

			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("bizdistrict", "in", userDistrictList));
			if(compCode != null && !"".equals(compCode)){
				params.add(new MetaFilter("comp_Code", "=", compCode));
			}
			if(compName != null && !"".equals(compName)){
				params.add(new MetaFilter("name", "like", "%" + compName + "%"));
			}
			if(status != null && !"".equals(status)){
				params.add(new MetaFilter("status", "=", Integer.parseInt(status)));
			}else{
				params.add(new MetaFilter("status", "=", FHConstant.COMP_STATUS_CANCEL));
			}

			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
			orders.add(new MetaOrder("updTime", MetaOrder.DESC));
			CompanyBO cmBO = new CompanyBO();

			List<EnterpriseQualifyVO> list = cmBO.search(EnterpriseQualifyVO.class, params, orders, page);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("comp_ID");
			linkparam.add("status");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("企业代码", "comp_Code");
			tableProperty.addColumn("企业名称", "name");
			tableProperty.addColumn("类型", "type_dict_name");
			tableProperty.addColumn("通过时间", "finalDate");
			tableProperty.addColumn("查看详细", "查看", "showCompany", linkparam, "查看", null);
			tableProperty.addColumn("企业ID", "comp_ID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "fhhouse/company/CompanyRevokedQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：资质撤销查看
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoCompanyRevokedEdit")
	public String gotoCompanyRevokedEdit(HttpServletRequest request,HttpSession session,Page page) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			CompanyBO cmBO = new CompanyBO();
			String compId = request.getParameter("compID");
			String status = request.getParameter("status");
			EnterpriseQualifyVO eqvo = new EnterpriseQualifyVO();
			eqvo.setComp_ID(Long.parseLong(compId));
			eqvo = (EnterpriseQualifyVO) cmBO.find(eqvo);
			List<EnterpriseQualifyVO> complist = cmBO.findCompanyInfo(compId);
			request.setAttribute("complist", complist);

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			//params.add(new MetaFilter("status", "=", status));
			//params.add(new MetaFilter("status", "<>", FHConstant.SIGNER_STATUS_SUBMIT));
			params.add(new MetaFilter("comp_ID", "=", compId));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
			orders.add(new MetaOrder("updTime", MetaOrder.DESC));

			List<SignerVO> list = cmBO.search(SignerVO.class, params, orders, page);
			session.setAttribute("list", list);
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(false);
			tableProperty.addColumn("个人帐号", "loginName");
			tableProperty.addColumn("姓名", "name");
			tableProperty.addColumn("证件名称", "cardName");
			tableProperty.addColumn("证件号码", "cardCode");
			tableProperty.addColumn("身份证号码", "brokercert");
			String htmlView1 = HtmlTableUtil.createHtmlTable(tableProperty, list);

			List<ProjectVO> list2 = cmBO.findProjectPresellList(Long.parseLong(compId));
			session.setAttribute("list2", list2);
			/** 设置table列名 */
			TableProperty tableProperty2 = new TableProperty();
			tableProperty2.setEnableSort(false);
			tableProperty2.setRowIndexStauts(false);
			tableProperty2.addColumn("项目名称", "projectname");
			tableProperty2.addColumn("预售许可证（开盘单元）", "presell_desc");
			String htmlView2 = HtmlTableUtil.createHtmlTable(tableProperty2, list2);

			List<StartUnitVO> list3 = cmBO.findStartUnitHouseList(Long.parseLong(compId));
			session.setAttribute("list3", list3);
			/** 设置table列名 */
			TableProperty tableProperty3 = new TableProperty();
			tableProperty3.setEnableSort(false);
			tableProperty3.setRowIndexStauts(false);
			tableProperty3.addColumn("预售许可证（开盘单元）", "presell_desc");
			tableProperty3.addColumn("相关楼幢", "building_name");
			String htmlView3 = HtmlTableUtil.createHtmlTable(tableProperty3, list3);

			request.setAttribute("htmlView1", htmlView1);
			request.setAttribute("htmlView2", htmlView2);
			request.setAttribute("htmlView3", htmlView3);
			request.setAttribute("eqvo", eqvo);
			return "/fhhouse/company/CompanyRevokedEdit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：资质撤销或恢复
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/doCompanyCancel")
	public String doCompanyCancel(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String compID = request.getParameter("compID");
			String status = request.getParameter("status");
			CompanyBO cmBo = new CompanyBO();
			EnterpriseQualifyVO eqvo = (EnterpriseQualifyVO) cmBo.find(EnterpriseQualifyVO.class, Long.parseLong(compID));
			List<SignerVO> list = (List) session.getAttribute("list");
			List<ProjectVO> list2 = (List) session.getAttribute("list2");
			List<StartUnitVO> list3 = (List) session.getAttribute("list3");

			eqvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			eqvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			eqvo.setUpdPerson(String.valueOf(uservo.getUserId()));
			boolean a = true;
			a = cmBo.cancelCompany(status, eqvo, list, list2, list3);
			return "redirect:/inner/companymanage/gotoCompanyRevokedQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：跳转到资质修改页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/gotoCompanyUpdateQuery")
	public String gotoCompanyUpdate(HttpServletRequest request,HttpSession session) {
		try{
			session.setAttribute("backFlag", "edit");
			return "/fhhouse/company/CompanyUpdateQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：资质修改列表
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/gotoCompanyUpdateQueryList")
	public String gotoCompanyUpdateList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String compCode = request.getParameter("compCode");
			String name = request.getParameter("compName");
			String documentId = request.getParameter("documentId");
			request.setAttribute("compCode", compCode);
			request.setAttribute("compName", name);
			request.setAttribute("documentId", documentId);
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String districtList = this.getUserSqlDistricts(vo.getRegionId());//获取用户操作区县列表
			CompanyBO cmBo = new CompanyBO();
			List<EnterpriseQualifyVO> list = cmBo.findCompanyList(page, compCode, name, documentId, districtList);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("comp_ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addSelectControl("radio", "selCompID", linkparam, "");
			tableProperty.addColumn("企业名称", "name", "doCompanyEdit2", linkparam);
			tableProperty.addColumn("地址", "regadr");
			tableProperty.addColumn("法人代表", "delegate");
			tableProperty.addColumn("资质状态", "status_dict_name");
			tableProperty.addColumn("企业ID", "comp_ID", true);
			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("logo", "edit");
			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/company/CompanyAddQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：资质审核
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoCompanyAuditQuery")
	public String gotoCompanyAudit(HttpServletRequest request,HttpSession session,Page page) {
		try{
			request.setAttribute("projectName", "");
			request.setAttribute("companyName", "");
			request.setAttribute("status", "audit");
			return "/fhhouse/company/QualificationAudit";

		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：资质审核-开盘单元审核
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoStartUnitAudit")
	public String gotoStartUnitAudit(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String startId = request.getParameter("id");
			String status = request.getParameter("status");
			CompanyBO cmBo = new CompanyBO();
			List<StartUnitVO> list1 = cmBo.findStartUnit1(startId);
			request.setAttribute("list1", list1);
			for(StartUnitVO svo : list1){
				request.setAttribute("svo", svo);
				String firstDate = DateUtil.parseDateTime3(String.valueOf(svo.getFirstAuditDate()));
				request.setAttribute("firstDate", firstDate);
				String finalDate = DateUtil.parseDateTime3(String.valueOf(svo.getFinalAuditDate()));
				request.setAttribute("finalDate", finalDate);
				session.setAttribute("svo", svo);
				session.setAttribute("firstDate", firstDate);
			}
			List<BuildingHouseVO> list2 = cmBo.findStartUnit2(startId);
			session.setAttribute("list2", list2);
			List<SignerVO> list3 = cmBo.findStartUnit3(startId);
			session.setAttribute("list3", list3);
			List<EnterpriseQualifyVO> list4 = cmBo.findStartUnit4(startId);
			session.setAttribute("list4", list4);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("building_id");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(false);
			//tableProperty.addSelectControl("radio", "selCompID", linkparam, "");
			tableProperty.addColumn("楼幢编号", "building_id");
			tableProperty.addColumn("楼幢名称", "building_name");
			tableProperty.addColumn("楼盘结构图", "点击查看", "showBuilding", linkparam, "点击查看", null);
			tableProperty.addColumn("参考单价（元）", "reference_price");
			tableProperty.addColumn("房屋ID", "house_ID", true);
			String htmlView = HtmlTableUtil.createHtmlTable(tableProperty, list2);

			/** 设置table列名 */
			TableProperty tableProperty1 = new TableProperty();
			tableProperty1.setEnableSort(false);
			tableProperty1.setRowIndexStauts(false);
			tableProperty1.addColumn("个人帐号", "loginname");
			tableProperty1.addColumn("姓名", "name");
			tableProperty1.addColumn("证件名称", "cardname");
			tableProperty1.addColumn("证件号码", "cardcode");
			tableProperty1.addColumn("身份证号码", "brokercert");
			String htmlView1 = HtmlTableUtil.createHtmlTable(tableProperty1, list3);

			request.setAttribute("status", status);
			request.setAttribute("list4", list4);
			request.setAttribute("htmlView", htmlView);
			request.setAttribute("htmlView1", htmlView1);
			return "/fhhouse/company/StartUnitAudit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：开盘单元提交审核
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/startUnitAudit")
	public String startUnitAudit(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO smUserVO = (SmUserVO) session.getAttribute("LgUser");
			String status = request.getParameter("status");
			String startId = request.getParameter("startId");
			String finalMark = URLDecoder.decode(request.getParameter("finalMark"), "utf-8");
			//System.out.println(finalMark);
			CompanyBO cmBo = new CompanyBO();
			StartUnitVO svo = (StartUnitVO) cmBo.find(StartUnitVO.class, Long.parseLong(startId));
			if("1".equals(status)){
				svo.setFinalMark(finalMark);
//				svo.setStatus(FHConstant.COMP_ISAGREE_TRUE);
				//20190829审核同意后自动发布
				svo.setStatus(FHConstant.START_UNIT_STATUS_PERMITTED);
			}
			if("2".equals(status)){
				svo.setFinalMark(finalMark);
				svo.setStatus(FHConstant.COMP_ISAGREE_FALSE);
			}
			svo.setFinalAuditDate(DateUtil.getSysDateYYYYMMDD());
			//svo.setFinalCensor(smUserVO.getDisplayName());
			svo.setFinalCensor(String.valueOf(smUserVO.getUserId()));
			svo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			svo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			svo.setUpdPerson(String.valueOf(smUserVO.getUserId()));
			cmBo.update(svo);
			return "redirect:/inner/companymanage/gotoCompanyAuditQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：查看楼盘结构图
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/houseViewList")
	public String houseViewList(HttpServletRequest request,HttpSession session) {
		try{
			String buildingId = request.getParameter("buildingId");
			CompanyBO cmBo = new CompanyBO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("building_ID", "=", buildingId));
			List<Object> statusList = new ArrayList<Object>();
			statusList.add(String.valueOf(FHConstant.HOUSE_STATUS_CANSELL));
			statusList.add(String.valueOf(FHConstant.HOUSE_STATUS_UNACTIVE));
			params.add(new MetaFilter("status", "in", statusList));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			List<HouseVO> list = cmBo.search(HouseVO.class, params);

			request.setAttribute("list", list);
			return "/fhhouse/company/HouseViewList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：开盘单元审核打印
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/startUnitAuditPrint")
	public String startUnitAuditPrint(HttpServletRequest request,HttpSession session) {
		try{
			String finalMark = request.getParameter("status");
			finalMark = URLDecoder.decode(finalMark, "utf-8");
			/*
			 * String finalMark = ""; if("1".equals(status)){ finalMark = "同意"; } if("2".equals(status)){ finalMark = "不同意"; }
			 */
			request.setAttribute("finalMark", finalMark);
			return "/fhhouse/company/StartUnitAuditPrint";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：资质审核-企业审核
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoCompanyAudit")
	public String gotoCompanysAudit(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String id = request.getParameter("id");
			String print = request.getParameter("print");
			String finalMark = request.getParameter("finalMark");
			//finalMark = URLDecoder.decode(finalMark, "utf-8");
			String status = request.getParameter("status");
			request.setAttribute("status", status);

			CompanyBO cmBo = new CompanyBO();
			EnterpriseQualifyVO eqVo = (EnterpriseQualifyVO) cmBo.find(EnterpriseQualifyVO.class, Long.parseLong(id));
			List<EnterpriseQualifyVO> complist = cmBo.findCompanyInfo(id);
			request.setAttribute("complist", complist);

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			if("audit".equals(status)){
				params.add(new MetaFilter("status", "=", FHConstant.SIGNER_STATUS_SUBMIT));
			}
			if("publish".equals(status)){
				params.add(new MetaFilter("status", "=", FHConstant.SIGNER_STATUS_PASSED));
			}
			params.add(new MetaFilter("comp_ID", "=", id));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
			orders.add(new MetaOrder("updTime", MetaOrder.DESC));
			List<SignerVO> list = cmBo.search(SignerVO.class, params, orders, null);
			request.setAttribute("signerList", list);
			List<ProjectVO> list2 = cmBo.findProjectInfo(status, id);
			request.setAttribute("projectList", list2);
			List<StartUnitVO> list3 = cmBo.findStartUnitInfo(id);
			request.setAttribute("startunitList", list3);

			if("print".equals(print)){
				SmUserVO sVO = (SmUserVO) session.getAttribute("LgUser");
				eqVo.setFinalMark(finalMark);
//				eqVo.setFinalCensor(sVO.getDisplayName());
				eqVo.setFinalCensor(String.valueOf(sVO.getUserId()));
				eqVo.setFinalAuditDate(DateUtil.getSysDateYYYYMMDD());
				request.setAttribute("eqvo", eqVo);
				return "/fhhouse/company/CompanyAuditPrint";
			}else{
				request.setAttribute("eqvo", eqVo);
				if("audit".equals(status)){
					return "/fhhouse/company/CompanyAudit";
				}else{
					return "/fhhouse/company/CompanyPublish";
				}
			}

		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：资质审核-企业审核是否通过
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoCompanyAuditPass")
	public String gotoCompanyAuditPass(HttpServletRequest request,HttpSession session) {
		try{
			String id = request.getParameter("id");
			String type = request.getParameter("type1");
			String finalMark = request.getParameter("finalMark");

			CompanyBO cmBo = new CompanyBO();
			EnterpriseQualifyVO eqVo = (EnterpriseQualifyVO) cmBo.find(EnterpriseQualifyVO.class, Long.parseLong(id));

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("status", "=", FHConstant.SIGNER_STATUS_SUBMIT));
			params.add(new MetaFilter("comp_ID", "=", id));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
			orders.add(new MetaOrder("updTime", MetaOrder.DESC));
			List<SignerVO> list = cmBo.search(SignerVO.class, params, orders, null);

			List<ProjectVO> list2 = cmBo.findProjectInfo("audit", id);

			List<StartUnitVO> list3 = new ArrayList<StartUnitVO>();
			if(list2 != null){
				for(ProjectVO projectVO : list2){
					if(projectVO.getAttribute("start_id") != null){
//						System.out.println("start_id:" + projectVO.getAttribute("start_id"));
						StartUnitVO suVo = new StartUnitVO();
						suVo.setStart_ID(Long.parseLong(projectVO.getAttribute("start_id").toString()));
						list3.add(suVo);
					}
				}
			}


			if(!"".equals(type)){
				EnterpriseQualifyVO eqvo = new EnterpriseQualifyVO();
				SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
				String userId = String.valueOf(vo.getUserId());

				eqvo.setComp_ID(Long.parseLong(id));
				cmBo.updateCompanyAUditStatus(type, userId, finalMark, eqvo, list, list2, list3);
				if("1".equals(type)){
					List<SignerVO> signerList = new ArrayList<SignerVO>();
					if(list != null && list.size() > 0){
						for(SignerVO sVo : list){
							if(sVo.getLoginName() == null){
								String pwd = PassGenerator.generateHalfInt();
								String loginname = new SystemBO().getSequence("LOGINNAME");
								sVo.setPwd(pwd);
								sVo.setLoginName(loginname);
								sVo.setChanged(1);
								cmBo.update(sVo);
								sVo.setAttribute("compName", eqVo.getName());
								sVo.setAttribute("legalManCode", eqVo.getLegalManCode());
								signerList.add(sVo);
							}
						}
						cmBo.updateCompanyAUditStatus(type, userId, null, null, signerList, null, null);
						request.setAttribute("signerList", signerList);

					}
					return "/fhhouse/company/UserView";
				}else{
					return this.gotoCompanyAudit(request, session, new Page());
				}

			}else{
				return this.gotoCompanyAudit(request, session, new Page());
			}

		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：资质审核-签约人审核
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoSignerAudit")
	public String gotoSignerAudit(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String signerId = request.getParameter("id");
			String status = request.getParameter("status");
			CompanyBO cmBo = new CompanyBO();
			List<SignerVO> list1 = cmBo.findSigner1(signerId);
			request.setAttribute("list1", list1);
			session.setAttribute("list1", list1);
			if(list1 != null && list1.size() > 0){
				SignerVO svo = list1.get(0);
				//System.out.println(svo.getFirstMark());
				request.setAttribute("svo", svo);
			}
			List<ProjectVO> list2 = cmBo.findSigner2(signerId);
			request.setAttribute("list2", list2);
			session.setAttribute("list2", list2);
			List<StartUnitVO> list3 = cmBo.findSigner3(signerId);
			request.setAttribute("list3", list3);
			session.setAttribute("list3", list3);
			List<SmObjectVO> objectList = cmBo.findSignerObjectList(Long.parseLong(signerId));
			request.setAttribute("objectList", objectList);
			session.setAttribute("objectList", objectList);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("building_id");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(false);
			tableProperty.addColumn("项目名称", "projectname");
			tableProperty.addColumn("预售许可证列表（开盘单元）", "presell_desc");
			String htmlView = HtmlTableUtil.createHtmlTable(tableProperty, list2);

			/** 设置table列名 */
			TableProperty tableProperty1 = new TableProperty();
			tableProperty1.setEnableSort(false);
			tableProperty1.setRowIndexStauts(false);
			tableProperty1.addColumn("预售许可证（开盘单元）", "presell_desc");
			tableProperty1.addColumn("相关楼幢", "building_name");
			String htmlView1 = HtmlTableUtil.createHtmlTable(tableProperty1, list3);

			request.setAttribute("status", status);
			request.setAttribute("htmlView", htmlView);
			request.setAttribute("htmlView1", htmlView1);
			return "/fhhouse/company/SignerAudit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：签约人提交审核
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/signerAudit")
	public String signerAudit(HttpServletRequest request,HttpSession session) {
		try{
			String url = "";
			SmUserVO smUserVO = (SmUserVO) session.getAttribute("LgUser");
			String option = request.getParameter("option");
			String signerId = request.getParameter("signerId");
			String status = request.getParameter("status");
			String finalMark = URLDecoder.decode(request.getParameter("finalMark"), "utf-8");
			CompanyBO cmBo = new CompanyBO();
			SignerVO svo = (SignerVO) cmBo.find(SignerVO.class, Long.parseLong(signerId));

			if(FHConstant.SIGNER_STATUS_SUBMIT == Integer.parseInt(status)){
				if(svo.getLoginName() == null || "".equals(svo.getLoginName())){
					SystemBO systemBO = new SystemBO();
					String loginName = systemBO.getSequence("LOGINNAME");
					String pwd = PassGenerator.generateHalfInt();
					svo.setLoginName(loginName);
					svo.setPwd(pwd);
					//svo.setIsEnable(1);
					cmBo.update(svo);
					url = "/fhhouse/company/SignerView";
				}else{
					url = "redirect:/inner/companymanage/gotoCompanyAuditQuery";
				}
			}
			if("1".equals(option)){
				svo.setFinalMark(finalMark);
//				svo.setStatus(FHConstant.SIGNER_STATUS_PASSED);
				//20190829 added by gcf 审核后直接发布。
				svo.setStatus(FHConstant.SIGNER_STATUS_PERMITTED);
			}
			if("2".equals(option)){
				svo.setFinalMark(finalMark);
				svo.setStatus(FHConstant.SIGNER_STATUS_NOPASSED);
			}
			svo.setFinalAuditDate(DateUtil.getSysDateYYYYMMDD());
			svo.setFinalCensor(String.valueOf(smUserVO.getUserId()));
			svo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			svo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			svo.setUpdPerson(String.valueOf(smUserVO.getUserId()));
			cmBo.update(svo);
			request.setAttribute("svo", svo);
			return url;
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/**
	 * 功能描述：签约人审核打印
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/signerAuditPrint")
	public String signerAuditPrint(HttpServletRequest request,HttpSession session) {
		try{
			String status = request.getParameter("status");
			String finalMark = URLDecoder.decode(request.getParameter("finalMark"), "utf-8");
			request.setAttribute("finalMark", finalMark);
			return "/fhhouse/company/SignerAuditPrint";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/**
	 * 功能描述：资质审核-项目审核
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotoProjectAudit")
	public String gotoProjectAudit(HttpServletRequest request,HttpSession session) {
		try{
			String id = request.getParameter("id");
			String print = request.getParameter("print");
			String status = request.getParameter("status");
			String finalMark = request.getParameter("finalMark");
			request.setAttribute("status", status);
			CompanyBO cmBo = new CompanyBO();
			ProjectVO pvo = new ProjectVO();
			SmUserVO sVO = (SmUserVO) session.getAttribute("LgUser");
			if(id != null && !"".equals(id)){
				List<ProjectVO> projectList = cmBo.findProjectForProject(id);
				if(projectList != null && projectList.size() > 0){
					pvo = projectList.get(0);
				}
				List<SignerVO> signerList = cmBo.findSignerForProject(status, id);
				List<PresellVO> presellList = cmBo.findPresellForProject(status, id);
				List<BuildingVO> buildingList = cmBo.findBuildingForProject(status, id);
				List<EnterpriseQualifyVO> companyList = cmBo.findCompanyForProject(id);
				request.setAttribute("signerList", signerList);
				request.setAttribute("presellList", presellList);
				request.setAttribute("buildingList", buildingList);
				request.setAttribute("companyList", companyList);
			}
			if(print != null && !"".equals(print)){
				pvo.setFinalMark(finalMark);
				pvo.setFinalCensor(sVO.getDisplayName());
				pvo.setFinalAuditDate(DateUtil.getSysDateYYYYMMDD());
				request.setAttribute("pvo", pvo);
				return "/fhhouse/company/ProjectAuditPrint";
			}else{
				request.setAttribute("pvo", pvo);
				if("audit".equals(status)){
					return "/fhhouse/company/ProjectAudit";
				}else{
					return "/fhhouse/company/ProjectPublish";
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：资质审核-项目审核
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotoProjectAuditPass")
	public String gotoProjectAuditPass(HttpServletRequest request,HttpSession session) {
		try{
			String id = request.getParameter("id");
			String finalMark = request.getParameter("finalMark");
			String type = request.getParameter("type1");
			SmUserVO sVO = (SmUserVO) session.getAttribute("LgUser");
			String userId = String.valueOf(sVO.getUserId());

			CompanyBO cmBo = new CompanyBO();
			if(id != null && !"".equals(id)){
				List<ProjectVO> projectList = cmBo.findProjectForProject(id);
				List<ProjectVO> proList = new ArrayList<ProjectVO>();
				if(projectList != null && projectList.size() > 0){
					ProjectVO pvo = projectList.get(0);
					proList.add(pvo);
				}
				List<PresellVO> presellList = cmBo.findPresellForProject("audit", id);
				List<StartUnitVO> startUnitList = new ArrayList<StartUnitVO>();
				StartUnitVO startUnitVO = new StartUnitVO();
				if(presellList != null && presellList.size() > 0){
					for(PresellVO pvo : presellList){
						startUnitVO.setStart_ID(Long.valueOf(String.valueOf(pvo.getAttribute("start_id"))));
						startUnitList.add(startUnitVO);
					}
				}
				cmBo.updateCompanyAUditStatus(type, userId, finalMark, null, null, proList, startUnitList);
			}
			return this.gotoCompanyAudit(request, session, new Page());
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：资质信息发布
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoCompanyPublishQuery")
	public String gotoCompanyPublish(HttpServletRequest request,HttpSession session,Page page) {
		try{
			request.setAttribute("projectName", "");
			request.setAttribute("companyName", "");
			request.setAttribute("status", "publish");
			return "/fhhouse/company/QualificationAudit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：资质审核/发布列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoCompanyQueryList")
	public String gotoCompanyQueryList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String project = request.getParameter("projectName") == null ? "" : request.getParameter("projectName");
			String company = request.getParameter("companyName") == null ? "" : request.getParameter("companyName");
			String status = request.getParameter("status");
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String districtList = this.getUserSqlDistricts(vo.getRegionId());//获取用户操作区县列表

			CompanyBO cmBo = new CompanyBO();
			List list = null;
			if("audit".equalsIgnoreCase(status)){
				list = cmBo.findCompanyAuditInfo(page, company, project, districtList);
			}else if("publish".equalsIgnoreCase(status)){
				list = cmBo.findCompanyPublishInfo(page, company, project, districtList);
			}

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("auditingid");
			linkparam.add("code");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("分类ID", "auditingname", "doSearch", linkparam);
			tableProperty.addColumn("提交审核日期", "firstDate");
			tableProperty.addColumn("审核分类", "name");

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("project", project);
			request.setAttribute("company", company);
			request.setAttribute("status", status);
			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/company/QualificationAuditList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/**
	 * 功能描述：开盘单元发布提交审核
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/startUnitPublish")
	public String startUnitPublish(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO smUserVO = (SmUserVO) session.getAttribute("LgUser");
			String option = request.getParameter("option");
			String startId = request.getParameter("startId");
			CompanyBO cmBo = new CompanyBO();
			StartUnitVO svo = (StartUnitVO) cmBo.find(StartUnitVO.class, Long.parseLong(startId));

			if("1".equals(option)){
				svo.setPublishMark("同意");
				svo.setStatus(FHConstant.START_UNIT_STATUS_PERMITTED);
			}
			if("2".equals(option)){
				svo.setPublishMark("不同意");
				svo.setStatus(FHConstant.START_UNIT_STATUS_NOPERMITTED);
			}
			svo.setPublishDate(DateUtil.parseDate(DateUtil.parseDateTime3(DateUtil.getSysDateYYYYMMDD())));
//			svo.setPublishCensor(smUserVO.getDisplayName());
			svo.setPublishCensor(String.valueOf(smUserVO.getUserId()));
			svo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			svo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			svo.setUpdPerson(String.valueOf(smUserVO.getUserId()));
			cmBo.update(svo);
			return "redirect:/inner/companymanage/gotoCompanyPublishQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：签约人发布提交审核
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/signerPublish")
	public String signerPublish(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO smUserVO = (SmUserVO) session.getAttribute("LgUser");
			String option = request.getParameter("option");
			String signerId = request.getParameter("signerId");
			CompanyBO cmBo = new CompanyBO();
			SignerVO svo = (SignerVO) cmBo.find(SignerVO.class, Long.parseLong(signerId));

			if("1".equals(option)){
				svo.setPublishMark("同意");
				svo.setStatus(FHConstant.SIGNER_STATUS_PERMITTED);
			}
			if("2".equals(option)){
				svo.setPublishMark("不同意");
				svo.setStatus(FHConstant.SIGNER_STATUS_NOPERMITTED);
			}
			svo.setPublishDate(DateUtil.parseDate(DateUtil.parseDateTime3(DateUtil.getSysDateYYYYMMDD())));
//			svo.setPublishCensor(smUserVO.getDisplayName());
			svo.setPublishCensor(String.valueOf(smUserVO.getUserId()));
			svo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			svo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			svo.setUpdPerson(String.valueOf(smUserVO.getUserId()));
			cmBo.update(svo);
			return "redirect:/inner/companymanage/gotoCompanyPublishQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：资质审核-项目发布
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoProjectPublishPass")
	public String gotoProjectPublishPass(HttpServletRequest request,HttpSession session) {
		try{
			String id = request.getParameter("id");
			String option = request.getParameter("option");
			SmUserVO sVO = (SmUserVO) session.getAttribute("LgUser");
			String userId = String.valueOf(sVO.getUserId());

			CompanyBO cmBo = new CompanyBO();
			if(id != null && !"".equals(id)){
				List<ProjectVO> proList = cmBo.findProjectForProject(id);
				List<PresellVO> presellList = cmBo.findPresellForProject("publish", id);
				List<StartUnitVO> startUnitList = new ArrayList<StartUnitVO>();
				StartUnitVO startUnitVO = new StartUnitVO();
				if(presellList != null && presellList.size() > 0){
					for(PresellVO pvo : presellList){
						startUnitVO.setStart_ID(Long.valueOf(String.valueOf(pvo.getAttribute("start_id"))));
						startUnitList.add(startUnitVO);
					}
				}
				cmBo.updateCompanyPublishStatus(option, userId, null, null, proList, startUnitList);
			}
			return this.gotoCompanyPublish(request, session, new Page());
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：资质审核-企业发布是否通过
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoCompanyPublishPass")
	public String gotoCompanyPublishPass(HttpServletRequest request,HttpSession session) {
		try{
			String id = request.getParameter("id");
			String option = request.getParameter("option");

			CompanyBO cmBo = new CompanyBO();

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("status", "=", FHConstant.SIGNER_STATUS_PASSED));
			params.add(new MetaFilter("comp_ID", "=", id));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
			orders.add(new MetaOrder("updTime", MetaOrder.DESC));
			List<SignerVO> list = cmBo.search(SignerVO.class, params, orders, null);

			List<ProjectVO> list2 = cmBo.findProjectInfo("publish", id);

			List<StartUnitVO> list3 = new ArrayList<StartUnitVO>();
			if(list2 != null){
				for(ProjectVO projectVO : list2){
					if(projectVO.getAttribute("start_id") != null){
//						System.out.println("start_id:" + projectVO.getAttribute("start_id"));
						StartUnitVO suVo = new StartUnitVO();
						suVo.setStart_ID(Long.parseLong(projectVO.getAttribute("start_id").toString()));
						list3.add(suVo);
					}
				}
			}


			if(id != null && !"".equals(id)){
				EnterpriseQualifyVO eqvo = new EnterpriseQualifyVO();
				eqvo.setComp_ID(Long.parseLong(id));
				SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
				String userId = String.valueOf(vo.getUserId());

				cmBo.updateCompanyPublishStatus(option, userId, eqvo, list, list2, list3);
			}
			return "redirect:/inner/companymanage/gotoCompanyPublishQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
}
