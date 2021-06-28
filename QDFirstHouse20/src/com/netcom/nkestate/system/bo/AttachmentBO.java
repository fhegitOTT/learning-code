/**
 * <p>AttachmentBO.java </p>
 *
 * <p>系统名称: 海神管理系统<p>  
 * <p>功能描述: 附件管理组件<p>
 *
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2016-12-21<p>
 * 
 */ 
package com.netcom.nkestate.system.bo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.netcom.nkestate.common.Constant;
import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.dao.AttachmentDAO;
import com.netcom.nkestate.system.vo.AttachmentFileVO;
import com.netcom.nkestate.system.vo.AttachmentVO;
import com.netcom.nkestate.system.vo.SmUserVO;

@Service
public class AttachmentBO extends MiniBO {
	static Logger logger = Logger.getLogger(AttachmentBO.class.getName());

	private AttachmentDAO attachmentDAO = new AttachmentDAO();

	/**
	 * 功能描述：保存上传文件
	 * @param request
	 * @param ObjectType
	 * 对象分类
	 * @param objectID
	 * 对象编号
	 * @param attachmentCode
	 * 文件代码，可以为0
	 * @param attachmentName
	 * 文件名称，如产权证、抵押证
	 * @param user
	 * 操作用户
	 * @return
	 * @throws Exception
	 */
	public List<AttachmentFileVO> upload(HttpServletRequest request,int objectType,long objectID,int attachmentCode,String attachmentName,long userID) throws Exception {
		List<AttachmentFileVO> list = new ArrayList<AttachmentFileVO>();
		try{
			openDAO(attachmentDAO);

			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			if(!multipartResolver.isMultipart(request))
				return list;
						
			//检查是否已有相同的附件存在
			String relativePath = null;
			AttachmentVO attachmentVO = attachmentDAO.findAttachment(objectID, objectType, attachmentName);
			if(attachmentVO!=null) {
				relativePath = attachmentVO.getRelativeURL();
			} else {
				//relativePath = "/" + getSubDirectory(objectType) + "/" + DateUtil.format(DateUtil.getSysDate(), "yyyy/MM/dd");
				attachmentVO = new AttachmentVO();
				attachmentVO.setObjectID(objectID);
				attachmentVO.setObjectType(objectType);
				attachmentVO.setFileCode(attachmentCode);
				attachmentVO.setFileName(attachmentName);
				attachmentVO.setRelativeURL(relativePath);
				attachmentDAO.add(attachmentVO);
			}

			long attachmentID = attachmentVO.getAttachmentID();
			String filePath = Constant.AttachmentURL + relativePath;

			int fileCount = 0;

			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()){
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if(file != null){
					fileCount++;
					String fileName = file.getOriginalFilename();
					String fileType = "";
					if(fileName.lastIndexOf(".") > 0)
						fileType = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					long fileSize = file.getBytes().length;

					//保存数据库记录
					AttachmentFileVO fileVO = new AttachmentFileVO();
					fileVO.setAttachmentID(attachmentID);
					fileVO.setFileType(fileType);
					fileVO.setFileSize(fileSize);
					fileVO.setOrginalFileName(fileName);
					fileVO.setUploadTime(DateUtil.getSysDate());
					fileVO.setUserID(userID);

					attachmentDAO.add(fileVO);

					String newFileName = fileVO.getAttachmentFileID() + "." + fileType;
					list.add(fileVO);
					File targetFile = new File(filePath, newFileName);
					if(!targetFile.exists()){
						targetFile.mkdirs();
					}
					file.transferTo(targetFile);
				}
			}

			attachmentVO.setFileCount(attachmentVO.getFileCount() + fileCount);
			attachmentDAO.update(attachmentVO);
		}catch (IOException e){
			e.printStackTrace();
			logger.error(e);
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e);
			//closeDAO(attachmentDAO);
			throw e;
		}
		return list;
	}


	/**
	 * 功能描述：获取上传文件的类型，多个文件用,分隔，如：jpg,doc
	 * @param request
	 * @return
	 */
	public String getUploadFileType(HttpServletRequest request) throws Exception {
		String fileTypes = "";
		try{
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			if(!multipartResolver.isMultipart(request))
				return null;

			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator it = multiRequest.getFileNames();
			while (it.hasNext()){
				MultipartFile file = multiRequest.getFile(it.next().toString());
				if(file != null){
					String fileName = file.getOriginalFilename();
					String fileType = "";
					if(fileName.lastIndexOf(".") > 0){
						fileType = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
						if(fileTypes.indexOf(fileType) < 0)
							fileTypes += fileType + ",";
					}
				}
			}
			if(fileTypes.endsWith(","))
				fileTypes = fileTypes.substring(0, fileTypes.length() - 1);
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e);
			throw e;
		}
		return fileTypes.toLowerCase();
	}


	/**
	 * 功能描述：根据对象编号、对象类型得到附件清单
	 * @param objectID
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	public List<AttachmentVO> getAttachments(long objectID,int objectType) throws Exception {
		try{
			openDAO(attachmentDAO);
			List<AttachmentVO> list = attachmentDAO.findAttachments(objectID, objectType);
			for(int i = 0; i < list.size(); i++){
				AttachmentVO vo = list.get(i);
				List<AttachmentFileVO> files = attachmentDAO.findAttachmentFiles(vo.getAttachmentID());
				vo.setAttribute("files", files);
			}
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(attachmentDAO);
		}
	}

	/**
	 * 功能描述：根据附件编号查看文件清单
	 * @param attachmentID
	 * @return
	 * @throws Exception
	 */
	public List<AttachmentFileVO> getAttachmentFiles(long attachmentID) throws Exception {
		try{
			openDAO(attachmentDAO);
			List list = attachmentDAO.findAttachmentFiles(attachmentID);
			return list;
		}catch (Exception e){
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(attachmentDAO);
		}
	}

	/**
	 * 功能描述：删除一个文件
	 * @param fileID
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int delete(long attachmentFileID,SmUserVO user) throws Exception {
		int result = 0;
		try{
			openDAO(attachmentDAO);
			attachmentDAO.setTransaction();

			AttachmentFileVO fileVO = (AttachmentFileVO) attachmentDAO.find(AttachmentFileVO.class, attachmentFileID);
			if(fileVO == null)
				return 0;

			AttachmentVO attachmentVO = (AttachmentVO) attachmentDAO.find(AttachmentVO.class, fileVO.getAttachmentID());
			String relativePath = attachmentVO.getRelativeURL(); //相对路径
			String filePath = Constant.AttachmentURL + relativePath + "/" + attachmentFileID + "." + fileVO.getFileType();

			//删除文件
			File file = new File(filePath);
			if(file.exists())
				file.delete();

			result = attachmentDAO.delete(fileVO);
			int fileCount = attachmentVO.getFileCount();
			if(fileCount - 1 == 0){
				attachmentDAO.delete(attachmentVO);
			}else{
				attachmentVO.setFileCount(fileCount - 1);
				attachmentDAO.update(attachmentVO);
			}

			attachmentDAO.commit();

		}catch (Exception e){
			attachmentDAO.rollback();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(attachmentDAO);
		}
		return result;
	}

	/**
	 * 功能描述：得到文件内容
	 * @param fileID
	 * @return
	 * @throws Exception
	 */
	public byte[] getFileContent(long attachmentFileID) throws Exception {
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try{
			openDAO(attachmentDAO);

			AttachmentFileVO fileVO = (AttachmentFileVO) this.find(AttachmentFileVO.class, attachmentFileID);
			if(fileVO == null)
				return null;

			AttachmentVO attachmentVO = (AttachmentVO) attachmentDAO.find(AttachmentVO.class, fileVO.getAttachmentID());
			String relativePath = attachmentVO.getRelativeURL(); //相对路径
			String filePath = Constant.AttachmentURL + relativePath + "/" + attachmentFileID + "." + fileVO.getFileType();

			//文件
			File file = new File(filePath);
			if(!file.exists())
				return null;

			in = new FileInputStream(file);
			out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int n;
			while ( (n = in.read(buffer)) != -1){
				out.write(buffer, 0, n);
			}
			byte[] b = out.toByteArray();

			return b;
		}catch (Exception e){
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(attachmentDAO);
			try{
				in.close();
			}catch (Exception e){

			}
			try{
				out.close();
			}catch (Exception e){

			}
		}
	}

	/**
	 * 功能描述：得到图标缩略图内容
	 * @param fileID
	 * @return
	 * @throws Exception
	 */
	public byte[] getImageSmallContent(long attachmentFileID) throws Exception {
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try{
			AttachmentFileVO fileVO = (AttachmentFileVO) this.find(AttachmentFileVO.class, attachmentFileID);
			if(fileVO == null)
				return null;

			AttachmentVO attachmentVO = (AttachmentVO) attachmentDAO.find(AttachmentVO.class, fileVO.getAttachmentID());
			String relativePath = attachmentVO.getRelativeURL(); //相对路径
			String filePath = Constant.AttachmentURL + relativePath + "/" + attachmentFileID + "." + fileVO.getFileType();
			String filetype = fileVO.getFileType();

			File file = new File(filePath);
			if(!file.exists())
				return null;

			in = new FileInputStream(file);
			Image src = javax.imageio.ImageIO.read(file); //构造Image对象
			float tagsize = 100;
			int old_w = src.getWidth(null); //得到源图宽
			int old_h = src.getHeight(null);
			int new_w = 0;
			int new_h = 0; //得到源图长
			float tempdouble;
			if(old_w > old_h){
				tempdouble = old_w / tagsize;
			}else{
				tempdouble = old_h / tagsize;
			}
			new_w = Math.round(old_w / tempdouble);
			new_h = Math.round(old_h / tempdouble);//计算新图长宽
			BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(src, 0, 0, new_w, new_h, null); //绘制缩小后的图

		    out = new ByteArrayOutputStream();
			boolean flag = ImageIO.write(tag, filetype, out);
			if(!flag)
				return null;
			byte[] b = out.toByteArray();
			return b;
		}catch (Exception e){
			logger.error(e.getMessage());
			throw e;
		}finally{
			try{
				in.close();
			}catch (Exception e){

			}
			try{
				out.close();
			}catch (Exception e){

			}
		}
	}


	/**
	 * 功能描述：根据附件编号，查找附件信息
	 * @param fileID
	 * @return
	 * @throws Exception
	 */
	public AttachmentVO findAttachment(long attachmentID) throws Exception {
		try{
			openDAO(attachmentDAO);
			AttachmentVO vo = new AttachmentVO();
			vo.setAttachmentID(attachmentID);
			vo = (AttachmentVO) attachmentDAO.find(vo);
			return vo;
		}catch (Exception e){
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(attachmentDAO);
		}
	}

	/**
	 * 功能描述：根据附件文件编号，查找附件信息
	 * @param fileID
	 * @return
	 * @throws Exception
	 */
	public AttachmentFileVO findAttachmentFile(long attachmentFileID) throws Exception {
		try{
			openDAO(attachmentDAO);
			AttachmentFileVO vo = new AttachmentFileVO();
			vo.setAttachmentFileID(attachmentFileID);
			//AttachmentFileVO fileVO = (AttachmentFileVO) attachmentDAO.find(AttachmentFileVO.class, attachmentFileID);
			vo = (AttachmentFileVO) attachmentDAO.find(vo);
			return vo;
		}catch (Exception e){
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(attachmentDAO);
		}
	}


}
