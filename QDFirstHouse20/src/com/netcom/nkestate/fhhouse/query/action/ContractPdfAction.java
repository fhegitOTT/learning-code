package com.netcom.nkestate.fhhouse.query.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cfca.paperless.client.bean.SignLocation;
import cfca.paperless.client.util.PwdEncryptUtil;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.PdfWriter;
import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.common.Constant;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.vo.EnterpriseQualifyVO;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.fhhouse.interfaces.bo.RealestateBO;
import com.netcom.nkestate.fhhouse.interfaces.bo.UniRealestateBO;
import com.netcom.nkestate.fhhouse.interfaces.vo.DocMessageVO;
import com.netcom.nkestate.fhhouse.query.bo.ContractPdfBO;
import com.netcom.nkestate.fhhouse.query.bo.ContractQueryBO;
import com.netcom.nkestate.fhhouse.query.vo.ContractPdfSignVO;
import com.netcom.nkestate.fhhouse.query.vo.ContractPdfVO;
import com.netcom.nkestate.fhhouse.query.vo.ContractSignPdfLogVO;
import com.netcom.nkestate.fhhouse.query.vo.ContractSignPdfVO;
import com.netcom.nkestate.fhhouse.salecontract.bo.SignContractBO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDetailCsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDetailYsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractReportLogVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.DeliverContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.PresellContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellerInfoVO;
import com.netcom.nkestate.framework.MiniConfiger;
import com.netcom.nkestate.framework.dao.MetaField;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.util.Base64Util;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.util.SealAutoPdf;
import com.netcom.nkestate.framework.util.StringUtil;
import com.netcom.nkestate.framework.util.VOUtil;
import com.netcom.nkestate.system.vo.SmUserVO;

/**
 * 合同管理-合同文本查看Action
 */
@Controller
@RequestMapping("/inner/ContractPdf2")
public class ContractPdfAction extends BaseAction {

	private static final String suffix = ".pdf";

