package com.netcom.nkestate.system.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.common.Constant;
import com.netcom.nkestate.common.StringStamper;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.FileUtil;
import com.netcom.nkestate.system.bo.AttachmentBO;
import com.netcom.nkestate.system.vo.AttachmentFileVO;
import com.netcom.nkestate.system.vo.AttachmentVO;
import com.netcom.nkestate.system.vo.SmUserVO;

@Controller
@RequestMapping(value = "/attachment")
public class AttachmentAction extends BaseAction {

	static Logger logger = Logger.getLogger(AttachmentAction.class.getName());
	private AttachmentBO attachmentBO = new AttachmentBO();
   
	@RequestMapping(value = "/testUpLoad")
	public String testUpLoad(String objectName,String id,HttpServletRequest request) {
		return "attachment/TestUpload";
	}

	/**
	 * 功能描述：跳转到新增附件页面
	 * @param objectName
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/forward")
	public String forward(String objectName,String businessID,String businessType,String url,HttpServletRequest request) {
		request.setAttribute("objectName", objectName);
		request.setAttribute("businessID", businessID);
		request.setAttribute("businessType", businessType);
		return url;
	}

	/**
	 * 功能描述：跳转到附件查看页面
	 * @param objectName
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/viewForward")
	public String viewForward(String objectName,String businessID,String businessType,HttpServletRequest request) {
		request.setAttribute("objectName", objectName);
		request.setAttribute("businessID", businessID);
		request.setAttribute("businessType", businessType);
		return "/company/attachment/AttachmentListView";
    }

	/**
	 * 功能描述：附件列表
	 * @param objectName
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/attachments1")
	@ResponseBody
	public String attachments1(long objectID,int objectType) throws Exception {
		AttachmentBO attachmentBO = new AttachmentBO();
		List<AttachmentVO> list = attachmentBO.getAttachments(objectID, objectType);
		String htmlView = setAttachmentTable(list);
		return htmlView;

	
	}

	@RequestMapping(value = "/attachments")
	public String attachments(Model model,long objectID,int objectType) throws Exception {
		AttachmentBO attachmentBO = new AttachmentBO();
		List<AttachmentVO> list = attachmentBO.getAttachments(objectID, objectType);
		String htmlView = setAttachmentTable(list);
		model.addAttribute("objectID", objectID);
		model.addAttribute("objectType", objectType);
		model.addAttribute("htmlView", htmlView);
		return "/common/attachment/AttachmentList";

	}

	/**
	 * 功能描述：附件文件列表
	 * @param objectName
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/attachmentFiles1")
	@ResponseBody
	public String attachmentFiles1(long attachmentID) throws Exception {
		AttachmentBO attachmentBO = new AttachmentBO();
		List<AttachmentFileVO> list = attachmentBO.getAttachmentFiles(attachmentID);
		String htmlView = setAttachmentFileTable(list);

		return htmlView;
	}

	@RequestMapping(value = "/attachmentFiles")
	public String attachmentFiles(Model model,long attachmentID) throws Exception {
		AttachmentBO attachmentBO = new AttachmentBO();
		List<AttachmentFileVO> list = attachmentBO.getAttachmentFiles(attachmentID);
		String htmlView = setAttachmentFileTable(list);
		model.addAttribute("attachmentID", attachmentID);
		model.addAttribute("htmlView", htmlView);
		return "/common/attachment/AttachmentFileList";
	}


	//设置附件列表样式
	private String setAttachmentTable(List list) throws Exception {
		Page page = new Page();
		String htmlView;
		/** 设置table列名 */
		TableProperty tableProperty = new TableProperty();
		String controlType = "radio";
		String controlName = "radio";//控件名称自定义
		String controlvalueField = "AttachmentID";//设置一个参数
		String controlStyle = "width='7%';";//定义样式style内容，根据需求自己定义
		tableProperty.addSelectControl(controlType, controlName, controlvalueField, controlStyle);

		List<String> trEventParams = new ArrayList<String>();
		trEventParams.add("AttachmentID");
		String eventType = "ondblclick";
		String eventName = "db";
		tableProperty.setTrEventType(eventType);
		tableProperty.setTrEventName(eventName);
		tableProperty.setTrEventParams(trEventParams);

