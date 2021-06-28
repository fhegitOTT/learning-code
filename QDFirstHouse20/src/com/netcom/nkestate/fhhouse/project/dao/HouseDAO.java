package com.netcom.nkestate.fhhouse.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;


public class HouseDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(HouseDAO.class.getName());

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct project.project_id,project.projectname,project.status, ");
			sb.append(" project.projectadr,project.upddate,project.updtime,enterprisequalify.name ");
			sb.append(" from enterprisequalify,project,company_project  ");
			sb.append(" where company_project.comp_id = enterprisequalify.comp_id and company_project.project_id = project.project_id ");
			sb.append(" and project.districtid in " + districtList);
			sb.append(" and enterprisequalify.bizdistrict in " + districtList);
			sb.append(" and project.status in (2,7) ");
			//params.add(FHConstant.PROJECT_STATUS_PASSED, FHConstant.PROJECT_STATUS_PERMITTED);

			if(projectName != null && !"".equals(projectName)){
				sb.append(" and PROJECT.PROJECTNAME like ? ");
				params.add("%" + projectName + "%");
			}
			if(compCode != null && !"".equals(compCode)){
				sb.append(" and ENTERPRISEQUALIFY.COMP_CODE = ? ");
				params.add(compCode);
			}
			if(compName != null && !"".equals(compName)){
				sb.append(" and ENTERPRISEQUALIFY.name like ? ");
				params.add("%" + compName + "%");
			}

			sb.append(" order by project.upddate desc,project.updtime desc ");
			List list = DataBaseUtil.select(sb.toString(), params, ProjectVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("    select enterprisequalify.name,presell.presell_desc, ");
			sb.append("    start_unit.start_code,start_unit.document_id,start_unit.start_id ");
			sb.append("    from start_unit,presell,enterprisequalify, start_comp t_start_comp_1 ");
			sb.append("    inner join ");
			sb.append("      (select min(serial) as serial,t_start_id.start_id as start_id ");
			sb.append("       from start_comp t_start_comp_2 ");
			sb.append("       inner join  ");
			sb.append("	 (select t_start_unit.start_id as start_id ");
			sb.append("	  from start_unit t_start_unit  ");
			sb.append("	  inner join  ");
			sb.append("	    presell t_presell on ");
			sb.append("	    t_presell.project_id =? and t_presell.presell_id = t_start_unit.presell_id ");
			sb.append("   where 1=1  ");
			params.add(projectID);
			if(documentID != null && !"".equals(documentID)){
				sb.append(" and T_START_UNIT.DOCUMENT_ID = ? ");
				params.add(documentID);
			}
			if(startCode != null && !"".equals(startCode)){
				sb.append(" and T_START_UNIT.START_CODE = ? ");
				params.add(startCode);
			}

			sb.append("         ) t_start_id on ");
			sb.append("	 t_start_comp_2.start_id = t_start_id.start_id group by t_start_id.start_id) t_serial ");
			sb.append("     	on t_start_comp_1.serial = t_serial.serial ");
			sb.append("	where t_start_comp_1.comp_id = enterprisequalify.comp_id and start_unit.start_id = t_serial.start_id ");
			sb.append("	and start_unit.start_id = t_start_comp_1.start_id and presell.presell_id = start_unit.presell_id ");
			sb.append("	and start_unit.status in (2,7) and enterprisequalify.bizdistrict in " + districtList);
			if(documentID != null && !"".equals(documentID)){
				sb.append(" and START_UNIT.DOCUMENT_ID = ? ");
				params.add(documentID);
			}
			if(startCode != null && !"".equals(startCode)){
				sb.append(" and START_UNIT.START_CODE = ? ");
				params.add(startCode);
			}
			sb.append(" order by start_unit.upddate desc,start_unit.updtime desc ");
			//System.out.println("sql=" + sb.toString());
			List list = DataBaseUtil.select(sb.toString(), params, StartUnitVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct building.building_name,building.building_id, ");
			sb.append(" building_house.start_id,building.upddate,building.updtime ");
			sb.append(" from building_house,building ");
			sb.append(" where building_house.building_id = building.building_id  ");
			if(startID != null && !"".equals(startID)){
				sb.append(" and BUILDING_HOUSE.START_ID = ? ");
				params.add(startID);
			}
			sb.append(" order by building.upddate desc,building.updtime desc,building.building_id ");

			List list = DataBaseUtil.select(sb.toString(), params, BuildingVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct h.floor from house h ");
			sb.append(" where h.building_id=? and h.floor is not null ");
			sb.append(" order by h.floor desc ");
			params.add(buildingID);
			List list = DataBaseUtil.select(sb.toString(), params, HouseVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}
}
