package com.netcom.nkestate.fhhouse.manage.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.manage.dao.GuidePriceMgDAO;
import com.netcom.nkestate.fhhouse.manage.vo.HouseBandAverageVO;
import com.netcom.nkestate.fhhouse.manage.vo.JGGLLogVO;
import com.netcom.nkestate.framework.Page;


public class GuidePriceMgBO extends MiniBO {

	static Logger logger = Logger.getLogger(GuidePriceMgBO.class.getName());
	private GuidePriceMgDAO guidePriceMgDAO = new GuidePriceMgDAO();

	/**
	 * 功能描述：基准价格查询列表
	 * @param page
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	public List queryGuidePrice(Page page,String projectName,int districtList) throws Exception {

		try{
			openDAO(guidePriceMgDAO);
			List list = guidePriceMgDAO.queryGuidePrice(page, projectName, districtList);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(guidePriceMgDAO);
		}
	}

	/**
	 * 功能描述：房屋列表
	 * @param project_ID
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List queryHouse(long project_ID,Page page) throws Exception {
		try{
			openDAO(guidePriceMgDAO);
			return guidePriceMgDAO.queryHouse(project_ID, page);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(guidePriceMgDAO);
		}
	}

	/**
	 * 功能描述：房屋基准价格幅度修改明细
	 * @param project_id
	 * @param building_ID
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List GuidePriceUpdateList(long project_id,long building_ID,long nProjectID,Page page) throws Exception {
		try{
			openDAO(guidePriceMgDAO);
			return guidePriceMgDAO.GuidePriceUpdateList(project_id, building_ID, nProjectID, page);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(guidePriceMgDAO);
		}
	}

	/**
	 * 功能描述 ：基准价幅度修改删除
	 * @param request
	 * @param session
	 * @return
	 */
	public int GuidePriceUpdatedelete(long nHouseID) throws Exception {
		try{
			openDAO(guidePriceMgDAO);
			return guidePriceMgDAO.GuidePriceUpdatedelete(nHouseID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(guidePriceMgDAO);
		}
	}

	/**
	 * 功能描述：价格信息保存
	 * @param priceList
	 * @param logList
	 * @return
	 * @throws Exception
	 */
	public boolean saveGuidePrice(List<HouseBandAverageVO> priceList,List<JGGLLogVO> logList) throws Exception {
		try{
			openDAO(guidePriceMgDAO);
			guidePriceMgDAO.setTransaction();
			if(priceList != null && priceList.size() > 0){
				for(HouseBandAverageVO vo : priceList){
					guidePriceMgDAO.GuidePriceUpdatedelete(vo.getNHouseID());
					//2019.1.7 基准价大于0的才保存
					if(vo.getNHouseAverage() > 0){
						guidePriceMgDAO.add(vo);
					}
				}
			}
			if(logList != null && logList.size() > 0){
				for(JGGLLogVO logvo : logList){
					guidePriceMgDAO.add(logvo);
				}
			}
			
			guidePriceMgDAO.commit();
			return true;
		}catch (Exception e){
			guidePriceMgDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(guidePriceMgDAO);
		}
	}

}