		tableProperty.addColumn("附件编号", "AttachmentID", "align=center");
		tableProperty.addColumn("业务对象编号", "ObjectID", "align=center");
		tableProperty.addColumn("业务对象类型", "ObjectType", "align=center");
		tableProperty.addColumn("文件名称", "FileName", "align=center");

		htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
		return htmlView;
	}

	//设置附件文件列表样式
	private String setAttachmentFileTable(List list) throws Exception {
		Page page = new Page();
		String htmlView;
		/** 设置table列名 */
		TableProperty tableProperty = new TableProperty();
		String controlType = "radio";
		String controlName = "radio";//控件名称自定义
		String controlvalueField = "AttachmentFileID";//设置一个参数
		String controlStyle = "width='7%';";//定义样式style内容，根据需求自己定义
		tableProperty.addSelectControl(controlType, controlName, controlvalueField, controlStyle);

		List<String> trEventParams = new ArrayList<String>();
		trEventParams.add("AttachmentFileID");
		String eventType = "ondblclick";
		String eventName = "db";
		tableProperty.setTrEventType(eventType);
		tableProperty.setTrEventName(eventName);
		tableProperty.setTrEventParams(trEventParams);

		tableProperty.addColumn("附件文件编号", "AttachmentFileID", "align=center");
		tableProperty.addColumn("附件编号", "AttachmentID", "align=center");
		tableProperty.addColumn("文件类型", "FileType", "align=center");
		tableProperty.addColumn("文件大小", "FileSize", "align=center");
		tableProperty.addColumn("上传人", "UserID", "align=center");
		tableProperty.addColumn("上传时间", "UploadTime", "align=center");

		htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
		return htmlView;
	}

	/**
	 * 功能描述：查看附件清单
	 * @param objectID
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	public List<AttachmentVO> getAttachments(long objectID,int objectType) throws Exception {
		AttachmentBO attachmentBO = new AttachmentBO();

			List<AttachmentVO> list = attachmentBO.getAttachments(objectID, objectType);

			//TODO

		return list;
	}

	/**
	 * 功能描述：查看附件的文件清单
	 * @param objectID
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	public List<AttachmentFileVO> getAttachmentFiles(long attachmentID) throws Exception {
		AttachmentBO attachmentBO = new AttachmentBO();

			List<AttachmentFileVO> list = attachmentBO.getAttachmentFiles(attachmentID);

		//TODO		
		return list;
	}

	/**
	 * 功能描述：查看文件图片
	 * @param fileID
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/showPicture")
	public void showPicture(HttpServletResponse response,HttpServletRequest request,long attchmentFileID) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		try{
			AttachmentBO attachmentBO = new AttachmentBO();

			OutputStream os = response.getOutputStream();
			AttachmentFileVO fileVO = attachmentBO.findAttachmentFile(attchmentFileID);
			byte[] btImg = null;
			if(fileVO != null){
				String fileType = fileVO.getFileType();
				btImg = attachmentBO.getFileContent(attchmentFileID);
			}
			os.write(btImg);
			os.flush();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 功能描述：删除文件图片
	 * @param objectName
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String , Object> deleteFile(HttpServletResponse response,HttpServletRequest request,String attachmentFileID) {
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			//		long returnID = -1;
			long returnID = -1;
			SmUserVO user = (SmUserVO) request.getSession().getAttribute("user");
			long AttachmentFileID = Long.parseLong(attachmentFileID);
			returnID = attachmentBO.delete(AttachmentFileID, user);
			//	returnID = attachmentBO.delete(Long.parseLong(attachmentFileID), user);
			if(returnID > 0){
				map.put("mes", "删除成功。");
			}else{
				map.put("mes", "删除失败。");
			}
			map.put("flag", returnID);
			return map;
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			map.put("mes", "异常报错。");
			map.put("flag", -1);
			return map;
		}
	}

	/**
	 * 功能描述：TODO
	 * @param objectName
	 * （文件类型）
	 * @param id
	 * @param req
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	public String uploadFile(HttpServletRequest request,HttpServletResponse response,int businessType,long businessID,int attachmentCode,String attachmentName)
			throws IOException {

		//		String attachmentName = request.getParameter("attachmentName");

		Map<String , Object> map = new HashMap<String , Object>();
		try{
			AttachmentBO attachmentBO = new AttachmentBO();
			SmUserVO user = (SmUserVO) request.getSession().getAttribute("user");
			long userID = user != null ? user.getUserId() : 0;
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("file");
			String fileName = file.getOriginalFilename();
			if(fileName.indexOf(".jpg") >= 0 || fileName.indexOf(".jepg") >= 0 || fileName.indexOf(".bmp") >= 0 || fileName.indexOf(".png") >= 0 || fileName.indexOf(".icon") >= 0 || fileName.indexOf(".pdf") >= 0){
				List<AttachmentFileVO> list = attachmentBO.upload(request, businessType, businessID, attachmentCode, attachmentName, userID);
				map.put("fileID", list.get(0).getAttachmentID());


			}else{
				request.setAttribute("errorMessage", "文件类型非图片");
			}
			request.setAttribute("businessID", businessID);
			request.setAttribute("businessType", businessType);
		}catch (Exception e){
			e.printStackTrace();
			map.put("fileID", "0");
			return "error";
		}
		return "company/attachment/AttachmentUpload";
	}

	/**
	 * 功能描述：图片列表
	 * @param objectName
	 * @param id
	 * @param req
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/showFileList")
	public String showFileList(String objectName,String id,HttpServletRequest req,HttpServletResponse response) {
		try{
			List<AttachmentVO> list = attachmentBO.getAttachments(Long.parseLong(id), Integer.parseInt(objectName));
			req.setAttribute("files", list);
			return "attachment/AttachmentList";
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return "error";
		}
	}

	/**
	 * 功能描述：下载文件
	 * @param fileID
	 * @return
	 */
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(String attachmentFileID) {
		// 用来封装响应头信息
		HttpHeaders responseHeaders = new HttpHeaders();
		// 下载的附件的类型
		responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		try{
			String decodeFileID = StringStamper.decode(attachmentFileID);
			long AttachmentFileID = Long.parseLong(decodeFileID);
			AttachmentFileVO fileVO = attachmentBO.findAttachmentFile(AttachmentFileID);
			byte[] content = null;
			if(fileVO != null){
				String fileType = fileVO.getFileType();
				content = attachmentBO.getFileContent(AttachmentFileID);
			}

			// 下载的附件的名称

			responseHeaders.setContentDispositionFormData("attachment", fileVO.getOrginalFileName());


			/**
			 * arg1:需要响应到客户端的数据 arg2:设置本次请求的响应头 arg3:响应到客户端的状态码
			 ***/
			return new ResponseEntity<byte[]>(content, responseHeaders, HttpStatus.OK);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 功能描述：查看文件图片
	 * @param fileID
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/showPic")
	public void showPicture(String fileID,HttpServletResponse response,HttpServletRequest request) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		try{
			OutputStream os = response.getOutputStream();
			AttachmentBO attachmentBO = new AttachmentBO();
			String docodeFileID = StringStamper.decode(fileID);
			AttachmentFileVO fileVO = attachmentBO.findAttachmentFile(Long.parseLong(docodeFileID));
			byte[] btImg = null;
			if(fileVO != null){
				AttachmentVO attachment = attachmentBO.findAttachment(fileVO.getAttachmentID());
				String filepath = "";
				if(Constant.isImageFile(fileVO.getFileType())){
					filepath = Constant.AttachmentURL + "/" + attachment.getRelativeURL() + "/" + fileVO.getAttachmentFileID() + "." + fileVO.getFileType();
				}else{
					filepath = Constant.AttachmentURL + "/" + fileVO.getFileType().toLowerCase() + ".jpg";
				}
				if(!new File(filepath).exists()){
					filepath = Constant.AttachmentURL + "/no.jpg";;
				}

				btImg = FileUtil.getBytes(filepath);
			}
			os.write(btImg);
			os.flush();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
