package com.netcom.nkestate.fhhouse.queryFHE.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.vo.EnterpriseQualifyVO;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.fhhouse.project.vo.PresellVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.fhhouse.query.bo.ContractQueryBO;
import com.netcom.nkestate.fhhouse.query.vo.BuyerSearchLogVO;
import com.netcom.nkestate.fhhouse.salecontract.bo.ContractTemplateManageBO;
import com.netcom.nkestate.fhhouse.salecontract.vo.AttachTemplateVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.CancelCensorVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDetailCsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDetailYsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractTemplateCsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractTemplateYsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.DeliverContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.PresellContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellTemplateVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellerTemplateVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.bo.SystemBO;
import com.netcom.nkestate.system.vo.SmUserVO;


/**
 * 合同管理-合同查询 Action
 */
@Controller
@RequestMapping(value = "/inner/contractqueryFHE")
//@RequestMapping(value = "/fhe")
public class ContractQueryAction2 extends BaseAction {

	/**
	 * 功能描述：建设页面跳转
	 * @return
	 */
	@RequestMapping(value = "/contractQueryManageFHE")
	public String contractQueryManageFHE(HttpServletRequest request) {
		return "/Construction";
	}

	@RequestMapping(value = "/contractSignFHE")
	public void gotoBuyerSearch3(HttpServletRequest request) {

	}
	
