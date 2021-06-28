package com.netcom.nkestate.fhhouse.project.action;

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
import com.netcom.nkestate.fhhouse.project.bo.HouseBO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseSellLogVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.vo.SmUserVO;

@Controller
@RequestMapping("/inner/house")
public class HouseAction extends BaseAction {

	static Logger logger = Logger.getLogger(HouseAction.class.getName());

	/**
	 * 功能描述：跳转到房屋管理
	 * @return
	 */
	@RequestMapping("/gotoHouseManage")
	public String gotoSearchStartUnit() {
		try{
			return "fhhouse/project/house/HouseQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：房屋管理列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/houseQueryList")
	public String gotoHouseQueryList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			HouseBO hbo = new HouseBO();
			String projectName = request.getParameter("projectName");
			String compCode = request.getParameter("compCode");
			String compName = request.getParameter("compName");

			request.setAttribute("compName", compName);
			request.setAttribute("projectName", projectName);
			request.setAttribute("compCode", compCode);

			SmUserVO sVo = (SmUserVO) session.getAttribute("LgUser");
			String districtList = this.getUserSqlDistricts(sVo.getRegionId());
			List<ProjectVO> list = hbo.findHouses(page, projectName, compCode, compName, districtList);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("project_id");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("项目名称", "projectname");
			tableProperty.addColumn("项目地址", "projectadr");
			tableProperty.addColumn("企业名称", "name");
			tableProperty.addColumn("状态", "status_dict_name");
			tableProperty.addColumn("查看", "查看", "lookStartUnit", linkparam, "查看", null);
			tableProperty.addColumn("项目ID", "project_id", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/project/house/HouseQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：查看开盘单元
	 * @param request
	 * @return
	 */
	@RequestMapping("/lookStartUnit")
	public String gotoStartUnitList(HttpServletRequest request) {
		try{
			String projectID = request.getParameter("projectID");
			request.setAttribute("projectID", projectID);
			return "fhhouse/project/house/StartQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：开盘单元一览
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/startUnitQueryList")
	public String gotoStartUnitQueryList(HttpServletRequest request,HttpSession session,Page page,long projectID) {
		try{
			HouseBO hbo = new HouseBO();
			String documentID = request.getParameter("documentID");
			String startCode = request.getParameter("startCode");
			request.setAttribute("projectID", projectID);
			request.setAttribute("documentID", documentID);
			request.setAttribute("startCode", startCode);

			SmUserVO sVo = (SmUserVO) session.getAttribute("LgUser");
			String districtList = this.getUserSqlDistricts(sVo.getRegionId());
			List<StartUnitVO> list = hbo.findStartUnits(page, String.valueOf(projectID), documentID, startCode, districtList);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("start_id");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("开盘编号", "START_CODE");
			tableProperty.addColumn("许可证编号", "PRESELL_DESC");
			tableProperty.addColumn("收件编号", "DOCUMENT_ID");
			tableProperty.addColumn("甲方信息", "NAME");
			tableProperty.addColumn("楼幢信息", "楼幢信息", "lookBuilding", linkparam, "楼幢信息", null);
			tableProperty.addColumn("开盘ID", "start_id", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/project/house/StartQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	@RequestMapping("/buildingInfo")
	public String searchBuildingInfo(HttpServletRequest request,HttpSession session,Page page,long startID) {
		try{
			HouseBO hbo = new HouseBO();
			request.setAttribute("startID", startID);

			List<BuildingVO> list = hbo.findBuilding(page, String.valueOf(startID));
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("building_id");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("楼幢名称", "building_name");
			tableProperty.addColumn("查看", "查看", "lookHouse", linkparam, "查看", null);
			tableProperty.addColumn("楼幢ID", "building_id", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/project/house/BuildingQueryList";
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
	@RequestMapping("/lookHouseList")
	public String houseViewList(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO sVo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(sVo.getRegionId());//获取用户操作区县列表
			String buildingId = request.getParameter("buildingID");
			HouseBO hBo = new HouseBO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("building_ID", "=", buildingId));
			params.add(new MetaFilter("districtID", "in", userDistrictList));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("room_Number", MetaOrder.ASC));
			List<HouseVO> list = hBo.search(HouseVO.class, params, orders, null);
			request.setAttribute("list", list);
			List<HouseVO> list1 = hBo.findFloor(buildingId);
			request.setAttribute("list1", list1);
			return "/fhhouse/project/house/HouseViewList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：关联房屋改变
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/changeStatus")
	@ResponseBody
	public JSONArray changeHouseStatus(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
		try{
			HouseBO hbo = new HouseBO();
			String selectVal = request.getParameter("selectVal");
			String notSelectVal = request.getParameter("notSelectVal");
			JSONArray selectList = JSONArray.fromObject(selectVal);
			JSONArray notSelectList = JSONArray.fromObject(notSelectVal);
			long count1 = -1;
			long count2 = -1;
			if(selectList != null && selectList.size() > 0)
				for(int i = 0; i < selectList.size(); i++){
					HouseVO hvo = (HouseVO) hbo.find(HouseVO.class, Long.parseLong(String.valueOf(selectList.get(i))));
					hvo.setManual_Status(FHConstant.MANUAL_STATUS_CANSELL);;
					hvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					hvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					hvo.setUpdPerson(String.valueOf(uservo.getUserId()));
					count1 = hbo.update(hvo);
					//记录操作日志 20190515 added by gcf 
					addHouseSaleLog(uservo.getUserId(), FHConstant.MANUAL_STATUS_CANSELL, hvo.getHouse_ID());
				}
			if(notSelectList != null && notSelectList.size() > 0)
				for(int i = 0; i < notSelectList.size(); i++){
					HouseVO hvo = (HouseVO) hbo.find(HouseVO.class, Long.parseLong(String.valueOf(notSelectList.get(i))));
					hvo.setManual_Status(FHConstant.MANUAL_STATUS_CANNOTSELL);
					hvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					hvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					hvo.setUpdPerson(String.valueOf(uservo.getUserId()));
					count2 = hbo.update(hvo);
					//记录操作日志 20190515 added by gcf 
					addHouseSaleLog(uservo.getUserId(), FHConstant.MANUAL_STATUS_CANNOTSELL, hvo.getHouse_ID());
			}

			if(count1 <= 0 && count2 <= 0){
				map.put("result", "fail");
				map.put("message", "房屋人工可售更新失败！");
			}else{
				map.put("result", "success");
				map.put("message", "房屋人工可售更新成功！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "房屋人工可售更新操作出错！");
			json = JSONArray.fromObject(map);
			return json;
		}
	}
	
	/**
	 * 功能描述：记录操作日志 20190515 added by gcf 
	 * @param userID
	 * @param type
	 * @param houseID
	 */
	private void addHouseSaleLog(long userID,int type,long houseID) {
		HouseBO hbo = new HouseBO();
		try {
			HouseSellLogVO logVO = new HouseSellLogVO();
			logVO.setHouseID(houseID);
			logVO.setOperateDate(DateUtil.getSysDate());
			logVO.setOperateType(type);
			logVO.setOperatorUserID(userID);
			hbo.add(logVO);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
