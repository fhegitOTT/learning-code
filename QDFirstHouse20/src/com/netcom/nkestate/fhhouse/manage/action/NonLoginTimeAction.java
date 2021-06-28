package com.netcom.nkestate.fhhouse.manage.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.netcom.nkestate.fhhouse.manage.bo.NonLoginTimeBO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.util.StringUtil;
import com.netcom.nkestate.system.vo.NonLoginTimeVO;
import com.netcom.nkestate.system.vo.SmUserVO;

/**
 * 系统管理 -登录设置Action
 */
@Controller
@RequestMapping("/inner/nonLoginTime")
public class NonLoginTimeAction extends BaseAction {

	/**
	 * 功能描述：显示非登录时间设置一览
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoNonLoginTimeList")
	public String gotoNonLoginTimeList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			NonLoginTimeBO nonLoginTimeBO = new NonLoginTimeBO();
			NonLoginTimeVO nltVo = new NonLoginTimeVO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("type", "asc"));
			orders.add(new MetaOrder("id", "desc"));
			List<NonLoginTimeVO> list = nonLoginTimeBO.search(NonLoginTimeVO.class, params, orders, page);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("id");

			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("标题", "title", "doModify", linkparam, null, null);
			tableProperty.addColumn("开始时间", "startDateTime", null);
			tableProperty.addColumn("结束时间", "endDateTIme", null);
			tableProperty.addColumn("类型", "type_dict_name");
			tableProperty.addColumn("删除", "删除", "doDelete", linkparam, "删除", null);
			tableProperty.addColumn("编号", "Id", true);
			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/nonlogintimeset/NonLoginTimeList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：登录设置删除
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteNonLoginTime")
	@ResponseBody
	public JSONArray deleteNonLoginTime(HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			NonLoginTimeBO nonLoginTimeBO = new NonLoginTimeBO();
			long id = Long.parseLong(request.getParameter("id"));
			NonLoginTimeVO nltVo = (NonLoginTimeVO) nonLoginTimeBO.find(NonLoginTimeVO.class, id);
			if(nltVo != null && nltVo.getType() != FHConstant.LOGIN_TIME_TYPE_EVERYDAY){
				int count = nonLoginTimeBO.delete(nltVo);
				if(count == 1){
					map.put("result", "success");
					map.put("message", nltVo.getTitle() + "删除成功！");
				}else{
					map.put("result", "fail");
					map.put("message", nltVo.getTitle() + "删除失败！");
				}
			}else{
				map.put("result", "fail");
				map.put("message", "不能删除该条记录！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "登录设置删除失败");
			json = JSONArray.fromObject(map);
			return json;
		}

	}
	/**
	 * 功能描述：转到登录设置新增
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotoNonLoginTimeAdd")
	public String gotoNonLoginTimeAdd(HttpServletRequest request) {
		try{
			return "fhhouse/manage/nonlogintimeset/NonLoginTimeAdd";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：转到登录设置修改
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotoNonLoginTimeModify")
	public String gotoNonLoginTimeModify(HttpServletRequest request) {
		try{
			NonLoginTimeBO nonLoginTimeBO = new NonLoginTimeBO();
			long id = Long.parseLong(request.getParameter("id"));
			NonLoginTimeVO nltVo = new NonLoginTimeVO();
			nltVo.setId(id);
			nltVo = (NonLoginTimeVO) nonLoginTimeBO.find(nltVo);

			int len = 6;
			String startTime = StringUtil.getFullwidthStr(String.valueOf(nltVo.getStartTime()), len);
			String endTime = StringUtil.getFullwidthStr(String.valueOf(nltVo.getEndTime()), len);

			request.setAttribute("startHour", startTime.substring(0, 2));
			request.setAttribute("startMinute", startTime.substring(2, 4));
			request.setAttribute("startSecond", startTime.substring(4, 6));
			request.setAttribute("endHour", endTime.substring(0, 2));
			request.setAttribute("endMinute", endTime.substring(2, 4));
			request.setAttribute("endSecond", endTime.substring(4, 6));
			request.setAttribute("startDate", nltVo.getStartDate2());
			request.setAttribute("endDate", nltVo.getEndDate2());
			request.setAttribute("nltVo", nltVo);
			return "fhhouse/manage/nonlogintimeset/NonLoginTimeAdd";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：登录设置保存
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveNonLoginTime")
	@ResponseBody
	public JSONArray saveNonLoginTime(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			NonLoginTimeBO nonLoginTimeBO = new NonLoginTimeBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			NonLoginTimeVO nltVo = new NonLoginTimeVO();
			String Id = request.getParameter("id");
			String title = request.getParameter("title");
			String startDate = request.getParameter("startDate").replaceAll("-", "");
			if(startDate == ""){
				startDate = StringUtil.getFullwidthStr(startDate, 8);
			}
			long startTime = Long.parseLong(request.getParameter("startHour") + request.getParameter("startMinute") + request.getParameter("startSecond"));
			String endDate = request.getParameter("endDate").replaceAll("-", "");
			if(endDate == ""){
				endDate = StringUtil.getFullwidthStr(endDate, 8);
			}
			long endTime = Long.parseLong(request.getParameter("endHour") + request.getParameter("endMinute") + request.getParameter("endSecond"));
			long cacheTime = Long.parseLong(request.getParameter("cacheTime"));
			long type = Long.parseLong(request.getParameter("typename"));

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("TYPE", "=", FHConstant.LOGIN_TIME_TYPE_EVERYDAY));
			List list = nonLoginTimeBO.search(NonLoginTimeVO.class, params);
			if(type == FHConstant.LOGIN_TIME_TYPE_EVERYDAY && list != null && list.size() > 0){
				map.put("result", "fail");
				map.put("message", "每天设置已存在，不能新增！");
			}else{

				boolean falg = this.compareTime(Id, type, startDate, endDate, startTime, endTime);

				if(falg == true){

					nltVo.setTitle(title);
					if(type == FHConstant.LOGIN_TIME_TYPE_EVERYDAY){
						nltVo.setStartDate("");
						nltVo.setEndDate("");
					}else{
						nltVo.setStartDate(startDate);
						nltVo.setEndDate(endDate);
					}
					nltVo.setStartTime(startTime);
					nltVo.setEndTime(endTime);
					nltVo.setCacheTime(cacheTime);
					nltVo.setType(type);
					nltVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					nltVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					nltVo.setUpdPerson(vo.getLoginName());


					if(Id != null && !Id.equals("")){
						long id = Long.parseLong(Id);
						nltVo.setId(id);

						long count = nonLoginTimeBO.update(nltVo);
						if(count == 1){
							map.put("result", "success");
							map.put("message", title + "更新成功！");
						}else{
							map.put("result", "fail");
							map.put("message", title + "更新失败！");
						}
					}else{
						nltVo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
						nltVo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
						nltVo.setCrePerson(vo.getLoginName());
						long count = nonLoginTimeBO.add(nltVo);
						if(count > 0){
							map.put("result", "success");
							map.put("message", title + "新增成功！");
						}else{
							map.put("result", "fail");
							map.put("message", title + "新增失败！");
						}
					}
				}else{
					map.put("result", "fail");
					map.put("message", "时间有交叉，不能保存该设置！");
				}
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
	 * 功能描述：判断输入时间是否有交叉
	 * @param id
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean compareTime(String id,long type,String startDate,String endDate,long startTime,long endTime) {
		try{
			NonLoginTimeBO nonLoginTimeBO = new NonLoginTimeBO();
			int len = 6;
			String curStartTime = startDate + StringUtil.getFullwidthStr(String.valueOf(startTime), len);
			String curEndTime = endDate + StringUtil.getFullwidthStr(String.valueOf(endTime), len);
			Date curStart = DateUtil.parseDateTime2(curStartTime.toString());
			Date curEnd = DateUtil.parseDateTime2(curEndTime.toString());
			if(curStart.compareTo(curEnd)<0){
				if(type == FHConstant.LOGIN_TIME_TYPE_ONLYONCE){
					//判断时间是否交叉
					List<MetaFilter> params = new ArrayList<MetaFilter>();
					params.add(new MetaFilter("TYPE", "=", new BigDecimal(FHConstant.LOGIN_TIME_TYPE_ONLYONCE)));
					List list = nonLoginTimeBO.search(NonLoginTimeVO.class, params);
					if(list != null && list.size() > 0){
						Iterator it = list.iterator();
						while (it.hasNext()){
							NonLoginTimeVO nltVo = (NonLoginTimeVO) it.next();
							String strStartTime = nltVo.getStartDate() + StringUtil.getFullwidthStr(String.valueOf(nltVo.getStartTime()), len);
							String strEndTime = nltVo.getEndDate() + StringUtil.getFullwidthStr(String.valueOf(nltVo.getEndTime()), len);
							Date bcStart = DateUtil.parseDateTime2(strStartTime);
							Date bcEnd = DateUtil.parseDateTime2(strEndTime);
							//如果当前结束时间小于数据库中的开始时间或者当前开始时间大于数据库中的结束时间，则认为不交叉。
							if(!id.equals(String.valueOf(nltVo.getId()))){
								if(curStart.compareTo(bcEnd) > 0 || curEnd.compareTo(bcStart) < 0){
									continue;
								}else{
									return false;
								}
							}else{
								continue;
							}
						}
					}
					return true;
				}else{
					return true;
				}
			}else{
				return false;
			}
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
