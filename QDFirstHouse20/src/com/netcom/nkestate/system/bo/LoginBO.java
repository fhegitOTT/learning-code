package com.netcom.nkestate.system.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.framework.dao.MetaField;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.dao.LoginDAO;
import com.netcom.nkestate.system.vo.BulletinVO;
import com.netcom.nkestate.system.vo.LogLoginTodayVO;
import com.netcom.nkestate.system.vo.LogLoginVO;
import com.netcom.nkestate.system.vo.LoginVO;
import com.netcom.nkestate.system.vo.NonLoginTimeVO;
import com.netcom.nkestate.system.vo.SmObjectVO;
import com.netcom.nkestate.system.vo.SmUserVO;


public class LoginBO extends MiniBO {

	static Logger logger = Logger.getLogger(LoginBO.class.getName());

	private LoginDAO loginDAO = new LoginDAO();

	/**
	 * 功能描述：根据登录名查询用户
	 * @param loginName
	 * @param password
	 * @return SmUserVO vo
	 * @throws Exception
	 */
	public SmUserVO findLoginUser(String loginName) throws Exception {
		try{
			openDAO(loginDAO);
			SmUserVO smUserVO = loginDAO.findLoginUser(loginName);
			return smUserVO;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(loginDAO);
		}
	}

	/**
	 * 功能描述：根据用户的userID查询用户
	 * @param userID
	 * @return
	 * @throws Exception
	 */
	public SmUserVO findLoginUserByID(String userID) throws Exception {
		try{
			openDAO(loginDAO);
			SmUserVO smUserVO = loginDAO.findLoginUserByID(userID);
			return smUserVO;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(loginDAO);
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
			openDAO(loginDAO);
			List<SmObjectVO> list = loginDAO.findUserObjects(userID);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(loginDAO);
		}
	}

	/**
	 * 功能描述：保存登陆日志
	 * @param user
	 * @param clientIP
	 * @param sessionID
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public SmUserVO insertLoginLog(SmUserVO user,String clientIP,String sessionID,int userType) throws Exception {
		try{
			openDAO(loginDAO);
			LoginVO logVO = new LoginVO();
			Date currentDate = DateUtil.getSysDate();
			logVO.setUserID(user.getUserId());
			logVO.setUserIP(clientIP);
			logVO.setSessionID(sessionID);
			logVO.setStartDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			logVO.setStartTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			logVO.setUserType(userType);
			logVO.setLoginType(user.getLoginType());
			long logID = loginDAO.add(logVO);
			user.setLoginLogID(logID);
			user.setLoginDate(currentDate);
			user.setClientIP(clientIP);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(loginDAO);
		}
		return user;
	}

	/**
	 * 功能描述：更新退出时间
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public int udpateLogoutTime(SmUserVO userVO) throws Exception {
		try{
			openDAO(loginDAO);

			List<MetaField> fields = new ArrayList<MetaField>();
			fields.add(new MetaField("EndDate", DateUtil.getSysDateYYYYMMDD()));
			fields.add(new MetaField("EndTime", DateUtil.getSysDateHHMMSS()));

			loginDAO.update(LoginVO.class, userVO.getLoginLogID(), fields);

		}catch (Exception e){
			logger.error(e);
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(loginDAO);
		}
		return 0;
	}

	/**
	 * 更新用户密码
	 * @param userId
	 * @param passWord
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserPassWord(long userId,String passWord) throws Exception {
		try{
			openDAO(loginDAO);


			return loginDAO.updateUserPassWord(userId, passWord);

		}catch (Exception e){
			logger.error(e);
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(loginDAO);
		}
	}

	/**
	 * 功能描述：根据登录名查询签约人
	 * @param loginName
	 * @return SignerVO
	 * @throws Exception
	 */
	public SignerVO findLoginSigner(String loginName) throws Exception {
		try{
			openDAO(loginDAO);
			SignerVO signerVO = loginDAO.findLoginSigner(loginName);
			return signerVO;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(loginDAO);
		}
	}

	/**
	 * 功能描述：非登陆时间查询
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<NonLoginTimeVO> findNonLoginTimeList(int type) throws Exception {
		try{
			openDAO(loginDAO);
			List<NonLoginTimeVO> list = loginDAO.findNonLoginTimeList(type);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(loginDAO);
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
			openDAO(loginDAO);
			List<SmObjectVO> list = loginDAO.findSignerObjects(signerId);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(loginDAO);
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
			openDAO(loginDAO);
			List<BulletinVO> list = loginDAO.findShowBulletinList(userDistrictId);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(loginDAO);
		}
	}

	/**
	 * 功能描述：保存登陆日志
	 * @param user
	 * @param clientIP
	 * @param sessionID
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public SmUserVO addLoginLog(SmUserVO user,int loginType,String clientIP,String sessionID,String serverName,int successFlag,String memo) throws Exception {
		try{
			openDAO(loginDAO);
			Date currentDate = DateUtil.getSysDate();

			//当日登录日志
			LogLoginTodayVO logVO = new LogLoginTodayVO();
			logVO.setUserID(user.getUserId());
			logVO.setLoginName(user.getLoginName());
			logVO.setLoginType(loginType);
			logVO.setUserType(user.getUserType());
			logVO.setLoginTime(currentDate);
			logVO.setClientIP(clientIP);
			logVO.setServerIP(serverName);
			logVO.setSessionID(sessionID);
			logVO.setSuccess(successFlag);
			logVO.setMemo(memo);

			long logID = loginDAO.add(logVO);

			//所有日志
			LogLoginVO logVO2 = new LogLoginVO();
			logVO2.setLogID(logID);
			logVO2.setUserID(user.getUserId());
			logVO2.setLoginName(user.getLoginName());
			logVO2.setUserType(user.getUserType());
			logVO2.setLoginType(loginType);
			logVO2.setLoginTime(currentDate);
			logVO2.setClientIP(clientIP);
			logVO2.setServerIP(serverName);
			logVO2.setSessionID(sessionID);
			logVO2.setSuccess(successFlag);
			logVO.setMemo(memo);

			loginDAO.add(logVO2);

			user.setLoginLogID(logID);
			user.setLoginDate(currentDate);
			user.setClientIP(clientIP);

		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(loginDAO);
		}
		return user;
	}

	/**
	 * 功能描述：更新退出时间
	 * @param userVO
	 * 登录用户对象
	 * @return
	 * @throws Exception
	 */
	public int udpateLogoutLogTime(SmUserVO userVO) throws Exception {
		try{
			openDAO(loginDAO);

			Date currentDate = DateUtil.getSysDate();

			List<MetaField> fields = new ArrayList<MetaField>();
			fields.add(new MetaField("logoutTime", currentDate));

			loginDAO.update(LogLoginVO.class, userVO.getLoginLogID(), fields);
			loginDAO.update(LogLoginTodayVO.class, userVO.getLoginLogID(), fields);
		}catch (Exception e){
			logger.error(e);
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(loginDAO);
		}
		return 1;
	}
}
