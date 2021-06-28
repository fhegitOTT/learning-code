package com.netcom.nkestate.fhhouse.queryFHE.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.query.dao.ContractQueryDAO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellTemplateVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellerInfoVO;
import com.netcom.nkestate.framework.Page;

/**
 * 合同管理-合同查询BO
 */
public class ContractQueryBO extends MiniBO {

	static Logger logger = Logger.getLogger(ContractQueryBO.class.getName());
	ContractQueryDAO cqDao = new ContractQueryDAO();

	/**
	 * 功能描述：合同查询（乙方）列表
	 * @param buyerName
	 * @param buyerCardcode
	 * @param buyerBirth
	 * @param buyerNationality
	 * @param buyerProxy
	 * @param buyerCardname
	 * @param buyerType
	 * @param buyerProvince
	 * @param buyerAddress
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> queryContractDeal(String buyerName,String buyerCardcode,String buyerCardCodeN15,String buyerBirth,String buyerNationality,String buyerProxy,String buyerCardname,String buyerType,String buyerProvince,String buyerAddress,String districtStr,Page page)
			throws Exception {
		try{
			openDAO(cqDao);
			return cqDao.queryContractDeal(buyerName, buyerCardcode, buyerCardCodeN15, buyerBirth, buyerNationality, buyerProxy, buyerCardname, buyerType, buyerProvince, buyerAddress, districtStr, page);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cqDao);
		}
	}

	/**
	 * 功能描述：根据合同编号查询
	 * @param contractID
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> findContractsByContract(Page page,String contractID,String districtList) throws Exception {
		try{
			openDAO(cqDao);
			List<ContractDealVO> list = cqDao.findContractsByContract(page, contractID, districtList);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cqDao);
		}
	}

	/**
	 * 功能描述：根据项目信息查询
	 * @param startID
	 * @param projectName
	 * @param signDate1
	 * @param signDate2
	 * @param type
	 * @param confirmDate1
	 * @param confirmDate2
	 * @param status
	 * @param district
	 * @param road
	 * @param laneName
	 * @param subLane
	 * @param buildingNumber
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> findContractsByProject(Page page,String startID,String projectName,String signDate1,String signDate2,String type,String confirmDate1,String confirmDate2,String status,String district,String road,String laneName,String subLane,String buildingNumber,String districtList)
			throws Exception {
		try{
			openDAO(cqDao);
			List<ContractDealVO> list = cqDao.findContractsByProject(page, startID, projectName, signDate1, signDate2, type, confirmDate1, confirmDate2, status, district, road, laneName, subLane, buildingNumber, districtList);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cqDao);
		}
	}

	/**
	 * 功能描述：根据甲方信息查询
	 * @param sellerName
	 * @param compCode
	 * @param sellerAddress
	 * @param sellerCertcode
	 * @param sellerDlgCall
	 * @param sellerDelegate
	 * @param sellerProxy
	 * @param signDate1
	 * @param signDate2
	 * @param type
	 * @param confirmDate1
	 * @param confirmDate2
	 * @param status
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> findContractsBySeller(Page page,String sellerName,String compCode,String sellerAddress,String sellerCertcode,String sellerDlgCall,String sellerDelegate,String sellerProxy,String signDate1,String signDate2,String type,String confirmDate1,String confirmDate2,
			String status,String districtList) throws Exception {
		try{
			openDAO(cqDao);
			List<ContractDealVO> list = cqDao.findContractsBySeller(page, sellerName, compCode, sellerAddress, sellerCertcode, sellerDlgCall, sellerDelegate, sellerProxy, signDate1, signDate2, type, confirmDate1, confirmDate2, status, districtList);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cqDao);
		}
	}

	/**
	 * 功能描述：通过合同ID查询合同甲方信息
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public List<SellerInfoVO> searchSellerInfo(String contractID) throws Exception {
		try{
			openDAO(cqDao);
			List<SellerInfoVO> list = cqDao.searchSellerInfo(contractID);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cqDao);
		}
	}

	/**
	 * 功能描述：通过合同ID查询合同乙方信息
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public List<BuyerInfoVO> searchBuyerInfo(String contractID) throws Exception {
		try{
			openDAO(cqDao);
			List<BuyerInfoVO> list = cqDao.searchBuyerInfo(contractID);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cqDao);
		}
	}


	/**
	 * 功能描述：查询合同基本信息
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public List searchContractInfo(String contractID,int type) throws Exception {
		try{
			openDAO(cqDao);
			List<ContractDealVO> list = cqDao.searchContractInfo(contractID, type);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cqDao);
		}
	}

	public List queryDeliverContract(Page page,String contractID,String seller,String buyer,String status,String confirmDateStart,String confirmDateEnd) throws Exception {
		try{
			openDAO(cqDao);
			List list = cqDao.queryDeliverContract(page, contractID, seller, buyer, status, confirmDateStart, confirmDateEnd);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cqDao);
		}
	}

	/**
	 * 功能描述：查询合同主模板列表
	 * @param compID
	 * @param legalManCode
	 * @param projectName
	 * @param compName
	 * @param startDate
	 * @param endDate
	 * @param startID
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<SellTemplateVO> queryTemplateList(String compCode,String legalManCode,String projectName,String compName,String startDate,String endDate,String startCode,String district,Page page) throws Exception {
		try{
			openDAO(cqDao);
			List<SellTemplateVO> list = cqDao.queryTemplateList(compCode, legalManCode, projectName, compName, startDate, endDate, startCode, district, page);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cqDao);
		}
	}

	public List<BuyerInfoVO> getBuyerListOrderSerial(String contractID,String signFlag) throws Exception {
		try{
			openDAO(cqDao);
			return cqDao.searchBuyerInfoOrderSerial(String.valueOf(contractID),signFlag);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cqDao);
		}
	}

}
