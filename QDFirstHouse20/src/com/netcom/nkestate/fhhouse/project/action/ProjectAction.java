/**
 * <p>ProjectAction.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 开发项目Action <p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.project.action;

import java.math.BigDecimal;
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
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.interfaces.bo.RealestateBO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHBuildingVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHLocationVO;
import com.netcom.nkestate.fhhouse.manage.vo.DocumentVO;
import com.netcom.nkestate.fhhouse.project.bo.ProjectBO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingHouseVO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.project.vo.PresellVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.vo.SmUserVO;


/**
 * 项目模块Action
 */
@Controller
@RequestMapping(value = "/inner/projectmanage")
public class ProjectAction extends BaseAction {

	/**
	 * 功能描述：项目编辑列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoProjectEditList")
	public String gotoProjectEditList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String compId = request.getParameter("compId");
			String logo = request.getParameter("logo");
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String backFlag = session.getAttribute("backFlag").toString();//返回标志  add:资质录入 edit:资质修改

			ProjectBO projectBO = new ProjectBO();


			List<ProjectVO> list = projectBO.findCompanyProjectList(Long.parseLong(compId), logo, page);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("project_ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addSelectControl("radio", "selProjectID", linkparam, "");
			tableProperty.addColumn("项目名称", "projectName");
			tableProperty.addColumn("项目地址 ", "projectAdr");
			tableProperty.addColumn("出让合同编号", "lotNum");
			tableProperty.addColumn("状态", "status_dict_name");

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("backFlag", backFlag);
			request.setAttribute("compId", compId);
			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/project/CompanyProejctEditList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：项目编辑页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoProjectEdit")
	public String gotoProjectEdit(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			ProjectBO projectBO = new ProjectBO();
			String compId = request.getParameter("compId");
			String projectId = request.getParameter("projectId");
			String cmd = request.getParameter("cmd"); //add:新增   edit：修改
			ProjectVO projectVO = new ProjectVO();
			//默认值设置
			projectVO.setStatus(FHConstant.PROJECT_STATUS_EDIT);
			projectVO.setDistrictID(uservo.getRegionId());

			if("edit".equals(cmd)){
				projectVO.setProject_ID(Long.parseLong(projectId));
				projectVO = (ProjectVO) projectBO.find(projectVO);
			}

			request.setAttribute("cmd", cmd);
			request.setAttribute("compId", compId);
			request.setAttribute("projectVO", projectVO);
			return "/fhhouse/project/ProjectEdit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：项目保存
	 * @param request
	 * @param session
	 * @param projectVO
	 * @return
	 */
	@RequestMapping(value = "doProjectSave")
	@ResponseBody
	public JSONArray doProjectSave(HttpServletRequest request,HttpSession session,ProjectVO projectVO) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		String message = "";
		try{
			String cmd = request.getParameter("cmd"); //add:新增   edit：修改
			long compId = Long.parseLong(request.getParameter("compId"));
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			ProjectBO projectBO = new ProjectBO();
			//项目名称检查
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("projectName", "=", projectVO.getProjectName()));
			if("edit".equals(cmd)){
				params.add(new MetaFilter("project_ID", "<>", projectVO.getProject_ID()));
			}

			List<ProjectVO> list = projectBO.search(ProjectVO.class, params);
			if(list != null && list.size() > 0){
				message = "当前项目名称已经存在，无法保存！";
			}
			//项目状态检查
			if("edit".equals(cmd) && "".equals(message)){
				if(projectVO.getStatus() == FHConstant.PROJECT_STATUS_SUBMIT){
					message = "项目已经提交审核，无法保存！";
				}else if(projectVO.getStatus() == FHConstant.PROJECT_STATUS_CANCEL){
					message = "项目已经撤销 ，无法保存！";
				}
			}
			if("".equals(message)){
				projectVO.setStatus(FHConstant.PROJECT_STATUS_EDIT);
				projectVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				projectVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				projectVO.setUpdPerson(String.valueOf(uservo.getUserId()));
				long count = -1;
				if("add".equals(cmd)){
					projectVO.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					projectVO.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					projectVO.setCrePerson(String.valueOf(uservo.getUserId()));
					count = projectBO.saveProject(projectVO, compId);
				}else{
					count = projectBO.update(projectVO);
				}
				if(count <= 0){
					message = "保存失败！";
				}
			}
			

