/**
 * <p>PermitAction.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 许可证Action <p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2018-12-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.permit.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.common.CharSet;
import com.netcom.nkestate.common.Constant;
import com.netcom.nkestate.common.ListUtil;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.interfaces.action.RealestateFinder;
import com.netcom.nkestate.fhhouse.interfaces.bo.RealestateBO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHBuildingVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHLotVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.RHouseRightView;
import com.netcom.nkestate.fhhouse.interfaces.vo.RLandRightView;
import com.netcom.nkestate.fhhouse.interfaces.vo.TempRHouseRightView;
import com.netcom.nkestate.fhhouse.interfaces.vo.TempRLandRightView;
import com.netcom.nkestate.fhhouse.permit.bo.PermitBO;
import com.netcom.nkestate.fhhouse.permit.vo.ApplyFileVO;
import com.netcom.nkestate.fhhouse.permit.vo.ApplyVO;
import com.netcom.nkestate.fhhouse.permit.vo.CfgFileStandardVO;
import com.netcom.nkestate.fhhouse.permit.vo.EmptyVO;
import com.netcom.nkestate.fhhouse.permit.vo.HouseSaleLogVO;
import com.netcom.nkestate.fhhouse.permit.vo.ImageFileVO;
import com.netcom.nkestate.fhhouse.permit.vo.LocationVO;
import com.netcom.nkestate.fhhouse.permit.vo.PermitVO;
import com.netcom.nkestate.fhhouse.permit.vo.RHousePermitVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.framework.IModel;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaField;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.util.Formatter;
import com.netcom.nkestate.framework.util.StringUtil;
import com.netcom.nkestate.system.bo.SystemBO;
import com.netcom.nkestate.system.vo.SmUserVO;

/**
 * 预售许可模块Action
 */
@Controller
@RequestMapping(value = "/inner/permitmanage")
public class PermitAction extends BaseAction {
	
	
	/**
	 * 功能描述：预售许可查询界面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoPermitQueryFrame")
	public String gotoPermitQueryFrame(HttpServletRequest request,HttpSession session) {
		try{
			String cmd = request.getParameter("cmd");
			request.setAttribute("cmd", cmd);
			return "/fhhouse/permit/PermitQueryFrame";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：预售许可打印界面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoPermitPrintQueryFrame")
	public String gotoPermitPrintQueryFrame(HttpServletRequest request,HttpSession session) {
		try{
			return "/fhhouse/permit/PermitPrintQueryFrame";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：楼盘查询界面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoHouseQuery")
	public String gotoHouseQuery(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO userVO = (SmUserVO) session.getAttribute("LgUser");
			request.setAttribute("districtID", userVO.getRegionId());
			return "/fhhouse/permit/HouseQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：显示照片界面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/ShowImage")
	public String ShowImage(HttpServletRequest request,HttpSession session) {
		try{
			//transactionID="+transactionID+"&districtID="+districtID+"&imageID=" +imageID + "&userID="+userID+"&secretKey="+secretKey;
			String transactionID = request.getParameter("transactionID");
//			String districtID = request.getParameter("districtID");
			String imageID = request.getParameter("imageID");
//			String userID = request.getParameter("userID");
//			String secretKey = request.getParameter("secretKey");
			PermitBO permitBO = new PermitBO();
			ApplyVO apply = new ApplyVO();
			apply.setTransactionID(Long.parseLong(transactionID));
			apply = (ApplyVO) permitBO.find(apply);
			
			request.setAttribute("districtID", apply.getDistrictID());
			
			request.setAttribute("transactionID", transactionID);
//			request.setAttribute("districtID", districtID);
			request.setAttribute("imageID", imageID);
//			request.setAttribute("userID", userID);
//			request.setAttribute("secretKey", secretKey);
			return "/fhhouse/permit/ShowImage";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：显示照片界面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/ShowImageDetail")
	public String ShowImageDetail(HttpServletRequest request,HttpSession session) {
		try{
			//transactionID="+transactionID+"&districtID="+districtID+"&imageID=" +imageID + "&userID="+userID+"&secretKey="+secretKey;
			String transactionID = request.getParameter("transactionID");
			String districtID = request.getParameter("districtID");
			String imageID = request.getParameter("imageID");
			String userID = request.getParameter("userID");
			String secretKey = request.getParameter("secretKey");
			
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			PermitBO permitBO = new PermitBO();
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			//获取所有的imageID
			String imageIDs = "";
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "in", userDistrictList));
			params.add(new MetaFilter("transactionID", "=", transactionID));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("fileID", MetaOrder.ASC));
			orders.add(new MetaOrder("diskFileName", MetaOrder.ASC));
			List<ImageFileVO> list = permitBO.search(ImageFileVO.class, params, orders, null);
			for (ImageFileVO file : list) {
				imageIDs += file.getImageID() + ",";
			}
			if(!"".equals(imageIDs) && imageIDs.length()>0) {
				request.setAttribute("imageIDs", imageIDs.substring(0, imageIDs.lastIndexOf(",")));
			}
			
			request.setAttribute("transactionID", transactionID);
			request.setAttribute("districtID", districtID);
			request.setAttribute("imageID", imageID);
			request.setAttribute("userID", userID);
			request.setAttribute("secretKey", secretKey);
			return "/fhhouse/permit/ShowImageDetail";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：预售许可审核界面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoPermitAuditQueryFrame")
	public String gotoPermitAuditQueryFrame(HttpServletRequest request,HttpSession session) {
		try{
			return "/fhhouse/permit/PermitAuditQueryFrame";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：预售许可申请审核列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoPermitAuditQueryList")
	public String gotoPermitAuditList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String companyName = request.getParameter("companyName");
			String permitNo = request.getParameter("permitNo");
			request.setAttribute("companyName", companyName);
			request.setAttribute("permitNo", permitNo);
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			
			PermitBO permitBO = new PermitBO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "in", userDistrictList));
			params.add(new MetaFilter("status", "=", FHConstant.PERMIT_STATUS_AUDIT));
			if(companyName != null && !"".equals(companyName)){
				params.add(new MetaFilter("companyName", "like", "%" + companyName + "%"));
			}
			if(permitNo != null && !"".equals(permitNo)){
				params.add(new MetaFilter("permitNo", "like", "%" + permitNo + "%"));
			}
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
//			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
//			orders.add(new MetaOrder("updTime", MetaOrder.DESC));
			

			List<PermitVO> list = permitBO.search(PermitVO.class, params, orders, page);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("permitID");
			linkparam.add("transactionID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addSelectControl("radio", "permitID", linkparam, "");
			tableProperty.addColumn("区市", "districtID_dict_name");
			tableProperty.addColumn("申请编号", "transactionID","doPermitAuditDetail",linkparam);
			tableProperty.addColumn("企业名称", "companyName");
			tableProperty.addColumn("项目名称", "projectName");
			tableProperty.addColumn("预售许可证号", "permitNo");
			tableProperty.addColumn("地址", "location");

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/permit/PermitAuditQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：预售许可列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoPermitQueryList")
	public String gotoPermitQueryList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String companyName = request.getParameter("companyName");
			String permitNo = request.getParameter("permitNo");
			String cmd = request.getParameter("cmd");
			String param_status = request.getParameter("status");
			request.setAttribute("cmd", cmd);
			request.setAttribute("companyName", companyName);
			request.setAttribute("permitNo", permitNo);
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String userDistrictList = this.getUserSqlDistricts(vo.getRegionId());//获取用户操作区县列表
			
			PermitBO permitBO = new PermitBO();
			String status = "";
			List<PermitVO> list = new ArrayList<PermitVO>();
			
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "in", userDistrictList));
			if("print".equals(cmd)){//打印看审核通过的案件
				status = "3";//-1，3，缮证和归档的
				list = permitBO.searchPermitList(companyName,permitNo,vo.getUserId(),status,page,userDistrictList);
			}else if("audit".equals(cmd)){
				status = "2";//2，审核
				//acceptUserID,state,查询当前人的许可证列表信息
				list = permitBO.searchPermitList(companyName,permitNo,vo.getUserId(),status,page,userDistrictList);
			}else if("add".equals(cmd) || "edit".equals(cmd)){
				status = "1";//1，受理
				//acceptUserID,state,查询当前人的许可证列表信息
				list = permitBO.searchPermitList(companyName,permitNo,vo.getUserId(),status,page,userDistrictList);
			}else if("query".equals(cmd)){
				if(param_status != null && !"".equals(param_status)){
					if("10".equals(param_status)){
						status = "";
					}else{
						status = param_status;
					}
				}
				list = permitBO.searchPermitList(companyName,permitNo,vo.getUserId(),status,page,userDistrictList);
			}else if("modify".equals(cmd)){
				status = "-1";//-1归档
				list = permitBO.searchPermitList(companyName,permitNo,vo.getUserId(),status,page,userDistrictList);
			}
			
			/*if(companyName != null && !"".equals(companyName)){
				params.add(new MetaFilter("companyName", "like", "%" + companyName + "%"));
			}
			if(permitNo != null && !"".equals(permitNo)){
				params.add(new MetaFilter("permitNo", "like", "%" + permitNo + "%"));
			}
			List<MetaOrder> orders = new ArrayList<MetaOrder>();*/
			
			
			
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("permitID");
			linkparam.add("transactionID");
			
