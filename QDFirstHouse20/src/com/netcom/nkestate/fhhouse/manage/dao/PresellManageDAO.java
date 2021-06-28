package com.netcom.nkestate.fhhouse.manage.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.manage.vo.UserProjectSetVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;


public class PresellManageDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(PresellManageDAO.class.getName());

	/**
	 * 功能描述：开发企业项目列表
	 * @param compId
	 * @return
	 * @throws Exception
	 */
	public List<ProjectVO> queryProject(String compId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select distinct a.project_id,b.projectname");
			sb.append(" from company_project a,project b ");
			sb.append(" where comp_id=? ");
			sb.append(" and a.project_id = b.project_id ");
			//sb.append(" and b.districtid not in (2,3,5,13) ");
			sb.append(" order by a.project_id ");
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
	 * 功能描述：签约人许可证权限列表
	 * @param name
	 * @param presell_Desc
	 * @param companyname
	 * @param projectName
	 * @param saveDate
	 * @return
	 * @throws Exception
	 */
	public List queryUserProject(String name,String presell_Desc,String companyname,String projectName,String saveDate,Page page) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct a.username,a.acceptno,c.name,b.presell_desc,a.key,f.projectname,e.name as companyname,a.savedate ");
			sb.append(" from t_user_project_set a,presell b,signer c,company_project d,enterprisequalify e,project f ");
			sb.append(" where a.username = to_char(c.signer_id) ");
			sb.append(" and a.acceptno = to_char(b.presell_id) ");
			sb.append(" and b.project_id = d.project_id ");
			sb.append(" and d.comp_id = e.comp_id ");
			sb.append(" and d.project_id = f.project_id ");
			if(name != null && !"".equals(name)){
				sb.append(" and c.name like ? ");
				params.add("%" + name + "%");
			}
			if(presell_Desc != null && !"".equals(presell_Desc)){
				sb.append(" and b.presell_desc like ? ");
				params.add("%" + presell_Desc + "%");
			}
			if(companyname != null && !"".equals(companyname)){
				sb.append(" and e.name like ? ");
				params.add("%" + companyname + "%");
			}
			if(projectName != null && !"".equals(projectName)){
				sb.append(" and f.projectname like ? ");
				params.add("%" + projectName + "%");
			}
			if(saveDate != null && !"".equals(saveDate)){
				sb.append(" and a.savedate = ? ");
				params.add(saveDate.replaceAll("-", ""));
			}
			sb.append(" order by a.username ");
			List list = DataBaseUtil.select(sb.toString(), params, UserProjectSetVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：签约人对应项目的许可证权限列表
	 * @param signerID
	 * @param projectID
	 * @return
	 * @throws Exception
	 */
	public List queryUserPresell(String signerID,String projectID) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select a.acceptno ");
			sb.append(" from t_user_project_set a,presell b,signer c,company_project d,enterprisequalify e,project f ");
			sb.append(" where a.username = to_char(c.signer_id) ");
			sb.append(" and a.acceptno = to_char(b.presell_id) ");
			sb.append(" and b.project_id = d.project_id ");
			sb.append(" and d.comp_id = e.comp_id ");
			sb.append(" and d.project_id = f.project_id ");
			sb.append(" and c.signer_id = ? ");
			sb.append(" and f.project_id = ? ");
			sb.append(" order by a.username ");
			params.add(signerID);
			params.add(projectID);
			List list = DataBaseUtil.select(sb.toString(), params, UserProjectSetVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：删除签约人许可证
	 * @param username
	 * @param acceptno
	 * @return
	 * @throws Exception
	 */
	public long deleteUserProjectSet(String username,String[] acceptno) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" delete from t_user_project_set ");
			sb.append(" where username = ? ");
			sb.append(" and acceptno in ");
			sb.append("(");
			for(String str : acceptno){
				sb.append("'").append(str).append("',");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(")");

			params.add(username);
			return DataBaseUtil.execute(sb.toString(), params, conn);
		}catch (Exception e){
			logger.error(e);
			throw e;
		}
	}
}
