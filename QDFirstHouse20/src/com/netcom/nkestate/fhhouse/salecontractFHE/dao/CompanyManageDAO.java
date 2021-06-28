package com.netcom.nkestate.fhhouse.salecontractFHE.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.vo.CompFundAccountVO;
import com.netcom.nkestate.fhhouse.company.vo.ExchangeRecordVO;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.fhhouse.project.vo.ProPreBldSignerVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.CancelCensorVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ChargeVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.DeliverContractVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;

/**
 * 开发企业管理模块DAO
 */
public class CompanyManageDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(CompanyManageDAO.class.getName());

	/**
	 * 功能描述：查询签约人无效设置人员列表
	 * @param signerId
	 * @return List<SmObjectVO>
	 * @throws Exception
	 */
	public List<SignerVO> findSignerInvailidSetList(Page page,long signerId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select distinct  a.* ");
			sb.append(" from signer a,pro_pre_bld_signer b,( ");
			sb.append(" select t.start_id ");
			sb.append(" from pro_pre_bld_signer t ");
			sb.append(" where t.signer_id = ? ");
			sb.append(" )c ");
			sb.append(" where a.signer_id = b.signer_id ");
			sb.append(" and b.start_id = c.start_id ");
			sb.append(" and a.status = 2 ");
			sb.append(" order by a.signer_id ");
			params.add(String.valueOf(signerId));

			List list = DataBaseUtil.select(sb.toString(), params, SignerVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select distinct a.* ");
			sb.append(" from project a,pro_pre_bld_signer b ");
			sb.append(" where a.project_id = b.project_id ");
			sb.append(" and a.status = " + FHConstant.PROJECT_STATUS_PERMITTED);
			sb.append(" and b.signer_id = ? ");
			sb.append(" order by a.project_id desc ");
			params.add(String.valueOf(signerId));

			List list = DataBaseUtil.select(sb.toString(), params, ProjectVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：企业账户信息查询
	 * @param compID
	 * @return
	 * @throws Exception
	 */
	public CompFundAccountVO findCompFundAccount(long compID) throws Exception{
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
	
			sb.append(" SELECT ");
			sb.append(" 	sum(BALANCE) as BALANCE, ");
			sb.append(" 	sum(FREEZEMONEY) as FREEZEMONEY ");
			sb.append(" FROM COMPFUNDACCOUNT ");
			sb.append(" WHERE COMP_ID=? ");
			params.add(String.valueOf(compID));
			CompFundAccountVO vo = new CompFundAccountVO();
			List list = DataBaseUtil.select(sb.toString(), params, CompFundAccountVO.class, page, conn);
			if(list != null && list.size() > 0){
				vo = (CompFundAccountVO) list.get(0);
			}
			return vo;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" SELECT ");
			sb.append(" 	SUM(EXCOUNT) as EXCOUNT ");
			sb.append(" FROM EXCHANGERECORD ");
			sb.append(" WHERE COMP_ID=? AND EXTYPE=? ");
			params.add(String.valueOf(compID));
			params.add(String.valueOf(exType));
			ExchangeRecordVO vo = new ExchangeRecordVO();
			List list = DataBaseUtil.select(sb.toString(), params, ExchangeRecordVO.class, page, conn);
			if(list != null && list.size() > 0){
				vo = (ExchangeRecordVO) list.get(0);
			}
			return vo;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" SELECT ");
			sb.append(" 	SUM(CHARGE.CHARGECOUNT) as CHARGECOUNT ");
			sb.append(" FROM CHARGE, SIGNER ");
			sb.append(" WHERE COMP_ID=? AND CHARGE.SIGNER_ID=SIGNER.SIGNER_ID ");
			params.add(String.valueOf(compID));
			ChargeVO vo = new ChargeVO();
			List list = DataBaseUtil.select(sb.toString(), params, ChargeVO.class, page, conn);
			if(list != null && list.size() > 0){
				vo = (ChargeVO) list.get(0);
			}
			return vo;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" SELECT ");
			sb.append(" 	EXTYPE, ");
			sb.append(" 	EXCOUNT, ");
			sb.append(" 	BALANCE, ");
			sb.append(" 	CREATEDATE, ");
			sb.append(" 	CREATETIME, ");
			sb.append(" 	OPERATOR, ");
			sb.append(" 	UPDDATE, ");
			sb.append(" 	UPDTIME ");
			sb.append(" FROM( ");
			if("0".equals(feeRange) || feeRange == null || "".equals(feeRange)){
				sb.append("   SELECT ");
				sb.append(" 	to_char(EXTYPE) as EXTYPE,  ");
				sb.append(" 	EXCOUNT,  ");
				sb.append(" 	BALANCE,  ");
				sb.append(" 	CREDATE AS CREATEDATE, ");
				sb.append(" 	CRETIME AS CREATETIME, ");
				sb.append(" 	OPERATOR, ");
				sb.append(" 	UPDDATE, ");
				sb.append(" 	UPDTIME ");
				sb.append("   FROM EXCHANGERECORD ");
				sb.append("   WHERE COMP_ID=? ");
				params.add(String.valueOf(compId));
				if(startDate != null && !"".equals(startDate)){
					sb.append("   AND CREDATE >=? ");
					params.add(startDate.replaceAll("-", ""));
				}
				if(endDate != null && !"".equals(endDate)){
					sb.append("   AND CREDATE <=? ");
					params.add(endDate.replaceAll("-", ""));
				}
			}
			if(feeRange == null || "".equals(feeRange)){
				sb.append("   UNION  ");
			}
			if("1".equals(feeRange) || feeRange == null || "".equals(feeRange)){
				sb.append("   SELECT ");
				sb.append(" 	'5' as EXTYPE, ");
				sb.append(" 	CHARGE.CHARGECOUNT as EXCOUNT, ");
				sb.append(" 	CHARGE.BALANCE,  ");
				sb.append(" 	CHARGE.CREDATE AS CREATEDATE, ");
				sb.append(" 	CHARGE.CRETIME AS CREATETIME, ");
				sb.append(" 	SIGNER.LOGINNAME as OPERATOR, ");
				sb.append(" 	CHARGE.UPDDATE, ");
				sb.append(" 	CHARGE.UPDTIME ");
				sb.append("   FROM CHARGE, SIGNER ");
				sb.append("   WHERE SIGNER.COMP_ID=? ");
				sb.append("   AND CHARGE.SIGNER_ID=SIGNER.SIGNER_ID ");
				params.add(String.valueOf(compId));
				if(startDate != null && !"".equals(startDate)){
					sb.append("   AND CHARGE.CREDATE >=? ");
					params.add(startDate.replaceAll("-", ""));
				}
				if(endDate != null && !"".equals(endDate)){
					sb.append("   AND CHARGE.CREDATE <=? ");
					params.add(endDate.replaceAll("-", ""));
				}
			}
			sb.append(" )ORDER BY UPDDATE DESC,UPDTIME DESC ");
			List list = DataBaseUtil.select(sb.toString(), params, ExchangeRecordVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.*,c.name as cancelStatus ");
			sb.append(" from contractdeal a,cancel_censor b,ct_534 c,signer d ");
			sb.append(" where a.contract_id = b.contract_id(+) ");
			sb.append(" and b.status=c.code(+) ");
			sb.append(" and a.signer = d.signer_id ");
			sb.append(" and d.comp_id = ? ");
			sb.append(" and a.contract_id=? ");
			sb.append(" order by b.id ");
			params.add(compId);
			params.add(contractId);
			List list = DataBaseUtil.select(sb.toString(), params, ContractDealVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
	public List<DeliverContractVO> queryDeliverContract(long companyId,String buyer,String contractId,String startDate,String overDate,String startCode,Page page) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append("  select c.contract_id as contractID,c.location as addr, ");
			sb.append("  c.seller as seller,c.buyer as buyer,c.deliver_id as deliverID, ");
			sb.append("  c.deliver_year||'-'||c.deliver_month||'-'||c.deliver_day as confirmdate,c.STATUS  ");
			sb.append("  from (select d.deliver_year||''||d.deliver_month||''||d.deliver_day as deliverdate,d.* from delivercontract d) c, ");
			sb.append("  contractdeal t,start_unit s,company_project e ");
			sb.append("  where 1=1 ");
			sb.append("  and s.start_id=t.start_id and t.contract_id=c.contract_id ");
			sb.append("  and t.project_id = e.project_id ");
			sb.append("  and e.comp_id = ? ");
			params.add(companyId);
			if(contractId != null && !"".equals(contractId)){
				sb.append(" and c.contract_id =? ");
				params.add(contractId);
			}
			if(buyer != null && !"".equals(buyer)){
				sb.append(" and c.buyer like ? ");
				params.add("%" + buyer + "%");
			}
			if(startDate != null && !"".equals(startDate)){
				sb.append(" and c.deliverdate >=? ");
				params.add(startDate.replaceAll("-", ""));
			}
			if(overDate != null && !"".equals(overDate)){
				sb.append(" and c.deliverdate <=? ");
				params.add(overDate.replaceAll("-", ""));
			}
			if(startCode != null && !"".equals(startCode)){
				sb.append("  and s.start_code=? ");
				params.add(startCode);
			}
			sb.append(" order by c.upddate desc,c.updtime desc ");
			List list = DataBaseUtil.select(sb.toString(), params, DeliverContractVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select b.* ");
			sb.append(" from contractdeal a,pro_pre_bld_signer b ");
			sb.append(" where a.start_id = b.start_id ");
			sb.append(" and a.contract_id = ? ");
			sb.append(" and b.signer_id = ? ");
			params.add(contractId);
			params.add(userId);

			List list = DataBaseUtil.select(sb.toString(), params, ProPreBldSignerVO.class, conn);
			boolean flag = false;
			if(list != null && list.size() > 0){
				flag = true;
			}
			return flag;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append("select a.* ");
			sb.append("from delivercontract a ");
			sb.append("where a.contract_id = ? and a.status = ? ");
			params.add(contractId);
			params.add(status);

			List list = DataBaseUtil.select(sb.toString(), params, DeliverContractVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append("select a.* ");
			sb.append("from cancel_censor a ");
			sb.append("where a.contract_id = ?  ");
			sb.append("and a.status in (1,4,5) ");
			params.add(contractId);

			List list = DataBaseUtil.select(sb.toString(), params, CancelCensorVO.class, conn);
			boolean flag = false;
			if(list != null && list.size() > 0){
				flag = true;
			}
			return flag;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}


}
