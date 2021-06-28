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
import com.netcom.nkestate.fhhouse.manage.bo.BulletinBO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.vo.BulletinVO;
import com.netcom.nkestate.system.vo.SmUserVO;

/**
 * 公告管理模块Action
 */
@Controller
@RequestMapping("/inner/bulletin")
public class BulletinAction extends BaseAction {

	static Logger logger = Logger.getLogger(BulletinAction.class.getName());

	/**
	 * 功能描述：显示公告列表信息
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/findBulletins")
	public String findBulletins(HttpServletRequest request,HttpSession session,Page page) {
		try{
			BulletinBO bulletinBO = new BulletinBO();
			SmUserVO smUserVO = (SmUserVO) session.getAttribute("LgUser");
			//System.out.println("id为====" + smUserVO.getRegionId());
			List<BulletinVO> list = bulletinBO.findBulletins(page, smUserVO.getRegionId());
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("ID");

			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("标题", "title");
			tableProperty.addColumn("修改时间", "UpdateTime", "Date", "yyyy-MM-dd HH:mm:ss", null);
			tableProperty.addColumn("查看", "查看", "showBulletin", linkparam, "编辑", null);
			tableProperty.addColumn("删除", "删除", "delBulletin", linkparam, "删除", null);
			tableProperty.addColumn("公告编号", "ID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/bulletin/BulletinList";
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return ERROR_System;
		}

	}

	/**
	 * 功能描述：显示公告信息
	 * @param request
	 * @param session
	 * @return
	 */

	@RequestMapping("/showBulletin")
	public String showBulletin(HttpServletRequest request,HttpSession session) {
		try{
			BulletinBO bulletinBO = new BulletinBO();
			long bID = Long.parseLong(request.getParameter("bID"));
			BulletinVO bulletinVO = new BulletinVO();
			bulletinVO.setID(bID);
			bulletinVO = (BulletinVO) bulletinBO.find(bulletinVO);
			String cmd = "update";
			if(bulletinVO == null){
				cmd = "insert";
			}
			request.setAttribute("cmd", cmd);
			request.setAttribute("bID", bID);
			request.setAttribute("bulletinVO", bulletinVO);
			return "fhhouse/manage/bulletin/BulletinEdit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}

	}

	/**
	 * 功能描述：跳转到新增公告页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/add")
	public String addBulletin(HttpServletRequest request,HttpSession session) {
			String cmd = "insert";
			request.setAttribute("cmd", cmd);
		try{
			return "fhhouse/manage/bulletin/BulletinEdit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}

	}

	/**
	 * 功能描述：添加或者修改公告
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public JSONArray saveOrUpdateBulletin(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map=new HashMap<String , Object>();
		String msg="";
		BulletinBO bulletinBO = new BulletinBO();
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String cmd = request.getParameter("cmd");
			String title = request.getParameter("title");
			String s = request.getParameter("status");
			//System.out.println(s);
			int status = -1;
			if(s != null){
				status = 1;
			}else{
				status = 0;
			}
			String content = request.getParameter("content");
			if(title == null || "".equals(title.trim())){
				msg = "请输入标题";
			}
			if(content == null || "".equals(content.trim())){
				msg = "请输入内容";
			}
			if("".equals(msg)){
				long count = 0;
				BulletinVO bvo = new BulletinVO();
				bvo.setTitle(title);
				bvo.setStatus(status);
				bvo.setContent(content);
				bvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				bvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				bvo.setUpdPerson(vo.getLoginName());
				if("insert".equals(cmd)){
					//新增记录
					bvo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					bvo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					bvo.setCrePerson(vo.getLoginName());
					bvo.setDistrictID(vo.getRegionId());
					count = bulletinBO.add(bvo);
				}else{
					//更新记录
					long bID = Long.parseLong(request.getParameter("bID"));
					bvo.setID(bID);
					count = bulletinBO.update(bvo);
				}
				if(count > 0){
					map.put("result", "success");
					map.put("message", "公告更新成功！");
				}else{
					map.put("result", "fail");
					map.put("message", "未更新公告记录！");
				}
			}else{
				map.put("result", "fail");
				map.put("message", msg);
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "公告更新失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：删除公告
	 * @param request
	 * @return
	 */
	@RequestMapping("/delBulletin")
	@ResponseBody
	public JSONArray delBulletin(HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		BulletinVO vo=new BulletinVO();
		String bID = request.getParameter("bID");
		int count = 0;
		vo.setID(Long.parseLong(bID));
		BulletinBO bulletinBO = new BulletinBO();
		try{
			vo = (BulletinVO) bulletinBO.find(vo);
			count = bulletinBO.delete(vo);
			if(count > 0){
				map.put("result", "success");
				map.put("message", "公告删除成功！");
			}else{
				map.put("result", "fail");
				map.put("message", "未删除公告记录！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "公告删除失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}
		

}
