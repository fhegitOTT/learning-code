package com.netcom.nkestate.system.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.framework.dao.DataBaseUtil;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.vo.SmUserVO;


public class SystemDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(SystemDAO.class.getName());

	/**
	 * 功能描述：获取系统seq值
	 * @param typeName
	 * @return
	 * @throws Exception
	 */
	public String getSequence(String typeName) throws Exception {
		ResultSet rs = null;
		try{
			//获取数据系统时间 
			String sql = "select to_char(sysdate,'yyyymmdd') sdate from dual";
			String sysdate = "";
			rs = DataBaseUtil.executeQuery(sql, null, conn, null);
			if(rs.next()){
				sysdate = rs.getString("sdate");
			}
			if(rs != null){
				rs.close();
			}
			String topString = "";//头数据 ，yyyy、yyyymm、yyyymmdd
			long numValue = 0;
			BigDecimal numLen = null;
			String updType = null;
			String updDate = null;
			String sequence = null;
			List<Object> params = new ArrayList<Object>();
			String selectSql = "SELECT NUMVALUE,NUMLEN,UPDTYPE,UPDDATE FROM M_SEQUENCE WHERE NUMTPCD = ? FOR UPDATE";
			params.add(typeName);
			rs = DataBaseUtil.executeQuery(selectSql, params, conn, null);
			if(rs.next()){
				numValue = rs.getBigDecimal(1).longValue();
				numLen = rs.getBigDecimal(2);
				updType = rs.getString(3);
				updDate = rs.getBigDecimal(4).toString();
			}
			if(rs != null){
				rs.close();
			}

			//日期比较
			if(sysdate.compareTo(updDate) < 0){
				sysdate = updDate;
			}
			//seq值处理
			if("0".equals(updType)){ //get sequence not by date.
				numValue++;
			}else if("1".equals(updType)){ //get sequence by month.
				topString = sysdate.substring(0, 6);
				if(sysdate.substring(0, 6).equals(updDate.substring(0, 6))){
					numValue++;
				}else{
					numValue = 1;
				}
			}else if("2".equals(updType)){ //get sequence by day.
				topString = sysdate;
				if(sysdate.equals(updDate)){
					numValue++;
				}else{
					numValue = 1;
				}
			}else if("3".equals(updType)){ //get sequence by year.
				topString = sysdate.substring(0, 4);
				if(sysdate.substring(0, 4).equals(updDate.substring(0, 4))){
					numValue++;
				}else{
					numValue = 1;
				}
			}

			params = new ArrayList<Object>();
			String updateSql = "UPDATE M_SEQUENCE SET NUMVALUE=?, UPDDATE=? WHERE NUMTPCD = ?";
			params.add(numValue);
			params.add(sysdate);
			params.add(typeName);
			DataBaseUtil.execute(updateSql, params, conn);

			//长度处理
			if(String.valueOf(numValue).length() >= numLen.intValue()){
				sequence = String.valueOf(numValue).substring(0, numLen.intValue());
			}else{
				String zero = "";
				int count = numLen.intValue() - String.valueOf(numValue).length();
				while (zero.length() < count){
					zero = zero + "0";
				}
				sequence = zero + String.valueOf(numValue);
			}

			return topString + sequence;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(rs != null){
				rs.close();
			}
		}
	}

	/**
	 * 功能描述：获取系统seq值
	 * @param typeName
	 * @return
	 * @throws Exception
	 */
	public Date getDBSysDate() throws Exception {
		ResultSet rs = null;
		try{
			//获取数据系统时间 
			String sql = " select sysdate from dual";
			Date sysdate = null;
			rs = DataBaseUtil.executeQuery(sql, null, conn, null);
			if(rs.next()){
				Timestamp timestamp = rs.getTimestamp(1);
				return new Date(timestamp.getTime());
			}

			return sysdate;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(rs != null){
				rs.close();
			}
		}
	}

	/**
	 * 功能描述：查询用户信息
	 * @param userId
	 * @return SmUserVO
	 * @throws Exception
	 */
	public SmUserVO findUserInfo(long userId) throws Exception {

		try{
			String sql = "select * from platform.sm_user_t a where a.userId = " + userId;
			List list = DataBaseUtil.select(sql, null, SmUserVO.class, conn);

			if(list.size() > 0){
				return (SmUserVO) list.get(0);
			}
			return null;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}
	
	/**
	 * 功能描述：查询用户区县
	 * @param regionID
	 * @return String
	 * @throws Exception
	 */
	public String findUserRegionName(int regionID) throws Exception {
		ResultSet rs = null;
		try{
			String sql = "select name from platform.SM_DM_REGION_T  where valid = 1 and id = " + regionID;
			rs = DataBaseUtil.executeQuery(sql, null, conn, null);
			if(rs.next()){
				return rs.getString(1)!=null?rs.getString(1):"";
			}
			return "";
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
			if(rs != null){
				rs.close();
			}
		}
	}
}
