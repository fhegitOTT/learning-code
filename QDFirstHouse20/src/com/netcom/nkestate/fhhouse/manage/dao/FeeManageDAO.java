package com.netcom.nkestate.fhhouse.manage.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.company.vo.ExchangeRecordVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;

/**
 * 费用管理 DAO
 */
public class FeeManageDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(FeeManageDAO.class.getName());

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.comp_id,a.exchange_id as serial,a.extype,a.excount,a.balance,a.operator,a.credate as upddate,a.cretime as updtime ");
			sb.append(" from exchangerecord a,enterprisequalify b ");
			sb.append(" where a.comp_id=b.comp_id ");
			if(exchange_ID != 0){
				sb.append(" and a.exchange_id=? ");
				params.add(String.valueOf(exchange_ID));
			}
			sb.append(" and b.bizdistrict in " + districtStr);
			sb.append(" order by upddate desc,updtime desc ");
			List list = DataBaseUtil.select(sb.toString(), params, ExchangeRecordVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select serial,extype,excount,balance,operator,upddate,updtime ");
			sb.append(" from( ");
			if("0".equals(feeRange) || "".equals(feeRange)){
				sb.append(" select ");
				sb.append("  to_char(a.exchange_id) as serial,  ");
				sb.append("  to_char(a.extype) as extype,  ");
				sb.append("  a.excount, ");
				sb.append("  a.balance, ");
				sb.append("  a.operator, ");
				sb.append("  a.credate as upddate, ");
				sb.append("  a.cretime as updtime ");
				sb.append("  from exchangerecord a,enterprisequalify b ");
				sb.append("  where a.comp_id=b.comp_id");
				if(operator != null && !"".equals(operator)){
					sb.append("  and a.operator=? ");
					params.add(operator);
				}
				if(String.valueOf(compID) != null && compID != 0){
					sb.append("  and b.comp_id=? ");
					params.add(compID);
				}
				if(startDate != null && !"".equals(startDate)){
					sb.append("   and a.credate>=? ");
					params.add(startDate.replaceAll("-", ""));
				}
				if(endDate != null && !"".equals(endDate)){
					sb.append("   and a.credate <=? ");
					params.add(endDate.replaceAll("-", ""));
				}
				sb.append(" and b.bizdistrict in " + districtStr);
			}
			if(feeRange == null || "".equals(feeRange)){
				sb.append(" union ");
			}
			if("1".equals(feeRange) || "".equals(feeRange)){
				sb.append(" select ");
				sb.append("  '' as serial, ");
				sb.append("  '5' as extype, ");
				sb.append("  a.chargecount as excount,  ");
				sb.append("  a.balance, ");
				sb.append("  c.loginname as operator, ");
				sb.append("  a.credate as upddate, ");
				sb.append("  a.cretime as updtime ");
				sb.append(" from charge a,enterprisequalify b,signer c ");
				sb.append(" where b.comp_id=c.comp_id ");
				sb.append(" and a.signer_id=c.signer_id ");
				if(operator != null && !"".equals(operator)){
					sb.append("  and c.loginname=? ");
					params.add(operator);
				}
				if(String.valueOf(compID) != null && compID != 0){
					sb.append("  and b.comp_id=? ");
					params.add(compID);
				}
				if(startDate != null && !"".equals(startDate)){
					sb.append(" and a.credate>=? ");
					params.add(startDate.replaceAll("-", ""));
				}
				if(endDate != null && !"".equals(endDate)){
					sb.append(" and a.credate <=? ");
					params.add(endDate.replaceAll("-", ""));
				}
				sb.append(" and b.bizdistrict in " + districtStr);
			}
			sb.append(" )order by upddate desc,updtime desc ");
			List list = DataBaseUtil.select(sb.toString(), params, ExchangeRecordVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			e.printStackTrace();
			throw e;
		}finally{

		}
	}
}