			List<String> linkparam1 = new ArrayList<String>();
			linkparam1.add("permitID");
			if("print".equals(cmd)){
				linkparam1.add("transactionID");
			}
			String linkName = "";
			if("add".equals(cmd)||"edit".equals(cmd)){//新增界面，点击案件号进入编辑页面，其他页面点击案件号，只能查看案件信息。
				linkName = "doPermitEditHref";
			}else{
				linkName = "doPermitAuditDetail";
			}
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addSelectControl("radio", "permitID", linkparam1, "");
			tableProperty.addColumn("区市", "districtID_dict_name");
			tableProperty.addColumn("申请编号", "transactionID",linkName,linkparam);
			tableProperty.addColumn("企业名称", "companyName");
			tableProperty.addColumn("项目名称", "projectName");
			tableProperty.addColumn("预售许可证号", "permitNo");
			tableProperty.addColumn("地址", "location");
			if("query".equals(cmd)){
				tableProperty.addColumn("状态", "statusName");
			}
			
			
			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
//			String htmlView = HtmlTableUtil.createHtmlTable(tableProperty, list, page);
			
//			System.out.println("htmlView=="+htmlView);
			request.setAttribute("htmlView", htmlView);
			if("print".equals(cmd)){
				return "/fhhouse/permit/PermitPrintQueryList";
			}else if("audit".equals(cmd)){
				return "/fhhouse/permit/PermitAuditQueryList";
			}else if("add".equals(cmd)||"edit".equals(cmd)){
				return "/fhhouse/permit/PermitAddQueryList";
			}else if("query".equals(cmd)){
				return "/fhhouse/permit/PermitQueryList";
			}else if("modify".equals(cmd)){
				return "/fhhouse/permit/PermitEditQueryList";
			}
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
		return null;
	}
	
	/**
	 * 功能描述：图片列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoImageList")
	public String gotoImageList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String fileID = request.getParameter("fileID");
			String transactionID = request.getParameter("transactionID");
			String cmd = request.getParameter("cmd");
			
			request.setAttribute("fileID", fileID);
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			PermitBO permitBO = new PermitBO();
			ApplyVO apply = new ApplyVO();
			apply.setTransactionID(Long.parseLong(transactionID));
			apply = (ApplyVO) permitBO.find(apply);
			request.setAttribute("districtID", apply.getDistrictID());
			request.setAttribute("transactionID", transactionID);
			
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "in", userDistrictList));
			params.add(new MetaFilter("fileID", "=", fileID));
			params.add(new MetaFilter("transactionID", "=", transactionID));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			
			List<ImageFileVO> list = permitBO.search(ImageFileVO.class, params, orders, page);
			
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("imageID");
			linkparam.add("diskfilename");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("文件ID", "fileID");
			tableProperty.addColumn("文件名称", "fileName");
			tableProperty.addColumn("图片名称", "diskfilename","showImageDetail",linkparam);
			tableProperty.addColumn("文件类型", "fileType","showImageDetail",linkparam);
			tableProperty.addColumn("上传时间", "uploadtime", "Date", "yyyy-MM-dd", null);
			
			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			
			request.setAttribute("htmlView", htmlView);
			request.setAttribute("cmd", cmd);
			return "/fhhouse/permit/PermitImageList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：预售许可审核明细
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoPermitDetail")
	public String gotoPermitDetail(HttpServletRequest request,HttpSession session) {
		try{
			String permitID = request.getParameter("permitID");
			String transactionID = request.getParameter("transactionID");
			String cmd = request.getParameter("cmd");
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			
			PermitBO permitBO = new PermitBO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "in", userDistrictList));
			
			if("print".equals(cmd)){//打印看缮证状态的案件
				params.add(new MetaFilter("status", "in", "(3)"));//3
			}else if("audit".equals(cmd)){
				params.add(new MetaFilter("status", "=", FHConstant.PERMIT_STATUS_AUDIT));//2
			}else if("add".equals(cmd)){
				params.add(new MetaFilter("status", "=", FHConstant.PERMIT_STATUS_ACCEPT));//1
			}else if("query".equals(cmd)){
				//params.add(new MetaFilter("status", "=", FHConstant.PERMIT_STATUS_EDIT));//查询不加状态区分
			}else if("modify".equals(cmd)){
				params.add(new MetaFilter("status", "=", FHConstant.PERMIT_STATUS_ARCHIVED));//-1
			}
			
			params.add(new MetaFilter("permitID", "=", permitID));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			
			List<PermitVO> list = permitBO.search(PermitVO.class, params);
			if(list != null && list.size()>0){
				PermitVO permit = list.get(0);
				request.setAttribute("permit", permit);
				request.setAttribute("districtID", permit.getDistrictID());
			}
			
			ApplyVO applyVO = new ApplyVO();
			applyVO = (ApplyVO) permitBO.find(ApplyVO.class, Long.parseLong(transactionID));
			request.setAttribute("apply", applyVO);
			
			/******************照片材料****************************/
			params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "in", userDistrictList));
			params.add(new MetaFilter("transactionID", "=", transactionID));
			orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("fileID", MetaOrder.ASC));
			orders.add(new MetaOrder("diskFileName", MetaOrder.ASC));
			//(ImageFileVO.class, params);
			List<ImageFileVO> fileList = permitBO.search(ImageFileVO.class, params, orders, null);
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("imageID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("文件名称", "fileName");
			tableProperty.addColumn("照片名称", "diskFileName","doOpenImageList",linkparam);
			tableProperty.addColumn("上传日期", "uploadTimeStr");
			String fileHtmlView = HtmlTableUtil.createHtmlTable(tableProperty, fileList);
//			System.out.println("fileHtmlView==="+fileHtmlView);
			request.setAttribute("fileHtmlView", fileHtmlView);
			/******************照片材料****************************/
			
			
			/******************楼盘关联列表****************************/
			//获取房屋关联表数据
			params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "in", userDistrictList));
			params.add(new MetaFilter("permitID", "=", permitID));
			orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("MODIFYDATE", MetaOrder.DESC));
			List<RHousePermitVO> houseList = permitBO.search(RHousePermitVO.class, params);
			/** 设置table列名 */
			tableProperty = new TableProperty();
			if(houseList!=null && houseList.size()>0){
				RealestateBO rBo = new RealestateBO();
				for(RHousePermitVO rvo: houseList){
					CHFlatVO house = rBo.findCHFlatBuilding(rvo.getHouseID());
					if(house != null) {
						rvo.setAttribute("location", house.getLocation_Name()!=null?house.getLocation_Name():"");
						rvo.setAttribute("doorplate", house.getAttribute("doorplate"));//幢号
						rvo.setAttribute("housearchname", house.getAttribute("housearchname"));//房屋类型
						rvo.setAttribute("flarea", house.getFlarea());
						rvo.setAttribute("cellar_Area", house.getCellar_Area());
						rvo.setAttribute("plan_Flarea", house.getPlan_Flarea());
					}
				}
				
				linkparam = new ArrayList<String>();
				linkparam.add("houseID");
				tableProperty.setEnableSort(false);
				tableProperty.setRowIndexStauts(true);
				tableProperty.addSelectControl("radio", "houseID", linkparam, "");
				tableProperty.addColumn("房屋编号", "houseID");
				tableProperty.addColumn("房屋坐落", "location");
				tableProperty.addColumn("幢号", "doorplate");
				tableProperty.addColumn("房屋类型", "housearchname");
				tableProperty.addColumn("建筑面积", "flarea", "double", "#,##0.00", "");
				tableProperty.addColumn("地下面积", "cellar_Area", "double", "#,##0.00", "");
				tableProperty.addColumn("预测面积", "plan_Flarea", "double", "#,##0.00", "");
				
				String houseHtmlView = HtmlTableUtil.createHtmlTable(tableProperty, houseList);
				request.setAttribute("houseHtmlView", houseHtmlView);
				//System.out.println("houseHtmlView=="+houseHtmlView);
			}
			/******************楼盘关联列表****************************/
			request.setAttribute("permitID", permitID);
			request.setAttribute("transactionID", transactionID);
			request.setAttribute("cmd", cmd);
			return "/fhhouse/permit/PermitDetail";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：预售许可打印主界面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoPermitInfoMenu")
	public String gotoPermitInfoMenu(HttpServletRequest request,HttpSession session,Page page) {
		try{
			
			String permitID = request.getParameter("permitID");
			String cmd = request.getParameter("cmd");
			
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String userDistrictList = this.getUserSqlDistricts(vo.getRegionId());//获取用户操作区县列表
			
			PermitBO permitBO = new PermitBO();
			
			//获取transactionID
			long transactionID = 0;
			int districtID = 0;
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "in", userDistrictList));
			params.add(new MetaFilter("permitID", "=", permitID));
			List<PermitVO> permList = permitBO.search(PermitVO.class, params);
			if(permList!= null && permList.size() > 0){
				PermitVO pvo = permList.get(0);
				transactionID = pvo.getTransactionID();
				districtID = pvo.getDistrictID();
			}
			
			request.setAttribute("transactionID", transactionID); 
			request.setAttribute("districtID", districtID); 
			request.setAttribute("permitID", permitID); 
			request.setAttribute("cmd", cmd); 
			
			return "/fhhouse/permit/PermitInfoMenu";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：预售许可打印获取信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoPermitInfoPrint")
	public String gotoPermitInfoPrint(HttpServletRequest request,HttpSession session) {
		try{
			
			String permitID = request.getParameter("permitID");
			String transactionID = request.getParameter("transactionID");
			String districtID = request.getParameter("districtID");
			String cmd = request.getParameter("cmd");
			String printFlag = request.getParameter("printFlag");
			
//			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
//			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			RealestateBO rBo = new RealestateBO();
			PermitBO permitBO = new PermitBO();
			ApplyVO apply = new ApplyVO();
			apply.setTransactionID(Long.parseLong(transactionID));
			IModel model = permitBO.find(apply);
			if(model == null){
				throw new Exception("没有找到主表记录");
			}
			
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "=", districtID));
			params.add(new MetaFilter("permitID", "=", permitID));
			List<PermitVO> permList = permitBO.search(PermitVO.class, params);
			if(permList!= null && permList.size() > 0){
				PermitVO pvo = permList.get(0);
				request.setAttribute("permitVO", pvo); 
			}
			
			//查询房屋关联
			//获取房屋关联表数据
			/*params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "=", districtID));
			params.add(new MetaFilter("permitID", "=", permitID));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("MODIFYDATE", MetaOrder.DESC));*/
			//List<RHousePermitVO> list = permitBO.search(RHousePermitVO.class, params, orders, null);
			List<CHFlatVO> list = permitBO.getPermitUnitHouse(transactionID,districtID);
			List<CHFlatVO> houseList = new ArrayList<CHFlatVO>();
			double flarea = 0;
			double cellarArea = 0;
			long houseID = 0;
			if(list!=null && list.size() > 0){
				houseID = list.get(0).getHouseID();
				//for(RHousePermitVO rvo: list){
				for(CHFlatVO houseVO : list){
					//CHFlatVO houseVO = rBo.findCHFlatBuilding(rvo.getHouseID());
					if(houseVO != null){
						CHBuildingVO  buildingVO = rBo.searchBuildingsByBuildingID(Integer.parseInt(districtID), houseVO.getBuildingID());
						if(buildingVO != null){
							houseVO.setAttribute("buildingNumber", buildingVO.getDoorPlate()!=null ?buildingVO.getDoorPlate():"");
						}
						/*String buildingType = "";
						if(!StringUtil.isBlank(houseVO.getBuilding_Type())){
							buildingType = BusinessDictUtil.getDictName("CT_BUILDING_TYPE", houseVO.getBuilding_Type());
							if(buildingType.equals(houseVO.getBuilding_Type())){
								buildingType = "";
							}
						}*/
						String buildingType = "";
						if(!StringUtil.isBlank(houseVO.getBuilding_Type())){
							buildingType = permitBO.querydictNameDAJI("common","CT_BUILDING_TYPE", houseVO.getBuilding_Type());
							if(buildingType.equals(houseVO.getBuilding_Type())){
								buildingType = "";
							}
						}
						//houseVO.setAttribute("buildingType", houseVO.getAttribute("buildingTypeName"));
						houseVO.setAttribute("buildingType", buildingType);
						if(houseVO.getFloor_Name()==null||"".equals(houseVO.getFloor_Name())){
							houseVO.setFloor_Name("-");
						}
						if(houseVO.getFlarea() != null && !"null".equals(houseVO.getFlarea()) && !"0".equals(houseVO.getFlarea())){
							flarea += Double.parseDouble(houseVO.getFlarea()); 
						}else if(houseVO.getPlan_Flarea() != null && !"null".equals(houseVO.getPlan_Flarea())){
							flarea += Double.parseDouble(houseVO.getPlan_Flarea());
						}
						
						if(houseVO.getCellar_Area() != null && !"null".equals(houseVO.getCellar_Area()) && !"0".equals(houseVO.getCellar_Area())){
							cellarArea += Double.parseDouble(houseVO.getCellar_Area()); 
						}else if(houseVO.getPlan_Cellar_Area() != null && !"null".equals(houseVO.getPlan_Cellar_Area())){
							cellarArea += Double.parseDouble(houseVO.getPlan_Cellar_Area());
						}
						
						//cellarArea += Double.parseDouble(houseVO.getCellar_Area()) == 0 ? Double.parseDouble(houseVO.getPlan_Cellar_Area()) : Double.parseDouble(houseVO.getCellar_Area());
						houseList.add(houseVO);
					}
				}
			}
			
			if(houseID != 0){
				List<EmptyVO> realList = rBo.getRealRightByHouseID(String.valueOf(houseID),1);
				if(realList != null && realList.size() > 0){
					EmptyVO emptyVO = null;
					for(int i = 0;i < realList.size(); i++){
						emptyVO = realList.get(i);
						if(emptyVO != null){
							EmptyVO evo = rBo.getRightByTransactionID(emptyVO.getAttribute("transactionID").toString(), Integer.parseInt(emptyVO.getAttribute("districtID").toString()), 1);
							if(evo != null ){//关联的现势非预告产权
								String isPercert = evo.getAttribute("isprecert").toString();
								if(!"1".equals(isPercert)){
									request.setAttribute("realNO", evo.getAttribute("realNo").toString());
									break;
								}
							}
						}
					}
				}
			}
			
			request.setAttribute("flarea", Formatter.formatNormal(flarea)); 
			request.setAttribute("cellarArea", Formatter.formatNormal(cellarArea)); 
			request.setAttribute("houseList", houseList); 
			request.setAttribute("transactionID", transactionID); 
			request.setAttribute("districtID", districtID); 
			request.setAttribute("permitID", permitID); 
			request.setAttribute("cmd", cmd); 
			request.setAttribute("printFlag", printFlag); 
			
			return "/fhhouse/permit/PermitInfoPrint";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	
	/**
	 * 功能描述：预售许可图片文件列表
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoPhoto2")
	public String gotoPhoto2(HttpServletRequest request,HttpSession session,Page page) {
		try{
			
			String permitID = request.getParameter("permitID");
			
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			
			PermitBO permitBO = new PermitBO();
			
			//获取transactionID
			long transactionID = 0;
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "in", userDistrictList));
			params.add(new MetaFilter("permitID", "=", permitID));
			List<PermitVO> permList = permitBO.search(PermitVO.class, params);
			if(permList!= null && permList.size() > 0){
				PermitVO pvo = permList.get(0);
				transactionID = pvo.getTransactionID();
			}
			
			//根据transactionID查询收件材料列表
			ApplyFileVO fileVO = new ApplyFileVO();
			fileVO.setTransactionID(transactionID);
			List<ApplyFileVO> fileList = permitBO.searchByTemplate(fileVO);
			
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("fileID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addSelectControl("checkbox", "fileID", linkparam, "");
			tableProperty.addColumn("文件名称", "fileName");
			tableProperty.addColumn("页数", "fileCount");
			tableProperty.addColumn("文件类型", "fileType");
			tableProperty.addColumn("标志", "fileFlag");
			tableProperty.addColumn("备注", "remark");

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, fileList, page);

			request.setAttribute("htmlView", htmlView);
			request.setAttribute("transID", transactionID); 
			request.setAttribute("permitID", permitID); 
			
			return "/fhhouse/permit/PermitPhotoList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	
	/**
	 * 功能描述：房屋状态控制列表
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoOnLineHouse")
	public String gotoOnLineHouse(HttpServletRequest request,HttpSession session) {
		try{
			
			String houseIDs = request.getParameter("houseIDs");
//			String districtID = request.getParameter("districtID");
			String districtID = "";
			
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			PermitBO permitBO = new PermitBO();
			
			List<RHousePermitVO> onlineHouseList = permitBO.searchOnlineHouseList(houseIDs.substring(0, houseIDs.lastIndexOf(",")),districtID);
			
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("houseID");
			linkparam.add("permitID");
			linkparam.add("districtID");
			linkparam.add("saleFlag");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addSelectControl("checkbox", "houseID", linkparam, "");
			tableProperty.addColumn("房屋编号", "houseID");
			tableProperty.addColumn("室号", "room_number");
			tableProperty.addColumn("上网标志", "saleFlagStr");
			tableProperty.addColumn("预告ID", "permitID",true);

			String htmlView = HtmlTableUtil.createHtmlTable(tableProperty, onlineHouseList);
//			System.out.println(htmlView);
			request.setAttribute("htmlView", htmlView);
			
			return "/fhhouse/permit/OnlineHouseStateList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：进入纳入网上签约界面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoOnLineHouse2")
	public String gotoOnLineHouse2(HttpServletRequest request,HttpSession session) {
		try{
			String houseIDs = request.getParameter("houseIDs");
			request.setAttribute("houseIDs", houseIDs);
			return "/fhhouse/permit/OnlineHouseStateList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：进入纳入网上签约界面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoPermitFileList")
	public String gotoPermitFileList(HttpServletRequest request,HttpSession session,ModelMap modelMap) {
		try{
			String transID = request.getParameter("transID");
			String districtID = request.getParameter("districtID");
			String permitID = request.getParameter("permitID");
			SmUserVO userVO = (SmUserVO) session.getAttribute("LgUser");
			PermitBO permitBO = new PermitBO();
//			SystemBO sysBO = new SystemBO();
			//取出收件材料表
//			ApplyFileVO fileVO = new ApplyFileVO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("transactionID", "=", transID));
			params.add(new MetaFilter("districtID", "=", districtID));
			params.add(new MetaFilter("receivedFlag", "=", 1));
			List<ApplyFileVO> fileList = permitBO.search(ApplyFileVO.class, params);
			if(fileList != null && fileList.size()>0) {
				request.setAttribute("fileList", fileList);
				request.setAttribute("fileNum", fileList.size());
				int fileCount = 0;
				for(ApplyFileVO vo : fileList) {
					fileCount += vo.getFileCount();
				}
				request.setAttribute("fileCount", fileCount);
			}
			
			PermitVO permit = new PermitVO();
			permit.setPermitID(Long.parseLong(permitID));
			permit = (PermitVO) permitBO.find(permit);
			String location = permit.getLocation();
			String applicant = permit.getCompanyName();
			String acceptUser = userVO.getDisplayName();
//			String regionName = sysBO.findUserRegionName(userVO.getRegionId());
			String tel = "";
			if("2".equals(districtID) || "3".equals(districtID) || "5".equals(districtID) ||"13".equals(districtID)) {
				tel = "85916491";
			}
			request.setAttribute("transID", transID);
			request.setAttribute("location", location);
			request.setAttribute("applicant", applicant);
			request.setAttribute("acceptUser", acceptUser);
			request.setAttribute("tel", tel);
//			request.setAttribute("regionName", regionName);
			request.setAttribute("nowDate", DateUtil.fromatDate(DateUtil.getSysDateToString()));
			return "/fhhouse/permit/PermitFilePrint";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	@RequestMapping("/getOnLineHouseList")
	public void getOnLineHouseList(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			String houseIDs = request.getParameter("houseIDs");
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			PermitBO permitBO = new PermitBO();
			List<RHousePermitVO> onlineHouseList = permitBO.searchOnlineHouseList(houseIDs.substring(0, houseIDs.lastIndexOf(",")),"");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", onlineHouseList);
			JSONObject json = JSONObject.fromObject(map);
			PrintWriter pw = response.getWriter();
//			System.out.println(json.toString());
			pw.write(json.toString());
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能描述：纳入网上签约
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doAddOnLine")
	@ResponseBody
	public JSONArray doAddOnLine(HttpServletRequest request,HttpSession session) {
		try{
			PermitBO permitBO = new PermitBO();
			String entities = request.getParameter("entities"); 
			SmUserVO userVO = (SmUserVO) session.getAttribute("LgUser");
			
			entities = "["+entities.replace("}{", "},{")+"]";
			JSONArray array = JSONArray.fromObject(entities.toString());
			List<RHousePermitVO> list = (List) JSONArray.toCollection(array, RHousePermitVO.class);
			if(list != null && list.size() > 0){
				List<RHousePermitVO> listAdd = new ArrayList<RHousePermitVO>();
				List<RHousePermitVO> listUpdate = new ArrayList<RHousePermitVO>();
				List<RHousePermitVO> listrvo = new ArrayList<RHousePermitVO>();
				List<HouseSaleLogVO> listhvo = new ArrayList<HouseSaleLogVO>();
				RHousePermitVO rvo = null;
				HouseSaleLogVO hvo = null;
				for(RHousePermitVO vo :list){
					if(vo.getPermitID() < 0){//新增
						rvo = new RHousePermitVO();
						rvo.setPermitID(0);
						rvo.setTransactionID(0);
						rvo.setDistrictID(vo.getDistrictID());
						rvo.setIsFinish(-1);
						rvo.setHouseID(vo.getHouseID());
						rvo.setSaleFlag(1);
						rvo.setModifyDate(DateUtil.getDBSysDate());
						rvo.setModifyUser(userVO.getUserId());
						listAdd.add(rvo);
						continue;
					}else if(vo.getPermitID() >= 0 && vo.getSaleFlag() != 1){
						rvo = new RHousePermitVO();
						rvo.setPermitID(vo.getPermitID());
						rvo.setSaleFlag(1);
						rvo.setDistrictID(vo.getDistrictID());
						rvo.setHouseID(vo.getHouseID());
						rvo.setIsFinish(-1);
						rvo.setTransactionID(vo.getTransactionID());
						listUpdate.add(rvo);
						continue;
					}
					
				}
				
				//新增
				if(!listAdd.isEmpty() && listAdd.size()>0){
					for(int i = 0;i<listAdd.size();i++){
						//插入房屋许可证关联表
						rvo = new RHousePermitVO();
						rvo.setSaleFlag(listAdd.get(i).getSaleFlag());
						rvo.setDistrictID(listAdd.get(i).getDistrictID());
						rvo.setHouseID(listAdd.get(i).getHouseID());
						rvo.setIsFinish(listAdd.get(i).getIsFinish());
						rvo.setPermitID(listAdd.get(i).getPermitID());
						rvo.setTransactionID(listAdd.get(i).getTransactionID());
						rvo.setModifyDate(DateUtil.getDBSysDate());
						rvo.setModifyUser(userVO.getUserId());
						listrvo.add(rvo);
						//插入日志表
						hvo = new HouseSaleLogVO();
						hvo.setDistrictID(listAdd.get(i).getDistrictID());
						hvo.setHouseID(listAdd.get(i).getHouseID());
						hvo.setTransactionID(listAdd.get(i).getTransactionID());
						hvo.setSaleFlag(listAdd.get(i).getSaleFlag());
						hvo.setModifyUser(userVO.getUserId());
						hvo.setModifyDate(DateUtil.getDBSysDate());
						hvo.setMemo("房屋状态控制-纳入网上签约-新增");
						listhvo.add(hvo);
					}
					permitBO.addBatch(listrvo);
					permitBO.addBatch(listhvo);
				//修改
				}else if(!listUpdate.isEmpty() && listUpdate.size()>0){
					for(int i = 0;i<listUpdate.size();i++){
						//更新房屋许可证关联表
						rvo = new RHousePermitVO();
						rvo.setSaleFlag(listUpdate.get(i).getSaleFlag());
						rvo.setDistrictID(listUpdate.get(i).getDistrictID());
						rvo.setHouseID(listUpdate.get(i).getHouseID());
						rvo.setIsFinish(listUpdate.get(i).getIsFinish());
						rvo.setPermitID(listUpdate.get(i).getPermitID());
						rvo.setTransactionID(listUpdate.get(i).getTransactionID());
						rvo.setModifyDate(DateUtil.getDBSysDate());
						rvo.setModifyUser(userVO.getUserId());
						permitBO.update(rvo);
						//插入日志表
						hvo = new HouseSaleLogVO();
						hvo.setDistrictID(listUpdate.get(i).getDistrictID());
						hvo.setHouseID(listUpdate.get(i).getHouseID());
						hvo.setTransactionID(listUpdate.get(i).getTransactionID());
						hvo.setSaleFlag(listUpdate.get(i).getSaleFlag());
						hvo.setModifyUser(userVO.getUserId());
						hvo.setModifyDate(DateUtil.getDBSysDate());
						hvo.setMemo("房屋状态控制-纳入网上签约-修改");
						permitBO.add(hvo);
					}
				}
				return createMessage(1,"保存成功");
			}
		}catch (Exception e){
			e.printStackTrace();
			return createMessage(-1,"保存异常");
		}
		return createMessage(1,"保存成功");
	}
	
	
	/**
	 * 功能描述：不纳入网上签约
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doAddOffLine")
	@ResponseBody
	public JSONArray doAddOffLine(HttpServletRequest request,HttpSession session) {
		try{
			PermitBO permitBO = new PermitBO();
			String entities = request.getParameter("entities"); 
			SmUserVO userVO = (SmUserVO) session.getAttribute("LgUser");
			
			entities = "["+entities.replace("}{", "},{")+"]";
			JSONArray array = JSONArray.fromObject(entities.toString());
			List<RHousePermitVO> list = (List) JSONArray.toCollection(array, RHousePermitVO.class);
			if(list != null && list.size() > 0){
				List<RHousePermitVO> listDel = new ArrayList<RHousePermitVO>();
				List<RHousePermitVO> listUpdate = new ArrayList<RHousePermitVO>();
				RHousePermitVO rvo = null;
				HouseSaleLogVO hvo = null;
				for(RHousePermitVO vo :list){
					if(vo.getPermitID() > 0){//更新
						rvo = new RHousePermitVO();
						rvo.setPermitID(vo.getPermitID());
						rvo.setTransactionID(vo.getTransactionID());
						rvo.setDistrictID(vo.getDistrictID());
						rvo.setIsFinish(-1);
						rvo.setHouseID(vo.getHouseID());
						rvo.setSaleFlag(0);//不可售
						rvo.setModifyDate(DateUtil.getDBSysDate());
						rvo.setModifyUser(userVO.getUserId());
						listUpdate.add(rvo);
						continue;
					}else if(vo.getPermitID() == 0 && vo.getSaleFlag() == 1){//删除
						rvo = new RHousePermitVO();
						rvo.setPermitID(0);
						rvo.setTransactionID(0);
						rvo.setSaleFlag(0);
						rvo.setDistrictID(vo.getDistrictID());
						rvo.setHouseID(vo.getHouseID());
						rvo.setIsFinish(-1);
						listDel.add(rvo);
						continue;
					}
					
				}
				
				//修改
				if(!listUpdate.isEmpty() && listUpdate.size()>0){
					for(int i = 0;i<listUpdate.size();i++){
						//修改房屋许可证关联表
						rvo = new RHousePermitVO();
						rvo.setSaleFlag(listUpdate.get(i).getSaleFlag());
						rvo.setDistrictID(listUpdate.get(i).getDistrictID());
						rvo.setHouseID(listUpdate.get(i).getHouseID());
						rvo.setIsFinish(listUpdate.get(i).getIsFinish());
						rvo.setPermitID(listUpdate.get(i).getPermitID());
						rvo.setTransactionID(listUpdate.get(i).getTransactionID());
						rvo.setModifyDate(DateUtil.getDBSysDate());
						rvo.setModifyUser(userVO.getUserId());
						permitBO.update(rvo);
						//插入日志表
						hvo = new HouseSaleLogVO();
						hvo.setDistrictID(listUpdate.get(i).getDistrictID());
						hvo.setHouseID(listUpdate.get(i).getHouseID());
						hvo.setTransactionID(listUpdate.get(i).getTransactionID());
						hvo.setSaleFlag(0);//不纳入网上签约0
						hvo.setModifyUser(userVO.getUserId());
						hvo.setModifyDate(DateUtil.getDBSysDate());
						hvo.setMemo("房屋状态控制-不纳入网上签约-修改");
						permitBO.add(hvo);
					}
				//删除
				}else if(!listDel.isEmpty() && listDel.size()>0){
					for(int i = 0;i<listDel.size();i++){
						//删除房屋许可证关联表
						rvo = new RHousePermitVO();
						rvo.setHouseID(listDel.get(i).getHouseID());
						rvo.setPermitID(listDel.get(i).getPermitID());
						rvo.setTransactionID(listDel.get(i).getTransactionID());
						permitBO.delete(rvo);
						//插入日志表
						hvo = new HouseSaleLogVO();
						hvo.setDistrictID(listDel.get(i).getDistrictID());
						hvo.setHouseID(listDel.get(i).getHouseID());
						hvo.setTransactionID(listDel.get(i).getTransactionID());
						hvo.setSaleFlag(0);
						hvo.setModifyUser(userVO.getUserId());
						hvo.setModifyDate(DateUtil.getDBSysDate());
						hvo.setMemo("房屋状态控制-不纳入网上签约-删除");
						permitBO.add(hvo);
					}
				}
				return createMessage(1,"保存成功");
			}
		}catch (Exception e){
			e.printStackTrace();
			return createMessage(-1,"保存异常");
		}
		return createMessage(1,"保存成功");
	}
	
	/**
	 * 功能描述：预售许可申请录入
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoPhoto")
	public String gotoPhoto(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String cmd = request.getParameter("cmd");
			String permitID = request.getParameter("permitID");
			
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			
			PermitBO permitBO = new PermitBO();
			
			//获取transactionID
			long transactionID = 0;
			int districtID = 0;
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "in", userDistrictList));
			params.add(new MetaFilter("permitID", "=", permitID));
			List<PermitVO> permList = permitBO.search(PermitVO.class, params);
			if(permList!= null && permList.size() > 0){
				PermitVO pvo = permList.get(0);
				transactionID = pvo.getTransactionID();
				districtID = pvo.getDistrictID();
			}
			
			/*根据transactionID查询收件材料列表
			ApplyFileVO fileVO = new ApplyFileVO();
			fileVO.setTransactionID(transactionID);
			List<ApplyFileVO> fileList = permitBO.searchByTemplate(fileVO);
			
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("fileID");
			/** 设置table列名 
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addSelectControl("checkbox", "fileID", linkparam, "");
			tableProperty.addColumn("文件名称", "fileName");
			tableProperty.addColumn("页数", "fileCount");
			tableProperty.addColumn("文件类型", "fileType");
			tableProperty.addColumn("标志", "fileFlag");
			tableProperty.addColumn("备注", "remark");
			
			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, fileList, page);
			*/
