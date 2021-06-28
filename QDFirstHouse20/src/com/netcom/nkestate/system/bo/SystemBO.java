package com.netcom.nkestate.system.bo;

import java.util.Date;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.system.dao.SystemDAO;
import com.netcom.nkestate.system.vo.SmUserVO;


public class SystemBO extends MiniBO {

	private SystemDAO systemDAO = new SystemDAO();
	static Logger logger = Logger.getLogger(SystemBO.class.getName());

	/**
	 * 功能描述：获取M_SEQUENCE配置表中的seq值
	 * @param typeName
	 * @return
	 * @throws Exception
	 */
	public String getSequence(String typeName) throws Exception {
		try{
			openDAO(systemDAO);
			systemDAO.setTransaction();
			String seqvalue = systemDAO.getSequence(typeName);
			systemDAO.commit();
			return seqvalue;
		}catch (Exception e){
			systemDAO.rollback();
			logger.error("获取M_SEQUENCE配置表中的seq值出现异常，" + e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(systemDAO);
		}
	}

	/**
	 * 功能描述：获取数据库系统日期
	 * @param typeName
	 * @return
	 * @throws Exception
	 */
	public Date getDBSysDate() throws Exception {
		try{
			openDAO(systemDAO);
			return systemDAO.getDBSysDate();
		}catch (Exception e){
			logger.error("获取数据库系统日期出现异常，" + e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(systemDAO);
		}
	}

	/**
	 * 功能描述：查询用户信息
	 * @param userId
	 * @return SmUserVO
	 * @throws Exception
	 */
	public SmUserVO findUserInfo(long userId) throws Exception {
		try{
			openDAO(systemDAO);
			return systemDAO.findUserInfo(userId);
		}catch (Exception e){
			logger.error("查询用户信息出现异常，" + e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(systemDAO);
		}
	}
	
	/**
	 * 功能描述：查询区县信息
	 * @param regionID
	 * @return String
	 * @throws Exception
	 */
	public String findUserRegionName(int regionID) throws Exception {
		try{
			openDAO(systemDAO);
			return systemDAO.findUserRegionName(regionID);
		}catch (Exception e){
			logger.error("查询用户区县信息出现异常，" + e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(systemDAO);
		}
	}
}
