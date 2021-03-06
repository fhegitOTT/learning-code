package com.netcom.nkestate.fhhouse.salecontractFHE.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.interfaces.dao.HouseFundDAO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO;
import com.netcom.nkestate.fhhouse.manage.vo.HouseBandAverageVO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingHouseVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.salecontract.dao.SignContractDAO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach1VO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4HireVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4LimitVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4OtherVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4RealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4VO;
import com.netcom.nkestate.fhhouse.salecontract.vo.AttachVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractCancelPwdVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDetailCsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDetailYsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.EarnestContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.PresellContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.PresellManageContractConfirVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SupportVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.XgBuyerOwnerNameVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.XgLimitSaleContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.XgTempBuyerInfoVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaField;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.vo.DictVO;

/**
 * ??????????????????BO
 */
public class SignContractBO extends MiniBO{

	static Logger logger = Logger.getLogger(SignContractBO.class.getName());

	private SignContractDAO scDAO = new SignContractDAO();

	/**
	 * ???????????????????????????????????????????????????
	 * @param signerID
	 * @param startID
	 * @return
	 * @throws Exception
	 */
	public List<SupportVO> queryUserBuildingJson(long signerID,long startID) throws Exception {
		try{
			openDAO(scDAO);
			List<SupportVO> list = scDAO.queryUserBuildingJson(signerID, startID);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ???????????????????????????????????????????????????????????????
	 * @param signerID
	 * @param startID
	 * @param buildingID
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> queryUserHouseList(long signerID,String startID,String buildingID) throws Exception {
		try{
			openDAO(scDAO);
			List<HouseVO> list = scDAO.queryUserHouseList(signerID, startID, buildingID);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ??????????????????????????????????????????
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<Attach4RealVO> queryAttach4RealInfo(String contractId) throws Exception {
		try{
			openDAO(scDAO);
			List<Attach4RealVO> list = scDAO.queryAttach4RealInfo(contractId);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ??????????????????????????????????????????
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<Attach4OtherVO> queryAttach4OtherInfo(String contractId) throws Exception {
		try{
			openDAO(scDAO);
			List<Attach4OtherVO> list = scDAO.queryAttach4OtherInfo(contractId);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ??????????????????????????????????????????
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<Attach4LimitVO> queryAttach4LimitInfo(String contractId) throws Exception {
		try{
			openDAO(scDAO);
			List<Attach4LimitVO> list = scDAO.queryAttach4LimitInfo(contractId);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ??????????????????????????????????????????
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<Attach4HireVO> queryAttach4HireInfo(String contractId) throws Exception {
		try{
			openDAO(scDAO);
			List<Attach4HireVO> list = scDAO.queryAttach4HireInfo(contractId);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ??????????????????????????????????????????
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> queryPermitInfo(String contractId) throws Exception {
		try{
			openDAO(scDAO);
			List<HouseVO> list = scDAO.queryPermitInfo(contractId);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ????????????????????????????????????????????????
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<SellerInfoVO> querySellerInfo(String houseId) throws Exception {
		try{
			openDAO(scDAO);
			List<SellerInfoVO> list = scDAO.querySellerInfo(houseId);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ????????????????????????????????????????????????
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<BuyerInfoVO> queryBuyerInfo(String houseId) throws Exception {
		try{
			openDAO(scDAO);
			List<BuyerInfoVO> list = scDAO.queryBuyerInfo(houseId);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ????????????????????????????????????????????????
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<ContractCancelPwdVO> queryBuyerPwd(String houseId) throws Exception {
		try{
			openDAO(scDAO);
			List<ContractCancelPwdVO> list = scDAO.queryBuyerPwd(houseId);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ???????????????????????????????????????????????????????????????
	 * @param signerId
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public int checkSignerHouseRelate(String signerId,String houseId) throws Exception {
		try{
			openDAO(scDAO);
			int result = scDAO.checkSignerHouseRelate(signerId, houseId);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ???????????????????????????????????????????????????
	 * @param startId
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public int checkHouseExist(String startId,String houseId) throws Exception {
		try{
			openDAO(scDAO);
			int result = scDAO.checkHouseExist(startId, houseId);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ??????????????????????????????
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public HouseVO queryPermit(String houseId) throws Exception {
		try{
			openDAO(scDAO);
			HouseVO hvo = scDAO.queryPermit(houseId);
			return hvo;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}


	/**
	 * ??????????????????????????????????????????????????????????????????
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public String checkHousePrice(String contractId) throws Exception {
		try{
			openDAO(scDAO);
			String maxPrice = scDAO.checkHousePrice(contractId);
			return maxPrice;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ?????????????????????(??????)????????????
	 * @param page
	 * @param contractID
	 * @param type
	 * @param projectID
	 * @param buyer
	 * @param startDate
	 * @param overDate
	 * @param startCode
	 * @param district
	 * @param road
	 * @param alley
	 * @param buildingnumber
	 * @param cell
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> queryEditContract(long userID,String cmd,Page page,String contractID,String type,String projectID,String buyer,String startDate,String overDate,String startCode,String district,String road,String alley,String buildingnumber,String cell) throws Exception {
		try{
			openDAO(scDAO);
			List<ContractDealVO> list = scDAO.queryEditContract(userID,cmd,page, contractID, type, projectID, buyer, startDate, overDate, startCode, district, road, alley, buildingnumber, cell);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ?????????????????????????????????
	 * @param contractID
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean delContract(String contractID,String type) throws Exception {
		try{
			openDAO(scDAO);
			scDAO.setTransaction();
			ContractDealVO cdvo=(ContractDealVO)scDAO.find(ContractDealVO.class, Long.parseLong(contractID));
			if(cdvo != null){
				scDAO.delete(cdvo);
			}
			/* ???????????????????????????13????????????????????????4??? */
			if("1".equals(type)){
				PresellContractVO pcvo=(PresellContractVO)scDAO.find(PresellContractVO.class, Long.parseLong(contractID));
				if(pcvo!=null){
					scDAO.delete(pcvo);
				}
			}			
			if("2".equals(type)){
				SellContractVO scvo=(SellContractVO)scDAO.find(SellContractVO.class, Long.parseLong(contractID));
				if(scvo!=null){
					scDAO.delete(scvo);
				}
			}
			if("3".equals(type)){
				EarnestContractVO ecvo=(EarnestContractVO)scDAO.find(EarnestContractVO.class, Long.parseLong(contractID));
				if(ecvo!=null){
					scDAO.delete(ecvo);
				}
			}
			Attach4VO attach4vo = (Attach4VO) scDAO.find(Attach4VO.class, Long.parseLong(contractID)); //??????????????????
			if(attach4vo!=null){
				scDAO.delete(attach4vo);
			}
			scDAO.delAttachInfo(contractID);
			Attach1VO attach1vo = (Attach1VO) scDAO.find(Attach1VO.class, Long.parseLong(contractID)); //?????????
			if(attach1vo!=null){
				scDAO.delete(attach1vo);
			}
			scDAO.delAttach1MoneyInfo(contractID);
			scDAO.delAttach4Real(contractID);
			scDAO.delAttach4Other(contractID);
			scDAO.delAttach4Limit(contractID);
			scDAO.delAttach4Hire(contractID);
			scDAO.delSellerInfo(contractID);
			scDAO.delBuyerInfo(contractID);
			scDAO.delContractCancelPwdInfo(contractID);
			/* ???????????????????????? */
			List<XgTempBuyerInfoVO> xgList = scDAO.queryLocalXgInfo(contractID);
			if(xgList != null && xgList.size() > 0){
				scDAO.deleteLocalXgInfo(contractID);
				scDAO.deleteLocalHumanXgInfo(contractID);
				List<MetaFilter> xgParams = new ArrayList<MetaFilter>();
				xgParams.add(new MetaFilter("contractID", "=", contractID));
				scDAO.delete(XgLimitSaleContractVO.class, xgParams);
			}
			scDAO.commit();
			return true;
		}catch (Exception e){
			scDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ?????????????????????????????????
	 */
	public List<ContractDealVO> queryContract(String status,long userID,Page page,String contractID,String type,String projectID,String buyer,String startDate,String overDate,String startCode,String district,String road,String alley,String buildingnumber,String cell) throws Exception {
		try{
			openDAO(scDAO);
			List<ContractDealVO> list = scDAO.queryContract(status,userID,page, contractID, type, projectID, buyer, startDate, overDate, startCode, district, road, alley, buildingnumber, cell);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ?????????????????????????????????????????????????????????
	 * @param cdvo
	 * @param ecvo
	 * @param svo
	 * @return
	 * @throws Exception
	 */
	public boolean saveEarnestContract(ContractDealVO cdvo,EarnestContractVO ecvo,SellerInfoVO svo,HouseVO hvo) throws Exception {
		try{
			openDAO(scDAO);
			scDAO.setTransaction();
			if(cdvo != null){
				scDAO.update2(cdvo);
			}if(ecvo!=null){
				scDAO.update2(ecvo);
			}if(svo!=null){
				scDAO.update2(svo);
			}
			if(hvo != null){
				scDAO.update2(hvo);
			}
			scDAO.commit();
			return true;
		}catch (Exception e){
			scDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ????????????????????????????????????
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> payContarct(String contractId) throws Exception {
		try{
			openDAO(scDAO);
			List<ContractDealVO> list = scDAO.payContarct(contractId);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ?????????????????????????????????????????????
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public List<BuildingHouseVO> queryMaxPrice(String contractID) throws Exception {
		try{
			openDAO(scDAO);
			List<BuildingHouseVO> bHList = scDAO.queryMaxPrice(contractID);
			return bHList;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	public List<DictVO> queryTemplateList(String projectID,String startID,String district,String type) throws Exception {
		try{
			openDAO(scDAO);
			List<DictVO> list = scDAO.queryTemplateList(projectID, startID, district, type);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ???????????????????????????????????????????????????
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public long findState(String houseId) throws Exception {
		try{
			openDAO(scDAO);
			long nState = scDAO.findState(houseId);
			return nState;
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ?????????????????????????????????????????????
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> findAttribute(String houseId) throws Exception {
		try{
			openDAO(scDAO);
			List<HouseVO> list = scDAO.findAttribute(houseId);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ?????????????????????????????????????????????????????????????????????????????????
	 */
	public long addSignContract(ContractDetailYsVO pcVo,ContractDetailCsVO scVo,EarnestContractVO ecVo,SellerInfoVO siVo,BuyerInfoVO biVo,ContractDealVO cdVo,String type,List<AttachVO> attachList,String person,long dates,long times)
			throws Exception {
		long contractID = -1;
		try{
			openDAO(scDAO);
			scDAO.setTransaction();
			if(type != null && "1".equals(type)){
				//??????????????????
				contractID = scDAO.add(pcVo);
			}
			if(type != null && "2".equals(type)){
				//??????????????????
				contractID = scDAO.add(scVo);
			}
			if(type != null && "3".equals(type)){
				//??????????????????
				contractID = scDAO.add(ecVo);
			}

			if(contractID >= 0){
				//??????????????????
				//				biVo.setContractID(contractID);
				//				scDAO.add(biVo);

				//??????????????????
				//				ccpVo.setContractID(contractID);
				//				scDAO.add(ccpVo);

				//??????????????????
				siVo.setContractID(contractID);
				scDAO.add(siVo);

				//??????????????????
				cdVo.setContractID(contractID);
				scDAO.add(cdVo);

				//??????????????????
				if(attachList != null && attachList.size() > 0){
					for(AttachVO attachVO : attachList){
						attachVO.setContractID(contractID);
						scDAO.add(attachVO);
					}
				}
			}
			scDAO.commit();
		}catch (Exception e){
			scDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
		return contractID;
	}

	/**
	 * ?????????????????????????????????
	 * @param cdVo
	 * @param pcVo
	 * @param siVo
	 * @return
	 * @throws Exception
	 */
	public boolean saveBuyerInfo(ContractCancelPwdVO ccpVo,BuyerInfoVO biVo,int i) throws Exception {
		try{
			openDAO(scDAO);
			scDAO.setTransaction();
			if(i == 0){
				scDAO.update2(biVo);
				scDAO.update2(ccpVo);
			}else if(i == 1){
				scDAO.add(biVo);
				scDAO.add(ccpVo);
			}else if(i == 2){
				scDAO.update2(biVo);
				scDAO.add(ccpVo);
			}
			//????????????
			ContractDealVO cdo = new ContractDealVO();
			List<BuyerInfoVO> list = scDAO.findAllBuyerInfoByContractId(biVo);
			int num = list.size();
			String buyerName = list.get(0).getBuyerName();
			cdo.setContractID(biVo.getContractID());
			if(num == 1){
				cdo.setBuyerName(buyerName);
				scDAO.update2(cdo);
			}else{
				cdo.setBuyerName(buyerName + "..");
				scDAO.update2(cdo);
			}
			scDAO.commit();
			return true;
		}catch (Exception e){
			scDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ??????????????????????????????????????????
	 * @param cdVo
	 * @param pcVo
	 * @param siVo
	 * @return
	 * @throws Exception
	 */
	public boolean deleteBuyerInfo(ContractCancelPwdVO ccpVo,BuyerInfoVO biVo) throws Exception {
		try{
			openDAO(scDAO);
			scDAO.setTransaction();
			int i = scDAO.delete(ccpVo);
			int j = scDAO.delete(biVo);
			boolean res = false;
			if(i > 0 && j > 0){
				res = true;
			}
			//????????????
			ContractDealVO cdo = new ContractDealVO();
			List<BuyerInfoVO> list = scDAO.findAllBuyerInfoByContractId(biVo);
			int num = list.size();
			String buyerName = list.get(0).getBuyerName();
			cdo.setContractID(biVo.getContractID());
			if(num == 1){
				cdo.setBuyerName(buyerName);
				scDAO.update2(cdo);
			}

			scDAO.commit();
			return res;
		}catch (Exception e){
			scDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ???????????????????????????
	 * @param cdVo
	 * ????????????VO
	 * @param pcVo
	 * ????????????VO
	 * @param scVo
	 * ????????????VO
	 * @param ecVo
	 * ????????????VO
	 * @param siVo
	 * ??????VO
	 * @param attachList
	 * ????????????
	 * @param hvo
	 * ??????VO
	 * @param pmccVo
	 * ???????????????
	 * @param earnestVO
	 * ???????????????????????????
	 * @return
	 * @throws Exception
	 */
	public long saveContract(ContractDealVO cdVo,ContractDetailYsVO pcVo,ContractDetailCsVO scVo,EarnestContractVO ecVo,SellerInfoVO siVo,
			List<AttachVO> attachList,HouseVO hvo,PresellManageContractConfirVO pmccVo,ContractDealVO earnestVO) throws Exception {
		long i = -1;
		try{
			openDAO(scDAO);
			scDAO.setTransaction();
			if(cdVo != null){
				scDAO.update2(cdVo);
			}
			if(pcVo != null){
				i = scDAO.update2(pcVo);
				String totalPrice = "0";
				String decoPrice = "0";
				if("1".equals(pcVo.getf0601())){
					totalPrice = pcVo.getf0603();
					decoPrice = pcVo.getf0619();
				}else if("2".equals(pcVo.getf0601())){
					totalPrice = pcVo.getf0606();
					decoPrice = pcVo.getf0609();
				}else if("5".equals(pcVo.getf0601())){
                    totalPrice = pcVo.getf0631();
                    //decoPrice = ysVO.getf0609();
                }else if("6".equals(pcVo.getf0601())){
                    totalPrice = pcVo.getf0636();
                    //decoPrice = ysVO.getf0609();
                }else{
					totalPrice = pcVo.getf0610();
					decoPrice = pcVo.getf0624();
				}
				if(totalPrice == null || "".equals(totalPrice)){
					totalPrice = "0";
				}
				if(decoPrice == null || "".equals(decoPrice)){
					decoPrice = "0";
				}
				double totalmoney = Double.parseDouble(totalPrice) + Double.parseDouble(decoPrice);
				scDAO.updatePresellAttach2(totalmoney, pcVo.getContractID());
			}
			if(scVo != null){
				i = scDAO.update2(scVo);
			}
			if(ecVo != null){
				i = scDAO.update2(ecVo);
			}
			if(siVo != null){
				scDAO.update2(siVo);
			}

			//????????????????????????
			if(attachList != null && attachList.size() > 0){
				scDAO.delContractAttch(cdVo.getContractID());
				for(AttachVO attachVO : attachList){
					scDAO.add(attachVO);
				}
			}
			//??????????????????
			if(hvo != null){
				scDAO.update2(hvo);
			}
			//???????????????????????????
			if(pmccVo != null){
				scDAO.delete(pmccVo);
				scDAO.add(pmccVo);
			}
			//????????????????????????
			if(earnestVO != null){
				scDAO.update2(earnestVO);
			}

			scDAO.commit();
		}catch (Exception e){
			scDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
		return i;
	}

	/**
	 * ???????????????????????????????????????
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public List<XgTempBuyerInfoVO> queryLocalXgInfo(String contractID) throws Exception {
		try{
			openDAO(scDAO);
			List<XgTempBuyerInfoVO> list = scDAO.queryLocalXgInfo(contractID);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	public long saveXgInfo(XgLimitSaleContractVO xgLimitSaleContractVO,XgTempBuyerInfoVO xgBuyerInfoVO,List<XgBuyerOwnerNameVO> list) throws Exception {
		long i = -1;
		try{
			openDAO(scDAO);
			scDAO.setTransaction();
			if(xgLimitSaleContractVO != null){
				//???????????????????????????
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("contractID", "=", xgLimitSaleContractVO.getContractID()));
				scDAO.delete(XgLimitSaleContractVO.class, params);

				i = scDAO.add(xgLimitSaleContractVO);
			}
			if(xgBuyerInfoVO != null){
				scDAO.add(xgBuyerInfoVO);
			}
			if(list != null && list.size() > 0){
				for(XgBuyerOwnerNameVO xgBuyerOwnerNameVO : list){
					scDAO.add(xgBuyerOwnerNameVO);
				}
			}
			scDAO.commit();
		}catch (Exception e){
			scDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
		return i;
	}

	public int deleteLocalXgInfo(String contractID) throws Exception {
		try{
			openDAO(scDAO);
			int result = scDAO.deleteLocalXgInfo(contractID);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	public int deleteLocalHumanXgInfo(String contractID) throws Exception {
		try{
			openDAO(scDAO);
			int result = scDAO.deleteLocalHumanXgInfo(contractID);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ????????????????????????????????????????????????????????????
	 * @param houseID
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> queryHousePayInfo(String houseID) throws Exception {
		HouseFundDAO fundDao = new HouseFundDAO();
		try{
			openDAO(fundDao);
			List<HouseVO> list = fundDao.queryHousePayInfo(houseID);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(fundDao);
		}
	}

	/**
	 * ?????????????????????????????????????????????????????????
	 * @param houseID
	 * @return
	 * @throws Exception
	 */
	public int queryPresellDistrictidManage(String houseID) throws Exception {
		try{
			openDAO(scDAO);
			int res = scDAO.queryPresellDistrictidManage(houseID);
			return res;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ??????????????????????????????????????????????????????????????????
	 * @param xgID
	 * @param buyerName
	 * @param buyerCardcode
	 * @return
	 * @throws Exception
	 */
	public List<XgBuyerOwnerNameVO> queryXgBuyerInfo(String xgID,String buyerName,String buyerCardcode) throws Exception {
		try{
			openDAO(scDAO);
			List<XgBuyerOwnerNameVO> list = scDAO.queryXgBuyerInfo(xgID, buyerName, buyerCardcode);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ???????????????????????????????????????
	 * @param houseID
	 * @return
	 * @throws Exception
	 */
	public HouseBandAverageVO queryHouseAverage(String houseID) throws Exception {
		try{
			openDAO(scDAO);
			HouseBandAverageVO hbaVo = scDAO.queryHouseAverage(houseID);
			return hbaVo;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ????????????????????????????????????
	 * @param buildingID
	 * @return
	 * @throws Exception
	 */
	public List<CHFlatVO> queryHouseList(String buildingID) throws Exception {
		try{
			openDAO(scDAO);
			List<CHFlatVO> list = scDAO.queryHouseList(buildingID);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ????????????????????????????????????????????????????????????????????????
	 * @param buildingID
	 * @return
	 * @throws Exception
	 */
	public String queryEarnestID(String contractID,String type) throws Exception {
		try{
			openDAO(scDAO);
			return scDAO.queryEarnestID(contractID, type);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ??????????????????????????????????????????id????????????
	 * @param xgid
	 * @return
	 * @throws Exception
	 */
	public List<XgLimitSaleContractVO> findSHXgTransList(long xgid) throws Exception {
		try{
			openDAO(scDAO);
			return scDAO.findSHXgTransList(xgid);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ?????????????????????????????????????????????
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String getDistNameForZB(String code) throws Exception {
		try{
			openDAO(scDAO);
			String distName = scDAO.getDistNameForZB(code);
			return distName;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	/**
	 * ???????????????????????????????????????
	 * @param houseID
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> checkOtherRightByHouseID(String houseID) throws Exception {
		try{
			openDAO(scDAO);
			List<HouseVO> list = scDAO.checkOtherRightByHouseID(houseID);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

	public void updateBuyerInfoVO(String contractID, int serial,String fieldName, String fieldValue) {
		//?????????????????????
		List<MetaField> whereFields = new ArrayList<MetaField>();
		whereFields.add(new MetaField("contractID", contractID));
		whereFields.add(new MetaField("serial", serial+""));
		
		List<MetaField> fields = new ArrayList<MetaField>();
		fields.add(new MetaField(fieldName, fieldValue));
		try {
			this.update(BuyerInfoVO.class, whereFields , fields );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 2020.3.23 ph
	 * ???????????????????????????????????????????????????????????????
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public long findxgManCheckState(String houseId) throws Exception {
		try{
			openDAO(scDAO);
			long nState = scDAO.findxgManCheckState(houseId);
			return nState;
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}
	
	/**
	 * ???????????????????????????????????????????????????
	 * @param houseID
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> checkZjOtherRightByHouseID(String houseID) throws Exception {
		try{
			openDAO(scDAO);
			List<HouseVO> list = scDAO.checkZjOtherRightByHouseID(houseID);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(scDAO);
		}
	}

}
