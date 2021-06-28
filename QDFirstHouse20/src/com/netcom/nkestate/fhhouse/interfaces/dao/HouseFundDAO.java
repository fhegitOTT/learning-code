package com.netcom.nkestate.fhhouse.interfaces.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.BaseHouseFundDAO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.framework.dao.DataBaseUtil;


public class HouseFundDAO extends BaseHouseFundDAO {

	static Logger logger = Logger.getLogger(HouseFundDAO.class.getName());

	/**
	 * 功能描述：检查房屋专项维修资金系统中状态
	 * @param houseID
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> queryHousePayInfo(String houseID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select house_id,audit_status,repay_flag ");
			sb.append(" from house_pay_info ");
			sb.append(" where house_id=? ");
			params.add(houseID);

			List list = DataBaseUtil.select(sb.toString(), params, HouseVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}
}
