/**
 * <p>PermitDAO.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 许可证DAO <p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.permit.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO;
import com.netcom.nkestate.fhhouse.permit.vo.ApplyVO;
import com.netcom.nkestate.fhhouse.permit.vo.PermitVO;
import com.netcom.nkestate.fhhouse.permit.vo.RHousePermitVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;
import com.netcom.nkestate.framework.dao.MetaField;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.vo.DictVO;
import com.netcom.nkestate.system.vo.SmObjectVO;


public class PermitDAO extends MiniDAO {
	static Logger logger = Logger.getLogger(PermitDAO.class.getName());
	/**
	 * 功能描述：签约人菜单列表
	 * @param signerId
	 * @return
	 * @throws Exception
	 */
	public List<SmObjectVO> findSignerObjectList(long signerId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.*, b.signer_id ");
			sb.append(" from platform.sm_object_t a, ( ");
			sb.append(" 	select actionid,signer_id  ");
			sb.append(" 	from signeractions where signer_id = ? ");
			sb.append(" ) b ");
			sb.append(" where a.objectid = b.actionid(+) ");
			sb.append(" and a.appid = ? ");
			sb.append(" order by a.treeid ");
			params.add(signerId);
			List list = DataBaseUtil.select(sb.toString(), params, SmObjectVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}
	
	
	/**
	 * 功能描述：获取序列号 
	 * @param seqName
	 * @return
	 * @throws Exception 
	 */
	public long getSeqIDByName(String seqName) throws Exception {
		long seqNo = 0L;
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("select  " + seqName + ".nextval seqno from dual");
			List list = DataBaseUtil.select(sb.toString(), params, ApplyVO.class, conn);
			if(list != null && list.size() > 0){
				ApplyVO vo = (ApplyVO)list.get(0);
				seqNo = Long.parseLong(String.valueOf(vo.getAttribute("seqno")));
			}
			return seqNo;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}


	public long deleteRelateHouse(long permitID, String houseID) {
		// TODO Auto-generated method stub
		return 0;
	}


	public List<PermitVO> searchPermitList(String companyName, String permitNo,long userId, String status,Page page,String userDistrictList) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select b.* from apply_t a,permit_t b where a.transactionid = b.transactionid");
			if("1".equals(status)) {
				sb.append(" and a.acceptuser = ? ");
				params.add(userId);
			}
			if(userDistrictList != null && !"".equals(userDistrictList)){
				sb.append(" and a.districtID in "+userDistrictList);
			}
			if(status != null && !"".equals(status)){
				sb.append(" and a.state in ("+status+") ");
			}
			if(companyName != null && !"".equals(companyName)){
				sb.append(" and b.companyname like ? ");
				params.add("%" + companyName + "%");
			}
			if(permitNo != null && !"".equals(permitNo)){
				sb.append(" and b.permitNO like ? ");
				params.add("%" + permitNo + "%");
			}
			List list = DataBaseUtil.select(sb.toString(), params, PermitVO.class,page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}


	public int updatePermitVOByPermitID(String permitID, List<MetaField> fields) throws Exception {
		try{
			return DataBaseUtil.update(PermitVO.class, Long.parseLong(permitID), fields, conn);
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
		}
	}


	public int updateApplyVOByTransactionID(String transactionID, List<MetaField> fields) throws Exception {
		try{
			return DataBaseUtil.update(ApplyVO.class, Long.parseLong(transactionID), fields, conn);
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
		}
	}


	public List searchOnlineHouseList(String houseIDs, String districtID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" SELECT a.houseID,a.districtID,a.room_Number,nvl(b.saleFlag,0) saleFlag, nvl(b.permitID,-1) permitID,nvl(b.transactionID,-1) transactionID");
			sb.append(" FROM ch_flat a, r_house_permit_t b");
			sb.append(" WHERE a.HOUSEID = b.HOUSEID(+) AND a.DISTRICTID = b.DISTRICTID(+)");
			sb.append(" and a.HOUSEID in (").append(houseIDs).append(")");
			if(districtID != null && !"".equals(districtID)){
				sb.append(" and a.districtid = ?");
				params.add(districtID);
			}
			sb.append(" order by a.ROOM_NUMBER asc");
			List list = DataBaseUtil.select(sb.toString(), params, RHousePermitVO.class, conn);
			return list;
			
		}catch (Exception e){
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 功能描述：根据buildingID查询房屋列表
	 * @param buildingID
	 * @param districtID
	 * @return
	 * @throws Exception
	 */
	public List findHousesByBuildingIDAndPermitVO(String buildingID, String districtID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" SELECT a.* ,nvl(b.transactionID,0) transactionID ");
			sb.append(" FROM ch_flat a, r_house_permit_t b");
			sb.append(" WHERE a.HOUSEID = b.HOUSEID(+) AND a.DISTRICTID = b.DISTRICTID(+)");
			sb.append(" and a.buildingID = ? ");
			params.add(buildingID);
			if(districtID != null && !"".equals(districtID)){
				sb.append(" and a.districtid = ?");
				params.add(districtID);
			}
			sb.append(" order by a.floor_From,a.ROOM_NUMBER asc");
			List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			return list;
			
		}catch (Exception e){
			logger.error(e.getMessage());
			throw e;
		}
	}

	public int deleteRHousePermitVO(String permitID,long transactionID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" update r_house_permit_t set houseid = houseid * (-1)  where permitid = ? and transactionID = ? ");
			params.add(permitID);
			params.add(transactionID);
			int count  = DataBaseUtil.execute(sb.toString(), params, conn);
			return count;
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
			sb.append("select a.*,nvl(d.transactionID,0) transactionID from CH_FLAT a,common.CH_LOT_USES@DL_DAJI b,(select code,name from CT_LAND_USE2 where code = ?) c,r_house_permit_t d");
			sb.append(" where A.LOTID = B.LOTID and A.USERSNO = B.USERSNO and (B.APPROVED_USE_CODE = c.code or B.APPROVED_USE = c.name)");
			sb.append(" and a.HOUSEID = d.HOUSEID(+) AND a.DISTRICTID = d.DISTRICTID(+)");
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
	
	/**
	 * 许可证打印,获取房屋关联
	 * @param transctionID
	 * @param districtID
	 * @return
	 * @throws Exception
	 */
	public List<CHFlatVO> getPermitUnitHouse(String transctionID,String districtID) throws Exception {
		try {
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT B.*, ");
			sb.append("       C.DOORPLATE AS BUILDINGNUMBER, ");
			sb.append("       B.FLOOR_NAME, ");
			sb.append("       B.ROOM_NUMBER, ");
			sb.append("       B.BUILDING_TYPE, ");
			sb.append("       D.NAME ");
			sb.append("  FROM R_HOUSE_PERMIT_T        A, ");
			sb.append("       CH_FLAT          B, ");
			sb.append("       CH_BUILDING      C, ");
			sb.append("       COMMON.CT_BUILDING_TYPE@dl_daji D ");
			sb.append(" WHERE A.TRANSACTIONID = ? ");
			sb.append("   AND A.DISTRICTID = ?  ");
			sb.append("   AND A.HOUSEID = B.HOUSEID ");
			sb.append("   AND A.DISTRICTID = B.DISTRICTID ");
			sb.append("   AND B.BUILDINGID = C.BUILDINGID ");
			sb.append("   AND B.DISTRICTID = C.DISTRICTID ");
			sb.append("   AND B.BUILDING_TYPE = D.CODE(+) ");
			sb.append(" ORDER BY C.DOORPLATE, B.FLOOR_fROM, B.ROOM_NUMBER ");
			params.add(transctionID);
			params.add(districtID);
			List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	/**
	 * 许可证打印,获取房屋关联
	 * @param transctionID
	 * @param districtID
	 * @return
	 * @throws Exception
	 */
	public List<CHFlatVO> getPermitUnitHouse2(String transctionID,String districtID) throws Exception {
		try {
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT B.*, ");
			sb.append("       C.DOORPLATE AS BUILDINGNUMBER, ");
			sb.append("       B.FLOOR_NAME, ");
			sb.append("       B.ROOM_NUMBER, ");
			sb.append("       B.BUILDING_TYPE, ");
			sb.append("       D.NAME ");
			sb.append("  FROM R_HOUSE_PERMIT_T        A, ");
			sb.append("       CH_FLAT          B, ");
			sb.append("       CH_BUILDING      C, ");
			sb.append("       COMMON.CT_BUILDING_TYPE@dl_daji D ");
			sb.append(" WHERE A.TRANSACTIONID = ? ");
			sb.append("   AND A.DISTRICTID = ?  ");
			sb.append("   AND A.HOUSEID = B.HOUSEID ");
			sb.append("   AND A.DISTRICTID = B.DISTRICTID ");
			sb.append("   AND B.BUILDINGID = C.BUILDINGID ");
			sb.append("   AND B.DISTRICTID = C.DISTRICTID ");
			sb.append("   AND B.BUILDING_TYPE = D.CODE(+) ");
			sb.append(" ORDER BY C.DOORPLATE, B.FLOOR_fROM, B.ROOM_NUMBER ");
			params.add(transctionID);
			params.add(districtID);
			List list = DataBaseUtil.select(sb.toString(), params, CHFlatVO.class, conn);
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}


	public List<PermitVO> queryPermitStatis(String startDate, String endDate) throws Exception {
		try {
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			sb.append("select b.permitid, b.transactionid,b.location,b.projectname,B.COMPANYNAME,b.pset1,b.lbuildarea,b.hsets,b.inroomarea from apply_t a ,permit_t b ");
			sb.append("  where a.transactionid = b.transactionid and A.PASSRESULT = 1");
			if(!"".equals(startDate)){
				sb.append("  and to_char(a.passdate,'yyyy-MM-dd') >= ? ");
				params.add(startDate);
			}
			if(!"".equals(endDate)){
				sb.append("  and to_char(a.passdate,'yyyy-MM-dd') <= ? ");
				params.add(endDate);
			}
			List list = DataBaseUtil.select(sb.toString(), params, PermitVO.class, conn);
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}


	public List<RHousePermitVO> queryRPermitHouse(long permitID) throws Exception {
		try {
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT   a.*, decode(b.flarea,0,b.plan_flarea,b.flarea) area, b.land_use landuse ");
			sb.append(" FROM   r_house_permit_t a, ch_flat b");
			sb.append(" WHERE   a.houseid = b.houseid(+) AND a.permitid = ?");
			params.add(permitID);
			List list = DataBaseUtil.select(sb.toString(), params, RHousePermitVO.class, conn);
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 功能描述：查询登记库字典表信息,查询大机数据
	 * @param prefix --前缀
	 * @param tableName 表名
	 * @param dbLink 数据库连接
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String querydictNameDAJI(String prefix,String tableName,String dbLink,String key) throws Exception {
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
			
			if(dbLink != null && !"".equals(dbLink)){
				tabName  = tabName + "@" +dbLink;
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
			return value;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}
	}
}
