package com.netcom.nkestate.fhhouse.manage.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.framework.dao.DataBaseUtil;


public class PwdModifyDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(PwdModifyDAO.class.getName());

	/**
	 * 功能描述：查询签约用户个人信息
	 * @param loginName
	 * @param districtStr
	 * @return
	 * @throws Exception
	 */
	public List<SignerVO> querySigner(String loginName,String districtStr) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.* ");
			sb.append(" from signer a,enterprisequalify b,ct_district_range c ");
			sb.append(" where a.comp_id = b.comp_id ");
			sb.append(" and b.bizdistrict = c.targetdistrictid ");
			sb.append(" and a.loginname = ? ");
			sb.append(" and c.districtid in " + districtStr);
			params.add(String.valueOf(loginName));

			List list = DataBaseUtil.select(sb.toString(), params, SignerVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：查询合同甲方所有的项目
	 * @param compCode
	 * @param bizdistrict
	 * @return List
	 * @throws Exception
	 */
	public List<Map<String , Object>> queryProject(String compCode,String districtStr) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<String> params = new ArrayList<String>();

			sb.append(" select a.project_id,a.projectname ");
			sb.append(" from project a,compcancelpwd b,enterprisequalify c ");
			sb.append(" where c.comp_code=? ");
			sb.append(" and c.comp_id=b.comp_id ");
			sb.append(" and a.project_id=b.project_id ");
			sb.append(" and c.bizdistrict in " + districtStr);
			params.add(String.valueOf(compCode));
			List<Map<String , Object>> list = DataBaseUtil.executeQuery(sb.toString(), params, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}
	}

	/**
	 * 功能描述：查询合同的所有乙方
	 * @param compCode
	 * @param bizdistrict
	 * @return List
	 * @throws Exception
	 */
	public List<Map<String , Object>> queryBuyer(long contractID,String districtStr) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<String> params = new ArrayList<String>();

			sb.append(" select a.serial, b.buyer_name ");
			sb.append(" from contractcancelpwd a,buyerinfo b,contractdeal c,house d ");
			sb.append(" where a.contract_id=? ");
			sb.append(" and a.serial=b.serial ");
			sb.append(" and a.contract_id=b.contract_id  ");
			sb.append(" and c.contract_id=? ");
			sb.append(" and c.house_id =d.house_id ");
			sb.append(" and d.districtid in " + districtStr);
			params.add(String.valueOf(contractID));
			params.add(String.valueOf(contractID));
			List<Map<String , Object>> list = DataBaseUtil.executeQuery(sb.toString(), params, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}
	}
}
