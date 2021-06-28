package com.netcom.nkestate.fhhouse.manage.action;

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
import com.netcom.nkestate.fhhouse.manage.bo.PresellManageBO;
import com.netcom.nkestate.fhhouse.manage.vo.UserProjectSetVO;
import com.netcom.nkestate.fhhouse.project.vo.PresellVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.vo.SmUserVO;

/**
 * 签约人许可证权限管理Action
 */
@Controller
@RequestMapping(value = "/inner/presellmanage")
public class PresellManageAction extends BaseAction {


	/**
	 * 功能描述：转到签约人许可证权限维护
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoPresellMaintain")
	public String gotoCompanyAdd(HttpServletRequest request) {
		try{

			return "fhhouse/manage/userprojectset/UserProjectSetMaintain";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述 ：开发企业选择列表
	 * @return
	 */
	@RequestMapping(value = "/queryCompany")
	@ResponseBody
	public JSONArray queryCompany(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			PresellManageBO pmBO = new PresellManageBO();
			String name = request.getParameter("compName");
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> districtList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("name", "like", "%" + name + "%"));
			params.add(new MetaFilter("bizdistrict", "in", districtList));
			StringBuffer sb = new StringBuffer();
			List<EnterpriseQualifyVO> companyList = pmBO.search(EnterpriseQualifyVO.class, params);
			if(companyList != null && companyList.size() > 0){
				sb.append("<option value=''>==请选择==</option>");
				for(EnterpriseQualifyVO eVo : companyList){
					sb.append("<option value='" + eVo.getComp_ID() + "'>" + eVo.getName() + "</option>");
				}
			}else{
				sb.append("<option value=''>没有对应的企业</option>");
			}
			map.put("result", "success");
			map.put("message", "获取成功！");
			map.put("companyList", sb.toString());
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
	 * 功能描述 ：开发企业员工选择列表
	 * @return
	 */
	@RequestMapping(value = "/querySignerByCompany")
	@ResponseBody
	public JSONArray querySignerByCompany(HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			PresellManageBO pmBO = new PresellManageBO();
			String compId = request.getParameter("compId");
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("comp_id", "=", compId));

			StringBuffer sb = new StringBuffer();
			List<SignerVO> signerList = pmBO.search(SignerVO.class, params);
			if(signerList != null && signerList.size() > 0){
				for(SignerVO sVo : signerList){
					sb.append("<option value='" + sVo.getSigner_ID() + "'>" + sVo.getName() + "</option>");
				}
			}else{
				sb.append("<option value=''>==没有对应的员工==</option>");
			}
			map.put("result", "success");
			map.put("message", "获取成功！");
			map.put("signerList", sb.toString());
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
	 * 功能描述 ：开发企业项目选择列表
	 * @return
	 */
	@RequestMapping(value = "/queryProjectByCompany")
	@ResponseBody
	public JSONArray queryProjectByCompany(HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			PresellManageBO pmBO = new PresellManageBO();
			String compId = request.getParameter("compId");
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("compId", "=", compId));