//			request.setAttribute("htmlView", htmlView);
			request.setAttribute("transID", transactionID); 
			request.setAttribute("districtID", districtID); 
			request.setAttribute("permitID", permitID); 
			request.setAttribute("cmd", cmd); 
			
			return "/fhhouse/permit/PermitPhotoList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：获取收件列表
	 * @param request
	 * @param session
	 * @param response
	 */
	@RequestMapping("/getApplyFileList")
	public void getApplyFileList(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			String permitID = request.getParameter("permitID");
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			
			PermitBO permitBO = new PermitBO();
			
			//获取transactionID
			long transactionID = 0;
			int districtID = 0;
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "in", userDistrictList));
			params.add(new MetaFilter("permitID", "=", permitID));
			List<PermitVO> permList = permitBO.search(PermitVO.class, params);
			if(permList!= null && permList.size() > 0){
				PermitVO pvo = permList.get(0);
				transactionID = pvo.getTransactionID();
				districtID = pvo.getDistrictID();
			}
			
			//根据transactionID查询收件材料列表
			ApplyFileVO fileVO = new ApplyFileVO();
			fileVO.setTransactionID(transactionID);
			List<ApplyFileVO> fileList = permitBO.searchByTemplate(fileVO);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", fileList);
			JSONObject json = JSONObject.fromObject(map);
			PrintWriter pw = response.getWriter();
			pw.write(json.toString());
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能描述：保存照片列表
	 * @param request
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping("/doSavePhotoList")
	@ResponseBody
	public JSONArray doSavePhotoList(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "success");
		map.put("message", "");
		try {
			response.setContentType("text/html;charset=UTF-8");
			String entities = request.getParameter("entities");
			SmUserVO userVO = (SmUserVO) session.getAttribute("LgUser");
			PermitBO permitBO = new PermitBO();
			List<Object> userDistrictList = this.getUserDistricts(userVO.getRegionId());//获取用户操作区县列表
			entities = "["+entities.replace("}{", "},{")+"]";
			JSONArray array = JSONArray.fromObject(entities.toString());
			List<ApplyFileVO> fileList = (List) JSONArray.toCollection(array, ApplyFileVO.class);
			for(ApplyFileVO vo :fileList){
				//System.out.println(vo.toString());
				if(vo.getFileID() > 0){
					permitBO.update(vo);
					if (vo.getReceivedFlag() == 0)
			          {
			            ImageFileVO image = new ImageFileVO();
			            image.setFileID(vo.getFileID());
			            image.setTransactionID(vo.getTransactionID());
			            image.setDistrictID(vo.getDistrictID());
			            permitBO.deleteByTemplate(image);
			          }
				}else{
					ApplyFileVO file = new ApplyFileVO();
					file.setFileCount(vo.getFileCount());
					file.setFileType(vo.getFileType());
					file.setFileName(vo.getFileName());
					file.setReceivedFlag(vo.getReceivedFlag());
					file.setRemark(vo.getRemark());
					file.setTransactionID(vo.getTransactionID());
					file.setDistrictID(vo.getDistrictID());
					file.setFileKind(3);//1，标准收件，3，补充收件
					permitBO.add(file);
				}
			}
			map.put("result", "success");
			map.put("message", "保存成功");
			json = JSONArray.fromObject(map);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "success");
			map.put("message", "保存异常");
			json = JSONArray.fromObject(map);
			return json;
		}
	}
	
	/**
	 * 功能描述：通过fileID删除新增的材料文件 
	 * @param request
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping("/doDeleteFileByID")
	@ResponseBody
	public JSONArray doDeleteFileByID(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			String entities = request.getParameter("entities");
			PermitBO permitBO = new PermitBO();
			entities = "["+entities.replace("}{", "},{")+"]";
			JSONArray array = JSONArray.fromObject(entities.toString());
			List<ApplyFileVO> fileList = (List) JSONArray.toCollection(array, ApplyFileVO.class);
			for(ApplyFileVO vo :fileList){
				if(vo.getFileID() > 0 && vo.getFileKind() == 3){
					ApplyFileVO fileVO = new ApplyFileVO();
					fileVO.setFileID(vo.getFileID());
					permitBO.delete(fileVO);
				}else if(vo.getFileID() > 0 && vo.getFileKind() != 3){
					return createMessage(1,"只能删除新增的材料！");
				}
			}
			return createMessage(1,"删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return createMessage(-1,"删除异常");
		}
	}
	
	/**
	 * 功能描述：检查照片收件
	 * @param request
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkStandFile")
	@ResponseBody
	public JSONArray checkStandFile(HttpServletRequest request,HttpSession session,HttpServletResponse response) throws Exception {
		try {
			String transactionID = request.getParameter("transactionID");
			String districtID = request.getParameter("districtID");
			String filenameList = request.getParameter("filenameList");
			PermitBO permitBO = new PermitBO();
			String fileNameStr = "";
			String preFileName = "";
			String fileName = "";
			int preSeqNum = 0;
			int prePageNum = 0;
			int seqNum = 0;
			int pageNum = 0;
			
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("transactionID", "=", transactionID));
			params.add(new MetaFilter("districtID", "=", districtID));
			params.add(new MetaFilter("receivedFlag", "=", 1));
			List<ApplyFileVO> accFileList = permitBO.search(ApplyFileVO.class, params);
			if( (filenameList == null || filenameList.equals("")) && (accFileList == null || accFileList.size() == 0)) {
				return createMessage(1, "");
			} else if(filenameList == null || filenameList.equals("")) {
				return createMessage(0, "没有照片文件！");
			}

			List fNameList = new LinkedList();
			String[] fileNameArr = filenameList.split("&&");
			TreeSet fileNameStrTS = new TreeSet();
			List fileNameTs = new LinkedList();
			for(int i = 0; i < fileNameArr.length; i++) {
				if(!fileNameArr[i].substring(fileNameArr[i].length() - 4).equalsIgnoreCase(".jpg")) {
					return createMessage(0, "存在非法文件类型！！");
				}
				if(fileNameArr[i].charAt(2) != '&') {
					return createMessage(0, "文件名格式不正确！缺少&！");
				}
				try {
					Integer.parseInt(fileNameArr[i].substring(0, 2));
					Integer.parseInt(fileNameArr[i].substring(fileNameArr[i].length() - 6, fileNameArr[i].length() - 4));
				} catch (Exception e) {
					return createMessage(0, "文件名格式不正确！序号和顺序号不是数字类型！");
				}
				fileNameStrTS.add(fileNameArr[i].substring(0, fileNameArr[i].length() - 4));
			}
			if(!fileNameStrTS.isEmpty()) {
				List fileList = new ArrayList();
				String[] file = null;
				for(Iterator it = fileNameStrTS.iterator(); it.hasNext();) {//02&500000003155_01
					fileName = "";
					preSeqNum = 0;
					prePageNum = 0;
					fileNameStr = (String) it.next();
					fileName = fileNameStr.substring(3, fileNameStr.length() - 3);
					seqNum = Integer.parseInt(fileNameStr.substring(0, 2));
					pageNum = Integer.parseInt(fileNameStr.substring(fileNameStr.length() - 2));
					file = new String[3];
					file[0] = fileName;
					file[1] = String.valueOf(seqNum);
					file[2] = String.valueOf(pageNum);
					fileList.add(file);
				}
				int i = 0;
				for(Iterator it = fileNameStrTS.iterator(); it.hasNext();) {//02&500000003155_01
					i++;
					fileNameStr = (String) it.next();
					fileName = fileNameStr.substring(3, fileNameStr.length() - 3);//500000003155
					seqNum = Integer.parseInt(fileNameStr.substring(0, 2));//02
					pageNum = Integer.parseInt(fileNameStr.substring(fileNameStr.length() - 2));//01
					if(i > 1) {
						for(int m = fileList.size() - 1; m > -1; m--) {
							String[] curfile = (String[]) fileList.get(m);
							if(fileName.equals(curfile[0]) && i > m && i != (m + 1)) {
								preFileName = curfile[0];
								preSeqNum = Integer.parseInt(curfile[1]);
								prePageNum = Integer.parseInt(curfile[2]);
								break;
							}
						}
						if(fileName.equals(preFileName) && seqNum == preSeqNum) {
							if(pageNum != prePageNum + 1) {
								return createMessage(0, "顺序号不连续！");
							}
						}
						if(fileName.equals(preFileName) && seqNum != preSeqNum) {
							if(seqNum != preSeqNum + 1 || pageNum != 1) {
								return createMessage(0, "序号不连续或顺序号不是从01开始！");
							}
						}
						if(!fileName.equals(preFileName)) {
							if(seqNum != 1 || pageNum != 1) {
								return createMessage(0, "序号或顺序号不是从01开始！");
							}
						}
						if(!fileName.equals(preFileName)) {
							fileNameTs.add(fileName);
						}
					} else {
						fileNameTs.add(fileName);
					}

				}
			}
			if(accFileList != null && accFileList.size() > 0) {
				for(int i = 0; i < accFileList.size(); i++) {
					ApplyFileVO accVO = (ApplyFileVO) accFileList.get(i);
					if(!fileNameTs.contains(accVO.getFileID() + "")) {
						return createMessage(0, "找不到收件的照片文件！");
					}
					fNameList.add(accVO.getFileID());
				}
			}

			/*if(extraFileList != null && !extraFileList.isEmpty()) {
				for(int i = 0; i < extraFileList.size(); i++) {
					NodeFileVO extraVO = (NodeFileVO) extraFileList.get(i);
					if(!fileNameTs.contains(extraVO.getFileID() + "")) {
						return "找不到额外收件的照片文件！";
					}
					fNameList.add(extraVO.getFileID());
				}
			}*/
			for(int i = 0; i < fileNameTs.size(); i++) {
				if(!fNameList.contains(String.valueOf(fileNameTs.get(i)))) {
					//return "存在没用的照片文件";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return createMessage(-1,"检查收件异常");
		}
		return  createMessage(1, "");
	}
	
	/**
	 * 功能描述：创建返回的JSON字符串信息
	 * @param flag
	 * @param message
	 * @return
	 */
	public JSONArray createMessage(int flag ,String message){
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		String result = "";
		switch (flag) {
		case 1:
			result = "success";
			break;
		case 0:
			result = "fail";
			break;
		case -1:
			result = "error";
			break;
		default:
			break;
		}
		map.put("result", result);
		map.put("message", message);
		json = JSONArray.fromObject(map);
		return json;
	}
	
	/**
	 * 功能描述：预售许可文件列表
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getApplyFileList2")
	public String getApplyFileList2(HttpServletRequest request,HttpSession session,Page page) {
		try{
			
			String permitID = request.getParameter("permitID");
			
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			
			PermitBO permitBO = new PermitBO();
			
			//获取transactionID
			long transactionID = 0;
			int districtID = 0;
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "in", userDistrictList));
			params.add(new MetaFilter("permitID", "=", permitID));
			List<PermitVO> permList = permitBO.search(PermitVO.class, params);
			if(permList!= null && permList.size() > 0){
				PermitVO pvo = permList.get(0);
				transactionID = pvo.getTransactionID();
				districtID = pvo.getDistrictID();
			}
			
			//根据transactionID查询收件材料列表
			ApplyFileVO fileVO = new ApplyFileVO();
			fileVO.setTransactionID(transactionID);
			List<ApplyFileVO> fileList = permitBO.searchByTemplate(fileVO);
			JSONObject.fromObject(fileList);
//			request.setAttribute("htmlView", htmlView);
			request.setAttribute("transID", transactionID); 
			request.setAttribute("districtID", districtID); 
			request.setAttribute("permitID", permitID); 
			
			return "/fhhouse/permit/PermitPhotoList2";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：预售许可申请录入列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoPermitAddQueryList")
	public String gotoPermitAddList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String companyName = request.getParameter("companyName");
			String permitNo = request.getParameter("permitNo");
			request.setAttribute("companyName", companyName);
			request.setAttribute("permitNo", permitNo);
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			
			PermitBO permitBO = new PermitBO();
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "in", userDistrictList));
			params.add(new MetaFilter("status", "=", FHConstant.PERMIT_STATUS_ACCEPT));
			if(companyName != null && !"".equals(companyName)){
				params.add(new MetaFilter("companyName", "like", "%" + companyName + "%"));
			}
			if(permitNo != null && !"".equals(permitNo)){
				params.add(new MetaFilter("permitNo", "like", "%" + permitNo + "%"));
			}
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
//			orders.add(new MetaOrder("updDate", MetaOrder.DESC));
//			orders.add(new MetaOrder("updTime", MetaOrder.DESC));
			

			List<PermitVO> list = permitBO.search(PermitVO.class, params, orders, page);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("permitID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addSelectControl("radio", "permitID", linkparam, "");
			tableProperty.addColumn("区市", "districtID_dict_name");
			tableProperty.addColumn("申请编号", "transactionID");
			tableProperty.addColumn("企业名称", "companyName");
			tableProperty.addColumn("项目名称", "projectName");
			tableProperty.addColumn("预售许可证号", "permitNo");
			tableProperty.addColumn("地址", "location");

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/permit/PermitAddQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：获取楼盘关联信息
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoHouseRelateList")
	public String gotoHouseRelateList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String permitID = request.getParameter("permitID");
			String cmd = request.getParameter("cmd");
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			
			PermitBO permitBO = new PermitBO();
			int districtID = 0;
			//获取transactionID
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "in", userDistrictList));
			params.add(new MetaFilter("permitID", "=", permitID));
			List<PermitVO> permList = permitBO.search(PermitVO.class, params);
			if(permList!= null && permList.size() > 0){
				PermitVO pvo = permList.get(0);
				request.setAttribute("transID", pvo.getTransactionID());
				districtID = pvo.getDistrictID();
				request.setAttribute("districtID", districtID);
			}
			//获取房屋关联表数据
			params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("districtID", "=", districtID));//根据预售许可证选择的区县
			params.add(new MetaFilter("permitID", "=", permitID));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("MODIFYDATE", MetaOrder.DESC));
			//List<RHousePermitVO> list = permitBO.search(RHousePermitVO.class, params, orders, page);
			List<RHousePermitVO> list = permitBO.search(RHousePermitVO.class, params, orders, null);
			String htmlView = "";
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			if(list!=null && list.size()>0){
				RealestateBO rBo = new RealestateBO();
				for(RHousePermitVO rvo: list){
					CHFlatVO house = rBo.findCHFlatBuilding(rvo.getHouseID());
					if(house != null) {
						rvo.setAttribute("location", house.getLocation_Name()!=null?house.getLocation_Name():"");
						rvo.setAttribute("doorplate", house.getAttribute("doorplate"));//幢号
						rvo.setAttribute("housearchname", house.getAttribute("housearchname"));//房屋类型
						rvo.setAttribute("flarea", house.getFlarea());
						rvo.setAttribute("cellar_Area", house.getCellar_Area());
						rvo.setAttribute("plan_Flarea", house.getPlan_Flarea());
					}
				}
				
				List<String> linkparam = new ArrayList<String>();
				linkparam.add("houseID");
				tableProperty.setEnableSort(false);
				tableProperty.setRowIndexStauts(true);
				tableProperty.addSelectControl("checkbox", "houseID", linkparam, "");
				tableProperty.addColumn("房屋编号", "houseID");
				tableProperty.addColumn("房屋坐落", "location");
				tableProperty.addColumn("幢号", "doorplate");
				tableProperty.addColumn("房屋类型", "housearchname");
				tableProperty.addColumn("建筑面积", "flarea", "double", "#,##0.00","");
				tableProperty.addColumn("地下面积", "cellar_Area", "double", "#,##0.00","");
				tableProperty.addColumn("预测面积", "plan_Flarea", "double", "#,##0.00","");
			}
			//htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);
			htmlView = HtmlTableUtil.createHtmlTable(tableProperty, list);
			request.setAttribute("htmlView", htmlView);
			request.setAttribute("permitID", permitID);
			request.setAttribute("cmd", cmd);
			return "/fhhouse/permit/HouseRelateQueryList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：预售许可证编辑录入
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoPermitEdit")
	public String gotoPermitEdit(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String cmd = request.getParameter("cmd"); //add:新增   edit：修改
			long transID = 0;
			PermitBO permitBO = new PermitBO();
			PermitVO permit = null;
			 //add:新增   edit：修改
			if("add".equals(cmd)){
				permit = new PermitVO();
				ApplyVO applyVO = new ApplyVO();
				applyVO.setAcceptUser(vo.getUserId());
				applyVO.setAcceptDate(DateUtil.getDBSysDate());
				applyVO.setDistrictID(vo.getRegionId());
				applyVO.setState(1);//受理
				long id = permitBO.getApplySeqID("SEQ_APPLY_T");
				
				if(id > 0 && id < 100){
					String ids = "";
					if(String.valueOf(id).length() == 1){
						ids = "00"+id;
					}else{
						ids = "0"+id;
					}
					transID = Long.parseLong(DateUtil.getSysDateYYYYMMDD() + ids);
				}else if(id > 0 && id >= 100){
					transID = Long.parseLong(DateUtil.getSysDateYYYYMMDD() + id);
				}
				applyVO.setTransactionID(transID);
				System.out.println("生成的案件号是===="+transID);
				permitBO.add(applyVO);
				
				//生成收件材料。
				//1、先查询登记库的预售许可的标准收件
				RealestateFinder finder = new RealestateFinder();
				List<CfgFileStandardVO> fileStandards = finder.searchFileRule(1013,1);
				if (fileStandards != null && fileStandards.size()>0) {
					//2.新增预售许可的标准收件材料
					for (CfgFileStandardVO fileStandard : fileStandards) {
						ApplyFileVO file = new ApplyFileVO();
//						file.setFileCode(fileStandard.getFileCode());
						file.setFileName(fileStandard.getFileName());
						file.setTransactionID(transID);
						file.setFileKind(1);// 1.标准收件,3.补充收件
						file.setDistrictID(vo.getRegionId());
						file.setFileCount(fileStandard.getFileNum());
						file.setFileType(1);//默认是1，原件正本
						file.setTypeBID(1013);
						file.setReceivedFlag(0);
						permitBO.add(file);
					}
				}
				
				permit.setTransactionID(transID);
				permit.setStatus(1);//受理
				permit.setPermitType(1);//默认预售
				permit.setDistrictID(vo.getRegionId());//默认用户所在区县
				permit.setEType(1);//默认1
				permit.setFType(1);//默认1
				permit.setCurrency(1);//默认人民币
				request.setAttribute("permit", permit);
				request.setAttribute("transactionID", transID);
//				permitBO.add(permit);
			}else if("edit".equals(cmd) || "modify".equals(cmd) || "audit".equals(cmd)){
				String permitID = request.getParameter("permitID");
				PermitVO permitVO = new PermitVO();
				if("".equals(permitID) || "null".equals(permitID) || permitID == null){
					//通过点击案件号来查询，编辑
					String transactionID = request.getParameter("transactionID");
					List<MetaFilter> params = new ArrayList<MetaFilter>();
					params.add(new MetaFilter("transactionID", "=", transactionID));
					params.add(new MetaFilter("status", "=", FHConstant.PERMIT_STATUS_ACCEPT));
					List<PermitVO> list = permitBO.search(PermitVO.class, params);
					if(list!=null && list.size()>0){
						permitVO = list.get(0);
					}
				}else{//通过选中单选框，点击编辑
					permitVO = (PermitVO)permitBO.find(PermitVO.class, Long.parseLong(permitID));
				}
				ApplyVO apply = new ApplyVO();
				apply.setTransactionID(permitVO.getTransactionID());
				apply = (ApplyVO) permitBO.find(apply);
				request.setAttribute("passOpinion", apply.getPassOpinion());
				request.setAttribute("permit", permitVO);
				request.setAttribute("permitID", permitVO.getPermitID());
				request.setAttribute("transactionID", permitVO.getTransactionID());
			}
			request.setAttribute("cmd", cmd);
			return "/fhhouse/permit/PermitEdit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	
	/**
	 * 功能描述：预售许可提交  1受理，2，审核，3缮证，-1归档
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doPermitSubmit")
	@ResponseBody
	public JSONArray doPermitSubmit(HttpServletRequest request,HttpSession session) {
		try{
			String permitID = request.getParameter("permitID"); //案件编号
			String transactionID = request.getParameter("transactionID"); //案件编号
			SmUserVO userVO = (SmUserVO) session.getAttribute("LgUser");
			PermitBO permitBO = new PermitBO();
			List<MetaField> fields = new ArrayList<MetaField>();
			fields.add(new MetaField("permitID", permitID));
			fields.add(new MetaField("status", 2));//审核
			int count1 = permitBO.update(PermitVO.class, Long.parseLong(permitID), fields);
			
			fields = new ArrayList<MetaField>();
			fields.add(new MetaField("transactionID", transactionID));
			fields.add(new MetaField("state", 2));//审核
			fields.add(new MetaField("auditDate", DateUtil.getDBSysDate()));//受理提交时间
			fields.add(new MetaField("auditUser", userVO.getUserId()));//受理提交人员
			int count2 = permitBO.update(ApplyVO.class, Long.parseLong(transactionID), fields);
			
			if(count1 > 0 && count2 > 0){
				return createMessage(1, "提交完成");
			}	
		}catch (Exception e){
			e.printStackTrace();
			return createMessage(-1, "提交异常");
		}
		return createMessage(0, "操作失败");
	}
	
	/**
	 * 功能描述：预售许可证案件缮证
	 * @param request
	 * @param session
	 * @return
	 */
	public void printPermit(String transactionID,String districtID,String permitID,SmUserVO userVO) {
		try{
			PermitBO permitBO = new PermitBO();
			List<MetaField> fields = new ArrayList<MetaField>();
			fields.add(new MetaField("permitID", permitID));
			fields.add(new MetaField("status", 3));//打证
			int count1 = permitBO.update(PermitVO.class, Long.parseLong(permitID), fields);
			
			fields = new ArrayList<MetaField>();
			fields.add(new MetaField("transactionID", transactionID));
			fields.add(new MetaField("state", 3));//打证
			fields.add(new MetaField("printDate", DateUtil.getDBSysDate()));//缮证时间
			fields.add(new MetaField("printUser", userVO.getUserId()));//缮证人员
			int count2 = permitBO.update(ApplyVO.class, Long.parseLong(transactionID), fields);
			
			if(count1 > 0 && count2 > 0){
			}	
			
			//增加缮证日志表。
			//---------------------------------------------------------------------------------
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能描述：预售许可证案件提交归档
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doSubmitPermitArc")
	@ResponseBody
	public JSONArray doSubmitPermitArc(HttpServletRequest request,HttpSession session) {
		try{
			String permitID = request.getParameter("permitID"); //案件编号
			String transactionID = request.getParameter("transactionID"); //案件编号
			SmUserVO userVO = (SmUserVO) session.getAttribute("LgUser");
			PermitBO permitBO = new PermitBO();
			List<MetaField> fields = new ArrayList<MetaField>();
			fields.add(new MetaField("permitID", permitID));
			fields.add(new MetaField("status", -1));//归档
			int count1 = permitBO.update(PermitVO.class, Long.parseLong(permitID), fields);
			
			fields = new ArrayList<MetaField>();
			fields.add(new MetaField("transactionID", transactionID));
			fields.add(new MetaField("state", -1));//归档
			fields.add(new MetaField("archiveDate", DateUtil.getDBSysDate()));//归档时间
			fields.add(new MetaField("archiveUser", userVO.getUserId()));//归档人员
			int count2 = permitBO.update(ApplyVO.class, Long.parseLong(transactionID), fields);
			
			if(count1 > 0 && count2 > 0){
				//更新房屋关联表的isfinish字段
				List<MetaField> whereFields = new ArrayList<MetaField>();
				whereFields.add(new MetaField("transactionID", transactionID));
				whereFields.add(new MetaField("permitID", permitID));
				//更新字段
				List<MetaField> fieldList = new ArrayList<MetaField>();
				fieldList.add(new MetaField("isFinish",-1));
				permitBO.update(RHousePermitVO.class, whereFields, fieldList);
				return createMessage(1, "提交完成");
			}	
		}catch (Exception e){
			e.printStackTrace();
			return createMessage(-1, "提交异常");
		}
		return createMessage(0, "操作失败");
	}
	
	/**
	 * 功能描述：预售许可证案件提交归档
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doBackAuditPermit")
	@ResponseBody
	public JSONArray doBackAuditPermit(HttpServletRequest request,HttpSession session) {
		try{
			String permitID = request.getParameter("permitID"); //案件编号
			String transactionID = request.getParameter("transactionID"); //案件编号
			SmUserVO userVO = (SmUserVO) session.getAttribute("LgUser");
			PermitBO permitBO = new PermitBO();
			List<MetaField> fields = new ArrayList<MetaField>();
			fields.add(new MetaField("permitID", permitID));
			fields.add(new MetaField("status", 2));//审核
			int count1 = permitBO.update(PermitVO.class, Long.parseLong(permitID), fields);
			
			fields = new ArrayList<MetaField>();
			fields.add(new MetaField("transactionID", transactionID));
			fields.add(new MetaField("state", 2));//审核
			fields.add(new MetaField("archiveDate", DateUtil.getDBSysDate()));//归档时间
			fields.add(new MetaField("archiveUser", userVO.getUserId()));//归档人员
			int count2 = permitBO.update(ApplyVO.class, Long.parseLong(transactionID), fields);
			
			if(count1 > 0 && count2 > 0){
				return createMessage(1, "回退成功");
			}	
		}catch (Exception e){
			e.printStackTrace();
			return createMessage(-1, "回退异常");
		}
		return createMessage(0, "回退失败");
	}
	
	/**
	 * 功能描述：预售许可证审核
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doPermitAuditPass")
	@ResponseBody
	public JSONArray doPermitAuditPass(HttpServletRequest request,HttpSession session) {
		try{
			String permitID = request.getParameter("permitID"); //案件编号
			String transactionID = request.getParameter("transactionID"); //案件编号
			int result = Integer.parseInt(request.getParameter("result")); //审核结果
			String opinion = request.getParameter("opinion"); //审核意见
			SmUserVO userVO = (SmUserVO) session.getAttribute("LgUser");
			PermitBO permitBO = new PermitBO();
			
//			int count = permitBO.auditPermitDetail(permitID, transactionID,result,opinion,userVO);
			List<MetaField> fields = new ArrayList<MetaField>();
			fields.add(new MetaField("permitID", permitID));
			fields.add(new MetaField("status", result==1?3:1));//发证：3,受理：1
			fields.add(new MetaField("passdate", DateUtil.getDBSysDate()));
			int count1 = permitBO.update(PermitVO.class, Long.parseLong(permitID), fields);
			
			fields = new ArrayList<MetaField>();
			fields.add(new MetaField("transactionID", transactionID));
			fields.add(new MetaField("state", result==1?3:1));//发证：3，受理1
			fields.add(new MetaField("passDate", DateUtil.getDBSysDate()));//审核时间
			fields.add(new MetaField("passUser", userVO.getUserId()));//审核人员
			fields.add(new MetaField("passResult", result==1?1:-1));//审核通过：1，不通过：-1
			fields.add(new MetaField("passOpinion", opinion));//审核意见
			int count2 = permitBO.update(ApplyVO.class, Long.parseLong(transactionID), fields);
//			if(count > 0 && result == 1){//审核通过的要插入房屋合同状态表
			if(count1 > 0 && count2 > 0 && result == 1){//审核通过的要插入房屋合同状态表
				//批量插入房屋合同状态表
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("transactionID", "=", transactionID));
				params.add(new MetaFilter("permitID", "=", permitID));
				List<RHousePermitVO> houseList = permitBO.search(RHousePermitVO.class, params); 
				int count = permitBO.addHouseSaleLog(houseList, userVO.getUserId(), "审核通过添加", 1);
				if(count > 0){
					return createMessage(1, "审核成功");
				}
			}	
		}catch (Exception e){
			System.out.println("Error at PermitAction.doPermitAuditPass():"+e.getLocalizedMessage());
			e.printStackTrace();
			return createMessage(-1, "审核异常");
		}
		return createMessage(1, "审核完成");
	}
	
	/**
	 * 功能描述：预售许可证案件录入
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoPermitApply")
	public String gotoPermitApply(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			PermitBO permitBO = new PermitBO();
			ApplyVO applyVO = new ApplyVO();
			applyVO.setAcceptUser(vo.getUserId());
			applyVO.setAcceptDate(DateUtil.getDBSysDate());
			applyVO.setDistrictID(vo.getRegionId());
			long transactionID = permitBO.add(applyVO);
			
			request.setAttribute("transactionID", transactionID);
			return "/fhhouse/permit/ApplyAdd";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：查询楼盘坐落信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoAddHouse")
	public String gotoAddHouse(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String permitID = request.getParameter("permitID");
			String transID = request.getParameter("transID");
			String cmd = request.getParameter("cmd");
			String districtID = request.getParameter("districtID");
			request.setAttribute("permitID", permitID);
			request.setAttribute("transID", transID);
			request.setAttribute("cmd", cmd);
			request.setAttribute("districtID", districtID);
			return "/fhhouse/permit/HouseQuerySelect";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：房屋合同状态控制
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoHouseStateList")
	public String gotoHouseStateList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			SmUserVO userVO = (SmUserVO) session.getAttribute("LgUser");
			request.setAttribute("districtID", userVO.getRegionId());
			return "/fhhouse/permit/HouseQueryStateSelect";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	
	/**
	 * 功能描述：预售许可证案件录入检查
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doPermitSubmitCheck")
	@ResponseBody
	public JSONArray doPermitSubmitCheck(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "success");
		map.put("message", "");
		try{
			String cmd = request.getParameter("cmd"); //add:企业新增   edit：企业修改
			String permitNo = request.getParameter("permitNo"); //案件编号
			String transactionID = request.getParameter("transactionID"); //案件编号
			PermitBO permitBO = new PermitBO();
			if("add".equals(cmd)){
				RHousePermitVO  rvo = new RHousePermitVO();
				rvo.setTransactionID(Long.parseLong(transactionID));
				List list = permitBO.searchByTemplate(rvo);
				if(list != null && list.size() > 0){
					map.put("result", "fail");
					map.put("message", "新增失败：存在已关联案件号的许可证号！");
				}
				
				//检查是否存在相同的预售许可证号，且transactionID不相同
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("transactionID", "<>", transactionID));
				params.add(new MetaFilter("permitNO", "=", permitNo));
				params.add(new MetaFilter("status", "<>", "-9"));
				List list2 = permitBO.search(PermitVO.class, params);
				if(list2 != null && list2.size() > 0){
					map.put("result", "fail");
					map.put("message", "保存失败：预售许可证号已存在！");
				}
			}
			if("edit".equals(cmd) || "modify".equals(cmd) || "audit".equals(cmd)){
				//检查是否存在相同的预售许可证号，且transactionID不相同
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("transactionID", "<>", transactionID));
				params.add(new MetaFilter("permitNO", "=", permitNo));
				params.add(new MetaFilter("status", "<>", "-9"));
				List list2 = permitBO.search(PermitVO.class, params);
				if(list2 != null && list2.size() > 0){
					map.put("result", "fail");
					map.put("message", "保存失败：预售许可证号已存在！");
				}
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "error");
			map.put("message", "异常信息："+e.getLocalizedMessage());
			json = JSONArray.fromObject(map);
			return json;
		}
	}
	
	/**
	 *功能描述：根据坐落信息查询位置信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doSearchLocations")
	@ResponseBody
	public JSONArray doSearchLocations(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "success");
		map.put("message", "");
		try{
			String districtID = request.getParameter("districtID"); 
			String roadName = request.getParameter("roadName"); 
			String laneName = request.getParameter("laneName"); 
			String subLaneName = request.getParameter("subLaneName"); 
			String streetNumber = request.getParameter("streetNumber"); 
			
			RealestateBO realestateBO = new RealestateBO();
			List<EmptyVO> list = realestateBO.searchLocations(districtID,roadName,laneName,subLaneName,streetNumber);
//			Map<String , String> locationMap = new HashMap<String , String>();
			if(list!=null && list.size()>0){
				for(EmptyVO vo :list){
//					locationMap.put(String.valueOf(vo.getLocationID()), vo.getLocationName());
					vo.setId(String.valueOf(vo.getAttribute("locationID")));
					vo.setText(String.valueOf(vo.getAttribute("locationName")));
				}
			}
			map.put("result", "success");
			map.put("message", "");
			map.put("data", list);
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "error");
			map.put("message", "异常信息："+e.getLocalizedMessage());
			json = JSONArray.fromObject(map);
			return json;
		}
	}
	
	/**
	 * 功能描述：根据houseID查询位置信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doSearchLocationsByHouseID")
	@ResponseBody
	public JSONArray doSearchLocationsByHouseID(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "success");
		map.put("message", "");
		try{
			String districtID = request.getParameter("districtID"); 
			String houseID = request.getParameter("houseID"); 
			
			RealestateFinder finder = new RealestateFinder();
			List<CHFlatVO> list = finder.findHousesByHouseID(houseID,districtID);
			long buildingID = 0;
			if(list!=null && list.size()>0){
				CHFlatVO house = list.get(0);
				if (house != null && house.getBuildingID() > 0) {
					buildingID = house.getBuildingID();
				}
			}
			List<LocationVO> locationList = new ArrayList<LocationVO>();
			RealestateBO realestateBO = new RealestateBO();
			locationList = realestateBO.searchLocationsByHouseID(houseID,districtID);
			List<EmptyVO> returnList = new ArrayList<EmptyVO>();
			if(locationList != null && locationList.size() > 0 ) {
				for(LocationVO vo :locationList) {
					EmptyVO evo = new EmptyVO();
					evo.setId(String.valueOf(vo.getLocationID()));
					evo.setText(String.valueOf(vo.getLocationName()));
					evo.setAttribute("buildingID", buildingID);
					returnList.add(evo);
				}
			}
			
			map.put("result", "success");
			map.put("message", "");
			map.put("data", returnList);
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "error");
			map.put("message", "异常信息："+e.getLocalizedMessage());
			json = JSONArray.fromObject(map);
			return json;
		}
	}
	
	/**
	 * 功能描述：根据地号，地块编号查询地的信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doSearchLots")
	@ResponseBody
	public JSONArray doSearchLots(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "success");
		map.put("message", "");
		try{
			String lotID = request.getParameter("lotID"); 
			String lotNumber = request.getParameter("lotNumber"); 
			String districtID = request.getParameter("districtID"); 
			List<EmptyVO> list = new ArrayList<EmptyVO>();
			RealestateBO realestateBO = new RealestateBO();
			List<CHLotVO> lotList = realestateBO.searchLots(lotID,lotNumber,districtID);
			if(lotList!=null && lotList.size()>0){
				for(CHLotVO vo :lotList){
					EmptyVO evo = new EmptyVO();
					evo.setId(String.valueOf(vo.getLotID()));
//					System.out.println("宗地名称："+vo.getLot_Name());
					evo.setText(String.valueOf(vo.getLot_Name()!=null?vo.getLot_Name():""));
					list.add(evo);
				}
			}
			map.put("result", "success");
			map.put("message", "");
			map.put("data", list);
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "error");
			map.put("message", "异常信息："+e.getLocalizedMessage());
			json = JSONArray.fromObject(map);
			return json;
		}
	}
	
	
	/**
	 * 功能描述：根据地号查询地的用途
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doSearchLandUseByLotID")
	@ResponseBody
	public JSONArray doSearchLandUseByLotID(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "success");
		map.put("message", "");
		try{
			String lotID = request.getParameter("lotID"); 
			RealestateBO realestateBO = new RealestateBO();
			List<EmptyVO> lotList = realestateBO.searchLandUseByLotID(lotID);
			if(lotList!=null && lotList.size()>0){
				for(EmptyVO vo :lotList){
					vo.setId(String.valueOf(vo.getAttribute("code")!=null ?vo.getAttribute("code"):""));
					vo.setText(String.valueOf(vo.getAttribute("use")!=null?vo.getAttribute("use"):""));
				}
			}
			map.put("result", "success");
			map.put("message", "");
			map.put("data", lotList);
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "error");
			map.put("message", "异常信息："+e.getLocalizedMessage());
			json = JSONArray.fromObject(map);
			return json;
		}
	}
	
	/**
	 * 功能描述：根据用途编码和buildingID 查询房屋信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doSearchHousesByBuildingIDAndLanduse")
	@ResponseBody
	public JSONArray doSearchHousesByBuildingIDAndLanduse(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "success");
		map.put("message", "");
		try{
			String landuseID = request.getParameter("landuseID"); 
			String districtID = request.getParameter("districtID"); 
			String buildingID = request.getParameter("buildingID"); 
			PermitBO  permitBO = new PermitBO();
//			RealestateBO realestateBO = new RealestateBO();
//			List<CHFlatVO> houseList = realestateBO.searchHousesByBuildingIDAndLanduse(landuseID,buildingID,districtID);
			List<CHFlatVO> houseList = null;
			houseList = permitBO.searchHousesByBuildingIDAndLanduse(landuseID, buildingID, districtID);
			String htmlView = "";
			if(houseList!=null && houseList.size() > 0){
				for(CHFlatVO house :houseList){
					/*long houseID = house.getHouseID();
					int distID = Integer.parseInt(house.getDistrictID());
					List houseHints = finder.searchHouseHintVO(houseID);
					if(!houseHints.isEmpty()) {
						house.setAttribute("hint", 1);
					} else {
						house.setAttribute("hint", 0);
					}
					//增加房屋性质读取 2017.10.11 hwg
					List<CHHouseTypeVO> houseTypes = finder.searchHouseType(houseID);
					if (houseTypes != null && houseTypes.size() > 0) {
						house.setAttribute("housetype", houseTypes.get(0).getHouse_Type());
					} else {
						house.setAttribute("housetype", 0);
					}
					
					//根据houseID 获取预售许可。
					List<MetaFilter> params = new ArrayList<MetaFilter>();
					params.add(new MetaFilter("houseID", "=", house.getHouseID()));
					params.add(new MetaFilter("districtID", "=", house.getDistrictID()));
					List<RHousePermitVO> permitList = permitBO.search(RHousePermitVO.class, params);
					if(permitList!=null && permitList.size()>0){
						RHousePermitVO vo = permitList.get(0);
						house.setAttribute("permit", vo.getTransactionID());
					}*/
					String transID = String.valueOf(house.getAttribute("transactionID"));
					transID = transID.trim();
					if(transID != null && !"".equals(transID) && !"0".equals(transID)) {
						String transHref = "<a href='#' onclick='javascript:showTransFrame("+transID+")'>" +transID + "</a>";
						house.setAttribute("permit", transHref);
					}
					
				}
				/*HashMap<String , Object>[] houses = ListUtil.listToHashMap(houseList);
				Map<String , List<?>> lotMap = new HashMap<String , List<?>>();
				if (houseList != null && houseList.size() > 0) {
					for(HashMap<String , Object> house : houses) {
						dealRight(house, lotMap);
					}
				}*/
				
				/** 设置table列名 */
				TableProperty tableProperty = new TableProperty();
				if(houseList!=null && houseList.size()>0){
					List<String> linkparam = new ArrayList<String>();
					linkparam.add("houseID");
//					linkparam.add("districtID");
					tableProperty.setEnableSort(false);
//					tableProperty.setRowIndexStauts(true);

					List<String> trEventParams = new ArrayList<String>();
					trEventParams.add("houseID");
					trEventParams.add("districtID");
					trEventParams.add("thisStr");
					String eventType = "onclick";
					String eventName = "showTransInfo";

					tableProperty.setTrEventType(eventType);
					tableProperty.setTrEventName(eventName);
					tableProperty.setTrEventParams(trEventParams);
//					String controlStyle = "style='width:80px;visibility: hidden'";//定义样式style内容，根据需求自己定义
					tableProperty.addSelectControl("checkbox", "houseID", linkparam, "");
					tableProperty.addColumn("室号", "room_Number", "width=100px");
					tableProperty.addColumn("房屋编号", "houseID", "width=150px");
					tableProperty.addColumn("房屋坐落", "location_Name", "width=200px");
					tableProperty.addColumn("预测/实测面积", "areaCert", "double", "#,##0.00");
//					tableProperty.addColumn("预测面积", "plan_Flarea", "double", "#,##0.00", controlStyle);
//					tableProperty.addColumn("实测面积", "flarea", "double", "#,##0.00", controlStyle);
					tableProperty.addColumn("预售许可", "permit", "width=150px");
					tableProperty.addColumn("产权", "reals", "width=150px");
					tableProperty.addColumn("抵押", "others", "width=150px");
					tableProperty.addColumn("限制", "limits", "width=150px");
					tableProperty.addColumn("租赁", "hires", "width=150px");
					tableProperty.addColumn("文件", "files", "width=150px");
				}
				htmlView = HtmlTableUtil.createHtmlTable(tableProperty, houseList);
				htmlView = htmlView.replaceAll("'this'", "this");
				map.put("htmlView", htmlView);
				map.put("data", houseList.size());
//				System.out.println(htmlView);
			}else {
				map.put("result", "fail");
				map.put("message", "没有查询到房屋信息");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "error");
			map.put("message", "异常信息："+e.getLocalizedMessage());
			json = JSONArray.fromObject(map);
			return json;
		}
	}
	
	/**
	 * 功能描述：根据locationID查询building数据
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doSearchBuildingsByLocationID")
	@ResponseBody
	public JSONArray doSearchBuildingsByLocationID(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "success");
		map.put("message", "");
		try{
			String districtID = request.getParameter("districtID")!=null?request.getParameter("districtID"):""; 
			String locationID = request.getParameter("locationID")!=null?request.getParameter("locationID"):""; 
			List<EmptyVO> list = new ArrayList<EmptyVO>();
			if("".equals(districtID)){
				districtID = "0";
			}
			if(!"".equals(districtID) && !"".equals(locationID)){
				RealestateFinder finder = new RealestateFinder();
				List<CHBuildingVO> buildingList = finder.searchBuildingsByLocationID(Integer.parseInt(districtID),Long.parseLong(locationID));
				
				if(buildingList!=null && buildingList.size() > 0){
					for(CHBuildingVO cvo :buildingList){
						EmptyVO vo = new EmptyVO();
						vo.setId(String.valueOf(cvo.getBuildingID()));
						vo.setText(String.valueOf(cvo.getDoorPlate()));
						list.add(vo);
					}
				}
				map.put("result", "success");
				map.put("message", "");
			}
			map.put("data", list);
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "error");
			map.put("message", "异常信息："+e.getLocalizedMessage());
			json = JSONArray.fromObject(map);
			return json;
		}
	}
	
	/**
	 * 功能描述：根据lotID查询building数据
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doSearchBuildingsByLotID")
	@ResponseBody
	public JSONArray doSearchBuildingsByLotID(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "success");
		map.put("message", "");
		try{
			String districtID = request.getParameter("districtID")!=null?request.getParameter("districtID"):""; 
			String lotID = request.getParameter("lotID")!=null?request.getParameter("lotID"):""; 
			List<EmptyVO> list = new ArrayList<EmptyVO>();
			if("".equals(districtID)){
				districtID = "0";
			}
			if(!"".equals(districtID) && !"".equals(lotID)){
				RealestateFinder finder = new RealestateFinder();
				List<CHBuildingVO> buildingList = finder.searchBuildingsByLotID(Integer.parseInt(districtID),Long.parseLong(lotID));
				
				if(buildingList!=null && buildingList.size() > 0){
					for(CHBuildingVO cvo :buildingList){
						EmptyVO vo = new EmptyVO();
						vo.setId(String.valueOf(cvo.getBuildingID()));
						vo.setText(String.valueOf(cvo.getDoorPlate()));
						list.add(vo);
					}
				}
				map.put("result", "success");
				map.put("message", "");
			}
			map.put("data", list);
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "error");
			map.put("message", "异常信息："+e.getLocalizedMessage());
			json = JSONArray.fromObject(map);
			return json;
		}
	}
	
	/**
	 * 功能描述：
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doSearchHousesByBuildingID")
	@ResponseBody
	public JSONArray doSearchHousesByBuildingID(HttpServletRequest request,HttpSession session,Page page) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "success");
		map.put("message", "");
		try{
			String districtID = request.getParameter("districtID")!=null?request.getParameter("districtID"):""; 
			String buildingID = request.getParameter("buildingID")!=null?request.getParameter("buildingID"):""; 
			List<CHFlatVO> houseList = new ArrayList<CHFlatVO>();
			if("".equals(districtID)){
				districtID = "0";
			}
			PermitBO  permitBO = new PermitBO();
			if(!"".equals(districtID) && !"".equals(buildingID)){
				//前台显示
				String htmlView = "";
//				RealestateFinder finder = new RealestateFinder();
//				houseList = finder.findHousesByBuildingID(Integer.parseInt(districtID),Long.parseLong(buildingID),null);
				houseList = permitBO.findHousesByBuildingIDAndPermitVO(buildingID, districtID);
				if(houseList!=null && houseList.size() > 0){
					for(CHFlatVO house :houseList){
//						long houseID = house.getHouseID();
//						int distID = Integer.parseInt(house.getDistrictID());
						/*List houseHints = finder.searchHouseHintVO(houseID);
						if(!houseHints.isEmpty()) {
							house.setAttribute("hint", 1);
						} else {
							house.setAttribute("hint", 0);
						}
						//增加房屋性质读取 2017.10.11 hwg
						List<CHHouseTypeVO> houseTypes = finder.searchHouseType(houseID);
						if (houseTypes != null && houseTypes.size() > 0) {
							house.setAttribute("housetype", houseTypes.get(0).getHouse_Type());
						} else {
							house.setAttribute("housetype", 0);
						}*/
						
						//根据houseID 获取预售许可。
