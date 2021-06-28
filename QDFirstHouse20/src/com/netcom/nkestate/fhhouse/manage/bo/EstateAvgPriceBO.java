package com.netcom.nkestate.fhhouse.manage.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.manage.dao.EstateAvgPriceDAO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingHouseVO;
import com.netcom.nkestate.framework.Page;


public class EstateAvgPriceBO extends MiniBO {

	static Logger logger = Logger.getLogger(EstateAvgPriceBO.class.getName());
	private EstateAvgPriceDAO estateAvgPriceDAO = new EstateAvgPriceDAO();

	/**
	 * 功能描述：楼盘均价列表
	 * @param page
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	public List queryEstateAvgPrice(Page page,String projectName,int districtList) throws Exception {

		try{
			openDAO(estateAvgPriceDAO);
			List list = estateAvgPriceDAO.queryEstateAvgPrice(page, projectName, districtList);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateAvgPriceDAO);
		}
	}

	/**
	 * 功能描述：房屋列表
	 * @param project_ID
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List queryHouse(long project_ID,Page page) throws Exception {
		try{
			openDAO(estateAvgPriceDAO);
			return estateAvgPriceDAO.queryHouse(project_ID, page);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateAvgPriceDAO);
		}
	}

	/**
	 * 均价幅度修改明细 功能描述：TODO
	 * @param start_ID
	 * @param building_ID
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<BuildingHouseVO> priceUpdateList(long start_ID,long building_ID,Page page) throws Exception {
		try{
			openDAO(estateAvgPriceDAO);
			return estateAvgPriceDAO.priceUpdateList(start_ID, building_ID, page);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateAvgPriceDAO);
		}
	}

	public int priceUpdate(String reference_Price,String ratio,String UPDPERSON,long UPDDATE,long UPDTIME,long start_ID,long building_ID) throws Exception {
		try{
			openDAO(estateAvgPriceDAO);
			return estateAvgPriceDAO.priceUpdate(reference_Price, ratio, UPDPERSON, UPDDATE, UPDTIME, start_ID, building_ID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateAvgPriceDAO);
		}
	}
}
