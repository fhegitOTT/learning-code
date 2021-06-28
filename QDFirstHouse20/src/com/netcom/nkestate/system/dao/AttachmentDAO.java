/**
 * <p>AttachmentDAO.java </p>
 *
 * <p>系统名称: 海神管理系统<p>  
 * <p>功能描述: 附件管理DAO<p>
 *
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2016-12-21<p>
 * 
 */ 
package com.netcom.nkestate.system.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.framework.dao.DataBaseUtil;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.system.vo.AttachmentFileVO;
import com.netcom.nkestate.system.vo.AttachmentVO;

@Service
public class AttachmentDAO extends MiniDAO {
	static Logger logger = Logger.getLogger(AttachmentDAO.class.getName());

	/**
	 * 功能描述：根据对象编号、对象类型得到全部的附件清单
	 * @param objectID
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	public List<AttachmentVO> findAttachments(long objectID,int objectType) throws Exception {
		try{
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("objectID", "=", objectID));
			params.add(new MetaFilter("objectType", "=", objectType));
			List list = DataBaseUtil.findByAttibutes(AttachmentVO.class, params, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}
	}

	/**
	 * 功能描述：检查是否已有相同附件存在
	 * @param objectID
	 * @param objectType
	 * @param attachmentName
	 * @return
	 * @throws Exception
	 */
	public AttachmentVO findAttachment(long objectID,int objectType,String attachmentName) throws Exception {
		try{
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("objectID", "=", objectID));
			params.add(new MetaFilter("objectType", "=", objectType));
			params.add(new MetaFilter("fileName", "=", attachmentName));
			List list = DataBaseUtil.findByAttibutes(AttachmentVO.class, params, conn);
			if(list.size() > 0)
				return (AttachmentVO) list.get(0);
			return null;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}
	}

	/**
	 * 功能描述：根据附件编号得到文件清单
	 * @param attachmentID
	 * @return
	 * @throws Exception
	 */
	public List<AttachmentFileVO> findAttachmentFiles(long attachmentID) throws Exception {
		try{
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("attachmentID", "=", attachmentID));
			List list = DataBaseUtil.findByAttibutes(AttachmentFileVO.class, params, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}
	}

	/**
	 * 功能描述：根据文件编号附件信息
	 * @param fileID
	 * @return
	 * @throws Exception
	 */
	public AttachmentFileVO findAttachmentFile(long fileID) throws Exception {
		try{
			AttachmentFileVO vo = (AttachmentFileVO) DataBaseUtil.find(AttachmentFileVO.class, fileID, conn);
			return vo;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}
	}

}