			StringBuffer sb = new StringBuffer();
			List<ProjectVO> projectList = pmBO.queryProject(compId);
			if(projectList != null && projectList.size() > 0){
				sb.append("<option value=''>==请选择==</option>");
				for(ProjectVO pVo : projectList){
					sb.append("<option value='" + pVo.getProject_ID() + "'>" + pVo.getProjectName() + "</option>");
				}
			}else{
				sb.append("<option value=''>==没有对应的项目==</option>");
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
	 * 功能描述 ：许可证选择列表
	 * @return
	 */
	@RequestMapping(value = "/queryPresellByProject")
	@ResponseBody
	public JSONArray queryPresellByProject(HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			PresellManageBO pmBO = new PresellManageBO();
			String projectId = request.getParameter("projectId");
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("project_id", "=", projectId));
			List<PresellVO> presellList = pmBO.search(PresellVO.class, params);
			StringBuffer sb = new StringBuffer();
			if(presellList != null && presellList.size() > 0){
				sb.append("<option value='' disabled='disabled'>==请注意：不选择的默认为是删除==</option>");
				for(PresellVO pVo : presellList){
					sb.append("<option value='" + pVo.getPresell_ID() + "'>" + pVo.getPresell_Desc() + "</option>");
				}
			}else{
				sb.append("<option value=''>==没有对应的许可证信息==</option>");
			}
			map.put("result", "success");
			map.put("message", "获取成功！");
			map.put("presellList", sb.toString());
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
	 * 功能描述 ：签约人许可证权限新增或删除列表
	 * @return
	 */
	@RequestMapping(value = "/saveUserProjectSet")
	@ResponseBody
	public JSONArray saveUserProjectSet(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			PresellManageBO pmBO = new PresellManageBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String projectID = request.getParameter("project");
			String signerID = request.getParameter("signer");
			String[] acceptno = request.getParameterValues("presell");

			if(signerID != null && !"".equals(signerID) && projectID != null && !"".equals(projectID)){
				List<UserProjectSetVO> userpreselllist = pmBO.queryUserPresell(signerID, projectID);

				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("project_id", "=", projectID));
				List<PresellVO> presellList = pmBO.search(PresellVO.class, params);

				UserProjectSetVO upVo = new UserProjectSetVO();
				List<UserProjectSetVO> uplist = new ArrayList<UserProjectSetVO>();
				if(acceptno != null){
					
				}else{
					acceptno = new String[0];
				}
				int i = 0;
				if(userpreselllist != null){
					for(String acceptNo : acceptno){
						boolean same = false;
						for(UserProjectSetVO upVO1 : userpreselllist){
							if(acceptNo.equals(upVO1.getAcceptno())){
								same = true;
							}
						}
						if(!same){
							upVo.setUserName(signerID);
							upVo.setAcceptno(acceptNo);
							upVo.setSaveDate(DateUtil.getSysDateYYYYMMDD());
							upVo.setSavePerson(vo.getLoginName());
							pmBO.add(upVo);

						}
						i++;
					}

				}

				int j = 0;
				if(presellList != null){
					String[] acceptnos = new String[presellList.size() - acceptno.length];
					for(PresellVO pVo : presellList){
						boolean same = false;
						for(String accept : acceptno){
							if(accept.equals(String.valueOf(pVo.getPresell_ID()))){
								same = true;
							}
						}
						if(!same){
							acceptnos[j] = String.valueOf(pVo.getPresell_ID());
							j++;
						}
					}
					if(j > 0){
						pmBO.deleteUserProjectSet(signerID, acceptnos);
					}
					map.put("result", "success");
					map.put("message", "选择项已保存" + i + "条，未选项已删除" + j + "条!");
				}
			}else{
				map.put("result", "fail");
				map.put("message", "保存失败！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "保存出错！");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：转到签约人许可证权限列表查询
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoPresellManageQueryFrame")
	public String gotoUserProjectSetQueryFrame(HttpServletRequest request) {
		try{
			return "fhhouse/manage/userprojectset/UserProjectSetQueryFrame";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：转到签约人许可证权限列表
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoUserProjectSetQueryList")
	public String gotoUserProjectSetQueryList(HttpServletRequest request,Page page) {
		try{
			PresellManageBO pmBO = new PresellManageBO();
			String name = request.getParameter("signerName");
			String presell_Desc = request.getParameter("presell_Desc");
			String companyName = request.getParameter("companyName");
			String projectName = request.getParameter("projectName");
			String savedate = request.getParameter("savedate");
			request.setAttribute("signerName", name);
			request.setAttribute("presell_Desc", presell_Desc);
			request.setAttribute("companyName", companyName);
			request.setAttribute("projectName", projectName);
			request.setAttribute("savedate", savedate);
			List list = pmBO.queryUserProjectSet(name, presell_Desc, companyName, projectName, savedate, page);
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("用户名", "name");
			tableProperty.addColumn("开发公司", "companyname");
			tableProperty.addColumn("项目名称", "projectname");
			tableProperty.addColumn("许可证信息", "presell_desc");
			tableProperty.addColumn("key", "key");
			tableProperty.addColumn("操作时间", "savedate");

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/userprojectset/UserProjectSetQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
}
