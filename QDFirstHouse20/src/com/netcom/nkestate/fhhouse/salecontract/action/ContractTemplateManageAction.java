package com.netcom.nkestate.fhhouse.salecontract.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.vo.EnterpriseQualifyVO;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.fhhouse.project.vo.PresellVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.fhhouse.salecontract.bo.ContractTemplateManageBO;
import com.netcom.nkestate.fhhouse.salecontract.bo.SignContractBO;
import com.netcom.nkestate.fhhouse.salecontract.vo.AttachTemplateVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractTemplateCsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractTemplateYsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellerTemplateVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SubTemplateVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SupportVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.vo.DictVO;
import com.netcom.nkestate.system.bo.DictionaryBO;
import com.netcom.nkestate.system.vo.SmUserVO;

/**
 * 管理模块-合同模板管理Action
 */
@Controller
@RequestMapping(value = "/outer/contracttemplatemanage")
public class ContractTemplateManageAction extends BaseAction {

	/**
	 * 功能描述 ：转到合同主模板查询
	 * @return
	 */
	@RequestMapping(value = "/queryTemplate")
	public String queryTemplate(HttpServletRequest request) {
		try{
			return "/fhhouse/salecontract/manage/TemplateQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：查询合同主模板列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/queryTemplateList")
	public String queryTemplateList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String projectID = request.getParameter("projectID");
			String startID = request.getParameter("startID");
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String district = this.getUserSqlDistricts(vo.getRegionId());
			ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();
			if(projectID == null || "".equals(projectID)){
				projectID = "";
				List<DictVO> list = ctmBo.queryUserProjectJson(vo.getUserId());
				if(list != null && list.size() > 0){
					for(DictVO dictVO : list){
						projectID += dictVO.getCode() + ",";
					}
					if(projectID.length() > 1){
						projectID = projectID.substring(0, projectID.length() - 1);
					}
				}else{
					projectID = "-1";
				}
			}
			List<ContractTemplateYsVO> list = ctmBo.queryTemplateList(projectID, startID, district, page);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("templateID");
			linkparam.add("type");
			List<String> linkparam1 = new ArrayList<String>();
			linkparam1.add("templateID");

			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(false);
			tableProperty.addSelectControl("radio", "tempID", linkparam1, null);
			tableProperty.addColumn("项目名称", "projectname");
			tableProperty.addColumn("开盘编号", "start_code");
			tableProperty.addColumn("模板名称", "tempname", "viewTemplate", linkparam, "查看", null);
			tableProperty.addColumn("模板类型", "typeName");
			tableProperty.addColumn("修改时间", "updateTime", "Date", "yyyy-MM-dd", null);
			tableProperty.addColumn("修改", "修改", "doModify", linkparam, "修改", null);
			tableProperty.addColumn("删除", "删除", "doDelete", linkparam, "删除", null);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);
			return "/fhhouse/salecontract/manage/TemplateList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：新增主模板
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addTemplate")
	public String addTemplate(HttpServletRequest request,HttpSession session) {
		try{
			String type = request.getParameter("type");
			String projectID = request.getParameter("projectID");
			String fujian0 = request.getParameter("fujian0");
			String fujian1 = request.getParameter("fujian1");

			request.setAttribute("type", type);
			String startID = request.getParameter("startID");
			String opera = request.getParameter("opera"); //"view":查看，"modify":修改，"add":新增
			request.setAttribute("opera", opera);
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");

			ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();
			
			String person = vo.getLoginName();
			long dates = Long.parseLong(DateUtil.getSysDateYYYYMMDD());
			long times = Long.parseLong(DateUtil.getSysDateHHMMSS());

			long i = 0;
			ContractTemplateYsVO ptVo = new ContractTemplateYsVO();
			ContractTemplateCsVO stVo = new ContractTemplateCsVO();

			if(type != null && "1".equals(type)){
				//预售合同模板
				if(projectID != null && startID != null){

					ptVo.setProjectID(Integer.parseInt(projectID));
					ptVo.setStartID(Long.parseLong(startID));
					ProjectVO pVo = (ProjectVO) ctmBo.find(ProjectVO.class, Long.parseLong(projectID));
					if(pVo != null){
						String projectName = pVo.getProjectName();
						if(projectName.length() > 20){
							projectName = projectName.substring(0, 20);
						}
						ptVo.setTempname(projectName);
						request.setAttribute("Tempname", pVo.getProjectName());
						//获取项目的区县编号
						request.setAttribute("proDis", pVo.getDistrictID());
					}
				}
				ptVo.setCrePerson(person);
				ptVo.setCreDate(dates);
				ptVo.setCreTime(times);
				ptVo.setUpdPerson(person);
				ptVo.setUpdDate(dates);
				ptVo.setUpdTime(times);
			}else{
				//出售合同模板
				if(projectID != null && startID != null){
					stVo.setProjectID(Integer.parseInt(projectID));
					stVo.setStartID(Long.parseLong(startID));
					ProjectVO pVo = (ProjectVO) ctmBo.find(ProjectVO.class, Long.parseLong(projectID));
					if(pVo != null){
						String projectName = pVo.getProjectName();
						if(projectName.length() > 20){
							projectName = projectName.substring(0, 20);
						}
						stVo.setTempname(projectName);
						request.setAttribute("Tempname", pVo.getProjectName());
						//获取项目的区县编号			
						request.setAttribute("proDis", pVo.getDistrictID());
					}
				}
				stVo.setCrePerson(person);
				stVo.setCreDate(dates);
				stVo.setCreTime(times);
				stVo.setUpdPerson(person);
				stVo.setUpdDate(dates);
				stVo.setUpdTime(times);
			}
			//从企业表中获取甲方信息
			SignerVO signerVO = (SignerVO) ctmBo.find(SignerVO.class, vo.getUserId());
			EnterpriseQualifyVO eqvo = (EnterpriseQualifyVO) ctmBo.find(EnterpriseQualifyVO.class, Long.parseLong(signerVO.getComp_ID()));
			request.setAttribute("eqvo", eqvo);
			
			//甲方模板
			SellerTemplateVO sertempVo = new SellerTemplateVO();
			sertempVo.setCompID(vo.getOrgID());
			sertempVo.setSellerName(eqvo.getName());
			sertempVo.setSellerAddress(eqvo.getBizadr());
			sertempVo.setSellerPostcode(eqvo.getPost());
			sertempVo.setSellerBizcardcode(eqvo.getBizregister_Num());
			sertempVo.setSellerCertcode(eqvo.getAptitudeNum());
			sertempVo.setSellerDelegate(eqvo.getDelegate());
			sertempVo.setSellerDelegate(eqvo.getDelegate());
			sertempVo.setSellerDlgCall(eqvo.getDlg_Call());
			sertempVo.setSellerProxy(eqvo.getProxy());
			sertempVo.setSellerProxyCall(eqvo.getProxy_Call());
			sertempVo.setCrePerson(person);
			sertempVo.setCreDate(dates);
			sertempVo.setCreTime(times);
			sertempVo.setUpdPerson(person);
			sertempVo.setUpdDate(dates);
			sertempVo.setUpdTime(times);
			
			//附件模板
			List<AttachTemplateVO> atempList = new ArrayList<AttachTemplateVO>();
			if(type != null && "1".equals(type)){
				int attachSerial[] = { FHConstant.ATTACH_SERIAL_PFJ2, FHConstant.ATTACH_SERIAL_PFJ3, FHConstant.ATTACH_SERIAL_PFJ4, FHConstant.ATTACH_SERIAL_PFJ5, FHConstant.ATTACH_SERIAL_PFJ8, FHConstant.ATTACH_SERIAL_PFJ9, FHConstant.ATTACH_SERIAL_PFJ10, FHConstant.ATTACH_SERIAL_PFJ11 };
				for(int j = 0; j < attachSerial.length; j++){
					AttachTemplateVO atempVo = new AttachTemplateVO();
					atempVo.setSerial(attachSerial[j]);
					atempVo.setContent("".getBytes());
					atempVo.setCrePerson(person);
					atempVo.setCreDate(dates);
					atempVo.setCreTime(times);
					atempVo.setUpdPerson(person);
					atempVo.setUpdDate(dates);
					atempVo.setUpdTime(times);
					atempList.add(atempVo);
				}
			}else{
				int attachSerial[] = { FHConstant.ATTACH_SERIAL_SFJ2, FHConstant.ATTACH_SERIAL_SFJ3, FHConstant.ATTACH_SERIAL_SFJ4, FHConstant.ATTACH_SERIAL_SFJ5, FHConstant.ATTACH_SERIAL_SFJ6,
						FHConstant.ATTACH_SERIAL_SFJ9,FHConstant.ATTACH_SERIAL_SFJ10, FHConstant.ATTACH_SERIAL_SFJ11, FHConstant.ATTACH_SERIAL_SFJ12 };
				for(int j = 0; j < attachSerial.length; j++){
					AttachTemplateVO atempVo = new AttachTemplateVO();
					atempVo.setSerial(attachSerial[j]);
					atempVo.setContent("".getBytes());
					atempVo.setCrePerson(person);
					atempVo.setCreDate(dates);
					atempVo.setCreTime(times);
					atempVo.setUpdPerson(person);
					atempVo.setUpdDate(dates);
					atempVo.setUpdTime(times);
					atempList.add(atempVo);
				}
			}
			i = ctmBo.addTemplate(ptVo, stVo, sertempVo, atempList, type);

			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("templateID", "=", i));
			//获取附件模板信息
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("serial", MetaOrder.ASC));
			List<AttachTemplateVO> atList = ctmBo.search(AttachTemplateVO.class, params, orders, null);
			Map<String , String> map = new HashMap<String , String>();
			if(atList != null && atList.size() > 0){
				for(AttachTemplateVO attachTemplateVO : atList){
					map.put(attachTemplateVO.getSerial() + "", new String(attachTemplateVO.getContent()));
				}
			}
			request.setAttribute("attachmap", map);
			//获取合同甲方模板ID
				List<SellerTemplateVO> sertempList = ctmBo.search(SellerTemplateVO.class, params);
				if(sertempList != null && sertempList.size() > 0){
					request.setAttribute("sellerID", sertempList.get(0).getID());
				}
				if(type != null && "1".equals(type)){
					ptVo = (ContractTemplateYsVO) ctmBo.find(ContractTemplateYsVO.class, i);
				request.setAttribute("templateID", ptVo.getTemplateID());

					if(ptVo != null && ptVo.getStartID() > 0){
					//获取项目名称
					ProjectVO projectVO = (ProjectVO) ctmBo.find(ProjectVO.class, ptVo.getProjectID());
					request.setAttribute("projectName", projectVO.getProjectName());
					if(projectVO != null){
						String projectName = projectVO.getProjectName();
						if(projectName.length() > 20){
							projectName = projectName.substring(0, 20);
						}
						ptVo.setTempname(projectName);
						request.setAttribute("ptVo", ptVo);
					}
					//获取开盘单元，许可证信息
						StartUnitVO suVo = (StartUnitVO) ctmBo.find(StartUnitVO.class, ptVo.getStartID());
						if(suVo != null && suVo.getPresell_ID() > 0){
							PresellVO presellVO = (PresellVO) ctmBo.find(PresellVO.class, suVo.getPresell_ID());
							request.setAttribute("presellDesc", presellVO.getPresell_Desc());
						}
					}
				return "/fhhouse/salecontract/signcontract/ContractTemplateYS";
				}else{
				stVo = (ContractTemplateCsVO) ctmBo.find(ContractTemplateCsVO.class, i);
				request.setAttribute("templateID", stVo.getTemplateID());
					if(stVo != null && stVo.getStartID() > 0){
					//获取项目名称
					ProjectVO projectVO = (ProjectVO) ctmBo.find(ProjectVO.class, stVo.getProjectID());
					request.setAttribute("projectName", projectVO.getProjectName());
					if(projectVO != null){
						String projectName = projectVO.getProjectName();
						if(projectName.length() > 20){
							projectName = projectName.substring(0, 20);
						}
						stVo.setTempname(projectName);
						request.setAttribute("stVo", stVo);
					}
					//获取开盘单元，许可证信息
						StartUnitVO suVo = (StartUnitVO) ctmBo.find(StartUnitVO.class, stVo.getStartID());
						if(suVo != null && suVo.getPresell_ID() > 0){
							PresellVO presellVO = (PresellVO) ctmBo.find(PresellVO.class, suVo.getPresell_ID());
							request.setAttribute("presellDesc", presellVO.getPresell_Desc());
						}
					}
				return "/fhhouse/salecontract/signcontract/ContractTemplateCS";
				}

		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：主模板查看
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/viewTemplate")
	public String viewTemplate(HttpServletRequest request,HttpSession session) {
		try{
			String templateID = request.getParameter("templateID");
			String type = request.getParameter("type");
			request.setAttribute("templateID", templateID);
			request.setAttribute("type", type);
			ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();

			String opera = request.getParameter("opera"); //"view":查看，"modify":修改，"add":新增
			request.setAttribute("opera", opera);
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");

			EnterpriseQualifyVO eqvo = new EnterpriseQualifyVO();

			if(templateID != null && !"".equals(templateID)){
				//从甲方模板中获取甲方信息
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("templateID", "=", templateID));
				List<SellerTemplateVO> sertempList = ctmBo.search(SellerTemplateVO.class, params);
				if(sertempList != null && sertempList.size() > 0){
					SellerTemplateVO sertempVo = sertempList.get(0);
					request.setAttribute("sellerID", sertempVo.getID()); //获取合同甲方模板ID
					if(opera != null && "view".equals(opera)){ //当查看合同模板时，从甲方模板中取值，当新增或修改时从企业表取值
						eqvo.setName(sertempVo.getSellerName());
						eqvo.setComp_ID(sertempVo.getCompID());
						eqvo.setRegadr(sertempVo.getSellerAddress());
						eqvo.setPost(sertempVo.getSellerPostcode());
						eqvo.setBizregister_Num(sertempVo.getSellerBizcardcode());
						eqvo.setAptitudeNum(sertempVo.getSellerCertcode());
						eqvo.setDelegate(sertempVo.getSellerDelegate());
						eqvo.setDlg_Call(sertempVo.getSellerDlgCall());
						eqvo.setProxy(sertempVo.getSellerProxy());
						eqvo.setProxy_Call(sertempVo.getSellerProxyCall());
					}else{
						//从企业表中获取甲方信息
						SignerVO signerVO = (SignerVO) ctmBo.find(SignerVO.class, vo.getUserId());
						eqvo = (EnterpriseQualifyVO) ctmBo.find(EnterpriseQualifyVO.class, Long.parseLong(signerVO.getComp_ID()));
					}
				}else{
					//从企业表中获取甲方信息
					SignerVO signerVO = (SignerVO) ctmBo.find(SignerVO.class, vo.getUserId());
					eqvo = (EnterpriseQualifyVO) ctmBo.find(EnterpriseQualifyVO.class, Long.parseLong(signerVO.getComp_ID()));
				}
				request.setAttribute("eqvo", eqvo);

				//获取附件模板信息
				List<AttachTemplateVO> atList = ctmBo.search(AttachTemplateVO.class, params);
				Map<String , String> map = new HashMap<String , String>();
				if(atList != null && atList.size() > 0){
					for(AttachTemplateVO attachTemplateVO : atList){
						if(attachTemplateVO.getContent() != null){
							map.put(attachTemplateVO.getSerial() + "", new String(attachTemplateVO.getContent()));
						}
					}
				}
				request.setAttribute("attachmap", map);

				if(type != null && "1".equals(type)){
					//获取预售合同模板
					ContractTemplateYsVO ptVo = (ContractTemplateYsVO) ctmBo.find(ContractTemplateYsVO.class, Long.parseLong(templateID));
					request.setAttribute("ptVo", ptVo);
					//获取项目的区县编号
					request.setAttribute("proDis", ctmBo.findProDis(ptVo.getProjectID() + ""));

					if(ptVo != null && ptVo.getStartID() > 0){
						//获取项目名称
						ProjectVO projectVO = (ProjectVO) ctmBo.find(ProjectVO.class, ptVo.getProjectID());
						request.setAttribute("projectName", projectVO.getProjectName());
						//获取开盘单元，许可证信息
						StartUnitVO suVo = (StartUnitVO) ctmBo.find(StartUnitVO.class, ptVo.getStartID());
						if(suVo != null && suVo.getPresell_ID() > 0){
							PresellVO presellVO = (PresellVO) ctmBo.find(PresellVO.class, suVo.getPresell_ID());
							request.setAttribute("presellDesc", presellVO.getPresell_Desc());
						}
					}

					return "/fhhouse/salecontract/signcontract/ContractTemplateYS";
				}else{
					//获取出售合同模板
					ContractTemplateCsVO stVo = (ContractTemplateCsVO) ctmBo.find(ContractTemplateCsVO.class, Long.parseLong(templateID));
					request.setAttribute("stVo", stVo);
					//获取项目的区县编号
					request.setAttribute("proDis", ctmBo.findProDis(stVo.getProjectID() + ""));

					if(stVo != null && stVo.getStartID() > 0){
						//获取项目名称
						ProjectVO projectVO = (ProjectVO) ctmBo.find(ProjectVO.class, stVo.getProjectID());
						request.setAttribute("projectName", projectVO.getProjectName());
						//获取开盘单元，许可证信息
						StartUnitVO suVo = (StartUnitVO) ctmBo.find(StartUnitVO.class, stVo.getStartID());
						if(suVo != null && suVo.getPresell_ID() > 0){
							PresellVO presellVO = (PresellVO) ctmBo.find(PresellVO.class, suVo.getPresell_ID());
							request.setAttribute("presellDesc", presellVO.getPresell_Desc());
						}
					}

					return "/fhhouse/salecontract/signcontract/ContractTemplateCS";
				}
			}else{
				return ERROR_System;
			}
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：保存出售合同主模板
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveSellTemplate")
	@ResponseBody
	public JSONArray saveSellTemplate(HttpServletRequest request,HttpSession session,ContractTemplateCsVO stVo,SellerTemplateVO sertempVo) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			String templateID = request.getParameter("templateID");
			String type = request.getParameter("type");
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();
			long i = 0;
			if(type != null && "2".equals(type)){
				//出售合同模板
				stVo.setTemplateID(Long.parseLong(templateID));
				stVo.setUpdPerson(vo.getLoginName());
				stVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				stVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));

