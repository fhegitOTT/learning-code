package com.netcom.nkestate.fhhouse.manage.bo;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.fhhouse.manage.dao.PwdModifyDAO;


public class PwdModifyBO extends MiniBO {

	static Logger logger = Logger.getLogger(PwdModifyBO.class.getName());
	private PwdModifyDAO pmDao = new PwdModifyDAO();

	/**
	 * 功能描述：查询签约用户个人信息
	 * @param signerId
	 * @param regionId
	 * @return List<SignerVO>
	 * @throws Exception
	 */
	public List<SignerVO> querySigner(String loginName,String districtStr) throws Exception {
		try{
			openDAO(pmDao);
			return pmDao.querySigner(loginName, districtStr);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(pmDao);
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
			openDAO(pmDao);
			return pmDao.queryProject(compCode, districtStr);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(pmDao);
		}
	}
	/**
	 * 功能描述：查询合同乙方所有的用户
	 * @param compCode
	 * @param bizdistrict
	 * @return List
	 * @throws Exception
	 */
	public List<Map<String , Object>> queryBuyer(long contractID,String districtStr) throws Exception {
		try{
			openDAO(pmDao);
			return pmDao.queryBuyer(contractID, districtStr);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(pmDao);
		}
	}
}
