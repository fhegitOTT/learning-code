/**
 * <p>CompanyBO.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 开发企业BO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.company.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.dao.CompanyDAO;
import com.netcom.nkestate.fhhouse.company.vo.EnterpriseQualifyVO;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingHouseVO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingVO;
import com.netcom.nkestate.fhhouse.project.vo.PresellVO;
import com.netcom.nkestate.fhhouse.project.vo.ProPreBldSignerVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.vo.SignerActionsVO;
import com.netcom.nkestate.system.vo.SmObjectVO;
import com.netcom.nkestate.system.vo.SmUserVO;


public class CompanyBO extends MiniBO {

	static Logger logger = Logger.getLogger(CompanyBO.class.getName());

	private CompanyDAO companyDAO = new CompanyDAO();

	/**
	 * 功能描述：签约人菜单列表
	 * @param signerId
	 * @return
	 * @throws Exception
	 */
	public List<SmObjectVO> findSignerObjectList(long signerId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findSignerObjectList(signerId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：签约人项目选择列表
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	public List<ProjectVO> findSignerProjectList(long companyId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findSignerProjectList(companyId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：签约人许可证开盘单元选择列表
	 * @param companyId
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public List<StartUnitVO> findSignerStartUnitList(long companyId,String projectId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findSignerStartUnitList(companyId, projectId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：签约人许开盘单元下楼栋选择列表
	 * @param companyId
	 * @param projectId
	 * @param startId
	 * @return
	 * @throws Exception
	 */
	public List<BuildingHouseVO> findSignerStartBuildingList(long companyId,String projectId,String startId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findSignerStartBuildingList(companyId, projectId, startId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}


	/**
	 * 功能描述：签约人关联楼栋列表
	 * @param signerId
	 * @return
	 * @throws Exception
	 */
	public List<ProPreBldSignerVO> findSignerRelationBuildingList(long signerId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findSignerRelationBuildingList(signerId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：新增签约人
	 * @param signerVO
	 * @param actionList
	 * @param buildingList
	 * @return
	 * @throws Exception
	 */
	public long registSigner(SignerVO signerVO,List<SignerActionsVO> actionList,List<ProPreBldSignerVO> buildingList) throws Exception {
		long signerId = -1;
		try{
			openDAO(companyDAO);
			companyDAO.setTransaction();
			signerId = companyDAO.add(signerVO);
			//新增菜单关联
			for(SignerActionsVO actionvo : actionList){
				actionvo.setSignerID(signerId);
				companyDAO.add(actionvo);
			}
			//新增楼栋关联
			for(ProPreBldSignerVO relationvo : buildingList){
				relationvo.setSigner_ID(signerId);
				companyDAO.add(relationvo);
			}

			companyDAO.commit();
		}catch (Exception e){
			companyDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}

		return signerId;
	}

	/**
	 * 功能描述：更新签约人
	 * @param signerVO
	 * @param actionList
	 * @param buildingList
	 * @return
	 * @throws Exception
	 */
	public long updateSigner(SignerVO signerVO,List<SignerActionsVO> actionList,List<ProPreBldSignerVO> buildingList) throws Exception {
		long signerId = signerVO.getSigner_ID();
		try{
			openDAO(companyDAO);
			companyDAO.setTransaction();
			//companyDAO.update(signerVO);

			SignerVO tempSignerVO = new SignerVO();
			tempSignerVO.setSigner_ID(signerVO.getSigner_ID());
			tempSignerVO.setName(signerVO.getName());
			tempSignerVO.setCardName(signerVO.getCardName());
			tempSignerVO.setCardCode(signerVO.getCardCode());
			tempSignerVO.setBrokercert(signerVO.getBrokercert());
			tempSignerVO.setAgent_ID(signerVO.getAgent_ID());
			tempSignerVO.setStatus(signerVO.getStatus());
			tempSignerVO.setUpdDate(signerVO.getUpdDate());
			tempSignerVO.setUpdTime(signerVO.getUpdTime());
			tempSignerVO.setUpdPerson(signerVO.getUpdPerson());
			tempSignerVO.setCanSeal(signerVO.getCanSeal());
			tempSignerVO.setKeyID(signerVO.getKeyID());
			companyDAO.update2(tempSignerVO);
			//删除菜单
			SignerActionsVO delactionvo = new SignerActionsVO();
			delactionvo.setSignerID(signerVO.getSigner_ID());
			companyDAO.deleteByTemplate(delactionvo);

			ProPreBldSignerVO delrelationvo = new ProPreBldSignerVO();
			delrelationvo.setSigner_ID(signerId);
			companyDAO.deleteByTemplate(delrelationvo);

			//新增菜单关联
			for(SignerActionsVO actionvo : actionList){
				actionvo.setSignerID(signerId);
				companyDAO.add(actionvo);
			}
			//新增楼栋关联
			for(ProPreBldSignerVO relationvo : buildingList){
				relationvo.setSigner_ID(signerId);
				companyDAO.add(relationvo);
			}

			companyDAO.commit();
		}catch (Exception e){
			companyDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}

		return signerId;
	}

	/**
	 * 功能描述：资质提交审核
	 * @param type
	 * @param eqvo
	 * @param signerList
	 * @param projectList
	 * @param startUnitList
	 * @return
	 * @throws Exception
	 */
	public boolean firstSubmitCompany(SmUserVO uservo,EnterpriseQualifyVO eqvo,List<SignerVO> signerList,List<ProjectVO> projectList,List<StartUnitVO> startUnitList) throws Exception {
		try{
			openDAO(companyDAO);
			companyDAO.setTransaction();
			if(eqvo != null){
				EnterpriseQualifyVO epqVo = new EnterpriseQualifyVO();
				epqVo.setComp_ID(eqvo.getComp_ID());
				epqVo.setStatus(FHConstant.COMP_STATUS_SUBMIT);
				epqVo.setFirstAuditDate(DateUtil.getSysDateYYYYMMDD());
				epqVo.setFirstCensor(String.valueOf(uservo.getUserId()));
				epqVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				epqVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				epqVo.setUpdPerson(String.valueOf(uservo.getUserId()));
				companyDAO.update2(epqVo);
			}
			if(signerList != null && signerList.size() > 0){
				for(SignerVO svo : signerList){
					SignerVO signerVO = new SignerVO();
					signerVO.setSigner_ID(svo.getSigner_ID());
					signerVO.setStatus(FHConstant.SIGNER_STATUS_SUBMIT);
					signerVO.setFirstAuditDate(DateUtil.getSysDateYYYYMMDD());
					signerVO.setFirstCensor(String.valueOf(uservo.getUserId()));
					signerVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					signerVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					signerVO.setUpdPerson(String.valueOf(uservo.getUserId()));
					companyDAO.update2(signerVO);
				}
			}

			if(projectList != null && projectList.size() > 0){
				for(ProjectVO pvo : projectList){
					ProjectVO projectVO = new ProjectVO();
					projectVO.setProject_ID(pvo.getProject_ID());
					projectVO.setStatus(FHConstant.PROJECT_STATUS_SUBMIT);
					projectVO.setFirstAuditDate(DateUtil.getSysDateYYYYMMDD());
					projectVO.setFirstCensor(String.valueOf(uservo.getUserId()));
					projectVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					projectVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					projectVO.setUpdPerson(String.valueOf(uservo.getUserId()));
					companyDAO.update2(projectVO);
				}
			}
			if(startUnitList != null && startUnitList.size() > 0){
				for(StartUnitVO suvo : startUnitList){
					StartUnitVO startUnitVO = new StartUnitVO();
					startUnitVO.setStart_ID(suvo.getStart_ID());
					startUnitVO.setStatus(FHConstant.START_UNIT_STATUS_SUBMIT);
					startUnitVO.setFirstAuditDate(DateUtil.getSysDateYYYYMMDD());
					startUnitVO.setFirstCensor(String.valueOf(uservo.getUserId()));
					startUnitVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					startUnitVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					startUnitVO.setUpdPerson(String.valueOf(uservo.getUserId()));
					companyDAO.update2(startUnitVO);
				}
			}
			companyDAO.commit();
			return true;
		}catch (Exception e){
			companyDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}


	/**
	 * 功能描述：撤销和恢复资质
	 * @param type
	 * @param eqvo
	 * @param signerList
	 * @param projectList
	 * @param startUnitList
	 * @return
	 * @throws Exception
	 */
	public boolean cancelCompany(String status,EnterpriseQualifyVO eqvo,List<SignerVO> signerList,List<ProjectVO> projectList,List<StartUnitVO> startUnitList) throws Exception {
		try{
			openDAO(companyDAO);
			companyDAO.setTransaction();
			if(eqvo != null){
				EnterpriseQualifyVO epqVo = new EnterpriseQualifyVO();
				epqVo.setComp_ID(eqvo.getComp_ID());
				if("5".equals(status)){
					epqVo.setStatus(FHConstant.COMP_STATUS_PERMITTED);
				}
				if("2".equals(status)){
					epqVo.setStatus(FHConstant.COMP_STATUS_CANCEL);
				}
				epqVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				epqVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				epqVo.setUpdPerson(eqvo.getUpdPerson());
				companyDAO.update2(epqVo);
			}
			if(signerList != null && signerList.size() > 0){
				for(SignerVO svo : signerList){
					SignerVO signerVO = new SignerVO();
					signerVO.setSigner_ID(svo.getSigner_ID());
					if("2".equals(status)){
						signerVO.setStatus(FHConstant.SIGNER_STATUS_CANCEL);
					}
					if("5".equals(status)){
						signerVO.setStatus(FHConstant.SIGNER_STATUS_PERMITTED);
					}
					signerVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					signerVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					signerVO.setUpdPerson(eqvo.getUpdPerson());
					companyDAO.update2(signerVO);
				}
			}

			if(projectList != null && projectList.size() > 0){
				for(ProjectVO pvo : projectList){
					ProjectVO projectVO = new ProjectVO();
					projectVO.setProject_ID(pvo.getProject_ID());
					if("2".equals(status)){
						projectVO.setStatus(FHConstant.PROJECT_STATUS_CANCEL);
					}
					if("5".equals(status)){
						projectVO.setStatus(FHConstant.PROJECT_STATUS_PERMITTED);
					}
					projectVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					projectVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					projectVO.setUpdPerson(eqvo.getUpdPerson());
					companyDAO.update2(projectVO);
				}
			}
			if(startUnitList != null && startUnitList.size() > 0){
				for(StartUnitVO suvo : startUnitList){
					StartUnitVO startUnitVO = new StartUnitVO();
					startUnitVO.setStart_ID(suvo.getStart_ID());
					if("2".equals(status)){
						startUnitVO.setStatus(FHConstant.START_UNIT_STATUS_CANCEL);
					}
					if("5".equals(status)){
						startUnitVO.setStatus(FHConstant.START_UNIT_STATUS_PERMITTED);
					}
					startUnitVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					startUnitVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					startUnitVO.setUpdPerson(eqvo.getUpdPerson());
					companyDAO.update2(startUnitVO);
				}
			}
			companyDAO.commit();
			return true;
		}catch (Exception e){
			companyDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}


	/**
	 * 功能描述：查询项目下许可证列表
	 * @param projectId
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<ProjectVO> findProjectPresellList(long compId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findProjectPresellList(compId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：查询开盘单元下的所有楼栋列表
	 * @param compId
	 * @return
	 * @throws Exception
	 */
	public List<StartUnitVO> findStartUnitHouseList(long compId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findStartUnitHouseList(compId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：资质修改列表
	 * @param compCode
	 * @param name
	 * @param documentId
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List<EnterpriseQualifyVO> findCompanyList(Page page,String compCode,String name,String documentId,String districtList) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findCompanyList(page, compCode, name, documentId, districtList);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：资质审核列表
	 * @param status
	 * @param page
	 * @param companyName
	 * @param projectName
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List<EnterpriseQualifyVO> findCompanyAuditInfo(Page page,String companyName,String projectName,String districtList) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findCompanyAuditInfo(page, companyName, projectName, districtList);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：信息发布列表
	 * @param status
	 * @param page
	 * @param companyName
	 * @param projectName
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List<EnterpriseQualifyVO> findCompanyPublishInfo(Page page,String companyName,String projectName,String districtList) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findCompanyPublishInfo(page, companyName, projectName, districtList);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}


	/**
	 * 功能描述：企业审核--企业信息查询
	 * @param compId
	 * @return
	 * @throws Exception
	 */
	public List<EnterpriseQualifyVO> findCompanyInfo(String compId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findCompanyInfo(compId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：企业审核--项目信息查询
	 * @param compId
	 * @return
	 * @throws Exception
	 */
	public List<ProjectVO> findProjectInfo(String status,String compId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findProjectInfo(status, compId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：企业审核--开盘单元信息查询
	 * @param compId
	 * @return
	 * @throws Exception
	 */
	public List<StartUnitVO> findStartUnitInfo(String compId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findStartUnitInfo(compId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：开盘单元审核--开盘单元信息查询
	 * @param startId
	 * @return
	 * @throws Exception
	 */
	public List<StartUnitVO> findStartUnit1(String startId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findStartUnit1(startId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：开盘单元审核--楼栋信息查询
	 * @param startId
	 * @return
	 * @throws Exception
	 */
	public List<BuildingHouseVO> findStartUnit2(String startId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findStartUnit2(startId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：开盘单元审核--签约人信息查询
	 * @param startId
	 * @return
	 * @throws Exception
	 */
	public List<SignerVO> findStartUnit3(String startId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findStartUnit3(startId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：开盘单元审核--企业信息查询
	 * @param startId
	 * @return
	 * @throws Exception
	 */
	public List<EnterpriseQualifyVO> findStartUnit4(String startId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findStartUnit4(startId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：企业审核-更新终审状态
	 * @param page
	 * @param compId
	 * @return
	 * @throws Exception
	 */
	public boolean updateCompanyAUditStatus(String type,String userId,String finalMark,EnterpriseQualifyVO eqvo,List<SignerVO> signerList,List<ProjectVO> projectList,List<StartUnitVO> startUnitList) throws Exception {
		try{
			openDAO(companyDAO);
			companyDAO.setTransaction();
			if(eqvo != null && !"".equals(eqvo)){
				EnterpriseQualifyVO epqVO = new EnterpriseQualifyVO();
				epqVO.setComp_ID(eqvo.getComp_ID());
				epqVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				epqVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				epqVO.setUpdPerson(String.valueOf(userId));
				epqVO.setFinalMark(finalMark);
				epqVO.setFinalAuditDate(DateUtil.getSysDateYYYYMMDD());
				epqVO.setFinalCensor(userId);
				if("1".equals(type)){
//					epqVO.setStatus(FHConstant.COMP_STATUS_PASSED);
					//20190829 added by gcf 审核通过后直接变发布状态
					epqVO.setStatus(FHConstant.COMP_STATUS_PERMITTED);
				}else{
					epqVO.setStatus(FHConstant.COMP_STATUS_NOPASSED);
				}
				companyDAO.update2(epqVO);
			}
			if(signerList != null && signerList.size() > 0){
				for(SignerVO svo : signerList){
					SignerVO signerVO = new SignerVO();
					signerVO.setSigner_ID(svo.getSigner_ID());
					signerVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					signerVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					signerVO.setUpdPerson(userId);
					signerVO.setFinalMark(finalMark);
					signerVO.setFinalAuditDate(DateUtil.getSysDateYYYYMMDD());
					signerVO.setFinalCensor(userId);
					if("1".equals(type)){
//						signerVO.setStatus(FHConstant.SIGNER_STATUS_PASSED);
						//20190829 added by gcf 审核通过后直接变发布状态
						signerVO.setStatus(FHConstant.SIGNER_STATUS_PERMITTED);
					}else{
						signerVO.setStatus(FHConstant.SIGNER_STATUS_NOPASSED);
					}
					companyDAO.update2(signerVO);
				}
			}

			if(projectList != null && projectList.size() > 0){
				for(ProjectVO pvo : projectList){
					ProjectVO projectVO = new ProjectVO();
					projectVO.setProject_ID(pvo.getProject_ID());
					projectVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					projectVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					projectVO.setUpdPerson(userId);
					projectVO.setFinalMark(finalMark);
					projectVO.setFinalCensor(userId);
					projectVO.setFinalAuditDate(DateUtil.getSysDateYYYYMMDD());
					if("1".equals(type)){
//						projectVO.setStatus(FHConstant.PROJECT_STATUS_PASSED);
						//20190829 added by gcf 审核通过后直接变发布状态
						projectVO.setStatus(FHConstant.PROJECT_STATUS_PERMITTED);
					}else{
						projectVO.setStatus(FHConstant.PROJECT_STATUS_NOPASSED);
					}
					companyDAO.update2(projectVO);
				}
			}
			if(startUnitList != null && startUnitList.size() > 0){
				for(StartUnitVO suvo : startUnitList){
					StartUnitVO startUnitVO = new StartUnitVO();
					startUnitVO.setStart_ID(suvo.getStart_ID());
					startUnitVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					startUnitVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					startUnitVO.setUpdPerson(userId);
					startUnitVO.setFinalMark(finalMark);
					startUnitVO.setFinalAuditDate(DateUtil.getSysDateYYYYMMDD());
					startUnitVO.setFinalCensor(userId);
					if("1".equals(type)){
//						startUnitVO.setStatus(FHConstant.START_UNIT_STATUS_PASSED);
						//20190829 added by gcf 审核通过后直接变发布状态
						startUnitVO.setStatus(FHConstant.START_UNIT_STATUS_PERMITTED);
					}else{
						startUnitVO.setStatus(FHConstant.START_UNIT_STATUS_NOPASSED);
					}
					companyDAO.update2(startUnitVO);
				}
			}
			companyDAO.commit();
			return true;
		}catch (Exception e){
			companyDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：签约人审核--签约人信息查询
	 * @param signerId
	 * @return
	 * @throws Exception
	 */
	public List<SignerVO> findSigner1(String signerId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findSigner1(signerId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：签约人审核--项目信息查询
	 * @param signerId
	 * @return
	 * @throws Exception
	 */
	public List<ProjectVO> findSigner2(String signerId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findSigner2(signerId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：签约人审核--开盘单元信息查询
	 * @param signerId
	 * @return
	 * @throws Exception
	 */
	public List<StartUnitVO> findSigner3(String signerId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findSigner3(signerId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：项目审核--项目信息查询
	 * @param projectid
	 * @return
	 * @throws Exception
	 */
	public List<ProjectVO> findProjectForProject(String projectid) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findProjectForProject(projectid);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：项目审核--签约人信息查询
	 * @param projectid
	 * @return
	 * @throws Exception
	 */
	public List<SignerVO> findSignerForProject(String status,String projectid) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findSignerForProject(status, projectid);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：项目审核--许可证信息查询
	 * @param projectid
	 * @return
	 * @throws Exception
	 */
	public List<PresellVO> findPresellForProject(String status,String projectid) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findPresellForProject(status, projectid);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：项目审核--楼栋信息查询
	 * @param projectid
	 * @return
	 * @throws Exception
	 */
	public List<BuildingVO> findBuildingForProject(String status,String projectid) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findBuildingForProject(status, projectid);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：项目审核--楼栋信息查询
	 * @param projectid
	 * @return
	 * @throws Exception
	 */
	public List<EnterpriseQualifyVO> findCompanyForProject(String projectid) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findCompanyForProject(projectid);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：企业审核-更新发布状态
	 * @param page
	 * @param compId
	 * @throws Exception
	 */
	public void updateCompanyPublishStatus(String option,String userId,EnterpriseQualifyVO eqvo,List<SignerVO> signerList,List<ProjectVO> projectList,List<StartUnitVO> startUnitList) throws Exception {
		try{
			openDAO(companyDAO);
			companyDAO.setTransaction();
			if(eqvo != null && !"".equals(eqvo)){
				EnterpriseQualifyVO epqVo = new EnterpriseQualifyVO();
				epqVo.setComp_ID(eqvo.getComp_ID());
				epqVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				epqVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				epqVo.setUpdPerson(userId);
				epqVo.setPublishDate(DateUtil.parseDate(DateUtil.parseDateTime3(DateUtil.getSysDateYYYYMMDD())));
				epqVo.setPublishCensor(userId);
				if("1".equals(option)){
					epqVo.setStatus(FHConstant.COMP_STATUS_PERMITTED);
					epqVo.setPublishMark("同意");
				}else{
					epqVo.setStatus(FHConstant.COMP_STATUS_NOPERMITTED);
					epqVo.setPublishMark("不同意");
				}
				companyDAO.update2(epqVo);
			}
			if(signerList != null && signerList.size() > 0){
				for(SignerVO svo : signerList){
					SignerVO signerVO = new SignerVO();
					signerVO.setSigner_ID(svo.getSigner_ID());
					signerVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					signerVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					signerVO.setUpdPerson(userId);
					signerVO.setPublishDate(DateUtil.parseDate(DateUtil.parseDateTime3(DateUtil.getSysDateYYYYMMDD())));
					signerVO.setPublishCensor(userId);
					if("1".equals(option)){
						signerVO.setStatus(FHConstant.SIGNER_STATUS_PERMITTED);
						signerVO.setPublishMark("同意");
					}else{
						signerVO.setStatus(FHConstant.SIGNER_STATUS_NOPERMITTED);
						signerVO.setPublishMark("不同意");
					}
					companyDAO.update2(signerVO);
				}
			}

			if(projectList != null && projectList.size() > 0){
				for(ProjectVO pvo : projectList){
					ProjectVO projectVO = new ProjectVO();
					projectVO.setProject_ID(pvo.getProject_ID());
					projectVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					projectVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					projectVO.setUpdPerson(userId);
					projectVO.setPublishDate(DateUtil.parseDate(DateUtil.parseDateTime3(DateUtil.getSysDateYYYYMMDD())));
					projectVO.setPublishCensor(userId);
					if("1".equals(option)){
						projectVO.setStatus(FHConstant.PROJECT_STATUS_PERMITTED);
						projectVO.setPublishMark("同意");
					}else{
						projectVO.setStatus(FHConstant.PROJECT_STATUS_NOPERMITTED);
						projectVO.setPublishMark("不同意");
					}
					companyDAO.update2(projectVO);
				}
			}
			if(startUnitList != null && startUnitList.size() > 0){
				for(StartUnitVO suvo : startUnitList){
					StartUnitVO startUnitVO = new StartUnitVO();
					startUnitVO.setStart_ID(suvo.getStart_ID());
					startUnitVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					startUnitVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					startUnitVO.setUpdPerson(userId);
					startUnitVO.setPublishDate(DateUtil.parseDate(DateUtil.parseDateTime3(DateUtil.getSysDateYYYYMMDD())));
					startUnitVO.setPublishCensor(userId);
					if("1".equals(option)){
						startUnitVO.setStatus(FHConstant.START_UNIT_STATUS_PERMITTED);
						startUnitVO.setPublishMark("同意");
					}else{
						startUnitVO.setStatus(FHConstant.START_UNIT_STATUS_NOPERMITTED);
						startUnitVO.setPublishMark("不同意");
					}
					companyDAO.update2(startUnitVO);
				}
			}
			companyDAO.commit();
		}catch (Exception e){
			companyDAO.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

	/**
	 * 功能描述：查询企业下关联的开盘单元
	 * @param compId
	 * @return
	 * @throws Exception
	 */
	public List<StartUnitVO> findStartUnit(long compId) throws Exception {
		try{
			openDAO(companyDAO);
			return companyDAO.findStartUnit(compId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(companyDAO);
		}
	}

}
