/**
 * <p>RealestateBO.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 登记库BO <p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.interfaces.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.BaseRealestateBO;
import com.netcom.nkestate.fhhouse.interfaces.dao.RealestateDAO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHBuildingVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHLocationVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHLotVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CurrentCerLandSubUsesVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.DocMessageVO;
import com.netcom.nkestate.fhhouse.permit.vo.CfgFileStandardVO;
import com.netcom.nkestate.fhhouse.permit.vo.EmptyVO;
import com.netcom.nkestate.fhhouse.permit.vo.LocationVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.XgLimitSaleContractVO;


public class RealestateBO extends BaseRealestateBO {

	static Logger logger = Logger.getLogger(RealestateBO.class.getName());

	private RealestateDAO estateDao = new RealestateDAO();

	/**
	 * 功能描述：开盘单元关联楼栋，坐落查询 功能描述：TODO
	 * @param districtId
	 * @param roadname
	 * 路
	 * @param lanename
	 * 号
	 * @param sublane
	 * 栋
	 * @param streetnumber
	 * 单元
	 * @return
	 * @throws Exception
	 */
	public List<CHLocationVO> findLocationList(String districtId,String roadname,String lanename,String sublane,String streetnumber) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findLocationList(districtId, roadname, lanename, sublane, streetnumber);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：坐落查询楼栋
	 * @param locationId
	 * @return
	 * @throws Exception
	 */
	public List<CHBuildingVO> findBuidlingList(long locationId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findBuidlingList(locationId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询楼栋坐落
	 * @param buildingId
	 * @return
	 * @throws Exception
	 */
	public String findBuidlingLoaction(long buildingId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findBuidlingLoaction(buildingId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询房屋信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public CHFlatVO findCHFlat(long houseId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findCHFlat(houseId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}
	
	public List<CHFlatVO> findHousesForCreate(List<String> houseIDs,int districtID) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findHousesForCreate(houseIDs,districtID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}
	
	/**
	 * 功能描述：查询房屋信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public CHFlatVO findCHFlatBuilding(long houseId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findCHFlatBuilding(houseId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}
	

	/**
	 * 功能描述：检查房屋是否存在1034文件登记
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public boolean checkHouseHasFileReg(String houseId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.checkHouseHasFileReg(houseId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}


	/**
	 * 功能描述：查询地块信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public CHLotVO findChLot(String houseId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findChLot(houseId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询预售许可证号
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public String findPermitNo(String houseId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findPermitNo(houseId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询主体结构
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public String findBuildingMater(String houseId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findBuildingMater(houseId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询房屋坐落
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public String findLocation(String houseId,long districtId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findLocation(houseId, districtId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询不动产库限购信息
	 * @param houseId
	 * @param districtId
	 * @return
	 * @throws Exception
	 */
	public List<CHLotVO> queryXgInfo(String XgID) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.queryXgInfo(XgID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询本地是否存在该限购信息
	 * @param XgID
	 * @return
	 * @throws Exception
	 */
	public List<CHLotVO> queryXgApplicantInfo(String XgID) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.queryXgApplicantInfo(XgID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询不动产库限购人员信息
	 * @param XgID
	 * @return
	 * @throws Exception
	 */
	public List<CHLotVO> queryXgHumanInfo(String XgID) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.queryXgHumanInfo(XgID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询房屋坐落
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public CHLocationVO findLocationInfo(String houseId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findLocationInfo(houseId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：检查房屋是否存在查封
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public int checkHouseLimit(String houseId) throws Exception {
		try{
			openDAO(estateDao);
			int result = estateDao.checkHouseLimit(houseId);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：检查房屋是否存在异议
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public int checkHouseDisscent(String houseId) throws Exception {
		try{
			openDAO(estateDao);
			int result = estateDao.checkHouseDisscent(houseId);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询房屋信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public CHFlatVO findhouseInfo(String houseId) throws Exception {
		try{
			openDAO(estateDao);
			CHFlatVO chFlatVO = estateDao.findhouseInfo(houseId);
			return chFlatVO;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询土地信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public CHFlatVO findlandInfo(String houseId) throws Exception {
		try{
			openDAO(estateDao);
			CHFlatVO chFlatVO = estateDao.findlandInfo(houseId);
			return chFlatVO;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询产权信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<CHFlatVO> findRealInfo(String houseId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findRealInfo(houseId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询他项信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<CHFlatVO> findOtherInfo(String houseId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findOtherInfo(houseId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询限制信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<CHFlatVO> findLimitInfo(String houseId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findLimitInfo(houseId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询租赁信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<CHFlatVO> findHireInfo(String houseId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findHireInfo(houseId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询房屋的许可证信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> queryPermitInfo(String houseId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.queryPermitInfo(houseId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询登记库字典表信息
	 * @param sd_class
	 * @param sd_keyno
	 * @return
	 * @throws Exception
	 */
	public String querydictName(String tableName,String key) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.querydictName(tableName, key);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：顺序查询房屋的层数
	 * @param buildingID
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> findFloor(String buildingID) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findFloor(buildingID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	/**
	 * 功能描述：查询不动产限购id使用情况
	 * @param xgid
	 * @return
	 * @throws Exception
	 */
	public List<XgLimitSaleContractVO> findBdcXgTransList(long xgid) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findBdcXgTransList(xgid);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	public List searchLocations(String districtID, String roadName,	String laneName, String subLaneName, String streetNumber) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.searchLocations(districtID,  roadName,	 laneName,  subLaneName,  streetNumber);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	public CHBuildingVO searchBuildingsByBuildingID(int districtID,long buildingID) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.searchBuildingsByBuildingID(districtID, buildingID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}
	public List<EmptyVO> findHousesByLocationID(long locationID,int districtID) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.findHousesByLocationID(locationID, districtID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	public boolean checkLandRealExist(long houseId) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.checkLandRealExist(houseId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	public List<CfgFileStandardVO> searchFileRule(int typeBID, int status) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.searchFileRule(typeBID, status);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	public List<EmptyVO> getRealRightByHouseID(String houseIDs, int status) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.getRealRightByHouseID(houseIDs, status);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	public EmptyVO getRightByTransactionID(String transactionID, int districtID, int status) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.getRightByTransactionID(transactionID, districtID,status);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	public List<CurrentCerLandSubUsesVO> getCurrentCerLandUses(long lotID, int districtID, long rightID, int rightType,long usersNO) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.getCurrentCerLandUses(lotID, districtID,rightID,rightType,usersNO);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	public List<CurrentCerLandSubUsesVO> getTempCerLandUses(long lotID, int districtID, long rightID, int rightType,	long usersNO) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.getTempCerLandUses(lotID, districtID,rightID,rightType,usersNO);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	public List<EmptyVO> queryTransactionByTransactionID(long transactionID ,int status) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.queryTransactionByTransactionID(transactionID,status);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	public List<CHLotVO> searchLots(String lotID, String lotNumber,String districtID) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.searchLots(lotID,lotNumber,districtID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	public List<CHBuildingVO> searchBuildingsByLotID(long lotID, int districtID) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.searchBuildingsByLotID(lotID,districtID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	public List<EmptyVO> searchLandUseByLotID(String lotID) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.searchLandUseByLotID(lotID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	public List<CHFlatVO> searchHousesByBuildingIDAndLanduse(String landuseID, String buildingID, String districtID) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.searchHousesByBuildingIDAndLanduse(landuseID,buildingID,districtID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

	public List<LocationVO> searchLocationsByHouseID(String houseID, String districtID) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.searchLocationsByHouseID(houseID,districtID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}
	
	/**
	 * 功能描述：插入sms短信
	 * @param messageVO
	 * @return
	 * @throws Exception
	 */
	public long addSMS(DocMessageVO messageVO) throws Exception {
		try{
			openDAO(estateDao);
			return estateDao.addMessage(messageVO);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(estateDao);
		}
	}

}
