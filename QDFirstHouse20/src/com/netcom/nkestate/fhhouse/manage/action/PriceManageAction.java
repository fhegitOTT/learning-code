package com.netcom.nkestate.fhhouse.manage.action;

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
import com.netcom.nkestate.fhhouse.manage.bo.EstateAvgPriceBO;
import com.netcom.nkestate.fhhouse.manage.bo.GuidePriceLogBO;
import com.netcom.nkestate.fhhouse.manage.bo.GuidePriceMgBO;
import com.netcom.nkestate.fhhouse.manage.bo.LowPriceControlBO;
import com.netcom.nkestate.fhhouse.manage.vo.HouseBandAverageVO;
import com.netcom.nkestate.fhhouse.manage.vo.JGGLLogVO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingHouseVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.vo.SmUserVO;

/**
 * 价格管理模块Action
 */
@Controller
@RequestMapping(value = "/inner/Pricemanage")
public class PriceManageAction extends BaseAction {

	static Logger logger = Logger.getLogger(PriceManageAction.class.getName());

	/**
	 * 功能描述：楼盘均价菜单
	 * @param request
	 * @param session
	 * @return
	 */

	@RequestMapping(value = "/gotoEstateAvgPriceList")
	public String gotoEstateAvgPrice(HttpServletRequest request,HttpSession session) {
			try{
			return "fhhouse/manage/pricemanage/EstateAvgPrice";
			}catch (Exception e){
				e.printStackTrace();
				return ERROR_System;
			}
		}


