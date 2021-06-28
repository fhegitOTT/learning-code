/**
 * <p>PermitBO.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 许可证BO <p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.permit.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.interfaces.dao.RealestateDAO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO;
import com.netcom.nkestate.fhhouse.permit.dao.PermitDAO;
import com.netcom.nkestate.fhhouse.permit.vo.ApplyVO;
import com.netcom.nkestate.fhhouse.permit.vo.HouseSaleLogVO;
import com.netcom.nkestate.fhhouse.permit.vo.PermitVO;
import com.netcom.nkestate.fhhouse.permit.vo.RHousePermitVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaField;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.vo.SmObjectVO;
import com.netcom.nkestate.system.vo.SmUserVO;


public class PermitBO extends MiniBO {
	static Logger logger = Logger.getLogger(PermitBO.class.getName());

	private PermitDAO permitDAO = new PermitDAO();
	
	/**
	 * 功能描述：签约人菜单列表
	 * @param signerId
	 * @return
	 * @throws Exception
	 */
	public List<SmObjectVO> findSignerObjectList(long signerId) throws Exception {
		try{
			openDAO(permitDAO);
			return permitDAO.findSignerObjectList(signerId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(permitDAO);
		}
	}

	public long getApplySeqID(String seqName) throws Exception {
		try{
			openDAO(permitDAO);
			return permitDAO.getSeqIDByName(seqName);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(permitDAO);
		}
	}

	public List<PermitVO> searchPermitList(String companyName, String permitNo,	long userId, String status,Page page,String userDistrictList) throws Exception {
		try{
			openDAO(permitDAO);
			return permitDAO.searchPermitList(companyName,  permitNo,userId, status,page,userDistrictList);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(permitDAO);
		}
	}

	/**
	 * 功能描述：添加日志表HouseSaleLogVO
	 * @param houseList
	 * @param userID
	 * @param memo
	 * @param saleFlag
	 * @return
	 * @throws Exception
	 */
	public int addHouseSaleLog(List<RHousePermitVO> houseList,long userID,String memo,int saleFlag) throws Exception{
		try {
			List<HouseSaleLogVO> list = new ArrayList<HouseSaleLogVO>();
			for(RHousePermitVO house:houseList){
				HouseSaleLogVO logVO = new HouseSaleLogVO();
				logVO.setTransactionID(house.getTransactionID());
				logVO.setDistrictID(house.getDistrictID());
				logVO.setHouseID(house.getHouseID());
				logVO.setSaleFlag(saleFlag);//1、可售；0，不可售
				logVO.setMemo(memo);
				logVO.setModifyUser(userID);
				logVO.setModifyDate(DateUtil.getSysDate());
				list.add(logVO);
			}
			return this.addBatch(list);
		} catch (Exception e) {
			System.out.println("Error at addHouseSaleLog():"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return 0;
	}

	public int auditPermitDetail(String permitID, String transactionID,int result, String opinion, SmUserVO userVO) throws Exception {
		try {
			openDAO(permitDAO);
			List<MetaField> fields = new ArrayList<MetaField>();
			fields.add(new MetaField("status", result == 1?2:0));//审核通过：2,草稿：0
			int count1 = permitDAO.updatePermitVOByPermitID(permitID, fields);
			
			fields = new ArrayList<MetaField>();
			fields.add(new MetaField("state", result == 1?2:0));//审核通过：2,草稿：0
			fields.add(new MetaField("passDate", DateUtil.getSysDate()));//审核时间
			fields.add(new MetaField("passUser", userVO.getUserId()));//审核人员
			fields.add(new MetaField("passResult", result == 1?1:-1));//审核通过：1，不通过：-1
			fields.add(new MetaField("passOpinion", opinion));//审核意见
			int count2 = permitDAO.updateApplyVOByTransactionID(transactionID, fields);
			if(count1 > 0 && count2 > 0 && result == 1){//审核通过的要插入房屋合同状态表
				//批量插入房屋合同状态表
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("transactionID", "=", transactionID));
				params.add(new MetaFilter("permitID", "=", permitID));
				List<RHousePermitVO> houseList = this.search(RHousePermitVO.class, params); 
				int count = this.addHouseSaleLog(houseList, userVO.getUserId(), "审核通过添加", 1);
				if(count > 0){
					return 1;
				}
			}	
		} catch (Exception e) {
			permitDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(permitDAO);
		}
		return 0;
	}

	public List searchOnlineHouseList(String houseIDs, String districtID) throws Exception {
		try{
			openDAO(permitDAO);
			return permitDAO.searchOnlineHouseList(houseIDs,districtID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(permitDAO);
		}
	}
	
	public List findHousesByBuildingIDAndPermitVO(String buildingID, String districtID) throws Exception {
		try{
			openDAO(permitDAO);
			return permitDAO.findHousesByBuildingIDAndPermitVO(buildingID,districtID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(permitDAO);
		}
	}
	
	public List<CHFlatVO> searchHousesByBuildingIDAndLanduse(String landuseID, String buildingID, String districtID) throws Exception {
		try{
			openDAO(permitDAO);
			return permitDAO.searchHousesByBuildingIDAndLanduse(landuseID,buildingID,districtID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(permitDAO);
		}
	}

	public int deleteRHousePermitVO(String permitID, long transactionID) throws Exception {
		try{
			openDAO(permitDAO);
			return permitDAO.deleteRHousePermitVO(permitID,transactionID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(permitDAO);
		}
	}

	public List<PermitVO> queryPermitStatis(String startDate, String endDate) throws Exception {
		try{
			openDAO(permitDAO);
			return permitDAO.queryPermitStatis(startDate,endDate);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(permitDAO);
		}
	}

	public List<RHousePermitVO> queryRPermitHouse(long permitID) throws Exception {
		try{
			openDAO(permitDAO);
			return permitDAO.queryRPermitHouse(permitID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(permitDAO);
		}
	}

	public List<CHFlatVO> getPermitUnitHouse(String transactionID,String districtID) throws Exception {
		try{
			openDAO(permitDAO);
			return permitDAO.getPermitUnitHouse(transactionID,districtID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(permitDAO);
		}
	}


	/**
	 * 功能描述：查询登记库字典表信息
	 * @param sd_class
	 * @param sd_keyno
	 * @return
	 * @throws Exception
	 */
	public String querydictNameDAJI(String prefix,String tableName,String key) throws Exception {
		RealestateDAO estateDao = new RealestateDAO();
		try{
			openDAO(estateDao);
			return estateDao.querydictNameDAJI(prefix,tableName,key);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}
}
