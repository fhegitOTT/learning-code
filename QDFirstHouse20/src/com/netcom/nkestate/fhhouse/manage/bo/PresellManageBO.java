package com.netcom.nkestate.fhhouse.manage.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.manage.dao.PresellManageDAO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.framework.Page;


public class PresellManageBO extends MiniBO {

	static Logger logger = Logger.getLogger(PresellManageBO.class.getName());
	PresellManageDAO pmDao = new PresellManageDAO();

	/**
	 * 功能描述：开发企业项目列表
	 * @param compId
	 * @return
	 * @throws Exception
	 */
	public List<ProjectVO> queryProject(String compId) throws Exception {
		try{
			openDAO(pmDao);
			return pmDao.queryProject(compId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(pmDao);
		}
	}

	/**
	 * 功能描述：签约人许可证权限列表
	 * @param name
	 * @param presell_Desc
	 * @param companyname
	 * @param projectName
	 * @param saveDate
	 * @return
	 * @throws Exception
	 */
	public List queryUserProjectSet(String name,String presell_Desc,String companyname,String projectName,String saveDate,Page page) throws Exception {
		try{
			openDAO(pmDao);
			return pmDao.queryUserProject(name, presell_Desc, companyname, projectName, saveDate, page);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(pmDao);
		}
	}

	/**
	 * 功能描述：签约人对应项目的许可证权限列表
	 * @param signerID
	 * @param projectID
	 * @return
	 * @throws Exception
	 */
	public List queryUserPresell(String signerID,String projectID) throws Exception {
		try{
			openDAO(pmDao);
			List list = pmDao.queryUserPresell(signerID, projectID);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(pmDao);
		}
	}

	/**
	 * 功能描述：删除签约人对应项目的许可证权限列表
	 * @param username
	 * @param acceptno
	 * @return
	 * @throws Exception
	 */
	public long deleteUserProjectSet(String username,String[] acceptno) throws Exception {
		try{
			openDAO(pmDao);
			pmDao.setTransaction();
			long n = pmDao.deleteUserProjectSet(username, acceptno);
			pmDao.commit();
			return n;

		}catch (Exception e){
			pmDao.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(pmDao);
		}
	}
}
