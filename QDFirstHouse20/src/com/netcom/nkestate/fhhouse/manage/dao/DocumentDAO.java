package com.netcom.nkestate.fhhouse.manage.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.manage.vo.DocumentHistoryVO;
import com.netcom.nkestate.fhhouse.manage.vo.DocumentVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;
import com.netcom.nkestate.system.vo.SmUserVO;
import com.netcom.nkestate.system.vo.UserinfoVO;


public class DocumentDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(DocumentDAO.class.getName());

	/**
	 * 功能描述：查询收件信息列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<DocumentVO> findDocuments(Page page,String DocumentID,String projName,String position,String companyName,String status,String startDate,String overDate,String userId,List<Object> districtList) throws Exception {
		
		try{
			StringBuffer sb=new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			
			sb.append(" select t2.document_id as documentid, ");
			sb.append(" t4.company_name as company_name, ");
			sb.append(" t4.project_name as projname, ");
			sb.append(" t4.status as status, ");
			sb.append(" to_char( to_date(t4.credate || '' || replace(lpad(t4.cretime,6),' ','0'),'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss' ) as createdate, ");
			sb.append(" t4.creperson as createperson, ");
			sb.append(" nvl(t3.username,t5.displayname) as username from document_history t2, ");
			sb.append(" (select t0.document_id,max(t0.id) as maxid from document_history t0 group by t0.document_id) t1, ");
			sb.append(" document t4, ");
			sb.append(" userinfo t3, ");
			sb.append(" platform.sm_user_t t5 ");
			sb.append(" where t2.document_id=t1.document_id ");
			sb.append(" and t2.document_id=t4.document_id ");
			sb.append(" and t2.userid = t5.userid(+) ");
			sb.append(" and t2.olduserid = t3.userid(+) ");
			
				if(DocumentID!=null && !"".equals(DocumentID)){
				sb.append(" and t4.document_id=? ");
					params.add(Long.parseLong(DocumentID));
				}if(projName!=null && !"".equals(projName)){
				sb.append(" and t4.project_name like ?");
					params.add("%" +projName+ "%");
				}if(companyName!=null && !"".equals(companyName)){
				sb.append(" and t4.company_name like ?");
				params.add("%" + companyName + "%");
				}if(position!=null && !"".equals(position)){
				sb.append(" and t4.position like ?");
					params.add(position);
				}if(status!=null && !"".equals(status)){
					if(Integer.parseInt(status)==0 || Integer.parseInt(status)==1){
					sb.append(" and t4.status=? ");
						params.add(Integer.parseInt(status));
					}
				}if(startDate != null && !"".equals(startDate)){
				sb.append("  and t4.credate >=? ");
					params.add(startDate.replaceAll("-", ""));
				}
				if(overDate != null && !"".equals(overDate)){
				sb.append("  and t4.credate <=? ");
					params.add(overDate.replaceAll("-", ""));
			}
			if(userId != null && !"".equals(userId)){
					if("1".equals(userId.substring(userId.length() - 1))){
					sb.append(" and t2.userid=? ");
						params.add(userId.substring(0, userId.length() - 1));
					}if("2".equals(userId.substring(userId.length() - 1))){
						//System.out.println(userId.substring(0, userId.length() - 1));
					sb.append(" and t2.olduserid=? ");
					params.add(userId.substring(0, userId.length() - 1));
					}
			}
			if(districtList != null){
				if(districtList.size() > 1){
					sb.append("  and t4.districtid in (0,2,3,5,13) ");
				}else if(districtList.size() == 1){
					sb.append(" and t4.districtid  = " + districtList.get(0));
				}else{
					sb.append(" and t4.districtid  = -1 ");
				}
			}
			sb.append(" order by t2.document_id desc ");
			List list = DataBaseUtil.select(sb.toString(), params, DocumentVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：流转状态userID和username列表
	 * @return
	 * @throws Exception
	 */
	public List<SmUserVO> findSmUsers() throws Exception{
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct c.userid,c.displayname ");
			sb.append(" from document_history a,platform.sm_user_t c ");
			sb.append(" where a.userid = c.userid(+) and c.displayname is not null ");
			List list = DataBaseUtil.select(sb.toString(), params, SmUserVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}

	}

	/**
	 * 功能描述：流转状态olduserID和username列表
	 * @return
	 * @throws Exception
	 */
	public List<UserinfoVO> findUseinfos() throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct b.userid, b.username ");
			sb.append(" from document_history a,userinfo b ");
			sb.append(" where a.olduserid = b.userid(+) and b.username is not null ");
			List list = DataBaseUtil.select(sb.toString(), params, UserinfoVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：退件和恢复
	 * @param documentId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public int docReturn(long documentId,int status) throws Exception {
		try{
			List<Object> params = new ArrayList<Object>();
			String sql="";
			if(status==0){
				sql = " update document d set d.status=1  where d.document_id=? ";
			}if(status==1){
				sql = " update document d set d.status=0  where d.document_id=? ";
			}
			params.add(documentId);
			int aline = DataBaseUtil.execute(sql, params, conn);
			return aline;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：流转结果清单
	 * @param page
	 * @param DocumentID
	 * @param projName
	 * @param position
	 * @param companyName
	 * @param status
	 * @param startDate
	 * @param overDate
	 * @param userId
	 * @param districtList
	 * @return
	 * @throws Exception
	 */
	public List<DocumentVO> findDocResultList(Page page,String DocumentID,String projName,String position,String companyName,String status,String startDate,String overDate,String userId,List<Object> districtList) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select distinct t4.document_id as documentid , ");
			sb.append(" start_unit.start_id as startid , ");
			sb.append(" start_unit.start_code as startcode , ");
			sb.append(" to_date(start_unit.start_date,'yyyy-mm-dd') as startdate , ");
			sb.append(" project.projectname as projectname , ");
			sb.append(" presell.presell_id as presellid , ");
			sb.append(" presell.presell_desc as presellcode , ");
			sb.append(" enterprisequalify.name as companyname , ");
			sb.append(" project.projectadr as projectaddr , ");
			sb.append(" to_char(to_date(t4.credate,'yyyy-mm-dd'),'yyyy-mm-dd') as noticedate , ");
			sb.append(" t4.agent as agent , ");
			sb.append(" t4.content as content , ");
			sb.append(" t4.agent_phone as agentphone ");
			sb.append(" from document_history t2,document t4,start_unit,start_comp, presell,company_project, ");
			sb.append(" enterprisequalify,project,( ");
			sb.append(" select t0.document_id,max(t0.id) as maxid from  document_history t0 group by t0.document_id ");
			sb.append(" ) t1, userinfo t3,platform.sm_user_t t5 ");
			sb.append(" where t2.document_id=t4.document_id and  start_unit.document_id=t4.document_id ");
			sb.append(" and start_comp.start_id=start_unit.start_id and start_unit.presell_id=presell.presell_id ");
			sb.append(" and company_project.project_id=presell.project_id and enterprisequalify.comp_id=company_project.comp_id ");
			sb.append(" and project.project_id=company_project.project_id and t2.document_id=t1.document_id ");
			sb.append(" and t2.document_id=t4.document_id and t2.userid = t5.userid(+) ");
			sb.append(" and t2.olduserid = t3.userid(+) ");
			if(DocumentID!=null && !"".equals(DocumentID)){
				sb.append(" and t4.document_id=? ");
				params.add(Long.parseLong(DocumentID));
			}if(projName!=null && !"".equals(projName)){
				sb.append(" and t4.project_name like ? ");
				params.add("%" +projName+ "%");
			}if(companyName!=null && !"".equals(companyName)){
				sb.append(" and t4.company_name like ? ");
				params.add("%" + companyName + "%");
			}if(position!=null && !"".equals(position)){
				sb.append(" and t4.position like ? ");
				params.add(position);
			}if(status!=null && !"".equals(status)){
				if(Integer.parseInt(status)==0 || Integer.parseInt(status)==1){
					sb.append(" and t4.status=? ");
					params.add(Integer.parseInt(status));
				}
			}if(startDate != null && !"".equals(startDate)){
				sb.append("   and t4.credate >=? ");
				params.add(startDate.replaceAll("-", ""));
			}
			if(overDate != null && !"".equals(overDate)){
				sb.append("   and t4.credate <=? ");
				params.add(overDate.replaceAll("-", ""));
			}if(userId != null && !"".equals(userId)){
				if("1".equals(userId.substring(userId.length() - 1))){
					sb.append(" and t2.userid=? ");
					params.add(userId.substring(0, userId.length() - 1));
				}if("2".equals(userId.substring(userId.length() - 1))){
					sb.append(" and t2.olduserid=? ");
					params.add(userId.substring(0, userId.length() - 1));
				}
			}if(districtList != null){
				if( districtList.size() > 1){
					sb.append("  and t4.districtid in (0,2,3,5,13) ");
				}else{
					for(int i = 0; i < districtList.size(); i++){
						sb.append(" and t4.districtid in (districtlist.get(0)) ");
					}
				}
			}
			
			sb.append(" order by t4.document_id desc ");
			
			List list = DataBaseUtil.select(sb.toString(), params, DocumentVO.class, page, conn);
			return list;
			}catch (Exception e){
				logger.error(e);
				throw e;
			}finally{
	
			}
		}

	/**
	 * 功能描述：流转履历
	 * @return
	 * @throws Exception
	 */
	public List<DocumentHistoryVO> docRecord(String documentId) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" select nvl(b.displayname,c.username) as name,a.credate,a.cretime ");
			sb.append(" from document_history a,platform.sm_user_t b,userinfo c ");
			sb.append(" where a.document_id=? ");
			sb.append(" and a.userid = b.userid(+) and a.olduserid=c.userid(+) ");
			sb.append(" order by a.upddate desc,a.updtime desc ");
			params.add(documentId);
			List list = DataBaseUtil.select(sb.toString(), params, DocumentHistoryVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}
}