//						List<MetaFilter> params = new ArrayList<MetaFilter>();
//						params.add(new MetaFilter("houseID", "=", house.getHouseID()));
//						params.add(new MetaFilter("districtID", "=", house.getDistrictID()));
//						List<RHousePermitVO> permitList = permitBO.search(RHousePermitVO.class, params);
//						if(permitList!=null && permitList.size()>0){
//							RHousePermitVO vo = permitList.get(0);
//							String transID = String.valueOf(vo.getTransactionID());
							String transID = String.valueOf(house.getAttribute("transactionID"));
							transID = transID.trim();
							if(transID != null && !"".equals(transID) && !"0".equals(transID)) {
								String transHref = "<a href='#' onclick='javascript:showTransFrame("+transID+")'>" +transID + "</a>";
								house.setAttribute("permit", transHref);
							}
							
//						}
						
					}
					/*HashMap<String , Object>[] houses = ListUtil.listToHashMap(houseList);
					Map<String , List<?>> lotMap = new HashMap<String , List<?>>();
					if (houseList != null && houseList.size() > 0) {
						for(HashMap<String , Object> house : houses) {
							dealRight(house, lotMap);
						}
					}*/
					
					/** 设置table列名 */
					TableProperty tableProperty = new TableProperty();
					if(houseList!=null && houseList.size()>0){
						List<String> linkparam = new ArrayList<String>();
						linkparam.add("houseID");
//						linkparam.add("districtID");
						tableProperty.setEnableSort(false);
//						tableProperty.setRowIndexStauts(true);
						
						List<String> trEventParams = new ArrayList<String>();
						trEventParams.add("houseID");
						trEventParams.add("districtID");
						trEventParams.add("thisStr");
						String eventType = "onclick";
						String eventName = "showTransInfo";
						
						tableProperty.setTrEventType(eventType);
						tableProperty.setTrEventName(eventName);
						tableProperty.setTrEventParams(trEventParams);
//						String controlStyle = "style='width:80px;visibility: hidden'";//定义样式style内容，根据需求自己定义
						tableProperty.addSelectControl("checkbox", "houseID", linkparam, "");
						tableProperty.addColumn("室号", "room_Number", "width=100px");
						tableProperty.addColumn("房屋编号", "houseID", "width=150px");
						tableProperty.addColumn("房屋坐落", "location_Name", "width=200px");
						tableProperty.addColumn("预测/实测面积", "areaCert", "double", "#,##0.00");
//						tableProperty.addColumn("预测面积", "plan_Flarea", "double", "#,##0.00", controlStyle);
//						tableProperty.addColumn("实测面积", "flarea", "double", "#,##0.00", controlStyle);
						tableProperty.addColumn("预售许可", "permit", "width=150px");
						tableProperty.addColumn("产权", "reals", "width=150px");
						tableProperty.addColumn("抵押", "others", "width=150px");
						tableProperty.addColumn("限制", "limits", "width=150px");
						tableProperty.addColumn("租赁", "hires", "width=150px");
						tableProperty.addColumn("文件", "files", "width=150px");
					}
					htmlView = HtmlTableUtil.createHtmlTable(tableProperty, houseList);
					htmlView = htmlView.replaceAll("'this'", "this");