	/**
	 * 功能描述:楼盘均价查询列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/gotoEstateAvgPrice")
	public String gotoEstateAvgPriceList(HttpServletRequest request,HttpSession session,Page page) throws Exception {
	  try{
		EstateAvgPriceBO estateAvgPriceBO = new EstateAvgPriceBO();
		String projectName = request.getParameter("projectName");
		SmUserVO sVo = (SmUserVO) session.getAttribute("LgUser");
			int districtList = sVo.getRegionId();
			List list = estateAvgPriceBO.queryEstateAvgPrice(page, projectName, districtList);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("project_id");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("项目名称", "projectName");
			tableProperty.addColumn("项目地址", "projectAdr");
			tableProperty.addColumn("企业名称", "compname");
			tableProperty.addColumn("查看", "查看", "doSearch", linkparam, "查看", null);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/pricemanage/EstateAvgPriceList";
	}catch (Exception e){
		e.printStackTrace();
		return ERROR_System;
	}
	}

	/**
	 * 功能描述：楼盘均价房屋列表
	 * @param request
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/gotoAvgHouseByIdList")
	public String gotoAvgHouseByIdList(HttpServletRequest request,Page page) {
		try{
			EstateAvgPriceBO estateAvgPriceBO = new EstateAvgPriceBO();
			String project_id = request.getParameter("project_id");
			long project_ID = 0;
			if(project_id != null && !"".equals(project_id)){
				project_ID = Long.parseLong(project_id);

			}
			List list = estateAvgPriceBO.queryHouse(project_ID, page);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("building_ID");
			linkparam.add("start_ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(false);
			tableProperty.addColumn("楼幢名称", "building_Name");
			tableProperty.addColumn("基准价格", "reference_Price");
			tableProperty.addColumn("浮动区间", "ratio");
			tableProperty.addColumn("查看", "修改", "doPriceUpdate", linkparam, "修改", null);
			tableProperty.addColumn("楼栋ID", "building_ID", true);
			tableProperty.addColumn("开盘ID", "start_ID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/pricemanage/EstateHouseList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：楼盘均价幅度修改明细
	 * @param request
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/priceUpdateInfo")
	public String priceUpdateInfo(HttpServletRequest request,HttpSession session,Page page) {
		try{
			EstateAvgPriceBO estateAvgPriceBO = new EstateAvgPriceBO();
			String building_ID = request.getParameter("building_ID");
			String start_ID = request.getParameter("start_ID");
			List<BuildingHouseVO> list = estateAvgPriceBO.priceUpdateList(Long.valueOf(start_ID), Long.valueOf(building_ID), page);
			for(BuildingHouseVO bh : list){
				request.setAttribute("ratio", bh.getRatio());
				request.setAttribute("reference_Price", bh.getReference_Price());
			}
			request.setAttribute("building_ID", building_ID);
			request.setAttribute("start_ID", start_ID);
			return "fhhouse/manage/pricemanage/EstateUpdate";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：楼盘均价幅度修改保存
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/savePrcie")
	@ResponseBody
	public JSONArray priceUpdate(HttpServletRequest request,HttpSession session) {

		EstateAvgPriceBO estateAvgPriceBO = new EstateAvgPriceBO();
		SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			String ratio = request.getParameter("ratio");
			String reference_Price = request.getParameter("reference_Price");
			String building_ID = request.getParameter("building_ID");
			String start_ID = request.getParameter("start_ID");
			int count = -1;
			count = estateAvgPriceBO.priceUpdate(reference_Price, ratio, uservo.getLoginName(), Long.parseLong(DateUtil.getSysDateYYYYMMDD()), Long.parseLong(DateUtil.getSysDateHHMMSS()), Long.valueOf(start_ID), Long.valueOf(building_ID));
		if(count > 0){
			map.put("result", "success");
				map.put("message", "均价幅度修改成功！");
		}else{
			map.put("result", "fail");
				map.put("message", "均价幅度修改失败！");
		}
	   json = JSONArray.fromObject(map);
	   return json;
    }catch (Exception e){
	e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "均价幅度修改失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：基准价格管理菜单
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoGuidePriceMg")
	public String gotoGuidePriceMg(HttpServletRequest request,HttpSession session) {
		try{
			return "fhhouse/manage/pricemanage/GuidePriceMg";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述:基准价格管理列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/gotoGuidePriceMgList")
	public String gotoGuidePriceMgList(HttpServletRequest request,HttpSession session,Page page) throws Exception {
		try{
			GuidePriceMgBO guidePriceMgBO = new GuidePriceMgBO();
			String projectName = request.getParameter("projectName");
			SmUserVO sVo = (SmUserVO) session.getAttribute("LgUser");
			int districtList = sVo.getRegionId();
			List list = guidePriceMgBO.queryGuidePrice(page, projectName, districtList);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("project_id");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("项目名称", "projectName");
			tableProperty.addColumn("项目地址", "projectAdr");
			tableProperty.addColumn("企业名称", "compname");
			tableProperty.addColumn("查看", "查看", "doSearch", linkparam, "查看", null);
			tableProperty.addColumn("项目ID", "project_id", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/pricemanage/GuidePriceMgList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：基准价格管理-楼栋列表
	 * @param request
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/gotoGuideHouseByIdList")
	public String gotoGuideHouseList(HttpServletRequest request,Page page) {
		try{
			GuidePriceMgBO guidePriceMgBO = new GuidePriceMgBO();
			String project_id = request.getParameter("project_id");

			long project_ID = 0;
			if(project_id != null && !"".equals(project_id)){
				project_ID = Long.parseLong(project_id);

			}
			if(page != null){
				if(page.getPageSize() == 10){
					page.setPageSize(100);
				}
			}

			List list = guidePriceMgBO.queryHouse(project_ID, page);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("building_ID");
			linkparam.add("project_id");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(false);
			tableProperty.addColumn("楼幢名称", "building_Name");
			tableProperty.addColumn("楼栋ID", "building_ID");
			tableProperty.addColumn("查看", "查看", "doPriceUpdate", linkparam, "查看", null);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("project_id", project_id);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/pricemanage/GuidePriceHouseList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：基准价格管理-房屋列表
	 * @param request
	 * @param page
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/GuideHouseUpdateByIdList")
	public String GuideHouseUpdateByIdList(HttpServletRequest request,Page page) throws Exception {
		try{
			GuidePriceMgBO guidePriceMgBO = new GuidePriceMgBO();
			String project_id = request.getParameter("project_id");
			String building_ID= request.getParameter("building_ID");
			request.setAttribute("project_id", project_id);
			request.setAttribute("building_ID", building_ID);
			long project_ID = 0;
			if(project_id != null && !"".equals(project_id)){
				project_ID = Long.parseLong(project_id);
			}
			long Building_ID = 0;
			if(building_ID != null && !"".equals(building_ID)){
				Building_ID = Long.parseLong(building_ID);
			}
			if(page != null){
				if(page.getPageSize() == 10){
					page.setPageSize(100);
				}
			}
			//System.out.println(project_ID);
			//System.out.println(Building_ID);
			List list = guidePriceMgBO.GuidePriceUpdateList(project_ID, Building_ID, project_ID, page);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("house_ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(false);
			tableProperty.addSelectControl("CheckBox", "findSelectID", linkparam, "");
			tableProperty.addColumn("房屋坐落", "building_Name");
			tableProperty.addColumn("室号", "room_Number");
			tableProperty.addColumn("基准价格", "nHouseAverageStr");
			tableProperty.addColumn("浮动区间上幅度", "nBandAverageStr");
			tableProperty.addColumn("浮动区间下幅度", "nBandAverageXiaStr");
			tableProperty.addColumn("项目ID", "project_ID", true);
			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/pricemanage/GuidePriceUpdate";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：基准价格管理-基准价幅度修改
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveGuidePrcie")
	@ResponseBody
	public JSONArray GuidePriceUpdate(HttpServletRequest request,HttpSession session) {

		GuidePriceMgBO guidePriceMgBO = new GuidePriceMgBO();
		GuidePriceLogBO logBO = new GuidePriceLogBO();
		JSONArray json = new JSONArray(); 
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			SmUserVO sVo = (SmUserVO) session.getAttribute("LgUser");
			String nHouseID = request.getParameter("compId");
			String nHouseAverage = request.getParameter("nHouseAverage");
			String nBandAverage = request.getParameter("nBandAverage");
			String nBandAverageXia = request.getParameter("nBandAverageXia");
			String project_id = request.getParameter("project_id");
			List<HouseBandAverageVO> priceList = new ArrayList<HouseBandAverageVO>();
			List<JGGLLogVO> logList = new ArrayList<JGGLLogVO>();
			JSONArray json1 = JSONArray.fromObject(nHouseID);
			for(int i = 0; i < json1.size(); i++){
				if("on".equals(json1.get(0))){
					json1.remove(0);
				}
				long houseId = Long.parseLong(String.valueOf(json1.get(i)));
				//删除原有对应的nhouseid 的记录	
				//guidePriceMgBO.GuidePriceUpdatedelete(Long.parseLong(String.valueOf(json1.get(i))));

				int count = -1;
				double NBandAverage = 0;
				if(nBandAverage != null && !"".equals(nBandAverage)){
					NBandAverage = Double.parseDouble(nBandAverage);
				}
				double NBandAverageXia = 0;
				if(nBandAverageXia != null && !"".equals(nBandAverageXia)){
					NBandAverageXia = Double.parseDouble(nBandAverageXia);
				}
				//新增记录	
				HouseBandAverageVO hbvo = new HouseBandAverageVO();
				hbvo.setNHouseID(houseId);
				hbvo.setNHouseAverage(Long.parseLong(nHouseAverage));
				hbvo.setNProjectID(Long.parseLong(project_id));
				hbvo.setNBandAverage(NBandAverage);
				hbvo.setNBandAverageXia(NBandAverageXia);
				
				priceList.add(hbvo);

				//日志记录
				ProjectVO projectVO = (ProjectVO) guidePriceMgBO.find(ProjectVO.class, Long.parseLong(project_id));
				JGGLLogVO logvo = new JGGLLogVO();
				logvo.setHouseID(houseId);
				logvo.setProjectID(Long.parseLong(project_id));
				logvo.setNhouseAverage(Long.parseLong(nHouseAverage));
				logvo.setNbandAverage(NBandAverage);
				logvo.setNbandAveragexia(NBandAverageXia);
				logvo.setSaddDate(DateUtil.getSysDateYYYYMMDD());
				logvo.setSendDate(DateUtil.getSysDateHHMMSS());
				logvo.setSprojectName(projectVO.getProjectName());
				String location = logBO.queryHouseLocation(houseId);
				logvo.setShouseLocation(location);
				logvo.setSaddUser(sVo.getLoginName());
				logList.add(logvo);
			}

			guidePriceMgBO.saveGuidePrice(priceList, logList);
			map.put("result", "success");
			map.put("message", "基准价幅度修改成功！");
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "基准价幅度修改失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：基准价格查看菜单
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoGuidePriceCk")
	public String gotoGuidePriceCk(HttpServletRequest request,HttpSession session) {
		try{
			return "fhhouse/manage/pricemanage/GuidePriceCk";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述:基准价格查看列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/gotoGuidePriceCkList")
	public String gotoGuidePriceCkList(HttpServletRequest request,HttpSession session,Page page) throws Exception {
		try{
			GuidePriceMgBO guidePriceMgBO = new GuidePriceMgBO();
			String projectName = request.getParameter("projectName");
			SmUserVO sVo = (SmUserVO) session.getAttribute("LgUser");
			int districtList = sVo.getRegionId();
			List list = guidePriceMgBO.queryGuidePrice(page, projectName, districtList);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("project_id");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("项目名称", "projectName");
			tableProperty.addColumn("项目地址", "projectAdr");
			tableProperty.addColumn("企业名称", "compname");
			tableProperty.addColumn("查看", "查看", "doSearch", linkparam, "查看", null);
			tableProperty.addColumn("项目ID", "project_id", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/pricemanage/GuidePriceCkList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：基准价格查看-楼栋列表
	 * @param request
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/gotoGuideCkHouseByIdList")
	public String gotoGuideCkHouseList(HttpServletRequest request,Page page) {
		try{
			GuidePriceMgBO guidePriceMgBO = new GuidePriceMgBO();
			String project_id = request.getParameter("project_id");

			long project_ID = 0;
			if(project_id != null && !"".equals(project_id)){
				project_ID = Long.parseLong(project_id);

			}
			List list = guidePriceMgBO.queryHouse(project_ID, page);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("building_ID");
			linkparam.add("project_id");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(false);
			tableProperty.addColumn("楼幢名称", "building_Name");
			tableProperty.addColumn("楼栋ID", "building_ID");
			tableProperty.addColumn("查看", "查看", "doPriceUpdate", linkparam, "查看", null);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/pricemanage/GuidePriceCkHouseList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：房屋列表基准价格查看-幅度查看
	 * @param request
	 * @param page
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/GuideHouseCkByIdList")
	public String GuideHouseUpdateByIdLists(HttpServletRequest request,Page page) throws Exception {
		try{
			GuidePriceMgBO guidePriceMgBO = new GuidePriceMgBO();
			String project_id = request.getParameter("project_id");
			String building_ID = request.getParameter("building_ID");

			long project_ID = 0;
			if(project_id != null && !"".equals(project_id)){
				project_ID = Long.parseLong(project_id);
			}
			long Building_ID = 0;
			if(building_ID != null && !"".equals(building_ID)){
				Building_ID = Long.parseLong(building_ID);
			}
			/*
			 * System.out.print(project_ID); System.out.print(Building_ID);
			 */
			List list = guidePriceMgBO.GuidePriceUpdateList(project_ID, Building_ID, project_ID, page);

