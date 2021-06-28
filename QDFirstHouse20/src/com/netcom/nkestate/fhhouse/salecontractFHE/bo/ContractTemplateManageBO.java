package com.netcom.nkestate.fhhouse.salecontractFHE.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.salecontract.dao.ContractTemplateManageDAO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach1TemplateVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.AttachTemplateVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractTemplateCsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractTemplateYsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellerTemplateVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SubTemplateVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.vo.DictVO;


public class ContractTemplateManageBO extends MiniBO {

	static Logger logger = Logger.getLogger(ContractTemplateManageBO.class.getName());
	private ContractTemplateManageDAO ctmDao = new ContractTemplateManageDAO();

	/**
	 * 功能描述：查询主模板列表
	 * @param projectID
	 * @param startCode
	 * @param district
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<ContractTemplateYsVO> queryTemplateList(String projectID,String startID,String district,Page page) throws Exception {
		try{
			openDAO(ctmDao);
			List<ContractTemplateYsVO> list = ctmDao.queryTemplateList(projectID, startID, district, page);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(ctmDao);
		}
	}

	/**
	 * 功能描述：查询子模板列表
	 * @param projectID
	 * @param startCode
	 * @param contractType
	 * @param articleType
	 * @param district
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<SubTemplateVO> querySubTemplateList(String projectID,String startID,String contractType,String articleType,String district,Page page) throws Exception {
		try{
			openDAO(ctmDao);
			List<SubTemplateVO> list = ctmDao.querySubTemplateList(projectID, startID, contractType, articleType, district, page);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(ctmDao);
		}
	}

	/**
	 * 功能描述：将用户能操作的项目的id及name存为DictVO
	 * @param signer_ID
	 * @return
	 * @throws Exception
	 */
	public List<DictVO> queryUserProjectJson(long signer_ID) throws Exception {
		try{
			openDAO(ctmDao);
			List<DictVO> list = ctmDao.queryUserProjectJson(signer_ID);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(ctmDao);
		}
	}

	/**
	 * 功能描述：将用户能操作的项目下开盘单元的id及预售许可证的name存为DictVO
	 * @param signer_ID
	 * @return
	 * @throws Exception
	 */
	public List<DictVO> queryUserPresellJson(long signer_ID,String projectID,int districtID) throws Exception {
		try{
			openDAO(ctmDao);
			List<DictVO> list = ctmDao.queryUserPresellJson(signer_ID, projectID, districtID);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(ctmDao);
		}
	}

	/**
	 * 功能描述：新增子模板
	 * @param stVo
	 * @return
	 * @throws Exception
	 */
	public long addSubTemplate(SubTemplateVO stVo) throws Exception {
		try{
			openDAO(ctmDao);
			ctmDao.setTransaction();
			long i = ctmDao.add(stVo);
			ctmDao.commit();
			return i;
		}catch (Exception e){
			ctmDao.rollback();
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(ctmDao);
		}
	}

	/**
	 * 功能描述：更新子模板
	 * @param stVo
	 * @return
	 * @throws Exception
	 */
	public long updateSubTemplate(SubTemplateVO stVo) throws Exception {
		try{
			openDAO(ctmDao);
			ctmDao.setTransaction();
			long i = ctmDao.update(stVo);
			ctmDao.commit();
			return i;
		}catch (Exception e){
			ctmDao.rollback();
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(ctmDao);
		}
	}

	/**
	 * 功能描述：通过项目ID获取项目区县编号
	 * @param projectID
	 * @return
	 * @throws Exception
	 */
	public long findProDis(String projectID) throws Exception {
		try{
			openDAO(ctmDao);
			if(projectID != null && !"".equals(projectID)){
				ProjectVO ptVo = (ProjectVO) ctmDao.find(ProjectVO.class, Long.parseLong(projectID));
				if(ptVo != null){
					return ptVo.getDistrictID();
				}else{
					return 0;
				}
			}else{
				return 0;
			}
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(ctmDao);
		}
	}