//					System.out.println(htmlView);
				}
				map.put("result", "success");
				map.put("message", "");
				map.put("htmlView", htmlView);
			}
			map.put("data", houseList.size());
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return createMessage(-1, "异常信息："+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 功能描述：质量监督办理信息跳转
	 * @return
	 */
	@RequestMapping(value = "/checkInfoFrame")
	public String checkInfoFrame(HttpServletRequest request,HttpSession sess) {
		try{
			String transactionID=request.getParameter("transactionID");
			request.setAttribute("transactionID", transactionID);
			return "/fhhouse/permit/CheckInfoFrame";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：获取房屋权利信息 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getRights")
	@ResponseBody
	public JSONArray getRights(HttpServletRequest request,HttpSession session) throws Exception {
		Map<String, List<?>> lotMap = new HashMap<String , List<?>>();;
		
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "success");
		String tempHouseID = CharSet.toWebCharSet(request.getParameter("houseID")); 
		String districtIDs = CharSet.toWebCharSet(request.getParameter("districtID")); 
		long houseID = Long.parseLong(tempHouseID);
		int districtID = Integer.parseInt(String.valueOf(districtIDs));
		RealestateFinder houseFinder = new RealestateFinder();
		try {
			//展示临时权利变量
			List<RHouseRightView> permits = new ArrayList<RHouseRightView>();
			List<RHouseRightView> reals = new ArrayList<RHouseRightView>();
			List<RHouseRightView> otherrights = new ArrayList<RHouseRightView>();
			List<RHouseRightView> limits = new ArrayList<RHouseRightView>();
			List<RHouseRightView> hires = new ArrayList<RHouseRightView>();
			List<RHouseRightView> files = new ArrayList<RHouseRightView>();
			// 现势权属信息
			List<RHouseRightView> rights = houseFinder.searchRHouseRightView(houseID,districtID);
			//如果不存在现势房屋产权关联，或者是IsPrecert=1, 或YGFlag=1则加载宗地上的权利信息 
			boolean realFlag = true;
			boolean isprecertFlag = false;
			boolean ygFlagFlag = false;
			for (RHouseRightView right : rights) {
				int righttype = right.getRightType();//房地所有权/使用权
				String isprecert = String.valueOf(right.getIsPrecert());
				String ygFlag = String.valueOf(right.getYgFlag());
				this.addRight(righttype, isprecert, right, reals, permits, otherrights, limits, hires, files);
				if (righttype == Constant.RightType_Real)
					realFlag = false;
				if ("1".equals(isprecert))
					isprecertFlag = true;
				if ("1".equals(ygFlag))
					ygFlagFlag = true;
			}
			//加载宗地上的权利信息 
			if (realFlag || isprecertFlag || ygFlagFlag || rights.isEmpty()) {
				List<String> tempHouseIDs = new ArrayList<String>();
				tempHouseIDs.add(tempHouseID);
				List<CHFlatVO> houses = houseFinder.findHousesForCreate(tempHouseIDs, districtID);
				String lotID = String.valueOf(houses.get(0).getLotID());
				this.addLotRight(lotID, districtID, reals, permits, otherrights, limits, hires, files, lotMap, houses.get(0));
			}
			// 临时权属信息
			List<TempRHouseRightView> temprights = houseFinder.searchTempRHouseRightView(houseID,districtID);
			/*注销独立的视图查询-临时
			TempRHouseRegistOutView tempRRegistOut = new TempRHouseRegistOutView();
			tempRRegistOut.setHouseID(houseID);
			tempRRegistOut.setDistrictID(districtID);
			List<RHouseRegistOutView> rRegistList = houseFinder.searchByTemplate(tempRRegistOut);
			for (RHouseRegistOutView tempR:rRegistList){
				TempRHouseRightView tempRight = new TempRHouseRightView();
				tempRight.setHouseID(tempR.getHouseID());
				tempRight.setTransactionID(tempR.getTransactionID());
				tempRight.setRightID(tempR.getRegistOutID());
				tempRight.setIsFinish(tempR.getIsFinish());
				tempRight.setDistrictID(tempR.getDistrictID());
				tempRight.setRightType(tempR.getRightType());
				tempRight.setIsPrecert(tempR.getIsPrecert());
				tempRight.setYgFlag(tempR.getYgFlag());
				tempRight.setDbFlag(0);
				temprights.add(tempRight);
			}*/
			for(TempRHouseRightView tempright : temprights) {
				int righttype = tempright.getRightType();
				String isprecert = String.valueOf(tempright.getIsPrecert());
				this.addRight(righttype, isprecert, tempright, reals, permits, otherrights, limits, hires, files);
			}


			map.put("reals", reals);
//			map.put("permits", permits);
			map.put("others", otherrights);
			map.put("limits", limits);
			map.put("hires", hires);
			map.put("files", files);
			reals = null;
			permits = null;
			otherrights = null;
			limits = null;
			hires = null;
			files = null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		json = JSONArray.fromObject(map);
//		System.out.println(json.toString());
		return json;
	}
	
	/**
	 * 功能描述：查询权利信息
	 * @param righttype
	 * @param isprecert
	 * @param tempright
	 * @param reals
	 * @param permits
	 * @param otherrights
	 * @param limits
	 * @param hires
	 * @param files
	 * @throws Exception
	 */
	public void addRight(int righttype,String isprecert,RHouseRightView tempRight,List<RHouseRightView> reals,List<RHouseRightView> permits,List<RHouseRightView> otherrights,List<RHouseRightView> limits,List<RHouseRightView> hires,List<RHouseRightView> files) throws Exception {
		if(righttype == Constant.RightType_Real && !"1".equals(isprecert)) {
			// 产权
			reals.add(tempRight);
		} else if( (righttype == Constant.RightType_Real && "1".equals(isprecert)) || righttype == Constant.RightType_Permit || righttype == 81) {
			// 预售/许可
			permits.add(tempRight);
		} else if(righttype == Constant.RightType_Other) {
			// 抵押
			otherrights.add(tempRight);
		} else if(righttype == Constant.RightType_Limit) {
			// 限制
			limits.add(tempRight);
		} else if(righttype == Constant.RightType_Hire) {
			// 租赁
			hires.add(tempRight);
		} else if(righttype == Constant.RightType_FileReg || righttype == Constant.RightType_Servitude) {
			// 文件
			files.add(tempRight);
			} else if(righttype == Constant.RightType_Dissent) {
				
				/* 异议
			// 异常也是针对不同权利
			long rightID = tempRight.getRightID();
			int districtID = tempRight.getDistrictID();
			RightDissentVO dissent = null;
			int DbFlag = tempRight.getDbFlag();
			if(DbFlag == 1) {//现势
				dissent = (RightDissentVO) rightFinder.getRight(rightID, districtID, Constant.RightStatus_Current);
				tempRight.setDbFlag(Constant.RightStatus_Current);
			} else {//临时
				dissent = (RightDissentVO) rightFinder.getRight(rightID, districtID, Constant.RightStatus_Temp);
				tempRight.setDbFlag(Constant.RightStatus_Temp);
			}
			//tempRight.setRightType(dissent.getDissentRightType());
			if(dissent != null) {
				if(tempRight.getRightType() == Constant.RightType_Registout) {
					tempRight.setDbFlag(0);
				}
				this.addRight(dissent.getDissentRightType(), isprecert, tempRight, reals, permits, otherrights, limits, hires, files);
			}
			*/
		} else if(righttype == Constant.RightType_Registout) {
			/*注销只查临时
			if(tempRight.getDbFlag() == 0) {
				long rightID = tempRight.getRightID();
				RegistoutFinder rightFinder = (RegistoutFinder) RightFactory.getRightFinder(Constant.RightType_Registout);
				RegistoutVO vo = (RegistoutVO) rightFinder.getRight(rightID, Constant.RightStatus_Temp);
				tempRight.setDbFlag(Constant.RightStatus_Temp);
				if(vo != null) {
					//如果登记小类是405，则在预售许可中显示注销
					if(vo.getRegistoutRightType() == 1) {
						TransactionVO transactionVO = (TransactionVO) rightFinder.getTypebid(vo.getTransactionID());
						if(transactionVO.getTypeBID() == 405) {
							isprecert = "1";
						}
					}//end
					if(vo.getRegistoutRightType() == Constant.RightType_Dissent) {
						List registoutRightList = rightFinder.getRegistoutRights(rightID, Constant.RightStatus_Temp);
						if(registoutRightList.size() > 0) {
							TempRegistoutRightVO tempRegistoutRightVO = (TempRegistoutRightVO) registoutRightList.get(0);
							rightID = tempRegistoutRightVO.getPushRightID();
							tempRight.setRightID(rightID);
							RHouseRightView rHouseRightView = new RHouseRightView();
							rHouseRightView.setHouseID(tempRight.getHouseID());
							rHouseRightView.setDistrictID(tempRight.getDistrictID());
							rHouseRightView.setRightType(tempRight.getRightType());
							rHouseRightView.setTransactionID(tempRight.getTransactionID());
							rHouseRightView.setRightID(tempRight.getRightID());
							rHouseRightView.setIsFinish(tempRight.getIsFinish());
							rHouseRightView.setIsPrecert(tempRight.getIsPrecert());
							rHouseRightView.setYgFlag(tempRight.getYgFlag());
							rHouseRightView.setRightStatus(tempRight.getRightStatus());
							rHouseRightView.setPutinTransactionID(tempRight.getPutinTransactionID());
							rHouseRightView.setLotFlag(tempRight.getLotFlag());
							tempRight = rHouseRightView;
						}
					}
					this.addRight(vo.getRegistoutRightType(), isprecert, tempRight, reals, permits, otherrights, limits, hires, files);
				}
			}*/
		} else {
			reals.add(tempRight);
		}
	}
	
	/**
	 * 功能描述：TODO
	 * @param lotID
	 * @param districtID
	 * @param reals
	 * @param permits
	 * @param otherrights
	 * @param limits
	 * @param hires
	 * @param files
	 * @param lotMap
	 */
	private void addLotRight(String lotID,int districtID,List<RHouseRightView> reals,List<RHouseRightView> permits,List<RHouseRightView> otherrights,List<RHouseRightView> limits,List<RHouseRightView> hires,List<RHouseRightView> files,Map lotMap,CHFlatVO house) {
		try {
			RealestateFinder houseFinder = new RealestateFinder();
			// 现势权属信息
			RLandRightView realtemplate = new RLandRightView();
			realtemplate.setLotID(Long.parseLong(lotID));
			realtemplate.setDistrictID(districtID);
			List<RLandRightView> rights;
			if (lotMap.containsKey(lotID)) {
				rights = (List<RLandRightView>) lotMap.get(lotID);
			} else {
				rights = houseFinder.searchRLandRightView(Long.parseLong(lotID),districtID);
				lotMap.put(lotID, rights);
			}

			for (RLandRightView right : rights) {
				//判断该宗地权利是否属于该房屋对应的用途上
				boolean load = houseFinder.isHouseInLotUses(right, house);
				if (!load) {
					continue;
				}
				String isprecert = String.valueOf(right.getIsPrecert());
				RHouseRightView houseRight = new RHouseRightView();
				houseRight.setHouseID(right.getLotID());
				houseRight.setDistrictID(right.getDistrictID());
				houseRight.setRightType(right.getRightType());
				houseRight.setTransactionID(right.getTransactionID());
				houseRight.setRightID(right.getRightID());
				houseRight.setIsFinish(right.getIsFinish());
				houseRight.setIsPrecert(right.getIsPrecert());
				houseRight.setYgFlag(right.getYgFlag());
				houseRight.setRightStatus(right.getRightStatus());
				houseRight.setDbFlag(right.getDbFlag());
				houseRight.setLotFlag(1);
				this.addRightForLot(right.getRightType(), isprecert, houseRight, reals, permits, otherrights, limits, hires, files);
			}
			// 临时权属信息
			List<TempRLandRightView> temprights;
			if (lotMap.containsKey(lotID + "temp")) {
				temprights = (List<TempRLandRightView>) lotMap.get(lotID + "temp");
			} else {
				temprights = houseFinder.searchTempRLandRightView(Long.parseLong(lotID),districtID);
				lotMap.put(lotID + "temp", temprights);
			}

			for (TempRLandRightView tempright : temprights) {
				//TODO 判断该宗地权利是否属于该房屋对应的用途上
				boolean load = houseFinder.isHouseInLotUses(tempright, house);
				if (!load) {
					continue;
				}
				String isprecert = String.valueOf(tempright.getIsPrecert());
				TempRHouseRightView houseRight = new TempRHouseRightView();
				houseRight.setHouseID(tempright.getLotID());
				houseRight.setDistrictID(tempright.getDistrictID());
				houseRight.setRightType(tempright.getRightType());
				houseRight.setTransactionID(tempright.getTransactionID());
				houseRight.setRightID(tempright.getRightID());
				houseRight.setIsFinish(tempright.getIsFinish());
				houseRight.setIsPrecert(tempright.getIsPrecert());
				houseRight.setYgFlag(tempright.getYgFlag());
				houseRight.setRightStatus(tempright.getRightStatus());
				houseRight.setDbFlag(tempright.getDbFlag());
				houseRight.setLotFlag(1);
				this.addRightForLot(tempright.getRightType(), isprecert, houseRight, reals, permits, otherrights, limits, hires, files);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能描述：TODO
	 * @param righttype
	 * @param isprecert
	 * @param tempright
	 * @param reals
	 * @param permits
	 * @param otherrights
	 * @param limits
	 * @param hires
	 * @param files
	 * @throws Exception
	 */
	public void addRightForLot(int righttype,String isprecert,RHouseRightView tempRight,List<RHouseRightView> reals,List<RHouseRightView> permits,List<RHouseRightView> otherrights,List<RHouseRightView> limits,List<RHouseRightView> hires,List<RHouseRightView> files) throws Exception {
		if (righttype == Constant.RightType_LandOwnerReal || righttype == Constant.RightType_LandUseReal) {
			// 产权
			reals.add(tempRight);
		} else if (righttype == Constant.RightType_Other) {
			// 抵押
			otherrights.add(tempRight);
		} else if (righttype == Constant.RightType_Limit) {
			// 限制
			limits.add(tempRight);
		} else if (righttype == Constant.RightType_FileReg || righttype == Constant.RightType_Servitude) {
			// 文件
			files.add(tempRight);
		}
	}
	
	/**
	 * 功能描述：删除房屋关联
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteHouseRelate")
	@ResponseBody
	public JSONArray deleteHouseRelate(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "fail");
		map.put("message", "");
		try{
			SmUserVO userVO = (SmUserVO) session.getAttribute("LgUser");
			PermitBO permitBO = new PermitBO();
			String cmd = request.getParameter("cmd");
			String type = request.getParameter("type");
			String permitID = request.getParameter("permitID"); 
			if("delall".equals(type)){//删除全部
				
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("permitID", "=", permitID));
				
				List<RHousePermitVO> houseList = permitBO.search(RHousePermitVO.class, params);
				if(houseList!=null && houseList.size()>0){
					for(RHousePermitVO vo:houseList){
						long houseID = vo.getHouseID();
						
						//检查houseID有没有签合同，签合同的不能删除
						params = new ArrayList<MetaFilter>();
						params.add(new MetaFilter("houseID", "=", houseID));
						params.add(new MetaFilter("status", "<>", "5"));
						params.add(new MetaFilter("status", "<>", "7"));
						List dealList = permitBO.search(ContractDealVO.class, params);
						if(dealList!=null && dealList.size() > 0){//存在合同
							map.put("result", "fail");
							map.put("message", "该房屋编号"+houseID+"存在已签的合同，不能删除！");
							return JSONArray.fromObject(map);
						}else{
							//不存在合同，则删除
							params = new ArrayList<MetaFilter>();
							params.add(new MetaFilter("permitID", "=", permitID));
							params.add(new MetaFilter("houseID", "=", houseID));
							long count = permitBO.delete(RHousePermitVO.class, params);
							//删除向HouseSaleLog_T表插入0
							if(cmd != null && "modify".equals(cmd)){
								params = new ArrayList<MetaFilter>();
								params.add(new MetaFilter("houseID", "=", houseID));
								params.add(new MetaFilter("permitID", "=", permitID));
								
								List<RHousePermitVO> list = permitBO.search(RHousePermitVO.class, params);
								permitBO.addHouseSaleLog(list, userVO.getUserId(), "维护删除", 0);
							}
							
							//20190308  added 增加保存房屋关联的时候，更新permit_t表的修改时间和修改人
							PermitVO permit = new PermitVO();
							permit.setPermitID(Long.parseLong(permitID));
							permit.setModifyDate(DateUtil.getDBSysDate());
							permit.setModifyUser(userVO.getUserId());
							permitBO.update(permit);
							
							if(count <= 0){
								map.put("result", "fail");
								map.put("message", "删除失败！");
								return JSONArray.fromObject(map);
							}else{
								map.put("result", "success");
								map.put("message", "删除成功！");
								System.out.println("用户名称："+userVO.getLoginName()+"删除了"+permitID+"的房屋关联"+houseID);
							}
						}
					}
				}else{
					map.put("result", "success");
					map.put("message", "没有房屋关联！");
					return JSONArray.fromObject(map);
				}
			}else{//删除单个
				String houseIDs = request.getParameter("houseID");
				String[] houseArr = houseIDs.split(",");
				for(String houseID:houseArr){
					//检查houseID有没有签合同，签合同的不能删除
					List<MetaFilter> params = new ArrayList<MetaFilter>();
					params.add(new MetaFilter("houseID", "=", houseID));
	//				params.add(new MetaFilter("status", " not in ", "(5,7)"));
					
					List dealList = permitBO.search(ContractDealVO.class, params);
					if(dealList!=null && dealList.size() > 0){
						map.put("result", "fail");
						map.put("message", "该房屋编号"+houseID+"存在已签的合同，不能删除！");
						break;
					}else{//不存在合同，则继续删除。
						List<MetaFilter> whereFields = new ArrayList<MetaFilter>();
						whereFields.add(new MetaFilter("houseID", "=", houseID));
						whereFields.add(new MetaFilter("permitID", "=", permitID));
							
						long count = permitBO.delete(RHousePermitVO.class, whereFields);
						
						//删除向HouseSaleLog_T表插入0
						if(cmd != null && "modify".equals(cmd)){
							params = new ArrayList<MetaFilter>();
							params.add(new MetaFilter("houseID", "=", houseID));
							params.add(new MetaFilter("permitID", "=", permitID));
							
							List<RHousePermitVO> houseList = permitBO.search(RHousePermitVO.class, params);
							permitBO.addHouseSaleLog(houseList, userVO.getUserId(), "维护单个删除", 0);
						}
						
						//20190308  added 增加保存房屋关联的时候，更新permit_t表的修改时间和修改人
						PermitVO permit = new PermitVO();
						permit.setPermitID(Long.parseLong(permitID));
						permit.setModifyDate(DateUtil.getDBSysDate());
						permit.setModifyUser(userVO.getUserId());
						permitBO.update(permit);
						
						if(count <= 0){
							map.put("result", "fail");
							map.put("message", "删除"+houseID+"失败！");
							return JSONArray.fromObject(map);
						}else{
							map.put("result", "success");
							map.put("message", "删除成功");
						}
					}
				}
			}
			
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return createMessage(-1, "异常信息："+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 功能描述：保存新增预售许可
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doPermitSave")
	public String doPermitSave(HttpServletRequest request,HttpSession session,PermitVO permit) {
		try{
			SmUserVO userVO = (SmUserVO) session.getAttribute("LgUser");
			PermitBO permitBO = new PermitBO();
			String cmd = request.getParameter("cmd");
			String transactionID = request.getParameter("transID");
			if("add".equals(cmd)){
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("transactionID", "=", transactionID));
				List<PermitVO> permitList=permitBO.search(PermitVO.class, params);
				if(permitList.size()>0){
					throw new Exception("请不要频繁点击保存按钮！");
				}
				
				
				//新增预售许可保存
				long permitID = permitBO.add(permit);
				if(permitID <= 0){
					throw new Exception("新增预售许可失败！");
				}
				cmd = "edit";
				
				//更新主表的区县值。
				ApplyVO apply = new ApplyVO();
				apply.setTransactionID(Long.parseLong(transactionID));
				apply.setDistrictID(permit.getDistrictID());
				permitBO.update(apply);
			}else{
				permit.setModifyDate(DateUtil.getDBSysDate());
				permit.setModifyUser(userVO.getUserId());
				long count = permitBO.update(permit);
				if(count <= 0){
					throw new Exception("更新预售许可失败！");
				}
				//更新主表的区县值。
				ApplyVO apply = new ApplyVO();
				apply.setTransactionID(Long.parseLong(transactionID));
				apply.setDistrictID(permit.getDistrictID());
				permitBO.update(apply);
				
				//获取审核意见
				apply.setTransactionID(Long.parseLong(transactionID));
				apply = (ApplyVO) permitBO.find(apply);
				request.setAttribute("passOpinion", apply.getPassOpinion());
			}
			
			//更新收件表的区县
			List<MetaField> whereFields = new ArrayList<MetaField>();
			whereFields.add(new MetaField("transactionID", transactionID));
			//更新字段
			List<MetaField> fieldList = new ArrayList<MetaField>();
			fieldList.add(new MetaField("districtID",permit.getDistrictID()));
			permitBO.update(ApplyFileVO.class, whereFields, fieldList);
			
			
			request.setAttribute("cmd", cmd);
			request.setAttribute("permit", permit);
			return "/fhhouse/permit/PermitEdit";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：删除预售许可
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doPermitDelByPermitID")
	@ResponseBody
	public JSONArray doPermitDelByPermitID(HttpServletRequest request,HttpSession session,PermitVO permit) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "fail");
		map.put("message", "");
		try{
			SmUserVO userVO = (SmUserVO) session.getAttribute("LgUser");
			PermitBO permitBO = new PermitBO();
			String permitID = request.getParameter("permitID"); //许可证号
			
			//先查询出来主表transactionID
			long transactionID = 0;
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("permitID", "=", permitID));
			List<PermitVO> list = permitBO.search(PermitVO.class, params);
			if(list!=null && list.size()>0){
				PermitVO vo = list.get(0);
				transactionID = vo.getTransactionID();
			}
			//更新permit_t表状态-9
			List<MetaField> fields = new ArrayList<MetaField>();
			fields.add(new MetaField("status", -9));
			fields.add(new MetaField("modifyUser", userVO.getUserId()));
			fields.add(new MetaField("modifyDate", DateUtil.getDBSysDate()));
			int count1 = permitBO.update(PermitVO.class, Long.parseLong(permitID), fields);
			
			//更新房屋关联表
			int ret = permitBO.deleteRHousePermitVO(permitID,transactionID);
			
			//更新applyVO表数据state = -9
			fields = new ArrayList<MetaField>();
			fields.add(new MetaField("state", -9));
			fields.add(new MetaField("modifyUser", userVO.getUserId()));
			fields.add(new MetaField("modifyDate", DateUtil.getDBSysDate()));
			fields.add(new MetaField("memo", "删除"));
			int count2 = permitBO.update(ApplyVO.class, transactionID, fields);
			
			if(count1>0 && count2>0 && ret > 0){
				map.put("result", "success");
				map.put("message", "删除成功！");
			}
			
			return JSONArray.fromObject(map);
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "error");
			map.put("message", "异常信息："+e.getLocalizedMessage());
			json = JSONArray.fromObject(map);
			return json;
		}
	}
	
	/**
	 * 功能描述：保存房屋关联
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/doSaveHouseRelate")
	@ResponseBody
	public JSONArray doSaveHouseRelate(HttpServletRequest request,HttpSession session) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "fail");
		map.put("message", "");
		try{
			SmUserVO userVO = (SmUserVO) session.getAttribute("LgUser");
			PermitBO permitBO = new PermitBO();
			RealestateFinder finder = new RealestateFinder();
			String permitID = request.getParameter("permitID"); //许可证号
			String houseIDs = request.getParameter("houseIDs"); //选择的房屋编号
			String transactionID = request.getParameter("transID"); //案件编号
			String cmd = request.getParameter("cmd"); //操作类型，cmd==‘modify’的时候，要新增日志表housesalelog_t表记录
			
			RealestateBO rBo = new RealestateBO();
			long count = 0;
			if(!"".equals(houseIDs) && houseIDs.length() > 0 && houseIDs.indexOf(",") != -1){
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				String houseID[] = houseIDs.split(",");
				List<RHousePermitVO>  houseList = new ArrayList<RHousePermitVO>();
				for(int i = 0;i<houseID.length;i++){
					long houseId = Long.parseLong(houseID[i]);
					//1、检查房屋必须要有土地产权登记
					boolean existLandReal= finder.checkLandRealExist(houseId);
					if(!existLandReal){//不存在，则跳过当前房屋。
						return createMessage(-1, "房屋编号："+houseId+"没有土地初始登记！");
					}

					//2、检查是否已存在合同信息(非撤销的合同)，已存在合同的房屋不允许添加
					/*ContractDealVO dealVO = new ContractDealVO();
					dealVO.setHouseID(houseId);
					List dealList = permitBO.searchByTemplate(dealVO);
					boolean existContract = permitBO.checkContractInfo(houseId);*/
					
					params = new ArrayList<MetaFilter>();
					params.add(new MetaFilter("houseID", "=", houseId));
					params.add(new MetaFilter("status", "<>", "5"));
					params.add(new MetaFilter("status", "<>", "7"));
					
					List dealList = permitBO.search(ContractDealVO.class, params);
					if(dealList!=null && dealList.size() > 0){
						return createMessage(-1, "房屋编号："+houseId+"存在有效的合同！");
					}
					
					//先检查是否存在于房屋关联表中
					params = new ArrayList<MetaFilter>();
					params.add(new MetaFilter("houseID", "=", houseId));
					
					List<RHousePermitVO> houselist = permitBO.search(RHousePermitVO.class, params);
					if(houselist!=null && houselist.size() > 0){
						RHousePermitVO vo = houselist.get(0);
						return createMessage(-1, "房屋编号："+houseId+"已经被预售许可编号"+vo.getTransactionID()+"关联！");
					}else{
						//添加房屋关联。
						CHFlatVO house = rBo.findCHFlat(houseId);
						RHousePermitVO vo = new RHousePermitVO();
						vo.setPermitID(Long.parseLong(permitID));
						vo.setHouseID(houseId);
						vo.setDistrictID(Integer.parseInt(house.getDistrictID()));
						vo.setTransactionID(Long.parseLong(transactionID));
						vo.setModifyDate(DateUtil.getDBSysDate());
						vo.setModifyUser(userVO.getUserId());
						vo.setIsFinish(0);
						vo.setSaleFlag(1);//1,可售0，不可售
						houseList.add(vo);
					}
				}
				//批量添加
				if(houseList != null && houseList.size() > 0){
					count = permitBO.addBatch(houseList);
					System.out.println("用户："+userVO.getLoginName()+"添加了"+count+"条房屋关联！");
					if("modify".equals(cmd)){
						permitBO.addHouseSaleLog(houseList, userVO.getUserId(), "维护添加", 1);
					}
					
					//20190308  added 增加保存房屋关联的时候，更新permit_t表的修改时间和修改人
					PermitVO permit = new PermitVO();
					permit.setPermitID(Long.parseLong(permitID));
					permit.setModifyDate(DateUtil.getDBSysDate());
					permit.setModifyUser(userVO.getUserId());
					permitBO.update(permit);
				}
				
			}
			return createMessage(1, "保存成功！");
		}catch (Exception e){
			e.printStackTrace();
			return createMessage(-1, "异常信息："+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 功能描述：预售许可证统计界面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/gotoPermitStatis")
	public String gotoPermitStatis(HttpServletRequest request,HttpSession session) {
		try{
			SmUserVO userVO = (SmUserVO) session.getAttribute("LgUser");
			return "/fhhouse/permit/PermitStatisFrame";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	
	/**
	 * 功能描述：预售许可证统计列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/gotoPermitStatisList")
	public String gotoPermitStatisList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			List<Object> userDistrictList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			
			PermitBO permitBO = new PermitBO();
			List<PermitVO> list = permitBO.queryPermitStatis(startDate,endDate);
			double totalArea = 0;//房屋总面积
			double totalAreaFor21 = 0;//住宅房屋总面积
			double total = 0;//总套数
			double totalFor21 = 0;//住宅总套数
			if(list != null && list.size() > 0){
				for(PermitVO pvo : list){
						totalArea += pvo.getLBuildArea();
						totalAreaFor21 += pvo.getInRoomArea();
						total += pvo.getPSet1();
						totalFor21 += pvo.getHSets();
				}
			}
			
			List<String> linkparam = new ArrayList<String>();
			linkparam.add("permitID");
			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("收件号", "transactionID");
			tableProperty.addColumn("坐落", "location");
			tableProperty.addColumn("项目名称", "projectName");
			tableProperty.addColumn("企业名称", "companyName");
			tableProperty.addColumn("房屋数量", "pset1");
			tableProperty.addColumn("总面积", "lbuildarea");
			tableProperty.addColumn("住宅数量", "hsets");
			tableProperty.addColumn("住宅面积", "inroomarea");

			String htmlView = HtmlTableUtil.createHtmlTable(tableProperty, list);
			
			String headHtml = "<div align='center'><font size='4'>"+startDate+"到"+endDate+"，共办理预售许可证"+list.size()+"条</font></div>";
			
			String totalHtml = "<div align='left'><table><font size='4'><tr><td align='right'>合计>房屋总套数：</td><td>"+total+"</td></div>";
			/*totalHtml += "<td align='right'>&nbsp;&nbsp;房屋总面积：</td><td>"+totalArea+"</td>";
			totalHtml += "<td align='right'>&nbsp;&nbsp;住宅总套数：</td><td>"+totalFor21+"</td>";
			totalHtml += "<td align='right'>&nbsp;&nbsp;住宅总面积：</td><td>"+totalAreaFor21+"</td>";*/
			totalHtml = totalHtml + "<td align='right'>&nbsp;&nbsp;房屋总面积：</td><td>" + Formatter.formatNormal(totalArea) + "</td>";
		    totalHtml = totalHtml + "<td align='right'>&nbsp;&nbsp;住宅总套数：</td><td>" + Formatter.formatNormal(totalFor21) + "</td>";
		    totalHtml = totalHtml + "<td align='right'>&nbsp;&nbsp;住宅总面积：</td><td>" + Formatter.formatNormal(totalAreaFor21) + "</td>";
			
			request.setAttribute("htmlView", headHtml+htmlView+totalHtml);
			return "/fhhouse/permit/PermitStatisList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
}

