/**
 * <p>CompanyDAO.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 开发企业DAO <p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.company.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.vo.EnterpriseQualifyVO;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingHouseVO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingVO;
import com.netcom.nkestate.fhhouse.project.vo.PresellVO;
import com.netcom.nkestate.fhhouse.project.vo.ProPreBldSignerVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;
import com.netcom.nkestate.system.vo.SmObjectVO;


public class CompanyDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(CompanyDAO.class.getName());

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
			params.add(FHConstant.APP_FH_OUTER);
			List list = DataBaseUtil.select(sb.toString(), params, SmObjectVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.* ");
			sb.append(" from project a,company_project b ");
			sb.append(" where a.project_id = b.project_id ");
			sb.append(" and b.comp_id = ? ");
			//sb.append(" and a.status = ? ");
			sb.append(" order by a.project_id ");
			params.add(companyId);
			//params.add(FHConstant.PROJECT_STATUS_PERMITTED);
			List list = DataBaseUtil.select(sb.toString(), params, ProjectVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.*,b.presell_desc ");
			sb.append(" from  start_unit a, presell b,start_comp c,project d ");
			sb.append(" where a.presell_id = b.presell_id ");
			sb.append(" and a.start_id = c.start_id ");
			sb.append(" and b.project_id = d.project_id ");
			sb.append(" and c.comp_id = ? ");
			//sb.append(" and a.status = ? ");
			//sb.append(" and d.status = ? ");
			params.add(companyId);
			//params.add(FHConstant.START_UNIT_STATUS_PERMITTED);
			//params.add(FHConstant.PROJECT_STATUS_PERMITTED);
			if(projectId != null && !"".equals(projectId)){
				sb.append(" and b.project_id = ? ");
				params.add(projectId);
			}

			sb.append(" order by a.start_id ");

			List list = DataBaseUtil.select(sb.toString(), params, StartUnitVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select distinct a.project_id,a.presell_id,b.start_id,c.building_id,d.building_name ");
			sb.append(" from  presell a,start_unit b,building_house c,building d,START_COMP e,PROJECT f ");
			sb.append(" where a.presell_id = b.presell_id ");
			sb.append(" and b.start_id = c.start_id ");
			sb.append(" and c.building_id = d.building_id ");
			sb.append(" and b.start_id = e.start_id ");
			sb.append(" and a.project_id = f.project_id ");
			sb.append(" and e.comp_id = ? ");
			//sb.append(" and b.status = ? ");
			//sb.append(" and f.status = ? ");
			params.add(companyId);
			//params.add(FHConstant.START_UNIT_STATUS_PERMITTED);
			//params.add(FHConstant.PROJECT_STATUS_PERMITTED);
			if(startId != null && !"".equals(startId)){
				sb.append(" and b.start_id = ? ");
				params.add(startId);
			}
			if(projectId != null && !"".equals(projectId)){
				sb.append(" and f.project_id = ? ");
				params.add(projectId);
			}
			sb.append(" order by b.start_id,c.building_id ");

			List list = DataBaseUtil.select(sb.toString(), params, BuildingHouseVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select a.start_id,a.project_id,a.building_id,b.building_name ");
			sb.append(" from pro_pre_bld_signer a,building b ");
			sb.append(" where a.building_id = b.building_id ");
			sb.append(" and a.signer_id = ? ");
			sb.append(" order by a.start_id,a.building_id ");
			params.add(signerId);

			List list = DataBaseUtil.select(sb.toString(), params, ProPreBldSignerVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select c.presell_desc,a.projectname,a.project_id ");
			sb.append(" from project a,company_project b,presell c,start_unit d ");
			sb.append(" where a.project_id = b.project_id and a.project_id = c.project_id ");
			sb.append(" and c.presell_id = d.presell_id and b.comp_id = ? ");
			//						if(status != null && !"".equals(status)){
			//							sb.append(" and a.status=? ");
			//							params.add(status);
			//						}
			//						if(status != null && !"".equals(status)){
			//							sb.append(" and d.status=? ");
			//							params.add(status);
			//						}
			sb.append(" order by d.upddate desc,d.updtime desc ");
			params.add(compId);

			List list = DataBaseUtil.select(sb.toString(), params, ProjectVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select distinct f.building_name,c.presell_desc,d.start_id ");
			sb.append(" from project a,company_project b,presell c,start_unit d,building_house e,building f ");
			sb.append(" where a.project_id = b.project_id and a.project_id = c.project_id ");
			sb.append(" and c.presell_id = d.presell_id and e.building_id = f.building_id ");
			sb.append(" and e.start_id =d.start_id and b.comp_id = ? ");
			sb.append(" order by f.building_name ");
			params.add(compId);
			List list = DataBaseUtil.select(sb.toString(), params, StartUnitVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct enterprisequalify.comp_id,enterprisequalify.name, ");
			sb.append(" enterprisequalify.regadr,enterprisequalify.delegate,enterprisequalify.status ");
			sb.append(" from enterprisequalify,company_project,presell,start_unit ");
			sb.append(" where enterprisequalify.comp_id = company_project.comp_id(+) and company_project.project_id = presell.project_id(+) ");
			sb.append(" and presell.presell_id = start_unit.presell_id(+) and bizdistrict in " + districtList);
			sb.append(" and enterprisequalify.status not in (0,1) ");
			if(compCode != null && !"".equals(compCode)){
				sb.append(" and enterprisequalify.comp_code=? ");
				params.add(compCode);
			}
			if(name != null && !"".equals(name)){
				sb.append(" and enterprisequalify.name like ? ");
				params.add("%" + name + "%");
			}
			if(documentId != null && !"".equals(documentId)){
				sb.append(" and start_unit.document_id = ? ");
				params.add(documentId);
			}
			List list = DataBaseUtil.select(sb.toString(), params, EnterpriseQualifyVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：资质审核列表
	 * @param page
	 * @param companyName
	 * @param projectName
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List<EnterpriseQualifyVO> findCompanyAuditInfo(Page page,String companyName,String projectName,String districtList) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select * from ( ");
			sb.append(" select distinct start_unit.start_id as auditingid,start_unit.start_code ||'('||project.projectname||')' as auditingname, ");
			sb.append(" start_unit.firstauditdate,start_unit.upddate,start_unit.updtime, ");
			sb.append(" (select name from  ct_317 where  code = 0) as name,'0' as code ");

			sb.append(" from enterprisequalify,company_project,project,presell,start_unit ");
			sb.append(" where enterprisequalify.comp_id = company_project.comp_id and company_project.project_id = presell.project_id ");
			sb.append(" and company_project.project_id = project.project_id and start_unit.presell_id = presell.presell_id ");
			sb.append(" and enterprisequalify.status in (2,4,7) and project.status in (2,4,7) ");
			sb.append(" and project.districtid in " + districtList);
			sb.append(" and start_unit.status= " + FHConstant.START_UNIT_STATUS_SUBMIT);

			if(companyName != null && !"".equals(companyName)){
				sb.append(" and ENTERPRISEQUALIFY.NAME LIKE ? ");
				params.add("%" + companyName + "%");
			}
			if(projectName != null && !"".equals(projectName)){
				sb.append(" and PROJECT.PROJECTNAME LIKE ? ");
				params.add("%" + projectName + "%");
			}
			sb.append(" order by start_unit.upddate desc,start_unit.updtime desc ) t1 ");

			sb.append(" union all ");
			sb.append(" select * from ( ");
			sb.append(" select distinct signer.signer_id as auditingid,signer.name as auditingname,	");
			sb.append(" signer.firstauditdate,signer.upddate,signer.updtime, ");
			sb.append(" (select name from  ct_317 where  code = 1) as name,'1' as code ");

			sb.append(" from enterprisequalify,signer,company_project,project ");
			sb.append(" where signer.comp_id = enterprisequalify.comp_id and enterprisequalify.comp_id = signer.comp_id ");
			sb.append(" and enterprisequalify.comp_id = company_project.comp_id and company_project.project_id = project.project_id ");
			sb.append(" and enterprisequalify.status in (2,4,7) ");
			sb.append(" and enterprisequalify.bizdistrict in " + districtList);
			sb.append(" and signer.status= " + FHConstant.SIGNER_STATUS_SUBMIT);

			if(companyName != null && !"".equals(companyName)){
				sb.append(" and ENTERPRISEQUALIFY.NAME LIKE ? ");
				params.add("%" + companyName + "%");
			}
			if(projectName != null && !"".equals(projectName)){
				sb.append(" and PROJECT.PROJECTNAME LIKE ? ");
				params.add("%" + projectName + "%");
			}
			sb.append(" order by signer.upddate desc,signer.updtime desc ) t2 ");

			sb.append(" union all ");
			sb.append(" select * from ( ");
			sb.append(" select * from (select distinct project.project_id as auditingid, ");
			sb.append("	project.projectname as auditingname,project.firstauditdate, ");
			sb.append("	project.upddate,project.updtime, ");
			sb.append(" (select name from  ct_317 where  code = 2) as name,'2' as code ");

			sb.append("	from enterprisequalify,company_project,project ");
			sb.append("	where enterprisequalify.comp_id = company_project.comp_id ");
			sb.append("	and project.project_id = company_project.project_id ");
			sb.append("	and enterprisequalify.status in (2,4,7)  ");
			sb.append("	and project.districtid in " + districtList);
			sb.append(" and project.status= " + FHConstant.PROJECT_STATUS_SUBMIT);

			if(companyName != null && !"".equals(companyName)){
				sb.append(" and ENTERPRISEQUALIFY.NAME LIKE ? ");
				params.add("%" + companyName + "%");
			}
			if(projectName != null && !"".equals(projectName)){
				sb.append(" and PROJECT.PROJECTNAME LIKE ? ");
				params.add("%" + projectName + "%");
			}
			sb.append("  )t where not exists ( select enterprisequalify.comp_id ");
			sb.append("	from enterprisequalify,company_project ");
			sb.append("	where 1 =1 ");
			sb.append(" and enterprisequalify.status= " + FHConstant.COMP_STATUS_SUBMIT);

			sb.append("	and enterprisequalify.comp_id = company_project.comp_id ");
			sb.append("	and t.auditingid = company_project.project_id ");
			sb.append("  ) order by t.upddate desc,t.updtime desc ) t3 ");

			sb.append(" union all ");
			sb.append(" select * from ( ");
			sb.append(" select distinct enterprisequalify.comp_id as auditingid,enterprisequalify.name as auditingname, ");
			sb.append(" enterprisequalify.firstauditdate,enterprisequalify.upddate,enterprisequalify.updtime, ");
			sb.append(" (select name from  ct_317 where  code = 4) as name,'4' as code ");

			sb.append(" from enterprisequalify ");
			sb.append("	left outer join (project join company_project on company_project.project_id=project.project_id) ");
			sb.append("	on enterprisequalify.comp_id = company_project.comp_id ");
			sb.append("	where enterprisequalify.bizdistrict in " + districtList);
			sb.append(" and enterprisequalify.status= " + FHConstant.COMP_STATUS_SUBMIT);

			if(companyName != null && !"".equals(companyName)){
				sb.append(" and ENTERPRISEQUALIFY.NAME LIKE ? ");
				params.add("%" + companyName + "%");
			}
			if(projectName != null && !"".equals(projectName)){
				sb.append(" and PROJECT.PROJECTNAME LIKE ? ");
				params.add("%" + projectName + "%");
			}
			sb.append(" order by enterprisequalify.upddate desc,enterprisequalify.updtime desc ) t4 ");
			//System.out.println(sb.toString());
			List list = DataBaseUtil.select(sb.toString(), params, EnterpriseQualifyVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：资质发布列表
	 * @param page
	 * @param companyName
	 * @param projectName
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List<EnterpriseQualifyVO> findCompanyPublishInfo(Page page,String companyName,String projectName,String districtList) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select * from ( ");
			sb.append(" select distinct start_unit.start_id as auditingid,start_unit.start_code ||'('||project.projectname||')' as auditingname, ");
			sb.append(" start_unit.firstauditdate,start_unit.upddate,start_unit.updtime, ");
			sb.append(" (select name from  ct_318 where  code = 0) as name,'0' as code ");
			sb.append(" from enterprisequalify,company_project,project,presell,start_unit ");
			sb.append(" where enterprisequalify.comp_id = company_project.comp_id and company_project.project_id = presell.project_id ");
			sb.append(" and company_project.project_id = project.project_id and start_unit.presell_id = presell.presell_id ");
			sb.append(" and enterprisequalify.status in (2) and project.status in (2) ");
			sb.append(" and project.districtid in " + districtList);
			sb.append(" and start_unit.status in (" + FHConstant.START_UNIT_STATUS_PASSED + "," + FHConstant.START_UNIT_STATUS_NOPERMITTED + ") ");

			if(companyName != null && !"".equals(companyName)){
				sb.append(" and ENTERPRISEQUALIFY.NAME LIKE ? ");
				params.add("%" + companyName + "%");
			}
			if(projectName != null && !"".equals(projectName)){
				sb.append(" and PROJECT.PROJECTNAME LIKE ? ");
				params.add("%" + projectName + "%");
			}
			sb.append(" order by start_unit.upddate desc,start_unit.updtime desc ) t1 ");

			sb.append(" union all ");
			sb.append(" select * from ( ");
			sb.append(" select distinct signer.signer_id as auditingid,signer.name as auditingname,	");
			sb.append(" signer.firstauditdate,signer.upddate,signer.updtime, ");
			sb.append(" (select name from  ct_318 where  code = 1) as name,'1' as code ");

			sb.append(" from enterprisequalify,signer,company_project,project ");
			sb.append(" where signer.comp_id = enterprisequalify.comp_id and enterprisequalify.comp_id = signer.comp_id ");
			sb.append(" and enterprisequalify.comp_id = company_project.comp_id and company_project.project_id = project.project_id ");
			sb.append(" and enterprisequalify.status in (2) ");
			sb.append(" and enterprisequalify.bizdistrict in " + districtList);
			sb.append(" and signer.status in (" + FHConstant.SIGNER_STATUS_PASSED + "," + FHConstant.SIGNER_STATUS_NOPERMITTED + ") ");

			if(companyName != null && !"".equals(companyName)){
				sb.append(" and ENTERPRISEQUALIFY.NAME LIKE ? ");
				params.add("%" + companyName + "%");
			}
			if(projectName != null && !"".equals(projectName)){
				sb.append(" and PROJECT.PROJECTNAME LIKE ? ");
				params.add("%" + projectName + "%");
			}
			sb.append(" order by signer.upddate desc,signer.updtime desc ) t2 ");

			sb.append(" union all ");
			sb.append(" select * from (select distinct project.project_id as auditingid, ");
			sb.append("	project.projectname as auditingname,project.firstauditdate, ");
			sb.append("	project.upddate,project.updtime, ");
			sb.append(" (select name from  ct_318 where  code = 2) as name,'2' as code ");
			sb.append("	from enterprisequalify,company_project,project ");
			sb.append("	where enterprisequalify.comp_id = company_project.comp_id ");
			sb.append("	and project.project_id = company_project.project_id ");
			sb.append("	and enterprisequalify.status in (2)  ");
			sb.append("	and project.districtid in " + districtList);
			sb.append(" and project.status in (" + FHConstant.PROJECT_STATUS_PASSED + "," + FHConstant.PROJECT_STATUS_NOPERMITTED + ") ");

			if(companyName != null && !"".equals(companyName)){
				sb.append(" and ENTERPRISEQUALIFY.NAME LIKE ? ");
				params.add("%" + companyName + "%");
			}
			if(projectName != null && !"".equals(projectName)){
				sb.append(" and PROJECT.PROJECTNAME LIKE ? ");
				params.add("%" + projectName + "%");
			}
			sb.append("  order by project.upddate desc,project.updtime desc ) t3 ");

			sb.append(" union all ");
			sb.append(" select * from ( ");
			sb.append(" select distinct enterprisequalify.comp_id as auditingid,enterprisequalify.name as auditingname, ");
			sb.append(" enterprisequalify.firstauditdate,enterprisequalify.upddate,enterprisequalify.updtime, ");
			sb.append(" (select name from  ct_318 where  code = 4) as name,'4' as code ");

			sb.append(" from enterprisequalify ");
			sb.append("	left outer join (project join company_project on company_project.project_id=project.project_id) ");
			sb.append("	on enterprisequalify.comp_id = company_project.comp_id ");
			sb.append("	where enterprisequalify.bizdistrict in " + districtList);
			sb.append(" and enterprisequalify.status  in (" + FHConstant.COMP_STATUS_PASSED + "," + FHConstant.COMP_STATUS_NOPERMITTED + ") ");

			if(companyName != null && !"".equals(companyName)){
				sb.append(" and ENTERPRISEQUALIFY.NAME LIKE ? ");
				params.add("%" + companyName + "%");
			}
			if(projectName != null && !"".equals(projectName)){
				sb.append(" and PROJECT.PROJECTNAME LIKE ? ");
				params.add("%" + projectName + "%");
			}
			sb.append(" order by enterprisequalify.upddate desc,enterprisequalify.updtime desc ) t4 ");
			//System.out.println(sb.toString());
			List list = DataBaseUtil.select(sb.toString(), params, EnterpriseQualifyVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：企业审核--企业信息查询
	 * @param page
	 * @param compId
	 * @return
	 * @throws Exception
	 */
	public List<EnterpriseQualifyVO> findCompanyInfo(String compId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select nvl(c.displayname,b.username) as firstcensor,nvl(e.displayname,d.username)  as finalcensor ");
			sb.append(" from enterprisequalify a,userinfo b,platform.sm_user_t c,userinfo d,platform.sm_user_t e ");
			sb.append(" where a.firstcensor = b.loginname(+) and  a.firstcensor =to_char(c.userid(+)) ");
			sb.append(" and a.finalcensor = d.loginname(+) and  a.finalcensor =to_char(e.userid(+)) ");
			if(compId != null && !"".equals(compId)){
				sb.append(" and a.comp_id=? ");
				params.add(compId);
			}
			List list = DataBaseUtil.select(sb.toString(), params, EnterpriseQualifyVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：企业审核--项目信息查询
	 * @param page
	 * @param compId
	 * @return
	 * @throws Exception
	 */
	public List<ProjectVO> findProjectInfo(String status,String compId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select project.projectname,project.project_id,t0.presell_desc,t0.presell_id, ");
			sb.append(" t0.presell_code,t0.start_id,t0.start_code ");
			sb.append(" ,t0.presell_desc||'('||t0.start_code||')' as psdesc ");
			sb.append(" from (select start_unit.start_id,start_unit.start_code,start_unit.updcnt,presell.presell_desc, ");
			sb.append("   presell.presell_id,presell.presell_code,presell.project_id ");
			sb.append("   from start_unit,presell where start_unit.status = ? ");
			if("audit".equals(status)){
				params.add(FHConstant.START_UNIT_STATUS_SUBMIT);
			}else{
				params.add(FHConstant.START_UNIT_STATUS_PASSED);
			}
			sb.append("   and presell.presell_id = start_unit.presell_id) t0,project,company_project ");
			sb.append(" where company_project.project_id = project.project_id and company_project.project_id = t0.project_id(+) ");
			sb.append(" and project.status = ? and company_project.comp_id=? ");
			if("audit".equals(status)){
				params.add(FHConstant.PROJECT_STATUS_SUBMIT);
			}else{
				params.add(FHConstant.PROJECT_STATUS_PASSED);
			}
			params.add(compId);
			List list = DataBaseUtil.select(sb.toString(), params, ProjectVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct start_unit.start_id,building.building_name,presell.presell_id ");
			sb.append(" ,presell.presell_desc||'('||start_unit.start_code||')' as psdesc ");
			sb.append(" from company_project,presell,start_unit,building,building_house ");
			sb.append(" where company_project.project_id = presell.project_id and presell.presell_id = start_unit.presell_id ");
			sb.append(" and start_unit.start_id = building_house.start_id and building.building_id = building_house.building_id ");
			sb.append(" and company_project.comp_id=? ");
			params.add(compId);
			List list = DataBaseUtil.select(sb.toString(), params, StartUnitVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select start_unit.start_id,start_unit.start_code,presell.type,presell.presell_desc,presell.presell_id,  ");
			sb.append(" start_unit.document_id,start_unit.firstmark,start_unit.firstauditdate,nvl(c.displayname,b.username)  as firstcensor ");
			sb.append(" ,start_unit.finalmark, start_unit.finalauditdate,nvl(f.displayname,e.username)  as finalcensor  ");
			sb.append(" from start_unit,presell ,userinfo b,platform.sm_user_t c,userinfo e,platform.sm_user_t f ");
			sb.append(" where start_unit.presell_id = presell.presell_id and start_unit.start_id =? ");
			sb.append(" and start_unit.firstcensor = b.loginname(+) and  start_unit.firstcensor =to_char(c.userid(+)) ");
			sb.append(" and start_unit.finalcensor = e.loginname(+) and  start_unit.finalcensor =to_char(f.userid(+)) ");
			params.add(startId);
			List list = DataBaseUtil.select(sb.toString(), params, StartUnitVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct building_house.building_id,building_house.reference_price,building.building_name ");
			sb.append(" from building_house,building ");
			sb.append(" where building.building_id = building_house.building_id and building_house.start_id =? ");
			params.add(startId);
			List list = DataBaseUtil.select(sb.toString(), params, BuildingHouseVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct signer.signer_id,signer.name,signer.loginname, ");
			sb.append(" signer.cardname,signer.cardcode,signer.brokercert ");
			sb.append(" from pro_pre_bld_signer,signer ");
			sb.append(" where signer.signer_id = pro_pre_bld_signer.signer_id and pro_pre_bld_signer.start_id =? ");
			sb.append(" and signer.status = " + FHConstant.SIGNER_STATUS_SUBMIT);
			params.add(startId);
			List list = DataBaseUtil.select(sb.toString(), params, SignerVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select enterprisequalify.name from enterprisequalify,start_comp ");
			sb.append(" where enterprisequalify.comp_id = start_comp.comp_id and start_comp.start_id =? ");
			params.add(startId);
			List list = DataBaseUtil.select(sb.toString(), params, EnterpriseQualifyVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select a.signer_id,a.name,a.cardname,a.cardcode,a.loginname,a.pwd,a.brokercert,a.comp_id,a.status,b.name as compname,b.legalmancode,c.name as agentname,  ");
			sb.append("  nvl(e.displayname,d.username) as firstcensor ,a.firstmark,a.firstauditdate,nvl(g.displayname,f.username) as finalcensor ,a.finalmark,a.finalauditdate  ");
			sb.append(" from enterprisequalify b,agent c,signer a ,userinfo d,platform.sm_user_t e,userinfo f,platform.sm_user_t g ");
			sb.append(" where b.comp_id = a.comp_id  and c.agent_id(+) = a.agent_id and a.signer_id =? ");
			sb.append(" and a.firstcensor = d.loginname(+) and a.firstcensor = to_char(e.userid(+)) and  a.finalcensor = f.loginname(+) and a.finalcensor = to_char(g.userid(+)) ");
			params.add(signerId);
			List list = DataBaseUtil.select(sb.toString(), params, SignerVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct project.projectname,presell.presell_desc,presell.presell_code,start_unit.start_code, ");
			sb.append(" start_unit.start_id,start_unit.status as s_status,project.status as p_status,presell.presell_id ");
			sb.append(" from pro_pre_bld_signer,project,start_unit,presell ");
			sb.append(" where pro_pre_bld_signer.project_id = project.project_id and pro_pre_bld_signer.start_id = start_unit.start_id ");
			sb.append(" and start_unit.presell_id = presell.presell_id and pro_pre_bld_signer.signer_id =? ");
			params.add(signerId);
			List list = DataBaseUtil.select(sb.toString(), params, ProjectVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct building.building_name,presell.presell_desc, ");
			sb.append(" presell.presell_id,start_unit.start_code,start_unit.start_id ");
			sb.append(" from pro_pre_bld_signer,building,start_unit,presell ");
			sb.append(" where pro_pre_bld_signer.building_id = building.building_id and start_unit.start_id = pro_pre_bld_signer.start_id ");
			sb.append(" and start_unit.presell_id = presell.presell_id and pro_pre_bld_signer.signer_id =? ");
			params.add(signerId);
			List list = DataBaseUtil.select(sb.toString(), params, StartUnitVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select a.project_id,a.projectname,a.projectadr,a.lotnum,a.districtid,a.firstmark,a.firstauditdate,nvl(c.displayname,b.username) as firstcensor ");
			sb.append(" ,a.finalmark,a.finalauditdate,nvl(e.displayname,d.username) as finalcensor ");
			sb.append(" from (select * from project where  project_id =?) a,userinfo b,platform.sm_user_t c ,userinfo d,platform.sm_user_t e ");
			sb.append(" where a.firstcensor = b.loginname(+) and a.firstcensor =to_char( c.userid(+)) ");
			sb.append("  and a.finalcensor = d.loginname(+) and a.finalcensor = to_char(e.userid(+)) ");
			params.add(projectid);
			List list = DataBaseUtil.select(sb.toString(), params, ProjectVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select  distinct signer.signer_id,signer.name,signer.loginname,signer.cardname, signer.cardcode,signer.brokercert ");
			sb.append(" from pro_pre_bld_signer,signer ");
			sb.append(" where pro_pre_bld_signer.signer_id = signer.signer_id  and pro_pre_bld_signer.project_id =?");
			if("audit".equals(status)){
				sb.append("  and SIGNER.STATUS =" + FHConstant.SIGNER_STATUS_SUBMIT);
			}
			if("publish".equals(status)){
				sb.append("  and SIGNER.STATUS =" + FHConstant.SIGNER_STATUS_PASSED);
			}
			params.add(projectid);
			List list = DataBaseUtil.select(sb.toString(), params, SignerVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select presell.presell_desc,presell.presell_id,presell_code,start_unit.start_id,start_unit.start_code,start_unit.updcnt ");
			sb.append(" from start_unit,presell ");
			sb.append(" where start_unit.presell_id = presell.presell_id  and presell.project_id=? ");
			if("audit".equals(status)){
				sb.append(" AND START_UNIT.STATUS =  " + FHConstant.START_UNIT_STATUS_SUBMIT);
			}
			if("publish".equals(status)){
				sb.append(" AND START_UNIT.STATUS =  " + FHConstant.START_UNIT_STATUS_PASSED);
			}
			params.add(projectid);
			List list = DataBaseUtil.select(sb.toString(), params, PresellVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select  distinct start_unit.start_id,building.building_name,presell.presell_id ");
			sb.append(" from building,building_house,start_unit,presell ");
			sb.append(" where presell.presell_id = start_unit.presell_id and start_unit.start_id = building_house.start_id  ");
			sb.append(" and building_house.building_id = building.building_id and presell.project_id=? ");
			if("audit".equals(status)){
				sb.append(" AND START_UNIT.STATUS =  " + FHConstant.START_UNIT_STATUS_SUBMIT);
			}
			if("publish".equals(status)){
				sb.append(" AND START_UNIT.STATUS =  " + FHConstant.START_UNIT_STATUS_PASSED);
			}
			params.add(projectid);
			List list = DataBaseUtil.select(sb.toString(), params, BuildingVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
		}
	}

	/**
	 * 功能描述：项目审核--企业信息查询
	 * @param projectid
	 * @return
	 * @throws Exception
	 */
	public List<EnterpriseQualifyVO> findCompanyForProject(String projectid) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select enterprisequalify.* ");
			sb.append(" from enterprisequalify,company_project ");
			sb.append(" where　enterprisequalify.comp_id = company_project.comp_id　and company_project.project_id =? ");
			params.add(projectid);
			List list = DataBaseUtil.select(sb.toString(), params, EnterpriseQualifyVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
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
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select c.presell_desc,d.start_id  from project a,company_project b,presell c,start_unit d ");
			sb.append(" where a.project_id = b.project_id and a.project_id = c.project_id  and c.presell_id = d.presell_id ");
			sb.append(" and b.comp_id = ? ");
			sb.append(" order by d.upddate desc,d.updtime desc ");
			params.add(compId);
			List list = DataBaseUtil.select(sb.toString(), params, StartUnitVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}
}
