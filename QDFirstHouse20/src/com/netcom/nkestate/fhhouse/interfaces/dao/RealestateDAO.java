/**
 * <p>RealestateDAO.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 登记库DAO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.interfaces.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.BaseRealestateDAO;
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
import com.netcom.nkestate.framework.dao.DataBaseUtil;
import com.netcom.nkestate.framework.vo.DictVO;


public class RealestateDAO extends BaseRealestateDAO {

	static Logger logger = Logger.getLogger(RealestateDAO.class.getName());

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.* ");
			sb.append(" from common.ch_location a ");
			sb.append(" where 1=1 ");
			if(roadname != null && !"".equals(roadname)){
				sb.append(" and a.roadname like ? ");
				params.add("%" + roadname + "%");
			}
			if(lanename != null && !"".equals(lanename)){
				sb.append(" and a.lanename like ? ");
				params.add("%" + lanename + "%");
			}
			if(sublane != null && !"".equals(sublane)){
				sb.append(" and a.sublane like ? ");
				params.add("%" + sublane + "%");
			}
			if(streetnumber != null && !"".equals(streetnumber)){
				sb.append(" and a.streetnumber like ? ");
				params.add("%" + streetnumber + "%");
			}
			if(districtId != null && !"".equals(districtId)){
				sb.append(" and a.DISTRICTID = ?  ");
				params.add(districtId);
			}
			sb.append(" order by a.locationname ");

			//System.out.println(sb.toString());
			List list = DataBaseUtil.select(sb.toString(), params, CHLocationVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			List<CHBuildingVO> bList = new ArrayList<CHBuildingVO>();

			sb.append(" select distinct c.BuildingID ");
			sb.append(" from common.cl_flat_location b,common.ch_flat c ");
			sb.append(" where b.houseid = c.HouseID ");
			sb.append(" and b.districtid = c.DISTRICTID ");
			sb.append(" and b.locationid = ? ");
			sb.append(" order by c.BuildingID ");

			params.add(locationId);


			List list = DataBaseUtil.select(sb.toString(), params, CHBuildingVO.class, conn);

			if(list != null && list.size() > 0){
				//查询楼栋对应的坐落
				StringBuffer sb2 = new StringBuffer();
				List<Object> params2 = null;

				sb2.append(" select a.locationname as location_Name ");
				sb2.append(" from common.ch_location a,common.cl_flat_location b,common.ch_flat c ");
				sb2.append(" where a.locationid = b.locationid ");
				sb2.append(" and b.houseid = c.HouseID ");
				sb2.append(" and c.BuildingID = ? ");
				sb2.append(" order by a.locationid desc ");

				for(int i = 0; i < list.size(); i++){
					CHBuildingVO bvo = (CHBuildingVO) list.get(i);
					params2 = new ArrayList<Object>();
					params2.add(bvo.getBuildingID());
					List list2 = DataBaseUtil.select(sb2.toString(), params2, CHBuildingVO.class, conn);
					if(list2 != null && list2.size() > 0){
						bvo.setLocation_Name( ((CHBuildingVO) list2.get(0)).getLocation_Name());
						bList.add(bvo);
					}
				}
			}
			return bList;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			String location = "";
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.locationname as location_Name ");
			sb.append(" from common.ch_location a,common.cl_flat_location b,common.ch_flat c ");
			sb.append(" where a.locationid = b.locationid ");
			sb.append(" and b.houseid = c.HouseID ");
			sb.append(" and c.BuildingID = ? ");
			sb.append(" order by a.locationid desc ");
			params.add(buildingId);


			List list = DataBaseUtil.select(sb.toString(), params, CHBuildingVO.class, conn);
			if(list != null && list.size() > 0){
				CHBuildingVO vo = (CHBuildingVO) list.get(0);
				location = vo.getLocation_Name();
			}
			return location;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：查询楼栋坐落
	 * @param buildingId
	 * @return
	 * @throws Exception
	 */
	public CHFlatVO findCHFlat(long houseId) throws Exception {
		try{
			CHFlatVO flatvo = null;
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.*,b.house_type ");
			sb.append(" from common.ch_flat a,common.ch_house_type b ");
			sb.append(" where a.houseid=b.houseid(+) and a.houseid = ? ");
			params.add(houseId);

			List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			if(list != null && list.size() > 0){
				flatvo = (CHFlatVO) list.get(0);
			}
			return flatvo;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}
	
	/**
	 * 功能描述：查询楼栋坐落
	 * @param buildingId
	 * @return
	 * @throws Exception
	 */
	public CHFlatVO findCHFlatBuilding(long houseId) throws Exception {
		try{
			CHFlatVO flatvo = null;
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			
			sb.append(" select a.*,b.DOORPLATE,c.name housearchname ,c.name buildingTypeName");
			sb.append(" from common.ch_flat a,common.CH_BUILDING b ,common.CT_BUILDING_TYPE c ");
			sb.append(" where  a.BUILDINGID = b.BUILDINGID and a.districtID = b.districtID and a.building_type = c.code(+) and a.houseid = ? ");
			params.add(houseId);
			
			List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			if(list != null && list.size() > 0){
				flatvo = (CHFlatVO) list.get(0);
			}
			return flatvo;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
			
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
			boolean flag = false;
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select unitid as houseid ");
			sb.append(" from unirealestate.r_unit_filereg_t a,unirealestate.transaction_t b ");
			sb.append(" where a.transactionid = b.transactionid ");
			sb.append(" and a.districtid = b.districtid ");
			sb.append(" and a.unittype=100 ");
			sb.append(" and b.typebid=1034 ");
			sb.append(" and a.unitid=? ");
			params.add(houseId);

			List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			if(list != null && list.size() > 0){
				flag = true;
			}
			return flag;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			CHLotVO chLotVO = null;
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select d.* ");
			sb.append(" from common.ch_flat a,common.ch_building b,common.ch_naturebuilding c,common.ch_lot d ");
			sb.append(" where a.buildingid=b.buildingid ");
			sb.append(" and b.NatureID=c.NatureID ");
			sb.append(" and c.lotid=d.lotid ");
			sb.append(" and a.houseid = ? ");
			params.add(houseId);

			List list = DataBaseUtil.select(sb.toString(), params, CHLotVO.class, conn);
			if(list != null && list.size() > 0){
				chLotVO = (CHLotVO) list.get(0);
			}
			return chLotVO;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			String permitNo = "";
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.permitno from unirealestate.permit_t a,unirealestate.r_house_permit_t b ");
			sb.append(" where a.permitid=b.permitid ");
			sb.append(" and b.houseid=? ");
			params.add(houseId);

			List list = DataBaseUtil.select(sb.toString(), params, CHLotVO.class, conn);
			if(list != null && list.size() > 0){
				CHLotVO chLotVO = (CHLotVO) list.get(0);
				permitNo = chLotVO.getAttribute("permitno") == null ? "" : chLotVO.getAttribute("permitno").toString();
			}
			return permitNo;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			String buildingMater = "";
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select b.BUILDING_MATER from common.ch_flat a,common.ch_building b ");
			sb.append(" where a.BuildingID=b.BuildingID ");
			sb.append(" and a.houseid=? ");
			params.add(houseId);

			List list = DataBaseUtil.select(sb.toString(), params, CHLotVO.class, conn);
			if(list != null && list.size() > 0){
				CHLotVO chLotVO = (CHLotVO) list.get(0);
				buildingMater = chLotVO.getAttribute("BUILDING_MATER") == null ? "" : chLotVO.getAttribute("BUILDING_MATER").toString();
			}
			return buildingMater;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			String location = "";
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select c.locationname||a.ROOM_NUMBER location ");
			sb.append(" from common.ch_flat a,common.cl_flat_location b,common.ch_location c ");
			sb.append(" where a.HouseID = b.houseid and a.DISTRICTID = b.districtid ");
			sb.append(" and b.locationid = c.locationid and b.districtid = c.districtid ");
			sb.append(" and a.HouseID = ? and a.DISTRICTID = ? ");
			sb.append(" order by c.locationid desc ");
			params.add(houseId);
			params.add(districtId);

			List list = DataBaseUtil.select(sb.toString(), params, CHLotVO.class, conn);
			if(list != null && list.size() > 0){
				CHLotVO chLotVO = (CHLotVO) list.get(0);
				location = chLotVO.getAttribute("location") == null ? "" : chLotVO.getAttribute("location").toString();
			}
			return location;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select t.* from xg_applicant_t t ");
			sb.append(" where t.state = 2 ");
			sb.append(" and t.xgid = ? ");
			params.add(XgID);

			List list = DataBaseUtil.select(sb.toString(), params, CHLotVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select t.* from xg_applicant_t t ");
			sb.append(" where t.xgid = ? ");
			params.add(XgID);
			List list = DataBaseUtil.select(sb.toString(), params, CHLotVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select t.* from xg_human_t t ");
			sb.append(" where t.xgid = ? ");
			sb.append(" order by t.seqnum ");
			params.add(XgID);
			List list = DataBaseUtil.select(sb.toString(), params, CHLotVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			CHLocationVO clVO = null;
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select c.* from common.cl_flat_location t,common.ch_location c ");
			sb.append(" where t.houseid=? ");
			sb.append(" and t.locationid=c.locationid  ");
			sb.append(" order by c.locationid desc ");
			params.add(houseId);
			List list = DataBaseUtil.select(sb.toString(), params, CHLocationVO.class, conn);
			if(list != null && list.size() > 0){
				clVO = (CHLocationVO) list.get(0);
			}
			return clVO;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select t.limitid from r_house_limit_t t ");
			sb.append(" where t.houseid=? ");
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
	 * 功能描述：检查房屋是否存在异议
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public int checkHouseDisscent(String houseId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select t.dissentid from r_unit_dissent_t t ");
			sb.append(" where t.unitid=? ");
			sb.append(" and t.unittype=100 ");
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
	 * 功能描述：查询房屋信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public CHFlatVO findhouseInfoFHE(String houseId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			CHFlatVO chFlatVO = new CHFlatVO();

			sb.append(" select b.building_number as doornum, ");
			sb.append("        h.room_number     as part, ");
			sb.append("        nvl(decode(h.FLAREA,0,null,h.FLAREA),h.plan_flarea)     as buildingarea, ");
			sb.append("        b.floors          as totalfloors, ");
			sb.append("        to_char(b.complete_date,'yyyy-mm-dd')   as finisheddate, ");
			sb.append("        ct.name           as housetype, ");
			sb.append("        cm.name           as housearch, ");
			//	sb.append("        cd.name           as sourceid ");
			sb.append("        b.own           as own, ");
			sb.append("        b.location_Name           as locationname, ");
			sb.append("        b.districtID           as districtid ");
			sb.append("   from common.ch_flat             h, ");
			sb.append("        common.ch_building         b, ");
			sb.append("        common.ct_building_type    ct, ");
			sb.append("        common.ct_building_mater   cm, ");
			sb.append("        common.ct_building_descent cd ");
			sb.append("  where h.houseid = ? ");
			sb.append("    and h.buildingid = b.buildingid ");
			sb.append("    and h.building_type = ct.code(+) ");
			sb.append("    and b.building_mater = cm.code(+) ");
			sb.append("    and h.home_descent = cd.code(+) ");
			params.add(houseId);
			List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			if(list != null && list.size() > 0){
				chFlatVO = (CHFlatVO) list.get(0);
			}

			return chFlatVO;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			CHFlatVO chFlatVO = new CHFlatVO();

			sb.append(" select b.building_number as doornum, ");
			sb.append("        h.room_number     as part, ");
			sb.append("        nvl(decode(h.FLAREA,0,null,h.FLAREA),h.plan_flarea)     as buildingarea, ");
			sb.append("        b.floors          as totalfloors, ");
			sb.append("        to_char(b.complete_date,'yyyy-mm-dd')   as finisheddate, ");
			sb.append("        ct.name           as housetype, ");
			sb.append("        cm.name           as housearch, ");
			sb.append("        cd.name           as sourceid ");
			sb.append("   from common.ch_flat             h, ");
			sb.append("        common.ch_building         b, ");
			sb.append("        common.ct_building_type    ct, ");
			sb.append("        common.ct_building_mater   cm, ");
			sb.append("        common.ct_building_descent cd ");
			sb.append("  where h.houseid = ? ");
			sb.append("    and h.buildingid = b.buildingid ");
			sb.append("    and h.building_type = ct.code(+) ");
			sb.append("    and b.building_mater = cm.code(+) ");
			sb.append("    and h.home_descent = cd.code(+) ");
			params.add(houseId);
			List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			if(list != null && list.size() > 0){
				chFlatVO = (CHFlatVO) list.get(0);
			}

			return chFlatVO;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}
	
	public List<CHFlatVO> findHousesForCreate(List<String> houseIDs,int districtID) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT C.LOTID, A.*, D.LOCATIONID,D.SUBLANE,D.STREETNUMBER ");
		sb.append("  FROM COMMON.CH_FLAT           A, ");
		sb.append("       COMMON.CH_BUILDING       B, ");
		sb.append("       COMMON.CH_NATUREBUILDING C, ");
		sb.append("       COMMON.CH_LOCATION       D, ");
		sb.append("       COMMON.CL_FLAT_LOCATION  E ");
		sb.append(" WHERE A.BUILDINGID = B.BUILDINGID ");
		sb.append("   AND C.NATUREID = B.NATUREID ");
		sb.append("   AND A.HOUSEID = E.HOUSEID ");
		sb.append("   AND E.LOCATIONID = D.LOCATIONID ");
		if (districtID > 0) {
			sb.append(" and a.districtID = ? ");
			params.add(districtID);
		}
		if (houseIDs != null && houseIDs.size() > 0) {
			int size = houseIDs.size();
			int start = 0;
			int length = 500;
			if (length > (size - start)) {
				length = size - start;
			}
			sb.append(" and (");
			while (length > 0) {
				sb.append(" a.houseID in (");
				for (int i = start; i < start + length; i++) {
					String houseID = houseIDs.get(i);
					sb.append("'").append(houseID).append("',");
				}
				sb.replace(sb.length() - 1, sb.length(), ")");

				start += length;
				if (length > (size - start)) {
					length = size - start;
				}
				if (length > 0) {
					sb.append("  or ");
				}
			}
			sb.append(")");
		}
		List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);

		return list;
	}

	/**
	 * 功能描述：查询土地信息
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public CHFlatVO findlandInfo(String houseId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			CHFlatVO chFlatVO = null;

			sb.append(" select to_char(b.usabledate,'yyyy-mm-dd')||'至'||to_char(b.enddate,'yyyy-mm-dd')||'止' limittime,b.*,c.name as landsourceStr ");
			sb.append(" ,nvl(decode(b.blockarea,0,null,b.blockarea),b.landarea) blockarea2, to_char(b.enddate,'yyyy-mm-dd') enddateStr ");
			sb.append(" from r_house_real_t a, currentcer_land_t b,common.CT_LAND_DESCENT c ");
			sb.append(" where a.realid = b.rightid and b.righttype = 1 and b.landsource = c.code(+) ");
			sb.append(" and a.houseid=? ");
			params.add(houseId);
			List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			if(list != null && list.size() > 0){
				chFlatVO = (CHFlatVO) list.get(0);
			}

			return chFlatVO;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select a.realid,a.realno,a.location,to_char(c.startdate,'yyyy-mm-dd') startdate ");
			sb.append(" ,to_char(a.passdate,'yyyy-mm-dd') passdate ,c.typeaid,c.typebid,a.memo,a.transactionid,a.districtid ");
			sb.append(" from right_real_t a, r_house_real_t b, transaction_t c ");
			sb.append(" where a.realid = b.realid and a.districtid = b.districtid ");
			sb.append("   and a.transactionid = c.transactionid and a.districtid = c.districtid ");
			sb.append("   and b.houseid = ? and nvl(b.isprecert,0)<>1 ");
			params.add(houseId);
			List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			if(list!=null && list.size()>0){
				List<Object> params1 = null;
				String sql2 = "select a.name as ownerName from right_real_owner_t a where realid = ? order by a.seqnum asc ";
				for(int i = 0; i < list.size(); i++){
					CHFlatVO vo = (CHFlatVO) list.get(i);
					params1 = new ArrayList<Object>();
					params1.add(vo.getAttribute("realid"));
					List ownerList = DataBaseUtil.select(sql2, params1, CHFlatVO.class, conn);
					String ownerName = "";
					String otherName = "";
					if(ownerList!=null && ownerList.size()>0){
						for(int j = 0; j < ownerList.size(); j++){
							CHFlatVO ownerVO = (CHFlatVO) ownerList.get(j);
							if(j==0){
								ownerName = ownerVO.getAttribute("ownerName").toString();
							}else{
								otherName = otherName + " " + ownerVO.getAttribute("ownerName");
							}
						}
					}
					vo.setAttribute("ownerName", ownerName);
					vo.setAttribute("otherName", otherName);
				}
			}
			
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("   select e.name as otherrighttypeid, ");
			sb.append("       a.location,  ");
			sb.append("       a.loanyuanvalue, ");
			sb.append("       a.otherno, ");
			sb.append("       to_char(a.planstartdate,'yyyy-mm-dd') planstartdate, ");
			sb.append("       to_char(a.planenddate,'yyyy-mm-dd') planenddate,");
			sb.append("       to_char(c.startdate,'yyyy-mm-dd') startdate, ");
			sb.append("       to_char(a.passdate,'yyyy-mm-dd') passdate, ");
			sb.append("       a.memo,a.districtid,a.otherid,a.renter  ");
			sb.append("  from right_other_t       a, ");
			sb.append("       transaction_t       c, ");
			sb.append("       r_house_other_t d, ");
			sb.append("       common.CT_OTHERRIGHT_TYPE e ");
			sb.append(" where d.otherid = a.otherid and a.districtid = d.districtid ");
			sb.append("   and a.transactionid = c.transactionid and a.districtid = d.districtid ");
			sb.append("   and a.otherrighttypeid = e.code(+) ");
			sb.append("   and d.houseid = ? ");
			params.add(houseId);
			List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			if(list != null && list.size() > 0){
				List<Object> params1 = null;
				//抵押权人查询
				String sql2 = "select a.name as otherName from right_other_owner_t a where otherid = ? and districtid = ? order by a.seqnum asc ";
				//抵押人查询
				String sql3 = "select a.name as realName from right_other_man_t a where otherid = ? and districtid = ? order by a.seqnum asc ";
				for(int i = 0; i < list.size(); i++){
					CHFlatVO vo = (CHFlatVO) list.get(i);
					params1 = new ArrayList<Object>();
					params1.add(vo.getAttribute("otherid"));
					params1.add(vo.getDistrictID());
					//抵押权人
					List ownerList = DataBaseUtil.select(sql2, params1, CHFlatVO.class, conn);
					String otherName = "";
					if(ownerList != null && ownerList.size() > 0){
						for(int j = 0; j < ownerList.size(); j++){
							CHFlatVO tempVO = (CHFlatVO) ownerList.get(j);
							if(j == 0){
								otherName = tempVO.getAttribute("otherName").toString();
							}else{
								otherName = otherName + " " + tempVO.getAttribute("otherName");
							}
						}
					}
					vo.setAttribute("name", otherName);


					//抵押人
					String realName = "";
					//List realList = DataBaseUtil.select(sql3, params1, CHFlatVO.class, conn);
					//if(realList != null && realList.size() > 0){
					//	for(int j = 0; j < realList.size(); j++){
					//		CHFlatVO tempVO = (CHFlatVO) realList.get(j);
					//		if(j == 0){
					//			realName = tempVO.getAttribute("realName").toString();
					//		}else{
					//			realName = realName + " " + tempVO.getAttribute("realName");
					//		}
					//	}
					//}
					realName = vo.getAttribute("renter") == null ? "" : vo.getAttribute("renter").toString();
					vo.setAttribute("realName", realName);
				}
			}

			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select to_char(b.startdate,'yyyy-mm-dd') startdate, ");
			sb.append("	d.name,e.name bname, ");
			sb.append("	a.location, ");
			sb.append("	a.limitno, ");
			sb.append("	to_char(a.planstartdate, 'yyyy-mm-dd') planstartdate, ");
			sb.append("	to_char(a.planenddate, 'yyyy-mm-dd') planenddate, ");
			sb.append("	to_char(a.passdate, 'yyyy-mm-dd') passdate ");
			sb.append("   from right_limit_t a, transaction_t b, r_house_limit_t c,right_limit_owner_t d,right_limit_man_t e ");
			sb.append("  where a.transactionid = b.transactionid ");
			sb.append("    and a.limitid = c.limitid ");
			sb.append("    and a.limitid = d.limitid ");
			sb.append("    and a.limitid = e.limitid ");
			sb.append("    and c.houseid = ? ");
			params.add(houseId);
			List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	select e.name, ");
			sb.append("       a.hiretype, ");
			sb.append("       b.name hirename, ");
			sb.append("       b.name, ");
			sb.append("       a.hireuse, ");
			sb.append("       a.location, ");
			sb.append("       to_char(a.planstartdate, 'yyyy-mm-dd') || '至' || ");
			sb.append("       to_char(a.planenddate, 'yyyy-mm-dd') || '止' limittime, ");
			sb.append("       a.hireno, ");
			sb.append("       to_char(c.startdate, 'yyyy-mm-dd') startdate, ");
			sb.append("       to_char(a.passdate, 'yyyy-mm-dd') passdate");
			sb.append("  from right_hire_t       a, ");
			sb.append("       right_hire_owner_t b, ");
			sb.append("       transaction_t      c, ");
			sb.append("       r_house_hire_t d, ");
			sb.append("       right_hire_provider_t   e ");
			sb.append(" where a.hireid = b.hireid ");
			sb.append("   and a.transactionid = c.transactionid ");
			sb.append("   and a.hireid = d.hireid ");
			sb.append("   and a.hireid = e.hireid ");
			sb.append("   and d.houseid = ? ");
			params.add(houseId);
			List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
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
			sb.append(" and t.houseid=? ");
			//sb.append(" and t.isfinish=-1 ");
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
	 * 功能描述：查询登记库字典表信息
	 * @param sd_class
	 * @param sd_keyno
	 * @return
	 * @throws Exception
	 */
	public String querydictName(String tableName,String key) throws Exception {
		try{
			String value = "";
			if(tableName == null || key == null || "".equals(key)){
				return "";
			}
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	select * from " + tableName + " a");
			sb.append("	where a.code=? ");
			params.add(key);
			List list = DataBaseUtil.select(sb.toString(), params, CHLotVO.class, conn);
			if(list != null && list.size() > 0){
				CHLotVO chLVO = (CHLotVO) list.get(0);
				value = chLVO.getAttribute("name") == null ? "" : chLVO.getAttribute("name").toString(); //得到字典表name的值
			}
			return value;
		}catch (Exception e){
			logger.error(e);
			throw e;
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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct h.FLOOR_FROM as floor from common.ch_flat h ");
			sb.append(" where h.buildingid=? and h.FLOOR_FROM is not null ");
			sb.append(" order by h.FLOOR_FROM desc ");
			params.add(buildingID);
			List list = DataBaseUtil.select(sb.toString(), params, HouseVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("select * from xg_trans_t  where xgid=?");
			params.add(xgid);
			List list = DataBaseUtil.select(sb.toString(), params, XgLimitSaleContractVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}


	public List searchLocations(String districtID, String roadName,String laneName, String subLaneName, String streetNumber) throws Exception {
		List list = new ArrayList();
		try{
			String location = "";
			if (roadName != null && !roadName.equals("")) {
				location += roadName;
			}

			if (laneName != null && !laneName.equals("")) {
				location += "%" + laneName;
			}

			if (subLaneName != null && !subLaneName.equals("")) {
				location += "%" + subLaneName;
			}

			if (streetNumber != null && !streetNumber.equals("")) {
				location += "%" + streetNumber;
			}

			if ("".equals(districtID) && "".equals(location)) {
				return list;
			}
			String tempLocation = location.replaceAll("%","" );
			if("".equals(tempLocation.trim())){
				return list;
			}
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("select locationID ,locationName from common.CH_LOCATION where  LOCATIONNAME like '%" + location + "%'");
			if (!"".equals(districtID)) {
				sb.append(" and districtid=?");
				params.add(String.valueOf(districtID));
			}
			list = DataBaseUtil.select(sb.toString(), params, EmptyVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：根据locationID 获取房屋信息
	 * @param houseIDs
	 * @param districtID
	 * @return
	 * @throws Exception
	 */
	public List<EmptyVO> findHousesByLocationID(long locationID,int districtID) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select A.buildingID");
		sb.append(" from COMMON.CH_FlAT A,COMMON.CL_FLAT_LOCATION B ");
		sb.append(" where A.HOUSEID=B.HOUSEID and A.DISTRICTID=B.DISTRICTID ");
		sb.append(" And B.LOCATIONID=?");
		params.add(locationID);
		if (districtID > 0) {
			sb.append(" And A.DISTRICTID=?");
			params.add(districtID);
		}
		sb.append(" order by A.HOUSEID ");
		List buildingList = DataBaseUtil.select(sb.toString(), params, EmptyVO.class, conn);
		return buildingList;
	}

	/**
	 * 2019-09-29 added by gcf 新增检查108登记小类。 功能描述：检查该房屋现势登记中是否包含土地初始 登记。
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public boolean checkLandRealExist(long houseId) throws Exception {
		try{
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			sb.append(" select a.houseID from unirealestate.r_house_real_t a ,unirealestate.transaction_t b ");
			sb.append(" where a.transactionid = b.transactionid and b.typebid in (107,108,109,110,119,201,206,215,360,1114,1117,1303,1306,1103,1116,1302,1305,327,214,1108,103,202,212,213,302,303,339,1101,1102,1113,1115,1118,1301,1311,1312,205) ");
			sb.append(" and a.houseID = ? ");
			params.add(houseId);
			List list = DataBaseUtil.select(sb.toString(), params, EmptyVO.class, conn);
			if(list!=null && list.size()>0){
				return true;
			}
			return false;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	
	/**
	 * 功能描述：根据小类编号、状态查询收件规则
	 * @param typeBID
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<CfgFileStandardVO> searchFileRule(int typeBID, int status) throws Exception {
		try{
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			sb.append("select * from unirealestate.CFG_FILE_STANDARD_T where typebid=?");
			params.add(typeBID);
			if(status == 1){
				sb.append(" and trunc(validdate)<=trunc(sysdate) and trunc(nvl(invaliddate,sysdate))>=trunc(sysdate)");
			}
			List list = DataBaseUtil.select(sb.toString(), params, CfgFileStandardVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			throw e;
		}
	}


	public CHBuildingVO searchBuildingsByBuildingID(int districtID,long buildingID) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from COMMON.CH_BUILDING where districtid = ? and buildingID = ?");
		params.add(districtID);
		params.add(buildingID);
		List buildingList = DataBaseUtil.select(sb.toString(), params, CHBuildingVO.class, conn);
		if(buildingList != null && buildingList.size() > 0){
			return (CHBuildingVO) buildingList.get(0);
		}
		return null;
	}
	public EmptyVO searchTransactionBytransID(long transactionID,String tableName,Connection conn) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from unirealestate."+tableName+" where transactionID = ?");
		params.add(transactionID);
		List list = DataBaseUtil.select(sb.toString(), params, EmptyVO.class, conn);
		if(list != null && list.size() > 0){
			return (EmptyVO) list.get(0);
		}
		return null;
	}

	public List<EmptyVO> getRealRightByHouseID(String houseIDs, int status) throws Exception {
		try{
			List<EmptyVO> list = new ArrayList<EmptyVO>();
			String tableName = "";
			switch (status) {
				case 1:
					tableName = "r_house_real_t";
					break;
				case 0:
					tableName = "tempr_house_real_t";
					break;
				case 2:
					tableName = "hisr_house_real_t";
					break;
				case -1:
					tableName = "dtempr_house_real_t";
					break;
				default:
					break;
			}
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT DISTINCT transactionid from ");
			sb.append(tableName);
			String[] houseIDArray = houseIDs.split(",");
			if(houseIDArray.length > 0) {
				for(int i = 0; i < houseIDArray.length; i++) {
					if(i == 0) {
						sb.append(" WHERE houseid = " + houseIDArray[i]);
					} else {
						sb.append(" or houseid = " + houseIDArray[i]);
					}
				}
			}else{
				return null;
			}
			
			ResultSet rs = DataBaseUtil.executeQuery(sb.toString(), null, conn, null);
			EmptyVO transVO = null;
			EmptyVO arcTransVO = null;
			while(rs.next()){
				transVO = searchTransactionBytransID(rs.getLong("transactionid"), "transaction_t", conn);
				if(transVO == null){
					arcTransVO = searchTransactionBytransID(rs.getLong("transactionid"), "arc_transaction_t", conn);
					list.add(arcTransVO);
				}else{
					list.add(transVO);
				}
			}
			rs.close();
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			throw e;
		}
	}


	public EmptyVO getRightByTransactionID(String transactionID,int districtID, int status) throws Exception {
		String tableName = "";
		switch (status) {
			case 1:
				tableName = "right_real_t";
				break;
			case 0:
				tableName = "tempright_real_t";
				break;
			case 2:
				tableName = "hisright_real_t";
				break;
			case -1:
				tableName = "dtempright_real_t";
				break;
			default:
				break;
		}
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from unirealestate."+tableName+" where districtid = ? and transactionID = ?");
		params.add(districtID);
		params.add(transactionID);
		List list = DataBaseUtil.select(sb.toString(), params, EmptyVO.class, conn);
		if(list != null && list.size() > 0){
			return (EmptyVO) list.get(0);
		}
		return null;
	}


	public List<CurrentCerLandSubUsesVO> getCurrentCerLandUses(long lotID, int districtID, long rightID, int rightType,
			long usersNO) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("select b.*,a.lotID from CURRENTCER_LAND_T a ,CURRENTCER_LAND_SUBUSES_T b  ");
		sb.append(" where a.certLandID=b.certLandID and a.districtID=b.districtID ");
		sb.append(" and a.lotID=?");
		params.add(lotID);
		sb.append(" and a.districtID=?");
		params.add(districtID);
		sb.append(" and a.rightID=?");
		params.add(rightID);
		sb.append(" and a.rightType=?");
		params.add(rightType);
		//		sb.append(" and b.usersNo=?");
		//		params.add(usersNO);
		List list = DataBaseUtil.select(sb.toString(), params, CurrentCerLandSubUsesVO.class, conn);
		return list;
	}


	public List<CurrentCerLandSubUsesVO> getTempCerLandUses(long lotID, int districtID, long rightID, int rightType,
			long usersNO) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("select b.*,a.lotID from TempCER_LAND_T a ,TempCER_LAND_SUBUSES_T b  ");
		sb.append(" where a.certLandID=b.certLandID and a.districtID=b.districtID ");
		sb.append(" and a.lotID=?");
		params.add(lotID);
		sb.append(" and a.districtID=?");
		params.add(districtID);
		sb.append(" and a.rightID=?");
		params.add(rightID);
		sb.append(" and a.rightType=?");
		params.add(rightType);
		//		sb.append(" and b.usersNo=?");
		//		params.add(usersNO);
		List list = DataBaseUtil.select(sb.toString(), params, CurrentCerLandSubUsesVO.class, conn);
		return list;
	}

	/**
	 * 功能描述：查询登记信息
	 * @param transactionID
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<EmptyVO> queryTransactionByTransactionID(long transactionID,int status) throws Exception {
		try{
			String tableName = "";
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			if(status == 1){
				tableName = "unirealestate.transaction_t";
			}else {
				tableName = "unirealestate.arc_transaction_t";
			}
			sb.append("select * from "+tableName+" where transactionID=?");
			params.add(transactionID);
			List list = DataBaseUtil.select(sb.toString(), params, EmptyVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			throw e;
		}
	}


	public List<CHLotVO> searchLots(String lotID,String lotNumber,String districtID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.* ");
			sb.append(" from common.ch_lot a ");
			sb.append(" where 1=1 ");
			if(lotID != null && !"".equals(lotID)){
				sb.append(" and lotID = ? ");
				params.add(lotID.trim());
			}
			if(lotNumber != null && !"".equals(lotNumber)){
				sb.append(" and a.lot_Number = ? ");
				params.add(lotNumber.trim());
			}
			if(districtID != null && !"".equals(districtID)){
				sb.append(" and a.DISTRICTID = ?  ");
				params.add(districtID.trim());
			}

			List list = DataBaseUtil.select(sb.toString(), params, CHLotVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}


	public List<CHBuildingVO> searchBuildingsByLotID(long lotID, int districtID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.* ");
			sb.append(" from COMMON.CH_BUILDING A,COMMON.CH_NATUREBUILDING B,COMMON.CH_LOT C ");
			sb.append(" where A.NATUREID = B.NATUREID AND  B.LOTID=C.LOTID ");
			if (lotID > 0) {
				sb.append(" And C.lotID=?");
				params.add(lotID);
			}
			if (districtID > 0) {
				sb.append(" And C.DISTRICTID=?");
				params.add(districtID);
			}
			sb.append(" order by A.BUILDINGID ");

			List list = DataBaseUtil.select(sb.toString(), params, CHBuildingVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}


	public List<EmptyVO> searchLandUseByLotID(String lotID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select APPROVED_USE_CODE as code,APPROVED_USE as use FROM common.ch_lot_uses ");
			sb.append(" where lotID = ? and APPROVED_USE is not null ");
			params.add(lotID);
			List list = DataBaseUtil.select(sb.toString(), params, EmptyVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}


	public List<CHFlatVO> searchHousesByBuildingIDAndLanduse(String landuseID, String buildingID, String districtID) throws Exception {
		try {
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			sb.append("select a.* from common.CH_FLAT a,common.CH_LOT_USES b,(select code,name from common.CT_LAND_USE2 where code = ?) c");
			sb.append(" where A.LOTID = B.LOTID and A.USERSNO = B.USERSNO and (B.APPROVED_USE_CODE = c.code or B.APPROVED_USE = c.name)");
			sb.append(" and a.buildingID = ?");
			params.add(landuseID);
			params.add(buildingID);
			if(districtID != null && !"".equals(districtID)){
				sb.append(" and a.districtid=?");
				params.add(String.valueOf(districtID));
			}
			sb.append(" order by a.floor_From,a.room_Number");
			List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}


	public List<LocationVO> searchLocationsByHouseID(String houseID, String districtID) throws Exception {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select A.*,b.houseid");
		sb.append(" from COMMON.CH_LOCATION A,COMMON.CL_FLAT_LOCATION B ");
		sb.append(" where A.LOCATIONID=B.LOCATIONID and A.DISTRICTID=B.DISTRICTID ");
		if (houseID != "" && !"".equals(houseID)) {
			sb.append(" And b.houseID=?");
			params.add(houseID);
		}
			
		if (districtID != "" && !"".equals(districtID)) {
			sb.append(" And A.DISTRICTID=?");
			params.add(districtID);
		}
		sb.append(" order by A.LOCATIONID ");
		List buildingList = DataBaseUtil.select(sb.toString(), params, LocationVO.class, conn);
		return buildingList;
	}


	public long addMessage(DocMessageVO message) throws Exception {
		return DataBaseUtil.add(message, conn);
	}
	
	/**
	 * 功能描述：查询登记库字典表信息,查询大机数据
	 * @param prefix
	 * --前缀
	 * @param tableName
	 * 表名
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String querydictNameDAJI(String prefix,String tableName,String key) throws Exception {
		try{
			String value = "";
			if(tableName == null || key == null || "".equals(key)){
				return "";
			}
			
			String tabName = "";
			if(prefix != null && !"".equals(prefix)){
				tabName = prefix + "." + tableName;
				//prefix +"." + tableName + dbLink; 
			}else{
				tabName = tableName;
			}
			
			
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	select * from " + tabName);
			sb.append(" where status = 1 and code = ? ");
			params.add(key);
			List list = DataBaseUtil.select(sb.toString(), params, DictVO.class, conn);
			if(list != null && list.size() > 0){
				DictVO dictVO = (DictVO) list.get(0);
				value = dictVO.getName() == null ? "" : dictVO.getName(); //得到字典表name的值
			}
			System.out.println("$$$$$$$$$$$$$sql="+sb.toString());
			return value;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}
	}

}
