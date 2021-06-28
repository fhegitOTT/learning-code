package com.netcom.nkestate.fhhouse.salecontract.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO;
import com.netcom.nkestate.fhhouse.manage.vo.HouseBandAverageVO;
import com.netcom.nkestate.fhhouse.manage.vo.PresellDistrictidManageVO;
import com.netcom.nkestate.fhhouse.manage.vo.XGCityVO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingHouseVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4HireVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4LimitVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4OtherVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4RealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVOFHE;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractCancelPwdVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SupportVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.XgBuyerOwnerNameVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.XgLimitSaleContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.XgTempBuyerInfoVO;
import com.netcom.nkestate.fhhouse.salecontractFHE.vo.ContractDealFHEVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;
import com.netcom.nkestate.framework.vo.DictVO;

/**
 * 签约管理模块DAO
 */
public class SignContractDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(SignContractDAO.class.getName());

	/**
	 * 功能描述：查询开盘单元下的楼栋列表
	 * @param signerID
	 * @param startID
	 * @return
	 * @throws Exception
	 */
	public List<SupportVO> queryUserBuildingJson(long signerID,long startID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select distinct b.building_id as code,b.building_name as name ");
			sb.append(" from building_house a,building b,pro_pre_bld_signer c ");
			sb.append(" where a.building_id = b.building_id ");
			sb.append(" and a.building_id = c.building_id ");
			sb.append(" and a.start_id =c.start_id ");
			sb.append(" and a.start_id = ? ");
			sb.append(" and c.signer_id = ? ");
			sb.append(" order by b.building_name ");
			params.add(startID);
			params.add(signerID);
			List list = DataBaseUtil.select(sb.toString(), params, SupportVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}


	/**
	 * 功能描述：查询用户操作权限下的楼栋房屋列表
	 * @param signerID
	 * @param startID
	 * @param buildingID
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> queryUserHouseListFHE(long signerID,String startID,String buildingID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			//sb.append(" select a1.houseid as house_id,nvl(a2.status,9) as status,a2.manual_Status ");
			sb.append(" select a1.houseid as house_id, nvl(a2.status,9) as status ,a1.room_number,a1.FLOOR_FROM as floor ");
			sb.append(" ,a1.room_number,a1.FLOOR_FROM as floor  ");

			sb.append(" from ch_flat a1,");
			//sb.append(" select distinct b.house_id,b.status,b.room_number,b.floor,b.manual_Status  ");
			sb.append(" ( select distinct b.house_id,b.status,b.room_number,b.floor   ");
			sb.append(" from building_house a,ss_contract_fhe_house b,pro_pre_bld_signer c  ");
			sb.append(" where a.start_id=c.start_id and a.building_id = c.building_id  ");
			sb.append(" and a.house_id=b.house_id  and a.start_id=1569 ");
			sb.append(" and a.building_id=2500395850  and c.signer_id=1416  "); //
			sb.append(" )a2 ");

			//sb.append(" where a1.houseid = a2.house_id(+) ");
			sb.append(" where a1.houseid = a2.house_id ");
			sb.append(" and a1.buildingid = 2500395850 ");

			sb.append(" order by a1.FLOOR_FROM, lpad(a1.room_number,20,'0')  ");
			//params.add(startID);
			//params.add(buildingID);
			//params.add(signerID);
			//params.add(buildingID);
			List list = DataBaseUtil.select(sb.toString(), params, HouseVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：查询用户操作权限下的楼栋房屋列表
	 * @param signerID
	 * @param startID
	 * @param buildingID
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> queryUserHouseList(long signerID,String startID,String buildingID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a1.houseid as house_id,nvl(a2.status,9) as status,a2.manual_Status ");
			sb.append(" ,a1.room_number,a1.FLOOR_FROM as floor  ");

			sb.append(" from ch_flat a1,( ");
			sb.append(" select distinct b.house_id,b.status,b.room_number,b.floor,b.manual_Status  ");
			sb.append(" from building_house a,house b,pro_pre_bld_signer c ");
			sb.append(" where a.start_id=c.start_id  ");
			sb.append(" and a.building_id = c.building_id ");
			sb.append(" and a.house_id=b.house_id ");
			sb.append(" and a.start_id=? and a.building_id=? and c.signer_id=? ");
			sb.append(" )a2 ");

			sb.append(" where a1.houseid = a2.house_id(+) ");
			//sb.append(" where a1.houseid = a2.house_id ");
			sb.append(" and a1.buildingid = ? ");

			sb.append(" order by a1.FLOOR_FROM, lpad(a1.room_number,20,'0')  ");
			params.add(startID);
			params.add(buildingID);
			params.add(signerID);
			params.add(buildingID);
			List list = DataBaseUtil.select(sb.toString(), params, HouseVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：查询房屋的产权信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<Attach4RealVO> queryAttach4RealInfo(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append("	select * from attach4real t ");
			sb.append(" where t.contract_id=? ");
			params.add(contractId);
			List list = DataBaseUtil.select(sb.toString(), params, Attach4RealVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：查询房屋的他项信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<Attach4OtherVO> queryAttach4OtherInfo(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append("	select * from attach4other t ");
			sb.append(" where t.contract_id=? ");
			params.add(contractId);
			List list = DataBaseUtil.select(sb.toString(), params, Attach4OtherVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：查询房屋的限制信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<Attach4LimitVO> queryAttach4LimitInfo(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append("	select * from attach4limit t ");
			sb.append(" where t.contract_id=? ");
			params.add(contractId);
			List list = DataBaseUtil.select(sb.toString(), params, Attach4LimitVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：查询房屋的租赁信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<Attach4HireVO> queryAttach4HireInfo(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append("	select * from attach4hire t ");
			sb.append(" where t.contract_id=? ");
			params.add(contractId);
			List list = DataBaseUtil.select(sb.toString(), params, Attach4HireVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select s.permitno,s.districtid from r_house_permit_t t,permit_t s ");
			sb.append(" where t.permitid=s.permitid ");
			sb.append(" and t.house_id=? ");
			sb.append(" and t.isfinish=-1 ");
			params.add(houseId);
			List list = DataBaseUtil.select(sb.toString(), params, HouseVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：查询定金合同的甲方信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<SellerInfoVO> querySellerInfo(String houseId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append("	select s.* from contractdeal t,sellerinfo s ");
			sb.append(" where t.contract_id=s.contract_id ");
			sb.append(" and t.status=2 and t.house_id=? ");
			params.add(houseId);
			List list = DataBaseUtil.select(sb.toString(), params, SellerInfoVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：查询定金合同的乙方信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<BuyerInfoVO> queryBuyerInfo(String houseId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append("	select s.* from contractdeal t,buyerinfo s ");
			sb.append(" where t.contract_id=s.contract_id ");
			sb.append(" and t.status=2 and t.house_id=? ");
			sb.append(" order by s.serial ");
			params.add(houseId);
			List list = DataBaseUtil.select(sb.toString(), params, BuyerInfoVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：查询定金合同的乙方密码
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<ContractCancelPwdVO> queryBuyerPwd(String houseId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append("	select s.* from contractdeal t,contractcancelpwd s ");
			sb.append(" where t.contract_id=s.contract_id ");
			sb.append(" and t.status=2 and t.house_id=? ");
			sb.append(" order by s.serial ");
			params.add(houseId);
			List list = DataBaseUtil.select(sb.toString(), params, ContractCancelPwdVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：检查签约人是否有这个房屋的签约权
	 * @param signerId
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public int checkSignerHouseRelate(String signerId,String houseId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select * ");
			sb.append(" from pro_pre_bld_signer t1, house t2,building_house t3 ");
			sb.append(" where t1.signer_id = ? and t1.building_id = t3.building_id and t1.start_id=t3.start_id ");
			sb.append(" and t2.house_id=t3.house_id and t2.house_id = ? ");
			params.add(signerId);
			params.add(houseId);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：检查房屋是否在开盘单元下
	 * @param startId
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public int checkHouseExist(String startId,String houseId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select * ");
			sb.append(" from building_house ");
			sb.append(" where house_id = ? ");
			sb.append(" and start_id = ? ");
			params.add(houseId);
			params.add(startId);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：查询许可证
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public HouseVO queryPermit(String houseId) throws Exception {
		try{
			HouseVO hvo = null;
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.* from permit_t a,r_house_permit_t b ");
			sb.append(" where a.permitid=b.permitid ");
			sb.append(" and b.house_id=? ");
			params.add(houseId);
			List list = DataBaseUtil.select(sb.toString(), params, HouseVO.class, conn);
			if(list != null && list.size() > 0){
				hvo = (HouseVO) list.get(0);
			}
			return hvo;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}


	/**
	 * 功能描述：检查合同单价是否大于设置的房屋价格
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public String checkHousePrice(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			String maxPrice = "";
			sb.append("	select nvl(a.reference_price,0)*(1+nvl(a.ratio,0)) maxprice ");
			sb.append(" from building_house a ,contractdeal b ");
			sb.append(" where a.house_id = b.house_id ");
			sb.append(" and contract_id=? ");
			params.add(contractId);
			List list = DataBaseUtil.select(sb.toString(), params, BuildingHouseVO.class, conn);
			if(list != null && list.size() > 0){
				BuildingHouseVO bhvo = (BuildingHouseVO) list.get(0);
				maxPrice = bhvo.getAttribute("maxprice").toString();
			}
			return maxPrice;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：草签(待签)合同列表FHE
	 * @param page
	 * @param contractID
	 * @param type
	 * @param projectID
	 * @param buyer
	 * @param startDate
	 * @param overDate
	 * @param startCode
	 * @param district
	 * @param road
	 * @param alley
	 * @param buildingnumber
	 * @param cell
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealFHEVO> queryEditContractFHE(long userID,String cmd,Page page,String contractID,String sellerName,String projectID,String buyerName) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			/*
			 * sb.append("	select distinct t.*,a.name as crepersonname from contractdeal t,start_unit s,buyerinfo f,project p,pro_pre_bld_signer b,signer a ");
			 * sb.append(" where t.start_id=s.start_id and t.contract_id=f.contract_id(+) "); sb.append(" and p.project_id=b.project_id and b.signer_id=? ");
			 * sb.append(" and p.project_id=t.project_id and t.status=? "); sb.append(" and t.creperson = a.loginname ");
			 */

			sb.append(" select distinct t.*   ");
			sb.append(" from  SS_CONTRACT_FHE_CONTRACT t , SS_CONTRACT_FHE_BUYER f ");
			sb.append(" where   t.status=1 ");

			//			params.add(userID);
			//			if("edit".equals(cmd)){
			//				params.add(FHConstant.CONTRACT_STATUS_EDIT);
			//			}
			//			if("waiting".equals(cmd)){
			//				params.add(FHConstant.CONTRACT_STATUS_WAIT4SIGN);
			//			}
			if(contractID != null && !"".equals(contractID)){
				sb.append(" and t.contract_id =?");
				params.add(contractID);
			}
			if(sellerName != null && !"".equals(sellerName)){
				sb.append(" and t.seller_name like ?");
				params.add("%" + sellerName + "%");
			}
			if(buyerName != null && !"".equals(buyerName)){
				sb.append(" and t.buyer_name like ?");
				params.add("%" + buyerName + "%");
			}
			if(projectID != null && !"".equals(projectID) && !"-999".equals(projectID)){
				sb.append(" and t.project_id = ?");
				params.add(projectID);
			}
			//sb.append(" order by t.upddate desc,t.updtime desc ");

			List list = DataBaseUtil.select(sb.toString(), params, ContractDealFHEVO.class, page, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}


	/**
	 * 功能描述：草签(待签)合同列表
	 * @param page
	 * @param contractID
	 * @param type
	 * @param projectID
	 * @param buyer
	 * @param startDate
	 * @param overDate
	 * @param startCode
	 * @param district
	 * @param road
	 * @param alley
	 * @param buildingnumber
	 * @param cell
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> queryEditContract(long userID,String cmd,Page page,String contractID,String type,String projectID,String buyer,String startDate,String overDate,String startCode,String district,String road,String alley,String buildingnumber,String cell) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append("	select distinct t.*,a.name as crepersonname from contractdeal t,start_unit s,buyerinfo f,project p,pro_pre_bld_signer b,signer a ");
			sb.append(" where t.start_id=s.start_id and t.contract_id=f.contract_id(+) ");
			sb.append(" and p.project_id=b.project_id and b.signer_id=? ");
			sb.append(" and p.project_id=t.project_id and t.status=? ");
			sb.append(" and t.creperson = a.loginname ");
			params.add(userID);
			if("edit".equals(cmd)){
				params.add(FHConstant.CONTRACT_STATUS_EDIT);
			}
			if("waiting".equals(cmd)){
				params.add(FHConstant.CONTRACT_STATUS_WAIT4SIGN);
			}
			if(contractID != null && !"".equals(contractID)){
				sb.append(" and t.contract_id =?");
				params.add(contractID);
			}
			if(buyer != null && !"".equals(buyer)){
				sb.append(" and f.buyer_name like ?");
				params.add("%" + buyer + "%");
			}
			if(district != null && !"".equals(district) && !"-999".equals(district)){
				sb.append(" and p.districtid = ? ");
				params.add(district);
			}
			if(startCode != null && !"".equals(startCode)){
				sb.append(" and s.start_code =?");
				params.add(startCode);
			}
			if(projectID != null && !"".equals(projectID) && !"-999".equals(projectID)){
				sb.append(" and p.project_id = ?");
				params.add(projectID);
			}
			if(startDate != null && !"".equals(startDate)){
				sb.append(" and t.SIGNDATE >=? ");
				String a = startDate.replaceAll("-", "");
				a += "000000";
				params.add(a);
			}
			if(overDate != null && !"".equals(overDate)){
				sb.append(" and t.SIGNDATE <=? ");
				String a = overDate.replaceAll("-", "");
				a += "000000";
				params.add(a);
			}
			if(type != null && !"".equals(type) && !"-999".equals(type)){
				sb.append(" and t.TYPE=? ");
				params.add(type);
			}
			if(road != null && !"".equals(road)){
				sb.append(" and t.ROAD like ? ");
				params.add("%" + road + "%");
			}
			if(alley != null && !"".equals(alley)){
				sb.append(" and t.lane_name like ? ");
				params.add("%" + alley + "%");
			}
			if(buildingnumber != null && !"".equals(buildingnumber)){
				sb.append(" and t.sub_lane like ? ");
				params.add("%" + buildingnumber + "%");
			}
			if(cell != null && !"".equals(cell)){
				sb.append(" and t.buildingnumber like ?");
				params.add("%" + cell + "%");
			}
			sb.append(" order by t.upddate desc,t.updtime desc ");

			List list = DataBaseUtil.select(sb.toString(), params, ContractDealVO.class, page, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：删除甲方信息
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public int delSellerInfo(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	delete from sellerinfo s ");
			sb.append(" where s.contract_id=? ");
			params.add(contractId);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}


	/**
	 * 功能描述：删除乙方信息
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public int delBuyerInfo(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	delete from buyerinfo b ");
			sb.append(" where b.contract_id=? ");
			params.add(contractId);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：删除合同
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public int delContractIDFHE(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	update SS_CONTRACT_FHE_CONTRACT b set b.status = 0");
			sb.append(" where b.contract_id=? ");
			params.add(contractId);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}
	
	/**
	 * 功能描述：删除附件信息
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public int delAttachInfo(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	delete from attach a ");
			sb.append(" where a.contract_id=? ");
			params.add(contractId);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：删除附件一付款信息
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public int delAttach1MoneyInfo(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	delete from attach1money a ");
			sb.append(" where a.contract_id=? ");
			params.add(contractId);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：删除合同乙方密码信息
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public int delContractCancelPwdInfo(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	delete from contractcancelpwd c ");
			sb.append(" where c.contract_id=? ");
			params.add(contractId);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：已签合同列表FHE
	 * @param page
	 * @param contractID
	 * @param type
	 * @param projectID
	 * @param buyer
	 * @param startDate
	 * @param overDate
	 * @param startCode
	 * @param district
	 * @param road
	 * @param alley
	 * @param buildingnumber
	 * @param cell
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealFHEVO> queryContractFHE(String status,String cmd,Page page,String contractID,String type,String projectName,String buyer) throws Exception {

		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct t.*   ");
			sb.append(" from  SS_CONTRACT_FHE_CONTRACT t , SS_CONTRACT_FHE_BUYER f ");
			sb.append(" where   t.status=2 ");
			//			sb.append("	select distinct t.* from contractdeal t,start_unit s,buyerinfo f,project p,pro_pre_bld_signer b ");
			//			sb.append(" where t.start_id=s.start_id and t.contract_id=f.contract_id ");
			//			sb.append(" and p.project_id=t.project_id ");
			//			sb.append(" and p.project_id=b.project_id and b.signer_id=? ");


			if(contractID != null && !"".equals(contractID)){
				sb.append(" and t.contract_id =?");
				params.add(contractID);
			}
			if(buyer != null && !"".equals(buyer)){
				sb.append(" and f.buyer_name like ?");
				params.add("%" + buyer + "%");
			}
			

			List list = DataBaseUtil.select(sb.toString(), params, ContractDealFHEVO.class, page, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：已签合同列表
	 * @param page
	 * @param contractID
	 * @param type
	 * @param projectID
	 * @param buyer
	 * @param startDate
	 * @param overDate
	 * @param startCode
	 * @param district
	 * @param road
	 * @param alley
	 * @param buildingnumber
	 * @param cell
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> queryContract(String status,long userID,Page page,String contractID,String type,String projectID,String buyer,String startDate,String overDate,String startCode,String district,String road,String alley,String buildingnumber,String cell) throws Exception {


		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append("	select distinct t.* from contractdeal t,start_unit s,buyerinfo f,project p,pro_pre_bld_signer b ");
			sb.append(" where t.start_id=s.start_id and t.contract_id=f.contract_id ");
			sb.append(" and p.project_id=t.project_id ");
			sb.append(" and p.project_id=b.project_id and b.signer_id=? ");
			params.add(userID);
			if(status != null && !"".equals(status) && !"-999".equals(status)){
				sb.append(" and t.status=? ");
				params.add(status);
			}else{
				sb.append("  and t.status in (2,4,5,7,10,11)  ");
			}
			if(contractID != null && !"".equals(contractID)){
				sb.append(" and t.contract_id =?");
				params.add(contractID);
			}
			if(buyer != null && !"".equals(buyer)){
				sb.append(" and f.buyer_name like ?");
				params.add("%" + buyer + "%");
			}
			if(district != null && !"".equals(district) && !"-999".equals(district)){
				sb.append(" and p.districtid = ? ");
				params.add(district);
			}
			if(startCode != null && !"".equals(startCode)){
				sb.append(" and s.start_code =?");
				params.add(startCode);
			}
			if(projectID != null && !"".equals(projectID) && !"-999".equals(projectID)){
				sb.append(" and p.project_id = ?");
				params.add(projectID);
			}
			if(startDate != null && !"".equals(startDate)){
				sb.append(" and t.CONFIRMDATE >=? ");
				String a = startDate.replaceAll("-", "");
				a += "000000";
				params.add(a);
			}
			if(overDate != null && !"".equals(overDate)){
				sb.append(" and t.CONFIRMDATE <=? ");
				String a = overDate.replaceAll("-", "");
				a += "000000";
				params.add(a);
			}
			if(type != null && !"".equals(type) && !"-999".equals(type)){
				sb.append(" and t.TYPE=? ");
				params.add(type);
			}
			if(road != null && !"".equals(road)){
				sb.append(" and t.ROAD like ? ");
				params.add("%" + road + "%");
			}
			if(alley != null && !"".equals(alley)){
				sb.append(" and t.lane_name like ? ");
				params.add("%" + alley + "%");
			}
			if(buildingnumber != null && !"".equals(buildingnumber)){
				sb.append(" and t.sub_lane like ? ");
				params.add("%" + buildingnumber + "%");
			}
			if(cell != null && !"".equals(cell)){
				sb.append(" and t.buildingnumber like ?");
				params.add("%" + cell + "%");
			}
			sb.append(" order by t.upddate desc,t.updtime desc ");

			List list = DataBaseUtil.select(sb.toString(), params, ContractDealVO.class, page, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：缴款通知书打印
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> payContarct(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select a.contract_id,a.buyer_name,a.firstmoney,a.firstpaymentdate,a.secondmoney, ");
			sb.append(" a.endpaymentdate,a.secondpaymentdate,a.thirdmoney,a.thirdpaymentdate,a.fourthmoney, ");
			sb.append(" a.fourthpaymentdate,a.money,nvl(b.bank_name,'')||nvl(b.zhbank_name,'') bank_name, ");
			sb.append(" b.account_name,b.account_np,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') print_date ");
			sb.append(" from vw_contractdeal a,spf_dev_accountinfo b ");
			sb.append(" where a.zjjgid = b.id(+) and a.contract_id = ? ");

			params.add(contractId);
			List list = DataBaseUtil.select(sb.toString(), params, ContractDealVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：获取最高参考价格
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public List<BuildingHouseVO> queryMaxPrice(String contractID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" SELECT  NVL(A.REFERENCE_PRICE,0)*(1+NVL(A.RATIO,0)) MAXPRICE ");
			sb.append(" FROM BUILDING_HOUSE A , CONTRACTDEAL B   ");
			sb.append(" WHERE A.HOUSE_ID = B.HOUSE_ID ");
			if(contractID != null && !"".equals(contractID)){
				sb.append(" AND B.CONTRACT_ID=? ");
				params.add(contractID);
			}else{
				sb.append(" AND B.CONTRACT_ID=-1 ");
			}
			List bHList = DataBaseUtil.select(sb.toString(), params, BuildingHouseVO.class, conn);
			return bHList;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：删除附件四产权信息
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public int delAttach4Real(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	delete from attach4real a ");
			sb.append(" where a.contract_id=? ");
			params.add(contractId);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：删除附件四他项信息
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public int delAttach4Other(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	delete from attach4other a ");
			sb.append(" where a.contract_id=? ");
			params.add(contractId);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：删除附件四限制信息
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public int delAttach4Limit(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	delete from attach4limit a ");
			sb.append(" where a.contract_id=? ");
			params.add(contractId);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：删除附件四租赁信息
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public int delAttach4Hire(String contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	delete from attach4hire a ");
			sb.append(" where a.contract_id=? ");
			params.add(contractId);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	public List<DictVO> queryTemplateList(String projectID,String startID,String district,String type) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			List list = null;
			if(type != null && "2".equals(type)){
				sb.append(" select  distinct  contracttemplate_cs.templateid as code ,contracttemplate_cs.tempname as name ");
				sb.append(" from enterprisequalify,contracttemplate_cs,sellertemplate,project,start_unit ");
				sb.append(" where contracttemplate_cs.templateid = sellertemplate.template_id and project.project_id = contracttemplate_cs.projectid and contracttemplate_cs.startid = start_unit.start_id and enterprisequalify.comp_id = sellertemplate.comp_id ");
				sb.append(" and project.districtid in " + district);
				if(projectID != null && !"".equals(projectID)){
					sb.append(" and project.project_id = ? ");
					params.add(projectID);
				}else{
					sb.append(" and project.project_id = -1 ");
				}
				if(startID != null && !"".equals(startID)){
					sb.append(" and start_unit.start_id = ? ");
					params.add(startID);
				}else{
					sb.append(" and start_unit.start_id = -1 ");
				}
				list = DataBaseUtil.select(sb.toString(), params, DictVO.class, conn);
			}else if(type != null && "1".equals(type)){
				sb.append(" select  distinct  contracttemplate_ys.templateid as code ,contracttemplate_ys.tempname as name  ");
				sb.append(" from enterprisequalify,contracttemplate_ys,sellertemplate,project,start_unit ");
				sb.append(" where contracttemplate_ys.templateid = sellertemplate.template_id and project.project_id = contracttemplate_ys.projectid and contracttemplate_ys.startid = start_unit.start_id and enterprisequalify.comp_id = sellertemplate.comp_id ");
				sb.append(" and project.districtid in " + district);
				if(projectID != null && !"".equals(projectID)){
					sb.append(" and project.project_id = ? ");
					params.add(projectID);
				}else{
					sb.append(" and project.project_id = -1 ");
				}
				if(startID != null && !"".equals(startID)){
					sb.append(" and start_unit.start_id = ? ");
					params.add(startID);
				}else{
					sb.append(" and start_unit.start_id = -1 ");
				}
				list = DataBaseUtil.select(sb.toString(), params, DictVO.class, conn);
			}
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：判断房屋所在区县是否限购
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public long findState(String houseId) throws Exception {
		try{
			long nState = 0;
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.xgstate as nState from ct_district a,house b ");
			sb.append(" where a.code=b.districtid ");
			sb.append(" and b.house_id =? ");
			params.add(houseId);

			List list = DataBaseUtil.select(sb.toString(), params, XGCityVO.class, conn);
			if(list != null && list.size() > 0){
				XGCityVO xgCityVO = (XGCityVO) list.get(0);
				nState = xgCityVO.getNstate();
			}
			return nState;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：判断房屋性质是否限购
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> findAttribute(String houseId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select house_id from house ");
			sb.append(" where house_type not in (1,3,4,5,6,7,8,10,11,12,13,14,15) ");
			sb.append(" and house_id =? ");
			params.add(houseId);

			List list = DataBaseUtil.select(sb.toString(), params, HouseVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：查询本地限购信息
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public List<XgTempBuyerInfoVO> queryLocalXgInfo(String contractID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select b.name as buyername,b.cardno as buyercardno from xg_limitsale_contract a,xg_buyerownername b ");
			sb.append(" where a.limitsaleid=b.limitsaleid and a.contract_id=? ");
			params.add(contractID);

			List list = DataBaseUtil.select(sb.toString(), params, XgTempBuyerInfoVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	public int deleteLocalXgInfo(String contractID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" delete from xg_tempbuyerinfo a where a.limitsaleid in ");
			sb.append(" (select b.limitsaleid from xg_limitsale_contract b where b.contract_id=? ) ");
			params.add(contractID);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	public int deleteLocalHumanXgInfo(String contractID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" delete from xg_buyerownername a where a.limitsaleid in ");
			sb.append(" (select b.limitsaleid from xg_limitsale_contract b where b.contract_id=? ) ");
			params.add(contractID);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}


	/**
	 * 功能描述：查询房屋所在区县是否资金监管
	 * @param houseID
	 * @return
	 * @throws Exception
	 */
	public int queryPresellDistrictidManage(String houseID) throws Exception {
		try{
			int res = -1;
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select b.state,b.regulator  ");
			sb.append(" from house a, presell_districtid_manage b ");
			sb.append(" where a.house_id=? and a.districtid = b.districtid ");
			params.add(houseID);
			List list = DataBaseUtil.select(sb.toString(), params, PresellDistrictidManageVO.class, conn);
			if(list != null && list.size() > 0){
				PresellDistrictidManageVO pdmVo = (PresellDistrictidManageVO) list.get(0);
				res = pdmVo.getState();
			}
			return res;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：检查合同买方是否在限购申请的人员中
	 * @param xgID
	 * @param buyerName
	 * @param buyerCardcode
	 * @return
	 * @throws Exception
	 */
	public List<XgBuyerOwnerNameVO> queryXgBuyerInfo(String xgID,String buyerName,String buyerCardcode) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select * from XG_BUYEROWNERNAME ");
			sb.append(" where LIMITSALEID=? ");
			sb.append(" and name=? ");
			sb.append(" and cardno=? ");
			params.add(xgID);
			params.add(buyerName);
			params.add(buyerCardcode);
			List list = DataBaseUtil.select(sb.toString(), params, XgBuyerOwnerNameVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：房屋一房一价检查
	 * @param houseID
	 * @return
	 * @throws Exception
	 */
	public HouseBandAverageVO queryHouseAverage(String houseID) throws Exception {
		try{
			HouseBandAverageVO hbaVo = null;
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select t.nhouseaverage,t.nhouseaverage * (1 + t.nbandaverage) as nbandaverage, ");
			sb.append(" t.nhouseaverage * (1 - t.nbandaveragexia) as nbandaveragexia ");
			sb.append(" from housebandaverage t ");
			sb.append(" where t.nhouseid = ? ");
			params.add(houseID);
			List list = DataBaseUtil.select(sb.toString(), params, HouseBandAverageVO.class, conn);
			if(list != null && list.size() > 0){
				hbaVo = (HouseBandAverageVO) list.get(0);
			}
			return hbaVo;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：楼盘表数据检查
	 * @param buildingID
	 * @return
	 * @throws Exception
	 */
	public List<CHFlatVO> queryHouseList(String buildingID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select max(count(*)) as building_width ");
			sb.append(" from ch_flat  ");
			sb.append(" group by floor_from ");
			List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：根据合同编号和合同类型查询定金合同编号
	 * @param buildingID
	 * @return
	 * @throws Exception
	 */
	public String queryEarnestID(String contractID,String type) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select earnestid  ");
			if("1".equals(type)){
				sb.append(" from contract_detail_ys  ");
			}else{
				sb.append(" from contract_detail_cs  ");
			}
			sb.append(" where contract_id = ? ");
			params.add(contractID);
			rs = DataBaseUtil.executeQuery(sb.toString(), params, conn, pstmt);
			if(rs.next()){
				return rs.getString("earnestid");
			}else{
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 功能描述：查询存量房系统限购id使用情况
	 * @param xgid
	 * @return
	 * @throws Exception
	 */
	public List<XgLimitSaleContractVO> findSHXgTransList(long xgid) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("select * from SH_XG_LIMITSALE_CONTRACT  where limitsaleid=?");
			params.add(xgid);
			List list = DataBaseUtil.select(sb.toString(), params, XgLimitSaleContractVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：合同附件删除
	 * @param contractId
	 * @return
	 * @throws Exception
	 */
	public int delContractAttch(long contractId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	delete from attach a where a.contract_id = ?");
			params.add(contractId);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}


	public int addBuyerInfo(BuyerInfoVOFHE biVo) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	insert into SS_CONTRACT_FHE_BUYER values(");

			long contractID = biVo.getContractID();
			int serial = biVo.getSerial();
			String buyerName = biVo.getBuyerName();
			String buyerSex = biVo.getBuyerSex();
			String buyerAddress = biVo.getBuyerAddress();
			String buyerPostcode = biVo.getBuyerPostcode();
			String buyerCall = biVo.getBuyerCall();
			int signFlag = biVo.getSignFlag();

			sb.append(" " + contractID + ", " + serial + ", " + buyerName + ", " + buyerSex + ", " + buyerAddress + ", " + buyerPostcode + ", " + buyerCall + ", " + signFlag);
			sb.append(")");
			logger.debug("SQL:" + sb.toString());
			int result = DataBaseUtil.execute(sb.toString(), params, conn);

			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}


	//List<BuyerInfoVOFHE> list = scDAO.findAllBuyerInfoByContractIdFHE(biVo);
	public List<BuyerInfoVOFHE> findAllBuyerInfoByContractIdFHE(BuyerInfoVOFHE biVo) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	select * from SS_CONTRACT_FHE_BUYER where CONTRACT_ID = ?");
			params.add(biVo.getContractID());
			List list = DataBaseUtil.select(sb.toString(), params, BuyerInfoVOFHE.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	public List<BuyerInfoVO> findAllBuyerInfoByContractId(BuyerInfoVO biVo) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	select * from BUYERINFO where contract_id = ?");
			params.add(biVo.getContractID());
			List list = DataBaseUtil.select(sb.toString(), params, BuyerInfoVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：更新合同付款项
	 * @param totalmoney
	 * 总价
	 * @param contractID
	 * 合同编号
	 * @return long
	 * @throws Exception
	 * 更新异常
	 */
	public long updatePresellAttach2(double totalmoney,long contractID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	update presell_attach2money set totalmoney = ? where contract_id = ? ");
			params.add(totalmoney);
			params.add(contractID);
			return DataBaseUtil.execute(sb.toString(), params, conn);
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 功能描述：获取受理序号所在区市
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String getDistNameForZB(String code) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			String distName = "";
			sb.append("	select name from ct_zbdistrict_t where code = '" + code + "'");
			List list = DataBaseUtil.select(sb.toString(), null, BuyerInfoVO.class, conn);
			if(list != null && list.size() > 0){
				BuyerInfoVO bvo = (BuyerInfoVO) list.get(0);
				distName = bvo.getAttribute("name").toString();
			}
			return distName;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：根据houseID查询是否存在抵押
	 * @param houseID
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> checkOtherRightByHouseID(String houseID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	select * from R_HOUSE_OTHERRIGHT_T where houseID = ?");
			sb.append("	union ");
			sb.append("	select * from TEMPR_HOUSE_OTHERRIGHT_T where houseID = ?");
			params.add(houseID);
			params.add(houseID);
			List list = DataBaseUtil.select(sb.toString(), params, HouseVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}
	
	/**
	 * 2020.3.23 ph 功能描述：限购合同是否仅仅检查个人买方标志
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public long findxgManCheckState(String houseId) throws Exception {
		try{
			long xgManCheckState = 0;
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.xgManCheck as nState from ct_district a,house b ");
			sb.append(" where a.code=b.districtid ");
			sb.append(" and b.house_id =? ");
			params.add(houseId);

			List list = DataBaseUtil.select(sb.toString(), params, XGCityVO.class, conn);
			if(list != null && list.size() > 0){
				XGCityVO xgCityVO = (XGCityVO) list.get(0);
				xgManCheckState = xgCityVO.getNstate();
			}
			return xgManCheckState;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}
	
	/**
	 * 功能描述：根据houseID查询是否存在在建工程抵押,510,523,539
	 * @param houseID
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> checkZjOtherRightByHouseID(String houseID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	select a.* from R_HOUSE_OTHERRIGHT_T a,transaction_t b where a.transactionid = b.transactionid and b.typebid in (510,523,539)  and a.houseID = ?");
			sb.append("	union ");
			sb.append("	select a.* from TEMPR_HOUSE_OTHERRIGHT_T a,transaction_t b where a.transactionid = b.transactionid and b.typebid in (510,523,539) and a.houseID = ?");
			params.add(houseID);
			params.add(houseID);
			List list = DataBaseUtil.select(sb.toString(), params, HouseVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			
		}
	}
}
