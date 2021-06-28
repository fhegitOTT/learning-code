package com.netcom.nkestate.fhhouse.project.action;

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
import com.netcom.nkestate.fhhouse.company.vo.EnterpriseQualifyVO;
import com.netcom.nkestate.fhhouse.project.bo.StartUnitBO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.project.vo.StartCompVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitReasonVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.util.StringUtil;
import com.netcom.nkestate.system.vo.SmUserVO;

@Controller
@RequestMapping("/inner/startunit")
public class StartUnitAction extends BaseAction {

	static Logger logger = Logger.getLogger(StartUnitAction.class.getName());

	/**
	 * 功能描述：跳转到开盘管理
	 * @return
	 */
	@RequestMapping("/gotoStartUnitManage")
	public String gotoSearchStartUnit() {
		try{

			return "fhhouse/project/startUnit/StartUnitQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：开盘管理列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/startUnitQueryList")
	public String startUnitQueryList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			StartUnitBO suBO = new StartUnitBO();
			String projectName = request.getParameter("projectName");
			String presellDeSc = request.getParameter("presellDeSc");
			String issalable = request.getParameter("issalable");
			String documentID = request.getParameter("documentID");
			String startCode = request.getParameter("startCode");

			request.setAttribute("projectName", projectName);
			request.setAttribute("presellDeSc", presellDeSc);
			request.setAttribute("issalable", issalable);
			request.setAttribute("documentID", documentID);
			request.setAttribute("startCode", startCode);

			SmUserVO sVo = (SmUserVO) session.getAttribute("LgUser");
			String districtList = this.getUserSqlDistricts(sVo.getRegionId());
			List<StartUnitVO> list = suBO.findStartUnits(page, presellDeSc, documentID, startCode, projectName, issalable, districtList);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("START_ID");
			linkparam.add("presell_desc");
			linkparam.add("project_id");
			List<String> linkparam1 = new ArrayList<String>();
			linkparam1.add("START_ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addSelectControl("CheckBox", "selStartID", linkparam1, "");
			tableProperty.addColumn("开盘编号", "START_CODE", "doStartTime", linkparam1);
			tableProperty.addColumn("许可证编号", "PRESELL_DESC", "doStartUnitInfo", linkparam);
			tableProperty.addColumn("最初开盘时间", "InitialTimeStr");
			tableProperty.addColumn("开盘时间", "StartTimeStr");
			tableProperty.addColumn("状态", "ISSALABLE_dict_name");
			tableProperty.addColumn("历史记录", "reason", "openStartUnitReason", linkparam);
			tableProperty.addColumn("开盘ID", "START_ID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/project/startUnit/StartUnitQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：开盘单元信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/startUnitBasicInfo")
	public String startUnitBasicInfo(HttpServletRequest request,HttpSession session) {
		try{
			StartUnitBO suBO = new StartUnitBO();
			String startID = request.getParameter("startID");
			String presellDesc = request.getParameter("presellDesc");
			String projectID = request.getParameter("projectID");
			String desc = URLDecoder.decode(presellDesc, "utf-8"); //将中文参数转码
			StartUnitVO suvo = (StartUnitVO) suBO.find(StartUnitVO.class, Long.parseLong(startID));
			ProjectVO pvo = (ProjectVO) suBO.find(ProjectVO.class, Long.parseLong(projectID));
			StartCompVO scvo = (StartCompVO) suBO.find(StartCompVO.class, Long.parseLong(startID));
			EnterpriseQualifyVO eqvo = (EnterpriseQualifyVO) suBO.find(EnterpriseQualifyVO.class, scvo.getComp_ID());
			request.setAttribute("suvo", suvo);
			request.setAttribute("desc", desc);
			request.setAttribute("pvo", pvo);
			request.setAttribute("eqvo", eqvo);
			return "fhhouse/project/startUnit/StartUnitInfo";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}

	}
	
	/**
	 * 功能描述：开盘单元信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/openOperateReason")
	public String openOperateReason(HttpServletRequest request,HttpSession session) {
		try{
			String startID = request.getParameter("startID");
			request.setAttribute("startID", startID);
			return "fhhouse/project/startUnit/StartUnitReason";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
		
	}
	
	/**
	 * 功能描述：开盘单元信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/openOperateReasonList")
	public String openOperateReasonList(HttpServletRequest request,HttpSession session) {
		try{
			String startID = request.getParameter("startID");
			request.setAttribute("startID", startID);
			StartUnitBO suBO = new StartUnitBO();

			SmUserVO sVo = (SmUserVO) session.getAttribute("LgUser");
			List<StartUnitReasonVO> list = suBO.findOperateReasonList(startID);
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("操作人", "displayname");
			tableProperty.addColumn("操作时间", "operateDateStr");
			tableProperty.addColumn("操作原因", "reason");
			tableProperty.addColumn("操作类型", "statusStr");

			String htmlView = HtmlTableUtil.createHtmlTable(tableProperty, list);
			request.setAttribute("htmlView", htmlView);
			
			return "fhhouse/project/startUnit/StartUnitReasonList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
		
	}
	
	
	/**
	 * 功能描述：保存开盘操作原因
	 * @param request
	 * @return
	 */
	@RequestMapping("/doSaveStartUnitReason")
	@ResponseBody
	public JSONArray doSaveStartUnitReason(HttpServletRequest request,HttpSession session) {
		StartUnitBO suBO = new StartUnitBO();
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
		String startIDs = request.getParameter("startIDs");
		String reason = request.getParameter("reason");
		String status = request.getParameter("status");
		try {
			JSONArray jsonArr = JSONArray.fromObject(startIDs);
			List<StartUnitReasonVO> list = new ArrayList<StartUnitReasonVO>();
			for(int i = 0; i < jsonArr.size(); i++){
				if(!"on".equals(jsonArr.get(i))){
					StartUnitReasonVO vo = new StartUnitReasonVO();
					vo.setStartID(Long.parseLong(String.valueOf(jsonArr.get(i))));
					vo.setOperateDate(DateUtil.getSysDate());
					vo.setOperateUser(uservo.getUserId());
					vo.setReason(reason);
					vo.setStatus(Integer.parseInt(status));
					list.add(vo);
				}
			}
			
			long count = -1;
			count = suBO.addBatch(list);
			if (count > 0) {
				map.put("result", "success");
				map.put("message", "保存成功！");
			} else {
				map.put("result", "fail");
				map.put("message", "保存失败！");
			}
			json = JSONArray.fromObject(map);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "保存失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：开盘时间设定
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/setUpStartTime")
	public String setUpStartTime(HttpServletRequest request,HttpSession session) {
		try{
			StartUnitBO suBO = new StartUnitBO();
			String startID = request.getParameter("startID");
			StartUnitVO suvo = (StartUnitVO) suBO.find(StartUnitVO.class, Long.parseLong(startID));

			//String startDate = StringUtil.getFullwidthStr(String.valueOf(suvo.getInitial_Date()), 6);
			String startTime = StringUtil.getFullwidthStr(String.valueOf(suvo.getStart_Time()), 6);
			if(suvo.getStart_Date() != null && suvo.getStart_Time() != null){
				request.setAttribute("startHour", startTime.substring(0, 2));
				request.setAttribute("startMinute", startTime.substring(2, 4));
				request.setAttribute("startSecond", startTime.substring(4, 6));
				request.setAttribute("startDate", suvo.getStartDate2());
			}
			request.setAttribute("startID", startID);
			return "fhhouse/project/startUnit/StartUnitTime";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}

	}

	/**
	 * 功能描述：保存开盘时间
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveStartTime")
	@ResponseBody
	public JSONArray saveStartTime(HttpServletRequest request,HttpSession session) {
		StartUnitBO suBO = new StartUnitBO();
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
		String startID = request.getParameter("startID");
		String startDate = request.getParameter("startDate").replaceAll("-", "");
		String startTime = request.getParameter("startHour") + request.getParameter("startMinute") + request.getParameter("startSecond");
		try{
			String a = startDate + startTime;
			String b = DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS();
			if(Long.parseLong(a) < Long.parseLong(b)){
				map.put("result", "fail");
				map.put("message", "开盘时间设定不能小于当前时间！");
			}else{
				StartUnitVO vo = new StartUnitVO();
				vo.setStart_ID(Long.parseLong(startID));
				vo = (StartUnitVO) suBO.find(vo);
				/*
				 * if(vo.getInitial_Date()==null && vo.getInitial_Time()==null){ vo.setInitial_Date(startDate); vo.setInitial_Time(startTime); }
				 */
				vo.setStart_Date(startDate);
				vo.setStart_Time(startTime);
				vo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				vo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				vo.setUpdPerson(String.valueOf(uservo.getUserId()));
				long count = -1;
				count = suBO.update(vo);
				if(count > 0){
					map.put("result", "success");
					map.put("message", "开盘时间设定成功！");
				}else{
					map.put("result", "fail");
					map.put("message", "开盘时间设定失败！");
				}
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "开盘时间设定失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：开盘提交
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/doSubmit")
	public String doSubmit(HttpServletRequest request,HttpSession session) {
		try{
			StartUnitBO suBO = new StartUnitBO();
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String startIds = request.getParameter("startIds");
			String status = request.getParameter("status");
			JSONArray json = JSONArray.fromObject(startIds);
			for(int i = 0; i < json.size(); i++){
				if("on".equals(json.get(0))){
					json.remove(0);
				}
				//System.out.println(json.get(i));
				StartUnitVO svo = (StartUnitVO) suBO.find(StartUnitVO.class, Long.parseLong(String.valueOf(json.get(i))));
				if("0".equals(status)){//立即开盘
					svo.setIsSalable(1);
					if(svo.getInitial_Date() == null && svo.getInitial_Time() == null){
						svo.setInitial_Date(DateUtil.getSysDateYYYYMMDD());
						svo.setInitial_Time(DateUtil.getSysDateHHMMSS());
					}
					svo.setStart_Date(DateUtil.getSysDateYYYYMMDD());
					svo.setStart_Time(DateUtil.getSysDateHHMMSS());
				}
				if("1".equals(status)){//不开盘
					svo.setIsSalable(0);
					//svo.setInitial_Date(DateUtil.getSysDateYYYYMMDD());
					//svo.setInitial_Time(DateUtil.getSysDateHHMMSS());
					svo.setStart_Date("");
					svo.setStart_Time("");
				}
				if("2".equals(status)){//暂停销售
					svo.setIsSalable(2);
				}
				svo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				svo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				svo.setUpdPerson(String.valueOf(uservo.getUserId()));
				long count = suBO.update(svo);
				if(count <= 0){
					throw new Exception("更新开盘单元失败！");
				}
			}
			return "redirect:/inner/startunit/gotoStartUnitManage";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}

	}

}
