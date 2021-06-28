package com.netcom.nkestate.fhhouse.queryFHE.dao;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.query.vo.ContractPdfVO;
import com.netcom.nkestate.fhhouse.query.vo.ContractSignPdfVO;
import com.netcom.nkestate.framework.dao.DataBaseUtil;


public class ContractPdfDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(ContractPdfDAO.class.getName());

	public long savepdf(ContractPdfVO contractPdfVO) throws Exception {
		long num = 0;
		try{
			num = DataBaseUtil.add(contractPdfVO, conn);
		}catch (Exception e){
			logger.error(e);
			throw e;
		}
		return num;
	}
	public long savepdf(ContractSignPdfVO contractPdfVO) throws Exception {
		long num = 0;
		try{
			num = DataBaseUtil.add(contractPdfVO, conn);
		}catch (Exception e){
			logger.error(e);
			throw e;
		}
		return num;
	}

	public long updatePdf(ContractPdfVO contractPdfVO) throws Exception {
		long num = 0;
		try{
			num = DataBaseUtil.update(contractPdfVO, conn);
		}catch (Exception e){
			logger.error(e);
			throw e;
		}
		return num;
	}

	public ContractPdfVO findPdf(long contractID,int contractType) throws Exception {
		ContractPdfVO abv = new ContractPdfVO();
		try{
			abv.setContractID(contractID);
			abv.setContractType(contractType);
			abv = (ContractPdfVO) DataBaseUtil.find(abv, conn);
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
		return abv;
	}

}
