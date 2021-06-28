package com.netcom.nkestate.fhhouse.salecontractFHE.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cfca.com.itextpdf.text.Document;
import cfca.com.itextpdf.text.DocumentException;
import cfca.com.itextpdf.text.PageSize;
import cfca.com.itextpdf.text.Phrase;
import cfca.com.itextpdf.text.Rectangle;
import cfca.com.itextpdf.text.pdf.PdfPCell;
import cfca.com.itextpdf.text.pdf.PdfPTable;
import cfca.com.itextpdf.text.pdf.PdfWriter;

import com.netcom.nkestate.fhhouse.queryFHE.bo.ContractPdfBO;
import com.netcom.nkestate.fhhouse.queryFHE.bo.ContractQueryBO;
import com.netcom.nkestate.fhhouse.queryFHE.vo.ContractSignPdfVO;
import com.netcom.nkestate.fhhouse.salecontract.bo.SignContractBO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVOFHE;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellerInfoVO;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.util.DateUtil;

@Controller
@RequestMapping("/inner/ContractPdf")
public class CreateContractPdfAction {
	@RequestMapping("/doSearchText")
	//public static void main(String[] args) {
	public static void doSearchText(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		System.out.println("===========start=============");
		try{
			
			String contractID = request.getParameter("contractID");
			Document doc = createPdf("D:\\test\\"+contractID+".pdf");
			//生成  合同文件
			createFile(doc, contractID);
			doc.close();
			readPdf(request, session, response);
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("===========end=============");
	}


	/**
	 * 读pdf方法
	 * @param response
	 * @param contractID
	 * @param pdfPath
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	private static void readPdf(HttpServletRequest request,HttpSession session,HttpServletResponse response) throws NumberFormatException, Exception {

		/*
		 * FileInputStream in = new FileInputStream(new File(“c:/测试.pdf”)); OutputStream out = response.getOutputStream(); byte[] b = new byte[512]; while ((in.read(b))!=-1) { out.write(b); }
		 * out.flush(); in.close(); out.close();
		 */


		ContractQueryBO cqBo = new ContractQueryBO();
		ContractPdfBO cpBo = new ContractPdfBO();
		String contractID = request.getParameter("contractID");
		//获取合同pdf信息
		ContractSignPdfVO cpvo = (ContractSignPdfVO) cpBo.find(ContractSignPdfVO.class, Long.parseLong(contractID));
		String pdfPath = "D:\\test\\" + contractID + ".pdf";
		byte[] content = new byte[1024];
		System.out.println("合同[" + contractID + "]打印开始，访问已保存PDF文件！    " + DateUtil.getSysDate());
		OutputStream out = null;
		BufferedInputStream fin = null;
		try{
			out = response.getOutputStream();
			fin = new BufferedInputStream(new FileInputStream(pdfPath));
			int length;
			while ( (length = fin.read(content, 0, content.length)) != -1){
				out.write(content, 0, length);
			}
			fin.close();
			out.close();
			System.out.println("合同[" + contractID + "]打印结束，访问已保存PDF文件！    " + DateUtil.getSysDate());
		}catch (IOException e){
			System.out.println("Error at readPdf():" + e.getLocalizedMessage());
			e.printStackTrace();
		}finally{
			if(fin != null){
				fin.close();
			}
			if(out != null){
				out.close();
			}
		}
	}
	
	
	
	/**
	 * 创建一个pdf并打开
	 * @param outpath
	 * pdf路径
	 */
	public static Document createPdf(String outpath) throws DocumentException, IOException {
		FileOutputStream fos = new FileOutputStream(outpath);

		//页面大小
		//Rectangle rect = new Rectangle(PageSize.A4.rotate());//文档横方向
		Rectangle rect = new Rectangle(PageSize.A4);//文档竖方向
		//如果没有则创建
		File saveDir = new File(outpath);
		File dir = saveDir.getParentFile();
		if(!dir.exists()){
			dir.mkdirs();
		}
		Document doc = new Document(rect);
		//PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(outpath));
		//定义输出位置并把文档对象装入输出对象中
		PdfWriter writer = PdfWriter.getInstance(doc, fos);
		//PDF版本(默认1.4)
		writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
		//文档属性
		doc.addTitle("Title@wpixel");
		doc.addAuthor("Author@wpixel");
		doc.addSubject("Subject@wpixel");
		doc.addKeywords("Keywords@wpixel");
		doc.addCreator("Creator@wpixel");
		//页边空白
		doc.setMargins(40, 40, 40, 40);
		//打开文档
		doc.open();
		return doc;
	}

