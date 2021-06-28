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
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.manage.bo.LimitBuyHouseManageBO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.vo.SmUserVO;

/**
 * 限购房屋管理模块Action
 */
@Controller
@RequestMapping(value = "/inner/limitbuyhousemanage")
public class LimitBuyHouseManageAction extends BaseAction{


	@RequestMapping(value = "/gotoLimitBuyProjectList")
	public String gotoLimitBuyProject(HttpServletRequest request,HttpSession session) {
		try{
			return "fhhouse/manage/limitbuyhouse/LimitBuyProject";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：限购房屋管理-查询项目列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/limitBuyProjectList")
	public String gotoLimitBuyProjectList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			LimitBuyHouseManageBO lbhmBo = new LimitBuyHouseManageBO();
			String projectName = request.getParameter("projectName");
			SmUserVO smUserVO = (SmUserVO) session.getAttribute("LgUser");
			List<Object> districtlist = this.getUserDistricts(smUserVO.getRegionId());//获取用户操作区县列表

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "in", districtlist));
			if(projectName != null && !"".equals(projectName)){
				params.add(new MetaFilter("projectName", "like", "%" + projectName + "%"));
			}
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("project_ID", MetaOrder.DESC));
			
			List<ProjectVO> list = lbhmBo.search(ProjectVO.class, params, orders, page);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("project_ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(false);
			tableProperty.addColumn("项目名称", "projectName");
			tableProperty.addColumn("查看", "查看", "doSearch", linkparam, "查看", null);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			request.setAttribute("projectName", projectName);
			return "fhhouse/manage/limitbuyhouse/LimitBuyProjectList";

		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
		
	}

	/**
	 * 功能描述：限购房屋管理-查询楼栋列表
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoLimitBuyBuildingList")
	public String gotoLimitBuyBuildingList(HttpServletRequest request,Page page) {
		try{
			LimitBuyHouseManageBO lbhmBo = new LimitBuyHouseManageBO();
			String project_id = request.getParameter("project_id");
			long project_ID = 0;
			if(project_id != null && !"".equals(project_id)){
				project_ID = Long.parseLong(project_id);

			}
			List<BuildingVO> list = lbhmBo.queryBuilding(project_ID, page);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("building_ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(false);
			tableProperty.addColumn("楼幢名称", "building_Name");
			tableProperty.addColumn("查看", "查看", "doSearch", linkparam, "查看", null);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/limitbuyhouse/LimitBuyBuildingList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：限购房屋管理-查询房屋列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoLimitBuyHouseList")
	public String gotoLimitBuyHouseList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			LimitBuyHouseManageBO lbhmBo = new LimitBuyHouseManageBO();
			String building_id = request.getParameter("building_id");
			request.setAttribute("building_id", building_id);
			long building_ID = 0;
			if(building_id != null && !"".equals(building_id)){
				building_ID = Long.parseLong(building_id);
			}
			List list = lbhmBo.queryHouse(building_ID, page);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("house_ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(false);
			tableProperty.addColumn("房屋坐落", "room");
			tableProperty.addColumn("房屋状态", "noXgState2");
			tableProperty.addColumn("修改状态", "修改状态", "doModify", linkparam, "取消/执行", null);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			//2018.6.1现场暂时关闭该功能
			//return "fhhouse/manage/limitbuyhouse/LimitBuyHouseList";
			return "/Construction";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：取消限购令
	 * @return json
	 */
	@RequestMapping(value = "/modifyLimitBuyHouse")
	@ResponseBody
	public JSONArray modifyLimitBuyHouse(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			LimitBuyHouseManageBO lbhmBo = new LimitBuyHouseManageBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String house_ID = request.getParameter("house_id");

			if(house_ID != null && !"".equals(house_ID)){
				HouseVO hVo = new HouseVO();
				hVo.setHouse_ID(Long.parseLong(house_ID));
				if( ((HouseVO) lbhmBo.find(HouseVO.class, Long.parseLong(house_ID))).getNoXgState() == FHConstant.HOUSE_NOXGSTATE_NO){
					hVo.setNoXgState(FHConstant.HOUSE_NOXGSTATE_YES);
				}else{
					hVo.setNoXgState(FHConstant.HOUSE_NOXGSTATE_NO);
				}
				hVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				hVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				hVo.setUpdPerson(vo.getLoginName());
				long i = 0;
				i = lbhmBo.update(hVo);
				if(i > 0){
					map.put("result", "success");
					map.put("message", "限购令状态修改成功！");
				}else{
					map.put("result", "fail");
					map.put("message", "限购令状态修改失败！");
				}
			}else{
				map.put("result", "fail");
				map.put("message", "限购令状态修改出错！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "限购令状态修改出错！");
			json = JSONArray.fromObject(map);
			return json;
		}
	}
}
