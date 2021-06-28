package com.netcom.nkestate.fhhouse.query.action;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.query.bo.ContractPdfBO;
import com.netcom.nkestate.fhhouse.query.vo.ContractPdfVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractReportLogVO;
import com.netcom.nkestate.framework.util.Base64Util;
import com.netcom.nkestate.framework.util.DateUtil;


/**
 * 合同对外查看Action
 */
@Controller
@RequestMapping("/service/ContractView")
public class ContractViewAction extends BaseAction {


	/**
	 * 功能描述：合同pdf查看
	 * @param request
	 * @param response
	 */
	@RequestMapping("/viewContractPdf")
	public void viewContractPdf(HttpServletRequest request,HttpServletResponse response) {
		ContractPdfBO cpBo = new ContractPdfBO();
		String contractID = request.getParameter("contractID");//合同号
		String cid = request.getParameter("cid");//加密信息，规则yyyymmddhhmmss+合同号

		try{
			if(contractID == null || "".equals(contractID) || cid == null || "".equals(cid)){
				throw new Exception("合同pdf查看接口，请求参数非法！");
			}

			try{
				Long.parseLong(contractID);
			}catch (Exception e1){
				throw new Exception("合同pdf查看接口，请求合同号参数非法！");
			}
			
			try{
				String str = Base64Util.decode(cid);
				if(str == null || str.length() < 14){
					throw new Exception("解析加密信息出错！");
				}
				if(!contractID.equals(str.substring(14))){
					throw new Exception("解析加密信息与请求合同号不一致！");
				}

			}catch (Exception e1){
				throw new Exception("合同pdf查看接口，请求合同号验证未通过！" + e1.getMessage());
			}

			//获取合同处理信息
			ContractDealVO cdVo = (ContractDealVO) cpBo.find(ContractDealVO.class, Long.parseLong(contractID));
			if(cdVo == null || cdVo.getContractID() < 1){
				throw new Exception("合同pdf查看接口，无效合同号！");
			}

			if(cdVo.getType() == 3){
				throw new Exception("合同pdf查看接口，定金合同不能查看！");
			}

			if(cdVo.getStatus() != 2 && cdVo.getStatus() != 4){
				throw new Exception("合同pdf查看接口，无效合同不能查看！");
			}

			ContractPdfVO cpvo = null;
			if(cdVo.getType() == 1){
				//预售合同
				if(FHConstant.NEW_PC_CONTRACTVERSION.equals(cdVo.getContractversion())){
					cpvo = cpBo.createNewPresellContractPdf(request, cdVo.getContractID(), 0,0);
				}else{
					cpvo = cpBo.createPresellContractPdf(cdVo.getContractID(), 0,0);
				}
			}
			if(cdVo.getType() == 2){
				//出售合同
				if(FHConstant.NEW_SC_CONTRACTVERSION.equals(cdVo.getContractversion())){
					cpvo = cpBo.createNewSellContractPdf(request, cdVo.getContractID(), 0,0);
				}else{
					cpvo = cpBo.createSellContractPdf(cdVo.getContractID(), 0,0);
				}
			}
			if(cpvo == null){
				throw new Exception("合同pdf查看接口，pdf合同生成失败！");
			}
			OutputStream out = response.getOutputStream();
			out.write(cpvo.getPdfData());
			out.flush();
			out.close();

			//增加合同打印查看日志
			ContractReportLogVO logVO = new ContractReportLogVO();
			logVO.setContractID(cdVo.getContractID());
			logVO.setUserType(10);//预审查询请求
			logVO.setUserID(-999);
			logVO.setReportDate(DateUtil.getSysDate());
			cpBo.add(logVO);

			return;
		}catch (Exception e){
			e.printStackTrace();
			String msg = e.getMessage();
			try{
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('" + msg + "');</script>");
				return;
			}catch (Exception e2){
			}

		}

	}

}