	//public static void createFile(HttpServletRequest request,HttpSession session,HttpServletResponse response,Document doc,long contractID) throws DocumentException {
	public static void createFile(Document doc,String contractIDStr) throws Exception {
		SignContractBO scBO = new SignContractBO();
		long contractID = Long.parseLong(contractIDStr);
		//查询合同甲方信息
		SellerInfoVO svo = null;

		List<MetaFilter> params2 = new ArrayList<MetaFilter>();
		params2.add(new MetaFilter("contractID", "=", contractID));
		List<MetaOrder> orders2 = new ArrayList<MetaOrder>();
		orders2.add(new MetaOrder("serial", MetaOrder.ASC));
		List<SellerInfoVO> sellerList = scBO.search(SellerInfoVO.class, params2, orders2, null);
		if(sellerList != null && sellerList.size() > 0){
			svo = sellerList.get(0);
		}


		doc.add(PdfFontUtils.getFont(1, "合作协议"));
		doc.add(PdfFontUtils.getFont(6, "甲方(卖方)：" + svo.getSellerName()));

		//设表的宽度为523f，一行37个字，一个字占2.7%

		doc.add(PdfFontUtils.getFont(6, "住所：" + svo.getSellerAddress()));
		doc.add(PdfFontUtils.getFont(6, " 邮编：" + svo.getSellerPostcode()));
		doc.add(PdfFontUtils.getFont(6, "营业执照号码：" + svo.getSellerBizcardcode()));
		doc.add(PdfFontUtils.getFont(6, " 资质证书号码：" + svo.getSellerCertcode()));
		doc.add(PdfFontUtils.getFont(6, "法定代表人：" + svo.getSellerDelegate()));
		doc.add(PdfFontUtils.getFont(6, " 联系电话：" + svo.getSellerDlgCall()));
		doc.add(PdfFontUtils.getFont(6, "委托代理人：" + svo.getSellerProxy()));
		doc.add(PdfFontUtils.getFont(6, " 联系电话：" + svo.getSellerProxyCall()));


		List<MetaFilter> params = new ArrayList<MetaFilter>();
		params.add(new MetaFilter("contractID", "=", contractID)); //添加条件，
		List<MetaOrder> orders = new ArrayList<MetaOrder>();
		orders.add(new MetaOrder("serial", MetaOrder.ASC)); //添加排序，按serial属性升序排序
		//查询合同乙方信息
		List<BuyerInfoVOFHE> list2 = scBO.search(BuyerInfoVOFHE.class, params, null, null); //查找条件：contractID=contractID，排序方式：按serial属性升序排序，分页

		//List<BuyerInfoVOFHE> list2 = cqDao.searchBuyerInfoFHE(String.valueOf(contractID));

		BuyerInfoVOFHE bvo = null;
		for (int i = 0; i < list2.size(); i++) {
			bvo = list2.get(i);
			doc.add(PdfFontUtils.getFont(2, "乙方" + i + "："));
			doc.add(PdfFontUtils.getFont(5, "姓名：" + bvo.getBuyerName()));
			doc.add(PdfFontUtils.getFont(5, "性别：" + bvo.getBuyerSex()));
			doc.add(PdfFontUtils.getFont(5, " 住址：" + bvo.getBuyerAddress()));
			doc.add(PdfFontUtils.getFont(5, "邮箱：" + bvo.getBuyerPostcode()));
			doc.add(PdfFontUtils.getFont(5, " 联系电话：" + bvo.getBuyerCall()));
		}

		//一、合作方式及条件
		doc.add(PdfFontUtils.getFont(2, "一、合作方式及条件"));
		doc.add(PdfFontUtils.getFont(5, "1.双方根据国家法律规定建立合作关系，双方严格遵守和执行国家各项方针政策和有关法律、法规和条例规定。 "));
		doc.add(PdfFontUtils.getFont(5, "2.双方严格按照《中华人民共和国招标投标法》及相关规定实施合作。 "));
		doc.add(PdfFontUtils.getFont(5, "3.双方本着密切配合、分工协作、保证质量、按期完成的原则，共同做好工作。 "));

		//二、权利义务
		doc.add(PdfFontUtils.getFont(2, "二、权利义务"));
		doc.add(PdfFontUtils.getFont(5, "1.双方根据国家法律规定建立合作关系，双方严格遵守和执行国家各项方针政策和有关法律、法规和条例规定。 "));
		doc.add(PdfFontUtils.getFont(5, "2.双方严格按照《中华人民共和国招标投标法》及相关规定实施合作。 "));
		doc.add(PdfFontUtils.getFont(5, "3.双方本着密切配合、分工协作、保证质量、按期完成的原则，共同做好工作。 "));

		//三、其他
		doc.add(PdfFontUtils.getFont(2, "三、其他"));
		doc.add(PdfFontUtils.getFont(5, "1.双方根据国家法律规定建立合作关系，双方严格遵守和执行国家各项方针政策和有关法律、法规和条例规定。 "));
		doc.add(PdfFontUtils.getFont(5, "2.双方严格按照《中华人民共和国招标投标法》及相关规定实施合作。 "));
		doc.add(PdfFontUtils.getFont(5, "3.自定义 "));

		PdfPTable table = new PdfPTable(2);
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		PdfPCell cell;
		cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "甲方：（盖章）")));
		cell.setColspan(1);
		cell.setBorder(0);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "乙方：（盖章）")));
		cell.setColspan(1);
		cell.setBorder(0);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "法定代表人或负责人签章")));
		cell.setColspan(1);
		cell.setBorder(0);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "法定代表人或负责人签章")));
		cell.setColspan(1);
		cell.setBorder(0);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "地址：")));
		cell.setColspan(1);
		cell.setBorder(0);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "地址：")));
		cell.setColspan(1);
		cell.setBorder(0);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "开户银行：")));
		cell.setColspan(1);
		cell.setBorder(0);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "开户银行：")));
		cell.setColspan(1);
		cell.setBorder(0);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "邮编：")));
		cell.setColspan(1);
		cell.setBorder(0);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "邮编：")));
		cell.setColspan(1);
		cell.setBorder(0);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "授权代理人：")));
		cell.setColspan(1);
		cell.setBorder(0);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "项目经理：")));
		cell.setColspan(1);
		cell.setBorder(0);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "电话：")));
		cell.setColspan(1);
		cell.setBorder(0);
		table.addCell(cell);
		cell.setBorder(Rectangle.NO_BORDER);
		cell = new PdfPCell(new Phrase(PdfFontUtils.getFont(5, "电话：")));
		cell.setColspan(1);
		cell.setBorder(0);
		table.addCell(cell);
		doc.add(table);
	}

}