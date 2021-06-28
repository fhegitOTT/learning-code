package com.netcom.nkestate.fhhouse.manage.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.manage.vo.MonitorAverageVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;


public class LowPriceControlDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(LowPriceControlDAO.class.getName());

	/**
	 * 功能描述：低价签约监控列表
	 * @param page
	 * @param projectName
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List queryLowPriceControl(Page page,String projectName,String buildingName) throws Exception {

		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("  select distinct h.BUILDING_ID,  h.BUILDING_NAME, b.PROJECTNAME,f.ROOM_NUMBER,n.NHOUSEREFERENCE,n.NBANDAVERAGE, n.NHOUSEAVERAGE,n.ADDDATE  ");
			sb.append("  from presell a, PROJECT b, START_UNIT c, BUILDING_HOUSE d, HOUSE f,BUILDING  h,MonitorAverage n  ");
			sb.append("   where a.PROJECT_ID = b.PROJECT_ID and a.PRESELL_ID = c.PRESELL_ID and d.START_ID = c.START_ID and f.HOUSE_ID = d.HOUSE_ID and h.BUILDING_ID = f.BUILDING_ID and f.HOUSE_ID = n.nHouseId  ");
			if(projectName != null && !"".equals(projectName)){
				sb.append("  and b.PROJECTNAME like  ? ");
				params.add("%" + projectName + "%");
			}
			if(buildingName != null && !"".equals(buildingName)){
				sb.append(" and h.BUILDING_NAME like ? ");
				params.add("%" + buildingName + "%");
			}
			sb.append("  order by  n.ADDDATE desc  ");
			//System.out.println(sb.toString());
			List list = DataBaseUtil.select(sb.toString(), params, MonitorAverageVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
		}
	}
}
