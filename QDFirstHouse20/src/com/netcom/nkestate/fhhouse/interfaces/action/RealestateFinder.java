package com.netcom.nkestate.fhhouse.interfaces.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.fhhouse.interfaces.bo.RealestateBO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHBuildingVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHHouseTypeVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CurrentCerLandSubUsesVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.DocMessageVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.HouseHintVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.RHouseRightView;
import com.netcom.nkestate.fhhouse.interfaces.vo.RLandRightView;
import com.netcom.nkestate.fhhouse.interfaces.vo.TempRHouseRightView;
import com.netcom.nkestate.fhhouse.interfaces.vo.TempRLandRightView;
import com.netcom.nkestate.fhhouse.permit.vo.CfgFileStandardVO;
import com.netcom.nkestate.fhhouse.permit.vo.EmptyVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;

public class RealestateFinder  extends BaseAction {

	public List<CHBuildingVO> searchBuildingsByLocationID(int districtID,long locationID) throws Exception {
		List<CHBuildingVO> list = new ArrayList<CHBuildingVO>();
		// 查询房屋列表
		RealestateBO realestateBO = new RealestateBO();
		List<EmptyVO> houseList = realestateBO.findHousesByLocationID(locationID, districtID);
		String buildIDs = "";
		HashMap<String , String> tempMap = new HashMap<String , String>();
		for (int i = 0; i < houseList.size(); i++) {
			EmptyVO vo = houseList.get(i);
			if (tempMap.containsKey(String.valueOf(vo.getAttribute("buildingID")))) {
				continue;
			}
			buildIDs += String.valueOf(vo.getAttribute("buildingID")) + ",";
			tempMap.put(String.valueOf(vo.getAttribute("buildingID")), String.valueOf(vo.getAttribute("buildingID")));
		}
		
		if (!"".equals(buildIDs)) {
			//解决buildIDs数量过多问题
			String[] array = buildIDs.split(",");
			if (array.length >= 1000) {
				for (int i = 0; i < 999; i++) {
					buildIDs = array[i] + ",";
				}
			}
			buildIDs = buildIDs.substring(0, buildIDs.length() - 1);
			
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("buildingID", "in", "(" + buildIDs + ")"));
			if (districtID > 0) {
				params.add(new MetaFilter("districtID", "=", districtID));
			}
			list = realestateBO.search(CHBuildingVO.class, params, null, null);
		}
		if(list != null && list.size() > 0){
			for(CHBuildingVO bVO : list){
				if(bVO.getDoorPlate() == null || "".equals(bVO.getDoorPlate())){
					bVO.setDoorPlate(String.valueOf(bVO.getLogical_Number()));
				}
			}
		}
		return list;
	}
	

	/**
	 * 功能描述：根据buildingID 获取房屋数据
	 * @param districtID
	 * @param buildingID
	 * @return
	 * @throws Exception
	 */
	public List<CHBuildingVO> searchBuildingsByLotID(int districtID,long lotID) throws Exception {
		List<CHBuildingVO> buildingList = new ArrayList<CHBuildingVO>();
		try {
			RealestateBO realestateBO = new RealestateBO();
			buildingList = realestateBO.searchBuildingsByLotID(lotID,districtID);
			return buildingList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 功能描述：根据buildingID 获取房屋数据
	 * @param districtID
	 * @param buildingID
	 * @return
	 * @throws Exception
	 */
	public List<CHFlatVO> findHousesByBuildingID(int districtID,long buildingID,Page page) throws Exception {
		List<CHFlatVO> houseList = new ArrayList<CHFlatVO>();
		try {
			RealestateBO realestateBO = new RealestateBO();
			// 查询房屋列表
			List<MetaFilter> filters = new ArrayList<MetaFilter>();
			filters.add(new MetaFilter("buildingID", "=", buildingID));
			if (districtID > 0) {
				filters.add(new MetaFilter("districtID", "=", districtID));
			}
			//加载 lotID
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("floor_From", "asc"));
			orders.add(new MetaOrder("room_Number", "asc"));
			houseList = realestateBO.search(CHFlatVO.class, filters, orders, page);
			return houseList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 功能描述：根据buildingID 获取房屋数据
	 * @param districtID
	 * @param buildingID
	 * @return
	 * @throws Exception
	 */
	public List<CHFlatVO> findHousesByHouseID(String districtID,String houseID) throws Exception {
		List<CHFlatVO> houseList = new ArrayList<CHFlatVO>();
		try {
			RealestateBO realestateBO = new RealestateBO();
			// 查询房屋列表
			List<MetaFilter> filters = new ArrayList<MetaFilter>();
			filters.add(new MetaFilter("houseID", "=", houseID));
			filters.add(new MetaFilter("districtID", "=", districtID));
			//加载 lotID
			houseList = realestateBO.search(CHFlatVO.class, filters, null, null);
			return houseList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 功能描述：根据buildingID 获取房屋数据
	 * @param districtID
	 * @param buildingID
	 * @return
	 * @throws Exception
	 */
	public List<EmptyVO> queryTransaction(long transactionID,int status) throws Exception {
		List<EmptyVO> houseList = new ArrayList<EmptyVO>();
		try {
			RealestateBO realestateBO = new RealestateBO();
			houseList = realestateBO.queryTransactionByTransactionID(transactionID,status);
			return houseList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List searchHouseHintVO(long houseID) throws Exception {
		List<HouseHintVO> houseList = new ArrayList<HouseHintVO>();
		try {
			RealestateBO realestateBO = new RealestateBO();
			// 查询房屋列表
			List<MetaFilter> filters = new ArrayList<MetaFilter>();
			filters.add(new MetaFilter("houseID", "=", houseID));
			houseList = realestateBO.search(HouseHintVO.class, filters, null, null);
			return houseList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List searchHouseType(long houseID) throws Exception {
		List<CHHouseTypeVO> houseList = new ArrayList<CHHouseTypeVO>();
		try {
			RealestateBO realestateBO = new RealestateBO();
			// 查询房屋列表
			List<MetaFilter> filters = new ArrayList<MetaFilter>();
			filters.add(new MetaFilter("houseID", "=", houseID));
			houseList = realestateBO.search(CHHouseTypeVO.class, filters, null, null);
			return houseList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * 功能描述：查询该房屋是否存在土地初始登记
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public boolean checkLandRealExist(long houseId) throws Exception {
		try {
			RealestateBO realestateBO = new RealestateBO();
			return  realestateBO.checkLandRealExist(houseId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List<CfgFileStandardVO> searchFileRule(int typeBID,int status) throws Exception {
		List<CfgFileStandardVO> houseList = new ArrayList<CfgFileStandardVO>();
		try {
			RealestateBO realestateBO = new RealestateBO();
			houseList = realestateBO.searchFileRule(typeBID,status);
			return houseList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<CHFlatVO> findHousesForCreate(List<String> houseIDs,int districtID) throws Exception {
		try{
			RealestateBO realestateBO = new RealestateBO();
			return realestateBO.findHousesForCreate(houseIDs,districtID);
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
		}
	}

	/**
	 * 功能描述：查询现势权属 
	 * @param houseID
	 * @param districtID
	 * @return
	 * @throws Exception
	 */
	public List<RHouseRightView> searchRHouseRightView(long houseID,int districtID) throws Exception {
		List<RHouseRightView> houseList = new ArrayList<RHouseRightView>();
		try {
			RealestateBO realestateBO = new RealestateBO();
			// 查询房屋列表
			List<MetaFilter> filters = new ArrayList<MetaFilter>();
			filters.add(new MetaFilter("houseID", "=", houseID));
			filters.add(new MetaFilter("districtID", "=", districtID));
			houseList = realestateBO.search(RHouseRightView.class, filters, null, null);
			return houseList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 功能描述：查询临时权属
	 * @param houseID
	 * @param districtID
	 * @return
	 * @throws Exception
	 */
	public List<TempRHouseRightView> searchTempRHouseRightView(long houseID,int districtID) throws Exception {
		List<TempRHouseRightView> houseList = new ArrayList<TempRHouseRightView>();
		try {
			RealestateBO realestateBO = new RealestateBO();
			// 查询房屋列表
			List<MetaFilter> filters = new ArrayList<MetaFilter>();
			filters.add(new MetaFilter("houseID", "=", houseID));
			filters.add(new MetaFilter("districtID", "=", districtID));
			houseList = realestateBO.search(TempRHouseRightView.class, filters, null, null);
			return houseList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 功能描述：查询地的现势权属信息 
	 * @param lotID
	 * @param districtID
	 * @return
	 * @throws Exception
	 */
	public List<RLandRightView> searchRLandRightView(long lotID, int districtID) throws Exception {
		List<RLandRightView> houseList = new ArrayList<RLandRightView>();
		try {
			RealestateBO realestateBO = new RealestateBO();
			// 查询房屋列表
			List<MetaFilter> filters = new ArrayList<MetaFilter>();
			filters.add(new MetaFilter("lotID", "=", lotID));
			filters.add(new MetaFilter("districtID", "=", districtID));
			houseList = realestateBO.search(RLandRightView.class, filters, null, null);
			return houseList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 功能描述：查询地的现势权属信息 
	 * @param lotID
	 * @param districtID
	 * @return
	 * @throws Exception
	 */
	public List<TempRLandRightView> searchTempRLandRightView(long lotID, int districtID) throws Exception {
		List<TempRLandRightView> houseList = new ArrayList<TempRLandRightView>();
		try {
			RealestateBO realestateBO = new RealestateBO();
			// 查询房屋列表
			List<MetaFilter> filters = new ArrayList<MetaFilter>();
			filters.add(new MetaFilter("lotID", "=", lotID));
			filters.add(new MetaFilter("districtID", "=", districtID));
			houseList = realestateBO.search(TempRLandRightView.class, filters, null, null);
			return houseList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public boolean isHouseInLotUses(RLandRightView rLand, CHFlatVO house) throws Exception {
		RealestateBO realestateBO = new RealestateBO();
		// 房屋表中用途usersno==0时，房屋上加载该土地权属信息
		if (house.getUsersNO() <= 0)
			return true;
		// 根据 lotid，districtid，rightid，righttype，查找证地表，证土地用图表
		try {
			List<CurrentCerLandSubUsesVO> list;
			if (rLand.getDbFlag() == 1) {
				list = realestateBO.getCurrentCerLandUses(rLand.getLotID(), rLand.getDistrictID(), rLand.getRightID(),rLand.getRightType(), house.getUsersNO());
			} else {
				list = realestateBO.getTempCerLandUses(rLand.getLotID(), rLand.getDistrictID(), rLand.getRightID(),rLand.getRightType(), house.getUsersNO());
			}
			// 不存在多用途记录，不加载土地权属
			if (list.isEmpty())
				return false;
			// 循环查看证地多用途，有满足条件的
			boolean load = false;
			for (CurrentCerLandSubUsesVO vo : list) {
				long lotID = Long.parseLong(String.valueOf(vo.getAttribute("lotID")));
				if (lotID == house.getLotID() && house.getUsersNO() == vo.getUsersNo()) {
					load = true;
					break;
				}
			}
			return load;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 功能描述：插入短信记录
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public long addSignSms(DocMessageVO message) throws Exception {
		try {
			RealestateBO realestateBO = new RealestateBO();
			return  realestateBO.add(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
