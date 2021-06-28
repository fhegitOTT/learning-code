package com.netcom.nkestate.fhhouse.manage.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.manage.dao.BulletinDAO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.system.vo.BulletinVO;


public class BulletinBO extends MiniBO {

	static Logger logger = Logger.getLogger(BulletinBO.class.getName());

	private BulletinDAO bulletinDAO = new BulletinDAO();

	/**
	 * 功能描述：根据登录用户区县ID获取公告列表
	 * @param regionId
	 * @return
	 * @throws Exception
	 */
	public List<BulletinVO> findBulletins(Page page,int regionId) throws Exception {
		try{
			openDAO(bulletinDAO);
			List<BulletinVO> list = bulletinDAO.findBulletins(page, regionId);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(bulletinDAO);
		}

	}

	/**
	 * 功能描述：根据人员所属区县ID查询公告列表
	 * @param userDistrictId
	 * @return List<SmObjectVO>
	 * @throws Exception
	 */
	public List<BulletinVO> findShowBulletinList(Page page,int userDistrictId) throws Exception {
		try{
			openDAO(bulletinDAO);
			List<BulletinVO> list = bulletinDAO.findShowBulletinList(page, userDistrictId);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(bulletinDAO);
		}
	}


}