			if("".equals(message)){
				map.put("result", "success");
				map.put("message", "保存成功！");
			}else{
				map.put("result", "fail");
				map.put("message", message);
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "保存出错");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 项目提交审核
	 * @return
	 */
	@RequestMapping(value = "doProjectFirstSubmit")
	@ResponseBody
	public JSONArray doProjectFirstSubmit(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		String message = "";
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String projectId = request.getParameter("projectId");
			ProjectBO projectBO = new ProjectBO();

			ProjectVO projectVO = new ProjectVO();
			projectVO.setProject_ID(Long.parseLong(projectId));

			projectVO = (ProjectVO) projectBO.find(projectVO);
			if(projectVO == null){
				message = "未查询到该项目记录！";
			}
			if(projectVO.getStatus() != FHConstant.PROJECT_STATUS_EDIT){
				message = "项目不是编辑状态无法进行提交审核，请先进行保存操作！";
			}else if(projectVO.getStatus() == FHConstant.PROJECT_STATUS_SUBMIT){
				message = "项目已经提交审核，无法再次进行提交操作！";
			}else if(projectVO.getStatus() == FHConstant.PROJECT_STATUS_CANCEL){
				message = "项目已经撤销 ，无法再次进行提交操作！";
			}

			if("".equals(message)){
				projectVO = new ProjectVO();
				projectVO.setProject_ID(Long.parseLong(projectId));
				projectVO.setStatus(FHConstant.PROJECT_STATUS_SUBMIT);
				projectVO.setFirstAuditDate(DateUtil.getSysDateYYYYMMDD());
				projectVO.setFirstCensor(String.valueOf(uservo.getUserId()));
				projectVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				projectVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				projectVO.setUpdPerson(String.valueOf(uservo.getUserId()));

				long count = projectBO.update(projectVO);
				if(count != 1){
					message = "提交失败!";
				}
			}
			
			if("".equals(message)){
				map.put("result", "success");
				map.put("message", "提交成功！");
			}else{
				map.put("result", "fail");
				map.put("message", message);
			}

			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "项目提交审核出错");
			json = JSONArray.fromObject(map);
			return json;
		}
	}


	/**
	 * 功能描述：许可证编辑列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoPresellEditList")
	public String gotoPresellEditList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String compId = request.getParameter("compId");
			String projectId = request.getParameter("projectId");
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String backFlag = session.getAttribute("backFlag").toString();//返回标志  add:资质录入 edit:资质修改

			ProjectBO projectBO = new ProjectBO();


			List<StartUnitVO> list = projectBO.findProjectPresellList(Long.parseLong(projectId), page, backFlag);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("start_ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addSelectControl("radio", "selStartID", linkparam, "");
			tableProperty.addColumn("开盘编号", "start_Code");
			tableProperty.addColumn("许可证编号 ", "presell_desc");
			tableProperty.addColumn("坐落", "buildingName");
			tableProperty.addColumn("收件编号", "document_ID");
			tableProperty.addColumn("上网套数", "houseCount");
			tableProperty.addColumn("状态", "status_dict_name");

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("backFlag", backFlag);
			request.setAttribute("compId", compId);
			request.setAttribute("projectId", projectId);
			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/project/PresellEditList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：许可证编辑Frame
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoPresellEditFrame")
	public String gotoPresellEditFrame(HttpServletRequest request,HttpSession session) {
		String compId = request.getParameter("compId");
		String projectId = request.getParameter("projectId");
		String cmd = request.getParameter("cmd");
		String startId = request.getParameter("startId");
		request.setAttribute("cmd", cmd);
		request.setAttribute("startId", startId);
		request.setAttribute("compId", compId);
		request.setAttribute("projectId", projectId);
		return "/fhhouse/project/PresellEditFrame";
	}

	/**
	 * 功能描述：许可证编辑页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoPresellEdit")
	public String gotoPresellEdit(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			ProjectBO projectBO = new ProjectBO();
			String compId = request.getParameter("compId");
			String projectId = request.getParameter("projectId");
			String startId = request.getParameter("startId");
			String cmd = request.getParameter("cmd"); //add:新增   edit：修改
			PresellVO presellVO = new PresellVO();
			StartUnitVO unitVO = new StartUnitVO();
			//默认值设置
			unitVO.setStatus(FHConstant.PROJECT_STATUS_EDIT);
			presellVO.setType(FHConstant.LICENCE_DIVISION_PRESELL);
			presellVO.setProject_ID(Long.parseLong(projectId));

			if("edit".equals(cmd)){
				unitVO.setStart_ID(Long.parseLong(startId));
				unitVO = (StartUnitVO) projectBO.find(unitVO);

				presellVO.setPresell_ID(unitVO.getPresell_ID());
				presellVO = (PresellVO) projectBO.find(presellVO);
			}

			request.setAttribute("cmd", cmd);
			request.setAttribute("compId", compId);
			request.setAttribute("presellVO", presellVO);
			request.setAttribute("unitVO", unitVO);
			return "/fhhouse/project/PresellEdit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：收件编号检查页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoDocumentCheck")
	public String gotoDocumentCheck(HttpServletRequest request,HttpSession session) {
		try{
			String docId = request.getParameter("docId");
			DocumentVO documentVO = new DocumentVO();
			documentVO.setDocument_Id(Long.parseLong(docId));
			ProjectBO projectBO = new ProjectBO();

			documentVO = (DocumentVO) projectBO.find(documentVO);
			request.setAttribute("documentVO", documentVO);
			return "/fhhouse/project/DocumentInfoCheck";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：许可证保存
	 * @param request
	 * @param session
	 * @param presellVO
	 * @param unitVO
	 * @return
	 */
	@RequestMapping(value = "doPresellSave")
	@ResponseBody
	public JSONArray doPresellSave(HttpServletRequest request,HttpSession session,PresellVO presellVO,StartUnitVO unitVO) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		String message = "";
		try{
			String cmd = request.getParameter("cmd"); //add:新增   edit：修改
			long compId = Long.parseLong(request.getParameter("compId"));
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(uservo.getRegionId());//获取用户操作区县列表
			ProjectBO projectBO = new ProjectBO();
			//开盘编号检查
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("start_Code", "=", unitVO.getStart_Code()));
			if("edit".equals(cmd)){
				params.add(new MetaFilter("start_ID", "<>", unitVO.getStart_ID()));
			}

			List<StartUnitVO> list = projectBO.search(StartUnitVO.class, params);
			if(list != null && list.size() > 0){
				message = "当前开盘编号已经存在，无法保存！";
			}

			if("".equals(message)){
				//收件编号存在性检查
				params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("districtid", "in", userDistrictList));
				params.add(new MetaFilter("document_Id", "=", unitVO.getDocument_ID()));
				list = projectBO.search(DocumentVO.class, params);
				if(list == null || list.size() <= 0){
					message = "不存在该收件！";
				}
			}

			//项目状态检查
			if("edit".equals(cmd) && "".equals(message)){
				if(unitVO.getStatus() == FHConstant.START_UNIT_STATUS_SUBMIT){
					message = "开盘单元已经提交审核，无法保存！";
				}else if(unitVO.getStatus() == FHConstant.START_UNIT_STATUS_CANCEL){
					message = "开盘单元已经撤销 ，无法保存！";
				}
			}
			if("".equals(message)){
				presellVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				presellVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				presellVO.setUpdPerson(String.valueOf(uservo.getUserId()));

				unitVO.setStatus(FHConstant.START_UNIT_STATUS_EDIT);
				unitVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				unitVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				unitVO.setUpdPerson(String.valueOf(uservo.getUserId()));
				long count = -1;
				if("add".equals(cmd)){
					presellVO.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					presellVO.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					presellVO.setCrePerson(String.valueOf(uservo.getUserId()));

					unitVO.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					unitVO.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					unitVO.setCrePerson(String.valueOf(uservo.getUserId()));
					count = projectBO.savePresell(presellVO, unitVO, compId);
				}else{
					count = projectBO.updatePresell(presellVO, unitVO, compId);
				}
				if(count <= 0){
					message = "保存失败！";
				}
			}


			if("".equals(message)){
				map.put("result", "success");
				map.put("message", "保存成功！");
			}else{
				map.put("result", "fail");
				map.put("message", message);
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "保存出错");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 许可证提交审核
	 * @return
	 */
	@RequestMapping(value = "doPresellFirstSubmit")
	@ResponseBody
	public JSONArray doPresellFirstSubmit(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		String message = "";
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String startId = request.getParameter("startId");
			ProjectBO projectBO = new ProjectBO();

			StartUnitVO unitVO = new StartUnitVO();
			unitVO.setStart_ID(Long.parseLong(startId));

			unitVO = (StartUnitVO) projectBO.find(unitVO);
			if(unitVO == null){
				message = "未查询到该开盘单元记录！";
			}
			if(unitVO.getStatus() != FHConstant.START_UNIT_STATUS_EDIT){
				message = "开盘单元不是编辑状态无法进行提交审核，请先进行保存操作！";
			}else if(unitVO.getStatus() == FHConstant.START_UNIT_STATUS_SUBMIT){
				message = "开盘单元已经提交审核，无法再次进行提交操作！";
			}else if(unitVO.getStatus() == FHConstant.START_UNIT_STATUS_CANCEL){
				message = "开盘单元已经撤销 ，无法再次进行提交操作！";
			}

			if("".equals(message)){
				unitVO = new StartUnitVO();
				unitVO.setStart_ID(Long.parseLong(startId));
				unitVO.setStatus(FHConstant.START_UNIT_STATUS_SUBMIT);
				unitVO.setFirstAuditDate(DateUtil.getSysDateYYYYMMDD());
				unitVO.setFirstCensor(String.valueOf(uservo.getUserId()));
				unitVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				unitVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				unitVO.setUpdPerson(String.valueOf(uservo.getUserId()));

				long count = projectBO.update(unitVO);
				if(count != 1){
					message = "提交失败!";
				}
			}

			if("".equals(message)){
				map.put("result", "success");
				map.put("message", "提交成功！");
			}else{
				map.put("result", "fail");
				map.put("message", message);
			}

			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "许可证提交审核出错");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：开盘单元楼盘维护页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoStartBuildingRelation")
	public String gotoStartBuildingRelation(HttpServletRequest request,HttpSession session) {
		try{
			String compId = request.getParameter("compId");
			String projectId = request.getParameter("projectId");
			String startId = request.getParameter("startId");

			//获取开盘单元信息
			ProjectBO projectBO = new ProjectBO();
			StartUnitVO unitVO = new StartUnitVO();
			unitVO.setStart_ID(Long.parseLong(startId));

			unitVO = (StartUnitVO) projectBO.find(unitVO);

			
			request.setAttribute("compId", compId);
			request.setAttribute("projectId", projectId);
			request.setAttribute("startId", startId);
			request.setAttribute("startSatuts", unitVO.getStatus());
			session.setAttribute("selbuildingList", new ArrayList<BuildingHouseVO>());//进入页面设置选择楼栋为空
			session.setAttribute("houseMap", new HashMap<String , List<BuildingHouseVO>>());//设置楼栋选择房屋
			return "/fhhouse/project/StartBuildingRelation";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述 ：开盘单元关联楼栋列表
	 * @return
	 */
	@RequestMapping(value = "/getStartBuildingList")
	@ResponseBody
	public JSONArray getStartBuildingList(HttpSession httpSession,HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			String startId = request.getParameter("startId");
			ProjectBO projectBO = new ProjectBO();
			//开盘单元列表
			List<BuildingVO> startBuildingList = projectBO.findStartUnitBuildingList(Long.parseLong(startId));
			StringBuffer sb = new StringBuffer();

			if(startBuildingList != null && startBuildingList.size() > 0){
				for(BuildingVO buildingVO : startBuildingList){
					sb.append("<option value='" + buildingVO.getBuilding_ID() + "'>");
					sb.append(buildingVO.getBuilding_Name());
					sb.append("</option>");
				}
			}
			map.put("result", "success");
			map.put("message", "获取成功！");
			map.put("startBuildingList", sb.toString());
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
	 * 功能描述 ：开盘单元关联坐落查询
	 * @return
	 */
	@RequestMapping(value = "/queryLocationList")
	@ResponseBody
	public JSONArray queryLocationList(HttpSession httpSession,HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			//String startId = request.getParameter("startId");
			String districtId = request.getParameter("district");
			String roadname = request.getParameter("roadname");
			String lanename = request.getParameter("lanename");
			String sublane = request.getParameter("sublane");
			String streetnumber = request.getParameter("streetnumber");
			RealestateBO realestateBO = new RealestateBO();
			//坐落列表
			List<CHLocationVO> locationList = realestateBO.findLocationList(districtId, roadname, lanename, sublane, streetnumber);
			StringBuffer sb = new StringBuffer();
			sb.append("<option value=''></option>");
			if(locationList != null && locationList.size() > 0){
				for(CHLocationVO locationVO : locationList){
					sb.append("<option value='" + locationVO.getLocationId() + "'>");
					sb.append(locationVO.getLocationName());
					sb.append("</option>");
				}
			}
			map.put("result", "success");
			map.put("message", "获取成功！");
			map.put("queryLocationList", sb.toString());
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
	 * 功能描述 ：开盘单元关联坐落查询楼栋
	 * @return
	 */
	@RequestMapping(value = "/queryBuildingList")
	@ResponseBody
	public JSONArray queryBuildingList(HttpSession httpSession,HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			//String startId = request.getParameter("startId");
			String locationId = request.getParameter("locationId");
			RealestateBO realestateBO = new RealestateBO();
			//楼栋列表
			List<CHBuildingVO> buildingList = realestateBO.findBuidlingList(Long.parseLong(locationId));
			StringBuffer sb = new StringBuffer();

			if(buildingList != null && buildingList.size() > 0){
				for(CHBuildingVO buildingVO : buildingList){
					sb.append("<option value='" + buildingVO.getBuildingID() + "'>");
					sb.append(buildingVO.getLocation_Name());
					sb.append("</option>");
				}
			}
			map.put("result", "success");
			map.put("message", "获取成功！");
			map.put("queryBuildingList", sb.toString());
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
	 * 功能描述 ：开盘单元关联楼栋删除
	 * @return
	 */
	@RequestMapping(value = "/doDelStartBuilding")
	@ResponseBody
	public JSONArray doDelStartBuilding(HttpSession session,HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		String msg = "";
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String startId = request.getParameter("startId");
			String buildingId = request.getParameter("startBuilding");
			ProjectBO projectBO = new ProjectBO();
			
			List<HouseVO> houseList = projectBO.findStartUnitHouseListByBuidling(Long.parseLong(startId), Long.parseLong(buildingId));
			if(houseList != null && houseList.size() > 0){
				for(HouseVO houseVO : houseList){
					//状态= 已付定金（粉红）或 已签（黄）或 已登记（红）时 报错
					if(houseVO.getStatus() == FHConstant.HOUSE_STATUS_EARNEST || houseVO.getStatus() == FHConstant.HOUSE_STATUS_SIGNED
							|| houseVO.getStatus() == FHConstant.HOUSE_STATUS_REGISTED){

						//msg = "该楼栋中所包含的房屋不全为可售状态，不可进行修改或删除。";
						//break;
					}
				}
			}
			if("".equals(msg)){
				//更新开盘单元为编辑状态
				StartUnitVO startUnitVO = new StartUnitVO();
				startUnitVO.setStatus(FHConstant.START_UNIT_STATUS_EDIT);
				startUnitVO.setStart_ID(Long.parseLong(startId));
				startUnitVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				startUnitVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				startUnitVO.setUpdPerson(String.valueOf(uservo.getUserId()));

				//删除操作
				projectBO.deleteStartBuidling(Long.parseLong(startId), Long.parseLong(buildingId), startUnitVO);

			}
			if("".equals(msg)){
				//楼栋列表
				List<BuildingVO> startBuildingList = projectBO.findStartUnitBuildingList(Long.parseLong(startId));
				StringBuffer sb = new StringBuffer();
				if(startBuildingList != null && startBuildingList.size() > 0){
					for(BuildingVO buildingVO : startBuildingList){
						sb.append("<option value='" + buildingVO.getBuilding_ID() + "'>");
						sb.append(buildingVO.getBuilding_Name());
						sb.append("</option>");
					}
				}
				map.put("result", "success");
				map.put("message", "删除成功！");
				map.put("startBuildingList", sb.toString());
			}else{
				map.put("result", "fail");
				map.put("message", msg);
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "删除出错");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：开盘单元楼盘维护页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoStartBuildingEditList")
	public String gotoStartBuildingEditList(HttpServletRequest request,HttpSession session) {
		try{
			String compId = request.getParameter("compId");
			String projectId = request.getParameter("projectId");
			String startId = request.getParameter("startId");
			String selcmd = request.getParameter("selcmd");//1:关联楼盘变更   2：新增楼盘添加  3:删除
			List<BuildingHouseVO> buildingList = (List<BuildingHouseVO>) session.getAttribute("selbuildingList");
			//楼栋选择房屋Map
			Map<String , List<BuildingHouseVO>> houseMap = (Map<String , List<BuildingHouseVO>>) session.getAttribute("houseMap");
			//删除楼栋操作
			if("3".equals(selcmd)){
				List<BuildingHouseVO> newList = new ArrayList<BuildingHouseVO>();
				for(BuildingHouseVO vo : buildingList){
					String delBuildingId = request.getParameter("DelSelect_" + vo.getBuilding_ID());
					if(delBuildingId == null || "".equals(delBuildingId)){
						newList.add(vo);
					}else{
						//选择房屋Map中删除选项
						houseMap.remove(vo.getBuilding_ID() + "");
					}
				}
				buildingList = newList;

			}else{
				ProjectBO projectBO = new ProjectBO();
				RealestateBO realestateBO = new RealestateBO();
				String[] buildingId = null;
				if("1".equals(selcmd)){
					buildingId = request.getParameterValues("startBuilding");
				}else if("2".equals(selcmd)){
					buildingId = request.getParameterValues("queryBuilding");
				}


				if(buildingId != null && buildingId.length > 0){
					for(int i = 0; i < buildingId.length; i++){
						boolean exists = false;//判断选择的楼栋是否已经选择过
						for(BuildingHouseVO vo : buildingList){
							if(vo.getBuilding_ID() == Long.parseLong(buildingId[i])){
								exists = true;
							}
						}
						if(!exists){
							BuildingHouseVO bhvo = new BuildingHouseVO();
							List<MetaFilter> params = new ArrayList<MetaFilter>();
							params.add(new MetaFilter("start_ID", "=", startId));
							params.add(new MetaFilter("building_ID", "=", buildingId[i]));
							//查询是否已经关联记录
							List<BuildingHouseVO> list = projectBO.search(BuildingHouseVO.class, params);
							if(list != null && list.size() > 0){
								bhvo = list.get(0);
								//已关联楼栋选择房屋处理
								List<BuildingHouseVO> houseList = new ArrayList<BuildingHouseVO>();
								for(BuildingHouseVO tempvo : list){
									houseList.add(tempvo);
								}
								houseMap.put(buildingId[i], houseList);

							}else{

								bhvo.setStart_ID(Long.parseLong(startId));
								bhvo.setBuilding_ID(Long.parseLong(buildingId[i]));
							}
							//坐落查询
							String location = realestateBO.findBuidlingLoaction(Long.parseLong(buildingId[i]));
							bhvo.setAttribute("location", location);
							buildingList.add(bhvo);
						}
					}
				}
			}

			request.setAttribute("compId", compId);
			request.setAttribute("projectId", projectId);
			request.setAttribute("startId", startId);
			request.setAttribute("selbuildingList", buildingList);
			session.setAttribute("selbuildingList", buildingList);
			session.setAttribute("houseMap", houseMap);
			return "/fhhouse/project/StartBuildingEditList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/**
	 * 功能描述 ：开盘单元关联楼栋保存
	 * @return
	 */
	@RequestMapping(value = "/doStartBuildingSave")
	@ResponseBody
	public JSONArray doStartBuildingSave(HttpSession session,HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		String msg = "";
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String startId = request.getParameter("startId");
			String projectId = request.getParameter("projectId");
			ProjectBO projectBO = new ProjectBO();
			//选择楼栋
			List<BuildingHouseVO> buildingList = (List<BuildingHouseVO>) session.getAttribute("selbuildingList");
			//楼栋选择房屋Map
			Map<String , List<BuildingHouseVO>> houseMap = (Map<String , List<BuildingHouseVO>>) session.getAttribute("houseMap");
			//获取楼栋的参考价格等信息
			for(BuildingHouseVO bhVO : buildingList){
				String referencePrice = request.getParameter("Reference_Price_" + bhVO.getBuilding_ID());
				String ratio = request.getParameter("Ratio_" + bhVO.getBuilding_ID());
				bhVO.setReference_Price(Double.parseDouble(referencePrice));
				bhVO.setRatio(Double.parseDouble(ratio));
			}
			if("".equals(msg)){
				long count = projectBO.saveStartBuidling(Long.parseLong(projectId), Long.parseLong(startId), buildingList, houseMap, uservo);
				map.put("result", "success");
				map.put("message", "保存成功！");
			}else{
				map.put("result", "fail");
				map.put("message", msg);
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "开盘单元关联楼栋保存出错");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：楼栋房屋列表页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoHouseList")
	public String gotoHouseList(HttpServletRequest request,HttpSession session) {
		try{
			String startId = request.getParameter("startId");
			String buildingId = request.getParameter("buildingId");

			//楼栋选择房屋Map
			Map<String , List<BuildingHouseVO>> houseMap = (Map<String , List<BuildingHouseVO>>) session.getAttribute("houseMap");

			//获取楼栋房屋列表信息
			ProjectBO projectBO = new ProjectBO();
			List<CHFlatVO> list = projectBO.findBuildHouseList(Long.parseLong(buildingId));
			List<List<CHFlatVO>> houseList = new ArrayList<List<CHFlatVO>>();
			List<BuildingHouseVO> bhList = houseMap.get(buildingId);//该楼栋选择的房屋

			if(list != null && list.size() > 0){
				int tempfloor = -999999;
				List<CHFlatVO> tempList = new ArrayList<CHFlatVO>();
				for(int i = 0; i < list.size(); i++){
					CHFlatVO flatvo = list.get(i);
					//楼层处理
					int floor = 0;
					if(flatvo.getFloor_From() == null || "".equals(flatvo.getFloor_From())){
						floor = 0;
					}else{
						floor = (int) Double.parseDouble(flatvo.getFloor_From());
					}
					//合同状态处理
					String status1 = new String();//登记状态
					String status2 = new String();//一手房房屋状态
					if(flatvo.getAttribute("STATUS1") != null){
						status1 = (String) flatvo.getAttribute("STATUS1");
					}
					if(flatvo.getAttribute("STATUS2") != null){
						status2 = ((BigDecimal) flatvo.getAttribute("STATUS2")).toString();
					}
					String start_ID = new String("");
					if(flatvo.getAttribute("START_ID") != null){
						start_ID = ((BigDecimal) flatvo.getAttribute("START_ID")).toString();
					}

					String status = "";
					if(status2 == null || status2.equals(new String(""))){
						if(status1 == null || status1.equals(new String(""))){
							status = String.valueOf(FHConstant.HOUSE_STATUS_UNACTIVE);
						}else if(status1.equals(String.valueOf(FHConstant.DAJI_HOUSE_STATUS_ONLINE))){
							status = String.valueOf(FHConstant.HOUSE_STATUS_CANSELL);
						}else if(status1.equals(String.valueOf(FHConstant.DAJI_HOUSE_STATUS_REGISTED))){
							status = String.valueOf(FHConstant.HOUSE_STATUS_REGISTED);
						}else if(status1.equals(String.valueOf(FHConstant.DAJI_HOUSE_STATUS_CANCEL))){
							status = String.valueOf(FHConstant.HOUSE_STATUS_SIGNED);
						}else if(status1.equals(String.valueOf(FHConstant.DAJI_HOUSE_STATUS_UNONLINE))){
							status = String.valueOf(FHConstant.HOUSE_STATUS_UNACTIVE);

						}else{
							status = String.valueOf(FHConstant.HOUSE_STATUS_UNACTIVE);

						}
					}else{
						status = status2;
						if(status1.equals(String.valueOf(FHConstant.DAJI_HOUSE_STATUS_ONLINE))
								&& status2.equals(String.valueOf(FHConstant.HOUSE_STATUS_UNACTIVE)))
							status = String.valueOf(FHConstant.HOUSE_STATUS_CANSELL);
						if(status1.equals(String.valueOf(FHConstant.DAJI_HOUSE_STATUS_REGISTED))
								&& ! (status2.equals(String.valueOf(FHConstant.HOUSE_STATUS_UNACTIVE))))
							status = String.valueOf(FHConstant.HOUSE_STATUS_REGISTED);
						if(status1.equals(String.valueOf(FHConstant.DAJI_HOUSE_STATUS_UNONLINE))
								&& status2.equals(String.valueOf(FHConstant.HOUSE_STATUS_CANSELL)))
							status = String.valueOf(FHConstant.HOUSE_STATUS_UNACTIVE);
						if(status1.equals(String.valueOf(FHConstant.DAJI_HOUSE_STATUS_CANCEL))
								&& status2.equals(String.valueOf(FHConstant.HOUSE_STATUS_REGISTED)))
							status = String.valueOf(FHConstant.HOUSE_STATUS_SIGNED);
						if( (!start_ID.equals("")) && ! (start_ID.equals(startId))){
							status = "7";
						}
					}
					String statusName = "";
					if(FHConstant.HOUSE_STATUS_SIGNED == Integer.parseInt(status)){
						statusName = "已签（黄）";
					}else if(FHConstant.HOUSE_STATUS_REGISTED == Integer.parseInt(status)){
						statusName = "已登记（红）";
					}else if(FHConstant.HOUSE_STATUS_CANSELL == Integer.parseInt(status)){
						statusName = "可售（绿）";
					}else if(FHConstant.HOUSE_STATUS_EARNEST == Integer.parseInt(status)){
						statusName = "已付定金（粉红）";
					}else if(FHConstant.HOUSE_STATUS_UNACTIVE == Integer.parseInt(status)){
						statusName = "未纳入网上销售（白）";
					}else if(7 == Integer.parseInt(status)){
						statusName = "已被其它开盘单元添加";
					}
					flatvo.setAttribute("status", status);
					flatvo.setAttribute("statusName", statusName);

					//房屋选择判断
					String checked = "0";
					if(bhList != null && bhList.size() > 0){
						for(BuildingHouseVO bhvo : bhList){
							if(bhvo.getHouse_ID() == flatvo.getHouseID()){
								checked = "1";
								break;
							}
						}
					}
					flatvo.setAttribute("checked", checked);

					//判断是否第一条记录
					if(i == 0){
						tempfloor = floor;
						tempList.add(flatvo);
					}else{
						//判断是否同一层记录
						if(tempfloor != floor){
							houseList.add(tempList);
							tempList = new ArrayList<CHFlatVO>();
							tempfloor = floor;
							tempList.add(flatvo);
						}else{
							tempList.add(flatvo);
						}
					}

					//判断是否最后一条记录
					if(i == (list.size() - 1)){
						if(tempList.size() > 0){
							houseList.add(tempList);
						}
					}

				}
			}


			request.setAttribute("buildingId", buildingId);
			request.setAttribute("startId", startId);
			request.setAttribute("houseList", houseList);
			session.setAttribute("HouseListSize", list.size());
			return "/fhhouse/project/HouseList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/**
	 * 功能描述 ：开盘单元关联房屋选择保存
	 * @return
	 */
	@RequestMapping(value = "/doHouseSelSave")
	@ResponseBody
	public JSONArray doHouseSelSave(HttpSession session,HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		String msg = "";
		try{
			String startId = request.getParameter("startId");
			String buildingId = request.getParameter("buildingId");
			int houseListSize = Integer.parseInt(session.getAttribute("HouseListSize").toString());//房屋
			//楼栋选择房屋Map
			Map<String , List<BuildingHouseVO>> houseMap = (Map<String , List<BuildingHouseVO>>) session.getAttribute("houseMap");
			List<BuildingHouseVO> houseList = new ArrayList<BuildingHouseVO>();
			//获取选择的房屋
			for(int i = 0; i < houseListSize; i++){
				if("on".equalsIgnoreCase(request.getParameter("HOUSESELECT_" + i))){
					BuildingHouseVO bhvo = new BuildingHouseVO();
					bhvo.setBuilding_ID(Long.parseLong(buildingId));
					bhvo.setHouse_ID(Long.parseLong(request.getParameter("HOUSE_ID_" + i)));
					houseList.add(bhvo);
				}
			}
			//选择房屋信息放入缓存
			houseMap.put(buildingId, houseList);
			session.setAttribute("houseMap", houseMap);

			
			if("".equals(msg)){
				map.put("result", "success");
				map.put("message", "关联成功！");
			}else{
				map.put("result", "fail");
				map.put("message", msg);
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "开盘单元关联房屋选择关联出错");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

}
