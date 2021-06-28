package com.netcom.nkestate.fhhouse.manage.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.manage.vo.HouseBandAverageVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;


public class GuidePriceMgDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(GuidePriceMgDAO.class.getName());

	/**
	 * 功能描述：基准价格查询列表
	 * @param page
	 * @param projectName
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List queryGuidePrice(Page page,String projectName,int districtList) throws Exception {

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
			if(districtList != 0 && districtList != 2 && districtList != 3 && districtList != 5 && districtList != 13){
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
			sb.append("  select distinct h.BUILDING_ID,b.project_id, h.BUILDING_NAME  ");
			sb.append("  from presell a, PROJECT  b, START_UNIT c, BUILDING_HOUSE d, HOUSE f,BUILDING h  ");
			sb.append("  where a.PROJECT_ID = b.PROJECT_ID and a.PRESELL_ID = c.PRESELL_ID and d.START_ID = c.START_ID and f.HOUSE_ID = d.HOUSE_ID and h.BUILDING_ID = f.BUILDING_ID  ");
			sb.append("  and a.PROJECT_ID = ? ");
			sb.append("  order by h.BUILDING_ID  ");
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
	 * 功能描述：房屋基准价格修改明细
	 * @param project_id
	 * @param building_ID
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List GuidePriceUpdateList(long project_id,long building_ID,long nProjectID,Page page) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("  select f.building_id,h.building_name,f.house_id,f.room_number,g.* ");
			sb.append("  ,g.nHouseAverage as nHouseAverageStr  ");
			sb.append("  ,g.nBandAverage as nBandAverageStr  ");
			sb.append("  ,g.nBandAverageXia as nBandAverageXiaStr  ");
			sb.append("  from presell  a, PROJECT b,START_UNIT  c,BUILDING_HOUSE d,HOUSE  f,BUILDING  h, ");
			sb.append("  ( select * from HOUSEBANDAVERAGE t where t.nprojectid = ? ) g  ");
			sb.append("  where a.PROJECT_ID = b.PROJECT_ID and a.PRESELL_ID = c.PRESELL_ID  ");
			sb.append("  and d.START_ID = c.START_ID and f.HOUSE_ID = d.HOUSE_ID  ");
			sb.append("  and h.BUILDING_ID = f.BUILDING_ID and f.house_id = g.nhouseid(+)  ");
			sb.append("  and a.PROJECT_ID = ?  ");
			sb.append("  and d.building_id = ? ");
			params.add(nProjectID);
			params.add(project_id);
			params.add(building_ID);
			sb.append("  order by f.floor desc, f.room_number asc  ");
			//System.out.println(sb.toString());
			List list = DataBaseUtil.select(sb.toString(), params, HouseBandAverageVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述 ：基准价幅度修改删除
	 * @param request
	 * @param session
	 * @return
	 */
	public int GuidePriceUpdatedelete(long nHouseID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("  delete from  HOUSEBANDAVERAGE  ");
			sb.append("  where nHouseID=?  ");
			params.add(nHouseID);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
		}
	}
}