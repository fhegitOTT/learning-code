/**
 * <p>ProjectDAO.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 开发项目 DAO <p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;


public class ProjectDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(ProjectDAO.class.getName());

	/**
	 * 功能描述：查询开发企业下项目列表
	 * @param compId
	 * @return
	 * @throws Exception
	 */
	public List<ProjectVO> findCompanyProjectList(long compId,String logo,Page page) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.* ");
			sb.append(" from project a,company_project b ");
			sb.append(" where a.project_id = b.project_id ");
			sb.append(" and b.comp_id = ? ");
			if(logo != null && !"".equals(logo)){
				sb.append(" and a.status <> 1 ");
			}
			sb.append(" order by a.upddate desc,a.updtime desc ");
			params.add(compId);
			//System.out.println(sb.toString());
			List list = DataBaseUtil.select(sb.toString(), params, null, ProjectVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：查询项目下许可证列表
	 * @param projectId
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<StartUnitVO> findProjectPresellList(long projectId,Page page,String backFlag) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select b1.start_id,b1.start_code,a1.presell_desc,b1.document_id,b1.housecount,b1.status,b1.bookingoffice as buildingName ");
			sb.append(" from presell a1, start_unit b1 ");
			sb.append("	where a1.presell_id = b1.presell_id ");
			if("edit".equals(backFlag)){
				sb.append(" and b1.status<>1 ");
			}
			sb.append("	and a1.project_id = ? ");
			sb.append("	order by b1.upddate desc, b1.updtime desc ");
			params.add(projectId);
			//params.add(projectId);

			List list = DataBaseUtil.select(sb.toString(), params, null, StartUnitVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：查询开盘单元下的所有楼栋列表
	 * @param startId
	 * @return
	 * @throws Exception
	 */
	public List<BuildingVO> findStartUnitBuildingList(long startId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select distinct b.* ");
			sb.append(" from building_house a,building b ");
			sb.append(" where a.building_id = b.building_id ");
			sb.append(" and a.start_id = ? ");
			sb.append(" order by b.building_name ");
			params.add(startId);

			List list = DataBaseUtil.select(sb.toString(), params, BuildingVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：查询开盘单元下对应楼栋下的房屋列表
	 * @param startId
	 * @param buildingId
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> findStartUnitHouseListByBuidling(long startId,long buildingId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select b.* ");
			sb.append(" from building_house a,house b ");
			sb.append(" where a.house_id = b.house_id ");
			sb.append(" and a.start_id = ? ");
			sb.append(" and a.building_id = ? ");
			params.add(startId);
			params.add(buildingId);

			List list = DataBaseUtil.select(sb.toString(), params, HouseVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：更新开盘单元房屋套数
	 * @param startId
	 * @return
	 * @throws Exception
	 */
	public long updateStartUnitHouseCount(long startId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" update start_unit a ");
			sb.append(" set a.housecount =( ");
			sb.append("     select count(*) as count  ");
			sb.append(" 		from building_house a1,house a2 ");
			sb.append("     where a1.house_id = a2.house_id ");
			sb.append("     and a1.start_id = ? ");
			sb.append("     and a2.status <> " + FHConstant.HOUSE_STATUS_UNACTIVE);
			sb.append(" ) ");
			sb.append(" where a.start_id = ? ");
			params.add(startId);
			params.add(startId);

			long count = DataBaseUtil.execute(sb.toString(), params, conn);
			return count;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：删除开盘单元关联楼栋记录进行重新关联
	 * @param startId
	 * @param buildingId
	 * @return
	 * @throws Exception
	 */
	public long deleteBuildingHouse(long startId,long buildingId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" delete from building_house a ");
			sb.append(" where a.house_id in ( ");
			sb.append("   select a1.house_id ");
			sb.append("   from building_house a1,house a2 ");
			sb.append("   where a1.house_id = a2.house_id ");
			sb.append("   and a2.status in(4,9) ");
			sb.append("   and a1.start_id = ? ");
			sb.append("   and a1.building_id = ? ");
			sb.append(" ) ");
			params.add(startId);
			params.add(buildingId);

			long count = DataBaseUtil.execute(sb.toString(), params, conn);
			return count;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：房屋关联查询房屋列表
	 * @param buildingId
	 * @return
	 * @throws Exception
	 */
	public List<CHFlatVO> findBuildHouseList(long buildingId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select ");
			sb.append(" t1.* ");
			sb.append(" ,t2.status as status1 ");
			sb.append(" ,t3.status as status2 ");
			sb.append(" ,t4.start_id ");
			sb.append(" FROM CH_FLAT T1,HOUSE_STATE_CONSUME T2, HOUSE T3,BUILDING_HOUSE T4 ");
			sb.append(" where t1.houseid = t2.house_id(+) ");
			sb.append(" and t1.houseid = t3.house_id(+) ");
			sb.append(" and t1.houseid = t4.house_id(+) ");
			sb.append(" and t1.FLOOR_FROM is not null ");
			sb.append(" and t1.BuildingID = ? ");
			sb.append(" order by t1.FLOOR_FROM desc,t1.ROOM_NUMBER asc ");
			params.add(buildingId);

			List houseList = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			return houseList;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}


}
