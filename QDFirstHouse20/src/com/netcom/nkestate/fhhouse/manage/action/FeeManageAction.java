package com.netcom.nkestate.fhhouse.manage.action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.vo.CompFundAccountVO;
import com.netcom.nkestate.fhhouse.company.vo.EnterpriseQualifyVO;
import com.netcom.nkestate.fhhouse.company.vo.ExchangeRecordVO;
import com.netcom.nkestate.fhhouse.manage.bo.FeeManageBO;
import com.netcom.nkestate.fhhouse.salecontract.bo.CompanyManageBO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ChargeVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.util.StringUtil;
import com.netcom.nkestate.system.vo.SmUserVO;

/**
 * 业务管理 -费用管理Action
 */
@Controller
@RequestMapping("/inner/feemanage")
public class FeeManageAction extends BaseAction {


	/**
	 * 功能描述：企业费用查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotoFeeQuery")
	public String gotoFeeQueryFrame(HttpServletRequest request) {
		try{
			return "fhhouse/manage/feemgr/FeeQueryFrame";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：企业费用查询列表
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoFeeQueryList")
	public String gotoFeeQuery(HttpServletRequest request,HttpServletResponse response,HttpSession session,Page page) {
		try{
			FeeManageBO fmBo = new FeeManageBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String exchangeID = request.getParameter("exchangeID");
			String legalManCode = request.getParameter("legalManCode");
			String operator = request.getParameter("operator");
			String feeRange = request.getParameter("feeRange");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			long compID = 0;

			request.setAttribute("exchangeID", exchangeID);
			request.setAttribute("legalManCode", legalManCode);
			request.setAttribute("operator", operator);
			request.setAttribute("feeRange", feeRange);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);

			List<Object> districtList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			List<EnterpriseQualifyVO> listE = null;
			if(legalManCode != null && !"".equals(legalManCode)){
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("bizdistrict", "in", districtList));
				params.add(new MetaFilter("legalManCode", "=", legalManCode));
				listE = fmBo.search(EnterpriseQualifyVO.class, params);
				if(listE != null && listE.size() > 0){
					compID = listE.get(0).getComp_ID();
				}else{
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write("<script>alert('未找到该法人信息或不能查找该法人信息!');</script>");
					return null;
				}
			}

			if(operator != null && !"".equals(operator)){
				Pattern pattern = Pattern.compile("[0-9]*");
				if(pattern.matcher(operator).matches()){
					operator = StringUtil.getFullwidthStr(operator, 10);
				}
			}
			String districtStr = this.getUserSqlDistricts(vo.getRegionId());
			List<ExchangeRecordVO> list = null;
			if(exchangeID != null && !"".equals(exchangeID)){
				long exchange_ID = Long.parseLong(exchangeID);
				list = fmBo.queryFeeByExchangeID(exchange_ID, districtStr, page);
			}else{
				list = fmBo.queryFeeByRange(operator, compID, feeRange, startDate, endDate, districtStr, page);
				if(list != null && list.size() > 0 && listE != null && listE.size() > 0){
					request.setAttribute("epqVoE", listE.get(0));
				}
			}
			
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(false);
			tableProperty.addColumn("流水号", "serial");
			tableProperty.addColumn("操作", "exType_dict_name");
			tableProperty.addColumn("发生金额(单位：元)", "exCount");
			tableProperty.addColumn("本次余额(单位：元)", "balance");
			tableProperty.addColumn("时间", "UpdateTimeStr");
			tableProperty.addColumn("操作帐号", "operator");

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "fhhouse/manage/feemgr/FeeQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：企业费用打印
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotoFeePrint")
	public String gotoFeePrint(HttpServletRequest request) {
		try{
			FeeManageBO fmBo = new FeeManageBO();
			CompanyManageBO cmBO = new CompanyManageBO();
			long id = Long.parseLong(request.getParameter("comp_Id"));

			EnterpriseQualifyVO epqVo = (EnterpriseQualifyVO) fmBo.find(EnterpriseQualifyVO.class, id);
			request.setAttribute("epqVo", epqVo);
			//账号查询
			CompFundAccountVO accountVO = cmBO.findCompFundAccount(id);
			//交费查询
			ExchangeRecordVO reservevo = cmBO.findCompExchangeRecord(id, FHConstant.EXCHANGE_TYPE_RESERVE);
			//退费查询
			ExchangeRecordVO refundvo = cmBO.findCompExchangeRecord(id, FHConstant.EXCHANGE_TYPE_REFUNDMENT);
			//使用查询
			ChargeVO chargeVO = cmBO.findCompCharge(id);

			request.setAttribute("EXCOUNT_ADD", reservevo.getExCount());//累计交纳金额
			request.setAttribute("CHARGECOUNT_ADD", chargeVO.getChargeCount());//已经使用金额
			request.setAttribute("FREEZEMONEY", accountVO.getFreezeMoney());//已经冻结金额
			request.setAttribute("EXCOUNT", refundvo.getExCount());//已经退款金额
			request.setAttribute("BALANCE", accountVO.getBalance());//当前可用余额
			request.setAttribute("sysTime", DateUtil.format(DateUtil.getSysDate(), "yyyy年MM月dd日  HH时mm分"));
			return "fhhouse/manage/feemgr/FeePrint";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
}
