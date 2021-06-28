package com.netcom.nkestate.fhhouse.manage.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.manage.dao.LowPriceControlDAO;
import com.netcom.nkestate.framework.Page;


public class LowPriceControlBO extends MiniBO {

	static Logger logger = Logger.getLogger(LowPriceControlBO.class.getName());
	LowPriceControlDAO lowPriceControlDAO = new LowPriceControlDAO();

	/**
	 * 功能描述：低价签约监控列表
	 * @param page
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	public List queryLowPriceControl(Page page,String projectName,String buildingName) throws Exception {

		try{
			openDAO(lowPriceControlDAO);
			List list = lowPriceControlDAO.queryLowPriceControl(page, projectName, buildingName);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(lowPriceControlDAO);
		}
	}
}
