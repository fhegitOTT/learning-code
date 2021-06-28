package com.netcom.nkestate.fhhouse.manage.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;

/**
 * 限购房屋管理 DAO
 */
public class LimitBuyHouseManageDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(LimitBuyHouseManageDAO.class.getName());

	/**
	 * 功能描述：楼栋列表
	 * @param project_ID
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<BuildingVO> queryBuilding(long project_ID,Page page) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select distinct h.BUILDING_ID, h.BUILDING_NAME  ");
			sb.append(" from presell a,PROJECT b,START_UNIT c,BUILDING_HOUSE d ,BUILDING h  ");
			sb.append(" where a.PROJECT_ID=b.PROJECT_ID   ");
			sb.append(" and a.PRESELL_ID=c.PRESELL_ID ");
			sb.append(" and d.START_ID=c.START_ID  ");
			sb.append(" and h.BUILDING_ID=d.BUILDING_ID  ");
			sb.append(" and a.PROJECT_ID= ? ");
			sb.append(" order by BUILDING_NAME ");
			params.add(project_ID);
			List list = DataBaseUtil.select(sb.toString(), params, BuildingVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：房屋列表
	 * @param building_ID
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List queryHouse(long building_ID,Page page) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select (b.BUILDING_NAME||' '||a.room_Number) as room,a.house_ID,a.noXgState  ");
			sb.append(" from house a ,building b   ");
			sb.append(" where a.BUILDING_ID=b.BUILDING_ID   ");
			sb.append(" and  a.BUILDING_ID=? ");
			sb.append(" order by floor desc,room_number asc  ");

			params.add(building_ID);
			List list = DataBaseUtil.select(sb.toString(), params, HouseVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}
}
