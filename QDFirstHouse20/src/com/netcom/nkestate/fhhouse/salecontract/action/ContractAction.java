/**
 * <p>ContractAction.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 合同签约Action<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.salecontract.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.interfaces.bo.RealestateBO;
import com.netcom.nkestate.fhhouse.project.vo.CompCancelPwdVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.salecontract.bo.ContractBO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.CancelCensorVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractCancelPwdVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.DeliverContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellerInfoVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.vo.SmUserVO;

@Controller
@RequestMapping("/inner/contractmanage")
public class ContractAction extends BaseAction {

	static Logger logger = Logger.getLogger(ContractAction.class.getName());

	/**
	 * 功能描述：合同撤销申请
	 * @return
	 */
	@RequestMapping("/cancelApply")
	public String cancelApply() {
		try{
			return "/fhhouse/salecontract/cancelcontract/CancelApply";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同甲方密码设置
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/cancelSellerPwdConfirm")
	public String cancelSellerPwdConfirm(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		try{
			ContractBO contractBO = new ContractBO();
			String msg = null;

			String contractId = request.getParameter("contractId");
			ContractDealVO cdvo = (ContractDealVO) contractBO.find(ContractDealVO.class, Long.parseLong(contractId));
			if(cdvo == null){
				msg = "该合同不存在!";
				request.setAttribute("msg", msg);
				return "/fhhouse/salecontract/cancelcontract/CancelSellerPwdConfirm";
			}


			if(msg == null){
				if(cdvo.getStatus() != 2){
					msg = "该合同不是已签合同，不能执行合同撤销!";
				}
				if(cdvo.getType() == 3 && cdvo.getStatus() == 11){
					msg = "该定金合同已对应了预售或出售合同，不允许撤消!";
				}
			}

			RealestateBO rBo = new RealestateBO();
			//检查此房屋是否存则查封，如果存在，则不允许撤销
			if(rBo.checkHouseLimit(String.valueOf(cdvo.getHouseID())) > 0){
				msg = "该房屋存在查封，不允撤销!";
				request.setAttribute("msg", msg);
				return "/fhhouse/salecontract/cancelcontract/CancelSellerPwdConfirm";
			}


			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表

			ProjectVO pvo = (ProjectVO) contractBO.find(ProjectVO.class, cdvo.getProjectID()); //项目信息
			/* 交接书信息 */
			List<MetaFilter> param1 = new ArrayList<MetaFilter>();
			param1.add(new MetaFilter("contractID", "=", contractId));
			param1.add(new MetaFilter("status", "=", FHConstant.DELIVER_CONTRACT_STATUS_VALID));
			List<DeliverContractVO> dlist = contractBO.search(DeliverContractVO.class, param1);
			/* 合同撤销信息 */
			List<MetaFilter> param2 = new ArrayList<MetaFilter>();
			param2.add(new MetaFilter("contractID", "=", contractId));
			List<CancelCensorVO> clist = contractBO.search(CancelCensorVO.class, param2);

			if(msg == null){
				if(userDistrictList.contains(String.valueOf(pvo.getDistrictID())) == false){
					msg = "合同不在该登录用户所在的区县内，不可申请撤销!";
				}
			}
			if(msg == null){
				if(dlist != null && dlist.size() > 0){
					msg = "该合同存在有效的房屋交接书，请先撤销房屋交接书!";
				}
			}
			if(msg == null){
				if(clist != null && clist.size() > 0){
					CancelCensorVO ccvo = (CancelCensorVO) clist.get(0);
					if(ccvo.getStatus() == 1 || ccvo.getStatus() == 4 || ccvo.getStatus() == 5){
						msg = "该合同处于撤消审核状态，请等待!";
					}
					if(ccvo.getStatus() == 2){
						msg = "该合同已被撤消!";
					}
				}
			}

			List<MetaFilter> params1 = new ArrayList<MetaFilter>();
			params1.add(new MetaFilter("contractID", "=", contractId));
			params1.add(new MetaFilter("serial", "=", 1));
			List<SellerInfoVO> list1 = contractBO.search(SellerInfoVO.class, params1);
			String sellerPwd = null;
			if(list1 != null && list1.size() > 0){
				SellerInfoVO svo = list1.get(0);
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("compID", "=", svo.getCompID()));
				params.add(new MetaFilter("projectID", "=", cdvo.getProjectID()));
				List<CompCancelPwdVO> compList = contractBO.search(CompCancelPwdVO.class, params); //通过公司ID和项目ID查询合同甲方密码信息
				if(compList != null && compList.size() > 0){
					CompCancelPwdVO cpvo = compList.get(0);
					sellerPwd = cpvo.getPwd();
				}
			}

			if(msg == null){
				if(sellerPwd == null || "".equals(sellerPwd)){
					msg = "还有合同甲方没有设置密码，请有权限的操作人员设置密码!";
				}
			}

			String buyerPwd = null;
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("contractID", "=", contractId));
			List<ContractCancelPwdVO> list = contractBO.search(ContractCancelPwdVO.class, params);
			if(list != null && list.size() > 0){
				for(ContractCancelPwdVO ccvo1 : list){
					buyerPwd = ccvo1.getPwd();
				}
			}
			if(msg == null){
				if(buyerPwd == null || "".equals(buyerPwd)){
					msg = "还有合同乙方没有设置密码，请有权限的操作人员设置密码!";
				}
			}

			List<MetaFilter> params2 = new ArrayList<MetaFilter>();
			params2.add(new MetaFilter("contractID", "=", contractId));
			List<BuyerInfoVO> list2 = contractBO.search(BuyerInfoVO.class, params2);

			request.setAttribute("msg", msg);
			request.setAttribute("sellerPwd", sellerPwd);
			request.setAttribute("contractId", contractId);
			session.setAttribute("list1", list1);
			session.setAttribute("list2", list2);
			return "/fhhouse/salecontract/cancelcontract/CancelSellerPwdConfirm";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同乙方密码设置
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/cancelBuyerPwdConfirm")
	public String cancelBuyerPwdConfirm(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		try{
			ContractBO contractBO = new ContractBO();
			String contractId = request.getParameter("cId");
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("contractID", "=", contractId));
			List<ContractCancelPwdVO> list = contractBO.search(ContractCancelPwdVO.class, params);

			List<MetaFilter> params3 = new ArrayList<MetaFilter>();
			params3.add(new MetaFilter("contractID", "=", contractId));
			List<BuyerInfoVO> list3 = contractBO.search(BuyerInfoVO.class, params3);

			request.setAttribute("contractId", contractId);
			request.setAttribute("list", list);
			request.setAttribute("list3", list3);

			return "/fhhouse/salecontract/cancelcontract/CancelBuyerPwdConfirm";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同撤销申请经办意见
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/cancelApplyView")
	public String cancelApplyView(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		try{
			ContractBO contractBO = new ContractBO();
			String contractId = request.getParameter("cId");
			ContractDealVO cdvo = (ContractDealVO) contractBO.find(ContractDealVO.class, Long.parseLong(contractId));
			String confirmDate = DateUtil.parseDateTime3(String.valueOf(cdvo.getConfirmDate()).substring(0, 8));
			request.setAttribute("confirmDate", confirmDate);
			List<MetaFilter> params1 = new ArrayList<MetaFilter>();
			params1.add(new MetaFilter("contractID", "=", contractId));
			params1.add(new MetaFilter("serial", "=", 1));
			List<SellerInfoVO> list1 = contractBO.search(SellerInfoVO.class, params1);
			for(SellerInfoVO svo : list1){
				request.setAttribute("svo", svo);
			}

			List<MetaFilter> params2 = new ArrayList<MetaFilter>();
			params2.add(new MetaFilter("contractID", "=", contractId));
			params2.add(new MetaFilter("serial", "=", 1));
			List<BuyerInfoVO> list2 = contractBO.search(BuyerInfoVO.class, params2);
			for(BuyerInfoVO bvo : list2){
				request.setAttribute("bvo", bvo);
			}

			request.setAttribute("cdvo", cdvo);
			return "/fhhouse/salecontract/cancelcontract/CancelApplyView";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同撤销申请提交
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/cancelApplySubmit")
	public String cancelApplySubmit(HttpServletRequest request,HttpSession session,CancelCensorVO cvo) {
		try{
			ContractBO contractBO = new ContractBO();
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String cID = request.getParameter("contractID");
			ContractDealVO cdvo = (ContractDealVO) contractBO.find(ContractDealVO.class, Long.parseLong(cID));

			cvo.setConfirmDate(cvo.getConfirmDate().replace("-", ""));
			cvo.setStatus(FHConstant.CONTRACT_JUDGE_STATUS_WAITING);
			cvo.setReasonType(1); //默认值
			cvo.setContType(cdvo.getType()); //合同类型
			cvo.setFirstCensor(String.valueOf(uservo.getUserId())); //经办人
			cvo.setFirstDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));//经办日期

			cvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			cvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			cvo.setUpdPerson(uservo.getLoginName());
			cvo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			cvo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			cvo.setCrePerson(uservo.getLoginName());
			long count = contractBO.add(cvo);
			if(count <= 0){
				throw new Exception("新增撤销合同失败！");
			}
			cvo.setID(count);

			request.setAttribute("count", count);
			request.setAttribute("cID", cID);
			return "/fhhouse/salecontract/cancelcontract/CancelApplyFinish";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同撤销复核
	 * @return
	 */
	@RequestMapping("/cancelFirstAudit")
	public String CancelFirstAudit() {
		try{
			return "/fhhouse/salecontract/cancelcontract/CancelFirstAudit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同撤销复核列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/cancelFirstAuditList")
	public String gotoCancelFirstAuditList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			ContractBO contractBO = new ContractBO();
			String contractId = request.getParameter("contractId");
			String sellerName = request.getParameter("sellerName");
			String status = request.getParameter("status");
			String buyerName = request.getParameter("buyerName");
			request.setAttribute("contractId", contractId);
			request.setAttribute("sellerName", sellerName);
			request.setAttribute("status", status);
			request.setAttribute("buyerName", buyerName);

			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String districtList = this.getUserSqlDistricts(vo.getRegionId());
			List<CancelCensorVO> list = contractBO.findCancelFirstAudit(page, contractId, sellerName, status, buyerName, districtList);
			int a = -1;
			for(CancelCensorVO cvo : list){
				a = cvo.getStatus();
			}
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("合同编号", "contractID");
			tableProperty.addColumn("房屋坐落", "location");
			tableProperty.addColumn("房屋面积", "area");
			tableProperty.addColumn("申请人", "proposer1");
			tableProperty.addColumn("经办人", "firstCensor");
			tableProperty.addColumn("当前状态", "status_dict_name");
			if(a == FHConstant.CONTRACT_JUDGE_STATUS_WAITING || a == FHConstant.CONTRACT_JUDGE_STATUS_BACK){
				tableProperty.addColumn("审核", "审核", "doContractReview", linkparam, "点击送审", null);
			}else{
				tableProperty.addColumn("审核", "审核", "doContractReview", linkparam, "查看", null);
			}
			tableProperty.addColumn("流水号", "ID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/salecontract/cancelcontract/CancelFirstAuditList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同注销审批表
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/cancelFirstAuditView")
	public String cancelFirstAuditView(HttpServletRequest request,HttpSession session) {
		ContractBO contractBO = new ContractBO();
		SmUserVO smUserVO = (SmUserVO) session.getAttribute("LgUser");
		int userDistrictID = smUserVO.getRegionId();
		String ID = request.getParameter("cID");
		CancelCensorVO cvo = new CancelCensorVO();
		try{
			cvo.setID(Long.parseLong(ID));
			cvo = (CancelCensorVO) contractBO.find(cvo);
			request.setAttribute("cvo", cvo);
			List<CancelCensorVO> list = contractBO.findCensor(ID);
			request.setAttribute("list", list);
			return "/fhhouse/salecontract/cancelcontract/CancelFirstAuditView";
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：复审提交
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/doFirstSubmit")
	public String doFirstSubmit(HttpServletRequest request,HttpSession session) {
		try{
			ContractBO contractBO = new ContractBO();
			SmUserVO smUserVO = (SmUserVO) session.getAttribute("LgUser");
			String ID = request.getParameter("ID");
			String finalMark = URLDecoder.decode(request.getParameter("finalMark"), "utf-8");
			String option = request.getParameter("option");
			CancelCensorVO cvo = new CancelCensorVO();
			cvo.setID(Long.parseLong(ID));
			cvo = (CancelCensorVO) contractBO.find(cvo);
			if("1".equals(option)){
				cvo.setStatus(FHConstant.CONTRACT_JUDGE_STATUS_EARLY_PASSED);
			}
			if("2".equals(option)){
				cvo.setStatus(FHConstant.CONTRACT_JUDGE_STATUS_FAILED);
			}
			cvo.setEarlyNotion(finalMark);
			cvo.setEarlyCensor(String.valueOf(smUserVO.getUserId()));
			cvo.setEarlyDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			cvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			cvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			cvo.setUpdPerson(smUserVO.getLoginName());
			contractBO.update(cvo);

			return "/fhhouse/salecontract/cancelcontract/CancelFirstFinish";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/**
	 * 功能描述：合同撤销审批
	 * @return
	 */
	@RequestMapping("/cancelSecondAudit")
	public String gotoCancelSecondAudit(HttpServletRequest request,HttpSession session) {
		try{
			return "/fhhouse/salecontract/cancelcontract/CancelSecondAudit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同撤销审批列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/cancelSecondAuditList")
	public String gotoCancelSecondAuditList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			ContractBO contractBO = new ContractBO();
			String contractId = request.getParameter("contractId");
			String sellerName = request.getParameter("sellerName");
			String status = request.getParameter("status");
			String buyerName = request.getParameter("buyerName");
			String reason = request.getParameter("reason");
			String confirmDate1 = request.getParameter("confirmDate1");
			String confirmDate2 = request.getParameter("confirmDate2");
			request.setAttribute("contractId", contractId);
			request.setAttribute("sellerName", sellerName);
			request.setAttribute("status", status);
			request.setAttribute("buyerName", buyerName);
			request.setAttribute("reason", reason);
			request.setAttribute("confirmDate1", confirmDate1);
			request.setAttribute("confirmDate2", confirmDate2);

			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String districtList = this.getUserSqlDistricts(vo.getRegionId());
			List<CancelCensorVO> list = contractBO.findCancelSecondAudit(page, contractId, sellerName, status, buyerName, reason, confirmDate1, confirmDate2, districtList);
			int a = -1;
			for(CancelCensorVO cvo : list){
				a = cvo.getStatus();
			}
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("合同编号", "contractID");
			tableProperty.addColumn("房屋坐落", "location");
			tableProperty.addColumn("房屋面积", "area");
			tableProperty.addColumn("申请人", "proposer1");
			tableProperty.addColumn("复核人", "earlyCensor");
			tableProperty.addColumn("当前状态", "status_dict_name");
			tableProperty.addColumn("提交时间", "firstDate1");
			if(a == FHConstant.CONTRACT_JUDGE_STATUS_EARLY_PASSED || a == FHConstant.CONTRACT_JUDGE_STATUS_NOPERMITTED){
				tableProperty.addColumn("审核", "审核", "doContractReview", linkparam, "点击审核", null);
			}else{
				tableProperty.addColumn("审核", "审核", "doContractReview", linkparam, "查看", null);
			}
			tableProperty.addColumn("流水合同编号", "ID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/salecontract/cancelcontract/CancelSecondAuditList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同注销审批表
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/gotoContractCancelEdit")
	public String cancelSecondAuditView(HttpServletRequest request,HttpSession session) {
		String ID = request.getParameter("cID");
		CancelCensorVO cvo = new CancelCensorVO();
		try{
			ContractBO contractBO = new ContractBO();
			cvo.setID(Long.parseLong(ID));
			cvo = (CancelCensorVO) contractBO.find(cvo);
			request.setAttribute("cvo", cvo);
			List<CancelCensorVO> list = contractBO.findCensor(ID);
			request.setAttribute("list", list);
			return "/fhhouse/salecontract/cancelcontract/CancelSecondAuditView";
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return ERROR_System;
		}

	}


	/**
	 * 功能描述：合同撤销审批提交
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/doSecondSubmit")
	public String doSecondSubmit(HttpServletRequest request,HttpSession session) {
		try{
			ContractBO contractBO = new ContractBO();
			SmUserVO smUserVO = (SmUserVO) session.getAttribute("LgUser");
			String ID = request.getParameter("ID");
			String status = request.getParameter("status");
			String contractId = request.getParameter("cID");
			String option = request.getParameter("option");
			String reasonType = request.getParameter("reasonType");
			String finalMark = URLDecoder.decode(request.getParameter("finalMark"), "utf-8");
			CancelCensorVO ccvo = new CancelCensorVO();
			ContractDealVO cdvo = new ContractDealVO();
			HouseVO hvo = new HouseVO();
			ccvo.setID(Long.parseLong(ID));
			ccvo = (CancelCensorVO) contractBO.find(ccvo);
			cdvo.setContractID(Long.parseLong(contractId));
			cdvo = (ContractDealVO) contractBO.find(cdvo);
			hvo.setHouse_ID(cdvo.getHouseID());
			hvo = (HouseVO) contractBO.find(hvo);
			if("1".equals(option)){
//				ccvo.setStatus(FHConstant.CONTRACT_JUDGE_STATUS_PASSED);
				//20190829审核同意后自动发布
				ccvo.setStatus(FHConstant.CONTRACT_JUDGE_STATUS_PERMITTED);
				contractBO.doThirdSubmit(Long.parseLong(ID), Long.parseLong(contractId), option, smUserVO);
			}
			if("2".equals(option)){
				ccvo.setStatus(FHConstant.CONTRACT_JUDGE_STATUS_BACK);
			}
			ccvo.setFinalNotion(finalMark);
			ccvo.setFinalCensor(String.valueOf(smUserVO.getUserId()));
			ccvo.setFinalDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			ccvo.setReasonType(Integer.parseInt(reasonType));
			ccvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			ccvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			ccvo.setUpdPerson(smUserVO.getLoginName());
			contractBO.update(ccvo);

			request.setAttribute("ID", ID);
			return "/fhhouse/salecontract/cancelcontract/CancelSecondFinish";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/**
	 * 功能描述：合同撤销发布
	 * @return
	 */
	@RequestMapping("/cancelIssue")
	public String gotoCancelIssue() {
		try{
			return "/fhhouse/salecontract/cancelcontract/CancelIssue";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同撤销发布列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/cancelIssueList")
	public String gotoCancelIssueList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			ContractBO contractBO = new ContractBO();
			String contractId = request.getParameter("contractId");
			String sellerName = request.getParameter("sellerName");
			String status = request.getParameter("status");
			String buyerName = request.getParameter("buyerName");
			String reason = request.getParameter("reason");
			String confirmDate1 = request.getParameter("confirmDate1");
			String confirmDate2 = request.getParameter("confirmDate2");
			request.setAttribute("contractId", contractId);
			request.setAttribute("sellerName", sellerName);
			request.setAttribute("status", status);
			request.setAttribute("buyerName", buyerName);
			request.setAttribute("reason", reason);
			request.setAttribute("confirmDate1", confirmDate1);
			request.setAttribute("confirmDate2", confirmDate2);

			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String districtList = this.getUserSqlDistricts(vo.getRegionId());
			List<CancelCensorVO> list = contractBO.findCancelSecondAudit(page, contractId, sellerName, status, buyerName, reason, confirmDate1, confirmDate2, districtList);
			int a = -1;
			for(CancelCensorVO cvo : list){
				a = cvo.getStatus();
			}
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("合同编号", "contractID");
			tableProperty.addColumn("房屋坐落", "location");
			tableProperty.addColumn("房屋面积", "area");
			tableProperty.addColumn("申请人", "proposer1");
			tableProperty.addColumn("复核人", "earlyCensor");
			tableProperty.addColumn("当前状态", "status_dict_name");
			tableProperty.addColumn("提交时间", "firstDate1");
			if(a == FHConstant.CONTRACT_JUDGE_STATUS_PASSED){
				tableProperty.addColumn("审核", "审核", "doContractReview", linkparam, "点击发布", null);
			}else{
				tableProperty.addColumn("审核", "审核", "doContractReview", linkparam, "查看", null);
			}
			tableProperty.addColumn("流水合同编号", "ID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/salecontract/cancelcontract/CancelIssueList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同注销发布表
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/cancelIssueView")
	public String cancelIssueView(HttpServletRequest request,HttpSession session) {
		ContractBO contractBO = new ContractBO();
		String ID = request.getParameter("cID");
		CancelCensorVO cvo = new CancelCensorVO();
		try{
			cvo.setID(Long.parseLong(ID));
			cvo = (CancelCensorVO) contractBO.find(cvo);
			request.setAttribute("cvo", cvo);
			List<CancelCensorVO> list = contractBO.findCensor(ID);
			request.setAttribute("list", list);
			return "/fhhouse/salecontract/cancelcontract/CancelIssueView";
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return ERROR_System;
		}

	}

	/**
	 * 功能描述：发布提交
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/doThirdSubmit")
	public String doThirdSubmit(HttpServletRequest request,HttpSession session) {
		try{
			ContractBO contractBO = new ContractBO();
			SmUserVO smUserVO = (SmUserVO) session.getAttribute("LgUser");
			String ID = request.getParameter("ID");
			String option = request.getParameter("option");
			String contractId = request.getParameter("cID");
			boolean result = contractBO.doThirdSubmit(Long.parseLong(ID), Long.parseLong(contractId), option, smUserVO);
			if(result == false){
				throw new Exception("发布提交失败！");
			}

			request.setAttribute("ID", ID);
			return "/fhhouse/salecontract/cancelcontract/CancelSecondFinish";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
}