	/**
	 * 功能描述：合同查询列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/contractManageFHE")
	public String contractManageFHE(HttpServletRequest request,HttpSession session,Page page) {
		System.out.println("执行了！！！");
		try{
			return "fhe/query/ContractQueryByBuyer";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	@RequestMapping(value = "/contractSignConfirmFHE")
	public void gotoBuyerSearch5(HttpServletRequest request) {

	}

	@RequestMapping(value = "/contractHouseFHE")
	public void gotoBuyerSearch6(HttpServletRequest request) {

	}


	/**
	 * 功能描述：通过乙方查询的合同列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoBuyerQueryListFHE")
	public String queryContractByBuyerFHE(HttpServletRequest request,HttpSession session,Page page) {
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			String buyername = request.getParameter("buyerName");
			String buyercardcode = request.getParameter("buyerCardcode");
			String buyerbirth = request.getParameter("buyerBirth");
			String buyernationality = request.getParameter("buyerNationality");
			String buyerproxy = request.getParameter("buyerProxy");
			String buyercardname = request.getParameter("buyerCardname");
			String buyerType = request.getParameter("buyerType");
			String buyerprovince = request.getParameter("buyerProvince");
			String buyeraddress = request.getParameter("buyerAddress");

			request.setAttribute("buyerName", buyername);
			request.setAttribute("buyerCardcode", buyercardcode);
			request.setAttribute("buyerBirth", buyerbirth);
			request.setAttribute("buyerNationality", buyernationality);
			request.setAttribute("buyerProxy", buyerproxy);
			request.setAttribute("buyerCardname", buyercardname);
			request.setAttribute("buyerType", buyerType);
			request.setAttribute("buyerProvince", buyerprovince);
			request.setAttribute("buyerAddress", buyeraddress);

			SmUserVO sVo = (SmUserVO) session.getAttribute("LgUser");

			//查询日志写入
			BuyerSearchLogVO logvo = new BuyerSearchLogVO();
			String ip = request.getHeader("x-forwarded-for");//查询ip
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
				ip = request.getHeader("Proxy-Client-IP");
			}
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
				ip = request.getRemoteAddr();
			}
			logvo.setIPAddress(ip);
			logvo.setSearchUserID(sVo.getUserId());
			logvo.setSearchUserName(sVo.getLoginName());
			logvo.setSearchDate(DateUtil.getSysDate());
			logvo.setBuyerName(buyername);
			logvo.setBuyerCardcode(buyercardcode);
			logvo.setBuyerBirth(buyerbirth);
			logvo.setBuyerNationality(buyernationality);
			logvo.setBuyerProvince(buyerprovince);
			logvo.setBuyerProxy(buyerproxy);
			logvo.setBuyerCardname(buyercardname);
			logvo.setBuyerType(buyerType);
			logvo.setBuyerAddress(buyeraddress);
			cqBo.add(logvo);

			String districtStr = this.getUserSqlDistricts(sVo.getRegionId());
			String buyercardcodeN15 = "";
			if(buyercardcode.length() == 18){
				buyercardcodeN15 = (buyercardcode.substring(0, 6) + buyercardcode.substring(8)).substring(0, 15);
			}

			//List<ContractDealVO> list = cqBo.queryContractDeal(buyername, buyercardcode, buyercardcodeN15, buyerbirth, buyernationality, buyerproxy, buyercardname, buyerType, buyerprovince, buyeraddress, districtStr, page);
			List<ContractDealVO> list = cqBo.queryContractDealFHE(buyername, buyercardcode, buyercardcodeN15, buyerbirth, buyernationality, buyerproxy, buyercardname, buyerType, buyerprovince, buyeraddress, districtStr, page);

			List<String> linkparam1 = new ArrayList<String>();
			linkparam1.add("contractID");
			linkparam1.add("signer");
			linkparam1.add("serial");
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("signer");
			List<String> linkparam2 = new ArrayList<String>();
			linkparam2.add("contractID");
			linkparam2.add("type");
			List<String> linkparam3 = new ArrayList<String>();
			linkparam3.add("contractID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(false);
			tableProperty.addSelectControl("radio", "selContractID", linkparam3, "");
			tableProperty.addColumn("合同编号", "contractID", "doSearchContract", linkparam1);
			tableProperty.addColumn("房屋地址", "location");
			tableProperty.addColumn("创建时间", "signDate1");
			tableProperty.addColumn("状态", "status_dict_Name");
			tableProperty.addColumn("签订账号", "loginname", "doSearchSigner", linkparam, null);
			tableProperty.addColumn("卖方", "sellerName");
			tableProperty.addColumn("买方", "buyerName");
			tableProperty.addColumn("合同文本", "合同文本", "doSearchText", linkparam2, "查看", null);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "fhhouse/query/ContractListByBuyer";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/**
	 * 功能描述：通过合同ID查询合同信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/gotoContractBasicInfoFHE")
	public String queryContractBasicInfoFHE(HttpServletRequest request,HttpServletResponse response) {
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			String contractID = request.getParameter("contractID");
			String signerID = request.getParameter("signerID");
			String serial = request.getParameter("serial");
			if(contractID != null && !"".equals(contractID)){
				ContractDealVO cdVo = (ContractDealVO) cqBo.find(ContractDealVO.class, Long.parseLong(contractID));
				request.setAttribute("cdVo", cdVo);
				String confirmDateStr = DateUtil.formatDateTime(DateUtil.parseDateTime2(String.valueOf(cdVo.getConfirmDate())));
				String signDateStr = DateUtil.formatDateTime(DateUtil.parseDateTime2(String.valueOf(cdVo.getSignDate())));
				request.setAttribute("confirmDateStr", confirmDateStr);
				request.setAttribute("signDateStr", signDateStr);

				if(signerID != null && !"".equals(signerID)){
					SignerVO sVo = (SignerVO) cqBo.find(SignerVO.class, Long.parseLong(signerID));
					if(sVo != null){
						EnterpriseQualifyVO epqVo = (EnterpriseQualifyVO) cqBo.find(EnterpriseQualifyVO.class, Long.parseLong(sVo.getComp_ID()));
						request.setAttribute("eqVo", epqVo);
					}
				}

				if(serial != null && !"".equals(serial)){
					BuyerInfoVO bVo = new BuyerInfoVO();
					bVo.setContractID(Long.parseLong(contractID));
					bVo.setSerial(Integer.parseInt(serial));
					BuyerInfoVO biVo = (BuyerInfoVO) cqBo.find(bVo);
					request.setAttribute("biVo", biVo);
					request.setAttribute("buyercardname", biVo.getAttribute("buyer_cardname_dict_name"));
				}

				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("contractID", "=", Long.parseLong(contractID)));
				List<CancelCensorVO> list = cqBo.search(CancelCensorVO.class, params);
				if(list != null && list.size() > 0){
					CancelCensorVO ccVo = list.get(0);
					request.setAttribute("CancelCensorVO", ccVo);
				}

				return "fhhouse/query/ContractBasicInfo";
			}else{
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('未找到该合同信息!');</script>");
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	/**
	 * 功能描述：转到合同乙方查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotoBuyerQuery")
	public String gotoBuyerSearch(HttpServletRequest request) {
		try{
			return "fhhouse/query/ContractQueryByBuyer";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/**
	 * 功能描述：通过乙方查询的合同列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoBuyerQueryList")
	public String queryContractByBuyer(HttpServletRequest request,HttpSession session,Page page) {
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			String buyername = request.getParameter("buyerName");
			String buyercardcode = request.getParameter("buyerCardcode");
			String buyerbirth = request.getParameter("buyerBirth");
			String buyernationality = request.getParameter("buyerNationality");
			String buyerproxy = request.getParameter("buyerProxy");
			String buyercardname = request.getParameter("buyerCardname");
			String buyerType = request.getParameter("buyerType");
			String buyerprovince = request.getParameter("buyerProvince");
			String buyeraddress = request.getParameter("buyerAddress");

			request.setAttribute("buyerName", buyername);
			request.setAttribute("buyerCardcode", buyercardcode);
			request.setAttribute("buyerBirth", buyerbirth);
			request.setAttribute("buyerNationality", buyernationality);
			request.setAttribute("buyerProxy", buyerproxy);
			request.setAttribute("buyerCardname", buyercardname);
			request.setAttribute("buyerType", buyerType);
			request.setAttribute("buyerProvince", buyerprovince);
			request.setAttribute("buyerAddress", buyeraddress);

			SmUserVO sVo = (SmUserVO) session.getAttribute("LgUser");

			//查询日志写入
			BuyerSearchLogVO logvo = new BuyerSearchLogVO();
			String ip = request.getHeader("x-forwarded-for");//查询ip
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
				ip = request.getHeader("Proxy-Client-IP");
			}
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
				ip = request.getRemoteAddr();
			}
			logvo.setIPAddress(ip);
			logvo.setSearchUserID(sVo.getUserId());
			logvo.setSearchUserName(sVo.getLoginName());
			logvo.setSearchDate(DateUtil.getSysDate());
			logvo.setBuyerName(buyername);
			logvo.setBuyerCardcode(buyercardcode);
			logvo.setBuyerBirth(buyerbirth);
			logvo.setBuyerNationality(buyernationality);
			logvo.setBuyerProvince(buyerprovince);
			logvo.setBuyerProxy(buyerproxy);
			logvo.setBuyerCardname(buyercardname);
			logvo.setBuyerType(buyerType);
			logvo.setBuyerAddress(buyeraddress);
			cqBo.add(logvo);

			String districtStr = this.getUserSqlDistricts(sVo.getRegionId());
			String buyercardcodeN15 = "";
			if(buyercardcode.length() == 18){
				buyercardcodeN15 = (buyercardcode.substring(0, 6) + buyercardcode.substring(8)).substring(0, 15);
			}

			List<ContractDealVO> list = cqBo.queryContractDeal(buyername, buyercardcode, buyercardcodeN15, buyerbirth, buyernationality, buyerproxy, buyercardname, buyerType, buyerprovince, buyeraddress, districtStr, page);
			List<String> linkparam1 = new ArrayList<String>();
			linkparam1.add("contractID");
			linkparam1.add("signer");
			linkparam1.add("serial");
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("signer");
			List<String> linkparam2 = new ArrayList<String>();
			linkparam2.add("contractID");
			linkparam2.add("type");
			List<String> linkparam3 = new ArrayList<String>();
			linkparam3.add("contractID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(false);
			tableProperty.addSelectControl("radio", "selContractID", linkparam3, "");
			tableProperty.addColumn("合同编号", "contractID", "doSearchContract", linkparam1);
			tableProperty.addColumn("房屋地址", "location");
			tableProperty.addColumn("创建时间", "signDate1");
			tableProperty.addColumn("状态", "status_dict_Name");
			tableProperty.addColumn("签订账号", "loginname", "doSearchSigner", linkparam, null);
			tableProperty.addColumn("卖方", "sellerName");
			tableProperty.addColumn("买方", "buyerName");
			tableProperty.addColumn("合同文本", "合同文本", "doSearchText", linkparam2, "查看", null);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "fhhouse/query/ContractListByBuyer";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：通过签约人ID查询签约人信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/gotoSignerInfo")
	public String querySignerInfo(HttpServletRequest request,HttpServletResponse response) {
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			String signerID = request.getParameter("signerID");
			if(signerID != null && !"".equals(signerID)){
				SignerVO sVo = (SignerVO) cqBo.find(SignerVO.class, Long.parseLong(signerID));
				request.setAttribute("signerID", signerID);
				request.setAttribute("signer", sVo);
				EnterpriseQualifyVO eqVo = (EnterpriseQualifyVO) cqBo.find(EnterpriseQualifyVO.class, Long.parseLong(sVo.getComp_ID()));
				request.setAttribute("eqVo", eqVo);
				return "fhhouse/query/SignerInfo";
			}else{
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('未找到该签约人信息!');</script>");
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：通过企业ID查询企业信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotoCompanyInfo")
	public String queryCompanyInfo(HttpServletRequest request,HttpServletResponse response) {
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			String comp_ID = request.getParameter("compID");
			String signerID = request.getParameter("signerID");
			if(comp_ID != null && !"".equals(comp_ID)){
				EnterpriseQualifyVO epqVo = (EnterpriseQualifyVO) cqBo.find(EnterpriseQualifyVO.class, Long.parseLong(comp_ID));
				request.setAttribute("epqVo", epqVo);
				if(epqVo.getAttribute("status_dict_name") != null){
					request.setAttribute("status", epqVo.getAttribute("status_dict_name"));
				}
				request.setAttribute("signerID", signerID);
				return "fhhouse/query/CompanyInfo";
			}else{
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('未找到该企业信息!');</script>");
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：通过合同ID查询合同信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/gotoContractBasicInfo")
	public String queryContractBasicInfo(HttpServletRequest request,HttpServletResponse response) {
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			String contractID = request.getParameter("contractID");
			String signerID = request.getParameter("signerID");
			String serial = request.getParameter("serial");
			if(contractID != null && !"".equals(contractID)){
				ContractDealVO cdVo = (ContractDealVO) cqBo.find(ContractDealVO.class, Long.parseLong(contractID));
				request.setAttribute("cdVo", cdVo);
				String confirmDateStr = DateUtil.formatDateTime(DateUtil.parseDateTime2(String.valueOf(cdVo.getConfirmDate())));
				String signDateStr = DateUtil.formatDateTime(DateUtil.parseDateTime2(String.valueOf(cdVo.getSignDate())));
				request.setAttribute("confirmDateStr", confirmDateStr);
				request.setAttribute("signDateStr", signDateStr);

				if(signerID != null && !"".equals(signerID)){
					SignerVO sVo = (SignerVO) cqBo.find(SignerVO.class, Long.parseLong(signerID));
					if(sVo != null){
						EnterpriseQualifyVO epqVo = (EnterpriseQualifyVO) cqBo.find(EnterpriseQualifyVO.class, Long.parseLong(sVo.getComp_ID()));
						request.setAttribute("eqVo", epqVo);
					}
				}

				if(serial != null && !"".equals(serial)){
					BuyerInfoVO bVo = new BuyerInfoVO();
					bVo.setContractID(Long.parseLong(contractID));
					bVo.setSerial(Integer.parseInt(serial));
					BuyerInfoVO biVo = (BuyerInfoVO) cqBo.find(bVo);
					request.setAttribute("biVo", biVo);
					request.setAttribute("buyercardname", biVo.getAttribute("buyer_cardname_dict_name"));
				}

				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("contractID", "=", Long.parseLong(contractID)));
				List<CancelCensorVO> list = cqBo.search(CancelCensorVO.class, params);
				if(list != null && list.size() > 0){
					CancelCensorVO ccVo = list.get(0);
					request.setAttribute("CancelCensorVO", ccVo);
				}

				return "fhhouse/query/ContractBasicInfo";
			}else{
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('未找到该合同信息!');</script>");
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：跳转到合同查询页面
	 * @return
	 */
	@RequestMapping("/gotoSearchContract")
	public String gotoSearchContract() {
		try{
			return "fhe/query/sellcontract/ContractQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：合同查询列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/searchContract")
	public String SearchContract(HttpServletRequest request,HttpSession session,Page page) {
		ContractQueryBO cqBo = new ContractQueryBO();
		String cmd = request.getParameter("cmd");
		SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
		String districtList = this.getUserSqlDistricts(uservo.getRegionId());
		//合同编号查询参数
		String contractID = request.getParameter("contractID");
		request.setAttribute("contractID", contractID);
		//项目信息查询参数
		String startID = request.getParameter("startID");
		String projectName = request.getParameter("projectName");
		String signDate1 = request.getParameter("signDate1");
		String signDate2 = request.getParameter("signDate2");
		String type1 = request.getParameter("type1");
		String confirmDate1 = request.getParameter("confirmDate1");
		String confirmDate2 = request.getParameter("confirmDate2");
		String status1 = request.getParameter("status1");
		String district = request.getParameter("district");
		String road = request.getParameter("road");
		//System.out.println(district);
		String laneName = request.getParameter("laneName");
		String subLane = request.getParameter("subLane");
		String buildingNumber = request.getParameter("buildingNumber");

		request.setAttribute("startID", startID);
		request.setAttribute("projectName", projectName);
		request.setAttribute("signDate1", signDate1);
		request.setAttribute("signDate2", signDate2);
		request.setAttribute("type1", type1);
		request.setAttribute("confirmDate1", confirmDate1);
		request.setAttribute("confirmDate2", confirmDate2);
		request.setAttribute("status1", status1);
		request.setAttribute("district", district);
		request.setAttribute("road", road);
		request.setAttribute("laneName", laneName);
		request.setAttribute("subLane", subLane);
		request.setAttribute("buildingNumber", buildingNumber);
		//甲方信息查询参数
		String sellerName = request.getParameter("sellerName");
		String compCode = request.getParameter("comp_Code");
		String sellerAddress = request.getParameter("sellerAddress");
		String sellerCertcode = request.getParameter("sellerCertcode");
		String sellerDlgCall = request.getParameter("sellerDlgCall");
		String sellerDelegate = request.getParameter("sellerDelegate");
		String sellerProxy = request.getParameter("sellerProxy");
		String signDate3 = request.getParameter("signDate3");
		String signDate4 = request.getParameter("signDate4");
		String type2 = request.getParameter("type2");
		String confirmDate3 = request.getParameter("confirmDate3");
		String confirmDate4 = request.getParameter("confirmDate4");
		String status2 = request.getParameter("status2");

		request.setAttribute("sellerName", sellerName);
		request.setAttribute("compCode", compCode);
		request.setAttribute("sellerAddress", sellerAddress);
		request.setAttribute("sellerCertcode", sellerCertcode);
		request.setAttribute("sellerDlgCall", sellerDlgCall);
		request.setAttribute("sellerDelegate", sellerDelegate);
		request.setAttribute("sellerProxy", sellerProxy);
		request.setAttribute("signDate3", signDate3);
		request.setAttribute("signDate4", signDate4);
		request.setAttribute("type2", type2);
		request.setAttribute("confirmDate3", confirmDate3);
		request.setAttribute("confirmDate4", confirmDate4);
		request.setAttribute("status2", status2);

		List<ContractDealVO> list = null;
		try{
			//合同号查询
			if("0".equals(cmd)){
				list = cqBo.findContractsByContract(page, contractID, districtList);
			}
			//项目查询
			if("1".equals(cmd)){
				list = cqBo.findContractsByProject(page, startID, projectName, signDate1, signDate2, type1, confirmDate1, confirmDate2, status1, district, road, laneName, subLane, buildingNumber, districtList);
			}
			//甲方查询
			if("2".equals(cmd)){
				list = cqBo.findContractsBySeller(page, sellerName, compCode, sellerAddress, sellerCertcode, sellerDlgCall, sellerDelegate, sellerProxy, signDate3, signDate4, type2, confirmDate3, confirmDate4, status2, districtList);
			}
			List<String> linkparam1 = new ArrayList<String>();
			linkparam1.add("contractID");
			linkparam1.add("type");
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("signer");
			List<String> linkparam2 = new ArrayList<String>();
			linkparam2.add("contractID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addSelectControl("radio", "selContractID", linkparam2, "");
			tableProperty.addColumn("合同编号", "contractID", "doSearchContract", linkparam1);
			tableProperty.addColumn("房屋地址", "location");
			tableProperty.addColumn("创建时间", "signDate1");
			tableProperty.addColumn("状态", "status_dict_Name");
			tableProperty.addColumn("签订账号", "loginname", "doSearchSigner", linkparam);
			tableProperty.addColumn("卖方", "sellerName");
			tableProperty.addColumn("买方", "buyerName");
			tableProperty.addColumn("合同文本", "合同文本", "doSearchText", linkparam1, "查看", null);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("cmd", cmd);
			request.setAttribute("htmlView", htmlView);
			return "fhhouse/query/sellcontract/ContractQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：通过合同ID查询合同信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/basicInfoContract")
	public String gotoContractBasicInfo(HttpServletRequest request,HttpServletResponse response) {
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			SystemBO sysBo = new SystemBO();
			String contractID = request.getParameter("contractID"); //合同ID
			String type = request.getParameter("type"); //合同类型
			if(contractID != null && !"".equals(contractID)){
				ContractDealVO cdVo = (ContractDealVO) cqBo.find(ContractDealVO.class, Long.parseLong(contractID));
				request.setAttribute("cdVo", cdVo); //合同信息
				request.setAttribute("landusestr", cdVo.getAttribute("LAND_USE_CODE_dict_name"));
				String confirmDateStr = DateUtil.formatDateTime(DateUtil.parseDateTime2(String.valueOf(cdVo.getConfirmDate())));
				String signDateStr = DateUtil.formatDateTime(DateUtil.parseDateTime2(String.valueOf(cdVo.getSignDate())));
				request.setAttribute("confirmDateStr", confirmDateStr); //签约时间
				request.setAttribute("signDateStr", signDateStr); //草签时间
				if("1".equals(type)){
					if(FHConstant.NEW_PC_CONTRACTVERSION.equals(cdVo.getContractversion())){
						ContractDetailYsVO cdvo = (ContractDetailYsVO) cqBo.find(ContractDetailYsVO.class, Long.parseLong(contractID));
						if(cdvo != null){
							request.setAttribute("presellDesc", cdvo.getf0202()); //预售许可证
							request.setAttribute("houseDesc", cdvo.getf0104()); //房地产权证号
							request.setAttribute("landArea", cdvo.getf0105()); //土地面积
							request.setAttribute("houseType", cdvo.getf0302()); //结构
						}
					}else{
						PresellContractVO pcvo = (PresellContractVO) cqBo.find(PresellContractVO.class, Long.parseLong(contractID));
						if(pcvo != null){
							request.setAttribute("presellDesc", pcvo.getN1_11()); //预售许可证
							request.setAttribute("houseDesc", pcvo.getN1_3()); //房地产权证号
							request.setAttribute("landArea", pcvo.getN1_4()); //土地面积
							request.setAttribute("houseType", pcvo.getN1_7()); //结构
						}
					}
				}
				if("2".equals(type)){
					if(FHConstant.NEW_SC_CONTRACTVERSION.equals(cdVo.getContractversion())){
						ContractDetailCsVO cdvo = (ContractDetailCsVO) cqBo.find(ContractDetailCsVO.class, Long.parseLong(contractID));
						if(cdvo != null){
							request.setAttribute("houseDesc", cdvo.getf0104()); //房地产权证号						
						}
					}else{
					SellContractVO scvo = (SellContractVO) cqBo.find(SellContractVO.class, Long.parseLong(contractID));
					if(scvo != null)
							request.setAttribute("houseDesc", scvo.getN1_5()); //房地产权证号
					}
				}
				List<MetaFilter> params1 = new ArrayList<MetaFilter>();
				params1.add(new MetaFilter("contractID", "=", contractID));
				List<MetaOrder> orders1 = new ArrayList<MetaOrder>();
				orders1.add(new MetaOrder("serial", MetaOrder.ASC));
				List<SellerInfoVO> sellerList = cqBo.search(SellerInfoVO.class, params1, orders1, null);
				if(sellerList != null && sellerList.size() > 0){
					SellerInfoVO svo = sellerList.get(0);
					request.setAttribute("svo", svo); //甲方信息
				}

				List<MetaFilter> params2 = new ArrayList<MetaFilter>();
				params2.add(new MetaFilter("contractID", "=", contractID));
				List<MetaOrder> orders2 = new ArrayList<MetaOrder>();
				orders2.add(new MetaOrder("serial", MetaOrder.ASC));
				List<BuyerInfoVO> buyerList = cqBo.search(BuyerInfoVO.class, params2, orders2, null);
				String buyerCardName = "";
				if(buyerList != null && buyerList.size() > 0){
					BuyerInfoVO bvo = buyerList.get(0);
					request.setAttribute("bvo", bvo); //乙方信息(第一个)
					buyerCardName = bvo.getAttribute("BUYER_CARDNAME_dict_name").toString();
				}
				request.setAttribute("buyerCardName", buyerCardName);

				List<MetaFilter> params3 = new ArrayList<MetaFilter>();
				params3.add(new MetaFilter("contractID", "=", contractID));
				List<CancelCensorVO> cancelList = cqBo.search(CancelCensorVO.class, params3);
				if(cancelList != null && cancelList.size() > 0){
					CancelCensorVO cvo = cancelList.get(0);

					try{
						String firstCensor = cvo.getFirstCensor();
						long userId = Long.parseLong(firstCensor);
						SmUserVO uvo = sysBo.findUserInfo(userId);
						if(uvo != null){
							cvo.setFirstCensor(uvo.getDisplayName());
						}
					}catch (Exception e){
					}

					try{
						String finalCensor = cvo.getFinalCensor();
						long userId = Long.parseLong(finalCensor);
						SmUserVO uvo = sysBo.findUserInfo(userId);
						if(uvo != null){
							cvo.setFinalCensor(uvo.getDisplayName());
						}
					}catch (Exception e){
					}
					request.setAttribute("cvo", cvo); //撤销信息
					request.setAttribute("finalDate", cvo.getFinalDate1()); //撤销日期
				}

				request.setAttribute("type", type);
				return "fhhouse/query/sellcontract/ContractInfo";
			}else{
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('未找到该合同信息!');</script>");
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：接收ID并跳转到选择小微企业页面
	 */
	@RequestMapping(value = "/selectApplicationPrint")
	public String selectApplicationPrint(HttpServletRequest request, HttpSession session, Page page, long contractID) throws Exception {
		request.setAttribute("contractID", contractID);
		return "fhhouse/query/sellcontract/SelectApplicationPrint";
	}
	
	/**
	 * 功能描述：打印登记表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/registerPrint")
	public String gotoPrintRegister(HttpServletRequest request,HttpServletResponse response,String checkBox,String radio) {
		try{
			if("1".equals(checkBox)){
				request.setAttribute("checkBox", 1);
			}else {
				request.setAttribute("checkBox", 2);
			}
			if("1".equals(radio)){
				request.setAttribute("radio", 1);
			}else if("2".equals(radio)){
				request.setAttribute("radio", 2);
			}else {
				request.setAttribute("radio", 3);
			}
			ContractQueryBO cqBo = new ContractQueryBO();
			String contractID = request.getParameter("contractID");
			if(contractID != null && !"".equals(contractID)){
				ContractDealVO cdVo = (ContractDealVO) cqBo.find(ContractDealVO.class, Long.parseLong(contractID));
				request.setAttribute("cdVo", cdVo);

				List<MetaFilter> params1 = new ArrayList<MetaFilter>();
				params1.add(new MetaFilter("contractID", "=", contractID));
				List<SellerInfoVO> sellerList = cqBo.search(SellerInfoVO.class, params1);
				if(sellerList != null && sellerList.size() > 0){
					SellerInfoVO svo = sellerList.get(sellerList.size() - 1);
					request.setAttribute("svo", svo);
				}

				List<MetaFilter> params2 = new ArrayList<MetaFilter>();
				params2.add(new MetaFilter("contractID", "=", contractID));
				List<MetaOrder> orders = new ArrayList<MetaOrder>();
				orders.add(new MetaOrder("serial", MetaOrder.ASC));
				List<BuyerInfoVO> buyerList = cqBo.search(BuyerInfoVO.class, params2, orders, null);
				String memo = "";
				String buyerCardName = "";
				if(buyerList != null && buyerList.size() > 0){
					BuyerInfoVO bvo = buyerList.get(0);
					request.setAttribute("bvo", bvo);
					buyerCardName = bvo.getAttribute("BUYER_CARDNAME_dict_name").toString();
					if(buyerList.size() > 1){
						for(int i = 1; i < buyerList.size(); i++){
							memo = memo + "、" + buyerList.get(i).getBuyerName();
						}
						memo = "其他共有人：" + memo.substring(1);
					}

				}
				request.setAttribute("buyerCardName", buyerCardName);
				request.setAttribute("memo", memo);

			}
			return "fhhouse/query/sellcontract/ContractApply";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：跳转到交接书查询页面
	 * @return
	 */
	@RequestMapping("/queryDeliverContract")
	public String queryDeliverContract(HttpServletRequest request,Page page) {
		try{
			return "fhhouse/query/DeliverContractQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：交接书列表
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/queryDeliverContractList")
	public String queryDeliverContractList(HttpServletRequest request,Page page) {
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			String contractID = request.getParameter("contractID");
			String status = request.getParameter("status");
			String seller = request.getParameter("seller");
			String buyer = request.getParameter("buyer");
			String startDate = request.getParameter("startDate");
			String overDate = request.getParameter("overDate");
			
			List<DeliverContractVO> list = cqBo.queryDeliverContract(page, contractID, seller, buyer, status, startDate, overDate);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("contractID");
			List<String> linkparam1 = new ArrayList<String>();
			linkparam1.add("deliverID");

			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addSelectControl("radio", "contractID", linkparam, null);
			tableProperty.addColumn("合同编号", "contractID");
			tableProperty.addColumn("房屋地址", "addr");
			tableProperty.addColumn("卖方", "seller");
			tableProperty.addColumn("买方", "buyer");
			tableProperty.addColumn("确认时间", "confirmdate");
			tableProperty.addColumn("合同查看", "合同文本", "doSearchContract", linkparam, "查看", null);
			tableProperty.addColumn("交接书查看", "交接书文本", "doSearchDeliver", linkparam1, "查看", null);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			request.setAttribute("contractID", contractID);
			request.setAttribute("status", status);
			request.setAttribute("seller", seller);
			request.setAttribute("buyer", buyer);
			request.setAttribute("startDate", startDate);
			request.setAttribute("overDate", overDate);

			return "fhhouse/query/DeliverContractQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：转到合同主模板查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryContractTemplate")
	public String queryContractTemplate(HttpServletRequest request) {
		try{
			return "fhhouse/query/ContractTemplateQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：查询合同主模板列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryContractTemplateList")
	public String queryContractTemplateList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			String compID = request.getParameter("comp_ID");
			String legalManCode = request.getParameter("legalManCode");
			String projectName = request.getParameter("projectName");
			String compName = request.getParameter("compName");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String startID = request.getParameter("startID");

			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String district = this.getUserSqlDistricts(vo.getRegionId());
			List<SellTemplateVO> list = cqBo.queryTemplateList(compID, legalManCode, projectName, compName, startDate, endDate, startID, district, page);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("templateID");
			linkparam.add("type");

			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("项目名称", "projectname");
			tableProperty.addColumn("开盘编号", "start_code");
			tableProperty.addColumn("模板名称", "tempname");
			tableProperty.addColumn("模板类型", "typename");
			tableProperty.addColumn("修改时间", "updateTime", "Date", "yyyy-MM-dd", null);
			tableProperty.addColumn("查看", "查看", "viewTemp", linkparam, "查看", null);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "fhhouse/query/ContractTemplateList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：主模板查看
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/viewTemplate")
	public String viewTemplate(HttpServletRequest request,HttpSession session) {
		try{
			String templateID = request.getParameter("templateID");
			String type = request.getParameter("type");
			request.setAttribute("templateID", templateID);
			request.setAttribute("type", type);
			ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();

			String opera = "view";
			request.setAttribute("opera", opera);
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");

			EnterpriseQualifyVO eqvo = new EnterpriseQualifyVO();

			if(templateID != null && !"".equals(templateID)){
				//从甲方模板中获取甲方信息
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("templateID", "=", templateID));
				List<SellerTemplateVO> sertempList = ctmBo.search(SellerTemplateVO.class, params);
				if(sertempList != null && sertempList.size() > 0){
					SellerTemplateVO sertempVo = sertempList.get(0);
					request.setAttribute("sellerID", sertempVo.getID()); //获取合同甲方模板ID
					if(opera != null && "view".equals(opera)){ //当查看合同模板时，从甲方模板中取值，当新增或修改时从企业表取值
						eqvo.setName(sertempVo.getSellerName());
						eqvo.setComp_ID(sertempVo.getCompID());
						eqvo.setBizadr(sertempVo.getSellerAddress());
						eqvo.setPost(sertempVo.getSellerPostcode());
						eqvo.setBizregister_Num(sertempVo.getSellerBizcardcode());
						eqvo.setAptitudeNum(sertempVo.getSellerCertcode());
						eqvo.setDelegate(sertempVo.getSellerDelegate());
						eqvo.setDlg_Call(sertempVo.getSellerDlgCall());
						eqvo.setProxy(sertempVo.getSellerProxy());
						eqvo.setProxy_Call(sertempVo.getSellerProxyCall());
					}else{
						//从企业表中获取甲方信息
						SignerVO signerVO = (SignerVO) ctmBo.find(SignerVO.class, vo.getUserId());
						eqvo = (EnterpriseQualifyVO) ctmBo.find(EnterpriseQualifyVO.class, Long.parseLong(signerVO.getComp_ID()));
					}
				}else{
					//从企业表中获取甲方信息
					SignerVO signerVO = (SignerVO) ctmBo.find(SignerVO.class, vo.getUserId());
					eqvo = (EnterpriseQualifyVO) ctmBo.find(EnterpriseQualifyVO.class, Long.parseLong(signerVO.getComp_ID()));
				}
				request.setAttribute("eqvo", eqvo);

				//获取附件模板信息
				List<AttachTemplateVO> atList = ctmBo.search(AttachTemplateVO.class, params);
				Map<String , String> map = new HashMap<String , String>();
				if(atList != null && atList.size() > 0){
					for(AttachTemplateVO attachTemplateVO : atList){
						if(attachTemplateVO.getContent() != null){
							map.put(attachTemplateVO.getSerial() + "", new String(attachTemplateVO.getContent()));
						}
					}
				}
				request.setAttribute("attachmap", map);

				if(type != null && "1".equals(type)){
					//获取预售合同模板
					ContractTemplateYsVO ptVo = (ContractTemplateYsVO) ctmBo.find(ContractTemplateYsVO.class, Long.parseLong(templateID));

					if(ptVo != null && ptVo.getStartID() > 0){
						request.setAttribute("ptVo", ptVo);
						//获取项目的区县编号
						request.setAttribute("proDis", ctmBo.findProDis(ptVo.getProjectID() + ""));
						//获取项目名称
						ProjectVO projectVO = (ProjectVO) ctmBo.find(ProjectVO.class, ptVo.getProjectID());
						request.setAttribute("projectName", projectVO.getProjectName());
						//获取开盘单元，许可证信息
						StartUnitVO suVo = (StartUnitVO) ctmBo.find(StartUnitVO.class, ptVo.getStartID());
						if(suVo != null && suVo.getPresell_ID() > 0){
							PresellVO presellVO = (PresellVO) ctmBo.find(PresellVO.class, suVo.getPresell_ID());
							request.setAttribute("presellDesc", presellVO.getPresell_Desc());
						}
						return "/fhhouse/salecontract/signcontract/ContractTemplateYS";
					}else{
						return null;
					}
				}else{
					//获取出售合同模板
					ContractTemplateCsVO stVo = (ContractTemplateCsVO) ctmBo.find(ContractTemplateCsVO.class, Long.parseLong(templateID));

					if(stVo != null && stVo.getStartID() > 0){
						request.setAttribute("stVo", stVo);
						//获取项目的区县编号
						request.setAttribute("proDis", ctmBo.findProDis(stVo.getProjectID() + ""));
						//获取项目名称
						ProjectVO projectVO = (ProjectVO) ctmBo.find(ProjectVO.class, stVo.getProjectID());
						request.setAttribute("projectName", projectVO.getProjectName());
						//获取开盘单元，许可证信息
						StartUnitVO suVo = (StartUnitVO) ctmBo.find(StartUnitVO.class, stVo.getStartID());
						if(suVo != null && suVo.getPresell_ID() > 0){
							PresellVO presellVO = (PresellVO) ctmBo.find(PresellVO.class, suVo.getPresell_ID());
							request.setAttribute("presellDesc", presellVO.getPresell_Desc());
						}
						return "/fhhouse/salecontract/signcontract/ContractTemplateCS";
					}else{
						return null;
					}
				}
			}else{
				return ERROR_System;
			}
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


}
