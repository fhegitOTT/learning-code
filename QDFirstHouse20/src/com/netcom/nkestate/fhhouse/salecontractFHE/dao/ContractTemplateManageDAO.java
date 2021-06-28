package com.netcom.nkestate.fhhouse.salecontractFHE.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractTemplateYsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SubTemplateVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.vo.DictVO;


public class ContractTemplateManageDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(ContractTemplateManageDAO.class.getName());

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" SELECT  DISTINCT  project.projectname,start_unit.start_code,CONTRACTTEMPLATE_CS.TEMPNAME,CONTRACTTEMPLATE_CS.TEMPLATEID,CONTRACTTEMPLATE_CS.updDate,CONTRACTTEMPLATE_CS.updPerson,'2' as TYPE,'2' as typename ");
			sb.append(" FROM enterprisequalify,CONTRACTTEMPLATE_CS,sellertemplate,project,start_unit ");
			sb.append(" where CONTRACTTEMPLATE_CS.TEMPLATEID = sellertemplate.TEMPLATE_ID and  project.PROJECT_ID = CONTRACTTEMPLATE_CS.PROJECTID and CONTRACTTEMPLATE_CS.STARTID = start_unit.START_ID and enterprisequalify.COMP_ID = sellertemplate.COMP_ID ");
			sb.append(" and project.DISTRICTID in " + district);
			if(projectID != null && !"".equals(projectID)){
				sb.append(" and project.PROJECT_ID in ( " + projectID + " )");
			}
			if(startID != null && !"".equals(startID)){
				sb.append(" and start_unit.start_ID = ? ");
				params.add(startID);
			}
			sb.append(" UNION ALL ");
			sb.append(" SELECT  DISTINCT  project.projectname,start_unit.start_code,CONTRACTTEMPLATE_YS.TEMPNAME,CONTRACTTEMPLATE_YS.TEMPLATEID,CONTRACTTEMPLATE_YS.updDate,CONTRACTTEMPLATE_YS.updPerson,'1' as TYPE,'1' as typename ");
			sb.append(" FROM enterprisequalify,CONTRACTTEMPLATE_YS,sellertemplate,project,start_unit ");
			sb.append(" where CONTRACTTEMPLATE_YS.TEMPLATEID = sellertemplate.TEMPLATE_ID and project.PROJECT_ID = CONTRACTTEMPLATE_YS.PROJECTID  and CONTRACTTEMPLATE_YS.STARTID = start_unit.START_ID  and enterprisequalify.COMP_ID = sellertemplate.COMP_ID");
			sb.append(" and project.DISTRICTID in " + district);
			if(projectID != null && !"".equals(projectID)){
				sb.append(" and project.PROJECT_ID in ( " + projectID + " )");
			}
			if(startID != null && !"".equals(startID)){
				sb.append(" and start_unit.start_ID = ? ");
				params.add(startID);
			}
			List list = DataBaseUtil.select(sb.toString(), params, ContractTemplateYsVO.class, page, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" SELECT DISTINCT T3.projectName,T4.SUBTMPL_ID, T4.SUBTMPLNAME, T4.CREPERSON, T4.START_ID, T5.START_CODE, T4.TYPE, T4.ARTICLETYPE, T4.updDate,T4.updPerson ");
			sb.append(" FROM ENTERPRISEQUALIFY T1,COMPANY_PROJECT T2,PROJECT T3,SUBTEMPLATE T4, START_UNIT T5, START_COMP T6 ");
			sb.append(" where T1.COMP_ID = T2.COMP_ID and T2.PROJECT_ID = T3.PROJECT_ID and T3.PROJECT_ID = T4.PROJECT_ID ");
			if(projectID != null && !"".equals(projectID)){
				sb.append(" AND T3.PROJECT_ID  IN ( " + projectID + " ) ");
			}
			if(contractType != null && !"".equals(contractType)){
				sb.append(" AND T4.TYPE = ? ");
				params.add(contractType);
			}
			if(articleType != null && !"".equals(articleType)){
				sb.append("  AND T4.ARTICLETYPE = ? ");
				params.add(articleType);
			}
			if(startID != null && !"".equals(startID)){
				sb.append(" AND T5.START_ID = ? ");
				params.add(startID);
			}
			sb.append(" AND T3.DISTRICTID in " + district);
			sb.append("  AND T4.START_ID = T5.START_ID AND T4.START_ID = T6.START_ID AND T1.COMP_ID = T6.COMP_ID ");
			sb.append("  ORDER BY T4.ARTICLETYPE ASC ");
			List list = DataBaseUtil.select(sb.toString(), params, SubTemplateVO.class, page, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct project.project_id as code,project.projectname as name  ");
			sb.append(" from project ,pro_pre_bld_signer  ");
			sb.append(" where project.project_id=pro_pre_bld_signer.project_id ");
			sb.append(" and project.status in (2) and pro_pre_bld_signer.signer_id=? ");
			sb.append(" order by code desc");
			params.add(signer_ID);
			List list = DataBaseUtil.select(sb.toString(), params, DictVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			if(districtID == 2 || districtID == 3 || districtID == 5 || districtID == 13){
				sb.append(" select distinct start_unit.start_id as code, presell.presell_desc as name ");
				sb.append(" from start_unit, pro_pre_bld_signer, presell ");
				sb.append(" where start_unit.start_id = pro_pre_bld_signer.start_id and start_unit.presell_id = presell.presell_id ");
				sb.append(" and presell.project_id = pro_pre_bld_signer.project_id ");
				sb.append(" and start_unit.issalable=1 and start_unit.status=2 ");
				sb.append(" and pro_pre_bld_signer.signer_id = ? ");
				sb.append(" and pro_pre_bld_signer.project_id = ? ");
				sb.append(" order by code desc ");
				params.add(signer_ID);
				params.add(projectID);
			}else{
				sb.append(" select distinct ");
				sb.append(" start_unit.start_id as code, ");
				sb.append(" presell.presell_desc as name ");
				sb.append(" from pro_pre_bld_signer, presell, start_unit ");
				sb.append(" where pro_pre_bld_signer.signer_id=? ");
				sb.append(" and start_unit.issalable=1 and start_unit.status=2 ");
				sb.append(" and pro_pre_bld_signer.project_id=? and pro_pre_bld_signer.start_id=start_unit.start_id ");
				//--start--20190529 delete by gcf 非市内三区取消签约人许可证权限维护功能，直接可以查询签约--start--
				sb.append(" and start_unit.presell_id=presell.presell_id ");
				/*sb.append(" and start_unit.presell_id=presell.presell_id and presell.presell_id in ");
				sb.append("    (select t_user_project_set.acceptno from t_user_project_set ");
				sb.append("     where t_user_project_set.username=? ");
				sb.append("     group by t_user_project_set.acceptno) ");*/
				sb.append(" order by code desc ");
				params.add(signer_ID);
				params.add(projectID);
//				params.add(signer_ID);//20190529 delete by gcf 非市内三区取消签约人许可证权限维护功能，直接可以查询签约--end--
			}
			List list = DataBaseUtil.select(sb.toString(), params, DictVO.class, conn);
			return list;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	update subtemplate t set t.content=?, ");
			sb.append(" t.updperson=?,t.upddate=?,t.updtime=? ");
			sb.append(" where t.subtmpl_id=? ");
			params.add(content);
			params.add(person);
			params.add(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			params.add(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			params.add(subtempID);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：模板附件删除
	 * @param templateId
	 * @return
	 * @throws Exception
	 */
	public int delTemplateAttch(long templateId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append("	delete from attachtemplate a where a.template_id = ?");
			params.add(templateId);
			int result = DataBaseUtil.execute(sb.toString(), params, conn);
			return result;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}finally{

		}
	}

}
