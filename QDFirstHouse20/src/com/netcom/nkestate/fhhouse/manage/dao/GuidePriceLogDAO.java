package com.netcom.nkestate.fhhouse.manage.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;


public class GuidePriceLogDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(GuidePriceLogDAO.class.getName());

	/**
	 * 功能描述：基准日志列表
	 * @param page
	 * @param saddate
	 * @param sendate
	 * @param districtId
	 * @return
	 * @throws Exception
	 */
	public List queryGuidePriceLog(Page page,String saddate,String sendate,int districtId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select a.projectName,b.NLOGID,b.SPROJECTNAME,b.SHOUSELOCATION,b.NHOUSEAVERAGE,b.NBANDAVERAGE,b.NBANDAVERAGEXIA,b.SADDDATE,b.SENDDATE,b.SADDUSER,b.PROJECT_ID,b.HOUSE_ID ");
			sb.append(" from PROJECT a,JGGLLOG b ");
			sb.append(" where a.PROJECTNAME=b.SPROJECTNAME ");
			if(saddate != null && !"".equals(saddate)){
				sb.append(" and to_date(CONCAT(subStr(SADDDATE,0,8),subStr(SENDDATE,0,6)),'yyyy-mm-dd hh24:mi:ss') >= to_date(?,'yyyymmddhh24miss') ");
				params.add(saddate);
			}
			if(sendate != null && !"".equals(sendate)){
				sb.append(" and to_date(CONCAT(subStr(SADDDATE,0,8),subStr(SENDDATE,0,6)),'yyyy-mm-dd hh24:mi:ss') <= to_date(?,'yyyymmddhh24miss') ");
				params.add(sendate);
			}
			if(districtId != 0){
				sb.append(" and DISTRICTID=? ");
				params.add(districtId);
			}
			sb.append(" order by b.nlogid desc ");
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
	 * 功能描述：房屋坐落查询
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public String queryHouseLocation(long houseId) throws Exception {
		try{
			String location = "";
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select a.building_name||b.room_number as building_name ");
			sb.append(" from building a,house b ");
			sb.append(" where a.building_id=b.building_id ");
			sb.append(" and b.house_id = ? ");
			params.add(houseId);
			//System.out.println(sb.toString());
			List list = DataBaseUtil.select(sb.toString(), params, BuildingVO.class, page, conn);
			if(list != null && list.size() > 0){
				location = ((BuildingVO) list.get(0)).getBuilding_Name();
			}
			return location;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
		}
	}
}
