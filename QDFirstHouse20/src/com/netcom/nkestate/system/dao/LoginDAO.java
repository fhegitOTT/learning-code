package com.netcom.nkestate.system.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.framework.dao.DataBaseUtil;
import com.netcom.nkestate.system.vo.BulletinVO;
import com.netcom.nkestate.system.vo.NonLoginTimeVO;
import com.netcom.nkestate.system.vo.SmObjectVO;
import com.netcom.nkestate.system.vo.SmUserVO;


public class LoginDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(LoginDAO.class.getName());

	/**
	 * 功能描述：根据登录名查询用户
	 * @param loginName
	 * @return SmUserVO
	 * @throws Exception
	 */
	public SmUserVO findLoginUser(String loginName) throws Exception {
		if("".equals(loginName) || loginName == null){
			throw new Exception("用户名不能为空。");
		}
		try{
			String sql = "select * from platform.sm_user_t a where a.valid=1 and a.loginname = '" + loginName + "'";
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
	 * 功能描述：根据用户userID查询用户
	 * @param userID
	 * @return
	 * @throws Exception
	 */
	public SmUserVO findLoginUserByID(String userID) throws Exception {
		if("".equals(userID) || userID == null){
			throw new Exception("用户userID不能为空。");
		}
		try{
			String sql = "select * from platform.sm_user_t a where a.valid=1 and a.userID = '" + userID + "'";
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
	 * 功能描述：根据用户编号查询用户菜单列表
	 * @param userID
	 * @return List<SmObjectVO>
	 * @throws Exception
	 */
	public List<SmObjectVO> findUserObjects(long userID) throws Exception {
		try{

			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select distinct c.* ");
			sb.append(" from platform.sm_r_role_user_t a,platform.sm_op_authority_t b,platform.sm_object_t c ");
			sb.append(" where a.roleid = b.roleid and b.objectid = c.objectid ");
			sb.append(" and a.userid = ? and c.appid = ? ");
			sb.append(" order by c.treeid,c.objectid ");
			params.add(String.valueOf(userID));
			params.add(String.valueOf(FHConstant.APP_FH_INNER));

			List list = DataBaseUtil.select(sb.toString(), params, SmObjectVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 更新用户密码
	 * @param userId
	 * @param passWord
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserPassWord(long userId,String passWord) throws Exception {

		String sql = "update platform.sm_user_t a set a.password = '" + passWord + "' where a.userid = " + userId;
		DataBaseUtil.execute(sql, null, conn);
		return true;
	}

	/**
	 * 功能描述：根据登录名查询签约人
	 * @param loginName
	 * @return SignerVO
	 * @throws Exception
	 */
	public SignerVO findLoginSigner(String loginName) throws Exception {
		if("".equals(loginName) || loginName == null){
			throw new Exception("用户名不能为空。");
		}
		try{
			String sql = "select a.*,nvl(b.bizdistrict,0) districtid,nvl(b.comp_id,0) orgid,nvl(b.name,'') orgname ";
			sql += " from signer a,enterprisequalify b where a.comp_id = b.comp_id(+) and  a.loginname = ?";
			List<Object> params = new ArrayList<Object>();
			params.add(loginName);
			List list = DataBaseUtil.select(sql, params, SignerVO.class, conn);

			if(list.size() > 0){
				return (SignerVO) list.get(0);
			}
			return null;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：非登陆时间查询
	 * @param type
	 * @return List<NonLoginTimeVO>
	 * @throws Exception
	 */
	public List<NonLoginTimeVO> findNonLoginTimeList(int type) throws Exception {
		try{

			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select * from non_login_time where type = ? order by id ");
			params.add(String.valueOf(type));

			List list = DataBaseUtil.select(sb.toString(), params, NonLoginTimeVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：根据签约人编号查询签约人菜单列表
	 * @param signerId
	 * @return List<SmObjectVO>
	 * @throws Exception
	 */
	public List<SmObjectVO> findSignerObjects(long signerId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select b.* ");
			sb.append(" from signeractions a,platform.sm_object_t b ");
			sb.append(" where a.actionid = b.objectid  ");
			sb.append(" and a.signer_id = ? and b.appid = ? ");
			sb.append(" order by b.treeid ");
			params.add(String.valueOf(signerId));
			params.add(String.valueOf(FHConstant.APP_FH_OUTER));

			List list = DataBaseUtil.select(sb.toString(), params, SmObjectVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：根据人员所属区县ID查询公告列表
	 * @param userDistrictId
	 * @return List<BulletinVO>
	 * @throws Exception
	 */
	public List<BulletinVO> findShowBulletinList(int userDistrictId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select distinct a.* ");
			sb.append(" from bulletin a,ct_district_range b ");
			sb.append(" where a.districtid = b.targetdistrictid ");
			sb.append(" and a.status=" + FHConstant.BULLETIN_POPUP_ON + " and b.districtid = ? ");
			sb.append(" order by a.upddate desc,a.updtime desc ");
			params.add(String.valueOf(userDistrictId));

			List list = DataBaseUtil.select(sb.toString(), params, BulletinVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

}
