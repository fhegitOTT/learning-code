package com.netcom.nkestate.fhhouse.manage.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.manage.dao.LimitBuyHouseManageDAO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingVO;
import com.netcom.nkestate.framework.Page;

/**
 * 限购房屋管理 BO
 */
public class LimitBuyHouseManageBO extends MiniBO {

	static Logger logger = Logger.getLogger(LimitBuyHouseManageBO.class.getName());
	private LimitBuyHouseManageDAO lbhmDao = new LimitBuyHouseManageDAO();

	/**
	 * 功能描述：楼栋列表
	 * @param project_ID
	 * @return
	 * @throws Exception
	 */
	public List<BuildingVO> queryBuilding(long project_ID,Page page) throws Exception {
		try{
			openDAO(lbhmDao);
			return lbhmDao.queryBuilding(project_ID, page);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(lbhmDao);
		}
	}

	/**
	 * 功能描述：房屋列表
	 * @param building_ID
	 * @return
	 * @throws Exception
	 */
	public List queryHouse(long building_ID,Page page) throws Exception {
		try{
			openDAO(lbhmDao);
			return lbhmDao.queryHouse(building_ID, page);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(lbhmDao);
		}
	}
}
