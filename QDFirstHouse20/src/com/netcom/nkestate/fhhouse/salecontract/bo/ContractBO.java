/**
 * <p>ContractBO.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 合同签约BO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.salecontract.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.salecontract.dao.ContractDAO;
import com.netcom.nkestate.fhhouse.salecontract.vo.CancelCensorVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.PresellManageContractConfirVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.vo.SmUserVO;


public class ContractBO extends MiniBO {

	static Logger logger = Logger.getLogger(ContractBO.class.getName());

	private ContractDAO contractDAO = new ContractDAO();

	/*
	 * 合同撤销复核
	 */
	public List<CancelCensorVO> findCancelFirstAudit(Page page,String contractId,String sellerName,String status,String buyerName,String districtList) throws Exception {
		try{
			openDAO(contractDAO);
			List<CancelCensorVO> list = contractDAO.findCancelFirstAudit(page, contractId, sellerName, status, buyerName, districtList);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(contractDAO);
		}

	}

	/*
	 * 合同撤销审批
	 */
	public List<CancelCensorVO> findCancelSecondAudit(Page page,String contractId,String sellerName,String status,String buyerName,String reason,String confirmDate1,String confirmDate2,String districtList) throws Exception {
		try{
			openDAO(contractDAO);
			List<CancelCensorVO> list = contractDAO.findCancelSecondAudit(page, contractId, sellerName, status, buyerName, reason, confirmDate1, confirmDate2, districtList);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(contractDAO);
		}
	}

	public List<CancelCensorVO> findCensor(String id) throws Exception {
		try{
			openDAO(contractDAO);
			List<CancelCensorVO> list = contractDAO.findCensor(id);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(contractDAO);
		}
	}

	/**
	 * 功能描述：合同撤销发布提交
	 * @param id
	 * @param contractId
	 * @param option
	 * @param smUserVO
	 * @return
	 * @throws Exception
	 */
	public boolean doThirdSubmit(long id,long contractId,String option,SmUserVO smUserVO) throws Exception {
		try{
			openDAO(contractDAO);
			contractDAO.setTransaction();

			CancelCensorVO ccvo = (CancelCensorVO) contractDAO.find(CancelCensorVO.class, id);
			ContractDealVO cdvo = (ContractDealVO) contractDAO.find(ContractDealVO.class, contractId);
			PresellManageContractConfirVO pmccVo = (PresellManageContractConfirVO) contractDAO.find(PresellManageContractConfirVO.class, contractId);
			HouseVO hvo = (HouseVO) contractDAO.find(HouseVO.class, cdvo.getHouseID());
			if("1".equals(option)){
				ccvo.setStatus(FHConstant.CONTRACT_JUDGE_STATUS_PERMITTED);
				ccvo.setPublishNotion("同意");
				//合同状态变化
				cdvo.setStatus(FHConstant.CONTRACT_STATUS_ABOLISH);
				cdvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				cdvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				cdvo.setUpdPerson(smUserVO.getLoginName());
				//房屋状态变化
				hvo.setStatus(FHConstant.HOUSE_STATUS_CANSELL);
				hvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				hvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				hvo.setUpdPerson(smUserVO.getLoginName());
				if(pmccVo != null){
					pmccVo.setDataKind("0");
					pmccVo.setConfirmTime(DateUtil.parseDateTime2(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()));
					contractDAO.update2(pmccVo);
				}
			}
			if("2".equals(option)){
				ccvo.setStatus(FHConstant.CONTRACT_JUDGE_STATUS_NOPERMITTED);
				ccvo.setPublishNotion("不同意");
			}
			ccvo.setPublishCensor(String.valueOf(smUserVO.getUserId()));
			ccvo.setPublishDate(DateUtil.parseDate(DateUtil.parseDateTime3(DateUtil.getSysDateYYYYMMDD())));
			ccvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			ccvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			ccvo.setUpdPerson(smUserVO.getLoginName());
			contractDAO.update2(ccvo);
			contractDAO.update2(cdvo);
			contractDAO.update2(hvo);
			contractDAO.commit();
			return true;
		}catch (Exception e){
			contractDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(contractDAO);
		}
	}


}
