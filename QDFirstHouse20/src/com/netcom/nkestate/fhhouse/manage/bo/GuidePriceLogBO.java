package com.netcom.nkestate.fhhouse.manage.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.manage.dao.GuidePriceLogDAO;
import com.netcom.nkestate.framework.Page;


public class GuidePriceLogBO extends MiniBO {

	static Logger logger = Logger.getLogger(GuidePriceLogBO.class.getName());
	private GuidePriceLogDAO guidePriceLogDAO = new GuidePriceLogDAO();

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
			openDAO(guidePriceLogDAO);
			List list = guidePriceLogDAO.queryGuidePriceLog(page, saddate, sendate, districtId);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(guidePriceLogDAO);
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
			openDAO(guidePriceLogDAO);
			return guidePriceLogDAO.queryHouseLocation(houseId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(guidePriceLogDAO);
		}
	}
}
