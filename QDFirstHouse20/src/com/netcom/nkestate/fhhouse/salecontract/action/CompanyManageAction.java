package com.netcom.nkestate.fhhouse.salecontract.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.common.Constant;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.vo.CompFundAccountVO;
import com.netcom.nkestate.fhhouse.company.vo.ExchangeRecordVO;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.fhhouse.interfaces.bo.RealestateBO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO;
import com.netcom.nkestate.fhhouse.manage.bo.BulletinBO;
import com.netcom.nkestate.fhhouse.project.vo.CompCancelPwdVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.salecontract.bo.CompanyManageBO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ChargeVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractCancelPwdVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.DeliverContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellerInfoVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.bo.DictionaryBO;
import com.netcom.nkestate.system.bo.SystemBO;
import com.netcom.nkestate.system.vo.BulletinVO;
import com.netcom.nkestate.system.vo.SmUserVO;


/**
 * 开发企业管理模块Action
 */
@Controller
@RequestMapping(value = "/outer/manage")
public class CompanyManageAction extends BaseAction {

	/**
	 * 功能描述：显示公告列表信息
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoBulletinList")
	public String queryBulletinList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			BulletinBO bulletinBO = new BulletinBO();
			List<BulletinVO> list = bulletinBO.findShowBulletinList(page, vo.getRegionId());
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("标题", "title");
			tableProperty.addColumn("修改时间", "UpdateTime", "Date", "yyyy-MM-dd HH:mm:ss", null);
			tableProperty.addColumn("查看", "查看", "doViewBulletin", linkparam, "查看", null);
			tableProperty.addColumn("公告编号", "ID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/salecontract/manage/BulletinList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：显示公告信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/viewBulletin")
	public String queryBulletin(HttpServletRequest request,HttpSession session) {
		try{
			String bID = request.getParameter("bID");
			BulletinVO bvo = new BulletinVO();
			bvo.setID(Long.parseLong(bID));
			BulletinBO bulletinBO = new BulletinBO();
			bvo = (BulletinVO) bulletinBO.find(bvo);
			request.setAttribute("bvo", bvo);
			return "/fhhouse/salecontract/manage/BulletinView";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：显示公告列表信息
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoSignerInvailidSetList")
	public String querySignerInvailidSetList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			CompanyManageBO cmBO = new CompanyManageBO();
			List<SignerVO> list = cmBO.findSignerInvailidSetList(page, vo.getUserId());
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("signer_ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addSelectControl("radio", "selSigner", linkparam, "");
			tableProperty.addColumn("姓名", "name");
			tableProperty.addColumn("证件名称", "cardName");
			tableProperty.addColumn("证件号码", "cardCode");
			tableProperty.addColumn("状态", "isEnable_dict_name");
			tableProperty.addColumn("签约人编号", "signer_ID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/salecontract/manage/SignerInvailidSetList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 签约人有效性设置
	 * @return
	 */
	@RequestMapping(value = "doSignerInvailidSet")
	@ResponseBody
	public JSONArray doSignerInvailidSet(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			CompanyManageBO cmBO = new CompanyManageBO();
			long signerId = Long.parseLong(request.getParameter("signerId"));
			int invailid = Integer.parseInt(request.getParameter("invailid"));
			SignerVO signerVO = new SignerVO();
			signerVO.setSigner_ID(signerId);
			signerVO = (SignerVO) cmBO.find(signerVO);

			String msg = "";
			if(signerVO == null){
				msg = "未找到对应签约人记录！";
			}
			if(vo.getOrgID() != Long.parseLong(signerVO.getComp_ID())){
				msg = "被修改签约人与你不是同一个开发企业！";
			}

			if("".equals(msg)){
				signerVO = new SignerVO();
				signerVO.setSigner_ID(signerId);
				signerVO.setIsEnable(invailid);
				signerVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				signerVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				signerVO.setUpdPerson(vo.getLoginName());
				long count = cmBO.update(signerVO);
				if(count == 1){
					map.put("result", "success");
					map.put("message", "更新成功！");
				}else{
					map.put("result", "fail");
					map.put("message", "未更新记录！");
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
			map.put("message", "更新失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：密码设置项目列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoModifyPwdProjectList")
	public String queryModifyPwdProjectList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			CompanyManageBO cmBO = new CompanyManageBO();
			List<ProjectVO> list = cmBO.findModifyPwdProjectList(page, vo.getUserId());
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("project_ID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("项目名称", "projectName");
			tableProperty.addColumn("项目地址", "projectAdr");
			tableProperty.addColumn("出让合同编号", "lotNum");
			tableProperty.addColumn("密码设置", "密码设置", "doModifyPwd", linkparam, "密码设置", null);
			tableProperty.addColumn("签约人编号", "project_ID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/salecontract/manage/ModifyPwdProjectList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：项目密码设置
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoModifyProjectPwd")
	public String gotoModifyProjectPwd(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			CompanyManageBO cmBO = new CompanyManageBO();
			CompCancelPwdVO pwdVO = new CompCancelPwdVO();
			long projectId = Long.parseLong(request.getParameter("projectId"));
			pwdVO.setProjectID(projectId);
			pwdVO.setCompID(vo.getOrgID());
			pwdVO = (CompCancelPwdVO) cmBO.find(pwdVO);
			String cmd = "update";
			if(pwdVO == null){
				cmd = "insert";
			}
			request.setAttribute("cmd", cmd);
			request.setAttribute("projectId", projectId);
			request.setAttribute("compId", vo.getOrgID());
			return "/fhhouse/salecontract/manage/ModifyProjectPwd";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/**
	 * 功能描述：保存密码
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "doModifyProjectPwd")
	@ResponseBody
	public JSONArray doModifyProjectPwd(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		String msg = "";
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			CompanyManageBO cmBO = new CompanyManageBO();
			long projectId = Long.parseLong(request.getParameter("projectId"));
			long compId = Long.parseLong(request.getParameter("compId"));
			String cmd = request.getParameter("cmd");
			String oldPwd = request.getParameter("oldPwd");
			String newPwd = request.getParameter("newPwd");
			String newPwdConfirm = request.getParameter("newPwdConfirm");
			if(newPwd==null || "".equals(newPwd.trim())){
				msg = "请输入新密码！";
			}else if(!newPwd.trim().equals(newPwdConfirm.trim())){
				msg = "新密码两次输入不一致！";
			}else{
				if("update".equals(cmd)){
					if(oldPwd == null || "".equals(oldPwd.trim())){
						msg = "原密码不正确！";
					}else{
						CompCancelPwdVO pwdVO = new CompCancelPwdVO();
						pwdVO.setProjectID(projectId);
						pwdVO.setCompID(compId);
						pwdVO = (CompCancelPwdVO) cmBO.find(pwdVO);
						if(!oldPwd.equals(pwdVO.getPwd())){
							msg = "原密码不正确！";
						}
					}
				}else if(!"insert".equals(cmd)){
					msg = "非法操作！";
				}
			}

			if("".equals(msg)){
				long count = 0;

				CompCancelPwdVO pwdVO = new CompCancelPwdVO();
				pwdVO.setProjectID(projectId);
				pwdVO.setCompID(compId);
				pwdVO.setPwd(newPwd.trim());
				pwdVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				pwdVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				pwdVO.setUpdPerson(vo.getLoginName());
				if("insert".equals(cmd)){
					//新增记录
					pwdVO.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					pwdVO.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					pwdVO.setCrePerson(vo.getLoginName());
					count = cmBO.add(pwdVO);
				}else{
					//更新记录
					count = cmBO.update(pwdVO);
				}

				if(count > 0){
					map.put("result", "success");
					map.put("message", "密码更新成功！");
				}else{
					map.put("result", "fail");
					map.put("message", "未更新密码记录！");
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
			map.put("message", "密码更新失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：企业费用查询
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoCompFeeQuery")
	public String gotoCompFeeQuery(HttpServletRequest request,HttpSession session) {
		try{
			return "/fhhouse/salecontract/manage/CompanyFeeQueryFrame";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：企业费用明显查询
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoCompFeeList")
	public String gotoCompFeeList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String feeRange = request.getParameter("feeRange");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			CompanyManageBO cmBO = new CompanyManageBO();
			List<ExchangeRecordVO> list = cmBO.findCompExchangeRecordList(vo.getOrgID(), feeRange, startDate, endDate, page);
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("操作", "exType_dict_name");
			tableProperty.addColumn("发生金额(元)", "exCount");
			tableProperty.addColumn("本次余额(元)", "balance");
			tableProperty.addColumn("时间", "UpdateTimeStr");
			tableProperty.addColumn("操作帐号", "operator");

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/salecontract/manage/CompanyFeeList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：企业费用总账查询
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoCompFeeTotal")
	public String gotoCompFeeTotal(HttpServletRequest request,HttpSession session) {
		try{
			CompanyManageBO cmBO = new CompanyManageBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			//账号查询
			CompFundAccountVO accountVO = cmBO.findCompFundAccount(vo.getOrgID());
			//交费查询
			ExchangeRecordVO reservevo = cmBO.findCompExchangeRecord(vo.getOrgID(), FHConstant.EXCHANGE_TYPE_RESERVE);
			//退费查询
			ExchangeRecordVO refundvo = cmBO.findCompExchangeRecord(vo.getOrgID(), FHConstant.EXCHANGE_TYPE_REFUNDMENT);
			//使用查询
			ChargeVO chargeVO = cmBO.findCompCharge(vo.getOrgID());

			request.setAttribute("EXCOUNT_ADD", reservevo.getExCount());//累计交纳金额
			request.setAttribute("CHARGECOUNT_ADD", chargeVO.getChargeCount());//已经使用金额
			request.setAttribute("FREEZEMONEY", accountVO.getFreezeMoney());//已经冻结金额
			request.setAttribute("EXCOUNT", refundvo.getExCount());//已经退款金额
			request.setAttribute("BALANCE", accountVO.getBalance());//当前可用余额
			return "/fhhouse/salecontract/manage/CompanyFeeTotal";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/**
	 * 功能描述：合同撤销状态查询
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoContractCancelQuery")
	public String gotoContractCancelQuery(HttpServletRequest request,HttpSession session) {
		try{
			return "/fhhouse/salecontract/manage/ContractCancelQueryFrame";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同撤销状态列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoContractCancelList")
	public String gotoContractCancelList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String contractId = request.getParameter("contractId");
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			CompanyManageBO cmBO = new CompanyManageBO();
			List<ContractDealVO> list = cmBO.findContractCancelList(vo.getOrgID(), contractId, page);
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("合同号", "contractID");
			tableProperty.addColumn("合同类型", "type_dict_name");
			tableProperty.addColumn("合同状态", "status_dict_name");
			tableProperty.addColumn("合同坐落", "location");
			tableProperty.addColumn("撤销审核状态", "cancelStatus");

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/salecontract/manage/ContractCancelList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：签订房屋交接书
	 * @return
	 */
	@RequestMapping("/deliverApply")
	public String deliverApply(HttpServletRequest request,HttpSession session) {
		try{
			request.setAttribute("cmd", "apply");
			return "/fhhouse/salecontract/manage/DeliverApply";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：签订房屋交接书数据校验
	 * @return JSONArray
	 */
	@RequestMapping("/searchContract")
	@ResponseBody
	public JSONArray checkContractForDeliver(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			long contractID = Long.parseLong(request.getParameter("contractID"));
			CompanyManageBO cmbo = new CompanyManageBO();

			//查询合同
			ContractDealVO cdvo = (ContractDealVO) cmbo.find(ContractDealVO.class, contractID);
			//检查合同是否存在
			if(cdvo != null){
				//检查合同是否已签或者已登记的预售合同
				if(cdvo.getType() == 1 && (cdvo.getStatus() == FHConstant.CONTRACT_STATUS_SIGNED || cdvo.getStatus() == FHConstant.CONTRACT_STATUS_REGISTED)){
					//检查该签约人是否有操作该合同权限
					if(cmbo.checkSignerContract(uservo.getUserId(), contractID)){
						//检查合同是否已存在房屋交接书了
						List<DeliverContractVO> deliverList = cmbo.queryDeliverByContract(contractID, FHConstant.DELIVER_CONTRACT_STATUS_VALID);
						if(deliverList != null && deliverList.size() > 0){
							map.put("result", "fail");
							map.put("message", "该合同已存在房屋交接书。");
						}else{
							//检查合同是否正在撤销中
							if(cmbo.checkContractInCancel(contractID)){
								map.put("result", "fail");
								map.put("message", "该合同处于撤销中，不能签订房屋交接书。");
							}else{
								List<MetaFilter> params = new ArrayList<MetaFilter>();
								params.add(new MetaFilter("compID", "=", uservo.getOrgID()));
								params.add(new MetaFilter("projectID", "=", cdvo.getProjectID()));
								//通过公司ID和项目ID查询合同甲方密码信息
								List<CompCancelPwdVO> list = cmbo.search(CompCancelPwdVO.class, params);
								if(list==null || list.size()<=0){
									map.put("result", "fail");
									map.put("message", "该合同对应项目甲方密码还未设置，不能签订房屋交接书。");
								}else{
									map.put("result", "success");
									map.put("message", "");
								}
							}
						}
					}else{
						map.put("result", "fail");
						map.put("message", "该合同没有操作权限！");
					}
				}else{
					map.put("result", "fail");
					map.put("message", "该合同不是已签或者已登记状态的预售合同，不符合签订交接书的条件!");
				}
			}else{
				map.put("result", "fail");
				map.put("message", "未找到对应合同记录！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "合同查询失败！");
			json = JSONArray.fromObject(map);
			return json;
		}
	}


	/**
	 * 功能描述：撤销房屋交接书数据校验
	 * @return JSONArray
	 */
	@RequestMapping("/searchDeliver")
	@ResponseBody
	public JSONArray checkCancelDeliver(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			long contractID = Long.parseLong(request.getParameter("contractID"));
			CompanyManageBO cmbo = new CompanyManageBO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("contractID", "=", contractID));
			params.add(new MetaFilter("status", "=", FHConstant.DELIVER_CONTRACT_STATUS_VALID));
			List<DeliverContractVO> list = cmbo.search(DeliverContractVO.class, params);
			if(list.size() > 0){
				map.put("result", "success");
				map.put("message", "");
				for(DeliverContractVO dcvo : list){
					if(dcvo.getStatus() != FHConstant.DELIVER_CONTRACT_STATUS_VALID){
						map.put("result", "fail");
						map.put("message", "该交接书已是撤销状态!");
					}
				}
			}else{
				map.put("result", "fail");
				map.put("message", "未找到对应交接书记录！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "合同查询失败！");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：签订房屋交接书详情
	 * @return
	 */
	@RequestMapping("/deliverApplyView")
	public String deliverApplyView(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		try{
			String contractId = request.getParameter("contractID");
			CompanyManageBO cmBO = new CompanyManageBO();
			DictionaryBO dictBO = new DictionaryBO();
			//合同主表查询
			ContractDealVO cdvo = (ContractDealVO) cmBO.find(ContractDealVO.class, Long.parseLong(contractId));

			//卖方查询
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("contractID", "=", contractId));
			List<SellerInfoVO> slist = cmBO.search(SellerInfoVO.class, params);
			String sellerName = "";
			if(slist != null && slist.size() > 0){
				for(SellerInfoVO sellervo : slist){
					sellerName += "，" + sellervo.getSellerName();
				}
				if(!"".equals(sellerName)){
					sellerName = sellerName.substring(1);
				}
			}

			//买方查询
			List<MetaFilter> params1 = new ArrayList<MetaFilter>();
			params1.add(new MetaFilter("contractID", "=", contractId));
			List<BuyerInfoVO> blist = cmBO.search(BuyerInfoVO.class, params1);
			String buyerName = "";
			if(blist != null && blist.size() > 0){
				for(BuyerInfoVO buyervo : blist){
					buyerName += "，" + buyervo.getBuyerName();
				}
				if(!"".equals(buyerName)){
					buyerName = buyerName.substring(1);
				}
			}

			//预售合同明细信息
//			PresellContractVO pcvo = (PresellContractVO) cmBO.find(PresellContractVO.class, Long.parseLong(contractId));
//			ContractDetailYsVO contractDetailYs  = (ContractDetailYsVO)cmBO.find(ContractDetailYsVO.class, Long.parseLong(contractId));
			
			//房屋实测面积获取
			//房屋信息查询
			RealestateBO rBo = new RealestateBO();
			CHFlatVO flatvo = rBo.findCHFlat(cdvo.getHouseID());
			if(flatvo.getPro_Area() == null || "".equals(flatvo.getPro_Area()) || "null".equals(flatvo.getPro_Area())){
				flatvo.setPro_Area("0");
			}
			request.setAttribute("flatvo", flatvo);


			//项目对应机构名称获取
			ProjectVO projectVO = (ProjectVO) cmBO.find(ProjectVO.class, cdvo.getProjectID());
			if(projectVO != null){
				String orgName = dictBO.getDictName("ct_510", String.valueOf(projectVO.getDistrictID()));
				if("".equals(orgName)){
					orgName = dictBO.getDictName("ct_510", String.valueOf(0));
				}
				request.setAttribute("orgName", orgName);
				request.setAttribute("projectName", projectVO.getProjectName());
			}

			if(cdvo != null){
				request.setAttribute("cdvo", cdvo);
			}
			
			//大产证号获取
			String smallCode = Constant.REG_SMALL_CODE;
			List<CHFlatVO> realList = rBo.findRealInfo(cdvo.getHouseID() + "");
			String realNo = "";
			if(realList!=null && realList.size()>0){
				for(CHFlatVO realvo : realList){
					String typeaid = realvo.getAttribute("typeaid").toString();
					String typebid = realvo.getAttribute("typebid").toString();
					//判断大类是否初始登记
					if("11".equals(typeaid)||"12".equals(typeaid)){
						realNo = realvo.getAttribute("realno") == null ? "" : realvo.getAttribute("realno").toString();
					}else{
						//判断小类是否初始登记小类
						if(smallCode.indexOf("[" + typebid + "]") >= 0){
							realNo = realvo.getAttribute("realno") == null ? "" : realvo.getAttribute("realno").toString();
						}
					}
				}
			}
			request.setAttribute("realNo", realNo);

			request.setAttribute("sellerName", sellerName);
			request.setAttribute("buyerName", buyerName);
//			request.setAttribute("pcvo", pcvo);
//			request.setAttribute("pcvo", contractDetailYs);
			request.setAttribute("contractId", contractId);
			request.setAttribute("year", DateUtil.getCurrentYear());
			request.setAttribute("month", DateUtil.getSysDateYYYYMMDD().substring(4, 6));
			request.setAttribute("day", DateUtil.getSysDateYYYYMMDD().substring(6, 8));
			return "/fhhouse/salecontract/manage/DeliverApplyView";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：签订房屋交接书提交
	 * @param request
	 * @param session
	 * @param dcvo
	 * @return
	 */
	@RequestMapping("/doDeliverSubmit")
	@ResponseBody
	public JSONArray doDeliverSubmit(HttpServletRequest request,HttpSession session,DeliverContractVO dcvo) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			CompanyManageBO cmbo = new CompanyManageBO();
			String contractIDStr = request.getParameter("contractID");
			long contractID = -1;
			if(contractIDStr != null && !"".equals(contractIDStr)){
				contractID = Long.parseLong(contractIDStr);
			}
			List<DeliverContractVO> deliverList = cmbo.queryDeliverByContract(contractID, FHConstant.DELIVER_CONTRACT_STATUS_VALID);
			if(deliverList != null && deliverList.size() > 0){
				map.put("result", "fail");
				map.put("message", "该合同已存在房屋交接书。");
			}else{
				SystemBO sBo = new SystemBO();
				dcvo.setDeliverID(Long.parseLong(sBo.getSequence("CONTRACTID")));
				dcvo.setStatus(FHConstant.DELIVER_CONTRACT_STATUS_VALID); //默认值设置

				dcvo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				dcvo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				dcvo.setCrePerson(uservo.getLoginName());
				dcvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				dcvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				dcvo.setUpdPerson(uservo.getLoginName());
				session.setAttribute("dcvo", dcvo);
				if(dcvo != null){
					map.put("result", "success");
					map.put("message", "");
				}else{
					map.put("result", "fail");
					map.put("message", "交接书提交失败！");
				}
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "交接书提交失败！");
			json = JSONArray.fromObject(map);
			return json;
		}
	}


	/**
	 * 功能描述：甲方密码验证
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/deliverSellerPwdConfirm")
	public String deliverSellerPwdConfirm(HttpServletRequest request,HttpSession session) {
		try{
			String contractId = request.getParameter("contractID");
			CompanyManageBO cmBO = new CompanyManageBO();
			ContractDealVO cdvo = (ContractDealVO) cmBO.find(ContractDealVO.class, Long.parseLong(contractId));

			String sellerPwd = null;
			List<MetaFilter> params1 = new ArrayList<MetaFilter>();
			params1.add(new MetaFilter("contractID", "=", contractId));
			params1.add(new MetaFilter("serial", "=", 1));
			List<SellerInfoVO> list1 = cmBO.search(SellerInfoVO.class, params1);
			if(list1 != null && list1.size() > 0){
				SellerInfoVO svo = list1.get(0);
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("compID", "=", svo.getCompID()));
				params.add(new MetaFilter("projectID", "=", cdvo.getProjectID()));
				List<CompCancelPwdVO> list = cmBO.search(CompCancelPwdVO.class, params); //通过公司ID和项目ID查询合同甲方密码信息
				CompCancelPwdVO cpvo = list.get(0);
				sellerPwd = cpvo.getPwd();
			}

			request.setAttribute("contractId", contractId);
			request.setAttribute("sellerPwd", sellerPwd);
			request.setAttribute("list1", list1);
			return "/fhhouse/salecontract/manage/DeliverSellerPwdConfirm";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：乙方密码验证
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/deliverBuyerPwdConfirm")
	public String deliverBuyerPwdConfirm(HttpServletRequest request,HttpSession session) {
		try{
			String contractId = request.getParameter("cId");
			CompanyManageBO cmBO = new CompanyManageBO();

			String buyerPwd = null;
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("contractID", "=", contractId));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("serial", MetaOrder.ASC));

			List<BuyerInfoVO> list3 = cmBO.search(BuyerInfoVO.class, params, orders, null); //合同乙方

			List<ContractCancelPwdVO> list = cmBO.search(ContractCancelPwdVO.class, params, orders, null); //合同乙方撤销密码

			request.setAttribute("list", list);
			request.setAttribute("contractId", contractId);
			request.setAttribute("list3", list3);
			return "/fhhouse/salecontract/manage/DeliverBuyerPwdConfirm";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：房屋交接书新建成功
	 * @return
	 */
	@RequestMapping("/deliverApplyOK")
	public String deliverApplyOK(HttpServletRequest request,HttpSession session) {
		try{
			DeliverContractVO dcvo = (DeliverContractVO) session.getAttribute("dcvo");
			CompanyManageBO cmbo = new CompanyManageBO();
			//判断合同是否已经存在交接书，防止重复提交
			List<DeliverContractVO> deliverList = cmbo.queryDeliverByContract(dcvo.getContractID(), FHConstant.DELIVER_CONTRACT_STATUS_VALID);
			if(deliverList != null && deliverList.size() > 0){
				throw new Exception("签订房屋交接书失败,合同[" + dcvo.getContractID() + "]已经存在有效交接书！");
			}
			long deliverID = cmbo.add(dcvo);
			if(deliverID <= 0){
				throw new Exception("签订房屋交接书失败");
			}
			request.setAttribute("deliverID", deliverID);
			return "/fhhouse/salecontract/manage/DeliverApplyOK";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：撤销房屋交接书
	 * @return
	 */
	@RequestMapping("/deliverCancel")
	public String deliverCancel(HttpServletRequest request,HttpSession session) {
		try{
			request.setAttribute("cmd", "cancel");
			return "/fhhouse/salecontract/manage/DeliverApply";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：跳转到房屋交接书撤销页面
	 * @return
	 */
	@RequestMapping("/deliverCancelOK")
	public String deliverCancelOK(HttpServletRequest request,HttpSession session) {
		try{
			String contractId = request.getParameter("cId");
			CompanyManageBO cmBo = new CompanyManageBO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("contractID", "=", contractId));
			params.add(new MetaFilter("status", "=", FHConstant.DELIVER_CONTRACT_STATUS_VALID));
			List<DeliverContractVO> list = cmBo.search(DeliverContractVO.class, params);
			if(list != null && !list.isEmpty()){
				request.setAttribute("dcvo", list.get(0));
			}
			return "/fhhouse/salecontract/manage/DeliverCancelOK";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：交接书撤销成功
	 * @return
	 */
	@RequestMapping("/deliverCancelDone")
	public String deliverCancelDone(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String deliverID = request.getParameter("deliverID");
			CompanyManageBO cmBo = new CompanyManageBO();
			DeliverContractVO dcvo = new DeliverContractVO();
			dcvo.setDeliverID(Long.parseLong(deliverID));
			dcvo.setStatus(FHConstant.DELIVER_CONTRACT_STATUS_INVALID);
			dcvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			dcvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			dcvo.setUpdPerson(uservo.getLoginName());
			cmBo.updateDeliverContract(dcvo);
			return "redirect:/outer/manage/deliverCancel";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：查询房屋交接书
	 * @return
	 */
	@RequestMapping("/deliverQuery")
	public String deliverQuery(HttpServletRequest request,HttpSession session) {
		try{
			return "/fhhouse/salecontract/manage/DeliverQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：交接书列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/deliverQueryList")
	public String deliverQueryList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String contractID = request.getParameter("contractID");
			String buyer = request.getParameter("buyer");
			String startDate = request.getParameter("startDate");
			String overDate = request.getParameter("overDate");
			String startID = request.getParameter("startID");
			String startCode = request.getParameter("startCode");

			request.setAttribute("contractID", contractID);
			request.setAttribute("buyer", buyer);
			request.setAttribute("startDate", startDate);
			request.setAttribute("overDate", overDate);
			request.setAttribute("startID", startID);
			request.setAttribute("startCode", startCode);

			CompanyManageBO cmBo = new CompanyManageBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			//String districtList = this.getUserSqlDistricts(vo.getRegionId());

			List<DeliverContractVO> list = cmBo.queryDeliverContract(vo.getOrgID(), buyer, contractID, startDate, overDate, startCode, page);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("deliverID");
			List<String> linkparam1 = new ArrayList<String>();
			linkparam1.add("contractID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.addColumn("合同编号", "contractID", "doContractView", linkparam1);
			tableProperty.addColumn("房屋地址", "addr");
			tableProperty.addColumn("甲方", "seller");
			tableProperty.addColumn("乙方", "buyer");
			tableProperty.addColumn("确定时间", "confirmdate");
			tableProperty.addColumn("交接书状态", "status_dict_name");
			tableProperty.addColumn("查看", "查看", "doQueryView", linkparam, "查看", null);
			tableProperty.addColumn("交接书ID", "deliverID", true);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/salecontract/manage/DeliverQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

}
