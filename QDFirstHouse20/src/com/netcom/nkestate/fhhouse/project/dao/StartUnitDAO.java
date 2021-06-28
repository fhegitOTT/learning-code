package com.netcom.nkestate.fhhouse.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitReasonVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;


public class StartUnitDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(StartUnitDAO.class.getName());

	/**
	 * 功能描述：开盘管理列表
	 * @param page
	 * @param presellCode
	 * @param documentID
	 * @param startCode
	 * @param projectName
	 * @param issalable
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List<StartUnitVO> findStartUnits(Page page,String presellCode,String documentID,String startCode,String projectName,String issalable,String districtList) throws Exception {

		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select presell.presell_desc,start_unit.start_code,start_unit.start_id,start_unit.initial_date, ");
			sb.append(" start_unit.initial_time,start_unit.start_date,start_unit.start_time,start_unit.issalable, ");
			sb.append(" start_unit.updcnt,project.project_id ");
			sb.append(" from presell,start_unit,project ");
			sb.append(" where presell.presell_id=start_unit.presell_id and project.project_id = presell.project_id ");
			sb.append(" and project.districtid in " + districtList);
			sb.append(" and START_UNIT.STATUS in (2,4,7) ");
			if(presellCode != null && !"".equals(presellCode)){
				sb.append(" and PRESELL.PRESELL_DESC=? ");
				params.add(presellCode);
			}
			if(documentID != null && !"".equals(documentID)){
				sb.append(" and START_UNIT.DOCUMENT_ID=? ");
				params.add(documentID);
			}
			if(startCode != null && !"".equals(startCode)){
				sb.append(" and START_UNIT.START_CODE=? ");
				params.add(startCode);
			}
			if(projectName != null && !"".equals(projectName)){
				sb.append(" and PROJECT.PROJECTNAME like ? ");
				params.add("%" + projectName + "%");
			}
			if(issalable != null && !"".equals(issalable)){
				sb.append(" and START_UNIT.ISSALABLE=? ");
				params.add(issalable);
			}else{
				sb.append(" and START_UNIT.ISSALABLE=? ");
				params.add(FHConstant.ISSALABLE_OFF);
			}
			sb.append(" order by start_unit.start_date desc,start_unit.start_time desc ");
			List list = DataBaseUtil.select(sb.toString(), params, StartUnitVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 根据startID查询操作原因表
	 * @param startID
	 * @return
	 * @throws Exception 
	 */
	public List<StartUnitReasonVO> findOperateReasonList(String startID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select b.displayname,a.OPERATEDATE,a.reason,a.status from log_start_unit_reason a ,platform.sm_user_t b where a.operateUser = b.userid(+) and a.startID = ? order by logid ");
			params.add(startID);
			List list = DataBaseUtil.select(sb.toString(), params, StartUnitReasonVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}
}
