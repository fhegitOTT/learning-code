package com.netcom.nkestate.fhhouse.queryFHE.bo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.RenderDestination;
import org.icepdf.core.util.GraphicsRenderingHints;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.netcom.nkestate.common.Constant;
import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.fhhouse.project.vo.HousePlanVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.query.dao.ContractPdfDAO;
import com.netcom.nkestate.fhhouse.query.dao.ContractQueryDAO;
import com.netcom.nkestate.fhhouse.query.vo.ContractPdfVO;
import com.netcom.nkestate.fhhouse.query.vo.ContractSignPdfVO;
import com.netcom.nkestate.fhhouse.query.vo.Watermark;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach1MoneyVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach1VO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4HireVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4LimitVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4OtherVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4RealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4VO;
import com.netcom.nkestate.fhhouse.salecontract.vo.AttachVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDetailCsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDetailYsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.DeliverContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.EarnestContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.PresellAttach2MoneyRecordVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.PresellAttach2MoneyVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.PresellContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.PresellDepositVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.XgLimitSaleContractVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaField;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.util.Base64Util;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.util.SealAutoPdf;
import com.netcom.nkestate.framework.util.StringUtil;
import com.netcom.nkestate.framework.vo.DictVO;
import com.netcom.nkestate.system.bo.DictionaryBO;
import com.netcom.nkestate.system.dao.DictionaryDAO;

import cfca.paperless.client.bean.SignLocation;
import net.sf.json.JSONArray;


public class ContractPdfBO extends MiniBO {

	static Logger logger = Logger.getLogger(ContractPdfBO.class.getName());
	ContractQueryDAO cqDao = new ContractQueryDAO();
	ContractPdfDAO pdfDAO = new ContractPdfDAO();
	DictionaryBO dBo = new DictionaryBO();
	String tempPdfPath = Constant.TempFilePath + "/housePlan/pdf/";
	String tempImgPath = Constant.TempFilePath + "/housePlan/img/";

	/**
	 * 功能描述：生成并保存PDF
	 * @param contractPdfVO
	 * @return
	 * @throws Exception
	 */
	public long savepdf(ContractPdfVO contractPdfVO) throws Exception {
		long num = 0;
		try{
			openDAO(pdfDAO);
			pdfDAO.setTransaction();
			num = pdfDAO.savepdf(contractPdfVO);
			pdfDAO.commit();
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			pdfDAO.rollback();
		}finally{
			closeDAO(pdfDAO);
		}
		return num;
	}
	/**
	 * 功能描述：生成并保存PDF
	 * @param contractPdfVO
	 * @return
	 * @throws Exception
	 */
	public long saveSignPdf(ContractSignPdfVO contractPdfVO) throws Exception {
		long num = 0;
		try{
			openDAO(pdfDAO);
			pdfDAO.setTransaction();
			num = pdfDAO.savepdf(contractPdfVO);
			pdfDAO.commit();
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			pdfDAO.rollback();
		}finally{
			closeDAO(pdfDAO);
		}
		return num;
	}

	/**
	 * 功能描述：更新 PDF
	 * @param contractPdfVO
	 * @return
	 * @throws Exception
	 */
	public long updatePdf(ContractPdfVO contractPdfVO) throws Exception {
		long num = 0;
		try{
			openDAO(pdfDAO);
			pdfDAO.setTransaction();
			num = pdfDAO.updatePdf(contractPdfVO);
			pdfDAO.commit();
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			pdfDAO.rollback();
		}finally{
			closeDAO(pdfDAO);
		}
		return num;
	}

	/**
	 * 功能描述：查询 PDF
	 * @param contractPdfVO
	 * @return
	 * @throws Exception
	 */
	public ContractPdfVO findPdf(long contractID,int contractType) throws Exception {
		ContractPdfVO abv = new ContractPdfVO();
		try{
			openDAO(pdfDAO);
			abv.setContractID(contractID);
			abv.setContractType(contractType);
			return (ContractPdfVO) pdfDAO.find(ContractPdfVO.class, contractID);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(pdfDAO);
		}
	}

	float FS14 = 12; //字体大小
	float FS12 = 12;
	float FS10 = 10.5f;
	float SPB28 = 24; //段落前间距
	float SPB24 = 24;
	float SPB20 = 21f;
	float LD21 = 18; //行间距
	float LD18 = 18;
	float LD15 = 15.75f;
	float FL28 = 24; //首行缩进
	float FL24 = 24;
	float FL20 = 20;
	String kg2 = "  "; //2个空格
	String kg4 = "    ";//4个空格
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");//设置时间显示格式

	/**
	 * 功能描述：定金合同文本
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public ContractPdfVO createEarnestContractPdf(long contractID,int type) throws Exception {
		try{
			openDAO(cqDao);
			openDAO(pdfDAO);

			//创建文档，页面大小为A4，宽595F, 高842F
			Document document = new Document(PageSize.A4);
			//查询合同主表信息
			ContractDealVO cdvo = (ContractDealVO) cqDao.find(ContractDealVO.class, contractID);

			//查询定金合同信息
			EarnestContractVO ecvo = (EarnestContractVO) cqDao.find(EarnestContractVO.class, contractID);
			if(ecvo == null){
				ecvo = new EarnestContractVO();
			}
			//查询合同甲方信息
			SellerInfoVO svo = new SellerInfoVO();
			List<SellerInfoVO> list1 = cqDao.searchSellerInfo(String.valueOf(contractID));
			if(list1 != null && list1.size() > 0){
				svo = list1.get(0);
			}
			//查询合同乙方信息
			BuyerInfoVO bvo = new BuyerInfoVO();
			List<BuyerInfoVO> list2 = cqDao.searchBuyerInfo(String.valueOf(contractID));
			if(list2 != null && list2.size() > 0){
				bvo = list2.get(0);
			}

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//定义输出位置并把文档对象装入输出对象中
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			//添加水印
			writer.setPageEvent(new Watermark("定金合同"));
			
			//添加页码
			HeaderFooter footer = new HeaderFooter(new Phrase(" - ", setChinese(FS10)), new Phrase(" - ", setChinese(FS10)));
			footer.setAlignment(1);
			footer.setBorderColor(Color.red);
			footer.setBorder(Rectangle.NO_BORDER);
			document.setFooter(footer);

			document.open();

			Paragraph contractId = new Paragraph("合同代码：" + contractID, setChinese(FS14));
			contractId.setAlignment(Element.ALIGN_RIGHT);
			contractId.setSpacingBefore(10); //设置段落前间距
			document.add(contractId);

			Paragraph houseId = new Paragraph("房屋代码：" + cdvo.getHouseID(), setChinese(FS14));
			houseId.setAlignment(Element.ALIGN_RIGHT);
			houseId.setSpacingBefore(5); //设置段落前间距
			document.add(houseId);

			Paragraph docTitle = new Paragraph("认购协议\n", setChinese(22, Font.BOLD));
			docTitle.setAlignment(Element.ALIGN_CENTER);
			docTitle.setSpacingBefore(10); //设置段落前间距
			document.add(docTitle);
			Paragraph docRemake = new Paragraph("（供商品房预订时使用）\n", setChinese(FS14));
			docRemake.setAlignment(Element.ALIGN_CENTER);
			docRemake.setSpacingBefore(10); //设置段落前间距
			docRemake.setSpacingAfter(20); //设置段落后间距
			document.add(docRemake);

			PdfPTable table1 = new PdfPTable(2);
			int width1[] = { 18, 82 };
			table1.setWidths(width1);
			table1.setTotalWidth(540);
			table1.setLockedWidth(true);
			PdfPCell cell11 = new PdfPCell(new Paragraph("甲方(卖方)：", setChinese(FS14)));
			PdfPCell cell12 = new PdfPCell(new Paragraph(svo.getSellerName(), setChinese(FS14)));
			cell11.setBorder(0);
			cell12.setBorderWidthLeft(0);
			cell12.setBorderWidthTop(0);
			cell12.setBorderWidthRight(0);
			cell12.setPaddingBottom(5);
			table1.addCell(cell11);
			table1.addCell(cell12);
			document.add(table1);
			Paragraph blankRow1 = new Paragraph(12f, " ", setChinese(FS14)); //添加空白行
			document.add(blankRow1);

			PdfPTable table2 = new PdfPTable(4);
			int width2[] = { 11, 66, 11, 12 };
			table2.setWidths(width2);
			table2.setTotalWidth(540);
			table2.setLockedWidth(true);
			PdfPCell cell21 = new PdfPCell(new Paragraph("住所：", setChinese(FS14)));
			PdfPCell cell22 = new PdfPCell(new Paragraph(svo.getSellerAddress(), setChinese(FS14)));
			PdfPCell cell23 = new PdfPCell(new Paragraph("邮编：", setChinese(FS14)));
			PdfPCell cell24 = new PdfPCell(new Paragraph(svo.getSellerPostcode(), setChinese(FS14)));
			cell21.setBorder(0);
			cell23.setBorder(0);
			cell22.setBorderWidthLeft(0);
			cell22.setBorderWidthTop(0);
			cell22.setBorderWidthRight(0);
			cell22.setPaddingBottom(5);
			cell24.setBorderWidthLeft(0);
			cell24.setBorderWidthTop(0);
			cell24.setBorderWidthRight(0);
			cell24.setPaddingBottom(5);
			table2.addCell(cell21);
			table2.addCell(cell22);
			table2.addCell(cell23);
			table2.addCell(cell24);
			document.add(table2);
			Paragraph blankRow2 = new Paragraph(12f, " ", setChinese(FS14));
			document.add(blankRow2);

			PdfPTable table3 = new PdfPTable(2);
			int width3[] = { 22, 78 };
			table3.setWidths(width3);
			table3.setTotalWidth(540);
			table3.setLockedWidth(true);
			PdfPCell cell31 = new PdfPCell(new Paragraph("营业执照号码：", setChinese(FS14)));
			PdfPCell cell32 = new PdfPCell(new Paragraph(svo.getSellerBizcardcode(), setChinese(FS14)));
			cell31.setBorder(0);
			cell32.setBorderWidthLeft(0);
			cell32.setBorderWidthTop(0);
			cell32.setBorderWidthRight(0);
			cell32.setPaddingBottom(5);
			table3.addCell(cell31);
			table3.addCell(cell32);
			document.add(table3);
			Paragraph blankRow3 = new Paragraph(12f, " ", setChinese(FS14));
			document.add(blankRow3);

			PdfPTable table31 = new PdfPTable(2);
			int width31[] = { 22, 78 };
			table31.setWidths(width31);
			table31.setTotalWidth(540);
			table31.setLockedWidth(true);
			PdfPCell cell311 = new PdfPCell(new Paragraph("资格证书号码：", setChinese(FS14)));
			PdfPCell cell312 = new PdfPCell(new Paragraph(svo.getSellerCertcode(), setChinese(FS14)));
			cell311.setBorder(0);
			cell312.setBorderWidthLeft(0);
			cell312.setBorderWidthTop(0);
			cell312.setBorderWidthRight(0);
			cell312.setPaddingBottom(5);
			table31.addCell(cell311);
			table31.addCell(cell312);
			document.add(table31);
			Paragraph blankRow31 = new Paragraph(12f, " ", setChinese(FS14));
			document.add(blankRow31);

			PdfPTable table4 = new PdfPTable(4);
			int width4[] = { 18, 32, 16, 34 };
			table4.setWidths(width4);
			table4.setTotalWidth(540);
			table4.setLockedWidth(true);
			PdfPCell cell41 = new PdfPCell(new Paragraph("法定代表人：", setChinese(FS14)));
			PdfPCell cell42 = new PdfPCell(new Paragraph(svo.getSellerDelegate(), setChinese(FS14)));
			PdfPCell cell43 = new PdfPCell(new Paragraph("联系电话：", setChinese(FS14)));
			PdfPCell cell44 = new PdfPCell(new Paragraph(svo.getSellerDlgCall(), setChinese(FS14)));
			cell41.setBorder(0);
			cell43.setBorder(0);
			cell42.setBorderWidthLeft(0);
			cell42.setBorderWidthTop(0);
			cell42.setBorderWidthRight(0);
			cell42.setPaddingBottom(5);
			cell44.setBorderWidthLeft(0);
			cell44.setBorderWidthTop(0);
			cell44.setBorderWidthRight(0);
			cell44.setPaddingBottom(5);
			table4.addCell(cell41);
			table4.addCell(cell42);
			table4.addCell(cell43);
			table4.addCell(cell44);
			document.add(table4);
			Paragraph blankRow4 = new Paragraph(12f, " ", setChinese(FS14));
			document.add(blankRow4);

			PdfPTable table5 = new PdfPTable(4);
			int width5[] = { 18, 32, 16, 34 };
			table5.setWidths(width5);
			table5.setTotalWidth(540);
			table5.setLockedWidth(true);
			PdfPCell cell51 = new PdfPCell(new Paragraph("委托代理人：", setChinese(FS14)));
			PdfPCell cell52 = new PdfPCell(new Paragraph(svo.getSellerProxy(), setChinese(FS14)));
			PdfPCell cell53 = new PdfPCell(new Paragraph("联系电话：", setChinese(FS14)));
			PdfPCell cell54 = new PdfPCell(new Paragraph(svo.getSellerProxyCall(), setChinese(FS14)));
			cell51.setBorder(0);
			cell53.setBorder(0);
			cell52.setBorderWidthLeft(0);
			cell52.setBorderWidthTop(0);
			cell52.setBorderWidthRight(0);
			cell52.setPaddingBottom(5);
			cell54.setBorderWidthLeft(0);
			cell54.setBorderWidthTop(0);
			cell54.setBorderWidthRight(0);
			cell54.setPaddingBottom(5);
			table5.addCell(cell51);
			table5.addCell(cell52);
			table5.addCell(cell53);
			table5.addCell(cell54);
			document.add(table5);
			Paragraph blankRow5 = new Paragraph(24f, " ", setChinese(FS14));
			document.add(blankRow5);

			PdfPTable table6 = new PdfPTable(2);
			int width6[] = { 18, 82 };
			table6.setWidths(width6);
			table6.setTotalWidth(540);
			table6.setLockedWidth(true);
			PdfPCell cell61 = new PdfPCell(new Paragraph("乙方(买方)：", setChinese(FS14)));
			PdfPCell cell62 = new PdfPCell(new Paragraph(bvo.getBuyerName(), setChinese(FS14)));
			cell61.setBorder(0);
			cell62.setBorderWidthLeft(0);
			cell62.setBorderWidthTop(0);
			cell62.setBorderWidthRight(0);
			cell62.setPaddingBottom(5);
			table6.addCell(cell61);
			table6.addCell(cell62);
			document.add(table6);
			Paragraph blankRow6 = new Paragraph(12f, " ", setChinese(FS14));
			document.add(blankRow6);

			PdfPTable table7 = new PdfPTable(4);
			int width7[] = { 11, 20, 29, 40 };
			table7.setWidths(width7);
			table7.setTotalWidth(540);
			table7.setLockedWidth(true);
			PdfPCell cell71 = new PdfPCell(new Paragraph("国籍：", setChinese(FS14)));
			PdfPCell cell72 = new PdfPCell(new Paragraph(dBo.getDictName("ct_504", String.valueOf(bvo.getBuyerNationality())), setChinese(FS14)));
			PdfPCell cell73 = new PdfPCell(new Paragraph("居住(注册)所在省市：", setChinese(FS14)));
			PdfPCell cell74 = new PdfPCell(new Paragraph(dBo.getDictName("ct_524", String.valueOf(bvo.getBuyerProvince())), setChinese(FS14)));
			cell71.setBorder(0);
			cell73.setBorder(0);
			cell72.setBorderWidthLeft(0);
			cell72.setBorderWidthTop(0);
			cell72.setBorderWidthRight(0);
			cell72.setPaddingBottom(5);
			cell74.setBorderWidthLeft(0);
			cell74.setBorderWidthTop(0);
			cell74.setBorderWidthRight(0);
			cell74.setPaddingBottom(5);
			table7.addCell(cell71);
			table7.addCell(cell72);
			table7.addCell(cell73);
			table7.addCell(cell74);
			document.add(table7);
			Paragraph blankRow7 = new Paragraph(12f, " ", setChinese(FS14));
			document.add(blankRow7);

			PdfPTable table8 = new PdfPTable(6);
			int width8[] = { 17, 18, 11, 12, 18, 24 };
			table8.setWidths(width8);
			table8.setTotalWidth(540);
			table8.setLockedWidth(true);
			String buyerBirth = DateUtil.parseDateTime3(bvo.getBuyerBirth());
			PdfPCell cell81 = new PdfPCell(new Paragraph("个人/公司：", setChinese(FS14)));
			PdfPCell cell82 = new PdfPCell(new Paragraph("" + bvo.getAttribute("buyer_type_dict_name"), setChinese(FS14)));
			PdfPCell cell83 = new PdfPCell(new Paragraph("性别：", setChinese(FS14)));
			PdfPCell cell84 = new PdfPCell(new Paragraph("" + bvo.getAttribute("buyer_sex_dict_name"), setChinese(FS14)));
			PdfPCell cell85 = new PdfPCell(new Paragraph("出生年月日：", setChinese(FS14)));
			PdfPCell cell86 = new PdfPCell(new Paragraph(buyerBirth, setChinese(FS14)));
			cell81.setBorder(0);
			cell83.setBorder(0);
			cell85.setBorder(0);
			cell82.setBorderWidthLeft(0);
			cell82.setBorderWidthTop(0);
			cell82.setBorderWidthRight(0);
			cell82.setPaddingBottom(5);
			cell84.setBorderWidthLeft(0);
			cell84.setBorderWidthTop(0);
			cell84.setBorderWidthRight(0);
			cell84.setPaddingBottom(5);
			cell86.setBorderWidthLeft(0);
			cell86.setBorderWidthTop(0);
			cell86.setBorderWidthRight(0);
			cell86.setPaddingBottom(5);
			table8.addCell(cell81);
			table8.addCell(cell82);
			table8.addCell(cell83);
			table8.addCell(cell84);
			table8.addCell(cell85);
			table8.addCell(cell86);
			document.add(table8);
			Paragraph blankRow8 = new Paragraph(12f, " ", setChinese(FS14));
			document.add(blankRow8);

			PdfPTable table9 = new PdfPTable(4);
			int width9[] = { 15, 56, 11, 18 };
			table9.setWidths(width9);
			table9.setTotalWidth(540);
			table9.setLockedWidth(true);
			PdfPCell cell91 = new PdfPCell(new Paragraph("住所(址)：", setChinese(FS14)));
			PdfPCell cell92 = new PdfPCell(new Paragraph(bvo.getBuyerAddress(), setChinese(FS14)));
			PdfPCell cell93 = new PdfPCell(new Paragraph("邮编：", setChinese(FS14)));
			PdfPCell cell94 = new PdfPCell(new Paragraph(bvo.getBuyerPostcode(), setChinese(FS14)));
			cell91.setBorder(0);
			cell93.setBorder(0);
			cell92.setBorderWidthLeft(0);
			cell92.setBorderWidthTop(0);
			cell92.setBorderWidthRight(0);
			cell92.setPaddingBottom(5);
			cell94.setBorderWidthLeft(0);
			cell94.setBorderWidthTop(0);
			cell94.setBorderWidthRight(0);
			cell94.setPaddingBottom(5);
			table9.addCell(cell91);
			table9.addCell(cell92);
			table9.addCell(cell93);
			table9.addCell(cell94);
			document.add(table9);
			Paragraph blankRow9 = new Paragraph(12f, " ", setChinese(FS14));
			document.add(blankRow9);

			PdfPTable table10 = new PdfPTable(4);
			int width10[] = { 17, 20, 11, 52 };
			table10.setWidths(width10);
			table10.setTotalWidth(540);
			table10.setLockedWidth(true);
			PdfPCell cell101 = new PdfPCell(new Paragraph("证件名称：", setChinese(FS14)));
			PdfPCell cell102 = new PdfPCell(new Paragraph(dBo.getDictName("ct_506", String.valueOf(bvo.getBuyerCardname())), setChinese(FS14)));
			PdfPCell cell103 = new PdfPCell(new Paragraph("号码：", setChinese(FS14)));
			PdfPCell cell104 = new PdfPCell(new Paragraph(bvo.getBuyerCardcode(), setChinese(FS14)));
			cell101.setBorder(0);
			cell103.setBorder(0);
			cell102.setBorderWidthLeft(0);
			cell102.setBorderWidthTop(0);
			cell102.setBorderWidthRight(0);
			cell102.setPaddingBottom(5);
			cell104.setBorderWidthLeft(0);
			cell104.setBorderWidthTop(0);
			cell104.setBorderWidthRight(0);
			cell104.setPaddingBottom(5);
			table10.addCell(cell101);
			table10.addCell(cell102);
			table10.addCell(cell103);
			table10.addCell(cell104);
			document.add(table10);
			Paragraph blankRow10 = new Paragraph(12f, " ", setChinese(FS14));
			document.add(blankRow10);

			PdfPTable table11 = new PdfPTable(2);
			int width11[] = { 17, 83 };
			table11.setWidths(width11);
			table11.setTotalWidth(540);
			table11.setLockedWidth(true);
			PdfPCell cell111 = new PdfPCell(new Paragraph("联系电话：", setChinese(FS14)));
			PdfPCell cell112 = new PdfPCell(new Paragraph(bvo.getBuyerCall(), setChinese(FS14)));
			cell111.setBorder(0);
			cell112.setBorderWidthLeft(0);
			cell112.setBorderWidthTop(0);
			cell112.setBorderWidthRight(0);
			cell112.setPaddingBottom(5);
			table11.addCell(cell111);
			table11.addCell(cell112);
			document.add(table11);
			Paragraph blankRow11 = new Paragraph(12f, " ", setChinese(FS14));
			document.add(blankRow11);

			PdfPTable table12 = new PdfPTable(4);
			int width12[] = { 25, 25, 15, 35 };
			table12.setWidths(width12);
			table12.setTotalWidth(540);
			table12.setLockedWidth(true);
			PdfPCell cell121 = new PdfPCell(new Paragraph("委托/法定代表人：", setChinese(FS14)));
			PdfPCell cell122 = new PdfPCell(new Paragraph(bvo.getBuyerProxy(), setChinese(FS14)));
			PdfPCell cell123 = new PdfPCell(new Paragraph("联系电话：", setChinese(FS14)));
			PdfPCell cell124 = new PdfPCell(new Paragraph(bvo.getBuyerProxyCall(), setChinese(FS14)));
			cell121.setBorder(0);
			cell123.setBorder(0);
			cell122.setBorderWidthLeft(0);
			cell122.setBorderWidthTop(0);
			cell122.setBorderWidthRight(0);
			cell122.setPaddingBottom(5);
			cell124.setBorderWidthLeft(0);
			cell124.setBorderWidthTop(0);
			cell124.setBorderWidthRight(0);
			cell124.setPaddingBottom(5);
			table12.addCell(cell121);
			table12.addCell(cell122);
			table12.addCell(cell123);
			table12.addCell(cell124);
			document.add(table12);
			Paragraph blankRow12 = new Paragraph(12f, " ", setChinese(FS14));
			document.add(blankRow12);

			PdfPTable table13 = new PdfPTable(2);
			int width13[] = { 15, 85 };
			table13.setWidths(width13);
			table13.setTotalWidth(540);
			table13.setLockedWidth(true);
			PdfPCell cell131 = new PdfPCell(new Paragraph("住所(址)：", setChinese(FS14)));
			PdfPCell cell132 = new PdfPCell(new Paragraph(bvo.getBuyerProxyAdr(), setChinese(FS14)));
			cell131.setBorder(0);
			cell132.setBorderWidthLeft(0);
			cell132.setBorderWidthTop(0);
			cell132.setBorderWidthRight(0);
			cell132.setPaddingBottom(5);
			table13.addCell(cell131);
			table13.addCell(cell132);
			document.add(table13);
			Paragraph blankRow13 = new Paragraph(12f, " ", setChinese(FS14));
			document.add(blankRow13);
			document.newPage();
			Paragraph p;

			Phrase ph;

			Chunk c;
			Chunk ch;
			Chunk chu;
			Chunk chun;
			Chunk chunk;

			ph = new Phrase("甲、乙双方遵循自愿、公平和诚实信用的原则、经协商一致，就乙方向甲方预订《", setChinese(FS14));
			c = new Chunk(kg4 + baseHandle(ecvo.getProjectName()) + kg4, setChinese(FS14));
			c.setUnderline(0.1f, -1f);
			ch = new Chunk(" 》 商品房事宜，订立本合同。", setChinese(FS14));
			p = new Paragraph();
			p.setLeading(LD21);
			p.setFirstLineIndent(FL28);
			p.add(ph);
			p.add(c);
			p.add(ch);
			document.add(p);

			c = new Chunk("第一条 ", setChinese(FS14, Font.BOLD));
			ch = new Chunk("乙方预订", setChinese(FS14));
			chu = new Chunk(kg4 + baseHandle(ecvo.getN1_1()) + kg4, setChinese(FS14));
			chu.setUnderline(0.1f, -1f);
			chun = new Chunk("路", setChinese(FS14));
			chunk = new Chunk(kg4 + baseHandle(ecvo.getN1_2()) + kg4, setChinese(FS14));
			chunk.setUnderline(0.1f, -1f);
			Chunk c1 = new Chunk("号《", setChinese(FS14));
			Chunk c2 = new Chunk(kg4 + baseHandle(ecvo.getProjectName()) + kg4, setChinese(FS14));
			c2.setUnderline(0.1f, -1f);
			Chunk c3 = new Chunk("》", setChinese(FS14));
			Chunk c4 = new Chunk(kg4 + baseHandle(ecvo.getN1_4()) + kg4, setChinese(FS14));
			c4.setUnderline(0.1f, -1f);
			Chunk c5 = new Chunk("幢", setChinese(FS14));
			Chunk c6 = new Chunk(kg4 + baseHandle(ecvo.getN1_6()) + kg4, setChinese(FS14));
			c6.setUnderline(0.1f, -1f);
			Chunk c7 = new Chunk("单元 ", setChinese(FS14));
			Chunk c10 = new Chunk(kg4 + baseHandle(ecvo.getN1_7()) + kg4, setChinese(FS14));
			c10.setUnderline(0.1f, -1f);
			Chunk c11 = new Chunk("室（以下简称该房屋）。甲方已领取该房屋 ", setChinese(FS14));
			Chunk c12 = new Chunk(kg4 + baseHandle(ecvo.getAttribute("N1_8_dict_name")) + kg4, setChinese(FS14));
			c12.setUnderline(0.1f, -1f);
			Chunk c13 = new Chunk("（证书号：", setChinese(FS14));
			Chunk c14 = new Chunk(kg4 + baseHandle(ecvo.getN1_9()) + kg4, setChinese(FS14));
			c14.setUnderline(0.1f, -1f);
			Chunk c15 = new Chunk("），并经  ", setChinese(FS14));
			Chunk c16 = new Chunk(kg4 + baseHandle(ecvo.getN1_10()) + kg4, setChinese(FS14));
			c16.setUnderline(0.1f, -1f);
			Chunk c17 = new Chunk("测绘机构测绘，该房屋建筑面积为 ", setChinese(FS14));
			Chunk c18 = new Chunk(kg4 + baseHandle(ecvo.getN1_11()) + kg4, setChinese(FS14));
			c18.setUnderline(0.1f, -1f);
			Chunk c19 = new Chunk("平方米，另有地下附属面积", setChinese(FS14));
			Chunk c20 = new Chunk(kg4 + baseHandle(ecvo.getCellarArea()) + kg4, setChinese(FS14));
			c20.setUnderline(0.1f, -1f);
			Chunk c21 = new Chunk("平方米。该房屋定于 ", setChinese(FS14));
			Chunk c22 = new Chunk(kg4 + baseHandle(ecvo.getN1_12()) + kg4, setChinese(FS14));
			c22.setUnderline(0.1f, -1f);
			Chunk c23 = new Chunk("年", setChinese(FS14));
			Chunk c24 = new Chunk(kg4 + baseHandle(ecvo.getN1_13()) + kg4, setChinese(FS14));
			c24.setUnderline(0.1f, -1f);
			Chunk c25 = new Chunk("月", setChinese(FS14));
			Chunk c26 = new Chunk(kg4 + baseHandle(ecvo.getN1_14()) + kg4, setChinese(FS14));
			c26.setUnderline(0.1f, -1f);
			Chunk c27 = new Chunk("日交付。 ", setChinese(FS14));
			p = new Paragraph();
			p.setSpacingBefore(SPB28);
			p.setLeading(LD21);
			p.setFirstLineIndent(FL28);
			p.add(c);
			p.add(ch);
			p.add(chu);
			p.add(chun);
			p.add(chunk);
			p.add(c1);
			p.add(c2);
			p.add(c3);
			p.add(c4);
			p.add(c5);
			p.add(c6);
			p.add(c7);
			p.add(c10);
			p.add(c11);
			p.add(c12);
			p.add(c13);
			p.add(c14);
			p.add(c15);
			p.add(c16);
			p.add(c17);
			p.add(c18);
			p.add(c19);
			p.add(c20);
			p.add(c21);
			p.add(c22);
			p.add(c23);
			p.add(c24);
			p.add(c25);
			p.add(c26);
			p.add(c27);
			document.add(p);

			c1 = new Chunk("第二条 ", setChinese(FS14, Font.BOLD));
			c2 = new Chunk("乙方预订的该房屋每平方米建筑面积买卖单价为人民币", setChinese(FS14));
			c3 = new Chunk(kg4 + baseHandle(ecvo.getN2_1()) + kg4, setChinese(FS14));
			c3.setUnderline(0.1f, -1f);
			c4 = new Chunk("元，", setChinese(FS14));
			p = new Paragraph();
			p.setSpacingBefore(SPB28);
			p.setLeading(LD21);
			p.setFirstLineIndent(FL28);
			p.add(c1);
			p.add(c2);
			p.add(c3);
			p.add(c4);
			document.add(p);

			c5 = new Chunk("(大写)：", setChinese(FS14));
			c6 = new Chunk(kg4 + baseHandle(ecvo.getN2_2()) + kg4, setChinese(FS14));
			c6.setUnderline(0.1f, -1f);
			c7 = new Chunk("。", setChinese(FS14));
			p = new Paragraph();
			p.setLeading(LD21);
			p.add(c5);
			p.add(c6);
			p.add(c7);
			document.add(p);
			Chunk c8 = new Chunk("乙方预订的该房屋总房价为人民币", setChinese(FS14));
			Chunk c9 = new Chunk(kg4 + baseHandle(ecvo.getN2_3()) + kg4, setChinese(FS14));
			c9.setUnderline(0.1f, -1f);
			c10 = new Chunk("元，", setChinese(FS14));
			p = new Paragraph();
			p.setLeading(LD21);
			p.add(c8);
			p.add(c9);
			p.add(c10);
			document.add(p);
			c11 = new Chunk("(大写)：", setChinese(FS14));
			c12 = new Chunk(kg4 + baseHandle(ecvo.getN2_4()) + kg4, setChinese(FS14));
			c12.setUnderline(0.1f, -1f);
			c13 = new Chunk("。", setChinese(FS14));
			p = new Paragraph();
			p.setLeading(LD21);
			p.add(c11);
			p.add(c12);
			p.add(c13);
			document.add(p);
			c14 = new Chunk("乙方采取", setChinese(FS14));
			c15 = new Chunk(kg4 + baseHandle(ecvo.getAttribute("N2_5_dict_name")) + kg4, setChinese(FS14));
			c15.setUnderline(0.1f, -1f);
			c16 = new Chunk("方式。", setChinese(FS14));
			p = new Paragraph();
			p.setLeading(LD21);
			p.add(c14);
			p.add(c15);
			p.add(c16);
			document.add(p);

			c1 = new Chunk("第三条 ", setChinese(FS14, Font.BOLD));
			c2 = new Chunk("乙方同意签订本协议时，支付定金人民币", setChinese(FS14));
			c3 = new Chunk(kg4 + baseHandle(ecvo.getN3_1()) + kg4, setChinese(FS14));
			c3.setUnderline(0.1f, -1f);
			c4 = new Chunk("元，", setChinese(FS14));
			p = new Paragraph();
			p.setSpacingBefore(SPB28);
			p.setLeading(LD21);
			p.setFirstLineIndent(30);
			p.add(c1);
			p.add(c2);
			p.add(c3);
			p.add(c4);
			document.add(p);
			c5 = new Chunk("(大写)：", setChinese(FS14));
			c6 = new Chunk(kg4 + baseHandle(ecvo.getN3_2()) + kg4, setChinese(FS14));
			c6.setUnderline(0.1f, -1f);
			c7 = new Chunk(",", setChinese(FS14));
			p = new Paragraph();
			p.setLeading(LD21);
			p.add(c5);
			p.add(c6);
			p.add(c7);
			document.add(p);

			int ver = ecvo.getN1_8();
			if(ver == 0){
				c8 = new Chunk("作为甲、乙方双方当事人订立商品房预售合同的担保，签订商品房预售合同后，乙方支付的定金转为房价款。", setChinese(FS14));
			}
			if(ver == 1){
				c8 = new Chunk("作为甲、乙方双方当事人订立商品房出售合同的担保，签订商品房出售合同后，乙方支付的定金转为房价款。", setChinese(FS14));
			}
			p = new Paragraph();
			p.setLeading(LD21);
			p.add(c8);
			document.add(p);

			c1 = new Chunk("第四条 ", setChinese(FS14, Font.BOLD));
			c2 = new Chunk("甲、乙双方商定，预订期为", setChinese(FS14));
			c3 = new Chunk(kg4 + baseHandle(ecvo.getN4_1()) + kg4, setChinese(FS14));
			c3.setUnderline(0.1f, -1f);
			c4 = new Chunk("天，乙方于", setChinese(FS14));
			c5 = new Chunk(kg4 + baseHandle(ecvo.getN4_2() == null ? "" : ecvo.getN4_2().substring(0, 4)) + kg4, setChinese(FS14));
			c5.setUnderline(0.1f, -1f);
			c6 = new Chunk("年", setChinese(FS14));
			c7 = new Chunk(kg4 + baseHandle(ecvo.getN4_2() == null ? "" : ecvo.getN4_2().substring(4, 6)) + kg4, setChinese(FS14));
			c7.setUnderline(0.1f, -1f);
			c8 = new Chunk("月", setChinese(FS14));
			c9 = new Chunk(kg4 + baseHandle(ecvo.getN4_2() == null ? "" : ecvo.getN4_2().substring(6, 8)) + kg4, setChinese(FS14));
			c9.setUnderline(0.1f, -1f);
			c10 = new Chunk("日前到 ", setChinese(FS14));
			c11 = new Chunk(kg4 + baseHandle(ecvo.getN4_3()) + kg4, setChinese(FS14));
			c11.setUnderline(0.1f, -1f);
			if(ver == 0){
				c12 = new Chunk("与甲方签订《青岛市商品房预售合同》。", setChinese(FS14));
			}
			if(ver == 1){
				c12 = new Chunk("与甲方签订《青岛市商品房出售合同》。", setChinese(FS14));
			}
			p = new Paragraph();
			p.setSpacingBefore(SPB28);
			p.setLeading(LD21);
			p.setFirstLineIndent(FL28);
			p.add(c1);
			p.add(c2);
			p.add(c3);
			p.add(c4);
			p.add(c5);
			p.add(c6);
			p.add(c7);
			p.add(c8);
			p.add(c9);
			p.add(c10);
			p.add(c11);
			p.add(c12);
			document.add(p);

			c1 = new Chunk("第五条 ", setChinese(FS14, Font.BOLD));
			if(ver == 0){
				c2 = new Chunk("甲方同意将发布或提供的广告、售楼书、样品所标明的房屋平面布局、结构、建筑质量、装饰标准及附属设施、配套设施等状况作为商品房预售合同的附件。", setChinese(FS14));
			}
			if(ver == 1){
				c2 = new Chunk("甲方同意将发布或提供的广告、售楼书、样品所标明的房屋平面布局、结构、建筑质量、装饰标准及附属设施、配套设施等状况作为商品房出售合同的附件。", setChinese(FS14));
			}
			p = new Paragraph();
			p.setSpacingBefore(SPB28);
			p.setLeading(LD21);
			p.setFirstLineIndent(FL28);
			p.add(c1);
			p.add(c2);
			document.add(p);

			c1 = new Chunk("第六条 ", setChinese(FS14, Font.BOLD));
			if(ver == 0){
				c2 = new Chunk("在本协议的第四条约定的预订期限内，除本协议第七条、第八条约定的情形外，甲方拒绝签订商品房预售合同的，双倍返还已收取的定金；乙方拒绝签订商品房预售合同的，无权要求甲方返还已收取的定金。", setChinese(FS14));
			}
			if(ver == 1){
				c2 = new Chunk("在本协议的第四条约定的预订期限内，除本协议第七条、第八条约定的情形外，甲方拒绝签订商品房出售合同的，双倍返还已收取的定金；乙方拒绝签订商品房出售合同的，无权要求甲方返还已收取的定金。", setChinese(FS14));
			}
			p = new Paragraph();
			p.setSpacingBefore(SPB28);
			p.setLeading(LD21);
			p.setFirstLineIndent(FL28);
			p.add(c1);
			p.add(c2);
			document.add(p);

			c1 = new Chunk("第七条 ", setChinese(FS14, Font.BOLD));
			if(ver == 0){
				c2 = new Chunk("有下列情况之一，乙方拒绝签订商品房预售合同的，甲方应全额返还乙方已支付的定金。 ", setChinese(FS14));
				c3 = new Chunk("1、 甲乙双方在签订商品房预售合同时，因面积误差处理、房屋交付、房屋质量、违约责任、争议解决方式等条款，存在分歧，不能协商一致； ", setChinese(FS14));
				c4 = new Chunk("2、 甲乙双方签订本协议后、签订商品房预售合同前，由司法机关、行政机关依法限制该房屋房地产权利的。  ", setChinese(FS14));
			}
			if(ver == 1){
				c2 = new Chunk("有下列情况之一，乙方拒绝签订商品房出售合同的，甲方应全额返还乙方已支付的定金。 ", setChinese(FS14));
				c3 = new Chunk("1、甲乙双方在签订商品房出售合同时，因面积误差处理、房屋交付、房屋质量、违约责任、争议解决方式等条款，存在分歧，不能协商一致； ", setChinese(FS14));
				c4 = new Chunk("2、甲乙双方签订本协议后、签订商品房出售合同前，由司法机关、行政机关依法限制该房屋房地产权利的。  ", setChinese(FS14));
			}
			
			p = new Paragraph();
			p.setSpacingBefore(SPB28);
			p.setLeading(LD21);
			p.setFirstLineIndent(FL28);
			p.add(c1);
			p.add(c2);
			document.add(p);
			p = new Paragraph();
			p.setLeading(LD21);
			p.setFirstLineIndent(FL28);
			p.add(c3);
			document.add(p);
			p = new Paragraph();
			p.setLeading(LD21);
			p.setFirstLineIndent(FL28);
			p.add(c4);
			document.add(p);

			c1 = new Chunk("第八条 ", setChinese(FS14, Font.BOLD));
			if(ver == 0){
				c2 = new Chunk("有下列情况之一，乙方拒绝签订商品房预售合同的，甲方应双倍返还乙方已支付的定金： ", setChinese(FS14));
			}
			if(ver == 1){
				c2 = new Chunk("有下列情况之一，乙方拒绝签订商品房出售合同的，甲方应双倍返还乙方已支付的定金： ", setChinese(FS14));
			}
			c3 = new Chunk("1、 甲方未遵守本协议第二条、第五条约定的；  ", setChinese(FS14));
			c4 = new Chunk("2、 甲方未告知乙方在签订本协议前该房屋已存在的抵押、预租、查封等事实的。   ", setChinese(FS14));
			p = new Paragraph();
			p.setSpacingBefore(SPB28);
			p.setLeading(LD21);
			p.setFirstLineIndent(FL28);
			p.add(c1);
			p.add(c2);
			document.add(p);
			p = new Paragraph();
			p.setLeading(LD21);
			p.setFirstLineIndent(FL28);
			p.add(c3);
			document.add(p);
			p = new Paragraph();
			p.setLeading(LD21);
			p.setFirstLineIndent(FL28);
			p.add(c4);
			document.add(p);

			c1 = new Chunk("第九条 ", setChinese(FS14, Font.BOLD));
			c2 = new Chunk("本协议一式", setChinese(FS14));
			c3 = new Chunk(kg4 + baseHandle(ecvo.getN9_1()) + kg4, setChinese(FS14));
			c3.setUnderline(0.1f, -1f);
			c4 = new Chunk("份，甲乙双方各执 ", setChinese(FS14));
			c5 = new Chunk(kg4 + baseHandle(ecvo.getN9_2()) + kg4, setChinese(FS14));
			c5.setUnderline(0.1f, -1f);
			c6 = new Chunk("份，", setChinese(FS14));
			c7 = new Chunk(kg4 + baseHandle(ecvo.getN9_3()) + kg4, setChinese(FS14));
			c7.setUnderline(0.1f, -1f);
			Chunk c7_1 = new Chunk(" 、 ", setChinese(FS14));
			c8 = new Chunk(kg4 + baseHandle(ecvo.getN9_4()) + kg4, setChinese(FS14));
			c8.setUnderline(0.1f, -1f);
			c9 = new Chunk("各执一份。", setChinese(FS14));
			p = new Paragraph();
			p.setSpacingBefore(SPB28);
			p.setLeading(LD21);
			p.setFirstLineIndent(FL28);
			p.add(c1);
			p.add(c2);
			p.add(c3);
			p.add(c4);
			p.add(c5);
			p.add(c6);
			p.add(c7);
			p.add(c7_1);
			p.add(c8);
			p.add(c9);
			document.add(p);

			p = new Paragraph();
			p.setSpacingBefore(SPB28);
			p.setLeading(LD21);
			document.add(p);

			Table table = new Table(2, 1);
			table.setBorder(0);
			// 创建表头
			Cell cell1 = new Cell(new Chunk("甲方：（签章）", setChinese(FS14)));
			cell1.setBorder(0);
			Cell cell2 = new Cell(new Chunk("乙方：（签定）", setChinese(FS14)));
			cell2.setBorder(0);
			table.addCell(cell1);
			table.addCell(cell2);
			document.add(table);

			p = new Paragraph();
			p.setSpacingBefore(SPB28);
			p.setLeading(LD21);
			document.add(p);

			table = new Table(2, 1);
			table.setBorder(0);
			c = new Chunk("     年     月     日 ", setChinese(FS14));
			Cell cell3 = new Cell(c);
			cell3.setBorder(0);
			Cell cell4 = new Cell(c);
			cell4.setBorder(0);
			table.addCell(cell3);
			table.addCell(cell4);
			document.add(table);
			Paragraph blank = new Paragraph(24f, " ", setChinese(FS14));
			document.add(blank);

			document.add(getParagraph("  "));

			//甲方拟签人员信息
			SignerVO signerVO;
			if(String.valueOf(cdvo.getSigner()) != null && !"".equals(String.valueOf(cdvo.getSigner()))){
				signerVO = (SignerVO) cqDao.find(SignerVO.class, Long.parseLong(cdvo.getSigner()));
				PdfPTable table14 = new PdfPTable(4);
				int width14[] = { 21, 29, 21, 29 };
				table14.setWidths(width14);
				table14.setTotalWidth(540);
				table14.setLockedWidth(true);
				PdfPCell cell141 = new PdfPCell(new Paragraph("甲方拟签人员：", setChinese(FS14)));
				PdfPCell cell142 = new PdfPCell(new Paragraph(signerVO.getName(), setChinese(FS14)));
				PdfPCell cell143 = new PdfPCell(new Paragraph("销售员证书号：", setChinese(FS14)));
				PdfPCell cell144 = new PdfPCell(new Paragraph(signerVO.getCardCode(), setChinese(FS14)));
				cell141.setBorder(0);
				cell143.setBorder(0);
				cell142.setBorder(0);
				cell144.setBorder(0);
				table14.addCell(cell141);
				table14.addCell(cell142);
				table14.addCell(cell143);
				table14.addCell(cell144);
				document.add(table14);
				Paragraph blankRow14 = new Paragraph(12f, " ", setChinese(FS14));
				document.add(blankRow14);
			}

			document.close();

			ContractPdfVO ctv = new ContractPdfVO();
			ctv.setPdfData(baos.toByteArray());
			baos.close();
			return ctv;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cqDao);
			closeDAO(pdfDAO);
		}
	}

	/**
	 * 功能描述：出售合同文本
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public ContractPdfVO createSellContractPdf(long contractID,int userType,int signFlag) throws Exception {

		try{
			openDAO(cqDao);
			openDAO(pdfDAO);

			DictionaryBO dicBo = new DictionaryBO();
			//查询合同主表信息
			ContractDealVO cdvo = (ContractDealVO) cqDao.find(ContractDealVO.class, contractID);
			//出售合同信息
			SellContractVO scvo = (SellContractVO) cqDao.find(SellContractVO.class, contractID);
			if(scvo == null){
				scvo = new SellContractVO();
			}
			//查询合同甲方信息
			SellerInfoVO svo = new SellerInfoVO();
			List<SellerInfoVO> list1 = cqDao.searchSellerInfo(String.valueOf(contractID));
			if(list1 != null && list1.size() > 0){
				svo = list1.get(0);
			}
			//查询合同乙方信息
			List<BuyerInfoVO> list2 = cqDao.searchBuyerInfo(String.valueOf(contractID));

			Document document = new Document(PageSize.A4);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//定义输出位置并把文档对象装入输出对象中
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			//添加水印
			if(userType == 0) //内网用户
				writer.setPageEvent(new Watermark("合同查阅"));
			if(userType == 1 && (cdvo.getStatus() == 0 || cdvo.getStatus() == 1)) //外网用户草签或待签合同
				if (signFlag != 1) {
					writer.setPageEvent(new Watermark("非正式合同"));
				}else{
					if(cdvo.getStatus() == 1){
						writer.setPageEvent(new Watermark("电子合同"));
					}
				}
			if(userType == 1 && (cdvo.getStatus() != 0 && cdvo.getStatus() != 1 && cdvo.getStatus() != 2)) //外网其他合同
				writer.setPageEvent(new Watermark(dBo.getDictName("ct_500", String.valueOf(cdvo.getStatus())) + "合同"));

			//页眉
			HeaderFooter header = new HeaderFooter(new Phrase(String.valueOf(contractID)
					+ "---------------------------------------------------------------------------------------------"
					+ DateUtil.formatDateTime(DateUtil.parseDateTime2( (DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()))), setChinese(FS10)),
					false);
			header.setBorder(Rectangle.NO_BORDER);
			header.setAlignment(1);
			header.setBorderColor(Color.BLACK);
			document.setHeader(header);

			
			//页码
			HeaderFooter footer = new HeaderFooter(new Phrase("-", setChinese(FS10)), new Phrase("-", setChinese(FS10)));
			footer.setAlignment(1);
			footer.setBorderColor(Color.red);
			footer.setBorder(Rectangle.NO_BORDER);
			document.setFooter(footer);

			document.open();

			//封面
			//合同条形码
			PdfContentByte cd128 = writer.getDirectContent();
			Barcode128 code128 = new Barcode128();
			code128.setCodeType(code128.CODE128);
			code128.setCode(String.valueOf(contractID));
			code128.setBarHeight(30);
			Image image128 = code128.createImageWithBarcode(cd128, null, null);
			image128.setAlignment(2);
			document.add(image128);

			Paragraph sellContract = new Paragraph("青岛市商品房出售合同", setChinese(FS14 * 2, Font.BOLD));
			sellContract.setAlignment(Element.ALIGN_CENTER);
			sellContract.setSpacingBefore(FS14 * 4); //设置段落前间距
			sellContract.setSpacingAfter(FS14 * 6);
			document.add(sellContract);

			//房屋条形码
			PdfContentByte cd2 = writer.getDirectContent();
			Barcode128 code2 = new Barcode128();
			code2.setCodeType(code2.CODE128);
			code2.setCode(String.valueOf(cdvo.getHouseID()));
			code2.setBarHeight(30);
			Image image2 = code2.createImageWithBarcode(cd2, null, null);
			image2.setAlignment(1);
			document.add(image2);

			Paragraph p1 = new Paragraph("青岛市工商行政管理局", setChinese(FS10 * 2));
			p1.setAlignment(Element.ALIGN_CENTER);
			p1.setSpacingBefore(FS10 * 4); //设置段落前间距
			document.add(p1);
			Paragraph p2 = new Paragraph("制定          ", setChinese(FS10 * 2));
			p2.setAlignment(Element.ALIGN_RIGHT);
			document.add(p2);
			Paragraph p3 = new Paragraph("青岛市建设委员会", setChinese(FS10 * 2));
			p3.setAlignment(Element.ALIGN_CENTER);
			document.add(p3);
			document.newPage();

			Paragraph p = new Paragraph("特    别    告    知", setChinese(FS10 * 2, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB20);
			p.setSpacingAfter(SPB20);
			document.add(p);
			//String isNewVer = "0";
			String isNewVer = "1";//是否新合同版本
			if(cdvo.getContractversion() == null || "".equals(cdvo.getContractversion())){
				isNewVer = "0";
			}
			if("0".equals(isNewVer)){
				document.add(getParagraph("一、本合同文本是根据有关房地产转让的法律、法规、规定制定的示范文本，印制的合同条款为提示性条款，供未经预售阶段的商品房（现房）出售时当事人双方约定采用。", setChinese(FS14), FL28, 20.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("二、购房是一种民事法律行为，涉及的标的额较大、专业性较强、法律规范较多，为更好地维护双方当事人的权益，双方签订合同时应当慎重，力求签订得具体、全面、严密。", setChinese(FS14), FL28, 20.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("三、商品房在取得房地产权属登记证明后，方可出售。在签订出售合同前，房地产开发企业应向购房人出示房地产权属登记证明。新建商品房房地产权属登记证明真实性、合法性可向市、区、市房地产交易中心查询。", setChinese(FS14),
						FL28, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document
						.add(getParagraph(
								"四、房地产开发企业出售已抵押的商品房，应事先以书面形式通知抵押权人，同时，将已抵押的事实告知购房人。未通知抵押权人或未告知购房人的，转让行为无效。购房人在签约前可向市、区、市房地产交易中心查询拟购房屋是否已抵押以及是否存在被司法机关或行政机关依法裁定、决定查封或者其他形式限制房地产权利的情况。",
								setChinese(FS14), FL28, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("五、商品房出售合同签订后应及时向房地产登记机构办理商品房变更登记手续。购房人领取房地产权证。", setChinese(FS14), FL28, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("六、双方履行合同发生争议的，可选择向不动产所在地人民法院起诉，也可选择向仲裁委员会申请仲裁。如选择申请仲裁的，可向本市的仲裁委员会申请，也可向外地的仲裁委员会申请，本市仲裁委员会全称为青岛仲裁委员会。",
						setChinese(FS14), FL28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			}
			if("1".equals(isNewVer)){
				document.add(getParagraph("一、本合同文本是根据有关房地产转让的法律、法规、规定制定的示范文本，印制的合同条款为提示性条款，供未经预售阶段的商品房（现房）出售时当事人双方约定采用。", setChinese(FS14), FL28, 20.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("二、购房是一种民事法律行为，涉及的标的额较大、专业性较强、法律规范较多，为更好地维护双方当事人的权益，双方签订合同时应当慎重，力求签订得具体、全面、严密。", setChinese(FS14), FL28, 20.0f, 0.0f,
						Element.ALIGN_LEFT));
				document
						.add(getParagraph(
								"三、房地产开发企业出售已抵押的商品房，应事先以书面形式通知抵押权人，同时，将已抵押的事实告知购房人。未通知抵押权人或未告知购房人的，转让行为无效。购房人在签约前可向市、区、市房地产交易中心查询拟购房屋是否已抵押以及是否存在被司法机关或行政机关依法裁定、决定查封或者其他形式限制房地产权利的情况。",
								setChinese(FS14), FL28, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("四、商品房出售合同签订后应及时向房地产登记机构办理商品房变更登记手续。购房人领取房地产权证。", setChinese(FS14), FL28, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("五、双方履行合同发生争议的，可选择向不动产所在地人民法院起诉，也可选择向仲裁委员会申请仲裁。如选择申请仲裁的，可向本市的仲裁委员会申请，也可向外地的仲裁委员会申请，本市仲裁委员会全称为青岛仲裁委员会。",
						setChinese(FS14), FL28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			}

			document.newPage();

			p = new Paragraph("青岛市商品房出售合同", setChinese(FS14, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(20);
			p.setSpacingAfter(20);
			document.add(p);

			//设表的宽度为523f，一行37个字，一个字占2.7%
			document.add(getPTable(new String[] { "甲方(卖方)：", " *" + svo.getSellerName() }, 18, 82, 0, 0, 0, 0));
			document.add(getPTable(new String[] { "住所：", " *" + svo.getSellerAddress(), " 邮编：", " *" + svo.getSellerPostcode() }, 9, 61, 9, 21, 0, 0));
			document.add(getPTable(new String[] { "营业执照号码：", " *" + svo.getSellerBizcardcode() }, 21, 79, 0, 0, 0, 0));
			document.add(getPTable(new String[] { "资质证书号码： ", " *" + svo.getSellerCertcode() }, 21, 79, 0, 0, 0, 0));
			document.add(getPTable(new String[] { "法定代表人：", " *" + svo.getSellerDelegate(), " 联系电话：", " *" + svo.getSellerDlgCall() }, 18, 42, 15, 25, 0, 0));
			document.add(getPTable(new String[] { "委托代理人：", " *" + svo.getSellerProxy(), " 联系电话：", " *" + svo.getSellerProxyCall() }, 18, 42, 15, 25, 0, 0));

			String buyername = "";
			if(list2 != null && list2.size() > 0){
				for(BuyerInfoVO bvo : list2){
					p = new Paragraph();
					p.setSpacingBefore(20);
					p.setSpacingAfter(20);
					document.add(p);
					buyername += bvo.getBuyerName();
					document.add(getPTable(new String[] { "乙方(买方)：", " *" + bvo.getBuyerName() }, 18, 82, 0, 0, 0, 0));
					document.add(getPTable(new String[] { "国籍： ", " *" + dBo.getDictName("ct_504", String.valueOf(bvo.getBuyerNationality())), "  居住(注册)所在省市：",
							" *" + dBo.getDictName("ct_524", String.valueOf(bvo.getBuyerProvince())) }, 9, 30, 30, 31, 0, 0));

					String buyerBirth = DateUtil.parseDateTime3(bvo.getBuyerBirth());
					document.add(getPTable(new String[] { "个人/公司", "*" + baseHandle(bvo.getAttribute("buyer_type_dict_name")), " 性别：",
							" *" + baseHandle(bvo.getAttribute("buyer_sex_dict_name")), " 出生年月日： ", " *" + baseHandle(buyerBirth) }, 15, 15, 9, 9, 18, 34));
					document.add(getPTable(
							new String[] { "住址： ", "   *" + baseHandle(bvo.getBuyerAddress()), " 邮编：", " *" + baseHandle(bvo.getBuyerPostcode()) }, 9, 67, 9,
							15, 0, 0));
					document.add(getPTable(new String[] { "证件名称：", "  *" + dBo.getDictName("ct_506", String.valueOf(bvo.getBuyerCardname())), " 号码：",
							" *" + baseHandle(bvo.getBuyerCardcode()) }, 15, 40, 9, 36, 0, 0));
					document.add(getPTable(new String[] { "联系电话：", " *" + bvo.getBuyerCall() }, 15, 85, 0, 0, 0, 0));
					document.add(getPTable(new String[] { "委托/法定代理人：", "  *" + baseHandle(bvo.getBuyerProxy()), " 联系电话：",
							"  *" + baseHandle(bvo.getBuyerProxyCall()) }, 27, 33, 15, 25, 0, 0));
					document.add(getPTable(new String[] { "住址：", "   *" + baseHandle(bvo.getBuyerProxyAdr()) }, 12, 88, 0, 0, 0, 0));
				}
			}

			document.newPage();

			//
			String sellN5_4 = null, sellN7_6 = null, sellN13_4 = null, sellFJ3 = null, sellFJ5 = null, sellBC = null;
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("contractid", "=", contractID));
			List<AttachVO> atVOlList = cqDao.searchByAttibutes(AttachVO.class, params, new ArrayList<MetaOrder>(), new Page());
			if(atVOlList != null && atVOlList.size() > 0){
				for(AttachVO attachVO : atVOlList){
					if(attachVO.getContent() != null){
						String content = new String(attachVO.getContent());
						if(attachVO.getSerial() == 2054){
							sellN5_4 = content;
						}else if(attachVO.getSerial() == 2076){
							sellN7_6 = content;
						}else if(attachVO.getSerial() == 2134){
							sellN13_4 = content;
						}else if(attachVO.getSerial() == 2003){
							sellFJ3 = content;
						}else if(attachVO.getSerial() == 2005){
							sellFJ5 = content;
						}else if(attachVO.getSerial() == 2901){
							sellBC = content;
						}
					}

				}
			}

			document.add(getParagraph("甲、乙双方在平等、自愿、协商一致的基础上，就乙方购买甲方出售的《  " + "  *" + scvo.getProjectName() + "   》商品房事宜，订立本合同。"));
			if("1".equals(isNewVer)){
				document.add(getParagraph("^第一条 甲方依法取得   " + "  *" + scvo.getN1_1() + "   区／市  " + "  *" + scvo.getN1_2() + " 地块土地使用权（出让合同编号：  " + "  *"
						+ scvo.getN1_3() + "  ），投资建造的《  " + "  *" + scvo.getN1_4() + "  》商品房已竣工，并办理了房屋初始登记。"));
			}else{
				document.add(getParagraph("^第一条 甲方依法取得   " + "  *" + scvo.getN1_1() + "   区／市  " + "  *" + scvo.getN1_2() + " 地块土地使用权（出让合同编号：  " + "  *"
						+ scvo.getN1_3() + "  ），投资建造的《  " + "  *" + scvo.getN1_4() + "  》商品房已竣工，并取得了房地产权属登记证明，证书号：" + "  *" + scvo.getN1_5() + "。"));
			}
			document.add(getParagraph("^第二条 乙方向甲方购买   " + "  *" + scvo.getN1_1() + "  区／市  " + "  *" + scvo.getN2_1() + "  路  " + "  *" + scvo.getLaneName()
					+ " 号《   " + "  *" + scvo.getN2_3() + "   》    " + "  *" + scvo.getSubLane() + "   栋    " + "  *" + scvo.getN2_4() + "  单元    " + "  *"
					+ scvo.getN2_5() + "   层    " + "  *" + scvo.getN2_6() + "   户（以下简称该房屋）。据   " + "  *" + scvo.getChargeDep() + "   认定的测绘机构测量该房屋建筑面积为    "
					+ "  *" + scvo.getN2_8() + "  平方米， 其中套内建筑面积为    " + "  *" + scvo.getN2_9() + "   平方米、公用分摊建筑面积为    " + "  *" + scvo.getN2_10()
					+ "  平方米，另有地 下附属面积    " + "  *" + scvo.getCellarArea() + "   平方米（价格详见补充条款）。该房屋建筑层高为    " + "  *" + scvo.getN2_11() + "   米。"));
			document.add(getParagraph("该房屋平面图见本合同附件二；该房屋建筑结构、装修及设备标准见本合同附件三 ；该房屋相关情况（抵押关系、租赁关系）见本合同附件四，（相邻关系及小区平面图 ）见本合同附件六；该房屋的使用公约见本合同附件五。"));
			document.add(getParagraph("^第三条 乙方购买该房屋每平方米房屋建筑面积单价（不包含房屋全装修价格）为 人民币    " + "  *" + "￥" + "   " + "  *" + scvo.getN3_1() + "   元。 "));
			document.add(getPTable(new String[] { "（大写）： ", "      " + "  *" + scvo.getN3_2() + " ", " 。  " }, 15, 60, 25, 0, 0, 0));
			document.add(getParagraph("根据该房屋的房屋建筑面积，乙方购买该房屋的总房价款（不包含房屋全装修价格 ）为人民币     " + "  *" + "￥" + "   " + "  *" + scvo.getN3_3() + "     元。 "));
			document.add(getPTable(new String[] { "（大写）：  ", "      " + "  *" + scvo.getN3_4() + "  ", " 。  " }, 15, 60, 25, 0, 0, 0));
			document.add(getParagraph("该房屋全装修总价为人民币        " + "  *" + "￥" + "  *" + scvo.getFitmentPrice() + "      元。"));
			document.add(getPTable(new String[] { "（大写）：  ", "    " + "  *" + scvo.getFitmentPriceCn() + "  ", " 。  " }, 15, 60, 25, 0, 0, 0));
			document.add(getParagraph("该房屋的总房价款是指该房屋所有权和该房屋占用范围内土地使用权总价格。"));
			document.add(getParagraph("^第四条  乙方应按本合同约定的付款方式和付款期限将房价款解入甲方的帐户／监管帐户（金融机构／监管机构：   " + "  *" + scvo.getN4_1() + "     、帐户名称：    " + "  *"
					+ scvo.getN4_2() + "     、帐号：    " + "  *" + scvo.getN4_3() + "     ）。 "));
			document.add(getParagraph("乙方的付款时间和付款方式由甲、乙双方在本合同附件一中约定明确。"));
			document.add(getParagraph("甲方收到乙方支付的每一笔房款时均应开具发票。 "));
			document
.add(getParagraph("^第五条 乙方如未按合同约定的时间付款，应当向甲方支付违约金，违约金按逾期 未付款的日万分之    " + "  *" + scvo.getN5_1()
					+ "   计算，违约金自本合同的应付款期限之第二天起算至实际 付款之日止。逾期超过    " + "  *" + scvo.getN5_2() + "   天，甲方有权选择下列第    " + "  *" + getRes(scvo.getN5_3(), 2)
					+ "  种方案追究乙方责任： "));
			document.add(getParagraph("一、甲方有权单方面解除本合同。"));
			document.add(getParagraph("二、" + sellN5_4));
			document.add(getParagraph("^第六条 乙方支付房价款若采用银行贷款付款的，而银行未按本合同约定的期限代 乙方向甲方付款的，仍视为乙方未按合同约定的时间付款，但因甲方原因的除外。"));
			document.add(getParagraph("^第七条 甲、乙双方按下列第     " + "  *" + getRes(scvo.getN7_1(), 2) + "  种约定确定该房屋交付使用日期："));
			document.add(getParagraph("一、本合同自甲乙双方签署之日起    " + "  *" + scvo.getN7_2() + "     天内，甲方向乙方交付该房屋。 "));
			document.add(getParagraph("二、本合同经      " + "  *" + scvo.getN7_3() + "   公证处公证之日起      " + "  *" + scvo.getN7_4() + "     天内，甲方向乙方交付该房屋。"));
			document.add(getParagraph("三、甲方在收到乙方全部房价款之日起      " + "  *" + scvo.getN7_5() + "   天内，甲方向乙方交付该房屋。"));
			document.add(getParagraph("四、      " + "  *" + sellN7_6 + " "));
			document.add(getParagraph("^第八条   甲方向乙方保证在交付该房屋时："));
			document.add(getParagraph("（一）该房屋没有产权纠纷和财务纠纷；"));
			document.add(getParagraph("（二）甲方未设定抵押权或已清除原由甲方设定的抵押权；"));
			document.add(getParagraph("（三）已缴纳了甲方应缴纳的房屋维修基金。"));
			document.add(getParagraph("如交付房屋后发生与甲方保证不相一致的情况，由甲方承担相应违约责任。"));
			document.add(getParagraph("^第九条   乙方应在本合同第七条约定的房屋交接期限内到甲方要求的地点办理验收交接手续。该房屋交付的标志为      " + "  *" + scvo.getN9_1()
					+ "     。该房屋交付之日起，该房屋的风险责任由 甲方转移给乙方。"));
			document.add(getParagraph("该房屋为        " + "  *" + scvo.getN9_2() + "    用房，甲方应向乙方提供《      " + "  *" + scvo.getN9_3() + "     质量保证书》和《     "
					+ "  *" + scvo.getN9_4() + "    使用说明书》《前期物业管理服务合同》／《       " + "  *" + scvo.getN9_5() + "      》。乙方要求提供房屋实测面积的有关资料，甲方应如实提供。"));
			document.add(getParagraph("甲方不出示和不提供前款约定的材料，乙方有权拒绝接收该房屋，由此而产生的延期交房责任由甲方承担。"));
			document.add(getParagraph("^第十条   甲方交付的该房屋装修、设备标准达不到本合同附件三约定的标准，乙方在办理该房屋交接手续时有权要求甲方按装修、设备差价      " + "  *" + scvo.getN10_1() + "   倍补偿。"));
			document.add(getParagraph("双方对标准的认定产生争议时，委托本市有资质的建设工程质量检测机构检测并以该机构出具的书面鉴定意见为处理争议的依据。"));
			document.add(getParagraph("^第十一条   如乙方已付清全部房价款，除甲方原因外，乙方未按约定期限办理交付该房屋手续的，甲方应当发出书面催告书一次。乙方未按催告书规定的日期办理该房屋的验收交接手续的，则自催告书规定的验收交接日之第二天起，风险责任转移由乙方承担"));
			document
					.add(getParagraph("如本合同约定的付款时间（附件一）的最后付款日期迟于本合同第七条约定的房屋交付日期的（即乙方未支付完全部房价款时），除甲方原因外，乙方未按约定期限办理交付该房屋手续的，甲方应当发出书面催告书一次。乙方未按催告书规定的日期办理该房屋的验收交接手续的，甲方有权单方面解除本合同。 "));
			document.add(getParagraph("^第十二条   甲方行使本合同条款中约定的单方面解除本合同权利时，应书面通知乙方。在书面通知发出之日起      " + "  *" + scvo.getN12_1()
					+ "     日内将乙方已支付的房价款扣除乙方应支付的赔偿金额，剩余房价款退还乙方。赔偿金额以总房价款的      " + "  *" + scvo.getN12_2() + "    ％计算。"));
			document.add(getParagraph("^第十三条   除不可抗力外，甲方如未在本合同第七条约定的期内将该房屋交付乙方使用，应当向乙方支付违约金，违约金按乙方已支付的房价款日万分之      " + "  *" + scvo.getN13_1()
					+ "   计算，违约金自本合同第七条约定的最后交付期限之第二天起算至实际交付之日止。逾期超过       " + "  *" + scvo.getN13_2() + "   天，乙方有权选择下列第      " + "  *"
					+ getRes(scvo.getN13_3(), 2) + "    种方案追究甲方责任："));
			document.add(getParagraph("一、 乙方有权单方面解除本合同。"));
			document.add(getParagraph("二、     " + "  *" + sellN13_4 + " "));
			document.add(getParagraph("^第十四条   自该房屋正式交付之时起，甲方对该房屋负责保修，并从房地产权利转移之日起继续保修二年／      " + "  *" + scvo.getN14_1()
					+ "     年。保修范围由甲、乙双方参照国务院发布的《建设工程质量管理条例》规定在本合同附件五中约定。"));
			document.add(getParagraph("^第十五条   该房屋交付后，乙方认为主体结构不合格的，可以委托本市有资质的建设工程质量检测机构检测。经核验，确属主体结构质量不合格的，乙方有权单方解除本合同。"));
			document.add(getParagraph("^第十六条   甲方交付的该房屋有其他工程质量问题的，乙方在合同约定的保修期内有权要求甲方除免费修复外，还须按修复费的      " + "  *" + scvo.getN16_1() + "  倍给予补偿。"));
			document.add(getParagraph("双方商定对该房屋其他工程质量问题有争议的，委托本市有资质的建设工程质量检测机构检测并以该机构出具的书面鉴定意见为处理争议的依据。"));
			document.add(getParagraph("^第十七条    该房屋买卖所发生的税费按有关规定由甲、乙双方各自承担。"));
			document.add(getParagraph("^第十八条    乙方同意，因该物业管理区域尚未成立业主委员会，自该房屋交付之日起，即应将该房屋交甲方在《前期物业管理服务合同》中委托的房屋管理企业统一进行管理、并遵守房屋使用公约。"));
			document.add(getParagraph("甲方委托的房屋管理企业承担该房屋的前期房屋管理，管理的期限至业主委员会成立后选聘的新的房屋管理企业接手管理之日。"));
			document
.add(getParagraph("^第十九条    甲、乙双方商定，    " + "  *" + scvo.getN19_1() + "     年     " + "  *" + scvo.getN19_2() + "    月      " + "  *"
					+ scvo.getN19_3() + "    日前，由      " + "  *" + dicBo.getDictName("ct_511", String.valueOf(scvo.getRegType())) + "    向      " + "  *"
					+ dicBo.getDictName("ct_510", String.valueOf(scvo.getN19_4()))
 + "   办理价格申报及过户申请手续，申领该房屋房地产权证。"));
			document.add(getParagraph("因甲方原因，乙方无法在     " + "  *" + scvo.getN19_5() + "  年     " + "  *" + scvo.getN19_6() + "  月      " + "  *"
					+ scvo.getN19_7() + "    日前取得房地产权证，甲方应承担违约责任，违约金为总房价款的      " + "  *" + scvo.getN19_8() + "    ％；    " + "  *" + scvo.getN19_9()
					+ "    年     " + "  *" + scvo.getN19_10() + "  月     " + "  *" + scvo.getN19_11() + "     日之日起的       " + "  *" + scvo.getN19_12()
					+ "    日内，乙方仍无法取得房地产权证，则乙方有权单方面解除合同。"));
			document.add(getParagraph("^第二十条     乙方行使本合同条款中约定的单方面解除本合同权利时，应书面通知甲方，甲方应当在收到乙方的书面通知之日起     " + "  *" + scvo.getN20_1()
					+ "  天内将乙方已支付的房价款（包括利息 ，利息按中国人民银行公布的同期存款利率计算，下同）全部退还乙方，并承担赔偿责 任，赔偿金额以总房价款的     " + "  *" + scvo.getN20_2() + "  ％计算，在退还房价款时一并支付给乙方。 "));
			document.add(getParagraph("前款及本合同其他条款所称已支付的房价款是包括乙方直接的和通过贷款方式支付的房价款。"));
			document.add(getParagraph("^第二十一条    按本合同约定，甲、乙双方单方解除本合同的，在单方解除合同以前，对方已按合同约定支付违约金的，支付的违约金金额应在按本合同约定的赔偿金额中扣除。"));
			document.add(getParagraph("^第二十二条    甲方或乙方对相对方行使单方面解除本合同有异议的，应在接到对方有关单方面解除本合同的书面通知之日起     " + "  *" + scvo.getN22_1()
					+ "   天内，向按第二十七条选定的解决争议机关确认解除合同的效力。"));
			document.add(getParagraph("^第二十三条    甲方出售的该房屋仅作  " + "  *" + scvo.getN23_1()
					+ "  使用，乙方在使用期间不得擅自改变该房屋结构和用途。除本合同附件另有约定外，乙方在使用期间有权享用与该房屋有关联的公共通道和设施，同时应自觉遵守该房屋所在地的有关法律、法规和尊重社会公德、维护公共设施和公共利益。"));
			document.add(getParagraph("^第二十四条    乙方购买的房屋及其相应占有的土地使用权不可分离。自该房屋的房地产权利转移之时起，甲方与       " + "  *" + scvo.getN24_1() + "  签订的土地使用权      " + "  *"
					+ dicBo.getDictName("ct_507", String.valueOf(scvo.getLandpactType())) + "   合同中约定的权利、义务和责任依法转移给乙方。"));
			document.add(getParagraph("^第二十五条    本合同的未尽事宜及本合同在履行过程中需变更的事宜，双方将通过订立补充条款、补充协议或变更协议进行约定。"));
			document.add(getParagraph("本合同的补充条款、补充协议、变更协议均为本合同不可分割的一部分。本合同补充条款、补充协议、变更协议与正文条款不相一致的，以补充条款、补充协议、变更协议为准。"));
			document.add(getParagraph("^第二十六条    本合同一方按照本合同约定向另一方送达的任何文件、回复及其它任何联系，必须用书面形式，且采用挂号邮寄或直接送达的方式，送达本合同所列另一方的地址或另一方以本条所述方式通知更改后的地址。"));
			document.add(getParagraph("^第二十七条    本合同适用中华人民共和国法律。甲、乙双方在履行本合同过程中发生争议，应协商解决。协商不能解决的，选定下列第     " + "  *" + getRes(scvo.getResolveType(), 2)
					+ "   种方式解决："));
			document.add(getParagraph("一 、向      " + "  *" + scvo.getN27_1() + "   仲裁委员会申请仲裁； "));
			document.add(getParagraph("二 、依法向人民法院起诉。"));
			document.add(getParagraph("^第二十八条    本合同自双方签署／     " + "  *" + scvo.getN28_1() + "   公证处公证之日起生效。"));
			document.add(getParagraph("^第二十九条     本合同壹式    " + "  *" + scvo.getN29_1() + "   份，均具有同等效力。其中甲、乙双方各执    " + "  *" + scvo.getN29_2() + "   份，     "
					+ "  *" + scvo.getN29_3() + "    、       " + "  *" + scvo.getN29_4() + "  、      " + "  *" + scvo.getN29_5() + "   各执壹份。"));

			document.newPage();

			p = new Paragraph();
			Chunk c = new Chunk("附  件  一", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			document.add(getParagraph("付款时间和付款方式", setChinese(FS14), 0.0f, 20.0f, 0.0f, Element.ALIGN_CENTER));
			p = new Paragraph("-------------------------------------------------------");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);

			//查询是否新版附件一
			List<MetaFilter> params10 = new ArrayList<MetaFilter>();
			params10.add(new MetaFilter("contractID", "=", contractID));
			List<PresellAttach2MoneyRecordVO> ptmr = this.search(PresellAttach2MoneyRecordVO.class, params10);

			List<MetaFilter> paramsa = new ArrayList<MetaFilter>();
			paramsa.add(new MetaFilter("contractID", "=", contractID));
			List<PresellAttach2MoneyVO> a1mVOList = this.search(PresellAttach2MoneyVO.class, paramsa);
			String str1 = "不贷款 ";
			if(ptmr != null && ptmr.size() > 0){
				if(a1mVOList != null && a1mVOList.size() > 0){
					String contentText = "";
					for(PresellAttach2MoneyVO a1mVO : a1mVOList){
						if(a1mVO.getContentText() != null && !"".equals(a1mVO.getContentText())){
							contentText = a1mVO.getContentText();
						}
						if(a1mVO.getState() == 1){
							document.add(getParagraph("付款方式：    " + " *" + a1mVO.getPaymentMode() + "    贷款方式：" + " *" + str1 + " 。", setChinese(FS10), 0.0f,
									20.0f, 0.0f, Element.ALIGN_LEFT));
							document.add(getParagraph("（一）基本条款：1、乙方按以下方式向甲方支付本合同第三条约定的合同总价款     " + " *" + a1mVO.getTotalMoney() + "  元。", setChinese(FS10),
									0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							document.add(getParagraph(" 乙方向甲方于   " + substringDate(String.valueOf(a1mVO.getFkDate()), 1) + " 年 "
									+ substringDate(String.valueOf(a1mVO.getFkDate()), 2) + " 月 " + substringDate(String.valueOf(a1mVO.getFkDate()), 3)
									+ " 日前一次性支付总价款 " + " *" + a1mVO.getMoney() + " 元（含定金    " + "  *" + a1mVO.getDeposit() + " 元），存入本合同指定的账户。 ",
									setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
						}
						if(a1mVO.getState() == 2 && a1mVO.getStateSign() == 0){
							document.add(getParagraph("付款方式：    " + "  *" + a1mVO.getPaymentMode() + "    贷款方式：    " + " *" + a1mVO.getBorrowMode() + " 。",
									setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							document.add(getParagraph("（一）基本条款：1、乙方按以下方式向甲方支付本合同第三条约定的合同总价款     " + " *" + a1mVO.getTotalMoney() + "  元。", setChinese(FS10),
									0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							document.add(getParagraph("首付款：    " + "  *" + a1mVO.getMoney() + " 元（含定金    " + "  *" + a1mVO.getDeposit() + " 元），乙方应与 "
									+ substringDate(String.valueOf(a1mVO.getFkDate()), 1) + " 年 " + substringDate(String.valueOf(a1mVO.getFkDate()), 2) + " 月 "
									+ substringDate(String.valueOf(a1mVO.getFkDate()), 3) + " 日前存入本合同指定的账户。", setChinese(FS10), 0.0f, 20.0f, 0.0f,
									Element.ALIGN_LEFT));
							List<MetaFilter> params1 = new ArrayList<MetaFilter>();

							params1.add(new MetaFilter("contractID", "=", contractID));
							params1.add(new MetaFilter("state", "=", 2));
							params1.add(new MetaFilter("stateSign", "!=", 0));
							List<PresellAttach2MoneyVO> list3 = this.search(PresellAttach2MoneyVO.class, params1);
							//返回只有一条记录
							if(list3.size() == 1){
								document.add(getParagraph("剩余房价款:    " + " *" + list3.get(0).getMoney() + "  元，乙方应与  "
										+ substringDate(String.valueOf(list3.get(0).getFkDate()), 1) + " 年 "
										+ substringDate(String.valueOf(list3.get(0).getFkDate()), 2) + " 月 "
										+ substringDate(String.valueOf(list3.get(0).getFkDate()), 3) + " 日前存入本合同指定的账户。", setChinese(FS10), 0.0f, 20.0f, 0.0f,
										Element.ALIGN_LEFT));
							}else{
								for(int i = 0; i < list3.size(); i++){
									document.add(getParagraph("首付款" + " *" + (i + 1) + " 期：  " + " *" + list3.get(i).getMoney() + "  元，乙方应与  "
											+ substringDate(String.valueOf(list3.get(i).getFkDate()), 1) + " 年 "
											+ substringDate(String.valueOf(list3.get(i).getFkDate()), 2) + " 月 "
											+ substringDate(String.valueOf(list3.get(i).getFkDate()), 3) + " 日前存入本合同指定的账户。", setChinese(FS10), 0.0f, 20.0f,
											0.0f, Element.ALIGN_LEFT));

								}
							}
						}
						if(a1mVO.getState() == 3 && a1mVO.getStateSign() == 0){
							document.add(getParagraph("付款方式：    " + "  *" + a1mVO.getPaymentMode() + "    贷款方式：    " + " *" + a1mVO.getBorrowMode() + " 。",
									setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							document.add(getParagraph("（一）基本条款：1、乙方按以下方式向甲方支付本合同第三条约定的合同总价款     " + " *" + a1mVO.getTotalMoney() + "  元。", setChinese(FS10),
									0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							document.add(getParagraph("首付款：    " + "  *" + a1mVO.getMoney() + " 元（含定金    " + " *" + a1mVO.getDeposit() + " 元），乙方应与 "
									+ substringDate(String.valueOf(a1mVO.getFkDate()), 1) + " 年 " + substringDate(String.valueOf(a1mVO.getFkDate()), 2) + " 月 "
									+ substringDate(String.valueOf(a1mVO.getFkDate()), 3) + " 日前存入本合同指定的账户。", setChinese(FS10), 0.0f, 20.0f, 0.0f,
									Element.ALIGN_LEFT));
							List<MetaFilter> params2 = new ArrayList<MetaFilter>();

							params2.add(new MetaFilter("contractID", "=", contractID));
							params2.add(new MetaFilter("state", "=", 3));
							params2.add(new MetaFilter("stateSign", "!=", 0));
							List<PresellAttach2MoneyVO> list3 = this.search(PresellAttach2MoneyVO.class, params2);
							//返回只有一条记录
							if(list3.size() == 1){
								document.add(getParagraph("剩余房价款:    " + "  *" + list3.get(0).getMoney() + "  元，乙方应与  "
										+ substringDate(String.valueOf(list3.get(0).getFkDate()), 1) + " 年 "
										+ substringDate(String.valueOf(list3.get(0).getFkDate()), 2) + " 月 "
										+ substringDate(String.valueOf(list3.get(0).getFkDate()), 3) + " 日前存入本合同指定的账户。", setChinese(FS10), 0.0f, 20.0f, 0.0f,
										Element.ALIGN_LEFT));
							}else{
								for(int i = 0; i < list3.size(); i++){
									document.add(getParagraph("首付款" + " *" + (i + 1) + " 期：  " + " *" + list3.get(i).getMoney() + "  元，乙方应与  "
											+ substringDate(String.valueOf(list3.get(i).getFkDate()), 1) + " 年 "
											+ substringDate(String.valueOf(list3.get(i).getFkDate()), 2) + " 月 "
											+ substringDate(String.valueOf(list3.get(i).getFkDate()), 3) + " 日前存入本合同指定的账户。", setChinese(FS10), 0.0f, 20.0f,
											0.0f, Element.ALIGN_LEFT));
								}
							}
						}
						if(a1mVO.getState() == 4){
							document.add(getParagraph("贷款：    " + "  *" + a1mVO.getMoney() + " 元，乙方以 " + " *" + a1mVO.getBorrowMode() + " 方式应于 "
									+ substringDate(String.valueOf(a1mVO.getFkDate()), 1) + " 年 " + substringDate(String.valueOf(a1mVO.getFkDate()), 2) + " 月 "
									+ substringDate(String.valueOf(a1mVO.getFkDate()), 3) + " 日前将办理按揭贷款所需文件全部提交 " + " *" + a1mVO.getBankName() + " ，放贷机构于 "
									+ substringDate(String.valueOf(a1mVO.getDkDate()), 1) + " 年 " + substringDate(String.valueOf(a1mVO.getDkDate()), 2) + " 月 "
									+ substringDate(String.valueOf(a1mVO.getDkDate()), 3) + " 日前将贷款存入本合同指定的账户。 ", setChinese(FS10), 0.0f, 20.0f, 0.0f,
									Element.ALIGN_LEFT));
						}
					}
					document.add(getParagraph("（二）双方在遵守以上约定的基础上，作出以下补充条款：    " + " *" + contentText, setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
				}
			}else{
				//老合同判断
				PresellDepositVO attach3vo = (PresellDepositVO) cqDao.find(PresellDepositVO.class, contractID);
				if(attach3vo != null){
					List<MetaFilter> params3 = new ArrayList<MetaFilter>();
					params3.add(new MetaFilter("contractID", "=", contractID));
					List<Attach1VO> attach4vo = this.search(Attach1VO.class, params3);
					if(attach4vo != null && attach4vo.size() > 0){
						for(Attach1VO att4VO : attach4vo){
							String at1Content = att4VO.getContent() == null ? "" : new String(att4VO.getContent());
							if(att4VO.getPaymentType() == 1){
								document.add(getParagraph("按条件付款   ", setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
								document.add(getParagraph("条款如下按条件付款：    " + "  *" + at1Content, setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							}
							if(att4VO.getPaymentType() == 0){
								String paymentMode = this.getDictName("CT_502_1", String.valueOf(att4VO.getPaymentMode()));
								String borrowMode = this.getDictName("CT_503_1", String.valueOf(att4VO.getBorrowMode()));
								document.add(getParagraph("按时间付款   ", setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
								document.add(getParagraph("付款方式： " + " *" + paymentMode + "  贷款方式： " + " *" + borrowMode, setChinese(FS10), 0.0f, 20.0f, 0.0f,
										Element.ALIGN_LEFT));
								document.add(getParagraph("1、乙方于  " + substringDate(String.valueOf(attach3vo.getPayDate()), 1) + " 年 "
										+ substringDate(String.valueOf(attach3vo.getPayDate()), 2) + " 月 "
										+ substringDate(String.valueOf(attach3vo.getPayDate()), 3) + " 日前付定金计人民币" + " *" + attach3vo.getDeposit() + " 元；（大写） "
										+ " *" + attach3vo.getDepositCn() + " 。", setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
								List<MetaFilter> params4 = new ArrayList<MetaFilter>();
								params4.add(new MetaFilter("contractID", "=", contractID));
								List<MetaOrder> orders = new ArrayList<MetaOrder>();
								orders.add(new MetaOrder("serial", "asc"));
								List<Attach1MoneyVO> atmyvo = pdfDAO.search(Attach1MoneyVO.class, params4, orders, null);
								if(attach4vo != null && attach4vo.size() > 0){
									for(Attach1MoneyVO Atmyvo : atmyvo){
										if(Atmyvo.getSerial() == 1){
											document.add(getParagraph("2、乙方于   " + substringDate(String.valueOf(Atmyvo.getPaymentDate()), 1) + " 年 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 2) + " 月 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 3) + " 日与甲方签约，并付首付房款计人民币 " + " *"
													+ Atmyvo.getMoney() + " 元；（大写） " + " *" + Atmyvo.getMoneyCn() + " 。", setChinese(FS10), 0.0f, 20.0f, 0.0f,
													Element.ALIGN_LEFT));

										}else{
											document.add(getParagraph("2、乙方于   " + substringDate(String.valueOf(Atmyvo.getPaymentDate()), 1) + " 年 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 2) + " 月 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 3) + " 日前应支付房款计人民币" + " *" + Atmyvo.getMoney()
													+ " 元；（大写） " + " *" + Atmyvo.getMoneyCn() + " 。", setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
										}
									}
								}
								document.add(getParagraph("备注：    " + "  *" + at1Content, setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							}
						}
					}
				}else{
					List<MetaFilter> params5 = new ArrayList<MetaFilter>();
					params5.add(new MetaFilter("contractID", "=", contractID));
					List<Attach1VO> attach4vo = this.search(Attach1VO.class, params5);
					if(attach4vo != null && attach4vo.size() > 0){
						for(Attach1VO att4VO : attach4vo){
							String at1Content = att4VO.getContent() == null ? "" : new String(att4VO.getContent());
							if(att4VO.getPaymentType() == 1){
								document.add(getParagraph("按条件付款   ", setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
								document.add(getParagraph("条款如下按条件付款：    " + "  *" + at1Content, setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							}
							if(att4VO.getPaymentType() == 0){
								document.add(getParagraph("按时间付款   ", setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
								document.add(getParagraph("付款方式： " + " *" + att4VO.getAttribute("paymentMode_dict_name") + "  贷款方式： " + " *"
										+ att4VO.getAttribute("borrowMode_dict_name"), setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
								List<MetaFilter> params6 = new ArrayList<MetaFilter>();
								params6.add(new MetaFilter("contractID", "=", contractID));
								List<MetaOrder> orders = new ArrayList<MetaOrder>();
								orders.add(new MetaOrder("serial", "asc"));
								List<Attach1MoneyVO> atmyvo = pdfDAO.search(Attach1MoneyVO.class, params6, orders, null);
								if(attach4vo != null && attach4vo.size() > 0){
									for(Attach1MoneyVO Atmyvo : atmyvo){
										if(Atmyvo.getSerial() == 1){
											document.add(getParagraph("1、乙方于   " + substringDate(String.valueOf(Atmyvo.getPaymentDate()), 1) + " 年 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 2) + " 月 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 3) + " 日与甲方签约，并付首付房款计人民币 " + " *"
													+ Atmyvo.getMoney() + " 元；（大写） " + " *" + Atmyvo.getMoneyCn() + " 。", setChinese(FS10), 0.0f, 20.0f, 0.0f,
													Element.ALIGN_LEFT));

										}else{
											document.add(getParagraph("  1、乙方于   " + substringDate(String.valueOf(Atmyvo.getPaymentDate()), 1) + " 年 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 2) + " 月 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 3) + " 日前应支付房款计人民币" + " *" + Atmyvo.getMoney()
													+ " 元；（大写） " + " *" + Atmyvo.getMoneyCn() + " 。", setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
										}
									}
								}
								document.add(getParagraph("备注：    " + "  *" + at1Content, setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							}
						}
					}
				}
			}
			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  二", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			document.add(getParagraph("该房屋平面图（标明尺寸和比例）", setChinese(FS14), 0.0f, 20.0f, 0.0f, Element.ALIGN_CENTER));
			p = new Paragraph("-------------------------------------------------------");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			HouseVO hvo = (HouseVO) cqDao.find(HouseVO.class, cdvo.getHouseID());
			if(hvo.getSell_Plan_ID() != null && !"".equals(hvo.getSell_Plan_ID())){
				HousePlanVO hpvo = (HousePlanVO) cqDao.find(HousePlanVO.class, Long.parseLong(hvo.getSell_Plan_ID()));
				if(hpvo != null && hpvo.getPlan_Pdf() != null){
					File file1 = new File(tempPdfPath); //创建临时存放pdf的文件夹
					if(!file1.exists()){
						file1.mkdirs();
					}
					File file2 = new File(tempImgPath); //创建临时存放图片的文件夹
					if(!file2.exists()){
						file2.mkdirs();
					}
					String demo = hvo.getSell_Plan_ID() + "-" + String.valueOf(System.currentTimeMillis());
					getFile(hpvo.getPlan_Pdf(), tempPdfPath, demo + ".pdf");
					boolean flag = pdf2Pic(tempPdfPath + demo + ".pdf", tempImgPath + demo);
					if(flag == true){
						Image image = Image.getInstance(tempImgPath + demo + ".png");
						image.scaleAbsolute(523f, 500f);
						image.setAlignment(Element.ALIGN_CENTER);
						document.add(image);

						File pdfFile = new File(tempPdfPath + demo + ".pdf");
						//pdfFile.deleteOnExit();

						File imageFile = new File(tempImgPath + demo + ".png");
						//imageFile.deleteOnExit();

						deleteFile(pdfFile);
						deleteFile(imageFile);
					}
				}else{
					//2018-12-12 added by gcf 如果HousePlan表没有，直接从接口获取平面图。
					File file1 = new File(tempPdfPath); //创建临时存放pdf的文件夹
					if(!file1.exists()){
						file1.mkdirs();
					}
					File file2 = new File(tempImgPath); //创建临时存放图片的文件夹
					if(!file2.exists()){
						file2.mkdirs();
					}
					String demo = hvo.getSell_Plan_ID() + "-" + String.valueOf(System.currentTimeMillis());
					getFile(hvo.getSell_Plan_ID(), demo);
					boolean flag = pdf2Pic(tempPdfPath + demo + ".pdf", tempImgPath + demo);
					if(flag == true){
						Image image = Image.getInstance(tempImgPath + demo + ".png");
						image.scaleAbsolute(523f, 500f);
						image.setAlignment(Element.ALIGN_CENTER);
						document.add(image);

						File pdfFile = new File(tempPdfPath + demo + ".pdf");
						//pdfFile.deleteOnExit();

						File imageFile = new File(tempImgPath + demo + ".png");
						//imageFile.deleteOnExit();
						deleteFile(pdfFile);
						deleteFile(imageFile);
					}
				}
			}

			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  三", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			document.add(getParagraph("该房屋建筑结构、装修及设备标准 ", setChinese(FS14), 0.0f, 20.0f, 0.0f, Element.ALIGN_CENTER));
			p = new Paragraph("-------------------------------------------------------");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			document.add(new Paragraph(sellFJ3, setChinese(FS14)));

			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  四", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			document.add(getParagraph("该房屋权属情况（产权、抵押、租赁） ", setChinese(FS14), 0.0f, 20.0f, 0.0f, Element.ALIGN_CENTER));
			p = new Paragraph("-------------------------------------------------------");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);

			p = new Paragraph();
			p.setSpacingBefore(SPB28 * 3);
			document.add(p);

			Attach4VO attach4vo = (Attach4VO) cqDao.find(Attach4VO.class, contractID);
			if(attach4vo != null){
				PdfPTable table = new PdfPTable(6);
				table.setTotalWidth(523);
				int[] width = { 10, 22, 12, 22, 12, 22 };
				table.setWidths(width);
				table.setLockedWidth(true);
				PdfPCell cell = new PdfPCell(new Paragraph("房 屋 土 地 状 况", setChinese(FS10, Font.BOLD)));
				cell.setColspan(6);
				cell.setHorizontalAlignment(1);
				cell.setUseAscender(true);
				cell.setUseDescender(true);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell cell1 = new PdfPCell(new Paragraph("地号", setChinese(FS10)));
				PdfPCell cell2 = new PdfPCell(new Paragraph(attach4vo.getLotCode(), setChinese(FS10)));
				PdfPCell cell3 = new PdfPCell(new Paragraph("使用期限", setChinese(FS10)));
				PdfPCell cell4 = new PdfPCell(new Paragraph(attach4vo.getAllDate(), setChinese(FS10)));
				PdfPCell cell5 = new PdfPCell(new Paragraph("批准用途", setChinese(FS10)));
				PdfPCell cell6 = new PdfPCell(new Paragraph(attach4vo.getPermitUsage(), setChinese(FS10)));
				PdfPCell cell7 = new PdfPCell(new Paragraph("总面积", setChinese(FS10)));
				PdfPCell cell8 = new PdfPCell(new Paragraph(attach4vo.getLandArea(), setChinese(FS10)));
				PdfPCell cell9 = new PdfPCell(new Paragraph("共用面积", setChinese(FS10)));
				PdfPCell cell10 = new PdfPCell(new Paragraph(attach4vo.getBlockArea(), setChinese(FS10)));
				PdfPCell cell11 = new PdfPCell(new Paragraph("使用权来源", setChinese(FS10)));
				PdfPCell cell12 = new PdfPCell(new Paragraph(attach4vo.getLandSource(), setChinese(FS10)));
				table.addCell(cell);
				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
				table.addCell(cell4);
				table.addCell(cell5);
				table.addCell(cell6);
				table.addCell(cell7);
				table.addCell(cell8);
				table.addCell(cell9);
				table.addCell(cell10);
				table.addCell(cell11);
				table.addCell(cell12);
				document.add(table);
			}

			p = new Paragraph();
			p.setSpacingBefore(SPB20 * 3);
			document.add(p);

			List<MetaFilter> params1 = new ArrayList<MetaFilter>();
			params1.add(new MetaFilter("contractID", "=", contractID));
			List<Attach4RealVO> arlist = cqDao.search(Attach4RealVO.class, params1, null, null);
			//Attach4RealVO attach4RealVO = (Attach4RealVO) cqDao.find(Attach4RealVO.class, contractID);
			if(arlist != null && arlist.size() > 0){
				for(Attach4RealVO arvo : arlist){
					PdfPTable table = new PdfPTable(4);
					table.setTotalWidth(523);
					int[] width1 = { 10, 40, 15, 35 };
					table.setWidths(width1);
					table.setLockedWidth(true);
					PdfPCell cell = new PdfPCell(new Paragraph("产 权 信 息", setChinese(FS10, Font.BOLD)));
					cell.setColspan(6);
					cell.setHorizontalAlignment(1);
					cell.setUseAscender(true);
					cell.setUseDescender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

					PdfPCell cell1 = new PdfPCell(new Paragraph("权利人", setChinese(FS10)));
					PdfPCell cell2 = new PdfPCell(new Paragraph(arvo.getRealName(), setChinese(FS10)));
					PdfPCell cell3 = new PdfPCell(new Paragraph("权证或证明号", setChinese(FS10)));
					PdfPCell cell4 = new PdfPCell(new Paragraph(arvo.getRealNo(), setChinese(FS10)));
					PdfPCell cell5 = new PdfPCell(new Paragraph("共有人与共有情况", setChinese(FS10)));
					PdfPCell cell6 = new PdfPCell(new Paragraph(arvo.getAllRealName(), setChinese(FS10)));
					cell6.setColspan(3);
					PdfPCell cell7 = new PdfPCell(new Paragraph("房屋坐落", setChinese(FS10)));
					PdfPCell cell8 = new PdfPCell(new Paragraph(arvo.getLocation(), setChinese(FS10)));
					cell8.setColspan(3);
					PdfPCell cell9 = new PdfPCell(new Paragraph("受理日期", setChinese(FS10)));
					PdfPCell cell10 = new PdfPCell(new Paragraph(arvo.getStartDate(), setChinese(FS10)));
					PdfPCell cell11 = new PdfPCell(new Paragraph("核准日期", setChinese(FS10)));
					PdfPCell cell12 = new PdfPCell(new Paragraph(arvo.getPassDate(), setChinese(FS10)));
					PdfPCell cell13 = new PdfPCell(new Paragraph(" 备  \n 注", setChinese(FS10)));
					PdfPCell cell14 = new PdfPCell(new Paragraph(arvo.getRemark(), setChinese(FS10)));
					cell14.setColspan(3);
					table.addCell(cell);
					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);
					table.addCell(cell6);
					table.addCell(cell7);
					table.addCell(cell8);
					table.addCell(cell9);
					table.addCell(cell10);
					table.addCell(cell11);
					table.addCell(cell12);
					table.addCell(cell13);
					table.addCell(cell14);
					document.add(table);
				}
			}

			List<MetaFilter> params2 = new ArrayList<MetaFilter>();
			params2.add(new MetaFilter("contractID", "=", contractID));
			List<Attach4OtherVO> aolist = cqDao.search(Attach4OtherVO.class, params2, null, null);
			//Attach4OtherVO attach4OtherVO = (Attach4OtherVO) cqDao.find(Attach4OtherVO.class, contractID);
			if(aolist != null && aolist.size() > 0){
				for(Attach4OtherVO aovo : aolist){
					PdfPTable table = new PdfPTable(4);
					table.setTotalWidth(523);
					int[] width1 = { 10, 40, 15, 35 };
					table.setWidths(width1);
					table.setLockedWidth(true);
					PdfPCell cell = new PdfPCell(new Paragraph("他 项 信 息", setChinese(FS10, Font.BOLD)));
					cell.setColspan(6);
					cell.setHorizontalAlignment(1);
					cell.setUseAscender(true);
					cell.setUseDescender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

					PdfPCell cell1 = new PdfPCell(new Paragraph("权利种类", setChinese(FS10)));
					PdfPCell cell2 = new PdfPCell(new Paragraph(aovo.getOtherRightType(), setChinese(FS10)));
					PdfPCell cell3 = new PdfPCell(new Paragraph("债权金额", setChinese(FS10)));
					PdfPCell cell4 = new PdfPCell(new Paragraph(aovo.getDebtAccount(), setChinese(FS10)));
					PdfPCell cell5 = new PdfPCell(new Paragraph("房产坐落", setChinese(FS10)));
					PdfPCell cell6 = new PdfPCell(new Paragraph(aovo.getOtherRightPart(), setChinese(FS10)));
					PdfPCell cell7 = new PdfPCell(new Paragraph("权证或证明号", setChinese(FS10)));
					PdfPCell cell8 = new PdfPCell(new Paragraph(aovo.getOtherRightNo(), setChinese(FS10)));
					PdfPCell cell9 = new PdfPCell(new Paragraph("他项权利人", setChinese(FS10)));
					PdfPCell cell10 = new PdfPCell(new Paragraph(aovo.getOwnerName(), setChinese(FS10)));
					cell10.setColspan(3);
					PdfPCell cell11 = new PdfPCell(new Paragraph("受理日期", setChinese(FS10)));
					PdfPCell cell12 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(aovo.getStartDate()), setChinese(FS10)));
					PdfPCell cell13 = new PdfPCell(new Paragraph("核准日期", setChinese(FS10)));
					PdfPCell cell14 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(aovo.getPassDate()), setChinese(FS10)));
					PdfPCell cell15 = new PdfPCell(new Paragraph(" 备  \n 注", setChinese(FS10)));
					PdfPCell cell16 = new PdfPCell(new Paragraph(aovo.getRemark(), setChinese(FS10)));
					cell16.setColspan(3);
					table.addCell(cell);
					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);
					table.addCell(cell6);
					table.addCell(cell7);
					table.addCell(cell8);
					table.addCell(cell9);
					table.addCell(cell10);
					table.addCell(cell11);
					table.addCell(cell12);
					table.addCell(cell13);
					table.addCell(cell14);
					table.addCell(cell15);
					table.addCell(cell16);
					document.add(table);
				}
			}

			List<MetaFilter> params3 = new ArrayList<MetaFilter>();
			params3.add(new MetaFilter("contractID", "=", contractID));
			List<Attach4LimitVO> allist = cqDao.search(Attach4LimitVO.class, params3, null, null);
			//Attach4LimitVO attach4LimitVO = (Attach4LimitVO) cqDao.find(Attach4LimitVO.class, contractID);
			if(allist != null && allist.size() > 0){
				for(Attach4LimitVO alvo : allist){
					PdfPTable table = new PdfPTable(4);
					table.setTotalWidth(523);
					int[] width1 = { 10, 40, 15, 35 };
					table.setWidths(width1);
					table.setLockedWidth(true);
					PdfPCell cell = new PdfPCell(new Paragraph("限 制 信 息", setChinese(FS10, Font.BOLD)));
					cell.setColspan(6);
					cell.setHorizontalAlignment(1);
					cell.setUseAscender(true);
					cell.setUseDescender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

					PdfPCell cell1 = new PdfPCell(new Paragraph("被限制人", setChinese(FS10)));
					PdfPCell cell2 = new PdfPCell(new Paragraph(alvo.getNameb(), setChinese(FS10)));
					PdfPCell cell3 = new PdfPCell(new Paragraph("限制人", setChinese(FS10)));
					PdfPCell cell4 = new PdfPCell(new Paragraph(alvo.getNames(), setChinese(FS10)));
					PdfPCell cell5 = new PdfPCell(new Paragraph("房屋座落", setChinese(FS10)));
					PdfPCell cell6 = new PdfPCell(new Paragraph(alvo.getLimitPart(), setChinese(FS10)));
					PdfPCell cell7 = new PdfPCell(new Paragraph("权证或证明号", setChinese(FS10)));
					PdfPCell cell8 = new PdfPCell(new Paragraph(alvo.getLimitNo(), setChinese(FS10)));
					PdfPCell cell9 = new PdfPCell(new Paragraph("起始日期", setChinese(FS10)));
					PdfPCell cell10 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(alvo.getPlanStartDate()), setChinese(FS10)));
					PdfPCell cell11 = new PdfPCell(new Paragraph("结束日期", setChinese(FS10)));
					PdfPCell cell12 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(alvo.getPlanEndDate()), setChinese(FS10)));
					PdfPCell cell13 = new PdfPCell(new Paragraph("受理日期", setChinese(FS10)));
					PdfPCell cell14 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(alvo.getStartDate()), setChinese(FS10)));
					PdfPCell cell15 = new PdfPCell(new Paragraph("核准日期", setChinese(FS10)));
					PdfPCell cell16 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(alvo.getPassDate()), setChinese(FS10)));
					PdfPCell cell17 = new PdfPCell(new Paragraph(" 备  \n 注", setChinese(FS10)));
					PdfPCell cell18 = new PdfPCell(new Paragraph(alvo.getRemark(), setChinese(FS10)));
					table.addCell(cell);
					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);
					table.addCell(cell6);
					table.addCell(cell7);
					table.addCell(cell8);
					table.addCell(cell9);
					table.addCell(cell10);
					table.addCell(cell11);
					table.addCell(cell12);
					table.addCell(cell13);
					table.addCell(cell14);
					table.addCell(cell15);
					table.addCell(cell16);
					table.addCell(cell17);
					table.addCell(cell18);
					document.add(table);
				}
			}

			List<MetaFilter> params4 = new ArrayList<MetaFilter>();
			params4.add(new MetaFilter("contractID", "=", contractID));
			List<Attach4HireVO> ahlist = cqDao.search(Attach4HireVO.class, params4, null, null);
			//Attach4HireVO attach4HireVO = (Attach4HireVO) cqDao.find(Attach4HireVO.class, contractID);
			if(ahlist != null && allist.size() > 0){
				for(Attach4HireVO ahvo : ahlist){
					PdfPTable table = new PdfPTable(4);
					table.setTotalWidth(523);
					int[] width1 = { 10, 40, 15, 35 };
					table.setWidths(width1);
					table.setLockedWidth(true);
					PdfPCell cell = new PdfPCell(new Paragraph("租 赁 信 息", setChinese(FS10, Font.BOLD)));
					cell.setColspan(6);
					cell.setHorizontalAlignment(1);
					cell.setUseAscender(true);
					cell.setUseDescender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

					PdfPCell cell1 = new PdfPCell(new Paragraph("出租人", setChinese(FS10)));
					PdfPCell cell2 = new PdfPCell(new Paragraph(ahvo.getLessor(), setChinese(FS10)));
					PdfPCell cell3 = new PdfPCell(new Paragraph("出租类型", setChinese(FS10)));
					PdfPCell cell4 = new PdfPCell(new Paragraph(ahvo.getLendType(), setChinese(FS10)));
					PdfPCell cell5 = new PdfPCell(new Paragraph("承租人", setChinese(FS10)));
					PdfPCell cell6 = new PdfPCell(new Paragraph(ahvo.getLessee(), setChinese(FS10)));
					PdfPCell cell7 = new PdfPCell(new Paragraph("租赁用途", setChinese(FS10)));
					PdfPCell cell8 = new PdfPCell(new Paragraph(ahvo.getLendusage(), setChinese(FS10)));
					PdfPCell cell9 = new PdfPCell(new Paragraph("房屋座落", setChinese(FS10)));
					PdfPCell cell10 = new PdfPCell(new Paragraph(ahvo.getLimitPart(), setChinese(FS10)));
					PdfPCell cell11 = new PdfPCell(new Paragraph("期限", setChinese(FS10)));
					PdfPCell cell12 = new PdfPCell(new Paragraph(ahvo.getTimeBetween(), setChinese(FS10)));
					PdfPCell cell13 = new PdfPCell(new Paragraph("权证或证明号", setChinese(FS10)));
					PdfPCell cell14 = new PdfPCell(new Paragraph(ahvo.getRightNo(), setChinese(FS10)));
					PdfPCell cell15 = new PdfPCell(new Paragraph("受理日期", setChinese(FS10)));
					PdfPCell cell16 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(ahvo.getStartDate()), setChinese(FS10)));
					PdfPCell cell17 = new PdfPCell(new Paragraph("核准日期", setChinese(FS10)));
					PdfPCell cell18 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(ahvo.getPassDate()), setChinese(FS10)));
					PdfPCell cell19 = new PdfPCell(new Paragraph(" 备  \n 注", setChinese(FS10)));
					PdfPCell cell20 = new PdfPCell(new Paragraph(ahvo.getRemark(), setChinese(FS10)));
					cell20.setColspan(3);
					table.addCell(cell);
					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);
					table.addCell(cell6);
					table.addCell(cell7);
					table.addCell(cell8);
					table.addCell(cell9);
					table.addCell(cell10);
					table.addCell(cell11);
					table.addCell(cell12);
					table.addCell(cell13);
					table.addCell(cell14);
					table.addCell(cell15);
					table.addCell(cell16);
					table.addCell(cell17);
					table.addCell(cell18);
					table.addCell(cell19);
					table.addCell(cell20);
					document.add(table);
				}
			}

			Date date = new Date();//创建一个时间对象，获取到当前的时间
			String str = sdf.format(date);//将当前时间格式化为需要的类型
			p = new Paragraph("打印时间：" + DateUtil.parseDateTime4(cdvo.getConfirmDate()), setChinese(FS14));
			p.setAlignment(Element.ALIGN_RIGHT);
			document.add(p);

			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  五", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			document.add(getParagraph("《房屋使用公约》或有关承诺书 ", setChinese(FS14), 0.0f, 20.0f, 0.0f, Element.ALIGN_CENTER));
			p = new Paragraph("-------------------------------------------------------");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			document.add(new Paragraph(sellFJ5, setChinese(FS14)));

			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  六", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			document.add(getParagraph("该房屋相邻关系及小区布局", setChinese(FS14), 0.0f, 20.0f, 0.0f, Element.ALIGN_CENTER));
			p = new Paragraph("-------------------------------------------------------");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);


			document.newPage();

			XgLimitSaleContractVO limitContractVO = null;
			List<MetaFilter> xgParams = new ArrayList<MetaFilter>();
			xgParams.add(new MetaFilter("contractID", "=", contractID));
			//查找限购的信息返回list
			List<XgLimitSaleContractVO> xgList = pdfDAO.search(XgLimitSaleContractVO.class, xgParams, null, null);
			if(xgList != null && xgList.size() > 0){
				limitContractVO = xgList.get(0);
			}
			if(limitContractVO != null && limitContractVO.getContractID() > 0){
				p = new Paragraph();
				c = new Chunk("附  件  七", setChinese(FS14, Font.BOLD));
				p.add(c);
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingBefore(SPB28);
				document.add(p);
				document.add(getParagraph("购房资格查验证明", setChinese(FS14), 0.0f, 20.0f, 0.0f, Element.ALIGN_CENTER));
				p = new Paragraph("-------------------------------------------------------");
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingBefore(SPB28);
				document.add(p);
				p = new Paragraph("编号：" + limitContractVO.getLimitSaleID());
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingBefore(SPB28);
				document.add(p);

				document.newPage();
			}

			p = new Paragraph();
			p.add(new Chunk("补 充 条 款", setChinese(FS14, Font.BOLD)));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			p = new Paragraph("-------------------------------------------------------");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			document.add(new Paragraph(sellBC, setChinese(FS14)));

			document.newPage();

			PdfPTable table = new PdfPTable(4);
			table.setTotalWidth(523);
			int[] width2 = { 25, 25, 30, 20 };
			table.setWidths(width2);
			table.setLockedWidth(true);

			PdfPCell cell1 = new PdfPCell(new Paragraph("甲方（名称）：", setChinese(FS14)));
			cell1.setMinimumHeight(100);
			cell1.setBorder(0);
			PdfPCell cell2 = new PdfPCell(new Paragraph(svo.getSellerName(), setChinese(FS14)));
			cell2.setMinimumHeight(100);
			cell2.setBorder(0);
			PdfPCell cell3 = new PdfPCell(new Paragraph("乙方（名称或名字）：", setChinese(FS14)));
			cell3.setMinimumHeight(100);
			cell3.setBorder(0);
			PdfPCell cell4 = new PdfPCell(new Paragraph(buyername, setChinese(FS14)));
			cell4.setMinimumHeight(100);
			cell4.setBorder(0);
			PdfPCell cell5 = new PdfPCell(new Paragraph("法定代表人签署：", setChinese(FS14)));
			cell5.setColspan(2);
			cell5.setMinimumHeight(60);
			cell5.setBorder(0);
			PdfPCell cell6 = new PdfPCell(new Paragraph("乙方本人签署： ", setChinese(FS14)));
			cell6.setColspan(2);
			cell6.setMinimumHeight(60);
			cell6.setBorder(0);
			PdfPCell cell7 = new PdfPCell(new Paragraph("法定代表人的\n委托代理人签署：", setChinese(FS14)));
			cell7.setColspan(2);
			cell7.setMinimumHeight(80);
			cell7.setBorder(0);
			PdfPCell cell8 = new PdfPCell(new Paragraph("__________________的委托代理人/\n法定代理人签署：", setChinese(FS14)));
			cell8.setColspan(2);
			cell8.setMinimumHeight(80);
			cell8.setBorder(0);
			PdfPCell cell9 = new PdfPCell(new Paragraph("甲方盖章：", setChinese(FS14)));
			cell9.setColspan(2);
			cell9.setMinimumHeight(60);
			cell9.setBorder(0);
			PdfPCell cell10 = new PdfPCell(new Paragraph("乙方盖章：", setChinese(FS14)));
			cell10.setColspan(2);
			cell10.setMinimumHeight(60);
			cell10.setBorder(0);
			PdfPCell cell11 = new PdfPCell(new Paragraph("日期：     年     月     日 ", setChinese(FS14)));
			cell11.setColspan(2);
			cell11.setMinimumHeight(60);
			cell11.setBorder(0);
			PdfPCell cell12 = new PdfPCell(new Paragraph("日期：     年     月     日 ", setChinese(FS14)));
			cell12.setColspan(2);
			cell12.setMinimumHeight(60);
			cell12.setBorder(0);
			PdfPCell cell13 = new PdfPCell(new Paragraph("签于：", setChinese(FS14)));
			cell13.setColspan(2);
			cell13.setMinimumHeight(60);
			cell13.setBorder(0);
			PdfPCell cell14 = new PdfPCell(new Paragraph("签于：", setChinese(FS14)));
			cell14.setColspan(2);
			cell14.setMinimumHeight(60);
			cell14.setBorder(0);
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			table.addCell(cell6);
			table.addCell(cell7);
			table.addCell(cell8);
			table.addCell(cell9);
			table.addCell(cell10);
			table.addCell(cell11);
			table.addCell(cell12);
			table.addCell(cell13);
			table.addCell(cell14);
			document.add(table);

			//甲方拟签人员信息
			SignerVO signerVO;
			if(cdvo.getSigner() != null && !"".equals(cdvo.getSigner())){
				signerVO = (SignerVO) cqDao.find(SignerVO.class, Long.parseLong(cdvo.getSigner()));
				p = new Paragraph("甲方拟签人员：  " + signerVO.getName() + "      销售员证书号：  " + signerVO.getCardCode(), setChinese(FS14));
				p.setAlignment(Element.ALIGN_LEFT);
				p.setSpacingBefore(SPB28);
				document.add(p);
			}
			//甲方确认人员信息
			SignerVO signerVO1;
			if(cdvo.getConfirmer() != null && !"".equals(cdvo.getConfirmer())){
				signerVO1 = (SignerVO) cqDao.find(SignerVO.class, Long.parseLong(cdvo.getConfirmer()));
				p = new Paragraph("甲方确认人员：  " + signerVO1.getName() + "      销售员证书号：  " + signerVO1.getCardCode(), setChinese(FS14));
				p.setAlignment(Element.ALIGN_LEFT);
				p.setSpacingBefore(SPB28);
				document.add(p);
			}

			//网签合同时间
			String confirmDate = cdvo.getConfirmDate();
			if(confirmDate != null && !"".equals(confirmDate) && !"0".equals(confirmDate)){
				p = new Paragraph("网上合同签订时间：  " + sdf.format(DateUtil.parseDateTime2(confirmDate)), setChinese(FS14));
				p.setAlignment(Element.ALIGN_LEFT);
				p.setSpacingBefore(SPB28);
				document.add(p);
			}

			document.close();

			ContractPdfVO ctv = new ContractPdfVO();
			ctv.setPdfData(baos.toByteArray());
			baos.close();
			return ctv;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cqDao);
			closeDAO(pdfDAO);
		}
	}

	/**
	 * 功能描述：新版预售合同文本
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public ContractPdfVO createNewPresellContractPdf(HttpServletRequest request,long contractID,int userType,int signFlag) throws Exception {
		try{
			openDAO(cqDao);
			openDAO(pdfDAO);

			//查询合同主表信息
			ContractDealVO contractvo = (ContractDealVO) cqDao.find(ContractDealVO.class, contractID);
			//查询预售合同信息
			ContractDetailYsVO cdvo = (ContractDetailYsVO) cqDao.find(ContractDetailYsVO.class, contractID);
			//查询合同甲方信息
			SellerInfoVO svo = new SellerInfoVO();
			List<SellerInfoVO> list1 = cqDao.searchSellerInfo(String.valueOf(contractID));
			if(list1 != null && list1.size() > 0){
				svo = list1.get(0);
			}

			Document document = new Document(PageSize.A4);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//定义输出位置并把文档对象装入输出对象中
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			//添加水印
			if(userType == 0) //内网用户
				writer.setPageEvent(new Watermark("合同查阅"));
			if(userType == 1 && (contractvo.getStatus() == 0 || contractvo.getStatus() == 1)) //外网用户草签或待签合同
				if(signFlag != 1){
					writer.setPageEvent(new Watermark("非正式合同"));
				}else{
					if (contractvo.getStatus() == 1) {
						writer.setPageEvent(new Watermark("电子合同"));
					}
				}
			if(userType == 1 && (contractvo.getStatus() != 0 && contractvo.getStatus() != 1 && contractvo.getStatus() != 2)) //外网其他合同
				writer.setPageEvent(new Watermark(dBo.getDictName("ct_500", String.valueOf(contractvo.getStatus())) + "合同"));

			
			//页眉
			HeaderFooter header = new HeaderFooter(new Phrase(String.valueOf(contractID)
					+ "---------------------------------------------------------------------------------------------"
					+ DateUtil.formatDateTime(DateUtil.parseDateTime2( (DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()))), setChinese(FS10)),
					false);
			header.setBorder(Rectangle.NO_BORDER);
			header.setAlignment(1);
			header.setBorderColor(Color.BLACK);
			document.setHeader(header);

			//页码
			HeaderFooter footer = new HeaderFooter(new Phrase(" - ", setChinese(FS10)), new Phrase(" - ", setChinese(FS10)));
			footer.setAlignment(1);
			footer.setBorderColor(Color.red);
			footer.setBorder(Rectangle.NO_BORDER);
			document.setFooter(footer);

			document.open();

			//封面
			Paragraph p1 = new Paragraph("GF-2014-0171                                                                                         合同编号："
					+ contractID, setChinese(FS14));
			p1.setAlignment(Element.ALIGN_CENTER);
			p1.setSpacingAfter(FS10 * 5); //设置段落前间距
			document.add(p1);

			//合同条形码
			PdfContentByte cd128 = writer.getDirectContent();
			Barcode128 code128 = new Barcode128();
			code128.setCodeType(code128.CODE128);
			code128.setCode(String.valueOf(contractID));
			code128.setBarHeight(30);
			Image image128 = code128.createImageWithBarcode(cd128, null, null);
			image128.setAlignment(2);
			document.add(image128);


			Paragraph sellContract = new Paragraph("商品房买卖合同（预售）", setChinese(FS14 * 2, Font.BOLD));
			sellContract.setAlignment(Element.ALIGN_CENTER);
			sellContract.setSpacingBefore(FS14 * 4); //设置段落前间距
			sellContract.setSpacingAfter(FS10 * 1);
			document.add(sellContract);

			//房屋号条形码
			PdfContentByte cd2 = writer.getDirectContent();
			Barcode128 code2 = new Barcode128();
			code2.setCodeType(Barcode128.CODE128);
			code2.setCode(String.valueOf(contractvo.getHouseID()));
			code2.setBarHeight(30);
			Image image2 = code2.createImageWithBarcode(cd2, null, null);
			image2.setAlignment(1);
			document.add(image2);

			Paragraph p4 = new Paragraph("中华人民共和国住房和城乡建设部 ", setChinese(FS14));
			p4.setAlignment(Element.ALIGN_CENTER);
			p4.setSpacingBefore(FS14 * 11); //设置段落前间距
			document.add(p4);
			Paragraph p5 = new Paragraph("制定 ", setChinese(FS14));
			p5.setSpacingBefore(-10f);
			p5.setSpacingAfter(-10f);
			p5.setAlignment(Element.ALIGN_RIGHT);
			document.add(p5);
			Paragraph p6 = new Paragraph("中华人民共和国国家工商行政管理总局", setChinese(FS14));
			p6.setAlignment(Element.ALIGN_CENTER);
			document.add(p6);

			document.newPage();

			Paragraph p;
			Chunk c;

			//目录
			p = new Paragraph("目      录    ", setChinese(FS10 * 2, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB20);
			p.setSpacingAfter(SPB20);
			document.add(p);

			Paragraph p8 = new Paragraph("         说    明", setChinese(FS14));
			p8.setAlignment(Element.ALIGN_LEFT);
			p8.setSpacingBefore(FS14); //设置段落前间距
			document.add(p8);


			Paragraph p9 = new Paragraph("         专业术语解释", setChinese(FS14));
			p9.setAlignment(Element.ALIGN_LEFT);
			p9.setSpacingBefore(FS14); //设置段落前间距
			document.add(p9);

			Paragraph p10 = new Paragraph("        第一章   合同当事人", setChinese(FS14));
			p10.setAlignment(Element.ALIGN_LEFT);
			p10.setSpacingBefore(FS14); //设置段落前间距
			document.add(p10);

			Paragraph p11 = new Paragraph("        第二章   商品房基本状况", setChinese(FS14));
			p11.setAlignment(Element.ALIGN_LEFT);
			p11.setSpacingBefore(FS14); //设置段落前间距
			document.add(p11);

			Paragraph p12 = new Paragraph("        第三章   商品房价款", setChinese(FS14));
			p12.setAlignment(Element.ALIGN_LEFT);
			p12.setSpacingBefore(FS14); //设置段落前间距
			document.add(p12);

			Paragraph p13 = new Paragraph("        第四章   商品房交付条件与交付手续", setChinese(FS14));
			p13.setAlignment(Element.ALIGN_LEFT);
			p13.setSpacingBefore(FS14); //设置段落前间距
			document.add(p13);

			Paragraph p14 = new Paragraph("        第五章   面积差异处理方式", setChinese(FS14));
			p14.setAlignment(Element.ALIGN_LEFT);
			p14.setSpacingBefore(FS14); //设置段落前间距
			document.add(p14);

			Paragraph p15 = new Paragraph("        第六章   规划设计变更", setChinese(FS14));
			p15.setAlignment(Element.ALIGN_LEFT);
			p15.setSpacingBefore(FS14); //设置段落前间距
			document.add(p15);

			Paragraph p16 = new Paragraph("        第七章   商品房质量及保修责任", setChinese(FS14));
			p16.setAlignment(Element.ALIGN_LEFT);
			p16.setSpacingBefore(FS14); //设置段落前间距
			document.add(p16);

			Paragraph p17 = new Paragraph("        第八章   合同备案与房屋登记", setChinese(FS14));
			p17.setAlignment(Element.ALIGN_LEFT);
			p17.setSpacingBefore(FS14); //设置段落前间距
			document.add(p17);

			Paragraph p18 = new Paragraph("        第九章   前期物业管理", setChinese(FS14));
			p18.setAlignment(Element.ALIGN_LEFT);
			p18.setSpacingBefore(FS14); //设置段落前间距
			document.add(p18);

			Paragraph p19 = new Paragraph("        第十章   其他事项", setChinese(FS14));
			p19.setAlignment(Element.ALIGN_LEFT);
			p19.setSpacingBefore(FS14); //设置段落前间距
			document.add(p19);

			document.newPage();

			p = new Paragraph("说     明    ", setChinese(FS10 * 2, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB20);
			p.setSpacingAfter(SPB20);
			document.add(p);
			document.add(getParagraph("1. 本合同文本为示范文本，由中华人民共和国住房和城乡建设部、中 华人民共和国国家工商行政管理总局共同制定。各地可在有关法律法规、 规定的范围内，结合实际情况调整合同相应内容。 ", setChinese(FS14), SPB28,
					30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("2. 签订本合同前，出卖人应当向买受人出示《商品房预售许可证》及 其他有关证书和证明文件。 ", setChinese(FS14), SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("3. 出卖人应当就合同重大事项对买受人尽到提示义务。买受人应当审 慎签订合同，在签订本合同前，要仔细阅读合同条款，特别是审阅其中具 有选择性、补充性、修改性的内容，注意防范潜在的市场风险和交易风 险。 ",
					setChinese(FS14), SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("4. 本合同文本【】中选择内容、空格部位填写内容及其他需要删除 或添加的内容，双方当事人应当协商确定。【】中选择内容，以划 √ 方 式选定；对于实际情况未发生或双方当事人不作约定时，应当在空格部位 打 × ，以示删除。 ",
					setChinese(FS14), SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("5. 出卖人与买受人可以针对本合同文本中没有约定或者约定不明确的 内容，根据所售项目的具体情况在相关条款后的空白行中进行补充约定， 也可以另行签订补充协议。 ", setChinese(FS14), SPB28, 30.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("6. 双方当事人可以根据实际情况决定本合同原件的份数，并在签订合 同时认真核对，以确保各份合同内容一致；在任何情况下，出卖人和买受 人都应当至少持有一份合同原件。", setChinese(FS14), SPB28, 30.0f,
					0.0f, Element.ALIGN_LEFT));

			String ver = "0";

			document.newPage();

			p = new Paragraph("专业术语解释", setChinese(FS10 * 2, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB20);
			p.setSpacingAfter(SPB20);
			document.add(p);

			document.add(getParagraph("1. 商品房预售：是指房地产开发企业将正在建设中的取得《商品房预售许可证》 的商品房预先出售给买受人，并由买受人支付定金或房价款的行为。 ", setChinese(FS12), SPB28, 20.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("2. 法定代理人：是指依照法律规定直接取得代理权的人。  ", setChinese(FS12), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("3. 套内建筑面积：是指成套房屋的套内建筑面积，由套内使用面积、套内墙体面 积、套内阳台建筑面积三部分组成。  ", setChinese(FS12), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("4. 房屋的建筑面积：是指房屋外墙（柱）勒脚以上各层的外围水平投影面积，包 括阳台、挑廊、地下室、室外楼梯等，且具备有上盖，结构牢固，层高2.20M以上（含 2.20M）的永久性建筑。  ", setChinese(FS12), 28,
					20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("5. 不可抗力：是指不能预见、不能避免并不能克服的客观情况。 ", setChinese(FS12), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("6. 民用建筑节能：是指在保证民用建筑使用功能和室内热环境质量的前提下，降 低其使用过程中能源消耗的活动。民用建筑是指居住建筑、国家机关办公建筑和商 业、服务业、教育、卫生等其他公共建筑。", setChinese(FS12),
					SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("7. 房屋登记：是指房屋登记机构依法将房屋权利和其他应当记载的事项在房屋登 记簿上予以记载的行为。 ", setChinese(FS12), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("8. 所有权转移登记：是指商品房所有权从出卖人转移至买受人所办理的登记类型。  ", setChinese(FS12), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("9. 房屋登记机构：是指直辖市、市、县人民政府主管部门或者其 设置的负责房屋登记工作的机构。  ", setChinese(FS12), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("10. 分割拆零销售：是指房地产开发企业将成套的商品住宅分割为数部分分别出售 给买受人的销售方式。 ", setChinese(FS12), 28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("11. 返本销售：是指房地产开发企业以定期向买受人返还购房款的方式销售商品房 的行为。  ", setChinese(FS12), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("12. 售后包租：是指房地产开发企业以在一定期限内承租或者代为出租买受人所购 该企业商品房的方式销售商品房的行为。", setChinese(FS12), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.newPage();

			p = new Paragraph("商品房买卖合同", setChinese(FS10 * 2, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(-10f);
			p.setSpacingAfter(-10f);

			document.add(p);

			p = new Paragraph("（预 售）", setChinese(FS10 * 2));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB20);
			p.setSpacingAfter(SPB20);
			document.add(p);

			document.add(getParagraph(
					"出卖人向买受人出售其开发建设的房屋，双方当事人应当在自愿、平等、公平及 诚实信用的基础上，根据《中华人民共和国合同法》、《中华人民共和国物权法》、《中 华人民共和国城市房地产管理法》等法律、法规的规定，就商品房买卖相关内容协商 达成一致意见，签订本商品房买卖合同。",
					setChinese(FS12), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));

			p = new Paragraph("第一章         合同当事人", setChinese(FS14 * 1, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB20);
			p.setSpacingAfter(SPB20);
			document.add(p);

			//设表的宽度为523f，一行37个字，一个字占2.7%
			document.add(getPTable(new String[] { "甲方(卖方)：", " *" + svo.getSellerName() }, 18, 82, 0, 0, 0, 0));
			document.add(getPTable(new String[] { "住所：", " *" + svo.getSellerAddress(), " 邮编：", " *" + svo.getSellerPostcode() }, 10, 60, 11, 19, 0, 0));
			document.add(getPTable(new String[] { "营业执照号码：", " *" + svo.getSellerBizcardcode() }, 22, 78, 0, 0, 0, 0));
			document.add(getPTable(new String[] { "资质证书号码：", " *" + svo.getSellerCertcode() }, 22, 78, 0, 0, 0, 0));
			document.add(getPTable(new String[] { "法定代表人： ", " *" + svo.getSellerDelegate(), " 联系电话：", " *" + svo.getSellerDlgCall() }, 19, 41, 16, 24, 0, 0));
			document.add(getPTable(new String[] { "委托代理人：", " *" + svo.getSellerProxy(), " 联系电话：", " *" + svo.getSellerProxyCall() }, 19, 41, 16, 24, 0, 0));

			//查询合同乙方信息
			List<BuyerInfoVO> list2 = cqDao.searchBuyerInfo(String.valueOf(contractID));
			String buyername = "";
			if(list2 != null && list2.size() > 0){
				for(BuyerInfoVO bvo : list2){
					p = new Paragraph();
					p.setSpacingBefore(20);
					p.setSpacingAfter(20);
					document.add(p);
					buyername += bvo.getBuyerName();
					document.add(getPTable(new String[] { "乙方(买方)：", "*" + bvo.getBuyerName() }, 18, 82, 0, 0, 0, 0));
					document.add(getPTable(new String[] { "国籍：", "*" + dBo.getDictName("ct_504", String.valueOf(bvo.getBuyerNationality())), "  居住(注册)所在省市：",
							" *" + dBo.getDictName("ct_524", String.valueOf(bvo.getBuyerProvince())) }, 11, 28, 30, 31, 0, 0));
					String buyerBirth = DateUtil.parseDateTime3(bvo.getBuyerBirth());
					document.add(getPTable(new String[] { "个人/公司", "*" + baseHandle(bvo.getAttribute("buyer_type_dict_name")), " 性别：",
							" *" + baseHandle(bvo.getAttribute("buyer_sex_dict_name")), " 出生年月日： ", " *" + baseHandle(buyerBirth) }, 15, 15, 9, 9, 18, 34));
					document.add(getPTable(new String[] { "住址：", "*" + baseHandle(bvo.getBuyerAddress()), " 邮编：", " *" + baseHandle(bvo.getBuyerPostcode()) },
							11, 65, 11, 13, 0, 0));
					document.add(getPTable(new String[] { "证件名称：", "*" + dBo.getDictName("ct_506", String.valueOf(bvo.getBuyerCardname())), " 号码：",
							" *" + baseHandle(bvo.getBuyerCardcode()) }, 17, 38, 11, 34, 0, 0));
					document.add(getPTable(new String[] { "联系电话：", "*" + bvo.getBuyerCall() }, 17, 83, 0, 0, 0, 0));
					document.add(getPTable(new String[] { "委托/法定代理人：", "*" + baseHandle(bvo.getBuyerProxy()), " 联系电话：",
							"  *" + baseHandle(bvo.getBuyerProxyCall()) }, 27, 33, 17, 23, 0, 0));
					document.add(getPTable(new String[] { "住址：", "*" + baseHandle(bvo.getBuyerProxyAdr()) }, 12, 88, 0, 0, 0, 0));
				}
			}


			p = new Paragraph("第二章         商品房基本状况", setChinese(FS14 * 1, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);


			document.add(getParagraph("^第一条   项目建设依据  "));
			document.add(getParagraph(" 1. 出卖人以       " + " *" + cdvo.getf0101() + "    方式取得坐落于         " + " *" + cdvo.getf0102()
					+ "      地块的建设用地使用权。该地块        " + " *" + cdvo.getf0103() + "     为      " + " *" + cdvo.getf0104() + "    土地使用权面积为      " + " *"
					+ cdvo.getf0105() + "   平方米。买受人购买的商品房（以下简称该商品 房）所占用的土地用途为    " + " *" + cdvo.getf0106() + " ，土地使用权终止日期为  " + " *" + cdvo.getf0107() + " 。",
					setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("2. 出卖人经批准，在上述地块上建设的商品房项目核准名称为 " + " *" + cdvo.getf0108() + " ，建设工程规划许可证号为  " + " *" + cdvo.getf0109()
					+ "     ，建筑工程施工许可证号为       " + " *" + cdvo.getf0110() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("^第二条   预售依据  "));
			document.add(getParagraph(" 该商品房已由  " + " *" + cdvo.getf0201() + " 批准预售， 预售许可证号为  " + "  *" + cdvo.getf0202() + " 。", setChinese(FS14), SPB28,
					25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("^第三条   商品房基本情况   "));
			document.add(getParagraph(" 1. 该商品房的规划用途为" + " *" + cdvo.getf0301() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. 该商品房所在建筑物的主体结构为" + " *" + cdvo.getf0302() + " ，建筑总层 数为" + " *" + cdvo.getf0303() + " 层，其中地上 " + " *"
					+ cdvo.getf0304() + " 层，地下 " + " *" + cdvo.getf0305() + " 层。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 3. 该商品房为第一条规定项目中的" + " *" + cdvo.getf0306() + " 。房屋竣工后，如房号发生改变，不影响该商品房的特定位置。该商品房的平面图见附件一。", setChinese(FS14), SPB28,
					25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 4. 该商品房的房产测绘机构为 " + " *" + cdvo.getf0307() + " ,其预测建筑面积共" + " *" + cdvo.getf0308() + " 平方米,其中套内建筑面积  " + " *"
					+ cdvo.getf0309() + " 平方米，分摊共有建筑 面积  " + " *" + cdvo.getf0310() + " 平方米。该商品房共用部位见附件二。 ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("该商品房层高为 " + " *" + cdvo.getf0311() + " 米，有  " + " *" + cdvo.getf0312() + " 个阳台，其中  " + " *" + cdvo.getf0313()
					+ " 个阳台为封 闭式，" + " *" + cdvo.getf0314() + " 个阳台为非封闭式。阳台是否封闭以规划设计文件为准。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

			document.add(getParagraph("^第四条   抵押情况   "));
			document.add(getParagraph(" 与该商品房有关的抵押情况为   " + " *" + cdvo.getf0401() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 抵押类型：    " + " *" + cdvo.getf0402() + " ，抵押人：" + " *" + cdvo.getf0403(), setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 抵押权人：    " + " *" + cdvo.getf0404() + " ，抵押登记机构：" + " *" + cdvo.getf0405(), setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 抵押登记日期：    " + " *" + cdvo.getf0406() + " ，债务履行期限：" + " *" + cdvo.getf0407(), setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 抵押类型：    " + " *" + cdvo.getf0408() + " ，抵押人：" + " *" + cdvo.getf0409(), setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 抵押权人：    " + " *" + cdvo.getf0410() + " ，抵押登记机构：" + " *" + cdvo.getf0411(), setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 抵押登记日期：    " + " *" + cdvo.getf0412() + " ，债务履行期限：" + " *" + cdvo.getf0413(), setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 抵押权人同意该商品房转让的证明及关于抵押的相关约定见附件三。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("^第五条   房屋权利状况承诺   "));
			document.add(getParagraph(" 1. 出卖人对该商品房享有合法权利；    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. 该商品房没有出售给除本合同买受人以外的其他人；   ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 3. 该商品房没有司法查封或其他限制转让的情况；    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 4.   " + " *" + cdvo.getf0501() + " ; ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 5.   " + " *" + cdvo.getf0502() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(
					" 如该商品房权利状况与上述情况不符，导致不能完成本合同登记备案或房屋所有 权转移登记的，买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出 卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部 分），并自买受人付款之日起，按照" + " *"
							+ cdvo.getf0503() + " %（不低于中国人民银行公布的同期贷款基 准利率）计算给付利息。给买受人造成损失的，由出卖人支付  " + " *" + cdvo.getf0504() + " 的赔偿金。",
					setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

			p = new Paragraph("第三章         商品房价款", setChinese(FS14 * 1, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			document.add(getParagraph("^第六条   计价方式与价款    "));
			document.add(getParagraph(" 出卖人与买受人按照下列第 " + " *" + cdvo.getf0601() + " 种方式计算该商品房价款  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 1. 按照套内建筑面积计算，该商品房单价（不包含房屋装修）为每平方米  " + " *" + cdvo.getf0615() + " " + "（币种）" + " *" + cdvo.getf0602()
					+ " 元， 总价款（不包含房屋装修）为 " + " *" + cdvo.getf0616() + " " + "（币种）" + " *" + cdvo.getf0603() + " 元（大写  " + " *" + cdvo.getf0604()
					+ " 元整）。该商品房装修价格为每平方米  " + " *" + cdvo.getf0617() + " " + "（币种）" + " *" + cdvo.getf0618() + " 元， 装修总价为人民币 " + " *" + cdvo.getf0619()
					+ " 元（大写 "+ " *" + cdvo.getf0626()+" 元整）。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. 按照建筑面积计算，该商品房单价（不包含房屋装修）为每平方米 " + " *" + cdvo.getf0620() + " " + "（币种）" + " *" + cdvo.getf0605()
					+ " 元， 总价款（不包含房屋装修）为 " + " *" + cdvo.getf0621() + " " + "（币种）" + " *" + cdvo.getf0606() + " 元（大写  " + " *" + cdvo.getf0607()
					+ " 元整）。该商品房装修价格为每平方米  " + " *" + cdvo.getf0622() + " " + "（币种）" + " *" + cdvo.getf0608() + " 元， 装修总价为人民币 " + " *" + cdvo.getf0609()
					+ " 元（大写 "+ " *" + cdvo.getf0627()+" 元整）。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 3. 按照套计算，该商品房总价款（不包含房屋装修）为   " + " *" + cdvo.getf0623() + " " + "（币种）" + " *" + cdvo.getf0610() + "  元（大 写  " + " *"
					+ cdvo.getf0611() + " 元整）， 装修总价为人民币 " + " *" + cdvo.getf0624() + " 元（大写 "+ " *" + cdvo.getf0638()+" 元整）。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 4. 按照    " + " *" + cdvo.getf0612() + " 计算，该商品房总价款为  " + " *" + cdvo.getf0625() + " " + "（币种）" + " *" + cdvo.getf0613()
					+ " 元 （大写  " + " *" + cdvo.getf0614() + " 元整）。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 5. 按照套内建筑面积计算，该商品房单价为每平方米  " + " *" + cdvo.getf0628() + " " + "（币种）" + " *" + cdvo.getf0629()
					+ " 元， 总价款为 " + " *" + cdvo.getf0630() + " " + "（币种）" + " *" + cdvo.getf0631() + " 元（大写  " + " *" + cdvo.getf0632()
					+ " 元整）。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 6. 按照建筑面积计算，该商品房单价为每平方米  " + " *" + cdvo.getf0633() + " " + "（币种）" + " *" + cdvo.getf0634()
					+ " 元， 总价款为 " + " *" + cdvo.getf0635() + " " + "（币种）" + " *" + cdvo.getf0636() + " 元（大写  " + " *" + cdvo.getf0637()
					+ " 元整）。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("^第七条   付款方式及期限    "));
			document.add(getParagraph(" (一)签订本合同前，买受人已向出卖人支付定金 " + " *" + cdvo.getf0728() + "  （币种）    *" + cdvo.getf0701() + " 元（大写     *" + cdvo.getf0702()
					+ "  ）,该定金于     *" + cdvo.getf0703() + " 时   *" + cdvo.getf0705() + " 商品房价款。", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" (二)买受人采取下列第  " + " *" + cdvo.getf0707() + " 种方式付款：   ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 1. 一次性付款。买受人应当在   " + substringDate(cdvo.getf0708(), 1) + " 年 " + substringDate(cdvo.getf0708(), 2) + " 月 "
					+ substringDate(cdvo.getf0708(), 3) + " 日前支付该商品房全部价款。    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. 分期付款。买受人应当在    " + substringDate(cdvo.getf0709(), 1) + " 年 " + substringDate(cdvo.getf0709(), 2) + " 月 "
					+ substringDate(cdvo.getf0709(), 3) + " 日前分     " + " *" + cdvo.getf0710() + " 期支付该商品 房全部价款，首期房价款 " + " *" + cdvo.getf0729() + " " + "（币种）"
					+ " *" + cdvo.getf0711() + "  元（大写：  " + " *" + cdvo.getf0712() + " 元整），应当于  " + " *" + cdvo.getf0713() + "  日前支付。", setChinese(FS14),
					SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(cdvo.getf0714(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 3. 贷款方式付款： " + " *" + cdvo.getf0715() + " 。买受人应当于   " + " *" + cdvo.getf0717() + "  日前支付首期房价款 " + " *"
					+ cdvo.getf0730() + " " + "（币种）" + " *" + cdvo.getf0718() + " 元（大写：  " + " *" + cdvo.getf0719() + " 元整），占全部房价款 " + " *" + cdvo.getf0720()
					+ " % ",
					setChinese(FS14),
					SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 余款  " + " *" + cdvo.getf0731() + " " + "（币种）" + " *" + cdvo.getf0721() + " 元（大写  " + " *" + cdvo.getf0722()
					+ "  元整)向    " + " *" + cdvo.getf0723() + " （贷款机构）申请贷款支付。  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 4. 其他方式：   " + " *" + cdvo.getf0724() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (三)出售该商品房的全部房价款应当存入预售资金监管账户用于本工程建设。    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 该商品房的预售资金监管机构为  " + " *" + cdvo.getf0725() + " ，预售资金监管 账户名称为   " + " *" + cdvo.getf0726() + " ，账号为  " + " *"
					+ cdvo.getf0727() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 该商品房价款的计价方式、总价款、付款方式及期限的具体约定见附件四。    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("^第八条    逾期付款责任     "));
			document.add(getParagraph(" 除不可抗力外，买受人未按照约定时间付款的，双方同意按照下列第   " + " *" + cdvo.getf0801() + " 种方式 处理：   ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 1. 按照逾期时间，分别处理（（1）和（2）不作累加）。     ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (1)逾期在  " + " *" + cdvo.getf0802() + " 日之内，买受人按日计算向出卖人支付逾期应付款万分之   " + " *" + cdvo.getf0803() + " 的 违约金。 ",
					setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (2)逾期超过   " + " *" + cdvo.getf0804() + " 日（该期限应当与本条第（1）项中的期限相同）后，出卖人 有权解除合同。出卖人解除合同的，应当书面通知买受人。买受人应当自解除合同通 知送达之日起   "
					+ " *" + cdvo.getf0805() + "  日内按照累计应付款的  " + " *" + cdvo.getf0806() + "  %向出卖人支付违约金，同时，出卖 人退还买受人已付全部房款（含已付贷款部分）。 ", setChinese(FS14),
					SPB28,
					25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 出卖人不解除合同的，买受人按日计算向出卖人支付逾期应付款万分之   " + " *" + cdvo.getf0807() + " （该比率不低于第（1）项中的比率）的违约金。    ", setChinese(FS14), SPB28,
					25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 本条所称逾期应付款是指依照第七条及附件四约定的到期应付款与该期实际已付 款的差额;采取分期付款的,按照相应的分期应付款与该期的实际已付款的差额确定。     ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. " + " *" + cdvo.getf0808() + " 。"));

			p = new Paragraph("第四章         商品房交付条件与交付手续", setChinese(FS14 * 1, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);

			document.add(getParagraph("^第九条    商品房交付条件      "));
			document.add(getParagraph(" 该商品房交付时应当符合下列第1、2、" + " *" + cdvo.getf0901() + " 项所列条件 ", setChinese(FS14), SPB28,
					25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 1. 该商品房已取得建设工程竣工验收备案证明文件；      ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. 该商品房已取得房屋测绘报告；    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 3. " + " *" + cdvo.getf0902() + " ; ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 4. " + " *" + cdvo.getf0903() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 该商品房为住宅的，出卖人还需提供《住宅使用说明书》和《住宅质量保证书》。   ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

			document.add(getParagraph("^第十条    商品房相关设施设备交付条件       "));
			document.add(getParagraph("(一)基础设施设备       ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 1. 供水、排水：交付时供水、排水配套设施齐全，并与城市公共供水、排水管网 连接。使用自建设施供水的,供水的水质符合国家规定的饮用水卫生标准，   ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("  " + " *" + cdvo.getf1001() + " ; ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. 供电：交付时纳入城市供电网络并正式供电，  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("  " + " *" + cdvo.getf1002() + " ; ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 3. 供暖：交付时供热系统符合供热配建标准，使用城市集中供热的，纳入城市集 中供热管网，   ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("  " + " *" + cdvo.getf1003() + " ; ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 4. 燃气：交付时完成室内燃气管道的敷设，并与城市燃气管网连接，保证燃气供应，  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("  " + " *" + cdvo.getf1004() + " ; ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 5. 电话通信：交付时线路敷设到户   ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 6. 有线电视：交付时线路敷设到户；    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 7. 宽带网络：交付时线路敷设到户。    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 以上第1、2、3项由出卖人负责办理开通手续并承担相关费用；第4、5、6、7项 需要买受人自行办理开通手续。     ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 如果在约定期限内基础设施设备未达到交付使用条件，双方同意按照下列第" + " *" + cdvo.getf1005() + " 种 方式处理：     ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" (1)以上设施中第1、2、3、4项在约定交付日未达到交付条件的，出卖人按照本 合同第十二条的约定承担逾期交付责任。     ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 第5项未按时达到交付使用条件的，出卖人按日向买受人支付   " + " *" + cdvo.getf1006() + " 元的违约 金；第6项未按时达到交付使用条件的，出卖人按日向买受人支付   " + " *"
					+ cdvo.getf1007() + "  元的违约 金；第7项未按时达到交付使用条件的，出卖人按日向买受人支付  " + " *" + cdvo.getf1008() + "  元的违约 金。出卖人采取措施保证相关设施于约定交付日后 " + " *"
					+ cdvo.getf1009() + " 日之内达到交付使用条件。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(2)" + " *" + cdvo.getf1010() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(二)公共服务及其他配套设施（以建设工程规划许可为准）    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document
					.add(getParagraph(" 1. 小区内绿地率：   " + substringDate(cdvo.getf1011(), 1) + " 年 " + substringDate(cdvo.getf1011(), 2) + " 月 "
							+ substringDate(cdvo.getf1011(), 3) + " 日达到    " + " *" + cdvo.getf1012() + " ； ", setChinese(FS14), SPB28, 25.0f, 0.0f,
							Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. 小区内非市政道路：   " + substringDate(cdvo.getf1013(), 1) + " 年 " + substringDate(cdvo.getf1013(), 2) + " 月 "
					+ substringDate(cdvo.getf1013(), 3) + "  日达到      " + " *" + cdvo.getf1014() + " ； ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 3. 规划的车位、车库：  " + substringDate(cdvo.getf1015(), 1) + " 年 " + substringDate(cdvo.getf1015(), 2) + " 月 "
					+ substringDate(cdvo.getf1015(), 3) + " 日达到     " + " *" + cdvo.getf1016() + " ； ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 4.物业服务用房：  " + substringDate(cdvo.getf1017(), 1) + " 年 " + substringDate(cdvo.getf1017(), 2) + " 月 "
					+ substringDate(cdvo.getf1017(), 3) + " 日达到     " + " *" + cdvo.getf1018() + " ； ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 5. 医疗卫生机构：   " + substringDate(cdvo.getf1019(), 1) + " 年 " + substringDate(cdvo.getf1019(), 2) + " 月 "
					+ substringDate(cdvo.getf1019(), 3) + "  日达到      " + " *" + cdvo.getf1020() + " ； ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 6. 幼儿园：  " + substringDate(cdvo.getf1021(), 1) + " 年 " + substringDate(cdvo.getf1021(), 2) + " 月 "
					+ substringDate(cdvo.getf1021(), 3) + "  日达到      " + " *" + cdvo.getf1022() + " ； ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 7. 学校  " + substringDate(cdvo.getf1023(), 1) + " 年 " + substringDate(cdvo.getf1023(), 2) + " 月 "
					+ substringDate(cdvo.getf1023(), 3) + "  日达到      " + " *" + cdvo.getf1024() + " ； ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 8." + " *" + cdvo.getf1025() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 9." + " *" + cdvo.getf1026() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 以上设施未达到上述条件的，双方同意按照以下方式处理：    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 1. 小区内绿地率未达到上述约定条件的， " + " *" + cdvo.getf1027() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document
					.add(getParagraph(" 2. 小区内非市政道路未达到上述约定条件的，  " + " *" + cdvo.getf1028() + " 。   ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document
					.add(getParagraph(" 3. 规划的车位、车库未达到上述约定条件的，   " + " *" + cdvo.getf1029() + " 。   ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 4. 物业服务用房未达到上述约定条件的，   " + " *" + cdvo.getf1030() + " 。   ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 5. 其他设施未达到上述约定条件的，   " + " *" + cdvo.getf1031() + " 。   ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 关于本项目内相关设施设备的具体约定见附件五。    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

			document.add(getParagraph("^第十一条    交付时间和手续       "));
			document.add(getParagraph(" (一)出卖人应当在     " + " *" + cdvo.getf1101() + " 日前向买受人交付该商品房。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (二)该商品房达到第九条、第十条约定的交付条件后,出卖人应当在交付日期届 满前     " + " *" + cdvo.getf1102()
					+ "  日（不少于10日）将查验房屋的时间、办理交付手续的时间地点以及应当携 带的证件材料的通知书面送达买受人。买受人未收到交付通知书的,以本合同约定的 交付日期届满之日为办理交付手续的时间,以该商品房所在地为办理交付手续的地点。 ", setChinese(FS14),
					SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("  " + " *" + cdvo.getf1103() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 交付该商品房时，出卖人应当出示满足第九条约定的证明文件。出卖人不出示证 明文件或者出示的证明文件不齐全，不能满足第九条约定条件的，买受人有权拒绝接 收，由此产生的逾期交付责任由出卖人承担，并按照第十二条处理。 ",
					setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (三)查验房屋  "));
			document.add(getParagraph("  1. 办理交付手续前，买受人有权对该商品房进行查验，出卖人不得以缴纳相关税 费或者签署物业管理文件作为买受人查验和办理交付手续的前提条件。  ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("  2. 买受人查验的该商品房存在下列除地基基础和主体结构外的其他质量问题的， 由出卖人按照有关工程和产品质量规范、标准自查验次日起   " + " *" + cdvo.getf1104()
					+ " 日内负责修复，并承 担修复费用，修复后再行交付。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (1)屋面、墙面、地面渗漏或开裂等； ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (2)管道堵塞；  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (3)门窗翘裂、五金件损坏；  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (4)灯具、电器等电气设备不能正常使用； ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (5)" + " *" + cdvo.getf1105() + " ； ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (6)" + " *" + cdvo.getf1106() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("  3. 查验该商品房后，双方应当签署商品房交接单。由于买受人原因导致该商品房 未能按期交付的，双方同意按照以下方式处理：   ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" (1)" + " *" + cdvo.getf1107() + " ； ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (2)" + " *" + cdvo.getf1108() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

			document.add(getParagraph("^第十二条    逾期交付责任       "));
			document.add(getParagraph(" 除不可抗力外，出卖人未按照第十一条约定的时间将该商品房交付买受人的，双 方同意按照下列第   " + " *" + cdvo.getf1201() + " 种方式处理 ", setChinese(FS14), SPB28, 25.0f,
					0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 1. 按照逾期时间，分别处理 （（1）和（2）不作累加 ） 。  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (1)逾期在      " + " *" + cdvo.getf1202()
					+ " 日之内（该期限应当不多于第八条第1（1）项中的期限），自第 十一条约定的交付期限届满之次日起至实际交付之日止，出卖人按日计算向买受人支 付全部房价款万分之 " + " *" + cdvo.getf1203()
					+ " 的违约金（该违约金比率应当不低于第八条第1（1）项中的 比率）。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (2)逾期超过       " + " *" + cdvo.getf1204()
					+ "  日（该期限应当与本条第（1）项中的期限相同）后，买受人 有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通 知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之 日起，按照   " + " *"
					+ cdvo.getf1205() + "  %（不低于中国人民银行公布的同期贷款基准利率）计算给付利 息；同时，出卖人按照全部房价款的  " + " *" + cdvo.getf1206() + "  %向买受人支付违约金。 ", setChinese(FS14),
					SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 买受人要求继续履行合同的，合同继续履行，出卖人按日计算向买受人支付全部 房价款万分之  " + " *" + cdvo.getf1207() + " （该比率应当不低于本条第1（1）项中的比率）的违约金。",
					setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 2." + " *" + cdvo.getf1208() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));


			p = new Paragraph("第五章         面积差异处理方式", setChinese(FS14 * 1, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);

			document.add(getParagraph("^第十三条    面积差异处理       "));
			document.add(getParagraph(" 该商品房交付时，出卖人应当向买受人出示房屋测绘报告，并向买受人提供该商 品房的面积实测数据（以下简称实测面积）。实测面积与第三条载明的预测面积发生误 差的，双方同意按照第     " + " *" + cdvo.getf1301()
					+ " 种方式处理。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 1. 根据第六条按照套内建筑面积计价的约定，双方同意按照下列原则处理：       ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(1)套内建筑面积误差比绝对值在3%以内（含3%）的，据实结算房价款；   ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(2)套内建筑面积误差比绝对值超出3%时，买受人有权解除合同。        ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日 起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，按 照       " + " *" + cdvo.getf1302()
					+ "  %（不低于中国人民银行公布的同期贷款基准利率）计算给付利息。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document
					.add(getParagraph(
							" 买受人选择不解除合同的，实测套内建筑面积大于预测套内建筑面积时，套内建 筑面积误差比在3%以内（含3%）部分的房价款由买受人补足；超出 3% 部分的房价 款由出卖人承担，产权归买受人所有。实测套内建筑面积小于预测套内建筑面积时， 套内建筑面积误差比绝对值在3%以内（含3%）部分的房价款由出卖人返还买受人； 绝对值超出3%部分的房价款由出卖人双倍返还买受人。        ",
							setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 套内建筑面积误差比＝ 实测套内建筑面积－预测套内建筑面积/ 预测套内建筑面积 ×100%       ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. 根据第六条按照建筑面积计价的约定，双方同意按照下列原则处理：    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(1)建筑面积、套内建筑面积误差比绝对值均在3%以内（含3%）的，根据实测 建筑面积结算房价款；    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(2)建筑面积、套内建筑面积误差比绝对值其中有一项超出3%时，买受人有权解 除合同。    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日 起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，按 照  " + " *" + cdvo.getf1303()
					+ " %（不低于中国人民银行公布的同期贷款基准利率）计算给付利息。 "));
			document
					.add(getParagraph(
							" 买受人选择不解除合同的，实测建筑面积大于预测建筑面积时，建筑面积误差比 在3%以内（含3%）部分的房价款由买受人补足，超出3%部分的房价款由出卖人承 担，产权归买受人所有。实测建筑面积小于预测建筑面积时，建筑面积误差比绝对值 在3%以内（含3%）部分的房价款由出卖人返还买受人；绝对值超出3%部分的房价款 由出卖人双倍返还买受人 ",
							setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(3)因设计变更造成面积差异，双方不解除合同的，应当签署补充协议。       ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 3. 根据第六条按照套计价的，出卖人承诺在房屋平面图中标明详细尺寸，并约定 误差范围。该商品房交付时，套型与设计图纸不一致或者相关尺寸超出约定的误差范 围，双方约定如下：    ", setChinese(FS14), SPB28,
					25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("  " + " *" + cdvo.getf1304() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 4. 双方自行约定： "));
			document.add(getParagraph("  " + " *" + cdvo.getf1305() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));


			p = new Paragraph("第六章         规划设计变更", setChinese(FS14 * 1, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);

			document.add(getParagraph("^第十四条    规划变更       "));
			document.add(getParagraph("(一)出卖人应当按照城乡规划主管部门核发的建设工程规划许可证规定的条件建 设商品房，不得擅自变更。     ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(
					" 双方签订合同后，涉及该商品房规划用途、面积、容积率、绿地率、基础设施、 公共服务及其他配套设施等规划许可内容经城乡规划主管部门批准变更的，出卖人应 当在变更确立之日起10日内将书面通知送达买受人。出卖人未在规定期限内通知买受 人的，买受人有权解除合同。     ",
					setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(二)买受人应当在通知送达之日起15日内做出是否解除合同的书面答复。买受人 逾期未予以书面答复的，视同接受变更。        ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("(三)买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达 之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起， 按照     " + " *" + cdvo.getf1401()
					+ "  %（不低于中国人民银行公布的同期贷款基准利率）计算给付利息；同 时，出卖人按照全部房价款的  " + " *" + cdvo.getf1402() + "  %向买受人支付违约金。 "));
			document.add(getParagraph(" 买受人不解除合同的，有权要求出卖人赔偿由此造成的损失，双方约定如下：  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("  " + " *" + cdvo.getf1403() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

			document.add(getParagraph("^第十五条    设计变更       "));
			document.add(getParagraph(
					"(一)双方签订合同后，出卖人按照法定程序变更建筑工程施工图设计文件，涉及 下列可能影响买受人所购商品房质量或使用功能情形的，出卖人应当在变更确立之日 起10日内将书面通知送达买受人。出卖人未在规定期限内通知买受人的，买受人有权 解除合同。        ",
					setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 1. 该商品房结构形式、户型、空间尺寸、朝向；      ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. 供热、采暖方式；       ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 3." + " *" + cdvo.getf1501() + " ； ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 4. " + " *" + cdvo.getf1502() + " ； ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 5. " + " *" + cdvo.getf1503() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(二)买受人应当在通知送达之日起15日内做出是否解除合同的书面答复。买受人 逾期未予以书面答复的，视同接受变更。       ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document
.add(getParagraph("(三)买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达 之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起， 按照      " + " *" + cdvo.getf1504()
					+ "  %（不低于中国人民银行公布的同期贷款基准利率）计算给付利息；同 时，出卖人按照全部房价款的  " + cdvo.getf1505() + "  %向买受人支付违约金。 ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 买受人不解除合同的，有权要求出卖人赔偿由此造成的损失，双方约定如下：   ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("  " + " *" + cdvo.getf1506() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));


			p = new Paragraph("第七章         商品房质量及保修责任", setChinese(FS14 * 1, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);

			document.add(getParagraph("^第十六条    商品房质量       "));
			document.add(getParagraph("(一)地基基础和主体结构        ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(
					" 出卖人承诺该商品房地基基础和主体结构合格,并符合国家及行业标准。 经检测不合格的,买受人有权解除合同。买受人解除合同的,应当书面通知出卖 人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付 贷款部分）,并自买受人付款之日起,按照  " + " *"
							+ cdvo.getf1601() + "  %（不低于中国人民银行公布的同期 贷款基准利率）计算给付利息。给买受人造成损失的，由出卖人支付 " + " *" + cdvo.getf1602() + " 的赔偿金。因此而发生的检测费用由出卖人承担。 ",
					setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 买受人不解除合同的      " + " *" + cdvo.getf1603() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(二)其他质量问题      ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 该商品房质量应当符合有关工程质量规范、标准和施工图设计文件的要求。发现 除地基基础和主体结构外质量问题的，双方按照以下方式处理：   ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" (1)及时更换、修理；如给买受人造成损失的，还应当承担相应赔偿责任。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("  " + " *" + cdvo.getf1604() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (2)经过更换、修理，仍然严重影响正常使用的，买受人有权解除合同。买受人 解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退 还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起，按照        "
					+ " *" + cdvo.getf1605() + "  % （不低于中国人民银行公布的同期贷款基准利率）计算给付利息。给买受人造成损失 的，由出卖人承担相应赔偿责任。因此而发生的检测费用由出卖人承担。 ", setChinese(FS14), SPB28, 25.0f,
					0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 买受人不解除合同的   " + " *" + cdvo.getf1606() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(三)装饰装修及设备标准   ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 该商品房应当使用合格的建筑材料、构配件和设备，装置、装修、装饰所用材料 的产品质量必须符合国家的强制性标准及双方约定的标准。   ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 不符合上述标准的，买受人有权要求出卖人按照下列第（1）、    " + " *" + cdvo.getf1607() + " 、 " + " *" + cdvo.getf1608() + "  方 式处理（可多选）： ",
					setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (1)及时更换、修理；  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (2)出卖人赔偿双倍的装饰、设备差价；      ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (3) " + " *" + cdvo.getf1609() + " ； ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" (4) " + " *" + cdvo.getf1610() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 具体装饰装修及相关设备标准的约定见附件六。      ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(四)室内空气质量、建筑隔声和民用建筑节能措施    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 1. 该商品房室内空气质量符合       " + " *" + cdvo.getf1611() + " 标准，标准名称： " + " *" + cdvo.getf1612() + " ，标准文号： " + " *"
					+ cdvo.getf1613() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 该商品房为住宅的，建筑隔声情况符合      " + " *" + cdvo.getf1614() + " 标准，标准名称： " + " *" + cdvo.getf1615() + " ，标准文号： " + " *"
					+ cdvo.getf1616() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document
					.add(getParagraph(
							" 该商品房室内空气质量或建筑隔声情况经检测不符合标准，由出卖人负责整改， 整改后仍不符合标准的，买受人有权解除合同。买受人解除合同的，应当书面通知出 卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已 付贷款部分），并自买受人付款之日起，按照      "
									+ " *"
									+ cdvo.getf1617()
									+ " %（不低于中国人民银行公布的同 期贷款基准利率）计算给付利息。给买受人造成损失的，由出卖人承担相应赔偿责 任。经检测不符合标准的，检测费用由出卖人承担，整改后再次检测发生的费用仍由 出卖人承担。因整改导致该商品房逾期交付的，出卖人应当承担逾期交付责任。 ",
							setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. 该商品房应当符合国家有关民用建筑节能强制性标准的要求。    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 未达到标准的，出卖人应当按照相应标准要求补做节能措施，并承担全部费用； 给买受人造成损失的，出卖人应当承担相应赔偿责任。        ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("  " + " *" + cdvo.getf1618() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

			document.add(getParagraph("^第十七条     保修责任        "));
			document.add(getParagraph(
					"(一)商品房实行保修制度。该商品房为住宅的,出卖人自该商品房交付之日起, 按照《住宅质量保证书》承诺的内容承担相应的保修责任。该商品房为非住宅的,双 方应当签订补充协议详细约定保修范围、保修期限和保修责任等内容。具体内容见附 件七。       ",
					setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(二)下列情形，出卖人不承担保修责任：      ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 1. 因不可抗力造成的房屋及其附属设施的损害；   ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. 因买受人不当使用造成的房屋及其附属设施的损害；      ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 3. " + " *" + cdvo.getf1701() + " 。  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(三)在保修期内，买受人要求维修的书面通知送达出卖人    " + " *" + cdvo.getf1702()
					+ "  日内，出卖人 既不履行保修义务也不提出书面异议的，买受人可以自行或委托他人进行维修，维修 费用及维修期间造成的其他损失由出卖人承担。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

			document.add(getParagraph("^第十八条     质量担保        ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 出卖人不按照第十六条、第十七条约定承担相关责任的，由      " + " *" + cdvo.getf1801() + " 承担连带 责任。 ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 关于质量担保的证明见附件八。      ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));


			p = new Paragraph("第八章         合同备案与房屋登记", setChinese(FS14 * 1, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB20);
			document.add(p);

			document.add(getParagraph("^第十九条     预售合同登记备案        "));
			document.add(getParagraph("(一)出卖人应当自本合同签订之日起     " + " *" + cdvo.getf1901() + " 日内（不超过30日） 办理商品房预售合同登记备案手续，并将本合同登记备案情况告知买受人。 ", setChinese(FS14),
					SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(二)有关预售合同登记备案的其他约定如下：      ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("  " + " *" + cdvo.getf1902() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

			document.add(getParagraph("^第二十条     房屋登记       "));
			document.add(getParagraph("(一)双方同意共同向房屋登记机构申请办理该商品房的房屋所有权转移登记。        ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(二)因出卖人的原因，买受人未能在该商品房交付之日起       " + " *" + cdvo.getf2001() + " 日内取得该商 品房的房屋所有权证书的，双方同意按照下列第 " + " *" + cdvo.getf2002()
					+ "  种方式处理： ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 1. 买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当 自解除合同通知送达之日起15日内退还买受人已付全部房款(含已付贷款部分),并自 买受人付款之日起，按照   " + " *" + cdvo.getf2003()
					+ "  %(不低于中国人民银行公布的同期贷款基准利率) 计算给付利息。买受人不解除合同的，自买受人应当完成房屋所有权登记的期限届满 之次日起至实际完成房屋所有权登记之日止,出卖人按日计算向买受人支付全部房价 款万分之  " + " *" + cdvo.getf2004()
					+ "  的违约金。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 2." + " *" + cdvo.getf2005() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(三)因买受人的原因未能在约定期限内完成该商品房的房屋所有权转移登记的， 出卖人不承担责任。  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));


			p = new Paragraph("第九章         前期物业管理", setChinese(FS14 * 1, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);
			document.add(getParagraph("^第二十一条     前期物业管理       "));
			document
					.add(getParagraph("(一)出卖人依法选聘的前期物业服务企业为         " + " *" + cdvo.getf2101() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(二)物业服务时间从       " + substringDate(cdvo.getf2102(), 1) + " 年 " + substringDate(cdvo.getf2102(), 2) + " 月 "
					+ substringDate(cdvo.getf2102(), 3) + " 日到 " + substringDate(cdvo.getf2103(), 1) + " 年 " + substringDate(cdvo.getf2103(), 2) + " 月 "
					+ substringDate(cdvo.getf2103(), 3) + " 日。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(三)物业服务期间，物业收费计费方式为       " + " *" + cdvo.getf2104() + " 。物业 服务费为 " + " *" + cdvo.getf2105() + " 元/月·平方米（建筑面积）。",
					setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(四)买受人同意由出卖人选聘的前期物业服务企业代为查验并承接物业共用部 位、共用设施设备，出卖人应当将物业共用部位、共用设施设备承接查验的备案情况 书面告知买受人。  ", setChinese(FS14), SPB28, 25.0f,
					0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(五)买受人已详细阅读前期物业服务合同和临时管理规约，同意由出卖人依法选 聘的物业服务企业实施前期物业管理，遵守临时管理规约。业主委员会成立后，由业 主大会决定选聘或续聘物业服务企业。 ", setChinese(FS14), SPB28,
					25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 该商品房前期物业服务合同、临时管理规约见附件九。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

			p = new Paragraph("第十章         其他事项", setChinese(FS14 * 1, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);

			document.add(getParagraph("^第二十二条     建筑物区分所有权         "));
			document.add(getParagraph("(一)买受人对其建筑物专有部分享有占有、使用、收益和处分的权利。         ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(二)以下部位归业主共有：   ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 1. 建筑物的基础、承重结构、外墙、屋顶等基本结构部分，通道、楼梯、大堂等 公共通行部分，消防、公共照明等附属设施、设备，避难层、设备层或者设备间等结 构部分；       ", setChinese(FS14), SPB28,
					25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. 该商品房所在建筑区划内的道路（属于城镇公共道路的除外）、绿地（属于城镇 公共绿地或者明示属于个人的除外）、占用业主共有的道路或者其他场地用于停放汽车 的车位、物业服务用房；       ", setChinese(FS14),
					SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 3." + " *" + cdvo.getf2201() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(三)双方对其他配套设施约定如下：     ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 1. 规划的车位、车库：  " + " *" + cdvo.getf2202() + " ; ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. 会所：    " + " *" + cdvo.getf2203() + " ; ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 3." + " *" + cdvo.getf2204() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

			document.add(getParagraph("^第二十三条     税费        "));
			document.add(getParagraph(" 双方应当按照国家的有关规定，向相应部门缴纳因该商品房买卖发生的税费。因 预测面积与实测面积差异，导致买受人不能享受税收优惠政策而增加的税收负担，由      " + " *" + cdvo.getf2301() + " 承担。",
					setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

			document.add(getParagraph("^第二十四条     销售和使用承诺        "));
			document.add(getParagraph(" 1. 出卖人承诺不采取分割拆零销售、返本销售或者变相返本销售的方式销售商品 房；不采取售后包租或者变相售后包租的方式销售未竣工商品房。     ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. 出卖人承诺按照规划用途进行建设和出售，不擅自改变该商品房使用性质，并 按照规划用途办理房屋登记。出卖人不得擅自改变与该商品房有关的共用部位和设施 的使用性质。     ", setChinese(FS14), SPB28,
					25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 3. 出卖人承诺对商品房的销售，不涉及依法或者依规划属于买受人共有的共用部 位和设施的处分。        ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 4. 出卖人承诺已将遮挡或妨碍房屋正常使用的情况告知买受人。具体内容见附件十。    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 5. 买受人使用该商品房期间，不得擅自改变该商品房的用途、建筑主体结构和承 重结构。    ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 6." + " *" + cdvo.getf2401() + " ; ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 7." + " *" + cdvo.getf2402() + " 。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

			document.add(getParagraph("^第二十五条     送达        "));
			document.add(getParagraph(" 出卖人和买受人保证在本合同中记载的通讯地址、联系电话均真实有效。任何根 据本合同发出的文件，均应采用书面形式，以         " + " *" + cdvo.getf2501()
					+ " 方式送达对方。任何一方变更通讯地址、联系电话的，应在变更之日起 " + " *" + cdvo.getf2502() + "  日 内书面通知对方。变更的一方未履行通知义务导致送达不能的，应承担相应的法律责 任。 ", setChinese(FS14), SPB28,
					25.0f, 0.0f,
					Element.ALIGN_LEFT));

			document.add(getParagraph("^第二十六条     买受人信息保护        "));
			document.add(getParagraph(
					" 出卖人对买受人信息负有保密义务。非因法律、法规规定或国家安全机关、公安 机关、检察机关、审判机关、纪检监察部门执行公务的需要，未经买受人书面同意， 出卖人及其销售人员和相关工作人员不得对外披露买受人信息，或将买受人信息用于 履行本合同之外的其他用途。         ",
					setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

			document.add(getParagraph("^第二十七条     争议解决方式        "));
			document.add(getParagraph(" 本合同在履行过程中发生的争议，由双方当事人协商解决，也可通过消费者协会 等相关机构调解；或按照下列第        " + " *" + cdvo.getf2701() + " 种方式解决： ", setChinese(FS14),
					SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 1. 依法向房屋所在地人民法院起诉。  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 2. 提交       " + " *" + cdvo.getf2702() + " 仲裁委员会仲裁。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

			document.add(getParagraph("^第二十八条	补充协议        "));
			document.add(getParagraph(" 对本合同中未约定或约定不明的内容，双方可根据具体情况签订书面补充协议 （补充协议见附件十一）。     ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" 补充协议中含有不合理的减轻或免除本合同中约定应当由出卖人承担的责任，或 不合理的加重买受人责任、排除买受人主要权利内容的，仍以本合同为准。         ", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));

			document.add(getParagraph("^第二十九条 合同生效 "));
			document
					.add(getParagraph(" 本合同自双方签字或盖章之日起生效。本合同的解除应当采用书面形式。 本合同及附件共" + " *" + cdvo.getf2901() + " 页， 一式" + " *" + cdvo.getf2902() + " 份， 其中出卖人"
							+ " *" + cdvo.getf2903() + " 份， 买受人" + " *" + cdvo.getf2904() + " 份， [" + " *" + cdvo.getf2905() + " ]" + " *" + cdvo.getf2906()
							+ " 份， [" + " *" + cdvo.getf2907() + " ]" + " *" + cdvo.getf2908() + " 份。合同附件与本合同具有同等法律效力。", setChinese(FS14), SPB28, 25.0f, 0.0f,
							Element.ALIGN_LEFT));

			document.newPage();

			p = new Paragraph("  ");
			p.setSpacingAfter(200);
			document.add(p);

			PdfPTable table = new PdfPTable(4);
			table.setTotalWidth(523);
			int[] width2 = { 25, 25, 30, 20 };
			table.setWidths(width2);
			table.setLockedWidth(true);
			PdfPCell cell1 = new PdfPCell(new Paragraph("出卖人（签字或盖章）：", setChinese(FS10)));
			cell1.setColspan(2);
			cell1.setMinimumHeight(50);
			cell1.setBorder(0);

			PdfPCell cell3 = new PdfPCell(new Paragraph("买受人（签字或盖章）：", setChinese(FS10)));
			cell3.setColspan(2);
			cell3.setMinimumHeight(50);
			cell3.setBorder(0);

			PdfPCell cell5 = new PdfPCell(new Paragraph("【法定代表人】（签字或盖章）：", setChinese(FS10)));
			cell5.setColspan(2);
			cell5.setMinimumHeight(50);
			cell5.setBorder(0);
			PdfPCell cell6 = new PdfPCell(new Paragraph("【法定代表人】（签字或盖章）： ", setChinese(FS10)));
			cell6.setColspan(2);
			cell6.setMinimumHeight(50);
			cell6.setBorder(0);
			PdfPCell cell7 = new PdfPCell(new Paragraph("【委托代理人】（签字或盖章）：", setChinese(FS10)));
			cell7.setColspan(2);
			cell7.setMinimumHeight(50);
			cell7.setBorder(0);
			PdfPCell cell8 = new PdfPCell(new Paragraph("【委托代理人】（签字或盖章）：", setChinese(FS10)));
			cell8.setColspan(2);
			cell8.setMinimumHeight(50);
			cell8.setBorder(0);
			PdfPCell cell9 = new PdfPCell(new Paragraph("", setChinese(FS10)));
			cell9.setColspan(2);
			cell9.setMinimumHeight(50);
			cell9.setBorder(0);
			PdfPCell cell10 = new PdfPCell(new Paragraph("【法定代理人】（签字或盖章）：", setChinese(FS10)));
			cell10.setColspan(2);
			cell10.setMinimumHeight(50);
			cell10.setBorder(0);
			PdfPCell cell11 = new PdfPCell(new Paragraph("日期：______ 年 ___月___日 ", setChinese(FS10)));
			cell11.setColspan(2);
			cell11.setMinimumHeight(50);
			cell11.setBorder(0);
			PdfPCell cell12 = new PdfPCell(new Paragraph("日期：______年 ___月___日 ", setChinese(FS10)));
			cell12.setColspan(2);
			cell12.setMinimumHeight(50);
			cell12.setBorder(0);
			PdfPCell cell13 = new PdfPCell(new Paragraph("签于：________________", setChinese(FS10)));
			cell13.setColspan(2);
			cell13.setMinimumHeight(50);
			cell13.setBorder(0);
			PdfPCell cell14 = new PdfPCell(new Paragraph("签于：________________", setChinese(FS10)));
			cell14.setColspan(2);
			cell14.setMinimumHeight(50);
			cell14.setBorder(0);
			table.addCell(cell1);
			table.addCell(cell3);
			table.addCell(cell5);
			table.addCell(cell6);
			table.addCell(cell7);
			table.addCell(cell8);
			table.addCell(cell9);
			table.addCell(cell10);
			table.addCell(cell11);
			table.addCell(cell12);
			table.addCell(cell13);
			table.addCell(cell14);
			document.add(table);

			if(contractvo.getStatus() >= 2){
				//甲方拟签人员信息
				SignerVO signerVO;
				if(contractvo.getSigner() != null && !"".equals(contractvo.getSigner())){
					signerVO = (SignerVO) cqDao.find(SignerVO.class, Long.parseLong(contractvo.getSigner()));
					PdfPTable table14 = new PdfPTable(4);
					int width14[] = { 21, 29, 21, 29 };
					table14.setWidths(width14);
					table14.setTotalWidth(540);
					table14.setLockedWidth(true);
					PdfPCell cell141 = new PdfPCell(new Paragraph("甲方拟签人员：", setChinese(FS10)));
					PdfPCell cell142 = new PdfPCell(new Paragraph(signerVO.getName(), setChinese(FS10)));
					PdfPCell cell143 = new PdfPCell(new Paragraph("销售员证书号：", setChinese(FS10)));
					PdfPCell cell144 = new PdfPCell(new Paragraph(signerVO.getCardCode(), setChinese(FS10)));
					cell141.setBorder(0);
					cell143.setBorder(0);
					cell142.setBorder(0);
					cell144.setBorder(0);
					table14.addCell(cell141);
					table14.addCell(cell142);
					table14.addCell(cell143);
					table14.addCell(cell144);
					document.add(table14);
					Paragraph blankRow14 = new Paragraph(12f, " ", setChinese(FS10));
					document.add(blankRow14);
				}
				//甲方确认人员信息
				SignerVO signerVO1;
				if(contractvo.getConfirmer() != null && !"".equals(contractvo.getConfirmer())){
					signerVO1 = (SignerVO) cqDao.find(SignerVO.class, Long.parseLong(contractvo.getConfirmer()));
					PdfPTable table15 = new PdfPTable(4);
					int width15[] = { 21, 29, 21, 29 };
					table15.setWidths(width15);
					table15.setTotalWidth(540);
					table15.setLockedWidth(true);
					PdfPCell cell151 = new PdfPCell(new Paragraph("甲方确认人员：", setChinese(FS10)));
					PdfPCell cell152 = new PdfPCell(new Paragraph(signerVO1.getName(), setChinese(FS10)));
					PdfPCell cell153 = new PdfPCell(new Paragraph("销售员证书号：", setChinese(FS10)));
					PdfPCell cell154 = new PdfPCell(new Paragraph(signerVO1.getCardCode(), setChinese(FS10)));
					cell151.setBorder(0);
					cell153.setBorder(0);
					cell152.setBorder(0);
					cell154.setBorder(0);
					table15.addCell(cell151);
					table15.addCell(cell152);
					table15.addCell(cell153);
					table15.addCell(cell154);
					document.add(table15);
					Paragraph blankRow15 = new Paragraph(12f, " ", setChinese(FS10));
					document.add(blankRow15);
				}

				//网签合同时间
				String confirmDate = contractvo.getConfirmDate();
				if(confirmDate != null && !"".equals(confirmDate) && !"0".equals(confirmDate)){
					PdfPTable table16 = new PdfPTable(2);
					int width16[] = { 26, 74 };
					table16.setWidths(width16);
					table16.setTotalWidth(540);
					table16.setLockedWidth(true);
					PdfPCell cell161 = new PdfPCell(new Paragraph("网上合同签订时间：", setChinese(FS10)));
					PdfPCell cell162 = new PdfPCell(new Paragraph(sdf.format(DateUtil.parseDateTime2(confirmDate)), setChinese(FS10)));
					cell161.setBorder(0);
					cell162.setBorder(0);
					table16.addCell(cell161);
					table16.addCell(cell162);
					document.add(table16);
					Paragraph blankRow16 = new Paragraph(12f, " ", setChinese(FS10));
					document.add(blankRow16);
				}
			}

			document.newPage();


			String FJ2 = null, FJ3 = null, FJ4 = null, FJ5 = null, FJ8 = null, FJ9 = null, FJ10 = null, FJ11 = null;
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("contractid", "=", contractID));
			List<AttachVO> atVOlList = cqDao.searchByAttibutes(AttachVO.class, params, new ArrayList<MetaOrder>(), new Page());
			if(atVOlList != null && atVOlList.size() > 0){
				for(AttachVO attachVO : atVOlList){
					if(attachVO.getContent() != null){
						String content = new String(attachVO.getContent());
						if(attachVO.getSerial() == 3902){
							FJ2 = content;
						}else if(attachVO.getSerial() == 3903){
							FJ3 = content;
						}else if(attachVO.getSerial() == 3904){
							FJ4 = content;
						}else if(attachVO.getSerial() == 3905){
							FJ5 = content;
						}else if(attachVO.getSerial() == 3908){
							FJ8 = content;
						}else if(attachVO.getSerial() == 3909){
							FJ9 = content;
						}else if(attachVO.getSerial() == 3910){
							FJ10 = content;
						}else if(attachVO.getSerial() == 3911){
							FJ11 = content;
						}
					}
				}
			}
			p = new Paragraph();
			c = new Chunk("附  件  一    房屋平面图", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(SPB28);
			document.add(p);
			document.add(getParagraph("1.房屋分层分户图。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("2.建设工程规划方案总平面图。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			HouseVO hvo = (HouseVO) pdfDAO.find(HouseVO.class, contractvo.getHouseID());
//			hvo.setPre_Plan_ID("25000000868168");
			try{
				if(hvo.getPre_Plan_ID() != null && !"".equals(hvo.getPre_Plan_ID())){
					File file1 = new File(tempPdfPath); //创建临时存放pdf的文件夹
					if(!file1.exists()){
						file1.mkdirs();
					}
					File file2 = new File(tempImgPath); //创建临时存放图片的文件夹
					if(!file2.exists()){
						file2.mkdirs();
					}
					String demo = hvo.getPre_Plan_ID() + "-" + String.valueOf(System.currentTimeMillis());
					getFile(hvo.getPre_Plan_ID(), demo);
					boolean flag = pdf2Pic(tempPdfPath + demo + ".pdf", tempImgPath + demo);
					if(flag == true){
						Image image = Image.getInstance(tempImgPath + demo + ".png");
						image.scaleAbsolute(523f, 500f);
						image.setAlignment(Element.ALIGN_CENTER);
						document.add(image);

						File pdfFile = new File(tempPdfPath + demo + ".pdf");
						//pdfFile.deleteOnExit();

						File imageFile = new File(tempImgPath + demo + ".png");
						//imageFile.deleteOnExit();

						deleteFile(pdfFile);
						deleteFile(imageFile);

					}
				}
			}catch (Exception e){
				System.out.println("合同打印附件一出错(" + contractID + ")");
				e.printStackTrace();
			}
			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  二    关于该商品房共用部位的具体说明（可附图说明）", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(SPB28);
			document.add(p);
			document.add(getParagraph("1.纳入该商品房分摊的共用部位的名称、面积和所在位置。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("2.未纳入该商品房分摊的共用部位的名称、所在位置。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(new Paragraph(FJ2, setChinese(FS14)));

			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  三    抵押权人同意该商品房转让的证明及关于抵押的相关约定", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(SPB28);
			document.add(p);
			document.add(getParagraph("1.抵押权人同意该商品房转让的证明。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("2.解除抵押的条件和时间。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("3.关于抵押的其他约定。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(new Paragraph(FJ3, setChinese(FS14)));

			document.newPage();


			p = new Paragraph();
			c = new Chunk("附  件  四    关于该商品房价款的计价方式、总价款、付款方式及期限的具体约定", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(SPB28);
			document.add(p);
			
			List<MetaFilter> paramsa = new ArrayList<MetaFilter>();
			paramsa.add(new MetaFilter("contractID", "=", contractID)); //"201100158822"
			List<MetaOrder> ordersa = new ArrayList<MetaOrder>();
			ordersa.add(new MetaOrder("attach2MoneyID", MetaOrder.ASC));
			List<PresellAttach2MoneyVO> palist = cqDao.search(PresellAttach2MoneyVO.class, paramsa, ordersa, null);
			if(palist!=null&&!palist.isEmpty()){
				PresellAttach2MoneyVO paVo = palist.get(0);
				document.add(getParagraph("付款金额：      *" + baseHandle(paVo.getTotalMoneyStr()), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("付款方式：      *" + baseHandle(paVo.getPaymentMode()) + "      贷款方式：       *" + baseHandle(paVo.getBorrowMode()),
						setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				for(int count=0;count<palist.size();count++){
					PresellAttach2MoneyVO pa = palist.get(count);
					StringBuffer sb = new StringBuffer();
					if(pa.getState() == 4){
						sb.append(count + 1).append("、").append("贷款申请时间：     *").append(pa.getFkDate()).append("    ,贷款金额：    *").append(pa.getMoneyStr())
								.append("   ,款项：    *").append(pa.getStateStr()).append(",贷款机构：   *").append(pa.getBankName()).append(",放款时间：  *").append(
										pa.getDkDate());
					}else{
						sb.append(count + 1).append("、").append("付款时间:     *").append(pa.getFkDate()).append("    ,付款金额：    *").append(pa.getMoneyStr())
								.append("   ,款项：    *").append(pa.getStateStr());
					}
					document.add(getParagraph(sb.toString(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				}
			}

			document.add(new Paragraph(FJ4, setChinese(FS14)));
			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  五    关于本项目内相关设施、设备的具体约定", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(SPB28);
			document.add(p);
			document.add(getParagraph("1.相关设施的位置及用途", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("2.其他约定 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(new Paragraph(FJ5, setChinese(FS14)));

			document.newPage();


			p = new Paragraph();
			c = new Chunk("附  件  六    关于装饰装修及相关设备标准的约定", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(SPB28);
			document.add(p);
			document.add(getParagraph("交付的商品房达不到本附件约定装修标准的，按照本合同第十六条第（三）款约定处理。出卖人未经双方约定增加的装置、装修、装饰，视为无条件赠送给买受人。", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("双方就装饰装修主要材料和设备的品牌、产地、规格、数量等内容约定如下：", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("1.外墙：" + " *" + cdvo.getf9001() + " ；        *" + cdvo.getf9002() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("2.起居室：", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("（1）内墙：" + " *" + cdvo.getf9003() + " ；   *" + cdvo.getf9004() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("（2）顶棚：" + " *" + cdvo.getf9005() + " ；   *" + cdvo.getf9006() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("（3）室内地面：" + " *" + cdvo.getf9007() + " ；    *" + cdvo.getf9008() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("3.厨房：", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("（1）地面：" + " *" + cdvo.getf9009() + " ；     *" + cdvo.getf9010() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("（2）墙面：" + " *" + cdvo.getf9011() + " ；     *" + cdvo.getf9012() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("（3）顶棚：" + " *" + cdvo.getf9013() + " ；     *" + cdvo.getf9014() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("（4）厨具：" + " *" + cdvo.getf9015() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("4.卫生间：", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("（1）地面：" + " *" + cdvo.getf9016() + " ；    *" + cdvo.getf9017() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("（2）墙面：" + " *" + cdvo.getf9018() + " ；    *" + cdvo.getf9019() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("（3）顶棚：" + " *" + cdvo.getf9020() + " ；    *" + cdvo.getf9021() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("（4）卫生器具：" + " *" + cdvo.getf9022() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("5.阳台：" + " *" + cdvo.getf9023() + " ；     *" + cdvo.getf9024() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("6.电梯：", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("（1）品牌：" + " *" + cdvo.getf9025() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("（2）型号：" + " *" + cdvo.getf9026() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("7.管道：     *" + cdvo.getf9027() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("8.窗户：     *" + cdvo.getf9028() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("9.     *" + cdvo.getf9029(), setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("10.     *" + cdvo.getf9030(), setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  七    关于保修范围、保修期限和保修责任的约定", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(SPB28);
			document.add(p);
			document.add(getParagraph("该商品房为住宅的，出卖人应当提供《住宅质量保证书》；该商品房为非住宅的，双方可参照《住宅质量保证书》中的内容对保修范围、保修期限和保修责任等进行约定。", setChinese(FS14), SPB28, 25.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("该商品房的保修期自房屋交付之日起计算，关于保修期限的约定不应低于《建设工程质量管理条例》第四十条规定的最低保修期限。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(一)保修项目、期限及责任的约定 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("1.地基基础和主体结构：", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document
					.add(getParagraph("保修期限为：" + " *" + cdvo.getf9101() + " (不得低于设计文件规定的该工程的合理使用年限)", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" *" + cdvo.getf9102() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("2.屋面防水工程、有防水要求的卫生间、房间和外墙面的防渗漏：", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("保修期限为：" + " *" + cdvo.getf9103() + " (不得低于5年)", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" *" + cdvo.getf9104() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("3.供热、供冷系统和设备：", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("保修期限为：" + " *" + cdvo.getf9105() + " (不得低于2个采暖期、供冷期)", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" *" + cdvo.getf9106() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("4.电气管线、给排水管道、设备安装：", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("保修期限为：" + " *" + cdvo.getf9107() + " (不得低于2年)；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" *" + cdvo.getf9108() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("5.装修工程：", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("保修期限为：" + " *" + cdvo.getf9109() + " (不得低于2年)", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" *" + cdvo.getf9110() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("6.  *" + cdvo.getf9111(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("7.  *" + cdvo.getf9112(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("8.  *" + cdvo.getf9113(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(二)其他约定", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(" *" + cdvo.getf9114(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  八    关于质量担保的证明", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(SPB28);
			document.add(p);
			document.add(new Paragraph(FJ8, setChinese(FS14)));
			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  九    关于物业管理的约定", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(SPB28);
			document.add(p);
			document.add(getParagraph("1.前期物业服务合同", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("2.临时管理规约 ", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(new Paragraph(FJ9, setChinese(FS10)));
			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  十       出卖人关于遮挡或妨碍房屋正常使用情况的说明", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(SPB28);
			document.add(p);
			document.add(getParagraph("(如:该商品房公共管道检修口、柱子、变电箱等有遮挡或妨碍房屋正常使用的情况)", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(new Paragraph(FJ10, setChinese(FS10)));
			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  十  一    补充协议", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingAfter(SPB28);
			document.add(p);
			document.add(new Paragraph(FJ11, setChinese(FS10)));
			document.newPage();

			List<MetaFilter> xgParams = new ArrayList<MetaFilter>();
			xgParams.add(new MetaFilter("contractID", "=", contractID));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("contractID", MetaOrder.ASC));
			//查找限制的信息返回list
			List<XgLimitSaleContractVO> xgList = pdfDAO.search(XgLimitSaleContractVO.class, xgParams, orders, new Page());
			if(xgList != null && xgList.size() > 0){
				p = new Paragraph();
				c = new Chunk("附  件  十  二    购房资格查验证明", setChinese(FS14, Font.BOLD));
				p.add(c);
				document.add(p);
				p = new Paragraph("-------------------------------------------------------------------------------");
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingBefore(5);
				p.setSpacingAfter(SPB28);
				document.add(p);
				for(XgLimitSaleContractVO xgvo : xgList){
					document.add(getParagraph("编号：" + xgvo.getLimitSaleID() + "  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				}
			}

			document.close();

			ContractPdfVO ctv = new ContractPdfVO();
			ctv.setPdfData(baos.toByteArray());
			baos.close();
			return ctv;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cqDao);
			closeDAO(pdfDAO);
		}

	}

	/**
	 * 交接书合同pdf打印
	 * @param deliverID
	 * @return
	 * @throws Exception
	 */
	public ContractPdfVO createDeliverContract(long deliverID) throws Exception {
		DictionaryDAO dicDao = new DictionaryDAO();
		Document document = null;
		ByteArrayOutputStream baos = null;
		try{
			openDAO(cqDao);
			openDAO(dicDao);
			DeliverContractVO dcVo = new DeliverContractVO();
			if(deliverID > 0){
				dcVo = (DeliverContractVO) cqDao.find(DeliverContractVO.class, deliverID);
			}
			//ContractDealVO cdvo = (ContractDealVO) cqDao.find(ContractDealVO.class, dcVo.getContractID());
			//ProjectVO projectVO = (ProjectVO) cqDao.find(ProjectVO.class, cdvo.getProjectID());
			//查询项目区县对应机构名称
			//List<DictVO> dicList = dicDao.getDictionaryList("ct_510", false, String.valueOf(projectVO.getDistrictID()));
			//String orgName = "";
			//if(dicList != null && dicList.size() > 0){
			//	orgName = ((DictVO) dicList.get(0)).getName();
			//}

			document = new Document();
			baos = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			writer = PdfWriter.getInstance(document, baos);


			document.open();

			//合同号条形码
			PdfContentByte cd128 = writer.getDirectContent();
			Barcode128 code128 = new Barcode128();
			code128.setCodeType(code128.CODE128);
			code128.setCode(String.valueOf(dcVo.getContractID()));
			code128.setBarHeight(30);
			Image image128 = code128.createImageWithBarcode(cd128, null, null);
			image128.setAlignment(2);
			document.add(image128);

			Paragraph sellContract = new Paragraph("房 屋 交 接 书", setChinese(FS14 * 2));
			sellContract.setAlignment(Element.ALIGN_CENTER);
			sellContract.setSpacingBefore(FS14 * 1); //设置段落前间距
			sellContract.setSpacingAfter(FS14 * 1);
			document.add(sellContract);

			//房屋号条形码
			PdfContentByte cd2 = writer.getDirectContent();
			Barcode128 code2 = new Barcode128();
			code2.setCodeType(code2.CODE128);
			code2.setCode(String.valueOf(dcVo.getHouseID()));
			code2.setBarHeight(30);
			Image image2 = code2.createImageWithBarcode(cd2, null, null);
			image2.setAlignment(1);
			document.add(image2);

			document.add(getPTable(new String[] { "甲方（卖方）：", "*" + dcVo.getSeller() }, 25, 75, 0, 0, 0, 0));
			document.add(getPTable(new String[] { "乙方（买方）：", "*" + dcVo.getBuyer() }, 25, 75, 0, 0, 0, 0));

			Paragraph p = new Paragraph();
			p.add(setChunk(dcVo.getDeliverYear(), 0));
			p.add(setChunk("年", 1));
			p.add(setChunk(dcVo.getDeliverMonth(), 0));
			p.add(setChunk("月", 1));
			p.add(setChunk(dcVo.getDeliverDay(), 0));
			p.add(setChunk("日，甲、乙双方对", 1));
			p.add(setChunk(dcVo.getLocation(), 0));
			p.add(setChunk("室进行交接，双方确认：", 1));
			setParagraph(p, FL28, LD21, SPB28, Element.ALIGN_LEFT);
			document.add(p);

			p = new Paragraph();
			p.add(setChunk("1、甲方交付给乙方的房屋为", 1));
			p.add(setChunk(dcVo.getLocation(), 0));
			p.add(setChunk("室(以下简称该房屋)。该房屋的实测建筑面积为 ", 1));
			p.add(setChunk(StringUtil.convertDouble(dcVo.getN1_7()), 0));
			p.add(setChunk("平方米（相应占有的土地使用分摊面积为 ", 1));
			p.add(setChunk(StringUtil.convertDouble(dcVo.getN1_8()), 0));
			p.add(setChunk(" 平方米），另有地下附属面积", 1));
			p.add(setChunk(StringUtil.convertDouble(dcVo.getCellarArea()), 0));
			p.add(setChunk("平方米，实测建筑面积的测绘机构为", 1));
			p.add(setChunk(dcVo.getChargeDep(), 0));
			p.add(setChunk("认定的 ", 1));
			p.add(setChunk(dcVo.getN1_9(), 0));
			p.add(setChunk("新建商品房房地产权证号（权属证明）:", 1));
			p.add(setChunk(dcVo.getN1_10(), 0));
			p.add(setChunk("。 ", 1));
			setParagraph(p, FL28, LD21, SPB28, Element.ALIGN_LEFT);
			document.add(p);

			p = new Paragraph();
			p.add(setChunk("2、该房屋的总房价款为人民币", 1));
			p.add(setChunk(StringUtil.convertDouble(dcVo.getN2_1()), 0));
			p.add(setChunk("元。", 1));
			setParagraph(p, FL28, LD21, SPB28, Element.ALIGN_LEFT);
			document.add(p);

			//dcVo.setN2_2("　　" + dcVo.getN2_2() + "　　");
			//dcVo.setN2_4("　　" + dcVo.getN2_4() + "　　");
			//p = new Paragraph();
			//p.add(setChunk("", 1));a
			//p.add(setChunk(dcVo.getN2_2(), 0));
			//p.add(setChunk("。 ", 1));
			//document.add(p);
			p = new Paragraph();
			p = getParagraph("(大写)：*" + dcVo.getN2_2() + "。", setChinese(FS12), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT);
			document.add(p);

			
			p = new Paragraph();
			p.add(setChunk("乙方", 1));
			if(dcVo.getAttribute("paytype_dict_name") != null){
				p.add(setChunk(dcVo.getAttribute("paytype_dict_name").toString(), 0));
			}else{
				p.add(setChunk(" ", 0));
			}
			p.add(setChunk(StringUtil.convertDouble(dcVo.getN2_3()), 0));
			p.add(setChunk("元。", 1));
			setParagraph(p, FL28, LD21, SPB28, Element.ALIGN_LEFT);
			document.add(p);


			//p = new Paragraph();
			//p.add(setChunk("(大写)：", 1));
			//p.add(setChunk(dcVo.getN2_4(), 0));
			//p.add(setChunk("。 ", 1));
			//document.add(p);
			document.add(getParagraph("(大写)：*" + dcVo.getN2_4() + "。", setChinese(FS12), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));

			document.add(setChunk("甲方已开具", 1));
			if(dcVo.getAttribute("receipttype_dict_name") != null){
				document.add(setChunk(dcVo.getAttribute("receipttype_dict_name").toString(), 0));
			}else{
				document.add(setChunk(" ", 0));
			}

			document.add(setChunk("给乙方。", 1));

			p = new Paragraph();
			p.add(setChunk("3、", 1));
			if(dcVo.getN3_1() != null && dcVo.getN3_1().length > 0){
				p.add(setChunk(new String(dcVo.getN3_1(), "GBK"), 0));
			}
			setParagraph(p, FL28, LD21, SPB28, Element.ALIGN_LEFT);
			document.add(p);

			p = new Paragraph();
			p.add(setChunk("本交接书由甲乙双方签字生效。", 1));
			setParagraph(p, FL28, LD21, SPB28, Element.ALIGN_LEFT);
			document.add(p);

			document.add(new Chunk().NEWLINE);

			document.add(getPTable(new String[] { "甲方签字:", "乙方签字:" }, 50, 50, 0, 0, 0, 0));

			p = new Paragraph();
			p.add(setChunk("  ", 1));
			setParagraph(p, FL28, LD21, SPB28, Element.ALIGN_LEFT);
			document.add(p);

			PdfPTable table = new PdfPTable(2);
			table.setTotalWidth(523);
			int[] width2 = { 50, 50 };
			table.setWidths(width2);
			table.setLockedWidth(true);
			PdfPCell cell1 = new PdfPCell(new Paragraph("日期：     年     月     日 ", setChinese(FS14)));
			cell1.setMinimumHeight(40);
			cell1.setBorder(0);
			PdfPCell cell2 = new PdfPCell(new Paragraph("日期：     年     月     日 ", setChinese(FS14)));
			cell2.setMinimumHeight(40);
			cell2.setBorder(0);
			table.addCell(cell1);
			table.addCell(cell2);
			document.add(table);

			document.close();

			ContractPdfVO cpVo = new ContractPdfVO();
			String pdfPath = Constant.ContractURL + "\\" + DateUtil.getCurrentYear() + "\\" + DateUtil.getSysDateYYYYMMDD().substring(4, 6) + "\\" + DateUtil.getSysDateYYYYMMDD().substring(6, 8);
			File file = new File(pdfPath);
			if(!file.exists()){
				file.mkdirs();
			}
			getFile(baos.toByteArray(), pdfPath, String.valueOf(deliverID) + ".pdf");
			cpVo.setPdfPath(pdfPath + "\\" + String.valueOf(deliverID) + ".pdf");
			baos.close();
			return cpVo;
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(cqDao);
			closeDAO(dicDao);
			if(document != null){
				document.close();
			}
			if(baos != null){
				baos.close();
			}
		}
	}

	/**
	 * 功能描述：交接书html方式
	 * @param deliverID
	 * @return
	 * @throws Exception
	 */
	public DeliverContractVO createDeliverContractHtml(long deliverID) throws Exception {
		DictionaryDAO dicDao = new DictionaryDAO();
		try{
			openDAO(cqDao);
			openDAO(dicDao);
			DeliverContractVO dcVo = new DeliverContractVO();
			if(deliverID > 0){
				dcVo = (DeliverContractVO) cqDao.find(DeliverContractVO.class, deliverID);
			}
			return dcVo;
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			closeDAO(cqDao);
			closeDAO(dicDao);
		}
	}

	/**
	 * 功能描述：生成pdf段落，str为所有字符内容，通过空格分离，带^号的字体加粗，带*号的字体左右各加两个空格和下划线 ：替换需要加下滑线参数的格式为[ " + " *" + N1_1 + " ]
	 * @param str
	 * @return
	 */
	private Paragraph getParagraph(String str) {
		Paragraph p = new Paragraph();
		Chunk c;
		String test = str.replaceAll("\\s{1,}", " ");
		String[] strs = test.split(" ");
		for(String chunk : strs){
			if(chunk.indexOf("^") != -1){
				chunk = chunk.replace("^", "");
				c = new Chunk(chunk + " ", setChinese(FS14, Font.BOLD));
			}else if(chunk.indexOf("*") != -1){
				chunk = chunk.replace("*", "");

				c = new Chunk(kg2 + baseHandle(chunk) + kg2, setChinese(FS14));
				c.setUnderline(0.1f, -3f);
			}else{
				c = new Chunk(baseHandle(chunk), setChinese(FS14));
			}
			p.add(c);
		}
		p.setFirstLineIndent(FL28);
		p.setLeading(LD21);
		p.setSpacingBefore(SPB28);
		return p;
	}

	/**
	 * 功能描述：生成pdf段落，str为所有字符内容，通过空格分离，带^号的字体加粗，带*号的字体左右各加两个空格和下划线，FS为字体格式，FLI为首行缩进，LDi为行间距，SPB为段落前空间，AL为段落位置 ：替换需要加下滑线参数的格式为[ " + " *" + N1_1 + " ]
	 * @param str
	 * @return
	 */
	private Paragraph getParagraph(String str,Font FS,float FLI,float LDi,float SPB,int AL) {
		Paragraph p = new Paragraph();
		Chunk c;
		if(str == null)
			return p;
		String test = str.replaceAll("\\s{1,}", " ");
		String[] strs = test.split(" ");
		for(String chunk : strs){
			if(chunk.indexOf("^") != -1){
				chunk = chunk.replace("^", "");
				c = new Chunk(chunk + " ", FS);
			}else if(chunk.indexOf("*") != -1){
				chunk = chunk.replace("*", "");
				c = new Chunk(kg2 + baseHandle(chunk) + kg2, FS);
				c.setUnderline(0.1f, -3f);
			}else{
				c = new Chunk(baseHandle(chunk), FS);
			}
			p.add(c);
		}
		p.setFirstLineIndent(FLI);
		p.setLeading(LDi);
		p.setSpacingBefore(SPB);
		p.setAlignment(AL);
		return p;
	}

	/**
	 * 功能描述：生成pdf表格，最多六个单元格， str为表格的所有内容，通过空格分离，如果带*号，则加下边框，a,b,c,d,e,f分别为单元格的占整行的百分比
	 * @param str
	 * @return
	 */
	private PdfPTable getPTable(String[] str,int a,int b,int c,int d,int e,int f) throws DocumentException {
		int[] width = { a, b, c, d, e, f };
		int[] widthh = Arrays.copyOfRange(width, 0, str.length);
		PdfPTable table = new PdfPTable(str.length);
		table.setTotalWidth(523);
		table.setWidths(widthh);
		table.setLockedWidth(true);
		table.setSpacingBefore(5);
		PdfPCell cell;
		for(String chunk : str){
			if(chunk.indexOf("*") != -1){
				chunk = chunk.replaceAll("\\s{1,}", " ");
				chunk = chunk.replace(" *", "").replace("*", "");
				cell = new PdfPCell(new Paragraph(baseHandle(chunk), setChinese(FS14)));
				setCell(cell);
			}else{
				chunk = chunk.replaceAll("\\s{1,}", "");
				cell = new PdfPCell(new Paragraph(chunk, setChinese(FS14)));
				cell.setBorder(0);
			}
			table.addCell(cell);
		}
		return table;
	}

	/**
	 * 功能描述：生成pdf表格，最多六个单元格， str为表格的所有内容，通过空格分离，如果带*号，则加下边框，a,b,c,d,e,f分别为单元格的占整行的百分比， FS可以设置字体
	 * @param str
	 * @return
	 */
	public PdfPTable getPTable(String str,int a,int b,int c,int d,int e,int f,Font FS) throws DocumentException {
		String test = str.replaceAll("\\s{1,}", " ");
		String[] strs = test.split(" ");
		int[] width = { a, b, c, d, e, f };
		int[] widthh = Arrays.copyOfRange(width, 0, strs.length);
		PdfPTable table = new PdfPTable(strs.length);
		table.setTotalWidth(523);
		table.setWidths(widthh);
		table.setLockedWidth(true);
		table.setSpacingBefore(5);
		PdfPCell cell;
		for(String chunk : strs){
			if(chunk.indexOf("*") != -1){
				chunk = chunk.replace("*", "");
				cell = new PdfPCell(new Paragraph(baseHandle(chunk), FS));
				setCell(cell);
			}else{
				cell = new PdfPCell(new Paragraph(chunk, FS));
				cell.setBorder(0);
			}
			table.addCell(cell);
		}
		return table;
	}

	public PdfPTable getPTable1(String str,int a,int b,int c,int d,int e,int f,Font FS) throws DocumentException {
		String test = str.replaceAll("\\s{1,}", " ");
		String[] strs = test.split(" ");
		int[] width = { a, b, c, d, e, f };
		int[] widthh = Arrays.copyOfRange(width, 0, strs.length);
		PdfPTable table = new PdfPTable(strs.length);
		table.setTotalWidth(315);
		table.setWidths(widthh);
		table.setLockedWidth(true);
		table.setSpacingBefore(5);
		PdfPCell cell;
		for(String chunk : strs){
			if(chunk.indexOf("*") != -1){
				chunk = chunk.replace("*", "");
				cell = new PdfPCell(new Paragraph(baseHandle(chunk), FS));
				setCell(cell);
			}else{
				cell = new PdfPCell(new Paragraph(chunk, FS));
				cell.setBorder(0);
			}
			table.addCell(cell);
		}
		return table;
	}

	/*
	 * 设置Chunk，当为0时添加下划线，当为1时正常输出，当为2时，字体加粗
	 * @param str,o
	 * @return Chunk
	 */
	private Chunk setChunk(String str,int inputType) {
		Chunk c = new Chunk();
		if(inputType == 0){
			c = new Chunk(kg2 + baseHandle(str) + kg2, setChinese(FS14));
			c.setUnderline(0.1f, -3f);
		}else if(inputType == 1){
			c = new Chunk(baseHandle(str), setChinese(FS14));
		}else if(inputType == 2){
			c = new Chunk(baseHandle(str), setChinese(FS14, Font.BOLD));
		}
		return c;
	}
	
	private Paragraph setParagraph(Paragraph p,float FLI,float LDi,float SPB,int AL) {
		p.setFirstLineIndent(FLI);
		p.setLeading(LDi);
		p.setSpacingBefore(SPB);
		p.setAlignment(AL);
		return p;
	}

	/**
	 * 功能描述：生成表格的单元格时设置单元格只有下边框，
	 * @param cell
	 * @return
	 */
	private PdfPCell setCell(PdfPCell cell) {
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthTop(0);
		cell.setBorderWidthRight(0);
		cell.setPaddingBottom(5);
		return cell;
	}

	/**
	 * 功能描述：生成pdf时处理空数据，
	 * @param str
	 * @return
	 */
	public String baseHandle(Object obj) {
		String str = String.valueOf(obj);
		if(str.indexOf("null") != -1 || str.equalsIgnoreCase("null") || str.equals("/")){
			return "    ";
		}else{
			return str;
		}
	}

	/**
	 * 将一个对象里所有的空值属性设置成"  "
	 * @param o
	 * @return
	 */
	public Object changeToNull(Object o) {
		Class c = o.getClass();
		DecimalFormat df = new DecimalFormat(".00");
		try{
			Field[] fs = c.getDeclaredFields();
			for(Field f : fs){
				f.setAccessible(true);
				String st = f.get(o) + "";
				String str = st.replaceAll(" ", "");
				if(str.equals("") || str == null || str.equals("null") || str.equals("/") || str.equals("X")){
					f.set(o, kg2);
				}
				if(f.get(o) instanceof Float){
					f.set(o, df.format(o));
				}
				if(f.get(o) instanceof byte[] && f.get(o) != null){
					f.set(o, o.toString());
				}
			}
			o.toString();
		}catch (SecurityException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return o;
	}

	//设置字体样式
	public static Font setChinese(float size,int font) {
		BaseFont bfChinese = null;
		try{
			//bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			bfChinese = BaseFont.createFont("/com/netcom/nkestate/common/stsong.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		}catch (Exception e){
			e.printStackTrace();
		}
		Font chineseFont = new Font(bfChinese, size, font);
		return chineseFont;
	}

	//设置字体大小
	public static Font setChinese(float size) {
		BaseFont bfChinese = null;
		try{
			//bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			bfChinese = BaseFont.createFont("/com/netcom/nkestate/common/stsong.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			//			bfChinese = BaseFont.createFont("config/pdftemplate/simsuns.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		}catch (Exception e){
			e.printStackTrace();
		}
		Font setChinese = new Font(bfChinese, size, Font.NORMAL);
		return setChinese;
	}

	/**
	 * 功能描述：根据byte字节流数组，生成文件
	 * @param bfile
	 * @param filePath
	 * @param fileName
	 */
	public void getFile(byte[] bfile,String filePath,String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try{
			File dir = new File(filePath);
			if(!dir.exists() && dir.isDirectory()){//判断文件目录是否存在  
				dir.mkdirs();
			}
			file = new File(filePath + "\\" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if(bos != null){
				try{
					bos.close();
				}catch (IOException e1){
					e1.printStackTrace();
				}
			}
			if(fos != null){
				try{
					fos.close();
				}catch (IOException e1){
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 功能描述：pdf转化成图片
	 * @param pdfPath
	 * @param path
	 */
	public boolean pdf2Pic(String pdfPath,String path) {
		try{
			File pdfFile = new File(pdfPath);
			if(!pdfFile.exists()){
				throw new Exception("未存在保存的pdf房屋平面图：" + pdfPath);
			}

			/*org.icepdf.core.pobjects.Document document = new org.icepdf.core.pobjects.Document();
			document.setFile(pdfPath);
			float scale = 2.5f;//缩放比例
			float rotation = -90f;//旋转角度

			//取pdf第一页的内容，所以getPageImage方法的第一个参数为0
			BufferedImage image = (BufferedImage) document.getPageImage(0, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
			RenderedImage rendImage = image;
			try{
				String imgName = ".png";
				//System.out.println(imgName);
				File file = new File(path + imgName);
				ImageIO.write(rendImage, "png", file);
			}catch (IOException e){
				e.printStackTrace();
			}
			image.flush();
			document.dispose();*/
			PDDocument doc = PDDocument.load(pdfFile);
            PDFRenderer renderer = new PDFRenderer(doc);
            //float scale = 2.5f;//缩放比例
            // 方式1,第二个参数是设置缩放比(即像素)
            BufferedImage rendImage = renderer.renderImageWithDPI(0, 400);
            int w = rendImage.getWidth();
            int h = rendImage.getHeight();
            int type = rendImage.getColorModel().getTransparency();
            BufferedImage img = new BufferedImage(h, w, type);//创建新的img，因为旋转90°，所以长宽和原图对调
            Graphics2D graphics2d = img.createGraphics();
            graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
            		  RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2d.rotate(Math.toRadians(90),h/2,w/2);//旋转角度
            graphics2d.drawImage(rendImage, (h-w)/2, (w-h)/2, null);//起始旋转位置
            graphics2d.dispose();
            try{
				String imgName = ".png";
				//System.out.println(imgName);
				File file = new File(path + imgName);
				ImageIO.write(img, "png", file);
			}catch (IOException e){
				e.printStackTrace();
			}
            img.flush();
            doc.close();
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	//递归删除文件夹  
	public void deleteFile(File file) {
		if(file.exists()){ //判断文件是否存在  
			if(file.isFile()){ //判断是否是文件  
				file.delete(); //删除文件   
			}else if(file.isDirectory()){ //否则如果它是一个目录  
				File[] files = file.listFiles(); //声明目录下所有的文件 files[];  
				for(int i = 0; i < files.length; i++){ //遍历目录下所有的文件  
					this.deleteFile(files[i]); //把每个文件用这个方法进行迭代  
				}
				//file.delete();	//删除文件夹  
			}
		}else{
			System.out.println("所删除的文件不存在");
		}
	}

	/**
	 * 功能描述：根据url路径生成文件（pdf）
	 * @param planID
	 * @param fileName
	 */
	public void getFile(String planID,String fileName) {
		URL url = null;
		HttpURLConnection conn = null;
		InputStream is = null;
		BufferedOutputStream baos = null;
		try{
			System.out.println("########通过URL获取PDF：" + Constant.housePlanURL + planID);
			url = new URL(Constant.housePlanURL + planID);
			conn = (HttpURLConnection) url.openConnection();
			is = (InputStream) conn.getInputStream();
			baos = new BufferedOutputStream(new FileOutputStream(new File(tempPdfPath + fileName + ".pdf")));
			int buffer = 1024;
			byte[] b = new byte[buffer];
			int length = 0;
			while ( (length = is.read(b, 0, buffer)) > 0){
				baos.write(b, 0, length);
				//System.out.println("length=" + length);
			}
			System.out.println("@@@@@@@@@@@@@通过URL获取PDF：" + Constant.housePlanURL + planID);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if(is != null){
				try{
					is.close();
				}catch (IOException e1){
					e1.printStackTrace();
				}
			}
			if(baos != null){
				try{
					baos.close();
				}catch (IOException e1){
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 功能描述：预售合同文本
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public ContractPdfVO createPresellContractPdf(long contractID,int userType,int signFlag) throws Exception {

		try{
			openDAO(cqDao);
			openDAO(pdfDAO);

			DictionaryBO dicBo = new DictionaryBO();
			//查询合同主表信息
			ContractDealVO cdvo = (ContractDealVO) cqDao.find(ContractDealVO.class, contractID);
			//查询预售合同信息
			PresellContractVO pcVo = (PresellContractVO) cqDao.find(PresellContractVO.class, contractID);
			if(pcVo == null){
				pcVo = new PresellContractVO();
			}
			//查询合同甲方信息
			SellerInfoVO svo = new SellerInfoVO();
			List<SellerInfoVO> list1 = cqDao.searchSellerInfo(String.valueOf(contractID));
			if(list1 != null && list1.size() > 0){
				svo = list1.get(0);
			}

			Document document = new Document(PageSize.A4);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//定义输出位置并把文档对象装入输出对象中
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			//添加水印
			if(userType == 0) //内网用户
				writer.setPageEvent(new Watermark("合同查阅"));
			if(userType == 1 && (cdvo.getStatus() == 0 || cdvo.getStatus() == 1)) //外网用户草签或待签合同
				if(signFlag != 1){
					writer.setPageEvent(new Watermark("非正式合同"));
				}else{
					if(cdvo.getStatus() == 1){
						writer.setPageEvent(new Watermark("电子合同"));
					}
				}
			if(userType == 1 && (cdvo.getStatus() != 0 && cdvo.getStatus() != 1 && cdvo.getStatus() != 2)) //外网其他合同
				writer.setPageEvent(new Watermark(dBo.getDictName("ct_500", String.valueOf(cdvo.getStatus())) + "合同"));

			//页眉
			HeaderFooter header = new HeaderFooter(new Phrase(String.valueOf(contractID)
					+ "---------------------------------------------------------------------------------------------"
					+ DateUtil.formatDateTime(DateUtil.parseDateTime2( (DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()))), setChinese(FS10)),
					false);
			header.setBorder(Rectangle.NO_BORDER);
			header.setAlignment(1);
			header.setBorderColor(Color.BLACK);
			document.setHeader(header);

			//页码
			HeaderFooter footer = new HeaderFooter(new Phrase(" - ", setChinese(FS10)), new Phrase(" - ", setChinese(FS10)));
			footer.setAlignment(1);
			footer.setBorderColor(Color.red);
			footer.setBorder(Rectangle.NO_BORDER);
			document.setFooter(footer);

			document.open();

			//封面
			//合同号条形码
			PdfContentByte cd128 = writer.getDirectContent();
			Barcode128 code128 = new Barcode128();
			code128.setCodeType(code128.CODE128);
			code128.setCode(String.valueOf(contractID));
			code128.setBarHeight(30);
			Image image128 = code128.createImageWithBarcode(cd128, null, null);
			image128.setAlignment(2);
			document.add(image128);

			Paragraph sellContract = new Paragraph("青岛市商品房预售合同", setChinese(FS14 * 2));
			sellContract.setAlignment(Element.ALIGN_CENTER);
			sellContract.setSpacingBefore(FS14 * 4); //设置段落前间距
			sellContract.setSpacingAfter(FS14 * 6);
			document.add(sellContract);

			//房屋号条形码
			PdfContentByte cd2 = writer.getDirectContent();
			Barcode128 code2 = new Barcode128();
			code2.setCodeType(code2.CODE128);
			code2.setCode(String.valueOf(cdvo.getHouseID()));
			code2.setBarHeight(30);
			Image image2 = code2.createImageWithBarcode(cd2, null, null);
			image2.setAlignment(1);
			document.add(image2);

			Paragraph p1 = new Paragraph("青岛市工商行政管理局", setChinese(FS10 * 2));
			p1.setAlignment(Element.ALIGN_CENTER);
			p1.setSpacingBefore(FS10 * 6); //设置段落前间距
			document.add(p1);
			Paragraph p2 = new Paragraph("制定          ", setChinese(FS10 * 2));
			p2.setAlignment(Element.ALIGN_RIGHT);
			document.add(p2);
			Paragraph p3 = new Paragraph("青岛市建设委员会", setChinese(FS10 * 2));
			p3.setAlignment(Element.ALIGN_CENTER);
			document.add(p3);
			document.newPage();

			Paragraph p;
			Chunk c;

			p = new Paragraph("特    别    告    知", setChinese(FS10 * 2, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB20);
			p.setSpacingAfter(SPB20);
			document.add(p);
			document.add(getParagraph("一、本合同文本是根据有关房地产转让的法律、法规、规定制定的示范文本，印制的合同条款为提示性条款，供双方当事人约定采用。", setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("二、购房是一种民事法律行为，涉及的标的额较大、专业性较强、法律规范较多，为更好地维护双方当事人的权益，双方签订合同时应当慎重，力求签订得具体、全面、严密。", setChinese(FS14), SPB28, 20.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("三、在签订预售合同前，房地产开发企业应向购房人出示商品房预售许可证。预售许可证真实性、合法性及该商品房屋是否存在重复预售和被司法机关查封等权利转移受限制的情况，购房人可向该商品房屋所在地的区市房地产交易中心查阅。",
					setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("四、为保护合同双方当事人的合法权益，双方可以将预售广告、售楼书约定为商品房预售合同的附件。", setChinese(FS14), 28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("五、预售的商品房是房地产开发企业正在建设中的房屋（或已建成但未办理房屋所有权初始登记的房屋），该房屋的面积、交房日期质量等方面都存在着不确定因素。房屋买卖双方在签订前，应对下列问题予以充分了解：", setChinese(FS14),
					SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph(
					"1、商品房预售时房屋的建筑面积是暂测的，房屋交付时则以房屋所在地房屋、土地或房地产主管部门认定的测绘机构实测建筑面积为准。预售合同中须明确载明房屋的建筑面积、套内建筑面积、公用分摊建筑面积。暂测面积与交付时实测面积不一致，按《商品房销售管理办法》第二十条规定）处理。",
					setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("[第二十条：按套内建筑面积或者建筑面积计价的，当事人应当在合同中载明合同约定面积与产权登记面积发生误差的处理方式。", setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("合同未作约定的，按以下原则处理：", setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("(一)面积误差比绝对值在3%以内(含3%)的，据实结算房价款；", setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document
					.add(getParagraph(
							"(二)面积误差比绝对值超出3%时，买受人有权退房。买受人退房的，房地产开发企业应当在买受人提出退房之日起30日内将买受人已付房价款退还给买受人，同时支付已付房价款利息。买受人不退房的，产权登记面积大于合同约定面积时，面积误差比在3%以内(含3%)部分的房价款由买受人补足；超出3%部分的房价款由房地产开发企业承担，产权归买受人。产权登记面积小于合同约定面积时，面积误差比绝对值在3%以内(含3%)部分的房价款由房地产开发企业返还买受人；绝对值超出3%部分的房价款由房地产开发企业双倍返还买受人。",
					setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("面积误差比＝(产权登记面积-合同约定面积 )/合同约定面积 ×100％", setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_CENTER));
			document.add(getParagraph("因本办法第二十四条规定的规划设计变更造成面积差异，当事人不解除合同的，应当签署补充协议。]", setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			document
					.add(getParagraph(
							"2、国务院发布的《建设工程质量管理条例》规定，施工单位对商品房屋的质量的保修期限为：\"（一）基础设施工程、房屋建筑的地基基础工程和主体结构工程，为设计文件规定的该工程的合理使用年限；（二）屋面防水工程、有防水要求的卫生间、房间和外墙面的防渗漏，为5年；（三）供热与供冷系统，为2个采暖期、供冷期；（四）电气管线、给排水管道、设备安装和装修工程，为2年。\"保修期限自竣工合格验收之日起计算。房地产开发企业对购房人购买的商品房屋得保修责任从房屋交付之日起承担，保修期自房地产权利转移之日起，不得少于两年。",
					setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			String isNewVer = "1";//是否新合同版本
			if(cdvo.getContractversion() == null || "".equals(cdvo.getContractversion())){
				isNewVer = "0";
			}
			if("0".equals(isNewVer)){
				document
						.add(getParagraph(
								"3、房屋交付使用后，房屋的主体结构质量不合格的，按国务院发布的《城市房地产开发经营管理条例》第三十二条关于\"商品房交付使用后，购买人认为主体结构质量不合格的，可以向工程质量监督单位申请重新核验。经核验，确属主体结构质量不合格的，购买人有权退房；给购买人造成损失的，房地产开发企业应当依法承担赔偿责任。\"的规定处理。",
								setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("六、双方履行合同发生争议的，可选择向不动产所在地人民法院起诉，也可选择向仲裁委员会申请仲裁。如果选择申请仲裁的，可向本市的仲裁委员会申请，也可向外地的仲裁委员会申请，本市的仲裁委员会全称是青岛仲裁委员会。",
						setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(
						"七、商品房预售合同签订后应及时向房地产登记机构办理商品房预售合同登记备案，以便保护合同双方当事人权益。不登记备案的预售合同，当发生重复预售、抵押等事实时不能对抗第三人。根据规定，办理登记手续，可由合同当事人双方共同办理，也可由购房人一方单独办理。",
						setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			}else{
				document
						.add(getParagraph(
								"3、房屋交付使用后，房屋的主体结构质量不合格的，按国务院发布的《城市房地产开发经营管理条例》第三十二条关于\"商品房交付使用后，购买人认为主体结构质量不合格的，可以向工程质量监督单位申请重新核验。经核验，确属主体结构质量不合格的，购买人有权退房；给购买人造成损失的，房地产开发企业应当依法承担赔偿责任。\"的规定处理。",
								setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("六、双方履行合同发生争议的，可选择向不动产所在地人民法院起诉，也可选择向仲裁委员会申请仲裁。如果选择申请仲裁的，可向本市的仲裁委员会申请，也可向外地的仲裁委员会申请，本市的仲裁委员会全称是青岛仲裁委员会",
						setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("七、本合同第三十二条为必须选择条款。根据《中华人民共和国物权法》规定，预告登记后，未经预告登记权利人同意，处分该不动产的，不发生物权效力。为维护购房人合法权益，建议合同双方选择预告登记。", setChinese(FS14),
						SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("八、请乙方妥善保管签约时的合同密码。", setChinese(FS14), SPB28, 20.0f, 0.0f, Element.ALIGN_LEFT));
			}

			document.newPage();

			p = new Paragraph("青岛市商品房预售合同", setChinese(FS14, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB20);
			p.setSpacingAfter(SPB20);
			document.add(p);

			//设表的宽度为523f，一行37个字，一个字占2.7%
			document.add(getPTable(new String[] { "甲方(卖方)：", " *" + svo.getSellerName() }, 18, 82, 0, 0, 0, 0));
			document.add(getPTable(new String[] { "住所：", " *" + svo.getSellerAddress(), " 邮编：", " *" + svo.getSellerPostcode() }, 9, 61, 9, 21, 0, 0));
			document.add(getPTable(new String[] { "营业执照号码：", " *" + svo.getSellerBizcardcode() }, 21, 79, 0, 0, 0, 0));
			document.add(getPTable(new String[] { "资质证书号码：", " *" + svo.getSellerCertcode() }, 21, 79, 0, 0, 0, 0));
			document.add(getPTable(new String[] { "法定代表人： ", " *" + svo.getSellerDelegate(), " 联系电话：", " *" + svo.getSellerDlgCall() }, 18, 42, 15, 25, 0, 0));
			document.add(getPTable(new String[] { "委托代理人：", " *" + svo.getSellerProxy(), " 联系电话：", " *" + svo.getSellerProxyCall() }, 18, 42, 15, 25, 0, 0));

			//查询合同乙方信息
			List<BuyerInfoVO> list2 = cqDao.searchBuyerInfo(String.valueOf(contractID));
			String buyername = "";
			if(list2 != null && list2.size() > 0){
				for(BuyerInfoVO bvo : list2){
					p = new Paragraph();
					p.setSpacingBefore(20);
					p.setSpacingAfter(20);
					document.add(p);
					buyername += bvo.getBuyerName();
					document.add(getPTable(new String[] { "乙方(买方)：", "*" + bvo.getBuyerName() }, 18, 82, 0, 0, 0, 0));
					document.add(getPTable(new String[] { "国籍：", "*" + dBo.getDictName("ct_504", String.valueOf(bvo.getBuyerNationality())), "  居住(注册)所在省市：",
							" *" + dBo.getDictName("ct_524", String.valueOf(bvo.getBuyerProvince())) }, 9, 30, 30, 31, 0, 0));

					String buyerBirth = DateUtil.parseDateTime3(bvo.getBuyerBirth());
					document.add(getPTable(new String[] { "个人/公司", "*" + baseHandle(bvo.getAttribute("buyer_type_dict_name")), " 性别：",
							" *" + baseHandle(bvo.getAttribute("buyer_sex_dict_name")), " 出生年月日： ", " *" + baseHandle(buyerBirth) }, 15, 15, 9, 9, 18, 34));

					document.add(getPTable(new String[] { "住址：", "*" + baseHandle(bvo.getBuyerAddress()), " 邮编：", " *" + baseHandle(bvo.getBuyerPostcode()) },
							9, 67, 9, 15, 0, 0));
					document.add(getPTable(new String[] { "证件名称：", "*" + dBo.getDictName("ct_506", String.valueOf(bvo.getBuyerCardname())), " 号码：",
							" *" + baseHandle(bvo.getBuyerCardcode()) }, 15, 40, 9, 36, 0, 0));
					document.add(getPTable(new String[] { "联系电话：", "*" + bvo.getBuyerCall() }, 15, 85, 0, 0, 0, 0));
					document.add(getPTable(new String[] { "委托/法定代理人：", "*" + baseHandle(bvo.getBuyerProxy()), " 联系电话：",
							"  *" + baseHandle(bvo.getBuyerProxyCall()) }, 27, 33, 15, 25, 0, 0));
					document.add(getPTable(new String[] { "住址：", "*" + baseHandle(bvo.getBuyerProxyAdr()) }, 12, 88, 0, 0, 0, 0));
				}
			}

			document.newPage();

			String presellN7_5 = null, presellN10_5 = null, presellN12_4 = null, presellFJ3 = null, presellFJ5 = null, presellBC = null;
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("contractid", "=", contractID));
			List<AttachVO> atVOlList = cqDao.searchByAttibutes(AttachVO.class, params, new ArrayList<MetaOrder>(), new Page());
			if(atVOlList != null && atVOlList.size() > 0){
				for(AttachVO attachVO : atVOlList){
					if(attachVO.getContent() != null){
						String content = new String(attachVO.getContent());
						if(attachVO.getSerial() == 1075){
							presellN7_5 = content;
						}else if(attachVO.getSerial() == 1105){
							presellN10_5 = content;
						}else if(attachVO.getSerial() == 1124){
							presellN12_4 = content;
						}else if(attachVO.getSerial() == 1003){
							presellFJ3 = content;
						}else if(attachVO.getSerial() == 1005){
							presellFJ5 = content;
						}else if(attachVO.getSerial() == 1901){
							presellBC = content;
						}
					}

				}
			}

			document.add(getParagraph("甲、乙双方在平等、自愿、协商一致的基础上，就乙方购买甲方预售的《 " + " *" + pcVo.getProjectName() + " 》商品房事宜，订立本合同。"));
			document.add(getParagraph("^第一条    甲方通过土地使用权       " + " *" + pcVo.getAttribute("landattorntype_dict_name") + "    方式取得          " + " *"
					+ pcVo.getN1_1() + "       区/市        " + " *" + pcVo.getN1_2() + "     地块土地使用权，并依法进行了土地使用权登记取得房地产权证，证书号为：     " + " *" + pcVo.getN1_3()
					+ "    土地面积为：      " + " *" + pcVo.getN1_4() + "   平方米，土地用途为：    " + " *" + pcVo.getN1_5() + "  。"));
			document.add(getParagraph("甲方经批准，在该地块上投资建造《    " + " *" + pcVo.getN1_6() + "   》（暂定名/现定名）商品房，主体建筑物的建筑结构为    " + " *" + pcVo.getN1_7()
					+ "   结构；建筑物地上层数为    " + " *" + pcVo.getN1_8() + "   层，地下层数为    " + " *" + pcVo.getN1_9() + "  层。"));
			document.add(getParagraph("上述商品房已具备规定的预售条件，    " + " *" + pcVo.getN1_10() + "  已批准上市预售（预售许可证编号：    " + " *" + pcVo.getN1_11() + "  ）。"));
			document.add(getParagraph("^第二条   乙方向甲方购买    " + " *" + pcVo.getN1_1() + "  区/市    " + " *" + pcVo.getN2_1() + "  路     " + " *"
					+ pcVo.getLaneName() + "  号《     " + " *" + pcVo.getN2_3() + "  》     " + " *" + pcVo.getSubLane() + "   栋     " + " *" + pcVo.getN2_4()
					+ "  单元     " + " *" + pcVo.getN2_5() + "  层     " + " *" + pcVo.getN2_6() + "  户（以下简称该房屋），政府批准的规划用途为     " + " *" + pcVo.getN2_7()
					+ "   。"));
			document
.add(getParagraph("据甲方暂测该房屋建筑面积为    " + " *" + pcVo.getN2_8() + "  平方米，其中套内建筑面积为    " + " *" + pcVo.getN2_9() + "  平方米、公用分摊建筑面积为    "
					+ " *" + pcVo.getN2_10() + "  平方米，另有地下附属面积    " + " *" + pcVo.getCellarArea() + "  平方米（价格详见补充条款）。该房屋建筑层高为     " + " *" + pcVo.getN2_11()
					+ "  米。"));
			document
					.add(getParagraph("该房屋建筑设计及平面图见本合同附件二；该房屋建筑结构、装修及设备标准见本合同附件三；该房屋相关情况说明（抵押关系、资料关系）见本合同附件四，（相邻关系及小区平面布局）见本合同附件六；该房屋《前期物业管理服务合同》、《使用公约》或有关承诺书见本合同附件五。"));
			document.add(getParagraph("^第三条  乙方购买该房屋，每平方米房屋建筑面积单价（不包含房屋全装修价格）为人民币  " + "  *￥  " + " *" + StringUtil.getDoubleStr(pcVo.getN3_1()) + " 元。"));
			document.add(getPTable(new String[] { "（大写）：  ", " *" + pcVo.getN3_2(), " 。" }, 15, 60, 25, 0, 0, 0));
			document.add(getParagraph("根据甲方暂测的房屋建筑面积，乙方购买该房屋的总房价款（不包含房屋全装修价格）暂定为人民币  " + "  *￥  " + " *" + StringUtil.getDoubleStr(pcVo.getN3_3()) + " 元。"));
			document.add(getPTable(new String[] { "（大写）：  ", " *" + pcVo.getN3_4(), " 。" }, 15, 60, 25, 0, 0, 0));
//			document.add(getParagraph("该房屋全装修总价为人民币  " + "  *￥  " + " *" + StringUtil.getDoubleStr(pcVo.getFitmentPrice()) + " 元。 "));
			if(pcVo.getFitmentPrice() == 0){
				document.add(getParagraph("该房屋全装修总价为人民币  " + "  *  " + " *"  + " 元。 "));
			}else {
				document.add(getParagraph("该房屋全装修总价为人民币  " + "  *￥  " + " *" + StringUtil.getDoubleStr(pcVo.getFitmentPrice()) + " 元。 "));
			}
			document.add(getPTable(new String[] { "（大写）：", "*" + pcVo.getFitmentPriceCn(), "。" }, 15, 60, 25, 0, 0, 0));
			document.add(getParagraph("^第四条  乙方购买该房屋的总房价款（含附件三中装修、设备价格）是指该房屋和相应比例的土地使用权的总价格。本合同约定的总房价款除该房屋建筑面积的暂测与实测不一致的原因外，不再作变动。"));

			if("0".equals(isNewVer)){
				document.add(getParagraph("^第五条  在该房屋交付时，房屋建筑面积以   " + " *" + pcVo.getChargeDep() + "  认定的测绘机构实测面积为准，如甲方暂测面积与实测面积不一致时，除法律、法规、规章另有规定外按下列约定处理："));
				document.add(getParagraph("1、按该房屋每平方米建筑面积单价计算多退少补；", setChinese(FS14), FL28, LD21, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2、甲方同意当暂测面积与实测面积的误差超过+  " + " *" + pcVo.getN5_1() + " %（包括  " + " *" + pcVo.getN5_2()
						+ " %），不向乙方收取超过部分的房价款；甲方同意当暂测面积与实测面积的误差超过－  " + " *" + pcVo.getN5_3() + " % （包括－  " + " *" + pcVo.getN5_4()
						+ " %），乙方有权单方面解除本合同。乙方行使单方解除权时，必须在双方签署《房屋交接书》之时或之前提出，否则视为放弃该项权利。", setChinese(FS14), FL28, LD21, 0.0f, Element.ALIGN_LEFT));
			}else{
				document.add(getParagraph("^第五条  在该房屋交付时，房屋建筑面积以   " + " *" + pcVo.getChargeDep() + "   认定的测绘机构实测面积为准，如甲方暂测面积与实测面积不一致时，除法律、法规、规章另有规定外按下列第 "
						+ " *" + dicBo.getDictName("ct_509", String.valueOf(pcVo.getN5_6())) + "  项约定处理："));
				document.add(getParagraph("壹、按该房屋每平方米建筑面积单价计算多退少补。", setChinese(FS14), FL28, LD21, 0.0f, Element.ALIGN_LEFT));
				document
						.add(getParagraph(
"贰、面积误差比在 " + " *" + getDoubleStr(pcVo.getN5_5()) + "  %以内的，据实结算房价款，甲方同意当暂测面积与实测面积的误差超过+" + " *" + getDoubleStr(pcVo.getN5_1()) + " %（包括  "
						+ " *" + getDoubleStr(pcVo.getN5_2()) + "  %），不向乙方收取超过部分的房价款；甲方同意当暂测面积与实测面积的误差超过－  " + " *" + getDoubleStr(pcVo.getN5_3()) + " % （包括－ " + " *" + getDoubleStr(pcVo.getN5_4())
						+ " %），乙方有权单方面解除本合同。乙方行使单方解除权时，必须在双方签署《房屋交接书》之时或之前提出，否则视为放弃该项权利。",
								setChinese(FS14), FL28, LD21, 0.0f, Element.ALIGN_LEFT));

			}
			document.add(getParagraph("^第六条  签订本合同时，该房屋建设工程建设到  " + " *" + pcVo.getN6_1() + " 。乙方应当按本合同约定时间如期足额将房价款解入甲方的预售款监管帐户（预售款监管机构：  " + " *"
					+ pcVo.getN6_2() + " 、帐户名称  " + " *" + pcVo.getN6_3() + " 、帐号：  " + " *" + pcVo.getN6_4() + " ）。预售款按政府规定监管使用。"));
			document.add(getParagraph("乙方的付款方式和付款期限由甲乙双方在附件一中约定明确。"));
			document.add(getParagraph("^第七条   乙方若未按本合同约定的时间付款，应当向甲方支付违约金，违约金按逾期未付款额的日万分之  " + " *" + pcVo.getN7_1() + " 计算，违约金自本合同的应付款期限之第二天起算至实际付款之日止，逾期超过  "
					+ " *" + pcVo.getN7_2() + " 天后， 甲方有权选择下列第  " + " *" + getRes(pcVo.getN7_3(), 1) + " 种方案追究乙方责任："));
			document.add(getParagraph("壹、甲方有权单方面解除本合同，乙方应当承担赔偿责任。赔偿金额为总房价款的  " + " *" + getDoubleStr(pcVo.getN7_4())
					+ " %，甲方有权在乙方已支付的房价款中扣除乙方应支付的赔偿金额，剩余房款退还给乙方。如乙方已支付的房价款不足赔偿的，甲方有权追索。"));
			document.add(getParagraph("甲方如行使解除合同权的，应当书面通知乙方。"));
			document.add(getParagraph("贰、  " + " *" + presellN7_5));
			document.add(getParagraph("^第八条   签订本合同后，甲方不得擅自变更该房屋的建筑设计（见附件二），确需变更的应当征得乙方书面同意并报规划管理部门审核批准，在获得批准之日起  " + " *" + pcVo.getN8_1()
					+ " 天内与乙方签订本合同变更协议。"));
			document.add(getParagraph("甲方未征得乙方同意擅自变更该房屋的建筑设计，乙方有权单方面解除本合同。", setChinese(FS14), FL28, LD21, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("^第九条     甲方不得擅自变更已经与乙方约定的小区平面布局（见附件六），确需变更的应当征得乙方书面同意。"));
			document.add(getParagraph("甲方未征得乙方同意变更小区的平面布局，乙方有权要求甲方恢复，如不能恢复的，甲方应当向乙方支付总房价款的    " + " *" + pcVo.getN9_1() + " %违约金。", setChinese(FS14), FL28,
					LD21, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("^第十条   该房屋的交付必须符合下列第    " + " *" + getRes(pcVo.getN10_1(), 1) + "  种方案所列条件："));
			if("0".equals(isNewVer)){
				document.add(getParagraph("壹、取得了房地产权属登记证明；甲方对该房屋设定的抵押已注销；甲方已按规定缴纳了物业维修基金。"));
			}else{
				document.add(getParagraph("壹、办理了房屋所有权初始登记；甲方对该房屋设定的抵押已注销；甲方已按规定缴纳了物业维修基金。"));
			}

			document.add(getParagraph("贰、"));
			document.add(getParagraph(" *" + presellN10_5));
			document.add(getParagraph("^第十一条   甲方定于    " + " *" + pcVo.getN11_1() + "  年    " + " *" + pcVo.getN11_2() + "  月    " + " *" + pcVo.getN11_3()
					+ "  日前将该房屋交付给乙方，除不可抗力外。"));
			document.add(getParagraph("^第十二条    甲方如未在本合同第十一条约定期限内将该房屋交付乙方，应当向乙方支付违约金，违约金按乙方已支付的房价款日万分之  " + " *" + pcVo.getN12_1()
					+ " 计算，违约金自本合同第十一条约定的最后交付期限之第二天起算至实际交付之日止。逾期超过  " + " *" + pcVo.getN12_2() + " 天，乙方有权选择下列第  " + " *" + getRes(pcVo.getN12_3(), 1)
					+ " 种方案追究甲方责任："));
			document.add(getParagraph("壹、乙方有权单方面解除本合同。"));
			document.add(getParagraph("贰、"));
			document.add(getParagraph(" *" + presellN12_4));
			document.add(getParagraph("^第十三条    该房屋符合本合同第十条约定的交付条件后，甲方应在交付之日前  " + " *" + pcVo.getN13_1() + " 天书面通知乙方办理交付该房屋的手续，乙方应在收到该通知之日起  " + " *"
					+ pcVo.getN13_2() + " 天内，会同甲方对该房屋进行验收交接。房屋交付的标志为  " + " *" + pcVo.getN13_3() + " 。"));
			document.add(getParagraph("在验收交接时，甲方应出示符合本合同第十条约定的房屋交付条件的证明文件，因该房屋用途为  " + " *" + pcVo.getN13_4() + " 用房，甲方应向乙方提供《  " + " *" + pcVo.getN13_5()
					+ " 质量保证书》和《  " + " *" + pcVo.getN13_6() + " 使用说明书》。同时，甲方应当根据乙方要求提供实测面积的有关资料。"));
			document.add(getParagraph("甲方如不出示和不提供前款规定的材料，乙方有权拒绝接受该房屋，由此而产生的延期交房的责任由甲方承担。"));
			document.add(getParagraph("^第十四条    在甲方取得了房地产权属登记证明后  " + " *" + pcVo.getN14_1() + " 日内，由甲、乙双方签署本合同规定的《房屋交接书》。《房屋交接书》作为办理该房屋过户手续的必备文件。"));
			document
.add(getParagraph("甲、乙双方在签署《房屋交接书》之日起  " + " *" + pcVo.getN14_2() + " 天内，由双方依法向  " + " *"
					+ dicBo.getDictName("ct_510", String.valueOf(pcVo.getN14_3())) + " 办理价格申报、过户申请手续、申领该房屋的房地产权证。"));
			document
					.add(getParagraph("^第十五条    该房屋的风险责任自该房屋交付之日起由甲方转移给乙方。如乙方未按约定的日期办理该房屋的验收交接手续，甲方应当发出书面催告书一次。乙方未按催告书规定的日期办理该房屋的验收交接手续的，则自催告书约定的验收交接日之第二日起该房屋的风险责任转移由乙方承担。"));
			document.add(getParagraph("^第十六条      甲方保证在向乙方交付该房屋时该房屋没有甲方设定的抵押权，也不存在其它产权纠纷和财务纠纷。如房屋交付后出现与甲方保证不相一致的情况，由甲方承担全部责任。"));
			document.add(getParagraph("^第十七条   甲方交付的该房屋系验收合格的房屋。如该房屋的装修、设备标准达不到本合同附件三约定的标准，乙方有权要求甲方按实际的装修、设备与约定的装修、设备差价  " + " *" + pcVo.getN17_1()
					+ " 倍给予补偿。如主体结构不符合本合同附件三约定的标准，乙方有权单方面解除本合同。"));
			document.add(getParagraph("双方商定对标准的认定产生争议时，委托本市有资质的建设工程质量检测机构检测，并以该机构出具的书面鉴定意见为处理争议的依据。"));
			document.add(getParagraph("^第十八条    该房屋交付后，乙方认为主体结构不合格的，可以委托本市有资质的建筑工程质量检测机构检测。经核验，确属主体结构质量不合格的，乙方有权单方面解除本合同。"));
			document.add(getParagraph("^第十九条     乙方行使本合同条款中约定的单方面解除本合同权利时，应书面通知甲方，甲方应当在收到乙方的书面通知起  " + " *" + pcVo.getN19_1()
					+ " 天内将乙方已支付的房价款（包括利息，利息按中国人民银行公布的同期存款利率计算）全部退还乙方，并承担赔偿责任，赔偿金额为总房价款的  " + " *" + pcVo.getN19_2() + " %，在退还房价款时一并支付给乙方。"));
			document.add(getParagraph("前款及本合同其他条款所称已支付的房价款，是包括乙方直接支付的和通过贷款方式支付的房价款。"));
			document.add(getParagraph("^第二十条    按本合同约定，甲、乙双方单方解除合同的，在单方解除合同以前，对方已按合同约定支付违约金的，支付的违约金金额应在按本合同约定的赔偿金额中扣除。"));
			if("0".equals(isNewVer)){
				document.add(getParagraph("^第二十一条   甲方或乙方对相对方行使单方面解除本合同有异议的，应在接到对方有关单方面解除本合同的书面通知之日起  " + " *" + pcVo.getN21_1()
						+ " 天内，向按第三十二条选定的解决争议机关确认解除合同的效力。"));
			}else{
				document.add(getParagraph("^第二十一条   甲方或乙方对相对方行使单方面解除本合同有异议的，应在接到对方有关单方面解除本合同的书面通知之日起  " + " *" + pcVo.getN21_1()
						+ " 天内，向按第三十三条选定的解决争议机关确认解除合同的效力。"));
			}
			document.add(getParagraph("^第二十二条    甲方交付该房屋有其他工程质量问题的，乙方在保修期内有权要求甲方除免费修复外，还须按照修复费的  " + " *" + pcVo.getN22_1() + " 倍给予补偿。"));
			document.add(getParagraph("双方商定对该房屋其他工程质量问题有争议的，委托本市有资质的建设工程质量检测机构检测，并以该机构出具的书面鉴定意见为处理争议的依据。"));
			document.add(getParagraph("^第二十三条     自该房屋验收交接之日起，甲方对该房屋负责保修。保修范围和保修期由甲乙双方依照国务院发布的《建筑工程质量管理条例》的规定在本合同附件五中约定。"));
			document.add(getParagraph("^第二十四条     甲方已选聘  " + " *" + pcVo.getN24_1() + " 物业公司对该房屋进行前期物业管理，并与其签订了《前期物业管理服务合同》（见附件五）。因该房屋规划用途为  " + " *"
					+ pcVo.getN24_2() + " 用房，甲乙双方已签订了《  " + " *" + pcVo.getN24_3() + " 使用公约》（见附件五）。"));
			document.add(getParagraph("^第二十五条      乙方购买的房屋及其相应占有的土地使用权不可分离。自该房屋的房地产权利转移之日起，甲方与  " + " *" + pcVo.getN25_1() + " 签订的土地使用权  " + " *"
					+ dicBo.getDictName("ct_507", String.valueOf(pcVo.getLandpactType())) + " 合同中约定的权利、义务和责任转移给乙方。"));
			document
					.add(getParagraph("^第二十六条      本合同一方按照本合同约定向另一方送达的任何文件、回复及其他任何联系，必须用书面形式，且采用挂号邮寄或直接送达的方式， 送达本合同所列另一方的地址或另一方以本条所述方式通知更改后的地址。如以挂号邮寄的方式，在投邮后（以寄出的邮戳为准）第  "
							+ " *" + pcVo.getN27_1() + " 日将被视为已送达另一方，如以直接送达的方式送达，则于另一方签收时视为已送达。"));
			document.add(getParagraph("^第二十七条      该房屋买卖过程中所发生的税费按有关规定由甲、乙双方各自承担。"));
			document.add(getParagraph("^第二十八条      本合同的补充条款、附件及补充协议均为本合同不可分割的部分。本合同补充条款、补充协议与正文条款不相一致的，以补充条款、补充协议为准。"));
			document.add(getParagraph("本合同的未尽事宜及本合同在履行过程中需变更的事宜，双方应通过订立变更协议进行约定。", setChinese(FS14), FL28, LD21, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("^第二十九条     甲、乙双方在签署本合同时，对各自的权利和义务清楚明白，并愿按本合同约定严格履行， 如一方违反本合同，另一方有权按本合同约定要求索赔。"));
			if("0".equals(isNewVer)){
				document.add(getParagraph("^第三十条        本合同自双方签字/  " + " *" + pcVo.getN31_1() + " 公证处公证之日起生效。双方商定本合同生效之日起  " + " *" + pcVo.getN31_2()
						+ " 日内由  " + " *" + pcVo.getAttribute("regtype_dict_name") + " 负责向房地产登记机构办理本合同登记备案手续。"));
				document.add(getParagraph("若责任方逾期不办理登记备案手续造成另一方损失的，应当承担赔偿责任。"));
			}else{
				document.add(getParagraph("^第三十条        本合同自双方签字/  " + " *" + pcVo.getN31_1() + " 公证处公证之日起生效。"));
				document.add(getParagraph("本合同一经甲、乙双方网上签约，即视为甲、乙双方向房地产登记机构申请办理合同登记备案。"));
			}

			document.add(getParagraph("^第三十一条     本合同登记备案后，如发生协议解除本合同的事实时，在事实发生之日起30天内双方持解除合同的书面文件到  " + " *"
					+ dicBo.getDictName("ct_510", String.valueOf(pcVo.getN32_1())) + " 办理注销本合同登记备案的手续。"));
			if("0".equals(isNewVer)){
				document.add(getParagraph("甲方或乙方依据本合同有关条款的约定单方解除本合同的，甲方或乙方应凭单方面解除合同的书面通知的送达凭据单方面到房地产登记机构办理注销本合同登记备案的手续。"));
				document.add(getParagraph("^第三十二条      甲、乙双方在履行本合同过程中发生争议，应协商解决。协商不能解决的，选定下列第  " + " *" + getRes(pcVo.getN33_1(), 1) + " 种方式解决："));
				document.add(getParagraph("壹、向  " + " *" + pcVo.getN33_2() + " 仲裁委员会申请仲裁；", setChinese(FS14), FL28, LD21, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("贰、依法向人民法院起诉。", setChinese(FS14), FL28, LD21, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("^第三十三条      本合同壹式  " + " *" + pcVo.getN34_1() + " 份，均具有同等效力。其中甲、乙双方各执  " + " *" + pcVo.getN34_2() + " 份，  " + " *"
						+ pcVo.getN34_3() + " 、  " + " *" + pcVo.getN34_4() + " 、  " + " *" + pcVo.getN34_5() + " 各执壹份"));
			}else{
				document
.add(getParagraph("^第三十二条    甲、乙双方选择  " + " *" + dicBo.getDictName("ct_509", String.valueOf(pcVo.getN32_2())) + " 自本合同生效之日起  " + " *" + pcVo.getN31_2()
						+ " 日内向房地产登记机构申请预购商品房预告登记。"));
				document.add(getParagraph("壹、同意。", setChinese(FS14), FL28, LD21, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("贰、不同意。", setChinese(FS14), FL28, LD21, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("^第三十三条     甲、乙双方在履行本合同过程中发生争议，应协商解决。协商不能解决的，选定下列第  " + " *" + getRes(pcVo.getN33_1(),1) + " 种方式解决："));
				document.add(getParagraph("壹、向 " + " *" + pcVo.getN33_2() + " 仲裁委员会申请仲裁；", setChinese(FS14), FL28, LD21, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("贰、依法向人民法院起诉。", setChinese(FS14), FL28, LD21, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("^第三十四条    本合同壹式  " + " *" + pcVo.getN34_1() + " 份，均具有同等效力。其中甲、乙双方各执  " + " *" + pcVo.getN34_2() + " 份，  " + " *"
						+ pcVo.getN34_3() + " 、  " + " *" + pcVo.getN34_4() + " 、  " + " *" + pcVo.getN34_5() + " 各执壹份"));
			}


			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  一", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			document.add(getParagraph("付款方式和付款期限", setChinese(FS14), 0.0f, 20.0f, 0.0f, Element.ALIGN_CENTER));
			p = new Paragraph("-------------------------------------------------------");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(5);
			p.setSpacingAfter(SPB28);
			document.add(p);

			//查询是否新版附件一
			List<MetaFilter> params10 = new ArrayList<MetaFilter>();
			params10.add(new MetaFilter("contractID", "=", contractID));
			List<PresellAttach2MoneyRecordVO> ptmr = this.search(PresellAttach2MoneyRecordVO.class, params10);

			List<MetaFilter> paramsa = new ArrayList<MetaFilter>();
			paramsa.add(new MetaFilter("contractID", "=", contractID));
			List<PresellAttach2MoneyVO> a1mVOList = this.search(PresellAttach2MoneyVO.class, paramsa);
			String str1 = "不贷款 ";
			if(ptmr != null && ptmr.size() > 0){
				if(a1mVOList != null && a1mVOList.size() > 0){
					//补充条款
					String contentText = "";
					for(PresellAttach2MoneyVO a1mVO : a1mVOList){
						if(a1mVO.getContentText() != null && !"".equals(a1mVO.getContentText())){
							contentText = a1mVO.getContentText();
						}
						if(a1mVO.getState() == 1){
							document.add(getParagraph("付款方式：    " + " *" + a1mVO.getPaymentMode() + "    贷款方式：" + " *" + str1 + " 。", setChinese(FS10), 0.0f,
									20.0f, 0.0f, Element.ALIGN_LEFT));
							document.add(getParagraph("（一）基本条款：1、乙方按以下方式向甲方支付本合同第三条约定的合同总价款     " + " *" + a1mVO.getTotalMoney() + "  元。", setChinese(FS10),
									0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							document.add(getParagraph(" 乙方向甲方于   " + substringDate(String.valueOf(a1mVO.getFkDate()), 1) + " 年 "
									+ substringDate(String.valueOf(a1mVO.getFkDate()), 2) + " 月 " + substringDate(String.valueOf(a1mVO.getFkDate()), 3)
									+ " 日前一次性支付总价款 " + " *" + a1mVO.getMoney() + " 元（含定金    " + "  *" + a1mVO.getDeposit() + " 元），存入本合同指定的账户。 ",
									setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
						}
						if(a1mVO.getState() == 2 && a1mVO.getStateSign() == 0){
							document.add(getParagraph("付款方式：    " + "  *" + a1mVO.getPaymentMode() + "    贷款方式：    " + " *" + a1mVO.getBorrowMode() + " 。",
									setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							document.add(getParagraph("（一）基本条款：1、乙方按以下方式向甲方支付本合同第三条约定的合同总价款     " + " *" + a1mVO.getTotalMoney() + "  元。", setChinese(FS10),
									0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							document.add(getParagraph("首付款：    " + "  *" + a1mVO.getMoney() + " 元（含定金    " + "  *" + a1mVO.getDeposit() + " 元），乙方应与 "
									+ substringDate(String.valueOf(a1mVO.getFkDate()), 1) + " 年 " + substringDate(String.valueOf(a1mVO.getFkDate()), 2) + " 月 "
									+ substringDate(String.valueOf(a1mVO.getFkDate()), 3) + " 日前存入本合同指定的账户。", setChinese(FS10), 0.0f, 20.0f, 0.0f,
									Element.ALIGN_LEFT));
							List<MetaFilter> params1 = new ArrayList<MetaFilter>();

							params1.add(new MetaFilter("contractID", "=", contractID));
							params1.add(new MetaFilter("state", "=", 2));
							params1.add(new MetaFilter("stateSign", "!=", 0));
							List<PresellAttach2MoneyVO> list3 = this.search(PresellAttach2MoneyVO.class, params1);
							//返回只有一条记录
							if(list3.size() == 1){
								document.add(getParagraph("剩余房价款:    " + " *" + list3.get(0).getMoney() + "  元，乙方应与  "
										+ substringDate(String.valueOf(list3.get(0).getFkDate()), 1) + " 年 "
										+ substringDate(String.valueOf(list3.get(0).getFkDate()), 2) + " 月 "
										+ substringDate(String.valueOf(list3.get(0).getFkDate()), 3) + " 日前存入本合同指定的账户。", setChinese(FS10), 0.0f, 20.0f, 0.0f,
										Element.ALIGN_LEFT));

							}else{
								for(int i = 0; i < list3.size(); i++){
									document.add(getParagraph("首付款" + " *" + (i + 1) + " 期：  " + " *" + list3.get(i).getMoney() + "  元，乙方应与  "
											+ substringDate(String.valueOf(list3.get(i).getFkDate()), 1) + " 年 "
											+ substringDate(String.valueOf(list3.get(i).getFkDate()), 2) + " 月 "
											+ substringDate(String.valueOf(list3.get(i).getFkDate()), 3) + " 日前存入本合同指定的账户。", setChinese(FS10), 0.0f, 20.0f,
											0.0f, Element.ALIGN_LEFT));

								}

							}
						}
						if(a1mVO.getState() == 3 && a1mVO.getStateSign() == 0){
							document.add(getParagraph("付款方式：    " + "  *" + a1mVO.getPaymentMode() + "    贷款方式：    " + " *" + a1mVO.getBorrowMode() + " 。",
									setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							document.add(getParagraph("（一）基本条款：1、乙方按以下方式向甲方支付本合同第三条约定的合同总价款     " + " *" + a1mVO.getTotalMoney() + "  元。", setChinese(FS10),
									0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							document.add(getParagraph("首付款：    " + "  *" + a1mVO.getMoney() + " 元（含定金    " + " *" + a1mVO.getDeposit() + " 元），乙方应与 "
									+ substringDate(String.valueOf(a1mVO.getFkDate()), 1) + " 年 " + substringDate(String.valueOf(a1mVO.getFkDate()), 2) + " 月 "
									+ substringDate(String.valueOf(a1mVO.getFkDate()), 3) + " 日前存入本合同指定的账户。", setChinese(FS10), 0.0f, 20.0f, 0.0f,
									Element.ALIGN_LEFT));
							List<MetaFilter> params2 = new ArrayList<MetaFilter>();

							params2.add(new MetaFilter("contractID", "=", contractID));
							params2.add(new MetaFilter("state", "=", 3));
							params2.add(new MetaFilter("stateSign", "!=", 0));
							List<PresellAttach2MoneyVO> list3 = this.search(PresellAttach2MoneyVO.class, params2);
							//返回只有一条记录
							if(list3.size() == 1){
								document.add(getParagraph("剩余房价款:    " + "  *" + list3.get(0).getMoney() + "  元，乙方应与  "
										+ substringDate(String.valueOf(list3.get(0).getFkDate()), 1) + " 年 "
										+ substringDate(String.valueOf(list3.get(0).getFkDate()), 2) + " 月 "
										+ substringDate(String.valueOf(list3.get(0).getFkDate()), 3) + " 日前存入本合同指定的账户。", setChinese(FS10), 0.0f, 20.0f, 0.0f,
										Element.ALIGN_LEFT));
							}else{
								for(int i = 0; i < list3.size(); i++){
									document.add(getParagraph("首付款" + " *" + (i + 1) + " 期：  " + " *" + list3.get(i).getMoney() + "  元，乙方应与  "
											+ substringDate(String.valueOf(list3.get(i).getFkDate()), 1) + " 年 "
											+ substringDate(String.valueOf(list3.get(i).getFkDate()), 2) + " 月 "
											+ substringDate(String.valueOf(list3.get(i).getFkDate()), 3) + " 日前存入本合同指定的账户。", setChinese(FS10), 0.0f, 20.0f,
											0.0f, Element.ALIGN_LEFT));
								}
							}

						}
						if(a1mVO.getState() == 4){
							document.add(getParagraph("贷款：    " + "  *" + a1mVO.getMoney() + " 元，乙方以 " + " *" + a1mVO.getBorrowMode() + " 方式应于 "
									+ substringDate(String.valueOf(a1mVO.getFkDate()), 1) + " 年 " + substringDate(String.valueOf(a1mVO.getFkDate()), 2) + " 月 "
									+ substringDate(String.valueOf(a1mVO.getFkDate()), 3) + " 日前将办理按揭贷款所需文件全部提交 " + " *" + a1mVO.getBankName() + " ，放贷机构于 "
									+ substringDate(String.valueOf(a1mVO.getDkDate()), 1) + " 年 " + substringDate(String.valueOf(a1mVO.getDkDate()), 2) + " 月 "
									+ substringDate(String.valueOf(a1mVO.getDkDate()), 3) + " 日前将贷款存入本合同指定的账户。 ", setChinese(FS10), 0.0f, 20.0f, 0.0f,
									Element.ALIGN_LEFT));

						}
					}
					document.add(getParagraph("（二）双方在遵守以上约定的基础上，作出以下补充条款：    " + " *" + contentText, setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
				}
			}else{
				//老合同判断
				PresellDepositVO attach3vo = (PresellDepositVO) cqDao.find(PresellDepositVO.class, contractID);
				if(attach3vo != null){
					List<MetaFilter> params3 = new ArrayList<MetaFilter>();
					params3.add(new MetaFilter("contractID", "=", contractID));
					List<Attach1VO> attach4vo = this.search(Attach1VO.class, params3);
					if(attach4vo != null && attach4vo.size() > 0){
						for(Attach1VO att4VO : attach4vo){
							String at1Content = att4VO.getContent() == null ? "" : new String(att4VO.getContent());
							if(att4VO.getPaymentType() == 1){
								document.add(getParagraph("按条件付款   ", setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
								document.add(getParagraph("条款如下按条件付款：    " + "  *" + at1Content, setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							}
							if(att4VO.getPaymentType() == 0){
								String paymentMode = this.getDictName("CT_502_1", String.valueOf(att4VO.getPaymentMode()));
								String borrowMode = "";
								//按揭贷款支付
								if(att4VO.getPaymentMode() == 3){
									borrowMode = this.getDictName("CT_503_1", String.valueOf(att4VO.getBorrowMode()));
								}
								document.add(getParagraph("按时间付款   ", setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
								document.add(getParagraph("付款方式： " + " *" + paymentMode + "  贷款方式： " + " *" + borrowMode, setChinese(FS10), 0.0f, 20.0f, 0.0f,
										Element.ALIGN_LEFT));
								document.add(getParagraph("1、乙方于  " + substringDate(String.valueOf(attach3vo.getPayDate()), 1) + " 年 "
										+ substringDate(String.valueOf(attach3vo.getPayDate()), 2) + " 月 "
										+ substringDate(String.valueOf(attach3vo.getPayDate()), 3) + " 日前付定金计人民币" + " *" + attach3vo.getDeposit() + " 元；（大写） "
										+ " *" + attach3vo.getDepositCn() + " 。", setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
								List<MetaFilter> params4 = new ArrayList<MetaFilter>();
								params4.add(new MetaFilter("contractID", "=", contractID));
								List<MetaOrder> orders = new ArrayList<MetaOrder>();
								orders.add(new MetaOrder("serial", "asc"));
								List<Attach1MoneyVO> atmyvo = pdfDAO.search(Attach1MoneyVO.class, params4, orders, null);
								if(attach4vo != null && attach4vo.size() > 0){
									for(Attach1MoneyVO Atmyvo : atmyvo){
										if(Atmyvo.getSerial() == 1){
											document.add(getParagraph("2、乙方于   " + substringDate(String.valueOf(Atmyvo.getPaymentDate()), 1) + " 年 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 2) + " 月 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 3) + " 日与甲方签约，并付首付房款计人民币 " + " *"
													+ Atmyvo.getMoney() + " 元；（大写） " + " *" + Atmyvo.getMoneyCn() + " 。", setChinese(FS10), 0.0f, 20.0f, 0.0f,
													Element.ALIGN_LEFT));

										}else{
											document.add(getParagraph("2、乙方于   " + substringDate(String.valueOf(Atmyvo.getPaymentDate()), 1) + " 年 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 2) + " 月 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 3) + " 日前应支付房款计人民币" + " *" + Atmyvo.getMoney()
													+ " 元；（大写） " + " *" + Atmyvo.getMoneyCn() + " 。", setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));

										}
									}
								}
								document.add(getParagraph("备注：    " + "  *" + at1Content, setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							}
						}
					}
				}else{
					List<MetaFilter> params5 = new ArrayList<MetaFilter>();
					params5.add(new MetaFilter("contractID", "=", contractID));
					List<Attach1VO> attach4vo = this.search(Attach1VO.class, params5);
					if(attach4vo != null && attach4vo.size() > 0){
						for(Attach1VO att4VO : attach4vo){
							String at1Content = att4VO.getContent() == null ? "" : new String(att4VO.getContent());
							if(att4VO.getPaymentType() == 1){
								document.add(getParagraph("按条件付款   ", setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
								document.add(getParagraph("条款如下按条件付款：    " + "  *" + at1Content, setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							}
							if(att4VO.getPaymentType() == 0){
								document.add(getParagraph("按时间付款   ", setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
								document.add(getParagraph("付款方式： " + " *" + att4VO.getAttribute("paymentMode_dict_name") + "  贷款方式： " + " *"
										+ att4VO.getAttribute("borrowMode_dict_name"), setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
								List<MetaFilter> params6 = new ArrayList<MetaFilter>();
								params6.add(new MetaFilter("contractID", "=", contractID));
								List<MetaOrder> orders = new ArrayList<MetaOrder>();
								orders.add(new MetaOrder("serial", "asc"));
								List<Attach1MoneyVO> atmyvo = pdfDAO.search(Attach1MoneyVO.class, params6, orders, null);
								if(attach4vo != null && attach4vo.size() > 0){
									for(Attach1MoneyVO Atmyvo : atmyvo){
										if(Atmyvo.getSerial() == 1){
											document.add(getParagraph("1、乙方于   " + substringDate(String.valueOf(Atmyvo.getPaymentDate()), 1) + " 年 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 2) + " 月 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 3) + " 日与甲方签约，并付首付房款计人民币 " + " *"
													+ Atmyvo.getMoney() + " 元；（大写） " + " *" + Atmyvo.getMoneyCn() + " 。", setChinese(FS10), 0.0f, 20.0f, 0.0f,
													Element.ALIGN_LEFT));

										}else{
											document.add(getParagraph("  1、乙方于   " + substringDate(String.valueOf(Atmyvo.getPaymentDate()), 1) + " 年 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 2) + " 月 "
													+ substringDate(String.valueOf(Atmyvo.getPaymentDate()), 3) + " 日前应支付房款计人民币" + " *" + Atmyvo.getMoney()
													+ " 元；（大写） " + " *" + Atmyvo.getMoneyCn() + " 。", setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));

										}
									}
								}
								document.add(getParagraph("备注：    " + "  *" + at1Content, setChinese(FS10), 0.0f, 20.0f, 0.0f, Element.ALIGN_LEFT));
							}
						}
					}
				}
			}
			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  二", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			document.add(getParagraph("该房屋建筑设计及平面图 ", setChinese(FS14), 0.0f, 20.0f, 0.0f, Element.ALIGN_CENTER));
			p = new Paragraph("-------------------------------------------------------");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			p.setSpacingAfter(SPB28);
			document.add(p);
			//System.out.println("合同[" + contractID + "]附件二处理开始###################！    ");
			HouseVO hvo = (HouseVO) cqDao.find(HouseVO.class, cdvo.getHouseID());
			if(hvo.getPre_Plan_ID() != null && !"".equals(hvo.getPre_Plan_ID())){
				//System.out.println("合同[" + contractID + "]附件二处理1111111！    " + hvo.getPre_Plan_ID());
				HousePlanVO hpvo = (HousePlanVO) cqDao.find(HousePlanVO.class, Long.parseLong(hvo.getPre_Plan_ID()));
				if(hpvo != null && hpvo.getPlan_Pdf() != null){
					//System.out.println("合同[" + contractID + "]附件二处理222222！    " + hvo.getPre_Plan_ID());
					//baos.write(hpvo.getPlan_Pdf());
					File file1 = new File(tempPdfPath); //创建临时存放pdf的文件夹
					if(!file1.exists()){
						file1.mkdirs();
					}
					File file2 = new File(tempImgPath); //创建临时存放图片的文件夹
					if(!file2.exists()){
						file2.mkdirs();
					}
					String demo = hvo.getPre_Plan_ID() + "-" + String.valueOf(System.currentTimeMillis());
					getFile(hpvo.getPlan_Pdf(), tempPdfPath, demo + ".pdf");
					//System.out.println("合同[" + contractID + "]附件二处理333333！    " + demo);
					boolean flag = pdf2Pic(tempPdfPath + demo + ".pdf", tempImgPath + demo);
					//System.out.println("合同[" + contractID + "]附件二处理444444！    " + demo);
					if(flag == true){
						Image image = Image.getInstance(tempImgPath + demo + ".png");
						image.scaleAbsolute(523f, 500f);
						image.setAlignment(Element.ALIGN_CENTER);
						document.add(image);
						//System.out.println("合同[" + contractID + "]附件二处理55555！    " + demo);
						File pdfFile = new File(tempPdfPath + demo + ".pdf");
						//pdfFile.deleteOnExit();

						File imageFile = new File(tempImgPath + demo + ".png");
						//imageFile.deleteOnExit();

						deleteFile(pdfFile);
						deleteFile(imageFile);
					}
				}
			}
			//System.out.println("合同[" + contractID + "]附件二处理结束***********！    ");

			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  三", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(FS14);
			document.add(p);
			document.add(getParagraph("该房屋建筑结构、装修及设备标准 ", setChinese(FS14), 0.0f, 20.0f, 0.0f, Element.ALIGN_CENTER));
			p = new Paragraph("-------------------------------------------------------");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			p.setSpacingAfter(SPB28);
			document.add(p);
			document.add(new Paragraph(presellFJ3, setChinese(FS14)));

			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  四", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(FS14);
			document.add(p);
			document.add(getParagraph("该房屋权属情况（产权、抵押、租赁） ", setChinese(FS14), 0.0f, 20.0f, 0.0f, Element.ALIGN_CENTER));
			p = new Paragraph("-------------------------------------------------------");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);

			p = new Paragraph();
			p.setSpacingBefore(SPB28 * 3);
			document.add(p);

			Attach4VO attach4vo = (Attach4VO) cqDao.find(Attach4VO.class, contractID);
			if(attach4vo != null){
				PdfPTable table = new PdfPTable(6);
				table.setTotalWidth(523);
				int[] width = { 10, 22, 12, 22, 12, 22 };
				table.setWidths(width);
				table.setLockedWidth(true);
				PdfPCell cell = new PdfPCell(new Paragraph("房 屋 土 地 状 况", setChinese(FS10, Font.BOLD)));
				cell.setColspan(6);
				cell.setHorizontalAlignment(1);
				cell.setUseAscender(true);
				cell.setUseDescender(true);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

				PdfPCell cell1 = new PdfPCell(new Paragraph("地号", setChinese(FS10)));
				PdfPCell cell2 = new PdfPCell(new Paragraph(attach4vo.getLotCode(), setChinese(FS10)));
				PdfPCell cell3 = new PdfPCell(new Paragraph("使用期限", setChinese(FS10)));
				PdfPCell cell4 = new PdfPCell(new Paragraph(attach4vo.getAllDate(), setChinese(FS10)));
				PdfPCell cell5 = new PdfPCell(new Paragraph("批准用途", setChinese(FS10)));
				PdfPCell cell6 = new PdfPCell(new Paragraph(attach4vo.getPermitUsage(), setChinese(FS10)));
				PdfPCell cell7 = new PdfPCell(new Paragraph("总面积", setChinese(FS10)));
				PdfPCell cell8 = new PdfPCell(new Paragraph(attach4vo.getLandArea(), setChinese(FS10)));
				PdfPCell cell9 = new PdfPCell(new Paragraph("共用面积", setChinese(FS10)));
				PdfPCell cell10 = new PdfPCell(new Paragraph(attach4vo.getBlockArea(), setChinese(FS10)));
				PdfPCell cell11 = new PdfPCell(new Paragraph("使用权来源", setChinese(FS10)));
				PdfPCell cell12 = new PdfPCell(new Paragraph(attach4vo.getLandSource(), setChinese(FS10)));
				table.addCell(cell);
				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
				table.addCell(cell4);
				table.addCell(cell5);
				table.addCell(cell6);
				table.addCell(cell7);
				table.addCell(cell8);
				table.addCell(cell9);
				table.addCell(cell10);
				table.addCell(cell11);
				table.addCell(cell12);
				document.add(table);
			}

			p = new Paragraph();
			p.setSpacingBefore(SPB20 * 3);
			document.add(p);

			List<MetaFilter> params1 = new ArrayList<MetaFilter>();
			params1.add(new MetaFilter("contractID", "=", contractID));
			List<Attach4RealVO> arlist = cqDao.search(Attach4RealVO.class, params1, null, null);
			//Attach4RealVO attach4RealVO = (Attach4RealVO) cqDao.find(Attach4RealVO.class, contractID);
			if(arlist != null && arlist.size() > 0){
				for(Attach4RealVO arvo : arlist){
					PdfPTable table = new PdfPTable(4);
					table.setTotalWidth(523);
					int[] width1 = { 10, 40, 15, 35 };
					table.setWidths(width1);
					table.setLockedWidth(true);
					PdfPCell cell = new PdfPCell(new Paragraph("产 权 信 息", setChinese(FS10, Font.BOLD)));
					cell.setColspan(6);
					cell.setHorizontalAlignment(1);
					cell.setUseAscender(true);
					cell.setUseDescender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

					PdfPCell cell1 = new PdfPCell(new Paragraph("权利人", setChinese(FS10)));
					PdfPCell cell2 = new PdfPCell(new Paragraph(arvo.getRealName(), setChinese(FS10)));
					PdfPCell cell3 = new PdfPCell(new Paragraph("权证或证明号", setChinese(FS10)));
					PdfPCell cell4 = new PdfPCell(new Paragraph(arvo.getRealNo(), setChinese(FS10)));
					PdfPCell cell5 = new PdfPCell(new Paragraph("共有人与共有情况", setChinese(FS10)));
					PdfPCell cell6 = new PdfPCell(new Paragraph(arvo.getAllRealName(), setChinese(FS10)));
					cell6.setColspan(3);
					PdfPCell cell7 = new PdfPCell(new Paragraph("房屋坐落", setChinese(FS10)));
					PdfPCell cell8 = new PdfPCell(new Paragraph(arvo.getLocation(), setChinese(FS10)));
					cell8.setColspan(3);
					PdfPCell cell9 = new PdfPCell(new Paragraph("受理日期", setChinese(FS10)));
					PdfPCell cell10 = new PdfPCell(new Paragraph(arvo.getStartDate(), setChinese(FS10)));
					PdfPCell cell11 = new PdfPCell(new Paragraph("核准日期", setChinese(FS10)));
					PdfPCell cell12 = new PdfPCell(new Paragraph(arvo.getPassDate(), setChinese(FS10)));
					PdfPCell cell13 = new PdfPCell(new Paragraph(" 备  \n 注", setChinese(FS10)));
					PdfPCell cell14 = new PdfPCell(new Paragraph(arvo.getRemark(), setChinese(FS10)));
					cell14.setColspan(3);
					table.addCell(cell);
					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);
					table.addCell(cell6);
					table.addCell(cell7);
					table.addCell(cell8);
					table.addCell(cell9);
					table.addCell(cell10);
					table.addCell(cell11);
					table.addCell(cell12);
					table.addCell(cell13);
					table.addCell(cell14);
					document.add(table);
				}
			}

			List<MetaFilter> params2 = new ArrayList<MetaFilter>();
			params2.add(new MetaFilter("contractID", "=", contractID));
			List<Attach4OtherVO> aolist = cqDao.search(Attach4OtherVO.class, params2, null, null);
			//Attach4OtherVO attach4OtherVO = (Attach4OtherVO) cqDao.find(Attach4OtherVO.class, contractID);
			if(aolist != null && aolist.size() > 0){
				for(Attach4OtherVO aovo : aolist){
					PdfPTable table = new PdfPTable(4);
					table.setTotalWidth(523);
					int[] width1 = { 10, 40, 15, 35 };
					table.setWidths(width1);
					table.setLockedWidth(true);
					PdfPCell cell = new PdfPCell(new Paragraph("他 项 信 息", setChinese(FS10, Font.BOLD)));
					cell.setColspan(6);
					cell.setHorizontalAlignment(1);
					cell.setUseAscender(true);
					cell.setUseDescender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

					PdfPCell cell1 = new PdfPCell(new Paragraph("权利种类", setChinese(FS10)));
					PdfPCell cell2 = new PdfPCell(new Paragraph(aovo.getOtherRightType(), setChinese(FS10)));
					PdfPCell cell3 = new PdfPCell(new Paragraph("债权金额", setChinese(FS10)));
					PdfPCell cell4 = new PdfPCell(new Paragraph(aovo.getDebtAccount(), setChinese(FS10)));
					PdfPCell cell5 = new PdfPCell(new Paragraph("房产坐落", setChinese(FS10)));
					PdfPCell cell6 = new PdfPCell(new Paragraph(aovo.getOtherRightPart(), setChinese(FS10)));
					PdfPCell cell7 = new PdfPCell(new Paragraph("权证或证明号", setChinese(FS10)));
					PdfPCell cell8 = new PdfPCell(new Paragraph(aovo.getOtherRightNo(), setChinese(FS10)));
					PdfPCell cell9 = new PdfPCell(new Paragraph("他项权利人", setChinese(FS10)));
					PdfPCell cell10 = new PdfPCell(new Paragraph(aovo.getOwnerName(), setChinese(FS10)));
					cell10.setColspan(3);
					PdfPCell cell11 = new PdfPCell(new Paragraph("受理日期", setChinese(FS10)));
					PdfPCell cell12 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(aovo.getStartDate()), setChinese(FS10)));
					PdfPCell cell13 = new PdfPCell(new Paragraph("核准日期", setChinese(FS10)));
					PdfPCell cell14 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(aovo.getPassDate()), setChinese(FS10)));
					PdfPCell cell15 = new PdfPCell(new Paragraph(" 备  \n 注", setChinese(FS10)));
					PdfPCell cell16 = new PdfPCell(new Paragraph(aovo.getRemark(), setChinese(FS10)));
					cell16.setColspan(3);
					table.addCell(cell);
					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);
					table.addCell(cell6);
					table.addCell(cell7);
					table.addCell(cell8);
					table.addCell(cell9);
					table.addCell(cell10);
					table.addCell(cell11);
					table.addCell(cell12);
					table.addCell(cell13);
					table.addCell(cell14);
					table.addCell(cell15);
					table.addCell(cell16);
					document.add(table);
				}
			}

			List<MetaFilter> params3 = new ArrayList<MetaFilter>();
			params3.add(new MetaFilter("contractID", "=", contractID));
			List<Attach4LimitVO> allist = cqDao.search(Attach4LimitVO.class, params3, null, null);
			//Attach4LimitVO attach4LimitVO = (Attach4LimitVO) cqDao.find(Attach4LimitVO.class, contractID);
			if(allist != null && allist.size() > 0){
				for(Attach4LimitVO alvo : allist){
					PdfPTable table = new PdfPTable(4);
					table.setTotalWidth(523);
					int[] width1 = { 10, 40, 15, 35 };
					table.setWidths(width1);
					table.setLockedWidth(true);
					PdfPCell cell = new PdfPCell(new Paragraph("限 制 信 息", setChinese(FS10, Font.BOLD)));
					cell.setColspan(6);
					cell.setHorizontalAlignment(1);
					cell.setUseAscender(true);
					cell.setUseDescender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

					PdfPCell cell1 = new PdfPCell(new Paragraph("被限制人", setChinese(FS10)));
					PdfPCell cell2 = new PdfPCell(new Paragraph(alvo.getNameb(), setChinese(FS10)));
					PdfPCell cell3 = new PdfPCell(new Paragraph("限制人", setChinese(FS10)));
					PdfPCell cell4 = new PdfPCell(new Paragraph(alvo.getNames(), setChinese(FS10)));
					PdfPCell cell5 = new PdfPCell(new Paragraph("房屋座落", setChinese(FS10)));
					PdfPCell cell6 = new PdfPCell(new Paragraph(alvo.getLimitPart(), setChinese(FS10)));
					PdfPCell cell7 = new PdfPCell(new Paragraph("权证或证明号", setChinese(FS10)));
					PdfPCell cell8 = new PdfPCell(new Paragraph(alvo.getLimitNo(), setChinese(FS10)));
					PdfPCell cell9 = new PdfPCell(new Paragraph("起始日期", setChinese(FS10)));
					PdfPCell cell10 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(alvo.getPlanStartDate()), setChinese(FS10)));
					PdfPCell cell11 = new PdfPCell(new Paragraph("结束日期", setChinese(FS10)));
					PdfPCell cell12 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(alvo.getPlanEndDate()), setChinese(FS10)));
					PdfPCell cell13 = new PdfPCell(new Paragraph("受理日期", setChinese(FS10)));
					PdfPCell cell14 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(alvo.getStartDate()), setChinese(FS10)));
					PdfPCell cell15 = new PdfPCell(new Paragraph("核准日期", setChinese(FS10)));
					PdfPCell cell16 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(alvo.getPassDate()), setChinese(FS10)));
					PdfPCell cell17 = new PdfPCell(new Paragraph(" 备  \n 注", setChinese(FS10)));
					PdfPCell cell18 = new PdfPCell(new Paragraph(alvo.getRemark(), setChinese(FS10)));
					table.addCell(cell);
					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);
					table.addCell(cell6);
					table.addCell(cell7);
					table.addCell(cell8);
					table.addCell(cell9);
					table.addCell(cell10);
					table.addCell(cell11);
					table.addCell(cell12);
					table.addCell(cell13);
					table.addCell(cell14);
					table.addCell(cell15);
					table.addCell(cell16);
					table.addCell(cell17);
					table.addCell(cell18);
					document.add(table);
				}
			}

			List<MetaFilter> params4 = new ArrayList<MetaFilter>();
			params4.add(new MetaFilter("contractID", "=", contractID));
			List<Attach4HireVO> ahlist = cqDao.search(Attach4HireVO.class, params4, null, null);
			//Attach4HireVO attach4HireVO = (Attach4HireVO) cqDao.find(Attach4HireVO.class, contractID);
			if(ahlist != null && allist.size() > 0){
				for(Attach4HireVO ahvo : ahlist){
					PdfPTable table = new PdfPTable(4);
					table.setTotalWidth(523);
					int[] width1 = { 10, 40, 15, 35 };
					table.setWidths(width1);
					table.setLockedWidth(true);
					PdfPCell cell = new PdfPCell(new Paragraph("租 赁 信 息", setChinese(FS10, Font.BOLD)));
					cell.setColspan(6);
					cell.setHorizontalAlignment(1);
					cell.setUseAscender(true);
					cell.setUseDescender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

					PdfPCell cell1 = new PdfPCell(new Paragraph("出租人", setChinese(FS10)));
					PdfPCell cell2 = new PdfPCell(new Paragraph(ahvo.getLessor(), setChinese(FS10)));
					PdfPCell cell3 = new PdfPCell(new Paragraph("出租类型", setChinese(FS10)));
					PdfPCell cell4 = new PdfPCell(new Paragraph(ahvo.getLendType(), setChinese(FS10)));
					PdfPCell cell5 = new PdfPCell(new Paragraph("承租人", setChinese(FS10)));
					PdfPCell cell6 = new PdfPCell(new Paragraph(ahvo.getLessee(), setChinese(FS10)));
					PdfPCell cell7 = new PdfPCell(new Paragraph("租赁用途", setChinese(FS10)));
					PdfPCell cell8 = new PdfPCell(new Paragraph(ahvo.getLendusage(), setChinese(FS10)));
					PdfPCell cell9 = new PdfPCell(new Paragraph("房屋座落", setChinese(FS10)));
					PdfPCell cell10 = new PdfPCell(new Paragraph(ahvo.getLimitPart(), setChinese(FS10)));
					PdfPCell cell11 = new PdfPCell(new Paragraph("期限", setChinese(FS10)));
					PdfPCell cell12 = new PdfPCell(new Paragraph(ahvo.getTimeBetween(), setChinese(FS10)));
					PdfPCell cell13 = new PdfPCell(new Paragraph("权证或证明号", setChinese(FS10)));
					PdfPCell cell14 = new PdfPCell(new Paragraph(ahvo.getRightNo(), setChinese(FS10)));
					PdfPCell cell15 = new PdfPCell(new Paragraph("受理日期", setChinese(FS10)));
					PdfPCell cell16 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(ahvo.getStartDate()), setChinese(FS10)));
					PdfPCell cell17 = new PdfPCell(new Paragraph("核准日期", setChinese(FS10)));
					PdfPCell cell18 = new PdfPCell(new Paragraph(DateUtil.parseDateTime3(ahvo.getPassDate()), setChinese(FS10)));
					PdfPCell cell19 = new PdfPCell(new Paragraph(" 备  \n 注", setChinese(FS10)));
					PdfPCell cell20 = new PdfPCell(new Paragraph(ahvo.getRemark(), setChinese(FS10)));
					cell20.setColspan(3);
					table.addCell(cell);
					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);
					table.addCell(cell6);
					table.addCell(cell7);
					table.addCell(cell8);
					table.addCell(cell9);
					table.addCell(cell10);
					table.addCell(cell11);
					table.addCell(cell12);
					table.addCell(cell13);
					table.addCell(cell14);
					table.addCell(cell15);
					table.addCell(cell16);
					table.addCell(cell17);
					table.addCell(cell18);
					table.addCell(cell19);
					table.addCell(cell20);
					document.add(table);
				}
			}

			//Date date = new Date();//创建一个时间对象，获取到当前的时间
			//String str = sdf.format(date);//将当前时间格式化为需要的类型
			p = new Paragraph("打印时间：" + DateUtil.parseDateTime4(cdvo.getConfirmDate()), setChinese(FS14));
			p.setAlignment(Element.ALIGN_RIGHT);
			document.add(p);

			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  五", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			document.add(getParagraph("该房屋《前期物业管理服务合同》、《使用公约》或有关承诺书 ", setChinese(FS14), 0.0f, 20.0f, 0.0f, Element.ALIGN_CENTER));
			p = new Paragraph("-------------------------------------------------------");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			document.add(new Paragraph(presellFJ5, setChinese(FS14)));

			document.newPage();

			p = new Paragraph();
			c = new Chunk("附  件  六", setChinese(FS14, Font.BOLD));
			p.add(c);
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			document.add(getParagraph("该房屋相邻关系及小区布局", setChinese(FS14), 0.0f, 20.0f, 0.0f, Element.ALIGN_CENTER));
			p = new Paragraph("-------------------------------------------------------");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);

			document.newPage();

			XgLimitSaleContractVO limitContractVO = null;
			List<MetaFilter> xgParams = new ArrayList<MetaFilter>();
			xgParams.add(new MetaFilter("contractID", "=", contractID));
			//查找限购的信息返回list
			List<XgLimitSaleContractVO> xgList = pdfDAO.search(XgLimitSaleContractVO.class, xgParams, null, null);
			if(xgList != null && xgList.size() > 0){
				limitContractVO = xgList.get(0);
			}

			if(limitContractVO != null && limitContractVO.getContractID() > 0){
				p = new Paragraph();
				c = new Chunk("附  件  七", setChinese(FS14, Font.BOLD));
				p.add(c);
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingBefore(SPB28);
				document.add(p);
				document.add(getParagraph("购房资格查验证明", setChinese(FS14), 0.0f, 20.0f, 0.0f, Element.ALIGN_CENTER));
				p = new Paragraph("-------------------------------------------------------");
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingBefore(SPB28);
				document.add(p);
				p = new Paragraph("编号：" + limitContractVO.getLimitSaleID());
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingBefore(SPB28);
				document.add(p);

				document.newPage();
			}

			p = new Paragraph();
			p.add(new Chunk("补 充 条 款", setChinese(FS14, Font.BOLD)));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			p = new Paragraph("-------------------------------------------------------");
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB28);
			document.add(p);
			document.add(new Paragraph(presellBC, setChinese(FS14)));

			document.newPage();

			PdfPTable table = new PdfPTable(4);
			table.setTotalWidth(523);
			int[] width2 = { 20, 30, 30, 20 };
			table.setWidths(width2);
			table.setLockedWidth(true);

			PdfPCell cell1 = new PdfPCell(new Paragraph("甲方（名称）：", setChinese(FS14)));
			cell1.setMinimumHeight(100);
			cell1.setBorder(0);
			PdfPCell cell2 = new PdfPCell(new Paragraph(svo.getSellerName(), setChinese(FS14)));
			cell2.setMinimumHeight(100);
			cell2.setBorder(0);
			PdfPCell cell3 = new PdfPCell(new Paragraph("乙方（名称或名字）：", setChinese(FS14)));
			cell3.setMinimumHeight(100);
			cell3.setBorder(0);
			PdfPCell cell4 = new PdfPCell(new Paragraph(buyername, setChinese(FS14)));
			cell4.setMinimumHeight(100);
			cell4.setBorder(0);
			PdfPCell cell5 = new PdfPCell(new Paragraph("法定代表人签署：", setChinese(FS14)));
			cell5.setColspan(2);
			cell5.setMinimumHeight(60);
			cell5.setBorder(0);
			PdfPCell cell6 = new PdfPCell(new Paragraph("乙方本人签署： ", setChinese(FS14)));
			cell6.setColspan(2);
			cell6.setMinimumHeight(60);
			cell6.setBorder(0);
			PdfPCell cell7 = new PdfPCell(new Paragraph("法定代表人的\n委托代理人签署：", setChinese(FS14)));
			cell7.setColspan(2);
			cell7.setMinimumHeight(80);
			cell7.setBorder(0);
			PdfPCell cell8 = new PdfPCell(new Paragraph("__________________的委托代理人/\n法定代理人签署：", setChinese(FS14)));
			cell8.setColspan(2);
			cell8.setMinimumHeight(80);
			cell8.setBorder(0);
			PdfPCell cell9 = new PdfPCell(new Paragraph("甲方盖章：", setChinese(FS14)));
			cell9.setColspan(2);
			cell9.setMinimumHeight(60);
			cell9.setBorder(0);
			PdfPCell cell10 = new PdfPCell(new Paragraph("乙方盖章：", setChinese(FS14)));
			cell10.setColspan(2);
			cell10.setMinimumHeight(60);
			cell10.setBorder(0);
			PdfPCell cell11 = new PdfPCell(new Paragraph("日期：     年     月     日 ", setChinese(FS14)));
			cell11.setColspan(2);
			cell11.setMinimumHeight(60);
			cell11.setBorder(0);
			PdfPCell cell12 = new PdfPCell(new Paragraph("日期：     年     月     日 ", setChinese(FS14)));
			cell12.setColspan(2);
			cell12.setMinimumHeight(60);
			cell12.setBorder(0);
			PdfPCell cell13 = new PdfPCell(new Paragraph("签于：", setChinese(FS14)));
			cell13.setColspan(2);
			cell13.setMinimumHeight(60);
			cell13.setBorder(0);
			PdfPCell cell14 = new PdfPCell(new Paragraph("签于：", setChinese(FS14)));
			cell14.setColspan(2);
			cell14.setMinimumHeight(60);
			cell14.setBorder(0);
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			table.addCell(cell6);
			table.addCell(cell7);
			table.addCell(cell8);
			table.addCell(cell9);
			table.addCell(cell10);
			table.addCell(cell11);
			table.addCell(cell12);
			table.addCell(cell13);
			table.addCell(cell14);
			document.add(table);

			//甲方拟签人员信息
			SignerVO signerVO;
			if(cdvo.getSigner() != null && !"".equals(cdvo.getSigner())){
				signerVO = (SignerVO) cqDao.find(SignerVO.class, Long.parseLong(cdvo.getSigner()));
				PdfPTable table14 = new PdfPTable(4);
				int width14[] = { 21, 29, 21, 29 };
				table14.setWidths(width14);
				table14.setTotalWidth(540);
				table14.setLockedWidth(true);
				PdfPCell cell141 = new PdfPCell(new Paragraph("甲方拟签人员：", setChinese(FS14)));
				PdfPCell cell142 = new PdfPCell(new Paragraph(signerVO.getName(), setChinese(FS14)));
				PdfPCell cell143 = new PdfPCell(new Paragraph("销售员证书号：", setChinese(FS14)));
				PdfPCell cell144 = new PdfPCell(new Paragraph(signerVO.getCardCode(), setChinese(FS14)));
				cell141.setBorder(0);
				cell143.setBorder(0);
				cell142.setBorder(0);
				cell144.setBorder(0);
				table14.addCell(cell141);
				table14.addCell(cell142);
				table14.addCell(cell143);
				table14.addCell(cell144);
				document.add(table14);
				Paragraph blankRow14 = new Paragraph(12f, " ", setChinese(FS14));
				document.add(blankRow14);
			}
			//甲方确认人员信息
			SignerVO signerVO1;
			if(cdvo.getConfirmer() != null && !"".equals(cdvo.getConfirmer())){
				signerVO1 = (SignerVO) cqDao.find(SignerVO.class, Long.parseLong(cdvo.getConfirmer()));
				PdfPTable table15 = new PdfPTable(4);
				int width15[] = { 21, 29, 21, 29 };
				table15.setWidths(width15);
				table15.setTotalWidth(540);
				table15.setLockedWidth(true);
				PdfPCell cell151 = new PdfPCell(new Paragraph("甲方确认人员：", setChinese(FS14)));
				PdfPCell cell152 = new PdfPCell(new Paragraph(signerVO1.getName(), setChinese(FS14)));
				PdfPCell cell153 = new PdfPCell(new Paragraph("销售员证书号：", setChinese(FS14)));
				PdfPCell cell154 = new PdfPCell(new Paragraph(signerVO1.getCardCode(), setChinese(FS14)));
				cell151.setBorder(0);
				cell153.setBorder(0);
				cell152.setBorder(0);
				cell154.setBorder(0);
				table15.addCell(cell151);
				table15.addCell(cell152);
				table15.addCell(cell153);
				table15.addCell(cell154);
				document.add(table15);
				Paragraph blankRow15 = new Paragraph(12f, " ", setChinese(FS14));
				document.add(blankRow15);
			}

			//网签合同时间
			String confirmDate = cdvo.getConfirmDate();
			if(confirmDate != null && !"".equals(confirmDate) && !"0".equals(confirmDate)){
				PdfPTable table16 = new PdfPTable(2);
				int width16[] = { 26, 74 };
				table16.setWidths(width16);
				table16.setTotalWidth(540);
				table16.setLockedWidth(true);
				PdfPCell cell161 = new PdfPCell(new Paragraph("网上合同签订时间：", setChinese(FS14)));
				PdfPCell cell162 = new PdfPCell(new Paragraph(sdf.format(DateUtil.parseDateTime2(confirmDate)), setChinese(FS14)));
				cell161.setBorder(0);
				cell162.setBorder(0);
				table16.addCell(cell161);
				table16.addCell(cell162);
				document.add(table16);
				Paragraph blankRow16 = new Paragraph(12f, " ", setChinese(FS14));
				document.add(blankRow16);
			}


			document.close();

			ContractPdfVO ctv = new ContractPdfVO();
			ctv.setPdfData(baos.toByteArray());
			baos.close();
			return ctv;
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cqDao);
			closeDAO(pdfDAO);
		}
	}

	/**
	 * 功能描述：出售合同文本
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public ContractPdfVO createNewSellContractPdf(HttpServletRequest request,long contractID,int userType,int signFlag) throws Exception {

		try{
			openDAO(cqDao);
			openDAO(pdfDAO);

			//查询合同主表信息
			ContractDealVO cdvo = (ContractDealVO) cqDao.find(ContractDealVO.class, contractID);
			//出售合同信息
			SellContractVO scvo = (SellContractVO) cqDao.find(SellContractVO.class, contractID);

			ContractDetailCsVO cs = (ContractDetailCsVO) cqDao.find(ContractDetailCsVO.class, contractID);

			if(scvo == null){
				scvo = new SellContractVO();
			}
			//查询合同甲方信息
			SellerInfoVO svo = new SellerInfoVO();
			List<SellerInfoVO> list1 = cqDao.searchSellerInfo(String.valueOf(contractID));
			if(list1 != null && list1.size() > 0){
				svo = list1.get(0);
			}
			//查询合同乙方信息
			List<BuyerInfoVO> list2 = cqDao.searchBuyerInfo(String.valueOf(contractID));

			Document document = new Document(PageSize.A4);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//定义输出位置并把文档对象装入输出对象中
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			//添加水印
			if(userType == 0) //内网用户
				writer.setPageEvent(new Watermark("合同查阅"));
			if(userType == 1 && (cdvo.getStatus() == 0 || cdvo.getStatus() == 1)) //外网用户草签或待签合同
				if(signFlag != 1){
					writer.setPageEvent(new Watermark("非正式合同"));
				}else{
					if(cdvo.getStatus() == 1){
						writer.setPageEvent(new Watermark("电子合同"));
					}
				}
			if(userType == 1 && (cdvo.getStatus() != 0 && cdvo.getStatus() != 1 && cdvo.getStatus() != 2)) //外网其他合同
				writer.setPageEvent(new Watermark(dBo.getDictName("ct_500", String.valueOf(cdvo.getStatus())) + "合同"));

			//页眉
			HeaderFooter header = new HeaderFooter(new Phrase(String.valueOf(contractID)
					+ "---------------------------------------------------------------------------------------------"
					+ DateUtil.formatDateTime(DateUtil.parseDateTime2( (DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()))), setChinese(FS10)),
					false);
			header.setBorder(Rectangle.NO_BORDER);
			header.setAlignment(1);
			header.setBorderColor(Color.BLACK);
			document.setHeader(header);

			//页码
			HeaderFooter footer = new HeaderFooter(new Phrase("-", setChinese(FS14)), new Phrase("-", setChinese(FS14)));
			footer.setAlignment(1);
			footer.setBorderColor(Color.red);
			footer.setBorder(Rectangle.NO_BORDER);
			document.setFooter(footer);

			document.open();

			Paragraph p;
			Chunk c;
			//封面
			Paragraph p1 = new Paragraph("GF-2014-0172                                                                                         合同编号："
					+ contractID, setChinese(FS14));
			p1.setAlignment(Element.ALIGN_CENTER);
			p1.setSpacingAfter(FS10 * 5); //设置段落前间距
			document.add(p1);

			//合同条形码
			PdfContentByte cd128 = writer.getDirectContent();
			Barcode128 code128 = new Barcode128();
			code128.setCodeType(code128.CODE128);
			code128.setCode(String.valueOf(contractID));
			code128.setBarHeight(30);
			Image image128 = code128.createImageWithBarcode(cd128, null, null);
			image128.setAlignment(2);
			document.add(image128);

			Paragraph sellContract = new Paragraph("商品房买卖合同（现售）", setChinese(FS14 * 2, Font.BOLD));
			sellContract.setAlignment(Element.ALIGN_CENTER);
			sellContract.setSpacingBefore(FS14 * 4); //设置段落前间距
			document.add(sellContract);

			//房屋号条形码
			PdfContentByte cd2 = writer.getDirectContent();
			Barcode128 code2 = new Barcode128();
			code2.setCodeType(Barcode128.CODE128);
			code2.setCode(String.valueOf(cdvo.getHouseID()));
			code2.setBarHeight(30);
			Image image2 = code2.createImageWithBarcode(cd2, null, null);
			image2.setAlignment(1);
			document.add(image2);

			Paragraph p4 = new Paragraph("中华人民共和国住房和城乡建设部 ", setChinese(FS14));
			p4.setAlignment(Element.ALIGN_CENTER);
			p4.setSpacingBefore(FS14 * 8); //设置段落前间距
			document.add(p4);
			Paragraph p5 = new Paragraph("制定 ", setChinese(FS14));
			p5.setSpacingBefore(-10f);
			p5.setSpacingAfter(-10f);
			p5.setAlignment(Element.ALIGN_RIGHT);
			document.add(p5);
			Paragraph p6 = new Paragraph("中华人民共和国国家工商行政管理总局", setChinese(FS14));
			p6.setAlignment(Element.ALIGN_CENTER);
			document.add(p6);

			document.newPage();

			p = new Paragraph("目      录    ", setChinese(FS10 * 2, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB20);
			p.setSpacingAfter(SPB20);
			document.add(p);

			Paragraph p8 = new Paragraph("         说    明", setChinese(FS14));
			p8.setAlignment(Element.ALIGN_LEFT);
			p8.setSpacingBefore(FS14); //设置段落前间距
			document.add(p8);


			Paragraph p9 = new Paragraph("         专业术语解释", setChinese(FS14));
			p9.setAlignment(Element.ALIGN_LEFT);
			p9.setSpacingBefore(FS14); //设置段落前间距
			document.add(p9);

			Paragraph p10 = new Paragraph("        第一章   合同当事人", setChinese(FS14));
			p10.setAlignment(Element.ALIGN_LEFT);
			p10.setSpacingBefore(FS14); //设置段落前间距
			document.add(p10);

			Paragraph p11 = new Paragraph("        第二章   商品房基本状况", setChinese(FS14));
			p11.setAlignment(Element.ALIGN_LEFT);
			p11.setSpacingBefore(FS14); //设置段落前间距
			document.add(p11);

			Paragraph p12 = new Paragraph("        第三章   商品房价款", setChinese(FS14));
			p12.setAlignment(Element.ALIGN_LEFT);
			p12.setSpacingBefore(FS14); //设置段落前间距
			document.add(p12);

			Paragraph p13 = new Paragraph("        第四章   商品房交付条件与交付手续", setChinese(FS14));
			p13.setAlignment(Element.ALIGN_LEFT);
			p13.setSpacingBefore(FS14); //设置段落前间距
			document.add(p13);

			Paragraph p14 = new Paragraph("        第五章   商品房质量及保修责任", setChinese(FS14));
			p14.setAlignment(Element.ALIGN_LEFT);
			p14.setSpacingBefore(FS14); //设置段落前间距
			document.add(p14);

			Paragraph p15 = new Paragraph("        第六章   房屋登记", setChinese(FS14));
			p15.setAlignment(Element.ALIGN_LEFT);
			p15.setSpacingBefore(FS14); //设置段落前间距
			document.add(p15);

			Paragraph p16 = new Paragraph("        第七章   物业管理", setChinese(FS14));
			p16.setAlignment(Element.ALIGN_LEFT);
			p16.setSpacingBefore(FS14); //设置段落前间距
			document.add(p16);

			Paragraph p17 = new Paragraph("        第八章  其他事项", setChinese(FS14));
			p17.setAlignment(Element.ALIGN_LEFT);
			p17.setSpacingBefore(FS14); //设置段落前间距
			document.add(p17);
			document.newPage();

			p = new Paragraph("说     明    ", setChinese(FS10 * 2, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB20);
			p.setSpacingAfter(SPB20);
			document.add(p);
			document.add(getParagraph("1. 本合同文本为示范文本，由中华人民共和国住房和城乡建设部、中 华人民共和国国家工商行政管理总局共同制定。各地可在有关法律法规、 规定的范围内，结合实际情况调整合同相应内容。 ", setChinese(FS14), SPB28,
					30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("2. 签订本合同前，出卖人应当向买受人出示有关权属证书或证明文件。 ", setChinese(FS14), SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("3. 出卖人应当就合同重大事项对买受人尽到提示义务。买受人应当审 慎签订合同，在签订本合同前，要仔细阅读合同条款，特别是审阅其中具 有选择性、补充性、修改性的内容，注意防范潜在的市场风险和交易风 险。 ",
					setChinese(FS14), SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("4. 本合同文本【】中选择内容、空格部位填写内容及其他需要删除 或添加的内容，双方当事人应当协商确定。【】中选择内容，以划 √ 方 式选定；对于实际情况未发生或双方当事人不作约定时，应当在空格部位 打 × ，以示删除。 ",
					setChinese(FS14), SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("5. 出卖人与买受人可以针对本合同文本中没有约定或者约定不明确的 内容，根据所售项目的具体情况在相关条款后的空白行中进行补充约定， 也可以另行签订补充协议。 ", setChinese(FS14), SPB28, 30.0f, 0.0f,
					Element.ALIGN_LEFT));
			document.add(getParagraph("6. 双方当事人可以根据实际情况决定本合同原件的份数，并在签订合 同时认真核对，以确保各份合同内容一致；在任何情况下，出卖人和买受 人都应当至少持有一份合同原件。", setChinese(FS14), SPB28, 30.0f,
					0.0f, Element.ALIGN_LEFT));

			document.newPage();

			p = new Paragraph("专业术语解释", setChinese(FS10 * 2, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB20);
			document.add(p);

			document.add(getParagraph("1. 商品房现售：是指房地产开发企业将竣工验收合格的商品房出售给买受人，并由买受人支付房价款的行为。 ", setChinese(FS14), SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("2. 法定代理人：是指依照法律规定直接取得代理权的人。  ", setChinese(FS14), SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("3. 套内建筑面积：是指成套房屋的套内建筑面积，由套内使用面积、套内墙体面 积、套内阳台建筑面积三部分组成。  ", setChinese(FS14), SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("4. 房屋的建筑面积：是指房屋外墙（柱）勒脚以上各层的外围水平投影面积，包 括阳台、挑廊、地下室、室外楼梯等，且具备有上盖，结构牢固，层高2.20M以上（含 2.20M）的永久性建筑。  ", setChinese(FS14), 28,
					30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("5. 不可抗力：是指不能预见、不能避免并不能克服的客观情况。 ", setChinese(FS14), SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("6. 民用建筑节能：是指在保证民用建筑使用功能和室内热环境质量的前提下，降 低其使用过程中能源消耗的活动。民用建筑是指居住建筑、国家机关办公建筑和商 业、服务业、教育、卫生等其他公共建筑。", setChinese(FS14),
					SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("7. 房屋登记：是指房屋登记机构依法将房屋权利和其他应当记载的事项在房屋登 记簿上予以记载的行为。 ", setChinese(FS14), SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("8. 所有权转移登记：是指商品房所有权从出卖人转移至买受人所办理的登记类型。  ", setChinese(FS14), SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("9. 房屋登记机构：是指直辖市、市、县人民政府主管部门或者其 设置的负责房屋登记工作的机构。  ", setChinese(FS14), SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("10. 分割拆零销售：是指房地产开发企业将成套的商品住宅分割为数部分分别出售 给买受人的销售方式。 ", setChinese(FS14), SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			document.add(getParagraph("11. 返本销售：是指房地产开发企业以定期向买受人返还购房款的方式销售商品房 的行为。  ", setChinese(FS14), SPB28, 30.0f, 0.0f, Element.ALIGN_LEFT));
			//document.add(getParagraph("12. 售后包租：是指房地产开发企业以在一定期限内承租或者代为出租买受人所购 该企业商品房的方式销售商品房的行为。", setChinese(FS12), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			document.newPage();

			Paragraph contract = new Paragraph("商品房买卖合同", setChinese(FS14 * 2, Font.BOLD));
			contract.setAlignment(Element.ALIGN_CENTER);
			contract.setSpacingBefore(SPB20);
			document.add(contract);
			p1 = new Paragraph("（现 售）", setChinese(FS10 * 2));
			p1.setAlignment(Element.ALIGN_CENTER);
			p1.setSpacingBefore(SPB20); //设置段落前间距
			p1.setSpacingAfter(SPB20);
			document.add(p1);
			document.add(getParagraph(
					"出卖人向买受人出售其开发建设的房屋，双方当事人应当在自愿、平等、公平及 诚实信用的基础上，根据《中华人民共和国合同法》、《中华人民共和国物权法》、《中 华人民共和国城市房地产管理法》等法律、法规的规定，就商品房买卖相关内容协商 达成一致意见，签订本商品房买卖合同。",
					setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
			p = new Paragraph("第一章 合同当事人", setChinese(FS10 * 2, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			p.setSpacingBefore(SPB20);
			p.setSpacingAfter(SPB20);
			document.add(p);
			String ver = "0";
			if(ver == "0"){
				//document.add(getParagraph("出卖人", setChinese(FS14), FL28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getPTable(new String[] { "甲方(卖方)：", " *" + svo.getSellerName() }, 18, 82, 0, 0, 0, 0));
				document.add(getPTable(new String[] { "住所：", " *" + svo.getSellerAddress(), " 邮编：", " *" + svo.getSellerPostcode() }, 9, 61, 9, 21, 0, 0));
				document.add(getPTable(new String[] { "营业执照号码：", " *" + svo.getSellerBizcardcode() }, 21, 79, 0, 0, 0, 0));
				document.add(getPTable(new String[] { "资质证书号码：", " *" + svo.getSellerCertcode() }, 21, 79, 0, 0, 0, 0));
				document.add(getPTable(new String[] { "法定代表人： ", " *" + svo.getSellerDelegate(), " 联系电话：", " *" + svo.getSellerDlgCall() }, 18, 42, 15, 25, 0,
						0));
				document
						.add(getPTable(new String[] { "委托代理人：", " *" + svo.getSellerProxy(), " 联系电话：", " *" + svo.getSellerProxyCall() }, 18, 42, 15, 25, 0, 0));

				//查询合同乙方信息
				List<BuyerInfoVO> list3 = cqDao.searchBuyerInfo(String.valueOf(contractID));
				String buyername = "";
				if(list3 != null && list3.size() > 0){
					for(BuyerInfoVO bvo : list3){
						p = new Paragraph();
						p.setSpacingBefore(20);
						p.setSpacingAfter(20);
						document.add(p);
						buyername += bvo.getBuyerName();
						document.add(getPTable(new String[] { "乙方(买方)：", "*" + bvo.getBuyerName() }, 18, 82, 0, 0, 0, 0));
						document.add(getPTable(new String[] { "国籍：", "*" + dBo.getDictName("ct_504", String.valueOf(bvo.getBuyerNationality())),
								"  居住(注册)所在省市：", " *" + dBo.getDictName("ct_524", String.valueOf(bvo.getBuyerProvince())) }, 9, 30, 30, 31, 0, 0));

						String buyerBirth = DateUtil.parseDateTime3(bvo.getBuyerBirth());
						document.add(getPTable(new String[] { "个人/公司", "*" + baseHandle(bvo.getAttribute("buyer_type_dict_name")), " 性别：",
								" *" + baseHandle(bvo.getAttribute("buyer_sex_dict_name")), " 出生年月日： ", " *" + baseHandle(buyerBirth) }, 15, 15, 9, 9, 18, 34));
						document.add(getPTable(
								new String[] { "住址：", "*" + baseHandle(bvo.getBuyerAddress()), " 邮编：", " *" + baseHandle(bvo.getBuyerPostcode()) }, 9, 67, 9,
								15, 0, 0));
						document.add(getPTable(new String[] { "证件名称：", "*" + dBo.getDictName("ct_506", String.valueOf(bvo.getBuyerCardname())), " 号码：",
								" *" + baseHandle(bvo.getBuyerCardcode()) }, 15, 40, 9, 36, 0, 0));
						document.add(getPTable(new String[] { "联系电话：", "*" + bvo.getBuyerCall() }, 15, 85, 0, 0, 0, 0));
						document.add(getPTable(new String[] { "委托/法定代理人：", "*" + baseHandle(bvo.getBuyerProxy()), " 联系电话：",
								"  *" + baseHandle(bvo.getBuyerProxyCall()) }, 27, 33, 15, 25, 0, 0));
						document.add(getPTable(new String[] { "住址：", "*" + baseHandle(bvo.getBuyerProxyAdr()) }, 12, 88, 0, 0, 0, 0));
					}
				}

				document.newPage();

				p = new Paragraph("第二章 商品房基本状况", setChinese(FS14, Font.BOLD));
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingBefore(20);
				p.setSpacingAfter(20);
				document.add(p);

				String FJ2 = null, FJ3 = null, FJ4 = null, FJ5 = null, FJ6 = null, FJ9 = null, FJ10 = null, FJ11 = null, FJ12 = null;
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("contractid", "=", contractID));
				List<AttachVO> atVOlList = cqDao.searchByAttibutes(AttachVO.class, params, new ArrayList<MetaOrder>(), new Page());
				if(atVOlList != null && atVOlList.size() > 0){
					for(AttachVO attachVO : atVOlList){
						if(attachVO.getContent() != null){
							String content = new String(attachVO.getContent());
							if(attachVO.getSerial() == 4902){
								FJ2 = content;
							}else if(attachVO.getSerial() == 4903){
								FJ3 = content;
							}else if(attachVO.getSerial() == 4904){
								FJ4 = content;
							}else if(attachVO.getSerial() == 4905){
								FJ5 = content;
							}else if(attachVO.getSerial() == 4906){
								FJ6 = content;
							}else if(attachVO.getSerial() == 4909){
								FJ9 = content;
							}else if(attachVO.getSerial() == 4910){
								FJ10 = content;
							}else if(attachVO.getSerial() == 4911){
								FJ11 = content;
							}else if(attachVO.getSerial() == 4912){
								FJ12 = content;
							}
						}

					}
				}
				//设表的宽度为523f，一行37个字，一个字占2.7%
				document.add(getParagraph("^第一条 项目建设依据"));
				document.add(getParagraph("1. 出卖人以 " + " *" + cs.getf0101() + " 方式取得坐落于" + " *" + cs.getf0102() + " 地块的建设用地使用权。该地块 " + " *" + cs.getf0103()
						+ " 为" + " *" + cs.getf0104() + " ," + "土地使用权面积为" + " *" + cs.getf0105() + " 平方米。买受人购买的商品房（以下简称该商" + "品 房）所占用的土地用途为" + " *"
						+ cs.getf0106() + " ,土地使用权终止日期为" + " *" + cs.getf0107() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2. 出卖人经批准，在上述地块上建设的商品房项目核准名称为" + " *" + cs.getf0108() + " ， 建设工程规划许可证号为" + " *" + cs.getf0109() + " ， 建筑工程施工许可证号为"
						+ " *" + cs.getf0110() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

				document.add(getParagraph("^第二条 销售依据 "));
				document.add(getParagraph("该商品房已取得不动产登记证明号为 " + " *" + cs.getf0203() + " ,房屋登记机构为" + " *" + cs.getf0205() + " 。",
						setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

				document.add(getParagraph("^第三条 商品房基本情况 "));
				document.add(getParagraph("1.该商品房的规划用途为" + " *" + cs.getf0301() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.该商品房所在建筑物的主体结构为" + " *" + cs.getf0302() + " ， 建筑总层数为" + " *" + cs.getf0303() + " 层， 其中地上" + " *" + cs.getf0304()
						+ " 层， 地下" + " *" + cs.getf0305() + " 层。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" 3.该商品房为第一条规定项目中的" + " *" + cs.getf0306() + " 。该商品房的平面图见附件一。", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph(" 4.该商品房的房产测绘机构为" + " *" + cs.getf0307() + " ， 其实测建筑面积共" + " *" + cs.getf0308() + " 平方米， 其中套内建筑面积" + " *"
						+ cs.getf0309() + " 平方米， 分摊共有建筑面积" + " *" + cs.getf0310() + " 平方米。该商品房共用部位见附件二。 " + " 该商品房层高为" + " *" + cs.getf0311() + " 米， 有" + " *"
						+ cs.getf0312() + " 个阳台， 其中" + " *" + cs.getf0313() + " 个阳台为封闭式，" + " *" + cs.getf0314() + " 个阳台为非封闭式。阳台是否封闭以规划设计文件为准。 ",
						setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

				document.add(getParagraph("^第四条 抵押情况 "));
				document.add(getParagraph("与该商品房有关的抵押情况为" + " *" + cs.getf0401() + "  ， 抵押人：" + " *" + cs.getf0402() + " ， 抵押权人" + " *" + cs.getf0403()
						+ " ， 抵押登记机构：" + " *" + cs.getf0404() + " ， 抵押登记日期：" + " *" + cs.getf0405() + " ， 债务履行期限：" + " *" + cs.getf0406()
						+ " 。 抵押权人同意该商品房转让的证明及关于抵押的相关约定见附三。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

				document.add(getParagraph("^第五条 租赁情况 "));
				document.add(getParagraph("该商品房的租赁情况为" + " *" + cs.getf0501() + "  ，", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("出卖人已将该商品房出租，" + " *" + cs.getf0502() + "  ，", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("租赁期限：从" + substringDate(cs.getf0503(), 1) + " 年" + substringDate(cs.getf0503(), 2) + " 月"
						+ substringDate(cs.getf0503(), 3) + " 日" + " 至" + substringDate(cs.getf0504(), 1) + " 年" + substringDate(cs.getf0504(), 2) + " 月"
						+ substringDate(cs.getf0504(), 3) + " 日" + " 。出卖人与买受人经协商一致，自本合同约定的交付日至租赁期限届满期间的房屋收益归" + " *" + cs.getf0505() + " 所有。",
						setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("" + " *" + cs.getf0506() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("出卖人提供的承租人放弃优先购买权的声明见附件四。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

				document.add(getParagraph("^第六条 房屋权利状况承诺 "));
				document.add(getParagraph(" 1.出卖人对该商品房享有合法权利；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" 2.该商品房没有出售给除本合同买受人以外的其他人；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" 3.该商品房没有司法查封或其他限制转让的情况；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" 4." + " *" + cs.getf0601() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" 5." + " *" + cs.getf0602() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(
						" 如该商品房权利状况与上述情况不符，导致不能完成房屋所有权转移登记的，买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起， 按照" + " *"
								+ cs.getf0603() + " %(不低于中国人民银行公布的同期贷款基准利率）计算给付利息。给买受人造成损失的，由出卖人支付" + " *" + cs.getf0604() + " 的赔偿金。 ", setChinese(FS14),
						SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.newPage();

				p = new Paragraph("第三章 商品房价款", setChinese(FS14, Font.BOLD));
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingBefore(20);
				p.setSpacingAfter(20);
				document.add(p);
				document.add(getParagraph("^第七条 计价方式与价款 "));
				document.add(getParagraph("出卖人与买受人按照下列第" + " *" + cs.getf0701() + " 种方式计算该商品房价款： ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("1.按照套内建筑面积计算，该商品房单价（不包含房屋装修）为每平方米         *" + cs.getf0715() + "  （币种）         *" + cs.getf0702()
						+ "  元， 总价款为（不包含房屋装修）    *" + cs.getf0716() + " （币种）        *" + cs.getf0703() + " 元 （大写     *" + cs.getf0704()
						+ " 元整）。 该商品房装修价格为每平方米      *" + cs.getf0717() + " （币种）" + " *" + cs.getf0718() + " 元， 装修总价为人民币          *" + cs.getf0719() 
						+ " 元（大写 "+ " *" + cs.getf0726()+" 元整）。",
						setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.按照建筑面积计算，该商品房单价（不包含房屋装修）为每平方米       *" + cs.getf0720() + "  （币种）" + " *" + cs.getf0705()
						+ " 元， 总价款（不包含房屋装修）为        *" + cs.getf0721() + "  （币种）" + " *" + cs.getf0706() + " 元 （大写" + " *" + cs.getf0707()
						+ " 元整）。 该商品房装修价格为每平方米        *" + cs.getf0722() + "  （币种）" + " *" + cs.getf0708() + " 元， 装修总价为人民币" + " *" + cs.getf0709() 
						+ " 元（大写 "+ " *" + cs.getf0727()+" 元整）。",
						setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("3.按照套计算，该商品房总价款为       *" + cs.getf0723() + "  （币种）" + " *" + cs.getf0710() + " 元 （大写" + " *" + cs.getf0711()
						+ " 元整）。 装修总价为人民币" + " *" + cs.getf0724() + " 元（大写 "+ " *" + cs.getf0738()+" 元整）。", setChinese(FS14),
						SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("4.按照" + " *" + cs.getf0712() + " 计算， 该商品房总价款为        *" + cs.getf0725() + "  （币种）" + " *" + cs.getf0713() + " 元 （大写"
						+ " *" + cs.getf0714() + " 元整）。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("5.按照套内建筑面积计算，该商品房单价为每平方米         *" + cs.getf0728() + "  （币种）         *" + cs.getf0729()
						+ "  元， 总价款为    *" + cs.getf0730() + " （币种）        *" + cs.getf0731() + " 元 （大写     *" + cs.getf0732()
						+ " 元整）。",setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("6.按照建筑面积计算，该商品房单价为每平方米         *" + cs.getf0733() + "  （币种）         *" + cs.getf0734()
						+ "  元， 总价款为    *" + cs.getf0735() + " （币种）        *" + cs.getf0736() + " 元 （大写     *" + cs.getf0737()
						+ " 元整）。",setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				
				document.add(getParagraph("^第八条 付款方式及期限 "));
				document.add(getParagraph("（一）签订本合同前，买受人已向出卖人支付定金 人民币  （币种）" + " *" + cs.getf0801() + " 元 （大写" + " *" + cs.getf0802() + " 元整）， 该定金于" + " *"
						+ cs.getf0803() + " 时" + " *" + cs.getf0805() + " 商品房价款。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("(二）买受人采取下列第" + " *" + cs.getf0807() + " 种方式付款： ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("1.一次性付款。买受人应当在" + substringDate(cs.getf0808(), 1) + " 年" + substringDate(cs.getf0808(), 2) + " 月"
						+ substringDate(cs.getf0808(), 3) + " 日前支付该商品房全部价款。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.分期付款。买受人应当在" + substringDate(cs.getf0809(), 1) + " 年" + substringDate(cs.getf0809(), 2) + " 月"
						+ substringDate(cs.getf0809(), 3) + " 日前分" + " *" + cs.getf0810() + " 期支付该商品房全部价款， 首期房价款人民币  （币种）" + " *" + cs.getf0811() + " 元 （大写"
						+ " *" + cs.getf0812() + " 元整）， 应当于" + " *" + cs.getf0813() + " 日前支付。 ", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph(" *" + cs.getf0814(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("3.贷款方式付款：" + " *" + cs.getf0815() + " 。 买受人应当于" + substringDate(cs.getf0817(), 1) + " 年"
						+ substringDate(cs.getf0817(), 2) + " 月" + substringDate(cs.getf0817(), 3) + " 日前 支付首期房价款人民币  （币种）" + " *" + cs.getf0818() + " 元 （大写"
						+ " *" + cs.getf0819() + " 元整）， 占全部房价款的" + " *" + cs.getf0820() + " ％。", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph(" 余款人民币  （币种）" + " *" + cs.getf0821() + " 元 （大写" + " *" + cs.getf0822() + " 元整）， 向" + " *" + cs.getf0823()
						+ " （贷款机构）申请贷款支付。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("4.其他方式：" + " *" + cs.getf0824(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("该商品房价款的计价方式、总价款、付款方式及期限的具体约定见附件五。  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

				document.add(getParagraph("^第九条 逾期付款责任 "));
				document.add(getParagraph("除不可抗力外，买受人未按照约定时间付款的，双方同意按照下列第" + " *" + cs.getf0901() + " 种方式处理： ", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("1.按照逾期时间，分别处理（（1）和（2）不作累加） ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（1）逾期在" + " *" + cs.getf0902() + " 日之内， 买受人按日计算向出卖人支付逾期应付款万分之" + " *" + cs.getf0903() + " 的违约金。 ", setChinese(FS14),
						SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（2）逾期超过" + " *" + cs.getf0904() + " 日（该期限应当与本条第（1）项中的期限相同）后，出卖人有 权解除合同。出卖人解除合同的，应当书面通知买受人。 买受人应当自解除合同通知送达之日起" + " *"
						+ cs.getf0905() + " 日内按照累计应付款的" + " *" + cs.getf0906() + " %向出卖人支付违约金，同时，出卖人退还买受人已付全部房款（含已付贷款部分）。", setChinese(FS14), SPB28, 25.0f,
						0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("出卖人不解除合同的，买受人按日计算向出卖人支付逾期应付款万分之" + " *" + cs.getf0907() + " （该比率不低于第（1）项中的比率）的违约金。 ", setChinese(FS14), SPB28,
						25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("本条所称逾期应付款是指依照第八条及附件五约定的到期应付款与该期实际已付款的差额；采取分期付款的，按照相应的分期应付款与该期的实际已付款的差额确定。 ", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("2." + " *" + cs.getf0908() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

				p = new Paragraph("第四章 商品房交付条件与交付手续", setChinese(FS14, Font.BOLD));
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingBefore(20);
				p.setSpacingAfter(20);
				document.add(p);
				document.add(getParagraph("^第十条 商品房交付条件 "));
				document
						.add(getParagraph("该商品房交付时应当符合下列第1、2、" + " *" + cs.getf1001() + "   项所列条件： ", setChinese(FS14), SPB28,
						25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("1.该商品房已取得建设工程竣工验收备案证明文件；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.该商品房已取得房屋测绘报告；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("3." + " *" + cs.getf1002() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("4." + " *" + cs.getf1003() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("该商品房为住宅的，出卖人还需提供《住宅使用说明书》和《住宅质量保证书》。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

				document.add(getParagraph("^第十一条 商品房相关设施设备交付条件  "));
				document.add(getParagraph("(一) 基础设施设备 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("1.供水、排水：交付时供水、排水配套设施齐全，并与城市公共供水、排水管网连接。使用自建设施供水的，供水的水质符合国家规定的饮用水卫生标准， ", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph(" *" + cs.getf1101(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.供电：交付时纳入城市供电网络并正式供电， ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" *" + cs.getf1102(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("3.供暖：交付时供热系统符合供热配建标准，使用城市集中供热的，纳入城市集中供热管网， ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" *" + cs.getf1103(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("4.燃气：交付时完成室内燃气管道的敷设，并与城市燃气管网连接，保证燃气供应， ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" *" + cs.getf1104(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("5.电话通信：交付时线路敷设到户； ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("6.有线电视：交付时线路敷设到户； ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("7.宽带网络：交付时线路敷设到户。  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("以上第1、2、3项由出卖人负责办理开通手续并承担相关费用；第4、5、6、7项需要买受人自行办理开通手续。  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("如果在约定期限内基础设施设备未达到交付使用条件，双方同意按照下列第" + " *" + cs.getf1105() + " 种方式处理： ", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document
						.add(getParagraph("（1）以上设施中第1、2、3、4项在约定交付日未达到交付条件的，出卖人按照本合同第十三条的约定承担逾期交付责任。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("第5项未按时达到交付使用条件的，出卖人按日向买受人支付" + " *" + cs.getf1106() + " 元的违约金；第6项未按时达到交付使用条件的， 出卖人按日向买受人支付" + " *" + cs.getf1107()
						+ " 元的违约金； 第7项未按时达到交付使用条件的，出卖人按日向买受人支付" + " *" + cs.getf1108() + " 元的违约金。 出卖人采取措施保证相关设施于约定交付日后" + " *" + cs.getf1109()
						+ " 日之内达到交付使用条件。 ",
						setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（2）" + " *" + cs.getf1110(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（二）公共服务及其他配套设施（以建设工程规划许可为准） ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("1.小区内绿地率：" + substringDate(cs.getf1111(), 1) + " 年" + substringDate(cs.getf1111(), 2) + " 月"
						+ substringDate(cs.getf1111(), 3) + " 日达到" + " *" + cs.getf1112() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.小区内非市政道路：" + substringDate(cs.getf1113(), 1) + " 年" + substringDate(cs.getf1113(), 2) + " 月"
						+ substringDate(cs.getf1113(), 3) + " 日达到：" + " *" + cs.getf1114() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("3.规划的车位、车库：" + substringDate(cs.getf1115(), 1) + " 年" + substringDate(cs.getf1115(), 2) + " 月"
						+ substringDate(cs.getf1115(), 3) + " 日达到：" + " *" + cs.getf1116() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("4.物业服务用房：" + substringDate(cs.getf1117(), 1) + " 年" + substringDate(cs.getf1117(), 2) + " 月"
						+ substringDate(cs.getf1117(), 3) + " 日达到：" + " *" + cs.getf1118() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("5、医疗卫生机构：" + substringDate(cs.getf1119(), 1) + " 年" + substringDate(cs.getf1119(), 2) + " 月"
						+ substringDate(cs.getf1119(), 3) + " 日达到：" + " *" + cs.getf1120() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("6.幼儿园：" + substringDate(cs.getf1121(), 1) + " 年" + substringDate(cs.getf1121(), 2) + " 月"
						+ substringDate(cs.getf1121(), 3) + " 日达到：" + " *" + cs.getf1122() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("7.学校：" + substringDate(cs.getf1123(), 1) + " 年" + substringDate(cs.getf1123(), 2) + " 月"
						+ substringDate(cs.getf1123(), 3) + " 日达到：" + " *" + cs.getf1124() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("8." + " *" + cs.getf1125() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("9." + " *" + cs.getf1126() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("以上设施未达到上述条件的，双方同意按照以下方式处理： ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("1.小区内绿地率未达到上述约定条件的，" + " *" + cs.getf1127() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.小区内非市政道路未达到上述约定条件的，" + " *" + cs.getf1128() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("3.规划的车位、车库未达到上述约定条件的，" + " *" + cs.getf1129() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("4.物业服务用房未达到上述约定条件的，" + " *" + cs.getf1130() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("5.其他设施未达到上述约定条件的，" + " *" + cs.getf1131() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("关于本项目内相关设施设备的具体约定见附件六。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

				document.add(getParagraph("^第十二条 交付时间和手续"));
				document.add(getParagraph("(一)出卖人应当在" + substringDate(cs.getf1201(), 1) + " 年" + substringDate(cs.getf1201(), 2) + " 月"
						+ substringDate(cs.getf1201(), 3) + " 日前向买受人交付该商品房。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("(二)该商品房达到第十条、第十一条约定的交付条件后，出卖人应当在交付日期届满前" + " *" + cs.getf1202()
						+ " 日（不少于10日）将查验房屋的时间、办理交付手续的时间地点以及应当携带的证件材料的通知书面送达买受人。买受人未收到交付通知书的，以本合同约定 的交付日期届满之日为办理交付手续的时间，以该商品房所在地为办理交付手续的地点。 ", setChinese(FS14),
						SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" *" + cs.getf1203() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("交付该商品房时，出卖人应当出示满足第十条约定的证明文件。出卖人不出示证明文件或者出示的证明文件不齐全，不能满足第十条约定条件的，买受人有权拒绝接收，由此产生的逾期交付责任由出卖人承担，并按照第十三条处理。",
						setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("(三) 查验房屋 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("1.办理交付手续前，买受人有权对该商品房进行查验，出卖人不得以缴纳相关税 费或者签署物业管理文件作为买受人查验和办理交付手续的前提条件。 ", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph(
						"2.买受人查验的该商品房存在下列除地基基础和主体结构外的其他质量问题的， 由出卖人按照有关工程和产品质量规范、标准自查验次日起" + " *" + cs.getf1204() + " 日内负责修复，并承担修复费用，修复后再行交付。",
						setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（1）屋面、墙面、地面渗漏或开裂等； ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（2）管道堵塞； ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（3）门窗翘裂、五金件损坏；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（4）灯具、电器等电气设备不能正常使用； ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（5）" + " *" + cs.getf1205() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（6）" + " *" + cs.getf1206() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("3.查验该商品房后，双方应当签署商品房交接单。由于买受人原因导致该商品房 未能按期交付的，双方同意按照以下方式处理： ", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("（1）" + " *" + cs.getf1207() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（2）" + " *" + cs.getf1208() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));

				document.add(getParagraph("^第十三条 逾期交付责任 "));
				document.add(getParagraph("（除不可抗力外，出卖人未按照第十二条约定的时间将该商品房交付买受人的，双 方同意按照下列第" + " *" + cs.getf1301() + " 种方式处理：", setChinese(FS14), SPB28, 25.0f,
						0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("1.按照逾期时间，分别处理（（1）和（2）不作累加）。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（1）逾期在" + " *" + cs.getf1302() + " 日之内（该期限应当不多于第九条第1 （1）项中的期限），自第 十二条约定的交付期限届满之次日起至实际交付之日止，出卖人按日计算向买受人支付全部房价款万分之"
						+ " *" + cs.getf1303() + " 的违约金（该违约金比率应当不低于第九条第1 （1）项中的比率）。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（2）逾期超过" + " *" + cs.getf1304()
						+ " 日（该期限应当与本条第（1）项中的期限相同）后，买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分）， 并自买受人付款之日起，按照" + " *"
						+ cs.getf1305() + " %（不低于中国人民银行公布的同期贷款基准利率）计算给付利息； 同时，出卖人按照全部房价款的" + " *" + cs.getf1306() + " %向买受人支付违约金。", setChinese(FS14), SPB28,
						25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("买受人要求继续履行合同的，合同继续履行，出卖人按日计算向买受人支付全部 房价款万分之" + " *" + cs.getf1307() + " （该比率应当不低于本条第1 （1）项中的比率）的违约金。",
						setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2、" + " *" + cs.getf1308() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				p = new Paragraph("第五章 商品房质量及保修责任", setChinese(FS14, Font.BOLD));
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingBefore(20);
				p.setSpacingAfter(20);
				document.add(p);
				document.add(getParagraph("^第十四条 商品房质量 "));
				document.add(getParagraph("（一）地基基础和主体结构", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("出卖人承诺该商品房地基基础和主体结构合格，并符合国家及行业标准。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("经检测不合格的，买受人有权解除合同。买受人解除合同的，应当书面通知出卖 人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付 贷款部分），并自买受人付款之日起， 按照" + " *"
						+ cs.getf1401() + " %（不低于中国人民银行公布的同 期贷款基准利率）计算给付利息。给买受人造成损失的，由出卖人支付" + " *" + cs.getf1402() + " 的赔偿金。因此而发生的检测费用由出卖人承担。",
						setChinese(FS14), SPB28, 25.0f,
						0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("买受人不解除合同的，" + " *" + cs.getf1403() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（二）其他质量问题", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("该商品房质量应当符合有关工程质量规范、标准和施工图设计文件的要求。发现除地基基础和主体结构外质量问题的，双方按照以下方式处理：", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("（1）及时更换、修理；如给买受人造成损失的，还应当承担相应赔偿责任。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" *" + cs.getf1404(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（2）经过更换、修理，仍然严重影响正常使用的，买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退 还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起， 按照"
						+ " *" + cs.getf1405() + " %（不低于中国人民银行公布的同期贷款基准利率）计算给付利息。给买受人造成损失 的，由出卖人承担相应赔偿责任。因此而发生的检测费用由出卖人承担。", setChinese(FS14), SPB28, 25.0f,
						0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("买受人不解除合同的，" + " *" + cs.getf1406() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（三）装饰装修及设备标准", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" 该商品房应当使用合格的建筑材料、构配件和设备，装置、装修、装饰所用材料的产品质量必须符合国家的强制性标准及双方约定的标准。 ", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("不符合上述标准的，买受人有权要求出卖人按照下列第（1） 、" + " *" + cs.getf1407() + "  方式处理（可多选）：",
						setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（1）及时更换、修理；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（2）出卖人赔偿双倍的装饰、设备差价；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（3）" + " *" + cs.getf1408() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（4）" + " *" + cs.getf1409() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("具体装饰装修及相关设备标准的约定见附件七。 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（四）室内空气质量、建筑隔声和民用建筑节能措施", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("1.该商品房室内空气质量符合" + " *" + cs.getf1410() + "   标准， 标准名称：" + " *" + cs.getf1411() + " ， 标准文号：" + " *" + cs.getf1412()
						+ " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("该商品房为住宅的，建筑隔声情况符合" + " *" + cs.getf1413() + "   标准， 标准名称：" + " *" + cs.getf1414() + " ， 标准文号：" + " *"
						+ cs.getf1415() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document
						.add(getParagraph(
								"该商品房室内空气质量或建筑隔声情况经检测不符合标准，由出卖人负责整改，整改后仍不符合标准的，买受人有权解除合同。买受人解除合同的，应当书面通知出 卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自买受人付款之日起， 按照"
										+ " *"
										+ cs.getf1416()
										+ " %（不低于中国人民银行公布的同期贷款基准利率）计算给付利息。给买受人造成损失的，由出卖人承担相应赔偿责任。经检测不符合标准的，检测费用由出卖人承担，整改后再次检测发生的费用仍由 出卖人承担。因整改导致该商品房逾期交付的，出卖人应当承担逾期交付责任。",
								setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.该商品房应当符合国家有关民用建筑节能强制性标准的要求。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("未达到标准的，出卖人应当按照相应标准要求补做节能措施，并承担全部费用；给买受人造成损失的，出卖人应当承担相应赔偿责任。", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph(" *" + cs.getf1417() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("^第十五条 保修责任 "));
				document.add(getParagraph("（一）商品房实行保修制度。该商品房为住宅的，出卖人自该商品房交付之日起，按照《住宅质量保证书》承诺的内容承担相应的保修责任。该商品房为非住宅的，双方应当签订补充协议详细约定保修范围、保修期限和保修责任等内容。具体内容见附件八。",
						setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（二）下列情形，出卖人不承担保修责任：", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("1.因不可抗力造成的房屋及其附属设施的损害；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.因买受人不当使用造成的房屋及其附属设施的损害；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("3." + " *" + cs.getf1501() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（三）在保修期内，买受人要求维修的书面通知送达出卖人" + " *" + cs.getf1502()
						+ " 日内，出卖人既不履行保修义务也不提出书面异议的，买受人可以自行或委托他人进行维修，维修费用及维修期间造成的其他损失由出卖人承担。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("^第十六条 质量担保 "));
				document.add(getParagraph("出卖人不按照第十四条、第十五条约定承担相关责任的， 由" + " *" + cs.getf1601() + " 承担连带责任。", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("关于质量担保的证明见附件九。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				p = new Paragraph("第六章 房屋登记", setChinese(FS14, Font.BOLD));
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingBefore(20);
				p.setSpacingAfter(20);
				document.add(p);
				document.add(getParagraph("^第十七条 房屋登记 "));
				document.add(getParagraph("（一）双方同意共同向房屋登记机构申请办理该商品房的房屋所有权转移登记。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（二）因出卖人的原因，买受人未能在该商品房交付之日起" + " *" + cs.getf1701() + " 日内取得该商品房的房屋所有权证书的， 双方同意按照下列第" + " *" + cs.getf1702()
						+ " 种方式处理： ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(
"1.买受人有权解除合同。买受人解除合同的，应当书面通知出卖人。出卖人应当自解除合同通知送达之日起15日内退还买受人已付全部房款（含已付贷款部分），并自 买受人付款之日起， 按照" + " *" + cs.getf1703()
						+ " % （不低于中国人民银行公布的同期贷款基准利率） 计算给付利息。买受人不解除合同的，自买受人应当完成房屋所有权登记的期限届满之次日起至实际完成房屋所有权登记之日止， 出卖人按日计算向买受人支付全部房价款万分之" + " *" + cs.getf1704()
						+ " 的违约金。",
						setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2." + " *" + cs.getf1705() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（三）因买受人的原因未能在约定期限内完成该商品房的房屋所有权转移登记的， 出卖人不承担责任。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				p = new Paragraph("第七章 物业管理", setChinese(FS14, Font.BOLD));
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingBefore(20);
				p.setSpacingAfter(20);
				document.add(p);
				document.add(getParagraph("^第十八条 物业管理 "));
				document.add(getParagraph("(一)出卖人依法选聘的前期物业服务企业为" + " *" + cs.getf1801() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("(二)物业服务时间从" + substringDate(cs.getf1802(), 1) + " 年" + substringDate(cs.getf1802(), 2) + " 月"
						+ substringDate(cs.getf1802(), 3) + " 日到" + substringDate(cs.getf1803(), 1) + " 年" + substringDate(cs.getf1803(), 2) + " 月"
						+ substringDate(cs.getf1803(), 3) + " 日。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("(三)物业服务期间，物业收费计费方式为" + " *" + cs.getf1804() + "  。物业 服务费为" + " *" + cs.getf1805() + " 元/月·平方米（建筑面积）。",
						setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("(四)买受人同意由出卖人选聘的前期物业服务企业代为查验并承接物业共用部位、共用设施设备，出卖人应当将物业共用部位、共用设施设备承接查验的备案情况书面告知买受人。", setChinese(FS14), SPB28, 25.0f,
						0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("(五)买受人已详细阅读前期物业服务合同和临时管理规约，同意由出卖人依法选聘的物业服务企业实施前期物业管理，遵守临时管理规约。", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("(六)业主大会设立前适用该章约定。业主委员会成立后，由业主大会决定选聘或续聘物业服务企业。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("该商品房前期物业服务合同、临时管理规约见附件十。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				p = new Paragraph("第八章 其他事项", setChinese(FS14, Font.BOLD));
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingBefore(20);
				p.setSpacingAfter(20);
				document.add(p);
				document.add(getParagraph("^第十九条 建筑物区分所有权 "));
				document.add(getParagraph("(一)买受人对其建筑物专有部分享有占有、使用、收益和处分的权利。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("(二)以下部位归业主共有：", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("1.建筑物的基础、承重结构、外墙、屋顶等基本结构部分，通道、楼梯、大堂等 公共通行部分，消防、公共照明等附属设施、设备，避难层、设备层或者设备间等结构部分；", setChinese(FS14), SPB28, 25.0f,
						0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.该商品房所在建筑区划内的道路（属于城镇公共道路的除外）、绿地（属于城镇公共绿地或者明示属于个人的除外）、占用业主共有的道路或者其他场地用于停放汽车的车位、物业服务用房；", setChinese(FS14), SPB28,
						25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("3." + " *" + cs.getf1901() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("(三)双方对其他配套设施约定如下:", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("1.规划的车位、车库：" + " *" + cs.getf1902() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2. 会所：" + " *" + cs.getf1903() + " ；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("3." + " *" + cs.getf1904() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("^第二十条 税费 "));
				document.add(getParagraph("双方应当按照国家的有关规定，向相应部门缴纳因该商品房买卖发生的税费。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("^第二十一条 销售和使用承诺 "));
				document.add(getParagraph("1.出卖人承诺不采取分割拆零销售、返本销售或者变相返本销售的方式销售商品房。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.出卖人承诺按照规划用途进行建设和出售，不擅自改变该商品房使用性质，并按照规划用途办理房屋登记。出卖人不得擅自改变与该商品房有关的共用部位和设施的使用性质。", setChinese(FS14), SPB28, 25.0f,
						0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("3.出卖人承诺对商品房的销售，不涉及依法或者依规划属于买受人共有的共用部位和设施的处分。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("4.出卖人承诺已将遮挡或妨碍房屋正常使用的情况告知买受人。具体内容见附件十一。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("5.买受人使用该商品房期间，不得擅自改变该商品房的用途、建筑主体结构和承重结构。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("6." + " *" + cs.getf2101() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("7." + " *" + cs.getf2102() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("^第二十二条 送达 "));
				document.add(getParagraph("出卖人和买受人保证在本合同中记载的通讯地址、联系电话均真实有效。任何根据本合同发出的文件，均应采用书面形式，以" + " *" + cs.getf2201()
						+ " 方式送达对方。任何一方变更通讯地址、联系电话的， 应在变更之日起" + " *" + cs.getf2202() + " 日内书面通知对方。变更的一方未履行通知义务导致送达不能的，应承担相应的法律责任。", setChinese(FS14), SPB28,
						25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("^第二十三条 买受人信息保护 "));
				document.add(getParagraph(
						"出卖人对买受人信息负有保密义务。非因法律、法规规定或国家安全机关、公安机关、检察机关、审判机关、纪检监察部门执行公务的需要，未经买受人书面同意， 出卖人及其销售人员和相关工作人员不得对外披露买受人信息，或将买受人信息用于履行本合同之外的其他用途。",
						setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("^第二十四条 争议解决方式 "));
				document.add(getParagraph("本合同在履行过程中发生的争议，由双方当事人协商解决，也可通过消费者协会等相关机构调解；或按照下列第" + " *" + cs.getf2401() + " 种方式解决：", setChinese(FS14), SPB28,
						25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("1.依法向房屋所在地人民法院起诉。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.提交" + " *" + cs.getf2402() + " 仲裁委员会仲裁。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("^第二十五条 补充协议 "));
				document.add(getParagraph("对本合同中未约定或约定不明的内容，双方可根据具体情况签订书面补充协议（补充协议见附件十二）。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("补充协议中含有不合理的减轻或免除本合同中约定应当由出卖人承担的责任，或不合理的加重买受人责任、排除买受人主要权利内容的，仍以本合同为准。", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("^第二十六条 合同生效 "));
				document.add(getParagraph("本合同自双方签字或盖章之日起生效。本合同的解除应当采用书面形式。 本合同及附件共" + " *" + cs.getf2601() + " 页， 一式" + " *" + cs.getf2602() + " 份， 其中出卖人"
						+ " *" + cs.getf2603() + " 份， 买受人" + " *" + cs.getf2604() + " 份， 【" + " *" + cs.getf2605() + " 】 " + " *" + cs.getf2606() + " 份， 【"
						+ " *" + cs.getf2607() + " 】 " + " *" + cs.getf2608() + " 份。合同附件与本合同具有同等法律效力。", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));

				document.newPage();

				p = new Paragraph(" ");
				p.setSpacingAfter(200);
				document.add(p);

				PdfPTable table = new PdfPTable(4);
				table.setTotalWidth(523);
				int[] width2 = { 25, 25, 25, 25 };
				table.setWidths(width2);
				table.setLockedWidth(true);
				PdfPCell cell1 = new PdfPCell(new Paragraph("出卖人（签字或盖章）：", setChinese(FS10)));
				cell1.setColspan(2);
				cell1.setMinimumHeight(50);
				cell1.setBorder(0);

				PdfPCell cell3 = new PdfPCell(new Paragraph("买受人（签字或盖章）：", setChinese(FS10)));
				cell3.setColspan(2);
				cell3.setMinimumHeight(50);
				cell3.setBorder(0);

				PdfPCell cell5 = new PdfPCell(new Paragraph("【法定代表人】（签字或盖章）：", setChinese(FS10)));
				cell5.setColspan(2);
				cell5.setMinimumHeight(50);
				cell5.setBorder(0);
				PdfPCell cell6 = new PdfPCell(new Paragraph("【法定代表人】（签字或盖章）： ", setChinese(FS10)));
				cell6.setColspan(2);
				cell6.setMinimumHeight(50);
				cell6.setBorder(0);
				PdfPCell cell7 = new PdfPCell(new Paragraph("【委托代理人】（签字或盖章）：", setChinese(FS10)));
				cell7.setColspan(2);
				cell7.setMinimumHeight(50);
				cell7.setBorder(0);
				PdfPCell cell8 = new PdfPCell(new Paragraph("【委托代理人】（签字或盖章）：", setChinese(FS10)));
				cell8.setColspan(2);
				cell8.setMinimumHeight(50);
				cell8.setBorder(0);
				PdfPCell cell9 = new PdfPCell(new Paragraph("", setChinese(FS14)));
				cell9.setColspan(2);
				cell9.setMinimumHeight(50);
				cell9.setBorder(0);
				PdfPCell cell10 = new PdfPCell(new Paragraph("【法定代理人】（签字或盖章）：", setChinese(FS10)));
				cell10.setColspan(2);
				cell10.setMinimumHeight(50);
				cell10.setBorder(0);
				PdfPCell cell11 = new PdfPCell(new Paragraph("日期：______ 年 ___月___日 ", setChinese(FS10)));
				cell11.setColspan(2);
				cell11.setMinimumHeight(50);
				cell11.setBorder(0);
				PdfPCell cell12 = new PdfPCell(new Paragraph("日期：______年 ___月___日 ", setChinese(FS10)));
				cell12.setColspan(2);
				cell12.setMinimumHeight(50);
				cell12.setBorder(0);
				PdfPCell cell13 = new PdfPCell(new Paragraph("签于：________________", setChinese(FS10)));
				cell13.setColspan(2);
				cell13.setMinimumHeight(50);
				cell13.setBorder(0);
				PdfPCell cell14 = new PdfPCell(new Paragraph("签于：________________", setChinese(FS10)));
				cell14.setColspan(2);
				cell14.setMinimumHeight(50);
				cell14.setBorder(0);
				table.addCell(cell1);
				table.addCell(cell3);
				table.addCell(cell5);
				table.addCell(cell6);
				table.addCell(cell7);
				table.addCell(cell8);
				table.addCell(cell9);
				table.addCell(cell10);
				table.addCell(cell11);
				table.addCell(cell12);
				table.addCell(cell13);
				table.addCell(cell14);
				document.add(table);

				if(cdvo.getStatus() >= 2){
					//甲方拟签人员信息
					SignerVO signerVO;
					if(cdvo.getSigner() != null && !"".equals(cdvo.getSigner())){
						signerVO = (SignerVO) cqDao.find(SignerVO.class, Long.parseLong(cdvo.getSigner()));
						PdfPTable table14 = new PdfPTable(4);
						int width14[] = { 21, 29, 21, 29 };
						table14.setWidths(width14);
						table14.setTotalWidth(540);
						table14.setLockedWidth(true);
						PdfPCell cell141 = new PdfPCell(new Paragraph("甲方拟签人员：", setChinese(FS10)));
						PdfPCell cell142 = new PdfPCell(new Paragraph(signerVO.getName(), setChinese(FS10)));
						PdfPCell cell143 = new PdfPCell(new Paragraph("销售员证书号：", setChinese(FS10)));
						PdfPCell cell144 = new PdfPCell(new Paragraph(signerVO.getCardCode(), setChinese(FS10)));
						cell141.setBorder(0);
						cell143.setBorder(0);
						cell142.setBorder(0);
						cell144.setBorder(0);
						table14.addCell(cell141);
						table14.addCell(cell142);
						table14.addCell(cell143);
						table14.addCell(cell144);
						document.add(table14);
						Paragraph blankRow14 = new Paragraph(12f, " ", setChinese(FS10));
						document.add(blankRow14);
					}
					//甲方确认人员信息
					SignerVO signerVO1;
					if(cdvo.getConfirmer() != null && !"".equals(cdvo.getConfirmer())){
						signerVO1 = (SignerVO) cqDao.find(SignerVO.class, Long.parseLong(cdvo.getConfirmer()));
						PdfPTable table15 = new PdfPTable(4);
						int width15[] = { 21, 29, 21, 29 };
						table15.setWidths(width15);
						table15.setTotalWidth(540);
						table15.setLockedWidth(true);
						PdfPCell cell151 = new PdfPCell(new Paragraph("甲方确认人员：", setChinese(FS10)));
						PdfPCell cell152 = new PdfPCell(new Paragraph(signerVO1.getName(), setChinese(FS10)));
						PdfPCell cell153 = new PdfPCell(new Paragraph("销售员证书号：", setChinese(FS10)));
						PdfPCell cell154 = new PdfPCell(new Paragraph(signerVO1.getCardCode(), setChinese(FS10)));
						cell151.setBorder(0);
						cell153.setBorder(0);
						cell152.setBorder(0);
						cell154.setBorder(0);
						table15.addCell(cell151);
						table15.addCell(cell152);
						table15.addCell(cell153);
						table15.addCell(cell154);
						document.add(table15);
						Paragraph blankRow15 = new Paragraph(12f, " ", setChinese(FS10));
						document.add(blankRow15);
					}

					//网签合同时间
					String confirmDate = cdvo.getConfirmDate();
					if(confirmDate != null && !"".equals(confirmDate) && !"0".equals(confirmDate)){
						PdfPTable table16 = new PdfPTable(2);
						int width16[] = { 26, 74 };
						table16.setWidths(width16);
						table16.setTotalWidth(540);
						table16.setLockedWidth(true);
						PdfPCell cell161 = new PdfPCell(new Paragraph("网上合同签订时间：", setChinese(FS10)));
						PdfPCell cell162 = new PdfPCell(new Paragraph(sdf.format(DateUtil.parseDateTime2(confirmDate)), setChinese(FS10)));
						cell161.setBorder(0);
						cell162.setBorder(0);
						table16.addCell(cell161);
						table16.addCell(cell162);
						document.add(table16);
						Paragraph blankRow16 = new Paragraph(12f, " ", setChinese(FS10));
						document.add(blankRow16);
					}
				}

				document.newPage();

				p = new Paragraph();
				c = new Chunk("附  件  一    房屋平面图", setChinese(FS14, Font.BOLD));
				p.add(c);
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingAfter(SPB28);
				document.add(p);
				document.add(getParagraph("1.房屋分层分户图。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.建设工程规划方案总平面图。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				HouseVO hvo=(HouseVO)pdfDAO.find(HouseVO.class, cdvo.getHouseID());
				if(hvo.getSell_Plan_ID()!=null && !"".equals(hvo.getSell_Plan_ID())){
					File file1 = new File(tempPdfPath); //创建临时存放pdf的文件夹
					if(!file1.exists()){
						file1.mkdirs();
					}
					File file2 = new File(tempImgPath); //创建临时存放图片的文件夹
					if(!file2.exists()){
						file2.mkdirs();
					}
					String demo = hvo.getSell_Plan_ID() + "-" + String.valueOf(System.currentTimeMillis());
					getFile(hvo.getSell_Plan_ID(), demo);
					boolean flag = pdf2Pic(tempPdfPath + demo + ".pdf", tempImgPath + demo);
					if(flag == true){
						Image image = Image.getInstance(tempImgPath + demo + ".png");
						image.scaleAbsolute(523f, 500f);
						image.setAlignment(Element.ALIGN_CENTER);
						document.add(image);

						File pdfFile = new File(tempPdfPath + demo + ".pdf");
						//pdfFile.deleteOnExit();

						File imageFile = new File(tempImgPath + demo + ".png");
						//imageFile.deleteOnExit();
						deleteFile(pdfFile);
						deleteFile(imageFile);
					}
				}
				document.newPage();

				p = new Paragraph();
				c = new Chunk("附  件  二    关于该商品房共用部位的具体说明（可附图说明）", setChinese(FS14, Font.BOLD));
				p.add(c);
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingAfter(SPB28);
				document.add(p);
				document.add(getParagraph("1.纳入该商品房分摊的共用部位的名称、面积和所在位置。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.未纳入该商品房分摊的共用部位的名称、所在位置。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(new Paragraph(FJ2, setChinese(FS14)));

				document.newPage();

				p = new Paragraph();
				c = new Chunk("附  件  三    抵押权人同意该商品房转让的证明及关于抵押的相关约定", setChinese(FS14, Font.BOLD));
				p.add(c);
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingAfter(SPB28);
				document.add(p);
				document.add(getParagraph("1.抵押权人同意该商品房转让的证明。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.解除抵押的条件和时间。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("3.关于抵押的其他约定。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(new Paragraph(FJ3, setChinese(FS14)));

				document.newPage();

				p = new Paragraph();
				c = new Chunk("附  件  四    出卖人提供的承租人放弃优先购买权的声明", setChinese(FS14, Font.BOLD));
				p.add(c);
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingAfter(SPB28);
				document.add(p);
				p = new Paragraph();
				p.setSpacingBefore(SPB28 * 3);
				document.add(p);
				document.add(new Paragraph(FJ4, setChinese(FS14)));
				document.newPage();

				p = new Paragraph();
				c = new Chunk("附  件  五    关于该商品房价款的计价方式、总价款、付款方式及期限的具体约定", setChinese(FS14, Font.BOLD));
				p.add(c);
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingAfter(SPB28);
				document.add(p);
				document.add(new Paragraph(FJ5, setChinese(FS14)));
				document.newPage();

				p = new Paragraph();
				c = new Chunk("附  件  六    关于本项目内相关设施、设备的具体约定", setChinese(FS14, Font.BOLD));
				p.add(c);
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingAfter(SPB28);
				document.add(p);
				document.add(getParagraph("1.相关设施的位置及用途", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.其他约定 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(new Paragraph(FJ6, setChinese(FS14)));

				document.newPage();


				p = new Paragraph();
				c = new Chunk("附  件  七    关于装饰装修及相关设备标准的约定", setChinese(FS14, Font.BOLD));
				p.add(c);
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingAfter(SPB28);
				document.add(p);
				document.add(getParagraph("交付的商品房达不到本附件约定装修标准的，按照本合同第十四条第（三）款约定处理。出卖人未经双方约定增加的装置、装修、装饰，视为无条件赠送给买受人。", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("双方就装饰装修主要材料和设备的品牌、产地、规格、数量等内容约定如下：", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("1.外墙：" + " *" + cs.getf9001() + " ；    *" + cs.getf9002() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("2.起居室：", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（1）内墙：" + " *" + cs.getf9003() + " ；  *" + cs.getf9004() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("（2）顶棚：" + " *" + cs.getf9005() + " ；   *" + cs.getf9006() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("（3）室内地面：" + " *" + cs.getf9007() + " ；    *" + cs.getf9008() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("3.厨房：", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（1）地面：" + " *" + cs.getf9009() + " ；     *" + cs.getf9010() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("（2）墙面：" + " *" + cs.getf9011() + " ；     *" + cs.getf9012() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("（3）顶棚：" + " *" + cs.getf9013() + " ；      *" + cs.getf9014() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("（4）厨具：" + " *" + cs.getf9015() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("4.卫生间：", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（1）地面：" + " *" + cs.getf9016() + " ；     *" + cs.getf9017() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("（2）墙面：" + " *" + cs.getf9018() + " ；      *" + cs.getf9019() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("（3）顶棚：" + " *" + cs.getf9020() + " ；      *" + cs.getf9021() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("（4）卫生器具：" + " *" + cs.getf9022() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("5.阳台：" + " *" + cs.getf9023() + " ；     *" + cs.getf9024() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("6.电梯：", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（1）品牌：" + " *" + cs.getf9025() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("（2）型号：" + " *" + cs.getf9026() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("7.管道：      *" + cs.getf9027() + " 。", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("8.窗户：      *" + cs.getf9028() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("9.其他：    *" + cs.getf9029(), setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.newPage();

				p = new Paragraph();
				c = new Chunk("附  件  八    关于保修范围、保修期限和保修责任的约定", setChinese(FS14, Font.BOLD));
				p.add(c);
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingAfter(SPB28);
				document.add(p);
				document.add(getParagraph("该商品房为住宅的，出卖人应当提供《住宅质量保证书》；该商品房为非住宅的，双方可参照《住宅质量保证书》中的内容对保修范围、保修期限和保修责任等进行约定。", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("该商品房的保修期自房屋交付之日起计算，关于保修期限的约定不应低于《建设工程质量管理条例》第四十条规定的最低保修期限。", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph("(一)保修项目、期限及责任的约定 ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("1.地基基础和主体结构：", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("保修期限为：" + " *" + cs.getf9101() + " (不得低于设计文件规定的该工程的合理使用年限)", setChinese(FS14), SPB28, 25.0f, 0.0f,
						Element.ALIGN_LEFT));
				document.add(getParagraph(" *" + cs.getf9102() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.屋面防水工程、有防水要求的卫生间、房间和外墙面的防渗漏：", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("保修期限为：" + " *" + cs.getf9103() + " (不得低于5年)", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" *" + cs.getf9104() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("3.供热、供冷系统和设备：", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("保修期限为：" + " *" + cs.getf9105() + " (不得低于2个采暖期、供冷期)", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" *" + cs.getf9106() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("4.电气管线、给排水管道、设备安装：", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("保修期限为：" + " *" + cs.getf9107() + " (不得低于2年)；", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" *" + cs.getf9108() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("5.装修工程：", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("保修期限为：" + " *" + cs.getf9109() + " (不得低于2年)", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" *" + cs.getf9110() + " 。", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("6.其他：", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" *" + cs.getf9111(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("(二)其他约定", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph(" *" + cs.getf9112(), setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.newPage();

				p = new Paragraph();
				c = new Chunk("附  件  九    关于质量担保的证明", setChinese(FS14, Font.BOLD));
				p.add(c);
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingAfter(SPB28);
				document.add(p);
				document.add(new Paragraph(FJ9, setChinese(FS14)));
				document.newPage();

				p = new Paragraph();
				c = new Chunk("附  件  十    关于物业管理的约定", setChinese(FS14, Font.BOLD));
				p.add(c);
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingAfter(SPB28);
				document.add(p);
				document.add(getParagraph("1.前期物业服务合同", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(getParagraph("2.临时管理规约 ", setChinese(FS10), SPB20, 20.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(new Paragraph(FJ10, setChinese(FS10)));
				document.newPage();

				p = new Paragraph();
				c = new Chunk("附  件  十  一    出卖人关于遮挡或妨碍房屋正常使用情况的说明", setChinese(FS14, Font.BOLD));
				p.add(c);
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingAfter(SPB28);
				document.add(p);
				document.add(getParagraph("(如:该商品房公共管道检修口、柱子、变电箱等有遮挡或妨碍房屋正常使用的情况)", setChinese(FS10), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
				document.add(new Paragraph(FJ11, setChinese(FS10)));
				document.newPage();

				p = new Paragraph();
				c = new Chunk("附  件  十  二    补充协议", setChinese(FS14, Font.BOLD));
				p.add(c);
				p.setAlignment(Element.ALIGN_CENTER);
				p.setSpacingAfter(SPB28);
				document.add(p);
				document.add(new Paragraph(FJ12, setChinese(FS10)));
				document.newPage();


				List<MetaFilter> xgParams = new ArrayList<MetaFilter>();
				xgParams.add(new MetaFilter("contractID", "=", contractID));
				List<MetaOrder> orders = new ArrayList<MetaOrder>();
				orders.add(new MetaOrder("contractID", MetaOrder.ASC));
				//查找限制的信息返回list
				List<XgLimitSaleContractVO> xgList = pdfDAO.search(XgLimitSaleContractVO.class, xgParams, orders, new Page());
				if(xgList != null && xgList.size() > 0){
					p = new Paragraph();
					c = new Chunk("附  件  十  三    购房资格查验证明", setChinese(FS14, Font.BOLD));
					p.add(c);
					document.add(p);
					p = new Paragraph("-------------------------------------------------------------------------------");
					p.setAlignment(Element.ALIGN_CENTER);
					p.setSpacingBefore(5);
					p.setSpacingAfter(SPB28);
					document.add(p);
					for(XgLimitSaleContractVO xgvo : xgList){
						document.add(getParagraph("编号：" + xgvo.getLimitSaleID() + "  ", setChinese(FS14), SPB28, 25.0f, 0.0f, Element.ALIGN_LEFT));
					}
				}

				document.close();

				ContractPdfVO ctv = new ContractPdfVO();
				ctv.setPdfData(baos.toByteArray());
				baos.close();
				return ctv;
			}
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(cqDao);
			closeDAO(pdfDAO);
		}
		return null;
	}

	/**
	 * 功能描述：截取时间字符串
	 * @param date
	 * @param status
	 * @return
	 */
	public String substringDate(String date,int status) {
		String time = "________";
		if(date != null){
			if(date.replaceAll("-", "").length() == 8){
				date = date.replaceAll("-", "");
				if(status == 1){
					time = " *" + date.substring(0, 4);
				}
				if(status == 2){
					time = " *" + date.substring(4, 6);
				}
				if(status == 3){
					time = " *" + date.substring(6, 8);
				}
			}else{
				if(date.length() >= 8 && date.length() <= 10){
					String[] str = date.split("-");
					if(str != null && str.length == 3){
						if(status == 1){
							time = " *" + str[0];
						}
						if(status == 2){
							time = " *" + str[1];
						}
						if(status == 3){
							time = " *" + str[2];
						}
					}
				}
			}
		}
		return time;
	}

	//获取大写数字方式
	public String getRes(int num,int type) {
		String result = "";
		if(num == 1){
			if(type == 1){
				result = "壹";
			}
			if(type == 2){
				result = "一";
			}
		}else if(num == 2){
			if(type == 1){
				result = "贰";
			}
			if(type == 2){
				result = "二";
			}
		}else if(num == 3){
			if(type == 1){
				result = "叁";
			}
			if(type == 2){
				result = "三";
			}
		}else if(num == 4){
			if(type == 1){
				result = "肆";
			}
			if(type == 2){
				result = "四";
			}
		}
		return result;
	}

	/**
	 * 功能描述：获取字典表数据
	 * @param tableName
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String getDictName(String tableName,String code) throws Exception {
		String name = "";
		if(tableName == null || "".equals(tableName) || code == null || "".equals(code)){
			return name;
		}
		try{
			if(Constant.SysDictMap.containsKey(tableName.toUpperCase())){
				List<DictVO> list = Constant.SysDictMap.get(tableName.toUpperCase());
				if(list != null && list.size() > 0){
					for(DictVO vo : list){
						if(vo.getCode() == Integer.parseInt(code)){
							name = vo.getName();
							if(name == null){
								name = "";
							}
						}
					}
				}
			}

		}catch (Exception e){
			logger.error("获取字典表数据出现异常，" + e.getMessage());
			e.printStackTrace();
			throw e;
		}

		return name;
	}

	public String getDoubleStr(Double d) {
		if(d == 0 || d == 0.0){
			return " ";
		}else{
			return StringUtil.getDoubleStr(d);
		}
	}
	
	/**
     * 功能描述：强制清空所有买方人的短信验证码，验证标志，和签约时间
     * @param contractID
     * @return
     */
    public long updateBuyerInfo(long contractID) {
        long count = 0;
        try {
        	List<MetaField> whereFields = new ArrayList<MetaField>();
            whereFields.add(new MetaField("contractID",contractID));
            List<MetaField> fields = new ArrayList<MetaField>();
            fields.add(new MetaField("VERIFYCODE",null));
            fields.add(new MetaField("signflag",0));
            fields.add(new MetaField("signdate",null));
            this.update(BuyerInfoVO.class,whereFields,fields); 
            
           /* BuyerInfoVO buyerInfoVO = new BuyerInfoVO();
            buyerInfoVO.setContractID(contractID);
            buyerInfoVO.setVerifyCode(null);
            buyerInfoVO.setSignFlag(0);
            buyerInfoVO.setSignDate(null);
            cqBo.update(buyerInfoVO);*/
        } catch (Exception e) {
            System.out.println("Error at insertDocMessage():" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return count;
    }
    
    /*
	 * 功能描述：平板签字合成PDF
	 */
	public int signEnquirePic(String contractID,String base64Pic,String fingerImageBase64,String comp_code, String sealCode, String sealPassword) throws Exception{
		ContractQueryBO cqBo = new ContractQueryBO();
		ContractSignPdfVO cpvo = (ContractSignPdfVO) this.find(ContractSignPdfVO.class, Long.parseLong(contractID));
		int result = 1;
		int cnt = 1;
		String signLogo = "未找到签章位置";
		if(cpvo != null){
			//获取合同处理信息
			ContractDealVO cdVo = (ContractDealVO) cqBo.find(ContractDealVO.class, Long.parseLong(contractID));
			int type = cdVo.getType();
			if(type == 1){
				//预售合同
				if(FHConstant.NEW_PC_CONTRACTVERSION.equals(cdVo.getContractversion())){
					signLogo = "买受人（签字或盖章）：";
				}else{
					signLogo = "乙方（名称或名字）：";
				}
			}
			if(type == 2){
				//出售合同
				if(FHConstant.NEW_SC_CONTRACTVERSION.equals(cdVo.getContractversion())){
					signLogo = "买受人（签字或盖章）：";
				}else{
					signLogo = "乙方（名称或名字）：";
				}
			}
			if(type == 3){
				//定金合同
				signLogo = "乙方：（签定）：";
			}
			List<BuyerInfoVO> buyerList = cqBo.searchBuyerInfo(contractID);
			List<BuyerInfoVO> SignBuyerList = new ArrayList<BuyerInfoVO>();
			if(buyerList!=null&&buyerList.size()>0){
				for(int i = 0;i<buyerList.size();i++){
					BuyerInfoVO buyerInfoVO = buyerList.get(i);
					if(buyerInfoVO.getSignFlag()==1){
						SignBuyerList.add(buyerInfoVO);
					}
				}
				if(SignBuyerList!=null&&SignBuyerList.size()>0){
					cnt=SignBuyerList.size()+1;
				}
			}
			int offSetX1 = (cnt-1)*70+10;
			int offSetX2 = (cnt-1)*70+50;
			int offSetY = 0;
			int maxCnt=2;
			if(cnt>maxCnt){
				int zCnt = (int)Math.ceil((double)cnt/maxCnt);
				int num=maxCnt-(zCnt*maxCnt-cnt);
				offSetX1 = (num-1)*70+10;
				offSetX2 = (num-1)*70+50;
				offSetY = (zCnt-1)*(-35);
			}
			int buyCnt = cnt;
			offSetX1 = (buyCnt-1)*70+10;
			offSetX2 = (buyCnt-1)*70+50;
			offSetY = 0;
			if(buyCnt>maxCnt){
				int byuzCnt = (int)Math.ceil((double)buyCnt/maxCnt);
				int buyNum=maxCnt-(byuzCnt*maxCnt-buyCnt);
				offSetX1 = (buyNum-1)*70+10;
				offSetX2 = (buyNum-1)*70+50;
				offSetY = (byuzCnt-1)*(-35);
			}
				
				String filePath = cpvo.getPdfPath();
				//签名
				byte[] sealImage = Base64Util.decodeBufer(base64Pic);
				SignLocation signLocation = new SignLocation(signLogo, "R", offSetX1, offSetY);
				SealAutoPdf.signPdf(filePath, filePath, signLocation, sealImage, comp_code,sealCode,sealPassword);
				//指纹
				byte[] fingerImage = Base64Util.decodeBufer(fingerImageBase64);
				SignLocation signLocationfg = new SignLocation(signLogo, "R", offSetX2, offSetY);
				SealAutoPdf.signPdf(filePath, filePath, signLocationfg, fingerImage, comp_code,sealCode,sealPassword);
				
		}else{
			result = -1;
		}
		return result;
	}
}
