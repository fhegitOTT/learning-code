package com.netcom.nkestate.fhhouse.project.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.project.dao.StartUnitDAO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitReasonVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.framework.Page;


public class StartUnitBO extends MiniBO {

	static Logger logger = Logger.getLogger(StartUnitBO.class.getName());

	private StartUnitDAO startUnitDAO = new StartUnitDAO();

	/**
	 * 功能描述：开盘管理列表
	 * @param page
	 * @param presellCode
	 * @param documentID
	 * @param startCode
	 * @param projectName
	 * @param issalable
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List<StartUnitVO> findStartUnits(Page page,String presellDeSc,String documentID,String startCode,String projectName,String issalable,String districtList) throws Exception {
		try{
			openDAO(startUnitDAO);
			List<StartUnitVO> list = startUnitDAO.findStartUnits(page, presellDeSc, documentID, startCode, projectName, issalable, districtList);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(startUnitDAO);
		}
	}
	
	/**
	 * 根据startID查询操作原因记录
	 * @param startID
	 * @return
	 * @throws Exception
	 */
	public List<StartUnitReasonVO> findOperateReasonList(String startID) throws Exception {
		try{
			openDAO(startUnitDAO);
			List<StartUnitReasonVO> list = startUnitDAO.findOperateReasonList(startID);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(startUnitDAO);
		}
	}

}
