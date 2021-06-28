package com.netcom.nkestate.fhhouse.salecontractFHE.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.company.vo.CompFundAccountVO;
import com.netcom.nkestate.fhhouse.company.vo.ExchangeRecordVO;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.salecontract.dao.CompanyManageDAO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ChargeVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.DeliverContractVO;
import com.netcom.nkestate.framework.Page;


/**
 * 开发企业管理模块BO
 */
public class CompanyManageBO extends MiniBO {

	static Logger logger = Logger.getLogger(CompanyManageBO.class.getName());

	private CompanyManageDAO cmDAO = new CompanyManageDAO();

	/**
	 * 功能描述：查询签约人无效设置人员列表
	 * @param signerId
	 * @return List<SignerVO>
	 * @throws Exception
	 */
	public List<SignerVO> findSignerInvailidSetList(Page page,long signerId) throws Exception {
		try{
			openDAO(cmDAO);
			return cmDAO.findSignerInvailidSetList(page, signerId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cmDAO);
		}
	}

	/**
	 * 功能描述：查询密码设置项目列表
	 * @param signerId
	 * @return List<ProjectVO>
	 * @throws Exception
	 */
	public List<ProjectVO> findModifyPwdProjectList(Page page,long signerId) throws Exception {
		try{
			openDAO(cmDAO);
			return cmDAO.findModifyPwdProjectList(page, signerId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cmDAO);
		}
	}

	/**
	 * 功能描述：企业费用明细查询
	 * @param compId
	 * @param feeRange
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<ExchangeRecordVO> findCompExchangeRecordList(long compId,String feeRange,String startDate,String endDate,Page page) throws Exception {
		try{
			openDAO(cmDAO);
			return cmDAO.findCompExchangeRecordList(compId, feeRange, startDate, endDate, page);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cmDAO);
		}
	}

	/**
	 * 功能描述：企业账户信息查询
	 * @param compID
	 * @return
	 * @throws Exception
	 */
	public CompFundAccountVO findCompFundAccount(long compID) throws Exception {
		try{
			openDAO(cmDAO);
			return cmDAO.findCompFundAccount(compID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cmDAO);
		}
	}

	/**
	 * 功能描述：企业存款/退款查询
	 * @param compID
	 * @param exType
	 * 费用类型
	 * @return
	 * @throws Exception
	 */
	public ExchangeRecordVO findCompExchangeRecord(long compID,int exType) throws Exception {
		try{
			openDAO(cmDAO);
			return cmDAO.findCompExchangeRecord(compID,exType);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cmDAO);
		}
	}

	/**
	 * 功能描述：扣款查询
	 * @param compID
	 * @return
	 * @throws Exception
	 */
	public ChargeVO findCompCharge(long compID) throws Exception {
		try{
			openDAO(cmDAO);
			return cmDAO.findCompCharge(compID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cmDAO);
		}
	}

	/**
	 * 功能描述：合同撤销状态查询
	 * @param contractId
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> findContractCancelList(long compId,String contractId,Page page) throws Exception {
		try{
			openDAO(cmDAO);
			return cmDAO.findContractCancelList(compId, contractId, page);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cmDAO);
		}
	}

	/**
	 * 功能描述：交接书查询
	 * @param companyId
	 * @param buyer
	 * @param contractId
	 * @param startDate
	 * @param overDate
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<DeliverContractVO> queryDeliverContract(long companyId,String buyer,String contractId,String startDate,String overDate,String startCode,
			Page page) throws Exception {
		try{
			openDAO(cmDAO);
			return cmDAO.queryDeliverContract(companyId, buyer, contractId, startDate, overDate, startCode, page);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cmDAO);
		}
	}

	/**
	 * 功能描述：检查签约人是否有操作该合同权限
	 * @param userId
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public boolean checkSignerContract(long userId,long contractId) throws Exception {
		try{
			openDAO(cmDAO);
			return cmDAO.checkSignerContract(userId, contractId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cmDAO);
		}
	}

	/**
	 * 功能描述：查询合同对应交接书列表
	 * @param status
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public List<DeliverContractVO> queryDeliverByContract(long contractId,int status) throws Exception {
		try{
			openDAO(cmDAO);
			return cmDAO.queryDeliverByContract(contractId, status);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cmDAO);
		}
	}

	/**
	 * 功能描述：检查合同是否在被撤销中
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public boolean checkContractInCancel(long contractId) throws Exception {
		try{
			openDAO(cmDAO);
			return cmDAO.checkContractInCancel(contractId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cmDAO);
		}
	}

	/**
	 * 功能描述：更新交接书
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public void updateDeliverContract(DeliverContractVO deliverContractVO) throws Exception {
		try{
			openDAO(cmDAO);
			cmDAO.setTransaction();
			cmDAO.update2(deliverContractVO);
			cmDAO.commit();
		}catch (Exception e){
			cmDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cmDAO);
		}
	}
}
