package com.netcom.nkestate.fhhouse.manage.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;
import com.netcom.nkestate.system.vo.BulletinVO;


public class BulletinDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(BulletinDAO.class.getName());

	/**
	 * 功能描述：根据登录用户区县ID获取公告列表
	 * @param regionId
	 * @return
	 * @throws Exception
	 */
	public List<BulletinVO> findBulletins(Page page,int regionId) throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		//select a.* from bulletin a,ct_district_range b where a.districtid = b.targetdistrictid and b.districtid = 登陆用户所在区县
		sb.append("	select distinct a.* ");
		sb.append(" from bulletin a,ct_district_range b ");
		sb.append(" where a.districtid = b.targetdistrictid ");
		sb.append(" and b.districtid = ? ");
		sb.append(" order by a.upddate desc,a.updtime desc ");

		params.add(String.valueOf(regionId));
		List list = DataBaseUtil.select(sb.toString(), params, BulletinVO.class, page, conn);
		return list;

	}

	/**
	 * 功能描述：根据人员所属区县ID查询公告列表
	 * @param userDistrictId
	 * @return List<SmObjectVO>
	 * @throws Exception
	 */
	public List<BulletinVO> findShowBulletinList(Page page,int userDistrictId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select distinct a.* ");
			sb.append(" from bulletin a,ct_district_range b ");
			sb.append(" where a.districtid = b.targetdistrictid ");
			sb.append(" and a.status=1 and b.districtid = ? ");
			sb.append(" order by a.upddate desc,a.updtime desc ");
			params.add(String.valueOf(userDistrictId));

			List list = DataBaseUtil.select(sb.toString(), params, BulletinVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}
}
