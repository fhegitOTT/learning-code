package com.netcom.nkestate.fhhouse.manage.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.company.vo.ExchangeRecordVO;
import com.netcom.nkestate.fhhouse.manage.dao.FeeManageDAO;
import com.netcom.nkestate.framework.Page;

/**
 * 费用管理 BO
 */
public class FeeManageBO extends MiniBO {

	static Logger logger = Logger.getLogger(FeeManageBO.class.getName());
	private FeeManageDAO fmDao = new FeeManageDAO();

	/**
	 * 功能描述：根据流水号查询企业费用明细
	 * @param exchange_ID
	 * @param districtStr
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<ExchangeRecordVO> queryFeeByExchangeID(long exchange_ID,String districtStr,Page page) throws Exception {
		try{
			openDAO(fmDao);
			return fmDao.queryFeeByExchangeID(exchange_ID, districtStr, page);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(fmDao);
		}
	}

	/**
	 * 功能描述：企业费用明细查询
	 * @param operator
	 * @param compID
	 * @param loginName
	 * @param feeRange
	 * @param startDate
	 * @param endDate
	 * @param districtStr
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<ExchangeRecordVO> queryFeeByRange(String operator,long compID,String feeRange,String startDate,String endDate,String districtStr,Page page) throws Exception {
		try{
			openDAO(fmDao);
			return fmDao.queryFeeByRange(operator, compID, feeRange, startDate, endDate, districtStr, page);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(fmDao);
		}
	}
}