	/**
	 * 功能描述：保存附件一模板
	 * @param a1tempVo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public long saveAttach1temp(Attach1TemplateVO a1tempVo,String type) throws Exception {
		try{
			openDAO(ctmDao);
			ctmDao.setTransaction();
			long i = 0;
			if("add".equals(type)){
				i = ctmDao.add(a1tempVo);
			}else{
				i = ctmDao.update2(a1tempVo);
			}
			ctmDao.commit();
			return i;
		}catch (Exception e){
			ctmDao.rollback();
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(ctmDao);
		}
	}

	/**
	 * 功能描述：删除合同主模板
	 * @param templateID
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public long deleteTemplate(long templateID,String type) throws Exception {
		try{
			openDAO(ctmDao);
			ctmDao.setTransaction();
			long i = 0;
			if(type != null && "1".equals(type)){
				ContractTemplateYsVO ptVo = new ContractTemplateYsVO();
				ptVo.setTemplateID(templateID);
				i = ctmDao.delete(ptVo);
			}else{
				ContractTemplateCsVO stVo = new ContractTemplateCsVO();
				stVo.setTemplateID(templateID);
				i = ctmDao.delete(stVo);
			}
			//删除甲方模板
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("templateID", "=", templateID));
			ctmDao.delete(SellerTemplateVO.class, params);

			ctmDao.commit();
			return i;
		}catch (Exception e){
			ctmDao.rollback();
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(ctmDao);
		}
	}

	/**
	 * 功能描述：更新出售合同模板相关信息
	 * @param stVo
	 * @param sertVo
	 * @param atempList
	 * @return
	 * @throws Exception
	 */
	public long saveSellTemplate(ContractTemplateCsVO stVo,SellerTemplateVO sertempVo,List<AttachTemplateVO> atempList) throws Exception {
		try{
			openDAO(ctmDao);
			ctmDao.setTransaction();
			long i = 0;
			//更新出售合同模板
			i = ctmDao.update2(stVo);
			//更新合同甲方模板
			ctmDao.update2(sertempVo);
			//更新合同模板--附件模板信息
			//先删除附件信息
			ctmDao.delTemplateAttch(stVo.getTemplateID());
			if(atempList != null && atempList.size() > 0){
				for(AttachTemplateVO attachTemplateVO : atempList){
					ctmDao.add(attachTemplateVO);
				}
			}

			ctmDao.commit();
			return i;
		}catch (Exception e){
			ctmDao.rollback();
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(ctmDao);
		}
	}

	/**
	 * 功能描述：更新预售合同模板相关信息
	 * @param ptVo
	 * @param sertVo
	 * @param atVoList
	 * @return
	 * @throws Exception
	 */
	public long savePresellTemplate(ContractTemplateYsVO ptVo,SellerTemplateVO sertempVo,List<AttachTemplateVO> atVoList) throws Exception {
		try{
			openDAO(ctmDao);
			ctmDao.setTransaction();
			long i = 0;
			//更新预售合同模板
			i = ctmDao.update2(ptVo);
			//更新合同甲方模板
			ctmDao.update2(sertempVo);
			//更新合同模板--附件模板信息
			//先删除附件信息
			ctmDao.delTemplateAttch(ptVo.getTemplateID());
			if(atVoList != null && atVoList.size() > 0){
				for(AttachTemplateVO attachTemplateVO : atVoList){
					ctmDao.add(attachTemplateVO);
				}
			}
			ctmDao.commit();
			return i;
		}catch (Exception e){
			ctmDao.rollback();
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(ctmDao);
		}
	}

	/**
	 * 功能描述：新增合同主模板
	 * @param ptVo
	 * @param stVo
	 * @param sertVo
	 * @param a1tempVo
	 * @param a1moneytempVo
	 * @param atempList
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public long addTemplate(ContractTemplateYsVO ptVo,ContractTemplateCsVO stVo,SellerTemplateVO sertVo,List<AttachTemplateVO> atempList,String type) throws Exception {
		try{
			openDAO(ctmDao);
			ctmDao.setTransaction();
			long templateID = 0;
			if(type != null && "1".equals(type)){
				//获取新增预售合同ID
				templateID = ctmDao.add(ptVo);
			}else{
				//获取新增出售合同ID
				templateID = ctmDao.add(stVo);
			}

			if(templateID > 0){
				//新增合同甲方模板
				sertVo.setTemplateID(templateID);
				ctmDao.add(sertVo);
			}
			//新增合同附件模板
			if(atempList != null && atempList.size() > 0){
				for(AttachTemplateVO attachTemplateVO : atempList){
					attachTemplateVO.setTemplateID(templateID);
					ctmDao.add(attachTemplateVO);
				}
			}
			ctmDao.commit();
			return templateID;
		}catch (Exception e){
			ctmDao.rollback();
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(ctmDao);
		}
	}

	/**
	 * 功能描述：更改子模板clob字段
	 * @param subtempID
	 * @param content
	 * @param person
	 * @return
	 * @throws Exception
	 */
	public int updateSubtemplate(long subtempID,String content,String person) throws Exception {
		try{
			openDAO(ctmDao);
			int result = ctmDao.updateSubtemplate(subtempID, content, person);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(ctmDao);
		}
	}
	
}
