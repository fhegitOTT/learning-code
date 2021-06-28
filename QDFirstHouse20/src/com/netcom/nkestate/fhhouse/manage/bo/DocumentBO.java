package com.netcom.nkestate.fhhouse.manage.bo;

import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.manage.dao.DocumentDAO;
import com.netcom.nkestate.fhhouse.manage.vo.DocumentHistoryVO;
import com.netcom.nkestate.fhhouse.manage.vo.DocumentVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.system.vo.SmUserVO;
import com.netcom.nkestate.system.vo.UserinfoVO;


public class DocumentBO extends MiniBO {

	static Logger logger = Logger.getLogger(DocumentBO.class.getName());

	private DocumentDAO documentDAO = new DocumentDAO();

	/**
	 * 功能描述：查询收件信息列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<DocumentVO> findBulletins(Page page,String DocumentID,String projName,String position,String companyName,String status,String startDate,String overDate,String userId,List<Object> districtList) throws Exception {
		try{
			openDAO(documentDAO);
			List<DocumentVO> list = documentDAO.findDocuments(page, DocumentID, projName, position, companyName, status, startDate, overDate, userId, districtList);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(documentDAO);
		}

	}

	/**
	 * 功能描述：流转状态userID和username列表
	 * @return
	 * @throws Exception
	 */
	public List<SmUserVO> findSmUsers() throws Exception {
		try{
			openDAO(documentDAO);
			List<SmUserVO> list = documentDAO.findSmUsers();
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(documentDAO);
		}

	}

	/**
	 * 功能描述：流转状态olduserID和username列表
	 * @return
	 * @throws Exception
	 */
	public List<UserinfoVO> findUseinfos() throws Exception {
		try{
			openDAO(documentDAO);
			List<UserinfoVO> list = documentDAO.findUseinfos();
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(documentDAO);
		}

	}

	/**
	 * 功能描述：收件和恢复
	 * @param documentId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public int docReturn(long documentId,int status) throws Exception {
		try{
			openDAO(documentDAO);
			int aline = documentDAO.docReturn(documentId, status);
			return aline;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(documentDAO);
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
			openDAO(documentDAO);
			List<DocumentVO> list = documentDAO.findDocResultList(page, DocumentID, projName, position, companyName, status, startDate, overDate, userId, districtList);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(documentDAO);
		}
	}

	public List<DocumentHistoryVO> docRecord(String documentId) throws Exception {
		try{
			openDAO(documentDAO);
			List<DocumentHistoryVO> list = documentDAO.docRecord(documentId);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(documentDAO);
		}
	}

	
}