				//合同甲方模板
				String sellerID = request.getParameter("sellerID");
				if(sellerID != null && !"".equals(sellerID)){
					sertempVo.setID(Long.parseLong(sellerID));
				}
				sertempVo.setUpdPerson(vo.getLoginName());
				sertempVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				sertempVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				//附件模板
				List<AttachTemplateVO> atVoList = new ArrayList<AttachTemplateVO>();
				int attachSerial[] = { FHConstant.ATTACH_SERIAL_SFJ2, FHConstant.ATTACH_SERIAL_SFJ3, FHConstant.ATTACH_SERIAL_SFJ4,
						FHConstant.ATTACH_SERIAL_SFJ5, FHConstant.ATTACH_SERIAL_SFJ6, FHConstant.ATTACH_SERIAL_SFJ9, FHConstant.ATTACH_SERIAL_SFJ10,
						FHConstant.ATTACH_SERIAL_SFJ11, FHConstant.ATTACH_SERIAL_SFJ12 };
				for(int j = 0; j < attachSerial.length; j++){
					String atcontent = request.getParameter("content" + attachSerial[j]);
					if(atcontent == null){
						atcontent = "";
					}
					AttachTemplateVO atempVo = new AttachTemplateVO();
					atempVo.setTemplateID(Long.parseLong(templateID));
					atempVo.setSerial(attachSerial[j]);
					atempVo.setContent(atcontent.getBytes());
					atempVo.setCrePerson(vo.getLoginName());
					atempVo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					atempVo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					atempVo.setUpdPerson(vo.getLoginName());
					atempVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					atempVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					atVoList.add(atempVo);

				}
				i = ctmBo.saveSellTemplate(stVo, sertempVo, atVoList);
			}
			if(i > 0){
				map.put("result", "success");
				map.put("msg", "保存成功！");
			}else{
				map.put("result", "fail");
				map.put("msg", "保存失败！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("msg", "保存出错！" + e);
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：保存预售合同主模板
	 * @param request
	 * @param session
	 * @param ptVo
	 * @param response
	 * @param seVo
	 * @return
	 */
	@RequestMapping(value = "/savePresellTemplate")
	@ResponseBody
	public JSONArray savePresellTemplate(HttpServletRequest request,HttpSession session,ContractTemplateYsVO ptVo,SellerTemplateVO sertempVo) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			String templateID = request.getParameter("templateID");
			String type = request.getParameter("type");
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();
			long i = 0;
			if(type != null && "1".equals(type)){
				//预售合同模板
				ptVo.setTemplateID(Long.parseLong(templateID));
				ptVo.setUpdPerson(vo.getLoginName());
				ptVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				ptVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				//合同甲方模板
				String sellerID = request.getParameter("sellerID");
				if(sellerID != null && !"".equals(sellerID)){
					sertempVo.setID(Long.parseLong(sellerID));
				}
				sertempVo.setUpdPerson(vo.getLoginName());
				sertempVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				sertempVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));

				//模板附件
				List<AttachTemplateVO> atVoList = new ArrayList<AttachTemplateVO>();
				int attachSerial[] = { FHConstant.ATTACH_SERIAL_PFJ2, FHConstant.ATTACH_SERIAL_PFJ3, FHConstant.ATTACH_SERIAL_PFJ4,
						FHConstant.ATTACH_SERIAL_PFJ5, FHConstant.ATTACH_SERIAL_PFJ8, FHConstant.ATTACH_SERIAL_PFJ9, FHConstant.ATTACH_SERIAL_PFJ10,
						FHConstant.ATTACH_SERIAL_PFJ11 };
				for(int j = 0; j < attachSerial.length; j++){
					String atcontent = request.getParameter("content" + attachSerial[j]);
					if(atcontent == null){
						atcontent = "";
					}
					AttachTemplateVO atempVo = new AttachTemplateVO();
					atempVo.setTemplateID(Long.parseLong(templateID));
					atempVo.setSerial(attachSerial[j]);
					atempVo.setContent(atcontent.getBytes());
					atempVo.setCrePerson(vo.getLoginName());
					atempVo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					atempVo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					atempVo.setUpdPerson(vo.getLoginName());
					atempVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					atempVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					atVoList.add(atempVo);

				}
				i = ctmBo.savePresellTemplate(ptVo, sertempVo, atVoList);
			}
			if(i > 0){
				map.put("result", "success");
				map.put("msg", "保存成功！");
			}else{
				map.put("result", "fail");
				map.put("msg", "保存失败！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("msg", "保存出错！" + e.getMessage());
			json = JSONArray.fromObject(map);
			return json;
		}

	}

	/**
	 * 功能描述：删除主模板
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteTemplate")
	@ResponseBody
	public JSONArray deleteTemplate(HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			String templateID = request.getParameter("templateID");
			String type = request.getParameter("type");
			ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();

			if(templateID != null && !"".equals(templateID) && type != null && !"".equals(type)){
				long i = 0;
				i = ctmBo.deleteTemplate(Long.parseLong(templateID), type);

				if(i > 0){
					map.put("result", "success");
					map.put("msg", "删除成功！");
				}else{
					map.put("result", "fail");
					map.put("msg", "删除失败！");
				}
			}else{
				map.put("result", "fail");
				map.put("msg", "未找到对应的记录！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return json;
		}
	}


	/**
	 * 功能描述 ：转到合同子模板查询
	 * @return
	 */
	@RequestMapping(value = "/querySubTemplate")
	public String querySubTemplate(HttpServletRequest request) {
		try{
			return "/fhhouse/salecontract/manage/SubTemplateQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：查询合同子模板列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/querySubTemplateList")
	public String querySubTemplateList(HttpServletRequest request,HttpSession session,Page page) {
		try{
			String projectID = request.getParameter("projectID");
			String startID = request.getParameter("startID");
			String contractType = request.getParameter("contractType");
			String articleType = request.getParameter("articleType");
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String district = this.getUserSqlDistricts(vo.getRegionId());
			ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();
			if(projectID == null || "".equals(projectID)){
				projectID = "";
				List<DictVO> list = ctmBo.queryUserProjectJson(vo.getUserId());
				if(list != null && list.size() > 0){
					for(DictVO dictVO : list){
						projectID += dictVO.getCode() + ",";
					}
					if(projectID.length() > 1){
						projectID = projectID.substring(0, projectID.length() - 1);
					}
				}else{
					projectID = "-1";
				}
			}
			List<SubTemplateVO> list = ctmBo.querySubTemplateList(projectID, startID, contractType, articleType, district, page);

			List<String> linkparam = new ArrayList<String>();
			linkparam.add("subtmplID");

			/** 设置table列名 */
			TableProperty tableProperty = new TableProperty();
			tableProperty.setEnableSort(false);
			tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("项目名称", "projectname");
			tableProperty.addColumn("开盘编号", "start_code");
			tableProperty.addColumn("子模板名称", "subtmplName", "viewSubTemp", linkparam, "查看", null);
			tableProperty.addColumn("对应条款", "articleType9");
			tableProperty.addColumn("修改时间", "updateTime", "Date", "yyyy-MM-dd", null);
			tableProperty.addColumn("修改", "修改", "doModify", linkparam, "修改", null);
			tableProperty.addColumn("删除", "删除", "doDelete", linkparam, "删除", null);

			String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

			request.setAttribute("htmlView", htmlView);

			return "/fhhouse/salecontract/manage/SubTemplateList";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：转到子模板查看
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/viewSubTemplate")
	public String viewSubTemplate(HttpServletRequest request) {
		try{
			String subtmplID = request.getParameter("subtmplID");
			String opera = request.getParameter("opera");
			if(opera != null){
				request.setAttribute("opera", opera);
			}
			if(subtmplID != null && !"".equals(subtmplID)){
				ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();
				SubTemplateVO stVo = (SubTemplateVO) ctmBo.find(SubTemplateVO.class, Long.parseLong(subtmplID));
				request.setAttribute("subtmplName", stVo.getSubtmplName());
				request.setAttribute("content", new String(stVo.getContent()).toString());
			}
			return "/fhhouse/salecontract/manage/SubTemplateView";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：保存子模板
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveSubTemplate")
	@ResponseBody
	public JSONArray saveSubTemplate(HttpServletRequest request,HttpSession session,SubTemplateVO stVo) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
		try{
			String opera = request.getParameter("opera"); //opera 1：新增  ，2：更新
			String content = request.getParameter("content");
			ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();
			long i = 0;
			if(opera != null && "1".equals(opera)){
				stVo.setCrePerson(vo.getLoginName());
				stVo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				stVo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				stVo.setUpdPerson(vo.getLoginName());
				stVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				stVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				i = ctmBo.addSubTemplate(stVo);
			}else{
				SubTemplateVO subtVo = (SubTemplateVO) ctmBo.find(SubTemplateVO.class, stVo.getSubtmplID());
				if(subtVo != null){
/*					subtVo.setContent(null);
					subtVo.setContent(content.getBytes());
					subtVo.setUpdPerson(vo.getLoginName());
					subtVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					subtVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					i = ctmBo.updateSubTemplate(subtVo);*/
					i=ctmBo.updateSubtemplate(stVo.getSubtmplID(), content, vo.getLoginName());
				}else{
					map.put("result", "fail");
					map.put("msg", "数据不全，更新失败！");
				}
			}
			if(i > 0){
				map.put("result", "success");
				map.put("msg", "保存成功！");
			}else{
				map.put("result", "fail");
				map.put("msg", "保存失败！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return json;
		}
	}

	/**
	 * 功能描述：删除子模板
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteSubTemplate")
	@ResponseBody
	public JSONArray deleteSubTemplate(HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			String subtmplID = request.getParameter("subtmplID");
			ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();
			if(subtmplID != null && !"".equals(subtmplID)){
				SubTemplateVO stVo = new SubTemplateVO();
				stVo.setSubtmplID(Long.parseLong(subtmplID));
				int i = ctmBo.delete(stVo);
				if(i > 0){
					map.put("result", "success");
					map.put("msg", "删除成功！");
				}else{
					map.put("result", "fail");
					map.put("msg", "删除失败！");
				}
			}else{
				map.put("result", "fail");
				map.put("msg", "未找到对应的记录！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return json;
		}
	}


	/**
	 * 功能描述：获取项目列表
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getUserProjectJson")
	@ResponseBody
	public JSONArray getUserDistinctJson(HttpSession session,HttpServletRequest request) {
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		JSONArray json = new JSONArray();
		List<DictVO> list;
		JSONObject jo;
		try{
			ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String hasFirst = request.getParameter("hasFirst");//是否增加空值 1：是
			list = ctmBo.queryUserProjectJson(vo.getUserId());
			if("1".equals(hasFirst)){
				DictVO nullVO = new DictVO();
				nullVO.setCode(-999);
				nullVO.setName("");
				jo = JSONObject.fromObject(nullVO);
				jsonList.add(jo);
			}
			if(list != null && list.size() > 0){
				for(DictVO dvo : list){
					jo = JSONObject.fromObject(dvo);
					jsonList.add(jo);
				}
			}
			json = JSONArray.fromObject(jsonList);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return json;
		}
	}

	/**
	 * 功能描述 ：根据项目获取许可证列表
	 * @return
	 */
	@RequestMapping(value = "/getUserPresellJson")
	@ResponseBody
	public JSONArray getUserPresellJson(HttpSession session,HttpServletRequest request) {
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		JSONArray json = new JSONArray();
		List<DictVO> list;
		JSONObject jo;
		try{
			String projectID = request.getParameter("projectID");
			if(projectID == null || "".equals(projectID)){
				return json;
			}
			ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String hasFirst = request.getParameter("hasFirst");//是否增加空值 1：是
			ProjectVO projectVO = (ProjectVO) ctmBo.find(ProjectVO.class, Long.parseLong(projectID));

			list = ctmBo.queryUserPresellJson(vo.getUserId(), projectID, projectVO.getDistrictID());
			if("1".equals(hasFirst)){
				DictVO nullVO = new DictVO();
				nullVO.setCode(-999);
				nullVO.setName("");
				jo = JSONObject.fromObject(nullVO);
				jsonList.add(jo);
			}
			if(list != null && list.size() > 0){
				for(DictVO dvo : list){
					jo = JSONObject.fromObject(dvo);
					jsonList.add(jo);
				}
			}
			json = JSONArray.fromObject(jsonList);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return json;
		}
	}

	/**
	 * 功能描述 ：获取开盘单元对应楼栋列表
	 * @return
	 */
	@RequestMapping(value = "/getUserBuildingJson")
	@ResponseBody
	public JSONArray getUserBuildingJson(HttpSession session,HttpServletRequest request) {
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		JSONArray json = new JSONArray();
		List<SupportVO> list;
		JSONObject jo;
		try{
			String startID = request.getParameter("startID");
			if(startID == null || "".equals(startID)){
				return json;
			}
			SignContractBO scBo = new SignContractBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String hasFirst = request.getParameter("hasFirst");//是否增加空值 1：是
			list = scBo.queryUserBuildingJson(vo.getUserId(),Long.parseLong(startID));
			if("1".equals(hasFirst)){
				SupportVO nullVO = new SupportVO();
				nullVO.setCode(-999);
				nullVO.setName("");
				jo = JSONObject.fromObject(nullVO);
				jsonList.add(jo);
			}
			if(list != null && list.size() > 0){
				for(SupportVO dvo : list){
					jo = JSONObject.fromObject(dvo);
					jsonList.add(jo);
				}
			}
			json = JSONArray.fromObject(jsonList);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return json;
		}
	}

	/**
	 * 功能描述：查询子模板
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findSubTemplate")
	@ResponseBody
	public JSONArray findSubTemplate(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			String projectID = request.getParameter("projectID");
			String startID = request.getParameter("startID");
			String type = request.getParameter("type");
			String articleType = request.getParameter("articleType");
			ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String district = this.getUserSqlDistricts(vo.getRegionId());
			if(projectID != null && !"".equals(projectID) && startID != null && !"".equals(startID) && type != null && !"".equals(type) && articleType != null && !"".equals(articleType)){
				SubTemplateVO stVo = new SubTemplateVO();
				List<SubTemplateVO> list = ctmBo.querySubTemplateList(projectID, startID, type, articleType, district, null);

				if(list != null && list.size() > 0){
					stVo = list.get(0);
					stVo = (SubTemplateVO) ctmBo.find(SubTemplateVO.class, stVo.getSubtmplID());
					if(stVo.getContent() != null){
						String content = new String(stVo.getContent()).toString();
						map.put("content", content);
					}else{
						map.put("content", "");
					}
					map.put("result", "success");
					map.put("msg", "查询成功！");

				}else{
					map.put("result", "fail");
					map.put("msg", "未找到对应的记录！");
				}
			}else{
				map.put("result", "fail");
				map.put("msg", "没有对应的记录！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return json;
		}
	}

	/**
	 * 功能描述：通过项目ID查询项目所在区县
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findProDistrict")
	@ResponseBody
	public JSONArray findProDistrict(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			String projectID = request.getParameter("projectID");
			ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();
			if(projectID != null && !"".equals(projectID)){
				ProjectVO ptVo = (ProjectVO) ctmBo.find(ProjectVO.class, Long.parseLong(projectID));
				if(ptVo != null){
					DictionaryBO dictBO = new DictionaryBO();
					String districtName = dictBO.getDictName("ct_district", String.valueOf(ptVo.getDistrictID()));
					map.put("result", "success");
					map.put("disName", districtName);

				}
			}else{
				map.put("result", "success");
				map.put("disName", "青岛市");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return json;
		}
	}

	/**
	 * 功能描述：查询子模板列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findSubTempList")
	@ResponseBody
	public JSONArray findSubTempList(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		try{
			String projectID = request.getParameter("projectID");
			String startID = request.getParameter("startID");
			String type = request.getParameter("type");
			ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String district = this.getUserSqlDistricts(vo.getRegionId());
			List<Map<String , Object>> subList = new ArrayList<Map<String , Object>>();
			if(projectID != null && !"".equals(projectID) && startID != null && !"".equals(startID) && type != null && !"".equals(type)){
				List<SubTemplateVO> list = ctmBo.querySubTemplateList(projectID, startID, type, null, district, null);
				if(list != null && list.size() > 0){
					for(SubTemplateVO subTemplateVO : list){
//						SubTemplateVO stVo = new SubTemplateVO();
//						stVo.setSubtmplID(subTemplateVO.getSubtmplID());
//						stVo.setSubtmplName(subTemplateVO.getSubtmplName());
//						stVo.setArticleType(subTemplateVO.getArticleType());
						Map<String , Object> map = new HashMap<String , Object>();
						map.put("subtempID", subTemplateVO.getSubtmplID());
						map.put("subtempName", subTemplateVO.getSubtmplName());
						map.put("articleType", subTemplateVO.getArticleType());
						subList.add(map);
					}
				}
			}
			json = JSONArray.fromObject(subList);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return json;
		}
	}

	/**
	 * 功能描述：查询子模板内容
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findSubTempContent")
	@ResponseBody
	public JSONArray findSubTempContent(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			String subtmplID = request.getParameter("subtmplID");
			String content = "";
			if(subtmplID != null && !"".equals(subtmplID)){
				ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();
				SubTemplateVO stVo = (SubTemplateVO) ctmBo.find(SubTemplateVO.class, Long.parseLong(subtmplID));
				if(stVo != null){
					content = new String(stVo.getContent()).toString();
					map.put("content", content);
					map.put("result", "success");
					map.put("msg", "导入" + stVo.getSubtmplName() + "成功！");
				}else{
					map.put("result", "fail");
					map.put("msg", "未找到对应的记录！");
				}
			}else{
				map.put("result", "fail");
				map.put("msg", "未找到对应的记录！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			return json;
		}
	}


	@RequestMapping("/copyTemplate")
	public String copyTemplate(HttpServletRequest request,HttpSession session) {
		try{
			String tempID = request.getParameter("tempID");
			if(tempID != null && !"".equals(tempID)){
				ContractTemplateManageBO ctmBo = new ContractTemplateManageBO();
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("templateID", "=", tempID));
				//获取附件模板信息
				List<MetaOrder> orders = new ArrayList<MetaOrder>();
				orders.add(new MetaOrder("serial", MetaOrder.ASC));
				List<AttachTemplateVO> atList = ctmBo.search(AttachTemplateVO.class, params, orders, null);
				Map<String , String> map = new HashMap<String , String>();
				if(atList != null && atList.size() > 0){
					for(AttachTemplateVO attachTemplateVO : atList){
						map.put(attachTemplateVO.getSerial() + "", new String(attachTemplateVO.getContent()));
					}
				}
				request.setAttribute("attachmap", map);

				//获取合同甲方模板ID
				SellerTemplateVO sertempVo = null;
				List<SellerTemplateVO> sertempList = ctmBo.search(SellerTemplateVO.class, params);
				if(sertempList != null && sertempList.size() > 0){
					request.setAttribute("sellerID", sertempList.get(0).getID());
					sertempVo = sertempList.get(0);
					EnterpriseQualifyVO eqvo = new EnterpriseQualifyVO();
					eqvo.setName(sertempVo.getSellerName());
					eqvo.setComp_ID(sertempVo.getCompID());
					eqvo.setRegadr(sertempVo.getSellerAddress());
					eqvo.setPost(sertempVo.getSellerPostcode());
					eqvo.setBizregister_Num(sertempVo.getSellerBizcardcode());
					eqvo.setAptitudeNum(sertempVo.getSellerCertcode());
					eqvo.setDelegate(sertempVo.getSellerDelegate());
					eqvo.setDlg_Call(sertempVo.getSellerDlgCall());
					eqvo.setProxy(sertempVo.getSellerProxy());
					eqvo.setProxy_Call(sertempVo.getSellerProxyCall());
					request.setAttribute("eqvo", eqvo);
				}

				ContractTemplateYsVO ptVo = (ContractTemplateYsVO) ctmBo.find(ContractTemplateYsVO.class, Long.parseLong(tempID));
				ContractTemplateCsVO stVo = (ContractTemplateCsVO) ctmBo.find(ContractTemplateCsVO.class, Long.parseLong(tempID));
				if(ptVo != null && ptVo.getStartID() > 0){
					ptVo.setTemplateID(0);
					//获取项目名称
					ProjectVO projectVO = (ProjectVO) ctmBo.find(ProjectVO.class, ptVo.getProjectID());
					request.setAttribute("projectName", projectVO.getProjectName());
					if(projectVO != null){
						String projectName = ptVo.getTempname();
						if(projectName.length() > 20){
							projectName = projectName.substring(0, 20);
						}
						ptVo.setTempname(projectName);
						request.setAttribute("ptVo", ptVo);
					}
					//获取开盘单元，许可证信息
					StartUnitVO suVo = (StartUnitVO) ctmBo.find(StartUnitVO.class, ptVo.getStartID());
					if(suVo != null && suVo.getPresell_ID() > 0){
						PresellVO presellVO = (PresellVO) ctmBo.find(PresellVO.class, suVo.getPresell_ID());
						request.setAttribute("presellDesc", presellVO.getPresell_Desc());
					}
					long i = ctmBo.addTemplate(ptVo, null, sertempVo, atList, "1");
					request.setAttribute("templateID", i);
					request.setAttribute("type", 1);
					return "/fhhouse/salecontract/signcontract/ContractTemplateYS";
				}else if(stVo != null && stVo.getStartID() > 0){
					stVo.setTemplateID(0);
					//获取项目名称
					ProjectVO projectVO = (ProjectVO) ctmBo.find(ProjectVO.class, stVo.getProjectID());
					request.setAttribute("projectName", projectVO.getProjectName());
					if(projectVO != null){
						String projectName = stVo.getTempname();
						if(projectName.length() > 20){
							projectName = projectName.substring(0, 20);
						}
						stVo.setTempname(projectName);
						request.setAttribute("stVo", stVo);
					}
					//获取开盘单元，许可证信息
					StartUnitVO suVo = (StartUnitVO) ctmBo.find(StartUnitVO.class, stVo.getStartID());
					if(suVo != null && suVo.getPresell_ID() > 0){
						PresellVO presellVO = (PresellVO) ctmBo.find(PresellVO.class, suVo.getPresell_ID());
						request.setAttribute("presellDesc", presellVO.getPresell_Desc());
					}
					long i = ctmBo.addTemplate(null, stVo, sertempVo, atList, "2");
					request.setAttribute("templateID", i);
					request.setAttribute("type", 2);
					return "/fhhouse/salecontract/signcontract/ContractTemplateCS";
				}else{
					return ERROR_System;
				}
			}else{
				return ERROR_System;
			}
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
}