	/*
	 * 功能描述：预售、出售、认购协议查询并显示pdf
	 */
	@RequestMapping("/doSearchText")
	public void createFirstHandPdf(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			ContractPdfBO cpBo = new ContractPdfBO();

			SmUserVO svo = (SmUserVO) session.getAttribute("LgUser");
			int userType = svo.getUserType(); //内外网用户
			String contractID = request.getParameter("contractID");
			long conID = -1;
			if(contractID != null && !"".equals(contractID)){
				try{
					conID = Long.parseLong(contractID);
				}catch (Exception e){
					throw new Exception("账号(" + svo.getLoginName() + ")使用非法合同号查询！contractID=" + contractID + "&&&&" + DateUtil.getSysDate());
				}
			}
			//增加合同打印查看日志
			ContractReportLogVO logVO = new ContractReportLogVO();
			logVO.setContractID(conID);
			logVO.setUserType(userType);
			logVO.setUserID(svo.getUserId());
			logVO.setReportDate(DateUtil.getSysDate());
			cqBo.add(logVO);

			//获取合同处理信息
			ContractDealVO cdVo = (ContractDealVO) cqBo.find(ContractDealVO.class, conID);
			int type = 0;
			if(cdVo != null){
				type = cdVo.getType();

				/* 检查外网签约人查看合同的权限 */
				if(userType == FHConstant.WEBACTION_TYPE_OUTER){ //外网

					long comID = 0;
					List<MetaFilter> params = new ArrayList<MetaFilter>();
					params.add(new MetaFilter("contractID", "=", conID));
					List<SellerInfoVO> list = cpBo.search(SellerInfoVO.class, params);
					if(list != null && list.size() > 0){
						for(SellerInfoVO sellerInfoVO : list){
							comID = sellerInfoVO.getCompID();
						}
					}

					if(comID != svo.getOrgID()){
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().write("<script>alert('没有权限预览该合同!');</script>");
						return;
					}
				}
			}else{
				throw new Exception("账号(" + svo.getLoginName() + ")使用不存在合同号查询！contractID=" + contractID + "&&&&" + DateUtil.getSysDate());
			}
			//获取合同pdf信息
			ContractPdfVO cpvo = (ContractPdfVO) cpBo.find(ContractPdfVO.class, conID);

			if(cpvo == null){
				cpvo = new ContractPdfVO();
				System.out.println("合同[" + contractID + "]打印开始！    " + DateUtil.getSysDate());
				if(type == 1){
					//预售合同
					if(FHConstant.NEW_PC_CONTRACTVERSION.equals(cdVo.getContractversion())){
						cpvo = cpBo.createNewPresellContractPdf(request, conID, userType,0);
					}else{
						cpvo = cpBo.createPresellContractPdf(conID, userType,0);
					}
				}
				if(type == 2){
					//出售合同
					if(FHConstant.NEW_SC_CONTRACTVERSION.equals(cdVo.getContractversion())){
						cpvo = cpBo.createNewSellContractPdf(request, conID, userType,0);
					}else{
						cpvo = cpBo.createSellContractPdf(conID, userType,0);
					}
				}
				if(type == 3){
					//定金合同
					cpvo = cpBo.createEarnestContractPdf(conID, userType);
				}

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date = sdf.parse(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS());

				cpvo.setContractID(cdVo.getContractID());
				cpvo.setContractType(type);
				cpvo.setHouseID(cdVo.getHouseID());
				cpvo.setCreateDate(date);
				cpvo.setCreatePerson(svo.getLoginName());
				cpvo.setUpdateDate(date);
				cpvo.setUpdatePerson(svo.getLoginName());

				//外网用户查询已签合同保存pdf文件
				if(userType == FHConstant.WEBACTION_TYPE_OUTER && cdVo.getStatus() == 2){
					String pdfPath = Constant.ContractURL + "\\" + DateUtil.getCurrentYear() + "\\" + DateUtil.getSysDateYYYYMMDD().substring(4, 6) + "\\" + DateUtil.getSysDateYYYYMMDD().substring(6, 8);
					File file = new File(pdfPath);
					if(!file.exists()){
						file.mkdirs();
					}
					cpBo.getFile(cpvo.getPdfData(), pdfPath, String.valueOf(contractID) + ".pdf");
					cpvo.setPdfPath(pdfPath + "\\" + String.valueOf(contractID) + ".pdf");
					long sum = cpBo.savepdf(cpvo);
					if(sum > 0){
						System.out.println("合同[" + contractID + "]PDF数据新增成功！");
					}
				}
				OutputStream out = response.getOutputStream();
				out.write(cpvo.getPdfData());
				out.flush();
				out.close();
				System.out.println("合同[" + contractID + "]打印结束！    " + DateUtil.getSysDate());
			}else{
				System.out.println("合同[" + contractID + "]打印开始，访问已保存PDF文件！    " + DateUtil.getSysDate());
				OutputStream out = response.getOutputStream();
				BufferedInputStream fin = new BufferedInputStream(new FileInputStream(cpvo.getPdfPath()));
				byte[] content = new byte[1024];
				int length;
				while ( (length = fin.read(content, 0, content.length)) != -1){
					out.write(content, 0, length);
				}
				out.flush();
				out.close();
				System.out.println("合同[" + contractID + "]打印结束，访问已保存PDF文件！    " + DateUtil.getSysDate());
			}
		}catch (Exception e){
			e.printStackTrace();
			try{
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('预览该合同出现异常!');</script>");
				return;
			}catch (Exception e2){
			}
		}
	}
	
	/*
	 * 功能描述：查询交接书，并显示pdf
	 */
	@RequestMapping("/doSearchDeliverContract")
	public void createDeliverContractPdf(HttpServletRequest request,HttpServletResponse response) {
		try{
			ContractPdfBO cpBo = new ContractPdfBO();

			ContractPdfVO cpVo = new ContractPdfVO();
			String deliverID = request.getParameter("deliverID");
			if(deliverID != null && !"".equals(deliverID)){
				long deliver = Long.parseLong(deliverID);
				cpVo = cpBo.createDeliverContract(deliver);
				OutputStream out = response.getOutputStream();
				//out.write(cpVo.getPdfData());
				BufferedInputStream fin = new BufferedInputStream(new FileInputStream(cpVo.getPdfPath()));
				byte[] content = new byte[1024];
				int length;
				while ( (length = fin.read(content, 0, content.length)) != -1){
					out.write(content, 0, length);
				}
				out.flush();
				out.close();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 功能描述：查询交接书,以html方式展示
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/doSearchDeliverContractHtml")
	public String createDeliverContractHtml(HttpServletRequest request,HttpServletResponse response) {
		try{
			ContractPdfBO cpBo = new ContractPdfBO();
			String deliverID = request.getParameter("deliverID");
			if(deliverID != null && !"".equals(deliverID)){
				long deliver = Long.parseLong(deliverID);
				DeliverContractVO vo = cpBo.createDeliverContractHtml(deliver);
				request.setAttribute("vo", vo);
				//request.setAttribute("houseId", getBarCode(String.valueOf(vo.getHouseID())));
				//request.setAttribute("contractID", getBarCode(String.valueOf(vo.getContractID())));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "/fhhouse/salecontract/manage/DeliverQueryInfo";
	}
	
	/*
	 * 功能描述：查询并显示认购协议
	 */
	@RequestMapping("/viewEarnestContract")
	public void viewEarnestContract(HttpServletRequest request,HttpServletResponse response) {
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			ContractPdfBO cpBo = new ContractPdfBO();

			ContractPdfVO cpvo = new ContractPdfVO();
			String contract = request.getParameter("contractID");
			if(contract != null && !"".equals(contract)){
				long contracID = Long.parseLong(contract);
				ContractDealVO cdVo = (ContractDealVO) cqBo.find(ContractDealVO.class, contracID);
				if(cdVo != null && cdVo.getType() > 0){
					int type = cdVo.getType();
					if(type == 1){
						//判断新旧合同
						if(FHConstant.NEW_PC_CONTRACTVERSION.equals(cdVo.getContractversion())){
							ContractDetailYsVO pcVo = (ContractDetailYsVO) cqBo.find(ContractDetailYsVO.class, contracID);
							if(pcVo != null && !pcVo.getEarnestID().equals("")){
								cpvo = cpBo.createEarnestContractPdf(Long.parseLong(pcVo.getEarnestID()), type);
							}else{
								response.setContentType("text/html;charset=utf-8");
								response.getWriter().write("<script>alert('该合同不存在认购协议!');</script>");
								return;
							}
						}else{
							PresellContractVO pcVo = (PresellContractVO) cqBo.find(PresellContractVO.class, contracID);
							if(pcVo != null && pcVo.getEarnestID() != 0){
								cpvo = cpBo.createEarnestContractPdf(pcVo.getEarnestID(), type);
							}else{
								response.setContentType("text/html;charset=utf-8");
								response.getWriter().write("<script>alert('该合同不存在认购协议!');</script>");
								return;
							}
						}
					}
					if(type == 2){
						//判断新旧合同
						if(FHConstant.NEW_PC_CONTRACTVERSION.equals(cdVo.getContractversion())){
							ContractDetailCsVO scVo = (ContractDetailCsVO) cqBo.find(ContractDetailCsVO.class, contracID);
							if(scVo != null && !scVo.getEarnestID().equals("")){
								cpvo = cpBo.createEarnestContractPdf(Long.parseLong(scVo.getEarnestID()), type);
							}else{
								response.setContentType("text/html;charset=utf-8");
								response.getWriter().write("<script>alert('该合同不存在认购协议!');</script>");
								return;
							}
						}else{
							SellContractVO scVo = (SellContractVO) cqBo.find(SellContractVO.class, contracID);
							if(scVo != null && scVo.getEarnestID() != 0){
								cpvo = cpBo.createEarnestContractPdf(scVo.getEarnestID(), type);
							}else{
								response.setContentType("text/html;charset=utf-8");
								response.getWriter().write("<script>alert('该合同不存在认购协议!');</script>");
								return;
							}
						}
					}
					if(type == 3){
						cpvo = cpBo.createEarnestContractPdf(contracID, type);
					}
					OutputStream out = response.getOutputStream();
					out.write(cpvo.getPdfData());
					out.flush();
					out.close();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			response.setContentType("text/html;charset=utf-8");
			try{
				response.getWriter().write("<script>alert('认购协议打印出错!');window.close();</script>");

			}catch (IOException e1){
				e1.printStackTrace();
			}
		}
	}

	@RequestMapping("/getBarCode")
	public void getBarCode(String strInfo,HttpServletResponse resp) {
		int barCodeWidth = 160 * 2;
		int barCodeHeight = 50 * 2;
		int HEIGHT_SPACE = 20 * 2;
		OutputStream os = null;
		ByteArrayOutputStream baos = null;
		try{
			//图片宽度   
			int imageWidth = barCodeWidth;
			// 图片高度   
			int imageHeight = barCodeHeight + HEIGHT_SPACE;

			BufferedImage img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

			Graphics2D g2d = (Graphics2D) img.getGraphics();
			g2d.fillRect(0, 0, imageWidth, imageHeight);
			//平滑抗锯齿
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

			//绘制条形码start...
			Barcode128 barcode128 = new Barcode128();
			// 图片横坐标开始位置   
			int startX = 0;
			// 图片纵坐标开始位置   
			int imageStartY = 0;
			int stringStartY = imageHeight - 8;// 条形码（文字）开始位置 

			barcode128.setCode(strInfo);

			Image codeImg = barcode128.createAwtImage(Color.black, Color.white);
			g2d.drawImage(codeImg, startX, imageStartY, barCodeWidth, barCodeHeight, Color.white, null);
			//绘制条形码end...

			//绘制文字start...
			//			Font font = new Font("", 0, 28);
			//		    FontRenderContext fontRenderContext = g2d.getFontRenderContext();
			//
			//		    int strHeight = (int)font.getStringBounds("", fontRenderContext).getHeight();
			//
			//		    int strWidth = (int)font.getStringBounds(strInfo, fontRenderContext).getWidth();
			//
			//		    AttributedString ats = new AttributedString(strInfo);
			//		    ats.addAttribute(TextAttribute.FONT, font, 0, strInfo.length());
			//		    AttributedCharacterIterator iter = ats.getIterator();
			//
			//		    g2d.setColor(Color.BLACK);
			//
			//		    g2d.drawString(iter, startX + (barCodeWidth - strWidth) / 2, stringStartY);
			// 设置条形码（文字）的颜色  
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("", java.awt.Font.PLAIN, 14 * 2));
			FontMetrics fm = g2d.getFontMetrics();
			int width = (int) fm.getStringBounds(strInfo, g2d).getWidth();

			// 绘制条形码（文字）   
			g2d.drawString(strInfo, startX + (barCodeWidth - width) / 2, stringStartY);
			//绘制文字end...


			g2d.dispose();
			os = resp.getOutputStream();

			ImageIO.write(img, "PNG", os);
			//baos = new ByteArrayOutputStream();
			//ImageIO.write(img, "PNG", baos);
			//os.write(baos.toByteArray());
			//			String base64 = Base64.encodeBytes(baos.toByteArray());
			//			base64 = base64.replaceAll("\r|\n", "");
			//			base64 = "data:image/png;base64," + base64;
			//return base64;
		}catch (Exception e){
			System.out.println("生成条形码失败");
			System.out.println(e.getMessage());
			e.printStackTrace();
			//return "";
		}finally{
			if(os != null){
				try{
					os.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
			if(baos != null){
				try{
					baos.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * 功能描述：生成签字用的pdf
	 */
	@RequestMapping("/doCreateSignPdf")
	@ResponseBody
	public JSONArray createSignPdf(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		JSONArray json = new JSONArray();
	    Map<String , Object> map = new HashMap<String , Object>();
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			ContractPdfBO cpBo = new ContractPdfBO();

			SmUserVO svo = (SmUserVO) session.getAttribute("LgUser");
			int userType = svo.getUserType(); //内外网用户
			String contractID = request.getParameter("contractID");
			String flag = request.getParameter("flag") != null?request.getParameter("flag"):"";
			String ifForce = request.getParameter("ifForce") != null?request.getParameter("ifForce"):"";
			long conID = -1;
			if(contractID != null && !"".equals(contractID)){
				try{
					conID = Long.parseLong(contractID);
				}catch (Exception e){
					throw new Exception("账号(" + svo.getLoginName() + ")使用非法合同号查询！contractID=" + contractID + "&&&&" + DateUtil.getSysDate());
				}
			}
			//增加合同打印查看日志
			ContractReportLogVO logVO = new ContractReportLogVO();
			logVO.setContractID(conID);
			logVO.setUserType(userType);
			logVO.setUserID(svo.getUserId());
			logVO.setReportDate(DateUtil.getSysDate());
			cqBo.add(logVO);

			//获取合同处理信息
			ContractDealVO cdVo = (ContractDealVO) cqBo.find(ContractDealVO.class, conID);
			int type = 0;
			if(cdVo != null){
				type = cdVo.getType();

				/* 检查外网签约人查看合同的权限 */
				if(userType == FHConstant.WEBACTION_TYPE_OUTER){ //外网

					long comID = 0;
					List<MetaFilter> params = new ArrayList<MetaFilter>();
					params.add(new MetaFilter("contractID", "=", conID));
					List<SellerInfoVO> list = cpBo.search(SellerInfoVO.class, params);
					if(list != null && list.size() > 0){
						for(SellerInfoVO sellerInfoVO : list){
							comID = sellerInfoVO.getCompID();
						}
					}

					if(comID != svo.getOrgID()){
						map.put("result", "fail");
						map.put("msg", "没有权限生成该合同!");
			            json = JSONArray.fromObject(map);
			            return json;
					}
				}
			}else{
				throw new Exception("账号(" + svo.getLoginName() + ")使用不存在合同号查询！contractID=" + contractID + "&&&&" + DateUtil.getSysDate());
			}
			
			if(!"".equals(flag) && "1".equals(flag)){
				//获取合同pdf信息
				ContractSignPdfVO cpvo = (ContractSignPdfVO) cpBo.find(ContractSignPdfVO.class, conID);
				if(cpvo == null || "1".equals(ifForce)){//强制生成pdf
					if("1".equals(ifForce) && cpvo != null){
						ContractSignPdfLogVO log = new ContractSignPdfLogVO();
						VOUtil.copyVO(cpvo, log);
						long ret = cpBo.add(log);
						if(ret > 0){
							cpBo.delete(cpvo);
							cpBo.updateBuyerInfo(conID);
						}
					}
					cpvo = new ContractSignPdfVO();
					System.out.println("合同[" + contractID + "]打印开始！    " + DateUtil.getSysDate());
					ContractPdfVO cvo = new ContractPdfVO();
					if(type == 1){
						//预售合同
						if(FHConstant.NEW_PC_CONTRACTVERSION.equals(cdVo.getContractversion())){
							cvo = cpBo.createNewPresellContractPdf(request, conID, userType,Integer.parseInt(flag));
						}else{
							cvo = cpBo.createPresellContractPdf(conID, userType,Integer.parseInt(flag));
						}
					}
					if(type == 2){
						//出售合同
						if(FHConstant.NEW_SC_CONTRACTVERSION.equals(cdVo.getContractversion())){
							cvo = cpBo.createNewSellContractPdf(request, conID, userType,Integer.parseInt(flag));
						}else{
							cvo = cpBo.createSellContractPdf(conID, userType,Integer.parseInt(flag));
						}
					}
					if(type == 3){
						//定金合同
						cvo = cpBo.createEarnestContractPdf(conID, userType);
					}

					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					Date date = sdf.parse(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS());
					cpvo.setPdfData(cvo.getPdfData());
					cpvo.setContractID(cdVo.getContractID());
					cpvo.setContractType(type);
					cpvo.setHouseID(cdVo.getHouseID());
					cpvo.setCreateDate(date);
					cpvo.setCreatePerson(svo.getLoginName());
					cpvo.setUpdateDate(date);
					cpvo.setUpdatePerson(svo.getLoginName());

					//外网用户查询已签合同保存pdf文件
					if(userType == FHConstant.WEBACTION_TYPE_OUTER && cdVo.getStatus() == 1){//待签
						String pdfPath = Constant.ContractURL + "\\sign\\" + DateUtil.getCurrentYear() + "\\" + DateUtil.getSysDateYYYYMMDD().substring(4, 6) + "\\" + DateUtil.getSysDateYYYYMMDD().substring(6, 8);
						File file = new File(pdfPath);
						if(!file.exists()){
							file.mkdirs();
						}
						cpBo.getFile(cpvo.getPdfData(), pdfPath, String.valueOf(contractID) + ".pdf");
						cpvo.setPdfPath(pdfPath + "\\" + String.valueOf(contractID) + ".pdf");
						long sum = cpBo.saveSignPdf(cpvo);
						if(sum > 0){
							System.out.println("合同[" + contractID + "]PDF数据新增成功！");
							map.put("result", "success");
							map.put("msg", "成功生成合同！");
							
							//生成短信验证码填入买方第一个人。
							insertDocMessage(contractID);
						}
					}
//					OutputStream out = response.getOutputStream();
//					out.write(cpvo.getPdfData());
//					out.flush();
//					out.close();
					System.out.println("合同[" + contractID + "]打印结束！    " + DateUtil.getSysDate());
					
					
					
				}else{
					map.put("result", "fail");
					map.put("msg", "已经生成过合同！");
					//					System.out.println("合同[" + contractID + "]打印开始，访问已保存PDF文件！    " + DateUtil.getSysDate());
//					OutputStream out = response.getOutputStream();
//					BufferedInputStream fin = new BufferedInputStream(new FileInputStream(cpvo.getPdfPath()));
//					byte[] content = new byte[1024];
//					int length;
//					while ( (length = fin.read(content, 0, content.length)) != -1){
//						out.write(content, 0, length);
//					}
//					out.flush();
//					out.close();
					//					System.out.println("合同[" + contractID + "]打印结束，访问已保存PDF文件！    " + DateUtil.getSysDate());
				}
			}
			
		}catch (Exception e){
			e.printStackTrace();
			try{
				map.put("result", "error");
				map.put("msg", "生成合同出现异常！");
				json = JSONArray.fromObject(map);
				return json;
			}catch (Exception e2){
			}
		}
		
		json = JSONArray.fromObject(map);
        return json;
	}
	
	/**
	 * 功能描述：插入短信表
	 * @param contractID
	 * @return
	 */
    public long insertDocMessage(String contractID) {
    	ContractQueryBO cqBo = new ContractQueryBO();
    	RealestateBO realestateBO  = new RealestateBO();
    	UniRealestateBO uniRealestateBO = new UniRealestateBO();
        long count = 0;
        try {
			//取第一个未签字的买方信息。
			List<BuyerInfoVO> buyerList = cqBo.getBuyerListOrderSerial(String.valueOf(contractID), "0");
            if (buyerList != null && buyerList.size() > 0) {
                BuyerInfoVO buyer = buyerList.get(0);
                int random = (int) ((Math.random() * 9 + 1) * 100000);
//                if (buyer.getBuyerCall() != null || !"".equals(buyer.getBuyerCall().trim())) {
                if (buyer != null) {
                    String tel = "";
                    String name = "";
					//如果代理人电话不为空，发送给代理人。
                    if(buyer.getBuyerProxyCall() != null && !"".equals(buyer.getBuyerProxyCall().trim())){
                        tel = buyer.getBuyerProxyCall();
                        name = buyer.getBuyerProxy();
                    }else{
                        tel = buyer.getBuyerCall();
                        name = buyer.getBuyerName();
                    }
					//插入登记的doc_message_t表中。
					DocMessageVO messageVO = new DocMessageVO();
                    messageVO.setTransactionID(buyer.getContractID());
                    messageVO.setFlag(2);
                    messageVO.setDistrictID(3);
                    messageVO.setMobile(tel);
                    messageVO.setName(name);
                    //messageVO.setContent(Constant.SmsURL + "?contractID=" + contractID + "&cardNO=" + buyer.getBuyerCardcode() + "&code=" + random);
					messageVO.setContent("尊敬的签约人，您有新的网签合同需要确认。请点击" + Constant.SmsURL + "/signFH/" + contractID + "/" + random + "查看电子合同并进行电子签名确认。");
                    messageVO.setWriteTime(DateUtil.getSysDate());
					messageVO.setStatus(1);//1、有效
					count = uniRealestateBO.addSMS(messageVO);

					//将短信验证码更新到买方人表中。
					List<MetaField> whereFields = new ArrayList<MetaField>();
                    whereFields.add(new MetaField("contractID",contractID));
                    whereFields.add(new MetaField("serial",buyer.getSerial()));
                    List<MetaField> fields = new ArrayList<MetaField>();
                    fields.add(new MetaField("VERIFYCODE",String.valueOf(random)));
                    cqBo.update(BuyerInfoVO.class,whereFields,fields);
                }
            }
        } catch (Exception e) {
            System.out.println("Error at insertDocMessage():" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return count;
    }
    
	/*
	 * 功能描述：预览签字用的pdf
	 */
	@RequestMapping("/doPreviewSignPdf")
	@ResponseBody
	public void doPreviewSignPdf(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			ContractPdfBO cpBo = new ContractPdfBO();
			
			SmUserVO svo = (SmUserVO) session.getAttribute("LgUser");
			int userType = svo.getUserType(); //内外网用户
			String contractID = request.getParameter("contractID");
			String flag = request.getParameter("flag") != null?request.getParameter("flag"):"";
			long conID = -1;
			if(contractID != null && !"".equals(contractID)){
				try{
					conID = Long.parseLong(contractID);
				}catch (Exception e){
					throw new Exception("账号(" + svo.getLoginName() + ")使用非法合同号查询！contractID=" + contractID + "&&&&" + DateUtil.getSysDate());
				}
			}
			//增加合同打印查看日志
			ContractReportLogVO logVO = new ContractReportLogVO();
			logVO.setContractID(conID);
			logVO.setUserType(userType);
			logVO.setUserID(svo.getUserId());
			logVO.setReportDate(DateUtil.getSysDate());
			cqBo.add(logVO);
			
			//获取合同处理信息
			ContractDealVO cdVo = (ContractDealVO) cqBo.find(ContractDealVO.class, conID);
			int type = 0;
			if(cdVo != null){
				type = cdVo.getType();
				
				/* 检查外网签约人查看合同的权限 */
				if(userType == FHConstant.WEBACTION_TYPE_OUTER){ //外网
					
					long comID = 0;
					List<MetaFilter> params = new ArrayList<MetaFilter>();
					params.add(new MetaFilter("contractID", "=", conID));
					List<SellerInfoVO> list = cpBo.search(SellerInfoVO.class, params);
					if(list != null && list.size() > 0){
						for(SellerInfoVO sellerInfoVO : list){
							comID = sellerInfoVO.getCompID();
						}
					}
					
					if(comID != svo.getOrgID()){
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().write("<script>alert('没有权限预览该合同!');</script>");
						return;
					}
				}
			}else{
				throw new Exception("账号(" + svo.getLoginName() + ")使用不存在合同号查询！contractID=" + contractID + "&&&&" + DateUtil.getSysDate());
			}
			
			if(!"".equals(flag) && "1".equals(flag)){
				//获取合同pdf信息
				ContractSignPdfVO cpvo = (ContractSignPdfVO) cpBo.find(ContractSignPdfVO.class, conID);
				/*
				 * if(cpvo == null){ cpvo = new ContractSignPdfVO(); System.out.println("合同[" + contractID + "]打印开始！    " + DateUtil.getSysDate()); ContractPdfVO cvo = new ContractPdfVO(); if(type ==
				 * 1){ //预售合同 if(FHConstant.NEW_PC_CONTRACTVERSION.equals(cdVo.getContractversion())){ cvo = cpBo.createNewPresellContractPdf(request, conID, userType,Integer.parseInt(flag)); }else{
				 * cvo = cpBo.createPresellContractPdf(conID, userType,Integer.parseInt(flag)); } } if(type == 2){ //出售合同 if(FHConstant.NEW_SC_CONTRACTVERSION.equals(cdVo.getContractversion())){ cvo =
				 * cpBo.createNewSellContractPdf(request, conID, userType,Integer.parseInt(flag)); }else{ cvo = cpBo.createSellContractPdf(conID, userType,Integer.parseInt(flag)); } } if(type == 3){
				 * //定金合同 cvo = cpBo.createEarnestContractPdf(conID, userType); } SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); Date date = sdf.parse(DateUtil.getSysDateYYYYMMDD() +
				 * DateUtil.getSysDateHHMMSS()); cpvo.setPdfData(cvo.getPdfData()); cpvo.setContractID(cdVo.getContractID()); cpvo.setContractType(type); cpvo.setHouseID(cdVo.getHouseID());
				 * cpvo.setCreateDate(date); cpvo.setCreatePerson(svo.getLoginName()); cpvo.setUpdateDate(date); cpvo.setUpdatePerson(svo.getLoginName()); //外网用户查询已签合同保存pdf文件 if(userType ==
				 * FHConstant.WEBACTION_TYPE_OUTER && cdVo.getStatus() == 1){//待签 String pdfPath = Constant.ContractURL + "\\sign\\" + DateUtil.getCurrentYear() + "\\" +
				 * DateUtil.getSysDateYYYYMMDD().substring(4, 6) + "\\" + DateUtil.getSysDateYYYYMMDD().substring(6, 8); File file = new File(pdfPath); if(!file.exists()){ file.mkdirs(); }
				 * cpBo.getFile(cpvo.getPdfData(), pdfPath, String.valueOf(contractID) + ".pdf"); cpvo.setPdfPath(pdfPath + "\\" + String.valueOf(contractID) + ".pdf"); long sum =
				 * cpBo.saveSignPdf(cpvo); if(sum > 0){ System.out.println("合同[" + contractID + "]PDF数据新增成功！"); } } OutputStream out = response.getOutputStream(); out.write(cpvo.getPdfData());
				 * out.flush(); out.close(); System.out.println("合同[" + contractID + "]打印结束！    " + DateUtil.getSysDate()); }else{
				 */
				if(cpvo != null){
					System.out.println("合同[" + contractID + "]打印开始，访问已保存PDF文件！    " + DateUtil.getSysDate());
					OutputStream out = response.getOutputStream();
					BufferedInputStream fin = new BufferedInputStream(new FileInputStream(cpvo.getPdfPath()));
					byte[] content = new byte[1024];
					int length;
					while ( (length = fin.read(content, 0, content.length)) != -1){
						out.write(content, 0, length);
					}
					out.flush();
					out.close();
					fin.close();
					System.out.println("合同[" + contractID + "]打印结束，访问已保存PDF文件！    " + DateUtil.getSysDate());
				}
			}
			
		}catch (Exception e){
			e.printStackTrace();
			try{
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('预览该合同出现异常!');</script>");
				return;
			}catch (Exception e2){
			}
		}
	}
	
	/*
	 * 功能描述：打开盖章页面检查
	 */
	@RequestMapping("/openContractSealCheck")
	@ResponseBody
	public JSONArray openContractSealCheck(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "1");
		map.put("message", "");
		String contractID = request.getParameter("contractID");
		try{
			ContractPdfBO cpBo = new ContractPdfBO();
			ContractQueryBO cqBo = new ContractQueryBO();
			
			SmUserVO user = (SmUserVO) session.getAttribute("LgUser");
			//检查当前用户是否有盖章权限。
			int canSeal = 0;
			//根据当前用户信息，获取权限是否可以盖章，如果可以，
			SignerVO signer = (SignerVO) cpBo.find(SignerVO.class, user.getUserId());
			if(signer != null ){
				canSeal = signer.getCanSeal();
			}
			if(canSeal == 0){
				map.put("result", "-1");
				map.put("message", "该签约人不允许盖章！");
				return JSONArray.fromObject(map);
			}
			
			//获取签字合同pdf信息
			ContractPdfSignVO cpvo = (ContractPdfSignVO) cpBo.find(ContractPdfSignVO.class, Long.parseLong(contractID));
			if(cpvo == null){
				map.put("result", "-1");
				map.put("message", "没有找到签字的合同，请先签字再盖章！");
				return JSONArray.fromObject(map);
			}else{
				//检查是否所有买方已经签完字
				List<BuyerInfoVO> buyerList = cqBo.searchBuyerInfo(String.valueOf(contractID));
				if(buyerList != null && buyerList.size() > 0){
					for (BuyerInfoVO buyerInfoVO : buyerList) {
						if(buyerInfoVO.getSignFlag() == 0){//未签字
							map.put("result", "-1");
							map.put("message", buyerInfoVO.getBuyerName() + "未签字！");
							return JSONArray.fromObject(map);
						}
					}
				}
				
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("合同[" + contractID + "]检查pdf路径异常！    " + DateUtil.getSysDate());
			map.put("result", "-1");
			map.put("message", "检查异常！");
			return JSONArray.fromObject(map);
		}
		return JSONArray.fromObject(map);
	}
	
	/**
	 * 功能描述：已签合同
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/openDialog")
	public String queryContract(HttpServletRequest request,HttpSession session) {
		try{
			return "/fhhouse/salecontract/signcontract/OpenDlg";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
	

	/**
	 * 功能描述：打开盖章页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/openContractSeal")
	public String openContractSeal(HttpServletRequest request,HttpServletResponse response) {
		try{
			String contractID = request.getParameter("contractID");
			if(contractID != null && !"".equals(contractID)){
				request.setAttribute("contractID", contractID);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "/fhhouse/salecontract/signcontract/ContractSeal";
	}
	
	/*
	 * 功能描述：以防万一，删除项目目录中的pdf备份。
	 */
	@RequestMapping("/deleteContractPdfBak")
	@ResponseBody
	public JSONArray deleteContractPdfBak(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "1");
		map.put("message", "");
		String contractID = request.getParameter("contractID");
		try{
			ContractPdfBO cpBo = new ContractPdfBO();
			//获取合同pdf信息
			File bakFile = new File(Constant.ContractPdfPath+ "/"  + contractID + suffix);
			if(bakFile.exists()){
				bakFile.delete();
				System.out.println("合同[" + contractID + "]删除成功！    " + DateUtil.getSysDate());
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			System.out.println("合同[" + contractID + "]检查pdf路径异常！    " + DateUtil.getSysDate());
			e.printStackTrace();
			map.put("result", "-1");
			map.put("message", "检查异常！");
			return JSONArray.fromObject(map);
		}
	}
	
	/*
	 * 功能描述：读取pdf保存到项目路径
	 */
	@RequestMapping("/doMovePDF")
	@ResponseBody
	public JSONArray doMovePDF(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "1");
		map.put("message", "");
		String contractID = request.getParameter("contractID");
		try{
			ContractPdfBO cpBo = new ContractPdfBO();
			//获取合同pdf信息
			ContractPdfSignVO cpvo = (ContractPdfSignVO) cpBo.find(ContractPdfSignVO.class, Long.parseLong(contractID));
			if(cpvo != null){
				System.out.println("cpvo.getPdfPath()==========="+cpvo.getPdfPath());
				String newPath = Constant.ContractPdfPath + "/" + contractID + suffix;
				File file = new File(newPath);
				if (!file.exists()) {
					copyFile(cpvo.getPdfPath(),newPath);
				}
				System.out.println("合同[" + contractID + "]转移成功！    " + DateUtil.getSysDate());
				map.put("result", "1");
				map.put("message", "保存成功");
				return JSONArray.fromObject(map);
			}else {
				map.put("result", "-1");
				map.put("message", "合同不存在，请先生成合同！");
				json = JSONArray.fromObject(map);
				return json;
			}
		}catch (Exception e){
			System.out.println("合同[" + contractID + "]转移异常！    " + DateUtil.getSysDate());
			e.printStackTrace();
			map.put("result", "-1");
			map.put("message", "合同[" + contractID + "]转移异常！");
			return JSONArray.fromObject(map);
		}
	}
	
	/**
	 * 复制单个文件
	 * @param oldPath
	 * String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 * String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 * @throws IOException
	 */
	public void copyFile(String oldPath, String newPath) throws IOException {
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if(oldfile.exists()){ // 文件存在时
				inStream = new FileInputStream(oldPath); // 读入原文件
				fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		}finally{
			if(inStream != null){
				inStream.close();
			}
			if(fs != null){
				fs.close();
			}
		}
	}
	
	/*
	 * 功能描述：检查是否签过字
	 */
	@RequestMapping("/checkCreatePdf")
	@ResponseBody
	public JSONArray checkCreatePdf(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "1");//没有签字的
		map.put("msg", "");
		String contractID = request.getParameter("contractID");
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			//检查是否所有买方已经签完字
			List<BuyerInfoVO> buyerList = cqBo.searchBuyerInfo(String.valueOf(contractID));
			if(buyerList != null && buyerList.size() > 0){
				for (BuyerInfoVO buyerInfoVO : buyerList) {
					if(buyerInfoVO.getSignFlag() == 1){//已签字
						map.put("result", "-1");//有签字的
						map.put("msg", buyerInfoVO.getBuyerName() + "已签字，是否重新生成合同pdf？");
						return JSONArray.fromObject(map);
					}
				}
			}	
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("合同[" + contractID + "]检查是否签过字异常！    " + DateUtil.getSysDate());
			map.put("result", "-1");
			map.put("msg", "检查异常！");
			return JSONArray.fromObject(map);
		}
		return JSONArray.fromObject(map);
	}
	
	/*
	 * 功能描述：检查是否有签字合同
	 */
	@RequestMapping("/checkSignPdf")
	@ResponseBody
	public JSONArray checkSignPdf(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "1");
		map.put("msg", "");
		String contractID = request.getParameter("contractID");
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			ContractPdfBO cpBo = new ContractPdfBO();
			ContractSignPdfVO cpvo = (ContractSignPdfVO) cpBo.find(ContractSignPdfVO.class, Long.parseLong(contractID));
			if(cpvo == null){
				map.put("result", "-1");
				map.put("msg", "请先生成签字合同！");
				return JSONArray.fromObject(map);		
			}	
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("合同[" + contractID + "]检查是否you合同异常！    " + DateUtil.getSysDate());
			map.put("result", "-1");
			map.put("msg", "检查异常！");
			return JSONArray.fromObject(map);
		}
		return JSONArray.fromObject(map);
	}
	
	/*
	 * 功能描述：获取当前用户所在机构的机构章和法人章，进行签章，并保存pdf
	 */
	@RequestMapping("/startAutoSealPdf")
	@ResponseBody
	public JSONArray startAutoSealPdf(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "1");
		map.put("msg", "盖章成功！");
		String contractID = request.getParameter("contractID");
		try{
			int canSeal = 0;//1,允许盖章，0，不允许盖章。
			ContractPdfBO cpBo = new ContractPdfBO();
			SmUserVO user = (SmUserVO) session.getAttribute("LgUser");
			//根据当前用户信息，获取权限是否可以盖章，如果可以，
			SignerVO signer = (SignerVO) cpBo.find(SignerVO.class, user.getUserId());
			if(signer != null ){
				canSeal = signer.getCanSeal();
			}
			if(canSeal == 0){
				map.put("result", "-1");
				map.put("msg", "该签约人不允许盖章！");
				return JSONArray.fromObject(map);
			}
			ContractSignPdfVO cpvo = (ContractSignPdfVO) cpBo.find(ContractSignPdfVO.class, Long.parseLong(contractID));
			if(cpvo != null){
				if(cpvo.getSealDate() != null){
					map.put("result", "-1");
					map.put("msg", "该合同已经自动盖过章，请勿再次盖章！");
					return JSONArray.fromObject(map);
				}
				//获取pdf路径，
				String filePath = cpvo.getPdfPath();
				//获取机构印章。
				String comp_SeaID = "";
				String comp_SeaPwd = "";
				String delegate_SealID = "";
				String delegate_SealPwd = "";
				String comp_code = "";
				EnterpriseQualifyVO eqVo = (EnterpriseQualifyVO) cpBo.find(EnterpriseQualifyVO.class, user.getOrgID());
				if(eqVo != null){
					comp_SeaID = StringUtil.toEmpty(eqVo.getComp_SealID());
					comp_SeaPwd = StringUtil.toEmpty(eqVo.getComp_SealPwd());
					delegate_SealID = StringUtil.toEmpty(eqVo.getDelegate_SealID());
					delegate_SealPwd = StringUtil.toEmpty(eqVo.getDelegate_SealPwd());
					comp_code = StringUtil.toEmpty(eqVo.getComp_Code());
				}
				if(StringUtil.isBlank(comp_SeaID.trim()) || StringUtil.isBlank(delegate_SealID.trim())){
					map.put("result", "-1");
					map.put("msg", "该签约人对应的企业没有企业章或者法人章信息，请录入！");
					return JSONArray.fromObject(map);
				}
				//1、先机构盖章
				SignLocation signLocation = new SignLocation("出卖人（签字或盖章）", "C", 10, 30);
				signLocation.setKeywordPositionIndex("1");
				int result = SealAutoPdf.sealPdf(filePath, user.getDisplayName(), comp_SeaID, PwdEncryptUtil.encrypto(comp_SeaPwd), signLocation, 120, comp_code);//机构盖章
				//2、再法人签章
				if(result == 1){
					signLocation = new SignLocation("【法定代表人】（签字或盖章）", "C", 70, -10);
					signLocation.setKeywordPositionIndex("1");
					SealAutoPdf.sealPdf(filePath, user.getDisplayName(), delegate_SealID, PwdEncryptUtil.encrypto(delegate_SealPwd), signLocation, 110, comp_code);//法人盖章
				}
				//更新盖章人和盖章时间
				List<MetaField> fields = new ArrayList<MetaField>();
				fields.add(new MetaField("sealDate", DateUtil.getSysDate()));
				fields.add(new MetaField("sealPerson", user.getLoginName()));
				cpBo.update(ContractSignPdfVO.class, Long.parseLong(contractID), fields);
			}	
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("合同[" + contractID + "]自动盖章异常！    " + DateUtil.getSysDate());
			map.put("result", "-1");
			map.put("msg", "自动盖章异常！");
			return JSONArray.fromObject(map);
		}
		return JSONArray.fromObject(map);
	}
	
	/*
	 * 功能描述：创建一个空白的pdf，并盖对应的章。
	 */
	@RequestMapping("/createSealPdf")
	@ResponseBody
	public JSONArray createSealPdf(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "1");
		map.put("msg", "success");
		String flag = request.getParameter("flag");
		String sealID = request.getParameter("sealID");
		String sealPwd = request.getParameter("sealPwd");
		String comp_ID = request.getParameter("comp_ID");
		ByteArrayOutputStream baos = null;
		PdfWriter writer = null;
		Document document = null;
		try{
			ContractPdfBO cpBo = new ContractPdfBO();
			SmUserVO user = (SmUserVO) session.getAttribute("LgUser");
			if(StringUtil.isBlank(sealID.trim()) || StringUtil.isBlank(sealPwd.trim())){
				map.put("result", "-1");
				map.put("msg", "签章ID和签章密码为空，请录入！");
				return JSONArray.fromObject(map);
			}
			String comp_code = "";
			EnterpriseQualifyVO eqVo = (EnterpriseQualifyVO) cpBo.find(EnterpriseQualifyVO.class, user.getOrgID());
			if(eqVo != null){
				comp_code = StringUtil.toEmpty(eqVo.getComp_Code());
			}
			//获取空白pdf路径。
			String filePath = "";
			String sealLocation = MiniConfiger.getProperty("sealLocation");
			String fileName = "";
			if("1".equals(flag)){
				filePath = sealLocation + "/" + comp_ID + ".pdf";
				fileName = comp_ID + ".pdf";
			}else {
				sealLocation = sealLocation + "/delegate";
				filePath = sealLocation + "/" + comp_ID + "_delegate.pdf";
				fileName = comp_ID + "_delegate.pdf";
			}
			File dirFile = new File(sealLocation);
			
			if(!dirFile.exists()){
				dirFile.mkdir();
			}
			
			File file = new File(filePath);
			if(!file.exists()){
				file.createNewFile();
			}else{
				file.delete();
			}
			
			//创建一个只有抬头的空白PDF
			document = new Document(PageSize.A4);
			baos = new ByteArrayOutputStream();
			//定义输出位置并把文档对象装入输出对象中
			writer = PdfWriter.getInstance(document, baos);
			document.open();
			
			String nameString = "";
			if("1".equals(flag)){
				nameString = "企业章";
			}else{
				nameString = "法人章";
			}
			Paragraph p = new Paragraph(nameString, ContractPdfBO.setChinese(20,Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(10); //设置段落前间距
			document.add(p);
			document.close();
			
			byte[] bytes = baos.toByteArray();
			cpBo.getFile(bytes,sealLocation,fileName);
			baos.close();
			writer.close();
			//创建完毕
			
			//在空白的pdf上盖章。
			SignLocation signLocation = new SignLocation(1, 110, 210);
			SealAutoPdf.sealPdf(filePath, user.getDisplayName(), sealID, PwdEncryptUtil.encrypto(sealPwd), signLocation, 500, comp_code);//盖章	
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("异常！    " + DateUtil.getSysDate());
			map.put("result", "-1");
			map.put("msg", "异常！");
			return JSONArray.fromObject(map);
		}finally{
				try {
					if(baos != null){
						baos.close();
					}
					if(writer != null){
						writer.close();
					}
					if(document != null){
						document.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return JSONArray.fromObject(map);
	}
	
	/*
	 * 功能描述：预览章的pdf
	 */
	@RequestMapping("/previewSeal")
	public void previewSeal(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		try{
			String comp_ID = request.getParameter("comp_ID");
			String flag = request.getParameter("flag");
			OutputStream out = response.getOutputStream();
			String sealLocation =  MiniConfiger.getProperty("sealLocation");
			String filePath = "";
			if("1".equals(flag)){
				filePath = sealLocation +"/"+comp_ID+".pdf";
			}else{
				filePath = sealLocation +"/delegate/"+comp_ID+"_delegate.pdf";
			}
			BufferedInputStream fin = new BufferedInputStream(new FileInputStream(filePath));
			byte[] content = new byte[1024];
			int length;
			while ( (length = fin.read(content, 0, content.length)) != -1){
				out.write(content, 0, length);
			}
			out.flush();
			out.close();
			fin.close();
		}catch (Exception e){
			e.printStackTrace();
			try{
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('预览该pdf出现异常!');</script>");
				return;
			}catch (Exception e2){
			}
		}
	}
	
	/**
	 * 功能描述：远程显示pdf接口，供以后外网查看的接口。 有电子合同，显示电子合同，没有电子合同显示普通合同，如果还没有，则生成合同。
	 * @param request
	 * @param response
	 * @param contractID
	 * @param userTypeStr
	 * @return
	 */
	@RequestMapping("/getFirstHandPdf")
	@ResponseBody
	public String getFirstHandPdf(HttpServletRequest request,HttpServletResponse response,String contractID,String userTypeStr) {
		OutputStream out = null;
		BufferedInputStream fin = null;
		byte[] content = new byte[1024];
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			ContractPdfBO cpBo = new ContractPdfBO();

			//			String userTypes = request.getParameter("userType"); //内外网用户
			if(userTypeStr == null || "".equals(userTypeStr.trim())){
				return createResponseMsg(-1, "缺少参数userTypeStr！");
			}
			int userType = Integer.parseInt(userTypeStr); //内外网用户
			//			String contractID = request.getParameter("contractID");
			long conID = -1;
			if(contractID != null && !"".equals(contractID)){
				try{
					conID = Long.parseLong(contractID);
				}catch (Exception e){
					//throw new Exception("账号(" + svo.getLoginName() + ")使用非法合同号查询！contractID=" + contractID + "&&&&" + DateUtil.getSysDate());
				}
			}else{
				return createResponseMsg(-1, "缺少参数contractID！");
			}
			

			//获取合同处理信息
			ContractDealVO cdVo = (ContractDealVO) cqBo.find(ContractDealVO.class, conID);
			int type = 0;
			if(cdVo != null){
				type = cdVo.getType();
				if(cdVo.getStatus() != 2){
					return createResponseMsg(-1, "合同不是已签状态！");
				}
			}else{
				//throw new Exception("账号(" + svo.getLoginName() + ")使用不存在合同号查询！contractID=" + contractID + "&&&&" + DateUtil.getSysDate());
				return createResponseMsg(-1, "合同不存在！");
			}
			//获取电子签章合同pdf信息
			ContractSignPdfVO cpSignvo = (ContractSignPdfVO) cpBo.find(ContractSignPdfVO.class, conID);
			if(cpSignvo == null){
				//如果没有电子合同，查看普通合同。
				ContractPdfVO cpvo = (ContractPdfVO) cpBo.find(ContractPdfVO.class, conID);
				//pdf如果不存在的话，就重新生成。
				if(cpvo == null){
					cpvo = new ContractPdfVO();
					System.out.println("合同[" + contractID + "]打印开始！    " + DateUtil.getSysDate());
					if(type == 1){
						//预售合同
						if(FHConstant.NEW_PC_CONTRACTVERSION.equals(cdVo.getContractversion())){
							cpvo = cpBo.createNewPresellContractPdf(request, conID, userType,0);
						}else{
							cpvo = cpBo.createPresellContractPdf(conID, userType,0);
						}
					}
					if(type == 2){
						//出售合同
						if(FHConstant.NEW_SC_CONTRACTVERSION.equals(cdVo.getContractversion())){
							cpvo = cpBo.createNewSellContractPdf(request, conID, userType,0);
						}else{
							cpvo = cpBo.createSellContractPdf(conID, userType,0);
						}
					}
					if(type == 3){
						//定金合同
						cpvo = cpBo.createEarnestContractPdf(conID, userType);
					}
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					Date date = sdf.parse(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS());
					
					cpvo.setContractID(cdVo.getContractID());
					cpvo.setContractType(type);
					cpvo.setHouseID(cdVo.getHouseID());
					cpvo.setCreateDate(date);
					cpvo.setCreatePerson("自动生成");
					cpvo.setUpdateDate(date);
					cpvo.setUpdatePerson("自动生成");
					
					//外网用户查询已签合同保存pdf文件
					if(userType == FHConstant.WEBACTION_TYPE_OUTER && cdVo.getStatus() == 2){
						String pdfPath = Constant.ContractURL + "\\" + DateUtil.getCurrentYear() + "\\" + DateUtil.getSysDateYYYYMMDD().substring(4, 6) + "\\" + DateUtil.getSysDateYYYYMMDD().substring(6, 8);
						File file = new File(pdfPath);
						if(!file.exists()){
							file.mkdirs();
						}
						cpBo.getFile(cpvo.getPdfData(), pdfPath, String.valueOf(contractID) + suffix);
						cpvo.setPdfPath(pdfPath + "\\" + String.valueOf(contractID) + suffix);
						long sum = cpBo.savepdf(cpvo);
						if(sum > 0){
							System.out.println("合同[" + contractID + "]PDF数据新增成功！");
						}
					}
					System.out.println("合同[" + contractID + "]打印结束！    " + DateUtil.getSysDate());
				}else{
					//显示pdf
					readPdf(response, contractID, cpvo.getPdfPath());
				}
			}else{
				//显示pdf
				readPdf(response, contractID, cpSignvo.getPdfPath());
			}
			
			
			//增加合同打印查看日志
			ContractReportLogVO logVO = new ContractReportLogVO();
			logVO.setContractID(conID);
			logVO.setUserType(userType);
			logVO.setUserID(1);//暂时写死默认1
			logVO.setReportDate(DateUtil.getSysDate());
			cqBo.add(logVO);
		}catch (Exception e){
			System.out.println("Error at getFirstHandPdf():"+e.getLocalizedMessage());
			e.printStackTrace();
			return createResponseMsg(1, "读取合同出现异常" + e.getLocalizedMessage());
		}finally{
				try {
					if(out != null){
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if(fin != null){
						fin.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return createResponseMsg(1, "成功");
	}
	
	/*
	 * 功能描述：远程调用生成pdf接口，签字的
	 */
	@RequestMapping("/getFirstHandSignPdf")
	@ResponseBody
	public String getFirstHandSignPdf(HttpServletRequest request,HttpServletResponse response,String contractID,String userTypeStr) {
		OutputStream out = null;
		BufferedInputStream fin = null;
		byte[] content = new byte[1024];
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			ContractPdfBO cpBo = new ContractPdfBO();

			//			String userTypes = request.getParameter("userType"); //内外网用户
			if(userTypeStr == null || "".equals(userTypeStr.trim())){
				return createResponseMsg(-1, "缺少参数userTypeStr！");
			}
			int userType = Integer.parseInt(userTypeStr); //内外网用户
			//			String contractID = request.getParameter("contractID");
			long conID = -1;
			if(contractID != null && !"".equals(contractID)){
				try{
					conID = Long.parseLong(contractID);
				}catch (Exception e){
					//throw new Exception("账号(" + svo.getLoginName() + ")使用非法合同号查询！contractID=" + contractID + "&&&&" + DateUtil.getSysDate());
				}
			}else{
				return createResponseMsg(-1, "缺少参数contractID！");
			}
			

			//获取合同处理信息
			ContractDealVO cdVo = (ContractDealVO) cqBo.find(ContractDealVO.class, conID);
			int type = 0;
			if(cdVo != null){
				type = cdVo.getType();
//				if(cdVo.getStatus() != 2){
				//					return createResponseMsg(-1,"合同不是已签状态！");
//				}
			}else{
				//throw new Exception("账号(" + svo.getLoginName() + ")使用不存在合同号查询！contractID=" + contractID + "&&&&" + DateUtil.getSysDate());
				return createResponseMsg(-1, "合同不存在！");
			}
			//获取合同pdf信息
			ContractPdfSignVO cpvo = (ContractPdfSignVO) cpBo.find(ContractPdfSignVO.class, conID);
			//pdf如果不存在的话，就重新生成。
			if(cpvo == null){
				cpvo = new ContractPdfSignVO();
				System.out.println("合同[" + contractID + "]打印开始！    " + DateUtil.getSysDate());
				ContractPdfVO cdvo  = new ContractPdfVO();
				if(type == 1){
					//预售合同
					if(FHConstant.NEW_PC_CONTRACTVERSION.equals(cdVo.getContractversion())){
						cdvo = cpBo.createNewPresellContractPdf(request, conID, userType,0);
					}else{
						cdvo = cpBo.createPresellContractPdf(conID, userType,0);
					}
				}
				if(type == 2){
					//出售合同
					if(FHConstant.NEW_SC_CONTRACTVERSION.equals(cdVo.getContractversion())){
						cdvo = cpBo.createNewSellContractPdf(request, conID, userType,0);
					}else{
						cdvo = cpBo.createSellContractPdf(conID, userType,0);
					}
				}
				if(type == 3){
					//定金合同
					cdvo = cpBo.createEarnestContractPdf(conID, userType);
				}

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date = sdf.parse(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS());
				cpvo.setPdfData(cdvo.getPdfData());
				cpvo.setContractID(cdVo.getContractID());
				cpvo.setContractType(type);
				cpvo.setHouseID(cdVo.getHouseID());
				cpvo.setCreateDate(date);
				cpvo.setCreatePerson("自动生成");
				cpvo.setUpdateDate(date);
				cpvo.setUpdatePerson("自动生成");

				//外网用户查询已签合同保存pdf文件
				if(userType == FHConstant.WEBACTION_TYPE_OUTER && cdVo.getStatus() == 2){
					String pdfPath = Constant.ContractURL + "\\" + DateUtil.getCurrentYear() + "\\" + DateUtil.getSysDateYYYYMMDD().substring(4, 6) + "\\" + DateUtil.getSysDateYYYYMMDD().substring(6, 8);
					File file = new File(pdfPath);
					if(!file.exists()){
						file.mkdirs();
					}
					cpBo.getFile(cpvo.getPdfData(), pdfPath, String.valueOf(contractID) + suffix);
					cpvo.setPdfPath(pdfPath + "\\" + String.valueOf(contractID) + suffix);
					long sum = cpBo.savepdf(cpvo);
					if(sum > 0){
						System.out.println("合同[" + contractID + "]PDF数据新增成功！");
					}
				}
				System.out.println("合同[" + contractID + "]打印结束！    " + DateUtil.getSysDate());
			}else{
				/*
				 * System.out.println("合同[" + contractID + "]打印开始，访问已保存PDF文件！    " + DateUtil.getSysDate()); out = response.getOutputStream(); fin = new BufferedInputStream(new
				 * FileInputStream(cpvo.getPdfPath())); int length; while ( (length = fin.read(content, 0, content.length)) != -1){ out.write(content, 0, length); } fin.close(); out.close();
				 * System.out.println("合同[" + contractID + "]打印结束，访问已保存PDF文件！    " + DateUtil.getSysDate());
				 */
			}
			
			//增加合同打印查看日志
			ContractReportLogVO logVO = new ContractReportLogVO();
			logVO.setContractID(conID);
			logVO.setUserType(userType);
			logVO.setUserID(1);//暂时写死默认1
			logVO.setReportDate(DateUtil.getSysDate());
			cqBo.add(logVO);
		}catch (Exception e){
			System.out.println("Error at getFirstHandSignPdf():"+e.getLocalizedMessage());
			e.printStackTrace();
			return createResponseMsg(1, "读取合同出现异常" + e.getLocalizedMessage());
		}finally{
				try {
					if(out != null){
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if(fin != null){
						fin.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return createResponseMsg(1, "成功");
	}
	
	private String createResponseMsg(int code,String msg,byte[] pdfbytes){
		JSONObject json = new JSONObject();
		json.put("result", code);
		json.put("msg", msg);
		if(pdfbytes != null && pdfbytes.length > 0){
			json.put("pdfbytes", pdfbytes);
		}
		return json.toString();
	}
	private String createResponseMsg(int code,String msg){
		JSONObject json = new JSONObject();
		json.put("result", code);
		json.put("msg", msg);
		return json.toString();
	}
	
	/**
	 * 读pdf方法
	 * @param response
	 * @param contractID
	 * @param pdfPath
	 * @throws IOException
	 */
	private void readPdf(HttpServletResponse response,String contractID,String pdfPath) throws IOException{
		byte[] content = new byte[1024];
		System.out.println("合同[" + contractID + "]打印开始，访问已保存PDF文件！    " + DateUtil.getSysDate());
		OutputStream out = null;
		BufferedInputStream fin = null;
		try {
			out = response.getOutputStream();
			fin = new BufferedInputStream(new FileInputStream(pdfPath));
			int length;
			while ( (length = fin.read(content, 0, content.length)) != -1){
				out.write(content, 0, length);
			}
			fin.close();
			out.close();
			System.out.println("合同[" + contractID + "]打印结束，访问已保存PDF文件！    " + DateUtil.getSysDate());
		} catch (IOException e) {
			System.out.println("Error at readPdf():"+e.getLocalizedMessage());
			e.printStackTrace();
		}finally{
			if (fin != null) {
				fin.close();
			}
			if(out != null){
				out.close();
			}
		}
	}
	
	/**
	 * 获取合同信息
	 * @param response
	 * @param contractID
	 * @throws IOException
	 */
	@RequestMapping("/getContractInfo")
	@ResponseBody
	private JSONArray getContractInfo(HttpServletRequest request){
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "1");
		map.put("msg", "获取合同信息成功！");
		ContractPdfBO cpBo = new ContractPdfBO();
		ContractQueryBO cqBo = new ContractQueryBO();
		SignContractBO sBo = new SignContractBO();
		String contractID = request.getParameter("contractID");
		try {
			ContractSignPdfVO cpvo = (ContractSignPdfVO) cpBo.find(ContractSignPdfVO.class, Long.parseLong(contractID));
			if(cpvo == null){
				map.put("result", "fail");
				map.put("msg", "请先点击 生成pdf！");
				return JSONArray.fromObject(map);
			}
			List<BuyerInfoVO> buyerList = cqBo.searchBuyerInfo(contractID);
			List<String> noSignBuyerList = new ArrayList<String>();
			if(buyerList!=null&&buyerList.size()>0){
				for(int i = 0;i<buyerList.size();i++){
					BuyerInfoVO buyerInfoVO = buyerList.get(i);
					if(buyerInfoVO.getSignFlag()==0){
						noSignBuyerList.add(buyerInfoVO.getBuyerName());
					}
					if(buyerInfoVO.getSignFlag()==1&&!"".equals(buyerInfoVO.getVerifyCode())&&buyerInfoVO.getVerifyCode()!=null){
						map.put("result", "fail");
						map.put("msg", "已进入手机签字流程，请使用手机签字！");
						return JSONArray.fromObject(map);
					}
					if(buyerInfoVO.getSignFlag()==0&&!"".equals(buyerInfoVO.getVerifyCode())&&buyerInfoVO.getVerifyCode()!=null){
						sBo.updateBuyerInfoVO(contractID, buyerInfoVO.getSerial(), "VERIFYCODE", null);
					}
				}
				if(noSignBuyerList!=null&&noSignBuyerList.size()>0){
					map.put("buyerNames", noSignBuyerList);
					map.put("serial", noSignBuyerList);
					map.put("pdf64Str", Base64Util.getPDFBinary(cpvo.getPdfPath()));
					return JSONArray.fromObject(map);
				}else{
					map.put("result", "2");
					map.put("msg", "签字已完成，请刷新！");
					return JSONArray.fromObject(map);
				}
			}else{
				map.put("result", "fail");
				map.put("msg", "请先添加受让方签字人");
				return JSONArray.fromObject(map);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "error");
			map.put("msg", "平板签字异常");
            return JSONArray.fromObject(map);
		}	
	}
	
	/**
	 * 合成签字后的pdf
	 * @param request
	 * @param contractID
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping("/signEnquirePic")
	@ResponseBody
	private JSONArray signEnquirePic(HttpServletRequest request, HttpSession session) throws Exception{
		ContractPdfBO contractPdfBO = new ContractPdfBO();
		SmUserVO user = (SmUserVO) session.getAttribute("LgUser");
		
		Map<String , Object> map = new HashMap<String , Object>();
		ContractQueryBO cqBo = new ContractQueryBO();
		SignContractBO sBo = new SignContractBO();
		String contractID = request.getParameter("contractID");
		String base64Pic = Base64Util.replaceBase64(request.getParameter("base64pic"));
		String fingerImageBase64 = Base64Util.replaceBase64(request.getParameter("fingerImageBase64"));
		int serial = Integer.parseInt(request.getParameter("serial"));
		int result = 0;
		try {
			String comp_code = "";
			String sealCode = "";
			String sealPassword = "";
			EnterpriseQualifyVO eqVo = (EnterpriseQualifyVO) contractPdfBO.find(EnterpriseQualifyVO.class, user.getOrgID());
			if(eqVo != null){
				comp_code = StringUtil.toEmpty(eqVo.getComp_Code());
				sealCode = StringUtil.toEmpty(eqVo.getComp_SealID());
				sealPassword = StringUtil.toEmpty(eqVo.getComp_SealPwd());
			}
			if("".equals(base64Pic)||"".equals(fingerImageBase64)){
				map.put("result", result);
				Thread.sleep(2000);
				return JSONArray.fromObject(map);
			}
			result = contractPdfBO.signEnquirePic(contractID, base64Pic, fingerImageBase64,comp_code,sealCode,sealPassword);
			if(result==1){
				sBo.updateBuyerInfoVO(contractID,serial,"signFlag","1");
			}
			map.put("result", result);
			Thread.sleep(2000);
			return JSONArray.fromObject(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "error");
			return JSONArray.fromObject(map);
		}
	}
}
