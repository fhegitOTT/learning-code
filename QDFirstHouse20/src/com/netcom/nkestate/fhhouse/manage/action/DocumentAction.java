package com.netcom.nkestate.fhhouse.manage.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.fhhouse.manage.bo.DocumentBO;
import com.netcom.nkestate.fhhouse.manage.vo.DocumentCommitVO;
import com.netcom.nkestate.fhhouse.manage.vo.DocumentHistoryVO;
import com.netcom.nkestate.fhhouse.manage.vo.DocumentVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.bo.SystemBO;
import com.netcom.nkestate.system.vo.SmUserVO;
import com.netcom.nkestate.system.vo.UserinfoVO;

/**
 * 收件管理模块Action
 */
@Controller
@RequestMapping("/inner/document")
public class DocumentAction extends BaseAction {

	static Logger logger = Logger.getLogger(DocumentAction.class.getName());

	/**
	 * 功能描述：跳转到收件录入页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/documentCreate")
	public String gotoAddDocument(HttpServletRequest request,HttpSession session) {
		SmUserVO smUserVO = (SmUserVO) session.getAttribute("LgUser");
		String date = DateUtil.parseDateTime3(DateUtil.getSysDateYYYYMMDD()); //收件日期   将字符串日期转化为yyyy-mm-dd格式
		StringBuffer sb = new StringBuffer(date);
		int userDistrictID = smUserVO.getRegionId(); //获取用户所属区县编号
		List<Object> list = this.getUserDistricts(userDistrictID);//获取用户操作区县列表
		try{
			String cmd = "add";
			request.setAttribute("smUserVO", smUserVO);
			request.setAttribute("date", date);
			request.setAttribute("cmd", cmd);
			request.setAttribute("list", list);
			request.setAttribute("districtID", String.valueOf(userDistrictID));
			return "fhhouse/manage/document/DocumentEdit";
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return ERROR_System;
		}

	}

	/**
	 * 功能描述：跳转到收件编辑页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/documentEdit")
	public String gotoEditDocument(HttpServletRequest request,HttpSession session) {
		SmUserVO smUserVO = (SmUserVO) session.getAttribute("LgUser");
		String date = DateUtil.parseDateTime3(DateUtil.getSysDateYYYYMMDD()); //收件日期   将字符串日期转化为yyyy-mm-dd格式
		int userDistrictID = smUserVO.getRegionId();
		List<Object> list = this.getUserDistricts(userDistrictID);//获取用户操作区县列表

		String documentId = request.getParameter("document_Id");
		String cmd = request.getParameter("cmd");
		DocumentBO documentBO = new DocumentBO();
		DocumentVO dvo = new DocumentVO();
		DocumentCommitVO dcvo = new DocumentCommitVO();
		DocumentHistoryVO dhvo = new DocumentHistoryVO();
		try{
			dvo.setDocument_Id(Long.parseLong(documentId));
			dvo = (DocumentVO) documentBO.find(dvo);
			dcvo.setDocument_ID(Long.parseLong(documentId));
			dcvo = (DocumentCommitVO) documentBO.find(dcvo);
			dhvo.setDocument_ID(Long.parseLong(documentId));
			dhvo = (DocumentHistoryVO) documentBO.find(dhvo);
			//String cmd = "edit";
			request.setAttribute("documentId", documentId);
			request.setAttribute("smUserVO", smUserVO);
			request.setAttribute("date", date);
			request.setAttribute("list", list);
			request.setAttribute("districtID", String.valueOf(userDistrictID));
			request.setAttribute("dvo", dvo);
			request.setAttribute("dcvo", dcvo);
			request.setAttribute("dhvo", dhvo);
			request.setAttribute("cmd", cmd);
			return "fhhouse/manage/document/DocumentEdit";
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return ERROR_System;
		}

	}

	/*
	 * 跳转到查询列表页面
	 */
	@RequestMapping("/docList")
	public String gotoSearchDocument(HttpServletRequest request) {
		try{
			DocumentBO documentBO = new DocumentBO();
			List<SmUserVO> list1 = documentBO.findSmUsers();
			List<UserinfoVO> list2 = documentBO.findUseinfos();
			request.setAttribute("list1", list1);
			request.setAttribute("list2", list2);
			return "fhhouse/manage/document/DocumentQuery";
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：收件录入保存和收件修改
	 * @param request
	 * @param session
	 * @param docVO
	 * @return
	 */
	@RequestMapping("/doDocSave")
	public String saveOrUpdateDocument(HttpServletRequest request,HttpSession session,DocumentVO docVO,DocumentCommitVO doComVO,DocumentHistoryVO doHisVO) {
		try{
			DocumentBO documentBO = new DocumentBO();
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String cmd = request.getParameter("cmd"); //add:收件录入   edit：收件修改

			docVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			docVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			docVO.setUpdPerson(uservo.getLoginName());
			doComVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			doComVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			doComVO.setUpdPerson(uservo.getLoginName());
			doHisVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			doHisVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			doHisVO.setUpdPerson(uservo.getLoginName());
			if("add".equals(cmd)){
				SystemBO bo = new SystemBO();
				//docVO.setDistrictid(Integer.parseInt(request.getParameter("proj_Loca_Region")));
				doHisVO.setUserID(uservo.getUserId());

				String sequence = bo.getSequence("DOCUMENTID");
				docVO.setDocument_Id(Long.parseLong(sequence));
				doComVO.setDocument_ID(Long.parseLong(sequence));
				doHisVO.setDocument_ID(Long.parseLong(sequence));
				docVO.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				docVO.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				docVO.setCrePerson(uservo.getLoginName());
				doComVO.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				doComVO.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				doComVO.setCrePerson(uservo.getLoginName());
				doHisVO.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				doHisVO.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				doHisVO.setCrePerson(uservo.getLoginName());

				long count1 = documentBO.add(docVO);
				long count2 = documentBO.add(doComVO);
				long count3 = documentBO.add(doHisVO);
				if(count1 <= 0 || count2 <= 0 || count3 <= 0){
					throw new Exception("收件录入失败！");
				}
				//docVO.setDocument_Id(count);
				request.setAttribute("sequence", sequence);
				return "fhhouse/manage/document/Success";
			}else{
				String documentId = request.getParameter("documentId");
				docVO.setDocument_Id(Long.parseLong(documentId));
				//docVO.setDistrictid(Integer.parseInt(request.getParameter("proj_Loca_Region")));
				doComVO.setDocument_ID(Long.parseLong(documentId));
				doHisVO.setDocument_ID(Long.parseLong(documentId));
				long count1 = documentBO.update(docVO);
				long count2 = documentBO.update(doComVO);
				long count3 = documentBO.update(doHisVO);
				if(count1 <= 0 && count2 <= 0 && count3 <= 0){
					throw new Exception("更新开发企业失败！");
				}
				return "redirect:/inner/document/docList";
			}


		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：收件录入列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/gotoDocumentList")
	public String SearchDocument(HttpServletRequest request,HttpSession session,Page page) {
		try{
			DocumentBO documentBO = new DocumentBO();
			String documentId = request.getParameter("documentId");
			String projectName = request.getParameter("projectName");
			String position = request.getParameter("position");
			String companyName = request.getParameter("companyName");
			String startDate = request.getParameter("startDate");
			String overDate = request.getParameter("overDate");
			String userId = request.getParameter("userId");
			String status = request.getParameter("status");

			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> districtList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			List<DocumentVO> list = documentBO.findBulletins(page, documentId, projectName, position, companyName, status, startDate, overDate, userId, districtList);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("DOCUMENTID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addSelectControl("radio", "selDocumentID", linkparam, "");
			tableProperty.addColumn("收件编号", "DOCUMENTID", "doCompanyEdit2", linkparam);
			tableProperty.addColumn("公司名称", "COMPANY_NAME");
			tableProperty.addColumn("项目名称", "PROJNAME");
			tableProperty.addColumn("创建日期", "CREATEDATE", "Date", "yyyy-MM-dd HH:mm:ss", null);
			tableProperty.addColumn("创建人", "CREATEPERSON");
			tableProperty.addColumn("收件状态", "STATUS_dict_name");
			tableProperty.addColumn("流转状态", "USERNAME");
			tableProperty.addColumn("收件ID", "document_Id", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("documentId", documentId);
			request.setAttribute("projectName", projectName);
			request.setAttribute("position", position);
			request.setAttribute("companyName", companyName);
			request.setAttribute("startDate", startDate);
			request.setAttribute("overDate", overDate);
			request.setAttribute("userId", userId);
			request.setAttribute("status", status);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/document/DocumentList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：打印收件页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/documentPrint")
	public String printDocument(HttpServletRequest request,HttpSession session) {
		SmUserVO smUserVO = (SmUserVO) session.getAttribute("LgUser");
		String date = DateUtil.parseDateTime3(DateUtil.getSysDateYYYYMMDD()); //收件日期   将字符串日期转化为yyyy-mm-dd格式
		String documentId = request.getParameter("document_Id");
		DocumentBO documentBO = new DocumentBO();
		DocumentVO dvo = new DocumentVO();
		DocumentCommitVO dcvo = new DocumentCommitVO();
		DocumentHistoryVO dhvo = new DocumentHistoryVO();
		try{
			dvo.setDocument_Id(Long.parseLong(documentId));
			dvo = (DocumentVO) documentBO.find(dvo);
			dcvo.setDocument_ID(Long.parseLong(documentId));
			dcvo = (DocumentCommitVO) documentBO.find(dcvo);
			dhvo.setDocument_ID(Long.parseLong(documentId));
			dhvo = (DocumentHistoryVO) documentBO.find(dhvo);
			request.setAttribute("smUserVO", smUserVO);
			request.setAttribute("date", date);
			request.setAttribute("dvo", dvo);
			request.setAttribute("dcvo", dcvo);
			request.setAttribute("dhvo", dhvo);
			return "fhhouse/manage/document/DocumentPrint";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/*
	 * 跳转到运转管理列表页面
	 */
	@RequestMapping("/docManageList")
	public String gotoSearchDocumentManage(HttpServletRequest request) {
		try{
			DocumentBO documentBO = new DocumentBO();
			List<SmUserVO> list1 = documentBO.findSmUsers();
			List<UserinfoVO> list2 = documentBO.findUseinfos();
			request.setAttribute("list1", list1);
			request.setAttribute("list2", list2);
			return "fhhouse/manage/document/DocumentManageQuery";
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return ERROR_System;
		}
	}


	/**
	 * 功能描述：运转管理列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/gotoDocumentManageList")
	public String SearchDocumentManage(HttpServletRequest request,HttpSession session,Page page) {
		try{
			DocumentBO documentBO = new DocumentBO();
			String documentId = request.getParameter("documentId");
			String projectName = request.getParameter("projectName");
			String position = request.getParameter("position");
			String companyName = request.getParameter("companyName");
			String startDate = request.getParameter("startDate");
			String overDate = request.getParameter("overDate");
			String userId = request.getParameter("userId");
			String status = request.getParameter("status");

			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> districtList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			List<DocumentVO> list = documentBO.findBulletins(page, documentId, projectName, position, companyName, status, startDate, overDate, userId, districtList);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("DOCUMENTID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("收件编号", "DOCUMENTID", "doCompanyEdit2", linkparam);
			tableProperty.addColumn("公司名称", "COMPANY_NAME");
			tableProperty.addColumn("项目名称", "PROJNAME");
			tableProperty.addColumn("创建日期", "CREATEDATE", "Date", "yyyy-MM-dd HH:mm:ss", null);
			tableProperty.addColumn("创建人", "CREATEPERSON");
			tableProperty.addColumn("收件状态", "STATUS_dict_name");
			tableProperty.addColumn("流转状态", "USERNAME");
			tableProperty.addColumn("流转履历", "流转履历", "showDocManage", linkparam, "查看", null);
			tableProperty.addColumn("收件ID", "document_Id", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("documentId", documentId);
			request.setAttribute("projectName", projectName);
			request.setAttribute("position", position);
			request.setAttribute("companyName", companyName);
			request.setAttribute("startDate", startDate);
			request.setAttribute("overDate", overDate);
			request.setAttribute("userId", userId);
			request.setAttribute("status", status);
			request.setAttribute("htmlView", htmlView);
			//session.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/document/DocumentManageList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：跳转到收件信息详细页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/showDocManage")
	public String showDocumentManage(HttpServletRequest request,HttpSession session,Page page) {
		SmUserVO smUserVO = (SmUserVO) session.getAttribute("LgUser");
		String date = DateUtil.parseDateTime3(DateUtil.getSysDateYYYYMMDD()); //收件日期   将字符串日期转化为yyyy-mm-dd格式

		int userDistrictID = smUserVO.getRegionId();
		List<Object> list = this.getUserDistricts(userDistrictID);//获取用户操作区县列表

		String documentId = request.getParameter("document_Id");
		DocumentVO dvo = new DocumentVO();
		DocumentCommitVO dcvo = new DocumentCommitVO();
		DocumentBO documentBO = new DocumentBO();

		try{
			dvo.setDocument_Id(Long.parseLong(documentId));
			dvo = (DocumentVO) documentBO.find(dvo);
			dcvo.setDocument_ID(Long.parseLong(documentId));
			dcvo = (DocumentCommitVO) documentBO.find(dcvo);

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("document_ID", "=", documentId));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
			orders.add(new MetaOrder("updTime", MetaOrder.DESC));
			List<DocumentHistoryVO> list1 = documentBO.search(DocumentHistoryVO.class, params, orders, page);
			if(list1 != null && list1.size() > 0){
				DocumentHistoryVO dhvo = list1.get(0);
				request.setAttribute("dhvo", dhvo.getUserID());
			}

			request.setAttribute("documentId", documentId);
			request.setAttribute("smUserVO", smUserVO);
			request.setAttribute("date", date);
			request.setAttribute("list", list);
			request.setAttribute("districtID", String.valueOf(userDistrictID));
			request.setAttribute("dvo", dvo);
			request.setAttribute("dcvo", dcvo);
			request.setAttribute("userId", smUserVO.getUserId());
			return "fhhouse/manage/document/ShowDocManage";
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return ERROR_System;
		}

	}

	/**
	 * 功能描述：收件确认
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/docSure")
	public String documentSure(HttpServletRequest request,HttpSession session) {
		try{
			DocumentBO documentBO = new DocumentBO();
			SmUserVO smUserVO = (SmUserVO) session.getAttribute("LgUser");
			long dID = Long.parseLong(request.getParameter("documentId"));
			DocumentHistoryVO dhvo = new DocumentHistoryVO();
			dhvo.setDocument_ID(dID);
			dhvo.setUserID(smUserVO.getUserId());
			dhvo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			dhvo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			dhvo.setCrePerson(smUserVO.getLoginName());
			dhvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			dhvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			dhvo.setUpdPerson(smUserVO.getLoginName());
			long count = documentBO.add(dhvo);
			if(count <= 0){
				throw new Exception("收件确认失败！");
			}
			return "redirect:/inner/document/docManageList";
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return ERROR_System;
		}
	}


	/**
	 * 功能描述：档案流转履历
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/docManageRecord")
	public String documentManageRecord(HttpServletRequest request,HttpSession session) {
		try{
			DocumentBO documentBO = new DocumentBO();
			String documentId = request.getParameter("dID");
			List<DocumentHistoryVO> list = documentBO.docRecord(documentId);
			request.setAttribute("list", list);

			//			DocumentVO vo = new DocumentVO();
			//			vo.setDocument_Id(dID);
			//			vo = (DocumentVO) documentBO.find(vo);
			//			String date = vo.getCreDate() + StringUtil.getFullwidthStr(String.valueOf(vo.getCreTime()), 6);//拼接日期和时间
			//			Date dateTime = DateUtil.parseDateTime2(date);
			//			String credateTime = DateUtil.formatDateTime(dateTime);
			//			request.setAttribute("vo", vo);
			//			request.setAttribute("credateTime", credateTime);
			return "fhhouse/manage/document/DocManageRecord";
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：流转结果清单
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/docResultList")
	public String searchDocumentResult(HttpServletRequest request,HttpSession session,Page page) {
		try{
			DocumentBO documentBO = new DocumentBO();
			String documentId = request.getParameter("documentId");
			String projectName = request.getParameter("projectName");
			String position = request.getParameter("position");
			String companyName = request.getParameter("companyName");
			//System.out.println(companyName);
			String startDate = request.getParameter("startDate");
			String overDate = request.getParameter("overDate");
			String userId = request.getParameter("userId");
			String status = request.getParameter("status");
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> districtList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			List<DocumentVO> list = documentBO.findDocResultList(page, documentId, projectName, position, companyName, status, startDate, overDate, userId, districtList);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("DOCUMENTID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("收件编号", "DOCUMENTID", "doCompanyEdit2", linkparam);
			tableProperty.addColumn("开盘编号", "STARTCODE");
			tableProperty.addColumn("开盘日期", "STARTDATE", "Date", "yyyy-MM-dd", null);
			tableProperty.addColumn("项目名称", "PROJECTNAME");
			tableProperty.addColumn("许可证编号", "PRESELLCODE");
			tableProperty.addColumn("企业名称", "COMPANYNAME");
			tableProperty.addColumn("项目所在地", "PROJECTADDR");
			tableProperty.addColumn("联系人", "AGENT");
			tableProperty.addColumn("联系电话", "AGENTPHONE");
			tableProperty.addColumn("通知日期", "NOTICEDATE", "Date", "yyyy-MM-dd", null);
			tableProperty.addColumn("备注", "CONTENT");
			tableProperty.addColumn("收件ID", "document_Id", true);
			
			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("documentId", documentId);
			request.setAttribute("projectName", projectName);
			request.setAttribute("position", position);
			request.setAttribute("companyName", companyName);
			request.setAttribute("startDate", startDate);
			request.setAttribute("overDate", overDate);
			request.setAttribute("userId", userId);
			request.setAttribute("status", status);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/document/DocResultListPrint";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：生成审核表
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/docCheckList")
	public String gotoCheckList(HttpServletRequest request,HttpSession session) {
		try{
			DocumentBO documentBO = new DocumentBO();
			String documentId = request.getParameter("document_Id");
			DocumentVO vo = new DocumentVO();
			vo.setDocument_Id(Long.parseLong(documentId));
			vo = (DocumentVO) documentBO.find(vo);
			request.setAttribute("vo", vo);
			return "fhhouse/manage/document/DocConfirmPrint";
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：退件和恢复
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/docReturn")
	public String returnOrRestoreDocument(HttpServletRequest request,HttpSession session) {
		try{
			DocumentBO documentBO = new DocumentBO();
			String documentId = request.getParameter("document_Id");
			String status = request.getParameter("status");
			documentBO.docReturn(Long.parseLong(documentId), Integer.parseInt(status));
			return "redirect:/inner/document/docManageList";
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return ERROR_System;
		}
	}

}