			List<String> linkparam = new ArrayList<String>();
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(false);
			tableProperty.addColumn("房屋坐落", "building_Name");
			tableProperty.addColumn("室号", "room_Number");
			tableProperty.addColumn("基准价格", "nHouseAverage");
			tableProperty.addColumn("上幅度", "nBandAverage");
			tableProperty.addColumn("下幅度", "nBandAverageXia");
			tableProperty.addColumn("成交价格", "nactualPrices");
			tableProperty.addColumn("实际幅度", "nactualAmplitude");

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("project_id", project_id);
			request.setAttribute("building_ID", building_ID);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/pricemanage/GuidePriceUpdateCk";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/**
	 * 功能描述：低价签约监控菜单
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoLowPriceControl")
	public String gotoLowPriceControl(HttpServletRequest request,HttpSession session) {
		try{
			return "fhhouse/manage/pricemanage/LowPriceControl";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：低价签约监控列表
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoLowPriceControlList")
	public String gotoLowPriceControlList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			LowPriceControlBO lowPriceControlBO = new LowPriceControlBO();
			String projectName = request.getParameter("projectName");
			String buildingName = request.getParameter("buildingName");
			List list = lowPriceControlBO.queryLowPriceControl(page, projectName, buildingName);
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("项目名称", "projectName");
			tableProperty.addColumn("楼栋名称", "building_Name");
			tableProperty.addColumn("室号", "room_Number");
			tableProperty.addColumn("合同单价(元/米)", "nhouseAverage");
			tableProperty.addColumn("申报基准价(元/米)", "nhouseReference");
			tableProperty.addColumn("下幅度", "nbandAverage");
			tableProperty.addColumn("操作时间 ", "addDate");

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/pricemanage/LowPriceControlList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/**
	 * 功能描述：基准价格日志菜单
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoGuidePriceLog")
	public String gotoGuidePriceLog(HttpServletRequest request,HttpSession session) {
		try{
			return "fhhouse/manage/pricemanage/GuidePriceLog";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：系统日志查询列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoGuidePriceLogList")
	public String gotoGuidePriceLogList(HttpServletRequest request,HttpSession session,Page page) {
		String saddate = null;
		String sendate = null;
		try{
			GuidePriceLogBO guidePriceLogBO = new GuidePriceLogBO();
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			if(startDate != null && !"".equals(startDate)){
				String startHour = request.getParameter("startHour");
				if(startHour == null || "".equals(startHour)){
					startHour = "00";
				}
				String startMinute = request.getParameter("startMinute");
				if(startMinute == null || "".equals(startMinute)){
					startMinute = "00";
				}
				String startSecond = request.getParameter("startSecond");
				if(startSecond == null || "".equals(startSecond)){
					startSecond = "00";
				}
	
				saddate = startDate.replaceAll("-", "") + startHour + startMinute + startSecond;
			}
			if(endDate != null && !"".equals(endDate)){
				String endHour = request.getParameter("endHour");
				if(endHour == null || "".equals(endHour)){
					endHour = "00";
				}
				String endMinute = request.getParameter("endMinute");
				if(endMinute == null || "".equals(endMinute)){
					endMinute = "00";
				}
				String endSecond = request.getParameter("endSecond");
				if(endSecond == null || "".equals(endSecond)){
					endSecond = "00";
				}
				sendate = endDate.replaceAll("-", "") + endHour + endMinute + endSecond;
			}
			SmUserVO sVo = (SmUserVO) session.getAttribute("LgUser");
			int districtID = sVo.getRegionId();
			if(districtID == 2 || districtID == 3 || districtID == 5 || districtID == 13){
				districtID = 0;
			}
			
			List list = guidePriceLogBO.queryGuidePriceLog(page, saddate, sendate, districtID);

			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("项目名称", "projectName");
			tableProperty.addColumn("房屋座落", "shouseLocation");
			tableProperty.addColumn("基准价", "nHouseAverage");
			tableProperty.addColumn("浮动区间上幅度", "nBandAverage");
			tableProperty.addColumn("浮动区间下幅度", "nBandAverageXia");
			tableProperty.addColumn("操作人 ", "saddUser");

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);

			return "fhhouse/manage/pricemanage/GuidePriceLogList";

		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
}