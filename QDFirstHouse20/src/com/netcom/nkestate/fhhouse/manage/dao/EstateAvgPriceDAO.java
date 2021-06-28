package com.netcom.nkestate.fhhouse.manage.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingHouseVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;


public class EstateAvgPriceDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(EstateAvgPriceDAO.class.getName());

	/**
	 * 功能描述：楼盘均价查询列表
	 * @param page
	 * @param projectName
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List queryEstateAvgPrice(Page page,String projectName,int districtList) throws Exception {

		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select a.comp_id,a.name compname,a.comp_code,c.project_id,c.projectname,c.projectadr,d.name as districtname ");
			sb.append(" from enterprisequalify a,company_project b,project c,ct_district d ");
			sb.append(" where a.comp_id = b.comp_id and b.project_id = c.project_id and c.districtid = d.code(+) ");

			if(projectName != null && !"".equals(projectName)){
				sb.append(" and c.projectname like ? ");
				params.add("%" + projectName + "%");
			}
			if(districtList != 0){
				sb.append(" and c.districtid =? ");
				params.add(districtList);
				}
			sb.append(" order by c.project_id ");
			//System.out.println(sb.toString());
			List list = DataBaseUtil.select(sb.toString(), params, ProjectVO.class, page, conn);
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
	public List queryHouse(long project_ID,Page page) throws Exception {
		try{
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
			sb.append("  select distinct a.presell_id,a.presell_code,c.start_id,c.start_code,h.BUILDING_ID, h.BUILDING_NAME, d.REFERENCE_PRICE, d.RATIO  ");
			sb.append("  from presell a, PROJECT  b, START_UNIT c, BUILDING_HOUSE d, HOUSE f,BUILDING h  ");
			sb.append("  where a.PROJECT_ID = b.PROJECT_ID and a.PRESELL_ID = c.PRESELL_ID and d.START_ID = c.START_ID and f.HOUSE_ID = d.HOUSE_ID and h.BUILDING_ID = f.BUILDING_ID  ");
			sb.append("  and a.PROJECT_ID = ? ");
			sb.append("  order by h.BUILDING_ID,c.start_id  ");
			params.add(project_ID);
			//System.out.println(sb.toString());
			List list = DataBaseUtil.select(sb.toString(), params, HouseVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

	  }
	}

	/**
	 * 功能描述：均价幅度修改明细
	 * @param start_ID
	 * @param building_ID
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<BuildingHouseVO> priceUpdateList(long start_ID,long building_ID,Page page) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("  select *  ");
			sb.append("  from BUILDING_HOUSE  ");
			sb.append("  where BUILDING_ID=?  ");
			sb.append("  and start_id = ?  ");
			params.add(building_ID);
			params.add(start_ID);
			List list = DataBaseUtil.select(sb.toString(), params, BuildingHouseVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
		}
	}

	/**
	 * 功能描述：楼盘均价幅度修改
	 * @param reference_Price
	 * @param ratio
	 * @param UPDPERSON
	 * @param UPDDATE
	 * @param UPDTIME
	 * @param start_ID
	 * @param building_ID
	 * @return
	 * @throws Exception
	 */
	public int priceUpdate(String reference_Price,String ratio,String UPDPERSON,long UPDDATE,long UPDTIME,long start_ID,long building_ID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("  update BUILDING_HOUSE  ");
			sb.append("  set REFERENCE_PRICE=?,RATIO=?,UPDPERSON=?,UPDDATE=?,UPDTIME=?  ");
			sb.append("  where BUILDING_ID=? And start_id = ?  ");
			params.add(reference_Price);
			params.add(ratio);
			params.add(UPDPERSON);
			params.add(UPDDATE);
			params.add(UPDTIME);
			params.add(building_ID);
			params.add(start_ID);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
		}
	}
}