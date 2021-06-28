package com.netcom.nkestate.fhhouse.project.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.project.dao.HouseDAO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.framework.Page;


public class HouseBO extends MiniBO {

	static Logger logger = Logger.getLogger(HouseBO.class.getName());

	private HouseDAO houseDAO = new HouseDAO();

	/**
	 * 功能描述：房屋管理列表
	 * @param page
	 * @param projectName
	 * @param compCode
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List<ProjectVO> findHouses(Page page,String projectName,String compCode,String compName,String districtList) throws Exception {
		try{
			openDAO(houseDAO);
			List<ProjectVO> list = houseDAO.findHouses(page, projectName, compCode, compName, districtList);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(houseDAO);
		}
	}

	/**
	 * 功能描述：开盘单元一览
	 * @param page
	 * @param projectID
	 * @param documentID
	 * @param startCode
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List<StartUnitVO> findStartUnits(Page page,String projectID,String documentID,String startCode,String districtList) throws Exception {
		try{
			openDAO(houseDAO);
			List<StartUnitVO> list = houseDAO.findStartUnits(page, projectID, documentID, startCode, districtList);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(houseDAO);
		}
	}

	/**
	 * 功能描述：楼幢信息
	 * @param page
	 * @param startID
	 * @return
	 * @throws Exception
	 */
	public List<BuildingVO> findBuilding(Page page,String startID) throws Exception {
		try{
			openDAO(houseDAO);
			List<BuildingVO> list = houseDAO.findBuilding(page, startID);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(houseDAO);
		}
	}

	/**
	 * 功能描述：顺序查询房屋的层数
	 * @param buildingID
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> findFloor(String buildingID) throws Exception {
		try{
			openDAO(houseDAO);
			List<HouseVO> list = houseDAO.findFloor(buildingID);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(houseDAO);
		}
	}
}
