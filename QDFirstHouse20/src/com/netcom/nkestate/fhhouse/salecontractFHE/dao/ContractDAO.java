/**
 * <p>ContractDAO.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 合同签约DAO <p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.salecontractFHE.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.salecontract.vo.CancelCensorVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;


public class ContractDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(ContractDAO.class.getName());

	/**
	 * 功能描述：合同撤销复核
	 * @param page
	 * @param contractId
	 * @param sellerName
	 * @param status
	 * @param buyerName
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List<CancelCensorVO> findCancelFirstAudit(Page page,String contractId,String sellerName,String status,String buyerName,String districtList) throws Exception {

		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select distinct t1.id,t2.districtid,t1.updcnt,t1.contract_id,t1.proposer1,t1.proposer2, ");
			sb.append(" t1.status,t1.location,t1.area,nvl(t5.displayname,t4.username) as firstCensor,t1.upddate,t1.updtime ");
			sb.append(" from cancel_censor t1,project t2, contractdeal t3,userinfo t4,platform.sm_user_t t5 ");
			sb.append(" where t1.contract_id = t3.contract_id and t3.project_id = t2.project_id ");
			sb.append(" and t2.districtid in " + districtList);
			sb.append(" and t1.first_censor = t4.username(+) and t1.first_censor=to_char(t5.userid(+)) ");

			if(contractId != null && !"".equals(contractId)){
				sb.append(" and T1.CONTRACT_ID = ? ");
				params.add(contractId);
			}
			if(sellerName != null && !"".equals(sellerName)){
				sb.append(" and T1.PROPOSER1 like ? ");
				params.add("%" + sellerName + "%");
			}
			if(buyerName != null && !"".equals(buyerName)){
				sb.append(" and T1.PROPOSER2 like ? ");
				params.add("%" + buyerName + "%");
			}
			if(status != null && !"".equals(status)){
				sb.append(" and T1.STATUS = ? ");
				params.add(Integer.parseInt(status));
			}
			List list = DataBaseUtil.select(sb.toString(), params, CancelCensorVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：合同撤销审批
	 * @param page
	 * @param contractId
	 * @param sellerName
	 * @param status
	 * @param buyerName
	 * @param reason
	 * @param confirmDate1
	 * @param confirmDate2
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List<CancelCensorVO> findCancelSecondAudit(Page page,String contractId,String sellerName,String status,String buyerName,String reason,String confirmDate1,String confirmDate2,String districtList) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select cancel_censor.id,cancel_censor.updcnt,cancel_censor.contract_id,cancel_censor.proposer1,cancel_censor.proposer2, ");
			sb.append(" cancel_censor.status,cancel_censor.location,cancel_censor.area,nvl(t5.displayname,t4.username) as earlyCensor,cancel_censor.first_date ");
			sb.append(" from cancel_censor,house,contractdeal,userinfo t4,platform.sm_user_t t5 ");
			sb.append(" where contractdeal.contract_id=cancel_censor.contract_id and contractdeal.house_id=house.house_id ");
			sb.append(" and house.districtid in " + districtList);
			sb.append(" and cancel_censor.first_censor = t4.username(+) and cancel_censor.first_censor=to_char(t5.userid(+)) ");

			if(contractId != null && !"".equals(contractId)){
				sb.append(" and CANCEL_CENSOR.CONTRACT_ID = ? ");
				params.add(contractId);
			}
			if(sellerName != null && !"".equals(sellerName)){
				sb.append(" and CANCEL_CENSOR.PROPOSER1 like ? ");
				params.add("%" + sellerName + "%");
			}
			if(buyerName != null && !"".equals(buyerName)){
				sb.append(" and CANCEL_CENSOR.PROPOSER2 like ? ");
				params.add("%" + buyerName + "%");
			}
			if(status != null && !"".equals(status)){
				sb.append(" and CANCEL_CENSOR.STATUS = ? ");
				params.add(status);
			}
			if(reason != null && !"".equals(reason)){
				sb.append(" and CANCEL_CENSOR.REASON_TYPE = ? ");
				params.add(reason);
			}
			if(confirmDate1 != null && !"".equals(confirmDate1)){
				sb.append(" and CANCEL_CENSOR.FIRST_DATE >=? ");
				params.add(confirmDate1.replaceAll("-", ""));
			}
			if(confirmDate2 != null && !"".equals(confirmDate2)){
				sb.append(" and CANCEL_CENSOR.FIRST_DATE <=? ");
				params.add(confirmDate2.replaceAll("-", ""));
			}
			sb.append(" order by cancel_censor.upddate desc,cancel_censor.updtime desc ");
			List list = DataBaseUtil.select(sb.toString(), params, CancelCensorVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}


	/**
	 * 功能描述：获取合同撤销的经办人，初审人，复审人
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<CancelCensorVO> findCensor(String id) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select nvl(t3.displayname,t2.username) as firstcensor,nvl(t5.displayname,t4.username) as earlycensor,nvl(t7.displayname,t6.username) as finalcensor ");
			sb.append(" from cancel_censor t1,userinfo t2,platform.sm_user_t t3,userinfo t4,platform.sm_user_t t5,userinfo t6,platform.sm_user_t t7 ");
			sb.append(" where t1.first_censor = t2.username(+) and t1.first_censor=to_char(t3.userid(+)) ");
			sb.append(" and t1.early_censor = t4.username(+) and t1.early_censor=to_char(t5.userid(+)) ");
			sb.append(" and t1.final_censor=t6.username(+) and t1.final_censor=to_char(t7.userid(+)) ");
			if(id != null && !"".equals(id)){
				sb.append(" and t1.id = ? ");
				params.add(id);
			}
			List list = DataBaseUtil.select(sb.toString(), params, CancelCensorVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}


}
